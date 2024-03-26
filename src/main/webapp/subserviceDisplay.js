var subserviceObj = JSON.parse(localStorage.getItem("subservice"));
function displaySubservice(subserviceObj){
	var size = Object.values(subserviceObj).length;
	var element = document.getElementById("Subservices");
			var packName = document.createElement("p");
		 	packName.className = "serviceChoosed";
		 	packName.innerText = "Food";
		 	element.appendChild(packName);
}
displaySubservice(subserviceObj);
function changePage(){
	window.location.href = "showPacks.html";
}

 
 