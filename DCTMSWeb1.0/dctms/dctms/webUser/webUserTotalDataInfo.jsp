<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>��ѯ��ҵ</title>
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
		document.getElementById("mainForm_webUserInfoDomain_registerTimeQ").value=last;
		document.getElementById("mainForm_webUserInfoDomain_registerTimeZ").value=last;
		queryInfo();
	} ;
	function queryInfo(){
		var data ={
				"webUserInfoDomain.code":trim(document.getElementById("mainForm_webUserInfoDomain_code").value),
				"webUserInfoDomain.name":trim(document.getElementById("mainForm_webUserInfoDomain_name").value),
				"webUserInfoDomain.companyName":trim(document.getElementById("mainForm_webUserInfoDomain_companyName").value),
				"webUserInfoDomain.mobilephone":trim(document.getElementById("mainForm_webUserInfoDomain_mobilephone").value),
				"webUserInfoDomain.registerTimeQ":trim(document.getElementById("mainForm_webUserInfoDomain_registerTimeQ").value),
				"webUserInfoDomain.registerTimeZ":trim(document.getElementById("mainForm_webUserInfoDomain_registerTimeZ").value)
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
        var columus =["code","name","companyName","mobilephone","contactName","email","submitType","modifyTime"];
		showPageTable(dataList,columus,1,0);
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
                <td colspan="2">ע��ʱ��
                <s:textfield name="webUserInfoDomain.registerTimeQ" cssClass="ymdate"></s:textfield>��<s:textfield name="webUserInfoDomain.registerTimeZ" cssClass="ymdate"></s:textfield>
                 </td>
                <td >
                	<input type="button" onclick="queryInfo();" value="��ѯ" class="selectBtn"/>
                </td>
            </tr>
        </table>
        
	<table class="mainTable" style="width: 755px" id="dataList">
		<tr class="mainTopTr">
			<td width="30" >���</td>
			<td width="80">��¼����</td>
			<td width="80">����</td>
			<td width="140">��˾����</td>
			<td width="85">�ֻ�����</td>
			<td width="60">��ϵ��</td>
			<td width="80">��������</td>
			<td width="60">�Ƿ���֤</td>
			<td width="90">����ʱ��</td>
			
			
		</tr>
    </table>
</s:form>
</body>
</html>
