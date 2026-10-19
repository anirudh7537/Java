
package com.shopsphere.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public final class ConnectionFactory {

    private static final String DEFAULT_URL =
            "jdbc:mysql://localhost:3306/shopsphere?serverTimezone=UTC&useSSL=false&allowPublicKeyRetrieval=true";

    private ConnectionFactory() {
    }

    public static Connection getConnection() throws SQLException {
        String user = System.getenv("SHOPSPHERE_DB_USER");

        if (user == null || user.isBlank()) {
            throw new SQLException(
                    "Set SHOPSPHERE_DB_USER before starting ShopSphere.");
        }

        String url = System.getenv().getOrDefault(
                "SHOPSPHERE_DB_URL", DEFAULT_URL);

        String password = System.getenv().getOrDefault(
                "SHOPSPHERE_DB_PASSWORD", "");

        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            throw new SQLException(
                    "MySQL JDBC driver class was not found. Check WEB-INF/lib.",
                    e);
        }

        return DriverManager.getConnection(url, user, password);
    }
}
