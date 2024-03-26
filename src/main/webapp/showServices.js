var serviceObj = JSON.parse(localStorage.getItem("service"));
function displayService(serviceObj){
	 var size = Object.values(serviceObj).length;
	 var element = document.getElementById("services");
			var packName = document.createElement("p");
		 	packName.className = "packChoosed";
		 	packName.innerText = "Our Services";
		 	element.appendChild(packName);
	 
	 for(i=0; i<size; i++){
		 var parent = document.createElement("div");
		 parent.className="parent";
		 element.appendChild(parent);
		 var name = Object.values(serviceObj)[i][i];
		 if(i%2 != 0){
			 var write = document.createElement("div");
		 	write.className="info";
		 	parent.appendChild(write);
		 	var topic = document.createElement("p");
		 	topic.className = "topic";
		 	topic.innerText = name.substring(0,name.indexOf("-"));
		 	write.appendChild(topic);
		 	var content = document.createElement("p");
		 	content.className = "description";
		 	content.innerText = name.substring(name.indexOf("-")+1);
		 	write.appendChild(content);
		 	var div = document.createElement("div");
		 	div.className=`eachService ${name.substring(0, name.indexOf("-"))}`;
		 	
		 	parent.appendChild(div); 
		 }
		 else{
			var div = document.createElement("div");
		 	div.className=`eachService ${name.substring(0, name.indexOf("-"))}`;
		 	parent.appendChild(div); 
		 	var write = document.createElement("div");
		 	write.className="info";
		 	parent.appendChild(write);
		 	var topic = document.createElement("p");
		 	topic.className = "topic";
		 	topic.innerText = name.substring(0,name.indexOf("-"));
		 	write.appendChild(topic);
		 	var content = document.createElement("p");
		 	content.className = "description";
		 	content.innerText = name.substring(name.indexOf("-")+1);
		 	write.appendChild(content);
		 }
		 div.setAttribute("service", name.substring(0,name.indexOf("-")));
		 div.setAttribute("number", i+1);
		 div.setAttribute("onclick", "servletCallShowSubservice(this.getAttribute('service'))");
		 
		 var text = document.createElement("p");
		 div.appendChild(text);
		 text.className = "packName";
		 document.getElementsByClassName("packName")[i].innerText = (Object.values(serviceObj))[i][i];
		 //div.setAttribute("onclick", "servletCallShowService()");
	 }
 }
displayService(serviceObj);
function changePage(){
	window.location.href = "showPacks.html";
}

 
 
function servletCallShowSubservice(service){
	var packno = localStorage.getItem("packno");
	console.log(packno + " - " +  service);
	var req = new XMLHttpRequest();
   req.onreadystatechange = function(){
     if(this.readyState == 4){
		 if(this.status == 200){
		 	console.log(this.responseText); 
		 	var subserviceObj = this.responseText;
		 	localStorage.setItem("subservice", subserviceObj);
		 	window.location.href = "showSubservice.html";
		 	//displayService(serviceObj);
   	 	 }else{
			alert("Can't get Response!");
		 }
	 }
   	 
   }
   req.open('POST', "http://localhost:8080/Wedding_management/getDetails");
   req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
   req.send("packno=" + packno + "&service=" + service);
}

