<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>财务-客户预收收入</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		$("#yhShow").hide();
		$("#closeBtn").click(function(){
			window.close();
		})
		
		$("#mainForm_domain_jbrCzyDjxh").attr("disabled","disabled");
		
		$("#saveBtn").click(function(){
			var czJe = trim($("#mainForm_domain_czJe").val());
			if(czJe==''){
				showError("充值金额不能为空！");
				return;
			}
			if(czJe/1<=0){
				showAlert("金额必须大于零");
				return;
			} 
			if(!checkdata()){
				return;
			}
			saveBefore();
			
		});
	});
	
	function saveBefore(){
		var zffs = trim($("#mainForm_domain_zffsDm").val()); 
		var zcfl = trim($("#mainForm_domain_zcflDm").val()); 
		var url=jcontextPath+"/khyslr!checkZcFl";
		if(zffs!='2'){
			var json={"domain.zcfl":zcfl};
			ajaxCommon(url,json,"toSave");
		}
		else{
			onSave();
		}
	}
	
	function toSave(data){
		var zffs = trim($("#mainForm_domain_zffsDm").val()); 
		var val = $("#mainForm_domain_zffsDm").find("option:selected").text();
		var ary=val.split(" ");
		var tager=data.domain.tager;
		if(tager=='1'){
			onSave();
		}
		else{
			showError("请先到资产初始化里面维护"+ary[1]+"!");
			return;
		}
	}
	function onSave(){
		
		var ssJgbm=$("#mainForm_domain_ssJgbm").val();
		var khmc=$("#mainForm_domain_khMc").val();
		var czJe = trim($("#mainForm_domain_czJe").val()); 
		var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
		var khMc=$("#mainForm_domain_khMc").val();
		var je = trim($("#mainForm_domain_je").val())/1;
		var srDjxh = trim($("#mainForm_domain_srDjxh").val()); 
		je=parseFloat(je)+parseFloat(czJe);
		var zffs = trim($("#mainForm_domain_zffsDm").val()); 
		var zcfl = trim($("#mainForm_domain_zcflDm").val()); 
		var jbrCzyDjxh = trim($("#mainForm_domain_jbrCzyDjxh").val()); 
		var rq = trim($("#mainForm_domain_rq").val()); 
		var yhCshDjxh=$("#mainForm_domain_yhCshDjxh").val();
		if(zffs!="2"){
			yhCshDjxh="";
		}
		var yhhdh=$("#mainForm_domain_yhhdh").val();
		var bz=$("#mainForm_domain_bz").val();
		var url = jcontextPath+"/khyslr!save";  
    	var jsonObj = {"domain.khDjxh":khDjxh,"domain.je":je,"domain.khMc":khmc,"domain.ssJgbm":ssJgbm,
                       "domain.srDjxh":srDjxh,"domain.czJe":czJe,"domain.zffs":zffs,"domain.zcfl":zcfl,"domain.jbrCzyDjxh":jbrCzyDjxh,
                       "domain.jbRq":rq,"domain.yhCshDjxh":yhCshDjxh,"domain.yhhdh":yhhdh,"domain.bz":bz,"domain.khMc":khMc};   			
		ajaxCommon(url,jsonObj,"saveOk");
	}
	
	function saveOk(){
		showSuccess("保存成功！","saveAfter");
	}
	
	function saveAfter(){
		window.close();
	}
	
	function checkdata(){
		var controlNameArray = ["domain.czJe","domain.zffsDm",
		                        "domain.zcflDm","domain.jbrCzyDjxh","domain.rq",
		                        "domain.yhCshDjxh","domain.yhhdh"];
		var labelNameArray = ["充值金额","支付方式",
		                      "资产分类","经办人","经办日期","银行账号","银行回单号"];
		var compareValueArray = [14.2,20,
			                     20,20,20,20,20];
		var nodeTypeArray = [NodeType.DECIMAL,NodeType.STRING,
		                     NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING
		                     ,NodeType.STRING];
		var notNullArray = [true,true,
                            true,true,true,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	function onCheck1(){
		var zffs=$("#mainForm_domain_zffsDm").val();
		$("#mainForm_domain_zcflDm").val("1"+zffs);
		if(zffs=="2"){
			$("#yhShow").show();
		}
		else{
			$("#yhShow").hide();
		}
	}
	function onCheck2(){
		var zcfl=$("#mainForm_domain_zcflDm").val();
		$("#mainForm_domain_zffsDm").val(zcfl.charAt(1));
		if(zcfl.charAt(1)=="2"){
			$("#yhShow").show();
		}
		else{
			$("#yhShow").hide();
		}
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="cwkhysgl!initMx" namespace="" method="post" id="mainForm" name="mainForm">
	<input type="hidden" id="mainForm_domain_srDjxh" value='<s:property value="domain.srDjxh"/>'/>
	<s:hidden name="domain.khDjxh"></s:hidden>
	<s:hidden name="domain.ssJgbm"></s:hidden>
	<div id="maincont">
		<div class="pop_contc" style="height:360px;">
			<fieldset>
			<legend>客户基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
				     	<td width="15%" align="right">客户名称：</td>
				     	 <td width="35%">
				      		<s:textfield name="domain.khMc" cssClass="pop_input bgstyle_readonly"></s:textfield>
				     	 </td>
				     	 <td width="15%" align="right">余额：</td>
				     	 <td width="35%">
				      		<s:textfield name="domain.je" cssClass="pop_input bgstyle_readonly"  ></s:textfield>
				     	 </td>
				    </tr>
				    </table>
			</fieldset>
				<fieldset>
			<legend>费用类别信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				
      			 
      			
      			<tr>
      				<td align="right" width="15%"><font class="font_red">*</font>充值金额：</td>
      				<td colspan="5" >
      					<s:textfield name="domain.czJe" rows="3" cssClass="pop_input_colspan2 bgstyle_required" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				  <td align="right" width="15%"><font class="font_red">*</font>支付方式：</td>
	                <td width="35%">
	                    <sys:Zffs myName="domain.zffsDm" myId="mainForm_domain_zffsDm" myClass="select" onChange="onCheck1()"></sys:Zffs>
	                </td>	
	                 <td align="right" width="15%"><font class="font_red">*</font>资产分类：</td>
	  				<td width="35%">
	  				     <sys:ZcflCsh myName="domain.zcflDm" myId="mainForm_domain_zcflDm" myClass="select" onChange="onCheck2()" ssJgbm="domain.ssJgbm"></sys:ZcflCsh>
	  				</td>
      			</tr>
      			<tr>
      					<td align="right" width="15%"><font class="font_red">*</font>经办人：</td>
	                <td width="35%">
	                    <sys:GsryList myName="domain.jbrCzyDjxh" myId="mainForm_domain_jbrCzyDjxh" myClass="select" ssJgbm="domain.ssJgbm"></sys:GsryList>
	                </td>
	                <td align="right" width="15%"><font class="font_red">*</font>经办日期：</td>
	  				<td width="35%">
	  				     <sys:dateCurrentDayTag myName="domain.rq" myId="mainForm_domain_rq" myClass="ymdate" />
	  				</td>	
      			</tr>
      			<tr id="yhShow">
	  				<td align="center">银行账号：</td>
	                <td>
	                     <sys:yhzh myName="domain.yhCshDjxh" myId="mainForm_domain_yhCshDjxh" myClass="select" ssJgbm="domain.ssJgbm"></sys:yhzh>
	                </td>
	  				<td align="center" >银行回单号：</td>
	  				<td >
	  				     <s:textfield name="domain.yhhdh" cssClass="inputext pop_input noborder bgstyle_optional"></s:textfield>
	  				</td>	
	  			 </tr>
				 <tr>
	  			 	<td align="right">备注：</td>
	  			 	<td colspan="5">
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
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
