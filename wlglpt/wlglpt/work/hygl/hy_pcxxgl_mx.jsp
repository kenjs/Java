<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>派车信息管理</title>

<style type="text/css">
html,body {background:none;}
.hiddenCss {display: none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/hy_pcxxgl_mx.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>

<script type="text/javascript">
var yfHdfChanged = false;
$(function(){
	var zrbmDjxh = $("#mainForm_domain_zrbmDjxhShow").val();
	currentFbsInit('',zrbmDjxh, "zrbmFbs", "zrbmFbs", "Y", "N");
	
	$(".yfxx").change(function(){
		if (this == $("#mainForm_domain_yfHdf")[0]) {
			yfHdfChanged = true;
		}
		calYfxx();
	});
	
	$(".hkCal").change(function(){
		calHk();
	});
	
	$("#sendBtn").click(function(){
		var wsDm="302001";//费用登记审批表
		var pcDjxh = $("#mainForm_domain_pcDjxh").val();
		var oldWsspxh = $("#mainForm_domain_wsSpxh").val();
		scSend(wsDm,"",pcDjxh,oldWsspxh);
	});
	
	$("#mainForm_domain_cyrClhm").change(function() {
		setTimeout("checkClhmExists()", 200);
	});
	var xtcs20030 = $("#mainForm_domain_xtcs20030").val();
	var xtcs20000 = $("#mainForm_domain_xtcs20000").val();
	if(xtcs20030=="Y"){
	   initPs($("#mainForm_domain_yfHj"));
	   //initPs($("#mainForm_domain_yfYfyf"));
	   //initPs($("#mainForm_domain_yfHdf"));
	}
	if(xtcs20000=="1"){
	    $("#mainForm_domain_yfHdyf").removeClass("bgstyle_optional");
   		$("#mainForm_domain_yfHdyf").addClass("bgstyle_readonly");
   		$("#mainForm_domain_yfHdyf").attr("readonly",true);
	}
	initPs($("#mainForm_domain_yfHdyf"));
	
	var pcfsDm = $("#mainForm_domain_pcfsDm").val();
	if(pcfsDm=="3"){
		$("#sjs").html("货到付：");
	}
	//初始化任意一条货物的下游及转包信息
	initPcxx();	
});
function initPcxx(){
	var zrbmDm = $("#mainForm_domain_zrbmDmShow").val();
	var pchwClfsDm = $("#mainForm_domain_pchwClfsDm").val();
	$("#mainForm_domain_zrbmDm").val(zrbmDm);
	var zrbmDjxh = $("#mainForm_domain_zrbmDjxhShow").val();
	if(zrbmDm=="2"){
		$("#zrbmGs").val(zrbmDjxh);
		$("#sfjsDiv").css("display","inline");
		changeZrbm();
	}
	if(pchwClfsDm=="42"){
		$("#sfzbId")[0].checked = true;
		doZbNotDis($("#sfzbId")[0]);
	}
}
function initPs(obj){
	var pcfsDm = $("#mainForm_domain_pcfsDm").val();
	if(pcfsDm=="3"){
		$(obj).removeClass("bgstyle_optional");
   		$(obj).addClass("bgstyle_readonly");
   		$(obj).attr("readonly",true);
	}
}

function calYfxx() {
	var yfSjs = $("#mainForm_domain_yfSjs").val();
	var pcfsDm = $("#mainForm_domain_pcfsDm").val();
	if (yfSjs != "" && yfSjs/1 > 0) {
		var yfXxf;
		if(pcfsDm!="3"){
			 yfXxf= $("#mainForm_domain_yfYfyf").val()/1 + $("#mainForm_domain_yfHdyf").val()/1 + $("#mainForm_domain_yfHdf").val()/1 + yfSjs/1 - $("#mainForm_domain_yfHj").val()/1;
		}
		if (!isNaN(yfXxf)) {
			if (yfXxf <= 0) {
				$("#mainForm_domain_yfXxf").val("");
			}else {
				$("#mainForm_domain_yfXxf").val(yfXxf.toFixed(2));
			}
		}
	}else {
		if (!yfHdfChanged) {
			var yfHdf = $("#mainForm_domain_yfHj").val()/1 - $("#mainForm_domain_yfYfyf").val()/1 - $("#mainForm_domain_yfHdyf").val()/1;
			if (!isNaN(yfHdf) && yfHdf > 0) {
				$("#mainForm_domain_yfHdf").val(yfHdf.toFixed(2));
			}else {
				$("#mainForm_domain_yfHdf").val("");
			}
		}
		$("#mainForm_domain_yfXxf").val("");		
	}
}

function changeZrbm() {
	var zrbmDm = $("#mainForm_domain_zrbmDm").val();
	if ($("#sfzbId")[0].checked && zrbmDm == "") {
		$("#mainForm_domain_zrbmDm").val($("#mainForm_domain_zrbmDm").attr("title"));
		showAlert("已选择转包，不能再选提货到仓库，若要更改，请先去掉是否转包！");
		return;
	}
	//特殊情况 当已有货物  未选转包时 不允许下游再改为分包商
	var tableTrs = $("#dataList tr").length;
	if (tableTrs > 1 && !$("#sfzbId")[0].checked && zrbmDm=="3") {
		$("#mainForm_domain_zrbmDm").val("2");
		showAlert("已有货物,不可再转包给分包商！");
		return;
	}
	changeJsDivDis();
	changeZcDis();
	
	if (zrbmDm == "") {
		$("#sfzbDiv").css("display","none");
		$("#zrbmGsTd").css("display","none");
		$("#zrbmFbsTd").css("display","none");
		setZrbmDjxh("");
		//提货取本公司地址
		changeGsDz($("#mainForm_domain_dw4Query").val(),"QY_ZZJG");
	}else if (zrbmDm == "2") {
		$("#sfzbDiv").css("display","inline");
		$("#zrbmGsTd").css("display","inline");
		$("#zrbmFbsTd").css("display","none");
		
		setZrbmDjxh($("#zrbmGs").val());
		
		if ($("#zrbmGs").val() != null && $("#zrbmGs").val() != "") {
			changeGsDz($("#zrbmGs").val(),'QY_ZZJG');
		}
	}else if (zrbmDm == "3") {
		$("#sfzbDiv").css("display","inline");
		$("#zrbmGsTd").css("display","none");
		$("#zrbmFbsTd").css("display","inline");
		setZrbmDjxh($("#zrbmFbs").val());
		
		if ($("#zrbmFbs").val() != null && $("#zrbmFbs").val() != "") {
			changeGsDz($("#zrbmFbs").val(),'QY_FBS_DJXX');
			
		}
		//选分包商 则默认为转包
		var obj = $("#sfzbId")[0];
		obj.checked = true;
		doZbNotDis(obj);
	}
}

function changeJsDivDis() {
	var zrbmDm = $("#mainForm_domain_zrbmDm").val();
	var xtcs20000 = $("#mainForm_domain_xtcs20000").val();
	if (zrbmDm == "2" && $("#zrbmGs").val() != "") {
		$("#sfjsDiv").css("display","inline");
		if(xtcs20000=='1'){
			//特殊情况 当已有货物  未选转包时 不允许下游再选择分公司
			var tableTrs = $("#dataList tr").length;
			if (tableTrs > 1 && !$("#sfzbId")[0].checked ) {
				$("#zrbmGs").val("");
				showAlert("已有货物,不可再选择分公司！");
				return;
			}
			$("#sfzbId")[0].checked = true;
			doZbNotDis($("#sfzbId")[0]);
		}
	}else {
		$("#sfjsDiv").css("display","none");
		$("#sfjsId")[0].checked = false;
	}
}

function calHk() {
	var hk = $("#mainForm_domain_pcHwxxDomain_zcXf").val()/1+$("#zcDf").val()/1+$("#mainForm_domain_pcHwxxDomain_zcHf").val()/1+$("#mainForm_domain_pcHwxxDomain_zcYj").val()/1-$("#mainForm_domain_pcHwxxDomain_zcHj").val()/1;
	
	$("#mainForm_domain_pcHwxxDomain_zcHk").val(hk <= 0 ? '' : hk.toFixed(2)+'');
}

function changeZcDis() {
	if ($("#mainForm_domain_zrbmDm").val() == "3" || $("#sfjsId")[0].checked) {
		$(".zc").css("display", "block");
		// 重新计算到付
		setDf(null, true);
	}else {
		$("[name^='domain.pcHwxxDomain.zc']").attr("value","");
		$("#zcDf").val("");
		$(".zc").css("display", "none");
	}
}

function chengeZbNotDis(obj) {
	//js修改checkbox 就不执行 手点的就执行（即有焦点）
	if(document.activeElement.id=="sfzbId"){
		var tableTrs = $("#dataList tr").length;
		if (tableTrs > 1) {
			obj.checked = !obj.checked;
			showAlert("已选择货物，“是否转包”不可以更改，若需要更改，请先删除已选货物信息！");
			return;
		}	
		doZbNotDis(obj);
	}
}
function doZbNotDis(obj){
		if (obj.checked) {
			$(".zbNotDis").css({"display":"none"});
			$("#mainForm_domain_pcHwxxDomain_pchwClfsDm").val("42");
			// 清空派车信息（转包不需要派车）
			$("[name^='domain.cyr']").attr("value", "");
			$("[name^='domain.yf']").attr("value", "");
			$("[name='domain.dzrq']").val("");
			$("[name='domain.bz']").val("");
		}else {
			var zrbmDm = $("#mainForm_domain_zrbmDm").val();
			var pcfsDm = $("#mainForm_domain_pcfsDm").val();
			//只在运输派车时 控制
			if(zrbmDm=="3" && pcfsDm=="2"){
				obj.checked = true;
				showAlert("已选择下游为分包商，只能是转包！");
				return;
			}
			var zrbmDm = $("#mainForm_domain_zrbmDm").val();
			var xtcs20000 = $("#mainForm_domain_xtcs20000").val();
			if (zrbmDm == "2" && $("#zrbmGs").val() != "") {
				if(xtcs20000=='1'&& pcfsDm=="2"){
					$("#sfzbId")[0].checked = true;
					showAlert("已选择分公司，只能是转包！");
					return;
				}
			}
			$(".zbNotDis").css({"display":"block"});
			$("#mainForm_domain_pcHwxxDomain_pchwClfsDm").val("");
		}
}

</script>
<base target="_self" />
</head>

<body>
<%try{ %>

<s:form action="hypcxxgl!initMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.pchwLsxh" />
	<s:hidden name="domain.pcDjxh" />
	<s:hidden name="domain.pcfsDm" cssClass="reserveText" />
	<s:hidden name="domain.yfjsfDm" cssClass="reserveText" />
	<s:hidden name="domain.cyrClhmXh" />
	<s:hidden name="domain.cyrGchmXh" />
	<s:hidden name="domain.wsspztDm" />
	<s:hidden name="domain.pcHwxxDomain.pchwClfsDm" />
	<s:hidden name="domain.xtcs20004" cssClass="reserveText" />
	<s:hidden name="domain.xtcs20030" cssClass="reserveText"/>
	<s:hidden name="domain.xtcs20000" cssClass="reserveText"/>
	
	<s:hidden name="clxxData" cssClass="reserveText" />
	<s:hidden name="gcxxData" cssClass="reserveText" />
	
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">操作：</li>
		    <li><a href="#" id="queryBtn" class="licon04">检 索</a></li>
		    <li><a href="#" id="addClBtn" class="licon01">新增车辆</a></li>
		    <s:if test='domain.spbz == "Y" && (domain.wsspztDm == "0" || domain.wsspztDm == "2")'>
		    	<li><a href="#" id="sendBtn" class="licon10">发送审批</a></li>
		 	</s:if>
		    <li><a href="#" id="closeBtn" class="licon03">关 闭</a></li>
	  	</ul>
	</div> 
	
	<jsp:include page="/work/hygl/hy_tyd_wfhxx_pc.jsp" />
	<div id="maincont" style="display: none;"></div>
	<div class="pop_contc">
		<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
	      <tr>
	        <td width="5%" align="right">下游：</td>
	        <td width="10%">
	        	<s:if test='domain.pcfsDm == "1"'>
	        		<s:select onclick="javascript:this.title = this.value;" name="domain.zrbmDm" onchange="changeZrbm();" list="#{'':'仓库','2':'分公司','3':'分包商' }"></s:select>
	        	</s:if>
			  	<s:else>
			  		<s:select name="domain.zrbmDm" onchange="changeZrbm();" list="#{'2':'分公司','3':'分包商' }" ></s:select>
			  	</s:else>
			</td>
			<td width="14%">
				<s:hidden name="domain.zrbmDmShow" cssClass="reserveText" />
				<s:hidden name="domain.zrbmDjxhShow" cssClass="reserveText" />
				<s:hidden name="domain.zrbmDjxh" cssClass="reserveText" />
				<s:hidden name="domain.pchwClfsDm" />
				<%-- <s:if test='domain.pcfsDm == "1"'>
					<select id="zrbmGs" name="zrbmGs" disabled="disabled">
						<option value="">---请选择---</option>
					</select>
				</s:if>
				<s:else> --%>
					<div id="zrbmGsTd" class="hiddenCss"><sys:fgsList myName="zrbmGs" myId="zrbmGs" onChange="changeJsDivDis();setZrbmDjxh(this.value);changeGsDz(this.value,'QY_ZZJG');" contaisDq="N" contaisQxz="true" myClass="select"></sys:fgsList></div>
					<div id="zrbmFbsTd" class="hiddenCss"><select name="zrbmFbs" id="zrbmFbs" onChange="setZrbmDjxh(this.value);changeGsDz(this.value,'QY_FBS_DJXX');" class="select"></select></div>
				<%-- </s:else> --%>
				<s:hidden name="domain.zrbmDz" cssClass="reserveText" />
				<s:hidden name="domain.zrbmXzqhDm" cssClass="reserveText" />
				<s:hidden name="domain.zrbmXzqhMc" cssClass="reserveText" />
				<s:hidden name="domain.zrbmLxr" cssClass="reserveText" />
				<s:hidden name="domain.zrbmLxdh" cssClass="reserveText" />
			</td>
		    <td align="center" width="10%">
		    	<div id="sfzbDiv" class="hiddenCss"><input type="checkbox" onclick="chengeZbNotDis(this);" name="sfzb" id="sfzbId"/><label for="sfzbId">&nbsp;是否转包</label></div>
		    </td>
		    <td align="center" width="10%">
		    	<div id="sfjsDiv" class="hiddenCss"><input type="checkbox" onclick="changeZcDis();" name="sfjs" id="sfjsId"/><label for="sfjsId">&nbsp;是否结算</label></div>
		    </td>
	        <td align="left" width="50%">
	        	&nbsp;&nbsp;<button type="button" class="pop_btnbg" id="yesBtn">添加货物</button>&nbsp;
				<button type="button" class="pop_btnbg" id="delHwBtn">删除货物</button>&nbsp;
				<button type="button" class="pop_btnbg" id="refreshBtn">刷新货物</button>
	        </td>
	      </tr>
	    </table>
	    <table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
	    	<tr class="">
	    		<td align="left" class="bold_font" width="14%">已选货物信息：</td>
	    		<td align="right" width="8%"><div class="zc hiddenCss">总支出：</div></td>
  				<td width="7%">
  					<div class="zc hiddenCss"><s:textfield name="domain.pcHwxxDomain.zcHj" cssClass="pop_input inputright noborder bgstyle_optional hkCal" ></s:textfield></div>
  				</td>
	        	<td align="right" width="7%"><div class="zc hiddenCss">现付：</div></td>
  				<td width="7%">
  					<div class="zc hiddenCss"><s:textfield name="domain.pcHwxxDomain.zcXf" cssClass="pop_input inputright noborder bgstyle_optional hkCal" ></s:textfield></div>
  				</td>
  				<!-- 回付不存在 -->
  				<td width="8%" align="right" style="display: none;"><div class="zc hiddenCss" >回单付：</div></td>
  				<td width="7%" style="display: none;">
  					<div class="zc hiddenCss"><s:textfield name="domain.pcHwxxDomain.zcHf" cssClass="pop_input inputright noborder bgstyle_optional hkCal" ></s:textfield></div>
  				</td>
  				<td width="7%" align="right"><div class="zc hiddenCss">月结：</div></td>
  				<td width="7%">
  					<div class="zc hiddenCss"><s:textfield name="domain.pcHwxxDomain.zcYj" cssClass="pop_input inputright noborder bgstyle_optional hkCal" ></s:textfield></div>
  				</td>
  				<td width="7%" align="right"><div class="zc hiddenCss">到付：</div></td>
  				<td width="7%">
  					<div class="zc hiddenCss"><s:textfield id="zcDf" name="df" cssClass="pop_input inputright noborder bgstyle_readonly hkCal" ></s:textfield></div>
  				</td>
  				<td align="right" width="7%"><div class="zc hiddenCss">回扣：</div></td>
  				<td width="7%">
  					<div class="zc hiddenCss"><s:textfield name="domain.pcHwxxDomain.zcHk" cssClass="pop_input inputright noborder bgstyle_readonly" ></s:textfield></div>
  				</td>
  			</tr>
	    </table>
		<table id="dataList"><tr><td/></tr></table>
			<fieldset>
			<legend>派车信息</legend>
			<table width="99%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="10%" align="right">派车单号：</td>
      				<td width="13%">
      					<s:if test="domain.xtcs20004==0">
      						<s:textfield name="domain.pcdh" cssClass="pop_input noborder inputext bgstyle_optional" ></s:textfield>
      					</s:if>
      					<s:else>
	  						<s:textfield name="domain.pcdh" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
  						</s:else>
      				</td>
  					<td width="8%" align="right">重量：</td>
      				<td width="12%">
      					<s:textfield name="domain.zl" cssClass="pop_input noborder inputext bgstyle_readonly"></s:textfield>
      				</td>
      				<td width="8%" align="right">体积：</td>
      				<td width="11%">
      					<s:textfield name="domain.tj" cssClass="pop_input noborder inputext bgstyle_readonly"></s:textfield>
      				</td>
      				<td width="8%" align="right">收入：</td>
      				<td width="10%">
      					<s:textfield name="domain.srHj" cssClass="pop_input noborder inputext bgstyle_readonly"></s:textfield>
      				</td>
      				<td width="10%" align="right">派车日期</td>
      				<td width="10%">
      					<input type="text" name="domain.pcrq" id="mainForm_domain_pcrq" value="<s:date name="domain.pcrq" format="yyyy-MM-dd" />" class="ymdate reserveText"  />
      				</td>
      			</tr>
      		</table>
      		<table width="99%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css zbNotDis">
    			<tr>
    				<td width="10%" align="right">车辆：</td>
    				<td width="13%">
    					<s:select name="domain.clsxDm" list="#{'1':'自营车辆','2':'社会车辆' }" onchange="changeClxx();" class="select reserveText" />
    				</td>
    				<td width="8%" align="right">号码：</td>
    				<td width="12%">
    					<div class="inputsel" style="width: 80px; ">
		 						<s:textfield name="domain.cyrClhm" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width:50px;"></s:textfield>
		 						<a href="#" class="icon_arrow" id="clxx" onFocus="this.blur()"></a>
		 					</div>
				  		<div class="inputsc">
			              <div id="inputSel_clxx" class="inputselcont inputselFixedSize ac_results">
			              </div>
			              <iframe src="" scrolling="no" frameborder="0" style="position:absolute; top:0; left:0; z-index:-1; width:300px; height:200px;"></iframe>
			            </div>
    				</td>
    				<td width="8%" align="right">挂车：</td>
    				<td width="11%">
    					<div class="inputsel" style="width: 80px; ">
	 						<s:textfield name="domain.cyrGchm" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width:50px;"></s:textfield>
	 						<a href="#" class="icon_arrow" id="gcxx" onFocus="this.blur()"></a>
	 					</div>
				  		<div class="inputsc">
			              <div id="inputSel_gcxx" style="width: 90px; " class="inputselcont inputselFixedSize ac_results">
			              </div>
			              <iframe src="" scrolling="no" frameborder="0" style="position:absolute; top:0; left:0; z-index:-1; width:90px; height:200px;"></iframe>
			            </div>
    				</td>
    				<td width="8%" align="right">车主：</td>
    				<td width="10%">
    					<s:textfield name="domain.cyrCzxm" cssClass="pop_input noborder bgstyle_optional" />
    				</td>
    				<td width="10%" align="right">到站日期：</td>
    				<td width="10%">
    					<input type="text" name="domain.dzrq" id="mainForm_domain_dzrq" value="<s:date name="domain.dzrq" format="yyyy-MM-dd" />" class="ymdate"  />
    				</td>
    			</tr>
    			<tr>
    				<td align="right">司机：</td>
    				<td>
    					<s:textfield name="domain.cyrSjxm" cssClass="pop_input noborder bgstyle_optional" ></s:textfield>
    				</td>
    				<td align="right">身份证：</td>
    				<td>
    					<s:textfield name="domain.cyrSjsfz" cssClass="pop_input noborder bgstyle_optional" ></s:textfield>
    				</td>
    				<td align="right">手机：</td>
    				<td>
    					<s:textfield name="domain.cyrSjsjhm" cssClass="pop_input noborder bgstyle_optional" ></s:textfield>
    				</td>
    				<td align="right">电话1：</td>
    				<td>
    					<s:textfield name="domain.cyrQtlxdh" cssClass="pop_input noborder bgstyle_optional" />
    				</td>
    				<td align="right">电话2：</td>
    				<td>
    					<s:textfield name="domain.cyrQtlxdh2" cssClass="pop_input noborder bgstyle_optional" />
    				</td>
    			</tr>
   			</table>
			<table width="99%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css zbNotDis">
      			<tr>
      				<td align="right" width="6%">运费：</td>
      				<td width="8%">
      					<s:textfield name="domain.yfHj" cssClass="yfxx pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      				<td align="right" width="6%">预付：</td>
      				<td width="8%">
      					<s:textfield name="domain.yfYfyf" cssClass="yfxx pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      				<td align="right" width="6%">到付：</td>
      				<td width="8%">
      					<s:textfield name="domain.yfHdyf" cssClass="yfxx pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      				<td align="right" width="6%">回付：</td>
      				<td width="8%">
      					<s:textfield name="domain.yfHdf" cssClass="yfxx pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      				<td align="right" width="8%" id="sjs">司机收：</td>
      				<td width="7%">
      					<s:textfield name="domain.yfSjs" cssClass="yfxx pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right" width="8%">信息费：</td>
      				<td width="7%">
      					<s:textfield name="domain.yfXxf" cssClass="pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td align="right" width="6%">押金：</td>
      				<td width="8%">
      					<s:textfield name="domain.yfYj" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">备注：</td>
      				<td colspan="13">
      					<s:textarea name="domain.bz" rows="3" cssClass="pop_textarea_colspan2 noborder bgstyle_optional" ></s:textarea>
      				</td>
      			</tr>
			</table>
			</fieldset>
			<div class="pop_btn">
			 	<button type="button" class="pop_btnbg" id="saveBtn">保 存</button>
		    </div>
		</div>
		
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
