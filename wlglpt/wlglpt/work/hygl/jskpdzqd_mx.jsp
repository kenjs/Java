<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>已核销明细</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		$("#addBtn").click(function(){
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
			var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
			var kpsqDjxh = $("#mainForm_domain_kpsqDjxh").val();
		    var url = jcontextPath+"/hygl/jskpdzqd!initAddHxMx.action?domain.kpsqDjxh="+kpsqDjxh+"&domain.ssJgbm="+ssJgbm+"&domain.khDjxh="+khDjxh;
    	    window.showModalDialog(url,window,"dialogHeight:420px;dialogWidth:500px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:no;maximize:no;")
    		doMyRefresh();
		});
		
		$("#deleteBtn").click(function(){
			var xhs = $(":checked[name='xhs']");
			if (xhs.length <= 0) {
				showAlert("请先选择需要删除的已核销信息！");
				return;
		}
			
		showConfirm("确定要删除选中的已核销信息？", "delKpsqDzQd");
		});
		$("#closeBtn").click(function(){
			window.close();
		});
	});
	
	function onUpdateDzqd(qdDjxh){
		var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
		var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
		var kpsqDjxh = $("#mainForm_domain_kpsqDjxh").val();
		var url = jcontextPath+"/hygl/jskpdzqd!initHxMx.action?domain.kpsqDjxh="+kpsqDjxh+"&domain.qdDjxh="+qdDjxh+"&domain.ssJgbm="+ssJgbm+"&domain.khDjxh="+khDjxh;
		window.showModalDialog(url,window,"dialogHeight:420px;dialogWidth:500px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:no;maximize:no;")
		doMyRefresh();
	}

	function checkJs(obj) {
		$(":checkbox[name='xhs']").attr("checked", obj.checked);
	}

	function delKpsqDzQd() {
		var kpsqDjxh = trim($("#mainForm_domain_kpsqDjxh").val()); 
		var xhs = $(":checked[name='xhs'][value!='']");
		//showMessage();
		if (xhs.length > 0) {
			var jsonStr = getJqueryParam(xhs, "domain.kpsqmxDjxhs");
			var jsonObj = {"domain.kpsqDjxh":kpsqDjxh};
			
			jsonStr += jQuery.param(jsonObj);
			var url = jcontextPath+"/hygl/jskpdzqd!delete";  
			ajaxCommon(url,encodeURI(jsonStr),"doDelKpsqDzQdxxSuc", false);
		}
	}
	
	function doDelKpsqDzQdxxSuc(){ 
        //hideMessage();
        showAlert("删除成功！");
        doMyRefresh();
	}
	
	//刷新弹窗
	function doMyRefresh(){
	   var kpsqDjxh = $("#mainForm_domain_kpsqDjxh").val(); 
	   var ssJgbm = $("#mainForm_domain_ssJgbm").val(); 
	   var khDjxh = $("#mainForm_domain_khDjxh").val(); 
	   var url = jcontextPath+"/hygl/jskpdzqd!initMx.action?domain.kpsqDjxh="+kpsqDjxh+"&domain.ssJgbm="+ssJgbm+"&domain.khDjxh="+khDjxh;
	   reload.href = url;
	   reload.click();
	}
</script>
<base target="_self" />
</head>

<body>
<%try{ %>
<s:form action="jskpdzqd!initMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
		<div  style="display:none"><a id="reload" href="">reload</a></div>
		<s:hidden name="domain.kpsqDjxh"></s:hidden>
		<s:hidden name="domain.djJgbm"></s:hidden>
		<s:hidden name="domain.ssJgbm"></s:hidden>
		<s:hidden name="domain.kpsqfsDm"></s:hidden>
		<div class="pop_contc">
			<fieldset>
				<legend>基本信息</legend>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
					<tr>
						<td width="20%" align="right">所属机构：</td>
	      				<td width="30%">
	      					<s:textfield name="domain.ssJgmc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      				<td width="20%" align="right">登记部门：</td>
	      				<td width="30%">
	      					<s:textfield name="domain.djJgmc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      			</tr>
	      			<tr>
	      				<td align="right">客户名称：</td>
	      				<td>
	      					<s:hidden name="domain.khDjxh"></s:hidden>
	      					<s:textfield name="domain.khmc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      				<td align="right">已核销金额：</td>
	      				<td>
	      					<s:textfield name="domain.yhxje" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
	      				</td>
	      			</tr>
	      		</table>
	      	</fieldset>
	      			<fieldset>
						<legend>开票信息</legend>
						<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
			      			<tr>
				      			<td align="right" width="20%">申请开票金额：</td>
			      				<td width="30%">
			      					<s:textfield name="domain.sqKpjeHj" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
			      				</td>
			      				<td align="right" width="20%">申请开票日期：</td>
			      				<td width="30%">
			      					<s:textfield name="domain.sqKprq" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
			      				</td>
			      			</tr>
			      			<tr>
			      				<td align="right">登记人：</td>
			      				<td>
			      					<s:textfield name="domain.djrMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
			      				</td>
			      				<td align="right">登记日期：</td>
			      				<td>
			      					<s:textfield name="domain.djrq" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
			      				</td>
			      			</tr>
			      			<tr>
			      				<td align="right">备注说明：</td>
			      				<td colspan="3">
			      					<s:textarea name="domain.bzsm" rows="3" cssClass="pop_textarea_colspan2 bgstyle_optional" readonly="true" ></s:textarea>
			      				</td>
			      			</tr>
						</table>
					</fieldset>
			<div style="margin-top: 20px;">
		     <table id="zbTab" width="95%" border="0" cellspacing="0" cellpadding="0" class="poptab_css" align="center">
		      <tr>
		        <th width="5%">序号</th>
		        <th width="10%"><input type="checkbox" name="jsCheck" onclick="checkJs(this)"/></th>
		        <th width="20%">清单名称</th>
		        <th width="15%">合计金额</th>
		        <th width="15%">开票金额</th>
		        <th width="30%">备注说明</th>
		      </tr>
		      <s:iterator id="zb" value="domain.yhxList" status="sta">
		      	<tr>
		        	<td align="center" class="bh"><s:property value="#sta.index+1" /></td>
		        	<td align="center"><input type="checkbox" name="xhs" value="<s:property value="#zb.kpsqmxDjxh" />" /></td>
			        <td align="center"><a href="javascript:onUpdateDzqd(<s:property value="#zb.qdDjxh" />)"><font color="blue"><s:property value="#zb.qdmc" /></font></a></td>
			        <td align="center"><s:property value="#zb.heJe" /></td>
			        <td align="center"><s:property value="#zb.sqKpje" /></td>
			        <td align="center"><s:property value="#zb.bzsm" /></td>
			        <td style="display: none;"><input type="hidden" name="qdDjxh" value="<s:property value="#zb.qdDjxh" />"/></td>
			        <td style="display: none;"><input type="hidden" name="kpsqDjxh" value="<s:property value="#zb.kpsqDjxh" />"/></td>
		      	</tr>
		      </s:iterator>
		     </table>
		</div>
			<div class="pop_btn">
				<button type="button" class="pop_btnbg" id="addBtn">添加</button>
			 	&nbsp;
			 	<button type="button" class="pop_btnbg" id="deleteBtn">删 除</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		    </div>
		</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>