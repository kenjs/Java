<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>企业岗位维护</title>

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
			var gwMc = trim($("#mainForm_domain_gwMc").val()); 
			var gwJc = trim($("#mainForm_domain_gwJc").val()); 
			var bzsm = trim($("#mainForm_domain_bzsm").val()); 
			var qybz = $("#mainForm_domain_qybz").val();
			
			var gwDjxh = $("#mainForm_domain_gwDjxh").val(); 
			var ssJgbm = $("#mainForm_domain_ssJgbm").val();
			var url = jcontextPath+"/xtgl/gwwh!save";  
	    	var jsonObj = {"domain.gwMc":gwMc,"domain.gwJc":gwJc,"domain.bzsm":bzsm,
                           "domain.gwDjxh":gwDjxh,"domain.ssJgbm":ssJgbm};   			
			ajaxCommon(url,jsonObj,"doSuccess");
		});
	});
	
	function checkdata(){
		var controlNameArray = ["domain.gwMc","domain.gwJc","domain.bzsm","domain.qybz"];
		var labelNameArray = ["岗位名称","岗位简称","备注说明",
		                      "启用标志"];
		var compareValueArray = [40,40,400,10];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,false,true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	
	function doSuccess(){
		showAlert("保存成功！","yesCallBack");
	}
	
	function yesCallBack(){
		window.close();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="gwwh!initMx" namespace="/xtgl" method="post" id="mainForm" name="mainForm">
	<input type="hidden" id="mainForm_domain_gwDjxh" value='<s:property value="domain.gwDjxh"/>'/>
	<input type="hidden" id="mainForm_domain_ssJgbm" value='<s:property value="domain.ssJgbm"/>'/>
	<div class="pop_contc">
	<fieldset>
		<legend>所属部门</legend>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
			<tr>
				<td width="15%" align="right"><font class="font_red">*</font>名称：</td>
				<td width=“85% colspan="3">
					<s:textfield name="domain.bmMc" cssClass="pop_input bgstyle_readonly"></s:textfield>
				</td>
			</tr>
		</table>	
	</fieldset>
	<fieldset>
		<legend>岗位信息</legend>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
			<tr>
   				<td width="15%" align="right"><font class="font_red">*</font>名称：</td>
   				<td width="35%">
   					<s:if test='domain.lybz==\"Y\"'>
   						<s:textfield name="domain.gwMc" cssClass="pop_input bgstyle_readonly"></s:textfield>
   					</s:if>
   					<s:else>
   						<s:textfield name="domain.gwMc" cssClass="pop_input bgstyle_required" ></s:textfield>
   					</s:else>
   				</td>
   				<td width="15%" align="right"><font class="font_red">*</font>简称：</td>
   				<td width="35%">
   					<s:if test='domain.lybz==\"Y\"'>
   						<s:textfield name="domain.gwJc" cssClass="pop_input bgstyle_readonly"></s:textfield>
   					</s:if>
   					<s:else>
   						<s:textfield name="domain.gwJc" cssClass="pop_input bgstyle_required"></s:textfield>
   					</s:else>
   				</td>
   			</tr>
   			<tr>
   				<td align="right">备注：</td>
   				<td colspan="3">
   					<s:if test='domain.lybz==\"Y\"'>
   						<s:textarea name="domain.bzsm" rows="3" cssClass="pop_textarea_colspan2 bgstyle_readonly"></s:textarea>
   					</s:if>
   					<s:else>
   						<s:textarea name="domain.bzsm" rows="3" cssClass="pop_textarea_colspan2" ></s:textarea>
   					</s:else>
   				</td>
   			</tr>

		</table>
		</fieldset>
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
