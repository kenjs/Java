<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>货运派车货物签收</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">	
	$(function(){
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			doSave();
		});
		
		$("#closeBtn").click(function(){
			closeWin();
		});
	});
	
	function  doSave(){
		var pcDjxh = trim($("#mainForm_domain_pcDjxh").val()); 
		var qsrq = trim($("#mainForm_domain_qsrq").val()); 
		var qsrCzyDjxh = trim($("#mainForm_domain_qsrCzyDjxh").val()); 
		var bz = trim($("#mainForm_domain_bz").val()); 
		var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
		
		var wfhDjxh = trim($("#mainForm_domain_wfhDjxh").val());  
		var ddDjxh = trim($("#mainForm_domain_ddDjxh").val());  
		var xh = trim($("#mainForm_domain_xh").val());  
		var shfsDm = trim($("#mainForm_domain_shfsDm").val());  
		var url = jcontextPath+"/hygl/hypchwqs!save";  
    	var jsonObj = {"domain.pcDjxh":pcDjxh,"domain.qsrq":qsrq,"domain.qsrCzyDjxh":qsrCzyDjxh,"domain.bz":bz,"domain.ssJgbm":ssJgbm,
    			"domain.wfhDjxh":wfhDjxh,"domain.ddDjxh":ddDjxh,"domain.xh":xh,"domain.shfsDm":shfsDm};   
    	showMessage();
		ajaxCommon(url,jsonObj,"doSaveSuc");		
	}
	
	function doSaveSuc() {
		hideMessage();
		showAlert("保存成功！","closeWin");
	}
	
	function closeWin() {
		window.close();
	}
	
	function checkdata(){
		var controlNameArray = ["domain.qsrq","domain.qsrCzyDjxh","domain.bz"];
		var labelNameArray = ["签收日期","签收人","备注"];
		var compareValueArray = [11,16,500];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="hypchwqs!initMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.hwqsDjxh" />
	<s:hidden name="domain.pcDjxh" />
	<s:hidden name="domain.ssJgbm" />
	
	<s:hidden name="domain.wfhDjxh" />
	<s:hidden name="domain.ddDjxh" />
	<s:hidden name="domain.xh" />
	<s:hidden name="domain.shfsDm" />
		<div class="pop_contc">
			<fieldset>
			<legend>派车信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td align="right" width="12%"><font class="font_red">*</font>签收日期：</td>
      				<td width="25%">
      					<sys:dateCurrentDayTag myName="domain.qsrq" myId="mainForm_domain_qsrq" myClass="ymdate"></sys:dateCurrentDayTag>
      				</td>
      				<td align="right" width="10%">签收人：</td>
      				<td width="20%">
      					<sys:GsryList myName="domain.qsrCzyDjxh" myId="mainForm_domain_qsrCzyDjxh" ssJgbm="domain.ssJgbm" myClass="select" contaisQxz="true"></sys:GsryList>
      				</td>
      			</tr>      			
      			<tr>
      				<td align="right">备注：</td>
      				<td colspan="3">
      					<s:textarea name="domain.bz" rows="4" cssClass="pop_textarea_colspan2 noborder bgstyle_optional" ></s:textarea>
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
