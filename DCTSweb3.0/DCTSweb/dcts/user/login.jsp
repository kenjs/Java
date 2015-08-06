<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>快到网-用户-登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<sys:context/>/resource/js/checkoutData.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/user/login.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/index/cookie.js"></script>
<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(function(){
	inputTipText();  //初始化Input的灰色提示信息  
	getLoginMessage();//得到记住密码用户名 
	messageReturn();//返回错误信息提示
});
</script>
</head>
<body>
<!-- 头部开始 -->
<div class="header">
  <div class="hea_top">
  </div>
  <div class="hea_cent">
    <div class="fl hea_logo">
    	<a href="<sys:context/>/"><img src="<sys:context/>/resource/image/index/logo.jpg" /></a>
          <div class="slet_dta">
            <a>会员登录</a>
          </div>
    </div>
  </div>
</div>
<!-- 头部结束 -->
<s:form id="mainForm" action="/loginWebUserInfo" namespace="/" method="post">
<input type='hidden' id='errorMessage' name='errorMessage' value='${webUserInfoDamains.errorMessage}'/>
<div class="mian">
	<div class="fl flant">
    <div class="laing">
        <ul>
          <li>
            <label>用户名</label>
            <input id="code" name="webUserInfoDamains.code" tipMsg="用户名" type="text" class="int" onkeydown="if(event.keyCode == 13)login();" value="" onblur="on_blur('code');" onfocus="on_focus('code');"/>
            <div class="mpt" id="divCodeMpt" style="display:none">
            	<div class="wn_a"></div>
                <div class="wn_s" id="divCodeNull" style="display:none">用户名不能为空！</div>
                <div class="wn_s" id="divCodeLength" style="display:none">用户名长度为3到20位！</div>
          		<div class="wn_s" id="divCodeLetter" style="display:none">用户名数字字母或下划线组成！</div>
                <div class="wn_s" id="divCodeNot" style="display:none">用户名不存在！</div>
                <div class="wn_s" id="divMobilephoneNot" style="display:none">手机号码不存在！</div>
                <div class="wn_c"></div>
            </div>        
          </li>
          <li>
            <label>密码</label>
            <input id="password" name="webUserInfoDamains.password" type="password" value="" class="int" onkeydown="if(event.keyCode == 13)login();" onblur="on_blur('pwd');" onfocus="on_focus('pwd');"/>
            <div class="mpt" id="divPwdMpt" style="display:none">
            	<div class="wn_a"></div>
                <div class="wn_s" id="divPwdNull" style="display:none">密码不能为空！</div>
                <div class="wn_s" id="divPwdError" style="display:none">登录密码输入错误！</div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
            <label></label>
            <input id="bearPassword" name="bearPassword" type="checkbox" value="" />
            &nbsp;&nbsp;&nbsp;记住密码<span class="mpt1"><a href="<sys:context/>/dcts/user/retrieveUserPws.jsp">忘记密码？</a></span></li>
          <li class="lanot">
            <label></label>
            <a href="javascript:login();" id="saveBtn" class="hoa mr10">登录</a><a href="<sys:context/>/dcts/user/Register.jsp">注册</a>
          </li>
        </ul>
      </div>
    </div>
    <div class="fr frland"></div>
    <div class="both"></div>
<br />
<br />
</div>
</s:form>
<!-- 底部开始 -->
<jsp:include page="/bottomes.jsp" />
<!-- 底部结束 -->
</body>
</html>