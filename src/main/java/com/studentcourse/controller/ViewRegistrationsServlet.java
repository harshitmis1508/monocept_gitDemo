package com.studentcourse.controller;

import com.studentcourse.dao.RegistrationDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class ViewRegistrationsServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        if (!isLoggedIn(req)) { res.sendRedirect(req.getContextPath() + "/login"); return; }

        RegistrationDAO dao = new RegistrationDAO();
        req.setAttribute("registrationList", dao.getAllRegistrations());

        RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/registration-list.jsp");
        rd.forward(req, res);
    }

    private boolean isLoggedIn(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return session != null && session.getAttribute("loggedInUser") != null;
    }
}
