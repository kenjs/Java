<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>分包商结算价格维护</title>
<%@ include file="/common/meta.jsp"%>
<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(document).ready(function(){
			var fbsDjxh = $("#mainForm_domain_fbsDjxh").val();
			commonInit('fbs-xl', fbsDjxh, '', 'domain.lxDjxh', 'mainForm_domain_lxDjxh', 'N', 'Y');
	});

	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		})
	
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var lxDjxh = trim($("#mainForm_domain_lxDjxh").val());
			var yxqQ = trim($("#mainForm_domain_yxqQ").val()); 
			var yxqZ = trim($("#mainForm_domain_yxqZ").val()); 
			var url = jcontextPath+"/zygl/qyfbsjsjg!checkSave"; 
			var jsonObj = {"domain.lxDjxh":lxDjxh,"domain.yxqQ":yxqQ,"domain.yxqZ":yxqZ};
			ajaxCommon(url,jsonObj,"onCheck");
		});
	});
	
	function onCheck(data){
		
		var count = data.domain.count;
		if(count>0){
			showConfirm("当前有效期与系统中已存在的结算价格有效期重叠，是否继续？","onSave");
		}else{
			onSave();
		}
	}
	
	function onSave(){
		
		//var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var fbsDjxh = $("#mainForm_domain_fbsDjxh").val();
		var lxDjxh = $("#mainForm_domain_lxDjxh").val(); 
		var jsJldwDm = $("#mainForm_domain_jsJldwDm").val(); 
		//var dj = $("#mainForm_domain_dj").val(); 
		var yxqQ = $("#mainForm_domain_yxqQ").val(); 
		var yxqZ = $("#mainForm_domain_yxqZ").val(); 
		var bz = $("#mainForm_domain_bz").val(); 
		var jsjgDjxh = $("#mainForm_domain_jsjgDjxh").val(); 

		var url = jcontextPath+"/zygl/qyfbsjsjg!save";  
    	var jsonObj = {"domain.lxDjxh":lxDjxh,"domain.fbsDjxh":fbsDjxh,
                          "domain.jsJldwDm":jsJldwDm,"domain.yxqQ":yxqQ,"domain.yxqZ":yxqZ,"domain.bz":bz,"domain.jsjgDjxh":jsjgDjxh};   			
		ajaxCommon(url,jsonObj);
	}
	
	function checkdata(){
		var controlNameArray = ["domain.lxDjxh",
		                        "domain.jsJldwDm","domain.dj","domain.yxqQ","domain.yxqZ","domain.bz"];
		var labelNameArray = ["线路",
		                      "结算计量单位","单价","有效期起","有效期止","备注"];
		var compareValueArray = [16,
			                     6,10.6,10,10,200];
		var nodeTypeArray = [NodeType.STRING,
			                 NodeType.STRING,NodeType.DECIMAL,NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,
                            true,true,true,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="qyfbsjsjg!initMx" namespace="/zygl" method="post" id="mainForm" name="mainForm">
<s:hidden name="domain.fbsDjxh"></s:hidden>
	<input type="hidden" id="mainForm_domain_jsjgDjxh" value='<s:property value="domain.jsjgDjxh"/>'/>
		<div class="pop_contc" style="height:280px; overflow:auto;">
		   <fieldset>
		    <legend>基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="10%" align="right"><font class="font_red">*</font>线路：</td>
      				<td width="35%">
      					<select name="domain.lxDjxh" id="mainForm_domain_lxDjxh" class="select"/>
      				</td>
      				<td width="18%" align="right"><font class="font_red">*</font>结算计量单位：</td>
      				<td  width="35%">
      					<sys:QyAllJldw myName="domain.jsJldwDm" myId="mainForm_domain_jsJldwDm" myClass="select_long" mcContainDmBz="Y"></sys:QyAllJldw>
      				</td>
      			</tr>
      			<tr>
      				<td align="right" width="20%"><font class="font_red">*</font>有效期起止：</td>
      				<td>
      					<s:textfield name="domain.yxqQ" cssClass="ymdate"  readonly="true"></s:textfield><strong>~</strong>
      					<s:textfield name="domain.yxqZ" cssClass="ymdate"  readonly="true"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">备注：</td>
      				<td colspan="3">
      					<s:textarea name="domain.bz" rows="3" cssClass="pop_textarea_colspan2  bgstyle_optional" ></s:textarea>
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
