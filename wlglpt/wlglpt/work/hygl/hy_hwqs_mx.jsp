<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>����-����ǩ����ϸ</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(function(){
	
		$("#closeBtn").click(function(){
				window.close();
			})
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var hwqsDjxh = trim($("#mainForm_domain_hwqsDjxh").val()); 
			var qsrq = trim($("#mainForm_domain_qsrq").val()); 
			var qsrmc = trim($("#mainForm_domain_qsrmc").val()); 
			var sfzh = trim($("#mainForm_domain_sfzh").val()); 
			var lxdh = trim($("#mainForm_domain_lxdh").val()); 
			var bz = trim($("#mainForm_domain_bz").val()); 
			var pcDjxh = trim($("#mainForm_domain_pcDjxh").val()); 
			var wfhdjxh = trim($("#mainForm_domain_wfhdjxh").val()); 
			var ddDjxh = trim($("#mainForm_domain_ddDjxh").val()); 
			var xh = trim($("#mainForm_domain_xh").val()); 
			var url = jcontextPath+"/hwqs!save";  
	    	var jsonObj = {"domain.hwqsDjxh":hwqsDjxh,"domain.qsrq":qsrq,"domain.qsrmc":qsrmc,"domain.bz":bz,"domain.xh":xh,
	    					"domain.pcDjxh":pcDjxh,"domain.wfhdjxh":wfhdjxh,"domain.ddDjxh":ddDjxh,"domain.sfzh":sfzh,"domain.lxdh":lxdh
                           };
			ajaxCommon(url,jsonObj,"saveOK");
		});
	});
	
	function saveOK(){
		
		
			showSuccess("�Ǽǳɹ���","saveAfter");
		
		
	}
	function saveAfter(){
		window.close();
	}
	function checkdata(){

		var controlNameArray = ["domain.qsrmc", "domain.lxdh"];
		var labelNameArray = ["ǩ����", "��ϵ�绰"];
		var compareValueArray = [50, 30];
		var nodeTypeArray = [NodeType.STRING, NodeType.STRING
							];
		var notNullArray = [true, false];

		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	
</script>
<base target="_self" />
</head>

<body >
<%try{ %>
<s:form action="hwzt!initMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
      
	<s:hidden name="domain.hwqsDjxh"></s:hidden>
	<s:hidden name="domain.pcDjxh"></s:hidden>
	<s:hidden name="domain.wfhdjxh"></s:hidden>
	<s:hidden name="domain.ddDjxh"></s:hidden>
	<s:hidden name="domain.xh"></s:hidden>
<!--	Ӧ��Ӧ���ǼǴ���-->
		<div id="maincont">
		<div class="pop_contc" style="height:220px; overflow:auto;">
		<fieldset>
			<legend>��������</legend>
    	 	 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
				    <td width="15%" align="right"><font class="font_red">*</font>ǩ����:</td>
      				<td width="35%">
      					<s:textfield name="domain.qsrmc" cssClass="inputext pop_input noborder bgstyle_required"></s:textfield>
      				</td>
				    <td width="15%" align="right"><font class="font_red">*</font>ǩ������:</td>
      				<td width="35%">
      					<s:textfield name="domain.qsrq" cssClass="inputext pop_input noborder bgstyle_required" readonly="true"></s:textfield>
      				</td>
      			</tr>
				<tr>
      				<td width="15%" align="right">֤�����룺</td>
      				<td width="35%">
      					<s:textfield name="domain.sfzh" cssClass="pop_input bgstyle_optional"></s:textfield>
      				</td>
      				<td width="15%" align="right">��ϵ�绰��</td>
      				<td width="35%">
      					<s:textfield name="domain.lxdh" cssClass="pop_input bgstyle_optional"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">��ע˵����</td>
      				<td colspan="3">
      					<s:textarea name="domain.bz" cssClass="pop_textarea_colspan2"></s:textarea>
      				</td>
      			</tr>
			</table>
    	 </fieldset>
		<div class="pop_btn" style="margin-top: 10px;">
			   <button type="button" class="pop_btnbg" id="saveBtn">����</button>
			 	&nbsp;
		       <button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
		</div> 
		</div>
	</div>
	<%@include file="/common/message.jsp" %>
	<div id="inputSel_xzqh" class="inputselcont" style="position: absolute; top: 159px; left: 94px; display: none;" >
         <iframe src="" scrolling="no" frameborder="0" style="position:absolute; top:0; left:0; z-index:-1; width:274px; height:100px;"></iframe>
    </div>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
