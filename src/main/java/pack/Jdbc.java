package pack;

import java.sql.Connection;
import java.sql.DriverManager;

class Jdbc {
	private static Jdbc connect = null;
	Connection connection;
	private Jdbc() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/WeddingPlanner", "CHARU", "CHARU");
		} catch (Exception e) {
			System.out.println("Error connecting");
		}
	}
	
	public static Jdbc getJdbc(){  
		if(connect == null) {
			connect = new Jdbc();
		}
		  return connect;  
	} 
	Connection getConnection(){  
		  return connection;
	} 
}
