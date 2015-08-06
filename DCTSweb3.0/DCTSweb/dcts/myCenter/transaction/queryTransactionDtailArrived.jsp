<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>快到网-个人中心-订单详情</title>
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<!-- <link href="<sys:context/>/resource/css/starRating.css" rel="stylesheet" type="text/css" /> -->

<script type="text/javascript"  src="<sys:context/>/resource/js/basis.js"></script>
<script type="text/javascript"  src="<sys:context/>/resource/js/mycenter/transaction/queryTransactionDetail.js"></script>

<script type="text/javascript"  src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
<script type="text/javascript"   src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>
   
<script type="text/javascript">
</script> 
</head>
<body>
<jsp:include page="/head.jsp" /><div class="mian">
<!--个人中心-->
<div class="mian">
	<div class="mian_bor">
	<!-- 左边菜单 -->
	<jsp:include page="/dcts/myCenter/myCenterLeftMenu.jsp" />
    <div class="fr sonafr">
    	<div class="ntica">
            <h3><i>&nbsp;</i>订单详情</h3>
		<div class="duresf sf3">
        	<ul>
            	<li class="fli">1.等待确认</li>
                <li class="fli">2.待装货</li>
                <li class="fli">3.运输中</li>
                <li class="fli">4.已送达</li>
                <li>5.订单完成</li>
            </ul>
        </div>
        <div class="status">
        	<dl>
            	<dt><div><a href="#first">评价</a></div>当前交易状态：已送达 <a target="_blank" href="<sys:context/>/queryLocationInfo?locationCollectInfoDomain.driverId=${driverUserInfoDomain.id}&locationCollectInfoDomain.tradeId=${transactionInfo.id}&locationCollectInfoDomain.tradeStart=${driverUserInfoDomain.transactionStep}">货物跟踪</a></dt>
                <dd>1、订单待装货，如果您遇到问题您可以联系我们的客服。</dd>
                <dd><a style="margin-left:20px;"  id="collectId" href="javascript:collectionDriver('<sys:context/>/addUserDriver',{'driverUserInfoDomain.id':${driverUserInfoDomain.id}},'collectId');">收藏司机&nbsp;&nbsp;</a>
                <a href="<sys:context/>/queryTransactionInfo?transactionInfoDomain.menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@MY_ORDER'/>&transactionInfoDomain.cargoId=">返回我的订单</a>
                2、如果您感觉司机的服务良好，您可以把司机收藏到您。</dd>
            </dl>
        </div>
             <div class="data dataf" >
    	<h3 class="mb10"><i>&nbsp;</i>货源信息</h3>
		  <table border="0" cellpadding="0" cellspacing="0">
            <thead >
              <tr>
                <td width="96">货物名称</td>
                <td width="117">装货时间</td>
                <td width="259">装货地</td>
                <td width="197">卸货地</td>
                <td width="54">重量(吨)</td>
                <td width="67">体积(方)</td>
              </tr>
            </thead>
            <tr>
              <td>${orderCargoInfo.cargoName }</td>
              <td><s:date name="orderCargoInfo.requestStartTime" format="yyyy-MM-dd"/></td>
              <td><div class="ght">${orderCargoInfo.startProvince }-${orderCargoInfo.startCity }
               <br />${orderCargoInfo.startTown }</div></td>
              <td><div class="ght">${orderCargoInfo.endProvince }-${orderCargoInfo.endCity }
               <br />${orderCargoInfo.endTown }</div></td>
              <td>${orderCargoInfo.cargoWeight }</td> 
              <td>${orderCargoInfo.cargoCubage }</td>
            </tr>
          </table>
        </div>
        
                <div class="mh15"></div>
        <div class="data dataf dabor" >
        	<h3 class="mt12"><i>&nbsp;</i>承运人信息</h3>
          <table border="0" cellpadding="0" cellspacing="0">
            <thead >
              <tr>
              	<td width="31">&nbsp;</td>
                <td width="80">司机姓名</td>
                <td width="78">车牌号</td>
                <td width="161">车型</td>
                <td width="241">运营路线</td>
                <td width="97">当前位置</td>
                <td width="92">手机号码</td>
              </tr>
            </thead>
            <tr>
              <td><span class="icon">&nbsp;</span></td>
              <td>${driverUserInfoDomain.name }</td>
              <td>${driverUserInfoDomain.carNumber }</td>
              <td>${driverUserInfoDomain.carTypes }</td>
              <td title="${driverLine }">${driverLine }</td>
              <td><span class="icon2">&nbsp;</span>${lastLocation }</td>
              <td>${driverUserInfoDomain.code }</td>
            </tr>
          </table>
      <p class="coll"><a id="collectionsId" href="javascript:collectionDriver('<sys:context/>/addUserDriver',{'driverUserInfoDomain.id':${driverUserInfoDomain.id}},'collectionsId');">收藏司机</a></p>
        </div>

      <s:form id="mainForm" action="/modifyTransactionStart" namespace="/" method="post">
      <input type="hidden" id="tranId" value="${transactionInfo.id }" name="transactionInfoDomain.id"/>
      <input type="hidden" id="cargoFlag" value="<s:property value='@com.cy.dcts.common.constants.Constants@CARGO_FLAG_SUCCESS_KEY'/>" name="transactionInfoDomain.cargoFlag"/>
      <input type="hidden" id="tradeStart" value="<s:property value='@com.cy.dcts.common.constants.Constants@TRADE_START_SUCCESS_KEY'/>" name="transactionInfoDomain.tradeStart"/>
        <div class="evalua" id="first" name="first">
        	<h3><i>&nbsp;</i>货物已经送到了吗？快快给司机的服务进行评价吧！(温馨提示：请您及时给司机做出评价，系统将在30天后默认好评)</h3>
            <div class="fl flua">
            <ul>
            	<li>
                <table border="0" cellpadding="0" cellspacing="0">
                	<thead>
                	<tr>
                    	<td>好评</td>
                        <td>中评</td>
                        <td>差评</td>
                    </tr>
                    </thead>
                    <tr>
                    	<td><input name="userDriverAssessInfo.tradeEvaluateScore" checked type="radio" value="<s:property value='@com.cy.dcts.common.constants.Constants@EVALUATE_SATISFACTORY_KEY'/>" /><i class="icon7">&nbsp;</i></td>
                        <td><input name="userDriverAssessInfo.tradeEvaluateScore" type="radio" value="<s:property value='@com.cy.dcts.common.constants.Constants@EVALUATE_ARIAL_KEY'/>" /><i class="icon8">&nbsp;</i></td>
                        <td><input name="userDriverAssessInfo.tradeEvaluateScore" type="radio" value="<s:property value='@com.cy.dcts.common.constants.Constants@EVALUATE_NOSATISFACTORY_KEY'/>" /><i class="icon9">&nbsp;</i></td>
                    </tr>  
                </table>
                </li>
                <li><b id="transactionAreaID" style="color: red;"></b></li>
                <li><textarea name="userDriverAssessInfo.assess" onfocus="cleanContext('transactionAreaID')" id="assessId" cols="" rows="">${userDriverAssessInfo.assess }</textarea></li>
                <li class="ountf"><a href="javascript:saveEvaluate();">提交评价</a></li>
            </ul>
            </div>
            <!--start
				# 星级评分
				# star:value = 分数
			--> 
			 <!--
            <div class="shop-rating">
					<span class="title">货物到达速度：</span>
					<ul class="rating-level" id="stars1">
						<li><a class="one-star" star:value="1" href="#">1</a></li>
						<li><a class="two-stars" star:value="2" href="#">2</a></li>
						<li><a class="three-stars" star:value="3" href="#">3</a></li>
						<li><a class="four-stars" star:value="4" href="#">4</a></li>
						<li><a class="five-stars current-rating" star:value="5" href="#">5</a></li>
					</ul>
					<span class="result" id="stars1-tips">5分-完美</span>
					<input type="hidden" id="stars1-input" name="userDriverAssessInfo.arriverEvaluateScore" value="5" size="2" />
			</div>
			
			<div class="shop-rating">
				<span class="title">司机服务态度：</span>
				<ul class="rating-level" id="stars2">
					<li><a class="one-star" star:value="1" href="#">1</a></li>
					<li><a class="two-stars" star:value="2" href="#">2</a></li>
					<li><a class="three-stars" star:value="3" href="#">3</a></li>
					<li><a class="four-stars" star:value="4" href="#">4</a></li>
					<li><a class="five-stars current-rating" star:value="5" href="#">5</a></li>
				</ul>
				<span class="result" id="stars2-tips">5分-完美</span>
				<input type="hidden" id="stars2-input" name="userDriverAssessInfo.serveEvaluateScore" value="5" size="2" />
			</div>--> 
			<!-- end -->
        </div>
	</s:form>

        </div>
    </div>
    </div>
     
      <!-- 合作伙伴 -->
       <div class="both mh"></div>
  <jsp:include page="/cooperativePartner.jsp" />
</div>
</div>
<br />
<br />
<br />
<!--个人中心结束-->
<jsp:include page="/bottom.jsp" />
<!-- <script type="text/javascript"  src="<sys:context/>/resource/js/startRating.js"></script> -->
</body>
</html>
