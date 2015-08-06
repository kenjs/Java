<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>货运-货物自提明细</title>

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
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var hwztDjxh = trim($("#mainForm_domain_hwztDjxh").val()); 
			var jbrCzyDjxh = trim($("#mainForm_domain_jbrCzyDjxh").val()); 
			var thrq = trim($("#mainForm_domain_thrq").val()); 
			var bzsm = trim($("#mainForm_domain_bzsm").val()); 
			var thrMc = trim($("#mainForm_domain_thrMc").val()); 
			var thrLxdh = trim($("#mainForm_domain_thrLxdh").val()); 
			var thrSfzh = trim($("#mainForm_domain_thrSfzh").val()); 
			
			var url = jcontextPath+"/hwzt!save";  
	    	var jsonObj = {"domain.hwztDjxh":hwztDjxh,"domain.jbrCzyDjxh":jbrCzyDjxh,"domain.thrq":thrq,"domain.bzsm":encodeURI(bzsm),
	    					"domain.thrMc":thrMc,"domain.thrLxdh":thrLxdh,"domain.thrSfzh":thrSfzh
                           };
			ajaxCommon(url,jsonObj,"saveOK");
		});
	});
	
	function saveOK(data){
		var tager=data.domain.tager;
		if(tager=="2"){
			showSuccess("登记成功！","saveAfter");
		}
		else{
			showError("该货物不能登记！请先到收入登记进行登记！");
			return;
		}
	}
	function saveAfter(){
		window.close();
	}
	function checkdata(){

		var controlNameArray = ["domain.jbrCzyDjxh", "domain.thrMc"];
		var labelNameArray = ["经办人", "提货人"];
		var compareValueArray = [16, 20];
		var nodeTypeArray = [NodeType.STRING, NodeType.STRING
							];
		var notNullArray = [true, true];

		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	
</script>
<base target="_self" />
</head>

<body >
<%try{ %>
<s:form action="hwzt!initMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
        <div  style="display:none"><a id="reload" href="">reload</a></div>
	<s:hidden name="domain.hwztDjxh"></s:hidden>
	<s:hidden name="domain.ssJgbm"></s:hidden>
<!--	应收应付登记代码-->
		<div id="maincont">
		<div class="pop_contc" style="height:220px; overflow:auto;">
		<fieldset>
			<legend>货物自提</legend>
    	 	 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
				    <td width="15%" align="right"><font class="font_red">*</font>经办人:</td>
      				<td width="35%">
      					<sys:GsryList myName="domain.jbrCzyDjxh" myId="mainForm_domain_jbrCzyDjxh" myClass="select" ssJgbm="domain.ssJgbm"></sys:GsryList>
      				</td>
				    <td width="15%" align="right"><font class="font_red">*</font>提货人:</td>
      				<td width="35%">
      					<s:textfield name="domain.thrMc" cssClass="inputext pop_input noborder bgstyle_required"></s:textfield>
      				</td>
      			</tr>
				<tr>
      				<td width="15%" align="right">提货日期：</td>
      				<td width="35%">
      					<sys:dateCurrentDayTag myName="domain.thrq" myId="mainForm_domain_thrq" myClass="ymdate" />
      				</td>
      				<td width="15%" align="right">联系电话：</td>
      				<td width="35%">
      					<s:textfield name="domain.thrLxdh" cssClass="pop_input bgstyle_optional"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td width="15%" align="right">身份证号：</td>
      				<td width="35%">
      					<s:textfield name="domain.thrSfzh" cssClass="pop_input bgstyle_optional"></s:textfield>
      				</td>
      				<td colspan="2"></td>
      			</tr>
      			<tr>
      				<td align="right">备注说明：</td>
      				<td colspan="3">
      					<s:textarea name="domain.bzsm" cssClass="pop_textarea_colspan2"></s:textarea>
      				</td>
      			</tr>
			</table>
    	 </fieldset>
		<div class="pop_btn" style="margin-top: 10px;">
			   <button type="button" class="pop_btnbg" id="saveBtn">登 记</button>
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
