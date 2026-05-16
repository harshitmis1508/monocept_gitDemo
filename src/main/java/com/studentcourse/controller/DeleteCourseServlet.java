package com.studentcourse.controller;

import com.studentcourse.dao.CourseDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DeleteCourseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        if (!isLoggedIn(req)) { res.sendRedirect(req.getContextPath() + "/login"); return; }

        String idStr = req.getParameter("id");
        if (idStr == null || idStr.trim().isEmpty()) {
            res.sendRedirect(req.getContextPath() + "/courses?error=Invalid+course+ID");
            return;
        }

        try {
            int id = Integer.parseInt(idStr.trim());
            CourseDAO dao = new CourseDAO();

            if (dao.hasCourseActiveRegistration(id)) {
                res.sendRedirect(req.getContextPath() +
                    "/courses?error=Cannot+delete+course+with+active+registrations");
                return;
            }

            if (dao.deleteCourse(id)) {
                res.sendRedirect(req.getContextPath() + "/courses?success=Course+deleted+successfully");
            } else {
                res.sendRedirect(req.getContextPath() + "/courses?error=Could+not+delete+course");
            }

        } catch (NumberFormatException e) {
            res.sendRedirect(req.getContextPath() + "/courses?error=Invalid+course+ID");
        }
    }

    private boolean isLoggedIn(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return session != null && session.getAttribute("loggedInUser") != null;
    }
}
