


function showLogout(){
	var signinlogout = document.getElementById("signin");
	signinlogout.innerText = "Logout";
	signinlogout.setAttribute("onclick", "logout()");
}
function logout(){
	var req = new XMLHttpRequest();
   req.onreadystatechange = function(){
     if(this.readyState == 4 && this.status == 200){
		 var signinlogout = document.getElementById("signin");
		 document.getElementById("currUser").innerText = "";
		 signinlogout.innerText = "Signin";
		 alert("logout successfull");
		 var change = document.getElementById("signin");
		 change.setAttribute("onclick", "getDetailIn()");
     }
   }
   req.open('POST', "http://localhost:8080/Wedding_management/logout");
   req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
   req.send();
}

function getDetailIn(){
	document.getElementById("email").style.display = "none";
  document.getElementsByClassName("signupButton")[0].style.display = "none";
  document.getElementsByClassName("signinButton")[0].style.display = "block";
  document.getElementById("askDetail").style.display = "block";
  document.getElementById("toDisplay").innerText = "Signin";
}
function getDetailUp(){
	document.getElementById("email").style.display = "block";
  document.getElementsByClassName("signinButton")[0].style.display = "none";
  document.getElementsByClassName("signupButton")[0].style.display = "block";
  document.getElementById("askDetail").style.display = "block";
  document.getElementById("toDisplay").innerText = "Signup";
  document.getElementsByClassName("alternate")[0].style.display = "none";
  document.getElementsByClassName("alternate")[1].style.display = "none";
}


 function validate(){
	 
   var username = document.getElementById("user").value;
   var password = document.getElementById("pass").value;
   console.log(username, password);
   
   var req = new XMLHttpRequest();
   req.onreadystatechange = function(){
     if(this.readyState == 4 && this.status == 200){
       
       if(this.responseText.includes("Welcome")){
		   window.location.href='welcome.html';
	   }
       else{alert(this.responseText);}
       
     }
   }
   var operation = "signin";
   req.open('POST', "http://localhost:8080/Wedding_management/servlet");
   req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
   req.send("username=" + username + "&password=" + password + "&operation=" + operation);
 }


 function signupValidate(){
   var username = document.getElementById("user").value;
   var password = document.getElementById("pass").value;
   
   console.log(username, password);
   var req = new XMLHttpRequest();
   req.onreadystatechange = function(){
     if(this.readyState == 4 && this.status == 200){
       alert(this.responseText);
       document.getElementById("user").value = "";
       document.getElementById("pass").value = "";
     }
   }
   var operation = "signup";
   req.open('POST', "http://localhost:8080/Wedding_management/signup");
   req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
   req.send("username=" + username + "&password=" + password + "&operation=" + operation);
 }
 
 
 function countPack(){
	 var req = new XMLHttpRequest();
	 req.onreadystatechange = function(){
     if(this.readyState == 4 && this.status == 200){
		 if(this.responseText != null || this.responseText != ""){
			console.log(this.responseText);
		 var packs = JSON.parse(this.responseText);
       var pack = sortObj(packs);
       displayPacks(pack);  
		 } 
		 else{
			 console.log("error");
		 }
     }
   }
   req.open('POST', "http://localhost:8080/Wedding_management/showPack");
   req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
   req.send();
 }
 countPack();
 
 function sortObj(packs){
	 const keyValueArray = Object.entries(packs);
       keyValueArray.sort((a, b) => {
  		if (a[1] < b[1]) return 1;
  		if (a[1] > b[1]) return -1;
		return a[1] - b[1];
});
	 const sortedObject = Object.fromEntries(keyValueArray);
	 return sortedObject;
 }
 
 
 function displayPacks(pack){
	 var size = Object.keys(pack).length;
	 var element = document.getElementById("packages");
	 var elem = document.getElementById("line");
	 var packName = document.createElement("p");
		 	packName.className = "packChoosed";
		 	packName.innerText = "Our Packages";
		 	elem.appendChild(packName);
		 	packName.style.marginLeft = "4%";
	 for(i=0; i<size; i++){
		 var row = document.createElement("div");
		 row.className=`eachPack ${(Object.keys(pack))[i]}`;
		 element.appendChild(row);
		 row.setAttribute("Package", (Object.keys(pack))[i]);
		 var text = document.createElement("p");
		 row.appendChild(text);
		 text.className = "packName";
		 row.setAttribute("pack", i+1);
		 document.getElementsByClassName("packName")[i].innerText = (Object.keys(pack))[i] + "\n" +  "Rs."+(Object.values(pack))[i];
		 var buy = document.createElement("button");
		 buy.className = "buyButton";
		 buy.setAttribute("pkChoosed", (Object.keys(pack))[i]);
		 buy.innerText = "Book this package";
		 buy.style.fontSize = "1.2em";
		 buy.style.fontWeight = "bolder";
		 buy.setAttribute("onclick", "redirectpageWithDetails(this.getAttribute('pkChoosed'))");
		 row.appendChild(buy);
		 row.setAttribute("onclick", "servletCallShowService(this.getAttribute('pack'))");
	 }
	 var custom = document.createElement("div");
	 custom.className = "eachPack custom";
	 element.appendChild(custom);
	 custom.setAttribute("onclick", "customizePack()");
	 var textcustom = document.createElement("p");
	 textcustom.innerText = "Customize your pack";
	 custom.appendChild(textcustom);
	 textcustom.className = "packName";
 }
 
 function redirectpageWithDetails(pack){
	 console.log(pack)
	 localStorage.setItem("packBook", pack);
	 window.location.href = "form.html";
 }
 
 function servletCallShowService(pack){
   var req = new XMLHttpRequest();
   localStorage.setItem("packno", pack);
   req.onreadystatechange = function(){
     if(this.readyState == 4){
		 if(this.status == 200){
		 	console.log(this.responseText); 
		 	var serviceObj = JSON.parse(this.responseText).data;
		 	localStorage.setItem("service", JSON.stringify(serviceObj));
		 	window.location.href = "showServices.html";
		 	//displayService(serviceObj);
   	 	 }else{
			alert("Can't get Response!");
		 }
	 }
   	 
   }
   req.open('POST', "http://localhost:8080/Wedding_management/showPack");
   req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
   req.send("pack="+"pack");
}
function changePage(){
	window.location.href = "showPacks.html";
}
function customizePack(){
	var req = new XMLHttpRequest();
   req.onreadystatechange = function(){
     if(this.readyState == 4){
		 if(this.status == 200){
		 	console.log(this.responseText); 
		 	var subserviceObj = this.responseText;
		 	localStorage.setItem("subservicecustom", subserviceObj);
		 	window.location.href = "customPack.html";
   	 	 }else{
			alert("Can't get Response!");
		 }
	 }
   	 
   }
   req.open('POST', "http://localhost:8080/Wedding_management/getAllDetails");
   req.setRequestHeader("Content-Type", "application/x-www-form-urlencoded");
   req.send();
}
 
 
 
 
 
 
 
 
 
 
 