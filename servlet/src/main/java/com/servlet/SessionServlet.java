package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/session")
public class SessionServlet extends HttpServlet {

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession();

        String name = request.getParameter("name");

        if (name != null && !name.isEmpty()) {
            session.setAttribute("studentName", name);
        }

        String studentName = (String) session.getAttribute("studentName");

        out.println("<html>");
        out.println("<head><title>HttpSession</title></head>");
        out.println("<body>");

        out.println("<h1>HttpSession Example</h1>");

        if (studentName != null) {
            out.println("<p>Welcome, <strong>" + studentName + "</strong>!</p>");
            out.println("<p>Your name is stored in the current session.</p>");
            out.println("<a href='session-logout'>Logout</a>");
        } else {
            out.println("<p>No student is currently logged in.</p>");
            out.println("<p>Try: <strong>?name=Anirudh</strong></p>");
        }

        out.println("</body>");
        out.println("</html>");
    }
}