package pack;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.BCrypt;

import pack.Jdbc;

import java.util.regex.Matcher;
import java.util.regex.Pattern;


/**
 * Servlet implementation class servlet
 */
@WebServlet("/servlet")
public class servlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public servlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String username = request.getParameter("username");
        String password = request.getParameter("password");
//        String operation = request.getParameter("operation");
        
        String result = "";
        result = login(username, password, response);
        
        response.getWriter().write(result);
	}
	
	String login(String username, String password, HttpServletResponse response){
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

