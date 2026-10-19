package com.shopsphere.web.servlet;

import com.shopsphere.common.model.User;
import com.shopsphere.web.WebSupport;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Arrays;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { request.getRequestDispatcher("/WEB-INF/views/login.jsp").forward(request, response); }
    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        char[] password = request.getParameter("password") == null ? new char[0] : request.getParameter("password").toCharArray();
        try {
            User user = WebSupport.services(request).authentication().authenticate(request.getParameter("email"), password).orElseThrow(() -> new IllegalArgumentException("Email or password is incorrect."));
            HttpSession session = request.getSession(true); request.changeSessionId(); session.setAttribute(WebSupport.USER_SESSION_KEY, user); session.setAttribute("role", user.getRole());
            session.setAttribute("cartCount", WebSupport.services(request).cart().getCount(user.getUserId()));
            response.sendRedirect(safeNext(request.getParameter("next"), request.getContextPath()));
        } catch (Exception ex) { WebSupport.forwardError(request, response, "/WEB-INF/views/login.jsp", ex); }
        finally { Arrays.fill(password, '\0'); }
    }
    private String safeNext(String next, String context) {
        if (next == null || !next.startsWith("/") || next.startsWith("//") || next.contains("\\")) return context + "/home";
        return context + next;
    }
}
