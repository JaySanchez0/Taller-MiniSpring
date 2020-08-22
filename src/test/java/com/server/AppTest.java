package com.server;

import com.server.request.Request;
import org.junit.Test;
import java.io.File;
import static junit.framework.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest{

    /**
     * Valida asocie el / al archivo index.html
     */
    @Test
    public void shouldResolvARootPath(){
        FileResolve resolv = new FileResolve();
        File f = resolv.getFile("static/");
        System.out.println(f);
        assertNotNull(f);
    }

    /**
     * Valida encuentre un archivo existente
     */
    @Test
    public void shouldBeFindAFile(){
        FileResolve resolv = new FileResolve();
        File f = resolv.getFile("static/style.css");
        assertNotNull(f);
    }

    /**
     * Valida que deria dar null a un archivo inexistente
     */
    @Test
    public void shouldBeGiveNullIfAFileUnexist(){
        FileResolve resolv = new FileResolve();
        File f = resolv.getFile("/test/app.txt");
        assertNull(f);
    }

    /**
     * Valida mapee correctamente parametros de la url
     */
    @Test
    public void shouldBeBuildACorrectRequest(){
        String cad ="GET /app?id=3&&ciudad=canada HTTP/1.1\r\n\r\n";
        Request rq = Request.build(cad);
        assertEquals(rq.getParameter("id"),"3");
        assertEquals(rq.getParameter("ciudad"),"canada");

    }

    /**
     * Valida mapee correctamente el path de la url
     */
    @Test
    public void shouldBeMapingAPath(){
        String cad ="GET /ciudad?id=3&&ciudad=canada HTTP/1.1\r\n\r\n";
        String cad2 ="GET /index.html HTTP/1.1\r\n\r\n";
        Request rq = Request.build(cad);
        Request rq2 = Request.build(cad2);
        assertEquals("/ciudad",rq.getPath());
        assertEquals("/index.html",rq2.getPath());
    }

    @Test
    public void shouldBeMappingMethodHttp(){
        String cad ="GET /concierto?id=3&&ciudad=nnn HTTP/1.1\r\n\r\n";
        String cad2 ="POST /index.html HTTP/1.1\r\n\r\n";
        Request rq = Request.build(cad);
        Request rq2 = Request.build(cad2);
        assertEquals("GET",rq.getMethod());
        assertEquals("POST",rq2.getMethod());
    }


}
