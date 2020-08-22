package com.model;


public class People {
    private int id;
    private String name;
    private int age;
    private String country;

    /**
     *
     * @param id id de la base de datos
     * @param name nombre de la persona
     * @param age edad de la persona
     * @param country pais donde vive la persona
     */
    public People(int id,String name,int age,String country){
        this(name,age,country);
        this.id=id;
    }

    /**
     *
     * @param name nombre de la persona
     * @param age edad de la persona
     * @param country pais donde vive la persona
     */
    public People(String name,int age,String country){
        this.name = name;
        this.age = age;
        this.country = country;
    }

    /**
     *
     * @return nombre de la persona
     */
    public String getName() {
        return name;
    }

    /**
     * Actualiza el nombre de la persona
     * @param name nombre de la persona
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     *
     * @return edad de la persona
     */
    public int getAge() {
        return age;
    }

    /**
     * Actauliza la edad de la persona
     * @param age edad de la persona
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     *
     * @return ciudad donde vive la persona
     */
    public String getCountry() {
        return country;
    }

    /**
     * Actualiza la ciudad donde vive la persona
     * @param country ciudad donde vive la persona
     */
    public void setCountry(String country) {
        this.country = country;
    }

    @Override
    public String toString() {
        return "{\"name\":\""+name+"\",\"age\":"+age+"}";
    }

    /**
     *
     * @return identificador de la persona
     */
    public int getId() {
        return id;
    }

    /**
     * Actualiza el identificador de la persona
     * @param id identificador de la persona
     */
    public void setId(int id) {
        this.id = id;
    }
}
