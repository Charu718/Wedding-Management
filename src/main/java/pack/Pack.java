package pack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

public class Pack {
	int packID;
	String name;
	List<Feedback> feedback;
	List<Service> service;
	int cost;
	Pack(){}
	Pack(int packID, String name, int cost){
		this.name = name;
		this.cost = cost; 
	}
	String getName() {
		return this.name;
	}
	int getCost() {
		return this.cost;
	}
	public static Pack getInstance() {
		return new Pack();
	}
	
}
