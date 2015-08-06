$(function(){
		initDataGrid();
		initRadio();
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		
		/*if ($("#mainForm_domain_pcfsDm").val() == "1") {
			//���ȡ����˾��ַ
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
				showAlert("���ɳ�����ĳЩ�������޿�棬�ɳ����ɹ������飡");
				return;
			}else if (hwNumFlag == "2") {
				showConfirm("���ɳ�����ĳЩ�����ѳ������ɷ������������ڳ����ɳ����Ƿ������", "onSave");
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
				showAlert("��ѡ����Ҫɾ���Ļ��");
				return;
			}
			showConfirm("ɾ���󽫲��ɻָ���ȷ��Ҫɾ���û�����Ϣ��", "doDeletePcHwxx");
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
				alert("�ó���δά��������ά��������Ϣ��");
				onAddCl();
			}else {
				var yfXxf = $("#mainForm_domain_yfXxf").val();
				if (yfXxf != "" && yfXxf/1 > 0) {
					showConfirm("���ɳ����в�����Ϣ�ѣ��Ƿ�ȷ�ϱ��棿", "doSave");
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
	    		hwNumFlag = "1";    //���С�ڵ���0
	    		break;
	    	}
	    	
	    	if (hwSl > kcsl) {
	    		hwNumFlag = "2";	// �����ɳ�
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
				showAlert("�ó���δά�������ȵ��������������ά��������Ϣ��");
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
			alert("�˷���Ϣ¼�������˷������㹫ʽ��\"���˷�=Ԥ���˷�\+˾����\+�����˷�\+�ص���\"�����飡");
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
			showAlert("����ѡ������ٱ��棡");
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
		showAlert("����ɹ���","yesSaveCallBack");
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
		
		// ���֧����Ϣ
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
		showAlert("ɾ���ɹ���");
		onQueryPcHwxx();
	}
	
	// ��дbase.js��ķ���
	function setGridAuto(){  
       var gridTabWidth=pageWidth()-20;  	//ȥ����ȵļ��㣬ֻ�����߶�
       var gridTabHeigt=pageTableHeight()-90;  
       $("#dataList").setGridWidth(gridTabWidth);  
       $("#dataList").setGridHeight(gridTabHeigt);  
	} 
	
	/*******************�����վ��ת�벿�ţ���Ϣbegin*******************/
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
		
		// �����ɳ�ʱ����ѡ��һ�����Σ����ת���ְ���ʱ����ѡ��һ���ְ���,ת��ʱ����ѡ������
		if (zrbmDjxh == "") {
			if (pcfsDm == "2"  || zrbmDm == "3" || $("#sfzbId")[0].checked) {
				showAlert("����ѡ��һ�����Σ�");
				return false;
			}
		}
		
		//���
		if (pcfsDm == "1" && zrbmDjxh == "" && zrbmDz == "") {
			showAlert("������Ϣά�����������뵽ϵͳ�����н���˾�ĵ�ַ�����������������ˡ���ϵ�绰�Ȳ������������ɳ���");
			return false;
		}else if (zrbmDjxh != "" && zrbmDz == "") {
			showAlert("������Ϣά�����������뵽ϵͳ�����н���ѡ�����εĵ�ַ�����������������ˡ���ϵ�绰�Ȳ������������ɳ���");
			return false;
		}
		return true;
	}
	/*******************�����վ��ת�벿�ţ���Ϣend*******************/
	
	function checkPcdh() {
		var xtcs20004 = $("#mainForm_domain_xtcs20004").val();
		var controlNameArray = ["domain.pcdh"];
		var labelNameArray = ["�ɳ�����"];
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
		var labelNameArray = ["����","����","�ҳ�","˾��","���֤","�ֻ�",
		                      "�绰1","�绰2","�˷�","Ԥ��","Ѻ��","��Ϣ��",
		                      "˾����","����","�ظ�",
		                      "��ע"];
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
	
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
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
		 
		//����������
		var url = jcontextPath+"/hygl/hypcxxgl!queryPcHwxx";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.pcDjxh":pcDjxh,"domain.pchwLsxh":pchwLsxh,"domain.refreshBbhFlag":refreshBbhFlag}
		 	//����Ĳ�����json��ʽ
		 }
		 ).trigger("reloadGrid");		//��ʾ����һҳ�������������ݷ����仯��ʱ�򣬷�ҳ����Ϣ����
	}
	
	//jqGrid  ��ʼ�����
	function initDataGrid(){ 
	  $("#dataList").jqGrid({
	    url:"",
	    datatype: 'local',
	    mtype: 'POST',
	    rownumbers : true,					//�����
		width:pageWidth()-20,  
		height:pageTableHeight()-90,	
	    gridComplete: myGridComplete,		//����������¼�
	    shrinkToFit:false, 
	    colNames:['�����Ǽ����','pchwLsxh','pcDjxh','�������','������','<input title="ѡ��/ȡ��ѡ��" type="checkbox" onclick="checkAllJGridChk(event,this,\'hwXh4PcDel\');" />',
	            '��������','ʼ����','Ŀ�ĵ�','δ�������input','δ�������',
	            '��װ','���','����','�������','����','���','����','����','���','����','����','����','�ͻ���ʽ',
	    		'�ص���','�ջ���','�ջ���ַ','��������','��������',
	    		'������ַ','�Ǽ���','�Ǽ�����','�Ǽǲ���','��������'],			 //name ����ʾ������
	     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
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
	    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
	    rowNum: -1,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
	    rowList:[-1],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
	    sortname: 'DD_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
	    sortorder: 'DESC',				//Ĭ��������
	    viewrecords: true,					//�����Ƿ�Ҫ��ʾ�ܼ�¼��
	    gridview: false,					//Ĭ��false ����һ�����ݺ���ӵ�grid�У������Ϊtrue���ǽ������������ݶ�������ɺ�����ӵ�grid�У���treeGrid, subGrid, or afterInsertRow ������	
	    jsonReader: {     
	 	 	root: 	 "domain.pcHwxxList",   				// �����У�Ĭ��Ϊ��rows��
	 	 	page:	 "domain.page.curPage",					// ��ǰҳ
	 	 	total: 	 "domain.page.totalPages",				// ��ҳ��
	 	 	records: "domain.page.totalRecords", 			// �ܼ�¼��
	 	 	//userdata: "userdata",						    // ��ĳЩ����£�������Ҫ�ӷ������˷���һЩ������������ֱ�Ӱ�������ʾ������У��������ڱ�ĵط���ʾ
	    	repeatitems : false     						// ���ó�false���ں�̨����ֵ��ʱ�򣬿��������Ҳ���ÿ��ֵ������
	 	},
	 	prmNames:{rows:"domain.page.pageSize",page:"domain.page.curPageNo",sort:"domain.page.orderBy",
	 	order:"domain.page.order",search:"domain.page.search"}
	    //caption: '������Ϣ'							//�������,
	  }); 
	  
	  //����pageѡ��Ϊ1
	  jQuery("#dataList").jqGrid('navGrid','#pager',
	 		 {edit:false,add:false,del:false}
	  );
	  
	}
	
	function onViewMx(ddDjxh) {
		var url = jcontextPath+"/jcgl/jctydgl!initMx.action?domain.ddDjxh="+ddDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    	//window.open(url);
	}
	
	//��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
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
	        // ���û������
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
/**************************************��ҳ����****************************************/