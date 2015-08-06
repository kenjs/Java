<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>��ѯ��ҵ</title>
<script type="text/javascript">
	window.onload=function(){
		queryInfo();
	} ;
	function queryInfo(){
		var data ={
				"webUserInfoDomain.code":trim(document.getElementById("mainForm_webUserInfoDomain_code").value),
				"webUserInfoDomain.name":trim(document.getElementById("mainForm_webUserInfoDomain_name").value),
				"webUserInfoDomain.companyName":trim(document.getElementById("mainForm_webUserInfoDomain_companyName").value),
				"webUserInfoDomain.mobilephone":trim(document.getElementById("mainForm_webUserInfoDomain_mobilephone").value),
				"webUserInfoDomain.submitType":trim(document.getElementById("mainForm_webUserInfoDomain_submitType").value),
				"webUserInfoDomain.enterpriseTimeQ":trim(document.getElementById("mainForm_webUserInfoDomain_enterpriseTimeQ").value),
				"webUserInfoDomain.enterpriseTimeZ":trim(document.getElementById("mainForm_webUserInfoDomain_enterpriseTimeZ").value),
		};
		var url = jcontextPath+"/queryWebUserInfo";
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
		var totalPages = text.webUserInfoDomain.pageInfo.totalPages;//��ҳ��
		var curPageNos = text.webUserInfoDomain.pageInfo.curPageNo;//��ǰҳ��
		var totalRecords = text.webUserInfoDomain.pageInfo.totalRecords;//�ܼ�¼��
        var columus =["code","name","companyName","mobilephone","contactName","email","submitType","modifyTime"];
		showPageTable(dataList,columus,curPageNos,1,operateObject);
		pageInfo(totalPages,curPageNos,totalRecords);
	}
	function auditWebUserInfo(id){
		var url = jcontextPath+"/auditWebUserInfoMx?webUserInfoDomain.id="+id+"&random="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:700px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
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
<s:form id="mainForm" action="/exportWebUserInfo" namespace="/" method="post">



<table  class="conditionTable">
             <tr>            
                <td width="200px">��¼����<s:textfield name="webUserInfoDomain.code"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">�û�����<s:textfield name="webUserInfoDomain.name"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
               <td width="200px">��˾����<s:textfield name="webUserInfoDomain.companyName"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
               <td width="200px">�ֻ�����<s:textfield name="webUserInfoDomain.mobilephone"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
               </tr>
               <tr>
               <td >���״̬
               <s:select name="webUserInfoDomain.submitType" list="#{1:'���ύ',0:'δ�ύ',2:'δͨ��',3:'��ͨ��'}" >
                </s:select>
                <td colspan="2">��������
                <s:textfield name="webUserInfoDomain.enterpriseTimeQ" cssClass="ymdate"></s:textfield>��<s:textfield name="webUserInfoDomain.enterpriseTimeZ" cssClass="ymdate"></s:textfield>
                 </td>
                <td >
                	<input type="button" onclick="queryInfoInit();" value="��ѯ" class="selectBtn"/>
                </td>
            </tr>
        </table>
        
	<table class="mainTable" style="width: 840px" id="dataList">
		<tr class="mainTopTr">
			<td width="40" >���</td>
			<td width="60">����</td>
			<td width="100">��¼����</td>
			<td width="100">�û�����</td>
			<td width="300">��˾����</td>
			<td width="100">�ֻ�����</td>
			<td width="100">��ϵ��</td>
			<td width="200">��������</td>
			<td width="200">��֤�Ƿ�ͨ��</td>
			<td width="130">����ʱ��</td>
		</tr>
    </table>
</s:form>
</body>
</html>
