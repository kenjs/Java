<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>����ά��</title>
<%@ include file="/common/meta.jsp"%>
<!-- ʵ��IE6��pngͼƬ͸�� -->
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
		showSuccess("����ɹ���","yesSaveCallBack");
	}
	
	function yesSaveCallBack(){
		window.close();
	}
	function checkdata(){
		var controlNameArray = ["domain.mc","domain.dh","domain.fzr"];
		var labelNameArray = ["����","�绰","������"];
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
    	 	 <legend>�ϼ���λ</legend>
    	 	 	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
    	 	 	  	<tr>
    	 	 	  		<td  width="15%" align="right">�ϼ���λ���ƣ�</td>
    	 	 	  		<td  width="60%" align="left"><s:textfield name="domain.sjMc" rows="3" cssClass="pop_input bgstyle_required" readonly="true"  ></s:textfield></td>
    	 	 	  	</tr>
    	 	 	  </table>
    	 </fieldset>
	
	   <fieldset>
		    <legend>���Ż�����Ϣ</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">    			
				<tr>
					<td width="15%" align="right"><font class="font_red">*</font>���ƣ�</td>
					<td width="35%"><s:textfield name="domain.mc" cssClass="pop_input bgstyle_required"></s:textfield></td>
					<td  width="15%" align="right">�����ˣ�</td>
					<td width="35%" >
					<s:textfield name="domain.fzr" cssClass="pop_input bgstyle_optional"></s:textfield></td>	
				</tr>
				 <tr>
				      	<td width="15%" align="right">�ʱࣺ</td>
				      	<td>
				      		<s:textfield name="domain.yb" cssClass="pop_input bgstyle_optional" ></s:textfield>
				      	</td>
				        <td width="15%" align="right">�绰��</td>
				      	<td width="35%">
				      		 <s:textfield name="domain.dh" cssClass="pop_input bgstyle_optional" ></s:textfield>
				        </td>
				    </tr>
				    <tr>
				     	<td width="15%" align="right">�����ˣ�</td>
				     	 <td>
				      		<s:textfield name="domain.cjrMc" cssClass="pop_input bgstyle_optional" readonly="true" ></s:textfield>
				     	 </td>
				     	 <td width="15%" align="right">�������ڣ�</td>
				     	 <td>
				      		<s:textfield name="domain.cjrq" cssClass="pop_input bgstyle_optional" readonly="true" ></s:textfield>
				     	 </td>
				    </tr>
				    
				    <tr>
				      	<td width="15%" align="right">�޸��ˣ�</td>
				     	 <td>
				      		<s:textfield name="domain.xgrMc" cssClass="pop_input bgstyle_optional" readonly="true" ></s:textfield>
				     	 </td>
				     	 <td width="15%" align="right">�޸����ڣ�</td>
				     	 <td>
				      		<s:textfield name="domain.xgrq" cssClass="pop_input bgstyle_optional" readonly="true" ></s:textfield>
				     	 </td>
				    </tr>	
				    
				    <tr>
				      	<td width="15%" align="right">���ñ�־��</td>
				      	<td>
				      		<s:radio list="#{'1':'����','2':'ͣ��'}" name="bz" theme="simple"></s:radio>
				      	</td>
				   </tr>		
			</table>
			</fieldset>
			 <div class="pop_btn">
			 	  <button type="button" class="pop_btnbg" id="saveBtn">�� ��</button>
				 	&nbsp;
		       <button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
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
