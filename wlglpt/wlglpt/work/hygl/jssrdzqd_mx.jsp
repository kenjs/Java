<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>��������嵥</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		
		var sfKpBz = $("input[name='domain.sfKpBz']:checked").val();
		if(undefined==sfKpBz || null==sfKpBz || ""==sfKpBz) {
			document.getElementsByName('domain.sfKpBz')[0].checked=true;//Ĭ�Ͽ�Ʊ
		}			
		
		$("#addBtn").click(function(){
			var errCode1 = $("#mainForm_domain_errCode1").val();
			var errCode2 = $("#mainForm_domain_errCode2").val();
			//alert(errCode1);
			if(errCode1 /1 > 0){
				showAlert("���嵥�Ѿ���Ʊ���룬������ӡ�");
				return;
			}
			
			if(errCode2 /1 > 0){
				showAlert("���嵥�Ѿ�֧���Ǽǣ�������ӡ�");
				return;
			}
			
			var ywLydm = $("#ywLydm").val();
			var existBz = trim($("#mainForm_domain_existBz").val()); 
			var djJgbm = trim($("#mainForm_domain_djJgbm").val()); 
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
			//var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
			var qdDjxh = $("#mainForm_domain_qdDjxh").val();
			
			if (ywLydm == '1') {
				initSrdz(existBz,djJgbm,ssJgbm,qdDjxh);
			}else if (ywLydm == '2') {
				initFydj(existBz,djJgbm,ssJgbm,qdDjxh);
			}else if (ywLydm == '3') {
				initWlssDj(existBz,djJgbm,ssJgbm,qdDjxh);
			}
			
		});
		
		$("#saveBtn").click(function(){
			var errCode1 = $("#mainForm_domain_errCode1").val();
			var errCode2 = $("#mainForm_domain_errCode2").val();
			//alert(errCode1);
			if(errCode1 /1 > 0){
				showAlert("���嵥�Ѿ���Ʊ���룬���ܱ��档");
				return;
			}
			
			if(errCode2 /1 > 0){
				showAlert("���嵥�Ѿ�֧���Ǽǣ����ܱ��档");
				return;
			}
			
			if(!checkdata()){
				return;
			}
			
			var djJgbm = trim($("#mainForm_domain_djJgbm").val()); 
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
			var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
			var qdmc = trim($("#mainForm_domain_qdmc").val()); 
			var dzqdHzfsDm = $("#mainForm_domain_dzqdHzfsDm").val();
			var heJe = trim($("#mainForm_domain_heJe").val()); 
			
			var qdDjxh = trim($("#mainForm_domain_qdDjxh").val()); 
			var existBz = trim($("#mainForm_domain_existBz").val()); 
			var sfKpBz = $("input[name='domain.sfKpBz']:checked").val();
			var xtcs20212 = $("#mainForm_domain_xtcs20212").val();
			var jsdw = trim($("#mainForm_domain_jsdw").val()); 
			
			var url = jcontextPath+"/hygl/jssrdzqd!save";  
	    	var jsonObj = {"domain.khDjxh":khDjxh,"domain.qdmc":qdmc,"domain.dzqdHzfsDm":dzqdHzfsDm,"domain.existBz":existBz,"domain.sfKpBz":sfKpBz,
                           "domain.heJe":heJe,"domain.djJgbm":djJgbm,"domain.ssJgbm":ssJgbm,"domain.qdDjxh":qdDjxh,"domain.xtcs20212":xtcs20212,
                           "domain.jsdw":jsdw};   			
			ajaxCommon(url,jsonObj,"doSaveDzqdSuc");
		});
		
		$("#deleteBtn").click(function(){			
			var xhs = $(":checked[name='xhs']");
			if (xhs.length <= 0) {
				showAlert("����ѡ����Ҫɾ������������嵥��ϸ��");
				return;
			}
			
			var errCode1 = $("#mainForm_domain_errCode1").val();
			var errCode2 = $("#mainForm_domain_errCode2").val();
			//alert(errCode1);
			if(errCode1 /1 > 0){
				showAlert("���嵥�Ѿ���Ʊ���룬����ɾ����");
				return;
			}
			
			if(errCode2 /1 > 0){
				showAlert("���嵥�Ѿ�֧���Ǽǣ�����ɾ����");
				return;
			}
			
			showConfirm("ȷ��Ҫɾ��ѡ�е���������嵥��ϸ��Ϣ��", "delDzQdMx");
		});
		$("#closeBtn").click(function(){
			window.close();
		});
	});
	
	function initSrdz(existBz,djJgbm,ssJgbm,qdDjxh) {
		var url = jcontextPath+"/hygl/jssrdzqd!queryMxInit.action?domain.qdDjxh="+qdDjxh+"&domain.existBz="+existBz+"&domain.ssJgbm="+ssJgbm;
		window.showModalDialog(url,window,"dialogHeight:540px;dialogWidth:720px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:no;maximize:no;")
		initMx();
	}
	
	function initFydj(existBz,djJgbm,ssJgbm,qdDjxh) {
		var url = jcontextPath+"/hygl/jssrdzqd!queryFydjInit.action?domain.qdDjxh="+qdDjxh+"&domain.existBz="+existBz+"&domain.ssJgbm="+ssJgbm;
		window.showModalDialog(url,window,"dialogHeight:540px;dialogWidth:720px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:no;maximize:no;")
		initMx();
	}
	
	function initWlssDj(existBz,djJgbm,ssJgbm,qdDjxh) {
		var url = jcontextPath+"/hygl/wlssdj!init.action?domain.qdDjxh="+qdDjxh+"&domain.existBz="+existBz+"&domain.ssJgbm="+ssJgbm;
		window.showModalDialog(url,window,"dialogHeight:540px;dialogWidth:720px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:no;maximize:no;")
		initMx();
	}
	
	function doSaveDzqdSuc(data) {
		var existBz = data.domain.existBz;
		$("#mainForm_domain_existBz").val(existBz);
		hideMessage();
		//showSuccess("����ɹ���","doYesCallBack");
		showSuccess("����ɹ���","saveSucYesCallBack");
	}
	function saveSucYesCallBack() {
		window.close();
	}
	
	function checkJs(obj) {
		$(":checkbox[name='xhs']").attr("checked", obj.checked);
	}
	
	function delDzQdMx() {
		var qdDjxh = trim($("#mainForm_domain_qdDjxh").val()); 
		var xhs = $(":checked[name='xhs'][value!='']");
		var existBz = trim($("#mainForm_domain_existBz").val()); 
		if (xhs.length > 0) {
			var jsonStr = getJqueryParam(xhs, "domain.ywDjxhs");
			var jsonObj = {"domain.qdDjxh":qdDjxh,"domain.existBz":existBz};
			
			jsonStr += jQuery.param(jsonObj);
			var url = jcontextPath+"/hygl/jssrdzqd!deleteMx";  
			showMessage();
			ajaxCommon(url,encodeURI(jsonStr),"doDelDzQdMxxxSuc", false);
		}
	}
	
	function doDelDzQdMxxxSuc(){ 
		//delDzcyxxRows();
        //changeBh();
        hideMessage();
        showAlert("ɾ���ɹ���");
        initMx();
	}
	
	function delDzcyxxRows() {
		var hwXhs = $(":checked[name='xhs']");
		$.each(hwXhs, function(i, obj){
			$(obj).parents("tr").remove();
		});
	}
	
	function changeBh() {
		var bhs = $(".bh");
		$.each(bhs, function(i, obj){
			$(obj).text(i+1);
		});
	}
	
	function checkdata(){
		var controlNameArray = ["domain.khDjxh","domain.qdmc",
		                        "domain.heJe","domain.djrq","domain.jsdw"];
		var labelNameArray = ["�ͻ�����","�嵥����",
		                      "���","��������","���㵥λ"];
		var compareValueArray = [16,200,
			                     16.2,10,200];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,
			                 NodeType.DECIMAL,NodeType.STRING,NodeType.STRING];
		var notNullArray = [false,true,
                            true,true,true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	
	function initMx() {
		$("#mainForm").attr("action", "jssrdzqd!initMx");
		mainForm.submit();
	}
		
</script>
<base target="_self" />
</head>

<body>
<%try{ %>
<s:form action="jssrdzqd!initMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
		<s:hidden name="domain.qdDjxh"></s:hidden>
		<s:hidden name="domain.djJgbm"></s:hidden>
		<s:hidden name="domain.ssJgbm"></s:hidden>
		<s:hidden name="domain.dzqdHzfsDm"></s:hidden>
		<s:hidden name="domain.existBz"></s:hidden>
		<s:hidden name="domain.xtcs20212"></s:hidden>
		<s:hidden name="domain.khDjxh"></s:hidden>
		<s:hidden name="domain.errCode1"></s:hidden>
		<s:hidden name="domain.errCode2"></s:hidden>
		
		<div class="pop_contc">
			<fieldset>
				<legend>������Ϣ</legend>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
					<tr>
	      				<td width="15%" align="right"><font class="font_red">*</font>�嵥���ƣ�</td>
	      				<td width="35%">
	      					<s:textfield name="domain.qdmc" cssClass="pop_input noborder bgstyle_required" ></s:textfield>
	      				</td>
	      				<td width="15%" align="right"><font class="font_red">*</font>�Ƿ�Ʊ��</td>
	      				<td width="35%" id="hzfsTd">
	      				 	<s:radio name="domain.sfKpBz" list='#{"Y":"��","N":"��" }' listKey="key" listValue="value"></s:radio>
	      				</td>
	      			</tr>
	      			<tr>
	      				<td align="right"><font class="font_red">*</font>�ϼƽ�</td>
	      				<td>
	      					<s:textfield name="domain.heJe" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      				<td width="15%" align="right">���㵥λ��</td>
	      				<td width="35%">
	      					<s:textfield name="domain.jsdw" cssClass="pop_input noborder bgstyle_required"></s:textfield>	      				
	      				</td>	      				
	      			</tr>
					<tr>
						<td align="right"><font class="font_red">*</font>ҵ��λ��</td>
	      				<td>
	      					<s:textfield name="domain.dwMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      				<td align="right"><font class="font_red">*</font>���ţ�</td>
	      				<td>
	      					<s:textfield name="domain.bmMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      			</tr>
	      			<tr>
	      				<td align="right"><font class="font_red">*</font>�����ˣ�</td>
	      				<td>
	      					<s:textfield name="domain.cjrMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      				<td align="right"><font class="font_red">*</font>�������ڣ�</td>
	      				<td>
	      					<s:textfield name="domain.djrq" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      			</tr>
				</table>
			</fieldset>
			<br />
			<table id="zbTab" width="980" border="0" cellspacing="0" cellpadding="0" class="poptab_css">
		      <tr>
		      	<th width="30">���</th>
		      	<th width="30"><input type="checkbox" name="jsCheck" onclick="checkJs(this)"/></th>
		      	<th width="60">��Դ</th>
		      	<th width="60">���˽��</th>
		      	<th width="70">δ��</th>
		        <th width="60">������</th>
		        <th width="50">���</th>
		        <th width="70">�������</th>
		        <th width="70">�µ�����</th>
		        <th width="100">��������</th>
		        <th width="50">��װ</th>
		        <th width="80">�ص����</th>
		        <th width="50">ʼ����</th>
		        <th width="50">Ŀ�ĵ�</th>
		        <th width="50">����</th>
		        <th width="50">����</th>
		        <th width="50">���</th>
		      </tr>
		      <s:iterator value="domain.dataList" id="domain" status="sta">
		      	<tr>
	      			<td align="center" class="bh"><s:property value="#sta.index+1" /></td>
		      		<td align="center"><input type="checkbox" name="xhs" value="<s:property value="#domain.ywDjxh" />" /></td>
		      		<td align="center">
		      			<s:if test='1 == #domain.ywLydm'>�������</s:if>
		      			<s:elseif test="2 == #domain.ywLydm">���õǼ�</s:elseif>
		      			<s:elseif test="3 == #domain.ywLydm">������ʧ</s:elseif>
		      		</td>
		      		<td align="center"><s:property value="#domain.dzje" /></td>
		      		<td align="center"><s:property value="#domain.jsWj" /></td>
		      		<td align="center"><s:property value="#domain.dzcyje" /></td>
		      		<td align="center">
		      		<s:if test='"Y"==#domain.dzcybz'>
		      			<font color="red">�в���</font>
		      		</s:if>
		      		<s:else>&nbsp;</s:else>
		      		</td>
		      		<td align="center"><s:property value="#domain.ddbh" /></td>
		      		<td align="center"><s:property value="#domain.xdrq" /></td>
		      		<td align="center"><s:property value="#domain.hwmc" /></td>
		      		<td align="center"><s:property value="#domain.bz" /></td>
		      		<td align="center"><s:property value="#domain.hdbh" /></td>
		      		<td align="center"><s:property value="#domain.sfd" /></td>
		      		<td align="center"><s:property value="#domain.mdd" /></td>
		      		<td align="center"><s:property value="#domain.sl" /></td>
		      		<td align="center"><s:property value="#domain.zl" /></td>
		      		<td align="center"><s:property value="#domain.tj" /></td>
		      	</tr>
		      </s:iterator>
		    </table>
			<div class="pop_btn">
				��Դ��<s:select id="ywLydm" name="ywLydm" list="#{'1':'�������','2':'���õǼ�','3':'������ʧ' }"></s:select>
				<button type="button" class="pop_btnbg" id="addBtn">�� ��</button>
			 	&nbsp;
			 	<button type="button" class="pop_btnbg" id="deleteBtn">ɾ ��</button>
			 	&nbsp;
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
