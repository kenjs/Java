<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>个人中心菜单</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<!-- <link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" /> -->
<link href="<sys:context/>/resource/css/personal.css" rel="stylesheet" type="text/css" />
</head>

<body>
	<div class="fl sonafl ">
        	<ul class="uls">
        	<!--父级的parentid=0, 子级parentId是父级的id -->
        	<s:if test="#session.user.parentId != 0">
	        	<li><h4><span>基本信息</span></h4></li>
	            <li><a id="<s:property value='@com.cy.dcts.common.constants.Constants@ACCOUNT_MANAGEMENT'/>" href="<sys:context/>/accountManagementAction?domain.menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@ACCOUNT_MANAGEMENT'/>"><i class="nox">&nbsp;</i>账户管理</a></li>
	        	<li><h4><span>订单管理</span></h4></li>
	            <li><a id="<s:property value='@com.cy.dcts.common.constants.Constants@MY_ORDER'/>" href="<sys:context/>/queryTransactionInfo?transactionInfoDomain.menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@MY_ORDER'/>&transactionInfoDomain.cargoId="><i>&nbsp;</i>我的订单</a></li>
        	</s:if>
        	<s:else>
	        	<li><h4><span>基本信息</span></h4></li>
	            <li><a id="<s:property value='@com.cy.dcts.common.constants.Constants@ACCOUNT_MANAGEMENT'/>" href="<sys:context/>/accountManagementAction?domain.menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@ACCOUNT_MANAGEMENT'/>"><i class="nox">&nbsp;</i>账户管理</a></li>
	            <li><h4><span>我的认证</span></h4></li>
	            <li><a id="<s:property value='@com.cy.dcts.common.constants.Constants@COMPANY_AUTHENTICATION'/>" href="<sys:context/>/openCompanyAuthentication?menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@COMPANY_AUTHENTICATION'/>"><i class="nox1">&nbsp;</i>企业认证</a></li>
            </s:else>
            <s:if test="#session.user.parentId == 0">
            <li><h4><span>货源管理</span></h4></li>
            <li><a id="<s:property value='@com.cy.dcts.common.constants.Constants@PUBLISH_CARGO'/>" href="<sys:context/>/openAddLocalOrderCargoInfo?orderCargoInfoDomain.menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@PUBLISH_CARGO'/>"><i class="nox2">&nbsp;</i>发布货源</a></li>
            <li><a id="<s:property value='@com.cy.dcts.common.constants.Constants@MY_CARGO'/>" href="<sys:context/>/queryLocalOrderCargoInfo?orderCargoInfoDomain.menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@MY_CARGO'/>"><i class="nox3">&nbsp;</i>我的货源</a></li>
            <li><h4><span>订单管理</span></h4></li>
            <li><a id="<s:property value='@com.cy.dcts.common.constants.Constants@MY_ORDER'/>" href="<sys:context/>/queryTransactionInfo?transactionInfoDomain.menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@MY_ORDER'/>&transactionInfoDomain.cargoId="><i>&nbsp;</i>我的订单</a></li>
            <li><a id="<s:property value='@com.cy.dcts.common.constants.Constants@MY_TRANSACTION'/>" href="<sys:context/>/querySuccessCloseTransactionInfo?transactionInfoDomain.menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@MY_TRANSACTION'/>&transactionInfoDomain.tradeStart="><i class="nox4">&nbsp;</i>交易记录</a></li>
            <li><h4><span>我的客户</span></h4></li>
            <s:if test="#session.user!=null">
            	<s:if test="#session.user.userType==0">
            		<li><a id="<s:property value='@com.cy.dcts.common.constants.Constants@MY_DELIVER_MANAGE'/>" href="<sys:context/>/querySonWebUserInfo?userType=1&menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@MY_DELIVER_MANAGE'/>"><i class="nox9">&nbsp;</i>发货方管理</a></li>
            		<!-- <li><a id="<s:property value='@com.cy.dcts.common.constants.Constants@ADD_MY_DELIVER_MANAGE'/>" href="<sys:context/>/addSonWebUserInfo?userType=1&menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@ADD_MY_DELIVER_MANAGE'/>"><i>&nbsp;</i>新增发货方</a></li> -->
            	</s:if>
            	<s:if test="#session.user.userType==1">
            		<li><a id="<s:property value='@com.cy.dcts.common.constants.Constants@MY_ACCEPT_MANAGE'/>" href="<sys:context/>/querySonWebUserInfo?userType=0&menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@MY_ACCEPT_MANAGE'/>"><i class="nox10">&nbsp;</i>承运方管理</a></li>
            		<!-- <li><a id="<s:property value='@com.cy.dcts.common.constants.Constants@ADD_MY_ACCEPT_MANAGE'/>" href="<sys:context/>/addSonWebUserInfo?userType=0&menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@ADD_MY_ACCEPT_MANAGE'/>"><i>&nbsp;</i>新增承运方</a></li> -->
            	</s:if>
            </s:if>
            <li><a id="<s:property value='@com.cy.dcts.common.constants.Constants@MY_TAKE_DELIVER_MANAGE'/>" href="<sys:context/>/querySonWebUserInfo?userType=2&menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@MY_TAKE_DELIVER_MANAGE'/>"><i class="nox11">&nbsp;</i>收货方管理</a></li>
            <!-- <li><a id="<s:property value='@com.cy.dcts.common.constants.Constants@ADD_MY_TAKE_DELIVER_MANAGE'/>" href="<sys:context/>/addSonWebUserInfo?userType=2&menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@ADD_MY_TAKE_DELIVER_MANAGE'/>"><i class="nox4">&nbsp;</i>新增收货方</a></li> -->
            <li><h4><span>企业服务</span></h4></li>
            <li><a id="<s:property value='@com.cy.dcts.common.constants.Constants@IDENTITY_CARD_QUERY'/>" href="<sys:context/>/openIdetityVerifyPage?menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@IDENTITY_CARD_QUERY'/>"><i class="nox5">&nbsp;</i>身份证查询</a></li>
            <li ><a id="<s:property value='@com.cy.dcts.common.constants.Constants@COMMON_CAR'/>" href="<sys:context/>/queryLocalDriverUserCarInfo?driverUserInfoDomain.menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@COMMON_CAR'/>"><i class="nox6">&nbsp;</i>常用车辆</a></li>
             <li><h4><span>我的信誉</span></h4></li>
            <li><a id="<s:property value='@com.cy.dcts.common.constants.Constants@EVALUATE_MANAGEMENT'/>" href="<sys:context/>/loadDriverUserEvaluation?menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@EVALUATE_MANAGEMENT'/>"><i class="nox7">&nbsp;</i>评价管理</a></li>
            <s:if test="#session.user!=null">
              <s:if test="#session.user.pactCarDriverFlag==1">
               <li><h4><span>合同管理</span></h4></li>
               <li><a id="<s:property value='@com.cy.dcts.common.constants.Constants@PACT_DRIVER_MANAGEMENT'/>" href="<sys:context/>/queryPactDriveInfo?menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@PACT_DRIVER_MANAGEMENT'/>"><i class="nox8">&nbsp;</i>合同司机</a></li>
              </s:if>
            </s:if>
            </s:if>
        </ul>
    </div>
    
    <script type="text/javascript">
   $(document).ready(function(){
      //给导航栏标题加底色
	 	$(".nav ul li:eq(3)").css('background','url(resource/image/index/rl.jpg) repeat-x');
	
	});
  </script>
</body>
</html>
