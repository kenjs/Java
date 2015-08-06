<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>办公-名片夹新增修改</title>
<%@ include file="/common/meta.jsp"%>
<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		});
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var xm = trim($("#mainForm_domain_xm").val()); 
			var zw = trim($("#mainForm_domain_zw").val()); 
			var gs = trim($("#mainForm_domain_gs").val()); 
			var dz = trim($("#mainForm_domain_dz").val()); 
			var dh = trim($("#mainForm_domain_dh").val()); 
			var cz = trim($("#mainForm_domain_cz").val()); 
			var sj = trim($("#mainForm_domain_sj").val()); 
			var wz = trim($("#mainForm_domain_wz").val()); 
			var yb = trim($("#mainForm_domain_yb").val()); 
			var dy = trim($("#mainForm_domain_dy").val()); 
			var bgDjxh = trim($("#mainForm_domain_bgDjxh").val()); 
			var url = jcontextPath+"/bggl/bgmpj!save";  
	    	var jsonObj = {"domain.xm":xm,"domain.zw":zw,
                           "domain.gs":gs,"domain.dz":dz,"domain.dh":dh,"domain.cz":cz,"domain.sj":sj,
                           "domain.wz":wz,"domain.yb":yb,"domain.dy":dy,"domain.bgDjxh":bgDjxh};   			
			ajaxCommon(url,jsonObj,"YesSave");
		});
	});
	
	function YesSave(){
		showAlert("保存成功！");
	}
	
	function checkdata(){
		var controlNameArray = ["domain.xm","domain.gs","domain.zw","domain.dz","domain.dh",
								"domain.sj","domain.cz","domain.wz","domain.yb","domain.dy"];
		var labelNameArray = ["姓名","公司","职务","地址","电话",
							  "手机","传真","网址","邮编","电邮"];
		var compareValueArray = [40,100,100,100,50,
								 20,20,50,10,50];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,
							 NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,false,
                            false,false,false,false,false,
                            false,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="bgmpj!initMx" namespace="" method="post" id="mainForm" name="mainForm">
	<input type="hidden" id="mainForm_domain_bgDjxh" value='<s:property value="domain.bgDjxh"/>'/>
	
	<div class="pop_contc" style="height:320px; overflow:auto;">
		<fieldset>
			<legend>基本信息</legend>
			  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				    <tr>
				   		<td width="10%" align="right"><font class="font_red">*</font>姓名 </td>
				      	<td width="30%">
				      	<s:textfield name="domain.xm"  cssClass="pop_input bgstyle_required"></s:textfield>
				        </td>
				        <td width="10%" align="right">公司 </td>
				      	<td width="30%">
				      	<s:textfield name="domain.gs" cssClass="pop_input bgstyle_optional" ></s:textfield>
				        </td>
				    </tr>
				    
				    <tr>
				      	<td width="10%" align="right">职务</td>
				      	<td width="30%">
				      	<s:textfield name="domain.zw"  cssClass="pop_input bgstyle_optional" ></s:textfield>
				        </td>
				        <td width="10%" align="right">地址</td>
				      	<td width="30%">
				      	<s:textfield name="domain.dz"  cssClass="pop_input bgstyle_optional" ></s:textfield>
				        </td>
				    </tr>
				    
				    <tr>
				      	<td width="10%" align="right">电话</td>
				      	<td width="30%">
				      	<s:textfield name="domain.dh"  cssClass="pop_input bgstyle_optional" ></s:textfield>
				      	</td>
				      	<td width="10%" align="right">手机</td>
				      	<td width="30%">
				      	<s:textfield name="domain.sj" cssClass="pop_input bgstyle_optional" ></s:textfield>
				      	</td>
				    </tr>
				    
				    <tr>
				     	<td width="10%" align="right"><font class="font_red"></font>传真</td>
				     	 <td width="30%">
				      	 <s:textfield name="domain.cz" cssClass="pop_input bgstyle_optional" ></s:textfield>
				     	 </td>
				     	 <td width="10%" align="right"><font class="font_red"></font>网址</td>
				     	 <td width="30%">
				      	 <s:textfield name="domain.wz" cssClass="pop_input bgstyle_optional" ></s:textfield>
				     	 </td>
				    </tr>
				    
				    <tr>
				      	<td width="10%" align="right"><font class="font_red"></font>邮编</td>
				      	<td width="30%">
				      	<s:textfield name="domain.yb" cssClass="pop_input bgstyle_optional" ></s:textfield>
				         </td>
				         <td width="10%" align="right"><font class="font_red"></font>E-mail</td>
				      	<td width="30%">
				      	<s:textfield name="domain.dy" cssClass="pop_input bgstyle_optional" ></s:textfield>
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
