<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>Э��Ǽ�</title>

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
		<legend>Э����Ϣ</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="15%" align="right">Э��ţ�</td>
      				<td width="35%">
      					<s:textfield name="domain.xyh" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td width="15%" align="right">ҵ��Ա��</td>
      				<td width="35%">
      					<s:textfield name="domain.ywyCzyMc" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      		</table>
      	</fieldset>
      	<fieldset>
		<legend>Э��Ǽ��˷���Ϣ��ԭ����/�ַ��á�</legend>
   			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td width="13%" align="right">���˷ѣ�</td>
      				<td width="20%">
      					<s:textfield name="domain.yxyfHj" cssClass="yfxx pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td width="13%" align="right">��Ϣ�ѣ�</td>
      				<td width="20%">
      					<s:textfield name="domain.yxyfXxf" cssClass="yfxx pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td width="13%" align="right">Ѻ��</td>
      				<td width="20%">
      					<s:textfield name="domain.yxyfYj" cssClass="pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">Ԥ���˷ѣ�</td>
      				<td>
      					<s:textfield name="domain.yxyfYfyf" cssClass="yfxx pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">˾���գ�</td>
      				<td>
      					<s:textfield name="domain.yxyfSjs" cssClass="yfxx pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">�����˷ѣ�</td>
      				<td>
      					<s:textfield name="domain.yxyfHdyf" cssClass="yfxx pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">�ص�����</td>
      				<td>
      					<s:textfield name="domain.yxyfHdf" cssClass="yfxx pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td></td>
      				<td></td>
      			</tr>
      			<tr>
      				<td align="right">��ע��</td>
      				<td colspan="5">
      					<s:textarea name="domain.bz" rows="3" cssClass="pop_textarea_colspan2 noborder bgstyle_readonly" ></s:textarea>
      				</td>
      			</tr>
			</table>
			</fieldset>
			  <div style="width: 100%;overflow-x:auto;overflow-y:hidden;padding: 20px 0 20px 0;">
			  
				<table id="zbTab" width="1320px" border="0" cellspacing="0" cellpadding="0" class="poptab_css" align="center">
				      <tr>
				        <th width="30px">���</th>
				        <th width="60px">�������</th>
				        <th width="150px">�ͻ�����</th>
				        <th width="130px">��������</th>
				        <th width="60px">Ŀ�ĵ�</th>
				        <th width="80px">ת�벿��</th>
				        
				        <th width="80px">ԭ/�ֽ�������</th>
				        <th width="60px">ԭ/������</th>
				        <th width="60px">ԭ/������</th>
				        <th width="60px">ԭ/�����</th>
				        
				        <th width="130px">�ջ���</th>
				        <th width="150px">�ջ���ַ</th>
				        <th width="60px">��ϵ��</th>
				        <th width="130px">��ϵ��ַ</th>
				        <th width="80px">Ҫ�󵽴�����</th>
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
