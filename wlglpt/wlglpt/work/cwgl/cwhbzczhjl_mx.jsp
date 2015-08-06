<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>资产转换</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		
		initOldYe();
		initNewYe();
		initOldYh();
		initNewYh();
		$(".showYyh").hide();
		$(".showMdyh").hide();
		$("#mainForm_domain_oldZcflDm").change(function(){
			$("[name='domain.oldYe']").val("");
			var oldZcflDm = $("#mainForm_domain_oldZcflDm").val();
			if(oldZcflDm != "12"){
				$("#mainForm_domain_oldYhCshDjxh").val("");
				initOldYe();
				$(".showYyh").hide();
			}else{
				initOldYh();
				$(".showYyh").show();
			}
		});
		
		$("#mainForm_domain_oldYhCshDjxh").change(function(){
			var oldYhCshDjxh = $("#mainForm_domain_oldYhCshDjxh").val();
			if(oldYhCshDjxh == ""){
				$("[name='domain.oldYe']").val("");
				return;
			}
			initOldYe();
		});
		
		$("#mainForm_domain_newZcflDm").change(function(){
			$("[name='domain.newYe']").val("");
			var newZcflDm = $("#mainForm_domain_newZcflDm").val();
			if(newZcflDm != "12"){
				$("#mainForm_domain_newYhCshDjxh").val("");
				initNewYe();
				$(".showMdyh").hide();
			}else{
				initNewYh();
				$(".showMdyh").show();
			}
		});
		
		$("#mainForm_domain_newYhCshDjxh").change(function(){
			var newYhCshDjxh = $("#mainForm_domain_newYhCshDjxh").val();
			if(newYhCshDjxh == ""){
				$("[name='domain.newYe']").val("");
				return;
			}
			initNewYe();
		});
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			
			var zhje = $("#mainForm_domain_zhje").val();
			var jbrCzyDjxh = $("#mainForm_domain_jbrCzyDjxh").val();
			var rq = $("#mainForm_domain_rq").val();
			var ssJgbm = $("#mainForm_domain_ssJgbm").val();
			var oldZcflDm = $("#mainForm_domain_oldZcflDm").val();
			if(oldZcflDm == "12"){
				var oldYhCshDjxh = $("#mainForm_domain_oldYhCshDjxh").val();
			}else{
				var oldYhCshDjxh = $("#hidOldYhCshDjxh").val();
			}
			var newZcflDm = $("#mainForm_domain_newZcflDm").val();
			if(newZcflDm == "12"){
				var newYhCshDjxh = $("#mainForm_domain_newYhCshDjxh").val();
			}else{
				var newYhCshDjxh = $("#hidNewYhCshDjxh").val();
			}
			var oldYe = $("#mainForm_domain_oldYe").val();
			var newYe = $("#mainForm_domain_newYe").val();
			if(oldZcflDm == "12"){
				if(oldYhCshDjxh == "" || oldYhCshDjxh == null){
					showAlert("请先选择原银行！");
					return;
				}
			}
			
			if(newZcflDm == "12"){
				if(newYhCshDjxh == "" || newYhCshDjxh == null){
					showAlert("请先选择目标银行！");
					return;
				}
			}
			
			if(parseFloat(oldYe) <= 0){
				showAlert("原余额不足无法转账！");
				return;
			}
			
			if(zhje > parseFloat(oldYe)|| zhje < 0){
				showAlert("转换金额小于原资产余额且大于0");
				return;
			}
			if(oldZcflDm!=12&&newZcflDm!=12){
				if(oldZcflDm==newZcflDm){
					showAlert("类别相同无法转账！");
					return;
				}
			}else{
				if(oldZcflDm==newZcflDm&&oldYhCshDjxh==newYhCshDjxh){
					showAlert("类别、银行相同无法转账！");
					return;
				}
			}
			var url = jcontextPath+"/cwgl/cwhbzczhjl!save";  
	    	var jsonObj = {"domain.zhje":zhje,"domain.rq":rq,
	    			"domain.oldZcflDm":oldZcflDm,"domain.oldYhCshDjxh":oldYhCshDjxh,"domain.newZcflDm":newZcflDm,"domain.newYhCshDjxh":newYhCshDjxh,
	    			"domain.ssJgbm":ssJgbm,"domain.oldYe":oldYe,"domain.newYe":newYe};   			
			ajaxCommon(url,jsonObj,"doSuccess");
		});
	});
	
	function doSuccess(){
		showAlert("保存成功！","closeWin");
	}
	
	function closeWin(){
		window.close();
	}
	
	function initOldYh(){
		var sj = $("#mainForm_domain_ssJgbm").val();
		commonInit("CWYH", sj, '', "domain.oldYhCshDjxh", "mainForm_domain_oldYhCshDjxh", "Y", "Y");
	}
	
	function initNewYh(){
		var sj = $("#mainForm_domain_ssJgbm").val();
		commonInit("CWYH", sj, '', "domain.newYhCshDjxh", "mainForm_domain_newYhCshDjxh", "Y", "Y");
	}
	
	function initOldYe(){
		var ylb = $("#mainForm_domain_oldZcflDm").val();
		var yyh = $("#mainForm_domain_oldYhCshDjxh").val();
		if(yyh==null){
			yyh="";
		}
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var url = jcontextPath+"/cwgl/cwhbzczhjl!initOldYe";  
    	var jsonObj = {"domain.oldZcflDm":ylb,"domain.oldYhCshDjxh":yyh,"domain.ssJgbm":ssJgbm};   			
		ajaxCommon(url,jsonObj,"getOldYe");
	}
	
	function getOldYe(data){
		$("[name='domain.oldYe']").val(data.domain.oldYe);
		$("#hidOldYhCshDjxh").val(data.domain.yhCshDjxh)
	}
	
	function initNewYe(){
		var ylb = $("#mainForm_domain_newZcflDm").val();
		var yyh = $("#mainForm_domain_newYhCshDjxh").val();
		if(yyh==null){
			yyh="";
		}
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var url = jcontextPath+"/cwgl/cwhbzczhjl!initNewYe";  
    	var jsonObj = {"domain.newZcflDm":ylb,"domain.newYhCshDjxh":yyh,"domain.ssJgbm":ssJgbm};   			
		ajaxCommon(url,jsonObj,"getNewYe");
	}
	
	function getNewYe(data){
		$("[name='domain.newYe']").val(data.domain.newYe);
		$("#hidNewYhCshDjxh").val(data.domain.yhCshDjxh)
	}
	
	function checkdata(){
		var controlNameArray = ["domain.zhje","domain.rq"];
		var labelNameArray = ["转换金额","日期"];
		var compareValueArray = [14.2,16];
		var nodeTypeArray = [NodeType.DECIMAL,NodeType.String];
		var notNullArray = [true,true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="cwhbzczhjl!initMx" namespace="" metdod="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.ssJgbm"/>
	<input type="hidden" id="hidNewYhCshDjxh"/>
	<input type="hidden" id="hidOldYhCshDjxh"/>
		<div class="pop_contc" style="height:390px; width: 95%;">
			<fieldset>
			<legend>原资产信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="13%" align="right"><font class="font_red">*</font>类别：</td>
      				<td width="35%">
      					<sys:Zcfl myName="domain.oldZcflDm" myClass="select" myId="mainForm_domain_oldZcflDm" contaisQxz="false"/>
      				</td>
      				<td width="13%" align="right" class="showYyh"><font class="font_red">*</font>银行：</td>
      				<td width="35%" class="showYyh">
      					<select name="domain.oldYhCshDjxh" id="mainForm_domain_oldYhCshDjxh" class="select" style="width: 210px;"/>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">余额：</td>
      				<td>
      					<s:textfield name="domain.oldYe" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
			</table>
			</fieldset>
			<fieldset>
			<legend>目标资产信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="13%" align="right"><font class="font_red">*</font>类别：</td>
      				<td width="35%">
      					<sys:Zcfl myName="domain.newZcflDm" myClass="select" myId="mainForm_domain_newZcflDm" contaisQxz="false" />
      				</td>
      				<td width="13%" align="right" class="showMdyh"><font class="font_red">*</font>银行：</td>
      				<td width="35%" class="showMdyh">
      					<select name="domain.newYhCshDjxh" id="mainForm_domain_newYhCshDjxh" class="select" style="width: 210px;"/>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">余额：</td>
      				<td>
      					<s:textfield name="domain.newYe" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>

			</table>
			</fieldset>
			<fieldset>
			<legend>转换信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="15%" align="right"><font class="font_red">*</font>转换余额：</td>
      				<td width="35%">
      					<s:textfield name="domain.zhje" cssClass="pop_input bgstyle_required" ></s:textfield>
      				</td>
      				<td align="right"><font class="font_red">*</font>日期：</td>
      				<td>
      					<sys:dateCurrentDayTag myName="domain.rq" myId="mainForm_domain_rq" myClass="ymdate" />
      				</td>
      			</tr>

			</table>
			</fieldset>
			<div class="pop_btn">
			 	<button type="button" class="pop_btnbg" id="saveBtn">保 存</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn" onclick="window.close();">关 闭</button>
		    </div>
	  </div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
