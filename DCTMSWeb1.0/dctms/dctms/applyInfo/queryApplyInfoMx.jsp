<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>身份证验证申请明细信息</title>
<script type="text/javascript">
	var isPass; 
	function saveBtn(verifyStart){
		var id = trim($("#mainForm_applyInfoDomain_id").val());
		var userId = trim($("#mainForm_applyInfoDomain_userId").val());
		var verifyComment = trim($("#mainForm_applyInfoDomain_verifyComment").val());
		var applyType = trim($("#mainForm_applyInfoDomain_applyType").val());
		if(verifyStart==-1){
			if(verifyComment==null||verifyComment==""){
				alert("请输入回复内容");
				return;
			}
			isPass = "不通过";
		}else isPass = "通过";
		var url = jcontextPath+"/saveApplyInfo";  
		var data = {"applyInfoDomain.id":id
				,"applyInfoDomain.userId":userId
				,"applyInfoDomain.verifyStart":verifyStart
				,"applyInfoDomain.verifyComment":verifyComment
				,"applyInfoDomain.applyType":applyType
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
	function callBackList(text){
		document.getElementById("verifyStartTd").innerHTML = isPass;
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
</style>
<base target="_self" />
</head>

<body>
<s:form id="mainForm" action="/saveApplyInfo" namespace="/" method="post">
<s:hidden name="applyInfoDomain.id"></s:hidden>
<s:hidden name="applyInfoDomain.userId"></s:hidden>
<s:hidden name="applyInfoDomain.applyType"></s:hidden>
	  <table style=" margin:40px auto;"  > 
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%">申请公司：</td> 
              <td width="35%" align="left">   
             ${applyInfoDomain.companyId}
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%">申请功能：</td> 
              <td width="35%" align="left">  
              <s:if test="applyInfoDomain.applyType==0">
             		 货主版功能
              </s:if> 
              <s:elseif test="applyInfoDomain.applyType==1" >
             		 身份证查询
              </s:elseif>
              <s:elseif test="applyInfoDomain.applyType==2" >
           			 VIP功能
              </s:elseif>
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%">申请时间：</td> 
              <td width="35%" align="left">   
             ${applyInfoDomain.applyTime}
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%">申请人姓名：</td> 
              <td width="35%" align="left">   
             ${applyInfoDomain.contactName}
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%">申请人电话：</td> 
              <td width="35%" align="left">   
             ${applyInfoDomain.contactTelephone}
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%">申请内容：</td> 
              <td width="35%" align="left">   
             ${applyInfoDomain.applyComment}
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%">审核时间：</td> 
              <td width="35%" align="left">   
             ${applyInfoDomain.verifyTime}
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%">审核状态：</td> 
              <td width="35%" align="left" id="verifyStartTd">   
             ${applyInfoDomain.verifyStart}
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%">审核回复内容：</td> 
              <td width="35%" align="left">   
             <s:textarea name="applyInfoDomain.verifyComment"></s:textarea>
               </td>  
            </tr >
	   </table>
		<table >
    		<tr >
    			<td style="width:620px; height:100px; text-align:center;">
	    			<input onclick="saveBtn(1)" style="width:80px; border-radius:3px; height:30px; margin-left:10px; margin-right:20px;background:#2b94f1; border:1px solid #3596f0; color:white; font-size:16px; cursor:pointer;"value="通过" type="button" />
	    			<input onclick="saveBtn(-1)" style="width:80px; border-radius:3px; height:30px; margin-left:10px; margin-right:20px;background:#2b94f1; border:1px solid #3596f0; color:white; font-size:16px; cursor:pointer;"value="不通过" type="button" />
	        		<input  id="closeWindow" style="width:80px; border-radius:3px; height:30px; margin-left:10px; margin-right:20px;background:#2b94f1; border:1px solid #3596f0; color:white;font-size:16px; cursor:pointer;" value="关闭" type="button" />
	        	</td>
    		</tr>
		</table>
</s:form>
</body>
</html>
