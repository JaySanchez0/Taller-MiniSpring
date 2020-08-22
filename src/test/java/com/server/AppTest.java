package com.server;

import com.server.request.Request;
import org.junit.Test;

import java.io.File;

import static junit.framework.Assert.*;

/**
 * Unit test for simple App.
 */
public class AppTest{


    @Test
    public void shouldResolvARootPath(){
        FileResolve resolv = new FileResolve();
        File f = resolv.getFile("static/");
        System.out.println(f);
        assertNotNull(f);
    }

    @Test
    public void shouldBeFindAFile(){
        FileResolve resolv = new FileResolve();
        File f = resolv.getFile("static/style.css");
        assertNotNull(f);
    }

    @Test
    public void shouldBeGiveNullIfAFileUnexist(){
        FileResolve resolv = new FileResolve();
        File f = resolv.getFile("/test/app.txt");
        assertNull(f);
    }

    @Test
    public void shouldBeBuildACorrectRequest(){
        String cad ="GET /app?id=3 HTTP/1.1\r\n\r\n";
        Request rq = Request.build(cad);
        assertEquals(rq.getParameter("id"),"3");

    }


}
