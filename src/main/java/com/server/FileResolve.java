package com.server;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Paths;

public class FileResolve {
    /**
     * Lectura y escritura de archivos
     */
    public FileResolve(){

    }

    /**
     *
     * @param path url del archivo a buscar
     * @return objeto de tipo file correpondiente al path dentro de resources
     */
    public File getFile(String path){
        File file = null;
        try {
            if(path.charAt(path.length()-1)=='/') path=path+"index.html";
            if(getClass().getClassLoader().getResource(path)==null) return null;
            file = Paths.get(getClass().getClassLoader().getResource(path).toURI()).toFile();
            return file;
        } catch (URISyntaxException e) {
            return null;
        }
    }

    /**
     *
     * @param file file asociado al archivo a traer desde resources
     * @return contenido como string del archivo dentro de resources
     */
    public String readFile(File file){
        try {
            BufferedReader read = new BufferedReader(new FileReader(file));
            String res="";
            String tmp;
            while ((tmp=read.readLine())!=null){
                res=res+tmp+"\n";
            }
            read.close();
            return res;
        } catch (FileNotFoundException e) {
            return "";
        } catch (IOException e) {
            return "";
        }

    }

    public BufferedImage getImage(File file){
        try {
            BufferedImage  img = ImageIO.read(file);
            return img;
        } catch (IOException e) {
            return null;
        }
    }

}
