package com.nhnacademy.servlet;

import com.nhnacademy.domain.Student;
import com.nhnacademy.domain.StudentRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class StudentRegisterController implements Command{
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        resp.setContentType("text/html");
//        resp.setCharacterEncoding("UTF-8");
//        RequestDispatcher rd = req.getRequestDispatcher("/studentRegister.jsp");
//        rd.forward(req, resp);
//    }

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp){
        Student student = new Student(
                req.getParameter("id"),
                req.getParameter("name"),
                req.getParameter("gender"),
                Integer.parseInt(req.getParameter("age")));

        StudentRepository studentRepository = (StudentRepository) req.getServletContext().getAttribute("studentRepository");
        studentRepository.addStudent(student);

        return "redirect:/student/view.do?id="+ student.getId();
//        resp.sendRedirect("/student/view?id=" +student.getId());
    }
}
