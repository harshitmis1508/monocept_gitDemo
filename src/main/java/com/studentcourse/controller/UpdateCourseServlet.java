package com.studentcourse.controller;

import com.studentcourse.dao.CourseDAO;
import com.studentcourse.model.Course;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class UpdateCourseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        if (!isLoggedIn(req)) { res.sendRedirect(req.getContextPath() + "/login"); return; }

        String idStr       = req.getParameter("courseId");
        String courseName  = req.getParameter("courseName");
        String duration    = req.getParameter("duration");
        String feesStr     = req.getParameter("fees");
        String trainerName = req.getParameter("trainerName");

        String error = validate(courseName, duration, feesStr, trainerName);
        if (error != null || idStr == null || idStr.trim().isEmpty()) {
            String msg = (error != null) ? error : "Invalid course ID.";
            req.setAttribute("errorMsg", msg);

            Course c = new Course();
            try { c.setCourseId(Integer.parseInt(idStr)); } catch (Exception ignored) {}
            c.setCourseName(courseName);
            c.setDuration(duration);
            try { c.setFees(Double.parseDouble(feesStr)); } catch (Exception ignored) {}
            c.setTrainerName(trainerName);
            req.setAttribute("course", c);

            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/course-edit.jsp");
            rd.forward(req, res);
            return;
        }

        Course c = new Course();
        c.setCourseId(Integer.parseInt(idStr.trim()));
        c.setCourseName(courseName.trim());
        c.setDuration(duration.trim());
        c.setFees(Double.parseDouble(feesStr.trim()));
        c.setTrainerName(trainerName.trim());

        CourseDAO dao = new CourseDAO();
        if (dao.updateCourse(c)) {
            res.sendRedirect(req.getContextPath() + "/courses?success=Course+updated+successfully");
        } else {
            req.setAttribute("errorMsg", "Database error. Could not update course.");
            req.setAttribute("course", c);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/course-edit.jsp");
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
}
