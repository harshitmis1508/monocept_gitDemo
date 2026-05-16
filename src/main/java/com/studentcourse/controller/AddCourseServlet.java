package com.studentcourse.controller;

import com.studentcourse.dao.CourseDAO;
import com.studentcourse.model.Course;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AddCourseServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("[AddCourseServlet] init() called - Servlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        System.out.println("[AddCourseServlet] doGet() called - Showing add course form");

        if (!isLoggedIn(req)) { res.sendRedirect(req.getContextPath() + "/login"); return; }

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/course-form.jsp");
        rd.forward(req, res);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        System.out.println("[AddCourseServlet] doPost() called - Processing add course");

        if (!isLoggedIn(req)) { res.sendRedirect(req.getContextPath() + "/login"); return; }

        String courseName   = req.getParameter("courseName");
        String duration     = req.getParameter("duration");
        String feesStr      = req.getParameter("fees");
        String trainerName  = req.getParameter("trainerName");

        String error = validate(courseName, duration, feesStr, trainerName);
        if (error != null) {
            req.setAttribute("errorMsg",    error);
            req.setAttribute("courseName",  courseName);
            req.setAttribute("duration",    duration);
            req.setAttribute("fees",        feesStr);
            req.setAttribute("trainerName", trainerName);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/course-form.jsp");
            rd.forward(req, res);
            return;
        }

        Course c = new Course();
        c.setCourseName(courseName.trim());
        c.setDuration(duration.trim());
        c.setFees(Double.parseDouble(feesStr.trim()));
        c.setTrainerName(trainerName.trim());

        CourseDAO dao = new CourseDAO();
        if (dao.addCourse(c)) {
            res.sendRedirect(req.getContextPath() + "/courses?success=Course+added+successfully");
        } else {
            req.setAttribute("errorMsg", "Database error. Could not add course.");
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/course-form.jsp");
            rd.forward(req, res);
        }
    }

    private String validate(String name, String duration, String feesStr, String trainer) {
        if (name     == null || name.trim().isEmpty())     return "Course name is required.";
        if (duration == null || duration.trim().isEmpty()) return "Duration is required.";
        if (trainer  == null || trainer.trim().isEmpty())  return "Trainer name is required.";
        if (feesStr  == null || feesStr.trim().isEmpty())  return "Fees are required.";
        try {
            double fees = Double.parseDouble(feesStr.trim());
            if (fees <= 0) return "Fees must be greater than 0.";
        } catch (NumberFormatException e) {
            return "Fees must be a valid number.";
        }
        return null;
    }

    private boolean isLoggedIn(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return session != null && session.getAttribute("loggedInUser") != null;
    }

    @Override
    public void destroy() {
        System.out.println("[AddCourseServlet] destroy() called - Servlet destroyed");
    }
}
