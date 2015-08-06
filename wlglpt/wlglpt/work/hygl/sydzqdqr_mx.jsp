<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>上游对账清单确认</title>

<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript">
	$(function(){
	
		$("#closeBtn").click(function(){
				window.close();
			})
		
		$("[name='domain.zt']").change(function(){
			$("#mainForm_domain_bz").val("");
		});
			
		$("#saveBtn").click(function(){
			var qdDjxh = $("#mainForm_domain_qdDjxh").val();
			var zt = $(":checked[name='domain.zt']").val(); 
			var bz = trim($("#mainForm_domain_bz").val()); 
			
			if(zt == '4'){
				if(bz == ''){
					showAlert("请在备注说明内指明退回原因！");
					return;
				}
			}
			var url = jcontextPath+"/hygl/xyjssrdzqd!saveSydzqdqr";  
	    	var jsonObj = {"domain.qdDjxh":qdDjxh,"domain.zt":zt,"domain.bz":bz};
			ajaxCommon(url,jsonObj,"saveOK");
		});
	});
	
	function saveOK(data){
		showSuccess("保存成功！","saveAfter");
	}
	function saveAfter(){
		window.close();
	}
</script>
<base target="_self" />
</head>

<body >
<%try{ %>
<s:form action="xyjssrdzqd!initSydzqdQrMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
		<s:hidden name="domain.qdDjxh"/>
		<div class="pop_contc" style="height:220px; overflow:auto;">
		<fieldset>
			<legend>基本信息</legend>
    	 	 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
				    <td width="15%" align="right">清单名称:</td>
      				<td width="35%">
      					<s:textfield name="domain.qdmc" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
				    <td width="15%" align="right">上游单位:</td>
      				<td width="35%">
      					<s:textfield name="domain.ssJgmc" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
				<tr>
      				<td width="15%" align="right">合计金额：</td>
      				<td width="35%">
      					<s:textfield name="domain.heJe" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      				<td width="15%" align="right">对账金额：</td>
      				<td width="35%">
      					<s:textfield name="domain.dzJe" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td width="15%" align="right"></td>
      				<td width="35%">
      					<s:radio list="#{'3':'确认','4':'退回' }" name="domain.zt" value="'4'"/>
      				</td>
      				<td align="right">差异金额：</td>
      				<td>      					
      					<s:textfield name="domain.dzcyJe" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">备注说明：</td>
      				<td colspan="3">
      					<s:textarea name="domain.bz" cssClass="pop_textarea_colspan2"></s:textarea>
      				</td>
      			</tr>
			</table>
    	 </fieldset>
		<div class="pop_btn" style="margin-top: 10px;">
			   <button type="button" class="pop_btnbg" id="saveBtn">保存</button>
			 	&nbsp;
		       <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		</div> 
		</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
