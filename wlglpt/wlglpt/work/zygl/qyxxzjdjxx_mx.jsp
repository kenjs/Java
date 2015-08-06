<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>信息中介维护</title>
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
		$("#deleteBtn").click(function(){
			var tab=$("#tab")[0];
			var ll=tab.rows.length;
			for(var i=1;i<ll;i++){
				alert(tab.rows.length+"--"+i+"--"+ll);
				tab.deleteRow(1);
			}
		})
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
			var xxzjmc = trim($("#mainForm_domain_xxzjmc").val()); 
			var xxzjjc = trim($("#mainForm_domain_xxzjjc").val()); 
			var xzqhDm = trim($("#mainForm_domain_fhrXzqhDm").val()); 
			var dz = trim($("#mainForm_domain_dz").val()); 
			var dh = trim($("#mainForm_domain_dh").val()); 
			var yb = trim($("#mainForm_domain_yb").val()); 
			var fzr = trim($("#mainForm_domain_fzr").val()); 
			var xxzjQybm = trim($("#mainForm_domain_xxzjQybm").val()); 
			var bz = trim($("#mainForm_domain_bz").val()); 
			var xxzjDjxh = trim($("#mainForm_domain_xxzjDjxh").val()); 

			var url = jcontextPath+"/qyxxzjwh!save";  
	    	var jsonObj = {"domain.ssJgbm":ssJgbm,"domain.xxzjmc":xxzjmc,"domain.xxzjjc":xxzjjc,
                           "domain.xzqhDm":xzqhDm,"domain.dz":dz,"domain.dh":dh,
                           "domain.yb":yb,"domain.fzr":fzr,"domain.xxzjQybm":xxzjQybm,"domain.bz":bz,
                           "domain.xxzjDjxh":xxzjDjxh};   			
			ajaxCommon(url,jsonObj,"YesSave");
		});
	});
	
	function YesSave(){
		showAlert("保存成功！","doClose");
	}
	function doClose(){
		window.close();
	}
	
	function checkdata(){
		var controlNameArray = ["domain.xxzjmc","domain.xxzjjc","domain.fhrXzqhDm","domain.dz",
								"domain.dh","domain.yb","domain.fzr","domain.xxzjQybm","domain.bz"];
		var labelNameArray = ["信息中介名称","信息中介简称","行政区划代码","地址","电话",
		                      "邮编","负责人","信息中介企业编码","备注"];
		var compareValueArray = [100,100,80,100,20,
								 10,100,40,200];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,
							 NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,
                            true,false,false,false,false,
                            false,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="qyxxzjdjxx!initMx" namespace="" method="post" id="mainForm" name="mainForm">
	<input type="hidden" id="mainForm_domain_xxzjDjxh" value='<s:property value="domain.xxzjDjxh"/>'/>
	<input type="hidden" id="mainForm_domain_ssJgbm" value='<s:property value="domain.ssJgbm"/>'/>
	<div id="maincont">
	<div class="pop_contc" style="height:300px; width:97%;">
	   <fieldset>
		  <legend>基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td align="right" width="13%"><font class="font_red">*</font>名称：</td>
      				<td colspan="3" width="85%">
      					<s:textfield name="domain.xxzjmc" cssClass="pop_input_colspan2 bgstyle_required" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right" width="13%"><font class="font_red">*</font>简称：</td>
      				<td width="35%">
      					<s:textfield name="domain.xxzjjc"  cssClass="pop_input bgstyle_required" ></s:textfield>
      				</td>
      				<td align="right" width="15%">公司编码：</td>
      				<td width="35%">
      					<s:textfield name="domain.xxzjQybm"  cssClass="pop_input bgstyle_optional" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">地址：</td>
      				<td colspan="3">
      					<s:textfield name="domain.dz" cssClass="pop_input_colspan2 bgstyle_optional" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">行政区划：</td>
      				<td>
      					<s:hidden name="domain.xzqhDm" />
      					<s:hidden name="domain.xzqhMc" />
	      				<s:hidden name="domain.fhrXzqhDm" />
	  					<div class="inputsel" style="width: 116px;">
	  						<s:textfield name="domain.fhrXzqhMc" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width: 91px;"></s:textfield>
	  						<a href="#" class="icon_arrow" id="xzqh" xzqh="fhrXzqh" onFocus="this.blur()"></a>
			            </div>
      				</td>
      				<td align="right">负责人：</td>
      				<td>
      					<s:textfield name="domain.fzr" cssClass="pop_input bgstyle_optional" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">电话：</td>
      				<td>
      					<s:textfield name="domain.dh" cssClass="pop_input bgstyle_optional" ></s:textfield>
      				</td>
      				<td align="right">邮编：</td>
      				<td>
      					<s:textfield name="domain.yb" cssClass="pop_input bgstyle_optional" ></s:textfield>
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
