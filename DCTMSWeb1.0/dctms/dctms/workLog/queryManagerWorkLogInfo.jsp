<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>查询管理员操作日志信息</title>
<script type="text/javascript">
	window.onload=function(){
		queryInfoInit();
	} ;
	function queryInfo(){
		var data ={
				"managerWorkLogInfoDomain.name":trim(document.getElementById("mainForm_managerWorkLogInfoDomain_name").value),
				"managerWorkLogInfoDomain.managerCode":trim(document.getElementById("mainForm_managerWorkLogInfoDomain_managerCode").value),
				"managerWorkLogInfoDomain.managerName":trim(document.getElementById("mainForm_managerWorkLogInfoDomain_managerName").value),
				"managerWorkLogInfoDomain.pageInfo.curPage" :trim(document.getElementById("curPage").value),
				"managerWorkLogInfoDomain.pageInfo.curPageNo" : trim(document.getElementById("curPageNo").value)
		};
		var url = jcontextPath+"/queryManagerWorkLogInfo";
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
		var dataList = text.managerWorkLogInfoDomain.dataList;
		var totalPages = text.managerWorkLogInfoDomain.pageInfo.totalPages;//总页数
		var curPageNos = text.managerWorkLogInfoDomain.pageInfo.curPageNo;//当前页数
		var totalRecords = text.managerWorkLogInfoDomain.pageInfo.totalRecords;//总记录数
        var columus =["managerCode","createTime","managerName","name","content"];
        var operateObject = {'auditDriverUserInfo':'审核'};
		showPageTable(dataList,columus,curPageNos,0,operateObject);
		pageInfo(totalPages,curPageNos,totalRecords);
	}
	function auditDriverUserInfo(id){
		var url = jcontextPath+"/auditDriverUserInfoMx?driverUserInfoDomain.id="+id+"&random="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
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
<s:form id="mainForm" action="/exportManagerWorkLogInfo" namespace="/" method="post">



<table class="conditionTable">
            <tr>
            	<td width="250px">操作日志名<s:textfield name="managerWorkLogInfoDomain.name"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
				<td width="250px">  管理员账号<s:textfield name="managerWorkLogInfoDomain.managerCode"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>        
				<td width="250px">  管理员姓名<s:textfield name="managerWorkLogInfoDomain.managerName"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>     
            </tr>
            <tr>
                <td >
                <input type="button" onclick="queryInfoInit();" value="查询" class="selectBtn"/>
                <input type="button" onclick="exportInfo();" value="导出" class="selectBtn"/>
                </td>
            </tr>
        </table>
	<table class="mainTable" style="width: 840px" id="dataList">
		<tr class="mainTopTr">
			<td width="40">序号</td>
			<td width="80">管理员账号</td>
			<td width="150">操作时间</td>
			<td width="200">管理员姓名</td>
			<td width="150">操作日志名</td>
			<td width="1000" style="overflow:hidden" >操作内容</td>
		</tr>
    </table>
     <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage'  value='${managerWorkLogInfoDomain.pageInfo.curPage}'/><!-- 需要显示页面 -->
    <input type='hidden' id='curPageNo' value='${managerWorkLogInfoDomain.pageInfo.curPageNo}'/><!-- 当前页面 -->
    <input type='hidden' id='managerWorkLogInfoDomain.pageInfo.pageSize' value='${managerWorkLogInfoDomain.pageInfo.pageSize}'/><!-- 当前页面 -->
</s:form>
</body>
</html>
