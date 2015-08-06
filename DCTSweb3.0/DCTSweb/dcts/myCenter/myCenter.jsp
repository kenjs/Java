<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
    <link href="<sys:context/>/resource/css/bounced.css" rel="stylesheet" type="text/css" />
    
    <script type="text/javascript" src="<sys:context/>/resource/js/pageInfo.js"></script>
    <script type="text/javascript" src="<sys:context/>/resource/js/basis.js"></script>
	<script type="text/javascript" src="<sys:context/>/resource/js/mycenter/transaction/transaction.js"></script>
   
	<script type="text/javascript"  src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
	<script type="text/javascript"   src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>
	
	<title>快到网-个人中心-首页</title>
	<script type="text/javascript">
   $(function(){
   var totalPages = ${transactionInfoDomain.pageInfo.totalPages};//总页数
		var curPageNos = ${transactionInfoDomain.pageInfo.curPageNo};//当前页数
		var pageSize = ${transactionInfoDomain.pageInfo.pageSize};//每页显示数据
		var totalRecords = ${transactionInfoDomain.pageInfo.totalRecords};//总记录数
		pageInfo(totalPages,curPageNos,totalRecords);
		webUserAppraiseCount();
		myCenterTradePromptInfo();
   });

   </script>
</head>
<body>
<!-- 头部开始 -->
<jsp:include page="/head.jsp" />
<!-- 头部结束 -->
<!--个人中心-->
<div class="mian">
	<div class="mian_bor">
	<jsp:include page="/dcts/myCenter/myCenterLeftMenu.jsp" />
    <div class="fr sonafr">
    	<div class="head_top">
    		<dl>
        		<dt><a href="###"><img src="<sys:context/>/resource/image/index/tx.png" width="100" height="100" /></a></dt>
            	<dd>
            		<h3 id="webUserNameId"><!--  物流公司名称 --></h3>
            		<p><a href="<sys:context/>/openUpdatePwdView">密码修改</a></p>
            		<!-- 司机对物流公司的评价 -->
            		<div class="numder scnt" id="webUserAppraiseCountId">
            		</div>
            		<div class="clmt">
            			手机认证：<i class="aut">&nbsp;</i>
            			<s:if test="webUserInfo.enterpriseFlag==@com.cy.dcts.common.constants.Constants@ENTERPRISE_FLAG_END">
            				企业认证：<i class="aut6">&nbsp;</i>
            			</s:if><s:else>
              				企业认证：<i class="aut7">&nbsp;</i>
            			</s:else>
            			<!-- <a href="###">缴纳认证：<i class="aut2">&nbsp;</i></a> -->
            		</div>
              		<!--<div class="clmt">
              			<a href="###">手机认证：<i class="aut3">&nbsp;</i></a>
              			<a href="###">实名认证：<i class="aut4">&nbsp;</i></a>
              			<a href="###">企业认证：<i class="aut7">&nbsp;</i></a>
              			<a href="###">缴纳认证：<i class="aut5">&nbsp;</i></a></div>-->
            	</dd>
        	</dl>
        </div>
   <s:if test="#session.user.parentId == 0">
        <h3 class="titl_h3"><i>&nbsp;</i>待处理订单</h3>
    <div class="con_title">
      <ul id="trandePromptId">
      	<!-- <li>交易提醒：</li>   
        <li><a href="<sys:context/>/querySuccessCloseTransactionInfo?transactionInfoDomain.tradeStart=<s:property value='@com.cy.dcts.common.constants.Constants@TRADE_START_WAITING_DRIVER_CONFIRM_KEY'/>&transactionInfoDomain.menuAId=">待确认订单<span class="dius">${transactionInfoDomain.waitingDriverTrade }</span></a></li>
        <li><a href="<sys:context/>/querySuccessCloseTransactionInfo?transactionInfoDomain.tradeStart=<s:property value='@com.cy.dcts.common.constants.Constants@TRADE_START_IN_TRANSIT_KEY'/>&transactionInfoDomain.menuAId=">待确认收货<span class="dius">${transactionInfoDomain.waitingReceivingTrade }</span></a></li>
        <li><a href="<sys:context/>/queryTransactionInfo?transactionInfoDomain.menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@MY_ORDER'/>&transactionInfoDomain.tradeStart=8&transactionInfoDomain.cargoId=">待评价订单<span class="dius">${transactionInfoDomain.successNoAssessTrade }</span></a></li>
         -->
      </ul>
    </div>
    <s:form id="mainForm" action="/querySuccessCloseTransactionInfo" namespace="/" method="post">
       <input type="hidden" name="transactionInfoDomain.tradeStart" value="${transactionInfoDomain.tradeStart }"/>
       <div class="data dataf" >
       <table border="0" cellpadding="0" cellspacing="0">
       <thead>
              <tr>
              	<td width="84">货物名称</td>
                <td width="90">装货时间</td>
                <td width="121">装货地</td>
                <td width="157">卸货地</td>
                <td width="104">车牌号</td>
                <td width="120">交易状态</td>
                <td>交易操作</td>
              </tr>
        </thead>
       </table>
       <s:if test="transactionInfoDomain.list.size>0">
         <s:iterator value="transactionInfoDomain.list">
              <div class="usert">
         <table border="0" cellpadding="0" cellspacing="0" >
       		<thead>
              <tr>
              <td colspan="4">订单编号：${orderNumber } &nbsp;&nbsp; 时间：${createTime } &nbsp;&nbsp; <i class="icon">&nbsp;</i>${name }   ${code }</td>
                <td colspan="2">
                  <s:if test="deliveryTime!=null&&deliveryTime!=''">
                                                      发货：${deliveryTime }    
                  </s:if>
                   <s:if test="arrivalTime!=null&&arrivalTime!=''">
                   	 &nbsp; 到货：${arrivalTime }    
                  </s:if>
                </td>
                <td width="140px"><s:if test="receiveTime!=null&&receiveTime!=''">
                     &nbsp; 收货：${receiveTime }
                  </s:if></td>
              </tr> 
            
            <!-- 收货方Id不为空（是导入的订单就加一行展示导入的信息） -->
            <s:if test="receiverCodeId!=null&&receiverCodeId!=''">
               <tr>  
                 <td colspan="2">发货方：${shipperComName }</td>
                 <td colspan="2">承运商：${logisticsComName }</td>
                 <td>收货方：${receiverComName }</td>
                 <td>发货单号：${shipperOrderNo }</td> 
                 <td>收货单号：${receiverOrderNo }</td>
               </tr>
            </s:if>
            </thead>
              	<td >${cargoName }</td>
                <td >${requestStartTime }</td>
                <td  title="${startProCityCounty }">${startProCityCounty }</td>
                <td title="${endProCityCounty }">${endProCityCounty }</td>
                <td >${carNumber }</td>
                <td >
                <div class="netd">
                ${tradeStartValue }
                <s:if test="tradeCancelOriginVal!=''&&tradeCancelOriginVal!=null">
	                <br/><tt style="color: red;">(${tradeCancelOriginVal })</tt>
                </s:if>
                <br/>
                <!-- 除开交易取消及无效订单(导入的订单没有司机号码的)外都可以查看订单详情 -->
                <s:if test="tradeStart!=6&&tradeStart!=0">
                  <a href="<sys:context/>/queryTransactionDetail?transactionInfo.id=${id }&driverUserInfoDomain.transactionStep=${tradeStart }">订单详情</a>
                </s:if>
                <!-- 只能修改自己导入(登录的用户是注册的用户)司机为确定承运的订单
                <s:if test="#session.user!=null&&#session.user.parentId==0&&receiverCodeId!=null&&receiverCodeId!=''&&(deliveryTime==null||deliveryTime=='')">
                  <a href="<sys:context/>/openModifyImportTransactionInfo?transactionInfoDomain.id=${id }">修改</a>
                </s:if> -->
                </div>
                </td>
                <td><div class="netd">
                
                 <!-- 只有导入的会没有司机号码（订单状态为0）-->
                 <s:if test="tradeStart==0">
                   <!-- 只能修改自己导入(登录的用户是注册的用户)司机为确定承运的订单-->
                <s:if test="#session.user!=null&&#session.user.parentId==0">
                  <a href="<sys:context/>/openModifyImportTransactionInfo?transactionInfoDomain.id=${id }">修改</a>
                </s:if>
                </s:if>
                <!-- 等待司机确认1 提醒司机确认 -->
                <s:elseif test="tradeStart==1"> 
                <a href="javascript:baiduPushMessagesToDriver('${baiduChannelId}','${baiduUserId}',successPromptDriverInfo);">提醒司机确认</a>
                <br />
                <a href="javascript:cancleTranceDialog(${id },<s:property value="@com.cy.dcts.common.constants.Constants@CARGO_FLAG_PENDING_TRADE_KEY"/>,<s:property value="@com.cy.dcts.common.constants.Constants@TRADE_START_CLOSE_KEY"/>,${userDriverAssessCount },'${baiduChannelId }','${baiduUserId }',reload);">取消订单</a>
                 <!-- 只能修改自己导入(登录的用户是注册的用户)司机为确定承运的订单-->
                <s:if test="#session.user!=null&&#session.user.parentId==0&&receiverCodeId!=null&&receiverCodeId!=''">
                  /<a href="<sys:context/>/openModifyImportTransactionInfo?transactionInfoDomain.id=${id }">修改</a>
                </s:if>
                </s:elseif>
               
               <!-- <s:elseif test="tradeStart==2">
                <a href="<sys:context/>/queryTransactionDetail?transactionInfo.id=${id }&driverUserInfoDomain.transactionStep=${tradeStart }">确认装货</a>/<a target="_blank" href="<sys:context/>/queryLocationInfo?locationCollectInfoDomain.driverId=${driverId}&locationCollectInfoDomain.tradeId=${id}&locationCollectInfoDomain.tradeStart=${tradeStart}">货物跟踪</a><br />
                <a href="javascript:cancleTranceDialog(${id },<s:property value="@com.cy.dcts.common.constants.Constants@CARGO_FLAG_PENDING_TRADE_KEY"/>,<s:property value="@com.cy.dcts.common.constants.Constants@TRADE_START_CLOSE_KEY"/>,${userDriverAssessCount },${baiduChannelId },${baiduUserId });">取消订单</a></s:elseif> --> <!-- 待装货时货主取消订单是爽约 -->
                
                <s:elseif test="tradeStart==3||tradeStart==7">
                <!-- 有权限且 发货时间为空（即未发货确认）时：就显示发货确认按钮 -->
                <s:if test="#session.user!=null&&#session.user.deliveryFlag==1&&(deliveryTime==null||deliveryTime=='')">
                <a id="delivery${id }" href="javascript:modifyTimeHandle('${id }','deliveryTime','delivery','${requestStartTime }');">发货确认</a>/</s:if>
                <!-- 有权限且到货时间为空（即未到货确认）时：就显示到货确认按钮 -->
                <s:elseif test="#session.user!=null&&#session.user.arrivalSure==1&&(arrivalTime==null||arrivalTime=='')"><a id="arrival${id }" href="javascript:modifyTimeHandle('${id }','arrivalTime','arrival','');">到货确认</a>/</s:elseif>
                <!-- 有权限(收货确认后就订单完成了) -->
                <s:if test="#session.user.receiveSure==1">
                <a href="<sys:context/>/queryTransactionDetail?transactionInfo.id=${id }&driverUserInfoDomain.transactionStep=${tradeStart }">收货确认</a>
                </s:if>
                <s:if test="userDriverAssessCount==0">
                      /<a href="javascript:userDriverAssessInfo(${id },${driverId },${cargoId });">评价</a> 
                </s:if><br />
                <a target="_blank" href="<sys:context/>/queryLocationInfo?locationCollectInfoDomain.driverId=${driverId}&locationCollectInfoDomain.tradeId=${id}&locationCollectInfoDomain.tradeStart=${tradeStart}">货物跟踪</a>/
                <a href="javascript:cancleTranceDialog(${id },<s:property value="@com.cy.dcts.common.constants.Constants@CARGO_FLAG_PENDING_TRADE_KEY"/>,<s:property value="@com.cy.dcts.common.constants.Constants@TRADE_START_CLOSE_KEY"/>,${userDriverAssessCount },'${baiduChannelId }','${baiduUserId }',reload);">取消订单</a>
                <!-- 导入（收货方的Id不为空）的订单在运输跟踪状态下的可换车(只能由物流企业操作)-->
               <s:if test="#session.user!=null&&#session.user.userType==0&&receiverCodeId!=null&&receiverCodeId!=''">
               /<a id="${id }cd" href='javascript:confirmDrivers(${id },<s:property value="@com.cy.dcts.common.constants.Constants@TRADE_START_WAITING_DRIVER_CONFIRM_KEY"/>);'>换车</a></s:if></s:elseif>
                
                <!-- <s:elseif test="tradeStart==4"><a href="<sys:context/>/queryTransactionDetail?transactionInfo.id=${id }&driverUserInfoDomain.transactionStep=${tradeStart }">评价</a><br />
                <a target="_blank" href="<sys:context/>/queryLocationInfo?locationCollectInfoDomain.driverId=${driverId}&locationCollectInfoDomain.tradeId=${id}&locationCollectInfoDomain.tradeStart=${tradeStart}">货物跟踪</a></s:elseif> -->
               
                </div></td>
              </tr>
       </table>
   </div>
         </s:iterator>
       </s:if>
      
   <!-- 分页 -->
         <s:if test="transactionInfoDomain.list.size()>0">
			<div class="numberBox" id="pageInfoHtmlId">  
			</div>
			</s:if>
			<input type='hidden' id='curPage' name='transactionInfoDomain.pageInfo.curPage' value='${transactionInfoDomain.pageInfo.curPage}'/><!-- 需要显示页面 -->
			<input type='hidden' id='curPageNo' name='transactionInfoDomain.pageInfo.curPageNo' value='${transactionInfoDomain.pageInfo.curPageNo}'/><!-- 当前页面 -->
			<input type='hidden' id='transactionInfoDomain.pageInfo.pageSize' name='transactionInfoDomain.pageInfo.pageSize' value='${transactionInfoDomain.pageInfo.pageSize}'/><!-- 当前页面 -->
			   
      </div>
     </s:form>
</s:if>
<s:else>
	<div class="con_title" style="margin-top:10px;margin-bottom:50%;"></div>
</s:else>        
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
<script type="text/javascript">
   $(document).ready(function(){
     if('${signType}'=='cargoFllow'){
	 	$(".nav ul li:eq(3)").removeAttr("style");
	 	$(".nav ul li:eq(4)").css('background','url(resource/image/index/rl.jpg) repeat-x');
	 	}
	
	});
</script>
</body>
</html>
