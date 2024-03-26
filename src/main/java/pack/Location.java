package pack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Location {
	String city;
	String venue;
	Location(){}
	Location(String city, String venue){
		this.city = city;
		this.venue = venue;
	}
	String getCity() {
		return this.city;
	}
	String getVenue() {
		return this.venue;
	}
	public static Location getInstance(){
		return new Location();
	}
	List<String> getAllCity(){
		ResultSet res =null;
		List<String> city = null;
		try {
			Connection connection = Jdbc.getJdbc().getConnection();
			PreparedStatement stmt = connection.prepareStatement("select city_name from City");
			city = new ArrayList<String>();
			res = stmt.executeQuery();
			while(res.next()) {
				String cityname = res.getString(1);
				city.add(cityname);
			}
		} catch (SQLException e) {
			e.getMessage();
		} 
		return city;
	}
	List<String> getAllVenueForCity(String cityChoosed){
		ResultSet res =null;
		List<String> venue = null;
		try {
			Connection connection = Jdbc.getJdbc().getConnection();
			PreparedStatement stmt = connection.prepareStatement("select venue_name from (select V.Venue_id, V.venue_name, C.city_name from Venue V join City C on C.City_id = V.c_id)as cities where city_name like ?");
			stmt.setString(1, cityChoosed);
			venue = new ArrayList<String>();
			res = stmt.executeQuery();
			while(res.next()) {
				String cityname = res.getString(1);
				System.out.println(cityname + "thisssss");
				venue.add(cityname);
			}
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		} 
		System.out.println(venue);
		return venue;
	}
}
