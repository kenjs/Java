function getAttributeValue(o, key) {
	if (!o.attributes) return null;
	var attr = o.attributes;
	for (var i = 0; i < attr.length; i++){
		if (key.toLowerCase() == attr[i].name.toLowerCase())
			return attr[i].value;
		}
	return null;
}
//用户名点击事件
function focusInputEle(o) {
		var test = document.getElementById("test1");
	     test.style.visibility ="visible";
	//alert(o.value);
	document.getElementById("msglogin").innerHTML='';
	document.getElementById("myerror").className="hidden1";
	
	if (o.value == getAttributeValue(o, 'defaultVal')){
		o.value = '';
		o.style.color = "#000000";
	}

}
//用户名鼠标离开事件
function blurInputEle(o) {
	if (o.value == '') {
		o.value = getAttributeValue(o, 'defaultVal');
		o.style.color = "#999999";
	}
	var test = document.getElementById("test1");
	  test.style.visibility ="hidden";
}
//密码点击事件
function focusInputPwd(o) {

	document.getElementById("msglogin").innerHTML='';
	document.getElementById("myerror").className="hidden1";
	
	if (o.value == getAttributeValue(o, 'defaultVal')){
		o.value = '';
		o.style.color = "#000000";
	}

}

//验证码点击事件
function focusInputCauth(o) {

	document.getElementById("msglogin").innerHTML='';
	document.getElementById("myerror").className="hidden1";
	
	if (o.value == getAttributeValue(o, 'defaultVal')){
		o.value = '';
		o.style.color = "#000000";
	}

}
//用户名鼠标离开事件
function blurInputEle(o) {
	if (o.value == '') {
		o.value = getAttributeValue(o, 'defaultVal');
		o.style.color = "#999999";
	}
	var test = document.getElementById("test1");
	  test.style.visibility ="hidden";
}



 
