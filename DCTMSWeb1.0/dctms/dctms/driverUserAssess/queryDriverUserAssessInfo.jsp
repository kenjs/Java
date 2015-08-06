<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>查询司机对企业评价</title>
<script type="text/javascript">
	window.onload=function(){
		queryConditionInit();
		queryInfoInit();
	} ;
	function queryInfo(){
		var data ={
				"driverUserAssessInfoDomain.driverId":trim(document.getElementById("mainForm_driverUserAssessInfoDomain_driverId").value),
				"driverUserAssessInfoDomain.cargoName":trim(document.getElementById("mainForm_driverUserAssessInfoDomain_cargoName").value),
				"driverUserAssessInfoDomain.driverCode":trim(document.getElementById("mainForm_driverUserAssessInfoDomain_driverCode").value),
				"driverUserAssessInfoDomain.userId":trim(document.getElementById("mainForm_driverUserAssessInfoDomain_userId").value),
				"driverUserAssessInfoDomain.userCode":trim(document.getElementById("mainForm_driverUserAssessInfoDomain_userCode").value),
				"driverUserAssessInfoDomain.orderNumber":trim(document.getElementById("mainForm_driverUserAssessInfoDomain_orderNumber").value),
				"driverUserAssessInfoDomain.assessEvaluateScore":trim(document.getElementById("mainForm_driverUserAssessInfoDomain_assessEvaluateScore").value),
				"driverUserAssessInfoDomain.queryTimeQ":trim(document.getElementById("mainForm_driverUserAssessInfoDomain_queryTimeQ").value),
				"driverUserAssessInfoDomain.queryTimeZ":trim(document.getElementById("mainForm_driverUserAssessInfoDomain_queryTimeZ").value),
				"driverUserAssessInfoDomain.pageInfo.curPage" :trim(document.getElementById("curPage").value),
				"driverUserAssessInfoDomain.pageInfo.curPageNo" : trim(document.getElementById("curPageNo").value)
		};
		var url = jcontextPath+"/queryDriverUserAssessInfo";
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
		var dataList = text.driverUserAssessInfoDomain.dataList;
		var totalPages = text.driverUserAssessInfoDomain.pageInfo.totalPages;//总页数
		var curPageNos = text.driverUserAssessInfoDomain.pageInfo.curPageNo;//当前页数
		var totalRecords = text.driverUserAssessInfoDomain.pageInfo.totalRecords;//总记录数
        var columus =["assessEvaluateScore","cargoId","cargoName","userId","userCode","userName","driverId","driverCode","driverName","transactionId","orderNumber","assess","createTime"];
		var operateObject = {'updateDriverUserAssessInfo':'修改','deleteDriverUserAssessInfo':'删除'};
		var operateObjectMultiParameter = {'jumpHtml(7,2,':['userId','企业'],'jumpHtml(7,1,':['driverId','评价人']};
		showPageTable(dataList,columus,curPageNos,1,operateObject,operateObjectMultiParameter);
		pageInfo(totalPages,curPageNos,totalRecords);
	}
	function updateDriverUserAssessInfo(id){
		var url = jcontextPath+"/queryDriverUserAssessInfoMx?driverUserAssessInfoDomain.id="+id+"&random="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	} 
	function addInfo(){
		var url = jcontextPath+"/queryDriverUserAssessInfoMx?driverUserAssessInfoDomain.id="+0;
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	}
	function deleteDriverUserAssessInfo(id){
		if(confirm("确定要硬删除么？")){
			yesCallBack(id);
		}
	}	
	function yesCallBack(id){
		alert("不支持硬删除");
		return;
		 var jsonObj = {"driverUserAssessInfoDomain.id":id};
		 var url = jcontextPath+"/deleteDriverUserAssessInfo";   
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
<s:form id="mainForm" action="/exportDriverUserAssessInfo" namespace="/" method="post">
<s:hidden name="driverUserAssessInfoDomain.driverId"></s:hidden>
<s:hidden name="driverUserAssessInfoDomain.userId"></s:hidden>

<table  class="conditionTable">
             <tr>   
             <td width="200px">评分
                	<s:select name="driverUserAssessInfoDomain.assessEvaluateScore" list="#{'':'',3:'好评',6:'中评',9:'差评'}"/>
                </td>    
                <td width="200px">货源ID<s:textfield name="driverUserAssessInfoDomain.cargoId"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">货物名称<s:textfield name="driverUserAssessInfoDomain.cargoName"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">司机账号<s:textfield name="driverUserAssessInfoDomain.driverCode"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                 <td width="200px">订单号<s:textfield name="driverUserAssessInfoDomain.orderNumber"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
               </tr>
               <tr>
               <td colspan="2">操作日期
                <s:textfield name="driverUserAssessInfoDomain.queryTimeQ" cssClass="ymdate"></s:textfield>∽<s:textfield name="driverUserAssessInfoDomain.queryTimeZ" cssClass="ymdate"></s:textfield>
                 </td>
                <td >
				<input type="button" onclick="queryInfoInit();" value="查询" class="selectBtn"/>
				<input type="button" onclick="exportInfo();" value="导出" class="selectBtn"/>
				</td>
                 <td width="200px">企业账号<s:textfield name="driverUserAssessInfoDomain.userCode"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
            </tr>
        </table>
	<table class="mainTable" style="width: 840px" id="dataList">
		<tr class="mainTopTr">
			<td width="40" >序号</td>
			<td width="200">操作</td>
			<td width="100">评分</td>
			<td width="100">货源ID</td>
			<td width="100">货物名称</td>
			<td width="100">企业ID</td>
			<td width="100">企业账号</td>
			<td width="100">企业名称</td>
			<td width="100">评价人Id</td>
			<td width="100">评价人账号</td>
			<td width="100">评价人名称</td>
			<td width="100">交易订单Id</td>
			<td width="200">订单号</td>
			<td width="300">评语</td>
			<td width="130">评价时间</td>
		</tr>
    </table>
    <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage'  value='${driverUserAssessInfoDomain.pageInfo.curPage}'/><!-- 需要显示页面 -->
    <input type='hidden' id='curPageNo' value='${driverUserAssessInfoDomain.pageInfo.curPageNo}'/><!-- 当前页面 -->
    <input type='hidden' id='driverUserAssessInfoDomain.pageInfo.pageSize' value='${driverUserAssessInfoDomain.pageInfo.pageSize}'/><!-- 当前页面 -->
</s:form>
</body>
</html>
