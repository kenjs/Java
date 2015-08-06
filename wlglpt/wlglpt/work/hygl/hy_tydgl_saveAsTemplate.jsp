<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>����-���˵�-ģ�屣��</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function() {
			window.close();
		});
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var mbmc = trim($("#mainForm_domain_mbmc").val());
			var jsonObj = {"domain.mbmc":mbmc};
			var url = jcontextPath+"/hygl/hytydmbgl!checkTemplateName";  
			ajaxCommon(url,jsonObj,"checkTemplateNameBack");
		});
	});
	
	function checkTemplateNameBack(data) {
		var useable = data.domain.mcUseable;
		if (!useable) {
			showAlert("��ģ�������ѱ�ʹ�ã����������룡");
			return;
		}
		saveTemplate();
	}
	
	function saveTemplate() {
		var mbmc = trim($("#mainForm_domain_mbmc").val()); 
		var ddDjxh = $("#mainForm_domain_ddDjxh").val();

		var url = jcontextPath+"/hygl/hytydmbgl!saveAsTemplate";  
    	var jsonObj = {"domain.mbmc":mbmc,"domain.ddDjxh":ddDjxh};   			
    	showMessage();
    	ajaxCommon(url,jsonObj,"saveTemplateSuc");
	}
	
	function saveTemplateSuc(data) {
		hideMessage();
		showAlert("����ɹ�!","closeWin");
	}
	
	function closeWin() {
		window.close();
	}
	
	function checkdata(){
		var controlNameArray = ["domain.mbmc"];
		var labelNameArray = ["ģ������"];
		var compareValueArray = [100];
		var nodeTypeArray = [NodeType.STRING];
		var notNullArray = [true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="hytydmbgl!initSaveAsTemplate" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.ddDjxh"/>
		<div id="maincont">
		<div class="pop_contc" style="width: 96%">
			<fieldset>
			<legend>������Ϣ</legend>
			<table width="95%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td align="right" width="20%"><font class="font_red">*</font>ģ������</td>
      				<td width="75%">
      					<s:textfield name="domain.mbmc" cssClass="pop_input_colspan2 noborder bgstyle_required" />
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
		</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
