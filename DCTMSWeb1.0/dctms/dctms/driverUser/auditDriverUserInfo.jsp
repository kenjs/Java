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
				"driverUserInfoDomain.submitType":trim(document.getElementById("mainForm_driverUserInfoDomain_submitType").value),
				"driverUserInfoDomain.commitAuditTimeQ":trim(document.getElementById("mainForm_driverUserInfoDomain_commitAuditTimeQ").value),
				"driverUserInfoDomain.commitAuditTimeZ":trim(document.getElementById("mainForm_driverUserInfoDomain_commitAuditTimeZ").value),
				"driverUserInfoDomain.pageInfo.curPage" :trim(document.getElementById("curPage").value),
				"driverUserInfoDomain.pageInfo.curPageNo" : trim(document.getElementById("curPageNo").value)
		};
		var url = jcontextPath+"/auditDriverUserInfoList";
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
        var columus =["name","code","carNumber","submitType","modifyTime","location","lastTime"];
		var operateObject ={};
        var checkbox = {"name":"driverUserInfoDomain.idList","value":"id"};
		if(document.getElementById("mainForm_driverUserInfoDomain_submitType").value=="0"){
			document.getElementById("operationTitle").style.display="none";
			document.getElementById("checkAllTitle").style.display="";//Ϊ����IE�ͻ������block
			//��ʾȫѡ�󣬲���ʼ��ѡ��ֵΪ��
			document.getElementById("checkAll").checked = false;
			//��ʾ����ť
<%--			document.getElementById("selectPushBtn").style.display="";--%>
<%--			document.getElementById("selectPushAllBtn").style.display="";--%>
			document.getElementById("selectPushBtn").style.display="none";
			document.getElementById("selectPushAllBtn").style.display="none";
			showPageTable(dataList,columus,curPageNos,0,{},{},checkbox);
			pageInfo(totalPages,curPageNos,totalRecords);
			return;
		}else{
			document.getElementById("checkAllTitle").style.display="none";
			document.getElementById("operationTitle").style.display="";
			document.getElementById("selectPushBtn").style.display="none";
			document.getElementById("selectPushAllBtn").style.display="none";
		}
		if(document.getElementById("mainForm_driverUserInfoDomain_submitType").value=="1"){
			operateObject = {'auditDriverUserInfo':'���'};
		}
		if(document.getElementById("mainForm_driverUserInfoDomain_submitType").value=="2"){
			operateObject = {'auditDriverUserInfo':'�������'};
		}if(document.getElementById("mainForm_driverUserInfoDomain_submitType").value=="3"){
			operateObject = {'auditDriverUserInfo':'�������'};
		}
		showPageTable(dataList,columus,curPageNos,1,operateObject);
		pageInfo(totalPages,curPageNos,totalRecords);
	}
	function auditDriverUserInfo(id){
		var url = jcontextPath+"/auditDriverUserInfoMx?driverUserInfoDomain.id="+id+"&random="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:740px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
		//window.open(url,"_blank","Height=700px,width=900px,resizable=no,status=no,scrollbars=yes,help=no,minimize=yes,left=220px");
	} 
	//������Ϣ
	function sendMessage(){
		var idList = document.getElementsByName("driverUserInfoDomain.idList");
		var url = jcontextPath+"/pushMessagerInit";
		var arr = [];
		var isZeroFlag = true;
		for(var i=0;i<idList.length;i++){
			if(idList[i].checked){
				isZeroFlag =false;
				arr.push("driverUserInfoDomain.idList["+i+"]="+idList[i].value);
			}
		}
		if(isZeroFlag){
			alert("��ѡ��Ҫ���͵�˾��");
			return;
		}
		var str = arr.join("&");
		url += "?driverUserInfoDomain.isPushAll="+0; 
		url += "&" +str;
		url += "&random=" + Math.random();
		window.showModalDialog(url,window,"dialogHeight:740px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
	    queryInfo();
	}
	//������Ϣ
	function sendAllMessage(){
		var url = jcontextPath+"/pushMessagerInit";
		url += "?driverUserInfoDomain.isPushAll="+1; 
		url += "&driverUserInfoDomain.submitType="+ document.getElementById("mainForm_driverUserInfoDomain_submitType").value;
		url += "&driverUserInfoDomain.code="+ document.getElementById("mainForm_driverUserInfoDomain_code").value;
		url += "&driverUserInfoDomain.name="+ encodeURI(encodeURI(document.getElementById("mainForm_driverUserInfoDomain_name").value));
		url += "&driverUserInfoDomain.carNumber="+ encodeURI(encodeURI(document.getElementById("mainForm_driverUserInfoDomain_carNumber").value));
		url += "&driverUserInfoDomain.commitAuditTimeQ="+ document.getElementById("mainForm_driverUserInfoDomain_commitAuditTimeQ").value;
		url += "&driverUserInfoDomain.commitAuditTimeZ="+ document.getElementById("mainForm_driverUserInfoDomain_commitAuditTimeZ").value;
		url += "&random=" + Math.random();
		window.showModalDialog(url,window,"dialogHeight:740px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
	    queryInfo();
	}
</script>
<style type="text/css">
</style>
</head>

<body  onselect="false">
<s:form id="mainForm" action="/exportDriverUserInfo" namespace="/" method="post">
<table  class="conditionTable">
             <tr>            
                <td width="150px">���״̬
                <s:select name="driverUserInfoDomain.submitType" list="#{1:'���ύ',0:'δ�ύ',2:'δͨ��',3:'��ͨ��'}">
                </s:select>
                 <td width="200px">�ֻ�����<s:textfield name="driverUserInfoDomain.code"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">����<s:textfield name="driverUserInfoDomain.name"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                <td width="200px">���ƺ���<s:textfield name="driverUserInfoDomain.carNumber"  style="width:120px; height:20px; margin-left:10px;" ></s:textfield>  </td>
                </tr>
                <tr>
                <td colspan="2">��������
                <s:textfield name="driverUserInfoDomain.commitAuditTimeQ" cssClass="ymdate"></s:textfield>��<s:textfield name="driverUserInfoDomain.commitAuditTimeZ" cssClass="ymdate"></s:textfield>
                 </td>
                <td >
	                <input type="button" onclick="queryInfoInit();" value="��ѯ" class="selectBtn"/>
	                 <input type="button" onclick="sendMessage();" value="ѡ��֪ͨ" class="selectBtn" id="selectPushBtn" style="display:none"/>
	                 <input type="button" onclick="sendAllMessage();" value="ȫ��֪ͨ" class="selectBtn" id="selectPushAllBtn" style="display:none"/>
                </td>
            </tr>
        </table>
	<table class="mainTable" style="width: 775px" id="dataList">
		<tr class="mainTopTr">
			<td width="40" >���</td>
			<td width="70" id="checkAllTitle"><input type="checkbox" id="checkAll" onclick="oncheckAll('checkAll','driverUserInfoDomain.idList')"/></td>
			<td width="70" id="operationTitle">����</td>
			<td width="80">˾������</td>
			<td width="85">�ֻ�����</td>
			<td width="70">���ƺ���</td>
			<td width="60">�Ƿ���֤</td>
			<td width="90">��֤�ύʱ��</td>
			<td width="190">��ǰλ��</td>
			<td width="90">���¶�λʱ��</td>
		</tr>
    </table>
    <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage'  value='${driverUserInfoDomain.pageInfo.curPage}'/><!-- ��Ҫ��ʾҳ�� -->
    <input type='hidden' id='curPageNo'  value='${driverUserInfoDomain.pageInfo.curPageNo}'/><!-- ��ǰҳ�� -->
    <input type='hidden' id='driverUserInfoDomain.pageInfo.pageSize'  value='${driverUserInfoDomain.pageInfo.pageSize}'/><!-- ��ǰҳ�� -->
</s:form>
</body>
</html>
