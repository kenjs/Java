<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>���֤��֤������ϸ��Ϣ</title>
<script type="text/javascript">
	var isPass; 
	function saveBtn(verifyStart){
		var id = trim($("#mainForm_applyInfoDomain_id").val());
		var userId = trim($("#mainForm_applyInfoDomain_userId").val());
		var verifyComment = trim($("#mainForm_applyInfoDomain_verifyComment").val());
		var applyType = trim($("#mainForm_applyInfoDomain_applyType").val());
		if(verifyStart==-1){
			if(verifyComment==null||verifyComment==""){
				alert("������ظ�����");
				return;
			}
			isPass = "��ͨ��";
		}else isPass = "ͨ��";
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
               <td align="right" width="15%">���빫˾��</td> 
              <td width="35%" align="left">   
             ${applyInfoDomain.companyId}
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%">���빦�ܣ�</td> 
              <td width="35%" align="left">  
              <s:if test="applyInfoDomain.applyType==0">
             		 �����湦��
              </s:if> 
              <s:elseif test="applyInfoDomain.applyType==1" >
             		 ���֤��ѯ
              </s:elseif>
              <s:elseif test="applyInfoDomain.applyType==2" >
           			 VIP����
              </s:elseif>
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%">����ʱ�䣺</td> 
              <td width="35%" align="left">   
             ${applyInfoDomain.applyTime}
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%">������������</td> 
              <td width="35%" align="left">   
             ${applyInfoDomain.contactName}
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%">�����˵绰��</td> 
              <td width="35%" align="left">   
             ${applyInfoDomain.contactTelephone}
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%">�������ݣ�</td> 
              <td width="35%" align="left">   
             ${applyInfoDomain.applyComment}
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%">���ʱ�䣺</td> 
              <td width="35%" align="left">   
             ${applyInfoDomain.verifyTime}
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%">���״̬��</td> 
              <td width="35%" align="left" id="verifyStartTd">   
             ${applyInfoDomain.verifyStart}
               </td>  
            </tr >
	    <tr style="margin:40px auto; ">  
               <td align="right" width="15%">��˻ظ����ݣ�</td> 
              <td width="35%" align="left">   
             <s:textarea name="applyInfoDomain.verifyComment"></s:textarea>
               </td>  
            </tr >
	   </table>
		<table >
    		<tr >
    			<td style="width:620px; height:100px; text-align:center;">
	    			<input onclick="saveBtn(1)" style="width:80px; border-radius:3px; height:30px; margin-left:10px; margin-right:20px;background:#2b94f1; border:1px solid #3596f0; color:white; font-size:16px; cursor:pointer;"value="ͨ��" type="button" />
	    			<input onclick="saveBtn(-1)" style="width:80px; border-radius:3px; height:30px; margin-left:10px; margin-right:20px;background:#2b94f1; border:1px solid #3596f0; color:white; font-size:16px; cursor:pointer;"value="��ͨ��" type="button" />
	        		<input  id="closeWindow" style="width:80px; border-radius:3px; height:30px; margin-left:10px; margin-right:20px;background:#2b94f1; border:1px solid #3596f0; color:white;font-size:16px; cursor:pointer;" value="�ر�" type="button" />
	        	</td>
    		</tr>
		</table>
</s:form>
</body>
</html>
