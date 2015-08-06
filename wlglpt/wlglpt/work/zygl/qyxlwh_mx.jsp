<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>��ҵ-��·ά��</title>

<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>

<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(function(){
		//��������input����
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		initXzqhData(200);
		initXzqhInputSel();
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
			var sfdXzqhDm = trim($("#mainForm_domain_sfdXzqhDm").val()); 
			var mddXzqhDm = trim($("#mainForm_domain_mddXzqhDm").val()); 
			var fhr = trim($("#mainForm_domain_fhrXzqhDm").val()); 
			var shr = trim($("#mainForm_domain_shrXzqhDm").val()); 
			var lcs = trim($("#mainForm_domain_lcs").val()); 
			var ddts = trim($("#mainForm_domain_ddts").val());
			if(lcs/1<0){
				showAlert("���������С���㣡");
				return;
			}
			if(ddts/1<0){
				showAlert("������������С���㣡");
				return;
			} 
			var url = jcontextPath+"/qyxlwh!save";  
	    	var jsonObj = {"domain.ssJgbm":ssJgbm,"domain.sfdXzqhDm":sfdXzqhDm,"domain.mddXzqhDm":mddXzqhDm,"domain.lcs":lcs,
                           "domain.ddts":ddts,"domain.fhrXzqhDm":fhr,"domain.shrXzqhDm":shr};   			
			ajaxCommon(url,jsonObj,"saveOk");
		});
		
		 $("#closeBtn").click(function(){
				window.close();
			})
		
		
		//initRadio();	
	});
	
	function saveOk(){
		showSuccess("����ɹ���","saveAfter");
	}
	
	function saveAfter(){
		window.close();
	}
	
	function checkdata(){
		var controlNameArray = ["domain.fhrXzqhDm","domain.shrXzqhDm","domain.lcs",
		                        "domain.ddts"];
		var labelNameArray = ["ʼ����","Ŀ�ĵ�","�����",
		                      "�ﵽ����"];
		var compareValueArray = [20,20,16.2,
			                     6.2];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.DECIMAL,
			                 NodeType.DECIMAL];
		var notNullArray = [true,true,true,
                            true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="qyxlwh!initMx" namespace="" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.ssJgbm"></s:hidden>
	<s:hidden name="domain.sfdXzqhDm"></s:hidden>
	<s:hidden name="domain.mddXzqhDm"></s:hidden>
	<s:hidden name="jsonData"></s:hidden>
	<div id="maincont">
		<div class="pop_contc" style="height:270px; width: 97%;">
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
		    <legend>�ֹ�˾������Ϣ</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      			
      			<td align="right" width="13%"><font class="font_red">*</font>ʼ���أ�</td>
  				  <td align="left" width="30%">
  					<s:hidden name="domain.fhrXzqhDm" />
  					<div class="inputsel" style="width: 116px;">
  						<s:textfield name="domain.fhrXzqhMc" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width: 91px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="xzqh" xzqh="fhrXzqh" onFocus="this.blur()"></a>
		            </div>
		            
  				  </td>
  				  <td align="center" width="15%"><font class="font_red">*</font>�������</td>
      				<td width="30%">
      					<s:textfield name="domain.lcs" cssClass="pop_input bgstyle_required" ></s:textfield>
      				</td>
  				
  			
  					
      			</tr>
      			
      			<tr>
      				<td align="right" width="13%" ><font class="font_red">*</font>Ŀ�ĵأ�</td>
  				<td align="left" width="30%">
  					<s:hidden name="domain.shrXzqhDm"></s:hidden>
  					<div class="inputsel" style="width: 116px;">
  						<s:textfield name="domain.shrXzqhMc" cssClass="inputext pop_input noborder bgstyle_optional" cssStyle="width: 91px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="xzqh" xzqh="shrXzqh" onFocus="this.blur()"></a>
		            </div>
  				</td>	
      				<td align="center" width="15%"><font class="font_red">*</font>�ﵽ������</td>
      				<td>
      					<s:textfield name="domain.ddts" cssClass="pop_input bgstyle_required" ></s:textfield>
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
		</div>
	<%@include file="/common/message.jsp" %>
	
	<div id="inputSel_xzqh" class="inputselcont" style="position: absolute; top: 159px; left: 94px; display: none;" >
		                
      <iframe src="" scrolling="no" frameborder="0" style="position:absolute; top:0; left:0; z-index:-1; width:274px; height:100px;"></iframe>
    </div>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
