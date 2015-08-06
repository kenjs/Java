<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/include.jsp"%>
<%@ page language="java" pageEncoding="GBK"%>

<head>
<title>系统提示</title>
<%@ include file="/common/meta.jsp"%>
<!-- 实现弹出窗口关闭功能 -->
<script type="text/javascript">
	$(function(){
		var isQuery = false;
		showAlert("Session失效或服务器重启，请重新登录！","yesCallBack");	
	});
	
	function yesCallBack(){
		document.location.href=jcontextPath+"/";  
	}
</script>
</head>

<body>
</body>
</html>
