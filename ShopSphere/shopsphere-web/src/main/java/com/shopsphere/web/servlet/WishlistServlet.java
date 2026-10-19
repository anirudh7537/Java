package com.shopsphere.web.servlet;

import com.shopsphere.web.WebSupport;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/wishlist")
public class WishlistServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try { request.setAttribute("items", WebSupport.services(request).wishlist().getItems(WebSupport.userId(request))); request.getRequestDispatcher("/WEB-INF/views/wishlist.jsp").forward(request, response); }
        catch (Exception ex) { WebSupport.forwardError(request, response, "/WEB-INF/views/wishlist.jsp", ex); }
    }
    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            long userId = WebSupport.userId(request); long productId = WebSupport.positiveId(request.getParameter("productId"), "product"); String action = request.getParameter("action");
            if ("add".equals(action)) WebSupport.services(request).wishlist().add(userId, productId);
            else if ("remove".equals(action)) WebSupport.services(request).wishlist().remove(userId, productId);
            else if ("moveToCart".equals(action)) { WebSupport.services(request).cart().add(userId, productId, 1); WebSupport.services(request).wishlist().remove(userId, productId); }
            else throw new IllegalArgumentException("Choose a valid wishlist action.");
            response.sendRedirect(request.getContextPath() + ("add".equals(action) ? "/product?id=" + productId : "/wishlist"));
        } catch (Exception ex) { request.getSession().setAttribute("flashError", ex.getMessage()); response.sendRedirect(request.getContextPath() + "/wishlist"); }
    }
}
