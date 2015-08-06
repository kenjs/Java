var InterValObj; //timer变量，控制时间
var count = 120; //间隔函数，1秒执行
var curCount;//当前剩余秒数
var codeNote = ""; //验证码
var codeLength = 6;//验证码长度
var codeNoteTwo = "";//修改密码二次验证验证码
var booleanfasle = "";

function sendMessage() {
			var bol = validateCode();
			if(! bol) {
				return false;
			}
            curCount = count;
            var dealType; //验证方式
            var mobilephone = $.trim($("#mobilephone").val());
            if(mobilephone == null || mobilephone == "" || mobilephone == "请输入账号绑定手机号码"){//不能为空
    			$("#divMobilephonezhu").show();
       			$("#divMobilephoneNull").show();//显示
    			return false;
       		}
       		if(bilenumber(mobilephone) == false) {
       			$("#divMobilephonezhu").show();
       			$("#divMobilephoneFormat").show();//显示
    			return false;
       		}
       		$.ajax({
    				url: jcontextPath + "/queryWebUserInfoMoblePhone",
    				async: false,
    				type:'post',	
    				dataType:'json', 
    				data:{mobilephone:mobilephone}, //参数     	               
    				success:function(data){//回传函数
    					if(data.result == 0) {
    						booleanfasle = '1';
    					}
    				}
    		});
	   		if(booleanfasle == '1') {
	   			$("#divMobilephonezhu").show();
				$("#divMobilephoneCyi").show();//显示
				booleanfasle = "";
	   			return;
	   		}
            //产生验证码
			for (var i = 0; i < codeLength; i++) {
				codeNote += parseInt(Math.random() * 9).toString();
            }
            //设置button效果，开始计时
            $("#btnSendCode").attr("disabled", "true");
            $("#btnSendCode").val("请在" + curCount + "秒内输入验证码");
            $('input[name=mobilephone]').attr("readonly","readonly");
            InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
			//向后台发送处理数据
            $.ajax({
				url: jcontextPath + "/sendNote",
				async: false,
				type:'post',	
				dataType:'json', 
				data:{noteCode:codeNote,mobilephone:mobilephone,type:6}, //参数     	               
				success:function(data){//回传函数
					
				}
			});
}
//timer处理函数
function SetRemainTime() {
	if (curCount == 0) {                
		window.clearInterval(InterValObj);//停止计时器
        $("#btnSendCode").removeAttr("disabled");//启用按钮
        $("#btnSendCode").val("重新发送验证码");
        $('input[name=mobilephone]').removeAttr("readonly");
        codeNote = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效    
     }else {
        curCount--;
        $("#btnSendCode").val("请在" + curCount + "秒内输入验证码");
     }
}

function on_blur(str) {
	//手机号码 
	if(str == "mobilephone") {
		var mobilephone = $.trim($("#mobilephone").val());
		if(mobilephone == null || mobilephone == "" || mobilephone == "请输入账号绑定手机号码"){//不能为空
			$("#divMobilephonezhu").show();
   			$("#divMobilephoneNull").show();//显示
			return false;
   		}
   		if(bilenumber(mobilephone) == false) {
   			$("#divMobilephonezhu").show();
   			$("#divMobilephoneFormat").show();//显示
			return false;
   		}
   		$.ajax({
				url: jcontextPath + "/queryWebUserInfoMoblePhone",   
				type:'post',	
				dataType:'json', 
				data:{mobilephone:mobilephone}, //参数     	               
				success:function(data){//回传函数
					if(data.result == 0) {
						$("#divMobilephonezhu").show();
						$("#divMobilephoneCyi").show();//显示
						return false;
					}
				}
			});
	}		
	//验证码
	if(str == "codeNumber") {
		var codeNumber = $.trim($("#codeNumber").val());
		if(codeNumber.length != 6 || codeNumber == "请输入六位验证码"){//不能为空
			$("#divCodeNumberzhu").show();
   			$("#divCodeNumberLength").show();//显示
			return false;
   		}
   		if(codeNumber != codeNote) {
   			$("#divCodeNumberzhu").show();
   			$("#divCodeNumberFormat").show();//显示
			return false;
   		}
	}
	
}


function on_focus(str){
	if(str == "mobilephone") {
		$("#divMobilephoneNull").hide();//隐藏
		$("#divMobilephoneFormat").hide();//隐藏
		$("#divMobilephoneCyi").hide();//隐藏
	}
	if(str == "codeNumber") {
		$("#divCodeNumberLength").hide();//隐藏
		$("#divCodeNumberFormat").hide();//隐藏
	}
	
	$("#divMobilephonezhu").hide();
	$("#divCodeNumberzhu").hide();
}

function validateCode() {
	var mobilephone = $.trim($("#mobilephone").val());
	var flag = true;
	$.ajax({
		url: jcontextPath + "/queryWebUserInfoMoblePhone",   
		type:'post',	
		async:false,
		dataType:'json', 
		data:{"code":mobilephone,"userId":$("#user-id").val(),"type":1}, //参数     	               
		success:function(data){//回传函数
			if(data.result == 1) {	
				$("#divMobilephonezhu").show();
				$("#divMobilephoneCanUse").show();//显示					
				flag = false;				
			} 
		}
	});
	return flag;
}