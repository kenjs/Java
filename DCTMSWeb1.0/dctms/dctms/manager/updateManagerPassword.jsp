<!DOCTYPE HTML>
<html>
<%@ page language="java" contentType="text/html;charset=gbk"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>����Ա�����޸�</title>
<script type="text/javascript">
	function saveBtn(){
		if(!validatecheckpassword()){
		    return;
	    }
		var password = trim($("#mainForm_managerInfoDomain_password").val());
		if(password==null|password==''){
			document.getElementById("ispassword").innerHTML="���벻��Ϊ��";
			return;
		}
		var url = jcontextPath+"/updateManagerPassword";  
    	var jsonObj = {"managerInfoDomain.password":password};
		ajaxCommon(url,jsonObj,"doBack");
	}
	function doBack(data){
		if(confirm("����ɹ�!�Ƿ�ر�ҳ��")){
			doYesCallBack();
		}
	}
	
	//ѡ���ǵķ��ش���
	function doYesCallBack(){
		window.close();
	}
	//��֤������������һ��
	function validatecheckpassword(){
		var checkpassword = document.getElementById("mainForm_managerInfoDomain_checkpassword").value;
		var password = document.getElementById("mainForm_managerInfoDomain_password").value;
		if(checkpassword!=password){
			document.getElementById("ischeckpassword").innerHTML="�������벻һ��";
			return false;
		}
		document.getElementById("ischeckpassword").innerHTML="";
		return true;
		
	}
</script>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<base target="_self" />
</head>

<body>
<s:form id="mainForm" action="/saveManagerInfo" namespace="/" method="post">
	  <table style=" margin:40px auto;"  > 
	  <caption>����Ա�����޸�</caption>
		    <tr style="margin:40px auto; ">
		      <td align="right" ><span style="color:red; padding:0 5px;">*</span>�����룺</td>
		       <td  align="left">      
		      <s:password name="managerInfoDomain.password" style="border:1px solid #999;width:200px; height:20px; margin-left:10px;" type="text"  >     </s:password>
		      </td>
		   	<td  align="left" width="30%"><font class="redfont" id="ispassword"></font></td>
		    </tr>
		    <tr style="margin:40px auto; ">
		      <td align="right" width="40%"><span style="color:red; padding:0 5px;">*</span>����ȷ�ϣ�</td>
		       <td align="left" width="30%">      
		      <s:password name="managerInfoDomain.checkpassword" style="border:1px solid #999;width:200px; height:20px; margin-left:10px;" type="text"  onblur="validatecheckpassword()">      </s:password>
		      </td>
		      <td  align="left" width="30%"><font class="redfont" id="ischeckpassword"></font></td>
		    </tr>
	   </table>
		<table >
    		<tr >
    			<td style="width:620px; height:100px; text-align:center;"><input onclick="saveBtn()" style="width:80px; border-radius:3px; height:30px; margin-left:10px; margin-right:20px;background:#2b94f1; border:1px solid #3596f0; color:white; font-size:16px; cursor:pointer;"value="����" type="button" />
        		<input  id="closeWindow" style="width:80px; border-radius:3px; height:30px; margin-left:10px; margin-right:20px;background:#2b94f1; border:1px solid #3596f0; color:white;font-size:16px; cursor:pointer;" value="�ر�" type="button" /></td>
    		</tr>
		</table>
</s:form>
</body>
</html>