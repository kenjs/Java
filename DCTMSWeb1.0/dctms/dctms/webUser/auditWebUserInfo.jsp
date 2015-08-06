<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>��ѯ��ҵ</title>
<script type="text/javascript">
	window.onload=function(){
		queryInfoInit();
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
				"webUserInfoDomain.pageInfo.curPage" :trim(document.getElementById("curPage").value),
				"webUserInfoDomain.pageInfo.curPageNo" : trim(document.getElementById("curPageNo").value)
		};
		var url = jcontextPath+"/auditWebUserInfoList";
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
		var operateObject ={};
        var checkbox = {"name":"webUserInfoDomain.IdList","value":"id"};
		if(document.getElementById("mainForm_webUserInfoDomain_submitType").value=="0"){
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
		if(document.getElementById("mainForm_webUserInfoDomain_submitType").value=="1"){
        	operateObject = {'auditWebUserInfo':'���'};
		}else if(document.getElementById("mainForm_webUserInfoDomain_submitType").value=="2"){
			operateObject = {'auditWebUserInfo':'�������'};
		}else if(document.getElementById("mainForm_webUserInfoDomain_submitType").value=="3"){
			operateObject = {'auditWebUserInfo':'�������'};
		}else if(document.getElementById("mainForm_webUserInfoDomain_submitType").value=="0"){
			operateObject = {};
		}
		
		showPageTable(dataList,columus,curPageNos,1,operateObject);
		pageInfo(totalPages,curPageNos,totalRecords);
	}
	function auditWebUserInfo(id){
		var url = jcontextPath+"/auditWebUserInfoMx?webUserInfoDomain.id="+id+"&random="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:700px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		queryInfo();
	} 
	//������Ϣ
	function sendMessage(){
		var idList = document.getElementsByName("webUserInfoDomain.idList");
		var url = jcontextPath+"/sendWebMessagerInit";
		var arr = [];
		var isZeroFlag = true;
		for(var i=0;i<idList.length;i++){
			if(idList[i].checked){
				isZeroFlag =false;
				arr.push("webUserInfoDomain.idList["+i+"]="+idList[i].value);
			}
		}
		if(isZeroFlag){
			alert("��ѡ��Ҫ���͵�˾��");
			return;
		}
		var str = arr.join("&");
		url += "?webUserInfoDomain.isPushAll="+0; 
		url += "&" +str;
		url += "&random=" + Math.random();
		window.showModalDialog(url,window,"dialogHeight:740px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
	    queryInfo();
	}
	//������Ϣ
	function sendAllMessage(){
		var url = jcontextPath+"/sendWebMessagerInit";
		url += "?webUserInfoDomain.isPushAll="+1; 
		url += "&webUserInfoDomain.submitType="+ document.getElementById("mainForm_webUserInfoDomain_submitType").value;
		url += "&webUserInfoDomain.code="+ document.getElementById("mainForm_webUserInfoDomain_code").value;
		url += "&webUserInfoDomain.name="+ encodeURI(encodeURI(document.getElementById("mainForm_webUserInfoDomain_name").value));
		url += "&webUserInfoDomain.companyName="+ encodeURI(encodeURI(document.getElementById("mainForm_webUserInfoDomain_companyName").value));
		url += "&webUserInfoDomain.mobilephone="+ document.getElementById("mainForm_webUserInfoDomain_mobilephone").value;
		url += "&webUserInfoDomain.enterpriseTimeQ="+ document.getElementById("mainForm_webUserInfoDomain_enterpriseTimeQ").value;
		url += "&webUserInfoDomain.enterpriseTimeZ="+ document.getElementById("mainForm_webUserInfoDomain_enterpriseTimeZ").value;
		url += "&random=" + Math.random();
		window.showModalDialog(url,window,"dialogHeight:740px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
	    queryInfo();
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
             		  <input type="button" onclick="sendMessage();" value="ѡ��֪ͨ" class="selectBtn" id="selectPushBtn" style="display:none"/>
	                 <input type="button" onclick="sendAllMessage();" value="ȫ��֪ͨ" class="selectBtn" id="selectPushAllBtn" style="display:none"/>
                </td>
            </tr>
        </table>
        
	<table class="mainTable" style="width: 755px" id="dataList">
		<tr class="mainTopTr">
			<td width="30" >���</td>
			<td width="70" id="checkAllTitle"><input type="checkbox" id="checkAll" onclick="oncheckAll('checkAll','webUserInfoDomain.idList')"/></td>
			<td width="70" id="operationTitle">����</td>
			<td width="80">��¼����</td>
			<td width="80">����</td>
			<td width="120">��˾����</td>
			<td width="85">�ֻ�����</td>
			<td width="60">��ϵ��</td>
			<td width="80">��������</td>
			<td width="60">�Ƿ���֤</td>
			<td width="90">����ʱ��</td>
		</tr>
    </table>
    <div class="numberBox" id="pageInfoHtmlId">
    </div>
    <input type='hidden' id='curPage' value='${webUserInfoDomain.pageInfo.curPage}'/><!-- ��Ҫ��ʾҳ�� -->
    <input type='hidden' id='curPageNo'  value='${webUserInfoDomain.pageInfo.curPageNo}'/><!-- ��ǰҳ�� -->
    <input type='hidden' id='webUserInfoDomain.pageInfo.pageSize' value='${webUserInfoDomain.pageInfo.pageSize}'/><!-- ��ǰҳ�� -->
</s:form>
</body>
</html>
