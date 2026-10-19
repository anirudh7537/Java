package com.shopsphere.web.servlet;

import com.shopsphere.common.model.Address;
import com.shopsphere.common.model.User;
import com.shopsphere.web.WebSupport;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/profile")
public class ProfileServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try { request.setAttribute("addresses", WebSupport.services(request).addresses().list(WebSupport.userId(request))); request.getRequestDispatcher("/WEB-INF/views/profile.jsp").forward(request, response); }
        catch (Exception ex) { WebSupport.forwardError(request, response, "/WEB-INF/views/profile.jsp", ex); }
    }
    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        long userId = WebSupport.userId(request);
        try {
            String action = request.getParameter("action");
            if ("profile".equals(action)) {
                User user = WebSupport.user(request);
                WebSupport.services(request).authentication().updateProfile(userId, request.getParameter("name"), request.getParameter("mobile"));
                user.setName(request.getParameter("name").trim()); user.setMobile(request.getParameter("mobile"));
                request.getSession().setAttribute("flashSuccess", "Profile updated.");
            } else if ("address".equals(action)) {
                Address address = new Address();
                if (request.getParameter("addressId") != null && !request.getParameter("addressId").isBlank()) address.setAddressId(WebSupport.positiveId(request.getParameter("addressId"), "address"));
                address.setAddressLine(request.getParameter("addressLine")); address.setCity(request.getParameter("city")); address.setState(request.getParameter("state")); address.setPincode(request.getParameter("pincode")); address.setAddressType(request.getParameter("addressType"));
                WebSupport.services(request).addresses().save(userId, address); request.getSession().setAttribute("flashSuccess", "Address saved.");
            } else if ("deleteAddress".equals(action)) {
                WebSupport.services(request).addresses().delete(WebSupport.positiveId(request.getParameter("addressId"), "address"), userId);
            } else throw new IllegalArgumentException("Choose a valid profile action.");
            response.sendRedirect(request.getContextPath() + "/profile");
        } catch (Exception ex) { WebSupport.forwardError(request, response, "/WEB-INF/views/profile.jsp", ex); }
    }
}
