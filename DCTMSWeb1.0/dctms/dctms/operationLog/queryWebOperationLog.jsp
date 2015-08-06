<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>查询操作日志</title>
<script type="text/javascript">
	window.onload=function(){
		queryConditionInit();
		document.getElementById("mainForm_operationLogDomain_type").value = '0';
		queryInfoInit();
	} ;
	function queryInfo(){
		var data ={
				"operationLogDomain.userDriverId":trim(document.getElementById("mainForm_operationLogDomain_userDriverId").value),
				"operationLogDomain.userDriverCode":trim(document.getElementById("mainForm_operationLogDomain_userDriverCode").value),
				"operationLogDomain.userDriverName":trim(document.getElementById("mainForm_operationLogDomain_userDriverName").value),
				"operationLogDomain.type":trim(document.getElementById("mainForm_operationLogDomain_type").value),
				"operationLogDomain.remark":trim(document.getElementById("mainForm_operationLogDomain_remark").value),
				"operationLogDomain.queryTimeQ":trim(document.getElementById("mainForm_operationLogDomain_queryTimeQ").value),
				"operationLogDomain.queryTimeZ":trim(document.getElementById("mainForm_operationLogDomain_queryTimeZ").value),
				"operationLogDomain.pageInfo.curPage" :trim(document.getElementById("curPage").value),
				"operationLogDomain.pageInfo.curPageNo" : trim(document.getElementById("curPageNo").value)
		};
		var url = jcontextPath+"/queryOperationLog";
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
		var dataList = text.operationLogDomain.dataList;
		var totalPages = text.operationLogDomain.pageInfo.totalPages;//总页数
		var curPageNos = text.operationLogDomain.pageInfo.curPageNo;//当前页数
		var totalRecords = text.operationLogDomain.pageInfo.totalRecords;//总记录数
        var columus =["userDriverId","userDriverName","createTime","remark"];
        var operateObject = {};
		var operateObjectMultiParameter = {'jumpHtml(9,2,':['userDriverId','企业']};
		showPageTable(dataList,columus,curPageNos,1,operateObject,operateObjectMultiParameter);
		pageInfo(totalPages,curPageNos,totalRecords);
	}
	function exportInfo(){
		document.getElementById('mainForm').submit();
	}
</script>
<style type="text/css">
</style>
</head>

<body  onselect="false">
<s:form id="mainForm" action="/exportOperationLog" namespace="/" method="post">
<s:hidden name="operationLogDomain.type"></s:hidden>
<s:hidden name="operationLogDomain.userDriverId"></s:hidden>

<table  class="conditionTable">
             <tr>            
                <td width="200px">账号<s:textfield name="operationLogDomain.userDriverCode"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">姓名<s:textfield name="operationLogDomain.userDriverName"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
              	<td width="200px">备注<s:textfield name="operationLogDomain.remark"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
               </tr>
               <tr>
                <td colspan="2">操作日期
                <s:textfield name="operationLogDomain.queryTimeQ" cssClass="ymdate"></s:textfield>∽<s:textfield name="operationLogDomain.queryTimeZ" cssClass="ymdate"></s:textfield>
                 </td>
              	 <td >
				<input type="button" onclick="queryInfoInit();" value="查询" class="selectBtn"/>
				<input type="button" onclick="exportInfo();" value="导出" class="selectBtn"/>
				</td>
            </tr>
        </table>
	<table class="mainTable" style="width: 840px" id="dataList">
		<tr class="mainTopTr">
			<td width="40" >序号</td>
			<td width="80">操作人ID</td>
			<td width="100">操作人账号</td>
			<td width="100">操作人姓名</td>
			<td width="130">操作时间</td>
			<td width="300">备注</td>
		</tr>
    </table>
    <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage'  value='${operationLogDomain.pageInfo.curPage}'/><!-- 需要显示页面 -->
    <input type='hidden' id='curPageNo' value='${operationLogDomain.pageInfo.curPageNo}'/><!-- 当前页面 -->
    <input type='hidden' id='operationLogDomain.pageInfo.pageSize' value='${operationLogDomain.pageInfo.pageSize}'/><!-- 当前页面 -->
</s:form>
</body>
</html>
