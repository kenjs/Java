<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<head>
<title>托运单-货物明细</title>

<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript">
	var hwflObserverFlag = true;
	$(function(){
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		initJsJldwDm();
		initHwflDm();
		var khDjxh = trim($("#mainForm_domain_fhrDjxh").val()); 
		initHyhwData(200,khDjxh);
		initJldw();
		$("#mainForm_domain_hwmxDomain_hwBzHldwDm").focus();
		$("#hwmcShow2").hide();
		$("#showSaveHw2").hide();
		initXzqhData(200);
	   //行政区划input下拉
	   initXzqhInputSel();
		var fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
	    if (fhrDjxh != "") {
		  initHyZhdzData(fhrDjxh,300);
		  initHyShDwData(fhrDjxh,300);
	    }
		$("#mainForm_domain_fhrDjxh2").val($("#mainForm_domain_fhrDjxh").val());
		$("#mainForm_domain_fhrMc2").val($("#mainForm_domain_fhrMc").val());
		$(".saveHwBtn").click(function(){
			doSaveHwStart(0);
		});
		$("#emptyHwBtn").click(function(){
		     emptyForm();
		})
		$("#mainForm_domain_hwmxDomain_hwflDm").click(function() {
			hwflObserverFlag = false;
		});
		
		$(".hwflObserver").change(function(){
			var xh = $("#mainForm_domain_hwmxDomain_xh").val();
			if (xh != "") {
				hwflObserverFlag = false;
				return;
			}
			if (hwflObserverFlag) {
				if ($("#mainForm_domain_hwmxDomain_hwTj").val() != "" && $("#mainForm_domain_hwmxDomain_hwZl").val() == "") {
					$("[name='hwflDm']")[1].checked = true;
				}else if ($("#mainForm_domain_hwmxDomain_hwZl").val() != "" && $("#mainForm_domain_hwmxDomain_hwTj").val() == "") {
					$("[name='hwflDm']")[0].checked = true;
				}else if ($("#mainForm_domain_hwmxDomain_hwZl").val() != "" && $("#mainForm_domain_hwmxDomain_hwTj").val() != "") {
					var ZlTjProportion = $("#mainForm_domain_hwmxDomain_ZlTjProportion").val();
					if (ZlTjProportion != "") {
						var zl = $("#mainForm_domain_hwmxDomain_hwZl").val();
						var tj = $("#mainForm_domain_hwmxDomain_hwTj").val();
						if (zl/1 > tj/1*ZlTjProportion) {
							$("[name='hwflDm']")[0].checked = true;
						}else {
							$("[name='hwflDm']")[1].checked = true;
						}
					}
				}
			}
		});
		document.getElementById('mainForm_domain_hwmxDomain_hwBzHldwDm').disabled=true;
		document.getElementById('mainForm_domain_hwmxDomain_hwSlJldwDm').disabled=true;
		document.getElementById('mainForm_domain_hwmxDomain_hwZlJldwDm').disabled=true;
		document.getElementById('mainForm_domain_hwmxDomain_hwTjJldwDm').disabled=true;	
	});
	var saveTydFlag;
	function doSaveHwStart(flag){
			saveTydFlag = flag;
			var fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		    var fhrMc = $("#mainForm_domain_fhrMc").val();
			if (fhrMc != "" && fhrDjxh == "") {
				alert("该客户未维护，请先维护客户信息！");
				onAddKh();
				return;
			}
			if(!checkHwData()){
				return;
			} 
			if (!checkLxfs()) {
				return;
			}
			if (!checkHdbh()) {
				return;
			}
			if (!checkJsJldwFlDm()) {
				return;
			}
			if(!checkXtcs20014()){
			    return;
			}

			var srHj = trim($("#mainForm_domain_srHj").val());
			if (srHj == "") {
				srConfirmYes();
			}else {
				if (!checkSr()) {
					return;
				}
				
				var hk = $("#mainForm_domain_srHk").val();
				if (hk/1 > 0) {
					showConfirm("该托运单中收入有产生回扣，是否确认保存？","srConfirmYes");
				}else {
					srConfirmYes();
				}
			}
	}
	function doSaveHw(){ 
			var xh = trim($("#mainForm_domain_hwmxDomain_xh").val()); 
			var hwmc = trim($("#mainForm_domain_hwmxDomain_hwmc").val()); 
			var hwDjxh = trim($("#mainForm_domain_hwmxDomain_hwDjxh").val());
			var hwxhDjxh = trim($("#mainForm_domain_hwmxDomain_hwxhDjxh").val());
			var hwBzHldwDm = trim($("#mainForm_domain_hwmxDomain_hwBzHldwDm").val()); 
			var hwSl = trim($("#mainForm_domain_hwmxDomain_hwSl").val()); 
			var hwSlJldwDm = trim($("#mainForm_domain_hwmxDomain_hwSlJldwDm").val()); 
			var hwZl = trim($("#mainForm_domain_hwmxDomain_hwZl").val()); 
			var hwZlJldwDm = trim($("#mainForm_domain_hwmxDomain_hwZlJldwDm").val()); 
			var hwTj = trim($("#mainForm_domain_hwmxDomain_hwTj").val()); 
			var hwTjJldwDm = trim($("#mainForm_domain_hwmxDomain_hwTjJldwDm").val()); 
			var hwflDm = $("[name='hwflDm']:checked").val();
			var ddDjxh = trim($("#mainForm_domain_ddDjxh").val());
			var jsJldwFlDm = $("#jsJldwFlDm").val();
			var hdbh = $("#mainForm_domain_hwmxDomain_hdbh").val();
			var tempFlag = $("#mainForm_domain_hwmxDomain_tempFlag").val();
			
			var fhrXzqhDm = $("#mainForm_domain_fhrXzqhDm").val(); 
			var shrXzqhDm = $("#mainForm_domain_shrXzqhDm").val(); 
			
			var fhrDjxh2 = $("#mainForm_domain_fhrDjxh2").val();
			var fhrMc2 = $("#mainForm_domain_fhrMc2").val();                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                      
			var fhrDz = $("#mainForm_domain_fhrDz").val(); 
			var fhrLxr = $("#mainForm_domain_fhrLxr").val();
			var fhrLxdh = $("#mainForm_domain_fhrLxdh").val(); 
			var shrDjxh = $("#mainForm_domain_shrDjxh").val();
			var shrMc = $("#mainForm_domain_shrMc").val(); 
			var shrDz = $("#mainForm_domain_shrDz").val(); 
			var shrLxr = $("#mainForm_domain_shrLxr").val();
			var shrLxdh = $("#mainForm_domain_shrLxdh").val(); 		
			var yqFhrq = $("#mainForm_domain_yqFhrq").val(); 
			var yqDdrq = $("#mainForm_domain_yqDdrq").val(); 
			var shfsDm = document.getElementsByName("shfsDm")[0].checked ? "1" : "2"; 
	
			//var hdbh = $("#mainForm_domain_hdbh").val();
			 
			var thflDm = $("[name='thflDm']")[0].checked ? "1" : "";
			var yjjsfsDm = $("#mainForm_domain_yjjsfsDm").val();
			var ykjsfsDm;
			
			
			var srHj = trim($("#mainForm_domain_srHj").val());
			if(srHj==""){
				ykjsfsDm = "2";
			}else{
				ykjsfsDm = "1";
			}
			var srXf = $("#mainForm_domain_srXf").val();
	        var srHdf = $("#mainForm_domain_srHdf").val();
	        var srThf = $("#mainForm_domain_srThf").val();
	        var srYj = "0";
	        var srHf = $("#mainForm_domain_srHf").val();
	        var srHk = $("#mainForm_domain_srHk").val();
			
			
			var url = jcontextPath+"/hygl/hytydgl!saveHwMx";  
	    	var jsonObj = {"domain.hwmxDomain.xh":xh,"domain.hwmxDomain.hwmc":hwmc,"domain.hwmxDomain.hwDjxh":hwDjxh,"domain.hwmxDomain.hwBzHldwDm":hwBzHldwDm,
	    				   "domain.hwmxDomain.hwSl":hwSl,"domain.hwmxDomain.hwSlJldwDm":hwSlJldwDm,"domain.hwmxDomain.hwZl":hwZl,
                           "domain.hwmxDomain.hwZlJldwDm":hwZlJldwDm,"domain.hwmxDomain.hwTj":hwTj,"domain.hwmxDomain.hwTjJldwDm":hwTjJldwDm,
                           "domain.hwmxDomain.hwflDm":hwflDm,"domain.hwmxDomain.ddDjxh":ddDjxh,"domain.hwmxDomain.hwxhDjxh":hwxhDjxh,
                           "domain.hwmxDomain.jsJldwFlDm":jsJldwFlDm,"domain.hwmxDomain.hdbh":hdbh,"domain.hwmxDomain.tempFlag":tempFlag,
                           "domain.hwmxDomain.fhrXzqhDm":fhrXzqhDm,"domain.hwmxDomain.shrXzqhDm":shrXzqhDm,
	    				   "domain.hwmxDomain.fhrDjxh":fhrDjxh2,"domain.hwmxDomain.fhrMc":fhrMc2,"domain.hwmxDomain.fhrDz":fhrDz,"domain.hwmxDomain.fhrLxr":fhrLxr,"domain.hwmxDomain.fhrLxdh":fhrLxdh,
	    				   "domain.hwmxDomain.shrDjxh":shrDjxh,"domain.hwmxDomain.shrMc":shrMc,"domain.hwmxDomain.shrDz":shrDz,"domain.hwmxDomain.shrLxr":shrLxr,"domain.hwmxDomain.shrLxdh":shrLxdh,
	    				   "domain.hwmxDomain.yqFhrq":yqFhrq,"domain.hwmxDomain.yqDdrq":yqDdrq,"domain.hwmxDomain.shfsDm":shfsDm,"domain.hwmxDomain.thflDm":thflDm,
	    				   "domain.hwmxDomain.yjjsfsDm":yjjsfsDm,"domain.hwmxDomain.ykjsfsDm":ykjsfsDm,"domain.hwmxDomain.srHj":srHj,"domain.hwmxDomain.srXf":srXf,
	    		           "domain.hwmxDomain.srHdf":srHdf,"domain.hwmxDomain.srThf":srThf,"domain.hwmxDomain.srYj":srYj,"domain.hwmxDomain.srHf":srHf,"domain.hwmxDomain.srHk":srHk};   			
	    	showMessage();
	    	ajaxCommon(url,jsonObj,"saveHwSuc");
		}
		function srConfirmYes() {
			var yqFhrq = $("#mainForm_domain_yqFhrq").val(); 
			var yqDdrq = $("#mainForm_domain_yqDdrq").val();
			var dStr = getNow(false); 
			if ((yqFhrq != "" && yqFhrq < dStr) || (yqDdrq != "" && yqDdrq < dStr)) {
				showConfirm("【要求发货日期】或【要求到货日期】小于当前日期，是否确认保存？", "doSaveHw");
			}else {
				doSaveHw();
			}
		}
		
		function checkSr() {
			var hk = $("#mainForm_domain_srHk").val();
			if (hk/1 == 0) {
				if (($("#mainForm_domain_srXf").val()/1+$("#mainForm_domain_srHdf").val()/1+$("#mainForm_domain_srThf").val()/1+$("#mainForm_domain_srHf").val()/1) != $("#mainForm_domain_srHj").val()/1) {
					alert("收入录入有误，收入需满足公式：\"收入=现付\+到付\+回付\"，请检查！");
					return false;
				}
			}
			return true;
		}
	
	function checkJsJldwFlDm() {
		var jsJldwFlDm = $("#jsJldwFlDm").val();

		var srHj = $("#mainForm_domain_srHj").val()/1;
		if(jsJldwFlDm==""&&srHj==0){
		    showAlert("收入为空，结算方式必选！");
		    return false;
		}
		
		var hwSl = trim($("#mainForm_domain_hwmxDomain_hwSl").val()); 
		var hwSlJldwDm = trim($("#mainForm_domain_hwmxDomain_hwSlJldwDm").val()); 
		if ("01" == jsJldwFlDm && ("" == hwSlJldwDm || hwSl/1 <= 0)) {
			objForFocus = $("#mainForm_domain_hwmxDomain_hwSl");
			showAlert("结算方式选择有误，选中的结算方式对应的值必须大于0！", "focusSel");
			return false;
		}
		
		var hwZl = trim($("#mainForm_domain_hwmxDomain_hwZl").val()); 
		var hwZlJldwDm = trim($("#mainForm_domain_hwmxDomain_hwZlJldwDm").val()); 
		if ("02" == jsJldwFlDm && ("" == hwZlJldwDm || hwZl/1 <= 0)) {
			objForFocus = $("#mainForm_domain_hwmxDomain_hwZl");
			showAlert("结算方式选择有误，选中的结算方式对应的值必须大于0！", "focusSel");
			return false;
		}
		
		var hwTj = trim($("#mainForm_domain_hwmxDomain_hwTj").val()); 
		var hwTjJldwDm = trim($("#mainForm_domain_hwmxDomain_hwTjJldwDm").val());
		if ("03" == jsJldwFlDm && ("" == hwTjJldwDm || hwTj/1 <= 0)) {
			objForFocus = $("#mainForm_domain_hwmxDomain_hwTj");
			showAlert("结算方式选择有误，选中的结算方式对应的值必须大于0！", "focusSel");
			return false;
		}
		return true;
	}
	function checkXtcs20014(){
	    var xtcs20014 = $("#mainForm_domain_xtcs20014").val();
	    var hwSl = trim($("#mainForm_domain_hwmxDomain_hwSl").val());
	    var hwZl = trim($("#mainForm_domain_hwmxDomain_hwZl").val()); 
		var hwTj = trim($("#mainForm_domain_hwmxDomain_hwTj").val()); 
		if(hwSl/1<=0){
	        showAlert("数量需大于零！");
	        return false;
        }
	    if("Y"==xtcs20014){
        	if(hwZl/1<=0||hwTj/1<=0){
		        showAlert("当前系统参数，重量体积均需大于零！");
		        return false;
       		}
	    }else {
	    	if (hwZl/1<=0 && hwTj/1<=0) {
	    		showAlert("【重量】和【体积】不能同时为0，必须至少录入其中一项！");
	    		return false;
	    	}
	    }
	    
	    return true;
	}
	function checkLxfs() {
		var fhrLxr = $("#mainForm_domain_fhrLxr").val();
		var fhrLxdh = $("#mainForm_domain_fhrLxdh").val();
		if (fhrLxr == "" && fhrLxdh == "") {
			showAlert("【发货人联系人】和【发货人联系电话】不能同时为空，必须至少录入其中一项！");
			return false;
		}
		
		var shrLxr = $("#mainForm_domain_shrLxr").val();
		var shrLxdh = $("#mainForm_domain_shrLxdh").val();
		if (shrLxr == "" && shrLxdh == "") {
			showAlert("【收货人联系人】和【收货人联系电话】不能同时为空，必须至少录入其中一项！");
			return false;
		}
		return true;
	}
	function saveHwSuc(data) {
		hideMessage();
		emptyForm();
		var tempFlag = data.domain.hwmxDomain.tempFlag;
		if ("Y" == tempFlag) {
			$("#mainForm_domain_hwmxDomain_ddDjxh").val(data.domain.hwmxDomain.ddDjxh);
			$("#mainForm_domain_ddDjxh", document).val(data.domain.hwmxDomain.ddDjxh);
			refreshHwList();
		}else {
			refreshHwList();
		}
		if(saveTydFlag==0){
			showAlert("修改成功!","doFinish");
		}else{
			doFinish();
		}
		
	}
	
	function emptyForm() {
	    $("#lrbz").text("货物信息【新增】");
	    $("#hwmcShow1").show();
	    $("#hwmcShow2").hide();
	    $("#showSaveHw1").show();
	    $("#showSaveHw2").hide();
	    
	    $("#mainForm_domain_hwmxDomain_hwDjxh").val("");
	    $("#mainForm_domain_hwmxDomain_hwxhDjxh").val("");
	    $("#mainForm_domain_hwmxDomain_hwmc").val("");
		$("#mainForm_domain_hwmxDomain_hdbh").val("");
		$("#mainForm_domain_hwmxDomain_hwSl").val("");
		$("#mainForm_domain_hwmxDomain_hwZl").val("");
		$("#mainForm_domain_hwmxDomain_hwTj").val("");
		
		$("#mainForm_domain_hwmxDomain_xh").val("");
		$("#jsJldwFlDm").val("00");
		$("select").val("");
		initJldw();
		$("#mainForm_domain_hwmxDomain_hwmc").focus();
		$("#mainForm_domain_hwmxDomain_hwmc").select();
		
		var s="";
		$("#mainForm_domain_srHj").val(s);
		$("#mainForm_domain_srXf").val(s);
        $("#mainForm_domain_srHdf").val(s);
        $("#mainForm_domain_srThf").val(s);
        $("#mainForm_domain_srYj").val(s);
        $("#mainForm_domain_srHf").val(s);
        $("#mainForm_domain_srHk").val(s);
	}
	function doFinish() {
		if(saveTydFlag==1){
			saveData();
		}
	}
	
	function checkHwData(){
		var controlNameArray = ["domain.fhrXzqhDm","domain.shrXzqhDm",
		                        "domain.fhrMc","domain.fhrDz","domain.fhrLxr","domain.fhrLxdh",
		                        "domain.shrMc","domain.shrDz","domain.shrLxr","domain.shrLxdh",
		                        "domain.yqFhrq","domain.yqDdrq",
		                        "domain.srHj","domain.srXf",
		                        "domain.srHdf","domain.srThf","domain.srHf","domain.srYj","domain.srHk",
		                        "domain.hwmxDomain.hwmc","domain.hwmxDomain.hwBzHldwDm","domain.hwmxDomain.hwSl","domain.hwmxDomain.hwSlJldwDm",
		                        "domain.hwmxDomain.hwZl","domain.hwmxDomain.hwZlJldwDm","domain.hwmxDomain.hwTj","domain.hwmxDomain.hwTjJldwDm"];
		var labelNameArray = ["始发地","目的地",
		                      "客户名称","发货地址","发货人联系人","发货人联系电话",
		                      "收货单位","收货地址","收货人联系人","收货人联系电话",
		                      "托运日期","到货日期",
		                      "总收入","现付",
		                      "货到付","提货付","回单付","月结","回扣",
		                      "货物名称","包装","数量","数量_计量单位","重量",
		                      "重量_计量单位","体积","体积_计量单位"];
		var compareValueArray = [20,20,
			                     100,100,40,100,
			                     100,100,40,100,
			                     11,11,
			                     14.2,14.2,
			                     14.2,14.2,14.2,14.2,14.2,
		                         100,6,12.2,6,12.2,
			                     6,12.2,6];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,
		                     NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING,
			                 NodeType.DECIMAL,NodeType.DECIMAL,
			                 NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,
		                     NodeType.STRING,NodeType.STRING,NodeType.DECIMAL,NodeType.STRING,
		                     NodeType.DECIMAL,NodeType.STRING,NodeType.DECIMAL,NodeType.STRING];
		var notNullArray = [true,true,
		                    true,false,false,false,
                            false,false,false,false,
                            false,false,
                            true,true,
                            true,true,true,true,true,
		                    true,false,false,false,false,
                            false,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	
	function checkHdbh() {
		var hdbh = $("#mainForm_domain_hwmxDomain_hdbh").val();
		var str = hdbh.match(/^[0-9a-zA-Z\,\uff0c]*$/);
		if (str == null) {
			objForFocus = $("#mainForm_domain_hdbh");
			showAlert("回单编号录入有误，回单编号只能包含数字和字母，多个回单编号以\",\"分割！","focusSel");
			return false;
		}
		return true;
	}
	
	//根据企业的基本计量单位初始化 货物的计量单位
	function initJldw() {
		var xh = $("#mainForm_domain_hwmxDomain_xh").val();
		if (xh == "") {
			$("#mainForm_domain_hwmxDomain_hwBzHldwDm").val($("#mainForm_domain_hwmxDomain_qyHwBzJldwDm").val());
			$("#mainForm_domain_hwmxDomain_hwSlJldwDm").val($("#mainForm_domain_hwmxDomain_qyHwSlJldwDm").val());
			$("#mainForm_domain_hwmxDomain_hwZlJldwDm").val($("#mainForm_domain_hwmxDomain_qyHwZlJldwDm").val());
			$("#mainForm_domain_hwmxDomain_hwTjJldwDm").val($("#mainForm_domain_hwmxDomain_qyHwTjJldwDm").val());
		}
	}
	
	//初始化结算计量单位分类代码
	function initJsJldwDm() {
		var jsJldwFlDm = $("#mainForm_domain_hwmxDomain_jsJldwFlDm").val();
		 $("#jsJldwFlDm").val(jsJldwFlDm);
	}
	
	function initHwflDm() {
		var hwflDm = $("#mainForm_domain_hwmxDomain_hwflDm").val();
		if (hwflDm == '2') {
			$("[name='hwflDm']")[1].checked = true;
		}else {
			$("[name='hwflDm']")[0].checked = true;
		}
	}
</script>
</head>

<body>
<%try{ %>

	
	<s:hidden name="domain.hwmxDomain.xh" />
	<s:hidden name="domain.hwmxDomain.hwflDm" />
	
	<s:hidden name="domain.hwmxDomain.jsJldwFlDm" />
	<s:hidden name="domain.hwmxDomain.qyHwBzJldwDm" />
	<s:hidden name="domain.hwmxDomain.qyHwSlJldwDm" />
	<s:hidden name="domain.hwmxDomain.qyHwZlJldwDm" />
	<s:hidden name="domain.hwmxDomain.qyHwTjJldwDm" />
	<s:hidden name="domain.hwmxDomain.ZlTjProportion" cssClass="notChange"/>
	

	
	<s:hidden name="hwmcData"></s:hidden>		
		<fieldset>
		<legend id="lrbz">货物信息【新增】</legend>
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
  			<tr>
  				<td width="12%" class="td_noborder"></td>
  				<td width="10%" class="td_noborder"></td>
  				<td width="13%" class="td_noborder"></td>
  				<td width="11%" class="td_noborder"></td>
  				<td width="10%" class="td_noborder"></td>
  				<td width="12%" class="td_noborder"></td>
  				<td width="14%" class="td_noborder"></td>
  				<td width="12%" class="td_noborder"></td>
  				<td width="6%" class="td_noborder"></td>
  			</tr>
  			<tr>
  				<td align="right" >发货单位：</td>
  				<td colspan="2">
  					    <s:hidden name="domain.fhrDjxh2"></s:hidden>
  						<s:textfield name="domain.fhrMc2" cssClass="pop_input noborder inputext bgstyle_readonly" ></s:textfield>
  				</td>
  				<td align="right">发货人：</td>
  				<td>
  					<div class="inputsel" style="width: 100%; ">
  						<s:textfield name="domain.fhrLxr" cssClass="pop_input noborder inputext bgstyle_readonly" ></s:textfield>
					</div>
			  		<div class="inputsc">
		              <div id="inputSel_fhrLxr" class="inputselcont inputselFixedSize ac_results">
		              </div>
		            </div>
  				</td>
  				<td align="right">联系电话：</td>
  				<td>
  					<s:textfield name="domain.fhrLxdh" cssClass="pop_input noborder bgstyle_readonly" />
  				</td>
  				
  				<td align="right" colspan="2"></td>
  			</tr>
  			<tr>
  				<td align="right">发货地址：</td>
  				<td colspan="2">
  					<s:textfield name="domain.fhrDz" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
  				</td>
  				<td align="right">始发地：</td>
  				<td>
  					<s:hidden name="domain.fhrXzqhDm" />
  					<div class="inputsel" style="width: 100%;">
  						<s:textfield name="domain.fhrXzqhMc" cssClass="pop_input noborder inputext bgstyle_readonly" ></s:textfield>
		            </div>
  				</td>
  				<td align="right">是否提货：</td>
  				<td>
  					<s:radio name="thflDm" list="#{'1':' 是','':' 否' }" theme="simple" ></s:radio>
  				</td>
  				<td align="right">发货日期：</td>
  				<td>
  					<input type="text" name="domain.yqFhrq" id="mainForm_domain_yqFhrq" value="<s:date name="domain.yqFhrq" format="yyyy-MM-dd" />" disabled="disabled"/>
  				</td>
  			</tr>
  			<tr>
  				<td align="right">收货单位：</td>
  				<td colspan="2">
  					<s:hidden name="domain.shrDjxh"></s:hidden>
  					<div class="inputsel" style="width: 97%; ">
  					<s:textfield name="domain.shrMc" cssClass="pop_input noborder inputext bgstyle_readonly" ></s:textfield>
  						<a href="#" class="icon_arrow" id="shrMc" onFocus="this.blur()"></a></div>
			  		<div class="inputsc">
		              <div id="inputSel_shrMc" class="inputselcont inputselFixedSize ac_results">
		              </div>
		            </div>
  				</td>
  				<td align="right">收货人：</td>
  				<td>
  					<s:textfield name="domain.shrLxr" cssClass="pop_input noborder bgstyle_readonly" />
  				</td>
  				<td align="right">联系电话：</td>
  				<td>
  					<s:textfield name="domain.shrLxdh" cssClass="pop_input noborder bgstyle_readonly" />
  				</td>
  				<td colspan="2"></td>
  			</tr>
  			<tr>
  				<td align="right">收货地址：</td>
  				<td colspan="2">
  					<s:textfield name="domain.shrDz" cssClass="pop_input noborder bgstyle_readonly" />
  				</td>
  				<td align="right" >目的地：</td>
  				<td>
  					<s:hidden name="domain.shrXzqhDm"></s:hidden>
  					<div class="inputsel" style="width: 100%">
  						<s:textfield name="domain.shrXzqhMc" cssClass="inputext pop_input noborder bgstyle_readonly" ></s:textfield>
		            </div>
  				</td>
  				<td align="right">送货方式：</td>
  				<td >
  					<s:select list="#{'':' ---请选择---','1':' 自提','2':' 送货' }" id="shfsDm" cssClass="select" disabled="true"></s:select>
  				</td>
  				
  				<td align="right">到货日期：</td>
  				<td>
  					<input type="text" name="domain.yqDdrq" id="mainForm_domain_yqDdrq" value="<s:date name="domain.yqDdrq" format="yyyy-MM-dd" />"  disabled="disabled"/>
  				</td>
  			</tr>
  			<tr>
      				<td align="right">货物名称：</td>
      				<td colspan="2">
      					<s:hidden name="domain.hwmxDomain.hwDjxh"></s:hidden>
      					<s:hidden name="domain.hwmxDomain.hwxhDjxh"></s:hidden>
      					<div id="hwmcShow1">
	      					<div class="inputsel" style="width:97%">
		  						<s:textfield name="domain.hwmxDomain.hwmc" cssClass="pop_input noborder inputext bgstyle_required" ></s:textfield>
		  					</div>
	  					</div>
	  					<div id="hwmcShow2">
	  					    <input type="text" id="hwmc2" class="pop_input noborder inputext bgstyle_readonly"/>
		                </div>
				  		<div class="inputsc">
			              <div id="inputSel_hwmc" class="inputselcont inputselFixedSize ac_results">
			              </div>
			              <iframe src="" scrolling="no" frameborder="0" style="position:absolute; top:0; left:0; z-index:-1; width:200px; height:200px;"></iframe>
			            </div>
      				</td>
      				<td align="right">回单号：</td>
      				<td >
      					<s:textfield name="domain.hwmxDomain.hdbh" cssClass="pop_input noborder bgstyle_readonly" />
      				</td>
      				<td colspan="3" style="display: none;">
      					<div class="font_red" >(注：多个回单编号用逗号分隔)</div>
      				</td>
      				<td align="right">结算：</td>
      				<td >
      				    <s:select list="#{'':' ---请选择---','01':' 按数量','02':' 按重量','03':' 按体积' }" id="jsJldwFlDm" cssClass="select" disabled="true"></s:select>
      				</td>
      				<td align="right">包装：</td>
      				<td>
      					<sys:QyBzJldw myName="domain.hwmxDomain.hwBzHldwDm" myId="mainForm_domain_hwmxDomain_hwBzHldwDm" contaisQxz="true" myClass="select">   						
      					</sys:QyBzJldw>
      				</td>
      			</tr>
		</table>
		
		
		<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td width="10%" class="td_noborder"></td>
      				<td width="5%" class="td_noborder"></td>
      				<td width="5%" class="td_noborder"></td>
      				
      				<td width="10%" class="td_noborder"></td>
      				<td width="5%" class="td_noborder"></td>
      				<td width="5%" class="td_noborder"></td>
      				
      				<td width="10%" class="td_noborder"></td>
      				<td width="5%" class="td_noborder"></td>
      				<td width="5%" class="td_noborder"></td>
      				
      				<td width="8%" class="td_noborder"></td>
      				<td width="12%" class="td_noborder"></td>
      				
      				<td width="8%" class="td_noborder"></td>
      				<td width="12%" class="td_noborder"></td>

      			</tr>
      			
      			<tr>
      				<td align="right">数量：</td>
      				<td>
      					<s:textfield name="domain.hwmxDomain.hwSl" cssClass="pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td>
      					<sys:QySlJldw myName="domain.hwmxDomain.hwSlJldwDm" myId="mainForm_domain_hwmxDomain_hwSlJldwDm" contaisQxz="false" myClass="select noTabSelect">
      					</sys:QySlJldw>
      				</td>      			
      				<td align="right">重量：</td>
      				<td>
      					<s:textfield name="domain.hwmxDomain.hwZl" cssClass="pop_input inputright noborder bgstyle_readonly hwflObserver" ></s:textfield>
      				</td>
      				<td>
      					<sys:QyZlJldw myName="domain.hwmxDomain.hwZlJldwDm" myId="mainForm_domain_hwmxDomain_hwZlJldwDm" contaisQxz="false" myClass="select noTabSelect">      						
      					</sys:QyZlJldw>
      				</td>
      				<td align="right">体积：</td>
      				<td>
      					<s:textfield name="domain.hwmxDomain.hwTj" cssClass="pop_input inputright noborder bgstyle_readonly hwflObserver" ></s:textfield>
      				</td>
      				<td>
      					<sys:QyTjJldw myName="domain.hwmxDomain.hwTjJldwDm" myId="mainForm_domain_hwmxDomain_hwTjJldwDm" contaisQxz="false" myClass="select noTabSelect">      					
      					</sys:QyTjJldw>
      				</td>
      				
      				<td align="right">类型：</td>
      				<td>
      					<s:radio id="hwflDm" name="hwflDm" list="#{'1':' 重货','2':' 泡货' }" theme="simple" disabled="true"></s:radio>
      				</td>
      				<td align="right">代收货款：</td>
      				<td>
      					<s:textfield name="domain.hwmxDomain.fyDshk" cssClass="pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      		   </tr>
      		   <tr>
  				<td align="right">运输费:</td>
  				<td colspan="2">
  					<s:textfield name="domain.hwmxDomain.srYsf" cssClass="pop_input inputright noborder bgstyle_readonly srHjJs" ></s:textfield>
  				</td>
  				<td align="right">配送费:</td>
  				<td colspan="2">
  					<s:textfield name="domain.hwmxDomain.srPsf" cssClass="pop_input inputright noborder bgstyle_readonly srHjJs" ></s:textfield>
  				</td>
  				<td  class="jsfsChanged" align="right">回扣：</td>
  				<td class="jsfsChanged" colspan="2">
  					<s:textfield name="domain.srHk" cssClass="pop_input inputright noborder bgstyle_readonly srHjJs " ></s:textfield>
  				</td>
  				<td align="right">保价费:</td>
  				<td >
  					<s:textfield name="domain.hwmxDomain.srBjf" cssClass="pop_input inputright noborder bgstyle_readonly srHjJs" ></s:textfield>
  				</td>
  				<td align="right"  class="jsfsChanged">总收入：</td>
  				<td class="jsfsChanged" >
  					<s:textfield name="domain.srHj" cssClass="pop_input inputright noborder bgstyle_readonly  jsfsChanged" ></s:textfield>
  				</td>
  			</tr>
  			<tr>
  				
  				<td align="right" >现付：</td>
  				<td colspan="2">
  					<s:textfield name="domain.srXf" cssClass="pop_input inputright noborder bgstyle_readonly " ></s:textfield>
  				</td>
  				<td align="right" class="df">到付：</td>
  				<td class="df" colspan="2">
  					<s:textfield name="domain.srHdf" cssClass="pop_input inputright noborder bgstyle_readonly " ></s:textfield>
  				</td>
  				<td align="right" class="tf">到付：</td>
  				<td class="tf" colspan="2" >
  					<s:textfield name="domain.srThf" cssClass="pop_input inputright noborder bgstyle_readonly " ></s:textfield>
  				</td>
  				<td align="right" class="jsfsChanged" >月结：</td>
  				<td  class="jsfsChanged" colspan="2">
  					<s:textfield name="domain.srYj" cssClass="pop_input inputright noborder bgstyle_readonly  jsfsChanged" ></s:textfield>
  				</td>
  				<td align="right" class="jsfsChanged" style="display: none;">回付：</td>
  				<td  class="jsfsChanged" style="display: none;">
  					<s:textfield name="domain.srHf" cssClass="pop_input inputright noborder bgstyle_readonly  jsfsChanged" ></s:textfield>
  				</td>
  				<td align="right">声明价值</td>
  				<td >
  					<s:textfield name="domain.hwmxDomain.fySmjz" cssClass="pop_input inputright noborder bgstyle_readonly bjjs" ></s:textfield>
  				</td>
  				<td align="right">保价比率</td>
  				<td >
  					<s:select list="#{'0.005':' 千分之五','0.010':' 千分之十' }" id="bjbl" cssClass="select bjjs" disabled="true"></s:select>
  				</td>
  			</tr>
  			<tr >
  				<td align="right">备注：</td>
  				<td colspan="17">
  					<s:textarea name="domain.bz" rows="3" cssClass="pop_textarea_colspan2 noborder bgstyle_readonly" ></s:textarea>
  				</td>
  			</tr>
			</table>
		 
		</fieldset>
			
	
	<%@include file="/common/message.jsp" %>

<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
