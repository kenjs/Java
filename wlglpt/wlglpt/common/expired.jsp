<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/include.jsp"%>
<%@ page language="java" pageEncoding="GBK"%>

<head>
<title>ϵͳ��ʾ</title>
<%@ include file="/common/meta.jsp"%>
<!-- ʵ�ֵ������ڹرչ��� -->
<script type="text/javascript">
	$(function(){
		var isQuery = false;
		showAlert("SessionʧЧ������������������µ�¼��","yesCallBack");	
	});
	
	function yesCallBack(){
		document.location.href=jcontextPath+"/";  
	}
</script>
</head>

<body>
</body>
</html>
