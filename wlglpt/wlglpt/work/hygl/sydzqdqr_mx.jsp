<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>���ζ����嵥ȷ��</title>

<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript">
	$(function(){
	
		$("#closeBtn").click(function(){
				window.close();
			})
		
		$("[name='domain.zt']").change(function(){
			$("#mainForm_domain_bz").val("");
		});
			
		$("#saveBtn").click(function(){
			var qdDjxh = $("#mainForm_domain_qdDjxh").val();
			var zt = $(":checked[name='domain.zt']").val(); 
			var bz = trim($("#mainForm_domain_bz").val()); 
			
			if(zt == '4'){
				if(bz == ''){
					showAlert("���ڱ�ע˵����ָ���˻�ԭ��");
					return;
				}
			}
			var url = jcontextPath+"/hygl/xyjssrdzqd!saveSydzqdqr";  
	    	var jsonObj = {"domain.qdDjxh":qdDjxh,"domain.zt":zt,"domain.bz":bz};
			ajaxCommon(url,jsonObj,"saveOK");
		});
	});
	
	function saveOK(data){
		showSuccess("����ɹ���","saveAfter");
	}
	function saveAfter(){
		window.close();
	}
</script>
<base target="_self" />
</head>

<body >
<%try{ %>
<s:form action="xyjssrdzqd!initSydzqdQrMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
		<s:hidden name="domain.qdDjxh"/>
		<div class="pop_contc" style="height:220px; overflow:auto;">
		<fieldset>
			<legend>������Ϣ</legend>
    	 	 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
				    <td width="15%" align="right">�嵥����:</td>
      				<td width="35%">
      					<s:textfield name="domain.qdmc" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
				    <td width="15%" align="right">���ε�λ:</td>
      				<td width="35%">
      					<s:textfield name="domain.ssJgmc" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
				<tr>
      				<td width="15%" align="right">�ϼƽ�</td>
      				<td width="35%">
      					<s:textfield name="domain.heJe" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      				<td width="15%" align="right">���˽�</td>
      				<td width="35%">
      					<s:textfield name="domain.dzJe" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td width="15%" align="right"></td>
      				<td width="35%">
      					<s:radio list="#{'3':'ȷ��','4':'�˻�' }" name="domain.zt" value="'4'"/>
      				</td>
      				<td align="right">�����</td>
      				<td>      					
      					<s:textfield name="domain.dzcyJe" cssClass="pop_input bgstyle_readonly"></s:textfield>
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
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
