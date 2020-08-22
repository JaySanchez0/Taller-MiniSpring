package com.app;

import static com.app.ResponseJson.json;
import static com.server.Server.*;

public class App {
    private static Service service = new Service();

    public static void main(String[] args){
        port(getPort());
        staticFiles("static");
        get("/peoples",(request)->json(service.getPeoples()));
    }

    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 80;
    }

}
