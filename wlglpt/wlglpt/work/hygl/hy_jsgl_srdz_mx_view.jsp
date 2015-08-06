<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>收入对帐（订单）</title>

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
				<legend>订单信息</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
						<tr>
		      				<td width="14%" align="right"><font class="font_red">*</font>客户名称：</td>
		      				<td width="26%">
		      					<s:hidden name="domain.jsSrdzDomain.khDjxh"></s:hidden>
		      					<s:textfield name="domain.khMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
		      				</td>
		      				<td align="right" width="12%"><font class="font_red">*</font>订单编号：</td>
		      				<td width="16%">
		      					<s:textfield name="domain.ddbh" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
		      				</td>
		      				<td align="right" width="18%"><font class="font_red">*</font>下单日期：</td>
		      				<td width="14%">
		      					<input type="text" name="domain.xdrq" id="mainForm_domain_xdrq" value="<s:date name="domain.xdrq" format="yyyy-MM-dd" />" class="pop_input noborder bgstyle_readonly" />
		      				</td>
		      			</tr>
		      			<tr>
		      				<td align="right"><font class="font_red">*</font>订单收入：</td>
		      				<td>
		      					<s:textfield name="domain.dzsr" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
		      				</td>
		      				<td align="right"><font class="font_red">*</font>已结：</td>
		      				<td>
		      					<s:textfield name="domain.dzyj" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
		      				</td>
		      				<td align="right"><font class="font_red">*</font>未结（对帐金额）：</td>
		      				<td>
		      					<s:textfield name="domain.dzwj" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
		      				</td>
		      			</tr>
		      		</table>
		     </fieldset>
		     <fieldset>
		      	<legend>对账信息</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
		      			<tr>
		      				<td align="right" width="14%">未结：</td>
		      				<td width="26%">
		      					<s:hidden name="domain.jsSrdzDomain.jsSr"></s:hidden>
		      					<s:hidden name="domain.jsSrdzDomain.jsYj"></s:hidden>
		      					<s:textfield name="domain.jsSrdzDomain.jsWj" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
		      				</td>
		      				<td align="right" width="12%"><font class="font_red">*</font>对帐金额：</td>
		      				<td width="16%">
		      					<s:textfield name="domain.jsSrdzDomain.dzje" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
		      				</td>
		      				<td align="right" width="18%">差异金额：</td>
		      				<td width="14%">
		      					<s:textfield name="domain.jsSrdzDomain.dzcyje" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
		      				</td>
		      			</tr>
		      		</table>
		     </fieldset>		     
			<br />
			<table width="760" border="0" cellspacing="0" cellpadding="0" class="poptab_css">
		      <tr>
		      	<th width="30">序号</th>
		        <th width="80">调整金额</th>
		        <th width="150">差异原因</th>
		        <th width="200">处理方式</th>
		        <th width="200">备注说明</th>
		        <!--  
		        <th width="100">二次结算登记序号</th>
		        <th width="100">物流损失登记序号</th>
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
