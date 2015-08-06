<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>

<head>
<title>�칫-����ʱ��</title>
<%@ include file="/common/meta.jsp"%>
<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		 $("#closeBtn").click(function(){
			window.close();
		})
	
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var jgbm = $("#mainForm_domain_jgbm").val(); 
			var yxqQ = $("#mainForm_domain_yxqQ").val(); 
			var yxqZ = $("#mainForm_domain_yxqZ").val(); 
			var url = jcontextPath+"/bggl/bggzsj!checkSave"; 
			var jsonObj = {"domain.jgbm":jgbm,"domain.yxqQ":yxqQ,"domain.yxqZ":yxqZ};
			ajaxCommon(url,jsonObj,"onCheck");
		});
	});
	
	function onCheck(data){
		
		var count = data.domain.count;
		if(count>0){
			showConfirm("��ǰ��Ч����ϵͳ����ά����Ϣ�ص����Ƿ������","onSave");
		}else{
			onSave();
		}
	}
	
	function onSave(){
		var jgbm = trim($("#mainForm_domain_jgbm").val()); 
		var yxqQ = trim($("#mainForm_domain_yxqQ").val()); 
		var yxqZ = trim($("#mainForm_domain_yxqZ").val()); 
		var amSbsjS = trim($("#mainForm_domain_amSbsjS").val()); 
		var amSbsjF = trim($("#mainForm_domain_amSbsjF").val()); 
		var pmSbsjS = trim($("#mainForm_domain_pmSbsjS").val()); 
		var pmSbsjF = trim($("#mainForm_domain_pmSbsjF").val()); 
	
		var url = jcontextPath+"/bggl/bggzsj!save";  
	   	var jsonObj = {"domain.jgbm":jgbm,"domain.yxqQ":yxqQ,"domain.yxqZ":yxqZ,"domain.amSbsjS":amSbsjS,
	                         "domain.amSbsjF":amSbsjF,"domain.pmSbsjS":pmSbsjS,"domain.pmSbsjF":pmSbsjF};   			
		ajaxCommon(url,jsonObj);
	}
	
	function checkdata(){
		var controlNameArray = ["domain.jgbm","domain.yxqQ","domain.yxqZ","domain.amSbsjS",
		                        "domain.amSbsjF","domain.pmSbsjS","domain.pmSbsjF"];
		var labelNameArray = ["��������","��Ч����","��Ч��ֹ","�����ϰ�ʱ��-ʱ",
		                      "�����ϰ�ʱ��-��","�����°�ʱ��-ʱ","�����°�ʱ��-��"];
		var compareValueArray = [16,10,10,2,
			                     2,2,2];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,false,true,
                            true,true,true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="bggzsj!initMx" namespace="/bggl" method="post" id="mainForm" name="mainForm">
		<div class="pop_contc" style="height:220px; overflow:auto;">
		  <fieldset>
		    <legend>������Ϣ</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="25%" align="right"><font class="font_red">*</font>��������</td>
      				<td width="75%">
      					<sys:gsList myId="mainForm_domain_jgbm" myName="domain.jgbm" mcContainDmBz="N" myClass="select"/>
      				</td>
      			</tr>
      			<tr>
      				<td align="right"><font class="font_red">*</font>��Ч����</td>
      				<td>
      					<s:textfield name="domain.yxqQ" cssClass="ymdate" readonly="true"></s:textfield><strong>~</strong> 
      					<s:textfield name="domain.yxqZ" cssClass="ymdate" readonly="true"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right"><font class="font_red">*</font>�����ϰ�ʱ��</td>
      				<td>
      					<s:select list="domain.xsList" cssStyle="width:50px" name="domain.amSbsjS"></s:select>ʱ
      					<s:select list="domain.fzList" cssStyle="width:50px" name="domain.amSbsjF"></s:select>��
      				</td>
      			</tr>
      			<tr>
      				<td align="right"><font class="font_red">*</font>�����°�ʱ��</td>
      				<td>
      					<s:select list="domain.xsList" cssStyle="width:50px" name="domain.pmSbsjS"></s:select>ʱ
      					<s:select list="domain.fzList" cssStyle="width:50px" name="domain.pmSbsjF"></s:select>��
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
