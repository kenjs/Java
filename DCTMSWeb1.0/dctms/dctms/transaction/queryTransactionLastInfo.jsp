<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>查询订单历史状态</title>
<script type="text/javascript">
	window.onload=function(){
		queryConditionInit();
		queryInfoInit();
	} ;
	function queryInfo(){
		var data ={
				"transactionLastInfoDomain.transactionId":trim(document.getElementById("mainForm_transactionLastInfoDomain_transactionId").value)
		};
		var url = jcontextPath+"/queryTransactionLastInfo";
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
		var dataList = text.transactionLastInfoDomain.dataList;
		var totalPages = text.transactionLastInfoDomain.pageInfo.totalPages;//总页数
		var curPageNos = text.transactionLastInfoDomain.pageInfo.curPageNo;//当前页数
		var totalRecords = text.transactionLastInfoDomain.pageInfo.totalRecords;//总记录数
        var columus =["cargoId","driverId","driverCode","driverName","transactionId","start","remark"];
		showPageTable(dataList,columus,curPageNos,0);
		pageInfo(totalPages,curPageNos,totalRecords);
	}
	function updateTransactionLastInfo(id){
		var url = jcontextPath+"/queryTransactionLastInfoMx?transactionLastInfoDomain.id="+id+"&random="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	} 
	function addInfo(){
		var url = jcontextPath+"/queryTransactionLastInfoMx?transactionLastInfoDomain.id="+0;
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	}
	function deleteTransactionLastInfo(id){
		if(confirm("确定要删除么？")){
			yesCallBack(id);
		}
	}	
	function yesCallBack(id){
		 var jsonObj = {"transactionLastInfoDomain.id":id};
		 var url = jcontextPath+"/deleteTransactionLastInfo";   
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
<s:form id="mainForm" action="/exportTransactionLastInfo" namespace="/" method="post">
<s:hidden name="transactionLastInfoDomain.transactionId"></s:hidden>

<table  class="conditionTable">
             <tr>            
                <td >
				<input type="button" onclick="queryInfoInit();" value="查询" class="selectBtn"/>
				<input type="button" onclick="exportInfo();" value="导出" class="selectBtn"/>
				</td>
            </tr>
        </table>
	<table class="mainTable" style="width: 840px" id="dataList">
		<tr class="mainTopTr">
			<td width="40" >序号</td>
			<td width="100">货源ID</td>
			<td width="100">司机ID</td>
			<td width="100">司机账号</td>
			<td width="100">司机姓名</td>
			<td width="100">交易订单ID</td>
			<td width="100">状态</td>
			<td width="100">备注</td>
		</tr>
    </table>
    <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage'  value='${transactionLastInfoDomain.pageInfo.curPage}'/><!-- 需要显示页面 -->
    <input type='hidden' id='curPageNo' value='${transactionLastInfoDomain.pageInfo.curPageNo}'/><!-- 当前页面 -->
    <input type='hidden' id='transactionLastInfoDomain.pageInfo.pageSize' value='${transactionLastInfoDomain.pageInfo.pageSize}'/><!-- 当前页面 -->
</s:form>
</body>
</html>
