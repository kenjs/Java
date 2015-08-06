<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>查询企业对司机评价</title>
<script type="text/javascript">
	window.onload=function(){
		queryConditionInit();
		queryInfoInit();
	} ;
	function queryInfo(){
		var data ={
				"userDriverAssessInfoDomain.cargoId":trim(document.getElementById("mainForm_userDriverAssessInfoDomain_cargoId").value),
				"userDriverAssessInfoDomain.driverId":trim(document.getElementById("mainForm_userDriverAssessInfoDomain_driverId").value),
				"userDriverAssessInfoDomain.userId":trim(document.getElementById("mainForm_userDriverAssessInfoDomain_userId").value),
				"userDriverAssessInfoDomain.tradeEvaluateScore":trim(document.getElementById("mainForm_userDriverAssessInfoDomain_tradeEvaluateScore").value),
				"userDriverAssessInfoDomain.cargoName":trim(document.getElementById("mainForm_userDriverAssessInfoDomain_cargoName").value),
				"userDriverAssessInfoDomain.driverCode":trim(document.getElementById("mainForm_userDriverAssessInfoDomain_driverCode").value),
				"userDriverAssessInfoDomain.userCode":trim(document.getElementById("mainForm_userDriverAssessInfoDomain_userCode").value),
				"userDriverAssessInfoDomain.orderNumber":trim(document.getElementById("mainForm_userDriverAssessInfoDomain_orderNumber").value),
				"userDriverAssessInfoDomain.queryTimeQ":trim(document.getElementById("mainForm_userDriverAssessInfoDomain_queryTimeQ").value),
				"userDriverAssessInfoDomain.queryTimeZ":trim(document.getElementById("mainForm_userDriverAssessInfoDomain_queryTimeZ").value),
				"userDriverAssessInfoDomain.pageInfo.curPage" :trim(document.getElementById("curPage").value),
				"userDriverAssessInfoDomain.pageInfo.curPageNo" : trim(document.getElementById("curPageNo").value)
		};
		var url = jcontextPath+"/queryUserDriverAssessInfo";
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
		var dataList = text.userDriverAssessInfoDomain.dataList;
		var totalPages = text.userDriverAssessInfoDomain.pageInfo.totalPages;//总页数
		var curPageNos = text.userDriverAssessInfoDomain.pageInfo.curPageNo;//当前页数
		var totalRecords = text.userDriverAssessInfoDomain.pageInfo.totalRecords;//总记录数
		 var columus =["tradeEvaluateScore","cargoId","cargoName","driverId","driverCode","driverName","userId","userCode","userName","transactionId","orderNumber","arriverEvaluateScore","serveEvaluateScore","assess","createTime"];
		var operateObject = {'updateUserDriverAssessInfo':'修改','deleteUserDriverAssessInfo':'删除'};
		var operateObjectMultiParameter = {'jumpHtml(6,1,':['driverId','司机'],'jumpHtml(6,2,':['userId','评价人']};
		showPageTable(dataList,columus,curPageNos,1,operateObject,operateObjectMultiParameter);
		pageInfo(totalPages,curPageNos,totalRecords);
	}
	function updateUserDriverAssessInfo(id){
		var url = jcontextPath+"/queryUserDriverAssessInfoMx?userDriverAssessInfoDomain.id="+id+"&random="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	} 
	function addInfo(){
		var url = jcontextPath+"/queryUserDriverAssessInfoMx?userDriverAssessInfoDomain.id="+0;
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	}
	function deleteUserDriverAssessInfo(id){
		if(confirm("确定要硬删除么？")){
			yesCallBack(id);
		}
	}	
	function yesCallBack(id){
		alert("不支持硬删除");
		return;
		 var jsonObj = {"userDriverAssessInfoDomain.id":id};
		 var url = jcontextPath+"/deleteUserDriverAssessInfo";   
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
<s:form id="mainForm" action="/exportUserDriverAssessInfo" namespace="/" method="post">
<s:hidden name="userDriverAssessInfoDomain.userId"></s:hidden>
<s:hidden name="userDriverAssessInfoDomain.driverId"></s:hidden>

<table  class="conditionTable">
           <tr>   
             <td width="200px">总评价
                	<s:select name="userDriverAssessInfoDomain.tradeEvaluateScore" list="#{'':'',3:'好评',6:'中评',9:'差评'}"/>
                </td>    
                <td width="200px">货源ID<s:textfield name="userDriverAssessInfoDomain.cargoId"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">货物名称<s:textfield name="userDriverAssessInfoDomain.cargoName"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">司机账号<s:textfield name="userDriverAssessInfoDomain.driverCode"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                 <td width="200px">订单号<s:textfield name="userDriverAssessInfoDomain.orderNumber"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
               </tr>
               <tr>
               <td colspan="2">评价日期
                <s:textfield name="userDriverAssessInfoDomain.queryTimeQ" cssClass="ymdate"></s:textfield>∽<s:textfield name="userDriverAssessInfoDomain.queryTimeZ" cssClass="ymdate"></s:textfield>
                 </td>
                <td >
				<input type="button" onclick="queryInfoInit();" value="查询" class="selectBtn"/>
				<input type="button" onclick="exportInfo();" value="导出" class="selectBtn"/>
				</td>
                 <td width="200px">企业账号<s:textfield name="userDriverAssessInfoDomain.userCode"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
            </tr> 
            
            
            
        </table>
	<table class="mainTable" style="width: 840px" id="dataList">
		<tr class="mainTopTr">
			<td width="40" >序号</td>
			<td width="200">操作</td>
			<td width="100">交易总评价</td>
			<td width="100">货源ID</td>
			<td width="100">货物名称</td>
			<td width="100">司机ID</td>
			<td width="100">司机账号</td>
			<td width="100">司机名称</td>
			<td width="100">评价人Id</td>
			<td width="100">评价人账号</td>
			<td width="100">评价人名称</td>
			<td width="100">交易订单Id</td>
			<td width="200">订单号</td>
			<td width="100">到达速度</td>
			<td width="100">司机服务态度</td>
			<td width="300">评语</td>
			<td width="130">评价时间</td>
		</tr>
    </table>
    <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage'  value='${userDriverAssessInfoDomain.pageInfo.curPage}'/><!-- 需要显示页面 -->
    <input type='hidden' id='curPageNo' value='${userDriverAssessInfoDomain.pageInfo.curPageNo}'/><!-- 当前页面 -->
    <input type='hidden' id='userDriverAssessInfoDomain.pageInfo.pageSize' value='${userDriverAssessInfoDomain.pageInfo.pageSize}'/><!-- 当前页面 -->
</s:form>
</body>
</html>
