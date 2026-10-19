package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/register")
public class RegistrationServlet extends HttpServlet {


@Override
protected void doPost(HttpServletRequest request, HttpServletResponse response)
        throws ServletException, IOException {

    response.setContentType("text/html");

    String fullname = request.getParameter("fullname");
    String username = request.getParameter("username");
    String email = request.getParameter("email");
    String password = request.getParameter("password");
    String confirmPassword = request.getParameter("confirmPassword");
    String gender = request.getParameter("gender");
    String course = request.getParameter("course");

    PrintWriter out = response.getWriter();

    if (!password.equals(confirmPassword)) {

        out.println("<h1>Registration Failed</h1>");
        out.println("<h2>Passwords do not match!</h2>");
        out.println("<a href='index.html'>Try Again</a>");

        return;
    }

    HttpSession session = request.getSession();

    session.setAttribute("fullname", fullname);
    session.setAttribute("username", username);
    session.setAttribute("email", email);
    session.setAttribute("gender", gender);
    session.setAttribute("course", course);

    out.println("<h1>Registration Successful!</h1>");
    out.println("<h2>Welcome " + fullname + "</h2>");

    out.println("<p>Username: " + username + "</p>");
    out.println("<p>Email: " + email + "</p>");
    out.println("<p>Gender: " + gender + "</p>");
    out.println("<p>Course: " + course + "</p>");

    out.println("<br>");
    out.println("<a href='home'>Go to Home</a>");
}


}
