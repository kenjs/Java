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

<script type="text/javascript"></script>
<base target="_self" />
</head>

<body>
<%try{ %>
<s:form action="jskpsq!initMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
		<s:hidden name="domain.kpsqDjxh"></s:hidden>
		<s:hidden name="domain.djJgbm"></s:hidden>
		<s:hidden name="domain.ssJgbm"></s:hidden>
		<s:hidden name="domain.existBz"></s:hidden>
		<s:hidden name="domain.kpsqfsDm"></s:hidden>
		<div class="pop_contc">
			<fieldset>
				<legend>������Ϣ</legend>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
					<tr>
						<td width="20%" align="right">����������</td>
	      				<td width="30%">
	      					<s:textfield name="domain.dwMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      				<td width="20%" align="right">�Ǽǲ��ţ�</td>
	      				<td width="30%">
	      					<s:textfield name="domain.bmMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      			</tr>
	      			<tr>
	      				<td align="right">�ͻ����ƣ�</td>
	      				<td>
	      					<s:hidden name="domain.khDjxh"></s:hidden>
	      					<s:textfield name="domain.khMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      			</tr>
	      		</table>
	      	</fieldset>
	      			<fieldset>
						<legend>��Ʊ��Ϣ</legend>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
			      			<tr>
				      			<td align="right" width="20%">���뿪Ʊ��</td>
			      				<td width="30%">
			      					<s:textfield name="domain.sqKpjeHj" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
			      				</td>
			      				<td align="right" width="20%">���뿪Ʊ���ڣ�</td>
			      				<td width="30%">
			      					<s:textfield name="domain.sqKprq" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
			      				</td>
			      			</tr>
			      			<tr>
			      				<td align="right">��ע˵����</td>
			      				<td colspan="3">
			      					<s:textarea name="domain.bzsm" rows="3" cssClass="pop_textarea noborder bgstyle_readonly" ></s:textarea>
			      				</td>
			      			</tr>
			      			<tr>
			      				<td align="right">�Ǽ��ˣ�</td>
			      				<td>
			      					<s:textfield name="domain.cjrMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
			      				</td>
			      				<td align="right">�Ǽ����ڣ�</td>
			      				<td>
			      					<s:textfield name="domain.djrq" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
			      				</td>
			      			</tr>
						</table>
					</fieldset>
			<br />
			<table id="zbTab" width="750" border="0" cellspacing="0" cellpadding="0" class="poptab_css">
		      <tr>
		      	<th width="30">���</th>
		        <th width="80">���뿪Ʊ���</th>
		        <th width="160">�嵥����</th>
		        <th width="80">�ϼƽ��</th>
		        <th width="85">�����뿪Ʊ���</th>
		        <th width="85">δ���뿪Ʊ���</th>
		        <th width="100">����</th>
		        <th width="100">��λ</th>
		      </tr>
		      <s:iterator value="domain.dataList" id="dzqd" status="sta">
		        <tr>
			      	<td align="center" class="bh"><s:property value="#sta.index+1" /></td>
			        <td align="center"><s:property value="#dzqd.sqKpJe" /></td>
			        <td align="center"><s:property value="#dzqd.qdmc" /></td>
			        <td align="center"><s:property value="#dzqd.heJe" /></td>
			        <td align="center"><s:property value="#dzqd.ysqKpJe" /></td>
			        <td align="center"><s:property value="#dzqd.wsqKpJe" /></td>
			        <td align="center"><s:property value="#dzqd.bmMc" /></td>
			        <td align="center"><s:property value="#dzqd.dwMc" /></td>
			      </tr>
		      </s:iterator>
		    </table>
		</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
