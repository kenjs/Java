<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>������ʣ�������</title>

<style type="text/css">
html,body {background:none;}
</style>
<base target="_self" />
</head>

<body>
<%try{ %>
<s:form action="jsglsrdz!view" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.jsSrdzDomain.jsDjxh"></s:hidden>
		<div class="pop_contc">
			<fieldset>
				<legend>������Ϣ</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
						<tr>
		      				<td width="14%" align="right"><font class="font_red">*</font>�ͻ����ƣ�</td>
		      				<td width="26%">
		      					<s:hidden name="domain.jsSrdzDomain.khDjxh"></s:hidden>
		      					<s:textfield name="domain.khMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
		      				</td>
		      				<td align="right" width="12%"><font class="font_red">*</font>������ţ�</td>
		      				<td width="16%">
		      					<s:textfield name="domain.ddbh" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
		      				</td>
		      				<td align="right" width="18%"><font class="font_red">*</font>�µ����ڣ�</td>
		      				<td width="14%">
		      					<input type="text" name="domain.xdrq" id="mainForm_domain_xdrq" value="<s:date name="domain.xdrq" format="yyyy-MM-dd" />" class="pop_input noborder bgstyle_readonly" />
		      				</td>
		      			</tr>
		      			<tr>
		      				<td align="right"><font class="font_red">*</font>�������룺</td>
		      				<td>
		      					<s:textfield name="domain.dzsr" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
		      				</td>
		      				<td align="right"><font class="font_red">*</font>�ѽ᣺</td>
		      				<td>
		      					<s:textfield name="domain.dzyj" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
		      				</td>
		      				<td align="right"><font class="font_red">*</font>δ�ᣨ���ʽ���</td>
		      				<td>
		      					<s:textfield name="domain.dzwj" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
		      				</td>
		      			</tr>
		      		</table>
		     </fieldset>
		     <fieldset>
		      	<legend>������Ϣ</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
		      			<tr>
		      				<td align="right" width="14%">δ�᣺</td>
		      				<td width="26%">
		      					<s:hidden name="domain.jsSrdzDomain.jsSr"></s:hidden>
		      					<s:hidden name="domain.jsSrdzDomain.jsYj"></s:hidden>
		      					<s:textfield name="domain.jsSrdzDomain.jsWj" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
		      				</td>
		      				<td align="right" width="12%"><font class="font_red">*</font>���ʽ�</td>
		      				<td width="16%">
		      					<s:textfield name="domain.jsSrdzDomain.dzje" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
		      				</td>
		      				<td align="right" width="18%">�����</td>
		      				<td width="14%">
		      					<s:textfield name="domain.jsSrdzDomain.dzcyje" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
		      				</td>
		      			</tr>
		      		</table>
		     </fieldset>		     
			<br />
			<table width="760" border="0" cellspacing="0" cellpadding="0" class="poptab_css">
		      <tr>
		      	<th width="30">���</th>
		        <th width="80">�������</th>
		        <th width="150">����ԭ��</th>
		        <th width="200">����ʽ</th>
		        <th width="200">��ע˵��</th>
		        <!--  
		        <th width="100">���ν���Ǽ����</th>
		        <th width="100">������ʧ�Ǽ����</th>
		        -->
		      </tr>
		      <s:iterator value="domain.jsSrdzcyList" id="dzcy" status="sta">
		      	<tr>
		      		<td align="center" class="bh"><s:property value="#sta.index+1" /></td>
			        <td align="center">
				        <s:property value="#dzcy.dzcyje" />
			        </td>
			        <td align="center"><s:property value="#dzcy.dzcyyyMc" /></td>
			        <td align="center"><s:property value="#dzcy.dzcyClfsMc" /></td>
			        <td align="left"><s:property value="#dzcy.bz" /></td>
			        <!--  
			        <td align="center"><s:property value="#dzcy.preJsDjxh" /></td>
			        <td align="center"><s:property value="#dzcy.wlssDjxh" /></td>
			        -->
		      	</tr>
		      </s:iterator>
		    </table>
		</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
