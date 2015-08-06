<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>����-����</title>

<style type="text/css">
html,body {background:none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>

<script type="text/javascript">
	$(function(){
		initDataGrid();
		onQueryPzHwxx();
		
		$("#mainForm_domain_clxhwhDjxh").change(function(){
			var url = jcontextPath + "/zygl/qyclxhwh!queryClxhByKey";
			var clxhwhDjxh = this.value;
			var jsonObj = {"domain.clxhwhDjxh":clxhwhDjxh};
			ajaxCommon(url,jsonObj,"doQueryClxhSuc");
		});
		
		$(":input:text[name='hwsls']").live("focus", function() {
			$(this).attr("title", this.value);
		}).live("change", function() {
			var pre = $(this).attr("title")/1;
			if (pre > 0) {
				var bl = this.value/1 / pre;
				var hwzl = $(":text[name='hwzls']",$(this).parents("tr"));
				var zlcy = hwzl.val()/1 * (1-bl);
				$("#mainForm_domain_pzCz").val(($("#mainForm_domain_pzCz").val()-zlcy).toFixed(2));
				hwzl.val((hwzl.val()/1 * bl).toFixed(2));
				var hwtj = $(":text[name='hwtjs']",$(this).parents("tr"));
				var tjcy = hwtj.val()/1 * (1-bl);
				$("#mainForm_domain_pzTj").val(($("#mainForm_domain_pzTj").val()-tjcy).toFixed(2));
				hwtj.val((hwtj.val()/1 * bl).toFixed(2));
				
				if (bl <= 1) {
					var graduateIds = $("#dataList").jqGrid('getDataIDs');
					var index = $(this).parents("tr").index()-1;
					var hj = jQuery("#dataList").jqGrid('getCell', graduateIds[index],"srHj");
					if (hj != "") {
						var hjcy = parseFloat(hj) * (1-bl);
					    $("#mainForm_domain_pzsr").val(($("#mainForm_domain_pzsr").val()-hjcy).toFixed(2));
					    $("#dataList").jqGrid('setRowData', graduateIds[index], { 'srHj': (parseFloat(hj)*bl).toFixed(2) });
					}
				}
			}
		});
		
		$(":input:text[name='hwzls']").live("change", function() {
			calCzTj();
		});
		
		$(":input:text[name='hwtjs']").live("change", function() {
			calCzTj();
		});
		
		$("#saveBtn").click(function(){
			var hwNum = $("#dataList tr").length;
			if (hwNum <= 1) {
				showAlert("����ѡ������ٱ��棡");
				return;
			}
			
			if(!checkdata()){
				return;
			}
			
			if (checkHwNum()) {
				showConfirm("�����ص��������ѳ������ɷ����������Ƿ������", "doSave");
			}else {
				doSave();
			}
		});
		
		$("#delHwBtn").click(function(){
			var hwXh4PcDel = $(":checked[name='hwXh4PcDel']");
			if (hwXh4PcDel.length <= 0) {
				showAlert("��ѡ����Ҫɾ���Ļ��");
				return;
			}
			showConfirm("ɾ���󽫲��ɻָ���ȷ��Ҫɾ���û�����Ϣ��", "doDeletePzHwxx");
		});
		
		$("#pcBtn").click(function(){
			var hwNum = $("#dataList tr").length;
			if (hwNum <= 1) {
				showAlert("����ѡ��������ɳ���");
				return;
			}
			
			var hzJgbm = trim($("#mainForm_domain_hzJgbm").val()); 
			if (hzJgbm == "") {
				showAlert("��վ����Ϊ�գ�����ѡ��һ����վ��");
				return;
			}
			
			var wfhDjxhs = $(":input[name='wfhDjxhs']");
			var hwSls = $(":input:text[name='hwsls']");
			var hwZls = $(":input:text[name='hwzls']");
			var hwTjs = $(":input:text[name='hwtjs']");
			
			var jsonStr = getJqueryParam(wfhDjxhs, "domain.wfhDjxhs") + 
							getJqueryParam(hwSls, "domain.hwSls") + getJqueryParam(hwZls, "domain.hwZls") +
							getJqueryParam(hwTjs, "domain.hwTjs");
			
			var url = jcontextPath+"/hygl/ddpzxxgl!initPcxxFromPz";  
	    	var jsonObj = {"domain.hzJgbm":hzJgbm};  
	    	jsonStr += jQuery.param(jsonObj);
	    	showMessage();
			ajaxCommon(url,encodeURI(jsonStr),"doInitPcSuc",false);
		});
		
		$("#viewBtn").click(function(){
			var hwNum = $("#dataList tr").length;
			if (hwNum <= 1) {
				showAlert("����ѡ����");
				return;
			}
			
			if(!checkdata()){
				return;
			}
			doView();
			if (checkHwNum()) {
				showConfirm("�����ص��������ѳ������ɷ����������Ƿ������", "");
			}
		});
		
	});
	
	function doView() {
		var pzDjxh = $("#mainForm_domain_pzDjxh").val();
		var pchwLsxh = $("#mainForm_domain_pchwLsxh").val();
		 
		var hzJgbm = trim($("#mainForm_domain_hzJgbm").val());
		var clxhwhDjxh = trim($("#mainForm_domain_clxhwhDjxh").val()); 
		var pzCz = trim($("#mainForm_domain_pzCz").val()); 
		var pzTj = trim($("#mainForm_domain_pzTj").val()); 
		var pzsr = trim($("#mainForm_domain_pzsr").val()); 

		var url = jcontextPath+"/hygl/ddpzxxgl!viewPzXx?domain.hzJgbm="+hzJgbm+"&domain.clxhwhDjxh="+clxhwhDjxh+"&domain.pzCz="+pzCz
								+"&domain.pzTj="+pzTj+"&domain.pzsr="+pzsr+"&domain.pzDjxh="+pzDjxh+"&domain.pchwLsxh="+pchwLsxh;  
    	window.showModalDialog(url,window,"dialogHeight:540px;dialogWidth:690px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
	}
	
	function calCzTj() {
		var zls = $(":text[name='hwzls']");
		var tjs = $(":text[name='hwtjs']");
		
		var zlHj = 0.00;
		var tjHj = 0.00;
		
		$.each(zls, function(i, obj){
			if (obj.value != "") {
				zlHj += parseFloat(obj.value);
			}
			
			if (tjs.length > i && tjs[i].value != "") {
				tjHj += parseFloat(tjs[i].value);
			}
		});
		
		$("#mainForm_domain_pzCz").val(zlHj.toFixed(2));
		$("#mainForm_domain_pzTj").val(tjHj.toFixed(2));
	}
	
	function doSave() {
		var hzJgbm = trim($("#mainForm_domain_hzJgbm").val()); 
		var clxhwhDjxh = trim($("#mainForm_domain_clxhwhDjxh").val()); 
		var clCz = trim($("#mainForm_domain_clCz").val()); 
		var clTj = trim($("#mainForm_domain_clTj").val()); 
		var clCd = trim($("#mainForm_domain_clCd").val()); 
		var clKd = trim($("#mainForm_domain_clKd").val()); 
		var clGd = trim($("#mainForm_domain_clGd").val()); 
		var pzCz = trim($("#mainForm_domain_pzCz").val()); 
		var pzTj = trim($("#mainForm_domain_pzTj").val()); 
		var pzCd = trim($("#mainForm_domain_pzCd").val()); 
		var pzKd = trim($("#mainForm_domain_pzKd").val()); 
		var pzGd = trim($("#mainForm_domain_pzGd").val()); 
		var pzsr = trim($("#mainForm_domain_pzsr").val()); 
		var pzDjxh = trim($("#mainForm_domain_pzDjxh").val()); 
		var pchwLsxh = $("#mainForm_domain_pchwLsxh").val();
		
		var wfhDjxhs = $(":input[name='wfhDjxhs']");
		var hwSls = $(":input:text[name='hwsls']");
		var hwZls = $(":input:text[name='hwzls']");
		var hwTjs = $(":input:text[name='hwtjs']");
		
		var jsonStr = getJqueryParam(wfhDjxhs, "domain.wfhDjxhs") + 
						getJqueryParam(hwSls, "domain.hwSls") + getJqueryParam(hwZls, "domain.hwZls") +
						getJqueryParam(hwTjs, "domain.hwTjs");
		
		var url = jcontextPath+"/hygl/ddpzxxgl!save";  
    	var jsonObj = {"domain.hzJgbm":hzJgbm,"domain.clxhwhDjxh":clxhwhDjxh,"domain.clCz":clCz,
                       "domain.clTj":clTj,"domain.clCd":clCd,"domain.clKd":clKd,"domain.clGd":clGd,"domain.pzCz":pzCz,
                       "domain.pzTj":pzTj,"domain.pzCd":pzCd,"domain.pzKd":pzKd,"domain.pzGd":pzGd,"domain.pzsr":pzsr,
                       "domain.pzDjxh":pzDjxh};  
    	jsonStr += jQuery.param(jsonObj);
    	showMessage();
		ajaxCommon(url,encodeURI(jsonStr),"doSaveSuc",false);
	}
	
	function doSaveSuc(data) {
		hideMessage();
		showAlert("����ɹ���", "yesSaveCallBack");
	}
	
	function yesSaveCallBack() {
		var pzDjxh = $("#mainForm_domain_pzDjxh").val();
		if (pzDjxh == "") {
			$("#mainForm").submit();
		}else {
			$("#closeBtn").click();
		}
	}
	
	function checkHwNum() {
		var clFlag = false;
		var hwSls = $(":input:text[name='hwsls']");
		
		var graduateIds = $("#dataList").jqGrid('getDataIDs');
	    
		$.each(hwSls, function(i, obj){
			if (obj.value/1 > jQuery("#dataList").jqGrid('getCell', graduateIds[i],"hwSl")/1) {	
				clFlag = true;
				return false;
			}
		});
		
		return clFlag;
	}
	
	function saveWfhxx4Pz(wfhXhs) {
		var pchwLsxh = $("#mainForm_domain_pchwLsxh").val();
		var pzDjxh = $("#mainForm_domain_pzDjxh").val();
		
		var wfhDjxhs = $(":input[name='wfhDjxhs']");
		var tempBz = $(":input[name='tempBz']");
		var hwSls = $(":input:text[name='hwsls']");
		var hwZls = $(":input:text[name='hwzls']");
		var hwTjs = $(":input:text[name='hwtjs']");
		
		var jsonStr = getJqueryParam(wfhDjxhs, "domain.wfhDjxhs") + getJqueryParam(tempBz, "domain.tempBz") +
						getJqueryParam(hwSls, "domain.hwSls") + getJqueryParam(hwZls, "domain.hwZls") +
						getJqueryParam(hwTjs, "domain.hwTjs");
		
		var url = jcontextPath+"/hygl/ddpzxxgl!saveWfhxx4Pz";
		var jsonObj = {"domain.wfhXhs":wfhXhs,"domain.pzDjxh":pzDjxh,"domain.pchwLsxh":pchwLsxh}
		
		jsonStr += jQuery.param(jsonObj);
    	showMessage();
		ajaxCommon(url,encodeURI(jsonStr),"saveWfhxx4PzSuc",false);
	}
	
	function saveWfhxx4PzSuc(data) {
		hideMessage();
		var pchwLsxh = data.domain.pchwLsxh;
		if ($("#mainForm_domain_pchwLsxh").val() == "") {
			$("#mainForm_domain_pchwLsxh").val(pchwLsxh)
		}
		onRefresh();
		onQueryPzHwxx();
	}
	
	function doInitPcSuc(data) {
		var pchwLsxh = data.domain.pchwLsxh;
		var url = jcontextPath + "/hygl/hypcxxgl!initMx.action?domain.pcfsDm=2&domain.pchwLsxh="+pchwLsxh;
		navigateMenu(url,'�����ɳ�','true');
	}
	
	function doDeletePzHwxx() {
		var pzDjxh = $("#mainForm_domain_pzDjxh").val();
		var pchwLsxh = $("#mainForm_domain_pchwLsxh").val();
		
		var tempBz = new Array()
		var hwXh4PcDel = $(":checked[name='hwXh4PcDel']");
		$.each(hwXh4PcDel, function(i, hwxh){
			var temp = $(":input[name='tempBz']",$(hwxh).parents("tr"));
			tempBz.push(temp[0]);
		});
		
		var wfhDjxhs = $(":input[name='wfhDjxhs']");
		var tempBz = $(":input[name='tempBz']");
		var hwSls = $(":input:text[name='hwsls']");
		var hwZls = $(":input:text[name='hwzls']");
		var hwTjs = $(":input:text[name='hwtjs']");
		
		var jsonStr = getJqueryParam(hwXh4PcDel, "domain.hwXh4PcDel") + getJqueryParam(tempBz, "domain.tempBz") +
						getJqueryParam(wfhDjxhs, "domain.wfhDjxhs") + getJqueryParam(hwSls, "domain.hwSls") + 
						getJqueryParam(hwZls, "domain.hwZls") + getJqueryParam(hwTjs, "domain.hwTjs");
		var jsonObj = {"domain.pzDjxh":pzDjxh,"domain.pchwLsxh":pchwLsxh};
		jsonStr += jQuery.param(jsonObj);
		
		var url = jcontextPath+"/hygl/ddpzxxgl!deleteWfhxx4Pz";
		showMessage();
		ajaxCommon(url,encodeURI(jsonStr),"deletePzHwxxSuc", false);
	}
	
	function deletePzHwxxSuc(data) {
		hideMessage();
		showAlert("ɾ���ɹ���");
		onRefresh();
		onQueryPzHwxx();
	}
	
	function doQueryClxhSuc(data) {
		var domain = data.domain;
		$("#mainForm_domain_clCz").val(domain.cz == null ? "" : domain.cz);
		$("#mainForm_domain_clTj").val(domain.tj == null ? "" : domain.tj);
		$("#mainForm_domain_clCd").val(domain.cd == null ? "" : domain.cd);
		$("#mainForm_domain_clKd").val(domain.kd == null ? "" : domain.kd);
		$("#mainForm_domain_clGd").val(domain.gd == null ? "" : domain.gd);
	}
	
	function checkdata(){
		var controlNameArray = ["domain.hzJgbm","domain.clxhwhDjxh","domain.clCz",
		                        "domain.clTj","domain.clCd","domain.clKd","domain.clGd","domain.pzCz",
		                        "domain.pzTj","domain.pzCd","domain.pzKd","domain.pzGd","domain.pzsr"];
		var labelNameArray = ["��վ","�����ͺ�","����_����",
		                      "����_���","����_����","����_���","����_�߶�","����_����",
		                      "����_���","����_����","����_���","����_�߶�","��������"];
		var compareValueArray = [16,16,16.2,
			                     16.2,16.2,16.2,16.2,16.2,
			                     16.2,16.2,16.2,16.2,16.2];
		var nodeTypeArray = [NodeType.INTEGER,NodeType.INTEGER,NodeType.DECIMAL,
			                 NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,
			                 NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL];
		var notNullArray = [true,true,false,
                            false,false,false,false,false,
                            false,false,false,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onQueryPzHwxx(){
		 var pzDjxh = $("#mainForm_domain_pzDjxh").val();
		 var pchwLsxh = $("#mainForm_domain_pchwLsxh").val();
		 
		//����������
		var url = jcontextPath+"/hygl/ddpzxxgl!queryPzHwxx.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.pzDjxh":pzDjxh,"domain.pchwLsxh":pchwLsxh}
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
		width:pageWidth()-10,  
		height:pageTableHeight()-90,	
	    gridComplete: myGridComplete,		//����������¼�
	    shrinkToFit:false, 
	    colNames:['�����Ǽ����','�������','������','<input title="ѡ��/ȡ��ѡ��" type="checkbox" onclick="checkAllJGridChk(event,this,\'hwXh4PcDel\');" />',
	            '��������','ʼ����','Ŀ�ĵ�','δ�������input','δ�������',
	            '��װ','���','����','����','���','����','����','���','����','����','�ͻ�',
	    		'�ص���','�ջ���','�ջ���ַ','��������','��������',
	    		'������ַ','�Ǽ���','�Ǽ�����','�Ǽǲ���','��������'],			 //name ����ʾ������
	     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
	    colModel :[
		  {name:'ddDjxh', index:'ddDjxh', hidden:true, width:'70', align:'center'},
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
	      {name:'sl', index:'sl', width:'70', sortable:false, align:'right'},
	      {name:'zl', index:'zl', width:'70', sortable:false, align:'right'}, 
	      {name:'tj', index:'tj', width:'70', sortable:false, align:'right'},
	      {name:'hwSl', index:'hwSl', width:'30', hidden:true, sortable:false, align:'right'},
	      {name:'hwZl', index:'hwZl', width:'30', hidden:true, sortable:false, align:'right'}, 
	      {name:'hwTj', index:'hwTj', width:'30', hidden:true, sortable:false, align:'right'},
	      {name:'srHj', index:'srHj', width:'45', sortable:false, align:'center'},
	      {name:'df', index:'df', width:'45', sortable:false, align:'center'},
	      {name:'pchwClfsDm', index:'pchwClfsDm', width:'30', sortable:false, align:'center'},

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
	 	 	root: 	 "domain.pzHwxxList",   				// �����У�Ĭ��Ϊ��rows��
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
	        var pchwClfsDm = jQuery("#dataList").jqGrid('getCell', cl,"pchwClfsDm");
	        
	        var link = "<input type=\"checkbox\" name=\"hwXh4PcDel\" value=\""+wfhDjxh+"\" />";
	        $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
	        var shStr = "";
	        if (pchwClfsDm == "21" || pchwClfsDm == "31" ) {
	        	shStr = "ֱ��";
	        }
	        $("#dataList").jqGrid('setRowData', cl, { 'pchwClfsDm': shStr });
	        
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
	    $("#mainForm_domain_pzCz").val(zls.toFixed(2));
	    $("#mainForm_domain_pzTj").val(tjs.toFixed(2));
	    $("#mainForm_domain_pzsr").val(srHjs.toFixed(2));
	    
	   var gridName = "dataList";
		   var a = ['ddbh','fhrMc','djRq','djrMc','djJgmc','ssJgmc'];
			
	    Merger(gridName, 'ddDjxh', a);
	}
/**************************************��ҳ����****************************************/
</script>
</head>

<body>
<%try{ %>
<s:form action="ddpzxxgl!initMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.pzDjxh" />
	<s:hidden name="domain.pchwLsxh" />
	
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">������</li>
		    <li><a href="#" id="queryBtn" class="licon04">�� ��</a></li>
		    <li><a href="#" id="pcBtn" class="licon01">�� ��</a></li>
		    <li><a href="#" id="closeBtn" class="licon03">�� ��</a></li>
	  	</ul>
	</div> 
	
	<jsp:include page="/work/hygl/hy_tyd_wfhxx_ddpz.jsp" />
	<div id="maincont" style="display: none;"></div>
	<div class="pop_contc">
		<table width="98%" border="0" align="center" cellpadding="0" cellspacing="0">
	      <tr>
	        <td align="left" class="bold_font" width="15%">��ѡ������Ϣ��</td>
	        <td width="8%" align="right"></td>
			<td width="14%">
			</td>
	        <td align="left" width="63%">
	        	&nbsp;&nbsp;<button type="button" class="pop_btnbg" id="yesBtn">��ӻ���</button>&nbsp;
				<button type="button" class="pop_btnbg" id="delHwBtn">ɾ������</button>
	        </td>
	      </tr>
	    </table>
		<table id="dataList"><tr><td/></tr></table>
		<fieldset>
			<legend>������Ϣ</legend>
		<table width="99%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
			<tr>
				<td align="right" width="9%">��վ��</td>
   				<td colspan="2">
   					<sys:fgsList myName="domain.hzJgbm" myId="mainForm_domain_hzJgbm" contaisDq="N" contaisQxz="true" myClass="select"></sys:fgsList>
   				</td>
			</tr>
			<tr>
   				<td align="right" width="9%">�����ͺţ�</td>
   				<td width="10%">
   					<sys:QyClxhwh myId="mainForm_domain_clxhwhDjxh" myName="domain.clxhwhDjxh" myClass="select" contaisQxz="true"></sys:QyClxhwh>
   				</td>
   				<td align="right" width="8%">���أ�</td>
   				<td width="9%">
   					<s:textfield name="domain.clCz" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
   				</td>
   				<td align="right" width="8%">�����</td>
   				<td width="8%">
   					<s:textfield name="domain.clTj" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
   				</td>
   				<td align="right" width="8%">���ȣ�</td>
   				<td width="8%">
   					<s:textfield name="domain.clCd" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
   				</td>
   				<td align="right" width="8%">��ȣ�</td>
   				<td width="8%">
   					<s:textfield name="domain.clKd" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
   				</td>
   				<td align="right" width="8%">�߶ȣ�</td>
   				<td width="8%">
   					<s:textfield name="domain.clGd" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
   				</td>
   			</tr>
   			<tr>
   				<td align="right">�������룺</td>
   				<td>
   					<s:textfield name="domain.pzsr" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
   				</td>
   				<td align="right">���أ�</td>
   				<td>
   					<s:textfield name="domain.pzCz" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
   				</td>
   				<td align="right">�����</td>
   				<td>
   					<s:textfield name="domain.pzTj" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
   				</td>
   				<td align="right">���ȣ�</td>
   				<td>
   					<s:textfield name="domain.pzCd" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
   				</td>
   				<td align="right">��ȣ�</td>
   				<td>
   					<s:textfield name="domain.pzKd" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
   				</td>
   				<td align="right">�߶ȣ�</td>
   				<td>
   					<s:textfield name="domain.pzGd" cssClass="pop_input inputright noborder bgstyle_optional" ></s:textfield>
   				</td>
   			</tr>
		</table>
		</fieldset>
		<div class="pop_btn">
		 	<button type="button" class="pop_btnbg" id="saveBtn">�� ��</button>
		 	&nbsp;
		    <button type="button" class="pop_btnbg" id="viewBtn">Ԥ ��</button>
	    </div>
	</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
