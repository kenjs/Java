<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>�ܹ�˾ά��</title>
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
      
      if(!checkdata()){//������Ϣ�ж�
      
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
		showSuccess("����ɹ���");
	}
	
	//�������
	function checkdata(){
		var controlNameArray = ["domain.mc","domain.jc",
		                        "domain.dz","domain.dh",
		                        "domain.yb","domain.fzr"];
		var labelNameArray = ["����","���",
		                      "��ַ","�绰",
		                      "�ʱ�","������"];
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
		    <li class="no">������</li>
		    <li><a href="#" id="saveBtn" class="licon06">�� ��</a></li>
		    <li><a href="#" id="closeBtn" class="licon03">�� ��</a></li>
	  	</ul>
	
	    <ul class="rcont">
		    <li class="ricon03">����</li>
	  	</ul>
	</div>
	<div class="right_cont">
	<fieldset>
		<legend>������Ϣ</legend>
	    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css" >  
		  <tr>
		  	<td width="10%" align="right"><font color="red">*</font>���ƣ�</td>
		  	<td width="40%"><s:textfield name="domain.mc" cssClass="pop_input bgstyle_required"/></td>
		  	<td width="10%" align="right"><font color="red">*</font>��ƣ�</td>
		  	<td width="40%"><s:textfield name="domain.jc" cssClass="pop_input bgstyle_required"></s:textfield></td>
		  	<td></td>
		  </tr> 
		  <tr>
		  	<td align="right"><font color="red">*</font>��ַ��</td>
		  	<td><s:textfield name="domain.dz" cssClass="pop_input bgstyle_required"></s:textfield></td>
		  	<td align="right"><font color="red">*</font>�����ˣ�</td>
		  	<td ><s:textfield name="domain.fzr" cssClass="pop_input bgstyle_required"></s:textfield></td>
		  	<td></td>
		  </tr>
		  <tr>
		  	<td align="right">�ʱࣺ</td>
		  	<td><s:textfield name="domain.yb" cssClass="pop_input bgstyle_optional" ></s:textfield></td>
		  	<td align="right">�绰��</td>
		  	<td><s:textfield name="domain.dh" cssClass="pop_input bgstyle_optional" ></s:textfield></td>
		  	<td></td>
		  </tr>
		  <tr>
		  	<td align="right">�������룺</td>
		  	<td><s:textfield name="domain.jgbm" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td align="right">����������</td>
		  	<td><s:textfield name="domain.xzqhMc" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td></td>
		  </tr>
		  <tr>
		  	<td align="right">���α��룺</td>
		  	<td><s:textfield name="domain.jcbm" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td align="right">������룺</td>
		  	<td><s:textfield name="domain.jbdm" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td></td>
		  </tr>
		  <tr>
		  	<td align="right">�������</td>
		  	<td><s:textfield name="domain.jglbMc" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td align="right">�ϼ�������</td>
		  	<td><s:textfield name="domain.sjJgbm" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td></td>
		  </tr>
		  <tr>
		  	<td align="right">��ҵע����ţ�</td>
		  	<td><s:textfield name="domain.qyZcxh" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td align="right">��ҵ���룺</td>
		  	<td><s:textfield name="domain.qybm" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td></td>
		  </tr>
		  <tr>
		  	<td align="right">ƴ����д��</td>
		  	<td><s:textfield name="domain.pyjx" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td align="right">ƴ��ȫƴ��</td>
		  	<td><s:textfield name="domain.pyqp" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td></td>
		  </tr>
		  <tr>
		  	<td align="right">�����ˣ�</td>
		  	<td><s:textfield name="domain.cjrMc" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td align="right">�������ڣ�</td>
		  	<td><s:textfield name="domain.cjrq" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td></td>
		  </tr>
		  <tr>
		  	<td align="right">�޸��ˣ�</td>
		  	<td><s:textfield name="domain.xgrMc" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
		  	<td align="right">�޸����ڣ�</td>
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
