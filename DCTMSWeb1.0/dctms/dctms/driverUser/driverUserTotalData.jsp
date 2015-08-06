<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>��ѯ˾����Ϣ</title>
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
		document.getElementById("mainForm_driverUserInfoDomain_registerTimeQ").value=first;
		document.getElementById("mainForm_driverUserInfoDomain_registerTimeZ").value=last;
		queryInfo();
	} ;
	function queryInfo(){
		var data ={
<%--				"driverUserInfoDomain.id":trim(document.getElementById("mainForm_driverUserInfoDomain_id").value),--%>
<%--				"driverUserInfoDomain.code":trim(document.getElementById("mainForm_driverUserInfoDomain_code").value),--%>
<%--				"driverUserInfoDomain.name":trim(document.getElementById("mainForm_driverUserInfoDomain_name").value),--%>
<%--				"driverUserInfoDomain.carNumber":trim(document.getElementById("mainForm_driverUserInfoDomain_carNumber").value),--%>
				"driverUserInfoDomain.registerTimeQ":trim(document.getElementById("mainForm_driverUserInfoDomain_registerTimeQ").value),
				"driverUserInfoDomain.registerTimeZ":trim(document.getElementById("mainForm_driverUserInfoDomain_registerTimeZ").value)
		};
		var url = jcontextPath+"/driverUserTotalData";
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
		var dataList = text.driverUserInfoDomain.dataList;
        var columus =["name","carNumber","code","createTime","goodFindNum","quoteCount","orderingCount",
                      "orderingRate","passOrderCount","passOrderRate","phoneCallNum"];
		showPageTable(dataList,columus,1,0);
		//�����ܼ�
		var table = document.getElementById("dataList");
		var tr = table.insertRow(1);
		tr.insertCell(0).innerHTML = '�ܼ�';
		tr.insertCell(1).innerHTML = '';
		tr.insertCell(2).innerHTML = '';
		tr.insertCell(3).innerHTML = '��'+(table.rows.length-2)+'��';
		tr.insertCell(4).innerHTML = '';
		tr.insertCell(5).innerHTML = text.driverUserInfoDomain.goodFindNum;
		tr.insertCell(6).innerHTML = text.driverUserInfoDomain.quoteCount;
		tr.insertCell(7).innerHTML = text.driverUserInfoDomain.orderingCount;
		tr.insertCell(8).innerHTML = text.driverUserInfoDomain.orderingRate;
		tr.insertCell(9).innerHTML = text.driverUserInfoDomain.passOrderCount;
		tr.insertCell(10).innerHTML = text.driverUserInfoDomain.passOrderRate;
		tr.insertCell(11).innerHTML = text.driverUserInfoDomain.phoneCallNum;
		
		//��ĳһ����ͬ�����ݺϲ�
		contactRow(table.rows,4,table.rows.length-1,2);
	}
	
	function exportInfo(){
		document.getElementById('mainForm').submit();
	}
</script>
<style type="text/css">
</style>
</head>

<body  onselect="false">
<s:form id="mainForm" action="/exportDriverUserInfo" namespace="/" method="post">
<s:hidden name="driverUserInfoDomain.id"></s:hidden>


<table  class="conditionTable">
<%--             <tr>            --%>
<%--                <td width="200px">�ֻ�����<s:textfield name="driverUserInfoDomain.code"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>--%>
<%--                <td width="200px">˾������<s:textfield name="driverUserInfoDomain.name"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>--%>
<%--               <td width="200px">���ƺ�<s:textfield name="driverUserInfoDomain.carNumber"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>--%>
<%--                </tr>--%>
                <tr>
                <td colspan="2">ע��ʱ��
                <s:textfield name="driverUserInfoDomain.registerTimeQ" cssClass="ymdate"></s:textfield>��<s:textfield name="driverUserInfoDomain.registerTimeZ" cssClass="ymdate"></s:textfield>
                 </td>
                <td >
                <input type="button" onclick="queryInfo();" value="��ѯ" class="selectBtn"/>
                <input type="button" onclick="exportInfo();" value="����" class="selectBtn"/>
                </td>
            </tr>
        </table>
	<table class="mainTable" style="width: 680px" id="dataList">
		<tr class="mainTopTr">
			<td width="40" >���</td>
			<td width="50">����</td>
			<td width="70">���ƺ�</td>
			<td width="90">�ֻ�����</td>
			<td width="80">��װ����</td>
			<td width="50">�һ���</td>
			<td width="50">������</td>
			<td width="50">������</td>
			<td width="50">������</td>
			<td width="50">������</td>
			<td width="50">������</td>
			<td width="50">�绰��</td>
		</tr>
    </table>
</s:form>
</body>
</html>
