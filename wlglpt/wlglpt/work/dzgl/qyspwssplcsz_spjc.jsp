<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>审批流程节点设置</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		var wsspmsDm = $("#mainForm_domain_wsspmsDm").val();
		if("2"==wsspmsDm || "3" ==wsspmsDm){
			initList();
		}
		
		
		$("#mainForm_domain_spjcDomain_spJgbm").change(initGw);
		
		$("#closeBtn").click(function(){
			window.close();
		});
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var spjgjbDm="";
			if("1"==wsspmsDm){
				spjgjbDm = $("#mainForm_domain_spjcDomain_spjgjbDm").val(); 
				if(undefined==spjgjbDm || null==spjgjbDm || ""==spjgjbDm){
					showAlert("请选择审批机构级别！");
					return;
				}
			}
			var spJgbm="";
			var gwDjxh="";
			if("2"==wsspmsDm || "3"==wsspmsDm){
				spJgbm = $("#mainForm_domain_spjcDomain_spJgbm").val(); 
				gwDjxh = $("#mainForm_domain_spjcDomain_gwDjxh").val(); 
				if(undefined==spJgbm || null==spJgbm || ""==spJgbm){
					showAlert("请选择审批部门！");
					return;
				}
				if(undefined==gwDjxh || null==gwDjxh || ""==gwDjxh){
					showAlert("请选择审批岗位！");
					return;
				}
			}
			var yxzsBz = $("input[name='domain.spjcDomain.yxzsBz']:checked").val();
			if(undefined==yxzsBz || null==yxzsBz || ""==yxzsBz){
				showAlert("请选择是否允许终审！");
				return;
			}	
			var gzrbz = $("input[name='domain.spjcDomain.gzrbz']:checked").val();
			if(undefined==gzrbz || null==gzrbz || ""==gzrbz){
				showAlert("请选择工作日还是自然日！");
				return;
			}
			var fsbz = $("input[name='domain.spjcDomain.fsbz']:checked").val();
			if(undefined==fsbz || null==fsbz || ""==fsbz){
				showAlert("请选择审批结果为“不同意”时是否允许继续发送审批！");
				return;
			}
			var splcSzxh = $("#mainForm_domain_spjcDomain_splcSzxh").val();
			var jdxh = $("#mainForm_domain_spjcDomain_jdxh").val(); 
			var spjdsm = $("#mainForm_domain_spjcDomain_spjdsm").val(); 
			var sphjsm = trim($("#mainForm_domain_spjcDomain_sphjsm").val()); 
			var spyjl = trim($("#mainForm_domain_spjcDomain_spyjl").val()); 
			var spqm = trim($("#mainForm_domain_spjcDomain_spqm").val()); 
			var zstj = trim($("#mainForm_domain_spjcDomain_zstj").val()); 
			var spsx = trim($("#mainForm_domain_spjcDomain_spsx").val()); 
			if(spsx/1<0){
				showAlert("审批时限不能小于零！");
				return;
			}
			var qzxs = $("#mainForm_domain_spjcDomain_qzxs").val(); 

			var url = jcontextPath+"/dzgl/qyspwssplcsz!saveSpjc";  
	    	var jsonObj = {"domain.spjcDomain.jdxh":jdxh,"domain.spjcDomain.spjdsm":spjdsm,"domain.spjcDomain.spjgjbDm":spjgjbDm,"domain.spjcDomain.spJgbm":spJgbm,"domain.spjcDomain.gwDjxh":gwDjxh,
                           "domain.spjcDomain.sphjsm":sphjsm,"domain.spjcDomain.spyjl":spyjl,"domain.spjcDomain.spqm":spqm,"domain.spjcDomain.yxzsBz":yxzsBz,"domain.spjcDomain.zstj":zstj,
                           "domain.spjcDomain.spsx":spsx,"domain.spjcDomain.gzrbz":gzrbz,"domain.spjcDomain.qzxs":qzxs,"domain.spjcDomain.splcSzxh":splcSzxh,
                           "domain.spjcDomain.fsbz":fsbz};   			
			ajaxCommon(url,jsonObj);
		});
	});
	
	//成功后的数据处理 提示保存成功，关闭当前页面，并调用父窗口的刷新函数
	function doSuccess(data){
		hideMessage();
		showSuccess("保存成功！","doYesCallBack");
	}
	
	//选择是的返回处理
	function doYesCallBack(){
		//sysClose();
		//parent.initMx();
		window.close();
	}
	
	function initList() {
		var dwDm = $("#mainForm_domain_spjcDomain_dwDm").val(); 
		var bm = $("#mainForm_domain_spjcDomain_spJgbm").val();
		var jsonObj = {"domain.paramdm":dwDm,
			"domain.defaultValue":bm,
			"domain.currentObjName":"domain.spjcDomain.spJgbm",
			"domain.currentObjId":"mainForm_domain_spjcDomain_spJgbm",
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
		//审批岗位
		initGw();
	}
	
	function initGw() {
		var bm = $("#mainForm_domain_spjcDomain_spJgbm").val();
		gwInit(bm, '', "domain.spjcDomain.gwDjxh", "mainForm_domain_spjcDomain_gwDjxh", "Y", "Y");
	}
	
	
	
	function checkdata(){
		var controlNameArray = ["domain.spjcDomain.jdxh","domain.spjcDomain.spjdsm",
		                        "domain.spjcDomain.sphjsm","domain.spjcDomain.spyjl","domain.spjcDomain.spqm","domain.spjcDomain.zstj",
		                        "domain.spjcDomain.spsx","domain.spjcDomain.qzxs"];
		var labelNameArray = ["节点序号","审批节点说明",
		                      "审批环节说明","审批意见栏","审批签名","终审条件",
		                      "审批时限","权重系数"];
		var compareValueArray = [2,60,
			                     40,40,40,500,
			                     4.2,4.2];
		var nodeTypeArray = [NodeType.INTEGER,NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,
			                 NodeType.DECIMAL,NodeType.DECIMAL];
		var notNullArray = [true,true,
                            false,false,false,false,
                            true,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
</script>
<base target="_self" />
</head>

<body>
<%try{ %>
<s:form action="qyspwssplcsz!initSpjcMx" namespace="/dzgl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.spjcDomain.splcSzxh" /> 
	<s:hidden name="domain.wsspmsDm" />
	
		<div class="pop_contc" style="height:390px; overflow:auto;">
			<fieldset><legend>基本信息</legend>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
					<tr>
	      				<td width="18%" align="right"><font class="font_red">*</font>节点序号：</td>
	      				<td width="32%">
	      					<s:textfield name="domain.spjcDomain.jdxh" readonly="true" cssClass="pop_input noborder bgstyle_required" ></s:textfield>
	      				</td>
	      				<td width="18%" align="right"><font class="font_red">*</font>审批节点说明：</td>
	      				<td width="32%">
	      					<s:textfield name="domain.spjcDomain.spjdsm" cssClass="pop_input noborder bgstyle_required"></s:textfield>
	      				</td>
	      			</tr>
	      			<s:if test='domain.wsspmsDm=="1"'>
	      				<tr>
	      					<td align="right"><font class="font_red">*</font>审批机构级别：</td>
	      					<td>
	      						<sys:Spjgjb myName="domain.spjcDomain.spjgjbDm" myId="mainForm_domain_spjcDomain_spjgjbDm" myClass="select" contaisQxz="true"></sys:Spjgjb>
	      					</td>
	      					<td align="right">审批环节说明：</td>
		      				<td>
		      					<s:textfield name="domain.spjcDomain.sphjsm" cssClass="pop_input noborder bgstyle_optional" ></s:textfield>
		      				</td>
	      				</tr>
	      			</s:if>
	      			<s:else>
	      				<tr>
	      					<td align="right"><font class="font_red">*</font>审批单位：</td>
	      					<td>
	      						<sys:fgsList myId="mainForm_domain_spjcDomain_dwDm" myName="domain.spjcDomain.dwDm" mcContainDmBz="Y" myClass="select" onChange="initList()" />
	      					</td>
	      					<td align="right"><font class="font_red">*</font>审批部门：</td>
	      					<td>
	      						<select name="domain.spjcDomain.spJgbm" id="mainForm_domain_spjcDomain_spJgbm" class="select">
	      							<option value="${domain.spjcDomain.spJgbm}"></option>
	      						</select>
	      					</td>
	      				</tr>
	      				<tr>
	      					<td align="right"><font class="font_red">*</font>审批岗位：</td>
	      					<td>
	      						<select id="mainForm_domain_spjcDomain_gwDjxh" name="domain.spjcDomain.gwDjxh" class="select">
	      						<option value="${domain.spjcDomain.gwDjxh }"></option>
	      					</select>
	      					</td>
	      					<td align="right">审批环节说明：</td>
		      				<td>
		      					<s:textfield name="domain.spjcDomain.sphjsm" cssClass="pop_input noborder bgstyle_optional" ></s:textfield>
		      				</td>
	      				</tr>
	      			</s:else>
	      			<tr>
	      				<td align="right">审批意见栏：</td>
	      				<td>
	      					<s:textfield name="domain.spjcDomain.spyjl" cssClass="pop_input noborder bgstyle_optional" ></s:textfield>
	      				</td>
	      				<td align="right">审批签名：</td>
	      				<td>
	      					<s:textfield name="domain.spjcDomain.spqm" cssClass="pop_input noborder bgstyle_optional" ></s:textfield>
	      				</td>
	      			</tr>
	      			<tr>
	      				<td align="right">权重系数：</td>
	      				<td>
	      					<s:textfield name="domain.spjcDomain.qzxs" cssClass="pop_input noborder bgstyle_optional" ></s:textfield>
	      				</td>
	      				<td align="right"><font class="font_red">*</font>是否允许终审：</td>
	      				<td>
	      					<s:if test='domain.existsBz=="Y"'>
	      						<s:radio name="domain.spjcDomain.yxzsBz" list='#{"Y":"允许", "N":"不允许"}' listKey="key" listValue="value"></s:radio>
	      					</s:if>
	      					<s:else>
	      						<s:radio name="domain.spjcDomain.yxzsBz" list="#{'Y':'允许', 'N':'不允许'}" listKey="key" listValue="value" value="'N'"></s:radio>
	      					</s:else>
	      				</td>
	      			</tr>
	      			<tr>
	      				<td align="right">终审条件：</td>
	      				<td colspan="3">
	      					<s:textarea name="domain.spjcDomain.zstj" rows="3" cssClass="pop_textarea_colspan2 bgstyle_optional" ></s:textarea>
	      				</td>
	      			</tr>
	      			<tr>
	      				<td align="right"><font class="font_red">*</font>审批时限：</td>
	      				<td colspan="3">
	      					<s:textfield name="domain.spjcDomain.spsx" cssClass="pop_input noborder bgstyle_required" cssStyle="width:25%;"></s:textfield>
	      					&nbsp;&nbsp;
	      					<s:if test='domain.existsBz=="Y"'>
	      						<s:radio name="domain.spjcDomain.gzrbz" list='#{"1":"工作日", "2":"自然日"}' listKey="key" listValue="value"></s:radio>
	      					</s:if>
	      					<s:else>
	      						<s:radio name="domain.spjcDomain.gzrbz" list='#{"1":"工作日", "2":"自然日"}' listKey="key" listValue="value" value="1"></s:radio>
	      					</s:else>
	      				</td>
	      			</tr>
	      			<tr>
	      				<td align="right" colspan="3"><font class="font_red">*</font>审批结果为“不同意”时是否允许继续发送审批：
	      					<s:if test='domain.existsBz=="Y"'>
	      						<s:radio name="domain.spjcDomain.fsbz" list="#{'Y':'是', 'N':'否'}" listKey="key" listValue="value"></s:radio>
	      					</s:if>
	      					<s:else>
	      						<s:radio name="domain.spjcDomain.fsbz" list="#{'Y':'是', 'N':'否'}" listKey="key" listValue="value" value="'N'"></s:radio>
	      					</s:else>
	      					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	      				</td>
	      				<td>&nbsp;</td>
	      			</tr>
				</table>
			</fieldset>
			<div class="pop_btn">
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
