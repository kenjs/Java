<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>��������嵥����</title>

<style type="text/css">
html,body {background:none;}
.in {width:100%;height:18px;border:0;background:transparent;text-align:center;}
</style>
<base target="_self" />
<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		});
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var qdDjxh = $("#qdDjxh").val();
			if(qdDjxh==""||qdDjxh==null){
				showAlert("�嵥����ѡ��");
				return;
			}
			var kpsqDjxh = $("#mainForm_domain_kpsqDjxh").val();
			var sqKpje = $("#sqKpje").val();
			var wsqKpJe = $("#wsqKpJe").val();
			var bzsm = $("#mainForm_domain_bzsm").val(); 
			var xhStr = $("#mainForm_domain_xhStr").val(); 

            var url = jcontextPath+"/hygl/jskpdzqd!save";  
	        var jsonObj = {"domain.qdDjxh":qdDjxh,"domain.kpsqDjxh":kpsqDjxh,"domain.sqKpje":sqKpje,"domain.bzsm":bzsm};
	        
	        var flag = "";
            var strs=xhStr.split(","); //�ַ��ָ�   
            var sqje = parseFloat(sqKpje);
			var wsqJe = parseFloat(wsqKpJe);

			for (i=0;i<strs.length ;i++ ){
			    if(qdDjxh == strs[i]){			       
			      flag = "1";
			    } 
			 }
			 if(flag == "1"){
			    var str = "���嵥�Ѵ��ں�����¼��������������<br />���Ե���嵥���ƽ����޸ġ�";
			    showAlert(str);
			    return;
			 }
			 if(sqje<=0){
	           showAlert("��������С�ڵ��� 0 ��");
	           return;
	        }else if(sqje>wsqJe){
	           showAlert("�������ɴ���δ�����");
	           return;
	        }
			  ajaxCommon(url,jsonObj,"YesSave");	
		});
	})
	function YesSave(obj){
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
	
	function showQdxq(){
	    var qdDjxh = $("#mainForm_domain_qdDjxh").val();
	    doMyRefresh(qdDjxh);
	}
	//ˢ�µ���
	function doMyRefresh(qdDjxh){
	   var kpsqDjxh = $("#mainForm_domain_kpsqDjxh").val(); 
	   var ssJgbm = $("#mainForm_domain_ssJgbm").val(); 
	   var khDjxh = $("#mainForm_domain_khDjxh").val(); 
	   var url = jcontextPath+"/hygl/jskpdzqd!initAddHxMx.action?domain.kpsqDjxh="+kpsqDjxh+"&domain.ssJgbm="+ssJgbm+"&domain.khDjxh="+khDjxh+"&domain.qdDjxh="+qdDjxh;
	   reload.href = url;
	   reload.click();
	}
	//�޸Ķ����嵥
	function onUpdateDzqd(qdDjxh){
	 var kpsqDjxh = $("#mainForm_domain_kpsqDjxh").val();
	 var url = jcontextPath+"/hygl/jskpdzqd!initHxMx.action?domain.kpsqDjxh="+kpsqDjxh+"&domain.qdDjxh="+qdDjxh;
     window.showModalDialog(url,window,"dialogHeight:350px;dialogWidth:450px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
	}
</script>
</head>
<body>
<%try{ %>
<s:form action="jskpdzqd!initAddHxMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.ssJgbm"></s:hidden>
	<s:hidden name="domain.khDjxh"></s:hidden>
	<s:hidden name="domain.kpsqDjxh"></s:hidden>
	<s:hidden name="domain.xhStr"></s:hidden>
	<input type="hidden" id="mainForm_zt"/>
	<div  style="display:none"><a id="reload" href="">reload</a></div>
	<div class="pop_contc" style="height:360px; overflow:auto;">
	<fieldset>
        	  <legend>�����嵥</legend>
        	  <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="20%" align="right"><font class="font_red">*</font>��ѡ���嵥��</td>
     				<td width="80%">
     				    <s:select list="domain.dataList" name="domain.qdDjxh" listKey="qdDjxh" listValue="queryName"  cssClass="pop_input_colspan2  bgstyle_required" onchange="showQdxq()"></s:select>
     				</td>
      			</tr>
      			</table>
    </fieldset>
    <s:if test="domain.dataList.size==0">
    <fieldset>
		<legend>�嵥��Ϣ</legend>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
     				<td width="25%" align="right">�嵥���ƣ�</td>
     				<td width="25%" >
     					<input type="text" class="pop_input bgstyle_readonly" name="qdmc" readonly="readonly" value="<s:property value="#qd.qdmc"/>"/>
     				</td>
     				<td width="25%" align="right">�ϼƽ�</td>
     				<td width="25%" >
     					<input type="text" class="pop_input bgstyle_readonly" name="heJe" readonly="readonly" value="<s:property value="#qd.heJe"/>"/>
     				</td>    				
  				    
     			</tr>
     			<tr>
     			    <td align="right">�����뿪Ʊ��</td>
     				<td>
     					<input type="text" class="pop_input bgstyle_readonly" name=ysqKpJe readonly="readonly" value="<s:property value="#qd.ysqKpJe"/>"/>
     				</td>
     				<td align="right">δ���뿪Ʊ��</td>
     				<td>
     				    <input type="text" class="pop_input bgstyle_readonly" name="wsqKpJe" id="wsqKpJe" readonly="readonly" value="<s:property value="#qd.wsqKpJe"/>"/>
     				</td>
     			</tr>	
		</table>
		</fieldset>
		<fieldset>
		<legend>������Ϣ</legend>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
     				<td width="25%"></td>
     				<td width="25%"></td>
     				<td width="25%"></td>
     				<td width="25%"></td>
     			</tr>
				<tr>
     				<td  align="right"><font class="font_red">*</font>������</td>
     				<td  colspan="3">
                       <input type="text" class="pop_input bgstyle_required" name="sqKpje" id="sqKpje" value="<s:property value="#qd.wsqKpJe"/>"/>     			
                    </td>
     			</tr>
     			<tr>
     				<td align="right">��ע˵����</td>
     				<td colspan="3">
     					<s:textarea name="domain.bzsm" rows="3" cssClass="pop_textarea_colspan2 bgstyle_optional" ></s:textarea>
     				</td>
     			</tr>	
		
				<tr>
     				<td width="25%" align="right" style="display: none;">�嵥��ţ�</td>
     				<td width="25%" style="display: none;">
     				    <input type="text" class="pop_input bgstyle_readonly" id="qdDjxh" name="qdDjxh" readonly="readonly" value="<s:property value="#qd.qdDjxh"/>"/>
     				</td>
     				<td width="25%" align="right" style="display: none;">��Ʊ�Ǽ���ţ�</td>
     				<td width="25%" style="display: none;">
     					<s:textfield name="domain.kpsqDjxh"  cssClass="pop_input bgstyle_readonly" readonly="true" ></s:textfield>
     				</td>
     			</tr>	
		</table>
		</fieldset>
	</s:if>
    <s:iterator id="qd" value="domain.dataList" status="sta">
    <s:if test="domain.qdDjxh==#qd.qdDjxh">
    <fieldset>
		<legend>�嵥��Ϣ</legend>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
     				<td width="25%" align="right" >�嵥���ƣ�</td>
     				<td width="25%" >
     					<input type="text" class="pop_input bgstyle_readonly" name="qdmc" readonly="readonly" value="<s:property value="#qd.qdmc"/>"/>
     				</td>
     				<td width="25%" align="right">�ϼƽ�</td>
     				<td width="25%" >
     					<input type="text" class="pop_input bgstyle_readonly" name="heJe" readonly="readonly" value="<s:property value="#qd.heJe"/>"/>
     				</td>    				
  				    
     			</tr>
     			<tr>
     			    <td align="right">�����뿪Ʊ��</td>
     				<td>
     					<input type="text" class="pop_input bgstyle_readonly" name=ysqKpJe readonly="readonly" value="<s:property value="#qd.ysqKpJe"/>"/>
     				</td>
     				<td align="right">δ���뿪Ʊ��</td>
     				<td>
     				    <input type="text" class="pop_input bgstyle_readonly" name="wsqKpJe" id="wsqKpJe" readonly="readonly" value="<s:property value="#qd.wsqKpJe"/>"/>
     				</td>
     			</tr>	
		</table>
		</fieldset>
		<fieldset>
		<legend>������Ϣ</legend>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
     				<td width="25%"></td>
     				<td width="25%"></td>
     				<td width="25%"></td>
     				<td width="25%"></td>
     			</tr>
				<tr>
     				<td  align="right"><font class="font_red">*</font>������</td>
     				<td  colspan="3">
                       <input type="text" class="pop_input bgstyle_required" name="sqKpje" id="sqKpje" value="<s:property value="#qd.wsqKpJe"/>"/>     			
                    </td>
     			</tr>
     			<tr>
     				<td align="right">��ע˵����</td>
     				<td colspan="3">
     					<s:textarea name="domain.bzsm" rows="3" cssClass="pop_textarea_colspan2 bgstyle_optional" ></s:textarea>
     				</td>
     			</tr>	
		
				<tr>
     				<td width="25%" align="right" style="display: none;">�嵥��ţ�</td>
     				<td width="25%" style="display: none;">
     				    <input type="text" class="pop_input bgstyle_readonly" id="qdDjxh" name="qdDjxh" readonly="readonly" value="<s:property value="#qd.qdDjxh"/>"/>
     				</td>
     				<td width="25%" align="right" style="display: none;">��Ʊ�Ǽ���ţ�</td>
     				<td width="25%" style="display: none;">
     					<s:textfield name="domain.kpsqDjxh"  cssClass="pop_input bgstyle_readonly" readonly="true" ></s:textfield>
     				</td>
     			</tr>
     	</table>	
		</fieldset>
		</s:if>
	
		</s:iterator>
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