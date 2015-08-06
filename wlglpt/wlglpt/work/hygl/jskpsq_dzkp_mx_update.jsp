<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>��Ʊ����</title>

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
			var kpsqmxDjxh = trim($("#mainForm_domain_kpsqmxDjxh").val());
			var kpsqDjxh = trim($("#mainForm_domain_kpsqDjxh").val()); 
			var qdDjxh = trim($("#mainForm_domain_qdDjxh").val()); 
			var sqKpJe = trim($("#mainForm_domain_sqKpJe").val()); 
			var bzsm = trim($("#mainForm_domain_bzsm").val());
			var wsqKpJe = trim($("#mainForm_domain_wsqKpJe").val());  
			var existBz = trim($("#mainForm_domain_existBz").val()); 
			var dzQdXgbz = trim($("#mainForm_domain_dzQdXgbz").val()); 
			var w_je=parseFloat(wsqKpJe);
			var s_je=parseFloat(sqKpJe);
			var ysqJe = $("#mainForm_domain_ysqKpJe").val();
			if (kpsqmxDjxh != '') {
				w_je += parseFloat(ysqJe);
			}
			if(w_je<s_je){
				showAlert("���뿪Ʊ���ܴ���δ���뿪Ʊ��������飡");
				return;
			}

			var url = jcontextPath+"/hygl/jskpsq!saveMx";  
	    	var jsonObj = {"domain.kpsqDjxh":kpsqDjxh,"domain.qdDjxh":qdDjxh,"domain.sqKpJe":sqKpJe,
                           "domain.bzsm":bzsm,"domain.existBz":existBz,"domain.dzQdXgbz":dzQdXgbz,"domain.kpsqmxDjxh":kpsqmxDjxh};   			
			ajaxCommon(url,jsonObj,"doSaveMxSuc", false);
		});
	});
	
	function doSaveMxSuc(){ 
		hideMessage();
		showSuccess("����ɹ���","doYesCallBack");
		//window.close();
	}
	
	//ѡ���ǵķ��ش���
	function doYesCallBack(){
		//sysClose();
		//parent.initMx();
		window.close();
	}
	
	function checkdata(){
		var controlNameArray = ["domain.qdDjxh","domain.sqKpJe","domain.bzsm"];
		var labelNameArray = ["�嵥","���뿪Ʊ���","��ע˵��"];
		var compareValueArray = [20,16.2,200];
		var nodeTypeArray = [NodeType.STRING,NodeType.DECIMAL,NodeType.STRING];
		var notNullArray = [true,true,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
<base target="_self" />
</head>

<body>
<%try{ %>
<s:form action="jskpsq!initMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
		<s:hidden name="domain.kpsqmxDjxh"></s:hidden>
		<s:hidden name="domain.kpsqDjxh"></s:hidden>
		<s:hidden name="domain.existBz"></s:hidden>
		<s:hidden name="domain.dzQdXgbz"></s:hidden>
		<div class="pop_contc">
			<fieldset>
				<legend>�嵥��Ϣ</legend>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
					<tr>
						<td width="20%" align="right"><font class="font_red">*</font>�嵥��</td>
	      				<td colspan="2">
	      					<s:hidden name="domain.qdDjxh"></s:hidden>
	      					<s:textfield name="domain.qdmc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      				<td>&nbsp;</td>
	      			</tr>
	      		</table>
	      	</fieldset>
	      	<fieldset>
				<legend>������Ϣ</legend>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
	      			<tr>
	      				<td width="20%" align="right">�ϼƽ�</td>
	      				<td width="30%">
	      					<s:textfield name="domain.heJe" cssClass="pop_input noborder bgstyle_readonly" readonly="true" ></s:textfield>
	      				</td>
	      				<td width="20%" align="right">�����뿪Ʊ��</td>
	      				<td width="30%">
	      					<s:textfield name="domain.ysqKpJe" cssClass="pop_input noborder bgstyle_readonly" readonly="true" ></s:textfield>
	      				</td>
	      			</tr>
	      			<tr>
	      				<td  align="right">δ���뿪Ʊ��</td>
	      				<td>
	      					<s:textfield name="domain.wsqKpJe" cssClass="pop_input noborder bgstyle_readonly" readonly="true" ></s:textfield>
	      				</td>
	      				<td colspan="2">&nbsp;</td>
	      			</tr>
	      		</table>
	      	</fieldset>
	      	<fieldset>
				<legend>��Ʊ��Ϣ</legend>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
	      			<tr>
	      				<td width="20%" align="right"><font class="font_red">*</font>���뿪Ʊ��</td>
	      				<td width="30%">
	      					<s:textfield name="domain.sqKpJe" cssClass="pop_input noborder bgstyle_required" ></s:textfield>
	      				</td>
	      				<td colspan="2">&nbsp;</td>
	      			</tr>
	      			<tr>
	      				<td align="right">��ע˵����</td>
	      				<td colspan="3">
	      					<s:textarea name="domain.bzsm" rows="3" cssClass="pop_textarea noborder bgstyle_optional" ></s:textarea>
	      				</td>
	      			</tr>
	      		</table>
	      	</fieldset>
			<div class="pop_btn">
			 	<button type="button" class="pop_btnbg" id="saveBtn">�� ��</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
		    </div>
		</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
