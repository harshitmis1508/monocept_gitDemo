package com.studentcourse.controller;

import com.studentcourse.dao.StudentDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DeleteStudentServlet extends HttpServlet {

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
            int id = Integer.parseInt(idStr.trim());
            StudentDAO dao = new StudentDAO();

            // Check if student has registrations
            if (dao.isStudentRegistered(id)) {
                res.sendRedirect(req.getContextPath() +
                    "/students?error=Cannot+delete+student+who+is+registered+in+a+course");
                return;
            }

            if (dao.deleteStudent(id)) {
                res.sendRedirect(req.getContextPath() + "/students?success=Student+deleted+successfully");
            } else {
                res.sendRedirect(req.getContextPath() + "/students?error=Could+not+delete+student");
            }

        } catch (NumberFormatException e) {
            res.sendRedirect(req.getContextPath() + "/students?error=Invalid+student+ID");
        }
    }

    private boolean isLoggedIn(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return session != null && session.getAttribute("loggedInUser") != null;
    }
}
