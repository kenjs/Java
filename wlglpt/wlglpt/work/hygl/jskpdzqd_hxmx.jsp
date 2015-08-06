<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>������ϸ</title>

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
			var qdDjxh = $("#mainForm_domain_qdDjxh").val();
			var kpsqDjxh = $("#mainForm_domain_kpsqDjxh").val();
			var ysqKpje = $("#sqKpje").val();//�����뿪Ʊ���
			var sqKpje = $("#mainForm_domain_sqKpje").val();
			var wsqKpJe = $("#mainForm_domain_wsqKpJe").val();
			var bzsm = $("#mainForm_domain_bzsm").val(); 


            var url = jcontextPath+"/hygl/jskpdzqd!save";  
	        var jsonObj = {"domain.qdDjxh":qdDjxh,"domain.kpsqDjxh":kpsqDjxh,"domain.sqKpje":sqKpje,"domain.bzsm":bzsm};
	        var ysqje = parseFloat(ysqKpje);
	        var sqje = parseFloat(sqKpje);
			var wsqJe = parseFloat(wsqKpJe);
	        if(sqje<=ysqje){
	           showAlert("��������С�ڵ����������");
	           return;
	        }else if(sqje>ysqje+wsqJe){
	           showAlert("�������ɴ����������");
	           return;
	        }
	        ajaxCommon(url,jsonObj,"YesSave");
		});	
    })


	function YesSave() {
		showAlert("����ɹ���", "closeWin");
	}
	function closeWin() {
		window.close();
	}
	function checkdata(){
		var controlNameArray = ["domain.sqKpje","domain.bzsm"];
		var labelNameArray = ["���뿪Ʊ���","��ע˵��"];
		var compareValueArray = [16.2,200];
		var nodeTypeArray = [NodeType.DECIMAL,NodeType.STRING];
		var notNullArray = [true,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
<base target="_self" />
</head>

<body>
<%try{ %>
<s:form action="jskpdzqd!initHxMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
<input type="hidden" id="sqKpje" value="<s:property value="domain.sqKpje"/>"/>
		<div class="pop_contc" style="height: 300px">
		<fieldset>
        	  <legend>�����嵥</legend>
        	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="20%" align="right"><font class="font_red">*</font>ѡ����嵥��</td>
     				<td width="80%">
     				    <s:select list="domain.dataList" name="domain.qdDjxh" listKey="qdDjxh" listValue="queryName"  cssClass="pop_input_colspan2  bgstyle_required" disabled = "true"></s:select>
     				</td>
      			</tr>
      			</table>
        </fieldset>
		<fieldset>
		<legend>�嵥��Ϣ</legend>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
     				<td width="25%" align="right">�嵥���ƣ�</td>
     				<td width="25%" >
     				    <s:textfield name="domain.qdmc" cssClass="pop_input bgstyle_readonly" readonly="true"/>
     				</td>
     				<td width="25%" align="right">�ϼƽ�</td>
     				<td width="25%" >
     				    <s:textfield name="domain.heJe" cssClass="pop_input bgstyle_readonly" readonly="true"/>
     				</td>    				
  				    
     			</tr>
     			<tr>
     			    <td align="right">�����뿪Ʊ��</td>
     				<td>
     					<s:textfield name="domain.ysqKpJe" cssClass="pop_input bgstyle_readonly" readonly="true"/>
     				</td>
     				<td align="right">δ���뿪Ʊ��</td>
     				<td>
     				    <s:textfield name="domain.wsqKpJe" cssClass="pop_input bgstyle_readonly" readonly="true"/>
     				</td>
     			</tr>	
		</table>
		</fieldset>
		<fieldset>
		<legend>������Ϣ</legend>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr style="display: none;">
     				<td width="25%" align="right">�嵥��ţ�</td>
     				<td width="25%" >
     				    <s:textfield name="domain.qdDjxh" cssClass="pop_input bgstyle_readonly" readonly="true"/>
     				</td>
     				<td width="25%" align="right">��Ʊ�Ǽ���ţ�</td>
     				<td width="25%" >
     					<s:textfield name="domain.kpsqDjxh"  cssClass="pop_input bgstyle_readonly" readonly="true" ></s:textfield>
     				</td>
     			</tr>
     			<tr>
     				<td width="25%" align="right"><font class="font_red">*</font>������</td>
     				<td width="25%">
                       <s:textfield name="domain.sqKpje" cssClass="pop_input bgstyle_required" />     			
                    </td>
     			</tr>
     			<tr>
     				<td align="right">��ע˵����</td>
     				<td colspan="3">
     					<s:textarea name="domain.bzsm" rows="3" cssClass="pop_textarea_colspan2 bgstyle_optional" ></s:textarea>
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