<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>��ѯ��ҵ��˾������</title>
<script type="text/javascript">
	window.onload=function(){
		queryConditionInit();
		queryInfoInit();
	} ;
	function queryInfo(){
		var data ={
				"userDriverAssessInfoDomain.cargoId":trim(document.getElementById("mainForm_userDriverAssessInfoDomain_cargoId").value),
				"userDriverAssessInfoDomain.driverId":trim(document.getElementById("mainForm_userDriverAssessInfoDomain_driverId").value),
				"userDriverAssessInfoDomain.userId":trim(document.getElementById("mainForm_userDriverAssessInfoDomain_userId").value),
				"userDriverAssessInfoDomain.tradeEvaluateScore":trim(document.getElementById("mainForm_userDriverAssessInfoDomain_tradeEvaluateScore").value),
				"userDriverAssessInfoDomain.cargoName":trim(document.getElementById("mainForm_userDriverAssessInfoDomain_cargoName").value),
				"userDriverAssessInfoDomain.driverCode":trim(document.getElementById("mainForm_userDriverAssessInfoDomain_driverCode").value),
				"userDriverAssessInfoDomain.userCode":trim(document.getElementById("mainForm_userDriverAssessInfoDomain_userCode").value),
				"userDriverAssessInfoDomain.orderNumber":trim(document.getElementById("mainForm_userDriverAssessInfoDomain_orderNumber").value),
				"userDriverAssessInfoDomain.queryTimeQ":trim(document.getElementById("mainForm_userDriverAssessInfoDomain_queryTimeQ").value),
				"userDriverAssessInfoDomain.queryTimeZ":trim(document.getElementById("mainForm_userDriverAssessInfoDomain_queryTimeZ").value),
				"userDriverAssessInfoDomain.pageInfo.curPage" :trim(document.getElementById("curPage").value),
				"userDriverAssessInfoDomain.pageInfo.curPageNo" : trim(document.getElementById("curPageNo").value)
		};
		var url = jcontextPath+"/queryUserDriverAssessInfo";
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
		var dataList = text.userDriverAssessInfoDomain.dataList;
		var totalPages = text.userDriverAssessInfoDomain.pageInfo.totalPages;//��ҳ��
		var curPageNos = text.userDriverAssessInfoDomain.pageInfo.curPageNo;//��ǰҳ��
		var totalRecords = text.userDriverAssessInfoDomain.pageInfo.totalRecords;//�ܼ�¼��
		 var columus =["tradeEvaluateScore","cargoId","cargoName","driverId","driverCode","driverName","userId","userCode","userName","transactionId","orderNumber","arriverEvaluateScore","serveEvaluateScore","assess","createTime"];
		var operateObject = {'updateUserDriverAssessInfo':'�޸�','deleteUserDriverAssessInfo':'ɾ��'};
		var operateObjectMultiParameter = {'jumpHtml(6,1,':['driverId','˾��'],'jumpHtml(6,2,':['userId','������']};
		showPageTable(dataList,columus,curPageNos,1,operateObject,operateObjectMultiParameter);
		pageInfo(totalPages,curPageNos,totalRecords);
	}
	function updateUserDriverAssessInfo(id){
		var url = jcontextPath+"/queryUserDriverAssessInfoMx?userDriverAssessInfoDomain.id="+id+"&random="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	} 
	function addInfo(){
		var url = jcontextPath+"/queryUserDriverAssessInfoMx?userDriverAssessInfoDomain.id="+0;
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	}
	function deleteUserDriverAssessInfo(id){
		if(confirm("ȷ��ҪӲɾ��ô��")){
			yesCallBack(id);
		}
	}	
	function yesCallBack(id){
		alert("��֧��Ӳɾ��");
		return;
		 var jsonObj = {"userDriverAssessInfoDomain.id":id};
		 var url = jcontextPath+"/deleteUserDriverAssessInfo";   
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
<s:form id="mainForm" action="/exportUserDriverAssessInfo" namespace="/" method="post">
<s:hidden name="userDriverAssessInfoDomain.userId"></s:hidden>
<s:hidden name="userDriverAssessInfoDomain.driverId"></s:hidden>

<table  class="conditionTable">
           <tr>   
             <td width="200px">������
                	<s:select name="userDriverAssessInfoDomain.tradeEvaluateScore" list="#{'':'',3:'����',6:'����',9:'����'}"/>
                </td>    
                <td width="200px">��ԴID<s:textfield name="userDriverAssessInfoDomain.cargoId"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">��������<s:textfield name="userDriverAssessInfoDomain.cargoName"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">˾���˺�<s:textfield name="userDriverAssessInfoDomain.driverCode"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                 <td width="200px">������<s:textfield name="userDriverAssessInfoDomain.orderNumber"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
               </tr>
               <tr>
               <td colspan="2">��������
                <s:textfield name="userDriverAssessInfoDomain.queryTimeQ" cssClass="ymdate"></s:textfield>��<s:textfield name="userDriverAssessInfoDomain.queryTimeZ" cssClass="ymdate"></s:textfield>
                 </td>
                <td >
				<input type="button" onclick="queryInfoInit();" value="��ѯ" class="selectBtn"/>
				<input type="button" onclick="exportInfo();" value="����" class="selectBtn"/>
				</td>
                 <td width="200px">��ҵ�˺�<s:textfield name="userDriverAssessInfoDomain.userCode"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
            </tr> 
            
            
            
        </table>
	<table class="mainTable" style="width: 840px" id="dataList">
		<tr class="mainTopTr">
			<td width="40" >���</td>
			<td width="200">����</td>
			<td width="100">����������</td>
			<td width="100">��ԴID</td>
			<td width="100">��������</td>
			<td width="100">˾��ID</td>
			<td width="100">˾���˺�</td>
			<td width="100">˾������</td>
			<td width="100">������Id</td>
			<td width="100">�������˺�</td>
			<td width="100">����������</td>
			<td width="100">���׶���Id</td>
			<td width="200">������</td>
			<td width="100">�����ٶ�</td>
			<td width="100">˾������̬��</td>
			<td width="300">����</td>
			<td width="130">����ʱ��</td>
		</tr>
    </table>
    <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage'  value='${userDriverAssessInfoDomain.pageInfo.curPage}'/><!-- ��Ҫ��ʾҳ�� -->
    <input type='hidden' id='curPageNo' value='${userDriverAssessInfoDomain.pageInfo.curPageNo}'/><!-- ��ǰҳ�� -->
    <input type='hidden' id='userDriverAssessInfoDomain.pageInfo.pageSize' value='${userDriverAssessInfoDomain.pageInfo.pageSize}'/><!-- ��ǰҳ�� -->
</s:form>
</body>
</html>
