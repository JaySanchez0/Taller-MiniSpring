package com.server.request;

import java.util.HashMap;
import java.util.Map;

public class Request {

    private String method;

    private String path;

    private Map<String,String> parameters;

    private String body;

    /**
     * Objeto que representa los datos generados por la peticion http
     */
    public Request(){

    }

    /**
     *
     * @return devuelve el metodo http GET POST DELETE PUT
     */
    public String getMethod() {
        return method;
    }

    /**
     *
     * @param method nuevo metodo http al que se le va asociar el path
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     *
     * @return ruta url a responder
     */
    public String getPath() {
        return path;
    }

    /**
     *
     * @param path nueva url a responder
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     *
     * @return todos los parametros pasados mediante la url
     */
    public Map<String, String> getParameters() {
        return parameters;
    }

    /**
     *
     * @param param parametro a buscar
     * @return string resultado como parametro
     */
    public String getParameter(String param){
        return parameters.get(param);
    }

    /**
     *
     * @param parameters nueva lista de parametros asociados a la url
     */
    public void setParameters(Map<String, String> parameters) {
        this.parameters = parameters;
    }

    /**
     *
     * @return cuerdo de la peticion
     */
    public String getBody() {
        return body;
    }

    /**
     *
     * @param body nuevo cuerpo de la peticion http POST DELETE UPDATE
     */
    public void setBody(String body) {
        this.body = body;
    }

    /**
     *
     * @return representacion a manera de string del cuerpo del request
     */
    public String toString(){
        return "{path:"+path+",method:"+method+"}";
    }

    /**
     *
     * @param request string que corresponde a la solicitud http
     * @return Objeto request que representa la peticion
     */
    public static Request build(String request){
        String[] li = request.split("\n")[0].split(" ");
        Request rq = new Request();
        String method  = li[0];
        String[] url = li[1].replace("?"," ").split(" ");
        String path = url[0];
        String query = null;
        if(url.length==2) query = url[1];
        rq.setParameters(buildParams(query));
        rq.setMethod(method);
        rq.setPath(path);
        return rq;
    }

    private static HashMap<String,String> buildParams(String query){
        HashMap<String,String> map = new HashMap<>();
        if(query==null) return map;
        String[] params = query.split("&&");
        for(String sq: params){
            String[] itemQuery = sq.split("=");
            map.put(itemQuery[0],itemQuery[1]);
            System.out.println(itemQuery[0]+" "+itemQuery[1]);
        }
        return map;
    }

}
