<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>财务-费用报销-申请</title>

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
					showError("请新增一条费用报销申请！");
					return;
				}
				for(var i=0;i<len;i++){
					var fyxm=$(".fyxmDjxh")[i].value;
					if(fyxm==''){
						showError("费用项目不能为空！");
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
				showError("请选择要删除的明细！");
				return;
			}
			showConfirm("确定要删除费用报销申请明细么？","deleteRow");
		})
		
		$("#sendBtn").click(function(){
			var wsDm="100002";//费用登记审批表
			var cwDjxh = $("#mainForm_domain_cwDjxh").val();
			var oldWsspxh = $("#mainForm_domain_wsSpxh").val();
			var xmflDjxh=$("#mainForm_domain_xmflDjxh").val();
			var fyjzDwDjxh=$("#mainForm_domain_jzdw").val();
			//alert(oldWsspxh+"--"+xmflDjxh)
			scSend(wsDm,xmflDjxh,cwDjxh,oldWsspxh,fyjzDwDjxh);
		});
		
		//初始化费用项目
		ininXm();
		
		
	});
	
	//初始化，根据项目类别级联费用项目
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
				 var option = $("<option>").text(domain.mc+"(项目分类："+domain.openFlag+")").val(domain.dm);
			}
		   
		    //默认选中
		    if(data.domain.defaultValue==domain.dm){
		    	option = $("<option selected='selected'>").text(domain.mc+"(项目分类："+domain.openFlag+")").val(domain.dm);
		    }
		    $("#"+data.domain.currentObjId).append(option);
		});
	}
	
	//根据费用类别的改变，级联费用项目
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
				showError("此费用项目对应的项目分类不能为空！");
				return;
			}
			doSave(djxh);
		}
		else{
			showError("每个明细对应的项目分类必须一样！");
			return ;
		}
	}
	
	//添加
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
				showError("费用金额不能为空！");
				return ;
			}
			if(fyjeVal<=0){
				showError("费用金额不能小于0！");
				return ;
			}
			
			if(bxjeVal==''){
				showError("报销金额不能为空！");
				return ;
			}
			if(bxjeVal<=0){
				showError("报销金额不能小于0！");
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
	
	//自定义jquery
	function getJqueryParamZdy(obj, paraName) {
		var data = "";
		$.each(obj, function(i){
			data += paraName + "[" + i + "]=" + encodeURI(obj[i]) + "&";
		});
		
		return data;
	}
	
	//添加前，检测页面
function  checkIsNull(){
	var len=$("#tab1 tr").length;
	for(var i=0;i<len;i++){
		var fyje=$(".fyjeDm")[i].value;
		var bxje=$(".bxjeDm")[i].value;
		if(fyje==''){
			showError("费用金额不能为空！");
			return false;
		}
		if(bxje==''){
			showError("报销金额不能为空！");
			return false;
		} 
		
		if(fyje<=0){
			showError("费用金额不能小于0！");
			return false;
		}
		if(bxje<=0){
			showError("报销金额不能小于0！");
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
	var labelNameArray = ["费用金额","报销金额","报销单号"];
	var compareValueArray = [16.2,16.2,50];
	var nodeTypeArray = [NodeType.DECIMAL,NodeType.DECIMAL,NodeType.STRING];
	var notNullArray = [true,true,false];				
	var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
	return dataValidity.isValidate();
}
	
	//根据费用金额和报销金额取值赋给费用报销金额总计
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
					showError("报销金额必须为数字类型！");
					return;
				}
		}
		else{	
			if(!isNum.test(bxje)){
				showError("报销金额必须为数字类型！");
				return;
			}
		}
			zje+=parseFloat(bxje);	
		}
		$("#bxzje").val(zje);
		
	}
	
	//删除行
	function deleteRow(){
		var xh=$(":checked[name='xhs']");
		var tab1=document.getElementById("tab1");
		var jsonStr="";
		var cwDjxh=$("#mainForm_domain_cwDjxh").val();
		var jzdw=$("#mainForm_domain_fyjzDwDjxh").val();
		for(var i=0;i<xh.length;i++){
			var djxh=xh[i].value;
			//如果dixh不为空，代表数据库已经有了,到后台删除
			if(djxh!=''){
				jsonStr+=djxh+","+cwDjxh+","+jzdw+"-";
			}
			//为空，直接到页面删除table行
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
	
	//后台删除之后调回调函数
	function deleSuccess(data){
		//$("#tab1").empty();
		var xhs=data.domain.xhs;
		var check=$(":checkbox[name='xhs']");
		var ary=xhs.split("-");
		for(var i=0;i<ary.length;i++){
			var str=ary[i].split(",");
			for(var j=0;j<check.length;j++){
				//根据xh的比对，判断刚才删除的是那几行
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
		//删除之后，循环重置下拉框的id和name
		var tabLen1=$("#tab1 tr").length;
		var count=0;
		for(var i=0;i<tabLen1;i++){
			count++;
			var index=$(".index")[i].innerText;
			
			if(index!=count){
				//循环重置td的id
				var obj=$("#wlssyyWhXhTd"+index)[0];
				$(obj).attr("id","wlssyyWhXhTd"+count);
				
				var obj=$("#wlssclfsDmTd"+index)[0];
				$(obj).attr("id","wlssclfsDmTd"+count);
				
				
			}
			//循环重置下拉框的id和name
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
		
		//删除之后，循环重置每一行的序列
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
		showSuccess("保存成功！","saveAfter");
	}
	function saveAfter(){
		window.close();
	}
	
	function checkJs(obj) {
		$(":checkbox[name='xhs']").attr("checked", obj.checked);
	}
	
	//点击添加每一行
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
	
	//添加之后给下拉框赋id和name赋值，在把下拉框赋给每一列中
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
	
	//初始化下拉框，下拉框赋id和name赋值，在把下拉框赋给每一列中
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
		<legend>费用项目信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				
				
      			<tr>
      				<td width="18%" align="right"><font class="font_red"  id="font4">*</font>费用记账单位：</td>
      				<td width="60%" align="left" colspan="3">
      					<sys:fgsList myName="domain.fyjzDwDjxh" myId="mainForm_domain_fyjzDwDjxh" contaisDq="Y" contaisQxz="false" myClass="select" mcContainDmBz="Y"></sys:fgsList>
      				</td>
      			</tr>
				
				 <tr>
				     	<td width="18%" align="right"><font class="font_red"  id="font4">*</font>费用支付单位：</td>
				     	 <td width="60%" align="left" colspan="3">
				     	<sys:fgsList myName="domain.fyzfDwDjxh" myId="mainForm_domain_fyzfDwDjxh" contaisDq="Y" contaisQxz="false" myClass="select" mcContainDmBz="Y"></sys:fgsList>
				      		
				     	 </td>
				     
				    </tr>
				
				 <tr>
				     	<td width="18%" align="right">报销单号：</td>
				     	 <td width="60%" align="left" colspan="3">
				      		<s:textfield  name="domain.bxdh" cssClass="pop_input_colspan2" ></s:textfield> 
				     	 </td>
				     
				    </tr>
				
				 <tr>
				     	<td width="9%" align="right">费用报销金额合计：</td>
				     	 <td width="30%" align="left">
				     			<s:textfield id="bxzje" name="domain.fybxje" cssClass="pop_input bgstyle_readonly" ></s:textfield> 
				      		
				     	 </td>
				     	<td width="15%" align="right">费用报销时间：</td>
				     	 <td width="30%" align="left">
				     		   <sys:dateCurrentDayTag myName="domain.sqrq" myId="mainForm_domain_sqrq" myClass="ymdate" />
				      		
				     	 </td>
				    </tr>
				<tr>
						<td width="18%" align="right">备注：</td>
					<td width="60%" align="left" colspan="3">
						<s:textarea name="domain.bz"  rows="3" cssClass="pop_textarea_colspan2  bgstyle_optional" ></s:textarea>
					</td>
				</tr>
			
			</table>
		</fieldset>
		
		<div style="margin:20px 0 20px 0;">
		<table id="zbTab" width="95%" border="0" cellspacing="0" cellpadding="0" class="poptab_css" align="center">
			<tr>
				<th align="center" width="2%">序号</th>
				<th  align="center" width="2%"><input type="checkbox" name="jsCheck" onclick="checkJs(this)"/></th>
				<th align="center" width="6%">费用类别</th>
				<th align="center" width="10%">费用项目</th>
				<th align="center" width="4%">费用金额</th>
				<th align="center" width="4%">报销金额</th>
				<th align="center" width="10%">报销费用明细</th>
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
				<button type="button" class="pop_btnbg" id="addBtn">新增报销申请</button>
			 	&nbsp;
			 	<button type="button" class="pop_btnbg" id="saveBtn">保 存</button>
			 	&nbsp;
			 		<s:if test="domain.sendBz">
			 		<button type="button" class="pop_btnbg" id="sendBtn">发送</button>
			 		&nbsp;
			 	</s:if>
			 	<button type="button" class="pop_btnbg" id="deleteBt">删除</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
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
