package helloSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

//链接数据库
		/**
		 * 原材料：
		 * 数据库的IP：127.0.0.1
		 * 数据库的端口号：3306
		 * 数据库名称：how2java
		 * 编码方式：UTE-8
		 * 账号 root
		 * 密码：admin
		 */
public class HeroDAO implements DAO {
	Connection c=null;
	Statement s= null;
	PreparedStatement ps;
	public HeroDAO() {
		//初始化驱动
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8", 
					"root", "admin");
			s = c.createStatement(); //对数据库进行操作的对象
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}catch(SQLException e) {
			e.printStackTrace();
		}		
	}
	
	@Override
	public void add(Hero hero) throws SQLException {
		String sql = "insert into hero values(null,?,?,?)";
	
		ps = c.prepareStatement(sql);
		ps.setString(1, hero.getName());
		ps.setFloat(2, hero.getHp());
		ps.setInt(3, hero.getDamage());
		ps.execute();
		
	}

	@Override
	public void delete(int id) throws SQLException {
		String sql = "delete from hero where id="+id;
		ps = c.prepareStatement(sql);
		
	}

	@Override
	public void update(int id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Hero get(int id) {
		// TODO Auto-generated method stub
		return null;
	}
	

}
