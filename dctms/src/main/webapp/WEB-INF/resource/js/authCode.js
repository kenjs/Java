var InterValObj; //timer变量，控制时间
var count = 60; //间隔函数，1秒执行
var curCount;//当前剩余秒数
var codeNote = ""; //验证码
var codeLength = 6;//验证码长度

function sendMessage() {
            curCount = count;
            var dealType; //验证方式
            var mobilephone = $.trim($("#mobilephone").val());
            if(mobilephone == null || mobilephone == ""){//不能为空
	   			$("#divMobilephoneNull").show();//显示
				return;
	   		}
	   		if(bilenumber(mobilephone) == false) {
	   			$("#divMobilephoneFormat").show();//显示
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
				type:'post',	
				dataType:'json', 
				data:{noteCode:codeNote,mobilephone:mobilephone}, //参数     	               
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
