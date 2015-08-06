function getAttributeValue(o, key) {
	if (!o.attributes) return null;
	var attr = o.attributes;
	for (var i = 0; i < attr.length; i++){
		if (key.toLowerCase() == attr[i].name.toLowerCase())
			return attr[i].value;
		}
	return null;
}
function focusInputEle(o) {
	//alert(o.value);
	document.getElementById("msglogin").innerHTML='';
	if (o.value == getAttributeValue(o, 'defaultVal')){
		o.value = '';
		o.style.color = "#000000";
	}
}
function blurInputEle(o) {
	if (o.value == '') {
		o.value = getAttributeValue(o, 'defaultVal');
		o.style.color = "#999999";
	}
}

function checkInput(o){
	
}

function save(){//保存新增or修改
	var cusername = document.getElementsByName("cusername");
	if(cusername==""||cusername==null){
		alert("用户名不能为空!");return false;
	}
	return true;
}