<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
<title>快到网-我的客户-新增发货方信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/personal.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/threeLinkage/css/cityLayout.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<sys:context/>/resource/js/basis.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/mycenter/myCenterLeftMenu.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/myClient/queryClient.js"></script>

<style type="text/css">
.city_input { border:1px solid #d6d6d6; width:180px; height:30px;
 background:url(<sys:context/>/resource/threeLinkage/images/ts-indexcity.png) no-repeat; 
 line-height:30px; margin-top:5px;  text-indent:5px;}
</style>
<script type="text/javascript">
   $(function(){
   		var deliveryFlag = $("#deliveryFlag").val();
   		var arrivalSure = $("#arrivalSure").val();
   		var receiveSure = $("#receiveSure").val();
   		if(deliveryFlag == "1") {
   			$("#deliveryFlag").attr("checked",true);
   		}
   		if(arrivalSure == "1") {
   			$("#arrivalSure").attr("checked",true);
   		}
   		if(receiveSure == "1") {
   			$("#receiveSure").attr("checked",true);
   		}
   });
</script>
<script type="text/javascript">
  function getReturnQuery() {
  	var userType = $("#userType").val();
  	var menuAId = $("#menuAIdHi").val();
  	location.href=jcontextPath+"/querySonWebUserInfo?userType="+userType+"&menuAId="+menuAId;
  }
</script>
</head>
  
<body>
  
<jsp:include page="/head.jsp" />
<!--个人中心-->

<div class="mian">
	<div class="mian_bor">
	<jsp:include page="/dcts/myCenter/myCenterLeftMenu.jsp" />
	<s:form id="mainForm" action="/registerSonWebUserInfo" namespace="/" method="post">
	<!-- 当前左边菜单链接的Id -->
	<input type="hidden" id="menuAIdHi" value="${webUserInfoDamain.menuAId }" name="webUserInfoDamain.menuAId"/>
	<input type="hidden" id="userType" value="${webUserInfoDamain.userType }" name="webUserInfoDamain.userType"/>
     <div class="fr sonafr">
    <div class="flant auton">
    <h3><i>&nbsp;</i>
			<c:if test="${webUserInfoDamain.userType == '0'}">
				承运方详细信息
			</c:if>
			<c:if test="${webUserInfoDamain.userType == '1'}">
				发货方详细信息
			</c:if>
			<c:if test="${webUserInfoDamain.userType == '2'}">
				收货方详细信息
			</c:if>
	</h3>
    <div class="laing shng">
        <ul class="order">
          <li>
            <label>用户名&nbsp;:</label>
            ${webUserInfoDamain.code}
          </li>
          <li>
            <label>编码&nbsp;:</label>
            ${webUserInfoDamain.encoded}
          </li>
          <li>
            <label>企业全称&nbsp;:</label>
            ${webUserInfoDamain.companyName}
          </li>
          <li>
            <label>公司地址 &nbsp;:</label>
            ${webUserInfoDamain.companyPcc}
          </li>
          <li>
            <label>详细地址 &nbsp;:</label>
            ${webUserInfoDamain.companyAddress}
          </li>
          <li>
            <label>联系人 &nbsp;:</label>
            ${webUserInfoDamain.contactName}
          </li>
          <li>
            <label>联系电话 &nbsp;:</label>
            ${webUserInfoDamain.contactTelephone}
          </li>
          <li>
            <label>创建时间 &nbsp;:</label>
            ${webUserInfoDamain.createTime}
          </li>          
          <li class="ower">
            <label>用户权限&nbsp;:</label>
            <input id="deliveryFlag" onclick="return false;"  name="webUserInfoDamain.deliveryFlag" type="checkbox" onclick="getDeliveryFlag()" value="${webUserInfoDamain.deliveryFlag}" />发货确认&nbsp;&nbsp;&nbsp;
            <input id="arrivalSure" onclick="return false;" name="webUserInfoDamain.arrivalSure" type="checkbox" onclick="getArrivalSure()" value="${webUserInfoDamain.arrivalSure}" />到货确认&nbsp;&nbsp;&nbsp;
            <input id="receiveSure" onclick="return false;" name="webUserInfoDamain.receiveSure" type="checkbox" onclick="getReceiveSure()" value="${webUserInfoDamain.receiveSure}" />收货确认
          </li>
          <li class="ternow">
            <label></label>
            <input name="" onclick="getReturnQuery();" type="button" value="返回列表" />
          </li>
        </ul>
        <div style="clear:both;"></div>
      </div>
    </div>
    </div>
    </s:form>
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
<!-- 弹出省市区的层 -->
<jsp:include page="/resource/threeLinkage/provinceCityAreaDiv.jsp" />
<script src="<sys:context/>/resource/threeLinkage/js/jquery-1.6.2.min.js"></script>
<script src="<sys:context/>/resource/threeLinkage/js/public.js"></script>
</body>
</html>