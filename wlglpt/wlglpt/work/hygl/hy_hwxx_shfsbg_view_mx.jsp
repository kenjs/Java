<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>货运-送货方式变更</title>

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
		showAlert("撤销成功！","closeWin");
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
			<legend>配送费录入</legend>
			<table width="95%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td align="right" width="10%">补收配送费:</td>
      				<td width="14%">
      					<s:textfield name="domain.bspsf" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
      				</td>
      				<td align="right" width="13%">变更后送货方式:</td>
      				<td width="14%">
      					<s:select list="#{'':' ---请选择---','1':' 自提','2':' 送货' }" id="shfsDm" cssClass="select" disabled="true"></s:select>
      				</td>
      				<td align="right" width="10%">创建人:</td>
      				<td width="12%">
      					<s:textfield name="domain.cjrMc" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
      				</td>
      				<td align="right" width="10%">创建日期:</td>
      				<td width="12%">
      					<s:textfield name="domain.cjrq" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="center">备注</td>
      				<td colspan="7">
      					<s:textarea name="domain.bz" rows="3" cssClass="pop_textarea_colspan2" readonly="true"></s:textarea>
      				</td>
      			</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>订单基本信息</legend>
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
      				<td align="right">货名：</td>
      				<td>
      					<s:textfield name="domain.ddhwDomain.hwmc" cssClass="pop_input noborder inputext bgstyle_readonly"></s:textfield>
      				</td>
      				<td align="right">数量：</td>
      				<td>
      					<s:textfield name="domain.ddhwDomain.hwSl" cssClass="pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td>
      					<sys:QySlJldw myName="domain.ddhwDomain.hwSlJldwDm" myId="mainForm_domain_ddhwDomain_hwSlJldwDm" contaisQxz="false" myClass="select noTabSelect">
      					</sys:QySlJldw>
      				</td>      			
      				<td align="right">重量：</td>
      				<td>
      					<s:textfield name="domain.ddhwDomain.hwZl" cssClass="pop_input inputright noborder bgstyle_readonly hwflObserver" ></s:textfield>
      				</td>
      				<td>
      					<sys:QyZlJldw myName="domain.ddhwDomain.hwZlJldwDm" myId="mainForm_domain_ddhwDomain_hwZlJldwDm" contaisQxz="false" myClass="select noTabSelect">      						
      					</sys:QyZlJldw>
      				</td>
      				<td align="right">体积：</td>
      				<td>
      					<s:textfield name="domain.ddhwDomain.hwTj" cssClass="pop_input inputright noborder bgstyle_readonly hwflObserver" ></s:textfield>
      				</td>
      				<td>
      					<sys:QyTjJldw myName="domain.ddhwDomain.hwTjJldwDm" myId="mainForm_domain_ddhwDomain_hwTjJldwDm" contaisQxz="false" myClass="select noTabSelect">      					
      					</sys:QyTjJldw>
      				</td>
      				<td align="right">代收货款：</td>
	   				<td >
	   					<s:textfield name="domain.ddhwDomain.fyDshk" cssClass="pop_input inputright noborder bgstyle_readonly" ></s:textfield>
	   				</td>
      		   </tr>
      		   <tr>
  				<td align="right">运输费:</td>
  				<td>
  					<s:textfield name="domain.ddhwDomain.srYsf" cssClass="pop_input inputright noborder bgstyle_readonly srHjJs" ></s:textfield>
  				</td>
  				<td align="right">配送费:</td>
  				<td colspan="2">
  					<s:textfield name="domain.ddhwDomain.bgPsf" cssClass="pop_input inputright noborder bgstyle_readonly srHjJs" ></s:textfield>
  				</td>
  				<td  class="jsfsChanged" align="right">回扣：</td>
  				<td class="jsfsChanged" colspan="2">
  					<s:textfield name="domain.ddhwDomain.srHk" cssClass="pop_input inputright noborder bgstyle_readonly srHjJs " ></s:textfield>
  				</td>
  				<td align="right">保价费:</td>
  				<td colspan="2">
  					<s:textfield name="domain.ddhwDomain.srBjf" cssClass="pop_input inputright noborder bgstyle_readonly srHjJs" ></s:textfield>
  				</td>
  				<td align="right"  class="jsfsChanged">总收入：</td>
  				<td class="jsfsChanged" >
  					<s:textfield name="domain.ddhwDomain.bgHj" cssClass="pop_input inputright noborder bgstyle_readonly  jsfsChanged" ></s:textfield>
  				</td>
  			</tr>
  			<tr>
  				<td align="right" >现付：</td>
  				<td>
  					<s:textfield name="domain.ddhwDomain.srXf" cssClass="pop_input inputright noborder bgstyle_readonly " ></s:textfield>
  				</td>
  				<td align="right" class="df">到付：</td>
  				<td class="df" colspan="2">
  					<s:textfield name="domain.ddhwDomain.bgDf" cssClass="pop_input inputright noborder bgstyle_readonly " ></s:textfield>
  				</td>
  				<td align="right" class="jsfsChanged" >月结：</td>
  				<td  class="jsfsChanged" colspan="2">
  					<s:textfield name="domain.ddhwDomain.srYj" cssClass="pop_input inputright noborder bgstyle_readonly  jsfsChanged" ></s:textfield>
  				</td>
  				<td align="right" class="jsfsChanged" style="display: none;">回付：</td>
  				<td  class="jsfsChanged" style="display: none;">
  					<s:textfield name="domain.ddhwDomain.srHf" cssClass="pop_input inputright noborder bgstyle_readonly  jsfsChanged" ></s:textfield>
  				</td>
  			</tr>
			</table>
		</fieldset>
		<fieldset>
			<legend>派车基本信息</legend>
			<table width="95%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td align="center" width="10%">数量:</td>
      				<td width="14%">
      					<s:textfield name="domain.pchwDomain.hwSl" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
      				</td>
      				<td align="center" width="13%">重量:</td>
      				<td width="14%">
      					<s:textfield name="domain.pchwDomain.hwZl" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
      				</td>
      				<td align="center" width="10%">体积:</td>
      				<td width="12%">
      					<s:textfield name="domain.pchwDomain.hwTj" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
      				</td>
      				<td align="center" width="10%" class="pcDf">到付:</td>
      				<td width="12%" class="pcDf">
      					<s:textfield name="domain.pchwDomain.bgDf" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
			</table>
		</fieldset>
			<div class="pop_btn">
			 	<button type="button" class="pop_btnbg" id="deleteBtn">撤 销</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		    </div>
	</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
