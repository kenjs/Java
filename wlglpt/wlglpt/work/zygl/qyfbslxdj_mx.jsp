<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>分包商路线维护</title>
<%@ include file="/common/meta.jsp"%>
<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		})
	    //行政区划input下拉
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		initXzqhData(200);
		initXzqhInputSel();
		$("#mainForm_domain_fhrXzqhDm").val($("#mainForm_domain_sfdXzqhDm").val());
		$("#mainForm_domain_fhrXzqhMc").val($("#mainForm_domain_sfdXzqhMc").val());
		$("#mainForm_domain_shrXzqhDm").val($("#mainForm_domain_mddXzqhDm").val());
		$("#mainForm_domain_shrXzqhMc").val($("#mainForm_domain_mddXzqhMc").val());
		
		$("#saveBtn").click(function(){
		
			if(!checkdata()){
				return;
			}
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
			var fbsDjxh = trim($("#mainForm_domain_fbsDjxh").val()); 
			var lxmc = trim($("#mainForm_domain_lxmc").val()); 
			var lxjc = trim($("#mainForm_domain_lxjc").val()); 
			var sfdXzqhDm = trim($("#mainForm_domain_fhrXzqhDm").val()); 
			var mddXzqhDm = trim($("#mainForm_domain_shrXzqhDm").val()); 
			var bz = trim($("#mainForm_domain_bz").val()); 
			var lxDjxh = trim($("#mainForm_domain_lxDjxh").val()); 
			var url = jcontextPath+"/zygl/qyfbslxdj!save";  
	    	var jsonObj = {"domain.lxmc":lxmc,"domain.lxjc":lxjc,"domain.sfdXzqhDm":sfdXzqhDm,"domain.mddXzqhDm":mddXzqhDm,
                           "domain.bz":bz,"domain.ssJgbm":ssJgbm,"domain.fbsDjxh":fbsDjxh,"domain.lxDjxh":lxDjxh};   			
			ajaxCommon(url,jsonObj,"saveSuc");
		});
	});
	function saveSuc(){
		showAlert("保存成功！","doClose");
	}
	function doClose(){
		window.close();
	}
	
	function checkdata(){
		var controlNameArray = ["domain.lxmc","domain.lxjc","domain.fhrXzqhDm","domain.shrXzqhDm","domain.bz"];
		var labelNameArray = ["线路名称","线路简称","始发地","目的地","备注"];
		var compareValueArray = [100,100,20,20,200];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,true,true,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="/zygl/qyfbslxdj!initMx" namespace="" method="post" id="mainForm" name="mainForm">
	<input type="hidden" id="mainForm_domain_lxDjxh" value='<s:property value="domain.lxDjxh"/>'/>
	<s:hidden name="domain.ssJgbm"/>
	<s:hidden name="domain.fbsDjxh"/>
	<div id="maincont">
		<div class="pop_contc" style="height:240px; overflow:auto;">
		   <fieldset>
		    <legend>基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td align="right" width="15%"><font class="font_red">*</font>线路名称：</td>
      				<td width="85%" colspan="3">
      					<s:textfield name="domain.lxmc" cssClass="pop_input_colspan2  bgstyle_required" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right"><font class="font_red">*</font>线路简称：</td>
      				<td colspan="3">
      					<s:textfield name="domain.lxjc" cssClass="pop_input_colspan2  bgstyle_required" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right" width="10%"><font class="font_red">*</font>始发地：</td>
      				<td width="35%">
      					<s:hidden name="domain.sfdXzqhDm" />
      					<s:hidden name="domain.sfdXzqhMc" />
	      				<s:hidden name="domain.fhrXzqhDm" />
	  					<div class="inputsel" style="width: 116px;">
	  						<s:textfield name="domain.fhrXzqhMc" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width: 91px;"></s:textfield>
	  						<a href="#" class="icon_arrow" id="xzqh" xzqh="fhrXzqh" onFocus="this.blur()"></a>
			            </div>
      				</td>
      				<td align="right" width="10%"><font class="font_red">*</font>目的地：</td>
      				<td width="35%">
      					<s:hidden name="domain.mddXzqhDm" />
      					<s:hidden name="domain.mddXzqhMc" />
	      				<s:hidden name="domain.shrXzqhDm"></s:hidden>
	  					<div class="inputsel" style="width: 116px;">
	  						<s:textfield name="domain.shrXzqhMc" cssClass="inputext pop_input noborder bgstyle_optional" cssStyle="width: 91px;"></s:textfield>
	  						<a href="#" class="icon_arrow" id="xzqh" xzqh="shrXzqh" onFocus="this.blur()"></a>
			            </div>
		            </td>
      			</tr>
      			
      			<tr>
      				<td align="right">备注：</td>
      				<td colspan="3">
      					<s:textarea name="domain.bz" cssClass="pop_textarea_colspan2  bgstyle_optional" rows="3"></s:textarea>
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
