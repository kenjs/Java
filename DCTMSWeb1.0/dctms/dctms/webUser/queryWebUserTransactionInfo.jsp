<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>查询企业成交记录</title>
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
		document.getElementById("mainForm_webUserInfoDomain_registerTimeQ").value=first;
		document.getElementById("mainForm_webUserInfoDomain_registerTimeZ").value=last;
		queryInfo();
	} ;
	function queryInfo(){
		var data ={
				"webUserInfoDomain.companyName":trim(document.getElementById("mainForm_webUserInfoDomain_companyName").value),
				"webUserInfoDomain.registerTimeQ":trim(document.getElementById("mainForm_webUserInfoDomain_registerTimeQ").value),
				"webUserInfoDomain.registerTimeZ":trim(document.getElementById("mainForm_webUserInfoDomain_registerTimeZ").value)
		};
		var url = jcontextPath+"/queryWebUserTransactionInfo";
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
		var dataList = text.webUserInfoDomain.dataList;
        var columus =["companyName","code","mobilephone","userType","createTime","accumulateCargoCount",
                      "orderCargoCount","orderCargoRate","accumulateTransactionCount","accumulateTransactionRate"];
        showPageTable(dataList,columus,1,0);
      //设置总计
		var table = document.getElementById("dataList");
		var tr = table.insertRow(1);
		tr.insertCell(0).innerHTML = '总计';
		tr.insertCell(1).innerHTML = '共'+(table.rows.length-2)+'企业';
		tr.insertCell(2).innerHTML = '';
		tr.insertCell(3).innerHTML = '';
		tr.insertCell(4).innerHTML = '';
		tr.insertCell(5).innerHTML = '';
		tr.insertCell(6).innerHTML = text.webUserInfoDomain.accumulateCargoCount;
		tr.insertCell(7).innerHTML = text.webUserInfoDomain.orderCargoCount;
		tr.insertCell(8).innerHTML = text.webUserInfoDomain.orderCargoRate;
		tr.insertCell(9).innerHTML = text.webUserInfoDomain.accumulateTransactionCount;
		tr.insertCell(10).innerHTML = text.webUserInfoDomain.accumulateTransactionRate;
		//对某一列相同的数据合并
		contactRow(table.rows,5,table.rows.length-1,2);
	}
	function exportInfo(){
		document.getElementById('mainForm').submit();
	}
</script>
<style type="text/css">
</style>
</head>

<body  onselect="false">
<s:form id="mainForm" action="/exportWebUserInfo" namespace="/" method="post">
<s:hidden name="webUserInfoDomain.id"></s:hidden>


<table  class="conditionTable">
             <tr>            
               <td width="300px">注册时间
                <s:textfield name="webUserInfoDomain.registerTimeQ" cssClass="ymdate"></s:textfield>∽<s:textfield name="webUserInfoDomain.registerTimeZ" cssClass="ymdate"></s:textfield>
                 </td>
                 <td width="200px">公司名称<s:textfield name="webUserInfoDomain.companyName"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td >
                <input type="button" onclick="queryInfo();" value="查询" class="selectBtn"/>
                <input type="button" onclick="exportInfo();" value="导出" class="selectBtn"/>
                </td>
            </tr>
        </table>
	<table class="mainTable" style="width: 715px" id="dataList">
		<tr class="mainTopTr">
			<td width="30" >序号</td>
			<td width="100">企业名称</td>
			<td width="100">帐号</td>
			<td width="90">联系电话</td>
			<td width="60">帐号类型</td>
			<td width="80">注册日期</td>
			<td width="45">货源数</td>
			<td width="45">订车数</td>
			<td width="60">订车率</td>
			<td width="45">成交数</td>
			<td width="60">成交率</td>
		</tr>
    </table>
</s:form>
</body>
</html>
