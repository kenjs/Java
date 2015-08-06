<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>查询交易信息</title>
<script type="text/javascript">
	window.onload=function(){
		queryConditionInit();
		queryInfoInit();
	} ;
	function queryInfo(){
		var data ={
				"transactionInfoDomain.driverId":trim(document.getElementById("mainForm_transactionInfoDomain_driverId").value),
				"transactionInfoDomain.deployUserid":trim(document.getElementById("mainForm_transactionInfoDomain_deployUserid").value),
				"transactionInfoDomain.cargoId":trim(document.getElementById("mainForm_transactionInfoDomain_cargoId").value),
				"transactionInfoDomain.orderNumber":trim(document.getElementById("mainForm_transactionInfoDomain_orderNumber").value),
				"transactionInfoDomain.tradeFair":trim(document.getElementById("mainForm_transactionInfoDomain_tradeFair").value),
				"transactionInfoDomain.tradeStart":trim(document.getElementById("mainForm_transactionInfoDomain_tradeStart").value),
				"transactionInfoDomain.orderStart":trim(document.getElementById("mainForm_transactionInfoDomain_orderStart").value),
				"transactionInfoDomain.remark":trim(document.getElementById("mainForm_transactionInfoDomain_remark").value),
				"transactionInfoDomain.tradeCancelOrigin":trim(document.getElementById("mainForm_transactionInfoDomain_tradeCancelOrigin").value),
				"transactionInfoDomain.queryTimeQ":trim(document.getElementById("mainForm_transactionInfoDomain_queryTimeQ").value),
				"transactionInfoDomain.queryTimeZ":trim(document.getElementById("mainForm_transactionInfoDomain_queryTimeZ").value),
				"transactionInfoDomain.pageInfo.curPage" :trim(document.getElementById("curPage").value),
				"transactionInfoDomain.pageInfo.curPageNo" : trim(document.getElementById("curPageNo").value)
		};
		var url = jcontextPath+"/queryTransactionInfo";
		AjaxSubmit({
			url:url,
			data:data,
		    method:"get",
		    async:true,
		    success:function(text){
		    	callBackList(text);
		    }
		});
	}
	function callBackList(text){
		var dataList = text.transactionInfoDomain.dataList;
		var totalPages = text.transactionInfoDomain.pageInfo.totalPages;//总页数
		var curPageNos = text.transactionInfoDomain.pageInfo.curPageNo;//当前页数
		var totalRecords = text.transactionInfoDomain.pageInfo.totalRecords;//总记录数
        var columus =["orderNumber","tradeFair","tradeStart","tradeStartTime","orderStart","remark","tradeCancelOrigin","cargoId","driverId","userId","companyId","cargoName","driverCode","driverName","userCode","userName","companyName","createTime"];
		var operateObject = {'updateTransactionInfo':'修改','deleteTransactionInfo':'删除'};
		var operateObjectMultiParameter = {'jumpHtml(3,4,':['cargoId','货物'],'jumpHtml(3,2,':['deployUserid','企业'],'jumpHtml(3,1,':['driverId','司机'],'jumpHtml(3,11,':['id','订单历史状态']};
		showPageTable(dataList,columus,curPageNos,1,operateObject,operateObjectMultiParameter);	
		pageInfo(totalPages,curPageNos,totalRecords);
	}
	function updateTransactionInfo(id){
		var url = jcontextPath+"/queryTransactionInfoMx?transactionInfoDomain.id="+id+"&random="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	} 
	function addInfo(){
		var url = jcontextPath+"/queryTransactionInfoMx?transactionInfoDomain.id="+0;
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	}
	function deleteTransactionInfo(id){
		if(confirm("确定要删除么？")){
			yesCallBack(id);
		}
	}	
	function yesCallBack(id){
		 var jsonObj = {"transactionInfoDomain.id":id};
		 var url = jcontextPath+"/deleteTransactionInfo";   
          AjaxSubmit({
				url:url,
				data:jsonObj,
			    method:"get",
			    async:true,
			    success:function(text){
			    	doSuccess();
			    }
			});
	}
	
	function doSuccess(){
        alert("删除成功！");
		queryInfo();
	}	
	function exportInfo(){
		document.getElementById('mainForm').submit();
	}
</script>
<style type="text/css">
</style>
</head>

<body  onselect="false">
<s:form id="mainForm" action="/exportTransactionInfo" namespace="/" method="post">
<s:hidden name="transactionInfoDomain.driverId"></s:hidden>
<s:hidden name="transactionInfoDomain.deployUserid"></s:hidden>
<s:hidden name="transactionInfoDomain.cargoId"></s:hidden>


<table  class="conditionTable">
             <tr>            
                <td width="200px">订单编号<s:textfield name="transactionInfoDomain.orderNumber"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">交易金额<s:textfield name="transactionInfoDomain.tradeFair"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">交易状态<s:textfield name="transactionInfoDomain.tradeStart"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">订单状态<s:textfield name="transactionInfoDomain.orderStart"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">备注<s:textfield name="transactionInfoDomain.remark"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                </tr>
                <tr>
                <td colspan="2">发布日期
                <s:textfield name="transactionInfoDomain.queryTimeQ" cssClass="ymdate"></s:textfield>∽<s:textfield name="transactionInfoDomain.queryTimeZ" cssClass="ymdate"></s:textfield>
                 </td>
                <td >
				<input type="button" onclick="queryInfoInit();" value="查询" class="selectBtn"/>
				<input type="button" onclick="exportInfo();" value="导出" class="selectBtn"/>
				</td>
               <td width="200px">取消来源<s:textfield name="transactionInfoDomain.tradeCancelOrigin"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
            </tr>
        </table>
	<table class="mainTable" style="width: 840px" id="dataList">
		<tr class="mainTopTr">
			<td width="40" >序号</td>
			<td width="300">操作</td>
			<td width="200">订单编号</td>
			<td width="100">交易金额</td>
			<td width="100">交易状态</td>
			<td width="130">修改时间</td>
			<td width="100">订单状态</td>
			<td width="300">备注</td>
			<td width="100">交易取消来源</td>
			<td width="100">货源ID</td>
			<td width="100">车源ID</td>
			<td width="100">用户ID</td>
			<td width="100">企业ID</td>
			<td width="100">货物名称</td>
			<td width="100">司机账号</td>
			<td width="100">司机名称</td>
			<td width="100">用户账号</td>
			<td width="100">用户名称</td>
			<td width="100">企业名称</td>
			<td width="130">发布时间</td>
			
		</tr>
    </table>
    <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage'  value='${transactionInfoDomain.pageInfo.curPage}'/><!-- 需要显示页面 -->
    <input type='hidden' id='curPageNo' value='${transactionInfoDomain.pageInfo.curPageNo}'/><!-- 当前页面 -->
    <input type='hidden' id='transactionInfoDomain.pageInfo.pageSize' value='${transactionInfoDomain.pageInfo.pageSize}'/><!-- 当前页面 -->
</s:form>
</body>
</html>
