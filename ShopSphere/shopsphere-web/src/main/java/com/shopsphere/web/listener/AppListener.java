package com.shopsphere.web.listener;

import com.shopsphere.service.ShopSphereServices;
import com.mysql.cj.jdbc.MysqlDataSource;
import jakarta.servlet.ServletContextEvent;
import jakarta.servlet.ServletContextListener;
import jakarta.servlet.annotation.WebListener;

@WebListener
public class AppListener implements ServletContextListener {
    @Override public void contextInitialized(ServletContextEvent event) {
        event.getServletContext().setAttribute("appName", "ShopSphere");
        event.getServletContext().setAttribute("shopsphere.services", new ShopSphereServices());
        String dbUser = System.getenv("SHOPSPHERE_DB_USER");
        if (dbUser != null && !dbUser.isBlank()) {
            MysqlDataSource demoDataSource = new MysqlDataSource();
            demoDataSource.setURL(System.getenv().getOrDefault("SHOPSPHERE_DB_URL", "jdbc:mysql://localhost:3306/shopsphere?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true"));
            demoDataSource.setUser(dbUser);
