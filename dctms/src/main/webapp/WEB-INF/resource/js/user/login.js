function on_focus(str){
	if(str == "code") {
		$("#divCodeMpt").hide();
		$("#divCodeNull").hide();
		$("#divCodeNot").hide();
	}
	if(str == "pwd") {
		$("#divPwdMpt").hide();
		$("#divPwdNull").hide();
		$("#divPwdError").hide();
	}
}
function login() {
	code = $.trim($("#code").val());
	if(code == null || code == "" || code == "用户名") {
		$("#divCodeMpt").show();
			$("#divCodeNull").show();
			return false;
	}
	pwd = $.trim($("#password").val());
	if(pwd == null || pwd == '') {
		$("#divPwdMpt").show();
			$("#divPwdNull").show();
			return false;
	}
	document.getElementById('mainForm').submit();
}

function messageReturn() {
	var errorMessages = $("#errorMessage").val();
	if(errorMessages == "-1") {//用户名不能为空
		$("#divCodeMpt").show();
		$("#divCodeNull").show();
		return;
	}
	if(errorMessages == "-2") {//密码不能为空 
		$("#divPwdMpt").show();	
		$("#divPwdNull").show();
		return;
	}
	if(errorMessages == "-3") {//用户名不存在
		$("#divCodeMpt").show();
		$("#divCodeNot").show();
		return;
	}
	if(errorMessages == "-5") {//密码错误
		$("#divPwdMpt").show();	
		$("#divPwdError").show();
		return;
	}
}

function getLoginMessage() {
	if(getCookie("loginPwd") == "" || getCookie("loginPwd") == 'undefined') {
	}else {
		$("#code").val(getCookie("loginCode"));
		$("#password").val(getCookie("loginPwd"));
		$('input[type=checkbox]').attr('checked','checked');
	}
}