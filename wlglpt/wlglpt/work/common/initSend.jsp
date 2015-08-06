<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>请选择发送人</title>
<script type="text/javascript">

	$(function(){	
		$("#sendBtn").click(function(){	
			var wsspms = trim($("#mainForm_domain_wsspms").val()); 
			var sprCzyDjxh ="";
			if("1"==wsspms || "2"==wsspms){
				sprCzyDjxh=trim($("#mainForm_domain_sprCzyDjxh").val());
				if(undefined==sprCzyDjxh || null==sprCzyDjxh || ""==sprCzyDjxh){
					showAlert("请您选择下一处理人！");
					return;
				}
			}
			window.dialogArguments.sprCzyDjxh =sprCzyDjxh;
			window.dialogArguments.wsspms =wsspms;
			window.close();
		});
	});		
</script>
<base target="_self" />
</head>
<body >
<%try{ %>
<s:form action="wsspCommon!init"  namespace="/common" method="post" id="mainForm" name="mainForm">	
	<s:hidden name="domain.wsspxh"></s:hidden>
	<s:hidden name="domain.spxh"></s:hidden>
	<s:hidden name="domain.wsspms"></s:hidden>
	<s:hidden name="domain.spjgjgDm"></s:hidden>
	<div class="pop_tbmain">
		<s:if test='domain.wsspms=="1"'>
			<table width="525" border="0" align="center" cellpadding="2" cellspacing="0" class="poptab_css">
				<tr>
					<th width="110" align="center">审批模式</th>
					<th width="100" align="center">审批节点说明</th>
					<th width="140" align="center">审批机构级别</th>
					<th width="170" align="center">下一处理人</th>
				</tr>
				<tr>
					<td align="center"><s:property value="domain.wsspmsmc" /></td>
					<td align="center"><s:property value="domain.spjdsm" /></td>
					<td align="center">
						<s:property value="domain.spjgjgDm"/>&nbsp;<s:property value="domain.spjgjgmc"/>
					</td>
					<td align="center">
						<s:select name="domain.sprCzyDjxh" list="domain.sprList" listKey="dm" listValue="mc" cssClass="select"></s:select>
					</td>
				</tr>
			</table>
		</s:if>
		<s:else>
			<table width="625" border="0" align="center" cellpadding="2" cellspacing="0" class="poptab_css">
				<tr>
					<th width="110" align="center">审批模式</th>
					<th width="100" align="center">审批节点说明</th>
					<th width="140" align="center">审批机构</th>
					<th width="100" align="center">审批岗位</th>
					<th width="170" align="center">下一处理人</th>
				</tr>
				<tr>
					<td align="center"><s:property value="domain.wsspmsmc" /></td>
					<td align="center"><s:property value="domain.spjdsm" /></td>
					<td align="center">
						<s:hidden name="domain.spJgbm"></s:hidden>
						<s:property value="domain.spJgbm"/>&nbsp;<s:property value="domain.spjgmc"/>
					</td>
					<td align="center">
						<s:hidden name="domain.gwDjxh"></s:hidden>
						<s:property value="domain.gwDjxh"/>&nbsp;<s:property value="domain.gwmc"/>
					</td>
					<td align="center">
						<s:if test='domain.wsspms=="2"'>
							<s:select name="domain.sprCzyDjxh" list="domain.sprList" listKey="dm" listValue="mc" cssClass="select"></s:select>
						</s:if>
						<s:else>
							<s:select name="domain.sprCzyDjxh" list="domain.sprList" listKey="dm" listValue="mc" disabled="true" cssClass="select"></s:select>
						</s:else>
					</td>
				</tr>
			</table>
		</s:else>
		<div class="btn">
			<button type="button" id="sendBtn" class="pop_btnbg">确 定</button>&nbsp;
			<button type="button" class="pop_btnbg" onclick="javascript:window.close();">取 消</button>
	 	</div>
 	
 	</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
