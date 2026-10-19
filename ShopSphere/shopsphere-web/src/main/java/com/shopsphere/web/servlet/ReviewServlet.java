package com.shopsphere.web.servlet;

import com.shopsphere.web.WebSupport;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/reviews")
public class ReviewServlet extends HttpServlet {
    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        long productId = 0;
        try {
            productId = WebSupport.positiveId(request.getParameter("productId"), "product");
            int rating = WebSupport.positiveInt(request.getParameter("rating"), "rating");
            WebSupport.services(request).reviews().submit(WebSupport.userId(request), productId, rating, request.getParameter("reviewText"));
            request.getSession().setAttribute("flashSuccess", "Your review was submitted.");
        } catch (Exception ex) { request.getSession().setAttribute("flashError", ex.getMessage()); }
        response.sendRedirect(request.getContextPath() + "/product?id=" + productId);
    }
}
