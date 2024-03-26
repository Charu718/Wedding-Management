package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pack.Service;

public class serviceDAO {
private static serviceDAO ser = null;
	
	public static serviceDAO getService(){  
		if(ser == null) {
			ser = new serviceDAO();
		}
		return ser;   
	} 
	public List<Service> getAllService(){
		ResultSet res =null;
		List<Service> services = null;
		try {
			Connection connection = Jdbc.getJdbc().getConnection();
			PreparedStatement stmt = connection.prepareStatement("select * from Service");
			services = new ArrayList<Service>();
			res = stmt.executeQuery();
			while(res.next()) {
				Service ser = new Service(res.getInt(1), res.getString(2), res.getString(3));
				services.add(ser);
			}
		} catch (SQLException e) {
			e.getMessage();
		} 
		System.out.println(services.toString() + "kjdcbsjkd");
		return services;
	}
}
