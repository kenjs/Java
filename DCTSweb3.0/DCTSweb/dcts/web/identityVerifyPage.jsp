<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>快到网-身份证查询</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/personal.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<sys:context/>/resource/js/cargo/importCargoFormFile.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/mycenter/myCenterLeftMenu.js"></script>
<script type="text/javascript"  src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
<script type="text/javascript"   src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>
<script type="text/javascript">
	function getSubmit() {
		var number = $("#form_number").val();
		var name = $("#form_name").val();
		if(number == "") {
			$("#divNumber").css({"display":"block","margin-left":"95px"});
			$("#divNumberMsg").css("display","block");
			$("#divNumberMsg").html("身份证号码不能为空！");
			return false;
		}
		var rgx = /(^\d{15}$)|(^\d{18}$)|(^\d{17}(\d|X|x)$)/;
		if(! rgx.test(number)) {
			$("#divNumber").css({"display":"block","margin-left":"95px"});
			$("#divNumberMsg").css("display","block");
			$("#divNumberMsg").html("身份证号码格式不正确！");
			return false;
		}
		if(name == "") {
			$("#divName").css({"display":"block","margin-left":"95px"});
			$("#divNameMsg").css("display","block");
			$("#divNameMsg").html("姓名不能为空！");
			return false;
		}
		
		document.getElementById('mainForm').submit();
	}
	function clearMsg(obj) {
		$(obj).next().hide();
	}
	function clearMg(obj) {
		$(obj).val("");
		$(obj).next().hide();
	}
</script>
</head>
  
<body>
  
<jsp:include page="/head.jsp" />
<div class="mian">
	<input type="hidden" id="menuAIdHi" value="${menuAId }" name="menuAId"/>
	<div class="mian_bor">
	<jsp:include page="/dcts/myCenter/myCenterLeftMenu.jsp" />
	<s:form id="mainForm" action="/citizenshipVerificationAction" namespace="/" method="post">
		<div class="fr sonafr">
	    	<div class="ntica">
	            <h3><i>&nbsp;</i>身份证查询</h3>
	            <div class="laing two_laing">
	                <ul>
	                  <li>
	                    <label>身份证号&nbsp;:</label>
	                    <input name="IDNumber" type="text" class="int m3" id="form_number" onfocus="clearMg(this)" onblur="clearMsg(this)"/>
	                   	<div class="mpt" id="divNumber" style="display:none">
	                   		<div class="wn_a"></div>
			                <div class="wn_s" id="divNumberMsg" style="display:none"></div>
			                <div class="wn_c"></div>
			            </div>
	                  </li>
	                  <li>
	                    <label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;:</label>
	                    <input name="name" type="text" class="int m3" id="form_name" onfocus="clearMg(this)" onblur="clearMsg(this)"/>
	                    <div class="mpt" id="divName" style="display:none">
	                    	<div class="wn_a"></div>
			                <div class="wn_s" id="divNameMsg" style="display:none"></div>
			                <div class="wn_c"></div>
			            </div>
	                  </li>
	                  <li class="ternow mt20">
	                    <label></label>
	                    <input id="form_submit" type="button" value="查询" class="sub" onclick="getSubmit()" /></li>
	                </ul>
	           </div>
	        </div>
	    </div>
    </s:form>
    </div>
		<!-- 合作伙伴 -->
       <div class="both mh"></div>
  <jsp:include page="/cooperativePartner.jsp" />
</div>
<br />
<br />
<br />

<!--个人中心结束-->
<jsp:include page="/bottom.jsp" />

</body>
</html>    