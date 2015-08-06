<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>��ѯ��ҵ�ɽ���¼</title>
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
      //�����ܼ�
		var table = document.getElementById("dataList");
		var tr = table.insertRow(1);
		tr.insertCell(0).innerHTML = '�ܼ�';
		tr.insertCell(1).innerHTML = '��'+(table.rows.length-2)+'��ҵ';
		tr.insertCell(2).innerHTML = '';
		tr.insertCell(3).innerHTML = '';
		tr.insertCell(4).innerHTML = '';
		tr.insertCell(5).innerHTML = '';
		tr.insertCell(6).innerHTML = text.webUserInfoDomain.accumulateCargoCount;
		tr.insertCell(7).innerHTML = text.webUserInfoDomain.orderCargoCount;
		tr.insertCell(8).innerHTML = text.webUserInfoDomain.orderCargoRate;
		tr.insertCell(9).innerHTML = text.webUserInfoDomain.accumulateTransactionCount;
		tr.insertCell(10).innerHTML = text.webUserInfoDomain.accumulateTransactionRate;
		//��ĳһ����ͬ�����ݺϲ�
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
               <td width="300px">ע��ʱ��
                <s:textfield name="webUserInfoDomain.registerTimeQ" cssClass="ymdate"></s:textfield>��<s:textfield name="webUserInfoDomain.registerTimeZ" cssClass="ymdate"></s:textfield>
                 </td>
                 <td width="200px">��˾����<s:textfield name="webUserInfoDomain.companyName"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td >
                <input type="button" onclick="queryInfo();" value="��ѯ" class="selectBtn"/>
                <input type="button" onclick="exportInfo();" value="����" class="selectBtn"/>
                </td>
            </tr>
        </table>
	<table class="mainTable" style="width: 715px" id="dataList">
		<tr class="mainTopTr">
			<td width="30" >���</td>
			<td width="100">��ҵ����</td>
			<td width="100">�ʺ�</td>
			<td width="90">��ϵ�绰</td>
			<td width="60">�ʺ�����</td>
			<td width="80">ע������</td>
			<td width="45">��Դ��</td>
			<td width="45">������</td>
			<td width="60">������</td>
			<td width="45">�ɽ���</td>
			<td width="60">�ɽ���</td>
		</tr>
    </table>
</s:form>
</body>
</html>
