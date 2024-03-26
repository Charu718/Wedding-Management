package pack;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class addToDB
 */
@WebServlet("/addToDB")
public class addToDB extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public addToDB() {
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
		LocalDate today = LocalDate.now();
        DateTimeFormatter formatdt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String currdate = today.format(formatdt);
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String name = request.getParameter("name");
		String bride = request.getParameter("bride");
		String groom = request.getParameter("groom");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		String pack = request.getParameter("package");
		String stdt = request.getParameter("stdt");
		String end = request.getParameter("end");
		Date utilDate;
		java.sql.Date sqlDate = null;
		Date utilDateEnd;
		java.sql.Date sqlDateEnd = null;
		try {
			utilDate = dateFormat.parse(stdt);
			sqlDate = new java.sql.Date(utilDate.getTime());
			utilDateEnd = dateFormat.parse(end);
			sqlDateEnd = new java.sql.Date(utilDateEnd.getTime());
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		
		String city = request.getParameter("city");
		String venue = request.getParameter("venue");
		String attendees = request.getParameter("attendees");
		String isAdded = CustomerDetails.getInstance().addCustomerDetails(name, bride, groom, address, phone, sqlDate, sqlDateEnd, city, venue, attendees, pack);
		if(isAdded == "details added successfully") {
			response.getWriter().write("success");
		}
		else {
			response.getWriter().write("something went wrong");
		}
	}

}
