<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>�ְ����û�����</title>
<%@ include file="/common/meta.jsp"%>
<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		 $("#closeBtn").click(function(){
			window.close();
		})
	
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			
			// ���������ȷ�������Ƿ�һ��
			var pwd = trim($("#mainForm_domain_pwd").val()); 
			var pwd1 = trim($("#mainForm_domain_pwd1").val()); 
			if (pwd != pwd1){
				showAlert("������ȷ�����벻һ��");
				return;
			}	
			
			var mc = trim($("#mainForm_domain_mc").val()); 
			var zh = trim($("#mainForm_domain_zh").val()); 
			var dlyzfsDm = trim($("#mainForm_domain_dlyzfsDm").val()); 
			var yhDjxh = trim($("#mainForm_domain_yhDjxh").val()); 
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
			var fbsDjxh = trim($("#mainForm_domain_fbsDjxh").val()); 

			var url = jcontextPath+"/zygl/fbsyhgl!save";  
	    	var jsonObj = {"domain.mc":mc,"domain.zh":zh,"domain.pwd":pwd,"domain.dlyzfsDm":dlyzfsDm,
                           "domain.yhDjxh":yhDjxh,"domain.ssJgbm":ssJgbm,"domain.fbsDjxh":fbsDjxh};   			
			ajaxCommon(url,jsonObj);
		});
	});
	
	function checkdata(){
		var controlNameArray = ["domain.mc","domain.zh","domain.pwd","domain.pwd1","domain.dlyzfsDm"];
		var labelNameArray = ["����","�˺�","����","ȷ������","��¼��֤��ʽ"];
		var compareValueArray = [40,40,100,100,2];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.INTEGER];
		var notNullArray = [true,true,true,true,true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="fbsyhgl!initMx" namespace="/zygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.yhDjxh"></s:hidden>
	<s:hidden name="domain.ssJgbm"></s:hidden>
	<s:hidden name="domain.fbsDjxh"></s:hidden>
		<div class="pop_contc" style="height:280px; overflow:auto;">
		   <fieldset>
		    <legend>������Ϣ</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="15%" align="right"><font class="font_red">*</font>���ƣ�</td>
      				<td width="70%">
      					<s:textfield name="domain.mc" cssClass="pop_input noborder bgstyle_required" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right"><font class="font_red">*</font>�˺ţ�</td>
      				<td>
      					<s:textfield name="domain.zh" cssClass="pop_input noborder bgstyle_required" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right"><font class="font_red">*</font>���룺</td>
      				<td>
      					<s:password name="domain.pwd" cssClass="pop_input noborder bgstyle_required" ></s:password>
      				</td>
      			</tr>
      			<tr>
      				<td align="right"><font class="font_red">*</font>ȷ�����룺</td>
      				<td>
      					<s:password name="domain.pwd1" cssClass="pop_input noborder bgstyle_required" ></s:password>
      				</td>
      			</tr>
      			<tr>
      				<td align="right"><font class="font_red">*</font>��¼��֤��ʽ��</td>
      				<td>
      					<sys:Dlyzfs myId="mainForm_domain_dlyzfsDm" myName="domain.dlyzfsDm" contaisQxz="true" myClass="select"></sys:Dlyzfs>
      				</td>
      			</tr>

			</table>
			</fieldset>
			<div class="pop_btn">
			 	<button type="button" class="pop_btnbg" id="saveBtn">�� ��</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
		    </div>
		</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
