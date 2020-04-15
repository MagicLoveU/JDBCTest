package webtest;
    
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
    
public class PreparedStatement_1 {
    public static void main(String[] args) {
        try {
        	Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
  
        String sql = "select * from hero where name = ?";
        try (Connection c = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=demo", "sa", "sa");
            // ����sql��䴴��PreparedStatement
            PreparedStatement ps = c.prepareStatement(sql);
        ) {
             
            // ���ò���
            ps.setString(1, "��Ī");
            // ִ��
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {

            	System.out.printf("%d\t%S\t%f\t%S%n", rs.getInt(1),rs.getString(2),rs.getFloat(3),rs.getString(4));
            }
            
  
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    
    }
}