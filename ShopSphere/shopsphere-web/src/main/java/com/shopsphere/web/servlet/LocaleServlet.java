package com.shopsphere.web.servlet;

import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/locale")
public class LocaleServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String language = request.getParameter("lang");
        if ("hi".equals(language)) request.getSession(true).setAttribute("locale", "hi_IN");
        else if ("en".equals(language)) request.getSession(true).setAttribute("locale", "en_IN");
        response.sendRedirect(request.getContextPath() + "/home");
    }
}
