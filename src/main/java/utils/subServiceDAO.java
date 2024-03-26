package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import pack.Subservice;
import pack.Subservice.Subcategory;
import pack.Subservice.typecheck;

public class subServiceDAO {
private static subServiceDAO ser = null;
	
	public static subServiceDAO getSubService(){  
		if(ser == null) {
			ser = new subServiceDAO();
		}
		return ser;   
	} 
	
	public List<Subservice> getAllSubservice(int packNo, String service){
		ResultSet res =null;
		List<Subservice> subservices = null;
		try {
			Connection connection = Jdbc.getJdbc().getConnection();
			PreparedStatement stmt = connection.prepareStatement("select p.P_Name, s.item_name, subcategory,type,c.Ser_name,picpath from Subservice as s join Pack as p on p.P_id = s.P_no join Service as c on c.Ser_id = s.ser_no where P_no = ?  and c.Ser_name like ?");
			stmt.setInt(1, packNo);
			stmt.setString(2, service);
			subservices = new ArrayList<Subservice>();
			res = stmt.executeQuery();
			while(res.next()) {
				
				if(res.getString(4)==null || res.getString(3)==null) {
					Subservice ser = new Subservice((res.getString(1)), res.getString(2), res.getString(5), res.getString(6));
					subservices.add(ser);
				}
				else {
					Subservice ser = new Subservice((res.getString(1)), res.getString(2), Subcategory.valueOf(res.getString(3).toUpperCase().replace("-", "_")), typecheck.valueOf(res.getString(4).replace("bf", "BREAKFAST").toUpperCase()), res.getString(5), res.getString(6));
					subservices.add(ser);
				}
				
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		System.out.println(subservices + "is class");
		return subservices;
	}
	public List<Subservice> getAllSubserviceForcustom() throws SQLException{
		ResultSet res =null;
		List<Subservice> subservices = null;
		
			Connection connection = Jdbc.getJdbc().getConnection();
			PreparedStatement stmt = connection.prepareStatement("select p.P_Name, s.item_name, subcategory,type,c.Ser_name,picpath from Subservice as s join Pack as p on p.P_id = s.P_no join Service as c on c.Ser_id = s.ser_no");

			subservices = new ArrayList<Subservice>();
			res = stmt.executeQuery();
			while(res.next()) {
				
				if(res.getString(4)==null || res.getString(3)==null) {
					Subservice ser = new Subservice((res.getString(1)), res.getString(2), res.getString(5), res.getString(6));
					subservices.add(ser);
				}
				else {
					Subservice ser = new Subservice((res.getString(1)), res.getString(2), Subcategory.valueOf(res.getString(3).toUpperCase().replace("-", "_")), typecheck.valueOf(res.getString(4).replace("bf", "BREAKFAST").toUpperCase()), res.getString(5), res.getString(6));
					subservices.add(ser);
				}
				
			}
		 
		System.out.println(subservices + "is class");
		return subservices;
	}
}
