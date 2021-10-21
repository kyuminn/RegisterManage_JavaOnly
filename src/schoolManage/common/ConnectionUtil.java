package schoolManage.common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {
	private static ConnectionUtil instance;
	private static String url="jdbc:oracle:thin:@localhost:1521:xe";
	private static String user="tester";
	private static String password="1234";
	
	private ConnectionUtil() {
		try {
			Class.forName("oracle.jdbc.OracleDriver");
		}catch(ClassNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public static ConnectionUtil getInstance() {
		if (instance==null) {
			instance= new ConnectionUtil();
		}
		return instance;
	}
	
	public Connection getConnection() throws SQLException{
		return DriverManager.getConnection(url, user, password);
	}

}
