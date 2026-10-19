package com.shopsphere.web.servlet;

import com.shopsphere.common.model.Category;
import com.shopsphere.web.WebSupport;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = {"/admin/categories", "/admin/category-form"})
public class AdminCategoryServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if ("/admin/category-form".equals(request.getServletPath())) {
                long id = WebSupport.positiveId(request.getParameter("id"), "category");
                request.setAttribute("editing", WebSupport.services(request).admin().findCategory(id).orElseThrow(() -> new IllegalArgumentException("Category was not found.")));
                request.getRequestDispatcher("/WEB-INF/views/admin/category-form.jsp").forward(request, response);
            } else {
                request.setAttribute("categories", WebSupport.services(request).admin().categories()); request.getRequestDispatcher("/WEB-INF/views/admin/categories.jsp").forward(request, response);
            }
        } catch (Exception ex) { WebSupport.forwardError(request, response, "/WEB-INF/views/error.jsp", ex); }
    }
    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String action = request.getParameter("action"); long id = request.getParameter("categoryId") == null || request.getParameter("categoryId").isBlank() ? 0 : WebSupport.positiveId(request.getParameter("categoryId"), "category");
            if ("deactivate".equals(action)) WebSupport.services(request).admin().deactivateCategory(id);
            else if ("save".equals(action)) { Category category = new Category(); category.setCategoryId(id); category.setName(request.getParameter("name")); category.setDescription(request.getParameter("description")); category.setActive(!"off".equals(request.getParameter("active"))); WebSupport.services(request).admin().saveCategory(category); }
            else throw new IllegalArgumentException("Choose a valid category action.");
        } catch (Exception ex) { request.getSession().setAttribute("flashError", ex.getMessage()); }
        response.sendRedirect(request.getContextPath() + "/admin/categories");
    }
}
