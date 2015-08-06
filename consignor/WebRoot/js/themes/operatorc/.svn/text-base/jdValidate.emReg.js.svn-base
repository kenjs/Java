$.extend(validatePrompt, {
    mail:{
        onFocus:"输入邮箱后，邮箱可作为登录账号，方便您接收订单通知，找回密码等",
        succeed:"",
        isNull:"请输入邮箱",
        error:{
            // beUsed:"该邮箱已被使用，请更换其它邮箱，或使用该邮箱<a href='http://www.tf56.com' class='flk13'>找回密码</a>",
            beUsed:"该邮箱已被使用，请更换其它邮箱",
            badFormat:"请输入有效的邮箱地址",
            badLength:"您填写的邮箱过长，邮箱地址只能在50个字符以内"
        }
    }
});

$.extend(
    validateFunction, {
    emReg_validate:function () {
        var emtype = $("#emType").val();
        if (emtype == "mobile") {
            $("#mobileInfo").jdValidate(validatePrompt.mobileInfo, validateFunction.mobileInfo, true);
            $("#pwd").jdValidate(validatePrompt.pwd, validateFunction.pwd, true);
            $("#pwd2").jdValidate(validatePrompt.pwd2, validateFunction.pwd2, true);
//            $("#mobileCode").jdValidate(validatePrompt.mobileCode, validateFunction.mobileCode, true);

//            return validateFunction.FORM_submit(["#mobileInfo", "#pwd", "#pwd2", "#mobileCode"]);
               return validateFunction.FORM_submit(["#mobileInfo", "#pwd", "#pwd2"]);
        } else {
            $("#mail").jdValidate(validatePrompt.mail, validateFunction.mail, true);
            $("#pwd").jdValidate(validatePrompt.pwd, validateFunction.pwd, true);
            $("#pwd2").jdValidate(validatePrompt.pwd2, validateFunction.pwd2, true);
//            $("#authcode").jdValidate(validatePrompt.authcode, validateFunction.authcode, true);

//            return validateFunction.FORM_submit(["#mail", "#pwd", "#pwd2", "#authcode"]);
            return validateFunction.FORM_submit(["#mail", "#pwd", "#pwd2"]);
        }

    },
    mail:function (option) {
        var format = validateRules.isEmail(option.value);
        var format2 = validateRules.betweenLength(option.value, 0, 50);
        if (!format) {
            validateSettings.error.run(option, option.prompts.error.badFormat);
        } else {
            if (!format2) {
                validateSettings.error.run(option, option.prompts.error.badLength);
            } else {
                if (!emailstate || emailold != option.value) {
                    if (emailold != option.value) {
                        emailold = option.value;  
                        option.errorEle.html("<span style='color:#999'>检验中……</span>");
                        var url = "checkEmail.json?email=" +escape(option.value) + "&r=" + Math.random();                      
    					$.getJSON(url, function (data) { 
                                if (data.msg =='ok') {
                                    validateSettings.succeed.run(option);
                                    emailstate = true;
                                } else {
                                    validateSettings.error.run(option, option.prompts.error.beUsed);
                                    emailstate = false;
                                }
                           });
                    }
                    else {
                        validateSettings.error.run(option, option.prompts.error.beUsed);
                        emailstate = false;
                    }
                }
                else {
                    validateSettings.succeed.run(option);
                }
            }
        }
    }

});


var isSubmit = false;

$("#pwd").bind("keyup",
    function () {
        validateFunction.pwdstrength();
    }).jdValidate(validatePrompt.pwd, validateFunction.pwd)
$("#pwd2").jdValidate(validatePrompt.pwd2, validateFunction.pwd2);
$("#mail").jdValidate(validatePrompt.mail, validateFunction.mail);
$("#mobileInfo").jdValidate(validatePrompt.mobileInfo, validateFunction.mobileInfo);
$("#mobileCode").jdValidate(validatePrompt.mobileCode, validateFunction.mobileCode);
$("#authcode").jdValidate(validatePrompt.authcode, validateFunction.authcode);

function emReg(utype) {
    if (isSubmit) {
        return
    }
    isSubmit = true;
    $("#registsubmit").attr({ "disabled":"disabled" }).attr({ "value":"提交中,请稍等" });
    var cauthCode=$("#authcode").val();
    	var passed = validateFunction.emReg_validate();
    

   // passed=true;
   // pageTracker._trackEvent('Button', 'Regist', 'Normal');
    if (passed) {
    	//var uuid = $("#JD_Verification1").attr("src").split("&uid=")[1].split("&")[0];
        $.ajax({
            type: "POST",
            //url: "save?perOrCorp=per&uuid=" + uuid + "&" + location.search.substring(1),
            url: "save.json?perOrCorp=per&utype="+utype+"&cauthCode="+cauthCode,
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: $("#formpersonal").serialize(),
            dataType: "json", 
            success: function(data) {
	        	if (data.msg =='ok') {
                    window.location="../logincs/login";
                } else {
                	alert("验证码错误");
                	$("#registsubmit").attr({ "disabled":"disabled" }).attr({ "value":"提  交" });
                    //window.location="../logincs/login";
                }
	        }
       		
        });
    } else {
        $("#registsubmit").removeAttr("disabled");
        $("#registsubmit").attr({ "value":"提  交" });
        isSubmit = false;
    }
}


$("#authcode").bind('keyup', function (event) {
    if (event.keyCode == 13) {
        $("#registsubmit").click();
    }
});

$("#protocol").click(function () {
    if ($("#protocol").attr("checked") != true) {
        $("#registsubmitframe").attr({ "disabled":"disabled" });
    }
    else {
        $("#registsubmitframe").removeAttr("disabled");
    }
});

