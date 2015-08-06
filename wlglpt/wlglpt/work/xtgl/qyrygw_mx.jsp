<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>用户岗位维护</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(document).ready(function(){
		var ssJgbm = $("#mainForm_domain_gsbm").val();
		if(ssJgbm != null && ssJgbm !="" && ssJgbm != undefined){
		     bmInit(ssJgbm, '', "domain.ssJgbm", "mainForm_domain_ssJgbm", "Y", "Y");
		}
		
		var  gwDjxh = $("#mainForm_domain_ssJgbm").val();
		if(gwDjxh != null && gwDjxh !="" && gwDjxh != undefined){
			gwInit(gwDjxh, '', 'domain.gwDjxh', 'mainForm_domain_gwDjxh', 'Y', 'Y');
		}
	});
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		});
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val());
			var gwDjxh = trim($("#mainForm_domain_gwDjxh").val());
			var oldGwDjxh = trim($("#mainForm_domain_oldGwDjxh").val());
			var qxJgbm = trim($("#mainForm_domain_qxJgbm").val());
			var czyDjxh = trim($("#mainForm_domain_czyDjxh").val());
			//alert(oldGwDjxh);
			var url = jcontextPath+"/xtgl/qyrygw!save";  
	    	var jsonObj = {"domain.ssJgbm":ssJgbm,"domain.gwDjxh":gwDjxh,"domain.oldGwDjxh":oldGwDjxh,"domain.qxJgbm":qxJgbm,"domain.czyDjxh":czyDjxh};   			
			ajaxCommon(url,jsonObj,"saveOk");
		});
	});
	
	function saveOk(){
		showSuccess("保存成功！","saveAfter");
	}
	function saveAfter(){
		window.close();
	}
	function checkdata(){
		var controlNameArray = ["domain.ssJgbm","domain.gwDjxh","domain.qxJgbm"];
		var labelNameArray = ["所属部门","所属岗位","权限机构"];
		var compareValueArray = [100,100,100];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="qyrygw!initMx" namespace="/xtgl" method="post" id="mainForm" name="mainForm">
	<input type="hidden" id="mainForm_domain_oldGwDjxh" value='<s:property value="domain.oldGwDjxh"/>'/>
	<input type="hidden" id="mainForm_domain_czyDjxh" value='<s:property value="domain.czyDjxh"/>'/>
	
	<div class="pop_contc" style="height:270px; overflow:auto;">
	<fieldset>
	<legend>基本信息</legend>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">   			
		<tr>
			<td width="10%" align="right"><font class="font_red">*</font>所属单位：</td>
			<td width="45%">
			<sys:qxGsList myId="mainForm_domain_gsbm" myName="domain.gsbm" mcContainDmBz="Y" myClass="select" onChange="bmInit(this.value, '', 'domain.ssJgbm', 'mainForm_domain_ssJgbm', 'Y', 'Y')" ></sys:qxGsList>
				
			</td>
		</tr>
		<tr>
			<td align="right"><font class="font_red">*</font>所属部门：</td>
			<td>
				<select name="domain.ssJgbm" id="mainForm_domain_ssJgbm" class="select" onChange="gwInit(this.value, '', 'domain.gwDjxh', 'mainForm_domain_gwDjxh', 'Y', 'Y')">
					<option value="${domain.ssJgbm }"></option>
				</select>
			</td>
		</tr>	
		<tr>
			<td align="right"><font class="font_red">*</font>所属岗位：</td>
			<td>
				<select name="domain.gwDjxh" id="mainForm_domain_gwDjxh" class="select" >
					<option value="${domain.oldGwDjxh }"></option>
				</select>
			</td>
		</tr>	
		<tr>
			<td align="right"><font class="font_red">*</font>权限机构：</td>
			<td>
				<sys:gsBmList myId="mainForm_domain_qxJgbm" myName="domain.qxJgbm" mcContainDmBz="Y" myClass="select"/>
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
