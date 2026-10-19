package com.shopsphere.web.servlet;

import com.shopsphere.web.WebSupport;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/dashboard")
public class AdminDashboardServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try { request.setAttribute("summary", WebSupport.services(request).admin().dashboard()); request.getRequestDispatcher("/WEB-INF/views/admin/dashboard.jsp").forward(request, response); }
        catch (Exception ex) { WebSupport.forwardError(request, response, "/WEB-INF/views/admin/dashboard.jsp", ex); }
    }
}
