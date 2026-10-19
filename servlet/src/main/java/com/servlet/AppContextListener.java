package com.servlet;

import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppContextListener implements ServletContextListener {

    @Override
    public void contextInitialized(ServletContextEvent event) {
        event.getServletContext().log("Application Started");
        event.getServletContext().log(
                "ServletContextListener: contextInitialized() called"
        );
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        event.getServletContext().log("Application Stopped");
        event.getServletContext().log(
                "ServletContextListener: contextDestroyed() called"
        );
    }
}