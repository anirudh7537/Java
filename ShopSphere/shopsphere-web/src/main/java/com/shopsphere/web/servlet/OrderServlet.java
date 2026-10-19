package com.shopsphere.web.servlet;

import com.shopsphere.web.WebSupport;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/orders", "/order", "/order-success"})
public class OrderServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            long userId = WebSupport.userId(request);
            if ("/order".equals(request.getServletPath()) || "/order-success".equals(request.getServletPath())) {
                long id = WebSupport.positiveId(request.getParameter("id"), "order");
                var order = WebSupport.services(request).orders().details(id, userId).orElseThrow(() -> new IllegalArgumentException("Order was not found."));
                request.setAttribute("order", order);
                String view = "/order-success".equals(request.getServletPath()) ? "/WEB-INF/views/order-success.jsp" : "/WEB-INF/views/order-details.jsp";
                request.getRequestDispatcher(view).forward(request, response);
            } else {
                request.setAttribute("orders", WebSupport.services(request).orders().listForUser(userId));
                request.getRequestDispatcher("/WEB-INF/views/orders.jsp").forward(request, response);
            }
        } catch (Exception ex) { WebSupport.forwardError(request, response, "/WEB-INF/views/error.jsp", ex); }
    }
}
