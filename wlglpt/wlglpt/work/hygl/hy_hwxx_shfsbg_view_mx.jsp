<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>����-�ͻ���ʽ���</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		});
		$("#deleteBtn").click(function(){
			var ddDjxh = $("#mainForm_domain_ddDjxh").val(); 
			var pcDjxh = $("#mainForm_domain_pcDjxh").val(); 
			var wfhDjxh = $("#mainForm_domain_wfhDjxh").val(); 
			var xh = $("#mainForm_domain_xh").val(); 
			var shBz = trim($("#mainForm_domain_shBz").val());
			
			var url = jcontextPath+"/hygl/hyhwxxshfsbg!delete";  
	    	var jsonObj = {"domain.ddDjxh":ddDjxh,"domain.pcDjxh":pcDjxh,"domain.xh":xh,"domain.wfhDjxh":wfhDjxh,"domain.shBz":shBz};  			
			ajaxCommon(url,jsonObj,"saveSuc");
		});
		initMx();
	});
	function initMx(){
		var shBz = trim($("#mainForm_domain_shBz").val());
		if(shBz=='1'){
			$("#shfsDm").val('1');
		}else{
			$("#shfsDm").val('2');
		}
		$("#mainForm_domain_ddhwDomain_hwSlJldwDm").attr("disabled","true");
		$("#mainForm_domain_ddhwDomain_hwZlJldwDm").attr("disabled","true");
		$("#mainForm_domain_ddhwDomain_hwTjJldwDm").attr("disabled","true");
		$("body").focus();
	}
	function saveSuc(){
		showAlert("�����ɹ���","closeWin");
	}
	function closeWin(){
		window.dialogArguments.onRefresh();
		window.close();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="hygl/hyhwxxshfsbg!initMx.action" namespace="" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.shBz"></s:hidden>
	<s:hidden name="domain.pcDjxh"></s:hidden>
	<s:hidden name="domain.wfhDjxh"></s:hidden>
	<s:hidden name="domain.ddDjxh"></s:hidden>
	<s:hidden name="domain.xh"></s:hidden>
	<div class="pop_contc">
	<fieldset>
			<legend>���ͷ�¼��</legend>
			<table width="95%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td align="right" width="10%">�������ͷ�:</td>
      				<td width="14%">
      					<s:textfield name="domain.bspsf" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
      				</td>
      				<td align="right" width="13%">������ͻ���ʽ:</td>
      				<td width="14%">
      					<s:select list="#{'':' ---��ѡ��---','1':' ����','2':' �ͻ�' }" id="shfsDm" cssClass="select" disabled="true"></s:select>
      				</td>
      				<td align="right" width="10%">������:</td>
      				<td width="12%">
      					<s:textfield name="domain.cjrMc" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
      				</td>
      				<td align="right" width="10%">��������:</td>
      				<td width="12%">
      					<s:textfield name="domain.cjrq" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="center">��ע</td>
      				<td colspan="7">
      					<s:textarea name="domain.bz" rows="3" cssClass="pop_textarea_colspan2" readonly="true"></s:textarea>
      				</td>
      			</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>����������Ϣ</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td width="6%" class="td_noborder"></td>
      				<td width="12%" class="td_noborder"></td>
      				
      				<td width="10%" class="td_noborder"></td>
      				<td width="5%" class="td_noborder"></td>
      				<td width="5%" class="td_noborder"></td>
      				
      				<td width="10%" class="td_noborder"></td>
      				<td width="5%" class="td_noborder"></td>
      				<td width="5%" class="td_noborder"></td>
      				
      				<td width="10%" class="td_noborder"></td>
      				<td width="5%" class="td_noborder"></td>
      				<td width="5%" class="td_noborder"></td>
      				
      				<td width="8%" class="td_noborder"></td>
      				<td width="14%" class="td_noborder"></td>
      			</tr>
      			<tr>
      				<td align="right">������</td>
      				<td>
      					<s:textfield name="domain.ddhwDomain.hwmc" cssClass="pop_input noborder inputext bgstyle_readonly"></s:textfield>
      				</td>
      				<td align="right">������</td>
      				<td>
      					<s:textfield name="domain.ddhwDomain.hwSl" cssClass="pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td>
      					<sys:QySlJldw myName="domain.ddhwDomain.hwSlJldwDm" myId="mainForm_domain_ddhwDomain_hwSlJldwDm" contaisQxz="false" myClass="select noTabSelect">
      					</sys:QySlJldw>
      				</td>      			
      				<td align="right">������</td>
      				<td>
      					<s:textfield name="domain.ddhwDomain.hwZl" cssClass="pop_input inputright noborder bgstyle_readonly hwflObserver" ></s:textfield>
      				</td>
      				<td>
      					<sys:QyZlJldw myName="domain.ddhwDomain.hwZlJldwDm" myId="mainForm_domain_ddhwDomain_hwZlJldwDm" contaisQxz="false" myClass="select noTabSelect">      						
      					</sys:QyZlJldw>
      				</td>
      				<td align="right">�����</td>
      				<td>
      					<s:textfield name="domain.ddhwDomain.hwTj" cssClass="pop_input inputright noborder bgstyle_readonly hwflObserver" ></s:textfield>
      				</td>
      				<td>
      					<sys:QyTjJldw myName="domain.ddhwDomain.hwTjJldwDm" myId="mainForm_domain_ddhwDomain_hwTjJldwDm" contaisQxz="false" myClass="select noTabSelect">      					
      					</sys:QyTjJldw>
      				</td>
      				<td align="right">���ջ��</td>
	   				<td >
	   					<s:textfield name="domain.ddhwDomain.fyDshk" cssClass="pop_input inputright noborder bgstyle_readonly" ></s:textfield>
	   				</td>
      		   </tr>
      		   <tr>
  				<td align="right">�����:</td>
  				<td>
  					<s:textfield name="domain.ddhwDomain.srYsf" cssClass="pop_input inputright noborder bgstyle_readonly srHjJs" ></s:textfield>
  				</td>
  				<td align="right">���ͷ�:</td>
  				<td colspan="2">
  					<s:textfield name="domain.ddhwDomain.bgPsf" cssClass="pop_input inputright noborder bgstyle_readonly srHjJs" ></s:textfield>
  				</td>
  				<td  class="jsfsChanged" align="right">�ؿۣ�</td>
  				<td class="jsfsChanged" colspan="2">
  					<s:textfield name="domain.ddhwDomain.srHk" cssClass="pop_input inputright noborder bgstyle_readonly srHjJs " ></s:textfield>
  				</td>
  				<td align="right">���۷�:</td>
  				<td colspan="2">
  					<s:textfield name="domain.ddhwDomain.srBjf" cssClass="pop_input inputright noborder bgstyle_readonly srHjJs" ></s:textfield>
  				</td>
  				<td align="right"  class="jsfsChanged">�����룺</td>
  				<td class="jsfsChanged" >
  					<s:textfield name="domain.ddhwDomain.bgHj" cssClass="pop_input inputright noborder bgstyle_readonly  jsfsChanged" ></s:textfield>
  				</td>
  			</tr>
  			<tr>
  				<td align="right" >�ָ���</td>
  				<td>
  					<s:textfield name="domain.ddhwDomain.srXf" cssClass="pop_input inputright noborder bgstyle_readonly " ></s:textfield>
  				</td>
  				<td align="right" class="df">������</td>
  				<td class="df" colspan="2">
  					<s:textfield name="domain.ddhwDomain.bgDf" cssClass="pop_input inputright noborder bgstyle_readonly " ></s:textfield>
  				</td>
  				<td align="right" class="jsfsChanged" >�½᣺</td>
  				<td  class="jsfsChanged" colspan="2">
  					<s:textfield name="domain.ddhwDomain.srYj" cssClass="pop_input inputright noborder bgstyle_readonly  jsfsChanged" ></s:textfield>
  				</td>
  				<td align="right" class="jsfsChanged" style="display: none;">�ظ���</td>
  				<td  class="jsfsChanged" style="display: none;">
  					<s:textfield name="domain.ddhwDomain.srHf" cssClass="pop_input inputright noborder bgstyle_readonly  jsfsChanged" ></s:textfield>
  				</td>
  			</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>�ɳ�������Ϣ</legend>
			<table width="95%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td align="center" width="10%">����:</td>
      				<td width="14%">
      					<s:textfield name="domain.pchwDomain.hwSl" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
      				</td>
      				<td align="center" width="13%">����:</td>
      				<td width="14%">
      					<s:textfield name="domain.pchwDomain.hwZl" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
      				</td>
      				<td align="center" width="10%">���:</td>
      				<td width="12%">
      					<s:textfield name="domain.pchwDomain.hwTj" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
      				</td>
      				<td align="center" width="10%" class="pcDf">����:</td>
      				<td width="12%" class="pcDf">
      					<s:textfield name="domain.pchwDomain.bgDf" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
			</table>
		</fieldset>
			<div class="pop_btn">
			 	<button type="button" class="pop_btnbg" id="deleteBtn">�� ��</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
		    </div>
	</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
