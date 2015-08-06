<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>快到网-个人中心-我的订单</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/threeLinkage/css/cityLayout.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/bounced.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<sys:context/>/resource/js/pageInfoThree.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/mycenter/myCenterLeftMenu.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/basis.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/mycenter/transaction/transaction.js"></script>

<script type="text/javascript"  src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
<script type="text/javascript"   src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>
<style type="text/css">
.city_input { border:1px solid #d6d6d6; width:185px; height:30px;
 background:url(<sys:context/>/resource/threeLinkage/images/ts-indexcity.png) no-repeat; 
 line-height:30px; margin-top:5px;  text-indent:5px;}
 
</style>

<script type="text/javascript">
   $(function(){
   var totalPages = ${transactionInfoDomain.pageInfo.totalPages};//总页数
		var curPageNos = ${transactionInfoDomain.pageInfo.curPageNo};//当前页数
		var pageSize = ${transactionInfoDomain.pageInfo.pageSize};//每页显示数据
		var totalRecords = ${transactionInfoDomain.pageInfo.totalRecords};//总记录数
		pageInfo(totalPages,curPageNos,totalRecords);
   });
   
   //根据条件查询表单的提交
   function getSubmit(flag){
      if($("#cargoNameId").val()=="请输入货物名称"){
        $("#cargoNameId").val("");
      }
      // 区分点击的是搜索按钮（初始化从第一条开始查）还是分页链接 
      $("#flagId").val(flag);
      
      var serarchStartProCityCo=$("#serarchStartProCityCounty").val().split("-");
      var serarchEndProCityCo=$("#serarchEndProCityCounty").val().split("-");
      provinceCityCountyFormat(serarchStartProCityCo,"startProCityCounty");
      provinceCityCountyFormat(serarchEndProCityCo,"endProCityCounty");
      //点击我的货源——'查看订单'跳到——我的订单本页面（传过来cargoId），目的点搜索得清空cargoId 
      document.getElementById('mainForm').action="<sys:context/>/queryTransactionInfo?transactionInfoDomain.cargoId=";
      document.getElementById('mainForm').submit();
   }
  
  
  //删除订单
  function deleteTransaction(id,okFunc){
   var dialog = art.dialog({
    content:'确认删除该交易记录吗？',
    lock:true,
    fixed: true,
    id: 'Fm2',
    icon: 'question',
    ok: function () {
    	execDatabaseInteractionHandle(jcontextPath+'/modifyTransactionInfoOrderStart',{'transactionInfoDomain.id':id},okFunc);
    },
    cancel: true
  });
  }
 
</script>
</head>
  
<body>
  
<jsp:include page="/head.jsp" />
<div class="mian">
	<div class="mian_bor">
	<jsp:include page="/dcts/myCenter/myCenterLeftMenu.jsp" />
    <s:form id="mainForm" action="/queryTransactionInfo" namespace="/" method="post">
    <!-- 当前左边菜单链接Id -->
     <input type="hidden" id="menuAIdHi" value="${transactionInfoDomain.menuAId }" name="transactionInfoDomain.menuAId"/>
     <!-- 区分点击的是搜索按钮（初始化从第一条开始查）还是分页链接 -->
	<input type="hidden" id="flagId" value="" name="flag"/>
	
     <input type="hidden" id="startProCityCounty" name="transactionInfoDomain.startProCityCounty" value=""/>
     <input type="hidden" id="endProCityCounty" name="transactionInfoDomain.endProCityCounty" value=""/>
    <div class="fr sonafr">
    	<div class="funtop">
    	<h3><i>&nbsp;</i>我的订单</h3>
        <ul>
        	<li class="w396"><label>货物名称：</label><s:select list="dataDictInfoDomain.cargoTypeData"  listKey="code" listValue="name" cssClass="mw85" name="transactionInfoDomain.cargoType" />
        	<input name="transactionInfoDomain.cargoName" id="cargoNameId" tipMsg="请输入货物名称" value="${transactionInfoDomain.cargoName }" type="text" class="intu" /></li>
            <li><label>装货时间：</label><input  name="transactionInfoDomain.requestStartTime" value="${transactionInfoDomain.requestStartTime }" id="requestStartTime" readonly="readonly" onClick="WdatePicker()" class="intu"/>
            </li>
            <li class="w396"><label>装货地：</label><input name="transactionInfoDomain.serarchStartProCityCounty" readonly="readonly" value="${transactionInfoDomain.serarchStartProCityCounty }" id="serarchStartProCityCounty"  type="text"  class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/>
            </li>
           <!-- 20140702 --> 
           <li><label>卸货地：</label><input name="transactionInfoDomain.serarchEndProCityCounty" readonly="readonly" value="${transactionInfoDomain.serarchEndProCityCounty }" id="serarchEndProCityCounty"  type="text" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/>
           </li>
            <li class="w396"><label>司机姓名：</label><input name="transactionInfoDomain.name" value="${transactionInfoDomain.name }" type="text" class="intu" /></li>
            <li><label>司机手机号：</label><input name="transactionInfoDomain.code" type="text" value="${transactionInfoDomain.code }" class="intu" /></li>
            <li class="w396"><label>车牌号：</label><input name="transactionInfoDomain.carNumber" value="${transactionInfoDomain.carNumber }" type="text" class="intu" /></li>
            <li><a href="javascript:getSubmit('0')" class="sout">搜索</a><label>订单编号：</label><input name="transactionInfoDomain.orderNumber" value="${transactionInfoDomain.orderNumber }" type="text" class="intu" 

/></li>
           
        </ul>
        </div>
    <div class="data dataf" >
       <table border="0" cellpadding="0" cellspacing="0">
       <thead>
              <tr>
              	<td width="105">货物名称</td>
                <td width="105">装货时间</td>
                <td width="150" >装货地</td>
                <td width="105">卸货地</td>
                <td width="105">车牌号</td>
                <td width="105"><s:select list="dataDictInfoDomain.tradeStartData" id="tradeStart" listKey="code" listValue="name" name="transactionInfoDomain.tradeStart" onchange="getSubmit('0');" style="width:105px;" cssClass="slect"></s:select></td>
                <!-- <td width="100">交易取消来源</td> -->
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
              <td colspan="7">订单编号：${orderNumber } &nbsp;&nbsp; 时间：${createTime } &nbsp;&nbsp; <i class="icon">&nbsp;</i>${name }   ${code }</td>
              	<!-- <td colspan="2">订单编号：${orderNumber }</td>
                <td width="150" title="${createTime }">时间：${createTime }</td><!-- 交易表中的创建时间吗  -->
                <!-- <td colspan="2"><i class="icon">&nbsp;</i>${name }   ${code }</td> -->
                <!-- <td colspan="2">
                  <s:if test="deliveryTime!=null&&deliveryTime!=''">
                                                      发货：${deliveryTime }    
                  </s:if>
                   <s:if test="arrivalTime!=null&&arrivalTime!=''">
                   	 &nbsp; 到货：${arrivalTime }    
                  </s:if>
                </td>
                <td width="140px"><s:if test="receiveTime!=null&&receiveTime!=''">
                     &nbsp; 收货：${receiveTime }
                  </s:if>
               </td>
                -->
              </tr>
              <tr> 
              <td colspan="2">预计到达时间：</td>
              	<!-- <td colspan="2">订单编号：${orderNumber }</td>
                <td width="150" title="${createTime }">时间：${createTime }</td><!-- 交易表中的创建时间吗  -->
                <!-- <td colspan="2"><i class="icon">&nbsp;</i>${name }   ${code }</td> -->
                <td colspan="3">
                  <s:if test="deliveryTime!=null&&deliveryTime!=''">
                                                      发货：${deliveryTime }    
                  </s:if>
                   <s:if test="arrivalTime!=null&&arrivalTime!=''">
                   	 &nbsp; 到货：${arrivalTime }    
                  </s:if>
                </td>
                <td colspan="2" width="140px">
                  <s:if test="receiveTime!=null&&receiveTime!=''">
                     &nbsp; 收货：${receiveTime }
                  </s:if>
               </td>
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
              <tr valign="top">
              	<td width="120" >${cargoName }</td>
                <td width="90">${requestStartTime }</td>
                <td  title="${startProCityCounty }">${startProCityCounty }</td>
                <td  title="${endProCityCounty }">${endProCityCounty }</td>
                <td width="70">${carNumber }</td>
                <td width="120" style="overflow:visible">
                <div class="netd">${tradeStartValue }
                <s:if test="tradeCancelOriginVal!=''&&tradeCancelOriginVal!=null">
                <br/><tt style="color: red;">(${tradeCancelOriginVal })</tt>
                  </s:if>
                <br/>
                
                <!-- 除开货主取消交易及无效订单(导入的订单没有司机号码的)外（司机取消了货主仍然可以操作）都可以查看订单详情 -->
                <s:if test="tradeStart!=6&&tradeStart!=0">
                	<s:if test="#session.user.parentId == 0">
                  	<a href="<sys:context/>/queryTransactionDetail?transactionInfo.id=${id }&driverUserInfoDomain.transactionStep=${tradeStart }">订单详情</a>
                	</s:if>
                </s:if>
                
                </div>
                </td>
                <!-- <td>${tradeCancelOriginVal }</td>交易取消来源 -->
                  
                <td><div class="netd">
                <s:if test="#session.user.parentId == 0">
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
               
               <!--  <s:elseif test="tradeStart==2">
                <a href="<sys:context/>/queryTransactionDetail?transactionInfo.id=${id }&driverUserInfoDomain.transactionStep=${tradeStart }">确认装货</a>/
                <a target="_blank" href="<sys:context/>/queryLocationInfo?locationCollectInfoDomain.driverId=${driverId}&locationCollectInfoDomain.tradeId=${id}&locationCollectInfoDomain.tradeStart=${tradeStart}">货物跟踪</a>
                <br />
                <a href="javascript:cancleTranceDialog(${id },<s:property value="@com.cy.dcts.common.constants.Constants@CARGO_FLAG_PENDING_TRADE_KEY"/>,<s:property value="@com.cy.dcts.common.constants.Constants@TRADE_START_CLOSE_KEY"/>,reload);">取消订单</a></s:elseif> --><!-- 待装货时货主取消订单是爽约 -->
               
                <s:elseif test="tradeStart==3||tradeStart==7">
                <!-- 有权限且 发货时间为空（即未发货确认）时：就显示发货确认按钮 -->
                <s:if test="#session.user!=null&&#session.user.deliveryFlag==1&&(deliveryTime==null||deliveryTime=='')">
                <a id="delivery${id }" href="javascript:modifyTimeHandle('${id }','deliveryTime','delivery','${requestStartTime }');">发货确认</a>/</s:if>
                <!-- 有权限且到货时间为空（即未到货确认）时：就显示到货确认按钮 -->
                <s:elseif test="#session.user!=null&&#session.user.arrivalSure==1&&(arrivalTime==null||arrivalTime=='')"><a id="arrival${id }" href="javascript:modifyTimeHandle('${id }','arrivalTime','arrival','');">到货确认</a>/</s:elseif>
                <!-- 有权限(收货确认后就订单完成了) -->
                <s:if test="#session.user!=null&&#session.user.receiveSure==1">
                <a href="<sys:context/>/queryTransactionDetail?transactionInfo.id=${id }&driverUserInfoDomain.transactionStep=${tradeStart }">收货确认</a>
                </s:if>
                <!-- 货主对司机未评价过时就显示评价操作（一条订单只能评价一次） -->
                <s:if test="userDriverAssessCount==0">
                      /<a href="javascript:userDriverAssessInfo(${id },${driverId },${cargoId });">评价</a> 
                </s:if><br />
                
                <a target="_blank" href="<sys:context/>/queryLocationInfo?locationCollectInfoDomain.driverId=${driverId}&locationCollectInfoDomain.tradeId=${id}&locationCollectInfoDomain.tradeStart=${tradeStart}">货物跟踪</a>
                /<a href="javascript:cancleTranceDialog(${id },<s:property value="@com.cy.dcts.common.constants.Constants@CARGO_FLAG_PENDING_TRADE_KEY"/>,<s:property value="@com.cy.dcts.common.constants.Constants@TRADE_START_CLOSE_KEY"/>,${userDriverAssessCount },'${baiduChannelId }','${baiduUserId }',reload);">取消订单</a>
                <!-- 导入（收货方的Id不为空）的订单在运输跟踪状态下的可换车(只能由物流企业操作)-->
                <s:if test="#session.user!=null&&#session.user.userType==0&&receiverCodeId!=null&&receiverCodeId!=''">
               /<a id="${id }cd" href='javascript:confirmDrivers(${id },<s:property value="@com.cy.dcts.common.constants.Constants@TRADE_START_WAITING_DRIVER_CONFIRM_KEY"/>);'>换车</a></s:if></s:elseif>
                
                
                
                <!-- <s:elseif test="tradeStart==4"><a href="<sys:context/>/queryTransactionDetail?transactionInfo.id=${id }&driverUserInfoDomain.transactionStep=${tradeStart }">评价</a><br />
                <a target="_blank" href="<sys:context/>/queryLocationInfo?locationCollectInfoDomain.driverId=${driverId}&locationCollectInfoDomain.tradeId=${id}&locationCollectInfoDomain.tradeStart=${tradeStart}">货物跟踪</a></s:elseif> -->
                
                <s:elseif test="tradeStart==5">
                <a target="_blank" href="<sys:context/>/queryLocationInfo?locationCollectInfoDomain.driverId=${driverId}&locationCollectInfoDomain.tradeId=${id}&locationCollectInfoDomain.tradeStart=${tradeStart}">货物跟踪</a>
                 <s:if test="userDriverAssessCount==0">
                      /<a href="javascript:userDriverAssessInfo(${id },${driverId },${cargoId });">评价</a> 
                </s:if><br />
                <a href="javascript:deleteTransaction(${id },reload)">删除</a></s:elseif>
                <s:elseif test="tradeStart==6"><a href="javascript:deleteTransaction(${id },reload)">删除</a></s:elseif>
                </s:if>
                <s:else>
                	
                	<!-- 有权限且 发货时间为空（即未发货确认）时：就显示发货确认按钮 -->
	                <s:if test="#session.user!=null&&#session.user.deliveryFlag==1&&(deliveryTime==null||deliveryTime=='')">
	                	<a id="delivery${id }" href="javascript:modifyTimeHandle('${id }','deliveryTime','delivery','${requestStartTime }');">发货确认</a></s:if>
	                <!-- 有权限且到货时间为空（即未到货确认）时：就显示到货确认按钮 -->
	                <s:elseif test="#session.user!=null&&#session.user.arrivalSure==1&&(arrivalTime==null||arrivalTime=='')&&(deliveryTime!=null&&arrivalTime!='')">
	                	<a id="arrival${id }" href="javascript:modifyTimeHandle('${id }','arrivalTime','arrival','');">到货确认</a>
	                </s:elseif>
	                <!-- 有权限(收货确认后就订单完成了) -->
	                <s:if test="#session.user!=null&&#session.user.receiveSure==1&&(arrivalTime!=null&&arrivalTime!='')">
	                	<a href="<sys:context/>/queryTransactionDetail?transactionInfo.id=${id }&driverUserInfoDomain.transactionStep=${tradeStart }">收货确认</a>
	                </s:if>
                	<br/>
                	<a href="<sys:context/>/queryTransactionDetail?transactionInfo.id=${id }&driverUserInfoDomain.transactionStep=${tradeStart }">订单详情</a>
                </s:else>
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
