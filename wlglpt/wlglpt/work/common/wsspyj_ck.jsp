<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>文书审批意见详细内容</title>
</head>
<body >
<%try{ %>
<s:form action="wsspCommon!init"  namespace="/common" method="post" id="mainForm" name="mainForm">	
	<s:hidden name="domain.wsSpZbDomain.wsspxh"></s:hidden>
	<s:hidden name="domain.wsSpZbDomain.spxh"></s:hidden>
	<div class="pop_tbmain">
		<div class="pop_tbtitle"><h2>审批意见</h2></div>
		<div class="pop_tbcont">
			<table border="0" align="center" cellpadding="2" cellspacing="0">
				<tr>
					<td width="80" align="right">审批人：</td>
					<td width="160">
						<s:textfield name="domain.wsSpZbDomain.sprmc" cssClass="pop_input bgstyle_readonly"></s:textfield>
					</td>
					<td width="80" align="right">审批日期：</td>
					<td width="160"><s:textfield name="domain.wsSpZbDomain.sprq" cssClass="pop_input bgstyle_readonly"></s:textfield></td>
				</tr>
				<tr>
					<td width="80" align="right">审批结果：</td>
					<td colspan="3">
						<s:radio name="domain.wsSpZbDomain.spjg" list='#{"1":"同意","2":"不同意"}' listKey="key" listValue="value" disabled="true"></s:radio>
					</td>
				</tr>
				<tr>
					<td width="80" align="right">审批意见：</td>
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
