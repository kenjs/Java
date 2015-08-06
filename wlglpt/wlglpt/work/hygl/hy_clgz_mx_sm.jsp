<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>情况说明</title>

<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		});
	})
</script>	
<base target="_self" />
</head>

<body>
<%try{ %>
<s:form action="hyclgz!initMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
		
		<div id="maincont">
		<div class="pop_contc" style="height:250； overflow:auto;">
			<fieldset>
			<legend>情况说明</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
	  			 <tr>
	  			 	<td align="right">说明：</td>
	  			 	<td colspan="4">
	  			 		<s:textarea name="domain.sm" rows="5" cssClass="pop_textarea_colspan2 bgstyle_optional" readonly="true"></s:textarea>
	  			 	</td>
	  			 </tr>
    		</table>
    		</fieldset>
    		<div class="pop_btn" style="margin-top: 30px;">
			    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		    </div>
		</div>
	</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
