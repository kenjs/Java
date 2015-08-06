<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>项目分类维护</title>
<%@ include file="/common/meta.jsp"%>
<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		})
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var wsDm = trim($("#mainForm_domain_wsDm").val()); 
			var xmflmc = trim($("#mainForm_domain_xmflmc").val()); 
			var xmflDjxh = trim($("#mainForm_domain_xmflDjxh").val()); 
			var url = jcontextPath+"/qyspwsxmfl!save";  
	    	var jsonObj = {"domain.wsDm":wsDm,"domain.xmflmc":xmflmc,
                           "domain.xmflDjxh":xmflDjxh};   			
			ajaxCommon(url,jsonObj,"YesSave");
		});
	});
	
	function YesSave(){
		showAlert("保存成功！","saveDone");
	}
	
	function saveDone(){
		window.close();
	}
	
	function checkdata(){
		var controlNameArray = ["domain.wsDm","domain.xmflmc"];
		var labelNameArray = ["审批文书","项目分类名称"];
		var compareValueArray = [16,40];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
<base target="_self" />
</head>

<body>
<%try{ %>
<s:form action="qyspwsxmfl!initMx" namespace="" method="post" id="mainForm" name="mainForm">
	<input type="hidden" id="mainForm_domain_xmflDjxh" value='<s:property value="domain.xmflDjxh"/>'/>
	
	<div class="pop_contc" style="height:250px; overflow:auto;">
		<fieldset>
			<legend>基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				    <tr>
				   		<td width="10%" align="right"><font class="font_red">*</font>审批文书： </td>
				      	<td width="35%" colspan="3">
				      		<sys:Spws myId="mainForm_domain_wsDm" myName="domain.wsDm" contaisQxz="true" myClass="select" mcContainDmBz="Y"></sys:Spws>
				        </td>
				    </tr>
				    
				    <tr>
				      	<td width="10%" align="right">项目分类名称：</td>
				      	<td width="35%" colspan="3">
				      		<s:textfield name="domain.xmflmc" rows="3" cssClass="pop_input bgstyle_optional" ></s:textfield>
				        </td>
				    </tr>
				    <tr>
				      	<td align="right">创建人：</td>
				      	<td width="14%">
				      		<s:textfield name="domain.cjrMc" cssClass="pop_input bgstyle_readonly" ></s:textfield>
				        </td>
				        <td align="right">创建日期：</td>
				      	<td width="14%">
				      		<s:textfield name="domain.cjrq" cssClass="pop_input bgstyle_readonly" ></s:textfield>
				        </td>
				    </tr>
				    <tr>
				      	<td align="right">修改人：</td>
				      	<td>
				      		<s:textfield name="domain.xgrMc" cssClass="pop_input bgstyle_readonly" ></s:textfield>
				        </td>
				        <td align="right">修改日期：</td>
				      	<td>
				      		<s:textfield name="domain.xgrq" cssClass="pop_input bgstyle_readonly" ></s:textfield>
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
