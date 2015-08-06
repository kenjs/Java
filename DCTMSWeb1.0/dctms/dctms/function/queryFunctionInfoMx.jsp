<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>������ϸ��Ϣ</title>
<script type="text/javascript" src="<sys:context/>/resource/js/pageInfo.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/areaDataDict.js"></script>
<link href="<sys:context/>/resource/css/mycargo.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	$(function(){
		$("#saveBtn").click(function(){
			var id = trim($("#mainForm_functionInfoDomain_id").val());
			var number = trim($("#mainForm_functionInfoDomain_number").val());
			var name = trim($("#mainForm_functionInfoDomain_name").val());
			var parentNumber = trim($("#mainForm_functionInfoDomain_parentNumber").val());
			var urlInfo = trim($("#mainForm_functionInfoDomain_url").val());

			var url = jcontextPath+"/saveFunctionInfo";  
			var password = trim($("#mainForm_functionInfoDomain_password").val());
	    	var data = {"functionInfoDomain.id":id
			,"functionInfoDomain.number":number
			,"functionInfoDomain.name":name
			,"functionInfoDomain.parentNumber":parentNumber
			,"functionInfoDomain.url":urlInfo

	    	}
	    	AjaxSubmit({
				url:url,
				data:data,
			    method:"get",
			    async:true,
			    success:function(text){
			    	callBackList(text);
			    }
			});
		});
	});
	
	function callBackList(text){
		if(confirm("����ɹ�!�Ƿ�ر�ҳ��")){
			doYesCallBack()
		}
	}
	
	//ѡ���ǵķ��ش���
	function doYesCallBack(){
		window.close();
	}
</script>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<base target="_self" />
</head>


<body>
<s:form id="mainForm" action="/saveFunctionInfo" namespace="/" method="post">
<s:hidden name="functionInfoDomain.id"></s:hidden>
	  <table style=" margin:40px auto;"  > 
	     <tr style="margin:40px auto; "> 
 <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>��ţ�</td>  
  <td width="35%" align="left"> 
  <s:textfield name="functionInfoDomain.number" style="border:1px solid #999;width:200px; height:20px; margin-left:10px;" type="text"  ></s:textfield> 
 </td> 
 </tr >
	     <tr style="margin:40px auto; "> 
 <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>�������֣�</td>  
  <td width="35%" align="left"> 
  <s:textfield name="functionInfoDomain.name" style="border:1px solid #999;width:200px; height:20px; margin-left:10px;" type="text"  ></s:textfield> 
 </td> 
 </tr >
	     <tr style="margin:40px auto; "> 
 <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>�����ţ�</td>  
  <td width="35%" align="left"> 
  <s:textfield name="functionInfoDomain.parentNumber" style="border:1px solid #999;width:200px; height:20px; margin-left:10px;" type="text"  ></s:textfield> 
 </td> 
 </tr >
	     <tr style="margin:40px auto; "> 
 <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>URL��</td>  
  <td width="35%" align="left"> 
  <s:textfield name="functionInfoDomain.url" style="border:1px solid #999;width:200px; height:20px; margin-left:10px;" type="text"  ></s:textfield> 
 </td> 
 </tr >

	   </table>
		<table >
    		<tr >
    			<td style="width:620px; height:100px; text-align:center;"><input id="saveBtn" style="width:80px; border-radius:3px; height:30px; margin-left:10px; margin-right:20px;background:#2b94f1; border:1px solid #3596f0; color:white; font-size:16px; cursor:pointer;"value="����" type="button" />
        		<input  id="closeWindow" style="width:80px; border-radius:3px; height:30px; margin-left:10px; margin-right:20px;background:#2b94f1; border:1px solid #3596f0; color:white;font-size:16px; cursor:pointer;" value="�ر�" type="button" /></td>
    		</tr>
		</table>
</s:form>
</body>
</html>
