package com.server.socket;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.Socket;
import java.nio.ByteBuffer;

public class ReaderWriter {

    private BufferedReader in;

    private PrintWriter out;

    /**
    /**
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
     *
     * @param img imagen a entregar al cliente
     * @param ext extencion de la imagen
     * @param client el socket a quien tengo que entregar la imagen
     */
    public void write(BufferedImage img, String ext, Socket client,String header){
        try {
            ByteArrayOutputStream by = new ByteArrayOutputStream();
            ext = ext.substring(1).toLowerCase();
            ImageIO.write(img,ext,by);
            out.write(header);
            client.getOutputStream().write(by.toByteArray());
            client.getOutputStream().flush();
            client.getOutputStream().close();
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
