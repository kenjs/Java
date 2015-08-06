<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>�������</title>

<style type="text/css">
html,body {background:none;}
.hiddenCss {display: none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
	var closeFlag = true;
	$(function(){
		initDzcyList();
		window.onbeforeunload = function() { 
			if(!closeFlag){			
				return "ɾ�������������±��棡�������Ĳ��������ȷ�Ϲر�ô��";
			}
		} 	
		$("#addDzBtn").click(function(){
			onAddDzCy();
		});
		$("#delDzBtn").click(function(){
			var xhs = $(":checked[name='xhs']");
			if (xhs.length <= 0) {
				showAlert("����ѡ����Ҫɾ���Ķ��˲��죡");
				return;
			}
			
			showConfirm("ɾ�����������޸Ķ��˽����±��棡ȷ��Ҫɾ��ѡ�м�¼��", "delDzcy");
		});
		$("#closeBtn").click(function(){
			window.close();
		});
		$("#saveBtn").click(function(){
			if(!checkdata()) {
				return;
			}
			if (!checkDzcy()) {
				return;
			}
			var jsDjxh = trim($("#mainForm_domain_jsSrdzDomain_jsDjxh").val()); 
			var xgbz = trim($("#mainForm_domain_xgbz").val()); 
			var dzDjxh = trim($("#mainForm_domain_jsSrdzDomain_dzDjxh").val()); 
			var ssJgbm = trim($("#mainForm_domain_jsSrdzDomain_ssJgbm").val()); 
			var dzfsDm = trim($("#mainForm_domain_jsSrdzDomain_dzfsDm").val());
			
			var jsSr = trim($("#mainForm_domain_jsSrdzDomain_jsSr").val());
			var jsYj = trim($("#mainForm_domain_jsSrdzDomain_jsYj").val());
			var jsWj = trim($("#mainForm_domain_jsSrdzDomain_jsWj").val()); 
			var dzje = trim($("#mainForm_domain_jsSrdzDomain_dzje").val()); 
			var dzcyje = trim($("#mainForm_domain_jsSrdzDomain_dzcyje").val()); 
			
			var xhs = $(".xhs");
			var cyjes = $(":input[name='cyjes']");
			var dzcyyyDm = $("select[name='dzcyyyDm']");
			var dzcyClfsDm = $("select[name='dzcyClfsDm']");
			var wlssDjxh = $("select[name='wlssDjxh']");
			var bzs = $(":input[name='bzs']");
			var xcJsDjxh = $("input[name='xcJsDjxh']");
			closeFlag=true;

			var jsonStr = getJqueryParam(xhs, "domain.xhs") + getJqueryParam(cyjes, "domain.cyjes") + getJqueryParam(dzcyyyDm, "domain.dzcyyyDm")+
						getJqueryParam(dzcyClfsDm, "domain.dzcyClfsDm") + getJqueryParam(wlssDjxh, "domain.wlssDjxh") + 
						getJqueryParam(bzs, "domain.bzs") + getJqueryParam(xcJsDjxh, "domain.xcJsDjxh");
			//alert(jsonStr);	
			var url = jcontextPath+"/hygl/jsglsrdz!save";  
	    	var jsonObj = {"domain.jsSrdzDomain.dzDjxh":dzDjxh,"domain.xgbz":xgbz,"domain.jsSrdzDomain.jsDjxh":jsDjxh,
                           "domain.jsSrdzDomain.jsWj":jsWj,"domain.jsSrdzDomain.dzje":dzje,"domain.jsSrdzDomain.dzcyje":dzcyje,
                           "domain.jsSrdzDomain.ssJgbm":ssJgbm,"domain.jsSrdzDomain.dzfsDm":dzfsDm}; 
	    	jsonStr += jQuery.param(jsonObj);
	    	showMessage();
			ajaxCommon(url,encodeURI(jsonStr),"doSaveSrDzSuc",false);
		});
		
		$("#sendBtn").click(function(){
			var wsDm="305001";//������ʲ�����˱�
			var jsDjxh = $("#mainForm_domain_jsSrdzDomain_jsDjxh").val();
			var oldWsspxh = $("#mainForm_domain_jsSrdzDomain_wsSpxh").val();
			scSend(wsDm,"",jsDjxh,oldWsspxh);
		});
		
		$("#mainForm_domain_jsSrdzDomain_dzje").change(function(){
			var wj = $("#mainForm_domain_jsSrdzDomain_jsWj").val();
			var dzcyje = wj/1 - this.value/1;
			$("#mainForm_domain_jsSrdzDomain_dzcyje").val(dzcyje.toFixed(2));
		});
		$("select[name='dzcyClfsDm']").change(function(){
			wlssDjxhInit();
		});
	});
	function wlssDjxhInit(){
		var trs = $("#cyTbody tr");
		var clfsDmObjs = $("select[name='dzcyClfsDm']");
		for (var i=0; i<trs.length; i++) {
			var td5 = $("td:eq(5)",$(trs[i]));
			//alert(clfsDmObjs[i].value);
			if(clfsDmObjs[i].value!="32"){
				td5.html("<select class=\"select wlssDjxh\"><option value =\"\">--��--</option></select>");
			}else{
				td5.html($("#wlssDivId").html());
			}
		}
	}
	function doSaveSrDzSuc(data) {
		var xgbz = data.domain.xgbz;
		$("#mainForm_domain_xgbz").val(xgbz);
		hideMessage();
		//showSuccess("����ɹ���","doYesCallBack");
		showSuccess("����ɹ���","saveSucYesCallBack");
	}
	function saveSucYesCallBack() {
		window.close();
	}
	
	function onAddDzCy() {
		var trRows = $("#cyTbody tr").length;
		var tr = $("<tr></tr>");
		var xh = trRows+1;
		var td1 = $("<td align=\"center\" class=\"bh\"></td>");
		$(td1).text(trRows+1).appendTo($(tr));
		$("<td align=\"center\"><input type=\"checkbox\" name=\"xhs\" value=\"\"/></td>").appendTo($(tr));
		$("<td align=\"center\"><input type=\"text\" name=\"cyjes\" class=\"pop_input inputright\" /></td>").appendTo($(tr));
		$("<td align=\"center\"></td>").html($("#dycyyyDivId").html()).appendTo($(tr));
		$("<td align=\"center\" id=\"DzcyClfs"+xh+"\"></td>").html($("#dzcyClfsDivId").html()).appendTo($(tr));
		/*var tdDzcyClfs = $("<td align=\"center\" id=\"DzcyClfs"+xh+"\"></td>");
		var selectDzcyClfs = $("#dzcyClfsDmCsh");
		//alert(selectDzcyClfs);
			selectDzcyClfs.bind("change", function(){wlssDjxhInit();});
			selectDzcyClfs.appendTo(tdDzcyClfs);
		tdDzcyClfs.appendTo($(tr));*/
		$("<td align=\"center\"></td>").html("<select class=\"select wlssDjxh\"><option value =\"\">--��--</option></select>").appendTo($(tr));
		$("<td align=\"center\"><input type=\"text\" name=\"bzs\" class=\"pop_input\" /></td>").appendTo($(tr));
		$("<td align=\"center\" style=\"display: none;\"><input name=\"xcJsDjxh\" value=\"\"/></td>").appendTo($(tr));
		$("<td align=\"center\" style=\"display: none;\"><input class=\"xhs\" value=\"\"/></td>").appendTo($(tr));
		$(tr).appendTo($("#cyTbody"));
		
		DzcyClfsCsh();
	}
	function DzcyClfsCsh(){
		var trRows = $("#cyTbody tr").length;
		var selectDzcyClfs = $("#DzcyClfs"+trRows +" select");
		selectDzcyClfs.bind("change", function(){wlssDjxhInit();});
	}
	// ��ʼ�����˲����б��еĶ��˲���ԭ��ʹ���ʽ����
	function initDzcyList() {
		var trs = $("#cyTbody tr");
		for (var i=0; i<trs.length; i++) {
			var td3 = $("td:eq(3)",$(trs[i]));
			$("#dycyyyDivId select").val(td3.text());
			td3.html($("#dycyyyDivId").html());
			
			var td4 = $("td:eq(4)",$(trs[i]));
			$("#dzcyClfsDivId select").val(td4.text());
			td4.html($("#dzcyClfsDivId").html());
			
			var td5 = $("td:eq(5)",$(trs[i]));
			var wlssDjxh = td5.text();
			var xcJsDjxh = $("input[name='xcJsDjxh']")[i].value;
			if(xcJsDjxh!=""&&xcJsDjxh!=null){
				td5.html("<select class=\"select wlssDjxh\"><option value =\"\">--��--</option></select>");
			}else{
				$("#wlssDivId select").val(wlssDjxh);
				td5.html($("#wlssDivId").html());
			}
			
			
		}
		
		// ������ֵ������Ϊ��
		$("#dycyyyDivId select").val("");
		$("#dzcyClfsDivId select").val("");
	}
	
	function checkJs(obj) {
		$(":checkbox[name='xhs']").attr("checked", obj.checked);
	}
	
	function checkdata(){
		var controlNameArray = ["domain.jsSrdzDomain.dzje","cyjes"];
		var labelNameArray = ["���ʽ��","������"];
		var compareValueArray = [14.2,14.2];
		var nodeTypeArray = [NodeType.DECIMAL,NodeType.DECIMAL];
		var notNullArray = [true,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	
	function checkDzcy() {
		var dzje = $("#mainForm_domain_jsSrdzDomain_dzje").val();
		if (dzje == "" || dzje/1 == 0) {
			showAlert("���˽���Ϊ�գ����ұ������0��");
			return false;
		}
		
		var cyjes = $("input[name='cyjes']");
		var dzcyyyDm = $("select[name='dzcyyyDm']");
		var dzcyClfsDm = $("select[name='dzcyClfsDm']");
		var cyjeHj = 0.00;
		
		var trs = $("#cyTbody tr");
		for (var i=0; i<trs.length; i++) {
			if (cyjes[i].value == "" && dzcyyyDm[i].value == "" && dzcyClfsDm[i].value == "") {
				continue;
			}
			if (cyjes[i].value/1 == 0 && (dzcyyyDm[i].value != "" || dzcyClfsDm[i].value != "")) {
				objForFocus = $(cyjes[i]);
				showAlert("�����������Ϊ0��", "focusSel");
				return false;
			}
			cyjeHj += cyjes[i].value/1;
			if ((cyjes[i].value != "" && cyjes[i].value/1 != 0) && dzcyyyDm[i].value == "") {
				objForFocus = $(dzcyyyDm[i]);
				showAlert("������ԭ�򡿲���Ϊ�գ�", "focusSel");
				return false;
			}
			if ((cyjes[i].value != "" && cyjes[i].value/1 != 0) && dzcyClfsDm[i].value == "") {
				objForFocus = $(dzcyClfsDm[i]);
				showAlert("������ʽ������Ϊ�գ�", "focusSel");
				return false;
			}
			
		}
		var dzcyje = $("#mainForm_domain_jsSrdzDomain_dzcyje").val();
		if (cyjeHj != Math.abs(dzcyje)) {
			showAlert("�����˲�����Ϣ���еĲ���������Ӧ�������һ�£����飡");
			return false;
		}
		
		return true;
	}
	
	function delDzcy() {
		var dzDjxh = trim($("#mainForm_domain_jsSrdzDomain_dzDjxh").val()); 
		var xhs = $(":checked[name='xhs'][value!='']");
		var xhsAll = $(".xhs");
		//alert(xhs.length);
		if (xhs.length > 0) {
			var jsonStr = getJqueryParam(xhs, "domain.xhs")+ getJqueryParam(xhsAll, "domain.xhsAll");
			var jsonObj = {"domain.jsSrdzDomain.dzDjxh":dzDjxh,"domain.xgbz":"Y"};
			jsonStr += jQuery.param(jsonObj);
			//alert(jsonStr);
			var url = jcontextPath+"/hygl/jsglsrdz!deleteMx";  
			ajaxCommon(url,encodeURI(jsonStr),"delSuc", false);
		}else {
			delSuc(-1);
		}
	}
	function delSuc(obj){
		var checks = $(":checked[name='xhs']");
		$.each(checks, function(i, obj){
			$(obj).parents("tr").remove();
		});
		if(obj!=-1){
			closeFlag = false;
			//$("#mainForm_domain_jsSrdzDomain_dzcyje").val(obj.domain.dzcyje)
		}
		changeBh();
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
	function onUpdateDzcy(jsDjxh,xh){
		var xgbz = trim($("#mainForm_domain_xgbz").val()); 
		var url = jcontextPath+"/hygl/jsglsrdz!queryMx.action?domain.dzcyDomain.jsDjxh="+jsDjxh+"&domain.xgbz="+xgbz+"&domain.dzcyDomain.xh="+xh;
		window.showModalDialog(url,window,"dialogHeight:340px;dialogWidth:520px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:no;maximize:no;")
		initMx();
		//popwindow(url, 760, 488);	
	}
	function initMx() {
		$("#mainForm").attr("action", "jsglsrdz!initMx");
		mainForm.submit();
	}
	
	function checkdataDzcy(){
		var controlNameArray = ["domain.dzcyDomain.dzcyje",
		                        "domain.dzcyDomain.dzcyyyDm","domain.dzcyDomain.dzcyClfsDm","domain.dzcyDomain.bz",
		                        "domain.dzcyDomain.xcJsDjxh","domain.dzcyDomain.wlssDjxh"];
		var labelNameArray = ["���ʲ�����",
		                      "���ʲ���ԭ��","���ʲ��촦��ʽ","��ע˵��",
		                      "���ν���Ǽ����","������ʧ�Ǽ����"];
		var compareValueArray = [14.2,
			                     2,2,200,
			                     20,20];
		var nodeTypeArray = [NodeType.DECIMAL,
			                 NodeType.STRING,NodeType.STRING,NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,
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
<s:form action="jsglsrdz!initMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.jsSrdzDomain.dzDjxh"></s:hidden>
	<s:hidden name="domain.jsSrdzDomain.jsDjxh"></s:hidden>
	<s:hidden name="domain.xgbz"></s:hidden>
	<s:hidden name="domain.jsSrdzDomain.ssJgbm"></s:hidden>
	<s:hidden name="domain.jsSrdzDomain.dzfsDm"></s:hidden>
		<div class="pop_contc">
			<fieldset>
				<legend>������Ϣ</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
						<tr>
		      				<td width="14%" align="right">�ͻ����ƣ�</td>
		      				<td width="26%">
		      					<s:textfield name="domain.khMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
		      				</td>
		      				<td align="right" width="12%">������ţ�</td>
		      				<td width="16%">
		      					<s:textfield name="domain.ddbh" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
		      				</td>
		      				<td align="right" width="18%">�µ����ڣ�</td>
		      				<td width="14%">
		      					<input type="text" name="domain.xdrq" id="mainForm_domain_xdrq" value="<s:date name="domain.xdrq" format="yyyy-MM-dd" />" class="pop_input noborder bgstyle_readonly" />
		      				</td>
		      			</tr>
		      		</table>
		     </fieldset>
		     <fieldset>
		      	<legend>������Ϣ</legend>
					<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
		      			<tr>
		      				<td align="right" width="14%">δ�᣺</td>
		      				<td width="26%">
		      					<s:hidden name="domain.jsSrdzDomain.jsSr"></s:hidden>
		      					<s:hidden name="domain.jsSrdzDomain.jsYj"></s:hidden>
		      					<s:textfield name="domain.jsSrdzDomain.jsWj" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
		      				</td>
		      				<td align="right" width="12%"><font class="font_red">*</font>���ʽ�</td>
		      				<td width="16%">
		      					<s:textfield name="domain.jsSrdzDomain.dzje" cssClass="pop_input noborder bgstyle_optional"></s:textfield>
		      				</td>
		      				<td align="right" width="18%">�����</td>
		      				<td width="14%">
		      					<s:textfield name="domain.jsSrdzDomain.dzcyje" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
		      				</td>
		      			</tr>
		      		</table>
		     </fieldset>
			
			<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
		    	<tr>
		    		<td align="left" class="bold_font" width="100%">���˲�����Ϣ��</td>
	  			</tr>
		    </table>
			<table id="zbTab" width="790" border="0" cellspacing="0" cellpadding="0" class="poptab_css" style="margin: auto;">
		      <tr>
		      	<th width="30">���</th>
		      	<th width="30"><input type="checkbox" name="jsCheck" onclick="checkJs(this)"/></th>
		        <th width="80">������</th>
		        <th width="100">����ԭ��</th>
		        <th width="120">����ʽ</th>
		        <th width="250">������ʧ</th>
		        <th width="180">��ע˵��</th>
		      </tr>
		      <tbody id="cyTbody">
		      <s:iterator value="domain.jsSrdzcyList" id="dzcy" status="sta">
		      	<tr>
		      		<td align="center" class="bh"><s:property value="#sta.index+1" /></td>
		      		<td align="center"><input type="checkbox" name="xhs" value="<s:property value="#dzcy.xh" />"/></td>
			        <td align="center">
			        	<input type="text" name="cyjes" value="<s:property value="#dzcy.dzcyje" />" class="pop_input inputright" />
			        </td>
			        <td align="center"><s:property value="#dzcy.dzcyyyDm" /></td>
			        <td align="center"><s:property value="#dzcy.dzcyClfsDm" /></td>
			        <td align="center"><s:property value="#dzcy.wlssDjxh" /></td>
			        <td align="left">
			        	<input type="text" name="bzs" value="<s:property value="#dzcy.bz" />" class="pop_input" />
			        </td>
			        <td align="center" style="display: none;"><input name="xcJsDjxh" value="<s:property value="#dzcy.xcJsDjxh" />"/></td>
			        <td align="center" style="display: none;"><input class="xhs" value="<s:property value="#dzcy.xh" />"/></td>
		      	</tr>
		      </s:iterator>
		      </tbody>
		    </table>
		    <div class="pop_btn">
				<button type="button" class="pop_btnbg" id="addDzBtn">��Ӷ��˲���</button>
		 		&nbsp;
		 		<button type="button" class="pop_btnbg" id="delDzBtn">ɾ�����˲���</button>
		 		&nbsp;
			 	<button type="button" class="pop_btnbg" id="saveBtn">�� ��</button>
			 	&nbsp;
			 	<s:if test="domain.sendBz">
			 		<button type="button" class="pop_btnbg" id="sendBtn">����</button>
			 		&nbsp;
			 	</s:if>
			    <button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
		    </div>
		</div>
		
	<div id="dycyyyDivId" class="hiddenCss">
		<sys:QyDzcyyy myName="dzcyyyDm" myClass="select" mcContainDmBz="N" contaisQxz="true"></sys:QyDzcyyy>
	</div>
	<div id="dzcyClfsDivId" class="hiddenCss">
		<sys:DzcyClfs3 myName="dzcyClfsDm" myId="dzcyClfsDmCsh" myClass="select" contaisQxz="true" ></sys:DzcyClfs3>
	</div>
	<div id="wlssDivId" class="hiddenCss">
	 	<s:select list="domain.wlssXlList" name="wlssDjxh" listKey="DM" listValue="MC"
					cssClass="select wlssDjxh"></s:select>
     </div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
