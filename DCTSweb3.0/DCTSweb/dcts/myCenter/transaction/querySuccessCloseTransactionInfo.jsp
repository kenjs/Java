<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>快到网-个人中心-交易记录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/bounced.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<sys:context/>/resource/js/pageInfoThree.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/mycenter/myCenterLeftMenu.js"></script>

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
      // 区分点击的是搜索按钮（初始化从第一条开始查）还是分页链接
      $("#flagId").val(flag);
      document.getElementById('mainForm').submit();
   }
</script>
</head>
  
<body>
  
<jsp:include page="/head.jsp" />
<div class="mian">
<input type="hidden" id="menuAIdHi" value="${transactionInfoDomain.menuAId }" name="transactionInfoDomain.menuAId"/>
	<div class="mian_bor">
	<jsp:include page="/dcts/myCenter/myCenterLeftMenu.jsp" />
    <s:form id="mainForm" action="/querySuccessCloseTransactionInfo?transactionInfoDomain.menuAId=a_id_6" namespace="/" method="post">
    <!-- 区分点击的是搜索按钮（初始化从第一条开始查）还是分页链接 -->
	<input type="hidden" id="flagId" value="" name="flag"/>
    <div class="fr sonafr">
    	<div class="funtop">
    	<h3><i>&nbsp;</i>交易记录</h3>
        <ul>
           <li class="w396"><label>装货时间：</label>
	        	<input name="transactionInfoDomain.startTime" class="inut" value="${transactionInfoDomain.startTime }" readonly="readonly" onClick="WdatePicker()" type="text" />
	                至&nbsp&nbsp<input name="transactionInfoDomain.endTime" class="inut" value="${transactionInfoDomain.endTime }" readonly="readonly" onClick="WdatePicker()" type="text" />
        	</li>
        	<li><a href="javascript:getSubmit()" class="sout">搜索</a><label>交易状态：</label>
            <select name="transactionInfoDomain.tradeStart"  id="tradeStart">
                <option  value=''>请选择</option>
                <option  <s:if test="transactionInfoDomain.tradeStart==5">selected="selected"</s:if> value='<s:property value="@com.cy.dcts.common.constants.Constants@TRADE_START_SUCCESS_KEY"/>'><s:property value="@com.cy.dcts.common.constants.Constants@TRADE_START_SUCCESS_VALUE"/></option>
                <option <s:if test="transactionInfoDomain.tradeStart==6">selected="selected"</s:if> value='<s:property value="@com.cy.dcts.common.constants.Constants@TRADE_START_CLOSE_KEY"/>'><s:property value="@com.cy.dcts.common.constants.Constants@TRADE_START_CLOSE_VALUE"/></option>
              </select> 
            </li>
          </ul>
        </div>
    <div class="data dataf" >
       <table border="0" cellpadding="0" cellspacing="0">
       <thead>
              <tr>
              	<td width="92">货物名称</td>
                <td width="92" >装货时间</td>
                <td width="170">装货地</td>
                <td width="150">卸货地</td>
                <td width="92">车牌号</td>
                <td width="190">交易状态</td>
                <td></td>
              </tr>
        </thead>
       </table>
       <s:if test="transactionInfoDomain.list.size>0">
         <s:iterator value="transactionInfoDomain.list">
              <div class="usert">
         <table border="0" cellpadding="0" cellspacing="0" >
       		<thead>
              <tr>
              	<td colspan="2">订单编号：${orderNumber }</td>
                <td width="170">时间：${createTime }</td><!-- 交易表中的创建时间吗  -->
                <td width="150"><i class="icon">&nbsp;</i>${name }   ${code }</td>
                <td></td>
                <td></td>
                <td></td>
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
              	<td>${cargoName }</td>
                <td>${requestStartTime }</td>
                <td>${startProCityCounty }</td>
                <td>${endProCityCounty }</td>
                <td>${carNumber }</td>
                <td style="overflow:visible"> ${tradeStartValue }
                  <s:if test="tradeCancelOriginVal!=''&&tradeCancelOriginVal!=null">
                  <tt style="color: red;">(${tradeCancelOriginVal })</tt>
                  </s:if>
                </td>
                <td></td>
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

</body>
</html>
