<%@ page contentType="text/html;charset=GBK"%>
<%@ taglib uri="/struts-tags" prefix="s"%>
<script type="text/javascript">
	$(function(){	
		$("#sendBtn").click(function(){	
			var wsspxh = trim($("#mainForm_domain_wsspxh").val()); 
			var spxh = trim($("#mainForm_domain_spxh").val()); 
			var wsspms = trim($("#mainForm_domain_wsspms").val()); 
			var spjg = $("input[name='domain.spjg']:checked").val();
			var spyj = trim($("#mainForm_domain_spyj").val()); 
			var sprJdxh = trim($("#mainForm_domain_sprJdxh").val()); 
			var sprCzyDjxh ="";
			if("1"==wsspms || "2"==wsspms){
				sprCzyDjxh=trim($("#mainForm_domain_sprCzyDjxh").val());
				if(undefined==sprCzyDjxh || null==sprCzyDjxh || ""==sprCzyDjxh){
					showAlert("请您选择下一处理人！");
					return;
				}
			}
			
			var url = jcontextPath+"/common/wsspCommon!send";  
	    	var jsonObj = {"domain.wsspxh":wsspxh,"domain.spxh":spxh,"domain.sprJdxh":sprJdxh,
	                          "domain.sprCzyDjxh":sprCzyDjxh,"domain.spjg":spjg,"domain.spyj":spyj};   
			ajaxCommon(url,jsonObj,"doSaveSendSuc");
		});
	});		
	
	function doSaveSendSuc(data) {
		hideMessage();
		showSuccess("发送成功！","saveSucYesCallBack");
	}
	function saveSucYesCallBack() {
		window.close();
	}
</script>
<%try{ %>
	<s:if test='domain.sendBz'>
			<s:hidden name="domain.wsspms"></s:hidden>
			<s:hidden name="domain.sprJdxh"></s:hidden>
			<s:hidden name="domain.spjgjgDm"></s:hidden>
			<s:if test='domain.wsspms=="1"'>
				<table width="620" border="0" align="center" cellpadding="2" cellspacing="0" class="poptab_css">
					<tr>
						<th width="120" align="center">审批模式</th>
						<th width="120" align="center">审批节点说明</th>
						<th width="150" align="center">审批机构级别</th>
						<th align="center">下一处理人</th>
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
				<table width="770" border="0" align="center" cellpadding="2" cellspacing="0" class="poptab_css">
					<tr>
						<th width="120" align="center">审批模式</th>
						<th width="120" align="center">审批节点说明</th>
						<th width="150" align="center">审批机构</th>
						<th width="150" align="center">审批岗位</th>
						<th align="center">下一处理人</th>
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
				<button type="button" id="sendBtn" class="pop_btnbg">发 送</button>&nbsp;
		 	</div>
 	</s:if>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
