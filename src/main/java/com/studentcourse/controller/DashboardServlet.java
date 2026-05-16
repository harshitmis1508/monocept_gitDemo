package com.studentcourse.controller;

import com.studentcourse.dao.CourseDAO;
import com.studentcourse.dao.RegistrationDAO;
import com.studentcourse.dao.StudentDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DashboardServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("[DashboardServlet] init() called - Servlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        System.out.println("[DashboardServlet] doGet() called - Loading dashboard");

        // Session check
        HttpSession session = req.getSession(false);
        if (session == null || session.getAttribute("loggedInUser") == null) {
            res.sendRedirect(req.getContextPath() + "/login");
            return;
        }

        // Fetch summary counts
        StudentDAO      sDao  = new StudentDAO();
        CourseDAO       cDao  = new CourseDAO();
        RegistrationDAO rDao  = new RegistrationDAO();

        req.setAttribute("totalStudents",     sDao.countStudents());
        req.setAttribute("totalCourses",      cDao.countCourses());
        req.setAttribute("totalRegistrations",rDao.countRegistrations());

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/dashboard.jsp");
        rd.forward(req, res);
    }

    @Override
    public void destroy() {
        System.out.println("[DashboardServlet] destroy() called - Servlet destroyed");
    }
}
