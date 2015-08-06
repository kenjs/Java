<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>费用登记管理</title>
<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		});
		
		$("#saveBtn").click(function(){
			if(!checkSzje()){
				return;
			}
			var url = jcontextPath+"/hygl/fydjgl!save"
			
			var fyclfsDm = $("#mainForm_domain_fyclfsDm").val();
			var khDjxh = $("#mainForm_domain_khDjxh").val();
			
			if(fyclfsDm == "11" || fyclfsDm == "21"){
				if(khDjxh == ""){
					showAlert("名称不能为空!");
					return ;
				}
			}
			$("#mainForm").attr("action",url);
			$("#mainForm").submit();
		});
		
		$("#updateBtn").click(function(){
			$("#mainForm_domain_srje").val('');
			$("#mainForm_domain_zfje").val('');
			$(":[name='domain.skfCzyDjxh']").val('');
			var rowLen = $(":checked[name='delSel']");
			//alert(rowLen);
			if (rowLen.length == 0) {
				showAlert("请先选择要修改的数据！");
				return;
			}else if(rowLen.length != 1){
				showAlert("一次只能修改一条数据！");
				return;
			}
			$("#mainForm_domain_selOrUpd").val(2);
			var tabLen = document.getElementById("zTab").rows;
			var cBoxs = $(":checkbox[name='delSel']");
			for(var i=0;i<cBoxs.length;i++){
				if(cBoxs[i].checked){
				  var srje = tabLen[i+1].cells[2].innerHTML;
				  var zfje = tabLen[i+1].cells[3].innerHTML;
				  var skf = $(tabLen[i+1].cells[5].innerHTML).val();
				  var fyxm = tabLen[i+1].cells[10].innerHTML;
				  var fyDjxh = tabLen[i+1].cells[11].innerHTML;
				  
				  $("#mainForm_domain_fyDjxh").val(fyDjxh);
				  $("#mainForm_domain_fydjxmWhXh").val(fyxm);
				  $("#mainForm_domain_srje").val(srje);
				  $("#mainForm_domain_zfje").val(zfje);
				  $(":[name='domain.skfCzyDjxh']").val(skf);
				}
			}
		});
		
		$("#sendBtn").click(function(){
			var wsDm="303001";//费用登记审批表
			var fyDjxh = $("#mainForm_domain_fyDjxh").val();
			var oldWsspxh = $("#mainForm_domain_wsSpxh").val();
			scSend(wsDm,"",fyDjxh,oldWsspxh);
		});
		
		$("#delBtn").click(function(){
			var rowLen = $("#zTab tr").length;
			if (rowLen <= 1) {
				showAlert("没有可删除的数据！");
				return;
			}
			var khDjxh = $("#mainForm_domain_khDjxh").val();
			var pcDjxh = $("#mainForm_domain_pcDjxh").val();
			var wfhDjxh = $("#mainForm_domain_wfhDjxh").val();
			var ddDjxh = $("#mainForm_domain_ddDjxh").val();
			var jsonStr = "";
			var nu = $(":checkbox[name='delSel']");
			var tabLen = document.getElementById("zTab").rows;
			for(var i=0;i<nu.length;i++){
				if(nu[i].checked){
				  var index =  tabLen[i+1].cells[0].innerHTML;
				  var fyDjxh = tabLen[i+1].cells[11].innerHTML;				  
				  jsonStr += fyDjxh+","+index+"|";
				}
			}
			if(jsonStr == ""){
				showAlert("请先选择要删除的数据！");
				return;
			}
			var jsonObj = {"domain.jsonStr":jsonStr,"domain.pcDjxh":pcDjxh,"domain.wfhDjxh":wfhDjxh,"domain.khDjxh":khDjxh,"domain.ddDjxh":ddDjxh};
			var url = jcontextPath+"/hygl/fydjgl!delete";
			ajaxCommon(url,jsonObj,"deleSuccess");
		});
		
		var sj = $("#mainForm_domain_pcDjxh").val();
		commonInit("KHMC", sj, '', "domain.khDjxh", "mainForm_domain_khDjxh", "Y", "N");
		
		//var kh = $("#mainForm_domain_khDjxh").val();
		//initSel(sj,kh);
		//$("#mainForm_domain_khDjxh").change(function(){
		//	initSel($("#mainForm_domain_pcDjxh").val(),this.value);
		//});
		
	});
	
	function doScSendSuc(data) {
		hideMessage();
		showAlert("发送成功！","refreshList");
	}
	
	function refreshList() {
		var url = jcontextPath+"/hygl/fydjgl!initMx.action"
		
		$("#mainForm").attr("action",url);
		$("#mainForm").submit();
	}
	
	function onUpdate(obj) {
		$("#mainForm_domain_srje").val('');
		$("#mainForm_domain_zfje").val('');
		$(":[name='domain.skfCzyDjxh']").val('');		
		$("#mainForm_domain_selOrUpd").val(2);
		
		var rowInedx = $(obj).parents("tr").index();		
		var tabLen = document.getElementById("zTab").rows;
		  var srje = tabLen[rowInedx].cells[3].innerHTML;
		  var zfje = tabLen[rowInedx].cells[4].innerHTML;
		  var skf = $(tabLen[rowInedx].cells[6].innerHTML).val();
		  var fyxm = tabLen[rowInedx].cells[11].innerHTML;
		  var fyDjxh = tabLen[rowInedx].cells[12].innerHTML;
		  
		  $("#mainForm_domain_fyDjxh").val(fyDjxh);
		  $("#mainForm_domain_fydjxmWhXh").val(fyxm);
		  $("#mainForm_domain_srje").val(srje);
		  $("#mainForm_domain_zfje").val(zfje);
		  $(":[name='domain.skfCzyDjxh']").val(skf);
	}
	
	var fyDjxhGlo;
	function onDel(fyDjxh) {
		fyDjxhGlo = fyDjxh;
		showConfirm("确定要删除该记录吗？", "doDel");
	}
	
	function doDel() {
		var khDjxh = $("#mainForm_domain_khDjxh").val();
		var pcDjxh = $("#mainForm_domain_pcDjxh").val();
		var wfhDjxh = $("#mainForm_domain_wfhDjxh").val();
		var ddDjxh = $("#mainForm_domain_ddDjxh").val();
		var jsonObj = {"domain.fyDjxh":fyDjxhGlo,"domain.pcDjxh":pcDjxh,"domain.wfhDjxh":wfhDjxh,"domain.khDjxh":khDjxh,"domain.ddDjxh":ddDjxh};
		var url = jcontextPath+"/hygl/fydjgl!delete";
		ajaxCommon(url,jsonObj,"deleSuccess");
	}
	
	function checkHw(obj) {
		$(":checkbox[name='delSel']").attr("checked", obj.checked);
	}
	
	function deleSuccess(){
		showAlert("删除成功！","refreshWin");
	}
	function refreshWin() {
		var url = jcontextPath+"/hygl/fydjgl!initMx";
		$("#mainForm").attr("action",url);
		$("#mainForm").submit();
	}
	function closeWin(){
		window.close();
	}
	
	function initSel(val1,val2){
		var url = jcontextPath+"/hygl/fydjgl!queryKhHw";
		var jsonObj = {"domain.pcDjxh":val1,"domain.khDjxh":val2};
		ajaxCommon(url,jsonObj,"doInitSel");
	}
	
	/**function  doInitSel(data){
		$("#mainForm_domain_wfhDjxh").empty();
		var list = data.domain.khHwList;
		var s = document.getElementById('mainForm_domain_wfhDjxh');
		var option = document.createElement('option');
        s.options.add(option)
      	option.text = "---请选择---";
      	option.value = "";
		
		for(var i=0;i<list.length;i++){
			var option = document.createElement('option');
			s.options.add(option)
      		option.text = list[i].mc;
      		option.value = list[i].dm;
		}
	}**/
	
	function checkSzje(){
		var srje = trim($("#mainForm_domain_srje").val());
		var zfje = trim($("#mainForm_domain_zfje").val());
		if(isNaN(srje)){
			showAlert("[收入金额]内请录入数字");
			return false;
		}
		if(isNaN(zfje)){
			showAlert("[支付金额]内请录入数字");
			return false;
		}
		if(srje == '' && zfje == ''){
			showAlert("[收入金额]和[支付金额]请至少录入一项！");
			return false;
		}
		return true;
	}
</script>
<base target="_self" />
</head>

<body>
<%try{ %>
<s:form action="fydjgl!initMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
		<s:hidden name="domain.pcDjxh"></s:hidden>
		<s:hidden name="domain.wfhDjxh"></s:hidden>
		<s:hidden name="domain.clsxDm"></s:hidden>
		<s:hidden name="domain.fyDjxh"></s:hidden>
		<s:hidden name="domain.ssJgbm"></s:hidden>
		<s:hidden name="domain.ddDjxh"></s:hidden>
		<s:hidden name="domain.xh"></s:hidden>
		<s:hidden name="domain.khDjxh"></s:hidden>
		<s:hidden name="domain.zgsbm"></s:hidden>
		<s:hidden name="domain.selOrUpd"></s:hidden>
		<s:hidden name="domain.fkfCzyDjxh"/>
		
		<div class="pop_contc" >
		<fieldset>
			<legend>基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="15%" align="right">派车单号：</td>
      				<td width="35%">
      					<s:textfield name="domain.pcdh" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td width="15%" align="right">司机：</td>
      				<td width="35%">
      					<s:textfield name="domain.cyrSjxm" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">车辆号码：</td>
      				<td>
      					<s:textfield name="domain.cyrClhm" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      				<td align="right">挂车号码：</td>
      				<td>
      					<s:textfield name="domain.cyrGchm" cssClass="pop_input bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">派车人：</td>
      				<td>
      					<s:textfield name="domain.pcrMc" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">派车日期：</td>
      				<td>
      					<s:textfield name="domain.pcrq" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">派车部门：</td>
      				<td>
      					<s:textfield name="domain.pcJgmc" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right">业务单位：</td>
      				<td>
      					<s:textfield name="domain.ssJgmc" cssClass="pop_input bgstyle_readonly" ></s:textfield>
      				</td>
      			</tr>
      		
			</table>
			</fieldset>
			&nbsp;
			<div style="overflow:auto;">已登记费用信息：</div>
			<table id="zTab" width="100%" border="0" cellspacing="0" cellpadding="0" class="poptab_css">
			      <tr>
			        <th width="4%">序号</th>
			        <th width="5%">状态</th>
			        <th width="8%">收入金额</th>
			        <th width="8%">支付金额</th>
			        <th width="10%">付款方</th>
			        <th width="14%">收款方</th>
			        <th width="13%">费用项目</th>			        
			         <th width="12%">订单编号</th>
			        <th width="15%">客户名称</th>
			        <th width="11%">货物名称</th>			        	    
			      </tr>
			      <s:iterator id="zb" value="domain.fyDjMxList" status="i">
			      	<s:if test="domain.fyDjxh==#zb.fyDjxh">
			      	<tr>
				        <td align="center"><s:property value="#i.index+1"/></td>
				        <td align="center">
				        	<s:property value="#zb.wsspztMc" />
				        </td>
				        <td align="center"><s:property value="#zb.srje"/></td>
				         <td align="center"><s:property value="#zb.zfje"/></td>
				        <td align="center"><s:property value="#zb.khmc"/></td>				       
				        <td align="center">
				        	<s:select list="domain.skfList" listKey="dm" listValue="mc" cssClass="select" name="#zb.skfCzyDjxh" disabled="true">				        		
				        	</s:select>
				        </td>
				        <td align="center"><s:property value="#zb.fydjXmmc"/></td>
				        <td align="center"><s:property value="#zb.ddbh"/></td>
				        <td align="center"><s:property value="#zb.khmc"/></td>
				       <td align="center"><s:property value="#zb.hwmc"/></td>
				       <td style="display: none;"><s:property value="#zb.fydjxmWhXh"/></td>				        
				        <td style="display: none;"><s:property value="#zb.fyDjxh" /></td>				        
			      	</tr>
			      	</s:if>
			      </s:iterator>
    		</table>
    	</div>	    				
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>