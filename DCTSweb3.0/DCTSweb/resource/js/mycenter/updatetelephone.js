$(function(){
	var telephone = $.trim($("input[name='mobilephone']").val());
	telephone = telephone.substr(0,3) + '****' + telephone.substr(7,telephone.length);
	$('input[name="mobilephone_str"]').val(telephone);
});

var InterValObj,
	wait = 300,//60秒后重新获取验证码
	codeNote = "", //验证码
	codeLength = 6;//验证码长度
function nextStep() {
	if(checkCode()){
		$("#check_phone").css("display","none");
		$("#new_phone").css("display","block");
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
		data:{noteCode:codeNote,mobilephone:mobilephone,type:4}, //参数     	               
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

function codeTimer2() { 
	var flag = false;
	var mobilephone = $.trim($("input[name='new_mobilephone']").val());
	if(mobilephone == null || mobilephone == '') {
		art.dialog({
		 	width: 220,
		    height: 100,
		    ok:function(){
			},
		    content: '请输入新的手机号码！',                			   
		    cancelVal: '关闭',
		    cancel: true //为true等价于function(){}
		});
		return;
	}
	$("input[name='new_mobilephone']").attr('readonly','readonly');
	$.ajax({
		url:jcontextPath+'/checkPhoneNumber',
		type:'POST',
		async:false,
		dataType:'json',
		data:{mobilephone:mobilephone},
		success:function(data){
			if(data.result == '1'){
				flag = true;
				art.dialog({
				 	width: 220,
				    height: 100,
				    ok:function(){
					},
				    content: '该手机号已经存在！',                			   
				    cancelVal: '关闭',
				    cancel: true //为true等价于function(){}
				});
				$("input[name='new_mobilephone']").removeAttr("readonly");
			}
		}
	});
	if(flag) {
		flag = false;
		return;
	}
	//产生验证码
	codeNote = "";
	for (var i = 0; i < codeLength; i++) {
		codeNote += parseInt(Math.random() * 9).toString();
    }
	$("#get_code_new").attr("disabled",true);
	$("#get_code_new").val(wait + "后重新发送");
	InterValObj = window.setInterval(countTime2, 1000); //启动计时器，1秒执行一次
	//向后台发送处理数据
    $.ajax({
		url: jcontextPath + "/sendNote",   
		type:'post',	
		dataType:'json', 
		data:{noteCode:codeNote,mobilephone:mobilephone,type:4}, //参数     	               
		success:function(data){//回传函数
			
		}
	});
}
function countTime2(){
	if(wait == 0){
		$("input[name='new_mobilephone']").removeAttr("readonly");
		window.clearInterval(InterValObj);//停止计时器
		$("#get_code_new").removeAttr("disabled");
		$("#get_code_new").val("重新发送验证码");
		wait = 60;
	} 
	else {
		wait--;
		$("#get_code_new").val(wait + "后重新发送");
	}
}

function secondStep() {
	if(checkTelephone()){
		//window.location.href=jcontextPath+"/openUpdatePwdResultAction";
		updateMobileTelephone();
	}
}
/**
 * 
 * @returns {Boolean}
 */
function checkTelephone(){
	var new_telephone =  $.trim($("input[name='new_mobilephone']").val()),
		newCode = $.trim($("input[name='verification_new']").val());
	if(new_telephone == null || new_telephone == ''){
		art.dialog({
		 	width: 220,
		    height: 100,
		    ok:function(){
			},
		    content: '请输入新的手机号码！',                			   
		    cancelVal: '关闭',
		    cancel: true //为true等价于function(){}
		});
		$("input[name='new_telephone']").focus();
		return false;
	}
	if(newCode == null || newCode == ''){
		art.dialog({
		 	width: 220,
		    height: 100,
		    ok:function(){
			},
		    content: '请输入验证码！',                			   
		    cancelVal: '关闭',
		    cancel: true //为true等价于function(){}
		});
		$("input[name='verification_code_new']").focus();
		return false;
	}
	if(newCode != codeNote){
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
/**
 * 更新用户手机号码
 */
function updateMobileTelephone() {
	var new_telephone =  $.trim($("input[name='new_mobilephone']").val());
	$.ajax({
		url:jcontextPath+'/updateTelephoneAction',
		type:'POST',
		dataType:'json',
		data:{telephone:new_telephone},
		success:function(data){
			if(data.result == 1){
				$("#check_phone").css("display","none");
				$("#new_phone").css("display","none");
				$("#complete_success").css("display","block");
				$("#complete_fail").css("display","none");
				$(".dures").addClass('dur2');
			} else if(data.result == -1) {
				art.dialog({
				 	width: 220,
				    height: 100,
				    ok:function(){
					},
				    content: '该手机号码已被注册过！',                			   
				    cancelVal: '关闭',
				    cancel: true //为true等价于function(){}
				});
			} 
			else {
				$("#check_phone").css("display","none");
				$("#new_phone").css("display","none");
				$("#complete_success").css("display","none");
				$("#complete_fail").css("display","block");
				$(".dures").addClass('dur2');
			}
		}
	});
}