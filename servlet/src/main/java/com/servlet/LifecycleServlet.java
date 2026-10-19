package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/lifecycle")
public class LifecycleServlet extends HttpServlet {

    @Override
    public void init() throws ServletException {
        System.out.println("LifecycleServlet: init() called");
    }

    @Override
    protected void service(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        System.out.println("LifecycleServlet: service() called");

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>Servlet Lifecycle</title></head>");
        out.println("<body>");

        out.println("<h1>Servlet Lifecycle</h1>");
        out.println("<p><strong>init()</strong> → Servlet initialized</p>");
        out.println("<p><strong>service()</strong> → Request processed</p>");
        out.println("<p><strong>destroy()</strong> → Servlet destroyed</p>");

        out.println("</body>");
        out.println("</html>");
    }

    @Override
    public void destroy() {
        System.out.println("LifecycleServlet: destroy() called");
    }
}