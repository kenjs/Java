<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>下游结算-转包-月结对账</title>

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
			var dzDjxh = trim($("#mainForm_domain_dzDjxh").val());
			var jsYj = trim($("#mainForm_domain_jsYj").val()); 
			var dzje = trim($("#mainForm_domain_dzje").val()); 
			var dzCybz = trim($("#mainForm_domain_dzCybz").val()); 
			var dzcyje = trim($("#mainForm_domain_dzcyje").val());

			var url = jcontextPath+"/hygl/xyjszbyjdz!save";  
	    	var jsonObj = {"domain.dzDjxh":dzDjxh,"domain.jsYj":jsYj,"domain.dzje":dzje,"domain.dzCybz":dzCybz,"domain.dzcyje":dzcyje};   			
			ajaxCommon(url,jsonObj,"saveSuc");
		});
		//获取光标
		$("#mainForm_domain_dzje").focus();
	});


	//spgsbm 审批公司编码，默认为当前操作员所在公司，但是费用报销申请比较特殊，为记账单位编码
	function scSendzd(wsDm,xh,wsXmflDjxh,spgsbm){  
		 if (spgsbm == undefined || spgsbm == null) {
			spgsbm = "";
		 }
		  var sprCzyDjxh="",oldWsspxh="";
	      var url = jcontextPath+"/common/wsspCommon!scSend"; 
	   	  var jsonObj = {"domain.wsDm":wsDm,"domain.wsXmflDjxh":wsXmflDjxh,"domain.ywDjxh":xh,"domain.sprCzyDjxh":sprCzyDjxh,"domain.oldWsspxh":oldWsspxh};
	      ajaxCommon(url,jsonObj,"doScSendSuc",false);
	}
	function doScSendSuc(data) {
		hideMessage();
		showAlert("发送成功！");
	}
	
	function saveSuc(data) {
		if(data.domain.sfsp=="Y" && data.domain.zdsp=="Y"){
			var wsDm="306001"; //分包商月结对账表
			scSendzd(wsDm,data.domain.dzDjxh,"","");
			alert("对账完成，自动发送成功！");
		}else{
			alert("对账成功！");
		}
		window.close();
	}
	
	function checkdata(){
		var controlNameArray = ["domain.jsYj","domain.dzje","domain.dzcyje"];
		var labelNameArray = ["结算_未结","对账金额","差异金额"];
		var compareValueArray = [16.2,16.2,16.2];
		var nodeTypeArray = [NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL];
		var notNullArray = [true,true,true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	
	//计算差异
	function jscy(){
		var jsYj = trim($("#mainForm_domain_jsYj").val());
		var dzje = trim($("#mainForm_domain_dzje").val()); 
		var cyje = parseFloat(jsYj) - parseFloat(dzje);
		if(cyje > 0){
			$("#mainForm_domain_dzCybz").val("Y");
		}else if(cyje == 0){
			$("#mainForm_domain_dzCybz").val("N");
		}
		$("#mainForm_domain_dzcyje").val(cyje);
	}
	
</script>
</head>

<body>
<%try{ %>
<s:form action="xyjszbyjdz!initMx" namespace="/wlgl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.dzDjxh"/>
	<div class="pop_contc">
		<fieldset>
			<legend>基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td align="right">派车单号：</th>
      				<td>
      					<s:textfield name="domain.pcdh" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
      				</td>
      				<td align="right">派车日期：</th>
      				<td>
      					<input type="text" name="domain.pcrq" value="<s:date name='domain.pcrq' format='yyyy-MM-dd' />" class="pop_input noborder bgstyle_readonly" />
      				</td>
      			</tr>
      			<tr>
      				<td align="right">货物名称：</th>
      				<td>
      					<s:textfield name="domain.hwmc" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      			
      				<td align="right">分包商分类：</th>
      				<td>
      					<s:textfield name="domain.zrbmmc" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">分包商：</th>
      				<td>
      					<s:textfield name="domain.fbsmc" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right"><font class="font_red">*</font>结算_未结：</th>
      				<td>
      					<s:textfield name="domain.jsYj" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">对账金额：</th>
      				<td>
      					<s:textfield name="domain.dzje" onchange="jscy();" cssClass="pop_input noborder bgstyle_required" ></s:textfield>
      					<input type="hidden" id="mainForm_domain_dzCybz" value='<s:property value="domain.dzCybz"/>'/>
      				</td>
      				<td align="right">对帐差异金额：</th>
      				<td>
      					<s:textfield name="domain.dzcyje" id="mainForm_domain_dzcyje" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
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
