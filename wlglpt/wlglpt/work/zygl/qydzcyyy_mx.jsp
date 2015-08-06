<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>���˲���ԭ��</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		});
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var whXh = $("#mainForm_domain_whXh").val(); 
			var dzcyyy = trim($("#mainForm_domain_dzcyyy").val()); 
			var sm = trim($("#mainForm_domain_sm").val()); 
			var url = jcontextPath+"/zygl/qydzcyyy!save";  
	    	var jsonObj = {"domain.whXh":whXh,"domain.dzcyyy":dzcyyy,"domain.sm":sm};   			
			ajaxCommon(url,jsonObj,"doSuccess");
		});
	});
	
	function doSuccess(){
		showAlert("����ɹ���","closeWin");
	}
	
	function closeWin(){
		window.close();
	}
	
	function checkdata(){
		var controlNameArray = ["domain.dzcyyy","domain.sm"];
		var labelNameArray = ["���˲���ԭ��","˵��"];
		var compareValueArray = [50,500];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="qyclxhwh!initMx" namespace="/zygl" method="post" id="mainForm" name="mainForm">
		<s:hidden name="domain.whXh"></s:hidden>
		<div class="pop_contc">
		<fieldset>
			<legend>������Ϣ</legend>
			<table width="95%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
					<td width="20%"></td>
      				<td width="30%"></td>  
					<td width="15%"></td>
      				<td width="30%"></td>  
				</tr>
				<tr>
      				<td width="20%" align="right"><font class="font_red">*</font>���˲���ԭ��</td>
      				<td colspan="3">
      					<s:textfield name="domain.dzcyyy" cssClass="pop_input bgstyle_required" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">˵����</td>
      				<td colspan="3">
      					<s:textarea name="domain.sm" rows="3" cssClass="pop_textarea_colspan2" ></s:textarea>
      				</td>
      			</tr>
				<tr>
      				<td align="right">�����ˣ�</td>
      				<td >
      					<s:textfield name="domain.cjrMc" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">�������ڣ�</td>
      				<td >
      					<s:textfield name="domain.cjrq" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">�޸��ˣ�</td>
      				<td>
      					<s:textfield name="domain.xgrMc" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">�޸����ڣ�</td>
      				<td>
      					<s:textfield name="domain.xgrq" cssClass="pop_input bgstyle_readonly" ></s:textfield>
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
