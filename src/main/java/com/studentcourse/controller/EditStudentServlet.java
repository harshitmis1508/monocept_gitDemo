package com.studentcourse.controller;

import com.studentcourse.dao.StudentDAO;
import com.studentcourse.model.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class EditStudentServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        if (!isLoggedIn(req)) { res.sendRedirect(req.getContextPath() + "/login"); return; }

        String idStr = req.getParameter("id");
        if (idStr == null || idStr.trim().isEmpty()) {
            res.sendRedirect(req.getContextPath() + "/students?error=Invalid+student+ID");
            return;
        }

        try {
            int studentId = Integer.parseInt(idStr.trim());
            StudentDAO dao = new StudentDAO();
            Student student = dao.getStudentById(studentId);

            if (student == null) {
                res.sendRedirect(req.getContextPath() + "/students?error=Student+not+found");
                return;
            }

            req.setAttribute("student", student);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/student-edit.jsp");
            rd.forward(req, res);

        } catch (NumberFormatException e) {
            res.sendRedirect(req.getContextPath() + "/students?error=Invalid+student+ID");
        }
    }

    private boolean isLoggedIn(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return session != null && session.getAttribute("loggedInUser") != null;
    }
}
