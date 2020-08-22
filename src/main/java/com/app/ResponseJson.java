package com.app;

import com.model.People;
import java.util.List;

public class ResponseJson {
    /**
     *
     * @param object un objeto persona
     * @return representacion a manera de json del objeto persona
     */
    public static String json(People object){
        return "{\"name\":\""+object.getName()+"\",\"age\":"+object.getAge()+",\"country\":\""+object.getCountry()+"\",\"id\":"+object.getId()+"}";
    }

    /**
     *
     * @param peoples lista de objetos person
     * @return representacion a manera de json de la lista de personas
     */
    public static String json(List<People> peoples){
        String li = "[";
        for(People p:peoples){
            li = li+json(p)+",";
        }
        return li.substring(0,li.length()-1)+"]";
    }
}
