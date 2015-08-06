<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>��ҵ��ϸ��Ϣ</title>
<script type="text/javascript">
	function saveBtn(){
		var companyId = trim($("#mainForm_webUserInfoDomain_companyId").val());
		var companyName = trim($("#mainForm_webUserInfoDomain_companyName").val());
		var businessLicence = trim($("#mainForm_webUserInfoDomain_businessLicence").val());
		var organizationCode = trim($("#mainForm_webUserInfoDomain_organizationCode").val());
		var url = jcontextPath+"/saveWebUserInfo";  
		var data = {"webUserInfoDomain.companyId":companyId
				,"webUserInfoDomain.companyName":companyName
				,"webUserInfoDomain.businessLicence":businessLicence
				,"webUserInfoDomain.organizationCode":organizationCode
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
		var reason = trim($("#mainForm_webUserInfoDomain_reason").val());
		if(reason==null||reason==""){
			alert("�����붳������");
			return;
		}
		if(confirm("ȷ��Ҫ����ô��")){
			yesCallBack();
		}
	}
	function yesCallBack(){
		var id = trim($("#mainForm_webUserInfoDomain_id").val());
		var reason = trim($("#mainForm_webUserInfoDomain_reason").val());
		 var jsonObj = {"webUserInfoDomain.id":id
				 ,"webUserInfoDomain.deleteReason":reason
				 ,"webUserInfoDomain.freezeFlag":0};
		 var url = jcontextPath+"/deleteWebUserInfo";   
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
<base target="_self" />
</style>
</head>

<body>
<s:form id="mainForm" action="/saveWebUserInfo" namespace="/" method="post">
<s:hidden name="webUserInfoDomain.companyId"></s:hidden>
<s:hidden name="webUserInfoDomain.id"></s:hidden>
<s:if test="webUserInfoDomain.deleteOrModifyFlag!=0">
	  <table class="mxTable"  > 
	   <tr >  
               <td align="right" width="15%">��¼���ƣ�</td> 
              <td width="35%" align="left">   
             ${webUserInfoDomain.code}
               </td>  
            </tr >
             <tr  >  
               <td align="right" width="15%">��˾���ƣ�</td> 
              <td width="35%" align="left">   
             <s:textfield name="webUserInfoDomain.companyName"  type="text"  ></s:textfield>  
               </td>  
            </tr >
             <tr  >  
               <td align="right" width="15%">Ӫҵִ��ע��ţ�</td> 
              <td width="35%" align="left">   
             <s:textfield name="webUserInfoDomain.businessLicence"  type="text"  ></s:textfield>  
               </td>  
            </tr >
             <tr  >  
               <td align="right" width="15%">��֯�������룺</td> 
              <td width="35%" align="left">   
             <s:textfield name="webUserInfoDomain.organizationCode"  type="text"  ></s:textfield>  
               </td>  
            </tr >
	   </table>
		<table class="mxBtnTable">
    		<tr >
    			<td ><input onclick="saveBtn()" value="�޸�" type="button" />
        		<input  id="closeWindow" value="�ر�" type="button" /></td>
    		</tr>
		</table>
</s:if>
<s:else>
		 <table class="mxTable"  > 
	    <tr  >  
               <td align="right" width="15%">��¼���ƣ�</td> 
              <td width="35%" align="left">   
             ${webUserInfoDomain.code}
               </td>  
            </tr >
             <tr  >  
               <td align="right" width="15%">��˾���ƣ�</td> 
              <td width="35%" align="left">   
             ${webUserInfoDomain.companyName}
               </td>  
            </tr >
             <tr  >  
               <td align="right" width="15%">Ӫҵִ��ע��ţ�</td> 
              <td width="35%" align="left">   
             ${webUserInfoDomain.businessLicence}
               </td>  
            </tr >
             <tr >  
               <td align="right" width="15%">��֯�������룺</td> 
              <td width="35%" align="left">   
             ${webUserInfoDomain.organizationCode}
               </td>  
            </tr >
            <tr >  
               <td align="right" width="15%">���ɣ�</td> 
              <td width="35%" align="left">   
             <s:textarea name="webUserInfoDomain.reason"></s:textarea>
               </td>  
        </tr >
	   </table>
		<table class="mxBtnTable">
    		<tr >
    			<td ><input onclick="deleteBtn()" value="����" type="button" />
        		<input  id="closeWindow" value="�ر�" type="button" /></td>
    		</tr>
		</table>
</s:else>		
</s:form>
</body>
</html>
