$.extend(validateFunction, {
    FORM_validate:function() {
        $("#username").jdValidate(validatePrompt.username, validateFunction.username, true);
        $("#pwd").jdValidate(validatePrompt.pwd, validateFunction.pwd, true);
        $("#pwd2").jdValidate(validatePrompt.pwd2, validateFunction.pwd2, true);
        //$("#mail").jdValidate(validatePrompt.mail, validateFunction.mail, true);
        //$("#authcode").jdValidate(validatePrompt.authcode,validateFunction.authcode,true);
       // return validateFunction.FORM_submit(["#username","#pwd","#pwd2","#mail"])
        return validateFunction.FORM_submit(["#username","#pwd","#pwd2"])
    }
});
var isSubmit = false;
//调用
setTimeout(function() {
    $("#username").get(0).focus();
     }, 0);
$("#username").jdValidate(validatePrompt.username, validateFunction.username);
$("#pwd").bind("keyup",
    function() {
        validateFunction.pwdstrength();
    }).jdValidate(validatePrompt.pwd, validateFunction.pwd)
$("#pwd2").jdValidate(validatePrompt.pwd2, validateFunction.pwd2);
$("#mail").jdValidate(validatePrompt.mail, validateFunction.mail);
$("#referrer").bind("keydown",
    function() {
        $(this).css({"color":"#333333","font-size":"14px"});
    }).bind("keyup",
    function() {
        if ($(this).val() == "" || $(this).val() == "可不填") {
            $(this).css({ "color": "#999999", "font-size": "12px" }).jdValidate(validatePrompt.referrer, validateFunction.referrer, "可不填");
        }
    }).bind("blur",
    function() {
        if ($(this).val() == "" || $(this).val() == "可不填") {
            $(this).css({"color":"#999999","font-size":"12px"}).jdValidate(validatePrompt.referrer, validateFunction.referrer, "可不填");
        }
    }).bind("focus", function() {
        if ($(this).val() == "可不填") {
            $(this).val("")
        }

    })



//新注册   提交按钮触发事件

$("#submitregist").click(function() {
	//alert("sss");
    $(this).attr({ "disabled": "disabled" }).attr({ "value": "提 交" });
    //alert("sssaaaasss");
    var flag = validateFunction.FORM_validate(); //验证
    //alert("flag="+flag);
    if (flag) {
        $.ajax({
            type: "POST",
            url: "save.json",
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            data: $("#formpersonal").serialize(),
            dataType: "json", 
            success: function(data) {
        	//alert("xxx");
        	//alert(data.msg);
	        	if (data.msg =='ok') {
                   // alert("ok");
                   // window.location="../operatorcs/list";
                   // window.opener.location.reload();//先刷新父页面 
					alert("保存成功");
					//location.href="../operatorcs/list_json.json";
   					
					// window.parent.location.reload();
					 window.top.document.getElementById("right-iframe").src="../operatorcs/list?random="+ Math.random();
                } else {
                	//alert("验证码错误")
                	$("#submitregist").attr({ "disabled":"disabled" }).attr({ "value":"提 交" });
                }
	        }
        });
    } else {
        $("#submitregist").removeAttr("disabled");
        $("#submitregist").attr({ "value": "提 交" });
    }
});

function verc() {
    $("#JD_Verification1").click();
    $("#authcode").focus();
}
