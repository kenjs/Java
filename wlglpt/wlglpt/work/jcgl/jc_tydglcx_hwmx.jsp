<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<head>
<title>���˵�-������ϸ</title>

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
	   //��������input����
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
				alert("�ÿͻ�δά��������ά���ͻ���Ϣ��");
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
					showConfirm("�����˵��������в����ؿۣ��Ƿ�ȷ�ϱ��棿","srConfirmYes");
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
				showConfirm("��Ҫ�󷢻����ڡ���Ҫ�󵽻����ڡ�С�ڵ�ǰ���ڣ��Ƿ�ȷ�ϱ��棿", "doSaveHw");
			}else {
				doSaveHw();
			}
		}
		
		function checkSr() {
			var hk = $("#mainForm_domain_srHk").val();
			if (hk/1 == 0) {
				if (($("#mainForm_domain_srXf").val()/1+$("#mainForm_domain_srHdf").val()/1+$("#mainForm_domain_srThf").val()/1+$("#mainForm_domain_srHf").val()/1) != $("#mainForm_domain_srHj").val()/1) {
					alert("����¼���������������㹫ʽ��\"����=�ָ�\+����\+�ظ�\"�����飡");
					return false;
				}
			}
			return true;
		}
	
	function checkJsJldwFlDm() {
		var jsJldwFlDm = $("#jsJldwFlDm").val();

		var srHj = $("#mainForm_domain_srHj").val()/1;
		if(jsJldwFlDm==""&&srHj==0){
		    showAlert("����Ϊ�գ����㷽ʽ��ѡ��");
		    return false;
		}
		
		var hwSl = trim($("#mainForm_domain_hwmxDomain_hwSl").val()); 
		var hwSlJldwDm = trim($("#mainForm_domain_hwmxDomain_hwSlJldwDm").val()); 
		if ("01" == jsJldwFlDm && ("" == hwSlJldwDm || hwSl/1 <= 0)) {
			objForFocus = $("#mainForm_domain_hwmxDomain_hwSl");
			showAlert("���㷽ʽѡ������ѡ�еĽ��㷽ʽ��Ӧ��ֵ�������0��", "focusSel");
			return false;
		}
		
		var hwZl = trim($("#mainForm_domain_hwmxDomain_hwZl").val()); 
		var hwZlJldwDm = trim($("#mainForm_domain_hwmxDomain_hwZlJldwDm").val()); 
		if ("02" == jsJldwFlDm && ("" == hwZlJldwDm || hwZl/1 <= 0)) {
			objForFocus = $("#mainForm_domain_hwmxDomain_hwZl");
			showAlert("���㷽ʽѡ������ѡ�еĽ��㷽ʽ��Ӧ��ֵ�������0��", "focusSel");
			return false;
		}
		
		var hwTj = trim($("#mainForm_domain_hwmxDomain_hwTj").val()); 
		var hwTjJldwDm = trim($("#mainForm_domain_hwmxDomain_hwTjJldwDm").val());
		if ("03" == jsJldwFlDm && ("" == hwTjJldwDm || hwTj/1 <= 0)) {
			objForFocus = $("#mainForm_domain_hwmxDomain_hwTj");
			showAlert("���㷽ʽѡ������ѡ�еĽ��㷽ʽ��Ӧ��ֵ�������0��", "focusSel");
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
	        showAlert("����������㣡");
	        return false;
        }
	    if("Y"==xtcs20014){
        	if(hwZl/1<=0||hwTj/1<=0){
		        showAlert("��ǰϵͳ���������������������㣡");
		        return false;
       		}
	    }else {
	    	if (hwZl/1<=0 && hwTj/1<=0) {
	    		showAlert("���������͡����������ͬʱΪ0����������¼������һ�");
	    		return false;
	    	}
	    }
	    
	    return true;
	}
	function checkLxfs() {
		var fhrLxr = $("#mainForm_domain_fhrLxr").val();
		var fhrLxdh = $("#mainForm_domain_fhrLxdh").val();
		if (fhrLxr == "" && fhrLxdh == "") {
			showAlert("����������ϵ�ˡ��͡���������ϵ�绰������ͬʱΪ�գ���������¼������һ�");
			return false;
		}
		
		var shrLxr = $("#mainForm_domain_shrLxr").val();
		var shrLxdh = $("#mainForm_domain_shrLxdh").val();
		if (shrLxr == "" && shrLxdh == "") {
			showAlert("���ջ�����ϵ�ˡ��͡��ջ�����ϵ�绰������ͬʱΪ�գ���������¼������һ�");
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
			showAlert("�޸ĳɹ�!","doFinish");
		}else{
			doFinish();
		}
		
	}
	
	function emptyForm() {
	    $("#lrbz").text("������Ϣ��������");
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
		var labelNameArray = ["ʼ����","Ŀ�ĵ�",
		                      "�ͻ�����","������ַ","��������ϵ��","��������ϵ�绰",
		                      "�ջ���λ","�ջ���ַ","�ջ�����ϵ��","�ջ�����ϵ�绰",
		                      "��������","��������",
		                      "������","�ָ�",
		                      "������","�����","�ص���","�½�","�ؿ�",
		                      "��������","��װ","����","����_������λ","����",
		                      "����_������λ","���","���_������λ"];
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
			showAlert("�ص����¼�����󣬻ص����ֻ�ܰ������ֺ���ĸ������ص������\",\"�ָ","focusSel");
			return false;
		}
		return true;
	}
	
	//������ҵ�Ļ���������λ��ʼ�� ����ļ�����λ
	function initJldw() {
		var xh = $("#mainForm_domain_hwmxDomain_xh").val();
		if (xh == "") {
			$("#mainForm_domain_hwmxDomain_hwBzHldwDm").val($("#mainForm_domain_hwmxDomain_qyHwBzJldwDm").val());
			$("#mainForm_domain_hwmxDomain_hwSlJldwDm").val($("#mainForm_domain_hwmxDomain_qyHwSlJldwDm").val());
			$("#mainForm_domain_hwmxDomain_hwZlJldwDm").val($("#mainForm_domain_hwmxDomain_qyHwZlJldwDm").val());
			$("#mainForm_domain_hwmxDomain_hwTjJldwDm").val($("#mainForm_domain_hwmxDomain_qyHwTjJldwDm").val());
		}
	}
	
	//��ʼ�����������λ�������
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
		<legend id="lrbz">������Ϣ��������</legend>
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
  				<td align="right" >������λ��</td>
  				<td colspan="2">
  					    <s:hidden name="domain.fhrDjxh2"></s:hidden>
  						<s:textfield name="domain.fhrMc2" cssClass="pop_input noborder inputext bgstyle_readonly" ></s:textfield>
  				</td>
  				<td align="right">�����ˣ�</td>
  				<td>
  					<div class="inputsel" style="width: 100%; ">
  						<s:textfield name="domain.fhrLxr" cssClass="pop_input noborder inputext bgstyle_readonly" ></s:textfield>
					</div>
			  		<div class="inputsc">
		              <div id="inputSel_fhrLxr" class="inputselcont inputselFixedSize ac_results">
		              </div>
		            </div>
  				</td>
  				<td align="right">��ϵ�绰��</td>
  				<td>
  					<s:textfield name="domain.fhrLxdh" cssClass="pop_input noborder bgstyle_readonly" />
  				</td>
  				
  				<td align="right" colspan="2"></td>
  			</tr>
  			<tr>
  				<td align="right">������ַ��</td>
  				<td colspan="2">
  					<s:textfield name="domain.fhrDz" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
  				</td>
  				<td align="right">ʼ���أ�</td>
  				<td>
  					<s:hidden name="domain.fhrXzqhDm" />
  					<div class="inputsel" style="width: 100%;">
  						<s:textfield name="domain.fhrXzqhMc" cssClass="pop_input noborder inputext bgstyle_readonly" ></s:textfield>
		            </div>
  				</td>
  				<td align="right">�Ƿ������</td>
  				<td>
  					<s:radio name="thflDm" list="#{'1':' ��','':' ��' }" theme="simple" ></s:radio>
  				</td>
  				<td align="right">�������ڣ�</td>
  				<td>
  					<input type="text" name="domain.yqFhrq" id="mainForm_domain_yqFhrq" value="<s:date name="domain.yqFhrq" format="yyyy-MM-dd" />" disabled="disabled"/>
  				</td>
  			</tr>
  			<tr>
  				<td align="right">�ջ���λ��</td>
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
  				<td align="right">�ջ��ˣ�</td>
  				<td>
  					<s:textfield name="domain.shrLxr" cssClass="pop_input noborder bgstyle_readonly" />
  				</td>
  				<td align="right">��ϵ�绰��</td>
  				<td>
  					<s:textfield name="domain.shrLxdh" cssClass="pop_input noborder bgstyle_readonly" />
  				</td>
  				<td colspan="2"></td>
  			</tr>
  			<tr>
  				<td align="right">�ջ���ַ��</td>
  				<td colspan="2">
  					<s:textfield name="domain.shrDz" cssClass="pop_input noborder bgstyle_readonly" />
  				</td>
  				<td align="right" >Ŀ�ĵأ�</td>
  				<td>
  					<s:hidden name="domain.shrXzqhDm"></s:hidden>
  					<div class="inputsel" style="width: 100%">
  						<s:textfield name="domain.shrXzqhMc" cssClass="inputext pop_input noborder bgstyle_readonly" ></s:textfield>
		            </div>
  				</td>
  				<td align="right">�ͻ���ʽ��</td>
  				<td >
  					<s:select list="#{'':' ---��ѡ��---','1':' ����','2':' �ͻ�' }" id="shfsDm" cssClass="select" disabled="true"></s:select>
  				</td>
  				
  				<td align="right">�������ڣ�</td>
  				<td>
  					<input type="text" name="domain.yqDdrq" id="mainForm_domain_yqDdrq" value="<s:date name="domain.yqDdrq" format="yyyy-MM-dd" />"  disabled="disabled"/>
  				</td>
  			</tr>
  			<tr>
      				<td align="right">�������ƣ�</td>
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
      				<td align="right">�ص��ţ�</td>
      				<td >
      					<s:textfield name="domain.hwmxDomain.hdbh" cssClass="pop_input noborder bgstyle_readonly" />
      				</td>
      				<td colspan="3" style="display: none;">
      					<div class="font_red" >(ע������ص�����ö��ŷָ�)</div>
      				</td>
      				<td align="right">���㣺</td>
      				<td >
      				    <s:select list="#{'':' ---��ѡ��---','01':' ������','02':' ������','03':' �����' }" id="jsJldwFlDm" cssClass="select" disabled="true"></s:select>
      				</td>
      				<td align="right">��װ��</td>
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
      				<td align="right">������</td>
      				<td>
      					<s:textfield name="domain.hwmxDomain.hwSl" cssClass="pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td>
      					<sys:QySlJldw myName="domain.hwmxDomain.hwSlJldwDm" myId="mainForm_domain_hwmxDomain_hwSlJldwDm" contaisQxz="false" myClass="select noTabSelect">
      					</sys:QySlJldw>
      				</td>      			
      				<td align="right">������</td>
      				<td>
      					<s:textfield name="domain.hwmxDomain.hwZl" cssClass="pop_input inputright noborder bgstyle_readonly hwflObserver" ></s:textfield>
      				</td>
      				<td>
      					<sys:QyZlJldw myName="domain.hwmxDomain.hwZlJldwDm" myId="mainForm_domain_hwmxDomain_hwZlJldwDm" contaisQxz="false" myClass="select noTabSelect">      						
      					</sys:QyZlJldw>
      				</td>
      				<td align="right">�����</td>
      				<td>
      					<s:textfield name="domain.hwmxDomain.hwTj" cssClass="pop_input inputright noborder bgstyle_readonly hwflObserver" ></s:textfield>
      				</td>
      				<td>
      					<sys:QyTjJldw myName="domain.hwmxDomain.hwTjJldwDm" myId="mainForm_domain_hwmxDomain_hwTjJldwDm" contaisQxz="false" myClass="select noTabSelect">      					
      					</sys:QyTjJldw>
      				</td>
      				
      				<td align="right">���ͣ�</td>
      				<td>
      					<s:radio id="hwflDm" name="hwflDm" list="#{'1':' �ػ�','2':' �ݻ�' }" theme="simple" disabled="true"></s:radio>
      				</td>
      				<td align="right">���ջ��</td>
      				<td>
      					<s:textfield name="domain.hwmxDomain.fyDshk" cssClass="pop_input inputright noborder bgstyle_readonly" ></s:textfield>
      				</td>
      		   </tr>
      		   <tr>
  				<td align="right">�����:</td>
  				<td colspan="2">
  					<s:textfield name="domain.hwmxDomain.srYsf" cssClass="pop_input inputright noborder bgstyle_readonly srHjJs" ></s:textfield>
  				</td>
  				<td align="right">���ͷ�:</td>
  				<td colspan="2">
  					<s:textfield name="domain.hwmxDomain.srPsf" cssClass="pop_input inputright noborder bgstyle_readonly srHjJs" ></s:textfield>
  				</td>
  				<td  class="jsfsChanged" align="right">�ؿۣ�</td>
  				<td class="jsfsChanged" colspan="2">
  					<s:textfield name="domain.srHk" cssClass="pop_input inputright noborder bgstyle_readonly srHjJs " ></s:textfield>
  				</td>
  				<td align="right">���۷�:</td>
  				<td >
  					<s:textfield name="domain.hwmxDomain.srBjf" cssClass="pop_input inputright noborder bgstyle_readonly srHjJs" ></s:textfield>
  				</td>
  				<td align="right"  class="jsfsChanged">�����룺</td>
  				<td class="jsfsChanged" >
  					<s:textfield name="domain.srHj" cssClass="pop_input inputright noborder bgstyle_readonly  jsfsChanged" ></s:textfield>
  				</td>
  			</tr>
  			<tr>
  				
  				<td align="right" >�ָ���</td>
  				<td colspan="2">
  					<s:textfield name="domain.srXf" cssClass="pop_input inputright noborder bgstyle_readonly " ></s:textfield>
  				</td>
  				<td align="right" class="df">������</td>
  				<td class="df" colspan="2">
  					<s:textfield name="domain.srHdf" cssClass="pop_input inputright noborder bgstyle_readonly " ></s:textfield>
  				</td>
  				<td align="right" class="tf">������</td>
  				<td class="tf" colspan="2" >
  					<s:textfield name="domain.srThf" cssClass="pop_input inputright noborder bgstyle_readonly " ></s:textfield>
  				</td>
  				<td align="right" class="jsfsChanged" >�½᣺</td>
  				<td  class="jsfsChanged" colspan="2">
  					<s:textfield name="domain.srYj" cssClass="pop_input inputright noborder bgstyle_readonly  jsfsChanged" ></s:textfield>
  				</td>
  				<td align="right" class="jsfsChanged" style="display: none;">�ظ���</td>
  				<td  class="jsfsChanged" style="display: none;">
  					<s:textfield name="domain.srHf" cssClass="pop_input inputright noborder bgstyle_readonly  jsfsChanged" ></s:textfield>
  				</td>
  				<td align="right">������ֵ</td>
  				<td >
  					<s:textfield name="domain.hwmxDomain.fySmjz" cssClass="pop_input inputright noborder bgstyle_readonly bjjs" ></s:textfield>
  				</td>
  				<td align="right">���۱���</td>
  				<td >
  					<s:select list="#{'0.005':' ǧ��֮��','0.010':' ǧ��֮ʮ' }" id="bjbl" cssClass="select bjjs" disabled="true"></s:select>
  				</td>
  			</tr>
  			<tr >
  				<td align="right">��ע��</td>
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
