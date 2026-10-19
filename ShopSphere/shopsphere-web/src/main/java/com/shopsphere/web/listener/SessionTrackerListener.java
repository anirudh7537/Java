package com.shopsphere.web.listener;

import jakarta.servlet.annotation.WebListener;
import jakarta.servlet.http.HttpSessionEvent;
import jakarta.servlet.http.HttpSessionListener;
import java.util.concurrent.atomic.AtomicInteger;

@WebListener
public class SessionTrackerListener implements HttpSessionListener {
    private static final AtomicInteger ACTIVE_SESSIONS = new AtomicInteger();
    @Override public void sessionCreated(HttpSessionEvent event) {
        int active = ACTIVE_SESSIONS.incrementAndGet();
        event.getSession().getServletContext().setAttribute("activeSessionCount", active);
    }
    @Override public void sessionDestroyed(HttpSessionEvent event) {
        int active = Math.max(0, ACTIVE_SESSIONS.decrementAndGet());
        event.getSession().getServletContext().setAttribute("activeSessionCount", active);
    }
}
