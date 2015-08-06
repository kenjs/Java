<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>外联人员维护</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(document).ready(function(){
		var jgbm = $("#mainForm_domain_jgbm").val();
		if(jgbm != null && jgbm !="" && jgbm != undefined){
			commonInit('gs-wlryfl', jgbm,'', 'domain.wlryFlxh', 'mainForm_domain_wlryFlxh', 'N', 'Y');
		}			

	});
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		});
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var wlryFlxh = trim($("#mainForm_domain_wlryFlxh").val()); 
			var xm = trim($("#mainForm_domain_xm").val()); 
			var dz = trim($("#mainForm_domain_dz").val()); 
			var dh = trim($("#mainForm_domain_dh").val()); 
			var cz = trim($("#mainForm_domain_cz").val()); 
			var sj = trim($("#mainForm_domain_sj").val()); 
			var wz = trim($("#mainForm_domain_wz").val()); 
			var yb = trim($("#mainForm_domain_yb").val()); 
			var dy = trim($("#mainForm_domain_dy").val()); 
			var bz = trim($("#mainForm_domain_bz").val()); 
			var wlryDjxh = trim($("#mainForm_domain_wlryDjxh").val()); 

			var url = jcontextPath+"/bggl/bgwlry!save";  
	    	var jsonObj = {"domain.wlryFlxh":wlryFlxh,"domain.xm":xm,"domain.dz":dz,
                           "domain.dh":dh,"domain.cz":cz,"domain.sj":sj,"domain.wz":wz,"domain.yb":yb,
                           "domain.dy":dy,"domain.bz":bz,"domain.wlryDjxh":wlryDjxh};   			
			ajaxCommon(url,jsonObj,"YesSave");
		});
	});
	
	function YesSave(){
		showAlert("保存成功！");
		$("#mainForm_zt").val(1);
	}
	$(function(){
		$("#closeBtn").click(function(){
			var zt=$("#mainForm_zt").val();
			if(zt == '1'){
				parent.onRefresh();
			}
		})
	})
	
	function checkdata(){
		var controlNameArray = ["domain.wlryFlxh","domain.xm","domain.dz",
		                        "domain.dh","domain.cz","domain.sj","domain.wz","domain.yb",
		                        "domain.dy","domain.bz"];
		var labelNameArray = ["外联人员分类","姓名","地址",
		                      "电话","传真","手机","网址","邮编",
		                      "电邮","备注"];
		var compareValueArray = [16,16,100,
			                     50,20,20,50,10,
			                     50,200];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,false,
                            false,false,false,false,false,
                            false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="bgwlry!initMx" namespace="/bggl" method="post" id="mainForm" name="mainForm">
	<input type="hidden" id="mainForm_domain_wlryDjxh" value='<s:property value="domain.wlryDjxh"/>'/>
	<input type="hidden" id="mainForm_zt"/>
	
	<div class="pop_contc" style="height:320px; overflow:auto;">
	<fieldset>
	<legend>基本信息</legend>
	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
		<tr>
  				<td width="13%" align="right"><font class="font_red">*</font>单位：</td>
  				<td width="35%">
  					<sys:gsList myName="domain.jgbm" myId="mainForm_domain_jgbm" mcContainDmBz="Y" contaisQxz="false" myClass="select" onChange="commonInit('gs-wlryfl',this.value, '', 'domain.wlryFlxh', 'mainForm_domain_wlryFlxh','N', 'Y')"/>
  				</td>
  				<td width="15%" align="right"><font class="font_red">*</font>外联人员分类：</td>
  				<td width="35%">
  					<select name="domain.wlryFlxh" id="mainForm_domain_wlryFlxh" class="select">
  						<option value="${domain.wlryFlxh }"></option>
  					</select>
  				</td>
   			</tr>
   			<tr>
   				<td align="right"><font class="font_red">*</font>姓名：</td>
   				<td>
   					<s:textfield name="domain.xm" cssClass="pop_input bgstyle_required" ></s:textfield>
   				</td>
   				<td align="right">地址：</td>
   				<td>
   					<s:textfield name="domain.dz" cssClass="pop_input bgstyle_optional" ></s:textfield>
   				</td>
   			</tr>
   			<tr>
   				<td align="right">电话：</td>
   				<td>
   					<s:textfield name="domain.dh" cssClass="pop_input bgstyle_optional" ></s:textfield>
   				</td>
   				<td align="right">传真：</td>
   				<td>
   					<s:textfield name="domain.cz" cssClass="pop_input bgstyle_optional" ></s:textfield>
   				</td>
   			</tr>
   			<tr>
   				<td align="right">手机：</td>
   				<td>
   					<s:textfield name="domain.sj" cssClass="pop_input bgstyle_optional" ></s:textfield>
   				</td>
   				<td align="right">网址：</td>
   				<td>
   					<s:textfield name="domain.wz" cssClass="pop_input bgstyle_optional" ></s:textfield>
   				</td>
   			</tr>
   			<tr>
   				<td align="right">邮编：</td>
   				<td>
   					<s:textfield name="domain.yb" cssClass="pop_input bgstyle_optional" ></s:textfield>
   				</td>
   				<td align="right">电邮：</td>
   				<td>
   					<s:textfield name="domain.dy" cssClass="pop_input bgstyle_optional" ></s:textfield>
   				</td>
   			</tr>
   			<tr>
   				<td align="right">备注：</td>
   				<td colspan="3">
   					<s:textarea name="domain.bz" rows="3" cssClass="pop_textarea_colspan2" ></s:textarea>
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
