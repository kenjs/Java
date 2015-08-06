<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>���뿪Ʊ����</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		initJe();
		$("#addBtn").click(function(){
		
		//	var djJgbm = trim($("#mainForm_domain_djJgbm").val()); 
		//	var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
			var existBz = trim($("#mainForm_domain_existBz").val()); 
			var kpsqDjxh=$("#mainForm_domain_kpsqDjxh").val();
			var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
			var url = jcontextPath+"/hygl/jskpsq!querySrKpMxInit.action?domain.khDjxh="+khDjxh+"&domain.kpsqDjxh="+kpsqDjxh;
			var flag=window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:860px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:no;maximize:no;")
			var val=$("#mainForm_domain_flag").val();
			//alert(val)
			if(flag!=''&&flag!=undefined){
				if(val==''){
					$("#mainForm_domain_flag").val(flag);
				}
				else{
					$("#mainForm_domain_flag").val(val+flag);
				}
			}
			initMx();
			var val=$("#mainForm_domain_flag").val();
			if(val!=''){
				window.returnValue=val+"="+kpsqDjxh;
			}
			
			//popwindow(url, 760, 488);	
		});
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var kpsqfsDm = trim($("#mainForm_domain_kpsqfsDm").val()); 
			var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
			var sqKpjeHj = trim($("#hjje").val()); 
			var sqKprq = trim($("#mainForm_domain_sqKprq").val()); 
			var bzsm = trim($("#mainForm_domain_bzsm").val()); 
			var djJgbm = trim($("#mainForm_domain_djJgbm").val()); 
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
			var kpsqDjxh = trim($("#mainForm_domain_kpsqDjxh").val()); 
			var tabLen=$("#thisTab tr").length;
			if(tabLen==0){
				showError("�����һ�����뿪Ʊ��Ϣ��");
				return;
			}
			if(parseFloat(sqKpjeHj)==0){
				showError("���뿪Ʊ����Ϊ0��");
				return;
			}
			var xhs=$(":checkbox[name='xhs']");
			var je=new Array();
			var ywDjxh=new Array();
			var jsonStr="";
			for(var i=0;i<tabLen;i++){
				var jeVal=$(".je")[i].innerText;
				var ywDjxhVal=xhs[i].value;
				je[i]=jeVal;
				ywDjxh[i]=ywDjxhVal;
			}
			
			var shf = trim($("#mainForm_domain_shf").val()); 
			var shfSbh = trim($("#mainForm_domain_shfSbh").val()); 
			var fhf = trim($("#mainForm_domain_fhf").val()); 
			var fhfSbh = trim($("#mainForm_domain_fhfSbh").val()); 
			var ydrq = trim($("#mainForm_domain_ydrq").val()); 
			var dj = trim($("#mainForm_domain_dj").val()); 
			var mc = trim($("#mainForm_domain_mc").val()); 
			var sl = trim($("#mainForm_domain_sl").val()); 
			var dkf = trim($("#mainForm_domain_dkf").val()); 
			var kpDwJgMc = trim($("#mainForm_domain_kpDwJgMc").val()); 
			
			jsonStr=getJqueryParamZdy(ywDjxh,"domain.ywDjxhStr");
			jsonStr+=getJqueryParamZdy(je,"domain.jeStr");
			var url = jcontextPath+"/hygl/jskpsq!save";  
	    	var jsonObj = {"domain.kpsqfsDm":kpsqfsDm,"domain.khDjxh":khDjxh,"domain.sqKpjeHj":sqKpjeHj,
                           "domain.sqKprq":sqKprq,"domain.bzsm":encodeURI(bzsm),"domain.djJgbm":djJgbm,
                           "domain.ssJgbm":ssJgbm,"domain.kpsqDjxh":kpsqDjxh,"domain.shf":shf,"domain.shfSbh":shfSbh,
                           "domain.fhf":fhf,"domain.fhfSbh":fhfSbh,"domain.ydrq":ydrq,"domain.dj":dj,"domain.mc":mc,
                           "domain.sl":sl,"domain.dkf":dkf,"domain.kpDwJgMc":kpDwJgMc};   		
	    	jsonStr+=jQuery.param(jsonObj);
			ajaxCommon(url,jsonStr,"doSaveYkpSuc",false);
		});
		$("#deleteBtn").click(function(){
			var xhs = $(":checked[name='xhs']");
			if (xhs.length <= 0) {
				showAlert("����ѡ����Ҫɾ���Ŀ�Ʊ���������嵥��Ϣ��");
				return;
			}
			
			showConfirm("ȷ��Ҫɾ��ѡ�е����뿪Ʊ��Ϣ��", "delKpsqDzQd");
		});
		$("#closeBtn").click(function(){
			window.close();
		});
	});
	
	function getJqueryParamZdy(obj, paraName) {
		var data = "";
		$.each(obj, function(i){
			data += paraName + "[" + i + "]=" + encodeURI(obj[i]) + "&"; 
		});
		
		return data;
	}
	
	function initJe(){
		var length=$("#thisTab tr").length;
		var zje=0;
		for(var i=0;i<length;i++){
			var je=$(".je")[i].innerText;
			je=parseFloat(je);
			zje+=je;
		}
		$("#hjje").val(zje);
	}
	
	function doSaveYkpSuc(data) {
		hideMessage();
		//showSuccess("����ɹ���","doYesCallBack");
		showSuccess("����ɹ���","saveSucYesCallBack");
	}
	function saveSucYesCallBack() {
		//window.returnValue="";
		window.close();
	}
	function onUpdateDzqd(kpsqmxDjxh){
		var existBz = trim($("#mainForm_domain_existBz").val()); 
		var kpsqDjxh = $("#mainForm_domain_kpsqDjxh").val();
		var url = jcontextPath+"/hygl/jskpsq!queryMx.action?domain.kpsqDjxh="+kpsqDjxh+"&domain.existBz="+existBz+"&domain.kpsqmxDjxh="+kpsqmxDjxh+"&domain.dzQdXgbz=Y";
		
		window.showModalDialog(url,window,"dialogHeight:360px;dialogWidth:560px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:no;maximize:no;")
		initMx();
	}
	function checkdata(){
		var controlNameArray = ["domain.sqKpjeHj","domain.sqKprq","domain.bzsm",""];
		var labelNameArray = ["���뿪Ʊ���","���뿪Ʊ����","��ע˵��"];
		var compareValueArray = [16.2,10,200];
		var nodeTypeArray = [NodeType.DECIMAL,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	function checkJs(obj) {
		$(":checkbox[name='xhs']").attr("checked", obj.checked);
	}
	function initMx() {
		$("#mainForm").attr("action", "jskpsq!initMx");
		mainForm.submit();
	}
	
	function delKpsqDzQd() {
		var kpsqDjxh = trim($("#mainForm_domain_kpsqDjxh").val()); 
		var xhs = $(":checked[name='xhs'][value!='']");
		
		var checkBoxList=$(":checkbox[name='xhs']");
		var jsonStr="";
		var je=new Array();
		var ywDjxh=new Array();
		var j=0;
		$.each(checkBoxList,function(i,obj){
			if(obj.checked){
				var jeVal=$(".je")[i].innerText;
				var ywDjxhVal=obj.value;
				je[j]=jeVal;
				ywDjxh[j]=ywDjxhVal;
				j++;
			}
		});
		if (xhs.length > 0) {
		    jsonStr = getJqueryParamZdy(ywDjxh, "domain.ywDjxhStr");
			jsonStr+= getJqueryParamZdy(je, "domain.jeStr");
			var jsonObj = {"domain.kpsqDjxh":kpsqDjxh};
			jsonStr += jQuery.param(jsonObj);
			var url = jcontextPath+"/hygl/jskpsq!deleteSrKpMx";  
			ajaxCommon(url,jsonStr,"doDelKpsqDzQdxxSuc", false);
		}
	}
	
	function doDelKpsqDzQdxxSuc(){ 
		showSuccess("ɾ���ɹ���","initMx");
	}
</script>
<base target="_self" />
</head>

<body>
<%try{ %>
<s:form action="jskpsq!initMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
		<s:hidden name="domain.kpsqDjxh"></s:hidden>
		<s:hidden name="domain.djJgbm"></s:hidden>
		<s:hidden name="domain.ssJgbm"></s:hidden>
		<s:hidden name="domain.existBz"></s:hidden>
		<s:hidden name="domain.kpsqfsDm"></s:hidden>
		<s:hidden name="domain.flag"></s:hidden>
		<s:hidden name="domain.khDjxh"></s:hidden>
		<div class="pop_contc">
			<fieldset>
				<legend>������Ϣ</legend>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
					<tr>
						<td width="20%" align="right"><font class="font_red">*</font>����������</td>
	      				<td width="30%">
	      					<s:textfield name="domain.dwMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      				<td width="20%" align="right"><font class="font_red">*</font>�Ǽǲ��ţ�</td>
	      				<td width="30%">
	      					<s:textfield name="domain.bmMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      			</tr>	      			
	      			</tr>
	      		</table>
	      	</fieldset>
	      			<fieldset>
						<legend>��Ʊ��Ϣ</legend>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
			      			<tr>
				      			<td align="right" width="20%"><font class="font_red">*</font>���뿪Ʊ��</td>
			      				<td width="30%">
			      					<s:textfield id="hjje" name="domain.sqKpjeHj" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
			      				</td>
			      				<td align="right" width="20%"><font class="font_red">*</font>���뿪Ʊ���ڣ�</td>
			      				<td width="30%">
			      					<s:textfield name="domain.sqKprq" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
			      				</td>
			      			</tr>
			      			<tr>
			      				<td align="right">�ջ�����</td>
			      				<td>
			      					<s:textfield name="domain.shf" cssClass="pop_input noborder bgstyle_required"></s:textfield>
			      				</td>
			      				<td align="right">�ջ���ʶ��ţ�</td>
			      				<td>
			      					<s:textfield name="domain.shfSbh" cssClass="pop_input noborder bgstyle_required"></s:textfield>
			      				</td>
			      			</tr>
			      			<tr>
			      				<td align="right">��������</td>
			      				<td>
			      					<s:textfield name="domain.fhf" cssClass="pop_input noborder bgstyle_required"></s:textfield>
			      				</td>
			      				<td align="right">������ʶ��ţ�</td>
			      				<td>
			      					<s:textfield name="domain.fhfSbh" cssClass="pop_input noborder bgstyle_required"></s:textfield>
			      				</td>
			      			</tr>			      			
			      			<tr>
			      				<td align="right">Ԥ�����ڣ�</td>
			      				<td>
			      					<s:textfield name="domain.ydrq" readonly="true" cssClass="ymdate"></s:textfield>			      					
			      				</td>
			      				<td align="right"><font class="font_red">*</font>��Ʊ��λ��</td>
			      				<td>
			      					<s:textfield name="domain.kpDwJgMc" cssClass="pop_input noborder bgstyle_optional"></s:textfield>
			      				</td>
			      			</tr>
			      			<tr>			      				
			      				<td align="right">�ֿ۷���</td>
			      				<td>
			      					<s:select list="{'�ջ���','������' }" cssClass="select" name="domain.dkf"></s:select>
			      				</td>
			      				<td align="right">���ۣ�</td>
			      				<td>
			      					<s:textfield name="domain.dj" cssClass="pop_input noborder bgstyle_optional"></s:textfield>
			      				</td>	
			      			</tr>
			      			<tr>
			      				<td align="right">���ƣ�</td>
			      				<td>
			      					<s:textfield name="domain.mc" cssClass="pop_input noborder bgstyle_optional"></s:textfield>
			      				</td>
			      				<td align="right">������</td>
			      				<td>
			      					<s:textfield name="domain.sl" cssClass="pop_input noborder bgstyle_optional"></s:textfield>
			      				</td>
			      			</tr>			      	
			      			<tr>
			      				<td align="right">��ע˵����</td>
			      				<td colspan="3">
			      					<s:textarea name="domain.bzsm" rows="3" cssClass="pop_textarea noborder bgstyle_optional" ></s:textarea>
			      				</td>
			      			</tr>
			      			<tr>
			      				<td align="right"><font class="font_red">*</font>�Ǽ��ˣ�</td>
			      				<td>
			      					<s:textfield name="domain.cjrMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
			      				</td>
			      				<td align="right"><font class="font_red">*</font>�Ǽ����ڣ�</td>
			      				<td>
			      					<s:textfield name="domain.djrq" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
			      				</td>
			      			</tr>
						</table>
					</fieldset>
			<br />
			<table id="zbTab" width="850" border="0" align="center" cellspacing="0" cellpadding="0" class="poptab_css">
		      <tr>
		      	<th width="30">���</th>
		      	<th width="30"><input type="checkbox" name="jsCheck" onclick="checkJs(this)"/></th>
		        <th width="80">���뿪Ʊ���</th>
		        <th width="160">�˷ѽ��㷽����</th>
		        <th width="80">��ĿС������</th>
		        <th width="85">��Դ</th>
		        <th width="85">��������</th>
		        <th width="320">˵��</th>
		       
		      </tr>
		      <tbody id="thisTab">
		      <s:iterator value="domain.srKpList" id="dzqd" status="sta">
		        <tr>
			      	<td align="center" class="bh"><s:property value="#sta.index+1" /></td>
		      		<td align="center"><input type="checkbox" name="xhs" value="<s:property value="#dzqd.ysyfDjxh" />" /></td>
			        <td align="center" class="je">
				        <s:property  value="#dzqd.ysfJe"/>
			        </td>
			        <td align="center"><s:property value="#dzqd.khMc" /></td>
			        <td align="center"><s:property value="#dzqd.kmxlMc" /></td>
			        <td align="center"><s:property value="#dzqd.ysyflyMc" /></td>
			        <td align="center"><s:property value="#dzqd.csrq" /></td>
			        <td align="center"><s:property value="#dzqd.sm" /></td>
			      </tr>
		      </s:iterator>
		      </tbody>
		    </table>
			<div class="pop_btn">
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
