<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>快到网-用户-找回密码</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<sys:context/>/resource/js/checkoutData.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/user/retrieveUserPws.js"></script>
<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(function(){
//$('input[name=username]').attr("readonly","readonly")//将input元素设置为readonly
//$('input[name=username]').removeAttr("readonly");//去除input元素的readonly属性
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
            <a>修改密码</a>
          </div>
    </div>
  </div>
</div>
<!-- 头部结束 -->
<!--个人中心-->
<div class="mian">
    <div class="sonafr cenfr">
		<div class="dures" id="dures1" style="display:block;">
        	<ul>
            	<li class="fli">1.验证身份</li>
                <li>2.请填写新密码</li>
                <li>3.完成</li>
            </ul>
        </div>
        <div class="dures dur1" id="dures2" style="display:none;">
        	<ul>
            	<li class="fli">1.验证身份</li>
                <li class="fli">2.请填写新密码</li>
                <li>3.完成</li>
            </ul>
        </div>
        <div class="dures dur2" id="dures3" style="display:none;">
        	<ul>
            	<li class="fli">1.验证身份</li>
                <li class="fli">2.请填写新密码</li>
                <li class="fli">3.完成</li>
            </ul>
        </div>
        <!-- 输入手机获取验证码开始 -->
       <div class="laing two_laing" id="laing1" style="display:block;">
        <ul>
          <li class="state"><label>请输入手机号码:</label>
          	<input type="text" id="mobilephone" name="mobilephone" tipMsg="请输入账号绑定手机号码" onblur="on_blur('mobilephone');" onfocus="on_focus('mobilephone');" value="" class="int m3"/>
          	&nbsp;&nbsp;&nbsp;&nbsp;
          	<input type="button" id="btnSendCode" name="btnSendCode" value="重新获取验证码" class="unt" onclick="sendMessage()"/>
          	<div class="mpt md94" id="divMobilephonezhu" style="display:none">
            	<div class="wn_a"></div>
                	<div class="wn_s" id="divMobilephoneNull" style="display:none">请输入手机号码!</div>
                 	<div class="wn_s" id="divMobilephoneFormat" style="display:none">请输入正确手机号码!</div>
                 	<div class="wn_s" id="divMobilephoneCyi" style="display:none">手机号码不存在!</div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li><label>请输入验证码:</label>
          	<input type="text" id="codeNumber" name="codeNumber"  tipMsg="请输入六位验证码" class="int m3" onblur="on_blur('codeNumber');" onfocus="on_focus('codeNumber');"/>
          	<div class="mpt md94" id="divCodeNumberzhu" style="display:none">
            	<div class="wn_a"></div>
                	<div class="wn_s" id="divCodeNumberLength" style="display:none">请输入六位验证码!</div>
                 	<div class="wn_s" id="divCodeNumberFormat" style="display:none">验证码不正确!</div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li class="ternow mt20">
            <label></label>
            <input name="" type="button" value="下一步" class="sub" onclick="nextMoveOne();"/></li>
        </ul>
       </div>
       <!-- 输入手机获取验证码结束-->
       
       <!-- 输入新密码开始-->
       <div class="laing two_laing" id="laing2" style="display:none;">
        <ul>
          <li class="state"><label>填写新密码&nbsp;:</label>
          	<input type="password" id="password" name="password"  class="int m3" onblur="on_blur('password');" onfocus="on_focus('password');"/>
          	<div class="mpt md94" id="divPasswordzhu" style="display:none">
            	<div class="wn_a"></div>
                	<div class="wn_s" id="divPasswordNull" style="display:none">密码不能为空！</div>
                 	<div class="wn_s" id="divPasswordLength" style="display:none">密码长度为6到20位！</div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li><label>确认密码&nbsp;:</label>
            <input type="password" id="passwordes" name="passwordes" class="int m3" onblur="on_blur('passwordes');" onfocus="on_focus('passwordes');"/>
          	<div class="mpt md94" id="divPasswordeszhu" style="display:none">
            	<div class="wn_a"></div>
            		<div class="wn_s" id="divPasswordNull" style="display:none">确认密码不能为空！</div>
                	<div class="wn_s" id="divPasswordes" style="display:none">两次密码输入不一致！</div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li class="ternow mt20">
            <label></label>
            <input name="" type="button" value="下一步" class="sub" onclick="nextMoveTwo();"/></li>
        </ul>
       </div>
       <!-- 输入新密码结束-->
       <!-- 找回密码成功开始-->
       <div class="laing two_laing" id="laing3" style="display:none;">
        	<dl>
            	<dt><img src="<sys:context/>/resource/image/sonal/rt.jpg" width="66" height="57" /></dt>
                <dd>恭喜！您已成功修改密码！</dd>
            </dl>
            <div class="ternow mtf"><input name="" onclick="javascript:location.href=jcontextPath+'/dcts/user/login.jsp';" type="button" value="返回登录" class="sub" /></div>
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
      <div class="both mh"></div>
  <jsp:include page="/cooperativePartner.jsp" />
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