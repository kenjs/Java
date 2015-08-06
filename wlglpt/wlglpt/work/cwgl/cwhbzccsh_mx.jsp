<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>财务-货币资产初始化维护</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		  $("#closeBtn").click(function(){
				window.close();
			})
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
			var zcflDm = trim($("#mainForm_domain_zcflDm").val()); 
			var yhmc = trim($("#mainForm_domain_yhmc").val()); 
			var hm = trim($("#mainForm_domain_hm").val()); 
			var zh = trim($("#mainForm_domain_zh").val()); 
			var csje = trim($("#mainForm_domain_csje").val()); 
			if(csje/1<0){
				showAlert("金额必须大于零！");
				return;
			}
			var tage=$("#mainForm_domain_tage").val();
			 var val= $("select").find("option:selected").text();
			 var ary=val.split(" ");
			if(tage=='1'){
				if(yhmc==''){
					showError("银行名称不能为空！");
					return ;
				}
				if(hm==''){
					showError("户名不能为空！");
					return ;
				}
				if(zh==''){
					showError("账号不能为空！");
					return ;
				}
			}
			if(csje==''){
				showError("初始金额不能为空！");
				return ;
			}
			var cshDjxh = trim($("#mainForm_domain_cshDjxh").val()); 

			var url = jcontextPath+"/cwhbcsh!checkSaveBefore";  
	    	var jsonObj = {"domain.ssJgbm":ssJgbm,"domain.zcflDm":zcflDm,"domain.yhmc":yhmc,
                           "domain.hm":hm,"domain.zh":zh,"domain.csje":csje,"domain.cshDjxh":cshDjxh,"domain.flMc":ary[1]};   			
			ajaxCommon(url,jsonObj,"saveCheck");
		});
	});
	
	function saveCheck(tree){
		if(tree.domain.yhTree!=null&&tree.domain.yhTree!=''&&tree.domain.yhTree==1){
			showError("银行名称和账号已存在！");
			return;
		}
		if(tree.domain.flTree!=null&&tree.domain.flTree!=''&&tree.domain.flTree==1){
			showError("资产分类已存在！");
			return;
		}
		save();
	}
	function save(){
		var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
		var zcflDm = trim($("#mainForm_domain_zcflDm").val()); 
		var yhmc = trim($("#mainForm_domain_yhmc").val()); 
		var hm = trim($("#mainForm_domain_hm").val()); 
		var zh = trim($("#mainForm_domain_zh").val()); 
		var csje = trim($("#mainForm_domain_csje").val()); 
		var val= $("select").find("option:selected").text();
		var ary=val.split(" ");
		var cshDjxh = trim($("#mainForm_domain_cshDjxh").val()); 
		var url = jcontextPath+"/cwhbcsh!save";  
		var jsonObj = {"domain.ssJgbm":ssJgbm,"domain.zcflDm":zcflDm,"domain.yhmc":yhmc,
                "domain.hm":hm,"domain.zh":zh,"domain.csje":csje,"domain.cshDjxh":cshDjxh,"domain.flMc":ary[1]};   			
	ajaxCommon(url,jsonObj,"saveOK");
	}
	
	function saveOK(){
		showSuccess("保存成功！","saveAfter");
	}
	function saveAfter(){
		window.close();
	}
	function checkdata(){
		var controlNameArray = ["domain.csje"];
		var labelNameArray = ["初始金额"];
		var compareValueArray = [14.2];
		var nodeTypeArray = [NodeType.DECIMAL];
		var notNullArray = [true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	
	function onFl(){ 
		   var val= $("select").find("option:selected").text();
			var ary=val.split(" ");
			//alert(ary[1]);
			if(ary[1]=='银行存款')
			{
				$("#mainForm_domain_tage").val(1);
				var yhmc = $("#mainForm_domain_yhmc")[0]; 
				var hm = $("#mainForm_domain_hm")[0]; 
				var zh =$("#mainForm_domain_zh")[0]; 
				yhmc.readOnly=false;
				yhmc.className="pop_input bgstyle_required";
				hm.readOnly=false;
				hm.className="pop_input bgstyle_required";
				zh.readOnly=false;
				zh.className="pop_input bgstyle_required";
			}
			else{
				$("#mainForm_domain_tage").val(2);
				var yhmc = $("#mainForm_domain_yhmc")[0]; 
				var hm = $("#mainForm_domain_hm")[0]; 
				var zh =$("#mainForm_domain_zh")[0]; 
				yhmc.readOnly=true;
				yhmc.className="pop_input bgstyle_optional";
				$("#mainForm_domain_yhmc").val("");
				hm.readOnly=true;
				hm.className="pop_input bgstyle_optional";
				$("#mainForm_domain_hm").val("");
				zh.readOnly=true;
				zh.className="pop_input bgstyle_optional";
				$("#mainForm_domain_zh").val("");
			}
			//var temp=fl.options[fl.selectedindex].text;
			//alert(temp);
		}
</script>
</head>

<body >
<%try{ %>
<s:form action="cwhbzccsh!initMx" namespace="" method="post" id="mainForm" name="mainForm">
	<input type="hidden" id="mainForm_domain_cshDjxh" value='<s:property value="domain.cshDjxh"/>'/>
	<input type="hidden" id="mainForm_domain_tage" value="2"/>
	<s:hidden name="domain.ssJgbm"></s:hidden>
		<div class="pop_contc" style="height:310px; ">
		<fieldset>
    	 	 <legend>所属单位</legend>
    	 	 	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
    	 	 	  	<tr>
    	 	 	  		<td  width="10%" align="center">单位名称：</td>
    	 	 	  		<td  width="60%" align="left"><s:textfield name="domain.sjMc" rows="3" cssClass="pop_input bgstyle_required" readonly="true"  ></s:textfield></td>
    	 	 	  	</tr>
    	 	 	  </table>
    	 </fieldset>
		<fieldset>
		    <legend>财务货币信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				
				<tr>
      				<td width="15%" align="right">资产分类：</td>
      				<td width="35%">
						<sys:Zcfl myName="domain.zcflDm" myId="mainForm_domain_zcflDm" myClass="select" onChange="onFl()"></sys:Zcfl>
      				</td>
      				<td align="right" width="15%"><font class="font_red"  id="font1"></font>银行名称：</td>
      				<td width="35%">
      					<s:textfield name="domain.yhmc" rows="3" cssClass="pop_input bgstyle_optional"  readonly="true"></s:textfield>
      				</td>
      				
      			</tr>
      			<tr>
      				<td  align="right" width="15%"><font class="font_red"  id="font2"></font>账号：</td>
      				<td width="35%">
      					<s:textfield name="domain.zh" cssClass="pop_input bgstyle_optional" readonly="true"></s:textfield>
      				</td>
      				<td  align="right" width="15%"><font class="font_red"  id="font2"></font>用户名：</td>
      				<td width="35%">
      					<s:textfield name="domain.hm" rows="3" cssClass="pop_input bgstyle_optional"  readonly="true"></s:textfield>
      				</td>
      			</tr>
			</table>
			</fieldset>
			<fieldset>
    	 	 <legend>初始金额信息</legend>
    	 	 	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
    	 	 	  	<tr>
    	 	 	  		<td  width="10%" align="center"><font class="font_red"  id="font4">*</font>初始金额：</td>
    	 	 	  		<td  width="60%" align="left"><s:textfield name="domain.csje" cssClass="pop_input bgstyle_required" ></s:textfield></td>
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
