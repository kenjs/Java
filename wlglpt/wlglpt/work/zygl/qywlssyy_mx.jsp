<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>物流损失原因</title>
<%@ include file="/common/meta.jsp"%>
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
			var ssyy = trim($("#mainForm_domain_ssyy").val()); 
			var sm = trim($("#mainForm_domain_sm").val()); 
			var id = trim($("#mainForm_domain_whXh").val()); 
			
		
			var url = jcontextPath+"/zygl/wlssyy!save";  
	    	var jsonObj = {"domain.ssyy":ssyy,"domain.sm":sm,
                           "domain.whXh":id};   			
			ajaxCommon(url,jsonObj,"YesSave");
		});
	});
	
	function YesSave(){
		showSuccess("保存成功！","saveOk");
	}
	
	function saveOk(){
		window.close();
	}
	function checkdata(){
		var controlNameArray = ["domain.ssyy","domain.sm"];
		var labelNameArray = ["物流损失原因","说明"];
		var compareValueArray = [40,200];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="bgmpj!initMx" namespace="" method="post" id="mainForm" name="mainForm">
	<input type="hidden" id="mainForm_domain_whXh" value='<s:property value="domain.whXh"/>'/>
	
	<div class="pop_contc" style="height:330px; overflow:auto;">
		<fieldset>
			<legend>基本信息</legend>
			  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				    <tr>
				   		<td width="15%" align="right" ><font class="font_red">*</font>物流损失原因： </td>
				      	<td width="55%" align="left" colspan="3">
				      	<s:textfield name="domain.ssyy"  cssClass="pop_input bgstyle_required"></s:textfield>
				        </td>
				        
				    </tr>
				    
				    <tr>
				      	<td width="10%" align="right">说明：</td>
				      	<td width="30%" colspan="3">
				      	<s:textarea name="domain.sm"  rows="3" cssClass="pop_textarea_colspan2  bgstyle_optional" ></s:textarea>
				        </td>
				      
				    </tr>
				    
				    <tr>
				      	<td width="10%" align="right">创建人：</td>
				      	<td width="30%">
				      	<s:textfield name="domain.cjrMc"  cssClass="pop_input bgstyle_optional" readonly="true"></s:textfield>
				      	</td>
				      	<td width="10%" align="right">修改人</td>
				      	<td width="30%">
				      	<s:textfield name="domain.xgrMc" cssClass="pop_input bgstyle_optional" readonly="true"></s:textfield>
				      	</td>
				    </tr>
				    
				    <tr>
				     	<td width="10%" align="right"><font class="font_red"></font>创建日期：</td>
				     	 <td width="30%">
				      	 <s:textfield name="domain.cjrq" cssClass="pop_input bgstyle_optional" readonly="true"></s:textfield>
				     	 </td>
				     	 <td width="10%" align="right"><font class="font_red"></font>修改日期：</td>
				     	 <td width="30%">
				      	 <s:textfield name="domain.xgrq" cssClass="pop_input bgstyle_optional" readonly="true"></s:textfield>
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
