package com.shopsphere.web.servlet;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import javax.naming.InitialContext;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.nio.charset.StandardCharsets;
import java.util.stream.Collectors;

@WebServlet("/demos/*")
public class SyllabusDemoServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String page = request.getPathInfo() == null ? "/index" : request.getPathInfo();
        if ("/xml".equals(page)) request.setAttribute("productXml", "<products><product><name>Desk Lamp</name><kind>Home</kind></product><product><name>Wireless Headphones</name><kind>Electronics</kind></product></products>");
        if ("/jndi".equals(page)) {
            try { request.setAttribute("lookupValue", new InitialContext().lookup("java:comp/env/shopsphere/appName")); }
            catch (Exception ex) { request.setAttribute("demoError", "Configure the ShopSphere JNDI environment entry in WEB-INF/web.xml."); }
        } else if ("/url".equals(page)) {
            URL resource = Thread.currentThread().getContextClassLoader().getResource("com/shopsphere/common/i18n/messages_en.properties");
            if (resource != null) {
                URLConnection connection = resource.openConnection();
                try (BufferedReader reader = new BufferedReader(new InputStreamReader(connection.getInputStream(), StandardCharsets.UTF_8))) { request.setAttribute("urlResult", resource.getProtocol() + " URL; content type " + connection.getContentType() + "; first line: " + reader.readLine()); }
            } else request.setAttribute("urlResult", "Resource URL is not available until the web application is packaged.");
        }
        String view = switch (page) {
            case "/traditional" -> "traditional";
            case "/core" -> "core";
            case "/xml" -> "xml";
            case "/sql" -> "sql";
            case "/jndi" -> "jndi";
            case "/url" -> "url";
            case "/applet" -> "applet";
            default -> "index";
        };
        request.getRequestDispatcher("/WEB-INF/views/demos/" + view + ".jsp").forward(request, response);
    }
}
