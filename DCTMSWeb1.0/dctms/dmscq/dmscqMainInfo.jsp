<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>�ҵĻ�Դ</title>
<script type="text/javascript" src="<sys:context/>/resource/js/pageInfo.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/areaDataDict.js"></script>
<link href="<sys:context/>/resource/css/mycargo.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
</script>
<style type="text/css">
</style>
</head>

<body  onselect="false">
<s:form id="mainForm" action="/dmscqMain" namespace="/" method="post">
<div>
�����
<s:textfield name="dmscqDomain.tableName"></s:textfield>
</div>
<div>
�ؼ���
<s:textfield name="dmscqDomain.keyword"></s:textfield>
</div>
<div>
<s:submit></s:submit>
</div>
</s:form>
</body>
</html>