package helloSQL;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlConnect {
	public static void main(String[] args) throws ClassNotFoundException {
	
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
		Connection c = null;
		Statement s = null;
		try {
			Class.forName("com.mysql.jdbc.Driver");
			c = DriverManager.getConnection(
					"jdbc:mysql://127.0.0.1:3306/how2java?characterEncoding=UTF-8", 
					"root", "admin");
			System.out.println("链接成功获取链接对象："+c);
			
			s = c.createStatement(); //该对象用于实现数据库的增删
			SqlFun.sqlFun(s, "DELETE", "hero", null);
			
			for(int i = 1;i<=10;i++) {
				SqlFun.sqlFun(s, "INSERT", "hero", i+", 'timo"+i+"',313,100");//插入数据	
			}
			 for(int i = 1;i<=10;i++) { 				 
				 //SqlFun.sqlFun(s, "DELETE", "hero", "id="+i);
			 }
			 //查询数据
			 String sql = "select * from hero";
			 ResultSet rs = s.executeQuery(sql);
			 while(rs.next()){
				 int id = rs.getInt("id");
				 String name =rs.getString("name");
				 float hp = rs.getInt("hp");
				 int damage = rs.getInt("damage");
				 System.out.print(id+" "+name+" "+ hp+" "+damage);
				 System.out.println();
			 }
			 
			 //使用预编译插入数据
			 sql = "insert into hero values(null,?,?,?)";
			 PreparedStatement ps = c.prepareStatement(sql);
			 for (int i = 0; i < 10; i++) {
				 ps.setString(1, "timo"+i);
				 ps.setFloat(2, 500);
				 ps.setInt(3, 500);
				 ps.execute();
			 }
			 
			 //使用execute查询数句库，execute的返回值是布尔类型，通过getResultSet可以把结果取出来
			 sql = "select * from hero limit 1,5";//查询前5条数据
			 s.execute(sql);
			 rs = s.getResultSet();
			 while(rs.next()){
				 int id = rs.getInt("id");
				 String name =rs.getString("name");
				 float hp = rs.getInt("hp");
				 int damage = rs.getInt("damage");
				 System.out.print(id+" "+name+" "+ hp+" "+damage);
				 System.out.println();
			 }
			 //事务，既是同时执行多条SQL语句，若其中的某条语句有语法问题，而不能执行，则事务中的语句都不执行
			 {
				 c.setAutoCommit(false);
				 
				 SqlFun.sqlFun(s, "UPDATE", "hero", "hp=hp-1 where id = 1");
				 SqlFun.sqlFun(s, "UPDATE", "hero", "hp=hp+1 where id = 1");
				 c.commit();
			 }
			 //查询修改后的结果
			 sql = "select * from hero limit 1";
			 s.execute(sql);
			 rs = s.getResultSet();
			 while(rs.next()){
				 int id = rs.getInt("id");
				 String name =rs.getString("name");
				 float hp = rs.getInt("hp");
				 int damage = rs.getInt("damage");
				 System.out.print(id+" "+name+" "+ hp+" "+damage);
				 System.out.println();
			 }
			 System.out.println();
			 
			 //ORM 对象和数据库的映射， 即一条记录对应一个对象。
			 Hero hero = null;
			 int idnumber = 5;
			 sql = "select * from hero where id="+idnumber;
			 s.execute(sql);
			 rs = s.getResultSet();
			 if(rs.next()){
				 
				 int id = rs.getInt("id");
				 String name =rs.getString("name");
				 float hp = rs.getInt("hp");
				 int damage = rs.getInt("damage");
				 hero = new Hero(id, name, hp, damage);
				 //
				 //System.out.print(id+" "+name+" "+ hp+" "+damage);
				 System.out.println(hero);
			 }
			 //使用ORM的原理插入数据
			 Hero hero1= new Hero(500, "gailun", 500, 200);
			 SqlFun.sqlFun(s, "INSERT", "hero", hero1.getId()+", '"+hero1.getName()+"', "+
			 hero1.getHp()+", "+hero1.getDamage());
			 
			 Hero hero11 = null;
			 idnumber = 500;
			 sql = "select * from hero where id="+idnumber;
			 s.execute(sql);
			 rs = s.getResultSet();
			 if(rs.next()){
				 
				 int id = rs.getInt("id");
				 String name =rs.getString("name");
				 float hp = rs.getInt("hp");
				 int damage = rs.getInt("damage");
				 hero11 = new Hero(id, name, hp, damage);
				 //
				 //System.out.print(id+" "+name+" "+ hp+" "+damage);
				 System.out.println(hero11);
			 }
	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally
		{
			//关闭资源
			//1.先关闭statement
			if(s!=null) {
				try {
					s.close();
				}catch(SQLException e) {
					e.printStackTrace();
				}
			}
			//2.关闭Connection
			if(c!=null)
			try {
				c.close();
			}catch(SQLException e) {
				e.printStackTrace();
			}
		}
		
	}

}
