package pack;

import java.time.LocalDate;

public class Bill {
	String name;
	String bride;
	String groom;
	String address;
	String phone;
	LocalDate stdt;
	LocalDate end;
	String city;
	String venue;
	int attendees;
	Bill(String name, String bride,String groom, String address, String phone, LocalDate stdt, LocalDate end, String city, String venue, int attendees){
		this.name = name;
		this.bride = bride;
		this.groom = groom;
		this.address = address;
		this.phone = phone;
		this.stdt = stdt;
		this.end = end;
		this.city = city;
		this.venue = venue;
		this.attendees = attendees;
	}
	Bill(){}
	String addBill() {
		return "";
	}
}
