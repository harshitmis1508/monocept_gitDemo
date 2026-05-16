package com.studentcourse.controller;

import com.studentcourse.dao.StudentDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ViewStudentsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        if (!isLoggedIn(req)) { res.sendRedirect(req.getContextPath() + "/login"); return; }

        StudentDAO dao = new StudentDAO();
        req.setAttribute("studentList", dao.getAllStudents());

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/student-list.jsp");
        rd.forward(req, res);
    }

    private boolean isLoggedIn(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return session != null && session.getAttribute("loggedInUser") != null;
    }
}
