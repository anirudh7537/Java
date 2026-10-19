package com.shopsphere.web;

import com.shopsphere.common.model.User;
import com.shopsphere.service.ShopSphereServices;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.SQLException;

public final class WebSupport {
    public static final String USER_SESSION_KEY = "user";
    private WebSupport() { }

    public static ShopSphereServices services(HttpServletRequest request) {
        return (ShopSphereServices) request.getServletContext().getAttribute("shopsphere.services");
    }
    public static User user(HttpServletRequest request) { return request.getSession(false) == null ? null : (User) request.getSession(false).getAttribute(USER_SESSION_KEY); }
    public static long userId(HttpServletRequest request) { User user = user(request); return user == null ? 0 : user.getUserId(); }
    public static long positiveId(String value, String label) {
        try { long id = Long.parseLong(value); if (id > 0) return id; }
        catch (NumberFormatException ignored) { }
        throw new IllegalArgumentException("Choose a valid " + label + ".");
    }
    public static int positiveInt(String value, String label) {
        try { int number = Integer.parseInt(value); if (number > 0) return number; }
        catch (NumberFormatException ignored) { }
        throw new IllegalArgumentException("Enter a valid " + label + ".");
    }
    public static void showError(HttpServletRequest request, Exception exception) {
        if (exception instanceof SQLException) {
            request.getServletContext().log("ShopSphere request failed", exception);
            request.setAttribute("error", "The request could not be completed. Check the database connection and try again.");
        } else {
            request.setAttribute("error", exception.getMessage() == null ? "The request could not be completed." : exception.getMessage());
        }
    }
    public static void forwardError(HttpServletRequest request, jakarta.servlet.http.HttpServletResponse response, String view, Exception exception) throws ServletException, IOException {
        showError(request, exception);
        request.getRequestDispatcher(view).forward(request, response);
    }
}
