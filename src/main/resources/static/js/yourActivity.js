/**
 * 
 */
console.log("reached yourActivity js");
let email = document.getElementById('emailId');
let tableContainer = document.getElementById('tableContainer');
console.log(email.innerHTML);
let emailValue = email.innerHTML;
let tableData = [];
showTable();

function showTable(){
	console.log(" reached showTable");
	let url7 = "/save/showTable?email="+emailValue;
	const xhttp = new XMLHttpRequest(); //We could have also used fetch API instead of xhr object
	xhttp.onreadystatechange = function () {
         if (xhttp.readyState === XMLHttpRequest.DONE) {
             if (xhttp.status === 200) {
               console.log(" request is ready "); 
               console.log( xhttp.response); 
               tableData = xhttp.response; 
               createTable( tableData ); 
//             hybridDecryptionOutput = xhttp.responseText;   
//             outputMessageDecr.innerText = hybridDecryptionOutput;        
             }else {
                console.error("Error: " + xhttp.status);
            }
         }
    };
    xhttp.open("GET", url7, true);
    xhttp.send();
}

function createTable( tableData ){
	console.log( " reached create table function" +tableData);
    let tableDataObj = JSON.parse(tableData );
    let tableDataObjLength = tableDataObj.length;
	if( tableDataObj.length == 0 ){ 
		//alert( 'You havent performed any activity yet ')
		let noActivity = document.createElement('div');
		//noActivity.setAttribute('width', '1500px' );
		//noActivity.style.backgroundColor = '#FEC7B4';
		//noActivity.style.height = '4rem';
		//noActivity.style.borderRadius = '23px';
		//noActivity.style.fontFamily = 'monospace'
		noActivity.classList.add("noActivityClass");
		noActivity.innerHTML = 'Oops! You dont have any activity yet! Start saving your activity and they will appar here';
		tableContainer.appendChild(noActivity);
		console.log( tableDataObj.length );
	}
	else{
		console.log('You have performed some activity');
		console.log( tableDataObj.length );
		var table = document.createElement('table');
		var headerRow = document.createElement('tr'); 
		headerRow.setAttribute('height', '50px');
        headerRow.setAttribute('width', '4000px'); 	
        
        let headerColumn1 = document.createElement('th');
        headerColumn1.classList.add("tableHeaderStyle");
        headerColumn1.setAttribute('height', '50px');
        headerColumn1.setAttribute('width', '1000px'); 			
		headerColumn1.innerHTML = 'PlainText Message';
		headerRow.appendChild(headerColumn1);
		
		let headerColumn2 = document.createElement('th');
		headerColumn2.classList.add("tableHeaderStyle");
        headerColumn2.setAttribute('height', '50px');
        headerColumn2.setAttribute('width', '1000px'); 			
		headerColumn2.innerHTML = 'Encrypted Message';
		headerRow.appendChild(headerColumn2);
		
		let headerColumn3 = document.createElement('th');
		headerColumn3.classList.add("tableHeaderStyle");
        headerColumn3.setAttribute('height', '50px');
        headerColumn3.setAttribute('width', '1000px'); 			
		headerColumn3.innerHTML = 'Decrypted Message';
//		headerRow.appendChild(headerColumn1, headerColumn2, headerColumn3);
        headerRow.appendChild(headerColumn3);
		table.appendChild( headerRow );
		
		for( let i = 0; i < tableDataObjLength; i++ ){
			let dataRow = document.createElement('tr');
			dataRow.setAttribute('height', '50px');
            dataRow.setAttribute('width', '1500px'); 
            
            let dataColumn1 = document.createElement('td');
            dataColumn1.classList.add("tableCellStyle");
            dataColumn1.setAttribute('height', '50px');
	        dataColumn1.setAttribute('width', '500px'); 			
			dataColumn1.innerHTML = tableDataObj[i].plainTextMessage;
			dataRow.appendChild(dataColumn1);
            
            let dataColumn2 = document.createElement('td');
            dataColumn2.classList.add("tableCellStyle");
            dataColumn2.setAttribute('height', '50px');
	        dataColumn2.setAttribute('width', '500px'); 			
			dataColumn2.innerHTML = tableDataObj[i].encryptedMessage;
			dataRow.appendChild(dataColumn2);
			
			let dataColumn3 = document.createElement('td');
			dataColumn3.classList.add("tableCellStyle");
            dataColumn3.setAttribute('height', '50px');
	        dataColumn3.setAttribute('width', '500px'); 			
			dataColumn3.innerHTML = tableDataObj[i].decryptedMessage;
			dataRow.appendChild(dataColumn3);
			
            table.appendChild( dataRow );	
		}
		tableContainer.appendChild(table);
		
		
	}
}	

function logoutFunction(event){
    event.preventDefault();
    location.href = "http://localhost:8080/login";	
}	

function returnToHome(event){
	 event.preventDefault();
    location.href = "http://localhost:8080/home?email="+emailValue;
}