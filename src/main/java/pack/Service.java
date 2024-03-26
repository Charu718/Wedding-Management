package pack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;

import utils.subServiceDAO;

public class Service {
	String name;
	int id;
	String desc;
//	List<Subservice> subService = subServiceDAO.getSubService().getAllSubserviceForcustom();
	public Service(int id, String ser, String desc){
		this.id = id;
		this.name = ser;
		this.desc = desc;
	}
	Service(){}
	public String getName(){
		return this.name;
	}
	public String getDesc() {
		return this.desc;
	}
	public static Service getInstance(){
		return new Service();
	}
	public List<Service> getAllService() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
