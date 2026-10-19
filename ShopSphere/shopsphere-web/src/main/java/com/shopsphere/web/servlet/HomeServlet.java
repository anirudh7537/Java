package com.shopsphere.web.servlet;

import com.shopsphere.web.WebSupport;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/home")
public class HomeServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            request.setAttribute("products", WebSupport.services(request).products().search(null, null, "featured").stream().limit(8).toList());
            request.setAttribute("categories", WebSupport.services(request).categories().listActive());
            request.getRequestDispatcher("/WEB-INF/views/index.jsp").forward(request, response);
        } catch (Exception ex) { WebSupport.forwardError(request, response, "/WEB-INF/views/error.jsp", ex); }
    }
}
