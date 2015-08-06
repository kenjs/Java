<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>��ѯ������Ϣ</title>
<script type="text/javascript">
	window.onload=function(){
		queryConditionInit();
		queryInfoInit();
	} ;
	function queryInfo(){
		var data ={
				"transactionInfoDomain.driverId":trim(document.getElementById("mainForm_transactionInfoDomain_driverId").value),
				"transactionInfoDomain.deployUserid":trim(document.getElementById("mainForm_transactionInfoDomain_deployUserid").value),
				"transactionInfoDomain.cargoId":trim(document.getElementById("mainForm_transactionInfoDomain_cargoId").value),
				"transactionInfoDomain.orderNumber":trim(document.getElementById("mainForm_transactionInfoDomain_orderNumber").value),
				"transactionInfoDomain.tradeFair":trim(document.getElementById("mainForm_transactionInfoDomain_tradeFair").value),
				"transactionInfoDomain.tradeStart":trim(document.getElementById("mainForm_transactionInfoDomain_tradeStart").value),
				"transactionInfoDomain.orderStart":trim(document.getElementById("mainForm_transactionInfoDomain_orderStart").value),
				"transactionInfoDomain.remark":trim(document.getElementById("mainForm_transactionInfoDomain_remark").value),
				"transactionInfoDomain.tradeCancelOrigin":trim(document.getElementById("mainForm_transactionInfoDomain_tradeCancelOrigin").value),
				"transactionInfoDomain.queryTimeQ":trim(document.getElementById("mainForm_transactionInfoDomain_queryTimeQ").value),
				"transactionInfoDomain.queryTimeZ":trim(document.getElementById("mainForm_transactionInfoDomain_queryTimeZ").value),
				"transactionInfoDomain.pageInfo.curPage" :trim(document.getElementById("curPage").value),
				"transactionInfoDomain.pageInfo.curPageNo" : trim(document.getElementById("curPageNo").value)
		};
		var url = jcontextPath+"/queryTransactionInfo";
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
		var dataList = text.transactionInfoDomain.dataList;
		var totalPages = text.transactionInfoDomain.pageInfo.totalPages;//��ҳ��
		var curPageNos = text.transactionInfoDomain.pageInfo.curPageNo;//��ǰҳ��
		var totalRecords = text.transactionInfoDomain.pageInfo.totalRecords;//�ܼ�¼��
        var columus =["orderNumber","tradeFair","tradeStart","tradeStartTime","orderStart","remark","tradeCancelOrigin","cargoId","driverId","userId","companyId","cargoName","driverCode","driverName","userCode","userName","companyName","createTime"];
		var operateObject = {'updateTransactionInfo':'�޸�','deleteTransactionInfo':'ɾ��'};
		var operateObjectMultiParameter = {'jumpHtml(3,4,':['cargoId','����'],'jumpHtml(3,2,':['deployUserid','��ҵ'],'jumpHtml(3,1,':['driverId','˾��'],'jumpHtml(3,11,':['id','������ʷ״̬']};
		showPageTable(dataList,columus,curPageNos,1,operateObject,operateObjectMultiParameter);	
		pageInfo(totalPages,curPageNos,totalRecords);
	}
	function updateTransactionInfo(id){
		var url = jcontextPath+"/queryTransactionInfoMx?transactionInfoDomain.id="+id+"&random="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	} 
	function addInfo(){
		var url = jcontextPath+"/queryTransactionInfoMx?transactionInfoDomain.id="+0;
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	}
	function deleteTransactionInfo(id){
		if(confirm("ȷ��Ҫɾ��ô��")){
			yesCallBack(id);
		}
	}	
	function yesCallBack(id){
		 var jsonObj = {"transactionInfoDomain.id":id};
		 var url = jcontextPath+"/deleteTransactionInfo";   
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
<s:form id="mainForm" action="/exportTransactionInfo" namespace="/" method="post">
<s:hidden name="transactionInfoDomain.driverId"></s:hidden>
<s:hidden name="transactionInfoDomain.deployUserid"></s:hidden>
<s:hidden name="transactionInfoDomain.cargoId"></s:hidden>


<table  class="conditionTable">
             <tr>            
                <td width="200px">�������<s:textfield name="transactionInfoDomain.orderNumber"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">���׽��<s:textfield name="transactionInfoDomain.tradeFair"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">����״̬<s:textfield name="transactionInfoDomain.tradeStart"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">����״̬<s:textfield name="transactionInfoDomain.orderStart"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">��ע<s:textfield name="transactionInfoDomain.remark"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                </tr>
                <tr>
                <td colspan="2">��������
                <s:textfield name="transactionInfoDomain.queryTimeQ" cssClass="ymdate"></s:textfield>��<s:textfield name="transactionInfoDomain.queryTimeZ" cssClass="ymdate"></s:textfield>
                 </td>
                <td >
				<input type="button" onclick="queryInfoInit();" value="��ѯ" class="selectBtn"/>
				<input type="button" onclick="exportInfo();" value="����" class="selectBtn"/>
				</td>
               <td width="200px">ȡ����Դ<s:textfield name="transactionInfoDomain.tradeCancelOrigin"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
            </tr>
        </table>
	<table class="mainTable" style="width: 840px" id="dataList">
		<tr class="mainTopTr">
			<td width="40" >���</td>
			<td width="300">����</td>
			<td width="200">�������</td>
			<td width="100">���׽��</td>
			<td width="100">����״̬</td>
			<td width="130">�޸�ʱ��</td>
			<td width="100">����״̬</td>
			<td width="300">��ע</td>
			<td width="100">����ȡ����Դ</td>
			<td width="100">��ԴID</td>
			<td width="100">��ԴID</td>
			<td width="100">�û�ID</td>
			<td width="100">��ҵID</td>
			<td width="100">��������</td>
			<td width="100">˾���˺�</td>
			<td width="100">˾������</td>
			<td width="100">�û��˺�</td>
			<td width="100">�û�����</td>
			<td width="100">��ҵ����</td>
			<td width="130">����ʱ��</td>
			
		</tr>
    </table>
    <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage'  value='${transactionInfoDomain.pageInfo.curPage}'/><!-- ��Ҫ��ʾҳ�� -->
    <input type='hidden' id='curPageNo' value='${transactionInfoDomain.pageInfo.curPageNo}'/><!-- ��ǰҳ�� -->
    <input type='hidden' id='transactionInfoDomain.pageInfo.pageSize' value='${transactionInfoDomain.pageInfo.pageSize}'/><!-- ��ǰҳ�� -->
</s:form>
</body>
</html>
