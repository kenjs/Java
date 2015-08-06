<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>办公-即时通讯</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		
		$("#closeBtn").click(function(){
			window.close();
		});
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var czyDjxh = trim($("#mainForm_domain_czyDjxh").val()); 
			var nr = trim($("#mainForm_domain_nr").val()); 

			var url = jcontextPath+"/bggl/jstx!save";  
	    	var jsonObj = {"domain.czyDjxh":czyDjxh,"domain.nr":nr};   			
			ajaxCommon(url,jsonObj);
		});
	});
	
	function checkdata(){
		var controlNameArray = ["domain.czyDjxh"];
		var labelNameArray = ["接收人"];
		var compareValueArray = [16];
		var nodeTypeArray = [NodeType.STRING];
		var notNullArray = [true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="jstx!initMx" namespace="/bggl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.jstxXh" />
		<div class="pop_contc" style="height:320px; overflow:auto;">
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
       				<th colspan="4">基本信息</th>
      			</tr>
				<tr>
      				<td width="15%" align="right"><font class="font_red">*</font>接收人</td>
      				<td width="85%">
      					<s:textfield name="domain.czyDjxh" cssClass="pop_input_colspan2 noborder bgstyle_required" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">内容</td>
      				<td>
      					<s:textarea rows="8" name="domain.nr" cssClass="pop_textarea_colspan2" ></s:textarea>
      				</td>
      			</tr>
			</table>
			<div class="pop_btn">
			 	<button type="button" class="pop_btnbg" id="saveBtn">保 存</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		    </div>
		</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
