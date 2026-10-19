package com.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(
    urlPatterns = "/config",
    initParams = {
        @jakarta.servlet.annotation.WebInitParam(
            name = "college",
            value = "GIT College"
        ),
        @jakarta.servlet.annotation.WebInitParam(
            name = "course",
            value = "B.Tech CSE"
        )
    }
)
public class ConfigServlet extends HttpServlet {

    private String college;
    private String course;

    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);

        college = config.getInitParameter("college");
        course = config.getInitParameter("course");

        System.out.println("ConfigServlet initialized");
        System.out.println("College: " + college);
        System.out.println("Course: " + course);
    }

    @Override
    protected void doGet(
            HttpServletRequest request,
            HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("text/html");

        PrintWriter out = response.getWriter();

        out.println("<html>");
        out.println("<head><title>ServletConfig</title></head>");
        out.println("<body>");

        out.println("<h1>ServletConfig Example</h1>");
        out.println("<p><strong>College:</strong> " + college + "</p>");
        out.println("<p><strong>Course:</strong> " + course + "</p>");

        out.println("</body>");
        out.println("</html>");
    }
}