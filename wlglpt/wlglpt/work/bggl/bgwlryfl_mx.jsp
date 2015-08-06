<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>外联人员分类维护</title>

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
			var jgbm = trim($("#mainForm_domain_jgbm").val()); 
			var flmc = trim($("#mainForm_domain_flmc").val()); 
			var xjgxbz = "";
			var e = document.getElementById("radio");
			if(e.checked){
				xjgxbz="Y";
			} else {
				xjgxbz="N";
			}
			var wlryFlxh = trim($("#mainForm_domain_wlryFlxh").val()); 
			
			var url = jcontextPath+"/bggl/bgwlryfl!save";  
	    	var jsonObj = {"domain.jgbm":jgbm,"domain.flmc":flmc,"domain.xjgxbz":xjgxbz,
                           "domain.wlryFlxh":wlryFlxh};   			
			ajaxCommon(url,jsonObj,"YesSave");
		});
	});
	
	function YesSave(){
		showAlert("保存成功！");
		$("#mainForm_zt").val(1);
	}
	$(function(){
		$("#closeBtn").click(function(){
			var zt=$("#mainForm_zt").val();
			if(zt == '1'){
				parent.onRefresh();
			}
		})
	})
	
	function checkdata(){
		var controlNameArray = ["domain.jgbm","domain.flmc","domain.xjgxbz"];
		var labelNameArray = ["机构编码","分类名称","下级共享"];
		var compareValueArray = [16,80,10];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="bgwlryfl!initMx" namespace="/bggl" method="post" id="mainForm" name="mainForm">
	<input type="hidden" id="mainForm_domain_wlryFlxh" value='<s:property value="domain.wlryFlxh"/>'/>
	<input type="hidden" id="mainForm_zt"/>
	
	<div class="pop_contc" style="height:240px; overflow:auto;">
	<fieldset>
	<legend>基本信息</legend>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
		<tr>
			<td width="15%" align="right"><font class="font_red">*</font>单位：</td>
			<td width="85%">
				<sys:gsList myName="domain.jgbm" myId="mainForm_domain_jgbm" mcContainDmBz="Y" myClass="select"/>
			</td>
   		</tr>
		<tr>
			<td align="right"><font class="font_red">*</font>分类名称：</td>
			<td>
				<s:textfield name="domain.flmc" cssClass="pop_input bgstyle_required"></s:textfield>
			</td>
		</tr>
		<tr>
			<td align="right"><font class="font_red">*</font>下级共享：</td>
			<td>
				<s:if test='domain.xjgxbz==\"N\"'>
           			<s:checkbox name="domain.xjgxbz" id="radio" style="width:20px;"></s:checkbox>
  				</s:if>
  				<s:else>
   					<s:checkbox name="domain.xjgxbz" checked="true" id="radio" style="width:20px;"></s:checkbox>
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
