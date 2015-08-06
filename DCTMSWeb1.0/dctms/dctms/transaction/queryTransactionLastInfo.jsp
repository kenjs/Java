<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>��ѯ������ʷ״̬</title>
<script type="text/javascript">
	window.onload=function(){
		queryConditionInit();
		queryInfoInit();
	} ;
	function queryInfo(){
		var data ={
				"transactionLastInfoDomain.transactionId":trim(document.getElementById("mainForm_transactionLastInfoDomain_transactionId").value)
		};
		var url = jcontextPath+"/queryTransactionLastInfo";
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
		var dataList = text.transactionLastInfoDomain.dataList;
		var totalPages = text.transactionLastInfoDomain.pageInfo.totalPages;//��ҳ��
		var curPageNos = text.transactionLastInfoDomain.pageInfo.curPageNo;//��ǰҳ��
		var totalRecords = text.transactionLastInfoDomain.pageInfo.totalRecords;//�ܼ�¼��
        var columus =["cargoId","driverId","driverCode","driverName","transactionId","start","remark"];
		showPageTable(dataList,columus,curPageNos,0);
		pageInfo(totalPages,curPageNos,totalRecords);
	}
	function updateTransactionLastInfo(id){
		var url = jcontextPath+"/queryTransactionLastInfoMx?transactionLastInfoDomain.id="+id+"&random="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	} 
	function addInfo(){
		var url = jcontextPath+"/queryTransactionLastInfoMx?transactionLastInfoDomain.id="+0;
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	}
	function deleteTransactionLastInfo(id){
		if(confirm("ȷ��Ҫɾ��ô��")){
			yesCallBack(id);
		}
	}	
	function yesCallBack(id){
		 var jsonObj = {"transactionLastInfoDomain.id":id};
		 var url = jcontextPath+"/deleteTransactionLastInfo";   
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
        alert("ɾ���ɹ���");
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
<s:form id="mainForm" action="/exportTransactionLastInfo" namespace="/" method="post">
<s:hidden name="transactionLastInfoDomain.transactionId"></s:hidden>

<table  class="conditionTable">
             <tr>            
                <td >
				<input type="button" onclick="queryInfoInit();" value="��ѯ" class="selectBtn"/>
				<input type="button" onclick="exportInfo();" value="����" class="selectBtn"/>
				</td>
            </tr>
        </table>
	<table class="mainTable" style="width: 840px" id="dataList">
		<tr class="mainTopTr">
			<td width="40" >���</td>
			<td width="100">��ԴID</td>
			<td width="100">˾��ID</td>
			<td width="100">˾���˺�</td>
			<td width="100">˾������</td>
			<td width="100">���׶���ID</td>
			<td width="100">״̬</td>
			<td width="100">��ע</td>
		</tr>
    </table>
    <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage'  value='${transactionLastInfoDomain.pageInfo.curPage}'/><!-- ��Ҫ��ʾҳ�� -->
    <input type='hidden' id='curPageNo' value='${transactionLastInfoDomain.pageInfo.curPageNo}'/><!-- ��ǰҳ�� -->
    <input type='hidden' id='transactionLastInfoDomain.pageInfo.pageSize' value='${transactionLastInfoDomain.pageInfo.pageSize}'/><!-- ��ǰҳ�� -->
</s:form>
</body>
</html>
