<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>�ص��Ǽ�-�鿴</title>

<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
	$(function(){
		initRadio();	
		
		$(":input").attr("readonly", true);
		
		$("#closeBtn").click(function(){
			window.close();
		});
	});
	
	function initRadio() {
		if ($("#mainForm_domain_shfsDm").val() != "") {
			$("[name='shfsDm'][value='"+$("#mainForm_domain_shfsDm").val()+"']")[0].checked = true;
		}else {
			$("[name='shfsDm']")[0].checked = true;
		}
		if ($("#mainForm_domain_pcHwDomain_shfsDm").val() != "") {
			$("[name='ddShfsDm'][value='"+$("#mainForm_domain_pcHwDomain_shfsDm").val()+"']")[0].checked = true;
		}else {
			$("[name='ddShfsDm']")[0].checked = true;
		}
	}
	
</script>
</head>

<body>
<%try{ %>
<s:form action="hypchddj!initMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.shfsDm" />
	<s:hidden name="domain.pcHwDomain.shfsDm" />
		<div class="pop_contc">
		<fieldset>
			<legend>ʵ����Ϣ</legend>
			<table width="525" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td width="105" class="td_noborder"></td>
      				<td width="50" class="td_noborder"></td>
      				<td width="40" class="td_noborder"></td>
      				<td width="75" class="td_noborder"></td>
      				<td width="50" class="td_noborder"></td>
      				<td width="40" class="td_noborder"></td>
      				<td width="75" class="td_noborder"></td>
      				<td width="50" class="td_noborder"></td>
      				<td width="40" class="td_noborder"></td>
      			</tr>
      			<tr>
      				<td align="right">�������ƣ�</td>
      				<td colspan="3">
      					<s:textfield name="domain.pcHwDomain.hwmc" cssClass="pop_input noborder bgstyle_optional" />
      				</td>
      				<td colspan="2" align="right">�ص���ţ�</td>
      				<td colspan="3">
      					<s:textfield name="domain.hdbh" cssClass="pop_input noborder bgstyle_optional" />
      				</td>
      			</tr>
      			<tr>
      				<td align="right">�������ڣ�</td>
	  				<td colspan="2">
	  					<input type="text" name="domain.yqDdrq" id="mainForm_domain_yqDdrq" value="<s:date name="domain.yqDdrq" format="yyyy-MM-dd" />" class="pop_input noborder bgstyle_optional"  />
	  				</td>
	  				<td colspan="2" align="right">�ص��������ڣ�</td>
	  				<td colspan="2">
	  					<input type="text" name="domain.hdjsrq" id="mainForm_domain_hdjsrq" value="<s:date name="domain.hdjsrq" format="yyyy-MM-dd" />" class="pop_input noborder bgstyle_optional"  />
	  				</td>
	  				<td colspan="2"></td>
      			</tr>
      			<tr>
      				<s:hidden name="domain.szJsSl"></s:hidden>
      				<td align="right">�ջ���ʽ��</td>
	  				<td colspan="5">
	  					<s:radio name="shfsDm" disabled="true" list="#{'1':' ����','2':' �ͻ�����' }" theme="simple"></s:radio>
	  				</td>
      			</tr>
      			<tr>
      				<td align="right">ʵ��������</td>
      				<td>
      					<s:textfield name="domain.szHwSl" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      				<td>
      					<s:property value="domain.pcHwDomain.hwSlJldwMc" />
      				</td>
      				<td align="right">ʵ��������</td>
      				<td>
      					<s:textfield name="domain.szHwZl" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      				<td>
      					<s:property value="domain.pcHwDomain.hwZlJldwMc" />
      				</td>
      				<td align="right">ʵ�������</td>
      				<td>
      					<s:textfield name="domain.szHwTj" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      				<td>
      					<s:property value="domain.pcHwDomain.hwTjJldwMc" />
      				</td>
      			</tr>
	  			<tr>
	  				<td align="right">��ע��</td>
	  				<td colspan="8">
	  					<s:textarea name="domain.bz" rows="3" cssClass="pop_textarea_colspan2 noborder bgstyle_optional"></s:textarea>
	  				</td>
	  			</tr>
			</table>
			</fieldset>
			<fieldset>
			<legend>������Ϣ</legend>
			<table width="525" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td width="105" class="td_noborder"></td>
      				<td width="50" class="td_noborder"></td>
      				<td width="40" class="td_noborder"></td>
      				<td width="75" class="td_noborder"></td>
      				<td width="50" class="td_noborder"></td>
      				<td width="40" class="td_noborder"></td>
      				<td width="75" class="td_noborder"></td>
      				<td width="50" class="td_noborder"></td>
      				<td width="40" class="td_noborder"></td>
      			</tr>
      			<tr>
      				<td align="right">�ص���ţ�</td>
      				<td colspan="3">
      					<s:textfield name="domain.pcHwDomain.hdbh" cssClass="pop_input noborder bgstyle_optional" />
      				</td>
      			</tr>
      			<tr>
      			<s:hidden name="domain.pcHwDomain.jsSl"></s:hidden>
      				<td align="right">Ҫ�󵽻����ڣ�</td>
	  				<td colspan="2">
	  					<input type="text" name="domain.pcHwDomain.yqDdrq" value="<s:date name="domain.yqDdrq" format="yyyy-MM-dd" />"  class="pop_input noborder bgstyle_optional" />
	  				</td>
      				<td align="right">�ͻ���ʽ��</td>
	  				<td colspan="5">
	  					<s:radio name="ddShfsDm" disabled="true" list="#{'1':' ����','2':' �ͻ�����' }" theme="simple"></s:radio>
	  				</td>
      			</tr>
      			<tr>
      				<td align="right">������</td>
      				<td>
      					<s:textfield name="domain.pcHwDomain.hwSl" cssClass="pop_input noborder bgstyle_optional" ></s:textfield>
      				</td>
      				<td>
      					<s:property value="domain.pcHwDomain.hwSlJldwMc" />
      				</td>
      				<td align="right">������</td>
      				<td>
      					<s:textfield name="domain.pcHwDomain.hwZl" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      				<td>
      					<s:property value="domain.pcHwDomain.hwZlJldwMc" />
      				</td>
      				<td align="right">�����</td>
      				<td>
      					<s:textfield name="domain.pcHwDomain.hwTj" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      				<td>
      					<s:property value="domain.pcHwDomain.hwTjJldwMc" />
      				</td>
      			</tr>
	  			
			</table>
			</fieldset>
		</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
