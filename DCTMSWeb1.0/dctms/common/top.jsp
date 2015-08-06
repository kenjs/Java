<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%--<%@ include file="/common/include-jqueryJs.jsp"%>--%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<link href="<sys:context/>/resource/css/top.css" rel="stylesheet" type="text/css" />
<title>头部</title>
<style type="text/css">
</style>
<script>
function logout(){
	window.parent.location.href=jcontextPath+ "/managerLogout";
}
function updatePassword(){
	var url = jcontextPath+"/dctms/manager/updateManagerPassword.jsp";
	window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
}
</script>
</head>
<body>
<s:form action="/managerLogout"   id="mainForm" method="post" namespace="/">
<div class="top">
	<div class="topBox">
    	<img src="<sys:context/>/resource/images/logo.PNG" />
        <h2>网站管理系统</h2>
        <div class="topRight">
        	<ul>
        		 <li><a href="#">使用说明</a></li>
                <li style="background:url(<sys:context/>/resource/images/infor.png) left center no-repeat;" onclick="updatePassword()"><a href="#">修改密码</a></li>
                <li style="background:url(<sys:context/>/resource/images/out.png) left center no-repeat;"><a href="#" onclick="logout()">退出系统</a></li>
            </ul>
            <div>当前用户：<%=request.getSession().getAttribute("name")%>，欢迎您！</div>
        </div>
    </div>
</div>
</s:form>
</body>
</html>



