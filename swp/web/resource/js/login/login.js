function on_blur(str){
	if(str == "code") {
		code = $.trim($("#code").val());
		if(code == null || code == "" || code == "用户名") {
			$("#divCodeMpt").show();
   			$("#divCodeNull").show();
   			return false;
		}
	}
	if(str == "password") {
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
		$("#divCodeNot").hide();
		$("#divCodeNotMpt").hide();
	}
	if(str == "password") {
		$("#divPwdMpt").hide();
		$("#divPwdNull").hide();
		$("#divPwdError").hide();
	}
}
function login() {
	var code = $.trim($("#code").val());
	if(code == null || code == "" || code == "用户名") {
		$("#divCodeMpt").show();
		$("#divCodeNull").show();
		$("#saveBtn").removeAttr("disabled");
		return false;
	}
	var pwd = $.trim($("#password").val());
	if(pwd == null || pwd == '') {
		$("#divPwdMpt").show();
		$("#divPwdNull").show();
		$("#saveBtn").removeAttr("disabled");
		return false;
	}
	if($("#bearPassword").attr("checked")=="checked") {
		if(getCookie("loginPwd") == "") {
			addCookie("loginCode",code,7);
			addCookie("loginPwd",pwd,7);
		}
	}else {
		addCookie("loginCode","",-1);
		addCookie("loginPwd","",-1);
	}
	$.ajax({
		url: jcontextPath + "/loginUserInfoJson.jspx",   
		type:'post',	
		dataType:'json', 
		data:{code:code,password:pwd}, //参数     	               
		success:function(data){//回传函数
			if(data.result == 1) {
				window.location.href = jcontextPath + "/queryTodayImportInfo.jspx?mark=0";
			}else {
				$("#"+data.result).show();
				$("#"+data.errorMessage).show();
				$("#saveBtn").removeAttr("disabled");
			}
		}
	});
}
