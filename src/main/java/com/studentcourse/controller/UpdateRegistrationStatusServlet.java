package com.studentcourse.controller;

import com.studentcourse.dao.RegistrationDAO;
import com.studentcourse.model.Registration;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class UpdateRegistrationStatusServlet extends HttpServlet {

    // GET: show status update form
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        if (!isLoggedIn(req)) { res.sendRedirect(req.getContextPath() + "/login"); return; }

        String idStr = req.getParameter("id");
        if (idStr == null || idStr.trim().isEmpty()) {
            res.sendRedirect(req.getContextPath() + "/registrations?error=Invalid+registration+ID");
            return;
        }

        try {
            int id = Integer.parseInt(idStr.trim());
            RegistrationDAO dao = new RegistrationDAO();
            Registration reg = dao.getRegistrationById(id);

            if (reg == null) {
                res.sendRedirect(req.getContextPath() + "/registrations?error=Registration+not+found");
                return;
            }

            req.setAttribute("registration", reg);
            RequestDispatcher rd = req.getRequestDispatcher("/WEB-INF/views/registration-status.jsp");
            rd.forward(req, res);

        } catch (NumberFormatException e) {
            res.sendRedirect(req.getContextPath() + "/registrations?error=Invalid+registration+ID");
        }
    }

    // POST: process status update
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse res)
            throws ServletException, IOException {

        if (!isLoggedIn(req)) { res.sendRedirect(req.getContextPath() + "/login"); return; }

        String idStr  = req.getParameter("registrationId");
        String status = req.getParameter("status");

        if (idStr == null || idStr.trim().isEmpty() || status == null || status.trim().isEmpty()) {
            res.sendRedirect(req.getContextPath() + "/registrations?error=Invalid+input");
            return;
        }

        if (!status.equals("Active") && !status.equals("Completed") && !status.equals("Cancelled")) {
            res.sendRedirect(req.getContextPath() + "/registrations?error=Invalid+status+value");
            return;
        }

        int id = Integer.parseInt(idStr.trim());
        RegistrationDAO dao = new RegistrationDAO();

        if (dao.updateStatus(id, status.trim())) {
            res.sendRedirect(req.getContextPath() + "/registrations?success=Status+updated+successfully");
        } else {
            res.sendRedirect(req.getContextPath() + "/registrations?error=Could+not+update+status");
        }
    }

    private boolean isLoggedIn(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return session != null && session.getAttribute("loggedInUser") != null;
    }
}
