<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp" %>
<%@ include file="/common/include-jqueryJs.jsp" %>
<head>
<title>快到网-营销平台-登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<sys:context/>/resource/js/checkoutData.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/login/login.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/login/cookie.js"></script>
<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(function(){
	inputTipText();  //初始化Input的灰色提示信息  
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
            <a>管理员登录</a>
          </div>
    </div>
  </div>
</div>
<!-- 头部结束 -->
<div class="mian">
	<div class="fl flant">
    <div class="laing">
        <ul>
          <li>
            <label>用户名</label>
            <input id="code" name="code" tipMsg="用户名" type="text" class="int" onkeydown="if(event.keyCode == 13)login();" value="" onfocus="on_focus('code');"/>
          	 <div class="mpt" id="divCodeMpt" style="display:none">
            	<div class="wn_a"></div>
                <div class="wn_s" id="divCodeNull" style="display:none">用户名不能为空！</div>
                <div class="wn_s" id="divCodeNot" style="display:none">用户名不存在！</div>
                <div class="wn_s" id="divCodeNotMpt" style="display:none">用户已锁定，无法使用！</div>
                <div class="wn_c"></div>
            </div> 
          </li>
          <li>
            <label>密码</label>
            <input id="password" name="password" type="password" value="" class="int" onkeydown="if(event.keyCode == 13)login();" onfocus="on_focus('password');"/>
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
            &nbsp;&nbsp;&nbsp;记住密码<span class="mpt1"></span></li>
          <li class="lanot">
            <label></label>
            <a href="javascript:login();" id="saveBtn" class="hoa mr10">登录</a>
          </li>
        </ul>
      </div>
    </div>
    <div class="fr frland"></div>
    <div class="both"></div>
<br />
<br />
</div>
<!-- 底部开始 -->
<!-- 底部结束 -->
</body>
</html>