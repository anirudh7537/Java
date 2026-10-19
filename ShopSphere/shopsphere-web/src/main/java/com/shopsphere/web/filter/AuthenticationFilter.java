package com.shopsphere.web.filter;

import com.shopsphere.web.WebSupport;
import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter(urlPatterns = {"/cart", "/checkout", "/orders", "/order", "/order-success", "/profile", "/wishlist", "/reviews"})
public class AuthenticationFilter implements Filter {
    @Override public void doFilter(jakarta.servlet.ServletRequest request, jakarta.servlet.ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpRequest = (HttpServletRequest) request;
        HttpServletResponse httpResponse = (HttpServletResponse) response;
        if (httpRequest.getSession(false) == null || httpRequest.getSession(false).getAttribute(WebSupport.USER_SESSION_KEY) == null) {
            httpResponse.sendRedirect(httpRequest.getContextPath() + "/login?next=" + java.net.URLEncoder.encode(httpRequest.getRequestURI().substring(httpRequest.getContextPath().length()), java.nio.charset.StandardCharsets.UTF_8));
            return;
        }
        chain.doFilter(request, response);
    }
}
