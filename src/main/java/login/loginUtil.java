package login;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletResponse;

import utils.Jdbc;
import utils.utilityforpacks;

public class loginUtil {
	
private static loginUtil util = null;
	
	public static loginUtil getUtility(){  
		if(util == null) {
			util = new loginUtil();
		}
		return util;   
	} 
	
	public String loginOnLoad(String name) {
		ResultSet res = null;
		String idDB = "";
		try {
			Connection conn = Jdbc.getJdbc().getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select session_id from session where username like ?");
			pstmt.setString(1, name);
			res = pstmt.executeQuery();
			while(res.next()) {
				idDB = res.getString(1);
			}
			
		}
		catch(SQLException e) {
			System.out.println(e.getMessage());
		}
		return idDB;
	}
	
	public String signupRes(String username, String password) {
		String regex = "^[a-zA-Z0-9_]{3,16}$";
		try {
			if(username == "" || password == "" || !password.matches(regex)) {return "Invalid entry or password must be strong";}
			Connection connection = Jdbc.getJdbc().getConnection();
			PreparedStatement stmt = connection.prepareStatement("insert into login(user, password) values (?, ?)");
			stmt.setString(1, username.trim());
			
			stmt.setString(2, password.trim());
			PreparedStatement stmt2 = connection.prepareStatement("select user from login where user like ? ");
			stmt2.setString(1, username);
			ResultSet checkUser = stmt2.executeQuery();
			if(!checkUser.next()) {
				int rowsUpdated = stmt.executeUpdate();
				if(rowsUpdated == 1) {
					return "Signup successful. Signin to enter";
				}
				else {
					return "Signup unsuccessfull";
				}
			}
			else {
				return "User already exist!!";
			}
		   
		} catch (SQLException es) {
			return es.getMessage();
		}
	}
	
	public String manualLogin(String username, String password, HttpServletResponse response) {
		String regex = "^[a-zA-Z0-9_]{3,16}$";
		try {
			if(username == "" || password == "" || !username.matches(regex)) {return "Invalid entry";}
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection connection = Jdbc.getJdbc().getConnection();
			PreparedStatement stmt = connection.prepareStatement("select user,password from login where user like ?");
			stmt.setString(1, username.trim());
			ResultSet res = stmt.executeQuery();
			if(res.next()) {
				if(res.getString(2).equals(password)) {
					String now = String.valueOf(System.currentTimeMillis());
					Cookie cookie = new Cookie("id", now);
					Cookie cookiename = new Cookie("name", username);
					cookiename.setMaxAge(200);
					cookie.setMaxAge(200);
					Connection conn = Jdbc.getJdbc().getConnection();
					PreparedStatement pstmtforCheck = conn.prepareStatement("select username from session");
					ResultSet result = pstmtforCheck.executeQuery(); 
					if(result.next()) {
						PreparedStatement pstmt1 = conn.prepareStatement("delete from session where username like ?");
						pstmt1.setString(1, username);
						pstmt1.executeUpdate();
					}
					PreparedStatement pstmt = conn.prepareStatement("insert into session values(?, ?)");
					pstmt.setString(1, now);
					
					pstmt.setString(2, username);
					pstmt.executeUpdate();
					response.addCookie(cookiename);  
					 response.addCookie(cookie);  
					return "Welcome!" + username;
				}else {
					return "Wrong password!";
				}
			}
			else {
				return "User Not found!";
			}
		   
		} catch (SQLException es) {
			return es.getMessage();
		}catch(ClassNotFoundException e) {
			return "class not found!";
		} 
	}
}
