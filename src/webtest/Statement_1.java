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

		// �������ݿ�
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			System.out.println("���ݿ��������سɹ� ��");
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// �������ݿ�
		try (Connection c = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=demo", "sa",
				"sa");
				// System.out.println("���ӳɹ�����ȡ���Ӷ��� " + c);

				Statement s = c.createStatement();
		// System.out.println("��ȡ Statement���� " + s);
		)

		//ִ�����
		{
						
			// ִ�в�ѯ��䣬���ѽ�������ظ�ResultSet
			boolean hrs = s.execute(sql);
			ResultSet rs = s.getResultSet();
	        while (rs.next()) {
	            int id = rs.getInt(2);// Ҳ����ʹ���ֶε�˳��
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
