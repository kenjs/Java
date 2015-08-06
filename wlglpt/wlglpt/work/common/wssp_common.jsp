<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>文书审批</title>
<script type="text/javascript">
	$(function(){	
		$("#saveBtn").click(function(){	
			var spjg = $("input[name='domain.spjg']:checked").val();
			if(undefined==spjg || null==spjg || ""==spjg){
				showAlert("请您选择审批结果！");
				return;
			}
			$("#mainForm").attr("action", "wsspCommon!save");
			mainForm.submit();
		});
		$("#judgeBtn").click(function(){	
			var spjg = $("input[name='domain.spjg']:checked").val();
			if(undefined==spjg || null==spjg || ""==spjg){
				showAlert("请您选择审批结果！");
				return;
			}
			showConfirm("确定要终审吗？", "doSaveJudge");
		});
		$("#backBtn").click(function(){	
			showConfirm("确定要退回吗？", "doBackJudge");
		});
	});	
	
	function doSaveJudge(){
		var wsspxh = trim($("#mainForm_domain_wsspxh").val()); 
		var spxh = trim($("#mainForm_domain_spxh").val()); 
		var spjg = $("input[name='domain.spjg']:checked").val();
		var spyj = trim($("#mainForm_domain_spyj").val()); 
		
		var url = jcontextPath+"/common/wsspCommon!judge";  
    	var jsonObj = {"domain.wsspxh":wsspxh,"domain.spxh":spxh,"domain.spjg":spjg,
                          "domain.spyj":spyj};   			
		ajaxCommon(url,jsonObj,"doSaveJudgeSuc");
	}
	
	function doBackJudge(){
		var wsspxh = trim($("#mainForm_domain_wsspxh").val()); 
		var spxh = trim($("#mainForm_domain_spxh").val()); 
		var spyj = trim($("#mainForm_domain_spyj").val()); 
		var spjg = $("input[name='domain.spjg']:checked").val();
		if(undefined==spjg || null==spjg || ""==spjg){
			spjg="2";
		}
		
		var url = jcontextPath+"/common/wsspCommon!back";  
    	var jsonObj = {"domain.wsspxh":wsspxh,"domain.spxh":spxh,"domain.spjg":spjg,
                          "domain.spyj":spyj};   			
		ajaxCommon(url,jsonObj,"doSaveBackSuc");
	}	
	
	function doSaveJudgeSuc(data) {
		hideMessage();
		showSuccess("终审成功！","saveSucYesCallBack");
	}
	
	function doSaveBackSuc(data) {
		hideMessage();
		showSuccess("退回成功！","saveSucYesCallBack");
	}
	function saveSucYesCallBack() {
		window.close();
	}	 
	function setSpyj(obj){
		if("1"==obj.value){
			$("#mainForm_domain_spyj").val("同意")
		}
		if("2"==obj.value){
			$("#mainForm_domain_spyj").val("不同意")
		}
	}
</script>
<base target="_self" />
</head>
<body >
<%try{ %>
<s:form action="wsspCommon!init"  namespace="/common" method="post" id="mainForm" name="mainForm">	
	<s:hidden name="domain.wsspxh"></s:hidden>
	<s:hidden name="domain.spxh"></s:hidden>
	<div class="pop_tbmain">
      <div class="pop_tbtitle"><p><a href="#" class="btnarrow" onfocus="this.blur();" onclick="slideToggle('splc')"></a></p><h2>审批流程</h2></div>
      <div class="pop_tbcont" id="splccont">
      	<jsp:include page="wssplz.jsp"/>
      </div>
    </div>
	<div class="pop_tbmain" style="margin-top: 4px;">
		<div class="pop_tbtitle"><p><a href="#" class="btnarrow" onfocus="this.blur();" onclick="slideToggle('spyj')"></a></p><h2>审批意见</h2></div>
		<div class="pop_tbcont" id="spyjcont">
			<table width="800" border="0" align="center" cellpadding="2" cellspacing="0">
				<tr>
					<td width="65">审批结果：</td>
					<td>
						<s:radio name="domain.spjg" list='#{"1":"同意","2":"不同意"}' listKey="key" listValue="value" onclick="javascript:setSpyj(this)"></s:radio>
					</td>
				</tr>
				<tr>
					<td width="65">审批意见：</td>
					<td>
						<s:textarea name="domain.spyj" cols="100" rows="4"></s:textarea>
					</td>
				</tr>
			</table>
				
			<div class="btn">
				<s:if test="domain.saveBz">
					<button type="button" id="saveBtn" class="pop_btnbg">保 存</button>&nbsp;
				</s:if>
				<s:if test="domain.backBz">
					<button type="button" id="backBtn" class="pop_btnbg">退 回</button>&nbsp;
				</s:if>
				<s:if test="domain.judgeBz">
					<button type="button" id="judgeBtn" class="pop_btnbg">终 审</button>&nbsp;
				</s:if>
		  		<button type="button" class="pop_btnbg" onclick="javascript:window.close();">关 闭</button>
		 	</div>
		 	<jsp:include page="send.jsp"/>
		</div>
	</div>
 	
 	<div class="pop_tbmain">
      <div class="pop_tbtitle"><p><a href="#" class="btnarrow" onfocus="this.blur();" onclick="slideToggle('spj')"></a></p><h2>审批件</h2></div>
      <div class="pop_tbcont" id="spjcont">
      	<iframe id="iframeWS" scrolling="auto" style="width:100%;height:370px; background-color: #FFFFFF;" scrolling="no"  frameborder="0" src="<s:property value="domain.spjUrl"/>" >
      </div>
    </div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
