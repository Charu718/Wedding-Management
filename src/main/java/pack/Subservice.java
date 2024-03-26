package pack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Subservice {
	String pack;
	String item;
	Subcategory sub;
	typecheck type;
	String service;
	String image;
	public enum Subcategory{
		VEG,
		NON_VEG,
		MALE,
		FEMALE
	}
	public enum typecheck{
		BREAKFAST,
		LUNCH,
		DINNER,
		HAIR,
		MAKEUP
	}
	Subservice(){}
	public Subservice(String pack, String item, Subcategory sub, typecheck type, String service, String image){
		this.pack = pack;
		this.item = item;
		this.service = service;
		this.sub = sub;
		this.type = type;
		this.image = image;
	}
	public Subservice(String pack, String item, String service, String image){
		this.pack = pack;
		this.item = item;
		this.service = service;
		this.image = image;
	}
	String getPack() {
		return this.pack;
	}
	String getItem() {
		return this.item;
	}
	Subcategory getCategory() {
		return this.sub;
	}
	typecheck getType() {
		return this.type;
	}
	String getService() {
		return service;
	}
	String getImage() {
		return this.image;
	}
	static Subservice  getInstance() {
		return new Subservice();
	}
	
}
