<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>����-���ñ���-����鿴</title>

<style type="text/css">
	.fyjeDm{width:72px;}
	.bxjeDm{width:72px;}
	.bzDm{width:250px;}
html,body {background:none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
	$(function(){
		initFlXm();
		//initJe();
		$("#saveBtn").click(function(){
				var len=$("#tab1 tr").length;
				var str='';
				for(var i=0;i<len;i++){
					var fyxm=$(".fyxmDjxh")[i].value;
					if(fyxm==''){
						showError("������Ŀ����Ϊ�գ�");
						return;
					}
					str+=fyxm+",";
				}
				
				if(!checkIsNull()){
					return;
				}
				var url = jcontextPath+"/cwfybxsq!checkXmfl";  
		    	var jsonObj = {"domain.jsonStr":str};   			
				ajaxCommon(url,jsonObj,"toSave");
		});
		
		$("#closeBtn").click(function(){
			window.close();
		})
		
		$("#addBtn").click(function(){
			addBxSq();
		})
		
		$("#deleteBt").click(function(){
			deleteRow();
		})
		
		$("#sendBtn").click(function(){
			var wsDm="100002";//���õǼ�������
			var cwDjxh = $("#mainForm_domain_cwDjxh").val();
			var oldWsspxh = $("#mainForm_domain_wsSpxh").val();
			var xmflDjxh=$("#mainForm_domain_xmflDjxh").val();
			var fyjzDwDjxh=$("#mainForm_domain_jzdw").val();
			//alert(oldWsspxh+"--"+xmflDjxh)
			scSend(wsDm,xmflDjxh,cwDjxh,oldWsspxh,fyjzDwDjxh);
		});
		
		//��ʼ��������Ŀ
		ininXm();
		
		
	});
	
	function ininXm(){
		var tab1=$("#tab1 tr").length;
		for(var i=0;i<tab1;i++){
			var fyLb=$(".fylbDjxh")[i].value;
			getXmByFl("fyfl-xm", fyLb, '', "fyXmName"+i, "fyXmId"+i, "Y", "N");
		}
	}
	
	function getXmByFl(ywid, sj, defaultValue, listName, listId, containQbBz, mcContainDmBz){
		if (sj == null) {
			sj = "";
		}
		 if (defaultValue == null || defaultValue == "") {
			defaultValue = $("#"+listId).val();
		} 
		var jsonObj = {
		        "domain.ywid":ywid,
				"domain.paramdm":sj,
				"domain.defaultValue":defaultValue,
				"domain.currentObjName":listName,
				"domain.currentObjId":listId,
				"domain.containQbBz":containQbBz,
				"domain.mcContainDmBz":mcContainDmBz};
		
		var url=jcontextPath+"/common/wlglptCommon!commonInit";	
		ajaxCommon(url,jsonObj,"changeXmList");
	}
	
	function changeXmList(data){
		var list = data.domain.dataList;
		$("#"+data.domain.currentObjId).empty();
		$.each(list, function(i,domain){
			if(domain.openFlag==null){
				domain.openFlag="";
			}
			
			if(domain.dm==null){
				 var option = $("<option>").text(domain.mc).val(domain.dm);
			}
			else{
				 var option = $("<option>").text(domain.mc+"(��Ŀ���ࣺ"+domain.openFlag+")").val(domain.dm);
			}
		   
		    //Ĭ��ѡ��
		    if(data.domain.defaultValue==domain.dm){
		    	option = $("<option selected='selected'>").text(domain.mc+"(��Ŀ���ࣺ"+domain.openFlag+")").val(domain.dm);
		    }
		    $("#"+data.domain.currentObjId).append(option);
		});
	}
	
	function changeXm(obj){
		return false;
	}
	
	function toSave(data){
		var str=data.domain.tager;
		if(str=='1'){
			var djxh=data.domain.xmflDjxh;
			doSave(djxh);
		}
		else{
			showError("ÿ����ϸ��Ӧ����Ŀ�������һ����");
			return ;
		}
	}
	
	function doSave(djxh){
		var fyjzDwDjxh = trim($("#mainForm_domain_fyjzDwDjxh").val()); 
		var fyzfDwDjxh = trim($("#mainForm_domain_fyzfDwDjxh").val());
		var bz = trim($("#mainForm_domain_bz").val()); 
		var cwDjxh = trim($("#mainForm_domain_cwDjxh").val()); 
		var fybxje = trim($("#bxzje").val()); 
		var len=$("#tab1 tr").length;
		//var tabLen = document.getElementById("zbTab").rows;
		var jsonStr="";
		for(var i=0;i<len;i++){
			var fylb=$(".fylbDjxh")[i].value;
			var fyxm=$(".fyxmDjxh")[i].value;
			var fyje=$(".fyjeDm")[i].value;
			var bxje=$(".bxjeDm")[i].value;
			var mxbz=$(".bzDm")[i].value;
			var xh=$(".mxxh")[i].value;
			if(fyje==''){
				showError("���ý���Ϊ�գ�");
				return ;
			}
			if(fyje<=0){
				showError("���ý���С��0��");
				return ;
			}
			
			if(bxje==''){
				showError("��������Ϊ�գ�");
				return ;
			}
			if(bxje<=0){
				showError("��������С��0��");
				return;
			}
			
			if(xh==''){
				xh="null";
			}
			if(mxbz==''){
				mxbz=" ";
			}
			jsonStr+=fylb+","+fyxm+","+fyje+","+bxje+","+mxbz+","+xh+"-";
		}
		var url = jcontextPath+"/cwfybxsq!save";  
    	var jsonObj = {"domain.fyjzDwDjxh":fyjzDwDjxh,"domain.fyzfDwDjxh":fyzfDwDjxh,"domain.cwDjxh":cwDjxh,"domain.bz":bz
    					,"domain.jsonStr":jsonStr,"domain.fybxje":fybxje,"domain.xmflDjxh":djxh};   			
		ajaxCommon(url,jsonObj,"saveOk");
	}
	
function  checkIsNull(){
	var len=$("#tab1 tr").length;
	for(var i=0;i<len;i++){
		var fyje=$(".fyjeDm")[i].value;
		var bxje=$(".bxjeDm")[i].value;
		if(fyje==''){
			showError("���ý���Ϊ�գ�");
			return false;
		}
		if(bxje==''){
			showError("��������Ϊ�գ�");
			return false;
		} 
		
		if(fyje<=0){
			showError("���ý���С��0��");
			return false;
		}
		if(bxje<=0){
			showError("��������С��0��");
			return false;
		}
	}
	if(!checkdatee()){
		return ;
	}

	return true;
}
	
function checkdatee(){
	var controlNameArray = ["fyJee","bxJee"];
	var labelNameArray = ["���ý��","�������"];
	var compareValueArray = [16.2,16.2];
	var nodeTypeArray = [NodeType.DECIMAL,NodeType.DECIMAL];
	var notNullArray = [true,true];				
	var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
	return dataValidity.isValidate();
}
	
	function initJe(){
		if(!checkdatee()){
			return ;
		}
		
		var tabLen=$("#tab1 tr").length;
		var zje=0;
		for(var i=0;i<tabLen;i++){
			var fyje=$(".fyjeDm")[i].value;
			if(fyje==''){
				fyje=0;
			}
			var bxje=$(".bxjeDm")[i].value;
			if(bxje==''){
				bxje=0;
			}
			zje+=parseInt(fyje)+parseInt(bxje);
		}
		$("#bxzje").val(zje);
		
	}
	function deleteRow(){
		var xh=$(":checked[name='xhs']");
		var tab1=document.getElementById("tab1");
		if(xh.length<=0){
			showError("��ѡ��Ҫɾ������ϸ��");
			return;
		}
		var jsonStr="";
		var cwDjxh=$("#mainForm_domain_cwDjxh").val();
		for(var i=0;i<xh.length;i++){
			var djxh=xh[i].value;
			//���dixh��Ϊ�գ��������ݿ��Ѿ�����,����̨ɾ��
			if(djxh!=''){
				jsonStr+=djxh+","+cwDjxh+"-";
			}
			//Ϊ�գ�ֱ�ӵ�ҳ��ɾ��table��
			else{
				$.each(xh, function(i, obj){
					var td = $(obj).parent();
					var tr = $(td).parent();
					$(tr).remove();
					refreshIndex();
					initJe();
				}); 
				return ;
			}
		}
		var url=jcontextPath+"/cwfybxsq!deleteMx";
		var json={"domain.jsonStr":jsonStr};
		ajaxCommon(url,json,"deleSuccess");
	}
	
	//��̨ɾ��֮����ص�����
	function deleSuccess(data){
		//$("#tab1").empty();
		var xhs=data.domain.xhs;
		var check=$(":checkbox[name='xhs']");
		var ary=xhs.split("-");
		for(var i=0;i<ary.length;i++){
			var str=ary[i].split(",");
			for(var j=0;j<check.length;j++){
				//����xh�ıȶԣ��жϸղ�ɾ�������Ǽ���
				if(check[j].value==str[0]){
					var td = $(check[j]).parent();
					var tr = $(td).parent();
					$(tr).remove();
				}
			}
		}
		refreshIndex();
		initJe();
		//var list=data.domain.fyList;
	/* 	$.each(list,function(i,hw){
			var tr=$("<tr></tr>");
			var length=$("#tab1 tr").length+1;
			var td="<td align=\"center\" width=\"4%\">"+length+"</td>"+
			"<td align=\"center\" width=\"4%\"><input type=\"checkbox\" name=\"xhs\" value=\"\"></input></td>"
			//$("<td align=\"center\" width=\"4%\"><input type=\"checkbox\" name=\"xhs\" value=\"\">"+11+"</input></td>").appendTo($(tr));
			//alert($(tr));
		
			+"<td class=\"fylbDm\" id=\"wlssyyWhXhTd"+length+"\" width=\"8%\">"+hw.fylbDjxh+"</td>"
			+"<td class=\"fyxmDm\" id=\"wlssclfsDmTd"+length+"\" width=\"8%\">"+hw.fyxmDjxh+"</td>"
			+"<td  align=\"center\" width=\"3%\"><input class=\"fyjeDm\" type=\"text\"/ value=\""+hw.fyJe+"\"> </td>"
			+"<td  align=\"center\" width=\"3%\"> <input class=\"bxjeDm\" type=\"text\" value=\""+hw.bxJe+"\"/></td>"
			+"<td  align=\"center\" width=\"7%\"><input class=\"bzDm\" type=\"text\" value=\""+(hw.bz!=null?hw.bz:"")+"\"/> </td>"
			+"<td style=\"display:none\"  align=\"center\" ><input class=\"mxxh\" type=\"text\" value=\""+(hw.mxDjxh!=null?hw.mxDjxh:"")+"\"/> </td>"
			$(td).appendTo($(tr))
			$(tr).appendTo($("#tab1"));
			addWlssMxInit();
			
		}) */
	}
	
	function refreshIndex(){
		var tabLen=$("#tab1 tr").length;
		var count=0;
		for(var i=0;i<tabLen;i++){
			count++;
			var index=$(".index")[i].innerText;
			$(".index")[i].innerHTML=count;
		}
	}
	function refreshWin() {
		var url = jcontextPath+"/cwfybxsq!initMx";
		$("#mainForm").attr("action",url);
		$("#mainForm").submit();
	}
	
	function saveOk(){
		showSuccess("����ɹ���","saveAfter");
	}
	function saveAfter(){
		window.close();
	}
	
	function checkJs(obj) {
		$(":checkbox[name='xhs']").attr("checked", obj.checked);
	}
	
	function addBxSq(){
		var tr=$("<tr></tr>");
		var length=$("#tab1 tr").length+1;
		var td="<td class=\"index\" align=\"center\" width=\"3%\">"+length+"</td>"+
		"<td align=\"center\" width=\"3%\"><input type=\"checkbox\" name=\"xhs\" value=\"\"></input></td>"
		//$("<td align=\"center\" width=\"4%\"><input type=\"checkbox\" name=\"xhs\" value=\"\">"+11+"</input></td>").appendTo($(tr));
		//alert($(tr));
	
		+"<td class=\"fylbDm\" id=\"wlssyyWhXhTd"+length+"\" width=\"8%\"></td>"
		+"<td class=\"fyxmDm\" id=\"wlssclfsDmTd"+length+"\" width=\"8%\"></td>"
		+"<td  align=\"center\" width=\"4%\"><input name=\"fyJee\" onblur=\"initJe()\" class=\"fyjeDm\" type=\"text\"/> </td>"
		+"<td  align=\"center\" width=\"4%\"> <input name=\"bxJee\" onblur=\"initJe()\" class=\"bxjeDm\" type=\"text\"/></td>"
		+"<td  align=\"center\" width=\"10%\"><input class=\"bzDm\" type=\"text\"/> </td>"
		+"<td style=\"display:none\"  align=\"center\" ><input class=\"mxxh\" type=\"text\" value=\"\"/> </td>"
		$(td).appendTo($(tr))
		$(tr).appendTo($("#tab1"));
		addWlssMxInit();
		ininXm();
	}
	
	function addWlssMxInit() {
		var xh = $("#tab1 tr").length;
		$("#wlssyyWhXhDiv select").attr("id","fyFlId"+"-"+(xh-1));
		$("#wlssclfsDmDiv select").attr("id","fyXmId"+(xh-1));
		$("#wlssclfsDmDiv select").attr("name","fyXmName"+(xh-1));
		var wlssyyWhXhDiv = $("#wlssyyWhXhDiv").html();
		var wlssclfsDmDiv = $("#wlssclfsDmDiv").html();
		$("#wlssyyWhXhTd"+xh).html(wlssyyWhXhDiv);
		$("#wlssclfsDmTd"+xh).html(wlssclfsDmDiv);
	}
	
	function initFlXm(){
		var mxts = $("#tab1 tr").length;
		
		for(var i=0;i<mxts;i++){
			var wlssyyWhXh = $(".fylbDM")[i].innerText;
			var wlssclfsDm = $(".fyxmDm")[i].innerText;
			//alert(wlssyyWhXh+"***"+wlssclfsDm);
			$("#wlssyyWhXhDiv select").val(wlssyyWhXh);
			$("#wlssclfsDmDiv select").val(wlssclfsDm);
			$("#wlssyyWhXhDiv select").attr("id","fyFlId"+"-"+i);
			$("#wlssclfsDmDiv select").attr("id","fyXmId"+i);
			$("#wlssclfsDmDiv select").attr("name","fyXmName"+i);
			var wlssyyWhXhDiv = $("#wlssyyWhXhDiv").html();
			var wlssclfsDmDiv = $("#wlssclfsDmDiv").html();
			$(".fylbDM")[i].innerHTML=wlssyyWhXhDiv;
			$(".fyxmDm")[i].innerHTML=wlssclfsDmDiv;
		}
	}
	
	function checkdata(){
		var controlNameArray = ["domain.fyxmMc","domain.fylbCwDjxh"];
		var labelNameArray = ["������Ŀ����","�����������"];
		var compareValueArray = [40,40];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
</head>
<base target="_self" />
<body >
<%try{ %>
<s:form action="cwfylb!initMx" namespace="" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.xmflDjxh"></s:hidden>
	<input type="hidden" id="mainForm_domain_jzdw" value="<s:property value="domain.fyjzDwDjxh"/>"></input>
	<s:hidden name="domain.cwDjxh"></s:hidden>
	<s:hidden name="domain.wsSpxh"></s:hidden>
	<div id="maincont">
	<div class="pop_contc" style="height:360px; ">
		<fieldset>
		<legend>������Ŀ��Ϣ</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				
				
      			<tr>
      				<td width="18%" align="right">���ü��˵�λ��</td>
      				<td width="60%" align="left" colspan="3">
      				<s:textfield  name="domain.jzdw" cssClass="pop_input_colspan2 bgstyle_required "  readonly="true"  ></s:textfield> 
      				</td>
      			</tr>
				
				 <tr>
				     	<td width="18%" align="right">����֧����λ��</td>
				     	 <td width="60%" align="left" colspan="3">
				    <s:textfield name="domain.jfdw" cssClass="pop_input_colspan2 bgstyle_required "  readonly="true"  ></s:textfield> 
				      		
				     	 </td>
				     
				    </tr>
				<tr>
				
				 <tr>
				     	<td width="18%" align="right">���ñ������ϼƣ�</td>
				     	 <td width="60%" align="left" colspan="3">
				     			<s:textfield id="bxzje" name="domain.fybxje" cssClass="pop_input_colspan2 bgstyle_required "  readonly="true"  ></s:textfield> 
				      		
				     	 </td>
				     
				    </tr>
				<tr>
						<td width="18%" align="right">��ע��</td>
					<td width="60%" align="left" colspan="3">
						<s:textarea name="domain.bz"  rows="3" cssClass="pop_textarea_colspan2  bgstyle_optional" readonly="true" ></s:textarea>
					</td>
				</tr>
			
			</table>
		</fieldset>
		
		<div style="margin:20px 0 20px 0;">
		<table id="zbTab" width="95%" border="0" cellspacing="0" cellpadding="0" class="poptab_css" align="center">
			<tr>
				<th align="center" width="2%">���</th>
				<th align="center" width="6%">�������</th>
				<th align="center" width="10%">������Ŀ</th>
				<th align="center" width="3%">���ý��</th>
				<th align="center" width="3%">�������</th>
				<th align="center" width="10%">��ע</th>
			</tr>	
			<tbody id="tab1">
			<s:iterator value="domain.fyList" id="zb" status="i">
			<tr>
				<td class="index" align="center"><s:property value="#i.index+1"/></td>
				<td class="fylbDM"><s:property value="#zb.fylbDjxh"/></td>
				<td class="fyxmDm"><s:property value="#zb.fyxmDjxh"/></td>
				<td ><input name="fyJee"   type="text" readonly="readonly"  class="fyjeDm" value='<s:property value="#zb.fyJe"/>'></input></td>
				<td ><input name="bxJee" type="text" readonly="readonly" class="bxjeDm" value='<s:property value="#zb.bxJe"/>'></input></td>
				<td ><input type="text" readonly="readonly" class="bzDm" value='<s:property value="#zb.bz"/>'></input></td>
			
				<td style="display: none;"><input type="text" class="mxxh" value="<s:property value="#zb.mxDjxh" />"></input></td>
			</tr>
			</s:iterator>
			</tbody>
		</table>
		</div>
			
		</div>
	</div>
	<div id="wlssyyWhXhDiv" style="display: none;"><sys:CwFylb disabled="true" myName="fylbDjxh" onChange="changeXm(this)"    contaisQxz="false" myClass="select fylbDjxh " mcContainDmBz="N"></sys:CwFylb></div>
			<div id="wlssclfsDmDiv" style="display: none;">
			<sys:CwFyXmWh myName="fyxmDjxh"  contaisQxz="false" disabled="true" myClass="select fyxmDjxh" mcContainDmBz="N"></sys:CwFyXmWh></div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
