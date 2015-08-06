<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>查询查询存活率</title>
<script type="text/javascript">
	window.onload=function(){
		//初始化时间获取当天和这个月的第一天
		var time = new Date();
		var year = time.getFullYear();
		var month = time.getMonth() +1;
		var date = time.getDate();
		if(month<10)
			month = "0" + month;
		if(date<10)
			date = "0" + date;
		var last = year +"-"+ month +"-" +date;
		var first = year + "-" + month + "-" + "01";
		document.getElementById("mainForm_driverSurviveRateDomain_queryTimeQ").value=first;
		document.getElementById("mainForm_driverSurviveRateDomain_queryTimeZ").value=last;
		queryInfo();
	} ;
	function queryInfo(){
		var data ={
				"driverSurviveRateDomain.queryTimeQ":trim(document.getElementById("mainForm_driverSurviveRateDomain_queryTimeQ").value),
				"driverSurviveRateDomain.queryTimeZ":trim(document.getElementById("mainForm_driverSurviveRateDomain_queryTimeZ").value)
		};
		var url = jcontextPath+"/queryDriverSurviveRate";
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
		var dataList = text.driverSurviveRateDomain.dataList;
        var columus =["queryTime","registerCount","foreRegisterCount","surviveCount","surviveRate","activeCount","activeRate","goodFindNum","appointNum","phoneCallNum"];
		showPageTable(dataList,columus,1,0);
		//将总计加入
		var table = document.getElementById("dataList");
		var tr = table.insertRow(1);
		tr.insertCell(0).innerHTML = '总计';
		tr.insertCell(1).innerHTML = '共'+(table.rows.length-2)+'天';
		tr.insertCell(2).innerHTML = text.driverSurviveRateDomain.registerTotalCount;
		tr.insertCell(3).innerHTML = '';
		tr.insertCell(4).innerHTML = '';
		tr.insertCell(5).innerHTML = '';
		tr.insertCell(6).innerHTML = '';
		tr.insertCell(7).innerHTML = '';
		tr.insertCell(8).innerHTML = text.driverSurviveRateDomain.goodFindTotalNum;
		tr.insertCell(9).innerHTML = text.driverSurviveRateDomain.appointTotalNum;
		tr.insertCell(10).innerHTML = text.driverSurviveRateDomain.phoneCallTotalNum;
		
	}
	function exportInfo(){
		document.getElementById('mainForm').submit();
	}
</script>
<style type="text/css">
</style>
</head>

<body  onselect="false">
<s:form id="mainForm" action="/exportDriverSurviveRate" namespace="/" method="post">



<table  class="conditionTable">
             <tr>  
             	 <td width="300px">注册时间
                <s:textfield name="driverSurviveRateDomain.queryTimeQ" cssClass="ymdate"></s:textfield>∽<s:textfield name="driverSurviveRateDomain.queryTimeZ" cssClass="ymdate"></s:textfield>
                 </td>   
                 <td>       
				<input type="button" onclick="queryInfo();" value="查询" class="selectBtn"/>
				<input type="button" onclick="exportInfo();" value="导出" class="selectBtn"/>
				</td>
            </tr>
        </table>
	<table class="mainTable" style="width: 720px" id="dataList">
		<tr class="mainTopTr">
			<td width="40" >序号</td>
			<td width="100">日期</td>
			<td width="60">注册数</td>
			<td width="70">前注册数</td>
			<td width="60">存活量</td>
			<td width="60">存活率</td>
			<td width="60">活跃量</td>
			<td width="60">活跃率</td>
			<td width="70">找货次数</td>
			<td width="70">预约条数</td>
			<td width="70">电话拨打数</td>
		</tr>
    </table>
</s:form>
</body>
</html>
