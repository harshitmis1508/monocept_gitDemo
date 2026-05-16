package com.studentcourse.controller;

import com.studentcourse.dao.CourseDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ViewCoursesServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        if (!isLoggedIn(req)) { res.sendRedirect(req.getContextPath() + "/login"); return; }

        CourseDAO dao = new CourseDAO();
        req.setAttribute("courseList", dao.getAllCourses());

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/course-list.jsp");
        rd.forward(req, res);
    }

    private boolean isLoggedIn(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return session != null && session.getAttribute("loggedInUser") != null;
    }
}
