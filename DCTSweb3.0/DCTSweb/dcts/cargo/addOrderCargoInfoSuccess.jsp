<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<% String orderCargoId=request.getParameter("orderCargoId");%>
<head>
<title>快到网-发布货源成功</title>
<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/bounced.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"  src="<sys:context/>/resource/js/basis.js"></script>
<script type="text/javascript"  src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
<script type="text/javascript"   src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>
<script type="text/javascript">
//继续发布（刷新父页面，关闭art.dialog）
function continuePublish(){
   //art.dialog.open.origin 表示父窗体
   art.dialog.open.origin.$('#mainForm')[0].reset();
   art.dialog.close();
}
</script>
</head>
<style>
</style>
<body style="overflow:hidden;">
        <div class="buced">
                <div class="fl ticks"><img src="<sys:context/>/resource/image/index/jou.jpg" width="70" height="70" /><!--<img src="../resource/image/tanku/jou.jpg" width="70" height="70" /> 提示：发布失败图标--></div>
                <div class="fr frcks">恭喜！您已成功发布货源！<!--抱歉！您的货源发布失败！--></div>
                <div class="botcks" style="width: 450px;">
                <a href="javascript:setDriverCar('<%=orderCargoId %>')">立即匹配车源</a>
                <a href="javascript:continuePublish();">继续发布</a>
                <a href="javascript:pageJump('<sys:context/>/queryLocalOrderCargoInfo?orderCargoInfoDomain.menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@MY_CARGO'/>');">我的货源</a>
                <a href="javascript:pageJump('<sys:context/>/index.jsp');">返回首页<i>&nbsp;</i></a></div>
        </div>
</body>
</html>
