package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/request-response")
public class RequestResponseServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        String name = request.getParameter("name");

        out.println("<html>");
        out.println("<head><title>Request and Response</title></head>");
        out.println("<body>");

        out.println("<h1>Request and Response Handling</h1>");

        if (name != null && !name.isEmpty()) {
            out.println("<p>Hello, " + name + "!</p>");
        } else {
            out.println("<p>No name was provided.</p>");
            out.println("<p>Try adding <strong>?name=Anirudh</strong> to the URL.</p>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}