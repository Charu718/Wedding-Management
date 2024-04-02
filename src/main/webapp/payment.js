function getCard(){
	console.log("checking card details");
	var card = document.getElementById("holdername").value;
	var number = document.getElementById("cardnum").value;
	var exp = document.getElementById("exp").value;
	var cvv = document.getElementById("cvv").value;
	
	if(isCardEmpty(card) && isNumberEmpty(number) && isCvvEmpty(cvv)){
		console.log("payment successfull");
		document.getElementById("success").style.display = "flex";
		document.getElementById("success").style.justifyContent = "center";
		document.getElementById("success").style.alignItems = "center";
		document.getElementsByClassName("half")[0].style.opacity = .3;
		document.getElementsByClassName("half")[1].style.opacity = .3;
		setTimeout(() => {
			window.location.href = "Userbills.html";
		}, 2000);
	}
}
function isCardEmpty(card){
	console.log(card != "" && card != undefined && card != null);
	return card != "" && card != undefined && card != null;
}
function isNumberEmpty(number){
	console.log(number != "" && number != undefined && number != null && (number+"").length == 16);
	return number != "" && number != undefined && number != null && (number+"").length == 16;
}
function isExpEmpty(exp){
	console.log(exp != "" && exp != undefined && exp != null && exp > Date.now());
	return exp != "" && exp != undefined && exp != null && exp > Date.now();
}
function isCvvEmpty(cvv){
	console.log(cvv != "" && cvv != undefined && cvv != null && (cvv+"").length == 3);
	return cvv != "" && cvv != undefined && cvv != null && (cvv+"").length == 3;
}