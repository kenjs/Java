<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>�������������ϸ����</title>
</head>
<body >
<%try{ %>
<s:form action="wsspCommon!init"  namespace="/common" method="post" id="mainForm" name="mainForm">	
	<s:hidden name="domain.wsSpZbDomain.wsspxh"></s:hidden>
	<s:hidden name="domain.wsSpZbDomain.spxh"></s:hidden>
	<div class="pop_tbmain">
		<div class="pop_tbtitle"><h2>�������</h2></div>
		<div class="pop_tbcont">
			<table border="0" align="center" cellpadding="2" cellspacing="0">
				<tr>
					<td width="80" align="right">�����ˣ�</td>
					<td width="160">
						<s:textfield name="domain.wsSpZbDomain.sprmc" cssClass="pop_input bgstyle_readonly"></s:textfield>
					</td>
					<td width="80" align="right">�������ڣ�</td>
					<td width="160"><s:textfield name="domain.wsSpZbDomain.sprq" cssClass="pop_input bgstyle_readonly"></s:textfield></td>
				</tr>
				<tr>
					<td width="80" align="right">���������</td>
					<td colspan="3">
						<s:radio name="domain.wsSpZbDomain.spjg" list='#{"1":"ͬ��","2":"��ͬ��"}' listKey="key" listValue="value" disabled="true"></s:radio>
					</td>
				</tr>
				<tr>
					<td width="80" align="right">���������</td>
					<td colspan="3">
						<s:textarea name="domain.wsSpZbDomain.spyj" cols="64" rows="4" readonly="true"></s:textarea>
					</td>
				</tr>
			</table>
		</div>
	</div>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
