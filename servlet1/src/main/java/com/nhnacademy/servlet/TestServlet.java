package com.nhnacademy.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

@Slf4j
public class TestServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.error("service() called");
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        log.error("destroy() called");
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        log.error("init() called");
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Integer counter = Optional.ofNullable((Integer) getServletContext().getAttribute("allCounter"))
                .orElse(0);
        getServletContext().setAttribute("allCounter", ++counter);

        String title = getServletConfig().getInitParameter("title");
        String name = getServletConfig().getInitParameter("name");
        resp.getWriter().println("hello " + title + name);
        resp.getWriter().println(getServletContext().getInitParameter("url"));
        resp.getWriter().println(counter);
    }
}
