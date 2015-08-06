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
<link href="<sys:context/>/resource/threeLinkage/css/cityLayout.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<sys:context/>/resource/js/basis.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/mycenter/myCenterLeftMenu.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/myClient/saveClient.js"></script>

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
	<s:form id="mainForm" action="/registerSonWebUserInfo" namespace="/" method="post">
	<!-- 当前左边菜单链接的Id -->
	<input type="hidden" id="menuAIdHi" value="${webUserInfoDamain.menuAId }" name="webUserInfoDamain.menuAId"/>
	<input type="hidden" id="userType" value="${webUserInfoDamain.userType }" name="webUserInfoDamain.userType"/>
     <div class="fr sonafr">
    <div class="flant auton">
    <h3><i>&nbsp;</i>新增${webUserInfoDamain.userTypeVal}
	</h3>
    <div class="laing shng">
        <ul class="order">
          <li>
            <label>用户名&nbsp;<span>*</span></label>
            <input id="code" name="webUserInfoDamain.code" type="text" class="int m3" onfocus="on_focus('code');"/>
            <ee class="remark_info">注：用户名只能有数字字母或下划线组成,长度为3~20位</ee>
            <div class="mpt" id="divcodezhu" style="display:none">
                <div class="wn_a"></div>
                <div class="wn_s" id="divCodeNull" style="display:none">请输入用户名！</div>
          		<div class="wn_s" id="divCodeCyi" style="display:none">用户名已存在！</div>
          		<div class="wn_s" id="divCodeLength" style="display:none">用户名长度为3到20位！</div>
          		<div class="wn_s" id="divCodeLetter" style="display:none">用户名只能有数字字母或下划线组成！</div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
            <label>密码&nbsp;<span>*</span></label>
            <input id="password" name="webUserInfoDamain.password" type="password" class="int m3"  onfocus="on_focus('password');"/>
            <ee class="remark_info">注：密码长度为6~20位</ee>
            <div class="mpt" id="divPasswordzhu" style="display:none">
            	<div class="wn_a"></div>
            	<div class="wn_s" id="divPasswordNull" style="display:none">请输入密码！</div>
          		<div class="wn_s" id="divPasswordLength" style="display:none">密码长度为6到20位！</div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
            <label>确认密码&nbsp;<span>*</span></label>
            <input id="passwordes" name="passwordes" type="password" class="int m3"  onfocus="on_focus('passwordes');"/>
            <div class="mpt" id="divPasswordeszhu" style="display:none">
            	<div class="wn_a"></div>
            	<div class="wn_s"  id="divPasswordes" style="display:none" >两次密码输入不一致！</div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
            <label>企业全称&nbsp;<span>*</span></label>
            <input id="companyName" name="webUserInfoDamain.companyName" type="text" class="int m3"  onfocus="on_focus('companyName');"/>
            <div class="mpt" id="divCompanyNamezhu" style="display:none">
            	<div class="wn_a"></div>
            	<div class="wn_s" id="divCompanyName" style="display:none">请输入企业名称！</div>
          		<div class="wn_s" id="divCompanyNameCyi" style="display:none">该企业名称已存在！</div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
            <label>公司地址 &nbsp;<span>*</span></label>
            <input type="text" id="companyPcc"  onfocus="on_focus('companyPcc');" name="webUserInfoDamain.companyPcc" readonly="readonly" style="width:150px;" value="${webUserInfoDamain.companyPcc}" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/>
            <div class="mpt" id="companyPccMpt" style="display:none">
            	<div class="wn_a"></div>
            	<div class="wn_s" id="companyPccNotEmpt" style="display:none">请输入公司地址！</div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
            <label>详细地址 &nbsp;</label>
            <input id="companyAddress" name="webUserInfoDamain.companyAddress" type="text" class="int m3" />
          </li>
          <li>
            <label>联系人 &nbsp;<span>*</span></label>
            <input id="contactName" name="webUserInfoDamain.contactName" onfocus="on_focus('contactName');" type="text" class="int m3" />
            <div class="mpt" id="contactNameMpt" style="display:none">
            	<div class="wn_a"></div>
            	<div class="wn_s" id="contactNameNotEmpt" style="display:none">请输入联系人！</div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
            <label>联系电话 &nbsp;<span>*</span></label>
            <input id="contactTelephone" name="webUserInfoDamain.contactTelephone" onfocus="on_focus('contactTelephone');" type="text" class="int m3" />
             <div class="mpt" id="contactTelephoneMpt" style="display:none">
            	<div class="wn_a"></div>
            	<div class="wn_s" id="contactTelephoneNotEmpt" style="display:none">请输入联系电话！</div>
          		<div class="wn_s" id="contactTelephoneError" style="display:none">请填入正确的联系电话！</div>
                <div class="wn_c"></div>
            </div>
          </li>          
          <li class="ower">
            <label>用户权限&nbsp;</label>
            <input id="deliveryFlag" name="webUserInfoDamain.deliveryFlag" type="checkbox" onclick="getDeliveryFlag()" value="0" />发货确认&nbsp;&nbsp;&nbsp;
            <input id="arrivalSure" name="webUserInfoDamain.arrivalSure" type="checkbox" onclick="getArrivalSure()" value="0" />到货确认&nbsp;&nbsp;&nbsp;
            <input id="receiveSure" name="webUserInfoDamain.receiveSure" type="checkbox" onclick="getReceiveSure()" value="0" />收货确认
          </li>
          <li class="ternow">
            <label></label>
            <input name="" onclick="getAddSubmits();" type="button" value="保存" />
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