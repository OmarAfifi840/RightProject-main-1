package utilities;

import java.sql.*;

public class DBUtils {
    private static Connection conn;

    public static Connection getConnection() throws SQLException {
        if (conn == null || conn.isClosed()) {
            conn = DriverManager.getConnection(
                    "jdbc:sqlserver://localhost:1433;databaseName=Dev;encrypt=false;",
                    "sa",
                    "sa"
            );
            System.out.println("✅ Connected to SQL Server successfully!");
        }
        return conn;
    }

    public static ResultSet executeQuery(String sql) throws SQLException {
        Statement stmt = getConnection().createStatement();
        return stmt.executeQuery(sql);
    }

    public static int executeUpdate(String sql) throws SQLException {
        Statement stmt = getConnection().createStatement();
        return stmt.executeUpdate(sql);
    }
}
