package webtest;
  
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;
  
public class AutoCommit {
    public static void main(String[] args) {
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
 
        try (Connection c = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=demo", "sa", "sa");
            Statement s = c.createStatement();) {
  
            // 有事务的前提下
            // 在事务中的多个操作，要么都成功，要么都失败
  
            //c.setAutoCommit(false);
  
            String selectSql = "select top 10 id from hero";
            ResultSet rs = s.executeQuery(selectSql); 

            String[] sql = new String[10];

        	
            for (int i = 0;rs.next();i++) {
            	System.out.println("正在试图修改id = " + rs.getString(1) + " 的数据");
            	
            	sql[i] = "update hero set damage += 1 where id = " + rs.getString(1);
            	
			}
  
            String key = null;
            
            do {
            	System.out.println("是否要修改数据（Y/N）");
            	Scanner sc = new Scanner(System.in);
            	key = sc.nextLine();
            	if(key.equals("Y")){
            		for (int i = 0; i < sql.length; i++) {
						s.execute(sql[i]);
					}
            		System.out.println("提交修改");
            	}
            } while (!key.equals("Y")&&!key.equals("N"));
  
            // 手动提交
            c.commit();
  
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
  
    }
}