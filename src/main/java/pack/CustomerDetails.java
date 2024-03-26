package pack;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import utils.Jdbc;

public class CustomerDetails {
	String name;
	String bride;
	String groom;
	String address;
	String phone;
	Date stdt;
	Date end;
	String city;
	String venue;
	String attendees;
	String pack;
	Date bookedDate;
	String getName() {
		return this.name;
	}
	String getBride() {
		return this.bride;
	}
	String getGroom() {
		return this.groom;
	}
	String getAddress() {
		return this.address;
	}
	String getPhone() {
		return this.phone;
	}
	Date getStdt() {
		return this.stdt;
	}
	Date getEnd() {
		return this.end;
	}
	String getCity() {
		return this.city;
	}
	String getVenue() {
		return this.venue;
	}
	String getAttendees() {
		return this.attendees;
	}
	String getPack() {
		return this.pack;
	}
	Date getBookeddate() {
		return this.bookedDate;
	}
	public CustomerDetails(String name, String address,String phone, String bride, String groom, Date stdt, Date end, String city, String venue, String attendees, String pack, Date booked_date){
		this.name = name;
		this.bride = bride;
		this.groom = groom;
		this.address = address;
		this.phone = phone;
		this.stdt = stdt;
		this.end = end;
		this.city = city;
		this.venue = venue;
		this.attendees = attendees;
		this.pack = pack;
		this.bookedDate = booked_date;
	}
	static CustomerDetails getInstance() {
		return new CustomerDetails();
	}
	
	CustomerDetails(){}
	String addCustomerDetails(String name, String bride,String groom, String address, String phone, Date stdt, Date end, String city, String venue, String attendees, String pack) {
		LocalDate date = LocalDate.now();    
		Date bookDate = Date.valueOf(date);
		try {
			Connection connection = Jdbc.getJdbc().getConnection();
			PreparedStatement stmt=connection.prepareStatement("insert into Customer(Name,Address,PhoneNumber,bride_name,groom_name,event_stdate,event_endDt,citySelected,venueSelected,attendees,pack,booked_date) values(?,?,?,?,?,?,?,?,?,?,?,?)");  
			stmt.setString(1, name);
			stmt.setString(2, address);
			stmt.setString(3, phone);
			stmt.setString(4, bride);
			stmt.setString(5, groom);
			stmt.setDate(6, stdt);
			stmt.setDate(7, end);
			stmt.setString(8, city);
			stmt.setString(9, venue);
			stmt.setString(10, attendees);
			stmt.setString(11, pack);
			stmt.setDate(12, bookDate);
			int isAdded = stmt.executeUpdate();
			if(isAdded == 1) {
				return "details added successfully"; 
			}
			connection.close();  
			
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		
		return "something went wrong"; 
	}
	
	
}
