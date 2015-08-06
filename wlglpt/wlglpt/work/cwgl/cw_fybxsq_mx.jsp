<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>����-���ñ���-����</title>

<style type="text/css">
	.fyjeDm{width:65px;}
	.bxjeDm{width:65px;}
	.bzDm{width:200px;}
html,body {background:none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
	$(function(){
		initFlXm();
		$("#saveBtn").click(function(){
				var len=$("#tab1 tr").length;
				var str='';
				if(len==0){
					showError("������һ�����ñ������룡");
					return;
				}
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
			var xh=$(":checked[name='xhs']");
			if(xh.length<=0){
				showError("��ѡ��Ҫɾ������ϸ��");
				return;
			}
			showConfirm("ȷ��Ҫɾ�����ñ���������ϸô��","deleteRow");
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
	
	//��ʼ����������Ŀ�����������Ŀ
	function ininXm(){
		var tab1=$("#tab1 tr").length;
		for(var i=0;i<tab1;i++){
			var index=$(".index")[i].innerText;
			var fyLb=$(".fylbDjxh")[i].value;
			getXmByFl("fyfl-xm", fyLb, '', "fyXmName"+index, "fyXmId"+index, "Y", "N");
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
	
	//���ݷ������ĸı䣬����������Ŀ
	function changeXm(obj){
		var id=$(obj).attr("id");
		var i=id.split("-")[1];
		var fyLb=obj.value;
		getXmByFl("fyfl-xm", fyLb, '', "fyXmName"+i, "fyXmId"+i, "Y", "N");
	}
	
	function toSave(data){
		var str=data.domain.tager;
		if(str=='1'){
			var djxh=data.domain.xmflDjxh;
			if(djxh=="null"){
				showError("�˷�����Ŀ��Ӧ����Ŀ���಻��Ϊ�գ�");
				return;
			}
			doSave(djxh);
		}
		else{
			showError("ÿ����ϸ��Ӧ����Ŀ�������һ����");
			return ;
		}
	}
	
	//���
	function doSave(djxh){
		var fyjzDwDjxh = trim($("#mainForm_domain_fyjzDwDjxh").val()); 
		var fyzfDwDjxh = trim($("#mainForm_domain_fyzfDwDjxh").val());
		var bz = trim($("#mainForm_domain_bz").val()); 
		var cwDjxh = trim($("#mainForm_domain_cwDjxh").val()); 
		var fybxje = trim($("#bxzje").val()); 
		var sqrq=$("#mainForm_domain_sqrq").val();
		var bxdh=$("#mainForm_domain_bxdh").val();
		var len=$("#tab1 tr").length;
		//var tabLen = document.getElementById("zbTab").rows;
		var fylb=new Array();
		var fyxm=new Array();
		var fyje=new Array();
		var bxje=new Array();
		var mxbz=new Array();
		var xhs=new Array();
		var jsonStr="";
		for(var i=0;i<len;i++){
			var fylbVal=$(".fylbDjxh")[i].value;
			var fyxmVal=$(".fyxmDjxh")[i].value;
			var fyjeVal=$(".fyjeDm")[i].value;
			var bxjeVal=$(".bxjeDm")[i].value;
			var mxbzVal=$(".bzDm")[i].value;
			var xhVal=$(".mxxh")[i].value;
			if(fyjeVal==''){
				showError("���ý���Ϊ�գ�");
				return ;
			}
			if(fyjeVal<=0){
				showError("���ý���С��0��");
				return ;
			}
			
			if(bxjeVal==''){
				showError("��������Ϊ�գ�");
				return ;
			}
			if(bxjeVal<=0){
				showError("��������С��0��");
				return;
			}
			 fylb[i]=fylbVal;
			 fyxm[i]=fyxmVal;
			 fyje[i]=fyjeVal;
			 bxje[i]=bxjeVal;
			 mxbz[i]=mxbzVal;
			 xhs[i]=xhVal;
		}
		
		jsonStr=getJqueryParamZdy(xhs, "domain.mxXh");
		jsonStr+=getJqueryParamZdy(fylb,"domain.fylbDjxh");
		jsonStr+=getJqueryParamZdy(fyxm,"domain.fyxmDjxh");
		jsonStr+=getJqueryParamZdy(fyje,"domain.fyje");
		jsonStr+=getJqueryParamZdy(bxje,"domain.bxje");
		jsonStr+=getJqueryParamZdy(mxbz,"domain.fybz");
		var url = jcontextPath+"/cwfybxsq!save";  
    	var jsonObj = {"domain.fyjzDwDjxh":fyjzDwDjxh,"domain.fyzfDwDjxh":fyzfDwDjxh,"domain.cwDjxh":cwDjxh,"domain.bz":bz
    					,"domain.fybxje":fybxje,"domain.xmflDjxh":djxh,"domain.sqrq":sqrq,"domain.bxdh":bxdh};   	
    	jsonStr += jQuery.param(jsonObj);
		ajaxCommon(url,encodeURI(jsonStr),"saveOk",false);
	}
	
	//�Զ���jquery
	function getJqueryParamZdy(obj, paraName) {
		var data = "";
		$.each(obj, function(i){
			data += paraName + "[" + i + "]=" + encodeURI(obj[i]) + "&";
		});
		
		return data;
	}
	
	//���ǰ�����ҳ��
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
	var controlNameArray = ["fyJee","bxJee","domain.bxdh"];
	var labelNameArray = ["���ý��","�������","��������"];
	var compareValueArray = [16.2,16.2,50];
	var nodeTypeArray = [NodeType.DECIMAL,NodeType.DECIMAL,NodeType.STRING];
	var notNullArray = [true,true,false];				
	var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
	return dataValidity.isValidate();
}
	
	//���ݷ��ý��ͱ������ȡֵ�������ñ�������ܼ�
	function initJe(){
		/* if(!checkdatee()){
			return ;
		} */
		var tabLen=$("#tab1 tr").length;
		var zje=0;
		var isNum = /^[0-9]*$/;
		var reg = new RegExp("^[0-9]+(.[0-9]{1,2})?$", "g");  

		for(var i=0;i<tabLen;i++){
		var bxje=$(".bxjeDm")[i].value;
		if(bxje==''){
				bxje="0";
		}
		if(bxje.indexOf(".")!=-1){
				var ary=bxje.split(".");
				if(!isNum.test(ary[1])||!isNum.test(ary[0])){
					showError("����������Ϊ�������ͣ�");
					return;
				}
		}
		else{	
			if(!isNum.test(bxje)){
				showError("����������Ϊ�������ͣ�");
				return;
			}
		}
			zje+=parseFloat(bxje);	
		}
		$("#bxzje").val(zje);
		
	}
	
	//ɾ����
	function deleteRow(){
		var xh=$(":checked[name='xhs']");
		var tab1=document.getElementById("tab1");
		var jsonStr="";
		var cwDjxh=$("#mainForm_domain_cwDjxh").val();
		var jzdw=$("#mainForm_domain_fyjzDwDjxh").val();
		for(var i=0;i<xh.length;i++){
			var djxh=xh[i].value;
			//���dixh��Ϊ�գ��������ݿ��Ѿ�����,����̨ɾ��
			if(djxh!=''){
				jsonStr+=djxh+","+cwDjxh+","+jzdw+"-";
			}
			//Ϊ�գ�ֱ�ӵ�ҳ��ɾ��table��
			else{
				var td = $(xh[i]).parent();
				var tr = $(td).parent();
				$(tr).remove();
				refreshIndex();
				initJe();
				/* $.each(xh, function(i, obj){
					var td = $(obj).parent();
					var tr = $(td).parent();
					$(tr).remove();
					refreshIndex();
					initJe();
				}); 
				return ; */
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
				if(str[0]==''){
					continue;
				}
				if(check[j].value==str[0]){
					var td = $(check[j]).parent();
					var tr = $(td).parent();
					$(tr).remove();
					refreshIndex();
					initJe();
				}
			}
		}
	}
	
	function refreshIndex(){
		//ɾ��֮��ѭ�������������id��name
		var tabLen1=$("#tab1 tr").length;
		var count=0;
		for(var i=0;i<tabLen1;i++){
			count++;
			var index=$(".index")[i].innerText;
			
			if(index!=count){
				//ѭ������td��id
				var obj=$("#wlssyyWhXhTd"+index)[0];
				$(obj).attr("id","wlssyyWhXhTd"+count);
				
				var obj=$("#wlssclfsDmTd"+index)[0];
				$(obj).attr("id","wlssclfsDmTd"+count);
				
				
			}
			//ѭ�������������id��name
			var obj=document.getElementsByName("fyXmName"+index)[0];
			var flObj=document.getElementsByName("fyFlName"+index)[0];
			$(obj).attr("id","fyXmId"+count);
			$(obj).attr("name","fyXmName"+count);
			$(flObj).attr("id","fyFlId"+"-"+count);
			$(flObj).attr("name","fyFlName"+count);
		
			//alert($(obj).attr("id")+"---"+count);
			
			//var obj=document.getElementsByName("fyXmName"+count)[0];
			//alert($(obj).attr("id")+"---"+count);
		}
		
		//ɾ��֮��ѭ������ÿһ�е�����
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
	
	//������ÿһ��
	function addBxSq(){
		var tr=$("<tr></tr>");
		var length=$("#tab1 tr").length+1;
		var td="<td class=\"index\" align=\"center\" width=\"2%\">"+length+"</td>"+
		"<td align=\"center\" width=\"3%\"><input type=\"checkbox\" name=\"xhs\" value=\"\"></input></td>"
		//$("<td align=\"center\" width=\"4%\"><input type=\"checkbox\" name=\"xhs\" value=\"\">"+11+"</input></td>").appendTo($(tr));
		//alert($(tr));
		+"<td class=\"fylbDm\" id=\"wlssyyWhXhTd"+length+"\" width=\"6%\"></td>"
		+"<td class=\"fyxmDm\" id=\"wlssclfsDmTd"+length+"\" width=\"10%\"></td>"
		+"<td  align=\"center\" width=\"4%\"><input name=\"fyJee\"  class=\"fyjeDm\" type=\"text\"/> </td>"
		+"<td  align=\"center\" width=\"4%\"> <input name=\"bxJee\" onblur=\"initJe()\" class=\"bxjeDm\" type=\"text\"/></td>"
		+"<td  align=\"center\" width=\"10%\"><input class=\"bzDm\" type=\"text\"/> </td>"
		+"<td style=\"display:none\"  align=\"center\" ><input class=\"mxxh\" type=\"text\" value=\"\"/> </td>"
		$(td).appendTo($(tr))
		$(tr).appendTo($("#tab1"));
		addWlssMxInit();
		ininXm();
	}
	
	//���֮���������id��name��ֵ���ڰ������򸳸�ÿһ����
	function addWlssMxInit() {
		var xh = $("#tab1 tr").length;
		$("#wlssyyWhXhDiv select").attr("id","fyFlId"+"-"+xh);
		$("#wlssyyWhXhDiv select").attr("name","fyFlName"+xh);
		$("#wlssclfsDmDiv select").attr("id","fyXmId"+xh);
		$("#wlssclfsDmDiv select").attr("name","fyXmName"+xh);
		var wlssyyWhXhDiv = $("#wlssyyWhXhDiv").html();
		var wlssclfsDmDiv = $("#wlssclfsDmDiv").html();
		//alert(wlssyyWhXhDiv+"==="+wlssclfsDmDiv)
		$("#wlssyyWhXhTd"+xh).html(wlssyyWhXhDiv);
		$("#wlssclfsDmTd"+xh).html(wlssclfsDmDiv);
	}
	
	//��ʼ��������������id��name��ֵ���ڰ������򸳸�ÿһ����
	function initFlXm(){
		var mxts = $("#tab1 tr").length;
		for(var i=0;i<mxts;i++){
			var index=$(".index")[i].innerText;
			var wlssyyWhXh = $(".fylbDM")[i].innerText;
			var wlssclfsDm = $(".fyxmDm")[i].innerText;
			//alert(wlssyyWhXh+"***"+wlssclfsDm);
			$("#wlssyyWhXhDiv select").val(wlssyyWhXh);
			$("#wlssclfsDmDiv select").val(wlssclfsDm);
			$("#wlssyyWhXhDiv select").attr("id","fyFlId"+"-"+index);
			$("#wlssyyWhXhDiv select").attr("name","fyFlName"+index);
			$("#wlssclfsDmDiv select").attr("id","fyXmId"+index);
			$("#wlssclfsDmDiv select").attr("name","fyXmName"+index);
			var wlssyyWhXhDiv = $("#wlssyyWhXhDiv").html();
			var wlssclfsDmDiv = $("#wlssclfsDmDiv").html();
			$(".fylbDM")[i].innerHTML=wlssyyWhXhDiv;
			$(".fyxmDm")[i].innerHTML=wlssclfsDmDiv;
		}
	}
</script>
</head>

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
      				<td width="18%" align="right"><font class="font_red"  id="font4">*</font>���ü��˵�λ��</td>
      				<td width="60%" align="left" colspan="3">
      					<sys:fgsList myName="domain.fyjzDwDjxh" myId="mainForm_domain_fyjzDwDjxh" contaisDq="Y" contaisQxz="false" myClass="select" mcContainDmBz="Y"></sys:fgsList>
      				</td>
      			</tr>
				
				 <tr>
				     	<td width="18%" align="right"><font class="font_red"  id="font4">*</font>����֧����λ��</td>
				     	 <td width="60%" align="left" colspan="3">
				     	<sys:fgsList myName="domain.fyzfDwDjxh" myId="mainForm_domain_fyzfDwDjxh" contaisDq="Y" contaisQxz="false" myClass="select" mcContainDmBz="Y"></sys:fgsList>
				      		
				     	 </td>
				     
				    </tr>
				
				 <tr>
				     	<td width="18%" align="right">�������ţ�</td>
				     	 <td width="60%" align="left" colspan="3">
				      		<s:textfield  name="domain.bxdh" cssClass="pop_input_colspan2" ></s:textfield> 
				     	 </td>
				     
				    </tr>
				
				 <tr>
				     	<td width="9%" align="right">���ñ������ϼƣ�</td>
				     	 <td width="30%" align="left">
				     			<s:textfield id="bxzje" name="domain.fybxje" cssClass="pop_input bgstyle_readonly" ></s:textfield> 
				      		
				     	 </td>
				     	<td width="15%" align="right">���ñ���ʱ�䣺</td>
				     	 <td width="30%" align="left">
				     		   <sys:dateCurrentDayTag myName="domain.sqrq" myId="mainForm_domain_sqrq" myClass="ymdate" />
				      		
				     	 </td>
				    </tr>
				<tr>
						<td width="18%" align="right">��ע��</td>
					<td width="60%" align="left" colspan="3">
						<s:textarea name="domain.bz"  rows="3" cssClass="pop_textarea_colspan2  bgstyle_optional" ></s:textarea>
					</td>
				</tr>
			
			</table>
		</fieldset>
		
		<div style="margin:20px 0 20px 0;">
		<table id="zbTab" width="95%" border="0" cellspacing="0" cellpadding="0" class="poptab_css" align="center">
			<tr>
				<th align="center" width="2%">���</th>
				<th  align="center" width="2%"><input type="checkbox" name="jsCheck" onclick="checkJs(this)"/></th>
				<th align="center" width="6%">�������</th>
				<th align="center" width="10%">������Ŀ</th>
				<th align="center" width="4%">���ý��</th>
				<th align="center" width="4%">�������</th>
				<th align="center" width="10%">����������ϸ</th>
			</tr>	
			<tbody id="tab1">
			<s:iterator value="domain.fyList" id="zb" status="i">
			<tr>
				<td class="index" align="center"><s:property value="#i.index+1"/></td>
				<td align="center"><input type="checkbox" name="xhs" value="<s:property value="#zb.mxDjxh" />"/></td>
				<td class="fylbDM"><s:property value="#zb.fylbDjxh"/></td>
				<td class="fyxmDm"><s:property value="#zb.fyxmDjxh"/></td>
				<td ><input name="fyJee" type="text"   class="fyjeDm" value='<s:property value="#zb.fyJe"/>'></input></td>
				<td ><input name="bxJee" type="text" onblur="initJe()" class="bxjeDm" value='<s:property value="#zb.bxJe"/>'></input></td>
				<td ><input type="text" class="bzDm" value='<s:property value="#zb.bz"/>'></input></td>
			
				<td style="display: none;"><input type="text" class="mxxh" value="<s:property value="#zb.mxDjxh" />"></input></td>
			</tr>
			</s:iterator>
			</tbody>
		</table>
		</div>
			<div class="pop_btn">
				<button type="button" class="pop_btnbg" id="addBtn">������������</button>
			 	&nbsp;
			 	<button type="button" class="pop_btnbg" id="saveBtn">�� ��</button>
			 	&nbsp;
			 		<s:if test="domain.sendBz">
			 		<button type="button" class="pop_btnbg" id="sendBtn">����</button>
			 		&nbsp;
			 	</s:if>
			 	<button type="button" class="pop_btnbg" id="deleteBt">ɾ��</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
		    </div>
		</div>
	</div>
	<div id="wlssyyWhXhDiv" style="display: none;"><sys:CwFylb  onChange="changeXm(this)"   contaisQxz="false" myClass="select fylbDjxh" mcContainDmBz="N"></sys:CwFylb></div>
			<div id="wlssclfsDmDiv" style="display: none;">
			<sys:CwFyXmWh myName="fyxmDjxh"  contaisQxz="false" myClass="select fyxmDjxh" mcContainDmBz="N"></sys:CwFyXmWh></div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
