<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>收入对帐清单</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		
		var sfKpBz = $("input[name='domain.sfKpBz']:checked").val();
		if(undefined==sfKpBz || null==sfKpBz || ""==sfKpBz) {
			document.getElementsByName('domain.sfKpBz')[0].checked=true;//默认开票
		}			
		
		$("#addBtn").click(function(){
			var errCode1 = $("#mainForm_domain_errCode1").val();
			var errCode2 = $("#mainForm_domain_errCode2").val();
			//alert(errCode1);
			if(errCode1 /1 > 0){
				showAlert("该清单已经开票申请，不能添加。");
				return;
			}
			
			if(errCode2 /1 > 0){
				showAlert("该清单已经支付登记，不能添加。");
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
				showAlert("该清单已经开票申请，不能保存。");
				return;
			}
			
			if(errCode2 /1 > 0){
				showAlert("该清单已经支付登记，不能保存。");
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
				showAlert("请先选择需要删除的收入对账清单明细！");
				return;
			}
			
			var errCode1 = $("#mainForm_domain_errCode1").val();
			var errCode2 = $("#mainForm_domain_errCode2").val();
			//alert(errCode1);
			if(errCode1 /1 > 0){
				showAlert("该清单已经开票申请，不能删除。");
				return;
			}
			
			if(errCode2 /1 > 0){
				showAlert("该清单已经支付登记，不能删除。");
				return;
			}
			
			showConfirm("确定要删除选中的收入对账清单明细信息？", "delDzQdMx");
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
		//showSuccess("保存成功！","doYesCallBack");
		showSuccess("保存成功！","saveSucYesCallBack");
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
        showAlert("删除成功！");
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
		var labelNameArray = ["客户名称","清单名称",
		                      "金额","创建日期","结算单位"];
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
				<legend>基本信息</legend>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
					<tr>
	      				<td width="15%" align="right"><font class="font_red">*</font>清单名称：</td>
	      				<td width="35%">
	      					<s:textfield name="domain.qdmc" cssClass="pop_input noborder bgstyle_required" ></s:textfield>
	      				</td>
	      				<td width="15%" align="right"><font class="font_red">*</font>是否开票：</td>
	      				<td width="35%" id="hzfsTd">
	      				 	<s:radio name="domain.sfKpBz" list='#{"Y":"是","N":"否" }' listKey="key" listValue="value"></s:radio>
	      				</td>
	      			</tr>
	      			<tr>
	      				<td align="right"><font class="font_red">*</font>合计金额：</td>
	      				<td>
	      					<s:textfield name="domain.heJe" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      				<td width="15%" align="right">结算单位：</td>
	      				<td width="35%">
	      					<s:textfield name="domain.jsdw" cssClass="pop_input noborder bgstyle_required"></s:textfield>	      				
	      				</td>	      				
	      			</tr>
					<tr>
						<td align="right"><font class="font_red">*</font>业务单位：</td>
	      				<td>
	      					<s:textfield name="domain.dwMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      				<td align="right"><font class="font_red">*</font>部门：</td>
	      				<td>
	      					<s:textfield name="domain.bmMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      			</tr>
	      			<tr>
	      				<td align="right"><font class="font_red">*</font>创建人：</td>
	      				<td>
	      					<s:textfield name="domain.cjrMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      				<td align="right"><font class="font_red">*</font>创建日期：</td>
	      				<td>
	      					<s:textfield name="domain.djrq" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      			</tr>
				</table>
			</fieldset>
			<br />
			<table id="zbTab" width="980" border="0" cellspacing="0" cellpadding="0" class="poptab_css">
		      <tr>
		      	<th width="30">序号</th>
		      	<th width="30"><input type="checkbox" name="jsCheck" onclick="checkJs(this)"/></th>
		      	<th width="60">来源</th>
		      	<th width="60">对账金额</th>
		      	<th width="70">未结</th>
		        <th width="60">差异金额</th>
		        <th width="50">结果</th>
		        <th width="70">订单编号</th>
		        <th width="70">下单日期</th>
		        <th width="100">货物名称</th>
		        <th width="50">包装</th>
		        <th width="80">回单编号</th>
		        <th width="50">始发地</th>
		        <th width="50">目的地</th>
		        <th width="50">数量</th>
		        <th width="50">重量</th>
		        <th width="50">体积</th>
		      </tr>
		      <s:iterator value="domain.dataList" id="domain" status="sta">
		      	<tr>
	      			<td align="center" class="bh"><s:property value="#sta.index+1" /></td>
		      		<td align="center"><input type="checkbox" name="xhs" value="<s:property value="#domain.ywDjxh" />" /></td>
		      		<td align="center">
		      			<s:if test='1 == #domain.ywLydm'>收入对账</s:if>
		      			<s:elseif test="2 == #domain.ywLydm">费用登记</s:elseif>
		      			<s:elseif test="3 == #domain.ywLydm">物流损失</s:elseif>
		      		</td>
		      		<td align="center"><s:property value="#domain.dzje" /></td>
		      		<td align="center"><s:property value="#domain.jsWj" /></td>
		      		<td align="center"><s:property value="#domain.dzcyje" /></td>
		      		<td align="center">
		      		<s:if test='"Y"==#domain.dzcybz'>
		      			<font color="red">有差异</font>
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
				来源：<s:select id="ywLydm" name="ywLydm" list="#{'1':'收入对账','2':'费用登记','3':'物流损失' }"></s:select>
				<button type="button" class="pop_btnbg" id="addBtn">添 加</button>
			 	&nbsp;
			 	<button type="button" class="pop_btnbg" id="deleteBtn">删 除</button>
			 	&nbsp;
			 	<button type="button" class="pop_btnbg" id="saveBtn">保 存</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		    </div>
	</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
