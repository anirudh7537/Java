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

@WebServlet("/admin/login")
public class AdminLoginServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { request.getRequestDispatcher("/WEB-INF/views/admin/login.jsp").forward(request, response); }
    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        char[] password = request.getParameter("password") == null ? new char[0] : request.getParameter("password").toCharArray();
        try {
            User user = WebSupport.services(request).authentication().authenticate(request.getParameter("email"), password).filter(value -> "ADMIN".equals(value.getRole())).orElseThrow(() -> new IllegalArgumentException("Administrator credentials are invalid."));
            request.getSession(true); request.changeSessionId(); request.getSession(false).setAttribute(WebSupport.USER_SESSION_KEY, user); request.getSession(false).setAttribute("role", user.getRole());
            response.sendRedirect(request.getContextPath() + "/admin/dashboard");
        } catch (Exception ex) { WebSupport.forwardError(request, response, "/WEB-INF/views/admin/login.jsp", ex); }
        finally { Arrays.fill(password, '\0'); }
    }
}
