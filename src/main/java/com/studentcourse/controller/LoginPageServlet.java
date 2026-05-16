package com.studentcourse.controller;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class LoginPageServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("[LoginPageServlet] init() called - Servlet initialized");
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        System.out.println("[LoginPageServlet] doGet() called - Loading login page");

        // If already logged in, redirect to dashboard
        HttpSession session = req.getSession(false);
        if (session != null && session.getAttribute("loggedInUser") != null) {
            res.sendRedirect(req.getContextPath() + "/dashboard");
            return;
        }

        // Read remembered username cookie
        String rememberedUsername = "";
        Cookie[] cookies = req.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                if ("rememberedUsername".equals(c.getName())) {
                    rememberedUsername = c.getValue();
                    break;
                }
            }
        }

        req.setAttribute("rememberedUsername", rememberedUsername);
        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/login.jsp");
        rd.forward(req, res);
    }

    @Override
    public void destroy() {
        System.out.println("[LoginPageServlet] destroy() called - Servlet destroyed");
    }
}
