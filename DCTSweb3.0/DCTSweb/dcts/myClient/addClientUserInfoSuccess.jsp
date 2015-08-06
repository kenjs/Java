<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
<title>快到网-个人中心-新增发货方信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/personal.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="<sys:context/>/resource/js/basis.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/pageInfoThree.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/mycenter/myCenterLeftMenu.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/myClient/queryClient.js"></script>
</head>
<body>
  
<jsp:include page="/head.jsp" />
<!--个人中心-->
<div class="mian">
	<div class="mian_bor">
	<jsp:include page="/dcts/myCenter/myCenterLeftMenu.jsp" />
	<!-- 当前左边菜单链接的Id -->
	<input type="hidden" id="menuAIdHi" value="${webUserInfoDamain.menuAId }" name="webUserInfoDamain.menuAId"/>
	<input type="hidden" id="userType" value="${webUserInfoDamain.userType }" name="webUserInfoDamain.userType"/>
     <div class="fr sonafr">
		<div class="histo">
        	<dl>
            	<dt><img src="<sys:context/>/resource/image/sonal/rt.jpg" width="66" height="57" /></dt>
            	<c:if test="${webUserInfoDamain.userType == '0'}">
					<dd>恭喜！您已成功添加承运方客户！</dd>
				</c:if>
				<c:if test="${webUserInfoDamain.userType == '1'}">
					<dd>恭喜！您已成功添加发货方客户！</dd>
				</c:if>
				<c:if test="${webUserInfoDamain.userType == '2'}">
					<dd>恭喜！您已成功添加收货方客户！</dd>
				</c:if>
            </dl>
            <div class="serve">
            	<c:if test="${webUserInfoDamain.userType == '0'}">
					<!-- <a href="<sys:context/>/addSonWebUserInfo?userType=0&menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@ADD_MY_ACCEPT_MANAGE'/>">继续添加</a> -->
					<a href="<sys:context/>/querySonWebUserInfo?userType=0&menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@MY_ACCEPT_MANAGE'/>">返回列表</a>
				</c:if>
				<c:if test="${webUserInfoDamain.userType == '1'}">
					<!-- <a href="<sys:context/>/addSonWebUserInfo?userType=1&menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@ADD_MY_DELIVER_MANAGE'/>">继续添加</a> -->
					<a href="<sys:context/>/querySonWebUserInfo?userType=1&menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@MY_DELIVER_MANAGE'/>">返回列表</a>
				</c:if>
				<c:if test="${webUserInfoDamain.userType == '2'}">
					<!-- <a href="<sys:context/>/addSonWebUserInfo?userType=2&menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@ADD_MY_TAKE_DELIVER_MANAGE'/>">继续添加</a> -->
					<a href="<sys:context/>/querySonWebUserInfo?userType=2&menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@MY_TAKE_DELIVER_MANAGE'/>">返回列表</a>
				</c:if>
            </div>
       </div>
    </div>
    </div>
    <!-- 合作伙伴 -->
     <div class="both mh"></div>
    <jsp:include page="/cooperativePartner.jsp" />
</div>
<br />
<br />
<br />
<!--个人中心结束-->
<jsp:include page="/bottom.jsp" />
</body>
</html>