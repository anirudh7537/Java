package com.shopsphere.web.servlet;

import com.shopsphere.web.WebSupport;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/admin/orders")
public class AdminOrderServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try { request.setAttribute("orders", WebSupport.services(request).admin().orders()); request.getRequestDispatcher("/WEB-INF/views/admin/orders.jsp").forward(request, response); }
        catch (Exception ex) { WebSupport.forwardError(request, response, "/WEB-INF/views/admin/orders.jsp", ex); }
    }
    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try { WebSupport.services(request).admin().updateOrder(WebSupport.positiveId(request.getParameter("orderId"), "order"), request.getParameter("status")); request.getSession().setAttribute("flashSuccess", "Order status updated."); }
        catch (Exception ex) { request.getSession().setAttribute("flashError", ex.getMessage()); }
        response.sendRedirect(request.getContextPath() + "/admin/orders");
    }
}
