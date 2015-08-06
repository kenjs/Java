function on_blur(str){
	if(str == "code") {
		code = $.trim($("#code").val());
		if(code == null || code == "" || code == "用户名") {
			$("#divCodeMpt").show();
   			$("#divCodeNull").show();
   			return false;
		}
		if(code.length<1 || code.length>20) {//判断位数3到20位
   			$("#divCodeMpt").show();
   			$("#divCodeLength").show();
   			return false; 
   		}
   		if (!code.match(/^[a-zA-Z0-9_]{1,}$/)){//用户名只能由字母数字下划线组成
   			$("#divCodeMpt").show();
   			$("#divCodeLetter").show();
    		return false;  
  		}  
	}
	if(str == "pwd") {
		pwd = $.trim($("#password").val());
		if(pwd == null || pwd == '') {
			$("#divPwdMpt").show();
   			$("#divPwdNull").show();
   			return false;
		}
	}
}
function on_focus(str){
	if(str == "code") {
		$("#divCodeMpt").hide();
		$("#divCodeNull").hide();
		$("#divCodeLength").hide();
		$("#divCodeLetter").hide();
		$("#divCodeNot").hide();
		$("#divMobilephoneNot").hide();
	}
	if(str == "pwd") {
		$("#divPwdMpt").hide();
		$("#divPwdNull").hide();
		$("#divPwdError").hide();
	}
}
function login() {
	$("#saveBtn").attr({"disabled":"disabled"});
	if(on_blur("code") == false) {
		$("#saveBtn").removeAttr("disabled");
		return;
	}
	if(on_blur("pwd") == false) {
		$("#saveBtn").removeAttr("disabled");
		return;
	}
	code = $.trim($("#code").val());
	pwd = $.trim($("#password").val());
	if($("#bearPassword").attr("checked")=="checked") {
		addCookie("loginCode",code,7*24);
		addCookie("loginPwd",pwd,7*24);
	}else {
		addCookie("loginCode","",-1);
		addCookie("loginPwd","",-1);
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
	if(errorMessages == "-4") {//手机号码不存在
		$("#divCodeMpt").show();
		$("#divMobilephoneNot").show();
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