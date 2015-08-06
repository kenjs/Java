<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>协议登记</title>

<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
	var yfHdfChanged = false;
	$(function(){
		$(".yfxx").change(function(){
			if (this == $("#mainForm_domain_yfHdf")[0]) {
				yfHdfChanged = true;
			}
			calYfxx();
		});
		
		$("#sendBtn").click(function(){
			var wsDm="303003";//费用登记审批表
			var pcDjxh = $("#mainForm_domain_pcDjxh").val();
			var oldWsspxh = $("#mainForm_domain_wsSpxh").val();
			scSend(wsDm,"",pcDjxh,oldWsspxh);
		});
		
	});
	
	function calYfxx() {
		var yfSjs = $("#mainForm_domain_yfSjs").val();
		if (yfSjs != "" && yfSjs/1 > 0) {
			var yfXxf = $("#mainForm_domain_yfYfyf").val()/1 + $("#mainForm_domain_yfHdyf").val()/1 + $("#mainForm_domain_yfHdf").val()/1 + yfSjs/1 - $("#mainForm_domain_yfHj").val()/1;
			if (!isNaN(yfXxf)) {
				if (yfXxf <= 0) {
					$("#mainForm_domain_yfXxf").val("");
				}else {
					$("#mainForm_domain_yfXxf").val(yfXxf);
				}
			}
		}else {
			if (!yfHdfChanged) {
				var yfHdf = $("#mainForm_domain_yfHj").val()/1 - $("#mainForm_domain_yfYfyf").val()/1 - $("#mainForm_domain_yfHdyf").val()/1;
				if (!isNaN(yfHdf)) {
					$("#mainForm_domain_yfHdf").val(yfHdf);
				}else {
					$("#mainForm_domain_yfHdf").val("");
				}
			}
			$("#mainForm_domain_yfXxf").val("");		
		}
	}
	
	$(function(){
		$("#closeBtn").click(function(){
			closeWin();
		});
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			
			var yfXxf = $("#mainForm_domain_yfXxf").val();
			if (yfXxf != "" && yfXxf/1 > 0) {
				showConfirm("信息费大于0，是否确认保存？", "doSave");
			}else if (!checkYfxx()){
				return;
			}else {
				doSave();
			}
			
		});
		
		initRy();
	});
	
	function doSave() {
		var xyh = trim($("#mainForm_domain_xyh").val()); 
		var yfHj = trim($("#mainForm_domain_yfHj").val()); 
		var yfYfyf = trim($("#mainForm_domain_yfYfyf").val()); 
		var yfYj = trim($("#mainForm_domain_yfYj").val()); 
		var yfXxf = trim($("#mainForm_domain_yfXxf").val()); 
		var yfSjs = trim($("#mainForm_domain_yfSjs").val()); 
		var yfHdyf = trim($("#mainForm_domain_yfHdyf").val()); 
		var yfHdf = trim($("#mainForm_domain_yfHdf").val()); 
		var bz = trim($("#mainForm_domain_bz").val()); 
		var ywyCzyDjxh = trim($("#mainForm_domain_ywyCzyDjxh").val()); 
		var pcDjxh = trim($("#mainForm_domain_pcDjxh").val()); 

		var url = jcontextPath+"/zyegl/pcxydj!save";  
    	var jsonObj = {"domain.xyh":xyh,"domain.yfHj":yfHj,"domain.yfYfyf":yfYfyf,
                       "domain.yfYj":yfYj,"domain.yfXxf":yfXxf,"domain.yfSjs":yfSjs,"domain.yfHdyf":yfHdyf,"domain.yfHdf":yfHdf,
                       "domain.bz":bz,"domain.ywyCzyDjxh":ywyCzyDjxh,
                       "domain.pcDjxh":pcDjxh};   			
		ajaxCommon(url,jsonObj,"doSaveSuc");
	}
	
	function doSaveSuc(data) {
		hideMessage();
		showAlert("登记成功！", "closeWin");
	}
	
	function closeWin() {
		window.close();
	}
	
	function checkYfxx() {
		if ($("#mainForm_domain_yfHj").val()/1 != $("#mainForm_domain_yfYfyf").val()/1 + $("#mainForm_domain_yfHdyf").val()/1 + $("#mainForm_domain_yfHdf").val()/1 + $("#mainForm_domain_yfSjs").val()/1) {
			alert("运费信息录入有误，运费需满足公式：\"总运费=预付运费\+司机收\+货到运费\+回单付\"，请检查！");
			return false;
		}
		return true;
	}
	
	function initRy() {
		var sj = $("#mainForm_domain_bmbm").val();
		commonInit("BMYH", sj, '', "domain.ywyCzyDjxh", "mainForm_domain_ywyCzyDjxh", "Y", false);
	}
	
	function checkdata(){
		var controlNameArray = ["domain.xyh","domain.yfHj","domain.yfYfyf",
		                        "domain.yfYj","domain.yfXxf","domain.yfSjs","domain.yfHdyf","domain.yfHdf",
		                        "domain.bz","domain.ywyCzyDjxh"];
		var labelNameArray = ["协议号","总运费","预付运费",
		                      "押金","信息费","司机收","货到运费","回单付",
		                      "备注","业务员"];
		var compareValueArray = [50,14.2,14.2,
			                     14.2,14.2,14.2,14.2,14.2,
			                     500,16];
		var nodeTypeArray = [NodeType.STRING,NodeType.DECIMAL,NodeType.DECIMAL,
			                 NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,
			                 NodeType.STRING,NodeType.INTEGER];
		var notNullArray = [false,false,false,
                            false,false,false,false,false,
                            false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="pcxydj!initMx" namespace="/zyegl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.pcDjxh" />
	<s:hidden name="domain.bmbm" />
	<s:hidden name="domain.wsSpxh" />
	<s:hidden name="domain.wsspztDm" />
	
	<div class="pop_contc" >
		<fieldset>
		<legend>协议信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="15%" align="right">协议号：</td>
      				<td width="35%">
      					<s:textfield name="domain.xyh" cssClass="pop_input noborder bgstyle_optional" ></s:textfield>
      				</td>
      				<td width="15%" align="right">业务员：</td>
      				<td width="35%">
      					<select name="domain.ywyCzyDjxh" id="mainForm_domain_ywyCzyDjxh" class="select" >
      						<option value="${domain.ywyCzyDjxh }"></option>
      					</select>
      				</td>
      			</tr>
      		</table>
      	</fieldset>
      	<fieldset>
		<legend>协议登记运费信息</legend>
   			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td width="13%" align="right">总运费：</td>
      				<td width="20%">
      					<s:textfield name="domain.yfHj" cssClass="yfxx pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      				<td width="13%" align="right">信息费：</td>
      				<td width="20%">
      					<s:textfield name="domain.yfXxf" cssClass="yfxx pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td width="13%" align="right">押金：</td>
      				<td width="20%">
      					<s:textfield name="domain.yfYj" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">预付运费：</td>
      				<td>
      					<s:textfield name="domain.yfYfyf" cssClass="yfxx pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      				<td align="right">司机收：</td>
      				<td>
      					<s:textfield name="domain.yfSjs" cssClass="yfxx pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      				<td align="right">货到运费：</td>
      				<td>
      					<s:textfield name="domain.yfHdyf" cssClass="yfxx pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">回单付：</td>
      				<td>
      					<s:textfield name="domain.yfHdf" cssClass="yfxx pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      				<td></td>
      				<td></td>
      			</tr>
      			<tr>
      				<td align="right">备注：</td>
      				<td colspan="5">
      					<s:textarea name="domain.bz" rows="3" cssClass="pop_textarea_colspan2 noborder bgstyle_optional" ></s:textarea>
      				</td>
      			</tr>
			</table>
			</fieldset>
			<fieldset>
		<legend>派车单运费信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td width="13%" align="right">总运费：</td>
      				<td width="20%">
      					<s:textfield name="domain.pcxxDomain.yfHj" cssClass="pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td width="13%" align="right">信息费：</td>
      				<td width="20%">
      					<s:textfield name="domain.pcxxDomain.yfXxf" cssClass="pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td width="13%" align="right">押金：</td>
      				<td width="20%">
      					<s:textfield name="domain.pcxxDomain.yfYj" cssClass="pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">预付运费：</td>
      				<td>
      					<s:textfield name="domain.pcxxDomain.yfYfyf" cssClass="pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">司机收：</td>
      				<td>
      					<s:textfield name="domain.pcxxDomain.yfSjs" cssClass="pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">货到运费：</td>
      				<td>
      					<s:textfield name="domain.pcxxDomain.yfHdyf" cssClass="pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">回单付：</td>
      				<td>
      					<s:textfield name="domain.pcxxDomain.yfHdf" cssClass="pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td></td>
      				<td></td>
      			</tr>
			</table>
			</fieldset>
			<div class="pop_btn">
			 	<button type="button" class="pop_btnbg" id="saveBtn">保 存</button>
			 	&nbsp;
			 	<s:if test='domain.kfsFlag == "Y"'>
				 	<button type="button" class="pop_btnbg" id="sendBtn">发送审批</button>
				 	&nbsp;
			 	</s:if>
			    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		    </div>
		</div>
	</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
