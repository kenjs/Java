<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.cy.common.domain.UserDomain"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>����������ʽ</title>
<%@ include file="/common/meta.jsp"%>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	function onTc() {
			//showAlert("������~~~");
			showMaxError("������~~~");
			//checkdata();
			
		}
	
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		})
	}) 
			
$(document).ready(function(){
	var sjJgbm = $("#gs").val();
	bmInit(sjJgbm, '', "bm", "bm", "Y", "N");
	
	var bm = $("#bm").val();
	gwInit(bm, '', "gw", "gw", "Y", "N");
});
</script>
</head>

<body>
<s:form action="demo!initMx" namespace="/xtgl" method="post" id="mainForm" name="mainForm">
<!-- �������ڱ�����ر�ͼ�� id="toolbar"Ϊ�϶�iframe�����õ��������޸� -->

<!-- ���������������� style������Ϊ�������ݸ߶ȣ������涨�ĸ߶��Զ������������߶�ֵ����ʵ�ʸı� -->

 <div class="pop_contc" style="height:320px; overflow:auto;">
  	 <fieldset>
		  <legend>������Ϣ</legend>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      
      <tr>
        <td width="12%" align="right">��˾����:</td>
        <td width="88%" colspan="3"><button type="button" class="pop_btnbg" onclick="onTc();">������</button></td>
      </tr>
      <tr>
        <td align="right">��˾����:</td>
        <td colspan="3">����ϵͳ</td>
      </tr>
      <tr>
        <td align="right">��˾����:</td>
        <td colspan="3">����ϵͳ</td>
      </tr>
     
      <tr>
        <td align="right">��˾����:</td>
        <td colspan="3">����ϵͳ</td>
      </tr>
      <tr>
        <td align="right">��˾����:</td>
        <td colspan="3">����ϵͳ</td>
      </tr>
      <tr>
        <td align="right">��˾����:</td>
        <td colspan="3">����ϵͳ</td>
      </tr>
      <tr>
         <td width="15%" align="right">��˾��</td>
         <td width="35%">
         	<sys:gsList myId="gs" myName="gs" onChange="bmInit(this.value, '', 'bm', 'bm', 'Y', 'N')" mcContainDmBz="N" myClass="select"/>
		  </td>
		  <td width="15%" align="right">���ţ�</td>
	         <td width="35%">
	          	<select id="bm" name="bm" class="select" onchange="gwInit(this.value, '', 'gw', 'gw', 'Y', 'N')"></select>
		  </td>
       </tr>
       <tr>
         <td width="15%" align="right">��λ��</td>
         <td width="35%">
         	<select id="gw" name="gw" class="select"></select>
		  </td>
		  <td width="15%" align="right"></td>
	         <td width="35%">
	          	<button type="button" class="pop_btnbg" onclick="onTc();">������</button>
		  </td>
       </tr>
    </table>
    </fieldset>
    <div class="pop_btn">
      <button type="button" class="pop_btnbg">�� ��</button>
      &nbsp;
       <button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
    </div>
  </div>

<%@include file="/common/message.jsp" %>
</s:form>
</body>
</html>