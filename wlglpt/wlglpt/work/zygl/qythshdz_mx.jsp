<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>提货收货地址维护</title>

<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(function(){
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		initXzqhData(200);
		initXzqhInputSel();
		
		$("#closeBtn").click(function(){
			window.close();
		});
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
			var dz = trim($("#mainForm_domain_dz").val()); 
			var dh = trim($("#mainForm_domain_dh").val()); 
			var xzqhDm = trim($("#mainForm_domain_fhrXzqhDm").val()); 
			var fzr = trim($("#mainForm_domain_fzr").val()); 
			var whXh = trim($("#mainForm_domain_whXh").val()); 

			var url = jcontextPath+"/zygl/qythshdz!save";  
	    	var jsonObj = {"domain.ssJgbm":ssJgbm,"domain.dz":dz,"domain.dh":dh,
                           "domain.xzqhDm":xzqhDm,
                           "domain.fzr":fzr,"domain.whXh":whXh};   			
			ajaxCommon(url,jsonObj,"saveDone");
		});
	});
	
	function saveDone(){
		showAlert("保存成功！","closeWin");
	}
	
	function closeWin(){
		window.close();
	}
	
	function checkdata(){
		var controlNameArray = ["domain.fhrXzqhMc","domain.dz","domain.dh",		                       
		                        "domain.fzr"];
		var labelNameArray = ["行政区划","地址","联系电话",		                      
		                      "负责人"];
		var compareValueArray = [100,40,
			                     20,
			                     40];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,
			                 NodeType.STRING,
			                 NodeType.STRING];
		var notNullArray = [true,true,
                            false,
                            false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="qythshdz!initMx" namespace="/zygl" method="post" id="mainForm" name="mainForm">
	<input type="hidden" id="mainForm_domain_whXh" value='<s:property value="domain.whXh"/>'/>
	<s:hidden name="domain.ssJgbm"/>
	<s:hidden name="jsonData" />
		<div id="maincont">
		<div class="pop_contc" style="height:320px; width: 98%;">
		<fieldset>
			<legend>所属单位</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="15%" align="right">单位名称：</td>
      				<td>
  						<s:textfield name="domain.ssJgmc" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
      			</table>
		</fieldset>
		<fieldset>
			<legend>基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td align="right"><font class="font_red">*</font>行政区划：</td>
      				<td colspan="3">
      					<s:hidden name="domain.fhrXzqhDm" />
  						<div class="inputsel" style="width: 295px;">
  						<s:textfield name="domain.fhrXzqhMc" cssClass="pop_input noborder inputext bgstyle_required" cssStyle="width: 265px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="xzqh" xzqh="fhrXzqh" onFocus="this.blur()"></a>
		            </div>
      				</td>
      			</tr>
      			<tr>
      				<td align="right"><font class="font_red">*</font>地址：</td>
      				<td colspan="3">
      					<s:textarea name="domain.dz" rows="3" cssClass="pop_textarea_colspan2 noborder bgstyle_required" ></s:textarea>
      				</td>
      			</tr>
      			<tr>
      				<td width="15%" align="right">联系电话：</td>
      				<td width="35%">
      					<s:textfield name="domain.dh" cssClass="pop_input bgstyle_optional" ></s:textfield>
      				</td>
      				<td width="15%" align="right">负责人：</td>
      				<td width="35%">
      					<s:textfield name="domain.fzr" cssClass="pop_input bgstyle_optional" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">创建人：</td>
      				<td>
      					<s:textfield name="domain.cjrMc" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">创建日期：</td>
      				<td>
      					<s:textfield name="domain.cjrq" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
				<tr>
      				<td align="right">修改人：</td>
      				<td>
      					<s:textfield name="domain.xgrMc" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">修改日期：</td>
      				<td>
      					<s:textfield name="domain.xgrq" cssClass="pop_input bgstyle_readonly" ></s:textfield>
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
		</div>
	<%@include file="/common/message.jsp" %>
	 <div id="inputSel_xzqh" class="inputselcont" style="position: absolute; top: 159px; left: 94px; display: none;" >
      <iframe src="" scrolling="no" frameborder="0" style="position:absolute; top:0; left:0; z-index:-1; width:274px; height:100px;"></iframe>
    </div>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
