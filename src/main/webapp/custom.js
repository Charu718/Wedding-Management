var allSubservices = localStorage.getItem("subservicecustom");
var arrayOfServices = JSON.parse(allSubservices).data;
var arrayCustom = new Set();
var parentforcustom = document.getElementById("custom");
for(i=0; i<arrayOfServices.length; i++){
	var selectSubservice = document.createElement("div");
	selectSubservice.className = "foods customcards";
	selectSubservice.setAttribute("selectedone", i);
	selectSubservice.setAttribute("onclick", "selectThisItem(this)");
	var img = document.createElement("img");
	img.src = arrayOfServices[i].image;
	img.className = "eachfoodsize";
	selectSubservice.appendChild(img);
	parentforcustom.appendChild(selectSubservice);
	var textelem1 = document.createElement("p");
	var textelem2 = document.createElement("p");
	var textelem3 = document.createElement("p");
	var foodObj = arrayOfServices[i];
	textelem1.innerText = foodObj.item + "\n\n";
	if(foodObj.Category != undefined && foodObj.Type != undefined){
		textelem2.innerText = foodObj.Type + "\n\n";
		textelem3.innerText = foodObj.Category + "\n\n";
	}
	selectSubservice.appendChild(textelem1);
	if(textelem3.innerText == "VEG"){
		textelem3.style.color = "green";
	}
	else if(textelem3.innerText == "NON_VEG"){
		textelem3.style.color = "red";
	}
	else if(textelem3.innerText == "MALE"){
		textelem3.style.color = "#0088ff";
	}
	else if(textelem3.innerText == "FEMALE"){
		textelem3.style.color = "pink";
	}
	
	
	selectSubservice.appendChild(textelem2);
	selectSubservice.appendChild(textelem3);
}


function checkForSize(){
	var foodCount = 0;
	var makeupCount = 0;
	var compCount = 0;
	var invitationCount = 0;
	var decorCount = 0;
	arrayCustom.forEach(function(val){
		if(val.Service == "Food"){
			foodCount++;
		}
		else if(val.Service == "Makeup"){
			makeupCount++;
		}
		else if(val.Service == "Complementary"){
			compCount++;
		}
		else if(val.Service == "Invitations"){
			invitationCount++;
		}
		else if(val.Service == "Decorations"){
			decorCount++;
		}
		/*if(foodCount > 1)*/
	})
}

function selectThisItem(element){
	var index = +element.getAttribute("selectedone");
	console.log(arrayOfServices[index]);
	
	arrayCustom.add(arrayOfServices[index]);
	console.log(arrayCustom);
	element.style.filter = "blur(2px)";
}
