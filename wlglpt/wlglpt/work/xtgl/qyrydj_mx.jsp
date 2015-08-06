<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>用户维护</title>
<%@ include file="/common/meta.jsp"%>
<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript">
function yz(){
  var pwd = trim($("#mainForm_domain_pwd").val()); 
  var pwdd = trim($("#mainForm_domain_pwdd").val()); 
	if(pwd!=pwdd){	
	return false; 
  }	
   return true;	
}
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		})
		
		$("#saveBtn").click(function(){	
			var czyDjxh = trim($("#mainForm_domain_czyDjxh").val()); 
		
			if(!checkdata(czyDjxh)){
				return;
			}
			
			if(!yz()){	
			showAlert("密码不一致，请重新输入！");	
				return false;
			}
			var reg=/[\u4E00-\u9FA5]/g ;
			var mc = trim($("#mainForm_domain_mc").val()); 
			var zh = trim($("#mainForm_domain_zh").val()); 
			if(reg.test(zh)){
				showError("账号不能为汉字！");
				$("#mainForm_domain_zh").select();
				$("#mainForm_domain_zh").focus();
				return false;
			}
			var pwd = trim($("#mainForm_domain_pwd").val()); 
			var pwdd = trim($("#mainForm_domain_pwdd").val()); 
			var gsbm = trim($("#mainForm_domain_gsbm").val()); 
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
			var gwdjxh = trim($("#mainForm_domain_gwDjxh").val()); 
			var rylbDm = trim($("#mainForm_domain_rylbDm").val()); 
			var dlyzfsDm = trim($("#mainForm_domain_dlyzfsDm").val()); 
			var qxJgbm = trim($("#mainForm_domain_qxJgbm").val()); 			
			var sjhm = trim($("#mainForm_domain_sjhm").val()); 
			var sjdh = trim($("#mainForm_domain_sjdh").val()); 
			var bgdh = trim($("#mainForm_domain_bgdh").val()); 
			var bgdhao = trim($("#mainForm_domain_bgdhao").val()); 
			var jtdh = trim($("#mainForm_domain_jtdh").val()); 
			var qq = trim($("#mainForm_domain_qq").val()); 
			var msn = trim($("#mainForm_domain_msn").val()); 
			var email = trim($("#mainForm_domain_email").val());			
			var oldGwdjxh = trim($("#mainForm_domain_oldGwdjxh").val()); 
			var url = jcontextPath+"/yhwh!save";  
	    	var jsonObj = {"domain.mc":mc,"domain.zh":zh,"domain.pwd":pwd,"domain.pwdd":pwdd,"domain.gsbm":gsbm,"domain.ssJgbm":ssJgbm,"domain.gwDjxh":gwdjxh,"domain.qxJgbm":qxJgbm,"domain.rylbDm":rylbDm,
	    	              "domain.dlyzfsDm":dlyzfsDm,"domain.sjhm":sjhm,"domain.sjdh":sjdh,"domain.bgdh":bgdh,"domain.bgdhao":bgdhao,
	    	              "domain.jtdh":jtdh,"domain.qq":qq,"domain.msn":msn,"domain.email":email,
                          "domain.czyDjxh":czyDjxh,"domain.oldGwdjxh":oldGwdjxh};   			
			ajaxCommon(url,jsonObj,"saveOk");
		});
		
	});
	
	$(document).ready(function(){
		initTable();
		var gs = $("#mainForm_domain_gsbm").val();
		bmInit(gs, '', 'domain.ssJgbm', 'mainForm_domain_ssJgbm', 'Y', 'Y')
		
		var bm = $("#mainForm_domain_ssJgbm").val();
		gwInit(bm, '', 'domain.gwDjxh', 'mainForm_domain_gwDjxh', 'Y', 'Y')
	});
	
	function saveOk(){
		showSuccess("保存成功！","doSaveAfter");
	}
	
	function doSaveAfter(){
		window.close();
	}
	
	function checkdata(val){
		var controlNameArray = ["domain.mc","domain.zh","domain.pwd","domain.pwdd","domain.gsbm",
		                       "domain.ssJgbm","domain.gwDjxh","domain.qxJgbm","domain.rylbDm","domain.dlyzfsDm",
		                       "domain.sjhm","domain.sjdh","domain.bgdh","domain.bgdhao","domain.jtdh",
		                       "domain.qq","domain.msn","domain.email"];
		var labelNameArray =   ["名称","账号","密码","确认密码","所属公司",
		                       "所属部门","所属岗位","权限机构","人员类别代码","登录验证方式",
		                       "手机号码","手机短号","办公电话","办公短号","家庭电话",
		                       "QQ号码","MSN号码","EMAIL地址"];
		var compareValueArray =[40,40,100,40,40,
		                        40,40,40,40,40,
		                        40,40,40,40,20,
		                        20,40,40];
		var nodeTypeArray  =  [NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,
		                       NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,
			                   NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,
			                   NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = "";
		if(val != "" && val != null){
			notNullArray = [true,true,false,false,true,
				             true,true,true,true,true,
				             false,false,false,false,false,
				             false,false,false];	
		}else{
			notNullArray = [true,true,true,true,true,
	                         true,true,true,true,true,
	                         false,false,false,false,false,
	                         false,false,false];
		}
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}

	function initTable(){
		var czyDjxh = trim($("#mainForm_domain_czyDjxh").val()); 
		if(czyDjxh != "" && czyDjxh != null){
			$("#mm").html("登陆密码：");
			$("#qrmm").html("确认密码：");
			$("[name='domain.mc']").attr("readonly",true);
			$("[name='domain.mc']").css("background-color","#efefef");
		}
	}
</script>
</head>

<body>
<%
try {
%>
<s:form action="yhwh!initMx" namespace="/xtgl" method="post" id="mainForm" name="mainForm">
	<input type="hidden" id="mainForm_domain_czyDjxh" value='<s:property value="domain.czyDjxh"/>' />
	<input type="hidden" id="mainForm_domain_oldGwdjxh" value='<s:property value="domain.gwDjxh"/>' />
		<div class="pop_contc" style="height:420px; overflow:auto;">
		  
		  	  
		   <fieldset>
		    <legend>基本信息</legend>
			<table id="zbTab" width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
					<td width="15%" align="center"><font class="font_red">*</font>用户名称：</td>
					<td width="35%"><s:textfield name="domain.mc" cssClass="pop_input bgstyle_required"></s:textfield></td>	               
					<td align="center" width="17%"><font class="font_red">*</font>登陆账号：</td>
					<td width="35%"><s:textfield name="domain.zh" cssClass="pop_input bgstyle_required"></s:textfield></td>
				</tr>
				<tr>
					<td align="center" id="mm"><font class="font_red">*</font>登陆密码：</td>
					<td><s:password name="domain.pwd" myId="mainForm_domain_pwd" cssClass="pop_input bgstyle_required"></s:password></td>
					<td align="center" id="qrmm"><font class="font_red">*</font>确认密码：</td>
					<td><s:password name="domain.pwdd" myId="mainForm_domain_pwdd"
						 cssClass="pop_input bgstyle_required" ></s:password></td>
				</tr>
							
				<tr>
					<td align="center"><font class="font_red">*</font>人员类别：</td>
					<td><sys:Rylb myId="mainForm_domain_rylbDm"
						myName="domain.rylbDm" contaisQxz="false"  myClass="select_long"></sys:Rylb>
					</td>
					<td align="center"><font class="font_red">*</font>登录验证方式：</td>
					<td><sys:Dlyzfs myId="mainForm_domain_dlyzfsDm"
						myName="domain.dlyzfsDm" contaisQxz="false"  myClass="select_long"></sys:Dlyzfs>
					</td>
				</tr>
				<tr>
					<td align="center">手机号码：</td>
					<td><s:textfield name="domain.sjhm" cssClass="pop_input bgstyle_required"></s:textfield></td>
					<td align="center">手机短号：</td>
					<td><s:textfield name="domain.sjdh" cssClass="pop_input bgstyle_required"></s:textfield></td>
				</tr>
				<tr>
					<td align="center">办公电话：</td>
					<td><s:textfield name="domain.bgdh" cssClass="pop_input bgstyle_required"></s:textfield></td>
		
					<td align="center">办公短号：</td>
					<td><s:textfield name="domain.bgdhao" cssClass="pop_input bgstyle_required"></s:textfield></td>
				</tr>
				<tr>
					<td align="center">家庭电话：</td>
					<td><s:textfield name="domain.jtdh" cssClass="pop_input bgstyle_required"></s:textfield></td>
		
					<td align="center">QQ号码：</td>
					<td><s:textfield name="domain.qq" cssClass="pop_input bgstyle_required"></s:textfield></td>
				</tr>
				<tr>
					<td align="center">MSN号码：</td>
					<td><s:textfield name="domain.msn" cssClass="pop_input bgstyle_required"></s:textfield></td>
		
					<td align="center">EMAIL地址：</td>
					<td><s:textfield name="domain.email" cssClass="pop_input bgstyle_required"></s:textfield></td>
				</tr>
			</table>
			</fieldset>
			
			<fieldset>
    	 	 <legend>单位信息</legend>
    	 	 	<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
    	 	 	  <tr>
					<td align="center" width="15%"><font class="font_red">*</font>所属单位：</td>
					<td align="left" width="34%">
					<sys:qxGsList myId="mainForm_domain_gsbm" myName="domain.gsbm" mcContainDmBz="Y" myClass="select" onChange="bmInit(this.value, '', 'domain.ssJgbm', 'mainForm_domain_ssJgbm', 'Y', 'N')"></sys:qxGsList>
						
					</td>
					<td align="center" width="17%"><font class="font_red">*</font>所属部门：</td>
					<td  width="34%"><select id="mainForm_domain_ssJgbm" name="domain.ssJgbm"  class="select" 
						onChange="gwInit(this.value, '', 'domain.gwDjxh', 'mainForm_domain_gwDjxh', 'Y', 'N')">
						
							<option value="${domain.ssJgbm}"></option>
						</select>
					
					</td>
				</tr>
				
				<tr>
					<td align="center"><font class="font_red">*</font>所属岗位：</td>
					<td><select id="mainForm_domain_gwDjxh" name="domain.gwDjxh" class="select">
					     <option value="${domain.gwDjxh}"></option>
					</select></td>
					<td align="center"><font class="font_red">*</font>权限机构：</td>
					<td><sys:gsList myId="mainForm_domain_qxJgbm" myName="domain.gsbm" mcContainDmBz="Y" myClass="select" 
						 />
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
	
	<%@include file="/common/message.jsp"%>
</s:form>
<%
} catch (Exception e) {
	e.printStackTrace();
	throw e;
}
%>
</body>
</html>
