<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>单点登录维护</title>

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
			var jgbm = trim($("#mainForm_domain_jgbm").val()); 
			var mc = trim($("#mainForm_domain_mc").val()); 
			var qddz = trim($("#mainForm_domain_url").val()); 
			var dlfsDm = trim($("#mainForm_domain_dlfsDm").val()); 
			var xjgxbz = ""; 
			var e = document.getElementById("radio");
			if(e.checked){
				xjgxbz="Y";
			} else {
				xjgxbz="N";
			}

			var dddlDjxh = trim($("#mainForm_domain_dddlDjxh").val()); 
			var url = jcontextPath+"/bggl/bgdddl!save";  
	    	var jsonObj = {"domain.jgbm":jgbm,"domain.mc":mc,"domain.url":qddz,
                           "domain.dlfsDm":dlfsDm,"domain.xjgxbz":xjgxbz,"domain.dddlDjxh":dddlDjxh};   			
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
		var controlNameArray = ["domain.jgbm","domain.mc","domain.url",
		                        "domain.dlfsDm","domain.xjgxbz"];
		var labelNameArray = ["机构编码","名称","启动地址",
		                      "登录方式代码","下级共享标志(Y/N)"];
		var compareValueArray = [16,100,100,
			                     10,10];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,true,
                            true,true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="bgdddl!initMx" namespace="/bggl" method="post" id="mainForm" name="mainForm">
	<input type="hidden" id="mainForm_domain_dddlDjxh" value='<s:property value="domain.dddlDjxh"/>'/>
	<input type="hidden" id="mainForm_zt"/>
	
	<div class="pop_contc" style="height:280px; overflow:auto;">
		<fieldset>
		<legend>基本信息</legend>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
			<tr>
   				<td width="15%" align="right"><font class="font_red">*</font>单位：</td>
   				<td width="85%">
   					<sys:gsList myName="domain.jgbm" myId="mainForm_domain_jgbm" mcContainDmBz="Y" myClass="select"/>
   				</td>
   			</tr>
   			<tr>
   				<td align="right"><font class="font_red">*</font>名称：</td>
   				<td>
   					<s:textfield name="domain.mc" cssClass="pop_input bgstyle_required"></s:textfield>
   				</td>
   			</tr>
			<tr>
   				<td align="right"><font class="font_red">*</font>启动地址：</td>
   				<td>
   					<s:textfield name="domain.url" cssClass="pop_input bgstyle_required"></s:textfield>
   				</td>
   			</tr>
			<tr>
   				<td align="right"><font class="font_red">*</font>登陆方式：</td>
   				<td>
   					<sys:Dlfs myName="domain.dlfsDm" myId="mainForm_domain_dlfsDm" myClass="select" />
   				</td>
   			</tr>
   			<tr>
   				<td align="right"><font class="font_red">*</font>下级共享：</td>
   				<td>
   					<s:if test='domain.xjgxbz==\"N\"'>
               	<s:checkbox name="domain.xjgxbz" id="radio" style="width:20px;"></s:checkbox>
		    </s:if>
		    <s:else>
		     	<s:checkbox name="domain.xjgxbz" checked="true" id="radio" style="width:20px;"></s:checkbox>
		    </s:else>
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
