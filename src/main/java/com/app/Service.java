package com.app;

import com.data.DBClient;
import com.model.People;

import java.util.List;

public class Service {

    private DBClient client;

    /**
     * Servicio de personas
     */
    public Service(){
        client = new DBClient();
    }

    /**
     *
     * @return lista de objetos persona
     */
    public List<People> getPeoples(){
        return client.peoples();
    }

    /**
     *
     * @param people persona a almacenar
     */
    public void addPeople(People people){
        client.save(people);
    }

}
