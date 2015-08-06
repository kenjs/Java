<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>快到网-用户验证</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<sys:context/>/resource/js/checkoutData.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/user/validateUser.js"></script>
<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/personal.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(function(){
//$('input[name=username]').attr("readonly","readonly")//将input元素设置为readonly
//$('input[name=username]').removeAttr("readonly");//去除input元素的readonly属性
	inputTipText();  //初始化Input的灰色提示信息
});
function next() {
	var codeNumber = $.trim($("#codeNumber").val());
	var mobilephone = $.trim($("#mobilephone").val());
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
	$.ajax({
		url:jcontextPath + '/validateUser',
		type:'post',
		async:false,
		data:{"userId":$("#user-id").val(),"code":mobilephone},
		dataType:'json',
		success:function(data){
			if(data.result == 0) {
				$("#div_first").css("display","none");
				$("#div_complemete").css('display','block');
				$("#li2").addClass('fli');
				$(".drest").addClass('dres');
				delayURL(jcontextPath);
			}
		}
	});
}
function delayURL(url) {
	var delay = document.getElementById("time").innerHTML;
	if(delay > 0) {
		delay--;
		document.getElementById("time").innerHTML = delay;
	} else {
		window.top.location.href = url;
	}
	setTimeout("delayURL('" + url + "')", 1000);
}


</script>
</head>
<body>
<input type="hidden" value="${userId }" id="user-id" name="userId" />
<!-- 头部开始 -->
<div class="header">
  <div class="hea_top">
  </div>
  <div class="hea_cent">
    <div class="fl hea_logo">
    	<a href="<sys:context/>/"><img src="<sys:context/>/resource/image/index/logo.jpg" /></a>          
    </div>
  </div>
</div>
<!-- 头部结束 -->
<!--个人中心-->
<div class="mian">
    <div class="sonafr cenfr">
		<div class="drest" id="dures1" style="display:block;">
        	<ul>
            	<li class="fli">1.验证身份</li>
                <li id='li2'>2.完成</li>
            </ul>
        </div>
        <!-- 输入手机获取验证码开始 -->
       <div class="laing two_laing" id="div_first" style="display:block;">
        <ul>
          <li class="state"><label>请输入手机号码:</label>
          	<input type="text" id="mobilephone" name="mobilephone" tipMsg="请输入账号绑定手机号码" onblur="onis_blur('mobilephone');" onfocus="on_focus('mobilephone');" value="${mobilephone }" class="int m3"/>
          	&nbsp;&nbsp;&nbsp;&nbsp;
          	<input type="button" id="btnSendCode" name="btnSendCode" value="获取验证码" class="unt" onclick="sendMessage()"/>
          	<div class="mpt md94" id="divMobilephonezhu" style="display:none">
            	<div class="wn_a"></div>
                	<div class="wn_s" id="divMobilephoneNull" style="display:none">请输入手机号码!</div>
                	<div class="wn_s" id="divMobilephoneCanUse" style="display:none">手机号码不可用!</div>
                 	<div class="wn_s" id="divMobilephoneFormat" style="display:none">请输入正确手机号码!</div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li><label>请输入验证码:</label>&nbsp;
          	<input type="text" id="codeNumber" name="codeNumber"  tipMsg="请输入六位验证码" class="int m3" onblur="onis_blur('codeNumber');" onfocus="on_focus('codeNumber');"/>
          	<div class="mpt md94" id="divCodeNumberzhu" style="display:none">
            	<div class="wn_a"></div>
                	<div class="wn_s" id="divCodeNumberLength" style="display:none">请输入六位验证码!</div>
                 	<div class="wn_s" id="divCodeNumberFormat" style="display:none">验证码不正确!</div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li class="ternow mt20">
            <label></label>
            <input name="" type="button" value="确定" class="sub" onclick="next();"/></li>
        </ul>
       </div>
       <!-- 输入手机获取验证码结束-->
       
       <!-- 输入新密码结束-->
       <!-- 找回密码成功开始-->
       <div class="laing two_laing" id="div_complemete" style="display:none;">
        	<dl>
            	<dt><img src="<sys:context/>/resource/image/sonal/rt.jpg" width="66" height="57" /></dt>
                <dd>恭喜！您已成功验证！
                	<span id="time">10</span>
                	秒后自动跳到<a href='<sys:context/>'>主页</a>, 点击<a href='<sys:context/>'>这里</a>立即跳转;
                </dd>
            </dl>
            <div class="ternow mtf"><input name="" onclick="javascript:location.href=jcontextPath;" type="button" value="返回主页" class="sub" /></div>
       </div>
       <!-- 找回密码成功结束-->
       <div class="two_b">
       		<dl>
            	<dt><i class="wsfi">&nbsp;</i>为什么要进行身份验证？</dt>
                <dd>1、为保障您的账户信息安全，在变更账户中的重要信息时需要进行身份验证，感谢您的理解和支持。</dd>
                <dd>2、如果您绑定的快到网账号的手机因停机、注销、丢失等情况无法接收验证的，请您及时联系快到网客服。</dd>
            </dl>
       </div>
    </div>
        <!-- 合作伙伴 -->
  <div class="both"></div>
  <jsp:include page="/cooperativePartner.jsp" />
  <br />
  <br />
</div>
<br />
<br />
<br />
<!--个人中心结束-->
<!-- 底部开始 -->
<jsp:include page="/bottomes.jsp" />
<!-- 底部结束 -->
<link href="<sys:context/>/resource/css/personal.css" rel="stylesheet" type="text/css" />
</body>
</html>