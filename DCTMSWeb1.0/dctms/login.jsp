<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<sys:script/>
<head>
<title>�쵽��-��������ƽ̨-��̨����ϵͳ-��¼</title>

<script type='text/javascript' src='<sys:context/>/resource/pageframe/com/jquery-1.6.1.js'></script>
<script type='text/javascript' src='<sys:context/>/resource/pageframe/js/helper.js'></script>
<script type='text/javascript' src='<sys:context/>/resource/pageframe/js/common.js'></script>
<script type='text/javascript' src='<sys:context/>/resource/pageframe/js/jquery.cookie.js'></script>
<script type='text/javascript' src='<sys:context/>/resource/pageframe/com/json2.js'></script>
<script type='text/javascript' src='<sys:context/>/resource/pageframe/js/base.js'></script>
<link href="<sys:context/>/resource/css/login.css" rel="stylesheet" type="text/css" />
<!-- ʵ��IE6��pngͼƬ͸�� -->
<!--[if IE 6]>
<script src="<sys:context />/resource/pageframe/js/DD_belatedPNG_0.0.8a-min.js" type="text/javascript"></script>
<script type="text/javascript">
	DD_belatedPNG.fix('h2, a');
</script>
<![endif]-->

<script type="text/javascript">

	$(function(){	
		//��ȡCOOKIE��ֵ		
		$("#mainForm_managerInfoDomain_code").val(getCookieCode());		
		//$("#mainForm_managerInfoDomain_companySimpleEnCode").val(getCookieSimpleEnCode());
		//$("#mainForm_managerInfoDomain_password").val(getCookiePassword());
		
		MM_preloadImages();
		//��ʼ���۽�
		initFouce();
		
		//���س����е�¼
		$(document).keydown(function(){ 
			if(event.keyCode == 13){
				onLogin();
			}				
		}); 
				
	});
	
	function MM_preloadImages() { //v3.0
	  var d=document; if(d.images){ if(!d.MM_p) d.MM_p=new Array();
	    var i,j=d.MM_p.length,a=MM_preloadImages.arguments; for(i=0; i<a.length; i++)
	    if (a[i].indexOf("#")!=0){ d.MM_p[j]=new Image; d.MM_p[j++].src=a[i];}}
	}
	
	//��ʼ���۽�
	function initFouce(){		
 		var code = $("#mainForm_managerInfoDomain_code"); 
		var password = $("#mainForm_managerInfoDomain_password");
		//var companySimpleEnCode = $("#mainForm_managerInfoDomain_companySimpleEnCode");
		//if (companySimpleEnCode.val() == null || trim(companySimpleEnCode.val()) == "" || companySimpleEnCode.val() == companySimpleEnCode[0].defaultValue) {
		//	companySimpleEnCode.select();
		//	return;
		//}
		if (code.val() == null || trim(code.val()) == "" || code.val() == code[0].defaultValue) {
			code.select();
			return;
		}
		if (password.val() == null || trim(password.val()) == "" || password.val() == password[0].defaultValue) {
			password.select();
			return;
		}
	}
	
	function onReset() {
		//var companySimpleEnCode = $("#mainForm_managerInfoDomain_companySimpleEnCode");
		var code = $("#mainForm_managerInfoDomain_code");
		var password = $("#mainForm_managerInfoDomain_password");
		
		//companySimpleEnCode.val("");
		code.val("");
		password.val("");
	}

	function onLogin() {
		if (!checkLoginInfo()) {
			return;
		}
		//var myMac = "74:D4:35:4B:9E:B0";
		var myMac = getMac();
		$("#mainForm_managerInfoDomain_macAddress").val(myMac);
 		document.getElementById('mainForm').submit();
 		setCookies(trim($("#mainForm_managerInfoDomain_code").val()));
 		return true;
	}
	function getMac(){   
		try{
        var locator =new ActiveXObject ("WbemScripting.SWbemLocator");
		}catch(e){
			return "";
		}
        var service = locator.ConnectServer(".");       
        var properties = service.ExecQuery("Select * from Win32_NetworkAdapterConfiguration Where IPEnabled =True");       
        var e =new Enumerator (properties);
        var p = e.item();      
        //��ȡmac��ַ      
        var myMac = p.MACAddress;    
        return myMac;
    } 
	//��¼�ɹ�
	function doLoginSuccess(data) {
		setCookies(trim($("#mainForm_managerInfoDomain_code").val()));
		window.location.href = jcontextPath;
	}
	//ʧ�ܵ��÷���
	function doError(data){
		$("#codePasswordHtmlId").html(data.responseText);
	}
	
	function checkLoginInfo() {
		var code = $("#mainForm_managerInfoDomain_code");
		if (code.val() == null || trim(code.val()) == "" || code.val() == code[0].defaultValue) {
			$("#errorMessageId").html("");
			$("#codeHtmlId").html("�������û���");
			return false;
		}
		
		var password = $("#mainForm_managerInfoDomain_password");
		if (password.val() == null || trim(password.val()) == "" || password.val() == password[0].defaultValue) {
			$("#errorMessageId").html("");
			$("#passwordHtmlId").html("����������");
			return false;
		}
		return true;
	}
	
	/**
	* @param user	�û���
	* ����ʱ��Ϊ60��
	*/
	function setCookies(code){		
		$.cookie("code", code,{expires:60});
	}
	
	//��ȡcookie��code
	function getCookieCode(){
		var returnValue = "";
		returnValue = $.cookie("code");
		if(returnValue == null){
			returnValue = $("#mainForm_managerInfoDomain_code")[0].defaultValue;
		}
		return returnValue;
	}
	
	function getCookieSimpleEnCode() {
		var returnValue = "";
		returnValue = $.cookie("companySimpleEnCode");
		if(returnValue == null){
			returnValue = $("#mainForm_managerInfoDomain_companySimpleEnCode")[0].defaultValue;
		}
		return returnValue;
	}
	
	function getCookiePassword() {
		var returnValue = "";
		returnValue = $.cookie("password");
		return returnValue;
	}
	
	function getOnfucts(str) {
		$("#"+str).html("");
		$("#codePasswordHtmlId").html("");
	}
	
</script>


<!--[if IE 6]>
<style type="text/css">
 html{overflow:hidden;}
 body{height:100%;overflow:auto;}
 #FloatDiv_left{position:absolute;}
#left{height:740px;}
</style>
<![endif]-->

</head>

<body>
<s:form id="mainForm" action="/userLogin" namespace="/" method="post">
<s:hidden name="managerInfoDomain.macAddress"></s:hidden>
<div class="loginBox">
	<div class="login">
    	<div class="userName"><span>�û���:</span><s:textfield name="managerInfoDomain.code" value="�������û���" onclick="if(value==defaultValue){value='';this.style.color='#000'}" onfocus="getOnfucts('codeHtmlId');" onBlur="if(!value){value=defaultValue;this.style.color='#999'}" /></div>
    	<div class="error"><span id="codeHtmlId">${managerInfoDomain.errorMessage}</span></div>
        <div class="password"><span>����:</span><s:password name="managerInfoDomain.password" value="����������" onclick="if(value==defaultValue){value='';this.style.color='#000'}" onfocus="getOnfucts('passwordHtmlId');" onBlur="if(!value){value=defaultValue;this.style.color='#999'}" /></div>
        <div class="error"><span id="passwordHtmlId"></span></div>
        <div class="button">
        	<input type="button" onclick="javascript:onLogin();" class="button1" value="��&nbsp;&nbsp;&nbsp;&nbsp;¼" />
            <input type="button" onclick="javascript:onReset();" class="button2" value="��&nbsp;&nbsp;&nbsp;&nbsp;��" />
        </div>
    </div>
</div>
</s:form>
</body>
</html>
