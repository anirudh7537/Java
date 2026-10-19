package com.shopsphere.web.servlet;

import com.shopsphere.common.model.User;
import com.shopsphere.web.WebSupport;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { request.getRequestDispatcher("/WEB-INF/views/register.jsp").forward(request, response); }
    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        char[] password = request.getParameter("password") == null ? new char[0] : request.getParameter("password").toCharArray();
        try {
            User user = WebSupport.services(request).authentication().register(request.getParameter("name"), request.getParameter("email"), password, request.getParameter("mobile"));
            var session = request.getSession(true); request.changeSessionId();
            session.setAttribute(WebSupport.USER_SESSION_KEY, user); session.setAttribute("role", user.getRole()); session.setAttribute("cartCount", 0);
            response.sendRedirect(request.getContextPath() + "/home");
        } catch (Exception ex) { WebSupport.forwardError(request, response, "/WEB-INF/views/register.jsp", ex); }
        finally { Arrays.fill(password, '\0'); }
    }
}
