package webtest;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Statement_1 {

	public static void main(String[] args) {
		list(10,5);
	}
	
	public static void list(int start, int count){
		
		String sql = ";WITH aa AS(SELECT ROW_NUMBER() OVER(ORDER BY id DESC) AS Row_Index,* FROM hero)"
				+ " SELECT TOP "
				+ count
				+ " * FROM aa WHERE aa.Row_Index >"
				+ start;

		connectDataBase(sql);
		
	}

	public static void connectDataBase(String sql) {

		// 加载数据库
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("数据库驱动加载成功 ！");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// 连接数据库
		try (Connection c = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=demo", "sa",
				"sa");
				// System.out.println("连接成功，获取连接对象： " + c);

				Statement s = c.createStatement();
		// System.out.println("获取 Statement对象： " + s);
		)

		//执行语句
		{
						
			// 执行查询语句，并把结果集返回给ResultSet
			boolean hrs = s.execute(sql);
			ResultSet rs = s.getResultSet();
	        while (rs.next()) {
	            int id = rs.getInt(2);// 也可以使用字段的顺序
	            String name = rs.getString(3);
	            float hp = rs.getFloat(4);
	            int damage = rs.getInt(5);
	            System.out.printf("%d\t%s\t%f\t%d%n", id, name, hp, damage);
	        }
			

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
