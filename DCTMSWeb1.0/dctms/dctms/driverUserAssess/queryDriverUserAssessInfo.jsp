<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>��ѯ˾������ҵ����</title>
<script type="text/javascript">
	window.onload=function(){
		queryConditionInit();
		queryInfoInit();
	} ;
	function queryInfo(){
		var data ={
				"driverUserAssessInfoDomain.driverId":trim(document.getElementById("mainForm_driverUserAssessInfoDomain_driverId").value),
				"driverUserAssessInfoDomain.cargoName":trim(document.getElementById("mainForm_driverUserAssessInfoDomain_cargoName").value),
				"driverUserAssessInfoDomain.driverCode":trim(document.getElementById("mainForm_driverUserAssessInfoDomain_driverCode").value),
				"driverUserAssessInfoDomain.userId":trim(document.getElementById("mainForm_driverUserAssessInfoDomain_userId").value),
				"driverUserAssessInfoDomain.userCode":trim(document.getElementById("mainForm_driverUserAssessInfoDomain_userCode").value),
				"driverUserAssessInfoDomain.orderNumber":trim(document.getElementById("mainForm_driverUserAssessInfoDomain_orderNumber").value),
				"driverUserAssessInfoDomain.assessEvaluateScore":trim(document.getElementById("mainForm_driverUserAssessInfoDomain_assessEvaluateScore").value),
				"driverUserAssessInfoDomain.queryTimeQ":trim(document.getElementById("mainForm_driverUserAssessInfoDomain_queryTimeQ").value),
				"driverUserAssessInfoDomain.queryTimeZ":trim(document.getElementById("mainForm_driverUserAssessInfoDomain_queryTimeZ").value),
				"driverUserAssessInfoDomain.pageInfo.curPage" :trim(document.getElementById("curPage").value),
				"driverUserAssessInfoDomain.pageInfo.curPageNo" : trim(document.getElementById("curPageNo").value)
		};
		var url = jcontextPath+"/queryDriverUserAssessInfo";
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
		var dataList = text.driverUserAssessInfoDomain.dataList;
		var totalPages = text.driverUserAssessInfoDomain.pageInfo.totalPages;//��ҳ��
		var curPageNos = text.driverUserAssessInfoDomain.pageInfo.curPageNo;//��ǰҳ��
		var totalRecords = text.driverUserAssessInfoDomain.pageInfo.totalRecords;//�ܼ�¼��
        var columus =["assessEvaluateScore","cargoId","cargoName","userId","userCode","userName","driverId","driverCode","driverName","transactionId","orderNumber","assess","createTime"];
		var operateObject = {'updateDriverUserAssessInfo':'�޸�','deleteDriverUserAssessInfo':'ɾ��'};
		var operateObjectMultiParameter = {'jumpHtml(7,2,':['userId','��ҵ'],'jumpHtml(7,1,':['driverId','������']};
		showPageTable(dataList,columus,curPageNos,1,operateObject,operateObjectMultiParameter);
		pageInfo(totalPages,curPageNos,totalRecords);
	}
	function updateDriverUserAssessInfo(id){
		var url = jcontextPath+"/queryDriverUserAssessInfoMx?driverUserAssessInfoDomain.id="+id+"&random="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	} 
	function addInfo(){
		var url = jcontextPath+"/queryDriverUserAssessInfoMx?driverUserAssessInfoDomain.id="+0;
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	}
	function deleteDriverUserAssessInfo(id){
		if(confirm("ȷ��ҪӲɾ��ô��")){
			yesCallBack(id);
		}
	}	
	function yesCallBack(id){
		alert("��֧��Ӳɾ��");
		return;
		 var jsonObj = {"driverUserAssessInfoDomain.id":id};
		 var url = jcontextPath+"/deleteDriverUserAssessInfo";   
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
<s:form id="mainForm" action="/exportDriverUserAssessInfo" namespace="/" method="post">
<s:hidden name="driverUserAssessInfoDomain.driverId"></s:hidden>
<s:hidden name="driverUserAssessInfoDomain.userId"></s:hidden>

<table  class="conditionTable">
             <tr>   
             <td width="200px">����
                	<s:select name="driverUserAssessInfoDomain.assessEvaluateScore" list="#{'':'',3:'����',6:'����',9:'����'}"/>
                </td>    
                <td width="200px">��ԴID<s:textfield name="driverUserAssessInfoDomain.cargoId"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">��������<s:textfield name="driverUserAssessInfoDomain.cargoName"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">˾���˺�<s:textfield name="driverUserAssessInfoDomain.driverCode"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                 <td width="200px">������<s:textfield name="driverUserAssessInfoDomain.orderNumber"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
               </tr>
               <tr>
               <td colspan="2">��������
                <s:textfield name="driverUserAssessInfoDomain.queryTimeQ" cssClass="ymdate"></s:textfield>��<s:textfield name="driverUserAssessInfoDomain.queryTimeZ" cssClass="ymdate"></s:textfield>
                 </td>
                <td >
				<input type="button" onclick="queryInfoInit();" value="��ѯ" class="selectBtn"/>
				<input type="button" onclick="exportInfo();" value="����" class="selectBtn"/>
				</td>
                 <td width="200px">��ҵ�˺�<s:textfield name="driverUserAssessInfoDomain.userCode"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
            </tr>
        </table>
	<table class="mainTable" style="width: 840px" id="dataList">
		<tr class="mainTopTr">
			<td width="40" >���</td>
			<td width="200">����</td>
			<td width="100">����</td>
			<td width="100">��ԴID</td>
			<td width="100">��������</td>
			<td width="100">��ҵID</td>
			<td width="100">��ҵ�˺�</td>
			<td width="100">��ҵ����</td>
			<td width="100">������Id</td>
			<td width="100">�������˺�</td>
			<td width="100">����������</td>
			<td width="100">���׶���Id</td>
			<td width="200">������</td>
			<td width="300">����</td>
			<td width="130">����ʱ��</td>
		</tr>
    </table>
    <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage'  value='${driverUserAssessInfoDomain.pageInfo.curPage}'/><!-- ��Ҫ��ʾҳ�� -->
    <input type='hidden' id='curPageNo' value='${driverUserAssessInfoDomain.pageInfo.curPageNo}'/><!-- ��ǰҳ�� -->
    <input type='hidden' id='driverUserAssessInfoDomain.pageInfo.pageSize' value='${driverUserAssessInfoDomain.pageInfo.pageSize}'/><!-- ��ǰҳ�� -->
</s:form>
</body>
</html>
