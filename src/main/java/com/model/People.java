package com.model;


public class People {
    private int id;
    private String name;
    private int age;
    private String country;

    public People(int id,String name,int age,String country){
        this(name,age,country);
        this.id=id;
    }

    public People(String name,int age,String country){
        this.name = name;
        this.age = age;
        this.country = country;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "{\"name\":\""+name+"\",\"age\":"+age+"}";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
