package com.shopsphere.web.servlet;

import com.shopsphere.common.model.Product;
import com.shopsphere.web.WebSupport;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@WebServlet(urlPatterns = {"/products", "/product"})
public class ProductServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if (request.getServletPath().equals("/product")) {
                long id = WebSupport.positiveId(request.getParameter("id"), "product");
                Product product = WebSupport.services(request).products().find(id).orElseThrow(() -> new IllegalArgumentException("Product was not found."));
                request.setAttribute("product", product);
                request.setAttribute("reviews", WebSupport.services(request).reviews().listForProduct(id));
                rememberProduct(request, response, id);
                request.getRequestDispatcher("/WEB-INF/views/product-details.jsp").forward(request, response);
                return;
            }
            Long categoryId = request.getParameter("category") == null || request.getParameter("category").isBlank() ? null : WebSupport.positiveId(request.getParameter("category"), "category");
            request.setAttribute("products", WebSupport.services(request).products().search(request.getParameter("q"), categoryId, request.getParameter("sort")));
            request.setAttribute("categories", WebSupport.services(request).categories().listActive());
            request.getRequestDispatcher("/WEB-INF/views/products.jsp").forward(request, response);
        } catch (Exception ex) { WebSupport.forwardError(request, response, "/WEB-INF/views/error.jsp", ex); }
    }
    private void rememberProduct(HttpServletRequest request, HttpServletResponse response, long id) {
        List<String> ids = new ArrayList<>();
        if (request.getCookies() != null) for (Cookie cookie : request.getCookies()) if ("recentProducts".equals(cookie.getName())) ids.addAll(Arrays.asList(cookie.getValue().split(",")));
        ids.remove(Long.toString(id)); ids.add(0, Long.toString(id));
        Cookie recent = new Cookie("recentProducts", String.join(",", ids.stream().limit(8).toList())); recent.setMaxAge(30 * 24 * 60 * 60); recent.setHttpOnly(true); recent.setSecure(request.isSecure()); recent.setPath(request.getContextPath().isEmpty() ? "/" : request.getContextPath()); response.addCookie(recent);
    }
}
