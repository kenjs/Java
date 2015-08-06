var InterValObj; //timer变量，控制时间
var count = 300; //间隔函数，1秒执行
var curCount;//当前剩余秒数
var codeNote = ""; //验证码
var codeLength = 6;//验证码长度
var codeNoteTwo = "";//修改密码二次验证验证码
var booleanfasle = "";

function sendMessage() {
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
				data:{noteCode:codeNote,mobilephone:mobilephone,type:2}, //参数     	               
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
	
	//密码
	if(str == "password") {
		var password = $.trim($("#password").val());
		if(password == null || password == ""){//不能为空
			$("#divPasswordzhu").show();
   			$("#divPasswordNull").show();//显示
			return false;
   		}
   		if(password.length<6 || password.length>20) {//判断位数6到20位
   			$("#divPasswordzhu").show();
   			$("#divPasswordLength").show();//显示
   			return false; 
   		}
	}
	//两次密码
	if(str == "passwordes") {
		var password = $.trim($("#password").val());
		var passwordes = $.trim($("#passwordes").val());
		if(password == null || password == ""){//不能为空
			$("#divPasswordzhu").show();
   			$("#divPasswordNull").show();//显示
			return false;
   		}
   		if(passwordes != password) {
   			$("#divPasswordeszhu").show();
   			$("#divPasswordes").show();//显示
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
	
	if(str == "password") {
		$("#divPasswordNull").hide();//隐藏
		$("#divPasswordLength").hide();//隐藏
	}
	if(str == "passwordes") {
		$("#divPasswordNull").hide();
		$("#divPasswordes").hide();//隐藏
	}
	
	$("#divMobilephonezhu").hide();
	$("#divCodeNumberzhu").hide();
	$("#divPasswordeszhu").hide();
	$("#divPasswordzhu").hide();
}


//下一步1
function nextMoveOne() {
	if(on_blur("mobilephone") == false) {
		return;
	}
	if(on_blur("codeNumber") == false) {
		return;
	}
	codeNoteTwo = codeNote;
	$("#dures1").hide();
	$("#laing1").hide();
	
	$("#dures2").show();
	$("#laing2").show();
}

//下一步2
function nextMoveTwo() {
	if(on_blur("mobilephone") == false) {
		return;
	}
	var codeNumber = $.trim($("#codeNumber").val());
	if(codeNumber != codeNoteTwo) {
		return;
	}
	if(on_blur("password") == false) {
		return;
	}
	if(on_blur("passwordes") == false) {
		return;
	}
	var mobilephone = $.trim($("#mobilephone").val());
	var password = $.trim($("#password").val());
	$.ajax({
		url: jcontextPath + "/retrieveUserPws",   
		type:'post',	
		dataType:'json', 
		data:{mobilephone:mobilephone,password:password}, //参数     	               
		success:function(data){//回传函数
			if(data.result == 1) {
				//修改失败
				return;
			}if(data.result == 2) {
				//用户不存在
				return;
			}
		}
	});
	$("#dures1").hide();
	$("#laing1").hide();
	$("#dures2").hide();
	$("#laing2").hide();
	
	$("#dures3").show();
	$("#laing3").show();
}
