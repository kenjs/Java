<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>装货地址维护</title>

<style type="text/css">
html,body {background:none;}
</style>
<!-- 实现IE6下png图片透明 -->
<!--[if IE 6]>
<script src="<sys:context/>/resource/pageframe/js/DD_belatedPNG_0.0.8a-min.js" type="text/javascript"></script>
<script type="text/javascript">
	DD_belatedPNG.fix('h2, a');
</script>
<![endif]-->
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
			var ssJgbm = $("#mainForm_domain_ssJgbm").val(); 
			var zhdzDjxh = trim($("#mainForm_domain_zhdzDjxh").val()); 
			var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
			var xzqhDm = trim($("#mainForm_domain_fhrXzqhDm").val()); 
			var xxdz = trim($("#mainForm_domain_xxdz").val()); 
			var yb = trim($("#mainForm_domain_yb").val()); 
			var lxr = trim($("#mainForm_domain_lxr").val()); 
			var lxrYddh = trim($("#mainForm_domain_lxrYddh").val()); 
			var lxrGddh = trim($("#mainForm_domain_lxrGddh").val()); 
			var qtlxdh = trim($("#mainForm_domain_qtlxdh").val()); 
			var bz = trim($("#mainForm_domain_bz").val()); 

			var url = jcontextPath+"/zygl/qykhzhdz!save";  
	    	var jsonObj = {"domain.zhdzDjxh":zhdzDjxh,"domain.khDjxh":khDjxh,"domain.xzqhDm":xzqhDm,
                           "domain.xxdz":xxdz,"domain.yb":yb,"domain.lxr":lxr,
                           "domain.lxrYddh":lxrYddh,"domain.lxrGddh":lxrGddh,"domain.qtlxdh":qtlxdh,"domain.bz":bz,"domain.ssJgbm":ssJgbm};   			
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
		var controlNameArray = ["domain.xxdz","domain.fhrXzqhDm","domain.yb","domain.lxr",
		                        "domain.lxrYddh","domain.lxrGddh","domain.qtlxdh","domain.bz"];
		var labelNameArray = ["详细地址","行政区划","邮编","联系人",
		                      "移动电话","固定电话","其他电话","备注"];
		var compareValueArray = [100,20,10,100,
		                         500,80,100,200];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,
		                     NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,false,false,
		                    false,false,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="qykhzhdz!initMx" namespace="/zygl" method="post" id="mainForm" name="mainForm">
	<input type="hidden" id="mainForm_domain_zhdzDjxh" value='<s:property value="domain.zhdzDjxh"/>'/>
	<input type="hidden" id="mainForm_domain_khDjxh" value='<s:property value="domain.khDjxh"/>'/>
	<input type="hidden" id="mainForm_domain_ssJgbm" value='<s:property value="domain.ssJgbm"/>'/>
	<div id="maincont">
		<div class="pop_contc" style="height:320px; overflow:auto;">
			<fieldset>
		    <legend>基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				
				<tr>
      				<td width="15%" align="right"><font class="font_red">*</font>详细地址：</td>
      				<td width="85%" colspan="3">
      					<s:textfield name="domain.xxdz" cssClass="pop_input_colspan2 bgstyle_required" ></s:textfield>
      				</td>
      			</tr>
      			
      			<tr>
      				<td width="15%" align="right"><font class="font_red">*</font>行政区划：</td>
      				<td width="35%">
      					<s:hidden name="domain.xzqhDm" />
      					<s:hidden name="domain.xzqhMc" />
	      				<s:hidden name="domain.fhrXzqhDm" />
	  					<div class="inputsel" style="width: 116px;">
	  						<s:textfield name="domain.fhrXzqhMc" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width: 91px;"></s:textfield>
	  						<a href="#" class="icon_arrow" id="xzqh" xzqh="fhrXzqh" onFocus="this.blur()"></a>
			            </div>
      				</td>
      				<td width="15%" align="right">邮编：</td>
      				<td width="35%">
      					<s:textfield name="domain.yb" cssClass="pop_input bgstyle_optional" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">联系人：</td>
      				<td>
      					<s:textfield name="domain.lxr" cssClass="pop_input bgstyle_optional" ></s:textfield>
      				</td>
      				<td align="right">移动电话：</td>
      				<td>
      					<s:textfield name="domain.lxrYddh" cssClass="pop_input bgstyle_optional" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">固定电话：</td>
      				<td>
      					<s:textfield name="domain.lxrGddh" cssClass="pop_input bgstyle_optional" ></s:textfield>
      				</td>
      			
      				<td align="right">其他电话：</td>
      				<td>
      					<s:textfield name="domain.qtlxdh" cssClass="pop_input bgstyle_optional" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">备注：</td>
      				<td colspan="3">
      					<s:textarea name="domain.bz" rows="3" cssClass="pop_textarea_colspan2 bgstyle_optional" ></s:textarea>
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
