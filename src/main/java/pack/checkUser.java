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

import pack.Jdbc;

import jakarta.servlet.http.Cookie;

/**
 * Servlet implementation class checkUser
 */
@WebServlet("/checkUser")
public class checkUser extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public checkUser() {
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
		Cookie cookie[] = request.getCookies();
		ResultSet res = null;
		if(cookie != null) {
			String id = cookie[1].getValue();
			String name = cookie[0].getValue();
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
			
			if(id != null && id != "") {
				if(id.equals(idDB)) {
					response.getWriter().write("Welcome-" + name);
				}
				
			}
		} 
		else {
			response.getWriter().write("please login");
		}
	}

}
