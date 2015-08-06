<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>总公司维护</title>
<%@ include file="/common/meta.jsp"%>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript">
	$(function(){	
    $("#saveBtn").click(function(){
      var jgbm = trim($("#mainForm_domain_jgbm").val());
 
      var mc = trim($("#mainForm_domain_mc").val()); 
      var jc = trim($("#mainForm_domain_jc").val());  
      var dz = trim($("#mainForm_domain_dz").val()); 
      var dh = trim($("#mainForm_domain_dh").val()); 
      var yb = trim($("#mainForm_domain_yb").val()); 
      var fzr = trim($("#mainForm_domain_fzr").val());  
      
      if(!checkdata()){//保存信息判断
      
		 return ;
	   }

      var url = jcontextPath+"/xtgl/zgswh!save";  
      var jsonObj = {  
                       "domain.jgbm":jgbm,
                       "domain.mc":mc,
                       "domain.jc":jc,
                       "domain.dz":dz,
                       "domain.dh":dh,
                       "domain.yb":yb,
                       "domain.fzr":fzr
                       };  
       
      
      ajaxCommon(url,jsonObj, "doSuccess");
    });
  });
  
  function doSuccess(data){
		hideMessage();
		showSuccess("保存成功！");
	}
	
	//检测数据
	function checkdata(){
		var controlNameArray = ["domain.mc","domain.jc",
		                        "domain.dz","domain.dh",
		                        "domain.yb","domain.fzr"];
		var labelNameArray = ["名称","简称",
		                      "地址","电话",
		                      "邮编","负责人"];
		var compareValueArray = [100,100,
			                     100,40,
			                     10,40];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,
                            true,false,
                            false,true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<s:form action="zgswh!init" namespace="/xtgl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.jgbm"></s:hidden>
	<div class="right_btnbg">
	    <ul class="lcont">
		    <li class="no">操作：</li>
		    <li><a href="#" id="saveBtn" class="licon06">保 存</a></li>
		    <li><a href="#" id="closeBtn" class="licon03">关 闭</a></li>
	  	</ul>
	
	    <ul class="rcont">
		    <li class="ricon03">帮助</li>
	  	</ul>
	</div>
	<div class="right_cont">
	<fieldset>
		<legend>基本信息</legend>
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css" >  
		  <tr>
		  	<td width="10%" align="right"><font color="red">*</font>名称：</td>
		  	<td width="40%"><s:textfield name="domain.mc" cssClass="pop_input bgstyle_required"/></td>
		  	<td width="10%" align="right"><font color="red">*</font>简称：</td>
		  	<td width="40%"><s:textfield name="domain.jc" cssClass="pop_input bgstyle_required"></s:textfield></td>
		  	<td></td>
		  </tr> 
		  <tr>
		  	<td align="right"><font color="red">*</font>地址：</td>
		  	<td><s:textfield name="domain.dz" cssClass="pop_input bgstyle_required"></s:textfield></td>
		  	<td align="right"><font color="red">*</font>负责人：</td>
		  	<td ><s:textfield name="domain.fzr" cssClass="pop_input bgstyle_required"></s:textfield></td>
		  	<td></td>
		  </tr>
		  <tr>
		  	<td align="right">邮编：</td>
		  	<td><s:textfield name="domain.yb" cssClass="pop_input bgstyle_optional" ></s:textfield></td>
		  	<td align="right">电话：</td>
		  	<td><s:textfield name="domain.dh" cssClass="pop_input bgstyle_optional" ></s:textfield></td>
		  	<td></td>
		  </tr>
		  <tr>
		  	<td align="right">机构编码：</td>
		  	<td><s:textfield name="domain.jgbm" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td align="right">行政区划：</td>
		  	<td><s:textfield name="domain.xzqhMc" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td></td>
		  </tr>
		  <tr>
		  	<td align="right">级次编码：</td>
		  	<td><s:textfield name="domain.jcbm" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td align="right">级别代码：</td>
		  	<td><s:textfield name="domain.jbdm" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td></td>
		  </tr>
		  <tr>
		  	<td align="right">机构类别：</td>
		  	<td><s:textfield name="domain.jglbMc" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td align="right">上级机构：</td>
		  	<td><s:textfield name="domain.sjJgbm" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td></td>
		  </tr>
		  <tr>
		  	<td align="right">企业注册序号：</td>
		  	<td><s:textfield name="domain.qyZcxh" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td align="right">企业编码：</td>
		  	<td><s:textfield name="domain.qybm" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td></td>
		  </tr>
		  <tr>
		  	<td align="right">拼音简写：</td>
		  	<td><s:textfield name="domain.pyjx" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td align="right">拼音全拼：</td>
		  	<td><s:textfield name="domain.pyqp" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td></td>
		  </tr>
		  <tr>
		  	<td align="right">创建人：</td>
		  	<td><s:textfield name="domain.cjrMc" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td align="right">创建日期：</td>
		  	<td><s:textfield name="domain.cjrq" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td></td>
		  </tr>
		  <tr>
		  	<td align="right">修改人：</td>
		  	<td><s:textfield name="domain.xgrMc" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td align="right">修改日期：</td>
		  	<td><s:textfield name="domain.xgrq" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td></td>
		  </tr>
	 	 </table>
 	  </fieldset>
 	  </div>  
  <%@include file="/common/message.jsp" %>
</s:form>
</body>
</html>
