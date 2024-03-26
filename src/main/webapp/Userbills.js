var req = new XMLHttpRequest();
   var username = document.cookie.split("; ")[0].split("=")[1];
   req.onreadystatechange = function(){
     if(this.readyState == 4 && this.status == 200){
		 console.log(this.responseText);
       	 var cusdetails = JSON.parse(this.responseText);
         console.log(cusdetails);
         showOldBills(cusdetails);
     }
   }
   var operation = "signin";
   req.open('POST', "http://localhost:8080/Wedding_management/Userbill");
   req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
   req.send("username=" + username);
 function changePage(){
	window.location.href = "showPacks.html";
}
function showOldBills(cusdetails){
	var arrayOfHistory = cusdetails.data;
	var parent = document.getElementById("Userbill");
	for(i=0; i<arrayOfHistory.length; i++){
		var bill = document.createElement("div");
		bill.className = "bill";
		parent.appendChild(bill);
		var billElem = document.createElement("p");
		billElem.innerText = "Name : " + arrayOfHistory[i].name + "\n\n" + "Address : " + arrayOfHistory[i].address + "\n\n" + "Phone number : " + arrayOfHistory[i].phno + "\n\n" + "Bride name" + arrayOfHistory[i].bride + "\n\n" + "Groom name" + arrayOfHistory[i].groom + "\n\n" + "Event start date : " + arrayOfHistory[i].stdt + "\n\n" + "Event end date : " + arrayOfHistory[i].end + "\n\n" + "City of preference : " + arrayOfHistory[i].city + "\n\n" + "Venue of preference : " + arrayOfHistory[i].venue + "\n\n" + "Number of attendees : " + arrayOfHistory[i].attendees + "\n\n" + "Pack : " + arrayOfHistory[i].pack + "\n\n" + "Date : " + arrayOfHistory[i].date;
		bill.appendChild(billElem);
	}
}

 
 