var subservice = JSON.parse(localStorage.getItem('subservice')).data;
console.log(subservice);
/*var text = document.createElement("p");
text.innerText = subservice;
var parent = document.getElementById("Subservices");
parent.appendChild(text);*/
var parent = document.getElementById("Subservices");
for(i=0; i<subservice.length; i++){
	var minielemfood = document.createElement("div");
	minielemfood.className = "foods";
	var img = document.createElement("img");
	img.src = subservice[i].image;
	img.className = "eachfoodsize";
	minielemfood.appendChild(img);
	//minielemfood.style.backgroundImage = 'url('+subservice[i].image+')';
	parent.appendChild(minielemfood);
	var textelem1 = document.createElement("p");
	var textelem2 = document.createElement("p");
	var textelem3 = document.createElement("p");
	var foodObj = subservice[i];
	textelem1.innerText = foodObj.item + "\n\n";
	if(foodObj.Category != undefined && foodObj.Type != undefined){
		textelem2.innerText = foodObj.Type + "\n\n";
		textelem3.innerText = foodObj.Category + "\n\n";
	}
	minielemfood.appendChild(textelem1);
	if(textelem3.innerText == "VEG"){
		textelem3.style.color = "green";
	}
	else{
		textelem3.style.color = "red";
	}
	
	minielemfood.appendChild(textelem2);
	minielemfood.appendChild(textelem3);
	
}
function changePage(){
	window.location.href = "showPacks.html";
}

 
 