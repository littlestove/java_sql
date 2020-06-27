package helloSQL;

import java.sql.SQLException;
import java.sql.Statement;

public class SqlFun {
	//SQL的增删改操作
	//SqL的增删改语法：
	/**
	 * 增：insert into hero values (null, '盖伦', 616, 100)
	 * 删：delete from hero where id = 1
	 * 改：update hero set hp = 818 where id = 1
	 * @throws SQLException 
	 */
	public static void sqlFun(Statement s,String action, String tableName, String data) throws SQLException {
		//增
		StringBuilder sb = new StringBuilder();
		SqlAct act = SqlAct.valueOf(action);//使用枚举类型
		switch(act) {
		case INSERT:
			sb.append("insert into ");
			sb.append(tableName);
			sb.append(" values(");
			sb.append(data);
			sb.append(")");
			break;
		case DELETE:
			sb.append("delete from ");
			sb.append(tableName);
			if(data==null)
				break;
			sb.append(" where ");
			sb.append(data);
			break;
		case UPDATE:
			sb.append("update ");
			sb.append(tableName);
			sb.append(" set ");
			sb.append(data);
			break;
		case NULL:
			sb.append("test");
			break;
		}
		
		s.execute(sb.toString());
	}
}
