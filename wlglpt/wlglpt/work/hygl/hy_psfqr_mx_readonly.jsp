<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>���ͷ�ȷ��</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		})
	});
</script>
</head>

<body >
<%try{ %>
<s:form action="jspsfqr!viewMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<div id="maincont">
	<div class="pop_contc" style="height:260px; ">
		<fieldset>
		<legend>������Ϣ</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				
				
      			<tr>
      				<td width="15%" align="right"><font class="font_red">*</font>���ͷѣ�</td>
      				<td width="35%%" align="right">
      					<s:textfield name="domain.srPsf" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      				<td align="right">�Ƿ�ͬ�⣺</td>
      				<td align="left">
      					<s:radio name="domain.qrJg" list="#{'Y':'ͬ��','N':'��ͬ��'}" listKey="key" listValue="value" disabled="true"></s:radio>
      				</td>
      			</tr>

				<tr>
			     	<td align="right">ȷ���ˣ�</td>
			     	 <td>
			      		<s:textfield name="domain.cjrMc" cssClass="pop_input bgstyle_readonly" readonly="true" ></s:textfield>
			     	 </td>
			     	 <td align="right">ȷ��ʱ�䣺</td>
			     	 <td>
			      		<s:textfield name="domain.cjrq" cssClass="pop_input bgstyle_readonly" readonly="true" ></s:textfield>
			     	 </td>
				</tr>    
			    <tr>
			      	<td align="right">˵����</td>
			     	 <td colspan="3">
			      		<s:textarea name="domain.bz" rows="3" cssClass="pop_textarea_colspan2" readonly="true"></s:textarea>
			     	 </td>
			    </tr>
			</table>
		</fieldset>
			<div class="pop_btn">
			    <button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
		    </div>
		</div>
	</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
