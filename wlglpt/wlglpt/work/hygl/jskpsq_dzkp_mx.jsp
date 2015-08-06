<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>开票申请</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		$("#addBtn").click(function(){
			var existBz = trim($("#mainForm_domain_existBz").val()); 
			var djJgbm = trim($("#mainForm_domain_djJgbm").val()); 
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
			var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
			var kpsqDjxh = $("#mainForm_domain_kpsqDjxh").val();
			var url = jcontextPath+"/hygl/jskpsq!queryMx.action?domain.kpsqDjxh="+kpsqDjxh+"&domain.existBz="+existBz+"&domain.djJgbm="+djJgbm+"&domain.ssJgbm="+ssJgbm+"&domain.khDjxh="+khDjxh;
			window.showModalDialog(url,window,"dialogHeight:360px;dialogWidth:560px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:no;maximize:no;")
			initMx();
			//popwindow(url, 760, 488);	
		});
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var kpsqfsDm = trim($("#mainForm_domain_kpsqfsDm").val()); 
			//var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
			var sqKpjeHj = trim($("#mainForm_domain_sqKpjeHj").val()); 
			var sqKprq = trim($("#mainForm_domain_sqKprq").val()); 
			var bzsm = trim($("#mainForm_domain_bzsm").val()); 
			var djJgbm = trim($("#mainForm_domain_djJgbm").val()); 
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
			var kpsqDjxh = trim($("#mainForm_domain_kpsqDjxh").val()); 
			var existBz = trim($("#mainForm_domain_existBz").val()); 
			
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
			
			var url = jcontextPath+"/hygl/jskpsq!save";  
	    	var jsonObj = {"domain.kpsqfsDm":kpsqfsDm,"domain.sqKpjeHj":sqKpjeHj,
                           "domain.sqKprq":sqKprq,"domain.bzsm":bzsm,"domain.djJgbm":djJgbm,
                           "domain.ssJgbm":ssJgbm,"domain.kpsqDjxh":kpsqDjxh,"domain.existBz":existBz,
                           "domain.shf":shf,"domain.shfSbh":shfSbh,"domain.fhf":fhf,"domain.fhfSbh":fhfSbh,
                           "domain.ydrq":ydrq,"domain.dj":dj,"domain.mc":mc,"domain.sl":sl,"domain.dkf":dkf,
                           "domain.kpDwJgMc":kpDwJgMc};   			
			ajaxCommon(url,jsonObj,"doSaveYkpSuc");
		});
		$("#deleteBtn").click(function(){
			var xhs = $(":checked[name='xhs']");
			if (xhs.length <= 0) {
				showAlert("请先选择需要删除的开票申请对账清单信息！");
				return;
			}
			
			showConfirm("确定要删除选中的开票申请对账清单信息？", "delKpsqDzQd");
		});
		$("#closeBtn").click(function(){
			window.close();
		});
	});
	
	function doSaveYkpSuc(data) {
		hideMessage();
		//showSuccess("保存成功！","doYesCallBack");
		showSuccess("保存成功！","saveSucYesCallBack");
	}
	function saveSucYesCallBack() {
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
		var controlNameArray = ["domain.sqKpjeHj","domain.sqKprq","domain.bzsm","domain.kpDwJgMc"];
		var labelNameArray = ["申请开票金额","申请开票日期","备注说明","开票单位"];
		var compareValueArray = [16.2,10,200,200];
		var nodeTypeArray = [NodeType.DECIMAL,NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,false,true];				
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
		var existBz = trim($("#mainForm_domain_existBz").val()); 
		showMessage();
		if (xhs.length > 0) {
			var jsonStr = getJqueryParam(xhs, "domain.kpsqmxDjxhs");
			var jsonObj = {"domain.kpsqDjxh":kpsqDjxh,"domain.existBz":existBz};
			
			jsonStr += jQuery.param(jsonObj);
			var url = jcontextPath+"/hygl/jskpsq!deleteMx";  
			ajaxCommon(url,encodeURI(jsonStr),"doDelKpsqDzQdxxSuc", false);
		}
	}
	
	function doDelKpsqDzQdxxSuc(){ 
        hideMessage();
        showAlert("删除成功！");
        initMx();
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
		<div class="pop_contc">
			<fieldset>
				<legend>基本信息</legend>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
					<tr>
						<td width="20%" align="right"><font class="font_red">*</font>所属机构：</td>
	      				<td width="30%">
	      					<s:textfield name="domain.dwMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      				<td width="20%" align="right"><font class="font_red">*</font>登记部门：</td>
	      				<td width="30%">
	      					<s:textfield name="domain.bmMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      			</tr>	      			
	      		</table>
	      	</fieldset>
	      			<fieldset>
						<legend>开票信息</legend>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
			      			<tr>
				      			<td align="right" width="20%"><font class="font_red">*</font>申请开票金额：</td>
			      				<td width="30%">
			      					<s:textfield name="domain.sqKpjeHj" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
			      				</td>
			      				<td align="right" width="20%"><font class="font_red">*</font>申请开票日期：</td>
			      				<td width="30%">
			      					<s:textfield name="domain.sqKprq" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
			      				</td>
			      			</tr>
			      			<tr>
			      				<td align="right">收货方：</td>
			      				<td>
			      					<s:textfield name="domain.shf" cssClass="pop_input noborder bgstyle_required"></s:textfield>
			      				</td>
			      				<td align="right">收货方识别号：</td>
			      				<td>
			      					<s:textfield name="domain.shfSbh" cssClass="pop_input noborder bgstyle_required"></s:textfield>
			      				</td>
			      			</tr>
			      			<tr>
			      				<td align="right">发货方：</td>
			      				<td>
			      					<s:textfield name="domain.fhf" cssClass="pop_input noborder bgstyle_required"></s:textfield>
			      				</td>
			      				<td align="right">发货方识别号：</td>
			      				<td>
			      					<s:textfield name="domain.fhfSbh" cssClass="pop_input noborder bgstyle_required"></s:textfield>
			      				</td>
			      			</tr>			      			
			      			<tr>
			      				<td align="right">预达日期：</td>
			      				<td>
			      					<s:textfield name="domain.ydrq" readonly="true" cssClass="ymdate"></s:textfield>
			      				</td>
			      			  	<td align="right"><font class="font_red">*</font>开票单位：</td>
			      				<td>
			      					<s:textfield name="domain.kpDwJgMc" cssClass="pop_input noborder bgstyle_optional"></s:textfield>			      								      				
			      				</td>			      	
			      			</tr>
			      			<tr>			      				
			      				<td align="right">抵扣方：</td>
			      				<td>			      					
			      					<s:select list="{'收货方','发货方' }" cssClass="select" name="domain.dkf"></s:select>
			      				</td>
			      				<td align="right">单价：</td>
			      				<td>
			      					<s:textfield name="domain.dj" cssClass="pop_input noborder bgstyle_optional"></s:textfield>
			      				</td>	
			      			</tr>
			      			<tr>
			      				<td align="right">名称：</td>
			      				<td>
			      					<s:textfield name="domain.mc" cssClass="pop_input noborder bgstyle_optional"></s:textfield>
			      				</td>
			      				<td align="right">数量：</td>
			      				<td>
			      					<s:textfield name="domain.sl" cssClass="pop_input noborder bgstyle_optional"></s:textfield>
			      				</td>
			      			</tr>			      			
			      			<tr>
			      				<td align="right">备注说明：</td>
			      				<td colspan="3">
			      					<s:textarea name="domain.bzsm" rows="3" cssClass="pop_textarea noborder bgstyle_optional" ></s:textarea>
			      				</td>
			      			</tr>
			      			<tr>
			      				<td align="right"><font class="font_red">*</font>登记人：</td>
			      				<td>
			      					<s:textfield name="domain.cjrMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
			      				</td>
			      				<td align="right"><font class="font_red">*</font>登记日期：</td>
			      				<td>
			      					<s:textfield name="domain.djrq" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
			      				</td>
			      			</tr>			      			
						</table>
					</fieldset>
			<br />
			<table id="zbTab" width="750" border="0" cellspacing="0" cellpadding="0" class="poptab_css">
		      <tr>
		      	<th width="30">序号</th>
		      	<th width="30"><input type="checkbox" name="jsCheck" onclick="checkJs(this)"/></th>
		        <th width="80">申请开票金额</th>
		        <th width="160">清单名称</th>
		        <th width="80">合计金额</th>
		        <th width="85">已申请开票金额</th>
		        <th width="85">未申请开票金额</th>
		        <th width="100">部门</th>
		        <th width="100">单位</th>
		      </tr>
		      <s:iterator value="domain.dataList" id="dzqd" status="sta">
		        <tr>
			      	<td align="center" class="bh"><s:property value="#sta.index+1" /></td>
		      		<td align="center"><input type="checkbox" name="xhs" value="<s:property value="#dzqd.kpsqmxDjxh" />" /></td>
			        <td align="center">
				        <a href="javascript:onUpdateDzqd('<s:property value="#dzqd.kpsqmxDjxh" />');">
				        	<font color="blue"><s:property value="#dzqd.sqKpJe" /></font>
				        </a>
			        </td>
			        <td align="center"><s:property value="#dzqd.qdmc" /></td>
			        <td align="center"><s:property value="#dzqd.heJe" /></td>
			        <td align="center"><s:property value="#dzqd.ysqKpJe" /></td>
			        <td align="center"><s:property value="#dzqd.wsqKpJe" /></td>
			        <td align="center"><s:property value="#dzqd.bmMc" /></td>
			        <td align="center"><s:property value="#dzqd.dwMc" /></td>
			      </tr>
		      </s:iterator>
		    </table>
			<div class="pop_btn">
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
