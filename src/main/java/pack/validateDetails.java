package pack;

import java.io.IOException;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;
import jakarta.servlet.http.HttpFilter;

/**
 * Servlet Filter implementation class validateDetails
 */
@WebFilter("/addToDB")
public class validateDetails extends HttpFilter implements Filter {
       
    /**
     * @see HttpFilter#HttpFilter()
     */
    public validateDetails() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		LocalDate today = LocalDate.now();
        DateTimeFormatter formatdt = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String currdate = today.format(formatdt);
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
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
		String regex = "^[a-zA-Z0-9\\s,'-]*$";
		System.out.println(stdt + "  "+currdate + "  " + today.toString());
		if(name != null && bride != null && bride != null && groom != null && address != null && phone != null && stdt != null && end != null && city != null && venue != null && attendees != null) {
			if(address.matches(regex) && phone.length()==10 && stdt.compareTo(today.toString()) > 0 && end.compareTo(stdt) > 0) {
				try {
					if(BookingDao.getUtility().isValidBooking(city, stdt, end)) {
						chain.doFilter(request, response);
					}
				} catch (Exception e) {
					response.getWriter().write("Something went wrong!!");
				} 
			}
			else {
				response.getWriter().write("Incorrect details!!");
				
			}
		}
		
		
		
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
