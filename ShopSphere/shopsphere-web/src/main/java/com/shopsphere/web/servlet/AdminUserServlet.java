package com.shopsphere.web.servlet;

import com.shopsphere.web.WebSupport;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/users")
public class AdminUserServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try { request.setAttribute("customers", WebSupport.services(request).admin().customers()); request.getRequestDispatcher("/WEB-INF/views/admin/users.jsp").forward(request, response); }
        catch (Exception ex) { WebSupport.forwardError(request, response, "/WEB-INF/views/admin/users.jsp", ex); }
    }
    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try { long id = WebSupport.positiveId(request.getParameter("userId"), "customer"); boolean active = "activate".equals(request.getParameter("action")); WebSupport.services(request).admin().setCustomerActive(id, active); }
        catch (Exception ex) { request.getSession().setAttribute("flashError", ex.getMessage()); }
        response.sendRedirect(request.getContextPath() + "/admin/users");
    }
}
