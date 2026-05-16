package com.studentcourse.controller;

import com.studentcourse.dao.CourseDAO;
import com.studentcourse.dao.RegistrationDAO;
import com.studentcourse.dao.StudentDAO;
import com.studentcourse.model.Registration;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Date;

public class RegisterStudentCourseServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        if (!isLoggedIn(req)) { res.sendRedirect(req.getContextPath() + "/login"); return; }

        String studentIdStr       = req.getParameter("studentId");
        String courseIdStr        = req.getParameter("courseId");
        String registrationDate   = req.getParameter("registrationDate");
        String status             = req.getParameter("status");

        // Validate
        String error = validate(studentIdStr, courseIdStr, registrationDate, status);
        if (error != null) {
            req.setAttribute("errorMsg", error);
            loadDropdowns(req);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/registration-form.jsp");
            rd.forward(req, res);
            return;
        }

        int studentId = Integer.parseInt(studentIdStr.trim());
        int courseId  = Integer.parseInt(courseIdStr.trim());

        RegistrationDAO rDao = new RegistrationDAO();

        // Duplicate check for Active status
        if ("Active".equals(status) && rDao.isDuplicateActiveRegistration(studentId, courseId)) {
            req.setAttribute("errorMsg",
                "This student is already actively registered for this course.");
            loadDropdowns(req);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/registration-form.jsp");
            rd.forward(req, res);
            return;
        }

        Registration r = new Registration();
        r.setStudentId(studentId);
        r.setCourseId(courseId);
        r.setRegistrationDate(Date.valueOf(registrationDate.trim()));
        r.setStatus(status.trim());

        if (rDao.addRegistration(r)) {
            res.sendRedirect(req.getContextPath() + "/registrations?success=Registration+added+successfully");
        } else {
            req.setAttribute("errorMsg", "Database error. Could not add registration.");
            loadDropdowns(req);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/registration-form.jsp");
            rd.forward(req, res);
        }
    }

    private String validate(String studentId, String courseId, String date, String status) {
        if (studentId == null || studentId.trim().isEmpty() || "0".equals(studentId.trim()))
            return "Please select a student.";
        if (courseId == null || courseId.trim().isEmpty() || "0".equals(courseId.trim()))
            return "Please select a course.";
        if (date == null || date.trim().isEmpty())
            return "Registration date is required.";
        if (status == null || status.trim().isEmpty())
            return "Status is required.";
        if (!status.equals("Active") && !status.equals("Completed") && !status.equals("Cancelled"))
            return "Status must be Active, Completed, or Cancelled.";
        return null;
    }

    private void loadDropdowns(HttpServletRequest req) {
        req.setAttribute("studentList", new StudentDAO().getAllStudents());
        req.setAttribute("courseList",  new CourseDAO().getAllCourses());
    }

    private boolean isLoggedIn(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return session != null && session.getAttribute("loggedInUser") != null;
    }
}
