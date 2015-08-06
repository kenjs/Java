<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<!-- ʵ��IE6��pngͼƬ͸�� -->
<!--[if IE 6]>
<script src="<sys:context />/resource/pageframe/js/DD_belatedPNG_0.0.8a-min.js" type="text/javascript"></script>
<script type="text/javascript">
	DD_belatedPNG.fix('h2, a');
</script>
<![endif]-->

<head>
<title>��ɫά��</title>
<%@ include file="/common/meta.jsp"%>
<script type="text/javascript">
	$(function(){
		 $("#closeBtn").click(function(){
			window.close();
		})
	
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var jsMc = trim($("#mainForm_domain_jsMc").val()); 
			var jsJc = trim($("#mainForm_domain_jsJc").val()); 
			var bzsm = trim($("#mainForm_domain_bzsm").val()); 
			var jsDjxh = trim($("#mainForm_domain_jsDjxh").val()); 
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 

			var url = jcontextPath+"/xtgl/qyjs!save";  
	    	var jsonObj = {"domain.jsMc":jsMc,"domain.jsJc":jsJc,"domain.bzsm":bzsm,"domain.jsDjxh":jsDjxh,"domain.ssJgbm":ssJgbm};   			
			ajaxCommon(url,jsonObj,"saveOk");
		});
	});
	
	function saveOk(){
		showSuccess("����ɹ���","yesSaveCallBack");
	}
	
	function yesSaveCallBack(){
		window.close();
	}
	
	function checkdata(){
		var controlNameArray = ["domain.jsMc","domain.jsJc","domain.bzsm"];
		var labelNameArray = ["��ɫ����","��ɫ���","��ע˵��"];
		var compareValueArray = [40,40,400];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="qyjs!initMx" namespace="/xtgl" method="post" id="mainForm" name="mainForm">
	<input type="hidden" id="mainForm_domain_jsDjxh" value='<s:property value="domain.jsDjxh"/>'/>
	<input type="hidden" id="mainForm_domain_ssJgbm" value='<s:property value="domain.ssJgbm"/>'/>
	
	
	
<div class="pop_contc">
	 <fieldset>
    	 	 <legend>������λ��Ϣ</legend>
    	 	 	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
    	 	 	  	<tr>
    	 	 	  		<td  width="15%" align="right">������λ��</td>
    	 	 	  		<td  width="60%" align="left"><s:textfield name="domain.sjMc" rows="3" cssClass="pop_input bgstyle_required" readonly="true"  ></s:textfield></td>
    	 	 	  	</tr>
    	 	 	  </table>
    	 </fieldset>


    <fieldset>
		    <legend>��ɫ������Ϣ</legend>
	 <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css"> 
	    <tr>
	      <td width="18%" align="right"><font class="font_red">*</font>��ɫ����</td>
	      <td width="75%">
	      	<s:textfield name="domain.jsMc" cssClass="pop_input noborder bgstyle_required" ></s:textfield>
	      </td>
	    </tr>
	    <tr>
	      <td align="right"><font class="font_red">*</font>��ɫ���</td>
	      <td>
	      	<s:textfield name="domain.jsJc" cssClass="pop_input noborder bgstyle_required" ></s:textfield>
	      </td>
	    </tr>
	    <tr>
	      <td align="right">��ע˵��</td>
	      <td>
	      	<s:textarea name="domain.bzsm" rows="3" cssClass="pop_textarea bgstyle_optional" ></s:textarea>
	      </td>
	    </tr>

	  </table>
	  </fieldset>
	  <div class="btn">
		<button type="button" class="pop_btnbg" id="saveBtn">�� ��</button>&nbsp;
	    <button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
	  </div>
</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
