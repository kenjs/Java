<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/personal.css" rel="stylesheet" type="text/css" />
<script src="<sys:context/>/resource/js/mycenter/updatetelephone.js"></script>

<script type="text/javascript"  src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
<script type="text/javascript"   src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>
<title>首页</title>
</head>
<body>
<input type="hidden" name="mobilephone" value="${code }"/>
<!-- 头部开始 -->
<jsp:include page="/head.jsp" />
<!-- 头部结束 -->
<!--个人中心-->
<div class="mian">
	<div class="mian_bor">
	<jsp:include page="/dcts/myCenter/myCenterLeftMenu.jsp" />
    <div class="fr sonafr">
		<div class="dures">
        	<ul>
            	<li class="fli">1.验证身份</li>
                <li>2.填写新手机号码</li>
                <li>3.完成</li>
            </ul>
        </div>
       <div class="laing two_laing" id="check_phone">
        <ul>
          <li class="state"><label>已验证手机号&nbsp;:</label><input name="mobilephone_str" type="text"  value="" class="w160" readonly="readonly"/>
          					<input id="get_code" name="" type="button" value="获取验证码" class="unt" onclick="codeTimer()"/></li>
          <li>
            <label>请输入验证&nbsp;:</label><input name="verification_code" type="text" class="int m3" />
          </li>
          <li class="ternow mt20">
            <label></label>
            <input name="" type="button" value="下一步" class="sub" onclick="nextStep()" /></li>
        </ul>
       </div>
       <!--2.填写修改密码-->
       <div class="laing two_laing" id="new_phone" style="display: none;">
        <ul>
          <li class="state"><label>填写新手机号&nbsp;:</label><input name="new_mobilephone" type="text"  value="" class="int m3"/>
          					<input id="get_code_new" name="" type="button" value="获取验证码" class="unt" onclick="codeTimer2()"/></li>
          <li>
            <label>请输入验证&nbsp;:</label><input name="verification_new" type="text" class="int m3" />
          </li>
          <li class="ternow mt20">
            <label></label>
            <input name="" type="button" value="下一步" class="sub" onclick="secondStep()" /></li>
        </ul>
       </div>
       
       <!-- 修改密码已成功页面    --> 
       <div class="laing two_laing" id="complete_success" style="display: none;">
        	<dl>
            	<dt><img src="<sys:context/>/resource/image/sonal/rt.jpg" width="66" height="57" /></dt>
                <dd>恭喜！您已成功修改手机号码！</dd>
            </dl>
            <div class="ternow mtf"><input name="" type="button" value="完成" class="sub" onclick="javascript:window.location.href='<sys:context/>/accountManagementAction'"/></div>
       </div>
      
        <!-- 修改密码失败页面  --> 
      <div class="laing two_laing" id="complete_fail" style="display: none;">
        	<dl>
            	<dt><img src="<sys:context/>/resource/image/sonal/rt1.jpg" width="66" height="57" /></dt>
                <dd>抱歉！您的手机号码修改失败！</dd>
            </dl>
            <div class="ternow mtf"><input name="" type="button" value="取消" class="sub subf" /><input name="" type="button" value="重新修改" class="sub" onclick="javascript:window.location.href='<sys:context/>/openUpdateTelephoneView'" /></div>
       </div>
        
       <div class="two_b">
       		<dl>
            	<dt><i class="wsfi">&nbsp;</i>为什么要进行身份验证？</dt>
                <dd>1、为保障您的账户信息安全，在变更账户中的重要信息时需要进行身份验证，感谢您的理解和支持。</dd>
                <dd>2、如果您绑定的快到网账号的手机因停机、注销、丢失等情况无法接收验证的，请您及时联系快到网客服</dd>
            </dl>
       </div>
       
       
    </div>
    </div>
        <!-- 合作伙伴 -->
      <div class="both mh"></div>
  <jsp:include page="/cooperativePartner.jsp" />
</div>
<br />
<br />
<br />
<!-- 个人中心结束 -->
<jsp:include page="/bottom.jsp" />
</body>
</html>