$(function(){
		initDataGrid();
		initRadio();
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		
		/*if ($("#mainForm_domain_pcfsDm").val() == "1") {
			//提货取本公司地址
			changeGsDz($("#mainForm_domain_dw4Query").val(),"QY_ZZJG");
		}else {
			if ($("#zrbmGs").val() != null && $("#zrbmGs").val() != "") {
				setZrbmDjxh($("#zrbmGs").val());
				changeGsDz($("#zrbmGs").val(),'QY_ZZJG');
			}
		}*/
		changeZrbm();
		
		var clsxDm = $("#mainForm_domain_clsxDm").val();
		var pcfsDm = $("#mainForm_domain_pcfsDm").val();
		initClxxData(230, "domain.cyrClhm", "domain.cyrClhmXh", clsxDm, pcfsDm);
		if (clsxDm == "1") {
			initGcxxData(90, "domain.cyrGchm", "domain.cyrGchmXh", clsxDm, pcfsDm);
		}
		
		onQueryPcHwxx();
		
		$("#addClBtn").click(function(){
			onAddCl();
		});
		
		$("#saveBtn").click(function(){
			var hwNumFlag = checkHwNum();
			if (hwNumFlag == "1") {
				showAlert("该派车单中某些货物已无库存，派车不成功，请检查！");
				return;
			}else if (hwNumFlag == "2") {
				showConfirm("该派车单中某些货物已超出库存可发货数量，属于超量派车，是否继续？", "onSave");
			}else {
				onSave();
			}
		});
		
		$("#refreshBtn").click(function(){
			onQueryPcHwxx("Y");
		});
		
		$("#delHwBtn").click(function(){
			var hwXh4PcDel = $(":checked[name='hwXh4PcDel']");
			if (hwXh4PcDel.length <= 0) {
				showAlert("请选择需要删除的货物！");
				return;
			}
			showConfirm("删除后将不可恢复，确定要删除该货物信息吗？", "doDeletePcHwxx");
		});
	});
	
	function onSave() {
		if (!checkPcdh()) {
			return;
		}
		
		if ($("#sfzbId")[0].checked) {
			doSave();
		}else {
			if(!checkdata()){
				return;
			}
				
			var cyrClhm = $("#mainForm_domain_cyrClhm").val();
			var cyrClhmXh = $("#mainForm_domain_cyrClhmXh").val();
			if (cyrClhm != "" && cyrClhmXh == "") {
				alert("该车辆未维护，请先维护车辆信息！");
				onAddCl();
			}else {
				var yfXxf = $("#mainForm_domain_yfXxf").val();
				if (yfXxf != "" && yfXxf/1 > 0) {
					showConfirm("该派车单有产生信息费，是否确认保存？", "doSave");
				}else if (!checkYfxx()){
					return;
				}else {
					doSave();
				}
			}
		}
	}
	
	function checkHwNum() {
		var hwNumFlag = "0";
		
		var graduateIds = $("#dataList").jqGrid('getDataIDs');
	    for (var i=0; i<graduateIds.length; i++) {
	    	var hwSl = jQuery("#dataList").jqGrid('getCell', graduateIds[i],"hwSl");
	    	var kcsl = jQuery("#dataList").jqGrid('getCell', graduateIds[i],"kcsl");
	    	hwSl = hwSl == "" ? 0 : hwSl/1;
	    	kcsl = kcsl == "" ? 0 : kcsl/1;
	    	
	    	if (kcsl <= 0) {
	    		hwNumFlag = "1";    //库存小于等于0
	    		break;
	    	}
	    	
	    	if (hwSl > kcsl) {
	    		hwNumFlag = "2";	// 超量派车
	    		break;
	    	}
	    }
		
		return hwNumFlag;
	}
	
	function checkClhmExists() {
		var cyrClhm = $("#mainForm_domain_cyrClhm").val();
		if (cyrClhm != "") {
			var cyrClhmXh = $("#mainForm_domain_cyrClhmXh").val();
			var clsxDm = $("#mainForm_domain_clsxDm").val();
			var pcfsDm = $("#mainForm_domain_pcfsDm").val();
			if (cyrClhmXh == "") {
				onDropDownSelected4Clxx(createLi4InputSel(cyrClhm));
			}
			cyrClhmXh = $("#mainForm_domain_cyrClhmXh").val();
			if (cyrClhmXh == "") {
				showAlert("该车辆未维护，请先点击【新增车辆】维护车辆信息！");
			}
		} 
	}
	
	function onAddCl(){
		var cyrClhm = $("#mainForm_domain_cyrClhm").val();
		var clsxDm = $("#mainForm_domain_clsxDm").val();
		var pcfsDm = $("#mainForm_domain_pcfsDm").val();
		if(clsxDm == 1){
			var url = jcontextPath+"/zygl/qyylclxx!initMx?domain.clhm="+cyrClhm+"&domain.pcfsDm="+pcfsDm+"&domain.callOpenWinFun=initClxxFromCldj";
			window.showModalDialog(url,window,"dialogHeight:540px;dialogWidth:680px;center:yes;resizable:no;status:no;scroll:yes;help:no;");
		}else if(clsxDm == 2){
			var url = jcontextPath+"/zygl/qyylclxxshcl!initMx?domain.clhm="+cyrClhm+"&domain.pcfsDm="+pcfsDm+"&domain.callOpenWinFun=initClxxFromCldj";
			window.showModalDialog(url,window,"dialogHeight:540px;dialogWidth:680px;center:yes;resizable:no;status:no;scroll:yes;help:no;");
			//window.open(url);
		}
	}
	
	function initClxxFromCldj(cyrClhmXh, cyrClhm) {
		$("#mainForm_domain_cyrClhmXh").val(cyrClhmXh);
		$("#mainForm_domain_cyrClhm").val(cyrClhm);
		changeClxx();
	}
	
	function checkYfxx() {
		if ($("#mainForm_domain_yfHj").val()/1 != $("#mainForm_domain_yfYfyf").val()/1 + $("#mainForm_domain_yfHdyf").val()/1 + $("#mainForm_domain_yfHdf").val()/1 + $("#mainForm_domain_yfSjs").val()/1) {
			alert("运费信息录入有误，运费需满足公式：\"总运费=预付运费\+司机收\+货到运费\+回单付\"，请检查！");
			return false;
		}
		return true;
	}
	
	function doSave() {
		var yfZjf = '';//$("#mainForm_domain_yfZjf").val();
		var xxzjDjxh = '';//$("#mainForm_domain_xxzjDjxh").val(); 
		
		var pcdh = trim($("#mainForm_domain_pcdh").val()); 
		var clsxDm = $("#mainForm_domain_clsxDm").val(); 
		var cyrClhmXh = trim($("#mainForm_domain_cyrClhmXh").val()); 
		var cyrCzxm = trim($("#mainForm_domain_cyrCzxm").val()); 
		var cyrClhm = trim($("#mainForm_domain_cyrClhm").val()); 
		var cyrGchm = trim($("#mainForm_domain_cyrGchm").val()); 
		var cyrSjxm = trim($("#mainForm_domain_cyrSjxm").val()); 
		var cyrSjsfz = trim($("#mainForm_domain_cyrSjsfz").val()); 
		var cyrSjsjhm = trim($("#mainForm_domain_cyrSjsjhm").val()); 
		var cyrQtlxdh = trim($("#mainForm_domain_cyrQtlxdh").val()); 
		var cyrQtlxdh2 = trim($("#mainForm_domain_cyrQtlxdh2").val()); 
		var dzrq = $("#mainForm_domain_dzrq").val();
		var pcrq = $("#mainForm_domain_pcrq").val();
		var yfHj = trim($("#mainForm_domain_yfHj").val()); 
		var yfYfyf = trim($("#mainForm_domain_yfYfyf").val()); 
		var yfYj = trim($("#mainForm_domain_yfYj").val()); 
		var yfXxf = trim($("#mainForm_domain_yfXxf").val()); 
		var yfSjs = trim($("#mainForm_domain_yfSjs").val()); 
		var yfHdyf = trim($("#mainForm_domain_yfHdyf").val()); 
		var yfHdf = trim($("#mainForm_domain_yfHdf").val()); 
		var yfjsfDm = $("#mainForm_domain_yfjsfDm").val(); 
		var bz = trim($("#mainForm_domain_bz").val()); 
		var pcfsDm = $("#mainForm_domain_pcfsDm").val(); 
		var ysfsDm = '1';//$("#mainForm_domain_ysfsDm").val(); 
		var zcfsDm = '1';//$("#mainForm_domain_zcfsDm").val(); 
		var pcDjxh = $("#mainForm_domain_pcDjxh").val();
		var pchwLsxh = $("#mainForm_domain_pchwLsxh").val();
		
		/*var zrbmDm = '2';
		var zrbmDjxh = $("#mainForm_domain_zrbmDjxh").val(); 
		var zrbmXzqhDm = $("#mainForm_domain_zrbmXzqhDm").val();
		var zrbmDz = $("#mainForm_domain_zrbmDz").val();
		var zrbmLxr = $("#mainForm_domain_zrbmLxr").val();
		var zrbmLxdh = $("#mainForm_domain_zrbmLxdh").val();*/
		
		var hwNum = $("#dataList tr").length;
		if (hwNum <= 1) {
			showAlert("请先选择货物再保存！");
			return;
		}

		var url = jcontextPath+"/hygl/hypcxxgl!save";  
    	var jsonObj = {"domain.pcdh":pcdh,"domain.pcrq":pcrq,"domain.clsxDm":clsxDm,"domain.cyrClhmXh":cyrClhmXh,"domain.cyrCzxm":cyrCzxm,
                       "domain.cyrClhm":cyrClhm,"domain.cyrGchm":cyrGchm,"domain.cyrSjxm":cyrSjxm,"domain.cyrSjsfz":cyrSjsfz,"domain.cyrSjsjhm":cyrSjsjhm,
                       "domain.cyrQtlxdh":cyrQtlxdh,"domain.cyrQtlxdh2":cyrQtlxdh2,"domain.dzrq":dzrq,
                       "domain.yfHj":yfHj,"domain.yfYfyf":yfYfyf,"domain.yfYj":yfYj,"domain.yfXxf":yfXxf,
                       "domain.yfSjs":yfSjs,"domain.yfHdyf":yfHdyf,"domain.yfHdf":yfHdf,"domain.yfZjf":yfZjf,
                       "domain.yfjsfDm":yfjsfDm,"domain.xxzjDjxh":xxzjDjxh,"domain.bz":bz,
                       "domain.pcfsDm":pcfsDm,"domain.ysfsDm":ysfsDm,"domain.zcfsDm":zcfsDm,"domain.pcDjxh":pcDjxh,"domain.pchwLsxh":pchwLsxh};   
                       /*,"domain.zrbmDm":zrbmDm,"domain.zrbmDjxh":zrbmDjxh,"domain.zrbmDz":zrbmDz,
                       "domain.zrbmLxr":zrbmLxr,"domain.zrbmLxdh":zrbmLxdh,"domain.zrbmXzqhDm":zrbmXzqhDm}; */  			
		showMessage();
    	ajaxCommon(url,jsonObj, "doSaveSuc");
	}
	
	function changeClxx() {
		var pcfsDm = $("#mainForm_domain_pcfsDm").val();
		if (pcfsDm != '4') {
			var clsxDm = $("#mainForm_domain_clsxDm").val();
			$("[name='domain.cyrClhm']").unbind();
			$("#mainForm_domain_cyrClhm").bind("change", function() {
				setTimeout(checkClhmExists, 200);
			});
			
			initClxxData(230, "domain.cyrClhm", "", clsxDm, pcfsDm);
			
			$("[name='domain.cyrGchm']").unbind();
			if (clsxDm == "1") {
				$("#inputSel_gcxx").empty();
			}else {
				initGcxxData(90, "domain.cyrGchm", "", clsxDm, pcfsDm);
			}
		}
		
	}
	
	function chengeZbNotDis(obj) {
		if (obj.value == '4') {
			$(".zbNotDis").css({"display":"none"});
		}else {
			$(".zbNotDis").css({"display":"block"});
		}
	}
	
	function initRadio() {
		var pcfsDm = $("#mainForm_domain_pcfsDm").val();
		chengeZbNotDis($("#mainForm_domain_pcfsDm")[0]);
	}
	
	function doSaveSuc(data) {
		//$("#mainForm_domain_pcDjxh").val(data.domain.pcDjxh);
		hideMessage();
		showAlert("保存成功！","yesSaveCallBack");
	}
	
	function yesSaveCallBack() {
		var pcDjxh = $("#mainForm_domain_pcDjxh").val();
		if (pcDjxh == "") {
			emptyForm();
			onRefresh();
		}else {
			$("#closeBtn").click();
		}
	}
	
	function emptyForm() {
		$(":input:text:not(.reserveText)").attr("value", "");
		$("#mainForm_domain_bz").val("");
		/*if ($("#mainForm_domain_pcfsDm").val() != "1") {
			$("[name^='domain.zrbm']").attr("value","");
		}*/
		//$("#mainForm_domain_zrbmDm").val("2");
		//$("#mainForm_domain_clsxDm").val("1");
		jQuery("#dataList").jqGrid("clearGridData");
	}
	
	function saveWfhxx4Pc(wfhXhs) {
		var pchwLsxh = $("#mainForm_domain_pchwLsxh").val();
		var pcDjxh = $("#mainForm_domain_pcDjxh").val();
		var pcfsDm = $("#mainForm_domain_pcfsDm").val();
		var pchwClfsDm = $("#mainForm_domain_pcHwxxDomain_pchwClfsDm").val();
		var zrbmDm = $("#mainForm_domain_zrbmDm").val();
		var zrbmDjxh = $("#mainForm_domain_zrbmDjxh").val(); 
		var zrbmXzqhDm = $("#mainForm_domain_zrbmXzqhDm").val();
		var zrbmDz = $("#mainForm_domain_zrbmDz").val();
		var zrbmLxr = $("#mainForm_domain_zrbmLxr").val();
		var zrbmLxdh = $("#mainForm_domain_zrbmLxdh").val();
		
		var zcHj = $("#mainForm_domain_pcHwxxDomain_zcHj").val();
		var zcXf = $("#mainForm_domain_pcHwxxDomain_zcXf").val();
        var zcYj = $("#mainForm_domain_pcHwxxDomain_zcYj").val();
        var zcHf = $("#mainForm_domain_pcHwxxDomain_zcHf").val();
        var zcHk = $("#mainForm_domain_pcHwxxDomain_zcHk").val();
		
		var url = jcontextPath+"/hygl/hypcxxgl!saveWfhxx4Pc";
		var jsonObj = {"domain.wfhXhs":wfhXhs,"domain.pcDjxh":pcDjxh,"domain.pchwLsxh":pchwLsxh,"domain.pcfsDm":pcfsDm,
				"domain.zrbmDm":zrbmDm,"domain.zrbmDjxh":zrbmDjxh,"domain.zrbmDz":zrbmDz,
		        "domain.zrbmLxr":zrbmLxr,"domain.zrbmLxdh":zrbmLxdh,"domain.zrbmXzqhDm":zrbmXzqhDm,
		        "domain.pcHwxxDomain.zcHj":zcHj,"domain.pcHwxxDomain.zcXf":zcXf,
				"domain.pcHwxxDomain.zcYj":zcYj,"domain.pcHwxxDomain.zcHf":zcHf,
				"domain.pcHwxxDomain.zcHk":zcHk,"domain.pcHwxxDomain.pchwClfsDm":pchwClfsDm}
		
		ajaxCommon(url,jsonObj,"saveWfhxx4PcSuc");
	}
	
	function saveWfhxx4PcSuc(data) {
		var pchwLsxh = data.domain.pchwLsxh;
		if ($("#mainForm_domain_pchwLsxh").val() == "") {
			$("#mainForm_domain_pchwLsxh").val(pchwLsxh)
		}
		
		// 清空支出信息
		$("[name^='domain.pcHwxxDomain.zc']").attr("value","");
		$("#zcDf").val("");
		
		onRefresh();
		onQueryPcHwxx();
	}
	
	function onUpdateHw(pcDjxh,pchwLsxh,wfhDjxh) {
		var pcfsDm = $("#mainForm_domain_pcfsDm").val();
		var url = jcontextPath+"/hygl/hypcxxgl!initWfhxx4Pc.action?domain.pcHwxxDomain.pcDjxh="+pcDjxh+"&domain.pcHwxxDomain.pchwLsxh="+pchwLsxh
				+"&domain.pcHwxxDomain.wfhDjxh="+wfhDjxh+"&domain.pcfsDm="+pcfsDm+"&num="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:390px;dialogWidth:420px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
		//window.open(url)
	}
	
	function doDeletePcHwxx() {
		var pcDjxh = $("#mainForm_domain_pcDjxh").val();
		var pchwLsxh = $("#mainForm_domain_pchwLsxh").val();
		
		var bbhs = new Array();
		var tempBz = new Array()
		var hwXh4PcDel = $(":checked[name='hwXh4PcDel']");
		$.each(hwXh4PcDel, function(i, hwxh){
			var bbh = $(":input[name='bbhs']",$(hwxh).parents("tr"));
			bbhs.push(bbh[0]);
			var temp = $(":input[name='tempBz']",$(hwxh).parents("tr"));
			tempBz.push(temp[0]);
		});
		
		var jsonStr = getJqueryParam(hwXh4PcDel, "domain.hwXh4PcDel") + getJqueryParam(bbhs, "domain.bbhs") +
						getJqueryParam(tempBz, "domain.tempBz");
		var jsonObj = {"domain.pcDjxh":pcDjxh,"domain.pchwLsxh":pchwLsxh};
		jsonStr += jQuery.param(jsonObj);
		
		var url = jcontextPath+"/hygl/hypcxxgl!deleteWfhxx4Pc";
		showMessage();
		ajaxCommon(url,encodeURI(jsonStr),"deletePcHwxxSuc", false);
	}
	
	function deletePcHwxxSuc(data) {
		hideMessage();
		onRefresh();
		showAlert("删除成功！");
		onQueryPcHwxx();
	}
	
	// 重写base.js里的方法
	function setGridAuto(){  
       var gridTabWidth=pageWidth()-20;  	//去掉宽度的计算，只保留高度
       var gridTabHeigt=pageTableHeight()-90;  
       $("#dataList").setGridWidth(gridTabWidth);  
       $("#dataList").setGridHeight(gridTabHeigt);  
	} 
	
	/*******************处理货站（转入部门）信息begin*******************/
	function setZrbmDjxh(val) {
		$("#mainForm_domain_zrbmDjxh").val(val);
	}
	
	function changeGsDz(zrbmDjxh, tableName) {
		var url = jcontextPath+"/hygl/hycommondown!queryGsDz";
		var jsonObj = {"domain.thShdzDomain.zrbmDjxh":zrbmDjxh,"domain.thShdzDomain.tableName":tableName};
		ajaxCommon(url,jsonObj,"changeGsDzSuc");
	}
	
	function changeGsDzSuc(data) {
		var item = data.domain.thShdzDomain;
		$("#mainForm_domain_zrbmXzqhDm").val(item.zrbmXzqhDm);
		$("#mainForm_domain_zrbmXzqhMc").val(item.zrbmXzqhMc);
		$("#mainForm_domain_zrbmDz").val(item.zrbmDz);
		$("#mainForm_domain_zrbmLxr").val(item.zrbmLxr);
		$("#mainForm_domain_zrbmLxdh").val(item.zrbmLxdh);
	}
	
	function checkZrDzdata(){
		var pcfsDm = $("#mainForm_domain_pcfsDm").val();
		var zrbmDjxh = $("#mainForm_domain_zrbmDjxh").val();
		var zrbmDz = $("#mainForm_domain_zrbmDz").val();
		var zrbmDm = $("#mainForm_domain_zrbmDm").val();
		
		// 运输派车时必须选择一个下游，如果转到分包商时必须选择一个分包商,转包时必须选择下游
		if (zrbmDjxh == "") {
			if (pcfsDm == "2"  || zrbmDm == "3" || $("#sfzbId")[0].checked) {
				showAlert("请先选择一个下游！");
				return false;
			}
		}
		
		//提货
		if (pcfsDm == "1" && zrbmDjxh == "" && zrbmDz == "") {
			showAlert("基础信息维护不完整，请到系统管理中将公司的地址、行政区划、负责人、联系电话等补充完整后再派车！");
			return false;
		}else if (zrbmDjxh != "" && zrbmDz == "") {
			showAlert("基础信息维护不完整，请到系统管理中将所选择下游的地址、行政区划、负责人、联系电话等补充完整后再派车！");
			return false;
		}
		return true;
	}
	/*******************处理货站（转入部门）信息end*******************/
	
	function checkPcdh() {
		var xtcs20004 = $("#mainForm_domain_xtcs20004").val();
		var controlNameArray = ["domain.pcdh"];
		var labelNameArray = ["派车单号"];
		var compareValueArray = [50];
		var nodeTypeArray = [NodeType.STRING];
		var notNullArray = [false];				
		
		if (xtcs20004 == '0') {
			notNullArray = [true];	
		}
		
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	
	function checkdata(){
		var controlNameArray = ["domain.cyrCzxm","domain.cyrClhm","domain.cyrGchm","domain.cyrSjxm","domain.cyrSjsfz","domain.cyrSjsjhm",
		                        "domain.cyrQtlxdh","domain.cyrQtlxdh2","domain.yfHj","domain.yfYfyf","domain.yfYj","domain.yfXxf",
		                        "domain.yfSjs","domain.yfHdyf","domain.yfHdf",
		                        "domain.bz"];
		var labelNameArray = ["车主","号码","挂车","司机","身份证","手机",
		                      "电话1","电话2","运费","预付","押金","信息费",
		                      "司机收","到付","回付",
		                      "备注"];
		var compareValueArray = [100,20,20,20,18,11,
			                     100,100,14.2,14.2,14.2,14.2,
			                     14.2,14.2,14.2,
			                     500];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,NodeType.STRING,
			                 NodeType.STRING,NodeType.STRING,NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,
			                 NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,
			                 NodeType.STRING];
		var notNullArray = [false,true,false,true,true,true,
                            false,false,false,false,false,
                            false,false,false,false,
                            false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	
	function queryPcxxSjsInitValSuc(data) {
		var yfSjs = data.domain.yfSjs;
		if (yfSjs == null || yfSjs == "null" || yfSjs/1 == 0) {
			yfSjs = "";
		}
		$("#mainForm_domain_yfSjs").val(yfSjs);
		
		var bz = data.domain.bz;
		if (data.domain.bz == null || data.domain.bz == "null") {
			bz = "";
		}else {
			bz = "<"+data.domain.bz+">";
		}
		var str = $("#mainForm_domain_bz").val() + bz;
		if (checkSjsBz($("#mainForm_domain_bz").val())) {
			str = $("#mainForm_domain_bz").val().replace(/<[^>]+>/, bz);
		}				
		$("#mainForm_domain_bz").val(str);
		
		calYfxx();
	}
	
	function checkSjsBz(strVar) {
	    var str = strVar.match(/<[^>]+>/);
	    if (str == null) {
	        return false;
	    }
	    else {
	        return true;
	    }
	}
	
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onQueryPcHwxx(refreshBbhFlag){
		refreshBbhFlag = refreshBbhFlag == undefined ? "N" : refreshBbhFlag;
		var pcfsDm = $("#mainForm_domain_pcfsDm").val();
		if (pcfsDm == "1") {
			$("#dataList").jqGrid('hideCol',["srHj","df"]);
		}
		
		 var pcDjxh = $("#mainForm_domain_pcDjxh").val();
		 var pchwLsxh = $("#mainForm_domain_pchwLsxh").val();
		 
		 var pcfsDm = $("#mainForm_domain_pcfsDm").val();
		 if (pcfsDm != "1") {
			 var yfSjs = $("#mainForm_domain_yfSjs").val();
			 var urlInit = jcontextPath+"/hygl/hypcxxgl!queryPcxxSjsInitVal";   
			 var jsonObj = {"domain.pcDjxh":pcDjxh,"domain.pchwLsxh":pchwLsxh};
			 ajaxCommon(urlInit,jsonObj,"queryPcxxSjsInitValSuc");
		 }
		 
		//请求表格数据
		var url = jcontextPath+"/hygl/hypcxxgl!queryPcHwxx";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.pcDjxh":pcDjxh,"domain.pchwLsxh":pchwLsxh,"domain.refreshBbhFlag":refreshBbhFlag}
		 	//请求的参数，json格式
		 }
		 ).trigger("reloadGrid");		//显示到第一页，以免总体数据发生变化的时候，分页的信息混乱
	}
	
	//jqGrid  初始化表格
	function initDataGrid(){ 
	  $("#dataList").jqGrid({
	    url:"",
	    datatype: 'local',
	    mtype: 'POST',
	    rownumbers : true,					//序号列
		width:pageWidth()-20,  
		height:pageTableHeight()-90,	
	    gridComplete: myGridComplete,		//表格加载完毕事件
	    shrinkToFit:false, 
	    colNames:['订单登记序号','pchwLsxh','pcDjxh','订单编号','发货人','<input title="选中/取消选中" type="checkbox" onclick="checkAllJGridChk(event,this,\'hwXh4PcDel\');" />',
	            '货物名称','始发地','目的地','未发货序号input','未发货序号',
	            '包装','类别','数量','库存数量','重量','体积','数量','重量','体积','收入','到付','下游','送货方式',
	    		'回单号','收货人','收货地址','发货日期','到达日期',
	    		'发货地址','登记人','登记日期','登记部门','所属机构'],			 //name 列显示的名称
	     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
	    colModel :[
		  {name:'ddDjxh', index:'ddDjxh', hidden:true, width:'70', align:'center'},
		  {name:'pchwLsxh', index:'pchwLsxh', hidden:true, width:'70', align:'center'},
		  {name:'pcDjxh', index:'pcDjxh', hidden:true, width:'70', align:'center'},
		  {name:'ddbh', index:'ddbh', width:'70', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'ddbh' + rowId + '\'';
		    }
		  },
		  {name:'fhrMc', index:'fhrMc', width:'130', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'fhrMc' + rowId + '\'';
		    }
		  },
		  {name:'hstoperationcol', index:'hstoperationcol', sortable:false, width:'30', align:'center'},
	       
	      {name:'hwmc', index:'hwmc', width:'100', sortable:false, align:'center'}, 
	      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'40', sortable:false, align:'center'}, 
	      {name:'shrXzqhMc', index:'shrXzqhMc', width:'40', sortable:false, align:'center'},
	      {name:'xh', index:'xh', width:'100', hidden:true, align:'center'},
	      {name:'wfhDjxh', index:'wfhDjxh', width:'100', hidden:true, align:'center'},
	      {name:'bz', index:'bz', width:'30', sortable:false, align:'center'},
	      {name:'lb', index:'lb', width:'40', sortable:false, align:'center'},
	      {name:'sl', index:'sl', width:'50', sortable:false, align:'right'},
	      {name:'kcsl', index:'kcsl', width:'50', sortable:false, align:'right'},
	      {name:'zl', index:'zl', width:'50', sortable:false, align:'right'}, 
	      {name:'tj', index:'tj', width:'50', sortable:false, align:'right'},
	      {name:'hwSl', index:'hwSl', width:'30', hidden:true, sortable:false, align:'right'},
	      {name:'hwZl', index:'hwZl', width:'30', hidden:true, sortable:false, align:'right'}, 
	      {name:'hwTj', index:'hwTj', width:'30', hidden:true, sortable:false, align:'right'},
	      {name:'srHj', index:'srHj', width:'45', sortable:false, align:'center'},
	      {name:'df', index:'df', width:'45', sortable:false, align:'center'},
	      {name:'zrbmMc', index:'zrbmMc', width:'80', sortable:false, align:'center'},
	      {name:'shfsMc', index:'shfsMc', width:'60', sortable:false, align:'center'},

	      {name:'hdbh', index:'hdbh', width:'70', sortable:false, align:'right'},
	      {name:'shrMc', index:'shrMc', width:'60', sortable:false, align:'center'}, 
	      {name:'shDz', index:'shDz', width:'100', sortable:false, align:'center'}, 
	      {name:'fhRq', index:'fhRq', width:'70', sortable:false, align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}, 
	      {name:'yqDdrq', index:'yqDdrq', width:'70', sortable:false, align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
	      {name:'fhrDz', index:'fhrDz', width:'100', sortable:false, align:'center'},
	      {name:'djrMc', index:'djrMc', width:'70', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'djrMc' + rowId + '\'';
		    }
		  },
		  {name:'djRq', index:'djRq', width:'70', sortable:false, align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'djRq' + rowId + '\'';
		    }
		  },
		  {name:'djJgmc', index:'djJgmc', width:'100', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'djJgmc' + rowId + '\'';
		    }
		  },
		  {name:'ssJgmc', index:'ssJgmc', width:'100', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'ssJgmc' + rowId + '\'';
		    }
		  }
	    ],
	    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
	    rowNum: -1,							//在grid上显示记录条数，这个参数是要被传递到后台
	    rowList:[-1],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
	    sortname: 'DD_DJXH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
	    sortorder: 'DESC',				//默认排序方向
	    viewrecords: true,					//定义是否要显示总记录数
	    gridview: false,					//默认false 构造一行数据后添加到grid中，如果设为true则是将整个表格的数据都构造完成后再添加到grid中，但treeGrid, subGrid, or afterInsertRow 不能用	
	    jsonReader: {     
	 	 	root: 	 "domain.pcHwxxList",   				// 数据行（默认为：rows）
	 	 	page:	 "domain.page.curPage",					// 当前页
	 	 	total: 	 "domain.page.totalPages",				// 总页数
	 	 	records: "domain.page.totalRecords", 			// 总记录数
	 	 	//userdata: "userdata",						    // 在某些情况下，我们需要从服务器端返回一些参数但并不想直接把他们显示到表格中，而是想在别的地方显示
	    	repeatitems : false     						// 设置成false，在后台设置值的时候，可以乱序。且并非每个值都得设
	 	},
	 	prmNames:{rows:"domain.page.pageSize",page:"domain.page.curPageNo",sort:"domain.page.orderBy",
	 	order:"domain.page.order",search:"domain.page.search"}
	    //caption: '数据信息'							//表格名称,
	  }); 
	  
	  //设置page选项为1
	  jQuery("#dataList").jqGrid('navGrid','#pager',
	 		 {edit:false,add:false,del:false}
	  );
	  
	}
	
	function onViewMx(ddDjxh) {
		var url = jcontextPath+"/jcgl/jctydgl!initMx.action?domain.ddDjxh="+ddDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    	//window.open(url);
	}
	
	//表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
	function myGridComplete() {
	    var graduateIds = $("#dataList").jqGrid('getDataIDs');
	    var pcfsDm = $("#mainForm_domain_pcfsDm").val();
	    
	    var heightT = getAutoGridHeight(graduateIds.length);
	    $("#dataList").setGridHeight(heightT);
	    
	    var srHjs = 0.00;
	    var zls = 0.00;
	    var tjs = 0.00;
	    for (var i = 0; i < graduateIds.length; i++) {
	        var cl = graduateIds[i];
	        var wfhDjxh = jQuery("#dataList").jqGrid('getCell', cl,"wfhDjxh");
	        var hwmc = jQuery("#dataList").jqGrid('getCell', cl,"hwmc");
	        var pchwLsxh = jQuery("#dataList").jqGrid('getCell', cl,"pchwLsxh");
	        var pcDjxh = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh");
	        var ddbh = jQuery("#dataList").jqGrid('getCell', cl,"ddbh");
	        var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh");
	        var str="<a href=\"javascript:onViewMx("+ddDjxh+")\"><font color=\"blue\">"+ddbh+"</font></a>";
	        var link = "<input type=\"checkbox\" name=\"hwXh4PcDel\" value=\""+wfhDjxh+"\" />";
	        var mcLink = "<a href=\"javascript:onUpdateHw('"+pcDjxh+"','"+pchwLsxh+"','"+wfhDjxh+"')\"><font color=\"blue\">"+hwmc+"</font></a>";
	        $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
	        $("#dataList").jqGrid('setRowData', cl, { 'hwmc': mcLink });
	        $("#dataList").jqGrid('setRowData', cl, { 'ddbh': str });
	        var hj = jQuery("#dataList").jqGrid('getCell', cl,"srHj");
	        var hwzl = jQuery("#dataList").jqGrid('getCell', cl,"hwZl");
	        var hwtj = jQuery("#dataList").jqGrid('getCell', cl,"hwTj");
	        // 提货没有收入
	        if (hj != "" && pcfsDm != "1") {
	        	srHjs += parseFloat(hj);
	        }
	        if (hwzl != "") {
	        	zls += parseFloat(hwzl);
	        }
	        if (hwtj != "") {
	        	tjs += parseFloat(hwtj);
	        }
	       
	    }
	    $("#mainForm_domain_zl").val(zls.toFixed(2));
	    $("#mainForm_domain_tj").val(tjs.toFixed(2));
	    $("#mainForm_domain_srHj").val(srHjs.toFixed(2));
	    
	   var gridName = "dataList";
		   var a = ['ddbh','fhrMc','djRq','djrMc','djJgmc','ssJgmc'];
			
	    Merger(gridName, 'ddDjxh', a);
	}
/**************************************分页结束****************************************/