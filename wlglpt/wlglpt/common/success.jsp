<%@ page language="java" pageEncoding="GBK"%>
<%@ include file="include.jsp"%>
<html> 
<head>
	<meta http-equiv="pragma" content="no-cache">
	<title>ϵͳ��ʾ</title>
</head>

<body onload="moveMiddle();">
<TABLE height="100%" width="100%">
<TR align="center">
<TD align="center">
<TABLE align="center" width="350" border="1" bordercolor="#3366CC" cellspacing="0">
<TR>
	
	
	<TD bgcolor="#3366CC" height="25"><FONT SIZE="3" COLOR="#FFFFFF">&nbsp;<B>ϵͳ��ʾ</B></FONT></TD>
</TR>
<TR>
	<TD>
		<TABLE width="95%" align="center">
			<TR>
				<TD height="30"><p>&nbsp;&nbsp;�����ɹ���</p></TD>
			</TR>
			<TR>
				<TD><hr height="1">
					<logic:present name="successMessage" scope="request">
						<bean:write name="successMessage" />
					</logic:present>
					<logic:notPresent name="successMessage" scope="request">
						�����ɹ�
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
				<TD align="right"><html:submit onclick="window.location='<hjh:context/>/<%=path%>';" value=" �� �� "/></TD>
				<%
				} else {
				 %>
				<TD align="right"><html:submit onclick="goBack(1);" value=" �� �� "/></TD>
				<%
				}
				%>
				</logic:present>
				<logic:notPresent name="isRefresh" scope="request">
				<TD align="right"><html:submit onclick="self.close()" value=" ȷ  �� "/></TD>
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