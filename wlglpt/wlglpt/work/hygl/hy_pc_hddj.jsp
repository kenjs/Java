<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>����-�ɳ�-�ص�</title>
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
				var wsDm="303004";//���õǼ�������
				plScSend(wsDm,"");
			});
			
			//��������
			$("#plJsBtn").click(function(){
				var pljsxhs = $(":checked[name='pljs']");
	  			if (pljsxhs.length <= 0) {
					showAlert("��ѡ����Ҫ�������յļ�¼��");
					return;
	  			}
				var pljsStr = '';
				var pljs = $('[name=pljs]:checkbox');
				pljs.each(function(){
					if(this.checked){
						pljsStr += this.value + '|';
					}
				});
				//alert(pljsStr);
				var jsonObj = {"domain.pljsStr":pljsStr};
		 		var url = jcontextPath+"/hygl/hypchddj!plJs";   
         		ajaxCommon(url,jsonObj,"doPljsSu");   
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
	
	function onHddj(pcDjxh, wfhDjxh,hdDjxh,xybz) {
		var xtcs20016 = $("#mainForm_domain_xtcs20016").val();
		if(xtcs20016=='Y'){
			if(xybz=='N'){
				showAlert("����Э��Ǽǣ�");
				return;
			}
		}
		var url = jcontextPath+"/hygl/hypchddj!initMx.action?domain.pcDjxh="+pcDjxh+"&domain.wfhDjxh="+wfhDjxh
								+"&domain.hdDjxh="+hdDjxh+"&num="+Math.random();
    	window.showModalDialog(url,window,"dialogHeight:450px;dialogWidth:555px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
    	//window.open(url);
    	onRefresh();
	}
	
	var hdDjxhGolbal;
	var pc;
	var wfh;
	function onDelHddj(hdDjxh,pcDjxh,wfhDjxh) {
		hdDjxhGolbal = hdDjxh;
		 pc=pcDjxh;
		wfh=wfhDjxh;
		showConfirm("ȷ��Ҫ������","doDelHddj");
	}
	
	function doDelHddj() {
		 var jsonObj = {"domain.hdDjxh":hdDjxhGolbal,"domain.pcDjxh":pc,"domain.wfhDjxh":wfh};
		 var url = jcontextPath+"/hygl/hypchddj!delete";   
         ajaxCommon(url,jsonObj , "doDelSuc");  
	}
	
    function doDelSuc(){     
        showAlert("�����ɹ���");
        onRefresh();
	}	
    
    function doPljsSu() {
    	showAlert("�������ճɹ���");
        onRefresh();
    }

	function onViewMx(ddDjxh) {
		var url = jcontextPath+"/jcgl/jctydgl!initMx.action?domain.ddDjxh="+ddDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    	//window.open(url);
	}
    
	 function onViewPc(pcDjxh){
    	var url = jcontextPath+"/jcgl/jcpcxxgl!initMx.action?domain.pcDjxh="+pcDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
     }
	 
	 function onViewHddj(hdDjxh) {
		 var url = jcontextPath+"/hygl/hypchddj!initViewMx.action?domain.hdDjxh="+hdDjxh;
		 window.showModalDialog(url,window,"dialogHeight:300px;dialogWidth:555px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
	 }
	
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var xtcsSfsp = $("#mainForm_domain_xtcsSfsp").val();
		if ("Y" == xtcsSfsp) {
			$("#dataList").jqGrid('showCol',["wsspztMc"]);
			$("#dataList").jqGrid('showCol',["fsspCheck"]);
		}
		
		var zt = $("input[name='domain.zt']:checked").val();
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var hwmc=$("#mainForm_domain_hwmc").val();
		//pcJgbm = $("#mainForm_domain_pcJgbm").val();
		//pcrCzyDjxh = $("#mainForm_domain_pcrCzyDjxh").val();
		pcrqq = $("#mainForm_domain_pcrqq").val();
		pcrqz = $("#mainForm_domain_pcrqz").val(); 
		if (pcrqq == "" || pcrqz == "") {
			showAlert("�ɳ����ڲ���Ϊ�գ�");
			return;
		}
		fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		fhrMc = $("#mainForm_domain_fhrMc").val();
		pcdh = $("#mainForm_domain_pcdh").val();
		var hdbh = $("#mainForm_domain_hdbh").val();
		//var sfXsFgs=$(":checkbox[name='domain.sfXsFgs']")[0].checked?'1':'2';
		//����������
		var url = jcontextPath+"/hygl/hypchddj!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.pcrqq":pcrqq,
	 			"domain.pcrqz":pcrqz, "domain.fhrMc":encodeURI(fhrMc),"domain.pcdh":encodeURI(pcdh),
	 			"domain.fhrDjxh":fhrDjxh,"domain.zt":zt,"domain.hdbh":encodeURI(hdbh),"domain.hwmc":encodeURI(hwmc)}							//����Ĳ�����json��ʽ
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
		    colNames:['���','�ɳ��Ǽ����','��������',
		              '��������','����','״̬','����','����״̬����','�����������','�������','δ�����Ǽ����','�ص��Ǽ����','�ͻ�����', 
		              '�ص����','ddDjxh','��������', 'ʼ����', 'Ŀ�ĵ�', 'ת�벿��','��������', 
		              '������ַ', 'Ҫ�󷢻�����', '�ջ���','�ջ���ַ','Ҫ�󵽴�����','xybz',
		               '�ɳ�����','���', 'װ��', '��������', '�ҳ�����', 
		              '˾��', '�˷�', 'Ԥ��', '�ɳ���', '�ɳ�����', '�ɳ�����', '��������'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[
		      {name:'pageXh', index:'pageXh', sortable:false, width:'30', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pageXh' + rowId + '\'';
			    }
			  },
			  {name:'pcDjxh', index:'pcDjxh', hidden:true,width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcDjxh' + rowId + '\'';
			    }
			  },
			  {name:'fsspCheck', index:'fsspCheck', hidden:true, width:'60', align:'center'},
			  {name:'pljsCheck',index:'pljsCheck',width:'60',align:'center'},
			  {name:'hstoperationcol', index:'', width:'65', sortable:false,align:'center'},
			  {name:'dzsl', index:'dzsl', width:'45', align:'center'},
			  {name:'wsspztMc', index:'wsspztMc', hidden:true, width:'45', align:'center'},
			  {name:'wsspztDm', index:'wsspztDm', hidden:true,width:'80', align:'center'},
			  {name:'wsSpxh', index:'wsSpxh',hidden:true, width:'80', align:'center'},
			  {name:'dingdan', index:'dingdan', width:'80', align:'center'},
			  {name:'wfhDjxh', index:'wfhDjxh',hidden:true, width:'', align:'center'},
			  {name:'hdDjxh', index:'hdDjxh',hidden:true, width:'', align:'center'},
			  {name:'fhrMc', index:'fhrMc', width:'120', align:'center'},
			  {name:'hdbh', index:'hdbh', width:'80', align:'center'},
			  {name:'ddDjxh', index:'ddDjxh',hidden:true, width:'80', align:'center'},
			  {name:'hwMc', index:'hwMc', width:'100', align:'center'},
		      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'40', align:'center'},
		      {name:'shrXzqhMc', index:'shrXzqhMc', width:'40', align:'center'},
		      {name:'zrbmMc', index:'zrbmMc', width:'100', align:'center'},
		      {name:'jssl', index:'jssl', width:'60', align:'right'},
		      {name:'sfdMc', index:'sfdMc', width:'200', align:'center'},
		      {name:'yqFhrq', index:'yqFhrq', width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      {name:'shrMc', index:'shrMc', width:'100', align:'center'},
		      {name:'mddMc', index:'mddMc', width:'200', align:'center'},
		      {name:'yqDdrq', index:'yqDdrq', width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
			  {name:'xybz', index:'xybz',hidden:true, width:'80', align:'center'},
			  {name:'pcdh', index:'pcdh', width:'65', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcdh' + rowId + '\'';
			    }
			  },
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
			  {name:'yfHj', index:'yfHj', width:'50', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'yfHj' + rowId + '\'';
			    }
			  },
			  {name:'yfYfyf', index:'yfYfyf', width:'40', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'yfYfyf' + rowId + '\'';
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
			  }
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum %>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[10,20,50,100],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'PCDH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		  
	  	  // add custom button to export the data to excel
		  /* $("#dataList").jqGrid('navButtonAdd','#pager',{
		       caption:"", 
		       onClickButton : function () { 
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/hypchddj!download");
				   $("#mainForm").submit();
		       } 
		 }); */
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
       var graduateIds = $("#dataList").jqGrid('getDataIDs');
       var xtcsSfsp = $("#mainForm_domain_xtcsSfsp").val();
		
       for (var i = 0; i < graduateIds.length; i++) {
           var cl = graduateIds[i];
           var pcDjxh = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh"); 
           var pcdh = jQuery("#dataList").jqGrid('getCell', cl,"pcdh"); 
           var wfhDjxh = jQuery("#dataList").jqGrid('getCell', cl,"wfhDjxh");
           var wsSpxh = jQuery("#dataList").jqGrid('getCell', cl,"wsSpxh");
           var hdDjxh = jQuery("#dataList").jqGrid('getCell', cl,"hdDjxh");
           var dzsl = jQuery("#dataList").jqGrid('getCell', cl,"dzsl");
           var ddbh = jQuery("#dataList").jqGrid('getCell', cl,"dingdan");
           var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh"); 
           var xybz = jQuery("#dataList").jqGrid('getCell', cl,"xybz"); 
           
           var strDan="<a href=\"javascript:onViewMx("+ddDjxh+")\"><font color=\"blue\">"+ddbh+"</font></a>";
           var strPc="<a href=\"javascript:onViewPc("+pcDjxh+")\"><font color=\"blue\">"+pcdh+"</font></a>";
           var link4Hw = "<a href=\"javascript:onHddj('"+pcDjxh+"','"+wfhDjxh+"','"+hdDjxh+"','"+xybz+"')\"><font color=\"blue\">�Ǽ�</font></a>";
           var str = "<font color=\"red\">δ�Ǽ�</font>";
           var linkPlJs = '<input type="checkbox" name="pljs" value="'+pcDjxh+'#'+wfhDjxh+'" />';
           if (dzsl > 0) {
           	str = "�ѵǼ�";
           	linkPlJs = '<input type="checkbox" name="pljs" value="'+pcDjxh+'#'+wfhDjxh+'" disabled="disabled" />';
           	link4Hw = "<a href=\"javascript:onHddj('"+pcDjxh+"','"+wfhDjxh+"','"+hdDjxh+"','"+xybz+"')\"><font color=\"blue\">�޸�</font></a>"
           			+ "&nbsp;<a href=\"javascript:onDelHddj('"+hdDjxh+"','"+pcDjxh+"','"+wfhDjxh+"')\"><font color=\"blue\">����</font></a>"
           }
           $("#dataList").jqGrid('setRowData', cl, { 'dingdan': strDan });
           $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link4Hw }); 
           $("#dataList").jqGrid('setRowData', cl, { 'dzsl': str });
           $("#dataList").jqGrid('setRowData', cl, { 'pcdh': strPc });
           if ("Y" == xtcsSfsp) {
				var wsspztDm = jQuery("#dataList").jqGrid('getCell', cl,"wsspztDm"); 
				var spLink = '<input type="checkbox" name="xhs" value="'+hdDjxh+'#'+wsSpxh+'" />';
				if ("1" == wsspztDm || "3" == wsspztDm || "" == hdDjxh) {
					spLink = '<input type="checkbox" name="xhs" value="'+hdDjxh+'#'+wsSpxh+'" disabled="disabled" />';
				}
				
				$("#dataList").jqGrid('setRowData', cl, { 'fsspCheck': spLink }); 
   			}
   			$("#dataList").jqGrid('setRowData', cl, { 'pljsCheck': linkPlJs });
       }
        
       var gridName = "dataList";
	   var a = ['pageXh','pcdh','pcfsMc','zcfxMc','cyrClhm','cyrGchm','cyrSjxm',
	            'yfHj','yfYfyf','pcrMc','pcrq','pcJgbmMc','ssJgbmMc'];
 		
       Merger(gridName, 'pcDjxh', a);
    }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="hypchddj!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.xtcsSfsp" />
	<s:hidden name="domain.xtcs20016" />
	<s:hidden name="dropDownData"></s:hidden>
	<s:hidden name="jsonData"></s:hidden>
	
	
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">������</li>
		    <li><a href="#" id="queryBtn" class="licon04">�� ��</a></li>
	    <s:if test='domain.xtcsSfsp == "Y"'>
		    <li><a href="#" id="plScSendBtn" class="licon10">������������</a></li>
	    </s:if>
	    	<li><a href="#" id="plJsBtn" class="licon10">��������</a></li>
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
		        	<td width="10%" align="right">ҵ��λ��</td>
          			<td width="21%">
          				<sys:fgsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y" myClass="select"></sys:fgsList>
          				
		  			</td>
		  			 <td width="10%" align="right">�ͻ����ƣ�</td>
		          <td width="21%">
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
	        	  <td align="right" width="10%">�ɳ����ڣ�</td>
		          <td width="21%">
		          	<sys:dateFirstDMonth myName="domain.pcrqq" myId="mainForm_domain_pcrqq" myClass="ymdate" />
	          			��
	          		<sys:dateCurrentDayTag myName="domain.pcrqz" myId="mainForm_domain_pcrqz" myClass="ymdate" />
		         
		          </td>
		        </tr>
		        <tr>
	        	  <td align="right">�ص���ţ�</td>
		          <td><s:textfield name="domain.hdbh" cssClass="pop_input noborder" /></td>
		          <td align="right">�ɳ����ţ�</td>
		          <td><s:textfield name="domain.pcdh" cssClass="pop_input noborder" /></td>
		          <td align="right">�������ƣ�</td>
		          <td><s:textfield name="domain.hwmc" cssClass="pop_input noborder" /></td>
		        </tr>
		        <tr>
	        	  <td align="right">״̬��</td>
		          <td>
		          	<s:radio name="domain.zt" list='#{"":"����","1":"�ѵǼ�","2":"δ�Ǽ�"}' listKey="key" listValue="value"></s:radio>
		          </td>
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
