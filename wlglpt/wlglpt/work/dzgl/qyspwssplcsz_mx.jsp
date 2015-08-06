<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>审批流程定义</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		var existsBz=$("#mainForm_domain_existsBz").val();
		if(existsBz!="Y")
			initList();
			
		$("#closeBtn").click(function(){
			window.close();
		});
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var qzxsbz = $("input[name='domain.qzxsbz']:checked").val();
			if(undefined==qzxsbz || null==qzxsbz || ""==qzxsbz){
				showAlert("请选择权重系数！");
				return;
			}
			var gzrbz = $("input[name='domain.gzrbz']:checked").val();
			if(undefined==gzrbz || null==gzrbz || ""==gzrbz){
				showAlert("请选择工作日还是自然日！");
				return;
			}
		    doSave();
		});
		
		$("#addJdBtn").click(function(){
			var splcSzxh = $("#mainForm_domain_splcSzxh").val();
			var wsspmsDm = $("#mainForm_domain_wsspmsDm").val();
			if (splcSzxh == null || splcSzxh == "") {
				showAlert("请先保存当前页面再新增！");
				return;
			}
			var url = jcontextPath+"/dzgl/qyspwssplcsz!initSpjcMx?domain.spjcDomain.splcSzxh="+splcSzxh+"&domain.wsspmsDm="+wsspmsDm+"&num="+Math.random();
			window.showModalDialog(url,window,"dialogHeight:460px;dialogWidth:660px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:no;maximize:no;")
			initMx();
			//popwindow(url, 760, 488);
		});
		
		$("#delJdBtn").click(function(){
			var splcSzxh = $("#mainForm_domain_splcSzxh").val();
			var rowLen = $("#zbTab tr").length;
			if (splcSzxh == null || splcSzxh == "" || rowLen <= 1) {
				showAlert("没有可删除的数据！");
				return;
			}
			
			showConfirm("确定要删除审批级次最大的审批环节吗？", "deleteSplcZb");
		});
		
		$("#cxszBtn").click(function(){
			var splcSzxh = $("#mainForm_domain_splcSzxh").val();
			if(undefined==splcSzxh || null==splcSzxh || ""==splcSzxh){
				showAlert("数据出错！");
				return;
			}
			showConfirm("确定要重新设置审批流程吗？", "cxszSplc");
		});
		
		$("#qxszBtn").click(function(){
			var splcSzxh = $("#mainForm_domain_splcSzxh").val();
			if(undefined==splcSzxh || null==splcSzxh || ""==splcSzxh){
				showAlert("数据出错！");
				return;
			}
			showConfirm("确定要取消流程设置吗？", "qxszSplc");
		});
	});
	
	function doSave() {
		var dwDm = trim($("#mainForm_domain_dwDm").val());
		var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
		var wsDm = trim($("#mainForm_domain_wsDm").val()); 
		var xmflDjxh = trim($("#mainForm_domain_xmflDjxh").val()); 
		var zssx = trim($("#mainForm_domain_zssx").val()); 
		if(zssx/1<0){
			showAlert("审批时限不能小于零！");
			return;
		}
		var gzrbz = $("input[name='domain.gzrbz']:checked").val();
		if(undefined==gzrbz || null==gzrbz || ""==gzrbz)
			gzrbz="";
		var qzxsbz = $("input[name='domain.qzxsbz']:checked").val();
		if(undefined==qzxsbz || null==qzxsbz || ""==qzxsbz)
			qzxsbz="";
		
		var splcSzxh = trim($("#mainForm_domain_splcSzxh").val()); 
		var curDwbm = $("#mainForm_domain_curDwbm").val();
		var existsBz = $("#mainForm_domain_existsBz").val();
		var wsspmsDm = $("#mainForm_domain_wsspmsDm").val();
		
		var url = jcontextPath+"/dzgl/qyspwssplcsz!save";  
    	var jsonObj = {"domain.dwDm":dwDm,"domain.ssJgbm":ssJgbm,"domain.wsDm":wsDm,"domain.xmflDjxh":xmflDjxh,
                       "domain.zssx":zssx,"domain.gzrbz":gzrbz,"domain.qzxsbz":qzxsbz,"domain.splcSzxh":splcSzxh,
                       "domain.curDwbm":curDwbm,"domain.existsBz":existsBz,"domain.wsspmsDm":wsspmsDm};   			
		ajaxCommon(url,jsonObj,"saveSuc");
	}
	
	function saveSuc(data) {
		var splcSzxh = data.domain.splcSzxh;
		$("#mainForm_domain_splcSzxh").val(splcSzxh);
		hideMessage();
		showSuccess("保存成功！","doYesCallBack");
	}
	
	function deleteSplcZb() {
		$("#mainForm").attr("action", "qyspwssplcsz!deleteSplcZb");
		mainForm.submit();
	}
	
	function onUpdate(jdxh) {
		var splcSzxh = $("#mainForm_domain_splcSzxh").val();
		var wsspmsDm = $("#mainForm_domain_wsspmsDm").val();
		var url = jcontextPath+"/dzgl/qyspwssplcsz!initSpjcMx?domain.spjcDomain.splcSzxh="+splcSzxh+"&domain.spjcDomain.jdxh="+jdxh+"&domain.wsspmsDm="+wsspmsDm;
		window.showModalDialog(url,window,"dialogHeight:460px;dialogWidth:660px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:no;maximize:no;")
		initMx();
		//popwindow(url, 760, 488);
	}
	function cxszSplc(){
		$("#mainForm").attr("action", "qyspwssplcsz!cxszSplc");
		mainForm.submit();
	}
	function qxszSplc(){
		var splcSzxh = $("#mainForm_domain_splcSzxh").val();
		var url = jcontextPath+"/dzgl/qyspwssplcsz!qxszSplc";  
    	var jsonObj = {"domain.splcSzxh":splcSzxh};   			
		ajaxCommon(url,jsonObj,"doQxszSplcSuccess");
	}
	//成功后的数据处理 提示保存成功，关闭当前页面，并调用父窗口的刷新函数
	function doQxszSplcSuccess(data){
		hideMessage();
		showSuccess("取消设置成功！","doYesCallBack2");
	}
	
	//选择是的返回处理
	function doYesCallBack2(){
		//parent.initMx();
		window.close();
	}
	function initList() {
		var dwDm = $("#mainForm_domain_dwDm").val(); 
		var ssJgbm = $("#mainForm_domain_ssJgbm").val(); 
		var jsonObj = {"domain.paramdm":dwDm,
			"domain.defaultValue":ssJgbm,
			"domain.currentObjName":"domain.ssJgbm",
			"domain.currentObjId":"mainForm_domain_ssJgbm",
			"domain.containQbBz":"Y",
			"domain.mcContainDmBz":"Y"};
	
		var url=jcontextPath+"/common/wlglptCommon!bmInit";	
		ajaxCommon(url,jsonObj,"changeBmList");
	}
	function changeBmList(data){
		var list = data.domain.dataList;
		$("#"+data.domain.currentObjId).empty();
		$.each(list, function(i,domain){
			var option = document.createElement('option');
		    $("#"+data.domain.currentObjId)[0].add(option);
		    
		    $(option).text(domain.mc).val(domain.dm);
		    //默认选中
		    if(data.domain.defaultValue==domain.dm){
		    	$(option).attr("selected","selected");
		    	$(option).text(domain.mc).val(domain.dm);
		    }
		});
	}
	
	function checkdata(){
		var controlNameArray = ["domain.wsDm","domain.xmflDjxh","domain.zssx"];
		var labelNameArray = ["文书","项目分类","终审时限"];
		var compareValueArray = [6,16,
			                     4.2];
		var nodeTypeArray = [NodeType.STRING,NodeType.INTEGER,NodeType.DECIMAL];
		var notNullArray = [true,false,true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	
	function initMx() {
		$("#mainForm").attr("action", "qyspwssplcsz!initMx");
		mainForm.submit();
	}
</script>
<base target="_self" />
</head>

<body>
<%
try {
%>
<s:form action="qyspwssplcsz!initMx" namespace="/dzgl" method="post"
	id="mainForm" name="mainForm">
	<s:hidden name="domain.splcSzxh" />
	<s:hidden name="domain.curDwbm" />
	<s:hidden name="domain.xmflDjxh" />
	<s:hidden name="domain.existsBz" />

	<div class="pop_contc" style="height:520px; overflow:auto;">
	<fieldset><legend>文书信息</legend>
	<table width="100%" border="0" cellspacing="0" cellpadding="0"
		class="poptabinfo_css">
		<s:if test='domain.existsBz=="Y"'>
			<tr>
				<td width="15%" align="right"><font class="font_red">*</font>设置单位：</td>
				<td width="35%">
					<s:hidden name="domain.dwDm"></s:hidden>
					<s:textfield name="domain.dwMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield></td>
				<td width="15%" align="right">设置部门：</td>
				<td width="35%">
					<s:hidden name="domain.ssJgbm"></s:hidden>
					<s:textfield name="domain.ssJgmc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
				</td>
			</tr>
		</s:if>
		<s:else>
			<tr>
				<td width="15%" align="right"><font class="font_red">*</font>设置单位：</td>
				<td width="35%"><sys:gsList myId="mainForm_domain_dwDm"
					myName="domain.dwDm" mcContainDmBz="Y" myClass="select"
					onChange="initList()" /></td>
				<td width="15%" align="right">设置部门：</td>
				<td width="35%">
					<select name="domain.ssJgbm" id="mainForm_domain_ssJgbm" class="select">
						<option value="${domain.ssJgbm}"></option>
					</select>
				</td>
			</tr>
		</s:else>
		<tr>
			<td width="15%" align="right">文书代码：</td>
			<td width="35%"><s:textfield name="domain.wsDm"
				cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
			</td>
			<td align="right">文书名称：</td>
			<td><s:textfield name="domain.wsMc"
				cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield></td>
		</tr>
		<tr>
			<td align="right">项目分类：</td>
			<td><s:textfield name="domain.xmflmc"
				cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield></td>
			<td align="right">文书审批模式：</td>
			<td>
				<s:hidden name="domain.wsspmsDm"></s:hidden>
				<s:textfield name="domain.wsspmsMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield></td>
		</tr>
		<tr>
			<td align="right">业务分类：</td>
			<td><s:textfield name="domain.ywflMc"
				cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield></td>
			<td align="right">业务环节：</td>
			<td><s:textfield name="domain.ywhjMc"
				cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield></td>
		</tr>
		<s:if test='domain.splcSzxh==null || domain.splcSzxh==""'>
			<tr>
				<td align="right"><font class="font_red">*</font>审批时限：</td>
				<td><s:textfield name="domain.zssx"
					cssClass="pop_input noborder bgstyle_required" cssStyle="width:25%;"></s:textfield>
				&nbsp;&nbsp; <s:radio name="domain.gzrbz"
					list='#{"1":"工作日","2":"自然日"}' listKey="key" listValue="value" value="1"></s:radio></td>
				<td align="right"><font class="font_red">*</font>权重系数：</td>
				<td><s:radio name="domain.qzxsbz" list="#{'Y':'是','N':'否' }"
					listKey="key" listValue="value" value="'N'"></s:radio></td>
			</tr>
		</s:if>
		<s:else>
			<tr>
				<td align="right"><font class="font_red">*</font>审批时限：</td>
				<td><s:textfield name="domain.zssx"
					cssClass="pop_input noborder bgstyle_required" cssStyle="width:25%;"></s:textfield>
				&nbsp;&nbsp; <s:radio name="domain.gzrbz"
					list='#{"1":"工作日","2":"自然日"}' listKey="key" listValue="value"></s:radio></td>
				<td align="right"><font class="font_red">*</font>权重系数：</td>
				<td><s:radio name="domain.qzxsbz" list="#{'Y':'是','N':'否' }"
					listKey="key" listValue="value"></s:radio></td>
			</tr>
		</s:else>
		<tr>
			<td align="right">说明：</td>
			<td colspan="3"><s:textarea name="domain.sm" rows="3"
				cssClass="pop_textarea_colspan2 bgstyle_readonly" readonly="true"></s:textarea></td>
		</tr>
	</table>
	</fieldset>
	<div class="pop_btn">
		<s:if test='"Y"==domain.saveBz'>
			<button type="button" class="pop_btnbg" id="saveBtn">保 存</button>&nbsp;
			<button type="button" class="pop_btnbg" id="addJdBtn">添加节点</button>&nbsp;
			<button type="button" class="pop_btnbg" id="delJdBtn">删除节点</button>&nbsp;
		</s:if>
		<s:if test='"Y"==domain.cxszbz'>
			<button type="button" class="pop_btnbg" id="cxszBtn">重新设置</button>&nbsp;
		</s:if>
		<s:if test='"Y"==domain.qxszbz'>
			<button type="button" class="pop_btnbg" id="qxszBtn">取消设置</button>&nbsp;
		</s:if>
		<button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
	</div>
	<table id="zbTab" width="1145" border="0" cellspacing="0"
		cellpadding="0" class="poptab_css">
		<tr>
			<th width="30">序号</th>
			<th width="110">审批节点说明</th>
			<s:if test='domain.wsspmsDm=="1"'>
				<th width="100">审批机构级别</th>
			</s:if>
			<s:else>
				<th width="100">审批单位</th>
				<th width="80">审批部门</th>
				<th width="65">审批岗位</th>
			</s:else>
			<th width="110">审批环节说明</th>
			<th width="90">审批意见栏</th>
			<th width="80">审批签名</th>
			<th width="60">允许终审</th>
			<th width="80">审批时限</th>
			<th width="60">权重系数</th>
			<th width="70">创建人</th>
			<th width="70">创建日期</th>
			<th width="70">修改人</th>
			<th width="70">修改日期</th>
		</tr>
		<s:iterator id="zb" value="domain.zbList">
			<tr>
				<td align="center"><s:property value="#zb.jdxh" /></td>
				<td align="center">
				  <a href="javascript:onUpdate('<s:property value="#zb.jdxh" />')"><font color="blue"><s:property value="#zb.spjdsm" /></font></a>
				</td>
				<s:if test='domain.wsspmsDm=="1"'>
					<td align="center"><s:property value="#zb.spjgjbMc" /></td>	
				</s:if>
				<s:else>
					<td align="center"><s:property value="#zb.dwMc" /></td>
					<td align="center"><s:property value="#zb.spJgmc" /></td>
					<td align="center"><s:property value="#zb.gwMc" /></td>
				</s:else>
				<td align="center"><s:property value="#zb.sphjsm" /></td>
				<td align="center"><s:property value="#zb.spyjl" /></td>
				<td align="center"><s:property value="#zb.spqm" /></td>
				<td align="center"><s:if test='#zb.yxzsBz == "Y"'>允许</s:if><s:else>不允许</s:else></td>
				<td align="center"><s:property value="#zb.spsx" /><s:if test='#zb.gzrbz == "1"'>工作日</s:if><s:else>自然日</s:else></td>
				<td align="center"><s:property value="#zb.qzxs" /></td>
				<td align="center"><s:property value="#zb.cjrMc" /></td>
				<td align="center"><s:date name="#zb.cjrq" format="yyyy-MM-dd"/></td>
				<td align="center"><s:property value="#zb.xgrMc" /></td>
				<td align="center"><s:date name="#zb.xgrq" format="yyyy-MM-dd"/></td>
			</tr>
		</s:iterator>
	</table>
	<br/>
	<table width="100%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td>
				说明：1、若您要修改审批流转信息，则点击“审批节点说明”进行修改；<br/>
	     		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、若您要修改审批级次，则点击“删除节点”或“添加节点”按钮，“删除节点”仅删除审批级次最大的记录；<br/>
	     		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、若您不想采用上级单位的文书审批流转设置，则可以点击“重新设置”按照本级单位或部门的要求进行设置；<br/>
	     		&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、若您不想采用当前设置信息，则可以点击“取消设置”，采用上级单位设置信息。
			</td>
		</tr>
	</table>
	</div>
	<%@include file="/common/message.jsp"%>
</s:form>
<%
		} catch (Exception e) {
		e.printStackTrace();
		throw e;
	}
%>
</body>
</html>
