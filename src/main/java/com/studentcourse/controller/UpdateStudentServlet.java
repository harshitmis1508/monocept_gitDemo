package com.studentcourse.controller;

import com.studentcourse.dao.StudentDAO;
import com.studentcourse.model.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class UpdateStudentServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        if (!isLoggedIn(req)) { res.sendRedirect(req.getContextPath() + "/login"); return; }

        String idStr  = req.getParameter("studentId");
        String name   = req.getParameter("studentName");
        String email  = req.getParameter("email");
        String phone  = req.getParameter("phone");
        String ageStr = req.getParameter("age");
        String city   = req.getParameter("city");

        // Validation
        String error = validate(name, email, phone, ageStr, city);
        if (error != null || idStr == null || idStr.trim().isEmpty()) {
            String msg = (error != null) ? error : "Invalid student ID.";
            req.setAttribute("errorMsg", msg);

            // Re-populate form
            Student s = new Student();
            try { s.setStudentId(Integer.parseInt(idStr)); } catch (Exception ignored) {}
            s.setStudentName(name);
            s.setEmail(email);
            s.setPhone(phone);
            try { s.setAge(Integer.parseInt(ageStr)); } catch (Exception ignored) {}
            s.setCity(city);
            req.setAttribute("student", s);

            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/student-edit.jsp");
            rd.forward(req, res);
            return;
        }

        Student s = new Student();
        s.setStudentId(Integer.parseInt(idStr.trim()));
        s.setStudentName(name.trim());
        s.setEmail(email.trim());
        s.setPhone(phone.trim());
        s.setAge(Integer.parseInt(ageStr.trim()));
        s.setCity(city.trim());

        StudentDAO dao = new StudentDAO();
        if (dao.updateStudent(s)) {
            res.sendRedirect(req.getContextPath() + "/students?success=Student+updated+successfully");
        } else {
            req.setAttribute("errorMsg", "Database error. Could not update student.");
            req.setAttribute("student", s);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/student-edit.jsp");
            rd.forward(req, res);
        }
    }

    private String validate(String name, String email, String phone, String ageStr, String city) {
        if (name  == null || name.trim().isEmpty())   return "Student name is required.";
        if (email == null || email.trim().isEmpty())  return "Email is required.";
        if (phone == null || phone.trim().isEmpty())  return "Phone is required.";
        if (city  == null || city.trim().isEmpty())   return "City is required.";
        if (ageStr == null || ageStr.trim().isEmpty()) return "Age is required.";
        try {
            int age = Integer.parseInt(ageStr.trim());
            if (age < 18) return "Age must be 18 or above.";
        } catch (NumberFormatException e) {
            return "Age must be a valid number.";
        }
        return null;
    }

    private boolean isLoggedIn(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return session != null && session.getAttribute("loggedInUser") != null;
    }
}
