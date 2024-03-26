package pack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONArray;

import pack.Subservice.Subcategory;
import pack.Subservice.typecheck;

public class utilityforpacks {
	
	private static utilityforpacks utility = null;
	
	public static utilityforpacks getUtility(){  
		if(utility == null) {
			utility = new utilityforpacks();
		}
		return utility;   
	} 
	
	JSONArray getcusDetails(String currname){
		Connection connection = Jdbc.getJdbc().getConnection();
		JSONArray detArr = new JSONArray();
		ResultSet res = null;
		CustomerDetails cus = null;
		try {
			PreparedStatement pstmt = connection.prepareStatement(" select * from Customer where Name like ?");
			pstmt.setString(1, currname);
			res = pstmt.executeQuery();
			while(res.next()) {
				cus = new CustomerDetails(res.getString(2), res.getString(3), res.getString(4), res.getString(5), res.getString(6), res.getDate(7), res.getDate(8), res.getString(9), res.getString(10), res.getString(11), res.getString(12), res.getDate(13));
				detArr.add(cus);
			}
			
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return detArr;
	}
	LinkedHashMap<String, Integer> getAllPack(){
		LinkedHashMap<String, Integer> packSet = new LinkedHashMap<String, Integer>();
		ResultSet res =null;
		try {
			Connection connection = Jdbc.getJdbc().getConnection();
			PreparedStatement stmt = connection.prepareStatement("select * from Pack");
			res = stmt.executeQuery();
			while(res.next()) {
				packSet.put(res.getString(2), res.getInt(3));
			}
		} catch (SQLException e) {
			e.getMessage();
		} 
		return packSet;
	}
	List<Service> getAllService(){
//		subService = new ArrayList<Subservice>();
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
	List<Subservice> getAllSubservice(int packNo, String service){
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
	List<Subservice> getAllSubserviceForcustom(){
		ResultSet res =null;
		List<Subservice> subservices = null;
		try {
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
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		System.out.println(subservices + "is class");
		return subservices;
	}
}
