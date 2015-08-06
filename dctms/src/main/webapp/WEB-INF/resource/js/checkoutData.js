//电话校验（0571-88175786）
function testit(valeues){  
	var filter=/^((0\d{2,3})-)(\d{7,8})(-(\d{3,}))?$/;
	return filter.test(valeues);  
}
//电话校验（057188175786）
function checkphone(valeues){
    var filter=/^((0\d{2,3}))(\d{7,8})(-(\d{3,}))?$/;
	return filter.test(valeues);
}

//手机校验，必须是
function bilenumber(valeues) {
    var bilenumber = valeues;
	l = bilenumber.length;
	if($.trim(bilenumber) != '') {
		if(l != 11) {
			return false;
		} 
		var reg = /^0{0,1}(13[0-9]|145|147|15[0-3]|15[5-9]|18[0-9])[0-9]{8}$|^$/;
		var result = reg.exec(bilenumber);
		if(result == null){
			return false;
		}
	}
	return true;
}
//校验小数点三位
function myNumberic(e) {
	var num = $.trim($(e).val());
	if(isNaN(num)){
		$(e).val("");
	}else {
		$(e).val(num);
		var str = num.split(".");
		if(str.length>1){
			var numer = '';
			var strs = str[0];
			if(strs.length>10) {
				numer = strs.substr(0,10);
			}
			if(str[1]!=null && str[1].length>4){
				var newNum = str[0]+'.'+str[1][0]+str[1][1]+str[1][2]+str[1][3];
				$(e).val($.trim(newNum));
			}
		}else {
			if(num.length>9) {
				$(e).val($.trim(num.substr(0,9)));
			}
		}
	}
}


//校验电子邮箱
function emialIf(valeues) {
	reg=/^([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\-|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/gi;
    if(!reg.test($.trim(valeues)))
    {
        return false;
    }
    return true;
}


function inputTipText(){   
    $("input[tipMsg]").each(function(){  
        if($(this).val() == ""){  
            var oldVal=$(this).attr("tipMsg");  
        if($(this).val()==""){$(this).attr("value",oldVal).css({"color":"#888"});}  
        $(this)  
           .css({"color":"#888"})     //灰色  
           .focus(function(){  
            if($(this).val()!=oldVal){$(this).css({"color":"#000"})}else{$(this).val("").css({"color":"#888"})}  
           })  
           .blur(function(){  
            if($(this).val()==""){$(this).val(oldVal).css({"color":"#888"})}  
           })  
           .keydown(function(){$(this).css({"color":"#000"})});  
        }  
    });  
} 