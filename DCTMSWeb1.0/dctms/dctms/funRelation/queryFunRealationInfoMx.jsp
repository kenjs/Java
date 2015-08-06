<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>赋权信息明细信息</title>
<script type="text/javascript" src="<sys:context/>/resource/js/pageInfo.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/areaDataDict.js"></script>
<link href="<sys:context/>/resource/css/mycargo.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
	$(function(){
		$("#saveBtn").click(function(){
			var id = trim($("#mainForm_funRealationInfoDomain_id").val());
			var number = trim($("#mainForm_funRealationInfoDomain_number").val());
			var name = trim($("#mainForm_funRealationInfoDomain_name").val());
			var parentNumber = trim($("#mainForm_funRealationInfoDomain_parentNumber").val());
			var urlFun = trim($("#mainForm_funRealationInfoDomain_url").val());

			var url = jcontextPath+"/saveFunRealationInfo";  
			var password = trim($("#mainForm_funRealationInfoDomain_password").val());
	    	var data = {"funRealationInfoDomain.id":id
			,"funRealationInfoDomain.number":number
			,"funRealationInfoDomain.name":name
			,"funRealationInfoDomain.parentNumber":parentNumber
			,"funRealationInfoDomain.url":urlFun

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
		if(confirm("保存成功!是否关闭页面")){
			doYesCallBack()
		}
	}
	
	//选择是的返回处理
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
<s:form id="mainForm" action="/saveFunRealationInfo" namespace="/" method="post">
<s:hidden name="funRealationInfoDomain.id"></s:hidden>
	  <table style=" margin:40px auto;"  > 
	     <tr style="margin:40px auto; "> 
 <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>编号：</td>  
  <td width="35%" align="left"> 
  <s:textfield name="funRealationInfoDomain.number" style="border:1px solid #999;width:200px; height:20px; margin-left:10px;" type="text"  ></s:textfield> 
 </td> 
 </tr >
	     <tr style="margin:40px auto; "> 
 <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>功能名字：</td>  
  <td width="35%" align="left"> 
  <s:textfield name="funRealationInfoDomain.name" style="border:1px solid #999;width:200px; height:20px; margin-left:10px;" type="text"  ></s:textfield> 
 </td> 
 </tr >
	     <tr style="margin:40px auto; "> 
 <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>父类编号：</td>  
  <td width="35%" align="left"> 
  <s:textfield name="funRealationInfoDomain.parentNumber" style="border:1px solid #999;width:200px; height:20px; margin-left:10px;" type="text"  ></s:textfield> 
 </td> 
 </tr >
	     <tr style="margin:40px auto; "> 
 <td align="right" width="15%"><span style="color:red; padding:0 5px;">*</span>URL：</td>  
  <td width="35%" align="left"> 
  <s:textfield name="funRealationInfoDomain.url" style="border:1px solid #999;width:200px; height:20px; margin-left:10px;" type="text"  ></s:textfield> 
 </td> 
 </tr >

	   </table>
		<table >
    		<tr >
    			<td style="width:620px; height:100px; text-align:center;"><input id="saveBtn" style="width:80px; border-radius:3px; height:30px; margin-left:10px; margin-right:20px;background:#2b94f1; border:1px solid #3596f0; color:white; font-size:16px; cursor:pointer;"value="保存" type="button" />
        		<input  id="closeWindow" style="width:80px; border-radius:3px; height:30px; margin-left:10px; margin-right:20px;background:#2b94f1; border:1px solid #3596f0; color:white;font-size:16px; cursor:pointer;" value="关闭" type="button" /></td>
    		</tr>
		</table>
</s:form>
</body>
</html>
