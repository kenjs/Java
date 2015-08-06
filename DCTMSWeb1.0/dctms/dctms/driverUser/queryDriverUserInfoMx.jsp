<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>˾����Ϣ��ϸ��Ϣ</title>
<script type="text/javascript">
	function saveBtn(){
		var id = trim($("#mainForm_driverUserInfoDomain_id").val());
		var name = trim($("#mainForm_driverUserInfoDomain_name").val());
		var carNumber = trim($("#mainForm_driverUserInfoDomain_carNumber").val());
		var identityLicenseNum = trim($("#mainForm_driverUserInfoDomain_identityLicenseNum").val());
		var url = jcontextPath+"/saveDriverUserInfo";  
		var data = {"driverUserInfoDomain.id":id
				,"driverUserInfoDomain.name":name
				,"driverUserInfoDomain.carNumber":carNumber
				,"driverUserInfoDomain.identityLicenseNum":identityLicenseNum
				};
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
	function deleteBtn(){
		var reason = trim($("#mainForm_driverUserInfoDomain_reason").val());
		if(reason==null||reason==""){
			alert("�����붳������");
			return;
		}
		if(confirm("ȷ��Ҫ����ô��")){
			yesCallBack();
		}
	}
	function yesCallBack(){
		var id = trim($("#mainForm_driverUserInfoDomain_id").val());
		var reason = trim($("#mainForm_driverUserInfoDomain_reason").val());
		 var jsonObj = {"driverUserInfoDomain.id":id
				 ,"driverUserInfoDomain.deleteReason":reason
				 ,"driverUserInfoDomain.freezeFlag":0};
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
        if(confirm("����ɹ�!�Ƿ�ر�ҳ��")){
			doYesCallBack();
		}
	}	
	function callBackList(text){
		if(confirm("����ɹ�!�Ƿ�ر�ҳ��")){
			doYesCallBack();
		}
	}
	
	//ѡ���ǵķ��ش���
	function doYesCallBack(){
		window.close();
	}
</script>
<style type="text/css">
html,body {overflow-x:hidden;}
</style>
<base target="_self" />
</head>

<body>
<s:form id="mainForm" action="/saveDriverUserInfo" namespace="/" method="post">
<s:hidden name="driverUserInfoDomain.id"></s:hidden>
<s:if test="driverUserInfoDomain.deleteOrModifyFlag!=0">
	  <table class="mxTable"> 
	    <tr >  
               <td align="right" width="15%">��¼�ʺţ�</td> 
              <td width="35%" align="left">   
             ${driverUserInfoDomain.code }
               </td>  
            </tr >
	    <tr >  
               <td align="right" width="15%">˾��������</td> 
              <td width="35%" align="left">   
             <s:textfield name="driverUserInfoDomain.name"  type="text"  ></s:textfield>  
               </td>  
            </tr >
	    <tr >  
               <td align="right" width="15%">�������պ��룺</td> 
              <td width="35%" align="left">   
             <s:textfield name="driverUserInfoDomain.carNumber"  type="text"  ></s:textfield>  
               </td>  
            </tr >
            <tr>
               <td align="right" width="15%">���֤��</td> 
              <td width="35%" align="left">   
             <s:textfield name="driverUserInfoDomain.identityLicenseNum"  type="text"  ></s:textfield>  
               </td>  
            </tr >	 
	   </table>
		<table class="mxBtnTable">
    		<tr >
    			<td ><input onclick="saveBtn()" value="����" type="button" />
        		<input  id="closeWindow"  value="�ر�" type="button" /></td>
    		</tr>
		</table>
</s:if>
<s:else>
 <table class="mxTable"> 
	    <tr >  
               <td align="right" width="15%">��¼�ʺţ�</td> 
              <td width="35%" align="left">   
             ${driverUserInfoDomain.code }
               </td>  
            </tr >
	    <tr >  
               <td align="right" width="15%">˾��������</td> 
              <td width="35%" align="left">   
             ${driverUserInfoDomain.name }
               </td>  
            </tr >
	    <tr >  
               <td align="right" width="15%">�������պ��룺</td> 
              <td width="35%" align="left">   
             ${driverUserInfoDomain.carNumber }
               </td>  
            </tr >
	    <tr >  
               <td align="right" width="15%">���֤��</td> 
              <td width="35%" align="left">   
             ${driverUserInfoDomain.identityLicenseNum }
               </td>  
        </tr >
        <tr >  
               <td align="right" width="15%">���ɣ�</td> 
              <td width="35%" align="left">   
             <s:textarea name="driverUserInfoDomain.reason"></s:textarea>
               </td>  
        </tr >
	   </table>
		<table class="mxBtnTable">
    		<tr >
    			<td ><input onclick="deleteBtn()" value="����" type="button" />
        		<input  id="closeWindow"  value="�ر�" type="button" /></td>
    		</tr>
		</table>
</s:else>		
</s:form>
</body>
</html>
