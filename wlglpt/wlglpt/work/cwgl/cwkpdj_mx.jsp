<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>����-��Ʊ�Ǽ�</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		$("#saveBtn").click(function(){
			var kpDjxh = trim($("#mainForm_domain_kpDjxh").val()); 

			var url = jcontextPath+"/cwgl/cwkpdj!save";  
	    	var jsonObj = {"domain.kpDjxh":kpDjxh};   			
			ajaxCommon(url,jsonObj,"saveSuc");
		});
	});
	function saveSuc(){
		showAlert("���ɹ���", "closeWin");
	}
	function closeWin(){
		window.close();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="cwkpdj!initMx" namespace="/cwgl" method="post" id="mainForm" name="mainForm">
	<input type="hidden" id="mainForm_domain_kpDjxh" value='<s:property value="domain.kpDjxh"/>'/>
	<s:hidden name="domain.khDjxh"></s:hidden>
	<s:hidden name="domain.djJgbm"></s:hidden>
	<s:hidden name="domain.ssJgbm"></s:hidden>
		<div class="pop_contc" style="height:320px; overflow:auto;">
			<fieldset>
			<legend>������Ϣ</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">				
      			<tr>
      				<td align="right" width="20%">��Ʊ���룺</td>
      				<td width="70%">
      					<s:textfield name="domain.fpdm" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">��Ʊ���룺</td>
      				<td>
      					<s:textfield name="domain.fphm" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">�Ǽǲ��ţ�</td>
      				<td>
      					<s:textfield name="domain.djJgmc" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">����������</td>
      				<td>
      					<s:textfield name="domain.ssJgmc" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>

			</table>
			</fieldset>
			<div class="pop_btn">
			 	<button type="button" class="pop_btnbg" id="saveBtn">�� ��</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn" onclick="window.close()">�� ��</button>
		    </div>
		</div>

	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
