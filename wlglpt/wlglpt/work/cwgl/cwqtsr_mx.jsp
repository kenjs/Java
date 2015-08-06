<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>财务-其他收入</title>

<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
	$(function(){
		$("#mainForm_domain_qtsrDomain_ssJgbm").attr("disabled",true);
		$("#closeBtn").click(function(){
			window.close();
		})
		$("#addBtn").click(function(){
			addQtsr();
		});
		$("#deleteBtn").click(function(){
			var xhs = $(":checked[name='xhs']");
			if(xhs.length<=0){
				showAlert("请选择要删除的损失记录！")
				return;
			}else{
				showConfirm("删除后不可恢复！确认删除么？","delQtsr")
			}
		});
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			
			var ssJgbm=$("#mainForm_domain_qtsrDomain_ssJgbm").val();
			var rq=$("#mainForm_domain_qtsrDomain_rq").val();
			
			var xhObjs = $(".cwDjxh");
			var xmmcObjs = $(".xmmc");
			var fkfObjs = $(".fkf");
			var zcflDmObjs = $(".zcflDm");
			var yhCshDjxhObjs = $(".yhCshDjxh");
			var jeObjs = $(".je");
			var bzObjs = $(".bz");
			
			var xhs = new Array();
			var xmmcs = new Array();
			var fkfs = new Array();
			var zcflDms = new Array();
			var yhCshDjxhs = new Array();
			var jes = new Array();
			var bzs = new Array();
			
			for(i=0;i<xhObjs.length;i++){
				xhs[i]=xhObjs[i].value;
				xmmcs[i]=xmmcObjs[i].value;
				fkfs[i]=fkfObjs[i].value;
				zcflDms[i]=zcflDmObjs[i].value;
				if(zcflDms[i] != "12"){
				   yhhdh = "";
				   var val = $("#mainForm_domain_zcflDm").find("option:selected").text();
				   var strs=val.split(" "); //字符分割      
				   yhCshDjxhs[i] = strs[2];
				   var showStr;
				   if(zcflDms[i]=="11"){
				   		showStr="请先在资产初始化中维护现金！";
				   }else{
				   		showStr="请先在资产初始化中维护油卡！";
				   }
				   if(yhCshDjxhs[i]==null||yhCshDjxhs[i]==""){
						showAlert(showStr);
						return;
					}
				}else{
					yhCshDjxhs[i]=yhCshDjxhObjs[i].value;
					if(yhCshDjxhs[i]==null||yhCshDjxhs[i]==""){
						showAlert("请先在资产初始化中维护银行账号！");
						return;
					}
				}
				//alert(yhCshDjxhs[i]);
				jes[i]=jeObjs[i].value;
				bzs[i]=bzObjs[i].value;
			}
			for(var j = 0;j<jes.length;j++){
				var je = jes[j]/1;
				if(je<=0){
					showAlert("金额不可小于0！");
					return;
				}
			}
			var jsonStr = getJqueryParamZdy(xhs, "domain.qtsrDomain.cwDjxhs");
			jsonStr+= getJqueryParamZdy(xmmcs, "domain.qtsrDomain.xmmcs");
			jsonStr+= getJqueryParamZdy(fkfs, "domain.qtsrDomain.fkfs");
			jsonStr+= getJqueryParamZdy(jes, "domain.qtsrDomain.jes");
			jsonStr+= getJqueryParamZdy(zcflDms, "domain.qtsrDomain.zcflDms");
			jsonStr+= getJqueryParamZdy(yhCshDjxhs, "domain.qtsrDomain.yhCshDjxhs");
			jsonStr+= getJqueryParamZdy(bzs, "domain.qtsrDomain.bzs");
			
			var url = jcontextPath+"/cwgl/cwzjrb!saveQtsr";  
	    	var jsonObj = {"domain.qtsrDomain.ssJgbm":ssJgbm,"domain.qtsrDomain.rq":rq};
            jsonStr += jQuery.param(jsonObj);
            //alert(jsonStr);
            ajaxCommon(url,encodeURI(jsonStr),"saveOk", false);
		});
		initQtsrXl();
	});
	//自定义jquery
	function getJqueryParamZdy(obj, paraName) {
		var data = "";
		$.each(obj, function(i){
			data += paraName + "[" + i + "]=" + encodeURI(obj[i]) + "&";
		});
		return data;
	}
	function checkJs(obj) {
		$(":checkbox[name='xhs']").attr("checked", obj.checked);
	}
	function saveOk(){
		showSuccess("保存成功！","closeWin");
	}
	function closeWin(){
		window.close();
	}
	function checkdata(){
		var controlNameArray = ["xmmc","xmmc","je","domain.zcflDm","domain.yhCshDjxh","bz"];
		var labelNameArray = ["项目名称","付款方","金额","资产分类代码","银行初始化登记序号","备注"];
		var compareValueArray = [100,100,14.2,2,16,500];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.DECIMAL,NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,true,true,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	function addQtsr() {
		var xh = $("#qtsrTbody tr").length+1;
		var trObj = $("<tr></tr>");
		$("<td align=\"center\" class=\"bh\">"+xh+"</td>").appendTo($(trObj));
		$("<td align=\"center\"><input type=\"checkbox\" name=\"xhs\" value=\"\"/></td>").appendTo($(trObj));
		$("<td align=\"center\"><input type=\"text\" class=\"pop_input bgstyle_optional xmmc\" name=\"xmmc\" value=\"\"/></td>").appendTo($(trObj));	
		$("<td align=\"center\"><input type=\"text\" class=\"pop_input bgstyle_optional fkf\" name=\"fkf\"  value=\"\"/></td>").appendTo($(trObj));			
		
		var tdJe = $("<td></td>");
		var inputJe = $("<input type=\"text\" class=\"pop_input bgstyle_optional je\" name=\"je\" />")
			//inputJe.bind("change", function(){calJeHj();});
			inputJe.appendTo(tdJe);
		tdJe.appendTo($(trObj));
		$("<td class=\"zcflDmTd\" id=\"zcflDmTd"+xh+"\"></td>").appendTo($(trObj));
		$("<td style=\"display: none;\"><input type=\"hidden\" class=\"yhCshDjxhHid\" value=\"\"/></td>").appendTo($(trObj));
		$("<td class=\"yhCshDjxhTd\" id=\"yhCshDjxhTd"+xh+"\"></td>").appendTo($(trObj));
		$("<td style=\"display: none;\"><input type=\"hidden\" class=\"cwDjxh\" value=\"\"/></td>").appendTo($(trObj));
		$("<td align=\"center\"><input type=\"text\" class=\"pop_input bgstyle_optional bz\" name=\"bz\" value=\"\"/></td>").appendTo($(trObj));	
		
		$(trObj).appendTo($("#qtsrTbody"));
		addQtsrInit();
	}
	function addQtsrInit() {
		var xh = $("#qtsrTbody tr").length;
		var zcflDiv = $("#zcflDiv").html();
		var yhDiv = $("#yhDiv").html();
		$("#zcflDmTd"+xh).html(zcflDiv);
		$("#yhCshDjxhTd"+xh).html(yhDiv);
		
		onZcfl();
	}
	function changeBh() {
		var bhs = $(".bh");
		$.each(bhs, function(i, obj){
			$(obj).text(i+1);
		});
	}
	function delQtsr() {
		var xhs = $(":checked[name='xhs'][value!='']");
		//alert(xhs.length);
		if (xhs.length > 0) {
			var jsonStr = getJqueryParam(xhs, "domain.qtsrDomain.cwDjxhs");
			var url = jcontextPath+"/cwgl/cwzjrb!deleteQtsr";
			ajaxCommon(url,encodeURI(jsonStr),"doDelQtsrSuc", false);
		}else {
			doDelQtsrSuc(-1);
		}
	}
	function doDelQtsrSuc(obj){
		var xhs = $(":checked[name='xhs']");
		$.each(xhs, function(i, obj){
			var td = $(obj).parent();
			var tr = $(td).parent();
			$(tr).remove();
		});
		changeBh();
	}
	function initQtsrXl(){
		var mxts = $("#qtsrTbody tr").length;
		for(var i=0;i<mxts;i++){
			var zcflDm = $(".zcflDmTd")[i].innerText;
			
			//alert(yhCshDjxh);
			$("#zcflDiv select").val(zcflDm);
			

			var zcflDiv = $("#zcflDiv").html();
			
			
			$(".zcflDmTd")[i].innerHTML=zcflDiv;
			
		}
		onZcfl();
	}
	function onZcfl(){
			var objs =$(".zcflDm");
			var yhCshDjxhTds = $(".yhCshDjxhTd");
			$.each(yhCshDjxhTds,function(i,obj){
				if(objs[i].value=="11"){
					obj.innerHTML="<select class=\"select yhCshDjxh\"><option value =\"\">现金</option></select>";
				}else if(objs[i].value=="13"){
					obj.innerHTML="<select class=\"select yhCshDjxh\"><option value =\"\">油卡</option></select>";
				}else{
					var yhCshDjxh = $(".yhCshDjxhHid")[i].value;
					//alert(yhCshDjxh);
					$("#yhDiv select").val(yhCshDjxh);
					
					$(".yhCshDjxhTd")[i].innerHTML=$("#yhDiv").html();
				}
			})
	}
</script>
</head>

<body>
<%try{ %>
<s:form action="cwzjrb!initMx" namespace="" method="post" id="mainForm" name="mainForm">
		<div class="pop_contc" >
			<fieldset>
			<legend>基本信息</legend>
			
			<table width="96%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
					<td width="14%" align="right">单位：</td>
      				<td width="18%" align="left">
      					<sys:qxGsList myId="mainForm_domain_qtsrDomain_ssJgbm" myName="domain.qtsrDomain.ssJgbm" mcContainDmBz="Y" myClass="select" ></sys:qxGsList>
	  			    </td>
      				<td width="14%" align="right">日期：</td>
      				<td width="18%" align="left"><s:textfield name="domain.qtsrDomain.rq" cssClass="pop_input bgstyle_readonly" disabled="true"></s:textfield></td>
				</tr>
			</table>
		</fieldset>
			
		<div style="margin:20px 0 20px 0;">
			<table id="zbTab" width="95%" border="0" cellspacing="0" cellpadding="0" class="poptab_css" align="center">
		      <tr>
		        <th width="5%">序号</th>
		        <th width="5%"><input type="checkbox" name="jsCheck" onclick="checkJs(this)"/></th>
		        <th width="13%">项目名称</th>
		        <th width="13%">付款方</th>
		        <th width="10%">金额</th>
		        <th width="17%">资产分类</th>
		        <th width="17%">资产</th>
		        <th width="15%">备注</th>
		      </tr>
		       <tbody id="qtsrTbody">
     		  <s:iterator id="zb" value="domain.qtsrDomain.dataList" status="sta">
     		 
		      	<tr>
		        	<td align="center" class="bh"><s:property value="#sta.index+1" /></td>
			        <td align="center"><input type="checkbox" name="xhs" value="<s:property value="#zb.cwDjxh" />" /></td>
			        <td align="center"><input type="text" class="pop_input bgstyle_optional xmmc" name="xmmc"  value="<s:property value="#zb.xmmc" />"/></td>
			        <td align="center"><input type="text" class="pop_input bgstyle_optional fkf" name="fkf"  value="<s:property value="#zb.fkf" />"/></td>
			       	<td align="center"><input type="text" class="pop_input bgstyle_optional je" name="je" value="<s:property value="#zb.je" />"/></td>
			       	<td class="zcflDmTd"><s:property value="#zb.zcflDm" /></td>
			       	<td style="display: none;"><s:hidden cssClass="yhCshDjxhHid" name="#zb.yhCshDjxh"></s:hidden></td>
			        <td class="yhCshDjxhTd"><s:property value="#zb.yhCshDjxh" /></td>
			        <td align="center"><input type="text" class="pop_input bgstyle_optional bz" name="bz" value="<s:property value="#zb.bz" />"/></td>
			        <td style="display: none;"><input type="hidden" class="cwDjxh" value="<s:property value="#zb.cwDjxh" />" /></td>
		      	</tr>
		     </s:iterator>
		     </tbody>
		    </table>
		</div>

			<div class="pop_btn">
				<button type="button" class="pop_btnbg" id="addBtn">新 增</button>
			 	&nbsp;
			 	<button type="button" class="pop_btnbg" id="deleteBtn">删 除</button>
			 	&nbsp;
			 	<button type="button" class="pop_btnbg" id="saveBtn">保 存</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		    </div>
		</div>
	<div id="zcflDiv" style="display: none;"><sys:ZcflCsh myName="domain.zcflDm" myId="mainForm_domain_zcflDm" myClass="select zcflDm" onChange="onZcfl()" ssJgbm="domain.qtsrDomain.ssJgbm"></sys:ZcflCsh></div>
	<div id="yhDiv" style="display: none;"><sys:yhzh myName="domain.yhCshDjxh" myId="mainForm_domain_yhCshDjxh" myClass="select yhCshDjxh" ssJgbm="domain.qtsrDomain.ssJgbm"></sys:yhzh></div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>

