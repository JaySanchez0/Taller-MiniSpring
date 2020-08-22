package com.server.request;

public class HTTPBuilder {
    /**
     * Añade el encabezado http
     * @param status estado de la solicitud
     * @param text contenido a ser entregado al cliente
     * @return
     */
    public static String response(int status,String text){
        if(status==404) return "HTTP/1.1 404 NOT FOUND\r\n\r\n"+text;
        else if(status==200)return "HTTP/1.1 200 OK\r\nContent-Type: text/html\r\n\r\n"+text;
        return null;
    }

    public static String responseImage(String ext,int length){
        //ext = ext.substring(1).toLowerCase();
        return "HTTP/1.1 200 OK\r\nContent-Type: image/"+ext+"\r\nContent-Length:"+length+"\r\n\r\n";
    }

}
