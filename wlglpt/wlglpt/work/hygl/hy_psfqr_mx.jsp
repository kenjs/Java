<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>���ͷ�ȷ��</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		$("#saveBtn").click(function(){
			var psfDjxh = $("#mainForm_domain_psfDjxh").val();
			var qrJg = $(":checked[name='domain.qrJg']").val();
			var bz = trim($("#mainForm_domain_bz").val());
			var srPsf = $("#mainForm_domain_srPsf").val();			
			if(qrJg == 'N'){
				if(bz == ''){
					showAlert("����˵����ָ����ͬ��ԭ��");
					return;
				}
			}
			var url = jcontextPath+"/hygl/jspsfqr!save";  
	    	var jsonObj = {"domain.psfDjxh":psfDjxh,"domain.qrJg":qrJg,"domain.bz":bz,"domain.srPsf":srPsf};   			
			ajaxCommon(url,jsonObj,"saveOk");
		});
		
		$("#closeBtn").click(function(){
			window.close();
		})
	});
	function saveOk(){
		showSuccess("����ɹ���","saveAfter");
	}
	function saveAfter(){
		window.close();
	}
</script>
</head>

<body >
<%try{ %>
<s:form action="jspsfqr!initMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.psfDjxh" />
	<div id="maincont">
	<div class="pop_contc" style="height:260px; ">
		<fieldset>
		<legend>������Ϣ</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				
				
      			<tr>
      				<td width="15%" align="right"><font class="font_red">*</font>���ͷѣ�</td>
      				<td width="35%%" align="right">
      					<s:textfield name="domain.srPsf" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      				<td align="right">�Ƿ�ͬ�⣺</td>
      				<td align="left">
      					<s:radio name="domain.qrJg" list="#{'Y':'ͬ��','N':'��ͬ��'}" listKey="key" listValue="value" value="'Y'"></s:radio>
      				</td>
      			</tr>

			    <tr>
			      	<td align="right">˵����</td>
			     	 <td colspan="3">
			      		<s:textarea name="domain.bz" rows="3" cssClass="pop_textarea_colspan2"></s:textarea>
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
	</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
