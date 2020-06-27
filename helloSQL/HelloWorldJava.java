package helloSQL;

public class HelloWorldJava {
	public static void main(String[] args) throws ClassNotFoundException {
	try {
		//数据库驱动加载
		Class.forName("com.mysql.jdbc.Driver");
		
		System.out.println("Ok");
	}catch(ClassNotFoundException e) {
		e.printStackTrace();
	}
	
	
	
	}
}
