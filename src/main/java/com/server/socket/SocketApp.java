package com.server.socket;

import com.server.FileResolve;
import com.server.request.HTTPBuilder;
import com.server.request.Request;
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
     *
     * @param client el socket que se conecto a nuestro servidor
     * @param request los datos relevantes de la solictud http
     * @throws IOException Si falla la lectura de archivos
     */
    public void response(Socket client,Request request) throws IOException {
        File file = fileResolve.getFile(staticFolder+request.getPath());
        Function<Request,String> f = procedures.get(new FunctionRequest(request.getPath(),request.getMethod()));
        if(staticFolder!=null && request.getMethod().equals("GET") && file!=null && file.exists()){
            String content = fileResolve.readFile(file);
            //System.out.println(HTTPBuilder.response(200,content));
            readerWriter.write(HTTPBuilder.response(200,content));
        }else if(f!=null){
            String resp = f.apply(request);
            readerWriter.write(HTTPBuilder.response(200,resp));
        }else{
            readerWriter.write(HTTPBuilder.response(404,"<h1>Recurso no encontrado</h1>"));
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
