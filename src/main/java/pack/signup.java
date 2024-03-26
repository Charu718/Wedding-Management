package pack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.mindrot.jbcrypt.*;

import pack.Jdbc;

/**
 * Servlet implementation class signup
 */
@WebServlet("/signup")
public class signup extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public signup() {
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
		
		String result = signUp(username, password, response);
        
        response.getWriter().write(result);
		
	}
	
	String signUp(String username, String password, HttpServletResponse response){
		String regex = "^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)(?=.*[@#$%^&+=])(?=\\S+$).{8,20}$";

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

}
