<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>快到网-个人中心-订单详情</title>
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/bounced.css" rel="stylesheet" type="text/css" />

<script type="text/javascript"  src="<sys:context/>/resource/js/basis.js"></script>
<script type="text/javascript"  src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
<script type="text/javascript"   src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>

<script type="text/javascript">
$(function(){
	//1，2，3，5步  transactionStep 默认是1-交易确认
   	var transactionStep= $("#transactionStep").val();
   	var receiveSure = $("#receiveSure").val();
  	var promptVal="";
  	$("#promptId").html("");
  	$("#first").show();
  	if(transactionStep=="1"){
    	$("#first").hide();
  	}else{
  		if(${userDriverAssessCount }==1){//在该次交易中货主已给司机评价过了（一次交易只能评价一下）
    		$("#saveEvaluateId").hide();
   			 promptVal="<i>&nbsp;</i>此次交易您给司机的评价如下：";
  		}else{
    		promptVal="<i>&nbsp;</i>货物已经送到了吗？快快给司机的服务进行评价吧！(温馨提示：请您及时给司机做出评价，系统将在30天后默认好评)";
  		}
  	}
  	
 	$("#promptId").html(promptVal);
   	var liArray=$("#duresId ul li");
    var  dtObj=$("#dtId");
    var htmlVal="";
   	if(transactionStep=="3"||transactionStep=="7"){
       	var currentStateVal="";
      	if(transactionStep=="3"){
        	currentStateVal="运输中";
      	}
       	if(transactionStep=="7"){
         	currentStateVal="<tt style=color:red;font-size:14px; >司机已卸货</tt>";
      	}
   		//先清空
    	dtObj.html("");
   		//再赋值
   		htmlVal='<div>';
   		if(receiveSure == "1"){
   			htmlVal +='<a href=javascript:transactionAlertDialogPrompt(${driverUserInfoDomain.transactionStep},"确认货物已送达？",\'<sys:context/>/modifyTransactionStart?transactionInfoDomain.id=${transactionInfo.id}&transactionInfoDomain.deliveryTime=<s:date name="orderCargoInfo.requestStartTime" format="yyyy-MM-dd"/>&transactionInfoDomain.tradeStart=<s:property value="@com.cy.dcts.common.constants.Constants@TRADE_START_SUCCESS_KEY" />&transactionInfoDomain.cargoFlag=<s:property value="@com.cy.dcts.common.constants.Constants@CARGO_FLAG_SUCCESS_KEY"/>\',"${driverUserInfoDomain.baiduChannelId }","${driverUserInfoDomain.baiduUserId }")>确认收货</a>';
   		}
        htmlVal +=	'</div>当前交易状态：'+currentStateVal+
         			'<s:if test="transactionInfo.tradeCancelOriginVal!=''&&transactionInfo.tradeCancelOriginVal!=null">'+
           			'<tt style="color: red;">(${transactionInfo.tradeCancelOriginVal })</tt>'+
           			'</s:if>'+
           			' <a target="_blank" href="<sys:context/>/queryLocationInfo?locationCollectInfoDomain.driverId=${driverUserInfoDomain.id}&locationCollectInfoDomain.tradeId=${transactionInfo.id}&locationCollectInfoDomain.tradeStart=${driverUserInfoDomain.transactionStep}">货物跟踪</a>';;
    	dtObj.html(htmlVal);
    	//步骤条样式
   		document.getElementById("duresId").className="dures dur1";
   		liArray[1].className="fli";
  		// liArray[2].className="fli";
   	}else if(transactionStep=="5"){
   		//先清空
    	dtObj.html("");
   		//再赋值
    	htmlVal='当前交易状态：订单完成'+
                '<s:if test="transactionInfo.tradeCancelOriginVal!=''&&transactionInfo.tradeCancelOriginVal!=null">'+
                '<tt style="color: red;">(${transactionInfo.tradeCancelOriginVal })</tt>'+
                '</s:if>'+
                ' <a target="_blank" href="<sys:context/>/queryLocationInfo?locationCollectInfoDomain.driverId=${driverUserInfoDomain.id}&locationCollectInfoDomain.tradeId=${transactionInfo.id}&locationCollectInfoDomain.tradeStart=${driverUserInfoDomain.transactionStep}">货物跟踪</a>';
    	dtObj.html(htmlVal);
    	//步骤条样式
   		document.getElementById("duresId").className="dures dur2";
    	liArray[1].className="fli";
    	liArray[2].className="fli";
   	}
  });
  
 //链接到我的订单页面
function locationHref(){
	window.location.href=jcontextPath+"/queryTransactionInfo?transactionInfoDomain.menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@MY_ORDER'/>&transactionInfoDomain.cargoId=";
}
</script> 
</head>
<body>
<!-- 保存登录用户对象-js未登录是跳到登录页面 -->
    <!-- <input type="hidden" id="userObj" value="<s:property value="#session.user"/>"/> -->
    <jsp:include page="/head.jsp" /><div class="mian">
     <input type="hidden" id="receiveSure" name="receiveSure" value="${receiveSure}"/>
    <input type="hidden" id="transactionStep" name="driverUserInfoDomain.transactionStep" value="${driverUserInfoDomain.transactionStep}"/>
    <!-- 交易Id （例子取消订单）
    <input type="hidden" id="tranId" value="${transactionInfo.id }"/>-->
<!--个人中心-->
<div class="mian">
	<div class="mian_bor">
	<jsp:include page="/dcts/myCenter/myCenterLeftMenu.jsp" />
    <div class="fr sonafr">
    	<div class="ntica">
    		<h3 class="juh"><i>&nbsp;</i>订单详情</h3>
    		<div class="dures" id="duresId">
		    <ul>
            	<li class="fli">1.交易确认</li>
                <li>2.运输跟踪</li>
                <li>3.订单完成</li>
            </ul>
        	 <!-- <ul >
            	<li class="fli">1.交易确认</li>
                <li>2.待装货</li>
                <li>3.运输跟踪</li>
               <li>4.已送达</li>
                <li>5.订单完成</li>
            </ul> -->
        </div>
        <div class="status">
        	<dl>
            	<dt id="dtId">当前交易状态：等待司机确认
            	<s:if test="transactionInfo.tradeCancelOriginVal!=''&&transactionInfo.tradeCancelOriginVal!=null">
                <br/><tt style="color: red;">(${transactionInfo.tradeCancelOriginVal })</tt>
                </s:if>
            	 <!-- <a href=javascript:cancleTranceDialog(${transactionInfo.id },<s:property value="@com.cy.dcts.common.constants.Constants@CARGO_FLAG_PENDING_TRADE_KEY"/>,<s:property value="@com.cy.dcts.common.constants.Constants@TRADE_START_CLOSE_KEY"/>,'${userDriverAssessCount }','${driverUserInfoDomain.baiduChannelId }','${driverUserInfoDomain.baiduUserId }',locationHref);>取消订单</a></dt> -->
                <dd>1、订单待装货，如果您遇到问题您可以联系我们的客服。</dd>
                <dd >
                <a href="<sys:context/>/queryTransactionInfo?transactionInfoDomain.menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@MY_ORDER'/>&transactionInfoDomain.cargoId=">返回我的订单</a>
                2、如果您感觉司机的服务良好，您可以把司机收藏到常用车辆中。</dd>
            </dl>
        </div>
             <div class="data dataf" >
    	<h3 class="mb10"><i>&nbsp;</i>货源信息</h3>
		  <table border="0" cellpadding="0" cellspacing="0">
            <thead >
              <tr >
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
              <td><s:if test="orderCargoInfo.cargoWeight!=0.0">${orderCargoInfo.cargoWeight }</s:if></td> 
              <td><s:if test="orderCargoInfo.cargoCubage!=0.0">${orderCargoInfo.cargoCubage }</s:if></td>
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
                <td width="117">当前位置</td>
                <td width="80">手机号码</td>
              </tr>
            </thead>
            <tr>
              <td><span class="icon">&nbsp;</span></td>
              <td>${driverUserInfoDomain.name }</td>
              <td>${driverUserInfoDomain.carNumber }</td>
              <td>${driverUserInfoDomain.carTypes }</td>
              <td title="${driverUserInfoDomain.driverLine }">${driverUserInfoDomain.driverLine }</td>
              <td title="${driverUserInfoDomain.lastLocation }"><span class="icon2">&nbsp;</span>${driverUserInfoDomain.lastLocation }</td>
              <td>${driverUserInfoDomain.code }</td>
            </tr>
          </table>      
        </div>
         <!-- 承运商信息 -->
        <s:if test="webUserInfoDamain!=null">
           <div class="mh15"></div>
        <div class="data dataf dabor" >
        	<h3 class="mt12"><i>&nbsp;</i>承运商信息</h3>
          <table border="0" cellpadding="0" cellspacing="0">
           <thead >
              <tr>
              	<td width="31">&nbsp;</td>
                <td width="100">公司名称</td>
                <td width="241">手机号码</td>
                 <td width="100">公司电话</td>
                <td width="100">联系人</td>
                <td width="200">公司地址</td>
              </tr>
            </thead>
            <tr>
              <td><span class="icon">&nbsp;</span></td>
              <td>${webUserInfoDamain.companyName }</td>
              <td>${webUserInfoDamain.mobilephone }</td>
              <td title="${webUserInfoDamain.contactTelephone }">${webUserInfoDamain.contactTelephone }</td>
              <td>${webUserInfoDamain.contactName }</td>
              <td>${webUserInfoDamain.companyAddress }</td>
            </tr>
          </table>
          </div>
        </s:if>
        
        
        
        
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
</body>
</html>
