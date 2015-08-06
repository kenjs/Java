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

</head>

<body>
<%try{ %>
<s:form action="pcxydj!initMx" namespace="/zyegl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.pcDjxh" />
	<div class="pop_contc" >
		<fieldset>
		<legend>协议信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="15%" align="right">协议号：</td>
      				<td width="35%">
      					<s:textfield name="domain.xyh" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td width="15%" align="right">业务员：</td>
      				<td width="35%">
      					<s:textfield name="domain.ywyCzyMc" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      		</table>
      	</fieldset>
      	<fieldset>
		<legend>协议登记运费信息【原费用/现费用】</legend>
   			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td width="13%" align="right">总运费：</td>
      				<td width="20%">
      					<s:textfield name="domain.yxyfHj" cssClass="yfxx pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td width="13%" align="right">信息费：</td>
      				<td width="20%">
      					<s:textfield name="domain.yxyfXxf" cssClass="yfxx pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td width="13%" align="right">押金：</td>
      				<td width="20%">
      					<s:textfield name="domain.yxyfYj" cssClass="pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">预付运费：</td>
      				<td>
      					<s:textfield name="domain.yxyfYfyf" cssClass="yfxx pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">司机收：</td>
      				<td>
      					<s:textfield name="domain.yxyfSjs" cssClass="yfxx pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">货到运费：</td>
      				<td>
      					<s:textfield name="domain.yxyfHdyf" cssClass="yfxx pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">回单付：</td>
      				<td>
      					<s:textfield name="domain.yxyfHdf" cssClass="yfxx pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td></td>
      				<td></td>
      			</tr>
      			<tr>
      				<td align="right">备注：</td>
      				<td colspan="5">
      					<s:textarea name="domain.bz" rows="3" cssClass="pop_textarea_colspan2 noborder bgstyle_readonly" ></s:textarea>
      				</td>
      			</tr>
			</table>
			</fieldset>
			  <div style="width: 100%;overflow-x:auto;overflow-y:hidden;padding: 20px 0 20px 0;">
			  
				<table id="zbTab" width="1320px" border="0" cellspacing="0" cellpadding="0" class="poptab_css" align="center">
				      <tr>
				        <th width="30px">序号</th>
				        <th width="60px">订单编号</th>
				        <th width="150px">客户名称</th>
				        <th width="130px">货物名称</th>
				        <th width="60px">目的地</th>
				        <th width="80px">转入部门</th>
				        
				        <th width="80px">原/现结算数量</th>
				        <th width="60px">原/现数量</th>
				        <th width="60px">原/现重量</th>
				        <th width="60px">原/现体积</th>
				        
				        <th width="130px">收货人</th>
				        <th width="150px">收货地址</th>
				        <th width="60px">联系人</th>
				        <th width="130px">联系地址</th>
				        <th width="80px">要求到达日期</th>
				      </tr>
				      <s:iterator id="zb" value="domain.dataList" status="sta">
				      	<tr>
				        	<td align="center" class="bh"><s:property value="#sta.index+1" /></td>
					        <td align="center"><s:property value="#zb.ddbh" /></td>
					        <td align="center"><s:property value="#zb.khmc" /></td>
					        <td align="center"><s:property value="#zb.hwmc" /></td>
					        <td align="center"><s:property value="#zb.shrXzqhMc" /></td>
					        <td align="center"><s:property value="#zb.zrbmMc" /></td>

					       <td align="center"><s:property value="#zb.bdJsSl" /></td>
					       <td align="center"><s:property value="#zb.bdHwSl" /></td>
					       <td align="center"><s:property value="#zb.bdHwZl" /></td>
					       <td align="center"><s:property value="#zb.bdHwTj" /></td>
					       
					       <td align="center"><s:property value="#zb.shrMc" /></td>
					       <td align="center"><s:property value="#zb.shrDz" /></td>
					       <td align="center"><s:property value="#zb.shrLxr" /></td>
					       <td align="center"><s:property value="#zb.shrLxdh" /></td>
					       <td align="center"><s:property value="#zb.yqDdrq" /></td>
				      	</tr>
				      </s:iterator>
				</table>
			</div>
	  </div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
