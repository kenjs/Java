<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
<title>快到网-个人中心-修改发货方信息</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/personal.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/threeLinkage/css/cityLayout.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<sys:context/>/resource/js/jquery.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/mycenter/myCenterLeftMenu.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/myClient/saveClient.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/basis.js"></script>

<script type="text/javascript"  src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
<script type="text/javascript"   src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>

<style type="text/css">
.city_input { border:1px solid #d6d6d6; width:180px; height:30px;
 background:url(<sys:context/>/resource/threeLinkage/images/ts-indexcity.png) no-repeat; 
 line-height:30px; margin-top:5px;  text-indent:5px;}
</style>


</head>
  
<body>
  
<jsp:include page="/head.jsp" />
<!--个人中心-->

<div class="mian">
	<div class="mian_bor">
	<jsp:include page="/dcts/myCenter/myCenterLeftMenu.jsp" />
	<s:form id="mainUpdateForm" action="/modifySonWebUserInfo" namespace="/" method="post">
	<!-- 当前左边菜单链接的Id -->
	<input type="hidden" id="menuAIdHi" value="${webUserInfoDamain.menuAId }" name="webUserInfoDamain.menuAId"/>
	<input type="hidden" id="userType" value="${webUserInfoDamain.userType }" name="webUserInfoDamain.userType"/>
	<input type="hidden" id="id" value="${webUserInfoDamain.id }" name="webUserInfoDamain.id"/>
	<input type="hidden" id="companyId" value="${webUserInfoDamain.companyId }" name="webUserInfoDamain.companyId"/>
	
	<input type="hidden" id="deliveryFlages" value="${webUserInfoDamain.deliveryFlag}"/>
	<input type="hidden" id="arrivalSurees" value="${webUserInfoDamain.arrivalSure}" />
	<input type="hidden" id="receiveSurees" value="${webUserInfoDamain.receiveSure}"/>
     <div class="fr sonafr">
    <div class="flant auton">
    <h3><i>&nbsp;</i>修改${webUserInfoDamain.userTypeVal}
	</h3>
    <div class="laing shng">
        <ul class="order">
          <li>
            <label>用户名&nbsp;<span>*</span></label>
            <input id="code" value="${webUserInfoDamain.code}" type="text" readonly="readonly"  class="int m3" />
          </li>
          <li>
            <label>编码&nbsp;<span>*</span></label>
            <input id="encoded"  value="${webUserInfoDamain.encoded}" type="text" readonly="readonly" class="int m3" "/>
          </li>
          
          <li>
            <label>企业全称&nbsp;<span>*</span></label>
            <input id="companyName" value="${webUserInfoDamain.companyName}"  readonly="readonly" type="text" class="int m3" />
            
          </li>
          <li>
            <label>公司地址 &nbsp;<span>*</span></label>
            <input type="text" id="companyPcc" onfocus="on_focus_update('companyPcc')" name="webUserInfoDamain.companyPcc" readonly="readonly" style="width:150px;" value="${webUserInfoDamain.companyPcc}" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/>
            <div class="mpt" id="companyPccMpt" style="display:none">
            	<div class="wn_a"></div>
            	<div class="wn_s" id="companyPccNotEmpt" style="display:none">请输入公司地址！</div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
            <label>详细地址 &nbsp;</label>
            <input id="companyAddress" name="webUserInfoDamain.companyAddress" value="${webUserInfoDamain.companyAddress}" type="text" class="int m3" />
          </li>
          <li>
            <label>联系人 &nbsp;<span>*</span></label>
            <input id="contactName" name="webUserInfoDamain.contactName"  onfocus="on_focus_update('contactName')" value="${webUserInfoDamain.contactName}" type="text" class="int m3" />
            <div class="mpt" id="contactNameMpt" style="display:none">
            	<div class="wn_a"></div>
            	<div class="wn_s" id="contactNameNotEmpt" style="display:none">请输入联系人！</div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
            <label>联系电话 &nbsp;<span>*</span></label>
            <input id="contactTelephone" name="webUserInfoDamain.contactTelephone"  onfocus="on_focus_update('contactTelephone')" value="${webUserInfoDamain.contactTelephone}"  type="text" class="int m3" />
             <div class="mpt" id="contactTelephoneMpt" style="display:none">
            	<div class="wn_a"></div>
            	<div class="wn_s" id="contactTelephoneNotEmpt" style="display:none">请输入联系电话！</div>
          		<div class="wn_s" id="contactTelephoneError" style="display:none">请填入正确的联系电话！</div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
            <label>创建时间 &nbsp;</label>
            <input id="createTime" name="createTime" value="${webUserInfoDamain.createTime}"  readonly="readonly"  type="text" class="int m3" />
          </li>          
          <li class="ower">
            <label>用户权限&nbsp;</label>
            <input id="deliveryFlag" name="webUserInfoDamain.deliveryFlag" onclick="getDeliveryFlag();"  type="checkbox"  <c:if test="${webUserInfoDamain.deliveryFlag=='1' }">checked="checked"</c:if>  value="${webUserInfoDamain.deliveryFlag }" />发货确认&nbsp;&nbsp;&nbsp;
            <input id="arrivalSure" name="webUserInfoDamain.arrivalSure" onclick="getArrivalSure();" type="checkbox" <c:if test="${webUserInfoDamain.arrivalSure=='1' }">checked="checked"</c:if>  value="${webUserInfoDamain.arrivalSure }" />到货确认&nbsp;&nbsp;&nbsp;
            <input id="receiveSure" name="webUserInfoDamain.receiveSure" onclick="getReceiveSure();" type="checkbox" <c:if test="${webUserInfoDamain.receiveSure=='1' }">checked="checked"</c:if>  value="${webUserInfoDamain.receiveSure }" />收货确认
          </li>
          <li class="ternow">
            <label></label>
              
            <input name="" onclick="getUpdateSubmits();" type="button" value="保存修改" />
            &nbsp;&nbsp;&nbsp;&nbsp;
           	<input name="" onclick="restPw('${webUserInfoDamain.id }')" type="button" value="重置密码" />
             &nbsp;&nbsp;&nbsp;&nbsp;
            <input name="" onclick="returnQuery();" type="button" value="返回列表" />
           
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
<script src="<sys:context/>/resource/threeLinkage/js/public.js"></script>
<script src="<sys:context/>/resource/threeLinkage/js/jquery-1.6.2.min.js"></script>
</body>
</html>