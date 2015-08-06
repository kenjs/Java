<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>系统参数设置</title>
   
<style type="text/css">
html,body {background:none;}
.tydSel {width:68px;}
</style>
<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		})
		$("#saveCloseBtn").click(function(){
			window.close();
		})
		$("#resetBtn").click(function(){
		    var ssJgmc = $("#mainForm_domain_ssJgmc").val();
		    var sjxlbDm = $("#mainForm_domain_sjxlbDm").val();
		    var xzxmValueDm = $("#mainForm_domain_xzxmValueDm").val();
		    $("#mainForm_domain_dwmc").val(ssJgmc);
		    $("#cszTd").html("<font class=\"font_red\">*</font>参数值");
		    if(sjxlbDm == "23"){
		        document.mainForm.mainForm_domain_xzxmValueDm.disabled = false;
		    }else{
		        document.mainForm.mainForm_domain_csz.readOnly = false;
		        $("#mainForm_domain_csz").select();
		    }
			$("#resetDiv").hide();
			$("#saveDiv").show();
		})
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var ssJgbm = $("#mainForm_domain_ssJgbm").val(); 
			var csxh = $("#mainForm_domain_csxh").val();
			var cslbDm = $("#mainForm_domain_cslbDm").val();
			var csmc = $("#mainForm_domain_csmc").val();
			var csz = $("#mainForm_domain_csz").val();
			var xzxmValueDm = $("#mainForm_domain_xzxmValueDm").val();
			var sysm = $("#mainForm_domain_sysm").val();
		    var xzxmDm = $("#mainForm_domain_xzxmDm").val();
			
			var url = jcontextPath+"/qyxtcs!save";  
	    	var jsonObj = {"domain.ssJgbm":ssJgbm,"domain.csxh":csxh,"domain.cslbDm":cslbDm,
	    	               "domain.csmc":csmc,"domain.csz":csz,"domain.xzxmValueDm":xzxmValueDm,"domain.sysm":sysm,"domain.xzxmDm":xzxmDm};   			
			ajaxCommon(url,jsonObj,"saveOk");
		});
		$("#deleteBtn").click(function(){
		    showConfirm("确定要删除么？","yesCallBack");
		})
	});
	
		
	function yesCallBack(){
	     var ssJgbm = $("#mainForm_domain_ssJgbm").val(); 
	     var csxh = $("#mainForm_domain_csxh").val();
		 var cslbDm = $("#mainForm_domain_cslbDm").val();
		 var jsonObj = {"domain.ssJgbm":ssJgbm,"domain.csxh":csxh,"domain.cslbDm":cslbDm};
		 var url = jcontextPath+"/zygl/qyxtcs!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showSuccess("删除成功！","yesSaveCallBack");
	}	
	function saveOk(){
			showSuccess("保存成功！","yesSaveCallBack");
	}
	
	function yesSaveCallBack(){
		window.close();
	}
	
	function checkdata(){
		var controlNameArray = ["domain.ssJgbm","domain.csxh","domain.cslbDm","domain.csz","domain.xzxmValueDm"];
		var labelNameArray = ["机构编码","参数序号","参数类别代码","参数值","选择项目值代码"];
		var compareValueArray = [20,8,2,20,20];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [false,false,false,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
   <s:form action="qykhhwxx!initMx" namespace="/zygl" method="post" id="mainForm" name="mainForm">
   <s:hidden name="domain.ssJgbm"></s:hidden>
   <s:hidden name="domain.ssJgmc"></s:hidden>
   <s:hidden name="domain.cslbDm"></s:hidden>
   <s:hidden name="domain.sjxlbDm"></s:hidden>
   <s:hidden name="domain.xzxmDm"></s:hidden>
   	<div class="pop_contc" style="height:300px; overflow:auto;">
   	    <s:if test="domain.ssJgbm == domain.jgbm">
   	        <fieldset>
        	  <legend>所属部门</legend>
        	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="15%" align="right">设置单位：</td>
      				<td width="85%" colspan="3">
      					<s:textfield name="domain.dwmc"  cssClass="pop_input_colspan2  bgstyle_readonly" readonly="true"></s:textfield>
      				</td>
      			</tr>
      			</table>
		    </fieldset>
			<fieldset>
		    <legend>基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				
      			<tr>
      				<td width="15%" align="right">参数序号：</td>
      				<td width="85%">
      					<s:textfield name="domain.csxh"  cssClass="pop_input_colspan2  bgstyle_readonly" readonly="true"></s:textfield>
      				</td>
      		    </tr>      		    
      			<tr>
      				<td width="15%" align="right">参数名称：</td>
      				<td width="85%" >
      				    <s:textfield name="domain.csmc"  cssClass="pop_input_colspan2  bgstyle_readonly" readonly="true"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td width="15%" align="right"><font class="font_red">*</font>参数值：</td>
      				<td width="85%">      				
      				    <s:if test="domain.sjxlbDm==11">
      				      <s:textfield name="domain.csz"  cssClass="pop_input_colspan2  bgstyle_required" ></s:textfield>
      				   </s:if>
      				   <s:if test="domain.sjxlbDm==12">
      				      <s:textfield name="domain.csz"  cssClass="pop_input_colspan2  bgstyle_required" ></s:textfield>
      				   </s:if>
      				   <s:if test="domain.sjxlbDm==23">
      				         <s:select list="domain.dmXzxmList" name="domain.xzxmValueDm" listKey="xzxmValueDm" listValue="csz" cssClass="pop_input_colspan2  bgstyle_required"></s:select>
      				   </s:if> 
      				</td>
      			</tr>
      			<tr>
      				<td align="right">使用说明：</td>
      				<td colspan="3">
      					<s:textarea name="domain.sysm" rows="3"  cssClass="pop_textarea_colspan2  bgstyle_readonly" readonly="true"></s:textarea>
      				</td>
      			</tr>
			</table>
			</fieldset>
			<div class="pop_btn">
			 	<button type="button" class="pop_btnbg" id="saveBtn">保 存</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="deleteBtn">取消设置</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		    </div>
   	    </s:if>
   	    <s:if test="domain.ssJgbm != domain.jgbm">
   	        <fieldset>
        	  <legend>所属部门</legend>
        	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="15%" align="right">设置单位：</td>
      				<td width="85%" colspan="3">
      					<s:textfield name="domain.dwmc"  cssClass="pop_input_colspan2  bgstyle_readonly" readonly="true"></s:textfield>
      				</td>
      			</tr>
      			</table>
		    </fieldset>
			<fieldset>
		    <legend>基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				
      			<tr>
      				<td width="15%" align="right">参数序号：</td>
      				<td width="85%">
      					<s:textfield name="domain.csxh"  cssClass="pop_input_colspan2  bgstyle_readonly" readonly="true"></s:textfield>
      				</td>
      		    </tr>
      			<tr>
      				<td width="15%" align="right">参数名称：</td>
      				<td width="85%" >
      				    <s:textfield name="domain.csmc"  cssClass="pop_input_colspan2  bgstyle_readonly" readonly="true"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td width="15%" align="right" id="cszTd">参数值：</td>
      				<td width="85%">
      				   <s:if test="domain.sjxlbDm==11">
      				      <s:textfield name="domain.csz"  cssClass="pop_input_colspan2  bgstyle_readonly" readonly="true"></s:textfield>
      				   </s:if>
      				   <s:if test="domain.sjxlbDm==12">
      				      <s:textfield name="domain.csz"  cssClass="pop_input_colspan2  bgstyle_readonly" readonly="true"></s:textfield>
      				   </s:if>
      				   <s:if test="domain.sjxlbDm==23">
      				         <s:select list="domain.dmXzxmList" name="domain.xzxmValueDm" listKey="xzxmValueDm" listValue="csz" disabled="true" cssClass="pop_input_colspan2  bgstyle_readonly"></s:select>
      				   </s:if>  				
      				   
      				</td>
      			</tr>
      			<tr>
      				<td align="right">使用说明：</td>
      				<td colspan="3">
      					<s:textarea name="domain.sysm" rows="3"  cssClass="pop_textarea_colspan2  bgstyle_readonly" readonly="true"></s:textarea>
      				</td>
      			</tr>
			</table>
			</fieldset>
			<div class="pop_btn" id="resetDiv">
			 	<button type="button" class="pop_btnbg" id="resetBtn">重新设置</button>			 	
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		    </div>
		    <div class="pop_btn" id="saveDiv" style="display: none;">
			 	<button type="button" class="pop_btnbg" id="saveBtn">保存</button>			 	
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="saveCloseBtn">关 闭</button>
		    </div>
   	    </s:if>
	 </div>
   <%@include file="/common/message.jsp" %>
   </s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>