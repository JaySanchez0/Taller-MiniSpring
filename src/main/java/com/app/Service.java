package com.app;

import com.data.DBClient;
import com.model.People;

import java.util.List;

public class Service {

    private DBClient client;

    public Service(){
        client = new DBClient();
    }

    public List<People> getPeoples(){
        return client.peoples();
    }

    public void addPeople(People people){
        client.save(people);
    }

}
