package com.shopsphere.web.filter;

import com.shopsphere.common.model.User;
import com.shopsphere.web.WebSupport;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter("/admin/*")
public class AdminFilter implements Filter {
    @Override public void doFilter(jakarta.servlet.ServletRequest request, jakarta.servlet.ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        String path = httpRequest.getRequestURI().substring(httpRequest.getContextPath().length());
        if ("/admin/login".equals(path)) { chain.doFilter(request, response); return; }
        User user = httpRequest.getSession(false) == null ? null : (User) httpRequest.getSession(false).getAttribute(WebSupport.USER_SESSION_KEY);
        if (user == null) { httpResponse.sendRedirect(httpRequest.getContextPath() + "/admin/login"); return; }
        if (!"ADMIN".equals(user.getRole())) { httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN); return; }
        chain.doFilter(request, response);
    }
}
