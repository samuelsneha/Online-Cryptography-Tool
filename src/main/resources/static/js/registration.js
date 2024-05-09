/**
 * 
 */

 let closeBttn = document.getElementById('toastCloseBttn1');
 let flash = document.getElementById('flash1');
 if( closeBttn != null ){
    closeBttn.addEventListener( 'click', function(){
	 console.log('close button is clicked');
	 flash.style.display = 'none';
    });	 
 }

 
 
 let closeBttn2 = document.getElementById('toastCloseBttn2');
 let flash2 = document.getElementById('flash2');
 if( closeBttn2 != null ){
     console.log('registration reached '+closeBttn2 +flash2);
     closeBttn2.addEventListener( 'click', function(){
	 console.log('second close button is clicked');
	 flash2.style.display = 'none';
    });	 
 }
 