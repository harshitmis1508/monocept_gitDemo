package com.studentcourse.controller;

import com.studentcourse.dao.AdminDAO;
import com.studentcourse.model.Admin;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("[LoginServlet] init() called - Servlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {
        System.out.println("[LoginServlet] doGet() called - Redirecting to login page");
        res.sendRedirect(req.getContextPath() + "/login");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        System.out.println("[LoginServlet] doPost() called - Processing login");

        String username       = req.getParameter("username");
        String password       = req.getParameter("password");
        String rememberMe     = req.getParameter("rememberMe");  // "on" or null

        // Basic validation
        if (username == null || username.trim().isEmpty()
                || password == null || password.trim().isEmpty()) {
            req.setAttribute("errorMsg", "Username and password are required.");
            req.setAttribute("rememberedUsername", username);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/login.jsp");
            rd.forward(req, res);
            return;
        }

        AdminDAO dao   = new AdminDAO();
        Admin    admin = dao.validateAdmin(username.trim(), password.trim());

        if (admin != null) {
            // Valid login
            HttpSession session = req.getSession(true);
            session.setAttribute("loggedInUser", admin.getUsername());
            session.setAttribute("loginTime", new java.util.Date().toString());

            // Handle remember-me cookie
            if ("on".equals(rememberMe)) {
                Cookie cookie = new Cookie("rememberedUsername", admin.getUsername());
                cookie.setMaxAge(7 * 24 * 60 * 60);  // 7 days
                cookie.setPath(req.getContextPath() + "/");
                res.addCookie(cookie);
            } else {
                // Delete existing cookie
                Cookie cookie = new Cookie("rememberedUsername", "");
                cookie.setMaxAge(0);
                cookie.setPath(req.getContextPath() + "/");
                res.addCookie(cookie);
            }

            res.sendRedirect(req.getContextPath() + "/dashboard");

        } else {
            // Invalid login
            req.setAttribute("errorMsg", "Invalid username or password. Please try again.");
            req.setAttribute("rememberedUsername", username);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/login.jsp");
            rd.forward(req, res);
        }
    }

    @Override
    public void destroy() {
        System.out.println("[LoginServlet] destroy() called - Servlet destroyed");
    }
}
