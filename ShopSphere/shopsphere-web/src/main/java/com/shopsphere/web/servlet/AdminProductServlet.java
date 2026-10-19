package com.shopsphere.web.servlet;

import com.shopsphere.common.model.Product;
import com.shopsphere.web.WebSupport;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;

@WebServlet(urlPatterns = {"/admin/products", "/admin/product-form"})
public class AdminProductServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if ("/admin/product-form".equals(request.getServletPath())) {
                long id = WebSupport.positiveId(request.getParameter("id"), "product");
                request.setAttribute("editing", WebSupport.services(request).admin().findProduct(id).orElseThrow(() -> new IllegalArgumentException("Product was not found.")));
                request.setAttribute("categories", WebSupport.services(request).admin().categories());
                request.getRequestDispatcher("/WEB-INF/views/admin/product-form.jsp").forward(request, response);
            } else {
                request.setAttribute("products", WebSupport.services(request).admin().products(request.getParameter("q"), null, "name")); request.setAttribute("categories", WebSupport.services(request).admin().categories()); request.getRequestDispatcher("/WEB-INF/views/admin/products.jsp").forward(request, response);
            }
        } catch (Exception ex) { WebSupport.forwardError(request, response, "/WEB-INF/views/error.jsp", ex); }
    }
    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String action = request.getParameter("action"); long id = request.getParameter("productId") == null || request.getParameter("productId").isBlank() ? 0 : WebSupport.positiveId(request.getParameter("productId"), "product");
            if ("deactivate".equals(action)) WebSupport.services(request).admin().deactivateProduct(id);
            else if ("stock".equals(action)) WebSupport.services(request).admin().updateStock(id, Integer.parseInt(request.getParameter("stock")));
            else if ("save".equals(action)) {
                Product product = new Product(); product.setProductId(id); product.setCategoryId(WebSupport.positiveId(request.getParameter("categoryId"), "category")); product.setName(request.getParameter("name")); product.setBrand(request.getParameter("brand")); product.setDescription(request.getParameter("description")); product.setPrice(new BigDecimal(request.getParameter("price"))); product.setDiscount(new BigDecimal(request.getParameter("discount"))); product.setStock(Integer.parseInt(request.getParameter("stock"))); product.setImageUrl(request.getParameter("imageUrl")); product.setActive("on".equals(request.getParameter("active")) || request.getParameter("active") == null && id == 0); WebSupport.services(request).admin().saveProduct(product);
            } else throw new IllegalArgumentException("Choose a valid product action.");
            request.getSession().setAttribute("flashSuccess", "Product changes saved.");
        } catch (Exception ex) { request.getSession().setAttribute("flashError", ex.getMessage()); }
        response.sendRedirect(request.getContextPath() + "/admin/products");
    }
}
