package pack;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.json.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import utils.customerDAO;
import utils.serviceDAO;
import utils.utilityforpacks;

/**
 * Servlet implementation class showPack
 */
@WebServlet("/showPack")
public class showPack extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public showPack() {
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
		String packName = request.getParameter("pack");
		try {
			if(packName == null || packName == "") {
				LinkedHashMap<String, Integer> packsList = utilityforpacks.getUtility().getAllPack();
				JSONObject packObj = new JSONObject(packsList);
				response.getWriter().write(packObj.toJSONString());
			}
			else {
				JSONObject serviceData = new JSONObject();
				List<Service> serviceList = serviceDAO.getService().getAllService();
				List<String> values = new ArrayList<String>();
				System.out.println("......"+serviceList);
				JSONArray serviceObj = new JSONArray();
				for(int i=0; i<serviceList.size(); i++) {
					JSONObject obj = new JSONObject();
					obj.put(i+"", serviceList.get(i).getName() + "-" + serviceList.get(i).getDesc());
					serviceObj.add(obj);
				}
				serviceData.put("Statuscode", 200);
				serviceData.put("data", serviceObj);
				response.getWriter().write(serviceData.toJSONString());
			}
		}
		catch(Exception e) {
			JSONObject error = new JSONObject();
			error.put(500, e.getMessage());
			response.getWriter().write(error.toJSONString());
		}
		
	}

}
