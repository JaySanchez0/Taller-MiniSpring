package com.server.socket;

public class FunctionRequest {

    private String path;

    private String method;

    /**
     *
     * @param path url asociada al la operacion
     * @param method metodo http GET POST PUT DELETE
     */
    public FunctionRequest(String path,String method){
        this.path = path;
        this.method = method;
    }

    /**
     *
     * @return Devuelve el path
     */
    public String getPath() {
        return path;
    }

    /**
     * Actualiza la ruta asociada a un procedimiento
     * @param path string correspondiente a la ruta dentro de resources
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     *
     * @return el metodo http al que correponde uan operacion
     */
    public String getMethod() {
        return method;
    }

    /**
     *
     * @param method metodo http asociado al path url
     */
    public void setMethod(String method) {
        this.method = method;
    }

    /**
     *
     * @param obj objeto a comparar
     * @return si son iguales
     */
    @Override
    public boolean equals(Object obj) {
        if(!(obj instanceof  FunctionRequest)) return false;
        FunctionRequest re = (FunctionRequest) obj;
        return re.getMethod().equals(method) && re.getPath().equals(path);
    }

    @Override
    public int hashCode() {
        return 10;
    }
}
