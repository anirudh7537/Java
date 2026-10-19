package com.shopsphere.web.servlet;

import com.shopsphere.web.WebSupport;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/cart")
public class CartServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            var cart = WebSupport.services(request).cart(); long userId = WebSupport.userId(request);
            request.setAttribute("items", cart.getItems(userId)); request.setAttribute("subtotal", cart.getSubtotal(userId)); request.setAttribute("cartCount", cart.getCount(userId));
            request.getSession().setAttribute("cartCount", request.getAttribute("cartCount"));
            request.getRequestDispatcher("/WEB-INF/views/cart.jsp").forward(request, response);
        } catch (Exception ex) { WebSupport.forwardError(request, response, "/WEB-INF/views/cart.jsp", ex); }
    }
    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            var cart = WebSupport.services(request).cart(); long userId = WebSupport.userId(request); String action = request.getParameter("action");
            if ("clear".equals(action)) { cart.clear(userId); response.sendRedirect(request.getContextPath() + "/cart"); return; }
            long productId = WebSupport.positiveId(request.getParameter("productId"), "product");
            switch (action == null ? "" : action) {
                case "add" -> cart.add(userId, productId, request.getParameter("quantity") == null ? 1 : WebSupport.positiveInt(request.getParameter("quantity"), "quantity"));
                case "update" -> cart.update(userId, productId, WebSupport.positiveInt(request.getParameter("quantity"), "quantity"));
                case "remove" -> cart.remove(userId, productId);
                default -> throw new IllegalArgumentException("Choose a valid cart action.");
            }
            response.sendRedirect(request.getContextPath() + "/cart");
        } catch (Exception ex) { request.getSession().setAttribute("flashError", ex.getMessage()); response.sendRedirect(request.getContextPath() + "/cart"); }
    }
}
