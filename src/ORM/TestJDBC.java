package ORM;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
  
import ORM.Hero;
   
public class TestJDBC {
   
    public static Hero get(int id) {
        Hero hero = null;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
 
        try (Connection c = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;databaseName=demo", "sa", "sa");
            Statement s = c.createStatement();) {
 
            String sql = "select * from hero where id = " + id;
   
            ResultSet rs = s.executeQuery(sql);
   
            // 因为id是唯一的，ResultSet最多只能有一条记录
            // 所以使用if代替while
            if (rs.next()) {
                hero = new Hero();
                String name = rs.getString(2);
                float hp = rs.getFloat("hp");
                int damage = rs.getInt(4);
                hero.name = name;
                hero.hp = hp;
                hero.damage = damage;
                hero.id = id;
            }
   
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return hero;
   
    }
   
    public static void main(String[] args) {
           
        Hero h = get(11);
        System.out.println(h.name);
   
    }
}