package LeaveRequest;

import org.testng.annotations.Test;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

//public class Database {
public class TransactionTest {
    @Test
    public static void main(String[] args) throws Exception {
        String sql = "Select Top 1 * from LeavesRequestMaster Order by SerialNo Desc";
        String url = "jdbc:sqlserver://localhost/Dev";
        String Username = "sa";
        String password = "P@ssw0rd";
        Connection Con = DriverManager.getConnection(url, Username, password);
        Statement st = Con.createStatement();
        ResultSet rs = st.executeQuery(sql);
        rs.next();
        String record = rs.getString(1);
        System.out.println("Last record in LeavesRequestMaster table: " + record);
    }
}

