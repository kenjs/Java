<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>查询货源历史状态</title>
<script type="text/javascript">
	window.onload=function(){
		queryConditionInit();
		queryInfoInit();
	} ;
	function queryInfo(){
		var data ={
				"orderCargoLastInfoDomain.cargoId":trim(document.getElementById("mainForm_orderCargoLastInfoDomain_cargoId").value),
				"orderCargoLastInfoDomain.pageInfo.curPage" :trim(document.getElementById("curPage").value),
				"orderCargoLastInfoDomain.pageInfo.curPageNo" : trim(document.getElementById("curPageNo").value)
		};
		var url = jcontextPath+"/queryOrderCargoLastInfo";
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
		var dataList = text.orderCargoLastInfoDomain.dataList;
		var totalPages = text.orderCargoLastInfoDomain.pageInfo.totalPages;//总页数
		var curPageNos = text.orderCargoLastInfoDomain.pageInfo.curPageNo;//当前页数
		var totalRecords = text.orderCargoLastInfoDomain.pageInfo.totalRecords;//总记录数
        var columus =["cargoId","driverId","driverCode","driverName","stateType","remark"];
		showPageTable(dataList,columus,curPageNos,0);
		pageInfo(totalPages,curPageNos,totalRecords);
	}
	function updateOrderCargoLastInfo(id){
		var url = jcontextPath+"/queryOrderCargoLastInfoMx?orderCargoLastInfoDomain.id="+id+"&random="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	} 
	function addInfo(){
		var url = jcontextPath+"/queryOrderCargoLastInfoMx?orderCargoLastInfoDomain.id="+0;
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	}
	function deleteOrderCargoLastInfo(id){
		if(confirm("确定要删除么？")){
			yesCallBack(id);
		}
	}	
	function yesCallBack(id){
		 var jsonObj = {"orderCargoLastInfoDomain.id":id};
		 var url = jcontextPath+"/deleteOrderCargoLastInfo";   
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
<s:form id="mainForm" action="/exportOrderCargoLastInfo" namespace="/" method="post">
<s:hidden name="orderCargoLastInfoDomain.cargoId"></s:hidden>


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
			<td width="100">状态类型</td>
			<td width="100">备注</td>
		</tr>
    </table>
    <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage'  value='${orderCargoLastInfoDomain.pageInfo.curPage}'/><!-- 需要显示页面 -->
    <input type='hidden' id='curPageNo' value='${orderCargoLastInfoDomain.pageInfo.curPageNo}'/><!-- 当前页面 -->
    <input type='hidden' id='orderCargoLastInfoDomain.pageInfo.pageSize' value='${orderCargoLastInfoDomain.pageInfo.pageSize}'/><!-- 当前页面 -->
</s:form>
</body>
</html>
