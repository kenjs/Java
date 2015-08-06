<%@ page contentType="text/html; charset=GBK" %>
<%@ page isErrorPage="true" %>
<%@ include file="include.jsp"%>
<HTML>
<head>
	<meta http-equiv="pragma" content="no-cache">
	<%@ include file="meta.jsp"%>
	<TITLE>系统提示</TITLE>
</HEAD>

<BODY>
<TABLE height="100%" width="100%">
<TR align="center">
<TD align="center">
<TABLE align="center" width="350" border="1" bordercolor="#3366CC" cellspacing="0">
<TR>
	<TD bgcolor="#3366CC"  style="; color:#fff; font-size:15px; height:30px;" onclick="jQuery('#detail').toggle()">&nbsp;<B>操作失败</B></TD>
</TR>
<TR>
	<TD>
		<TABLE width="95%" align="center">
			<TR>
				<TD height="30"><p>&nbsp;&nbsp;操作失败！</p></TD>
			</TR>
			<TR>
				<TD><hr height="1">
				<%=exception.getMessage()%>
				</TD>
			</TR>
			<TR>
				<TD align="right"><button name="result" onclick="window.history.back(-1)">返 回</button></TD>
			</TR>
		</TABLE>
	</TD>
</TR>
</TABLE>
</TD>
</TR>
</TABLE>
<pre id="detail" style="display: none;">
<%=com.cy.framework.util.SysToolsUtil.getStackTrace(exception)%>
<br>
<br>
<br>
</pre>
</BODY>
</HTML>
