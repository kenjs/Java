$(function(){
	var telephone = $.trim($("input[name='mobilephone']").val());
	telephone = telephone.substr(0,3) + '****' + telephone.substr(7,telephone.length);
	$('input[name="mobilephone_str"]').val(telephone);
});

var InterValObj,
	wait = 120,//60秒后重新获取验证码
	codeNote = "", //验证码
	codeLength = 6;//验证码长度
function nextStep() {
	if(checkCode()){
		$("#check_phone").css("display","none");
		$("#new_pwd").css("display","block");
		$(".dures").addClass('dur1');
	}
	
}

function checkCode() {
	var code = $.trim($("input[name='verification_code']").val());
	if(code == null || code == ''){
		art.dialog({
		 	width: 220,
		    height: 100,
		    ok:function(){
			},
		    content: '请输入验证码！',                			   
		    cancelVal: '关闭',
		    cancel: true //为true等价于function(){}
		});
		$("input[name='verification_code']").focus();
		return false;
	}
	if(code != codeNote){
		art.dialog({
		 	width: 220,
		    height: 100,
		    ok:function(){
			},
		    content: '验证码输入错误！',                			   
		    cancelVal: '关闭',
		    cancel: true //为true等价于function(){}
		});
		return false;
	}
	return true;
}

function codeTimer() { 
	var mobilephone = $.trim($("input[name='mobilephone']").val());
	//产生验证码
	codeNote = "";
	for (var i = 0; i < codeLength; i++) {
		codeNote += parseInt(Math.random() * 9).toString();
    }
	$("#get_code").attr("disabled",true);
	$("#get_code").val(wait + "后重新发送");
	InterValObj = window.setInterval(countTime, 1000); //启动计时器，1秒执行一次
	//向后台发送处理数据
    $.ajax({
		url: jcontextPath + "/sendNote",   
		type:'post',	
		dataType:'json', 
		data:{noteCode:codeNote,mobilephone:mobilephone,type:3}, //参数     	               
		success:function(data){//回传函数
			
		}
	});
}
function countTime(){
	if(wait == 0){
		window.clearInterval(InterValObj);//停止计时器
		$("#get_code").removeAttr("disabled");
		$("#get_code").val("重新发送验证码");
		wait = 120;
	} 
	else {
		wait--;
		$("#get_code").val(wait + "后重新发送");
	}
}

function secondStep() {
	if(checkPwd()){
		updatePwd();
	}
}
/**
 * 校验密码
 * @returns {Boolean}
 */
function checkPwd(){
	var pwd = $.trim($("input[name='password']").val()),
		re_pwd = $.trim($("input[name='re_password']").val());
	if(pwd == null || pwd == ''){
		art.dialog({
		 	width: 220,
		    height: 100,
		    ok:function(){
			},
		    content: '请输入密码！',                			   
		    cancelVal: '关闭',
		    cancel: true //为true等价于function(){}
		});
		$("input[name='password']").focus();
		return false;
	}
	if(re_pwd == null || re_pwd == ''){
		art.dialog({
		 	width: 220,
		    height: 100,
		    ok:function(){
			},
		    content: '请再次输入密码！',                			   
		    cancelVal: '关闭',
		    cancel: true //为true等价于function(){}
		});
		$("input[name='re_password']").focus();
		return false;
	}
	if(pwd != re_pwd){
		art.dialog({
		 	width: 220,
		    height: 100,
		    ok:function(){
			},
		    content: '密码输入不一致！',                			   
		    cancelVal: '关闭',
		    cancel: true //为true等价于function(){}
		});
		return false;
	}
	return true;
}
/**
 * 更新用户密码
 */
function updatePwd() {
	var pwd =  $.trim($("input[name='password']").val());
	$.ajax({
		url:jcontextPath+'/updatePwdAction',
		type:'POST',
		dataType:'json',
		data:{pwd:pwd},
		success:function(data){
			if(data.result == 1){
				$("#check_phone").css("display","none");
				$("#new_pwd").css("display","none");
				$("#complete_success").css("display","block");
				$("#complete_fail").css("display","none");
				$(".dures").addClass('dur2');
			}
			else {
				$("#check_phone").css("display","none");
				$("#new_pwd").css("display","none");
				$("#complete_success").css("display","none");
				$("#complete_fail").css("display","block");
				$(".dures").addClass('dur2');
			}
		},
		error:function(XMLHttpRequest, textStatus, errorThrown){
			  alert(XMLHttpRequest.status);
              alert(XMLHttpRequest.readyState);
              alert(textStatus);
		}
	});
}