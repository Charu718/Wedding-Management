var xhr = new XMLHttpRequest();
   xhr.onreadystatechange = function(){
     if(this.readyState == 4){
		 if(this.status == 200){
		 	var cityLoc = Object.values(JSON.parse(this.responseText));
		 	console.log(this.responseText);
			createCityList(cityLoc);
		 	
   	 	 }else{
			alert("Can't get Response!");
		 }
	 }
   	 
   }
   xhr.open('POST', "http://localhost:8080/Wedding_management/Loadcities");
   xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
   xhr.send();
function changePage(){
	window.location.href = "showPacks.html";
}

 
 
function selectVenue(){
	var city = document.getElementById("city").value;
	console.log(city);
	var req = new XMLHttpRequest();
   req.onreadystatechange = function(){
     if(this.readyState == 4){
		 if(this.status == 200){
		 	var venueLoc = Object.values(JSON.parse(this.responseText));
			createVenueList(venueLoc);
		 	
   	 	 }else{
			alert("Can't get Response!");
		 }
	 }
   	 
   }
   req.open('POST', "http://localhost:8080/Wedding_management/getLocations");
   req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
   req.send("cityname=" + city);
}

	var elemSelect = document.getElementById("venue");
	while (elemSelect.hasChildNodes()){
               elemSelect.removeChild(elemSelect.firstChild)
        }


function createVenueList(venueLoc){
	for(i=0; i<venueLoc.length; i++){
		var options = document.createElement("option");
		options.text = venueLoc[i];
		elemSelect.appendChild(options);
	}
}

var elemSelectCity = document.getElementById("city");
function createCityList(cityLoc){
    for(var i = 0; i < cityLoc.length; i++){
        var options = document.createElement("option");
        options.text = cityLoc[i];
        elemSelectCity.appendChild(options);
    }
    var pack = document.getElementById("onlypack");
    pack.textContent = localStorage.getItem("packBook");
}
function getDetailsForcheck(){
	var name = document.getElementById("name").value;
	var bride = document.getElementById("bride").value;
	var groom = document.getElementById("groom").value;
	var address = document.getElementById("address").value;
	var phonenumber = document.getElementById("phno").value;
	var startdate = document.getElementById("stdt").value;
	var enddate = document.getElementById("enddt").value;
	var city = document.getElementById("city").value;
	var venue = document.getElementById("venue").value;
	var attendees = document.getElementById("attendees").value;
	var package = document.getElementById("package").value;
	var userMrgDetails = {
		cusname : name,
		cusaddress : address,
		cusphone : phonenumber,
		startdateselected : startdate,
		enddateselected : enddate,
		citychoosed : city,
		venueincity : venue,
		attendees : attendees,
		bridename : bride,
		groomname : groom
	};
	console.log(userMrgDetails);
	var req = new XMLHttpRequest();
	req.onreadystatechange = function(){
		if(this.status == 200 && this.readyState == 4){
		 	alert(this.responseText);
   	 	 }
   	 
   }
   
   req.open('POST', "http://localhost:8080/Wedding_management/addToDB");
   req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
   req.send("name=" + name  + "&bride="  + bride + "&address=" + address + "&groom=" + groom + "&phone=" + phonenumber + "&stdt=" + startdate + "&end=" + enddate + "&city=" + city + "&venue=" + venue + "&attendees=" + attendees + "&package=" + package);
}