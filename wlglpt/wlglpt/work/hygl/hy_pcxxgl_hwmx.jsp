<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>派车-货物明细</title>

<style type="text/css">
html,body {background:none;}
.hiddenCss {display: none;}
</style>
<script type="text/javascript">
	var hwflObserverFlag = true;
	$(function(){
		initDfDis();
		
		$("#mainForm_domain_pcHwxxDomain_hwSl").focus(function() {
			$(this).attr("title", this.value);
		}).change(function() {
			var pre = $(this).attr("title")/1;
			if (pre > 0) {
				var bl = this.value/1 / pre;
				var hwZl = $("#mainForm_domain_pcHwxxDomain_hwZl");
				hwZl.val((hwZl.val()/1 * bl).toFixed(2));
				var hwTj = $("#mainForm_domain_pcHwxxDomain_hwTj");
				hwTj.val((hwTj.val()/1 * bl).toFixed(2));
				var jsSl = $("#mainForm_domain_pcHwxxDomain_jsSl");
				jsSl.val((jsSl.val()/1 * bl).toFixed(2));
			}
		});
		
		$("#closeBtn").click(function() {
			window.close();
		});
		
		$("#saveBtn").click(function(){
			if (!checkHwsl()) {
				return;
			}
			if(!checkdata()){
				return;
			}
			if (!checkHdbh()) {
				return;
			}
			
			var hwSl = $("#mainForm_domain_pcHwxxDomain_hwSl");
			var kfHwsl = $("#mainForm_domain_pcHwxxDomain_kfHwsl").val();
			if (hwSl.val()/1 > kfHwsl/1) {
				showConfirm("货物数量已超出库存可发货数量，属于超量派车，是否继续？", "checkDf");
			}else {
				checkDf();
			}
		});
	});
	
	function doSave() {
		var hwSl = trim($("#mainForm_domain_pcHwxxDomain_hwSl").val()); 
		var hwZl = trim($("#mainForm_domain_pcHwxxDomain_hwZl").val()); 
		var hwTj = trim($("#mainForm_domain_pcHwxxDomain_hwTj").val()); 
		var jsSl = $("#mainForm_domain_pcHwxxDomain_jsSl").val();
		var hdbh = $("#mainForm_domain_pcHwxxDomain_hdbh").val();
		var pcfsDm = $("#mainForm_domain_pcfsDm").val();
		var pchwClfsDm = $("#mainForm_domain_pcHwxxDomain_pchwClfsDm").val();
		
		var xtcs20000 = $("#mainForm_domain_xtcs20000").val();
		if(xtcs20000=="2"){
			if (pcfsDm == "2" && $("#shfs")[0].checked){
				pchwClfsDm = "24";
			}else {
				pchwClfsDm = "22";
			}
		}else{
			pchwClfsDm = "21";
		}		
		var srdf = 0;
		var yfSjs = 0;
		if (pcfsDm != "1") {
			srdf = $("#mainForm_domain_pcHwxxDomain_srdf").val();
			//alert(srdf)
			if(xtcs20000=="2"){			
				if (pcfsDm == "2" && $("#sfSjs")[0].checked) {
					yfSjs = srdf;
				}else if (pcfsDm == "3" && pchwClfsDm == "31") {
					yfSjs = srdf;
				}
			}else{
				yfSjs = srdf;
			}
		}
		var bz = $("#mainForm_domain_pcHwxxDomain_bz").val();
		var pcDjxh = $("#mainForm_domain_pcHwxxDomain_pcDjxh").val();
		var pchwLsxh = $("#mainForm_domain_pcHwxxDomain_pchwLsxh").val();
		var wfhDjxh = $("#mainForm_domain_pcHwxxDomain_wfhDjxh").val();
		
		var url = jcontextPath+"/hygl/hypcxxgl!updateWfhxx4Pc";  
    	var jsonObj = {"domain.pcHwxxDomain.hwSl":hwSl,"domain.pcHwxxDomain.hwZl":hwZl,"domain.pcHwxxDomain.hwTj":hwTj,
    					"domain.pcHwxxDomain.jsSl":jsSl,"domain.pcHwxxDomain.yfSjs":yfSjs,"domain.pcHwxxDomain.srdf":srdf,
                       "domain.pcHwxxDomain.hdbh":hdbh,"domain.pcHwxxDomain.pchwClfsDm":pchwClfsDm,"domain.pcHwxxDomain.bz":bz,
                       "domain.pcHwxxDomain.pcDjxh":pcDjxh,"domain.pcHwxxDomain.pchwLsxh":pchwLsxh,"domain.pcHwxxDomain.wfhDjxh":wfhDjxh,
                       "domain.pcHwxxDomain.pcfsDm":pcfsDm};   			
    	showMessage();
    	ajaxCommon(url,jsonObj,"updateWfhxx4PcSuc");
	}
	
	function checkHwsl() {
		var hwSl = $("#mainForm_domain_pcHwxxDomain_hwSl");
		try {
			if (hwSl.val()/1 <= 0) {
				objForFocus = hwSl;
				throw "数量录入有误，数量必须大于0！";
			}
		}catch (e) {
			showAlert(e, "focusSel");
			return false;
		}
		
		return true;
	}
	
	function checkDf() {
		var flag = true;
		var pcfsDm = $("#mainForm_domain_pcfsDm").val();
		var xtcs20000 = $("#mainForm_domain_xtcs20000").val();
		if (pcfsDm == "2" && xtcs20000=="2") {
			var srdf = $("#mainForm_domain_pcHwxxDomain_srdf").val();
			if (srdf != "" && srdf/1 > 0 && $("#shfs")[0].checked && !$("#sfSjs")[0].checked) {
				var kfHwsl = $("#mainForm_domain_pcHwxxDomain_kfHwsl").val();
				var hwSl = $("#mainForm_domain_pcHwxxDomain_hwSl").val();
				if (hwSl/1 >= kfHwsl/1) {
					flag = false;
				}
			}
		}

		if (!flag) {
			showConfirm("该货物已全部发完，但仍有到付未处理，是否确定不由司机代收？", "doSave");
		}else {
			doSave();
		}
	}
	
	function updateWfhxx4PcSuc(data) {
		hideMessage();
		window.dialogArguments.onQueryPcHwxx();
		showAlert("修改成功!","closeWin");
	}
	
	function closeWin() {
		window.close();
	}
	
	function checkdata(){
		var controlNameArray = ["domain.pcHwxxDomain.hdbh","domain.pcHwxxDomain.hwSl","domain.pcHwxxDomain.hwZl",
		                        "domain.pcHwxxDomain.hwTj","domain.pcHwxxDomain.bz"];
		var labelNameArray = ["回单编号","数量","重量",
		                      "体积","备注"];
		var compareValueArray = [100,12.2,12.2,
			                     12.2,500];
		var nodeTypeArray = [NodeType.STRING,NodeType.DECIMAL,NodeType.DECIMAL,
		                     NodeType.DECIMAL,NodeType.STRING];
		var notNullArray = [false,true,false,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	
	function checkHdbh() {
		var hdbh = $("#mainForm_domain_pcHwxxDomain_hdbh").val();
		var str = hdbh.match(/^[0-9a-zA-Z\,\uff0c]*$/);
		if (str == null) {
			objForFocus = $("#mainForm_domain_pcHwxxDomain_hdbh");
			showAlert("回单编号录入有误，回单编号只能包含数字和字母，多个回单编号以\",\"分割！","focusSel");
			return false;
		}
		return true;
	}
	
	// 若选中“是否直送”且到付大于0，则显示“是否司机收”复选框，否则隐藏
	function changeDf() {
		// 若页面有是否直送复选框
		if ($("#shfs").length > 0) {
			if ($("#shfs")[0].checked && $("#mainForm_domain_pcHwxxDomain_srdf").val() != "" 
					&& $("#mainForm_domain_pcHwxxDomain_srdf").val()/1 > 0) {
				$("#sfSjs")[0].checked = true;
				$(".dfSjs").css("display", "inline");
			}else {
				$("#sfSjs")[0].checked = false;
				$(".dfSjs").css("display", "none");
			}
			
			//initHz();
		}
	}
	
	function initDfDis() {
		if ($("#shfs").length > 0 && $("#shfs")[0].checked && $("#mainForm_domain_pcHwxxDomain_srdf").val() != "" 
				&& $("#mainForm_domain_pcHwxxDomain_srdf").val()/1 > 0) {
			$(".dfSjs").css("display", "inline");
		}
		//initHz();
	}
	
	/* function initHz() {
		if ($("#shfs").length > 0) {
			if ($("#shfs")[0].checked) {
				$("[name^='domain.pcHwxxDomain.zrbm']").attr("value","");
				$("#mainForm_domain_pcHwxxDomain_zrbmDjxh").attr("disabled", "true");
			}else {
				$("#mainForm_domain_pcHwxxDomain_zrbmDjxh").removeAttr("disabled");
			}
		}
	} */
	
</script>
</head>

<body>
<%try{ %>
<s:form action="hypcxxgl!initWfhxx4Pc" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.pcHwxxDomain.pcDjxh"/>
	<s:hidden name="domain.pcHwxxDomain.pchwLsxh" />
	<s:hidden name="domain.pcHwxxDomain.wfhDjxh" />
	<s:hidden name="domain.pcfsDm" />
	<s:hidden name="domain.xtcs20000" />
	<s:hidden name="domain.pcHwxxDomain.pchwClfsDm" />
	<s:hidden name="domain.pcHwxxDomain.jsSl" />
	
		<div class="pop_contc">
			<fieldset>
			<legend>基本信息</legend>
			<table width="355" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td width="115" class="td_noborder"></td>
      				<td width="70" class="td_noborder"></td>
      				<td width="30" class="td_noborder"></td>
      				<td width="45" class="td_noborder"></td>
      				<td width="70" class="td_noborder"></td>
      				<td width="35" class="td_noborder"></td>
      			</tr>
      			<tr>
      				<td align="right">货物名称：</td>
      				<td colspan="5">
  						<s:textfield name="domain.pcHwxxDomain.hwmc" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">回单编号：</td>
      				<td colspan="5">
      					<s:textfield name="domain.pcHwxxDomain.hdbh" cssClass="pop_input noborder bgstyle_optional" />
      				</td>
      			</tr>
      			<tr>
      				<td align="right">数量：</td>
      				<td>
      					<s:textfield name="domain.pcHwxxDomain.hwSl" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      				<td><s:property value="domain.pcHwxxDomain.hwSlJldwMc" /></td>
      				<td align="right">库存：</td>
      				<td>
      					<s:textfield name="domain.pcHwxxDomain.kfHwsl" cssClass="pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td><s:property value="domain.pcHwxxDomain.hwSlJldwMc" /></td>
      			</tr>
      			<tr>
      				<td align="right">重量：</td>
      				<td>
      					<s:textfield name="domain.pcHwxxDomain.hwZl" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      				<td colspan="4"><s:property value="domain.pcHwxxDomain.hwZlJldwMc" /></td>
      			</tr>
      			<tr>
      				<td align="right">体积：</td>
      				<td>
      					<s:textfield name="domain.pcHwxxDomain.hwTj" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      				<td colspan="4"><s:property value="domain.pcHwxxDomain.hwTjJldwMc" /></td>
      			</tr>
      		<s:if test='domain.pcfsDm == "2" && domain.pcHwxxDomain.pchwClfsDm != "42" && domain.xtcs20000 =="2" '>
      			<tr>
      				<td align="right">
      					<s:if test='domain.pcHwxxDomain.pchwClfsDm == "24" || domain.pcHwxxDomain.pchwClfsDm == "31"'>
      						<input type="checkbox" checked="checked" onclick="changeDf();" name="shfs" id="shfs" />
      					</s:if>
      					<s:else>
      						<input type="checkbox" name="shfs" onclick="changeDf();" id="shfs" />
      					</s:else>
      				</td>
      				<td colspan="5">
      					<label for="shfs">是否直送</label>
      				</td>
      			</tr>
      			<tr>
      				<td align="right"><div class="hiddenCss dfSjs">到付：</div></td>
      				<td>
      					<div class="hiddenCss dfSjs"><s:textfield name="domain.pcHwxxDomain.srdf" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield></div>
      				</td>
      				<td align="right">
      					<div class="hiddenCss dfSjs">
      					<s:if test='domain.pcHwxxDomain.yfSjs != null && domain.pcHwxxDomain.yfSjs > 0'>
      						<input type="checkbox" checked="checked" name="sfSjs" id="sfSjs" />
      					</s:if>
      					<s:else>
      						<input type="checkbox" name="sfSjs" id="sfSjs" />
      					</s:else>
      					</div>
      				</td>
      				<td colspan="3">
      					<div class="hiddenCss dfSjs"><label for="sfSjs">是否由司机代收</label></div>
      				</td>
      			</tr>
      		</s:if>
      		<s:elseif test='domain.pcfsDm == "3" || domain.xtcs20000 =="1"'>
      			<s:hidden name="domain.pcHwxxDomain.srdf" />
      		</s:elseif>
      			<tr>
      				<td align="right">备注：</td>
      				<td colspan="5">
      					<s:textarea name="domain.pcHwxxDomain.bz" rows="3" cssClass="pop_textarea_colspan2 noborder bgstyle_optional" ></s:textarea>
      				</td>
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
