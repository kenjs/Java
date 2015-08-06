<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>财务-费用项目-维护</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var fyxmMc = trim($("#mainForm_domain_fyxmMc").val()); 
			var fylbCwDjxh = trim($("#mainForm_domain_fylbCwDjxh").val()); 
			var cwDjxh = trim($("#mainForm_domain_cwDjxh").val()); 
			var splcXmflDjxh=trim($("#mainForm_domain_splcXmflDjxh").val());
			var url = jcontextPath+"/fyxmwh!save";  
	    	var jsonObj = {"domain.fylbCwDjxh":fylbCwDjxh,"domain.cwDjxh":cwDjxh,"domain.fyxmMc":fyxmMc,"domain.splcXmflDjxh":splcXmflDjxh};   			
			ajaxCommon(url,jsonObj,"saveOk");
		});
		
		$("#closeBtn").click(function(){
			window.close();
		})
	});
	function saveOk(){
		showSuccess("保存成功！","saveAfter");
	}
	function saveAfter(){
		window.close();
	}
	
	function checkdata(){
		var controlNameArray = ["domain.fyxmMc","domain.fylbCwDjxh"];
		var labelNameArray = ["费用项目名称","费用类别名称"];
		var compareValueArray = [40,40];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body >
<%try{ %>
<s:form action="cwfylb!initMx" namespace="" method="post" id="mainForm" name="mainForm">
	<input type="hidden" id="mainForm_domain_cwDjxh" value='<s:property value="domain.cwDjxh"/>'/>
	<div id="maincont">
	<div class="pop_contc" style="height:360px; ">
		<fieldset>
		<legend>费用项目信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				
				
      			<tr>
      				<td width="18%" align="right"><font class="font_red">*</font>费用项目名称：</td>
      				<td width="60%" align="left" colspan="3">
      					
      					<s:textfield name="domain.fyxmMc" rows="3" cssClass="pop_input_colspan2"   ></s:textfield>
      				</td>
      			</tr>
				
				 <tr>
				     	<td width="15%" align="right"><font class="font_red">*</font>费用类别名称：</td>
				     	 <td width="35%">
				     	 <sys:CwFylb  myName="domain.fylbCwDjxh" myId="mainForm_domain_fylbCwDjxh" myClass="select" mcContainDmBz="Y" contaisQxz="true"></sys:CwFylb>
				      		
				     	 </td>
				     	 <td width="15%" align="right">审批项目分类：</td>
				     	 <td width="35%">
				      		<sys:CwFyxm myName="domain.splcXmflDjxh" myId="mainForm_domain_splcXmflDjxh" myClass="select"  contaisQxz="true"></sys:CwFyxm>
				     	 </td>
				    </tr>
				
				 <tr>
				     	<td width="15%" align="right">创建人：</td>
				     	 <td width="35%">
				      		<s:textfield name="domain.cjrMc" cssClass="pop_input bgstyle_optional" readonly="true" ></s:textfield>
				     	 </td>
				     	 <td width="15%" align="right">创建日期：</td>
				     	 <td width="35%">
				      		<s:textfield name="domain.cjrq" cssClass="pop_input bgstyle_optional" readonly="true" ></s:textfield>
				     	 </td>
				    </tr>
				    
				    <tr>
				      	<td width="15%" align="right">修改人：</td>
				     	 <td>
				      		<s:textfield name="domain.xgrMc" cssClass="pop_input bgstyle_optional" readonly="true" ></s:textfield>
				     	 </td>
				     	 <td width="15%" align="right">修改日期：</td>
				     	 <td>
				      		<s:textfield name="domain.xgrq" cssClass="pop_input bgstyle_optional" readonly="true" ></s:textfield>
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
	</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
