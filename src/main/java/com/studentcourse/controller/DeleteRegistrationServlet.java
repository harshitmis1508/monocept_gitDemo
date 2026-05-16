package com.studentcourse.controller;

import com.studentcourse.dao.RegistrationDAO;

import javax.servlet.*;
import javax.servlet.http.*;
import java.io.IOException;

public class DeleteRegistrationServlet extends HttpServlet {

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

            if (dao.deleteRegistration(id)) {
                res.sendRedirect(req.getContextPath() +
                    "/registrations?success=Registration+deleted+successfully");
            } else {
                res.sendRedirect(req.getContextPath() +
                    "/registrations?error=Could+not+delete+registration");
            }

        } catch (NumberFormatException e) {
            res.sendRedirect(req.getContextPath() + "/registrations?error=Invalid+registration+ID");
        }
    }

    private boolean isLoggedIn(HttpServletRequest req) {
        HttpSession session = req.getSession(false);
        return session != null && session.getAttribute("loggedInUser") != null;
    }
}
