package com.app.Controller;

import com.app.ResponseJson;
import com.app.Service;
import com.model.People;
import com.server.RequestMapping;

import java.util.HashMap;

public class AppController {

    Service service = new Service();
    /**
     *
     * @param params parametros de la query
     * @return suma entre 2 numeros
     */
    @RequestMapping("/suma")
    public String suma(HashMap<String,String> params){
        int a = Integer.parseInt(params.get("a"));
        int b = Integer.parseInt(params.get("b"));
        return String.valueOf(a+b);
    }

    /**
     *
     * @param params parametros query
     * @return multiplicacion
     */
    @RequestMapping("/mult")
    public String mult(HashMap<String,String> params){
        int a = Integer.parseInt(params.get("a"));
        int b = Integer.parseInt(params.get("b"));
        return String.valueOf(a*b);
    }

    /**
     *
     * @param params paremetros query
     * @return array de personas
     */
    @RequestMapping("/peoples")
    public String peoples(HashMap<String,String> params){
        return ResponseJson.json(service.getPeoples());
    }

    /**
     *
     * @param params parametros query
     * @return string vacio
     */
    @RequestMapping("/addPeople")
    public String addPeople(HashMap<String,String> params){
        service.addPeople(new People(params.get("name"),Integer.parseInt(params.get("age")), params.get("country")));
        return "";
    }
}
