<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>下游派送费录入</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
	
		$("#closeBtn").click(function(){
				window.close();
			})
		
		$("#saveBtn").click(function(){
			var sydwJgbm = $("#mainForm_domain_sydwJgbm").val();
			var zrbmDjxh = $("#mainForm_domain_zrbmDjxh").val();
			var ddDjxh = $("#mainForm_domain_ddDjxh").val();
			var hwmxXh = $("#mainForm_domain_hwmxXh").val();
			var hwMc = $("#mainForm_domain_hwMc").val();
			var psfy = $("#mainForm_domain_psfy").val();
			var bz = $("#mainForm_domain_bz").val();
			var pcDjxh = $("#mainForm_domain_pcDjxh").val();
			var wfhDjxh = $("#mainForm_domain_wfhDjxh").val();
			//alert(pcDjxh);
			if(!isNumber(psfy)){
				showAlert("派送费用必须录入数字！");
				$("#mainForm_domain_psfy").focus(); 
				return;
			}
			if(!checkdata()){
				return;
			}
			var url = jcontextPath+"/hygl/hypchwqs!savePsf";  
	    	var jsonObj = {"domain.sydwJgbm":sydwJgbm,"domain.zrbmDjxh":zrbmDjxh,"domain.ddDjxh":ddDjxh,"domain.hwmxXh":hwmxXh,
	    			"domain.hwMc":hwMc,"domain.psfy":psfy,"domain.bz":bz,"domain.wfhDjxh":wfhDjxh,"domain.pcDjxh":pcDjxh};
			ajaxCommon(url,jsonObj,"saveOK");
		});
	});
	
	function saveOK(){
		showSuccess("保存成功！","saveAfter");
	}
	function saveAfter(){
		window.close();
	}
	function isNumber(oNum) { 
  		if(!oNum) return false; 
  		var strP=/^\d+(\.\d+)?$/; 
 		if(!strP.test(oNum)) 
 			return false; 
  		try{ 
  			if(parseFloat(oNum)!=oNum) return false; 
  			} 
  		catch(ex) { 
   			return false; 
  		} 
  		return true; 
   }
	
	function checkdata(){
		var controlNameArray = ["domain.psfy","domain.bz"];
		var labelNameArray = ["派送费用","备注说明"];
		var compareValueArray = [20.2,200];
		var nodeTypeArray = [NodeType.DECIMAL,NodeType.STRING];
		var notNullArray = [true,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body >
<%try{ %>
<s:form action="hypchwqs!initPsfMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
		<s:hidden name="domain.sydwJgbm"></s:hidden>
		<s:hidden name="domain.zrbmDjxh"></s:hidden>
		<s:hidden name="domain.ddDjxh"></s:hidden>
		<s:hidden name="domain.hwmxXh"></s:hidden>
		<s:hidden name="domain.pcDjxh"></s:hidden>
		<s:hidden name="domain.wfhDjxh"></s:hidden>
		<div class="pop_contc" style="height:220px; overflow:auto;">
		<fieldset>
			<legend>基本信息</legend>
    	 	 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
				    <td width="20%" align="right">上游单位：</td>
      				<td width="35%">
      					<s:textfield name="domain.sydwMc" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
				    <td width="20%" align="right">货物名称:</td>
      				<td width="35%">
      					<s:textfield name="domain.hwMc" cssClass="inputext pop_input noborder bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
				<tr>
      				<td align="right">数量：</td>
      				<td>
      					<s:textfield name="domain.sl" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      				<td align="right">重量：</td>
      				<td>
      					<s:textfield name="domain.zl" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right"><font color="red">*</font>配送费用：</td>
      				<td>
      					<s:textfield name="domain.psfy" cssClass="pop_input bgstyle_required"></s:textfield>
      				</td>
      				<td align="right">体积：</td>
      				<td>
      					<s:textfield name="domain.tj" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">备注说明：</td>
      				<td colspan="3">
      					<s:textarea name="domain.bz" cssClass="pop_textarea_colspan2"></s:textarea>
      				</td>
      			</tr>
			</table>
    	 </fieldset>
		<div class="pop_btn" style="margin-top: 10px;">
			   <button type="button" class="pop_btnbg" id="saveBtn">保存</button>
			 	&nbsp;
		       <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		</div> 
		</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
