<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<head>
<title>快到网-定车成功</title>
<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/bounced.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"  src="<sys:context/>/resource/js/basis.js"></script>
<script type="text/javascript"  src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
<script type="text/javascript"   src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>
<script type="text/javascript">

</script>
</head>
<style>
</style>
<body style="overflow:hidden;">
        <div class="buced">
                <div class="fl ticks"><img src="<sys:context/>/resource/image/index/jou.jpg" width="70" height="70" /><!--<img src="../resource/image/tanku/jou.jpg" width="70" height="70" /> 提示：发布失败图标--></div>
                <div class="fr frcks">恭喜！您已成功定车！<!--抱歉！您的货源发布失败！--></div>
                <div class="botcks">
                <a style="margin-left:75px" href="javascript:pageJump('<sys:context/>/queryTransactionInfo?transactionInfoDomain.menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@MY_ORDER'/>');">我的订单</a>
                <a href="javascript:pageJump('<sys:context/>/queryLocalOrderCargoInfo?orderCargoInfoDomain.menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@MY_CARGO'/>');">我的货源</a>
                <a href="javascript:pageJump('<sys:context/>/index.jsp');">返回首页<i>&nbsp;</i></a></div>
        </div>
</body>
</html>
