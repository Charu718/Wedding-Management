package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.json.simple.JSONArray;

import pack.CustomerDetails;

public class customerDAO {
	
private static customerDAO cus = null;
	
	public static customerDAO getCus(){  
		if(cus == null) {
			cus = new customerDAO();
		}
		return cus;   
	} 
	
	public JSONArray getcusDetails(String currname){
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
}
