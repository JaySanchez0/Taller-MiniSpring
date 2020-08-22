package com.app;

import com.model.People;

import java.util.ArrayList;
import java.util.List;

public class ResponseJson {

    public static String json(People object){
        return "{\"name\":\""+object.getName()+"\",\"age\":"+object.getAge()+",\"country\":\""+object.getCountry()+"\",\"id\":"+object.getId()+"}";
    }

    public static String json(List<People> peoples){
        String li = "[";
        for(People p:peoples){
            li = li+json(p)+",";
        }
        return li.substring(0,li.length()-1)+"]";
    }
}
