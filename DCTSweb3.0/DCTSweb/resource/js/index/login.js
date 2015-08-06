function on_blur(str){
	if(str == "code") {
		code = $.trim($("#code").val());
		if(code == null || code == "" || code == "用户名") {
			$("#divCodeMpt").show();
   			$("#divCodeNull").show();
   			return false;
		}
		if(code.length<1 || code.length>20) {//判断位数1到20位(新用户位数是3到20位，1到20位是方便老用户登录)
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
		$("#divCodeLength").hide();
		$("#divCodeLetter").hide();
		$("#divCodeNot").hide();
		$("#divMobilephoneNot").hide();
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
		url: jcontextPath + "/loginUserInfo",   
		type:'post',	
		dataType:'json', 
		data:{code:code,password:pwd}, //参数     	               
		success:function(data){//回传函数
			if(data.result == 1) {
				closeUp();
				location.reload();
			}else if(data.result == 0){
				window.location.href = jcontextPath + "/openValidateView?userId=" + data.content.userId + ";mobilephone=" + data.content.mobilephone;
			} else {
				$("#"+data.result).show();
				$("#"+data.errorMessage).show();
//				$("#saveBtn").removeAttr("disabled");
			}
		}
	});
}

function getRegister() {
	closeUp();
	var url = jcontextPath + "/dcts/user/Register.jsp";
	window.open(url, "_blank"); //注意第二个参数
}

function getRetrieveUserPws() {
	closeUp();
	var url = jcontextPath + "/dcts/user/retrieveUserPws.jsp";
	window.open(url, "_blank"); //注意第二个参数
}
