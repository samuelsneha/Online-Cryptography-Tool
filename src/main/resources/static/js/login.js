
var closeBttn =  document.getElementById('toastCloseBttn1');
var flash = document.getElementById('flash1');
console.log( 'login.js');

if( closeBttn != null ){
    closeBttn.addEventListener('click', function(){
	console.log('close bttn clicked');
	flash.style.display = 'none';
});
}


var closeBttn2 =  document.getElementById('toastCloseBtn2');
var flash2 = document.getElementById('flash2');
console.log(closeBttn2, flash2 );
if( closeBttn2 != null ){
	closeBttn2.addEventListener('click', function(){
	console.log('close2 bttn clicked');
	flash2.style.display = 'none';
});
}
