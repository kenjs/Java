<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>���˵�����</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
			document.getElementById("maincont").onmousewheel=hideHelpWindow;
			initHykhData(300, $("#mainForm_domain_ssJgbm4Query").val(), $("#mainForm_domain_djJgbm4Query").val());
			
			$("#mainForm_domain_ssJgbm4Query").change(function(){
				$("[name='domain.fhrMc']").unbind();
				initHykhData(300, $(this).val(), $("#mainForm_domain_djJgbm4Query").val());
			});
			
			$("#mainForm_domain_djJgbm4Query").change(function(){
				initRy();
				$("[name='domain.fhrMc']").unbind();
				initHykhData(300, $("#mainForm_domain_ssJgbm4Query").val(), $(this).val());
			});
		
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				xdrqQ = $("#mainForm_domain_xdrqQ").val();
				xdrqZ = $("#mainForm_domain_xdrqZ").val();
				xdrqQs=xdrqQ.split("-");
				xdrqZs=xdrqZ.split("-");
				if(xdrqQs.length!=3||xdrqZs.length!=3){
					showAlert("�µ����ڸ�ʽ����");
					return;
				}
				onRefresh();
			});
			
			//������ť�¼�
			$("#addBtn").click(function(){
				onUpdate('');
				//popwindow(jcontextPath+"/hygl/hytydgl!initMx");
			});
			
			$("#mainForm_domain_ddbhQ").change(function(){
				$("#mainForm_domain_ddbhZ").val($(this).val())
			});
			//��ʼ�����
			initDataGrid();
			initRy();	
			var sjJgbm = $("#mainForm_domain_ssJgbm4Query").val();
			bmInit(sjJgbm, '', "domain.djJgbm4Query", "mainForm_domain_djJgbm4Query", "Y", "Y");
	});
	
	function initRy() {
		var sj = $("#mainForm_domain_djJgbm4Query").val();
		commonInit("BMYH", sj, '', "domain.djrCzyDjxh4Query", "mainForm_domain_djrCzyDjxh4Query", "Y", "Y");
	}
	

	function onViewMx(ddDjxh) {
		var url = jcontextPath+"/jcgl/jctydgl!initMx.action?domain.ddDjxh="+ddDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    	//window.open(url);
	}
	
	var keyValue = "";
	function onDelete( ddDjxh){
		keyValue = ddDjxh;
		showConfirm("ȷ��Ҫɾ��ô��","doDelete");
	}	
	
	function onUpdate(ddDjxh) {
		var url = jcontextPath + '/hygl/hytydgl!initMxCy.action?domain.ddDjxh='+ddDjxh
		navigateMenu(url,'���˵��޸�','true');
    	//window.open(url);
    	//onRefresh();
	}
	
	function doDelete(){		
		showMessage();
		 var jsonObj = {"domain.ddDjxh":keyValue};
		 var url = jcontextPath+"/hygl/hytydgl!delete";   
        ajaxCommon(url,jsonObj , "deleteSuc");  
	}
	
	function deleteSuc(){  
		hideMessage();
        showAlert("ɾ���ɹ���");
        onRefresh();
	}	
	
	function onUpdateHw(ddDjxh, xh) {
		var url = jcontextPath + '/hygl/hytydgl!initMxCy.action?domain.ddDjxh='+ddDjxh+'&domain.hwmxDomain.xh='+xh;
		navigateMenu(url,'���˵��޸�','true');
	}
	
	function onView(ddDjxh) {
		var url = jcontextPath+"/jcgl/jctydgl!initMx.action?domain.ddDjxh="+ddDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    	//window.open(url);
	}
	function onPrintView(ddDjxh, xh){
		var url = jcontextPath + '/hygl/hytydgl!printView.action?domain.hwmxDomain.ddDjxh='+ddDjxh+'&domain.hwmxDomain.xh='+xh;
		window.open(url, "_blank");
		//navigateMenu(url,'���˵���ӡ','true');
    	//window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
	}
	
	function onPrintAll(ddDjxh, xh){
		var url = jcontextPath + '/hygl/hytydgl!printAll.action?domain.hwmxDomain.ddDjxh='+ddDjxh+'&domain.hwmxDomain.xh='+xh;
		window.open(url, "_blank");
	}
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		xdrqQ = $("#mainForm_domain_xdrqQ").val();
		xdrqZ = $("#mainForm_domain_xdrqZ").val();
		if(xdrqQ>xdrqZ){
			showError("�µ���ʼ���ڲ��ܴ����µ���ֹ���ڣ�");
			return;
		}
		if (xdrqQ == "" || xdrqZ == "") {
			showAlert("�µ����ڲ���Ϊ�գ�");
			return;
		}
		djJgbm4Query = $("#mainForm_domain_djJgbm4Query").val();
		ssJgbm4Query = $("#mainForm_domain_ssJgbm4Query").val();
		djrCzyDjxh4Query = $("#mainForm_domain_djrCzyDjxh4Query").val();
		fhrDjxh4Query = $("#mainForm_domain_fhrDjxh").val();
		fhrMc4Query = $("#mainForm_domain_fhrMc").val();
		
		ddbhQ = $("#mainForm_domain_ddbhQ").val();
		ddbhZ = $("#mainForm_domain_ddbhZ").val();
		fhrMc4 = $("#mainForm_domain_fhrMc4").val();
		shrMc4 = $("#mainForm_domain_shrMc4").val();
		hwMc4 = $("#mainForm_domain_hwMc4").val();
		hwSl4 = $("#mainForm_domain_hwSl4").val();
		hwZl4 = $("#mainForm_domain_hwZl4").val();
		hwTj4 = $("#mainForm_domain_hwTj4").val();
		zsr4 = $("#mainForm_domain_zsr4").val();
		shfsDm4 = $("#mainForm_domain_shfsDm4").val();
		yjWjBz4 = $("#mainForm_domain_yjWjBz4").val();

		var xjBz4 = ""; 
		var dfBz4 = ""; 
		var yjBz4 = "";
		var e1 = document.getElementById("xjBz4");
		if(e1.checked){
			xjBz4="1";
		} else {
			xjBz4="0";
		}
		var e2 = document.getElementById("dfBz4");
		if(e2.checked){
			dfBz4="1";
		} else {
			dfBz4="0";
		}
		var e3 = document.getElementById("yjBz4");
		if(e3.checked){
			yjBz4="1";
		} else {
			yjBz4="0";
		}

		//����������
		var url = jcontextPath+"/hygl/hytydgl!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.djJgbm4Query":djJgbm4Query,"domain.ssJgbm4Query":ssJgbm4Query, "domain.djrCzyDjxh4Query":djrCzyDjxh4Query,"domain.fhrMc4Query":encodeURI(fhrMc4Query),
		 			"domain.xdrqQ":xdrqQ,"domain.xdrqZ":xdrqZ,"domain.fhrDjxh4Query":fhrDjxh4Query,
		 			"domain.ddbhQ":ddbhQ,"domain.ddbhZ":ddbhZ,"domain.fhrMc4":encodeURI(fhrMc4),"domain.shrMc4":encodeURI(shrMc4),"domain.hwMc4":encodeURI(hwMc4),
		 			"domain.hwSl4":hwSl4,"domain.hwZl4":hwZl4,"domain.hwTj4":hwTj4,"domain.zsr4":zsr4,"domain.xjBz4":xjBz4,
		 			"domain.dfBz4":dfBz4,"domain.yjBz4":yjBz4,"domain.shfsDm4":shfsDm4,"domain.yjWjBz4":yjWjBz4
		 			}								//����Ĳ�����json��ʽ
		 }
		 ).trigger("reloadGrid");		//��ʾ����һҳ�������������ݷ����仯��ʱ�򣬷�ҳ����Ϣ����
	}
	
	<% 
	    //��ȡÿ���û���ÿҳ��ʾ����ֵ
		UserDomain userDomain=(UserDomain) request.getSession().getAttribute(WebConstants.SES_USER_INFO);
		String rowNum = "50";
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
		    colNames:['���','����', '�����Ǽ����','�������','�µ�����', '������λ','�����־','��ӡ',
		              '��������', '������','�������','ʼ����', 'Ŀ�ĵ�','��װ','��������', '����', '����', '���','����','����','�ָ�','�½�',
		              '�ؿ�','�����','���ͷ�','���۷�','���ջ���',
		              '�ջ���λ','�ջ���', '�ͻ���ʽ', 'Ҫ�󷢻�����', 'Ҫ�󵽴�����', '״̬', '�Ǽ���', '�Ǽǲ���', '��������'
		              ],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'pageXh', index:'pageXh', sortable:false, width:'30', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pageXh' + rowId + '\'';
			    }
			  },
			  {name:'hstoperationcol', index:'', sortable:false, width:'55', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'hstoperationcol' + rowId + '\'';
			    }
			  },
			  {name:'ddDjxh', index:'ddDjxh', hidden:true,width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'ddDjxh' + rowId + '\'';
			    }
			  },
			  {name:'ddbh', index:'ddbh', width:'65', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'ddbh' + rowId + '\'';
			    }
			  },
			  {name:'xdrq', index:'xdrq', width:'65', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'xdrq' + rowId + '\'';
				    }
			  },
			  {name:'fhrMc', index:'fhrMc', width:'130', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'fhrMc' + rowId + '\'';
				    }
			  },
			  {name:'yjWjBz', index:'yjWjBz', width:'70', align:'center',
			  		cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'yjWjBz' + rowId + '\'';
				    }
			  },
			  {name:'print', index:'', sortable:false, width:'55', align:'center'},
		      {name:'hwmc', index:'hwmc', width:'100', align:'center'}, 
		      {name:'fhrLxr', index:'fhrLxr', width:'40', align:'center'},
		      {name:'xh', index:'xh', hidden:true,width:'', align:'center'},
		      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'40', align:'center'},
			  {name:'shrXzqhMc', index:'shrXzqhMc', width:'40', align:'center'},
			 
			  
			  {name:'hwbz', index:'hwbz', width:'50', align:'center'}, 
		      {name:'jsSl', index:'jsSl', hidden:true,width:'60',align:'right'},		      
		      {name:'sl', index:'sl', width:'50', align:'right'}, 
		      {name:'zl', index:'zl', width:'50', align:'right'}, 
		      {name:'tj', index:'tj', width:'50', align:'right'}, 
		      {name:'srHj', index:'srHj', width:'70', align:'center'},
		      {name:'srDf', index:'srDf', width:'70', align:'center'},
		      {name:'srXf', index:'srXf', width:'70', align:'center'},
		      {name:'srYj', index:'srYj', width:'70', align:'center'},
		      
		      {name:'srHk', index:'srHk', width:'70', align:'center'},
		      {name:'srYsf', index:'srYsf', width:'70', align:'center'},
		      {name:'srPsf', index:'srPsf', width:'70', align:'center'},
		      {name:'srBjf', index:'srBjf', width:'70', align:'center'},
		      {name:'fyDshk', index:'fyDshk', width:'70', align:'center'},	
		      
		      
		      {name:'shrMc', index:'shrMc', width:'130', align:'center'},
		      {name:'shrLxr', index:'shrLxr', width:'40', align:'center'},
			  {name:'shfsMc', index:'shfsMc', width:'60', align:'center'},
			  {name:'yqFhrq', index:'yqFhrq', width:'75', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
			  {name:'yqDdrq', index:'yqDdrq', width:'75', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
			  {name:'hwztMc', index:'hwztMc', width:'50', align:'center'},
			  {name:'djrMc', index:'djrMc', width:'70', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'djrMc' + rowId + '\'';
			    }
			  },
			  {name:'djJgmc', index:'djJgmc', width:'130', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'djJgmc' + rowId + '\'';
			    }
			  },
			  {name:'ssJgmc', index:'ssJgmc', width:'130', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'ssJgmc' + rowId + '\'';
			    }
			  }
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum %>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,200],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
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
		  
	  	  // add custom button to export the data to excel
		  $("#dataList").jqGrid('navButtonAdd','#pager',{
		       caption:"", 
		       onClickButton : function () { 
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/hytydgl!download.action");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                
                var pageXh = jQuery("#dataList").jqGrid('getCell',cl,"pageXh");
                if (pageXh == 0) {
                	$("#dataList").jqGrid('setRowData', cl, { 'pageXh': '�ϼ�' });
                	continue;
                }
                
                var val = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var hwmc = jQuery("#dataList").jqGrid('getCell', cl,"hwmc"); 
                var xh = jQuery("#dataList").jqGrid('getCell', cl,"xh");
                var ddbh = jQuery("#dataList").jqGrid('getCell', cl,"ddbh");
                var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh"); 
                var link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">�޸�</font></a>"
                + " <a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">ɾ��</font></a>";
                var str="<a href=\"javascript:onViewMx("+ddDjxh+")\"><font color=\"blue\">"+ddbh+"</font></a>";
                var hwLink = "<a href=\"javascript:onUpdateHw('"+val+"','"+xh+"')\"><font color=\"blue\">"+hwmc+"</font></a>";
                var link2 = "<a href=\"javascript:onPrintView('"+val+"','"+xh+"')\"><font color=\"blue\">��ӡ</font></a>"
                			+ "&nbsp;<a href=\"javascript:onPrintAll('"+val+"','"+xh+"')\"><font color=\"blue\">����</font></a>";
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
                $("#dataList").jqGrid('setRowData', cl, { 'print': link2 }); 
                $("#dataList").jqGrid('setRowData', cl, { 'hwmc': hwLink });
                $("#dataList").jqGrid('setRowData', cl, { 'ddbh': str });
            }
       var gridName = "dataList";
	   var a = ['pageXh','hstoperationcol','ddDjxh','ddbh',
	            'fhrMc','xdrq','yjWjBz','djrMc','djJgmc','ssJgmc'
	            ];
 		
       Merger(gridName, 'pageXh', a);
   }
       
     /**************************************��ҳ����****************************************/
     
</script>
</head>
<body>
<s:form action="hytydgl!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<input type="hidden" name="ypcHwNum" id="ypcHwNum" />
	<s:hidden name="fhrData" />
	
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">������</li>
		    <li><a href="#" id="queryBtn" class="licon04">�� ��</a></li>
		    
		    <li><a href="#" id="closeBtn" class="licon03">�� ��</a></li>
	  	</ul>
	
	    <ul class="rcont">
		    <li class="ricon02" onclick="slideToggle('syquery')">��ʾ/���ز�ѯ����</li>
		    <li class="ricon03">����</li>
	  	</ul>
	</div> 
	
	<div id="maincont" style="display: none;"></div>
	<div class="right_cont">
	  <div id="divQuery">
		<fieldset>
			<legend>��ѯ����</legend>
		   <table width="99%" border="0" cellspacing="0" cellpadding="0">
		        <tr style="display: none;">
          			<td width="9%" align="right">ҵ��λ��</td>
          			<td width="20%">
          				<sys:gsList myId="mainForm_domain_ssJgbm4Query" myName="domain.ssJgbm4Query" mcContainDmBz="Y" myClass="select" onChange="bmInit(this.value, '', 'domain.djJgbm4Query', 'mainForm_domain_djJgbm4Query', 'Y', 'Y')" />
		  			</td>
		  			<td width="10%" align="right">�Ǽǲ��ţ�</td>
          			<td width="20%">
          				<select id="mainForm_domain_djJgbm4Query" name="domain.djJgbm4Query" class="select" >
          					<option value="${domain.djJgbm4Query }" selected="selected"></option>
          				</select>
		  			</td>
	        	    <td width="10%" align="right">�Ǽ��ˣ�</td>
		            <td width="24%"><select name="domain.djrCzyDjxh4Query" id="mainForm_domain_djrCzyDjxh4Query" class="select" /></td>
        		</tr>
		        <tr>
	        	  <td width="10%" align="right">������λ��</td>
		          <td width="20%">
		          	<s:hidden name="domain.fhrDjxh"></s:hidden>
  					<div class="inputsel" style="width: 215px; ">
  						<s:textfield name="domain.fhrMc" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width:185px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="fhr" onFocus="this.blur()"></a>
  					</div>
			  		<div class="inputsc">
		              <div id="inputSel_fhr" class="inputselcont inputselFixedSize ac_results">
		              </div>
		            </div>
		          </td>
		          <td  width="10%" align="right">�������ƣ�</td>
		          <td width="23%"><s:textfield name="domain.hwMc4" cssClass="pop_input noborder inputext bgstyle_optional" ></s:textfield></td>
	        	  <td width="10%" align="right">�µ����ڣ�</td>
		          <td width="26%"><sys:dateCurrentDayTag myName="domain.xdrqQ" myId="mainForm_domain_xdrqQ" myClass="ymdate" />
	          			��
	          		<sys:dateCurrentDayTag myName="domain.xdrqZ" myId="mainForm_domain_xdrqZ" myClass="ymdate" /></td>
		          </tr>
	           	  <tr>
	           		<td  align="right">�����ˣ�</td>
		            <td><s:textfield name="domain.fhrMc4" cssClass="pop_input noborder inputext bgstyle_optional" ></s:textfield></td>
		            <td  align="right">�ջ��ˣ�</td>
		            <td><s:textfield name="domain.shrMc4" cssClass="pop_input noborder inputext bgstyle_optional" ></s:textfield></td>
	           		<td  align="right">���������ֹ��</td>
		            <td><s:textfield name="domain.ddbhQ" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width:40%"></s:textfield>
		            	��
		            <s:textfield name="domain.ddbhZ" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width:40%"></s:textfield></td>
	              </tr>
	              <tr>
	           		<td  align="right">������</td>
		            <td><s:textfield name="domain.hwSl4" cssClass="pop_input noborder inputext bgstyle_optional" ></s:textfield></td>
		            <td  align="right">������</td>
		            <td><s:textfield name="domain.hwZl4" cssClass="pop_input noborder inputext bgstyle_optional" ></s:textfield></td>
	           		<td  align="right">�����</td>
		            <td><s:textfield name="domain.hwTj4" cssClass="pop_input noborder inputext bgstyle_optional" ></s:textfield></td>
	              </tr>
	        </table>
	        <table width="99%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
		            <td  align="right" width="10%">�����룺</td>
		            <td width="20%"><s:textfield name="domain.zsr4" cssClass="pop_input noborder inputext bgstyle_optional" ></s:textfield></td>
	           		<td  align="right" width="10%">�ͻ���ʽ��</td>
		            <td width="9%"><s:select name="domain.shfsDm4" list="#{'':'����',1:'����',2:'�ͻ�'}" cssClass="select"/></td>
		            <td  align="right" width="10%">�����־��</td>
		            <td width="9%"><s:select name="domain.yjWjBz4" list="#{'':'����','Y':'�ѽ�','N':'δ��'}" cssClass="select"/></td>
		            <td  align="right" width="10%">���ʽ��</td>
		            <td width="21%">
		            	<s:checkbox name="domain.xjBz4" id="xjBz4"  checked="true" style="width:20px;"></s:checkbox>�ָ�
		            	<s:checkbox name="domain.dfBz4" id="dfBz4" checked="true" style="width:20px;"></s:checkbox>����
		            	<s:checkbox name="domain.yjBz4" id="yjBz4" checked="true" style="width:20px;"></s:checkbox>�½�
		            </td>
	              </tr>
	        </table>                   
		   
		</fieldset>
	  </div>
  
		<!-- ��ҳ��� id����ΪdataList -->
		<table id="dataList"><tr><td/></tr></table> 
		<!-- ��ҳ���� -->
		<div id="pager"></div>
		<%@include file="/common/message.jsp" %>
		
	</div>
</s:form>
</body>
</html>
