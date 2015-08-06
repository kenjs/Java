<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>下游结算-收入对帐</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function() {
			window.close();
		});
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var ywDjxh = trim($("#mainForm_domain_ywDjxh").val()); 
			var ywMxXh = trim($("#mainForm_domain_ywMxXh").val()); 
			var jsJe = trim($("#mainForm_domain_jsJe").val()); 
			var dzje = trim($("#mainForm_domain_dzje").val()); 
			var dzcyje = trim($("#mainForm_domain_dzcyje").val()); 
			var qdDjxh = trim($("#mainForm_domain_qdDjxh").val()); 
			var dzDjxh = trim($("#mainForm_domain_dzDjxh").val());
			var bz = trim($("#mainForm_domain_bz").val()); 

			if (dzcyje != "" && Math.abs(dzcyje/1) > 0.0 && bz == "") {
				showAlert("请在备注中录入差异说明！");
				return;
			}
			
			var url = jcontextPath+"/hygl/xyjssrdz!save";  
	    	var jsonObj = {"domain.ywDjxh":ywDjxh,"domain.ywMxXh":ywMxXh,
                           "domain.jsJe":jsJe,"domain.dzje":dzje,"domain.dzcyje":dzcyje,
                           "domain.qdDjxh":qdDjxh,"domain.dzDjxh":dzDjxh,"domain.bz":bz};   			
			ajaxCommon(url,jsonObj,"saveSuc");
		});
		
		$("#mainForm_domain_dzje").change(function(){
			var jsJe = $("#mainForm_domain_jsJe").val();
			var dzcyje = this.value/1 - jsJe/1;
			$("#mainForm_domain_dzcyje").val(dzcyje.toFixed(2));
		});
		
	});
	
	function saveSuc(data) {
		showAlert("保存成功！","closeWin");
	}
	
	function closeWin() {
		window.close();
	}
	
	function checkdata(){
		var controlNameArray = ["domain.dzje","domain.bz"];
		var labelNameArray = ["对账金额","备注"];
		var compareValueArray = [14.2,500];
		var nodeTypeArray = [NodeType.DECIMAL,NodeType.STRING];
		var notNullArray = [true,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="xyjssrdz!initMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.dzDjxh"/>
	<s:hidden name="domain.ywDjxh" />
	<s:hidden name="domain.ywMxXh" />
	<s:hidden name="domain.qdDjxh" />
	
	<div class="pop_contc">
		<fieldset>
		<legend>基本信息</legend>
		<table width="655" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
   			<tr>
   				<td width="75" align="right">订单编号：</td>
   				<td width="100"><s:textfield name="domain.ddbh" cssClass="pop_input noborder bgstyle_readonly"></s:textfield> </td>
   				<td width="75" align="right">下单日期：</td>
   				<td width="90"><input type="text" name="domain.xdrq" value="<s:date name='domain.xdrq' format='yyyy-MM-dd' />" class="pop_input noborder bgstyle_readonly" />  </td>
   				<td width="75" align="right">始发地：</td>
   				<td width="90"><s:textfield name="domain.sfdXzqhMc" cssClass="pop_input noborder bgstyle_readonly"></s:textfield> </td>
   				<td width="60" align="right">目的地：</td>
   				<td width="90"><s:textfield name="domain.mddXzqhMc" cssClass="pop_input noborder bgstyle_readonly"></s:textfield> </td>
   			</tr>
   			<tr>
   				<td align="right">货物名称：</td>
   				<td><s:textfield name="domain.hwmc" cssClass="pop_input noborder bgstyle_readonly"></s:textfield> </td>
   				<td align="right">数量：</td>
   				<td><s:textfield name="domain.hwSl" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>  </td>
   				<td align="right">重量：</td>
   				<td><s:textfield name="domain.hwZl" cssClass="pop_input noborder bgstyle_readonly"></s:textfield> </td>
   				<td align="right">体积：</td>
   				<td><s:textfield name="domain.hwTj" cssClass="pop_input noborder bgstyle_readonly"></s:textfield> </td>
   			</tr>
   			<tr>
   				<td align="right">收货单位：</td>
   				<td><s:textfield name="domain.shrMc" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>  </td>
   				<td align="right">收货人：</td>
   				<td><s:textfield name="domain.shrLxr" cssClass="pop_input noborder bgstyle_readonly"></s:textfield> </td>
   				<td align="right">收货地址：</td>
   				<td colspan="3"><s:textfield name="domain.shrDz" cssClass="pop_input noborder bgstyle_readonly"></s:textfield> </td>
   			</tr>
		</table>
		</fieldset>
		<fieldset>
		<legend>对账信息</legend>
		<table width="565" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
   			<tr>
   				<s:if test='domain.ywMxXh == "1"'>
   					<td width="90" align="right">配送费：</td>
   				</s:if>
   				<s:elseif test='domain.ywMxXh == "2"'>
   					<td width="90" align="right">到付款：</td>
   				</s:elseif>
   				<s:else>
   					<td width="90" align="right">代收货款：</td>
   				</s:else>
   				<td width="100"><s:textfield name="domain.jsJe" cssClass="pop_input noborder bgstyle_readonly"></s:textfield> </td>
   				<td width="90" align="right"><font class="font_red">*</font>对账金额：</td>
   				<td width="100"><s:textfield name="domain.dzje" cssClass="pop_input noborder bgstyle_optional"></s:textfield> </td>
   				<td width="90" align="right">差异金额：</td>
   				<td width="95"><s:textfield name="domain.dzcyje" cssClass="pop_input noborder bgstyle_readonly"></s:textfield> </td>
   			</tr>
   			<tr>
   				<td align="right">差异说明：</td>
   				<td colspan="5"><s:textarea name="domain.bz" rows="2" cssClass="pop_textarea_colspan2 noborder bgstyle_optional"></s:textarea> </td>
   			</tr>
		</table>
		</fieldset>
		<div class="pop_btn">
		 	<button type="button" class="pop_btnbg" id="saveBtn">保 存</button>
		 	&nbsp;
		    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
	    </div>
	</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
