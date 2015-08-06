<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/include.jsp"%>
<%@ page contentType="text/html;charset=GBK"%>

<head>
<title>物流信息管理平台</title>
<%@ include file="/common/meta.jsp"%>

<script type="text/javascript">
	$(function(){
		$('body').layout({ applyDefaultStyles: true });
		
		resizeWin();
	});
	
	//确定要重新登录操作
	function yesCallBackOne(){
		document.location.href=jcontextPath+"/login!logout.action";  
	}
	
	//确定要退出系统操作
	function yesCallBackTwo(){
		//清理SESSION
		var url = jcontextPath+"/login!clearSession";
		var jsonObj = {};
		ajaxCommon(url,jsonObj);
	}
	
	//成功后的数据处理
	function doSuccess(data){	
		if(data.loginUserDomain.isLoginSuccess){
			setTimeout("window.opener=null;top.close()",3);
		}else{
			showError("退出系统失败！");
		}		
	}
	
	//取消系统退出操作
	function noTabCloseNoCallBack(){
		//changeGreybackground();
		return false;
	}
	
	function navigateMenu(a,b,c){
		parent.parent.document.getElementById("ui-layout-center").contentWindow.navigate(a,b,c);
	}
	
	//
	function resizeWin(){
	    var sw = window.screen.availWidth;
	    var sh = window.screen.availHeight;
	    window.moveTo(0,0);
		window.resizeTo(sw,sh);
	}
</script>
</head>

<frameset rows="99,*" cols="*" id="FRM_WHOLE" frameborder="no" framespacing="0">
  <frame src="<sys:context/>/work/xtgl/top.jsp" name="topFrame" id="FRM_TOP" scrolling="no" frameborder="0">
  <frameset cols="170,5,*" frameborder="no" id="FRM_MIDDLE" framespacing="0">
    <frame src="<sys:context/>/work/xtgl/left.jsp" name="FRM_LEFT" id="FRM_LEFT" scrolling="no" frameborder="0">
    <frame src="<sys:context/>/work/xtgl/btn.jsp" name="FRM_BTN" id="FRM_BTN" scrolling="no" frameborder="0">
    <frame src="<sys:context/>/work/xtgl/right.jsp" name="FRM_RIGHT" id="FRM_RIGHT" scrolling="auto" height="100%" width="98%">
  </frameset>
</frameset><noframes></noframes>

<body>
</body>
 <%@include file="/common/message.jsp" %>
</html>