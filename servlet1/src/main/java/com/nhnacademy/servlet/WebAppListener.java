package com.nhnacademy.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import java.io.*;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Paths;

@Slf4j
@WebListener
public class WebAppListener implements ServletContextListener {
    @Override
    public void contextInitialized(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String counterFileName = servletContext.getInitParameter("counterFileName");
        String counterFilePath = "/WEB-INF/classes/"+counterFileName;
        int counter = 0;
        // getResourceAsStream : 읽어서 inputStream 형태로 만듬.
        try (DataInputStream dis = new DataInputStream(servletContext.getResourceAsStream(counterFilePath))) {
            counter = dis.readInt();
        }
        catch (Exception ex){
            log.error("", ex);
        }

        servletContext.setAttribute("counter", counter);
    }

    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        ServletContext servletContext = sce.getServletContext();
        String counterFileName = servletContext.getInitParameter("counterFileName");
        String counterFilePath = "/WEB-INF/classes/"+counterFileName;

        int counter = (int) servletContext.getAttribute("counter");

        try (OutputStream os = Files.newOutputStream(Paths.get(servletContext.getResource(counterFilePath).toURI()));
             DataOutputStream dos = new DataOutputStream(os)) {
            dos.writeInt(counter);
        }
        catch(IOException | URISyntaxException ex){
            log.error("", ex);
        }

//        File file = new File(servletContext.getResource(counterFilePath).toURI());
//        FileOutputStream fos = new FileOutputStream(file);
//        DataOutputStream dos = new DataOutputStream(fos);
    }
}
