package com.nhnacademy.servlet;

import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Optional;

public class ServletContextServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.service(req, resp);
    }

    @Override
    public void destroy() {
        super.destroy();
    }

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ServletContext servletContext = getServletConfig().getServletContext();

        resp.setContentType("text/plain");

        Integer counter = Optional.ofNullable((Integer) getServletContext().getAttribute("allCounter"))
                .orElse(0);
        getServletContext().setAttribute("allCounter", ++counter);

        try{
            PrintWriter out = resp.getWriter();
            out.println(servletContext.getContextPath());
            out.println(servletContext.getMajorVersion());
            out.println(servletContext.getMinorVersion());
            out.println(servletContext.getEffectiveMajorVersion());
            out.println(servletContext.getEffectiveMinorVersion());
            out.println(servletContext.getRealPath("/context"));
            out.println(getServletContext().getInitParameter("url"));
            out.println(counter);
        }
        catch(Exception e){

        }
    }
}
