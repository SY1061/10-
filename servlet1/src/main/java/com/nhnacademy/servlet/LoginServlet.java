package com.nhnacademy.servlet;

import lombok.extern.slf4j.Slf4j;

import javax.servlet.*;
import javax.servlet.annotation.WebInitParam;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;
@Slf4j
@WebServlet(name = "loginServlet", urlPatterns = "/login", initParams = {
        @WebInitParam(name = "id", value = "sunny"),
        @WebInitParam(name = "pwd", value = "1234")
})
public class LoginServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String initParamId = getServletConfig().getInitParameter("id");
        String initParamPwd = getServletConfig().getInitParameter("pwd");

        String id = req.getParameter("id");
        String pwd = req.getParameter("pwd");

        if(initParamId.equals(id) && initParamPwd.equals(pwd)){
            HttpSession session = req.getSession();
            session.setAttribute("id", id);

            resp.sendRedirect("/login");
        }
        else{
            RequestDispatcher rd = req.getRequestDispatcher("/login.html");
            rd.forward(req,resp);
//            resp.sendRedirect("/login.html");
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        resp.setCharacterEncoding("UTF-8");
        if(Objects.isNull(session)){
            RequestDispatcher rd = req.getRequestDispatcher("/login.html");
            rd.forward(req,resp);
//            resp.sendRedirect("/login.html");
        }
        else{
            resp.setContentType("text/html");
            resp.getWriter().println("login success: id=" + session.getAttribute("id"));
            resp.getWriter().println("<br />");
            resp.getWriter().println("<a href='/logout'>logout</a>");
        }
    }
}
