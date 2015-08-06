<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>企业明细信息</title>
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
			alert("请输入冻结理由");
			return;
		}
		if(confirm("确定要冻结么？")){
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
        if(confirm("冻结成功!是否关闭页面")){
			doYesCallBack();
		}
	}
	function callBackList(text){
		if(confirm("保存成功!是否关闭页面")){
			doYesCallBack();
		}
	}
	
	//选择是的返回处理
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
               <td align="right" width="15%">登录名称：</td> 
              <td width="35%" align="left">   
             ${webUserInfoDomain.code}
               </td>  
            </tr >
             <tr  >  
               <td align="right" width="15%">公司名称：</td> 
              <td width="35%" align="left">   
             <s:textfield name="webUserInfoDomain.companyName"  type="text"  ></s:textfield>  
               </td>  
            </tr >
             <tr  >  
               <td align="right" width="15%">营业执照注册号：</td> 
              <td width="35%" align="left">   
             <s:textfield name="webUserInfoDomain.businessLicence"  type="text"  ></s:textfield>  
               </td>  
            </tr >
             <tr  >  
               <td align="right" width="15%">组织机构代码：</td> 
              <td width="35%" align="left">   
             <s:textfield name="webUserInfoDomain.organizationCode"  type="text"  ></s:textfield>  
               </td>  
            </tr >
	   </table>
		<table class="mxBtnTable">
    		<tr >
    			<td ><input onclick="saveBtn()" value="修改" type="button" />
        		<input  id="closeWindow" value="关闭" type="button" /></td>
    		</tr>
		</table>
</s:if>
<s:else>
		 <table class="mxTable"  > 
	    <tr  >  
               <td align="right" width="15%">登录名称：</td> 
              <td width="35%" align="left">   
             ${webUserInfoDomain.code}
               </td>  
            </tr >
             <tr  >  
               <td align="right" width="15%">公司名称：</td> 
              <td width="35%" align="left">   
             ${webUserInfoDomain.companyName}
               </td>  
            </tr >
             <tr  >  
               <td align="right" width="15%">营业执照注册号：</td> 
              <td width="35%" align="left">   
             ${webUserInfoDomain.businessLicence}
               </td>  
            </tr >
             <tr >  
               <td align="right" width="15%">组织机构代码：</td> 
              <td width="35%" align="left">   
             ${webUserInfoDomain.organizationCode}
               </td>  
            </tr >
            <tr >  
               <td align="right" width="15%">理由：</td> 
              <td width="35%" align="left">   
             <s:textarea name="webUserInfoDomain.reason"></s:textarea>
               </td>  
        </tr >
	   </table>
		<table class="mxBtnTable">
    		<tr >
    			<td ><input onclick="deleteBtn()" value="冻结" type="button" />
        		<input  id="closeWindow" value="关闭" type="button" /></td>
    		</tr>
		</table>
</s:else>		
</s:form>
</body>
</html>
