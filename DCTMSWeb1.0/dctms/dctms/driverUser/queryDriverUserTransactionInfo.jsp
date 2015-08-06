<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>��ѯ˾����Ϣ</title>
<script type="text/javascript">
	window.onload=function(){
		queryInfoInit();
	} ;
	function queryInfo(){
		var data ={
				"driverUserInfoDomain.code":trim(document.getElementById("mainForm_driverUserInfoDomain_code").value),
				"driverUserInfoDomain.name":trim(document.getElementById("mainForm_driverUserInfoDomain_name").value),
				"driverUserInfoDomain.carNumber":trim(document.getElementById("mainForm_driverUserInfoDomain_carNumber").value),
				"driverUserInfoDomain.registerTimeQ":trim(document.getElementById("mainForm_driverUserInfoDomain_registerTimeQ").value),
				"driverUserInfoDomain.registerTimeZ":trim(document.getElementById("mainForm_driverUserInfoDomain_registerTimeZ").value),
				"driverUserInfoDomain.currentLocationTimeQ":trim(document.getElementById("mainForm_driverUserInfoDomain_currentLocationTimeQ").value),
				"driverUserInfoDomain.currentLocationTimeZ":trim(document.getElementById("mainForm_driverUserInfoDomain_currentLocationTimeZ").value),
				"driverUserInfoDomain.auditFlag":"",//Ŀ���Ƿ�ֹdomain��ԭ��������˱�־��
				"driverUserInfoDomain.pageInfo.curPage" :trim(document.getElementById("curPage").value),
				"driverUserInfoDomain.pageInfo.curPageNo" : trim(document.getElementById("curPageNo").value)
		};
		var url = jcontextPath+"/queryDriverUserTransactionInfo";
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
		var totalPages = text.driverUserInfoDomain.pageInfo.totalPages;//��ҳ��
		var curPageNos = text.driverUserInfoDomain.pageInfo.curPageNo;//��ǰҳ��
		var totalRecords = text.driverUserInfoDomain.pageInfo.totalRecords;//�ܼ�¼��
        var columus =["code","name","carNumber","accumulateTransactionCount","todayTransactionCount","location","lastTime","driverBusinessLine","driverLine","submitType"];
		showPageTable(dataList,columus,curPageNos,0);
		pageInfo(totalPages,curPageNos,totalRecords);
	}
	function updateDriverUserInfo(id){
		var url = jcontextPath+"/queryDriverUserInfoMx?driverUserInfoDomain.id="+id;
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	} 
	function addInfo(){
		var url = jcontextPath+"/queryDriverUserInfoMx?driverUserInfoDomain.id="+0;
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	}
	function deleteDriverUserInfo(id){
		if(confirm("ȷ��Ҫ����ô��")){
			yesCallBack(id);
		}
	}	
	function yesCallBack(id){
		 var jsonObj = {"driverUserInfoDomain.id":id};
		 var url = jcontextPath+"/deleteDriverUserInfo";   
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
        alert("����ɹ���");
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
<s:form id="mainForm" action="/exportDriverUserInfo" namespace="/" method="post">


<table  class="conditionTable">

             <tr>            
                <td width="200px">�ֻ�����<s:textfield name="driverUserInfoDomain.code"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">˾������<s:textfield name="driverUserInfoDomain.name"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">���ƺ�<s:textfield name="driverUserInfoDomain.carNumber"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
               <td colspan="2">ע��ʱ��
                <s:textfield name="driverUserInfoDomain.registerTimeQ" cssClass="ymdate"></s:textfield>��<s:textfield name="driverUserInfoDomain.registerTimeZ" cssClass="ymdate"></s:textfield>
                 </td>
                </tr>
                <tr>
                <td colspan="2">��ǰλ��ʱ��
                <s:textfield name="driverUserInfoDomain.currentLocationTimeQ" cssClass="ymdate"></s:textfield>��<s:textfield name="driverUserInfoDomain.currentLocationTimeZ" cssClass="ymdate"></s:textfield>
                 </td>
                <td >
                <input type="button" onclick="queryInfoInit();" value="��ѯ" class="selectBtn"/>
<%--                <input type="button" onclick="exportInfo();" value="����" class="selectBtn"/>--%>
                </td>
            </tr>
        </table>
	<table class="mainTable" style="width: 840px" id="dataList">
		<tr class="mainTopTr">
		
		
		
		
			<td width="40" >���</td>
			<td width="100">��¼�ʺ�</td>
			<td width="100">˾������</td>
			<td width="100">������</td>
			<td width="200">�ۼƽ��׼�¼����</td>
			<td width="200">���ս��׼�¼����</td>
			<td width="200">��ǰλ��</td>
			<td width="150">���λʱ��</td>
			<td width="300">ԤԼ��·</td>
			<td width="300">��Ӫ��·</td>
			<td width="100">�Ƿ���֤ͨ��</td>
		</tr>
    </table>
    <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage' value='${driverUserInfoDomain.pageInfo.curPage}'/><!-- ��Ҫ��ʾҳ�� -->
    <input type='hidden' id='curPageNo'  value='${driverUserInfoDomain.pageInfo.curPageNo}'/><!-- ��ǰҳ�� -->
    <input type='hidden' id='driverUserInfoDomain.pageInfo.pageSize'  value='${driverUserInfoDomain.pageInfo.pageSize}'/><!-- ��ǰҳ�� -->
</s:form>
</body>
</html>
