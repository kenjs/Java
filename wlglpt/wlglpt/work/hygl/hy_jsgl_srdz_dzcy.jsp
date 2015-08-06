<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>���ʲ������</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		
		$("#closeBtn").click(function(){
			window.close();
		});
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var jsDjxh = $("#mainForm_domain_dzcyDomain_jsDjxh").val();
			var xh = $("#mainForm_domain_dzcyDomain_xh").val(); 
			var dzcyje = $("#mainForm_domain_dzcyDomain_dzcyje").val(); 
			var dzcyyyDm = trim($("#mainForm_domain_dzcyDomain_dzcyyyDm").val()); 
			var dzcyClfsDm = trim($("#mainForm_domain_dzcyDomain_dzcyClfsDm").val()); 
			var bz = trim($("#mainForm_domain_dzcyDomain_bz").val()); 
			var xcDjsDjxh = trim($("#mainForm_domain_dzcyDomain_xcDjsDjxh").val()); 
			var wlssDjxh = trim($("#mainForm_domain_dzcyDomain_wlssDjxh").val()); 
			
			var xgbz = trim($("#mainForm_domain_xgbz").val()); 
			var djsDjxh= trim($("#mainForm_domain_djsDjxh").val()); 

			var url = jcontextPath+"/hygl/jsglsrdz!saveMx";  
	    	var jsonObj = {"domain.dzcyDomain.jsDjxh":jsDjxh,"domain.dzcyDomain.xh":xh,"domain.dzcyDomain.dzcyje":dzcyje,
                           "domain.dzcyDomain.dzcyyyDm":dzcyyyDm,"domain.dzcyDomain.dzcyClfsDm":dzcyClfsDm,"domain.dzcyDomain.bz":bz,
                           "domain.dzcyDomain.xcDjsDjxh":xcDjsDjxh,"domain.dzcyDomain.wlssDjxh":wlssDjxh,"domain.xgbz":xgbz,"domain.djsDjxh":djsDjxh
                           };   			
			ajaxCommon(url,jsonObj);
		});
	});
	
	//�ɹ�������ݴ��� ��ʾ����ɹ����رյ�ǰҳ�棬�����ø����ڵ�ˢ�º���
	function doSuccess(data){
		hideMessage();
		showSuccess("����ɹ���","doYesCallBack");
	}
	
	//ѡ���ǵķ��ش���
	function doYesCallBack(){
		//sysClose();
		//parent.initMx();
		window.close();
	}
	
	function checkdata(){
		var controlNameArray = ["domain.dzcyDomain.xh","domain.dzcyDomain.dzcyje",
		                        "domain.dzcyDomain.dzcyyyDm","domain.dzcyDomain.dzcyClfsDm","domain.dzcyDomain.bz",
		                        "domain.dzcyDomain.xcDjsDjxh","domain.dzcyDomain.wlssDjxh"];
		var labelNameArray = ["���","���ʲ�����",
		                      "���ʲ���ԭ��","���ʲ��촦��ʽ","��ע˵��",
		                      "���ν���Ǽ����","������ʧ�Ǽ����"];
		var compareValueArray = [4,14.2,
			                     2,2,200,
			                     20,20];
		var nodeTypeArray = [NodeType.INTEGER,NodeType.DECIMAL,
			                 NodeType.STRING,NodeType.STRING,NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,false,
                            true,true,false,
                            false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
<base target="_self" />
</head>

<body>
<%try{ %>
<s:form action="jsglsrdz!queryMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.dzcyDomain.jsDjxh" /> 
	<s:hidden name="domain.djsDjxh"></s:hidden>
	<s:hidden name="domain.xgbz"></s:hidden>
	<s:hidden name="domain.dzcyDomain.xh"></s:hidden>
		<div class="pop_contc">
			<fieldset><legend>������Ϣ</legend>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
					<tr>
	      				<td width="30%" align="right"><font class="font_red">*</font>���ʲ����</td>
	      				<td width="70%">
	      					<s:textfield name="domain.dzcyDomain.dzcyje" cssClass="pop_input noborder bgstyle_required"></s:textfield>
	      				</td>
	      			</tr>
	      			<tr>
	      				<td align="right">���ʲ���ԭ��</td>
	      				<td>
	      					<sys:Dzcyyy myId="mainForm_domain_dzcyDomain_dzcyyyDm" myName="domain.dzcyDomain.dzcyyyDm" myClass="select" contaisQxz="true"></sys:Dzcyyy>
	      				</td>
	      			</tr>
	      			<tr>
	      				<td align="right">���ʲ��촦��ʽ��</td>
	      				<td>
	      					<sys:DzcyClfs myId="mainForm_domain_dzcyDomain_dzcyClfsDm" myName="domain.dzcyDomain.dzcyClfsDm" myClass="select" contaisQxz="true"></sys:DzcyClfs>
	      				</td>
	      			</tr>
	      			<tr>
	      				<td align="right">��ע˵����</td>
	      				<td>
	      					<s:textarea name="domain.dzcyDomain.bz" rows="3" cssClass="pop_textarea_colspan2 bgstyle_optional" ></s:textarea>
	      				</td>
	      			</tr>
	      			<!--  
	      			<tr>
	      				<td align="right">���ν���Ǽ���ţ�</td>
	      				<td>
	      					<s:textfield name="domain.dzcyDomain.xcDjsDjxh" cssClass="pop_input noborder bgstyle_optional" ></s:textfield>
	      				</td>
	      			</tr>
	      			-->
	      			<tr>
	      				<td align="right">������ʧ�Ǽ���ţ�</td>
	      				<td>
	      					<s:hidden name="domain.dzcyDomain.xcDjsDjxh"></s:hidden>
	      					<s:textfield name="domain.dzcyDomain.wlssDjxh" cssClass="pop_input noborder bgstyle_optional" ></s:textfield>
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
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
