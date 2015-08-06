<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>预开票申请</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript"></script>
</head>

<body>
<%try{ %>
<s:form action="jskpsq!initMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.kpsqDjxh"></s:hidden>
	<s:hidden name="domain.ssJgbm"></s:hidden>
	<s:hidden name="domain.djJgbm"></s:hidden>
	<s:hidden name="domain.kpsqfsDm"></s:hidden>
		<div class="pop_contc">
			<fieldset>
				<legend>基本信息</legend>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
					<tr>
						<td width="20%" align="right">所属机构：</td>
	      				<td width="30%">
	      					<s:textfield name="domain.dwMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      				<td width="20%" align="right">登记部门：</td>
	      				<td width="30%">
	      					<s:textfield name="domain.bmMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      			</tr>
	      			<tr>
	      				<td align="right">客户名称：</td>
	      				<td>
	      					<s:hidden name="domain.khDjxh"></s:hidden>
	      					<s:textfield name="domain.khMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      			</tr>
	      			</table>
	      			</fieldset>
	      			<fieldset>
						<legend>开票信息</legend>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
			      			<tr>
				      			<td align="right" width="20%">申请开票金额：</td>
			      				<td width="30%">
			      					<s:textfield name="domain.sqKpjeHj" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
			      				</td>
			      				<td align="right" width="20%">申请开票日期：</td>
			      				<td width="30%">
			      					<s:textfield name="domain.sqKprq" readonly="true" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
			      				</td>
			      			</tr>
			      			<tr>
			      				<td align="right">备注说明：</td>
			      				<td colspan="3">
			      					<s:textarea name="domain.bzsm" rows="3" cssClass="pop_textarea noborder bgstyle_readonly" ></s:textarea>
			      				</td>
			      			</tr>
			      			<tr>
			      				<td align="right">登记人：</td>
			      				<td>
			      					<s:textfield name="domain.cjrMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
			      				</td>
			      				<td align="right">登记日期：</td>
			      				<td>
			      					<s:textfield name="domain.djrq" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
			      				</td>
			      			</tr>
						</table>
					</fieldset>
		</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
