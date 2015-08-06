<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>����-�ʽ��������</title>

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
			var drDwDjxh = trim($("#mainForm_domain_drDwDjxh").val()); 
			//alert(drDwDjxh);
			var je = trim($("#mainForm_domain_je").val()); 
			if(je/1<=0){
				showAlert("�������������0��");
				return;
			}
			var bz = trim($("#mainForm_domain_bz").val()); 
			var zjdbDjxh = trim($("#mainForm_domain_zjdbDjxh").val()); 

			var url = jcontextPath+"/cwgl/cwzjdbgl!save";  
	    	var jsonObj = {"domain.drDwDjxh":drDwDjxh,"domain.je":je,"domain.bz":bz,"domain.zjdbDjxh":zjdbDjxh};   			
			ajaxCommon(url,jsonObj,"saveSuc");
		});
	});
	function saveSuc(){
		showAlert("����ɹ���","closeWin");
	}
	function closeWin(){
		window.close();
	}
	function checkdata(){
		var controlNameArray = ["domain.je","domain.bz"];
		var labelNameArray = ["�������","��ע˵��"];
		var compareValueArray = [14.2,500];
		var nodeTypeArray = [NodeType.DECIMAL,NodeType.STRING];
		var notNullArray = [true,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="cwgl/cwzjdbgl!initMx.action" namespace="" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.zjdbDjxh"></s:hidden>
	<div class="pop_contc">
	<fieldset>
			<legend>������Ϣ</legend>
			<table width="95%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				
      			<tr>
      				<td align="center" width="25%">ת�뵥λ</td>
      				<td width="70%">
      					<sys:fgsList myId="mainForm_domain_drDwDjxh" myName="domain.drDwDjxh" myClass="select" contaisDq="N"></sys:fgsList>
      				</td>
      			</tr>
      			<tr>
      				<td align="center"><font class="font_red">*</font>�������</td>
      				<td>
      					<s:textfield name="domain.je" cssClass="pop_input noborder bgstyle_required" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td width="25%" align="center">ת����λ</td>
      				<td width="70%">
      					<s:textfield name="domain.dcDwMc" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="center">��ע˵��</td>
      				<td>
      					<s:textarea name="domain.bz" rows="3" cssClass="pop_textarea_colspan2" ></s:textarea>
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
