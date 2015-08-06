<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>����-�ɳ�-Э��Ǽ�</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		initHykhData(300, $("#mainForm_domain_ssJgbm").val(), $("#mainForm_domain_pcJgbm").val());
		
		$("#mainForm_domain_ssJgbm").change(function(){
			$("[name='domain.fhrMc']").unbind();
			initHykhData(300, $(this).val(), $("#mainForm_domain_pcJgbm").val());
		});
		
		$("#mainForm_domain_pcJgbm").change(function(){
			initRy();
			$("[name='domain.fhrMc']").unbind();
			initHykhData(300, $("#mainForm_domain_ssJgbm").val(), $(this).val());
		});
		
		//��ѯ��ť�¼�
		$("#queryBtn").click(function(){
			onRefresh();
		});
		
		//��������
		$("#plScSendBtn").click(function(){
			var wsDm="303003";//���õǼ�������
			plScSend(wsDm,"");
		});
		
		//��ʼ�����
		initDataGrid();
		initRy();
		var sjJgbm = $("#mainForm_domain_ssJgbm").val();
		bmInit(sjJgbm, '', "domain.pcJgbm", "mainForm_domain_pcJgbm", "Y", "Y");		
	});

	function initRy() {
		var sj = $("#mainForm_domain_pcJgbm").val();
		commonInit("BMYH", sj, '', "domain.pcrCzyDjxh", "mainForm_domain_pcrCzyDjxh", "Y", false);
	}
	
	function onXydj(pcDjxh) {
		var xtcs20016 = $("#mainForm_domain_xtcs20016").val();	
		if(xtcs20016=="N"){
			showAlert("����ҵ����ҪЭ��Ǽǣ�");
			return;
		}
		var url = jcontextPath+"/zyegl/pcxydj!initXydj.action?domain.pcDjxh="+pcDjxh+"&num="+Math.random();
    	window.showModalDialog(url,window,"dialogHeight:430px;dialogWidth:600px;center:yes;resizable:no;status:no;scroll:no;help:no;minimize:yes;")
    	//window.open(url);
    	onRefresh();
	}
	
	function onXydjHwmx(pcDjxh,wfhDjxh,xysl) {
		if (xysl/1 > 0) {
			editFlag = "Y";
		}else {
			editFlag = "N";
		}
		var url = jcontextPath+"/zyegl/pcxydj!initHwxxXydj.action?domain.hwmxDomain.pcDjxh="+pcDjxh
				+"&domain.hwmxDomain.wfhDjxh="+wfhDjxh+"&domain.hwmxDomain.editFlag="+editFlag+"&num="+Math.random();
    	window.showModalDialog(url,window,"dialogHeight:450px;dialogWidth:635px;center:yes;resizable:no;status:no;scroll:no;help:no;minimize:yes;")
    	//window.open(url);
    	onRefresh();
	}
	
	var pcDjxhGolbal;
	function onDelXydj(pcDjxh) {
		pcDjxhGolbal = pcDjxh;
		showConfirm("ȷ��Ҫ������","doDelXydj");
	}
	
	function doDelXydj() {
		 var jsonObj = {"domain.pcDjxh":pcDjxhGolbal};
		 var url = jcontextPath+"/zyegl/pcxydj!delete";   
         ajaxCommon(url,jsonObj , "doDelSuc");  
	}
	function onShowXybd(pcDjxh) {
		//alert(wfhDjxh);
		var url = jcontextPath+"/zyegl/pcxydj!initXybd.action?domain.pcDjxh="+pcDjxh;
    	window.showModalDialog(url,window,"dialogHeight:600px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:no;help:no;minimize:yes;")
    	onRefresh();
	}
    function doDelSuc(){     
        showAlert("�����ɹ���");
        onRefresh();
	}	
    
    function onViewMx(ddDjxh) {
    	//alert(ddDjxh);
    	var url = jcontextPath+"/jcgl/jctydgl!initMx.action?domain.ddDjxh="+ddDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    	//window.open(url);
    }
    
    function onViewPc(pcDjxh){
    	var url = jcontextPath+"/jcgl/jcpcxxgl!initMx.action?domain.pcDjxh="+pcDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    }
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var xtcs20206 = $("#mainForm_domain_xtcs20206").val();
		if ("Y" == xtcs20206) {
			$("#dataList").jqGrid('showCol',["wsspztMc"]);
			$("#dataList").jqGrid('showCol',["fsspCheck"]);
		}
		
		var zt = $("input[name='domain.zt']:checked").val();
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		pcJgbm = $("#mainForm_domain_pcJgbm").val();
		pcrCzyDjxh = $("#mainForm_domain_pcrCzyDjxh").val();
		pcrqq = $("#mainForm_domain_pcrqq").val();
		pcrqz = $("#mainForm_domain_pcrqz").val(); 
		if(pcrqq>pcrqz){
			showError("�ɳ���ʼ���ڲ��ܴ����ɳ���ֹ���ڣ�");
				return;
		}
		if (pcrqq == "" || pcrqz == "") {
			showAlert("�ɳ����ڲ���Ϊ�գ�");
			return;
		}
		fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		fhrMc = $("#mainForm_domain_fhrMc").val();
		pcdh = $("#mainForm_domain_pcdh").val();
		//����������
		var url = jcontextPath+"/zyegl/pcxydj!query";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.pcJgbm":pcJgbm, "domain.pcrCzyDjxh":encodeURI(pcrCzyDjxh),"domain.pcrqq":pcrqq,
	 			"domain.pcrqz":pcrqz, "domain.fhrMc":encodeURI(fhrMc),"domain.pcdh":encodeURI(pcdh),
	 			"domain.fhrDjxh":fhrDjxh,"domain.zt":zt}								//����Ĳ�����json��ʽ
		 }
		 ).trigger("reloadGrid");		//��ʾ����һҳ�������������ݷ����仯��ʱ�򣬷�ҳ����Ϣ����
	}
	
	<% 
	    //��ȡÿ���û���ÿҳ��ʾ����ֵ
		UserDomain userDomain=(UserDomain) request.getSession().getAttribute(WebConstants.SES_USER_INFO);
		String rowNum = "20";
		if (userDomain != null && userDomain.getRowNum() != null && !"".equals(userDomain.getRowNum().trim()))
			rowNum = userDomain.getRowNum();
	%>
	
	//jqGrid  ��ʼ�����
	function initDataGrid(){ 
		  $("#dataList").jqGrid({
		    url:"",
		    datatype: 'local',
		    mtype: 'POST',
		    rownumbers : false,					//�����
			width:pageWidth()-10,  
			height:pageTableHeight()-90,	
		    gridComplete: myGridComplete,		//����������¼�
		    shrinkToFit:false, 
		    colNames:['���','<input title="ѡ��/ȡ��ѡ��" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />',
		              '����','�ɳ��Ǽ����','�����������','�����־','����','����״̬����','�ɳ�����', 'ԭ/���˷�','���˷�', 'ԭ/��Ԥ��','��Ԥ��','�������','δ�����Ǽ����',
		              '�ͻ�����', 'ddDjxh','��������', 'ʼ����', 'Ŀ�ĵ�', 'ת�벿��',
		              'ԭ/������','�ֽ�������', '����', 
		              '����', '���','��װ', '������ַ', 'Ҫ�󷢻�����', '�ջ���','�ջ���ַ',
		               'Ҫ�󵽴�����','���', 'װ��', '��������', '�ҳ�����', 
			              '˾��','�ɳ���', '�ɳ�����', '�ɳ�����', '��������','״̬', 'Э���'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[
			  {name:'pageXh', index:'pageXh', sortable:false, width:'30', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pageXh' + rowId + '\'';
			    }
			  },
			  {name:'fsspCheck', index:'fsspCheck', hidden:true, width:'20', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'fsspCheck' + rowId + '\'';
			    }
			  },
			  {name:'hstoperationcol', index:'', sortable:false, width:'50', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'hstoperationcol' + rowId + '\'';
			    }
			  },
			  {name:'pcDjxh', index:'pcDjxh', hidden:true,width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcDjxh' + rowId + '\'';
			    }
			  },
			  {name:'wsSpxh', index:'wsSpxh',hidden:true, width:'80', align:'center'},
			  {name:'bgbz', index:'bgbz',hidden:true, width:'80', align:'center'},
			  {name:'wsspztMc', index:'wsspztMc', hidden:true,width:'45', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'wsspztMc' + rowId + '\'';
			    }
			  },
			  {name:'wsspztDm', index:'wsspztDm', hidden:true,width:'80', align:'center'},
			  {name:'pcdh', index:'pcdh', width:'65', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcdh' + rowId + '\'';
			    }
			  },
			  {name:'yfHj', index:'yfHj', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'yfHj' + rowId + '\'';
			    }
			  },
			  {name:'xyYfHj', index:'xyYfHj', hidden:true,width:'50', align:'center'},
			  {name:'yfYfyf', index:'yfYfyf', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'yfYfyf' + rowId + '\'';
			    }
			  },
			  {name:'xyYfYfyf', index:'xyYfYfyf', hidden:true,width:'40', align:'center'},
			  {name:'dingdan', index:'dingdan', width:'80', align:'center'},
			  {name:'wfhDjxh', index:'wfhDjxh',hidden:true, width:'', align:'center'},
			  {name:'fhrMc', index:'fhrMc', width:'120', align:'center'},
			  {name:'ddDjxh', index:'ddDjxh', hidden:true,width:'120', align:'center'},
			  {name:'hwMc', index:'hwMc', width:'100', align:'center'},
		      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'40', align:'center'},
		      {name:'shrXzqhMc', index:'shrXzqhMc', width:'40', align:'center'},
		      {name:'zrbmMc', index:'zrbmMc', width:'100', align:'center'},
		      {name:'jssl', index:'jssl', width:'80', align:'right'},
		      {name:'xyJsSl', index:'xyJsSl', hidden:true,width:'60', align:'right'},
		      {name:'sl', index:'sl', width:'50', hidden:true,align:'right'},  
		      {name:'zl', index:'zl', width:'50', align:'right'},
		      {name:'tj', index:'tj', width:'50', align:'right'},
		      {name:'hwbz', index:'hwbz', width:'50', align:'center'},
		      {name:'sfdMc', index:'sfdMc', width:'200', align:'center'},
		      {name:'yqFhrq', index:'yqFhrq', width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      {name:'shrMc', index:'shrMc', width:'100', align:'center'},
		      {name:'mddMc', index:'mddMc', width:'200', align:'center'},
		      {name:'yqDdrq', index:'yqDdrq', width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      
			  {name:'pcfsMc', index:'pcfsMc', width:'35', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcfsMc' + rowId + '\'';
			    }
			  },
			  {name:'zcfxMc', index:'zcfxMc', width:'40', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'zcfxMc' + rowId + '\'';
			    }
			  },
			  {name:'cyrClhm', index:'cyrClhm', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'cyrClhm' + rowId + '\'';
			    }
			  },
			  {name:'cyrGchm', index:'cyrGchm', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'cyrGchm' + rowId + '\'';
			    }
			  },
			  {name:'cyrSjxm', index:'cyrSjxm', width:'50', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'cyrSjxm' + rowId + '\'';
			    }
			  },
		      {name:'pcrMc', index:'pcrMc', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcrMc' + rowId + '\'';
			    }
			  },
			  {name:'pcrq', index:'pcrq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcrq' + rowId + '\'';
			    }
			  },
			  {name:'pcJgbmMc', index:'pcJgbmMc', width:'100', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcJgbmMc' + rowId + '\'';
			    }
			  },
			  {name:'ssJgbmMc', index:'ssJgbmMc', width:'100', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'ssJgbmMc' + rowId + '\'';
			    }
			  },
			  {name:'dzsl', index:'dzsl', sortable:false, width:'45', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'dzsl' + rowId + '\'';
				    }
				  },
				  {name:'xyh', index:'xyh', width:'65', align:'center', 
						cellattr: function(rowId, tv, rawObject, cm, rdata) {
						   return 'id=\'xyh' + rowId + '\'';
					    }
				  }
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: 10,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[10,20,50,100],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'DD_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
		    sortorder: 'DESC',				//Ĭ��������
		    viewrecords: true,					//�����Ƿ�Ҫ��ʾ�ܼ�¼��
		    gridview: false,					//Ĭ��false ����һ�����ݺ���ӵ�grid�У������Ϊtrue���ǽ������������ݶ�������ɺ�����ӵ�grid�У���treeGrid, subGrid, or afterInsertRow ������	
		    jsonReader: {     
	     	 	root: 	 "domain.dataList",   				// �����У�Ĭ��Ϊ��rows��
	     	 	page:	 "domain.page.curPage",					// ��ǰҳ
	     	 	total: 	 "domain.page.totalPages",				// ��ҳ��
	     	 	records: "domain.page.totalRecords", 			// �ܼ�¼��
	     	 	reccount: "domain.page.reccount",
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
		  
		 //add custom button to export the data to excel
		  /* $("#dataList").jqGrid('navButtonAdd','#pager',{
		       caption:"", 
		       onClickButton : function () { 
		       	   $("#mainForm").attr("action",jcontextPath+"/zyegl/pcxydj!download");
				   $("#mainForm").submit();
		       } 
		 }); */
		 
	}
	
	function myGridComplete() {
        var graduateIds = $("#dataList").jqGrid('getDataIDs');
        var xtcs20206 = $("#mainForm_domain_xtcs20206").val();
        for (var i = 0; i < graduateIds.length; i++) {
            var cl = graduateIds[i];
            var pcDjxh = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh"); 
            var pcdh = jQuery("#dataList").jqGrid('getCell', cl,"pcdh"); 
            var wsSpxh = jQuery("#dataList").jqGrid('getCell', cl,"wsSpxh");
            var xysl = jQuery("#dataList").jqGrid('getCell', cl,"dzsl");
            var dingdan = jQuery("#dataList").jqGrid('getCell', cl,"dingdan");
           // var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh"); 
            var ddbh = jQuery("#dataList").jqGrid('getCell', cl,"dingdan");
 	        var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh"); 
 	        var ddbhStr="<a href=\"javascript:onViewMx("+ddDjxh+")\"><font color=\"blue\">"+ddbh+"</font></a>";
 	        $("#dataList").jqGrid('setRowData', cl, { 'dingdan': ddbhStr });
 	        
            var link = "<a href=\"javascript:onXydj('"+pcDjxh+"')\"><font color=\"blue\">�Ǽ�</font></a>";
            var str = "<font color=\"red\">δ�Ǽ�</font>";
            if (xysl/1 > 0) {
            	str = "�ѵǼ�";
            	link = "<a href=\"javascript:onXydj('"+pcDjxh+"')\"><font color=\"blue\">�޸�</font></a>"
            			+"&nbsp;<a href=\"javascript:onDelXydj('"+pcDjxh+"')\"><font color=\"blue\">����</font></a>";
            			//+"&nbsp;<a href=\"javascript:onShowXybd('"+pcDjxh+"')\"><font color=\"blue\">�鿴</font></a>";
            }
           // var strDan="<a href=\"javascript:onViewMx("+1+")\"><font color=\"blue\">"+dingdan+"</font></a>";
            var strPc="<a href=\"javascript:onViewPc("+pcDjxh+")\"><font color=\"blue\">"+pcdh+"</font></a>";
            var hwMc = jQuery("#dataList").jqGrid('getCell', cl,"hwMc");
           	var wfhDjxh = jQuery("#dataList").jqGrid('getCell', cl,"wfhDjxh");
            var link4Hw = "<a href=\"javascript:onXydjHwmx('"+pcDjxh+"','"+wfhDjxh+"',"+xysl+")\"><font color=\"blue\">"+hwMc+"</font></a>";
            
            $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            $("#dataList").jqGrid('setRowData', cl, { 'dzsl': str });
            $("#dataList").jqGrid('setRowData', cl, { 'hwMc': link4Hw });
            //$("#dataList").jqGrid('setRowData', cl, { 'dingdan': strDan });
            $("#dataList").jqGrid('setRowData', cl, { 'pcdh': strPc });
            var bgbz = jQuery("#dataList").jqGrid('getCell', cl,"bgbz");
            if ("Y" == xtcs20206 ) {
				var wsspztDm = jQuery("#dataList").jqGrid('getCell', cl,"wsspztDm"); 
				var spLink = '<input type="checkbox" name="xhs" value="'+pcDjxh+'#'+wsSpxh+'" />';
				if ("1" == wsspztDm || "3" == wsspztDm || "Y" != bgbz) {
					spLink = '<input type="checkbox" name="xhs" value="'+pcDjxh+'#'+wsSpxh+'" disabled="disabled" />';
				}
				
				$("#dataList").jqGrid('setRowData', cl, { 'fsspCheck': spLink }); 
    		}
            
            var yfhj = jQuery("#dataList").jqGrid('getCell', cl,"yfHj");
	        var xyYfhj = jQuery("#dataList").jqGrid('getCell', cl,"xyYfHj");
	        var yfYfyf = jQuery("#dataList").jqGrid('getCell', cl,"yfYfyf");
	        var xyYfYfyf = jQuery("#dataList").jqGrid('getCell', cl,"xyYfYfyf");
	        var jssl = jQuery("#dataList").jqGrid('getCell', cl,"jssl");
	        var xyJssl = jQuery("#dataList").jqGrid('getCell', cl,"xyJsSl");
	        
	        var yfStr = yfhj + "/" + xyYfhj;
	        if (yfhj != xyYfhj) {
	        	yfStr = "<font color=\"red\">" + yfStr + "</font>";
	        }
	        var yfyfStr = yfYfyf + "/" + xyYfYfyf;
	        if (yfYfyf != xyYfYfyf) {
	        	yfyfStr = "<font color=\"red\">" + yfyfStr + "</font>";
	        }
	        var jsslStr = jssl + "/" + xyJssl;
	        if (jssl != xyJssl) {
	        	jsslStr = "<font color=\"red\">" + jsslStr + "</font>";
	        }
	        $("#dataList").jqGrid('setRowData', cl, { 'yfHj': yfStr });
	        $("#dataList").jqGrid('setRowData', cl, { 'yfYfyf': yfyfStr });
	        $("#dataList").jqGrid('setRowData', cl, { 'jssl': jsslStr });
        }
        
        var gridName = "dataList";
 	    var a = ['pageXh','hstoperationcol','dzsl','pcDjxh','pcdh','xyh','fsspCheck','wsspztMc','pcfsMc','zcfxMc','cyrClhm','cyrGchm','cyrSjxm',
 	            'yfHj','xyYfHj','yfYfyf','xyYfYfyf','pcrMc','pcrq','pcJgbmMc','ssJgbmMc'
 	            ];
  		
        Merger(gridName, 'pcDjxh', a);
 	}
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="pcxydj!query" namespace="/zyegl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.xtcs20206" />
	<s:hidden name="domain.xtcs20016" />
	<s:hidden name="jsonData"></s:hidden>
	
	
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">������</li>
		    <li><a href="#" id="queryBtn" class="licon04">�� ��</a></li>
	    <s:if test='domain.xtcs20206 == "Y"'>
		    <li><a href="#" id="plScSendBtn" class="licon10">������������</a></li>
	    </s:if>
		    <li><a href="#" id="closeBtn" class="licon03">�� ��</a></li>
	  	</ul>
	
	    <ul class="rcont">
		    <li class="ricon02" onclick="slideToggle('syquery')">��ʾ/���ز�ѯ����</li>
		    <li class="ricon03">����</li>
	  	</ul>
	</div> 
	<div class="right_cont" id="maincont">
		 <div id="divQuery">
		<fieldset>
			<legend>��ѯ����</legend>
		   <table width="99%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		        	<td width="8%" align="right">ҵ��λ��</td>
          			<td width="21%">
          				<sys:gsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y" myClass="select" onChange="bmInit(this.value, '', 'domain.pcJgbm', 'mainForm_domain_pcJgbm', 'Y', 'Y')" />
		  			</td>
		  			<td width="8%" align="right">�ɳ����ţ�</td>
          			<td width="21%">
          				<select id="mainForm_domain_pcJgbm" name="domain.pcJgbm" class="select" >
          					<option value="${domain.pcJgbm }" selected="selected"></option>
          				</select>
		  			</td>
	        	  <td width="8%" align="right">�ɳ��ˣ�</td>
		          <td width="25%">
		          		<select name="domain.pcrCzyDjxh" id="mainForm_domain_pcrCzyDjxh" class="select" />
	          	  </td>
		        </tr>
		        <tr>
	        	  <td align="right">�ͻ����ƣ�</td>
		          <td>
		          	<s:hidden name="domain.fhrDjxh"></s:hidden>
  					<div class="inputsel" style="width: 230px; ">
  						<s:textfield name="domain.fhrMc" cssClass="pop_input noborder inputext" cssStyle="width:200px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="fhr" onFocus="this.blur()"></a>
  					</div>
			  		<div class="inputsc">
		              <div id="inputSel_fhr" class="inputselcont inputselFixedSize ac_results">
		              </div>
		            </div>
		          </td>
		          <td align="right">�ɳ����ţ�</td>
		          <td><s:textfield name="domain.pcdh" cssClass="pop_input noborder" /></td>
		          <td align="right">�ɳ����ڣ�</td>
		          <td>
		          <sys:dateFirstDMonth myName="domain.pcrqq" myId="mainForm_domain_pcrqq" myClass="ymdate" />
	          			��
	          		<sys:dateCurrentDayTag myName="domain.pcrqz" myId="mainForm_domain_pcrqz" myClass="ymdate" />
		         
		          </td>
		        </tr>
		        <tr>
	        	  <td align="right">״̬��</td>
		          <td>
		          	<s:radio name="domain.zt" list='#{"":"����","1":"�ѵǼ�","2":"δ�Ǽ�"}' listKey="key" listValue="value"></s:radio>
		          </td>
		          <td align="right"></td>
		          <td></td>
		        </tr>
		   </table>
		</fieldset>
	  </div>
		<!-- ��ҳ��� id����ΪdataList -->
		<table id="dataList"><tr><td/></tr></table> 
		<!-- ��ҳ���� -->
		<div id="pager"></div>
		</div>
		
		<%@include file="/common/message.jsp" %>
</s:form>
</body>
</html>
