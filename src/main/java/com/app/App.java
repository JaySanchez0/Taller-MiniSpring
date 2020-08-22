package com.app;

import com.model.People;

import static com.app.ResponseJson.json;
import static com.server.Server.*;

public class App {
    private static Service service = new Service();

    /**
     * Controlador Rutas
     * @param args
     */
    public static void main(String[] args){
        port(getPort());
        staticFiles("static");
        get("/peoples",(request)->json(service.getPeoples()));
        get("/addPeople",(request)->{
            //System.out.println(request.getParameter("name")+" age:"+request.getParameter("age")+" country:"+request.getParameter("country"));
            //System.out.println();
           service.addPeople(new People(request.getParameter("name"),Integer.parseInt(request.getParameter("age")), request.getParameter("country")));
           return "";
        });
    }

    /**
     *
     * @return puerto por el cual se ejecutara el servidor
     */
    static int getPort() {
        if (System.getenv("PORT") != null) {
            return Integer.parseInt(System.getenv("PORT"));
        }
        return 80;
    }

}
