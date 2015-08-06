<!DOCTYPE HTML>
<html>
<%@ include file="/common/include.jsp"%>
<%@ page contentType="text/html;charset=GBK"%>

<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>物流信息管理平台</title>

<script type='text/javascript' src='<sys:context/>/resource/pageframe/com/jquery-1.6.1.js'></script>
<script type='text/javascript' src='<sys:context/>/resource/pageframe/js/helper.js'></script>
<script type='text/javascript' src='<sys:context/>/resource/pageframe/js/common.js'></script>
<script type='text/javascript'  src='<sys:context/>/resource/pageframe/js/jquery.cookie.js'></script>
<script type='text/javascript' src='<sys:context/>/resource/pageframe/com/json2.js'></script>
<script type='text/javascript' src='<sys:context/>/resource/pageframe/js/base.js'></script>
<link type="text/css" rel="stylesheet" href="<sys:context/>/resource/pageframe/css/base.css"/>
<link type="text/css" rel="stylesheet" href="<sys:context/>/resource/wlglpt/css/wlglpt.css" />
<!-- 实现IE6下png图片透明 -->
<!--[if IE 6]>
<script src="<sys:context />/resource/pageframe/js/DD_belatedPNG_0.0.8a-min.js" type="text/javascript"></script>
<script type="text/javascript">
	DD_belatedPNG.fix('h2, a');
</script>
<![endif]-->

<script type="text/javascript">

	var MACAddr='' ;
	function GetMACInfo() {
		/* try {
		　　　var locator = new ActiveXObject ("WbemScripting.SWbemLocator"); 
		}catch(err){
			//document.write("对不起，请开启ActiveX控件");
			window.location.href = jcontextPath+"/work/xtgl/ie_set.jsp"; 
		}
		if (locator) {
			var service = locator.ConnectServer("."); 
			　　var properties = service.ExecQuery("SELECT * FROM Win32_NetworkAdapterConfiguration where IPEnabled =True"); //mac地址
			　　var e = new Enumerator (properties); 	
			　　for (;!e.atEnd();e.moveNext ()) {
			　　　　    var pe = e.item ();
			         if(pe.MACAddress!=null && pe.MACAddress != "undefined" && (pe.IPEnabled))
			         MACAddr=pe.MACAddress;
			　     }		
					//alert(MACAddr);
		} */
		
			
	}


	$(function(){		
		GetMACInfo();
		//获取COOKIE赋值		
		$("#mainForm_loginUserDomain_dlzh").val(getCookieDlzh());		
		$("#mainForm_loginUserDomain_qybm").val(getCookieQybm());
		//$("#mainForm_loginUserDomain_dlmm").val(getCookieDlmm());
		
		MM_preloadImages();
		//初始化聚焦
		initFouce();
		
		//按回车进行登录
		$(document).keydown(function(){ 
			if(event.keyCode == 13){
				$("#loginBtn").click();	
			}				
		}); 
				
	});
	
	function MM_preloadImages() { //v3.0
	  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
	    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
	    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
	}
	
	function onReset() {
		var qybm = $("#mainForm_loginUserDomain_qybm");
		var dlzh = $("#mainForm_loginUserDomain_dlzh");
		var dlmm = $("#mainForm_loginUserDomain_dlmm");
		
		qybm.val(qybm[0].defaultValue);
		dlzh.val(dlzh[0].defaultValue);
		dlmm.val(dlmm[0].defaultValue);
	}

	function onLogin() {
		if (!checkLoginInfo()) {
			return;
		}
		var qybm = trim($("#mainForm_loginUserDomain_qybm").val());
		var dlzh = trim($("#mainForm_loginUserDomain_dlzh").val());
		var dlmm = trim($("#mainForm_loginUserDomain_dlmm").val());
		
		var url = jcontextPath + "/login!checkUserinfo";
		var jsonObj = {"loginUserDomain.qybm":qybm, "loginUserDomain.dlzh":dlzh, "loginUserDomain.dlmm":dlmm, "loginUserDomain.macAddr":MACAddr};
		ajaxCommon(url,jsonObj,"doLoginSuccess");
	}
	
	var qybm;
	var dlzh;
	var czyDjxh;
	var macAddr;
	function doLoginSuccess(data) {
		var isLoginSuccess = data.loginUserDomain.isLoginSuccess;
		var rtnCode = data.loginUserDomain.rtnCode;
		qybm = data.loginUserDomain.qybm;
		dlzh = data.loginUserDomain.dlzh;
		czyDjxh = data.loginUserDomain.czyDjxh;
		macAddr = data.loginUserDomain.macAddr;
		setLoginInfo(data.loginUserDomain.qybm,data.loginUserDomain.dlzh,data.loginUserDomain.dlmm);
		
		if (isLoginSuccess) {
			loginSuccess(data);
		}else if (rtnCode == "5") {
			showConfirm(data.loginUserDomain.rtnMess,"saveSwdnShOnly");
		}else if (rtnCode == "6"){
			showConfirm(data.loginUserDomain.rtnMess,"saveSwdnZhSh");
		}else {
			showError(data.loginUserDomain.rtnMess);
		}
	}
	
	function loginSuccess(data) {
		if (trim(data.loginUserDomain.loginMessage) != "") {
			var url = jcontextPath + "/" + data.loginUserDomain.loginMessage;
			$("#mainForm_loginUserDomain_czyDjxh").val(data.loginUserDomain.czyDjxh);
			$("#mainForm_loginUserDomain_macAddr").val(data.loginUserDomain.macAddr);
			$("#mainForm").attr("action", url);
			$("#mainForm").submit();
		}else {
			showError("系统出错，请刷新后操作或联系管理员!");
		}
	}
	
	function saveSwdnShOnly() {
		saveSwdnSh(macAddr,"",czyDjxh);
	}
	
	function saveSwdnZhSh() {
		saveSwdnSh(macAddr,dlzh,czyDjxh);
	}
	
	function saveSwdnSh(macAddr, dlzh, czyDjxh) {
		var jsonObj = {"loginUserDomain.macAddr":macAddr, "loginUserDomain.qybm":qybm, "loginUserDomain.dlzh":dlzh, "loginUserDomain.czyDjxh":czyDjxh};
		var url = jcontextPath + "/login!saveSwdnSh";
		ajaxCommon(url,jsonObj,"doShSuccess");
	}
	
	function doShSuccess() {
		showAlert("已发送至相关部门审核。");
	}
	
	function checkLoginInfo() {
		var qybm = $("#mainForm_loginUserDomain_qybm");
		if (qybm.val() == null || trim(qybm.val()) == "" || qybm.val() == qybm[0].defaultValue) {
			showError("请输入企业编码");
			qybm.select();
			return false;
		}
		
		var dlzh = $("#mainForm_loginUserDomain_dlzh");
		if (dlzh.val() == null || trim(dlzh.val()) == "" || dlzh.val() == dlzh[0].defaultValue) {
			showError("请输入用户名");
			dlzh.select();
			return false;
		}
		
		var dlmm = $("#mainForm_loginUserDomain_dlmm");
		if (dlmm.val() == null || trim(dlmm.val()) == "" || dlmm.val() == dlmm[0].defaultValue) {
			showError("请输入密码");
			dlmm.select();
			return false;
		}
		
		return true;
	}
	
	/**
	* @param user	用户名
	* 过期时间为60天
	*/
	function setLoginInfo(qybm,dlzh,dlmm){		
		$.cookie("dlzh", dlzh,{expires:60});
		$.cookie("qybm", qybm,{expires:60});
		//$.cookie("dlmm", dlmm,{expires:60});
	}
	
	//获取cookie的dlzh
	function getCookieDlzh(){
		var returnValue = "";
		returnValue = $.cookie("dlzh");
		if(returnValue == null){
			returnValue = $("#mainForm_loginUserDomain_dlzh")[0].defaultValue;
		}
		return returnValue;
	}
	
	function getCookieQybm() {
		var returnValue = "";
		returnValue = $.cookie("qybm");
		if(returnValue == null){
			returnValue = $("#mainForm_loginUserDomain_qybm")[0].defaultValue;
		}
		return returnValue;
	}
	
	function getCookieDlmm() {
		var returnValue = "";
		returnValue = $.cookie("dlmm");
		return returnValue;
	}
	
	//初始化聚焦
	function initFouce(){		
 		var dlzh = $("#mainForm_loginUserDomain_dlzh"); 
		var dlmm = $("#mainForm_loginUserDomain_dlmm");
		var qybm = $("#mainForm_loginUserDomain_qybm");
		if (qybm.val() == null || trim(qybm.val()) == "" || qybm.val() == qybm[0].defaultValue) {
			qybm.select();
			return;
		}
		if (dlzh.val() == null || trim(dlzh.val()) == "" || dlzh.val() == dlzh[0].defaultValue) {
			dlzh.select();
			return;
		}
		if (dlmm.val() == null || trim(dlmm.val()) == "" || dlmm.val() == dlmm[0].defaultValue) {
			dlmm.select();
			return;
		}
	}
</script>
</head>

<body>

<s:form action="login!checkUserinfo" namespace="/xtgl" id="mainForm">
<s:hidden name="loginUserDomain.czyDjxh" />
<s:hidden name="loginUserDomain.macAddr" />
<div class="wl_login">
  <div class="wl_logintop"></div>
  <div class="wl_loginmid">
    <div class="wl_loginmidcont">
      <div class="wl_loginmidcontl">
      	<% //根据不同端口号判断版本现实不同logo
    		int port = request.getServerPort();
      		String logo = "logo_customized.gif";
      		if(port==9959){//网络版
      			logo = "logo_internet.gif";
      		}else if(port==9969){//基础版
      			logo = "logo_base.gif";
      		}else if(port==9979){//加强版
      			logo = "logo_enhanced.gif";
      		}else if(port==9989){//高端版
      			logo = "logo_advanced.GIF";
      		}else if(port==9999){//定制版
      			logo = "logo_customized.gif";
      		}
      	%>
      	<img src='<sys:context/>/resource/pageframe/images/<%=logo %>' />
      </div>
      <ul class="wl_loginmidcontr">
        <li><p class="title">企业编码</p><p class="icon_01"></p><p class="cinputcont"><s:textfield name="loginUserDomain.qybm" cssClass="cinput" value="请输入企业编码" onclick="if(value==defaultValue){value='';this.style.color='#000'}" onblur="if(!value){value=defaultValue;this.style.color='#999'}" /></p></li>
        <li><p class="title">用 户 名</p><p class="icon_02"></p><p class="cinputcont"><s:textfield name="loginUserDomain.dlzh" cssClass="cinput" value="请输入用户名" onclick="if(value==defaultValue){value='';this.style.color='#000'}" onblur="if(!value){value=defaultValue;this.style.color='#999'}" /></p></li>
         <li><p class="title">密　　码</p><p class="icon_03"></p><p class="cinputcont"><s:password name="loginUserDomain.dlmm" cssClass="cinput" value="请输入密码" onclick="if(value==defaultValue){value='';this.style.color='#000'}" onblur="if(!value){value=defaultValue;this.style.color='#999'}" /></p></li>
        <li><a href="javascript:function(){}" onclick="javascript:onLogin();" id="loginBtn" class="login_btn01"></a>
        	<a href="javascript:onReset();" class="login_btn02"></a></li>
      </ul>
    </div>
  </div>
  <div class="wl_loginbtm"></div>
</div>
</s:form>
<%@include file="/common/message.jsp" %>
</body>
</html>
