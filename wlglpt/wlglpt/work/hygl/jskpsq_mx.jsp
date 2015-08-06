<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>预开票申请</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var kpsqfsDm = trim($("#mainForm_domain_kpsqfsDm").val()); 
			//var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
			var sqKpjeHj = trim($("#mainForm_domain_sqKpjeHj").val()); 
			var sqKprq = trim($("#mainForm_domain_sqKprq").val()); 
			var bzsm = trim($("#mainForm_domain_bzsm").val()); 
			var djJgbm = trim($("#mainForm_domain_djJgbm").val()); 
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
			var kpsqDjxh = trim($("#mainForm_domain_kpsqDjxh").val()); 

			var url = jcontextPath+"/hygl/jskpsq!save";  
	    	var jsonObj = {"domain.kpsqfsDm":kpsqfsDm,"domain.sqKpjeHj":sqKpjeHj,
                           "domain.sqKprq":sqKprq,"domain.bzsm":bzsm,"domain.djJgbm":djJgbm,
                           "domain.ssJgbm":ssJgbm,"domain.kpsqDjxh":kpsqDjxh};   			
			ajaxCommon(url,jsonObj,"doSaveYkpSuc");
		});
		$("#closeBtn").click(function(){
			window.close();
		});
	});
	
	function doSaveYkpSuc(data) {
		hideMessage();
		//showSuccess("保存成功！","doYesCallBack");
		showSuccess("保存成功！","saveSucYesCallBack");
	}
	function saveSucYesCallBack() {
		window.close();
	}
	
	function checkdata(){
		var controlNameArray = ["domain.sqKpjeHj","domain.sqKprq","domain.bzsm"];
		var labelNameArray = ["申请开票金额","申请开票日期","备注说明"];
		var compareValueArray = [16.2,10,200];
		var nodeTypeArray = [NodeType.DECIMAL,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="jskpsq!initMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.kpsqDjxh"></s:hidden>
	<s:hidden name="domain.ssJgbm"></s:hidden>
	<s:hidden name="domain.djJgbm"></s:hidden>
	<s:hidden name="domain.kpsqfsDm"></s:hidden>
		<div class="pop_contc">
			<fieldset>
				<legend>基本信息</legend>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
					<tr>
						<td width="20%" align="right"><font class="font_red">*</font>所属机构：</td>
	      				<td width="30%">
	      					<s:textfield name="domain.dwMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      				<td width="20%" align="right"><font class="font_red">*</font>登记部门：</td>
	      				<td width="30%">
	      					<s:textfield name="domain.bmMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      			</tr>
	      			<tr>
	      				<td align="right"><font class="font_red">*</font>客户名称：</td>
	      				<td>
	      					<s:hidden name="domain.khDjxh"></s:hidden>
	      					<s:textfield name="domain.khMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      			</tr>
	      			</table>
	      			</fieldset>
	      			<fieldset>
						<legend>开票信息</legend>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
			      			<tr>
				      			<td align="right" width="20%"><font class="font_red">*</font>申请开票金额：</td>
			      				<td width="30%">
			      					<s:textfield name="domain.sqKpjeHj" cssClass="pop_input noborder bgstyle_required" ></s:textfield>
			      				</td>
			      				<td align="right" width="20%"><font class="font_red">*</font>申请开票日期：</td>
			      				<td width="30%">
			      					<s:textfield name="domain.sqKprq" readonly="true" cssClass="ymdate bgstyle_required"></s:textfield>
			      				</td>
			      			</tr>
			      			<tr>
			      				<td align="right">备注说明：</td>
			      				<td colspan="3">
			      					<s:textarea name="domain.bzsm" rows="3" cssClass="pop_textarea noborder bgstyle_optional" ></s:textarea>
			      				</td>
			      			</tr>
			      			<tr>
			      				<td align="right"><font class="font_red">*</font>登记人：</td>
			      				<td>
			      					<s:textfield name="domain.cjrMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
			      				</td>
			      				<td align="right"><font class="font_red">*</font>登记日期：</td>
			      				<td>
			      					<s:textfield name="domain.djrq" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
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
