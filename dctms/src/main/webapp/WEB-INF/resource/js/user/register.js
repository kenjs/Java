var InterValObj; //timer变量，控制时间
var count = 120; //间隔函数，1秒执行
var curCount;//当前剩余秒数
var codeNote = ""; //验证码
var codeLength = 6;//验证码长度
var booleanfasle = "";
function sendMessage() {
            curCount = count;
            var dealType; //验证方式
            var mobilephone = $.trim($("#mobilephone").val());
            $("#divMobilephonezhu").hide();
    		$("#divMobilephoneNull").hide();//隐藏
    		$("#divMobilephoneFormat").hide();//隐藏
    		$("#divMobilephoneCyi").hide();//隐藏
            if(mobilephone == null || mobilephone == ""){//不能为空
            	$("#divMobilephonezhu").show();
	   			$("#divMobilephoneNull").show();//显示
				return;
	   		}
	   		if(bilenumber(mobilephone) == false) {
	   			$("#divMobilephonezhu").show();
	   			$("#divMobilephoneFormat").show();//显示
				return;
	   		}
	   		$.ajax({
				url: jcontextPath + "/queryWebUserInfoMoblePhone",
				async: false,   
				type:'post',	
				dataType:'json', 
				data:{mobilephone:mobilephone}, //参数     	               
				success:function(data){//回传函数
					if(data.result == 1) {
						booleanfasle = "1";
					}
				}
			});
	   		if(booleanfasle == "1") {
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
            InterValObj = window.setInterval(SetRemainTime, 1000); //启动计时器，1秒执行一次
			//向后台发送处理数据
            $.ajax({
				url: jcontextPath + "/sendNote",
				async: false,
				type:'post',	
				dataType:'json', 
				data:{noteCode:codeNote,mobilephone:mobilephone,type:1}, //参数     	               
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
        codeNote = ""; //清除验证码。如果不清除，过时间后，输入收到的验证码依然有效    
     }else {
        curCount--;
        $("#btnSendCode").val("请在" + curCount + "秒内输入验证码");
     }
}


function on_blur(str) {
	//用户名
	if(str == "code") {
		var code = $.trim($("#code").val());
   		if(code == null || code == "" || code == "请输入用户名"){//不能为空
   			$("#divcodezhu").show();
   			$("#divCodeNull").show();//显示
			return false;
   		}
   		if(code.length<3 || code.length>20) {//判断位数3到20位
   			$("#divcodezhu").show();
   			$("#divCodeLength").show();//显示
   			return false; 
   		}
   		if (!code.match(/^[a-zA-Z0-9_]{1,}$/)){//用户名只能由字母数字下划线组成
   			$("#divcodezhu").show();
   			$("#divCodeLetter").show();//显示
    		return false;  
  		}  
   		if(code != null && code != ""){
   			$.ajax({
				url: jcontextPath + "/queryWebUserInfoCode",   
				type:'post',	
				dataType:'json', 
				data:{code:code}, //参数     	               
				success:function(data){//回传函数
					if(data.result == 1) {
						$("#divcodezhu").show();
						$("#divCodeCyi").show();//显示
						return false;
					}
				}
			});
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
	//公司名称
	if(str == "companyName") {
		var companyName = $.trim($("#companyName").val());
		if(companyName == null || companyName == "" || companyName == "请输入企业名称"){//不能为空
			$("#divCompanyNamezhu").show();
   			$("#divCompanyName").show();//显示
			return false;
   		}
   		$.ajax({
				url: jcontextPath + "/queryCompanyByName",   
				type:'post',	
				dataType:'json', 
				data:{companyName:companyName}, //参数     	               
				success:function(data){//回传函数
					if(data.result == 1) {
						$("#divCompanyNamezhu").show();
						$("#divCompanyNameCyi").show();//显示
						return false;
					}
				}
		});
	}
	//手机号码 
	if(str == "mobilephone") {
		var mobilephone = $.trim($("#mobilephone").val());
		if(mobilephone == null || mobilephone == "" || mobilephone == "请输入手机号码"){//不能为空
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
					if(data.result == 1) {
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
		if(codeNumber.length != 6 || codeNumber == "请输入验证码"){//不能为空
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
	if(str == "code") {
		$("#divcodezhu").hide();
		$("#divCodeCyi").hide();//隐藏
		$("#divCodeNull").hide();//隐藏
		$("#divCodeLength").hide();//隐藏
		$("#divCodeLetter").hide();//隐藏
	}
	if(str == "password") {
		$("#divPasswordzhu").hide();
		$("#divPasswordNull").hide();//隐藏
		$("#divPasswordLength").hide();//隐藏
	}
	if(str == "passwordes") {
		$("#divPasswordeszhu").hide();
		$("#divPasswordes").hide();//隐藏
	}
	if(str == "companyName") {
		$("#divCompanyNamezhu").hide();
		$("#divCompanyName").hide();//隐藏
		$("#divCompanyNameCyi").hide();//隐藏
	}
	if(str == "mobilephone") {
		$("#divMobilephonezhu").hide();
		$("#divMobilephoneNull").hide();//隐藏
		$("#divMobilephoneFormat").hide();//隐藏
		$("#divMobilephoneCyi").hide();//隐藏
	}
	if(str == "codeNumber") {
		$("#divCodeNumberLength").hide();//隐藏
		$("#divCodeNumberFormat").hide();//隐藏
	}
	$("#divCodeNumberzhu").hide();
	$("#divContentFormat").hide();//隐藏		
}

function open(){
	$("#tndow").css("display","block");
}
function close(){
	$('input[type=checkbox]').attr('checked','checked');
	$("#tndow").css("display","none");
	$("#saveBtn").removeAttr("disabled");
}

function save(){
	$("#saveBtn").attr({"disabled":"disabled"});
	if($("#checkboxes").attr("checked")==undefined) {
		return;
	}	
	if(on_blur("code") == false){
		$("#saveBtn").removeAttr("disabled");
		return;
	};
	if(on_blur("password") == false){
		$("#saveBtn").removeAttr("disabled");
		return;
	}
	if(on_blur("passwordes") == false) {
		$("#saveBtn").removeAttr("disabled");
		return;
	}
	if(on_blur("companyName") == false) {
		$("#saveBtn").removeAttr("disabled");
		return;
	}
	if(on_blur("mobilephone") == false) {
		$("#saveBtn").removeAttr("disabled");
		return;
	}
	if(on_blur("codeNumber") == false) {
		$("#saveBtn").removeAttr("disabled");
		return;
	}
	document.getElementById('mainForm').submit();
	return true;
}

function messageReturn() {
	 var errorMessages = $("#errorMessage").val();
	if(errorMessages == "0") {//请填写完整注册信息
		$("#divCodeNumberzhu").show();
		$("#divContentFormat").show();
		return;
	}
	if(errorMessages == "-1") {//用户名不能为空
		$("#divcodezhu").show();
		$("#divCodeNull").show();
		return;
	}
	if(errorMessages == "-2") {//密码不能为空
		$("#divPasswordzhu").show();
		$("#divPasswordNull").show();
		return;
	}
	if(errorMessages == "-3") {//公司名不能为空
		$("#divCompanyNamezhu").show();
		$("#divCompanyName").show();
		return;
	}
	if(errorMessages == "-4") {//手机号码不能为空！
		$("#divMobilephonezhu").show();	
		$("#divMobilephoneNull").show();
		return;
	}
	if(errorMessages == "-5") {//手机号码格式错误！
		$("#divMobilephonezhu").show();
		$("#divMobilephoneFormat").show();
		return;
	}
	if(errorMessages == "-6") {//验证码不能为空！
		$("#divCodeNumberzhu").show();	
		$("#divCodeNumberLength").show();
		return;
	}
	if(errorMessages == "-7") {//用户名已存在！
		$("#divcodezhu").show();
		$("#divCodeCyi").show();
		return;
	}
	if(errorMessages == "-8") {//手机号码已绑定
		$("#divMobilephonezhu").show();
		$("#divMobilephoneCyi").show();
		return;
	}
	if(errorMessages == "-9") {//公司名称已存在
		$("#divCompanyNamezhu").show();
		$("#divCompanyNameCyi").show();
		return;
	}	
}