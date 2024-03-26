package utils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import org.json.simple.JSONArray;

import pack.CustomerDetails;
import pack.Subservice.Subcategory;
import pack.Subservice.typecheck;
import pack.*;

public class utilityforpacks {
	
	private static utilityforpacks utility = null;
	
	public static utilityforpacks getUtility(){  
		if(utility == null) {
			utility = new utilityforpacks();
		}
		return utility;   
	} 
	
	
	public LinkedHashMap<String, Integer> getAllPack(){
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
	
	
}
