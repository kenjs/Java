<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>费用登记项目</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		var clsxDm0 = $("#mainForm_domain_clsxDm").val();
		$("#clsxDm").val(clsxDm0);
		$("#closeBtn").click(function(){
			window.close();
		});
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var whXh = $("#mainForm_domain_whXh").val(); 
			var fydjXmmc = trim($("#mainForm_domain_fydjXmmc").val()); 
			var sm = trim($("#mainForm_domain_sm").val()); 
			var clsxDm=$("#clsxDm").val();
			var url = jcontextPath+"/zygl/qyfydjxm!save";  
	    	var jsonObj = {"domain.whXh":whXh,"domain.fydjXmmc":fydjXmmc,"domain.sm":sm,"domain.clsxDm":clsxDm};   			
			ajaxCommon(url,jsonObj,"doSuccess");
		});
	});
	
	function doSuccess(){
		showAlert("保存成功！","closeWin");
	}
	
	function closeWin(){
		window.close();
	}
	
	function checkdata(){
		var controlNameArray = ["domain.fydjXmmc","domain.sm"];
		var labelNameArray = ["费用登记项目","说明"];
		var compareValueArray = [50,200];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="qyclxhwh!initMx" namespace="/zygl" method="post" id="mainForm" name="mainForm">
		<s:hidden name="domain.whXh"></s:hidden>
		<s:hidden name="domain.clsxDm"></s:hidden>
		<div class="pop_contc" style="height:260px;width: 97%">
		<fieldset>
			<legend>基本信息</legend>
			<table width="95%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="20%" align="right"><font class="font_red">*</font>费用登记项目：</td>
      				<td width="30%">
      					<s:textfield name="domain.fydjXmmc" cssClass="pop_input bgstyle_required" ></s:textfield>
      				</td>
      				<td width="15%" align="right">车辆属性：</td>
      				<td width="30%">
      					<s:select id="clsxDm" list="#{1:'自由车辆',2:'社会车辆'}" headerKey="" headerValue="全部车辆" cssClass="select"></s:select>
      				</td>  
      			</tr>
      			<tr>
      				<td align="right">说明：</td>
      				<td colspan="3">
      					<s:textarea name="domain.sm" rows="3" cssClass="pop_textarea_colspan2" ></s:textarea>
      				</td>
      			</tr>
				<tr>
      				<td align="right">创建人：</td>
      				<td >
      					<s:textfield name="domain.cjrMc" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">创建日期：</td>
      				<td >
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
