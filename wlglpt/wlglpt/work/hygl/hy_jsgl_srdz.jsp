<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>����-�������</title>
<style type="text/css">

html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/jsgl_srdz.js" ></script>

</head>
<body>
<s:form action="jsglsrdz!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="jsonData" />
	<s:hidden name="domain.defultRqQ"></s:hidden>

	
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">������</li>
		    <li><a href="#" id="queryBtn" class="licon04">�� ��</a></li>
		    <li><a href="#" id="plScSendBtn" class="licon10">������������</a></li>
		    <li><a href="#" id="plDzBtn" class="licon10">��������</a></li>
		    <li><a href="#" id="closeBtn" class="licon03">�� ��</a></li>
	  	</ul>
	
	    <ul class="rcont">
		    <li class="ricon02" onclick="slideToggle('syquery')">��ʾ/���ز�ѯ����</li>
		    <li class="ricon03">����</li>
	  	</ul>
	</div> 
	
	<div class="right_cont" id="maincont">
	  <div id="divQuery">
		<fieldset>
			<legend>��ѯ����</legend>
		   <table width="100%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		          <td width="8%" align="right">ҵ��λ��</td>
		          <td width="21%">
		          	<sys:gsList myId="mainForm_domain_dwDm" myName="domain.dwDm" mcContainDmBz="Y" myClass="select" onChange="initList()"/>
		          </td>
		          <td width="8%" align="right">�Ǽǲ��ţ�</td>
		          <td width="22%">
		          	<select name="domain.bmDm" id="mainForm_domain_bmDm" class="select">
		          		<option value="${domain.bmDm }" selected="selected"></option>
		          	</select>
		          </td>
		           <td width="8%" align="right">���˷�ʽ��</td>
		           <td width="21%">
		          		<sys:dzfs myName="domain.dzfsDm" myId="mainForm_domain_dzfsDm" myClass="select" contaisQxz="false"></sys:dzfs>
		           </td>
		        </tr>
		        <tr>
		        	<td align="right">������ţ�</td>
		          <td>
		          	<s:textfield name="domain.ddbh" cssClass="pop_input noborder"></s:textfield>
		          </td>		          
		          <td align="right" id="rqTd">�µ����ڣ�</td>
		          <td>
		          	<s:textfield name="domain.rqQ" readonly="true" cssClass="ymdate"></s:textfield>
		          	��
		          	<s:textfield name="domain.rqZ" readonly="true" cssClass="ymdate"></s:textfield>
		          </td>
		          <td align="right">�ͻ����ƣ�</td>
		           <td>
		           	<s:hidden name="domain.khDjxh"></s:hidden>
		          	<div class="inputsel" style="width: 230px; ">
		          		<s:textfield name="domain.khMc" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width:200px;"></s:textfield>
		          		<a href="#" class="icon_arrow" id="fhr" onfocus="this.blur()"></a>
		          	</div>
		          	<div class="inputsc">
		              <div id="inputSel_fhr" class="inputselcont inputselFixedSize ac_results">
		              </div>
		            </div>
		           </td>
		        </tr>
		        <tr>
		          	<td align="right">����״̬��</td>
		          <td>
		          	<s:radio name="domain.dzztDm" list='#{"1":"δ����","2":"�Ѷ���"}' listKey="key" listValue="value"></s:radio>
		          </td>
		        	<td align="right">�½�״̬��</td>
		        	<td>
		        	<s:radio name="domain.yjZtDm" list='#{"1":"���½�","2":"���½�"}' listKey="key" listValue="value" value='"1"'></s:radio>
		        	</td>
		        </tr>
		   </table>
		</fieldset>
	  </div>
  
		<!-- ��ҳ��� id����ΪdataList -->
		<table id="dataList"><tr><td/></tr></table> 
		<!-- ��ҳ���� -->
		<div id="pager"></div>
		</div>
		<%@include file="/common/message.jsp" %>
</s:form>
</body>
</html>
