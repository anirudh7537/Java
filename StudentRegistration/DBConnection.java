// PHASE 3: JDBC Connection Helper
// One place to manage the connection so the rest of the app doesn't
// need to know connection details.

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection{

    // Update these 3 values to match your local MySQL setup
    private static final String URL = "jdbc:mysql://localhost:3306/student_db";
    private static final String USER = "root";
    private static final String PASSWORD = "your_mysql_password"; // set your local password here

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
}