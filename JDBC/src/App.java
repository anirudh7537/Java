import java.sql.*;

public class App {
    public static void main(String[] args) throws Exception {
        Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/college", "root", "1234");
        PreparedStatement pstmt = conn.prepareStatement("insert into student values(?, ?, ?)");
        pstmt.setInt(1, 104);
        pstmt.setString(2, "John Doe");
        pstmt.setString(3, "Cse");
        pstmt.executeUpdate();  
        System.out.println("Record inserted successfully!");
        Statement stmt = conn.createStatement();
        ResultSet rs = stmt.executeQuery("select * from student");
        while (rs.next()) {
            System.out.println(rs.getInt(1) + " " + rs.getString(2) + " " + rs.getString(3));
        }
        System.out.println(conn);
        System.out.println("Connected to the database!");
        conn.close();
    }
}