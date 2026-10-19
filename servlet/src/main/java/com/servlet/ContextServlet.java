package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/context")
public class ContextServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        ServletContext context = getServletContext();

        context.setAttribute("college", "GIT College");
        context.setAttribute("department", "Computer Science and Engineering");

        String college = (String) context.getAttribute("college");
        String department = (String) context.getAttribute("department");

        out.println("<html>");
        out.println("<head><title>ServletContext</title></head>");
        out.println("<body>");

        out.println("<h1>ServletContext Example</h1>");
        out.println("<p><strong>College:</strong> " + college + "</p>");
        out.println("<p><strong>Department:</strong> " + department + "</p>");
        out.println("<p>These attributes are shared across the web application.</p>");

        out.println("</body>");
        out.println("</html>");
    }
}