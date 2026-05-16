package com.studentcourse.controller;

import com.studentcourse.dao.CourseDAO;
import com.studentcourse.dao.StudentDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class RegistrationFormServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        if (!isLoggedIn(req)) { res.sendRedirect(req.getContextPath() + "/login"); return; }

        StudentDAO sDao = new StudentDAO();
        CourseDAO  cDao = new CourseDAO();

        req.setAttribute("studentList", sDao.getAllStudents());
        req.setAttribute("courseList",  cDao.getAllCourses());

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/registration-form.jsp");
        rd.forward(req, res);
    }

    private boolean isLoggedIn(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return session != null && session.getAttribute("loggedInUser") != null;
    }
}
