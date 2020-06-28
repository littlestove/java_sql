package helloSQL;

import java.sql.SQLException;

public interface DAO {
	public void add(Hero hero) throws SQLException;
	public void delete(int id) throws SQLException;
	public void update(int id);
	public Hero get(int id);
	

}
