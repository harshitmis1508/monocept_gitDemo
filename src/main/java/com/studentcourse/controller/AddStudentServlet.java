package com.studentcourse.controller;

import com.studentcourse.dao.StudentDAO;
import com.studentcourse.model.Student;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class AddStudentServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("[AddStudentServlet] init() called - Servlet initialized");
    }

    // Show the add student form
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        System.out.println("[AddStudentServlet] doGet() called - Showing add student form");

        if (!isLoggedIn(req)) { res.sendRedirect(req.getContextPath() + "/login"); return; }

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/student-form.jsp");
        rd.forward(req, res);
    }

    // Process add student form
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        System.out.println("[AddStudentServlet] doPost() called - Processing add student");

        if (!isLoggedIn(req)) { res.sendRedirect(req.getContextPath() + "/login"); return; }

        String name  = req.getParameter("studentName");
        String email = req.getParameter("email");
        String phone = req.getParameter("phone");
        String ageStr= req.getParameter("age");
        String city  = req.getParameter("city");

        // Validation
        String error = validate(name, email, phone, ageStr, city);
        if (error != null) {
            req.setAttribute("errorMsg",     error);
            req.setAttribute("studentName",  name);
            req.setAttribute("email",        email);
            req.setAttribute("phone",        phone);
            req.setAttribute("age",          ageStr);
            req.setAttribute("city",         city);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/student-form.jsp");
            rd.forward(req, res);
            return;
        }

        Student s = new Student();
        s.setStudentName(name.trim());
        s.setEmail(email.trim());
        s.setPhone(phone.trim());
        s.setAge(Integer.parseInt(ageStr.trim()));
        s.setCity(city.trim());

        StudentDAO dao = new StudentDAO();
        if (dao.addStudent(s)) {
            res.sendRedirect(req.getContextPath() + "/students?success=Student+added+successfully");
        } else {
            req.setAttribute("errorMsg", "Database error. Could not add student. Please try again.");
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/student-form.jsp");
            rd.forward(req, res);
        }
    }

    private String validate(String name, String email, String phone, String ageStr, String city) {
        if (name  == null || name.trim().isEmpty())  return "Student name is required.";
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

    @Override
    public void destroy() {
        System.out.println("[AddStudentServlet] destroy() called - Servlet destroyed");
    }
}
