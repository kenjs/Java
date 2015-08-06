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
	if(trim(bilenumber) != '') {
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
	var num = ($(e).val());
	if(isNaN(num)){
		$(e).val("");
	}
	var str = num.split(".");
	if(str.length>1){
		var numer = '';
		var strs = str[0];
		if(strs.length>10) {
			numer = strs.substr(0,10);
		}
		if(str[1]!=null && str[1].length>4){
			var newNum = str[0]+'.'+str[1][0]+str[1][1]+str[1][2]+str[1][3];
			$(e).val(newNum);
		}
	}else {
		if(num.length>9) {
			$(e).val(num.substr(0,9));
		}
	}
}