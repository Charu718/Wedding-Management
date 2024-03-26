package pack;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import utils.customerDAO;
import utils.utilityforpacks;

/**
 * Servlet implementation class Userbill
 */
@WebServlet("/Userbill")
public class Userbill extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Userbill() {
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
		String user = request.getParameter("username");
		JSONArray cusobject = customerDAO.getCus().getcusDetails(user);
		JSONArray arrayOfcustomerObj = new JSONArray();
		JSONObject customerObj = new JSONObject();
		for(int i=0; i<cusobject.size(); i++) {
			CustomerDetails cusobj = (CustomerDetails) cusobject.get(i);
			JSONObject cusDetails = new JSONObject();
			cusDetails.put("name", cusobj.getName());
			cusDetails.put("address", cusobj.getAddress());
			cusDetails.put("phno", cusobj.getPhone());
			cusDetails.put("bride", cusobj.getBride());
			cusDetails.put("groom", cusobj.getGroom());
			cusDetails.put("stdt", cusobj.getStdt().toString());
			cusDetails.put("end", cusobj.getEnd().toString());
			cusDetails.put("city", cusobj.getCity());
			cusDetails.put("venue", cusobj.getVenue());
			cusDetails.put("attendees", cusobj.getAttendees());
			cusDetails.put("pack", cusobj.getPack());
			cusDetails.put("date", cusobj.getBookeddate().toString());
			arrayOfcustomerObj.add(cusDetails);
		}
		customerObj.put("data", arrayOfcustomerObj);
		System.out.println(customerObj);
		response.getWriter().write(customerObj.toJSONString());
	}

}
