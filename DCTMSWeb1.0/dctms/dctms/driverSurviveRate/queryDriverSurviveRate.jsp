<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>��ѯ��ѯ�����</title>
<script type="text/javascript">
	window.onload=function(){
		//��ʼ��ʱ���ȡ���������µĵ�һ��
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
		//���ܼƼ���
		var table = document.getElementById("dataList");
		var tr = table.insertRow(1);
		tr.insertCell(0).innerHTML = '�ܼ�';
		tr.insertCell(1).innerHTML = '��'+(table.rows.length-2)+'��';
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
             	 <td width="300px">ע��ʱ��
                <s:textfield name="driverSurviveRateDomain.queryTimeQ" cssClass="ymdate"></s:textfield>��<s:textfield name="driverSurviveRateDomain.queryTimeZ" cssClass="ymdate"></s:textfield>
                 </td>   
                 <td>       
				<input type="button" onclick="queryInfo();" value="��ѯ" class="selectBtn"/>
				<input type="button" onclick="exportInfo();" value="����" class="selectBtn"/>
				</td>
            </tr>
        </table>
	<table class="mainTable" style="width: 720px" id="dataList">
		<tr class="mainTopTr">
			<td width="40" >���</td>
			<td width="100">����</td>
			<td width="60">ע����</td>
			<td width="70">ǰע����</td>
			<td width="60">�����</td>
			<td width="60">�����</td>
			<td width="60">��Ծ��</td>
			<td width="60">��Ծ��</td>
			<td width="70">�һ�����</td>
			<td width="70">ԤԼ����</td>
			<td width="70">�绰������</td>
		</tr>
    </table>
</s:form>
</body>
</html>
