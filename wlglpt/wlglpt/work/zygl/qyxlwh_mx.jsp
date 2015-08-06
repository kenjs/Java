<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>企业-线路维护</title>

<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>

<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(function(){
		//行政区划input下拉
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		initXzqhData(200);
		initXzqhInputSel();
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
			var sfdXzqhDm = trim($("#mainForm_domain_sfdXzqhDm").val()); 
			var mddXzqhDm = trim($("#mainForm_domain_mddXzqhDm").val()); 
			var fhr = trim($("#mainForm_domain_fhrXzqhDm").val()); 
			var shr = trim($("#mainForm_domain_shrXzqhDm").val()); 
			var lcs = trim($("#mainForm_domain_lcs").val()); 
			var ddts = trim($("#mainForm_domain_ddts").val());
			if(lcs/1<0){
				showAlert("里程数不能小于零！");
				return;
			}
			if(ddts/1<0){
				showAlert("到达天数不能小于零！");
				return;
			} 
			var url = jcontextPath+"/qyxlwh!save";  
	    	var jsonObj = {"domain.ssJgbm":ssJgbm,"domain.sfdXzqhDm":sfdXzqhDm,"domain.mddXzqhDm":mddXzqhDm,"domain.lcs":lcs,
                           "domain.ddts":ddts,"domain.fhrXzqhDm":fhr,"domain.shrXzqhDm":shr};   			
			ajaxCommon(url,jsonObj,"saveOk");
		});
		
		 $("#closeBtn").click(function(){
				window.close();
			})
		
		
		//initRadio();	
	});
	
	function saveOk(){
		showSuccess("保存成功！","saveAfter");
	}
	
	function saveAfter(){
		window.close();
	}
	
	function checkdata(){
		var controlNameArray = ["domain.fhrXzqhDm","domain.shrXzqhDm","domain.lcs",
		                        "domain.ddts"];
		var labelNameArray = ["始发地","目的地","里程数",
		                      "达到天数"];
		var compareValueArray = [20,20,16.2,
			                     6.2];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.DECIMAL,
			                 NodeType.DECIMAL];
		var notNullArray = [true,true,true,
                            true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="qyxlwh!initMx" namespace="" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.ssJgbm"></s:hidden>
	<s:hidden name="domain.sfdXzqhDm"></s:hidden>
	<s:hidden name="domain.mddXzqhDm"></s:hidden>
	<s:hidden name="jsonData"></s:hidden>
	<div id="maincont">
		<div class="pop_contc" style="height:270px; width: 97%;">
			 <fieldset>
    	 	 <legend>上级单位</legend>
    	 	 	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
    	 	 	  	<tr>
    	 	 	  		<td  width="15%" align="right">上级单位名称：</td>
    	 	 	  		<td  width="60%" align="left"><s:textfield name="domain.sjMc" rows="3" cssClass="pop_input bgstyle_required" readonly="true"  ></s:textfield></td>
    	 	 	  	</tr>
    	 	 	  </table>
    		 </fieldset>
    		  <fieldset>
		    <legend>分公司基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      			
      			<td align="right" width="13%"><font class="font_red">*</font>始发地：</td>
  				  <td align="left" width="30%">
  					<s:hidden name="domain.fhrXzqhDm" />
  					<div class="inputsel" style="width: 116px;">
  						<s:textfield name="domain.fhrXzqhMc" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width: 91px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="xzqh" xzqh="fhrXzqh" onFocus="this.blur()"></a>
		            </div>
		            
  				  </td>
  				  <td align="center" width="15%"><font class="font_red">*</font>里程数：</td>
      				<td width="30%">
      					<s:textfield name="domain.lcs" cssClass="pop_input bgstyle_required" ></s:textfield>
      				</td>
  				
  			
  					
      			</tr>
      			
      			<tr>
      				<td align="right" width="13%" ><font class="font_red">*</font>目的地：</td>
  				<td align="left" width="30%">
  					<s:hidden name="domain.shrXzqhDm"></s:hidden>
  					<div class="inputsel" style="width: 116px;">
  						<s:textfield name="domain.shrXzqhMc" cssClass="inputext pop_input noborder bgstyle_optional" cssStyle="width: 91px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="xzqh" xzqh="shrXzqh" onFocus="this.blur()"></a>
		            </div>
  				</td>	
      				<td align="center" width="15%"><font class="font_red">*</font>达到天数：</td>
      				<td>
      					<s:textfield name="domain.ddts" cssClass="pop_input bgstyle_required" ></s:textfield>
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
