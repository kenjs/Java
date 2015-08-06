<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>日程安排</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var rq = trim($("#cxrqId").val()); 
			var nr = trim($("#mainForm_domain_nr").val()); 
			var bgDjxh = trim($("#mainForm_domain_bgDjxh").val());
			var url = jcontextPath+"/bgrcap!save";  
	    	var jsonObj = {"domain.rq":rq,"domain.nr":nr,"domain.bgDjxh":bgDjxh}; 
	    	var jsonStr = jQuery.param(jsonObj);  			
			ajaxCommon(url,encodeURI(jsonStr),"doSuccess",false);
		});
	});
	
	function checkdata(){
		var controlNameArray = ["domain.nr"];
		var labelNameArray = ["日程安排"];
		var compareValueArray = [2000];
		var nodeTypeArray = [NodeType.STRING];
		var notNullArray = [true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	
	function doSuccess(){
		showSuccess("保存成功！","");
	}
	
	function doOnClose(){
		sysClose();
		parent.onRefresh();
	}
</script>
</head>

<body onLoad="addHandle(document.getElementById('toolbar'), window);">
<%try{ %>
<s:form action="bgrcap!initMx" namespace="" method="post" id="mainForm" name="mainForm">
	<input type="hidden" id="mainForm_domain_bgDjxh" value='<s:property value="domain.bgDjxh"/>'/>
	<s:hidden name="domain.cxrq" id="cxrqId" />
	<div class="pop_title" id="toolbar">
  		<div class="pop_titlec"> <span class="pop_titleleft"><h2>日程安排</h2></span> <span class="pop_titleright"></span> </div><a href="#" id="closePic" title="关闭" class="pop_close" onFocus="this.blur()" onclick="doOnClose();"></a>
	</div>
	<div class="pop_cont">
		<div class="pop_contc" style="height:280px; overflow:auto;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td align="center" width="10%"><font class="font_red">*</font>日程安排</th>
      				<td width="80%">
      					<s:textarea name="domain.nr" rows="10" cssClass="pop_textarea_colspan2"></s:textarea>
      				</td>
      			</tr>
			</table>
			<div class="pop_btn">
			 	<button type="button" class="pop_btnbg" id="saveBtn">保 存</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" onclick="doOnClose();">关 闭</button>
		    </div>
		</div>
	</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
