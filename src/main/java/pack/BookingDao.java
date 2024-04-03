package pack;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingDao {
	
	private static BookingDao utility = null;
	
	public static BookingDao getUtility(){  
		if(utility == null) {
			utility = new BookingDao();
		}
		return utility;   
	} 
	
	boolean isValidBooking(String place, String startdate, String enddate) throws SQLException{
		Connection connection = Jdbc.getJdbc().getConnection();
		PreparedStatement pstmt = connection.prepareStatement("select event_stdate, event_endDt, citySelected from Customer where citySelected like ?");
		pstmt.setString(1, place);
		ResultSet res = pstmt.executeQuery();
		while(res.next()) {
			System.out.println("startdate - " + startdate + "&& " + res.getString("event_stdate") +" --  "+(startdate.compareTo(res.getString("event_stdate")) >= 0));
			System.out.println("startdate - " + startdate + "&& " + res.getString("event_endDt") +" --  "+(startdate.compareTo(res.getString("event_endDt")) <= 0 ));
			System.out.println("enddate - " + enddate + "&& " + res.getString("event_endDt") +" --  "+(enddate.compareTo(res.getString("event_endDt")) > 0));
//			if(!(startdate.compareTo(res.getString("event_stdate")) >= 0 && (startdate.compareTo(res.getString("event_endDt")) <= 0 || enddate.compareTo(res.getString("event_endDt")) > 0)) ){
//				return true;
//			}
		}
		return true;
	}
}
