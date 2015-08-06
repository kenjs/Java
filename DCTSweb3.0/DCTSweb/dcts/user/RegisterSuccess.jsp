<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>注册成功</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">

</script>
</head>
<body>
<!-- 头部开始 -->
<jsp:include page="/heades.jsp" />
<!-- 头部结束 -->
<div class="mian">
<div class="botn">
	<div class="fl w400"><img src="../resource/image/index/dj.jpg" width="90" height="90" /></div>
    <div class="fr succes">
    	<ul>
        	<li class="size18">恭喜您注册成功！</li>
            <li>现在就去<a href="<sys:context/>/querySuccessCloseTransactionInfo?transactionInfoDomain.menuAId=">个人中心</a>完善资料精准找车！</li>
            <li class="m8">你还可以：
            <a href="<sys:context/>/openAddLocalOrderCargoInfo?orderCargoInfoDomain.menuAId=">发布货源</a>
            <a href="<sys:context/>/">首页找车</a></li>
            <li class="ternowf"><input name="" type="button" value="完成" class="sub" onclick="window.location.href='<sys:context/>/'"/></li>
        </ul>
    </div>
</div>
</div>
<!-- 底部开始 -->
<jsp:include page="/bottom.jsp" />
<!-- 底部结束 -->
</body>

</html>