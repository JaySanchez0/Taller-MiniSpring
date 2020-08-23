package com.server.socket;

import com.server.FileResolve;
import com.server.request.HTTPBuilder;
import com.server.request.Request;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.HashMap;
import java.util.function.Function;

public class SocketApp extends ServerSocket implements Runnable {

    private ReaderWriter readerWriter;

    private FileResolve fileResolve;

    private boolean isRunning;

    private String staticFolder = null;

    private HashMap<FunctionRequest,Function<Request,String>> procedures = new HashMap<>();

    private Thread thread;

    private static SocketApp app;

    public static SocketApp getInstance(int port){
        if(app==null) {
            try {
                app = new SocketApp(port);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return app;
    }

    /**
     *
     * @throws IOException si el puerto no esta disponible
     */
    private SocketApp(int port) throws IOException {
        super(port);
        readerWriter = new ReaderWriter();
        fileResolve = new FileResolve();
        isRunning = true;
        thread = new Thread(this);
        thread.start();
        System.out.println("Server Listening ........");
    }

    private SocketApp() throws IOException {
        this(80);
    }

    /**
     *
     * @param client el socket que ejecuto la solicitud
     * @return un objeto request asociado a la peticion http
     */
    public Request read(Socket client){
        String read = readerWriter.read();
        Request rq = Request.build(read);
        //System.out.println(rq.toString());
        return rq;
    }

    /**
     * Completa el tamaño del path para poder validar si tiene alguna estension
     * @param path
     * @return
     */
    private boolean validTextFormat(String path){
        String tmp ="xxxxxxxxxxx"+path;
        return tmp.substring(tmp.length()-5).equals(".html") ||
                tmp.substring(tmp.length()-3).equals(".js") ||
                tmp.substring(tmp.length()-4).equals(".css");
    }

    /**
     *
     * @param client el socket que se conecto a nuestro servidor
     * @param request los datos relevantes de la solictud http
     * @throws IOException Si falla la lectura de archivos
     */
    public void response(Socket client,Request request) throws IOException {
        File file = fileResolve.getFile(staticFolder+request.getPath());
        String path = "";
        if(file!=null) path = file.getPath();
        //Extension del archivo a cargar
        String ext = "";
        if(path!=null && path.length()>=4) ext = path.substring(path.length()-4);
        //Funcion asociada al path
        Function<Request,String> f = procedures.get(new FunctionRequest(request.getPath(),request.getMethod()));
        if(ext!=null && validTextFormat(path) && staticFolder!=null && request.getMethod().equals("GET") && file!=null){
            String content = fileResolve.readFile(file);
            readerWriter.write(HTTPBuilder.response(200,content,ext));
        }else if(ext != null && (ext.equals(".PNG") || ext.equals(".JPG")) && file!=null && request.getMethod().equals("GET")){
            readerWriter.write(fileResolve.getImage(file),ext,client);
        }else if(f!=null){
            //System.out.println("Entro funcion");
            readerWriter.write(HTTPBuilder.response(200,f.apply(request),".txt"));
        }else{
            readerWriter.write(HTTPBuilder.response(404,"<h1>Recurso no encontrado</h1>","txt"));
        }
        client.close();
    }

    /**
     * Recibe la conexion de un socket y devuelve la respuesta correspondiente
     */
    public void loadProcess(){
        try{
            Socket accept = accept();
            readerWriter.setSocket(accept);
            //System.out.println("------------- Conexion -----------------");
            Request data = read(accept);
            //System.out.println("------------------ Leyo data --------------------");
            response(accept,data);
            //System.out.println("-------------- Respondio ----------------------");
            accept.close();
        } catch(Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Ejecuta en un hilo las peticiones consecutivas
     */
    public void run() {
        while(isRunning){
            loadProcess();
        }
    }

    /**
     *
     * @param folder nombre del folder con el contenido estatico
     */
    public void setStaticFolder(String folder){
        this.staticFolder = folder;
    }

    /**
     *
     * @param method metodo http GET POST PUT DELETE ....
     * @param path url a la cual deberia reponder
     * @param function operacion a ejecutar para el path
     */
    public void addFunction(String method,String path, Function<Request,String> function){
        procedures.put(new FunctionRequest(path,method),function);
    }

    /**
     * Detiene el servidor
     */
    public void stop(){
        thread.stop();
    }
}
