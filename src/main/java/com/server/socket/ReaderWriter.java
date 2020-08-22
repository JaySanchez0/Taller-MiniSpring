package com.server.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class ReaderWriter {

    private BufferedReader in;

    private PrintWriter out;

    /**
     * Read Writer sobre el socket
     */
    public ReaderWriter(){

    }

    /**
     *
     * @return obtiene la solicitud http
     */
    public String read(){
        try {
            String cad;
            String response = "";
            while((cad = in.readLine()).length() != 0) {
                response = response + cad + "\n";
                //System.out.println(cad);
                if (!in.ready()) break;
            }
            return response;
        } catch (IOException e) {
            return null;
        }
    }

    /**
     * Entrega al cliente el resultado de la operacion http
     * @param content contenido a entregar al cliente
     */
    public void write(String content){
        try {
            out.println(content);
            out.close();
            in.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Obtiene la entrada y salida asociadas al socket
     * @param socket el cliente a quien vamos a responder
     */
    public void setSocket(Socket socket){
        try {
            out = new PrintWriter(socket.getOutputStream(),true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
