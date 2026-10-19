package com.shopsphere.web.servlet;

import com.shopsphere.web.WebSupport;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/checkout")
public class CheckoutServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long userId = WebSupport.userId(request);
            request.setAttribute("items", WebSupport.services(request).cart().getItems(userId));
            request.setAttribute("subtotal", WebSupport.services(request).cart().getSubtotal(userId));
            request.setAttribute("addresses", WebSupport.services(request).addresses().list(userId));
            request.getRequestDispatcher("/WEB-INF/views/checkout.jsp").forward(request, response);
        } catch (Exception ex) { WebSupport.forwardError(request, response, "/WEB-INF/views/checkout.jsp", ex); }
    }
    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long addressId = WebSupport.positiveId(request.getParameter("addressId"), "address");
            boolean approved = !"decline".equalsIgnoreCase(request.getParameter("mockResult"));
            var order = WebSupport.services(request).checkout().checkout(WebSupport.userId(request), addressId, request.getParameter("couponCode"), approved);
            response.sendRedirect(request.getContextPath() + (approved ? "/order-success?id=" : "/order?id=") + order.getOrderId());
        } catch (Exception ex) {
            WebSupport.showError(request, ex);
            doGet(request, response);
        }
    }
}
