package com.studentcourse.controller;

import com.studentcourse.dao.CourseDAO;
import com.studentcourse.model.Course;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class EditCourseServlet extends HttpServlet {

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
            int courseId = Integer.parseInt(idStr.trim());
            CourseDAO dao = new CourseDAO();
            Course course = dao.getCourseById(courseId);

            if (course == null) {
                res.sendRedirect(req.getContextPath() + "/courses?error=Course+not+found");
                return;
            }

            req.setAttribute("course", course);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/course-edit.jsp");
            rd.forward(req, res);

        } catch (NumberFormatException e) {
            res.sendRedirect(req.getContextPath() + "/courses?error=Invalid+course+ID");
        }
    }

    private boolean isLoggedIn(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return session != null && session.getAttribute("loggedInUser") != null;
    }
}
