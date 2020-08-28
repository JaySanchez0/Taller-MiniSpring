package com.app;

import com.model.People;
import com.server.RequestMapping;
import com.server.request.Request;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;

import static com.app.ResponseJson.json;
import static com.server.Server.*;

public class App {
    private static Service service = new Service();

    private static void createGetMethod(Method method,String path,Object inst){
        get(path,(request)->{
            try{
                return method.invoke(inst,new Object[]{request.getParameters()}).toString();
            }catch (Exception e){
                e.printStackTrace();
                return "";
            }
        });
    }
    /**
     * Controlador Rutas
     * @param args
     */
    public static void main(String[] args){
        port(getPort());
        staticFiles("static");
        //Nueva Implementacion
        //args = new String[]{"com.app.Controller.AppController"};
        if(args!=null && args.length>0){
            try {
                Class<?> clase = Class.forName(args[0]);
                Object inst = clase.newInstance();
                for(Method m:clase.getMethods()){
                    if(m.getAnnotation(RequestMapping.class)!=null){
                        String path = m.getAnnotation(RequestMapping.class).value();
                        //System.out.println(path);
                        createGetMethod(m,path,inst);
                    }
                }
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
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
