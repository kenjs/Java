
//按回车的时候相当于按tab键
$(document).keydown(function(){  
	if(event.keyCode==13 && event.srcElement.type!='button' && event.srcElement.type!='submit' && event.srcElement.type!='reset' && event.srcElement.type!='a' && event.srcElement.type!='textarea' && event.srcElement.type!='') {
		event.keyCode = 9;
	}
});

$(function(){
	$(".noTabSelect").attr("tabindex", -1);
	$(".icon_arrow").attr("tabindex", -1);
});