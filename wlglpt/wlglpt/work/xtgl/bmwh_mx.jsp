<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>部门维护</title>
<%@ include file="/common/meta.jsp"%>
<!-- 实现IE6下png图片透明 -->
<!--[if IE 6]>
<script src="<sys:context/>/resource/pageframe/js/DD_belatedPNG_0.0.8a-min.js" type="text/javascript"></script>
<script type="text/javascript">
	DD_belatedPNG.fix('h2, a');
</script>
<![endif]-->

<script type="text/javascript">
	$(function(){
		
		var bz=$("#mainForm_domain_qybz").val();
		if(bz=='Y'){
			$(":radio[name='bz']")[0].checked=true;
		}
		else if(bz=='N'){
			$(":radio[name='bz']")[1].checked=true;
		}
		else{
			$(":radio[name='bz']")[0].checked=true;
		}
		
		$("#closeBtn").click(function(){
			window.close();
		})
	
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var mc = trim($("#mainForm_domain_mc").val());
			var bz =$(":radio[name='bz']")[0].checked?"Y":"N";
			var yb = trim($("#mainForm_domain_yb").val()); 
			var fzr = trim($("#mainForm_domain_fzr").val()); 
			var dh = trim($("#mainForm_domain_dh").val()); 
			var sj = trim($("#mainForm_domain_sjJgbm").val()); 
			var id = trim($("#mainForm_domain_jgbm").val()); 
			var url = jcontextPath+"/bmwh!save";  
	    	var jsonObj = {"domain.mc":mc,"domain.fzr":fzr,"domain.yb":yb,
	    			   "domain.dh":dh,"domain.sjJgbm":sj,"domain.jgbm":id,"domain.qybz":bz};   			
			ajaxCommon(url,jsonObj,"saveOk");
		});
	});
	
	function saveOk(){
		showSuccess("保存成功！","yesSaveCallBack");
	}
	
	function yesSaveCallBack(){
		window.close();
	}
	function checkdata(){
		var controlNameArray = ["domain.mc","domain.dh","domain.fzr"];
		var labelNameArray = ["名称","电话","负责人"];
		var compareValueArray = [100,100,100];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%
try {
%>
<s:form action="bmwh!initMx" namespace="/xtgl" method="post" id="mainForm" name="mainForm">
	<input type="hidden" id="mainForm_domain_jgbm" value='<s:property value="domain.jgbm"/>' />
	<input type="hidden" id="mainForm_domain_sjJgbm" value='<s:property value="domain.sjJgbm"/>' />
	<s:hidden name="domain.qybz"></s:hidden>
	<div class="pop_contc">
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
		    <legend>部门基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">    			
				<tr>
					<td width="15%" align="right"><font class="font_red">*</font>名称：</td>
					<td width="35%"><s:textfield name="domain.mc" cssClass="pop_input bgstyle_required"></s:textfield></td>
					<td  width="15%" align="right">负责人：</td>
					<td width="35%" >
					<s:textfield name="domain.fzr" cssClass="pop_input bgstyle_optional"></s:textfield></td>	
				</tr>
				 <tr>
				      	<td width="15%" align="right">邮编：</td>
				      	<td>
				      		<s:textfield name="domain.yb" cssClass="pop_input bgstyle_optional" ></s:textfield>
				      	</td>
				        <td width="15%" align="right">电话：</td>
				      	<td width="35%">
				      		 <s:textfield name="domain.dh" cssClass="pop_input bgstyle_optional" ></s:textfield>
				        </td>
				    </tr>
				    <tr>
				     	<td width="15%" align="right">创建人：</td>
				     	 <td>
				      		<s:textfield name="domain.cjrMc" cssClass="pop_input bgstyle_optional" readonly="true" ></s:textfield>
				     	 </td>
				     	 <td width="15%" align="right">创建日期：</td>
				     	 <td>
				      		<s:textfield name="domain.cjrq" cssClass="pop_input bgstyle_optional" readonly="true" ></s:textfield>
				     	 </td>
				    </tr>
				    
				    <tr>
				      	<td width="15%" align="right">修改人：</td>
				     	 <td>
				      		<s:textfield name="domain.xgrMc" cssClass="pop_input bgstyle_optional" readonly="true" ></s:textfield>
				     	 </td>
				     	 <td width="15%" align="right">修改日期：</td>
				     	 <td>
				      		<s:textfield name="domain.xgrq" cssClass="pop_input bgstyle_optional" readonly="true" ></s:textfield>
				     	 </td>
				    </tr>	
				    
				    <tr>
				      	<td width="15%" align="right">启用标志：</td>
				      	<td>
				      		<s:radio list="#{'1':'启用','2':'停用'}" name="bz" theme="simple"></s:radio>
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
