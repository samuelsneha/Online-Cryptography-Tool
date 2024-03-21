
let email = document.getElementById('emailId');
let emailValue = email.innerHTML;

function logoutFunction(event){
	event.preventDefault();
    location.href = "http://localhost:8080/login";	
}

function returnToHome(event){
	event.preventDefault();
    location.href = "http://localhost:8080/home?email="+emailValue;	
}