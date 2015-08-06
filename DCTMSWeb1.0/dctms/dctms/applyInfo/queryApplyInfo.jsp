<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>��ѯ���֤��֤����</title>
<script type="text/javascript">
	window.onload=function(){
		queryInfoInit();
	} ;
	function queryInfo(){
		var data ={
				"applyInfoDomain.companyId":trim(document.getElementById("mainForm_applyInfoDomain_companyId").value),
				"applyInfoDomain.applyType":trim(document.getElementById("mainForm_applyInfoDomain_applyType").value),
				"applyInfoDomain.applyTimeQ":trim(document.getElementById("mainForm_applyInfoDomain_applyTimeQ").value),
				"applyInfoDomain.applyTimeZ":trim(document.getElementById("mainForm_applyInfoDomain_applyTimeZ").value),
				"applyInfoDomain.contactName":trim(document.getElementById("mainForm_applyInfoDomain_contactName").value),
				"applyInfoDomain.verifyStart":trim(document.getElementById("mainForm_applyInfoDomain_verifyStart").value),
				"applyInfoDomain.pageInfo.curPage" :trim(document.getElementById("curPage").value),
				"applyInfoDomain.pageInfo.curPageNo" : trim(document.getElementById("curPageNo").value)
		};
		var url = jcontextPath+"/queryApplyInfo";
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
		var dataList = text.applyInfoDomain.dataList;
		var totalPages = text.applyInfoDomain.pageInfo.totalPages;//��ҳ��
		var curPageNos = text.applyInfoDomain.pageInfo.curPageNo;//��ǰҳ��
		var totalRecords = text.applyInfoDomain.pageInfo.totalRecords;//�ܼ�¼��
        var columus =["companyId","applyType","applyTime","contactName","contactTelephone","applyComment","verifyTime","verifyStart","verifyComment"];
		var operateObject = {'auditApplyInfo':'���'};
		showPageTable(dataList,columus,curPageNos,1,operateObject);
		pageInfo(totalPages,curPageNos,totalRecords);
	}
	function auditApplyInfo(id){
		var url = jcontextPath+"/queryApplyInfoMx?applyInfoDomain.id="+id+"&random="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	} 
</script>
<style type="text/css">
</style>
</head>

<body  onselect="false">
<s:form id="mainForm" action="/exportApplyInfo" namespace="/" method="post">



<table  class="conditionTable">
             <tr>            
                <td width="200px">��˾<s:textfield name="applyInfoDomain.companyId"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">����<s:textfield name="applyInfoDomain.contactName"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
	                <td width="200px">���빦��
	                 <s:select name="applyInfoDomain.applyType" list="#{'':'ȫ��',0:'�����湦��',1:'���֤��ѯ',2:'VIP����'}">
	                </s:select>
	                </td>
	                 <td width="200px">���״̬
	                 <s:select name="applyInfoDomain.verifyStart" list="#{0:'�ȴ����',-1:'���ʧ��',1:'��˳ɹ�'}">
	                </s:select>
	                </td>
                </tr>
                <tr>
	                <td colspan="2">����ʱ��
	                <s:textfield name="applyInfoDomain.applyTimeQ" cssClass="ymdate"></s:textfield>��<s:textfield name="applyInfoDomain.applyTimeZ" cssClass="ymdate"></s:textfield>
	                </td>
                <td >
				<input type="button" onclick="queryInfoInit();" value="��ѯ" class="selectBtn"/>
				</td>
            </tr>
        </table>
	<table class="mainTable" style="width: 840px" id="dataList">
		<tr class="mainTopTr">
			<td width="40" >���</td>
			<td width="70">����</td>
			<td width="100">��˾</td>
			<td width="100">���빦��</td>
			<td width="90">����ʱ��</td>
			<td width="100">����</td>
			<td width="100">�绰</td>
			<td width="100">��������</td>
			<td width="90">���ʱ��</td>
			<td width="100">���״̬</td>
			<td width="100">�ظ�����</td>
		</tr>
    </table>
    <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage'  value='${applyInfoDomain.pageInfo.curPage}'/><!-- ��Ҫ��ʾҳ�� -->
    <input type='hidden' id='curPageNo' value='${applyInfoDomain.pageInfo.curPageNo}'/><!-- ��ǰҳ�� -->
    <input type='hidden' id='applyInfoDomain.pageInfo.pageSize' value='${applyInfoDomain.pageInfo.pageSize}'/><!-- ��ǰҳ�� -->
</s:form>
</body>
</html>
