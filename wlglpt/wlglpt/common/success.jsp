<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="include.jsp"%>
<html> 
<head>
	<meta http-equiv="pragma" content="no-cache">
	<title>系统提示</title>
</head>

<body onload="moveMiddle();">
<TABLE height="100%" width="100%">
<TR align="center">
<TD align="center">
<TABLE align="center" width="350" border="1" bordercolor="#3366CC" cellspacing="0">
<TR>
	
	
	<TD bgcolor="#3366CC" height="25"><FONT SIZE="3" COLOR="#FFFFFF">&nbsp;<B>系统提示</B></FONT></TD>
</TR>
<TR>
	<TD>
		<TABLE width="95%" align="center">
			<TR>
				<TD height="30"><p>&nbsp;&nbsp;操作成功！</p></TD>
			</TR>
			<TR>
				<TD><hr height="1">
					<logic:present name="successMessage" scope="request">
						<bean:write name="successMessage" />
					</logic:present>
					<logic:notPresent name="successMessage" scope="request">
						操作成功
					</logic:notPresent>
				</TD>
			</TR>
			<TR>
			<%
				String path = (String)request.getAttribute("path");
			%>
				<logic:present name="isRefresh" scope="request">
				<%
				if(path!=null&&path.trim().length()>0){
				%>
				<TD align="right"><html:submit onclick="window.location='<hjh:context/>/<%=path%>';" value=" 返 回 "/></TD>
				<%
				} else {
				 %>
				<TD align="right"><html:submit onclick="goBack(1);" value=" 返 回 "/></TD>
				<%
				}
				%>
				</logic:present>
				<logic:notPresent name="isRefresh" scope="request">
				<TD align="right"><html:submit onclick="self.close()" value=" 确  定 "/></TD>
				</logic:notPresent>
				
			</TR>
		</TABLE>
	</TD>
</TR>
</TABLE>
</TD>
</TR>
</TABLE>
</body>

</html>