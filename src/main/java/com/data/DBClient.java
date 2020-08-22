package com.data;


import com.model.People;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.util.ArrayList;
import java.util.List;

public class DBClient {

    private Connection con;

    /**
     * Cliente de la base de datos
     */
    public DBClient(){
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://ec2-50-16-198-4.compute-1.amazonaws.com:5432/dajvjt7vhtg3ne";
            String user = "rnurdvvtulhgoq";
            String password = "2ada64fc892dcdf555d3a14de6d9162b13dc39a4e1c2a7c2b6fe253aac85dcf2";
            con = DriverManager.getConnection(url,user,password);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }

    }

    /**
     *
     * @return la lista de todas las personas almacenadas en la base de datos
     */
    public List<People> peoples(){
        ArrayList<People> peoples=null;
        try {
            peoples = new ArrayList<>();
            ResultSet set = con.prepareStatement("select * from people").executeQuery();
            while(set.next()){
                int id = Integer.parseInt(set.getString(1));
                int age = Integer.parseInt(set.getString(2));
                String country = set.getString(4);
                String name = set.getString(3);
                People people = new People(id,name,age,country);
                peoples.add(people);

            }
        } catch (SQLException throwables) {
            //throwables.printStackTrace();
        }
        return peoples;
    }

    /**
     * Guarda en la base de datos
     * @param people objeto persona a almacenar
     */
    public void save(People people){
        try {
            PreparedStatement st = con.prepareStatement("insert into people(age,name,country) values(?,?,?)");
            st.setInt(1,people.getAge());
            st.setString(2,people.getName());
            st.setString(3,people.getCountry());
            st.execute();
            con.commit();;


        } catch (SQLException throwables) {
            //throwables.printStackTrace();
        }
    }
}
