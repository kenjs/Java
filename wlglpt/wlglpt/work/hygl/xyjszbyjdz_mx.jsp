<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>���ν���-ת��-�½����</title>

<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function() {
			window.close();
		});
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var dzDjxh = trim($("#mainForm_domain_dzDjxh").val());
			var jsYj = trim($("#mainForm_domain_jsYj").val()); 
			var dzje = trim($("#mainForm_domain_dzje").val()); 
			var dzCybz = trim($("#mainForm_domain_dzCybz").val()); 
			var dzcyje = trim($("#mainForm_domain_dzcyje").val());

			var url = jcontextPath+"/hygl/xyjszbyjdz!save";  
	    	var jsonObj = {"domain.dzDjxh":dzDjxh,"domain.jsYj":jsYj,"domain.dzje":dzje,"domain.dzCybz":dzCybz,"domain.dzcyje":dzcyje};   			
			ajaxCommon(url,jsonObj,"saveSuc");
		});
		//��ȡ���
		$("#mainForm_domain_dzje").focus();
	});


	//spgsbm ������˾���룬Ĭ��Ϊ��ǰ����Ա���ڹ�˾�����Ƿ��ñ�������Ƚ����⣬Ϊ���˵�λ����
	function scSendzd(wsDm,xh,wsXmflDjxh,spgsbm){  
		 if (spgsbm == undefined || spgsbm == null) {
			spgsbm = "";
		 }
		  var sprCzyDjxh="",oldWsspxh="";
	      var url = jcontextPath+"/common/wsspCommon!scSend"; 
	   	  var jsonObj = {"domain.wsDm":wsDm,"domain.wsXmflDjxh":wsXmflDjxh,"domain.ywDjxh":xh,"domain.sprCzyDjxh":sprCzyDjxh,"domain.oldWsspxh":oldWsspxh};
	      ajaxCommon(url,jsonObj,"doScSendSuc",false);
	}
	function doScSendSuc(data) {
		hideMessage();
		showAlert("���ͳɹ���");
	}
	
	function saveSuc(data) {
		if(data.domain.sfsp=="Y" && data.domain.zdsp=="Y"){
			var wsDm="306001"; //�ְ����½���˱�
			scSendzd(wsDm,data.domain.dzDjxh,"","");
			alert("������ɣ��Զ����ͳɹ���");
		}else{
			alert("���˳ɹ���");
		}
		window.close();
	}
	
	function checkdata(){
		var controlNameArray = ["domain.jsYj","domain.dzje","domain.dzcyje"];
		var labelNameArray = ["����_δ��","���˽��","������"];
		var compareValueArray = [16.2,16.2,16.2];
		var nodeTypeArray = [NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL];
		var notNullArray = [true,true,true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	
	//�������
	function jscy(){
		var jsYj = trim($("#mainForm_domain_jsYj").val());
		var dzje = trim($("#mainForm_domain_dzje").val()); 
		var cyje = parseFloat(jsYj) - parseFloat(dzje);
		if(cyje > 0){
			$("#mainForm_domain_dzCybz").val("Y");
		}else if(cyje == 0){
			$("#mainForm_domain_dzCybz").val("N");
		}
		$("#mainForm_domain_dzcyje").val(cyje);
	}
	
</script>
</head>

<body>
<%try{ %>
<s:form action="xyjszbyjdz!initMx" namespace="/wlgl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.dzDjxh"/>
	<div class="pop_contc">
		<fieldset>
			<legend>������Ϣ</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td align="right">�ɳ����ţ�</th>
      				<td>
      					<s:textfield name="domain.pcdh" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
      				</td>
      				<td align="right">�ɳ����ڣ�</th>
      				<td>
      					<input type="text" name="domain.pcrq" value="<s:date name='domain.pcrq' format='yyyy-MM-dd' />" class="pop_input noborder bgstyle_readonly" />
      				</td>
      			</tr>
      			<tr>
      				<td align="right">�������ƣ�</th>
      				<td>
      					<s:textfield name="domain.hwmc" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      			
      				<td align="right">�ְ��̷��ࣺ</th>
      				<td>
      					<s:textfield name="domain.zrbmmc" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">�ְ��̣�</th>
      				<td>
      					<s:textfield name="domain.fbsmc" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right"><font class="font_red">*</font>����_δ�᣺</th>
      				<td>
      					<s:textfield name="domain.jsYj" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">���˽�</th>
      				<td>
      					<s:textfield name="domain.dzje" onchange="jscy();" cssClass="pop_input noborder bgstyle_required" ></s:textfield>
      					<input type="hidden" id="mainForm_domain_dzCybz" value='<s:property value="domain.dzCybz"/>'/>
      				</td>
      				<td align="right">���ʲ����</th>
      				<td>
      					<s:textfield name="domain.dzcyje" id="mainForm_domain_dzcyje" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
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
