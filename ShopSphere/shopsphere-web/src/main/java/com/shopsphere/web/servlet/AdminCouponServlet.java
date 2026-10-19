package com.shopsphere.web.servlet;

import com.shopsphere.common.model.Coupon;
import com.shopsphere.web.WebSupport;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@WebServlet(urlPatterns = {"/admin/coupons", "/admin/coupon-form"})
public class AdminCouponServlet extends HttpServlet {
    @Override protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            if ("/admin/coupon-form".equals(request.getServletPath())) {
                long id = WebSupport.positiveId(request.getParameter("id"), "coupon");
                request.setAttribute("editing", WebSupport.services(request).admin().findCoupon(id).orElseThrow(() -> new IllegalArgumentException("Coupon was not found.")));
                request.getRequestDispatcher("/WEB-INF/views/admin/coupon-form.jsp").forward(request, response);
            } else {
                request.setAttribute("coupons", WebSupport.services(request).admin().coupons()); request.getRequestDispatcher("/WEB-INF/views/admin/coupons.jsp").forward(request, response);
            }
        } catch (Exception ex) { WebSupport.forwardError(request, response, "/WEB-INF/views/error.jsp", ex); }
    }
    @Override protected void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String action = request.getParameter("action");
            if ("toggle".equals(action)) WebSupport.services(request).admin().setCouponActive(WebSupport.positiveId(request.getParameter("couponId"), "coupon"), "activate".equals(request.getParameter("state")));
            else if ("save".equals(action)) {
                Coupon coupon = new Coupon(); String id = request.getParameter("couponId"); if (id != null && !id.isBlank()) coupon.setCouponId(WebSupport.positiveId(id, "coupon")); coupon.setCode(request.getParameter("code")); coupon.setDiscountType(request.getParameter("discountType")); coupon.setDiscountValue(new BigDecimal(request.getParameter("discountValue"))); coupon.setMinimumOrder(new BigDecimal(request.getParameter("minimumOrder"))); String max = request.getParameter("maximumDiscount"); coupon.setMaximumDiscount(max == null || max.isBlank() ? null : new BigDecimal(max)); coupon.setExpiryDate(LocalDateTime.parse(request.getParameter("expiryDate"))); coupon.setActive(true); WebSupport.services(request).admin().saveCoupon(coupon);
            } else throw new IllegalArgumentException("Choose a valid coupon action.");
        } catch (Exception ex) { request.getSession().setAttribute("flashError", ex.getMessage()); }
        response.sendRedirect(request.getContextPath() + "/admin/coupons");
    }
}
