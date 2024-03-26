package pack;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import utils.subServiceDAO;

/**
 * Servlet implementation class getAllDetails
 */
@WebServlet("/getAllDetails")
public class getAllDetails extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public getAllDetails() {
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
			JSONObject subserviceData = new JSONObject();	
			List<Subservice> subserviceList;
			try {
				subserviceList = subServiceDAO.getSubService().getAllSubserviceForcustom();

				System.out.println(subserviceList);
				System.out.println("------"+subserviceList);
				JSONArray serviceObj = new JSONArray();
				for(int i=0; i<subserviceList.size(); i++) {
					JSONObject obj = new JSONObject();
					obj.put("pack", subserviceList.get(i).getPack());
					obj.put("item", subserviceList.get(i).getItem());
					obj.put("Service", subserviceList.get(i).getService());
					obj.put("image", subserviceList.get(i).getImage());
					if(subserviceList.get(i).getType() != null && subserviceList.get(i).getCategory() != null){
						obj.put("Type", subserviceList.get(i).getType().toString());
						obj.put("Category", subserviceList.get(i).getCategory().toString());
						
					}
					serviceObj.add(obj);
				}
				subserviceData.put("Statuscode", 200);
				subserviceData.put("data", serviceObj);
				
				response.getWriter().write(subserviceData.toJSONString());
			} catch (SQLException e) {
				JSONObject error = new JSONObject();
				error.put(500, e.getMessage());
				response.getWriter().write(error.toJSONString());
			}
	

	}
}
