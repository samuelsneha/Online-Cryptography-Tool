/**
 * 
 */

const inputMessageVar = document.getElementById('inputMessage');
const outputMessageVar = document.getElementById('outputMessage');
const inputMessageDecr = document.getElementById('inputMessageDecr');
const outputMessageDecr = document.getElementById('outputMessageDecr');
const filePicker = document.getElementById('filePicker');
console.log("file picker is "+filePicker );
let email = document.getElementById('emailId').innerHTML;
console.log( email.innerHTML );
//const keyInput = document.getElementById('keyText');
console.log( inputMessageVar, outputMessageVar );
let singleEncryptionOutput, hybridEncryptionOutput, singleDecryptionOutput, hybridDecryptionOutput;
let firstTime = true;
let sessionStart = false, sessionEnd = false, hybridEncryptionFlag = false;

let flash = document.getElementById('flash1');
let closeButton = document.getElementById('toastCloseBttn1');

closeButton.addEventListener('click', function() {
	console.log( 'close 1 clicked ');
	flash.style.display = 'none';
});

function singleEncryption( event ){
	event.preventDefault();	
	firstTime = true;
	console.log( " its working");
	var value = inputMessageVar.value.trim();
    if( value === '' ){
	    console.log( " its empty ");
	    alert("Kindly enter some text");
	}
	else{
		console.log( " its not empty ");
		sessionStart = true;
		var originalMessage = inputMessageVar.value;
		console.log(originalMessage);
	    //var url1 = "/hybrid/firstEncryption?variable="+originalMessage;
	    var url1 = "/hybrid/firstEncryption";
        const xhttp = new XMLHttpRequest(); //We could have also used fetch API instead of xhr object
        xhttp.onreadystatechange = function () {
	         if (xhttp.readyState === XMLHttpRequest.DONE) {
	             if (xhttp.status === 200) {
	             console.log(" request is ready "); 
	             console.log( xhttp.responseText);   
	             singleEncryptionOutput = xhttp.responseText;   
	             outputMessageVar.innerText = singleEncryptionOutput;        
	            } else {
	                console.error("Error: " + xhttp.status);
	            }
	         }
        };
        const body = JSON.stringify({
		  text: originalMessage
        });
        xhttp.open("POST", url1, true);
        xhttp.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
	    xhttp.send(body);
	/*    fetch("/hybrid/firstEncryption", {
               method: "POST",
               body: JSON.stringify({
               text: originalMessage,
        }),
		  headers: {
		    "Content-type": "application/json; charset=UTF-8"
		  }
        }).then((response) => console.log(response.text())); */
        
     } 
     
}     

function hybridEncryption( event ){
	event.preventDefault();	
	console.log( " its working");
	hybridEncryptionFlag = true;
	var value = inputMessageVar.value.trim();
    if( value === '' ){
	    console.log( " its empty ");
	    alert("Kindly enter some text");
	}
	else{
		firstTime = false;
		console.log( " its not empty ");
		sessionStart = true;
		var originalMessage = inputMessageVar.value;
		console.log(originalMessage);
		//var url2 = "/hybrid/hybridEncryption?variable="+originalMessage;
		var url2 = "/hybrid/hybridEncryption";
		const xhttp = new XMLHttpRequest(); //We could have also used fetch API instead of xhr object
		xhttp.onreadystatechange = function () {
	         if (xhttp.readyState === XMLHttpRequest.DONE) {
	             if (xhttp.status === 200) {
	             console.log(" request is ready "); 
	             console.log( xhttp.responseText);   
	             hybridEncryptionOutput = xhttp.responseText;   
	             outputMessageVar.innerText = hybridEncryptionOutput;        
	            } else {
	                console.error("Error: " + xhttp.status);
	            }
	         }
        };
//  xhttp.open("GET", url2, true);
//  xhttp.send();
	    const body = JSON.stringify({
		  text: originalMessage
        });
        xhttp.open("POST", url2, true);
        xhttp.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
	    xhttp.send(body);
	}	
}


function keyAdd(){
	console.log("add a key ");
//    keyInput.setAttribute("style", "visibility:visible;");
//    console.log( keyInput.value )
//    console.log( inputMessageDecr.value )
}


function singleDecryption( event ){
	event.preventDefault();	
//	console.log( " its working");
	var value = inputMessageDecr.value.trim();
//	var value2 = keyInput.value.trim();
//    if( value1 !== '' && value2 !== '' ){
//	    console.log( " its not empty ");
//	}
    if( value === '' ){
	    console.log( " its empty ");
	    alert("Kindly Encrypt first");
	}
	else if( hybridEncryptionFlag){
		alert(" Hybrid Encryption cannot be followed by Single Decryption.")
	} 
	else{
		console.log( " its  not empty ");
		if( sessionStart ){
		    sessionEnd = true;	
		}
		var decryptedMessage = inputMessageDecr.value;
//		var key =  keyInput.value;
		console.log(decryptedMessage );
//		console.log( key );
//		var url3 = "/hybrid/firstDecryption?variable="+decryptedMessage;
		var url3 = "/hybrid/firstDecryption";
		const xhttp = new XMLHttpRequest(); //We could have also used fetch API instead of xhr object
		xhttp.onreadystatechange = function () {
	         if (xhttp.readyState === XMLHttpRequest.DONE) {
	             if (xhttp.status === 200) {
	             console.log(" request is ready "); 
	             console.log( xhttp.responseText);   
	             singleDecryptionOutput = xhttp.responseText;   
	             outputMessageDecr.innerText = singleDecryptionOutput;        
	             } else {
	                console.error("Error: " + xhttp.status);
	             }
	         }
        };
//        xhttp.open("GET", url3, true);
//	    xhttp.send();
         const body = JSON.stringify({
		   text: decryptedMessage
         });
        xhttp.open("POST", url3, true);
        xhttp.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
	    xhttp.send(body);
	}	
}

	
function hybridDecryption( event ){
	event.preventDefault();	
	hybridEncryptionFlag = false;
	if ( firstTime == false ) {
		console.log( " its working");
		var value = inputMessageDecr.value.trim();
	    if( value === '' ){
		    console.log( " its empty ");
		}
		else{
			console.log( " its not empty ");
			if( sessionStart ){
		        sessionEnd = true;	
		    }
			var decryptedMessage = inputMessageDecr.value;
			console.log(decryptedMessage);
			var url4 = "/hybrid/hybridDecryption";
			const xhttp = new XMLHttpRequest(); //We could have also used fetch API instead of xhr object
			xhttp.onreadystatechange = function () {
		         if (xhttp.readyState === XMLHttpRequest.DONE) {
		             if (xhttp.status === 200) {
		             console.log(" request is ready "); 
		             console.log( xhttp.responseText);   
		             hybridDecryptionOutput = xhttp.responseText;   
		             outputMessageDecr.innerText = hybridDecryptionOutput;        
		            } else {
		                console.error("Error: " + xhttp.status);
		            }
		         }
	        };
//	        xhttp.open("GET", url4, true);
//		    xhttp.send();
         const body = JSON.stringify({
		    text: decryptedMessage
         });
        xhttp.open("POST", url4, true);
        xhttp.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
	    xhttp.send(body);
		}	
	   
	}
	else{
		console.log( " not first time ");
		alert( " Kindly first do Hybrid Encryption, and then do Hybrid Decryption");
	}
}



function navigateYourAccount( event ){
	event.preventDefault();
	console.log( " reached youActivity");
	console.log(" reached in navigate function");
	location.href = "http://localhost:8080/yourActivity?email="+encodeURIComponent(email);
//	console.log(" reached in navigate function");
/*	let url5 = "/yourActivity";
	const xhttp = new XMLHttpRequest(); //We could have also used fetch API instead of xhr object
	xhttp.onreadystatechange = function () {
         if (xhttp.readyState === XMLHttpRequest.DONE) {
             if (xhttp.status === 200) {
               console.log(" request is ready "); 
               console.log( xhttp.response);   
//             hybridDecryptionOutput = xhttp.responseText;   
//             outputMessageDecr.innerText = hybridDecryptionOutput;        
             }else {
                console.error("Error: " + xhttp.status);
            }
         }
    };
    xhttp.open("POST", url5, true);
    xhttp.send(); */	
}


function saveData( event ){
	event.preventDefault();
	if( sessionStart && sessionEnd ){
		console.log("you can save");
		sessionStart = false;
		sessionEnd = false;
		alert(" Your Activity is saved successfully");
		console.log(" *** "+inputMessageVar.value +" *** "+outputMessageVar.value + " *** "+inputMessageDecr.value +" *** "+outputMessageDecr.value );
		let plainTextMessage = inputMessageVar.value;
		let encryptedMessage = outputMessageVar.value;
		let decryptedMessage = outputMessageDecr.value;
		console.log( " *** "+plainTextMessage +" *** "+encryptedMessage +" *** "+decryptedMessage );
		let data = {
			plainTextMessage:inputMessageVar.value,
			encryptedMessage:outputMessageVar.value,
			decryptedMessage: outputMessageDecr.value,
			email:document.getElementById('emailId').innerHTML
		};
		let jsonData = JSON.stringify(data);
		let url6 = "/save/saveData";
	    const xhttp = new XMLHttpRequest(); //We could have also used fetch API instead of xhr object
	    xhttp.onreadystatechange = function () {
           if (xhttp.readyState === XMLHttpRequest.DONE) {
              if (xhttp.status === 200) {
               console.log(" request is ready "); 
//             console.log( xhttp.response);   
//             hybridDecryptionOutput = xhttp.responseText;   
//             outputMessageDecr.innerText = hybridDecryptionOutput;        
              }else {
                console.error("Error: " + xhttp.status);
              }
           }
        };
        xhttp.open("POST", url6, true);
        xhttp.setRequestHeader("Content-Type", "application/json; charset=UTF-8");
        xhttp.send(jsonData);
        
	}
	else{
		alert("Kindly complete your existing activity OR start a new activity ");
	}
	
}

function logoutFunction(event){
    event.preventDefault();
    location.href = "http://localhost:8080/login";	
}

/*function fileLoaded(event){
	event.preventDefault();
	let fileContent;
	console.log(" file is loaded or removed ");
	let file = event.target.files[0];
	console.log("file is "+file);
	if( file ){
		console.log("reached if of file support");
	    let reader = new FileReader();
	    reader.onload = function( event ){
			console.log("reached the onload of reader");
		    fileContent = event.target.result;
		    console.log("file contents are &&&"+fileContent +"&&&");
		}	
        reader.readAsText(file);
	    console.log("read as text result  "+reader.result); 
	    console.log(reader.readAsText(file));
	    
	}else{
		alert("Empty object cannot be a file ");
	}
}*/

