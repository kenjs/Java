<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>分包商维护</title>
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
		$("#mainForm_domain_fhrXzqhDm").val($("#mainForm_domain_xzqhDm").val());
		$("#mainForm_domain_fhrXzqhMc").val($("#mainForm_domain_xzqhMc").val());
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var fbsDjxh = trim($("#mainForm_domain_fbsDjxh").val()); 
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
			var fbsmc = trim($("#mainForm_domain_fbsmc").val()); 
			var fbsjc = trim($("#mainForm_domain_fbsjc").val()); 
			var xzqhDm = trim($("#mainForm_domain_fhrXzqhDm").val()); 
			var dz = trim($("#mainForm_domain_dz").val()); 
			var dh = trim($("#mainForm_domain_dh").val()); 
			var yb = trim($("#mainForm_domain_yb").val()); 
			var fzr = trim($("#mainForm_domain_fzr").val()); 
			var bz = trim($("#mainForm_domain_bz").val()); 
			var fbsQybm = trim($("#mainForm_domain_fbsQybm").val()); 

			var url = jcontextPath+"/zygl/qyfbsdjxx!save";  
	    	var jsonObj = {"domain.fbsDjxh":fbsDjxh,"domain.ssJgbm":ssJgbm,"domain.fbsmc":fbsmc,"domain.fbsjc":fbsjc,"domain.xzqhDm":xzqhDm,"domain.dz":dz,"domain.dh":dh,
                           "domain.yb":yb,"domain.fzr":fzr,"domain.bz":bz,"domain.fbsQybm":fbsQybm};   			
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
		var controlNameArray = ["domain.fbsmc","domain.fbsjc","domain.fbsQybm","domain.fhrXzqhDm","domain.dz","domain.dh",
		                        "domain.yb","domain.fzr","domain.bz"];
		var labelNameArray = ["分包商名称","分包商简称","分包商企业编码","行政区划","地址","电话",
		                      "邮编","负责人","备注"];
		var compareValueArray = [100,100,20,20,100,40,
			                     10,40,200];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,false,true,true,true,
                            false,true,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="qyfbsdjxx!initMx" namespace="/zygl" method="post" id="mainForm" name="mainForm">

	<s:hidden name="domain.fbsDjxh"></s:hidden>
	<s:hidden name="domain.ssJgbm"></s:hidden>
	<div id="maincont">
		<div class="pop_contc" style="height:320px; overflow:auto;">
		   <fieldset>
		    <legend>基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="15%" align="right"><font class="font_red">*</font>分包商名称：</td>
      				<td width="85%" colspan="3">
      					<s:textfield name="domain.fbsmc" cssClass="pop_input_colspan2 noborder bgstyle_required" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right" width="15%"><font class="font_red">*</font>分包商简称：</td>
      				<td width="35%">
      					<s:textfield name="domain.fbsjc" cssClass="pop_input noborder bgstyle_required" ></s:textfield>
      				</td>
      				<td align="right" width="18%">分包商企业编码：</td>
      				<td width="35%">
      					<s:textfield name="domain.fbsQybm" cssClass="pop_input  bgstyle_optional" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right"><font class="font_red">*</font>地址：</td>
      				<td colspan="3">
      					<s:textfield name="domain.dz" rows="3" cssClass="pop_input noborder bgstyle_required" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right"><font class="font_red">*</font>行政区划：</td>
      				<td>
      					<s:hidden name="domain.xzqhDm" />
      					<s:hidden name="domain.xzqhMc" />
	      				<s:hidden name="domain.fhrXzqhDm" />
	  					<div class="inputsel" style="width: 116px;">
	  						<s:textfield name="domain.fhrXzqhMc" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width: 91px;"></s:textfield>
	  						<a href="#" class="icon_arrow" id="xzqh" xzqh="fhrXzqh" onFocus="this.blur()"></a>
			            </div>
      				</td>
      				<td align="right"><font class="font_red">*</font>负责人：</td>
      				<td>
      					<s:textfield name="domain.fzr" cssClass="pop_input noborder bgstyle_required" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right"><font class="font_red">*</font>电话：</td>
      				<td>
      					<s:textfield name="domain.dh" cssClass="pop_input noborder bgstyle_required" ></s:textfield>
      				</td>
      				<td align="right">邮编：</td>
      				<td>
      					<s:textfield name="domain.yb" cssClass="pop_input noborder bgstyle_optional" ></s:textfield>
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
	</div>
	<%@include file="/common/message.jsp" %>
	<div id="inputSel_xzqh" class="inputselcont" style="position: absolute; top: 159px; left: 94px; display: none;" >
      <iframe src="" scrolling="no" frameborder="0" style="position:absolute; top:0; left:0; z-index:-1; width:274px; height:100px;"></iframe>
    </div>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
