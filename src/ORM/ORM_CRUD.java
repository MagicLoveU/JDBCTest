package ORM;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ORM_CRUD {

	public static void add(Hero h) {

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection c = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=demo", "sa",
				"sa"); Statement s = c.createStatement();) {

			String sql = "select count(*) from hero";

			ResultSet rs = s.executeQuery(sql);
			while (rs.next()) {
				System.out.println("数据库中总共有" + rs.getInt(1) + "条数据");

			}

			String sql1 = "insert hero values (N'" + h.name + "','" + h.hp + "','" + h.damage + "')";
			s.execute(sql1);
			System.out.println("新加一条数据");

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void delete(Hero h) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection c = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=demo", "sa",
				"sa"); Statement s = c.createStatement();) {

			String sql = "delete hero where name = N'" + h.name + "' and hp = '" + h.hp + "' and damage = '" + h.damage
					+ "'";

			s.execute(sql);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void update(Hero h) {
		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection c = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=demo", "sa",
				"sa"); Statement s = c.createStatement();) {

			String sql1 = "select count(*) from hero";

			ResultSet rs = s.executeQuery(sql1);
			while (rs.next()) {
				System.out.println("数据库中总共有" + rs.getInt(1) + "条数据");

			}
			String sql2 = "select count(*) from hero";

			rs = s.executeQuery(sql2);

			String sql3 = "update hero set name = N'" + h.name + "',hp = '" + h.hp + "',damage = '" + h.damage
					+ "' where id = 1005";
			System.out.println("修改id=1005的数据，它的name是" + rs.getString(2));
			System.out.println("把名字改为提莫，血量改为313，伤害改为50，并且更新到数据库");
			s.execute(sql3);

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static List<Hero> list() {
		List<Hero> heros = new ArrayList<>();

		try {
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}

		try (Connection c = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=demo", "sa",
				"sa"); Statement s = c.createStatement();) {

			String sql = "select * from hero";

			ResultSet rs = s.executeQuery(sql);

			while (rs.next()) {
				Hero h = new Hero();
				h.id = rs.getInt("id");
				h.name = rs.getString(2);
				h.hp = rs.getFloat(3);
				h.damage = rs.getInt("damage");

				heros.add(h);

			}

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return heros;
	}

	public static void main(String[] args) {

		List<Hero> heros = new ArrayList<>();
		Hero h = new Hero();
		h.name = "提莫";
		h.hp = 313;
		h.damage = 50;

		// add(h);
		// update(h);
		// delete(h);
		heros = list();
		for (Hero hero : heros) {
			System.out.println("id:"+hero.id+" name:"+hero.name+" hp:"+hero.hp+" damage:"+hero.damage);
		}
	}
}
