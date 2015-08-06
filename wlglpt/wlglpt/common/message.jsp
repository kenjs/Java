<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
 
<s:if test="hasActionMessages()">   
	<s:iterator value="actionMessages">   
		<script language="JavaScript">   
		$(document).ready(function(){
			showAlert("<s:property escape="false"/>");
		});		   
		</script>   
	</s:iterator>   
</s:if> 
<s:if test="hasActionErrors()">   
	<s:iterator value="actionErrors">   
		<script language="JavaScript">   
		$(document).ready(function(){
			showMaxError("<s:property escape="false"/>");
		});		   
		</script>   
	</s:iterator>   
</s:if> 
 