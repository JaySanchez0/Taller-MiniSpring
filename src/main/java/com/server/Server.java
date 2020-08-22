package com.server;

import com.server.socket.SocketApp;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class Server
{
    public static void main(String[] args){
        try {
            SocketApp app = new SocketApp();
            app.setStaticFolder("static");
            app.addFunction("GET","/app",(request)->request.getParameters().toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
