<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>����-�ɳ���Ϣ����</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript">
	$(document).ready(function(){
			init();
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			$("#plScSendBtn").click(function(){				
				var xhs = $(":checked:enabled[name='xhs']");
			  	if (xhs.length <= 0) {
					showAlert("��ѡ����Ҫ����ȷ�ϵļ�¼��");
					return;
			  	}
			  	var plXhs = new Array();
			  	$.each(xhs,function(i,obj){			  		
			  		plXhs[i] = xhs[i].value;
			  	});
			  	
			  	var jsonStr = getJqueryParamZdy(plXhs,"domain.plqrXhs");
			  	var url = jcontextPath+"/hygl/jspsfqr!plqr"; 
			    ajaxCommon(url,encodeURI(jsonStr),"doPlScSendSuc",false);
			});
			
			 $("#psfBtn").click(function(){
			 	var pcrqq = $("#mainForm_domain_pcrqq").val();
				var pcrqz = $("#mainForm_domain_pcrqz").val(); 
				if (pcrqq == "" || pcrqz == "") {
					showAlert("�ɳ����ڲ���Ϊ�գ�");
					return;
				}
				var pcdh = $("#mainForm_domain_pcdh").val();
				var url = jcontextPath+"/hygl/jspsfqr!dyyl?domain.pcrqq="+pcrqq+"&domain.pcrqz="+pcrqz+"&domain.pcdh="+pcdh;
				window.open(url,"_blank")
					
			 });
			//��ʼ�����
			initDataGrid();
	});
	function getJqueryParamZdy(obj, paraName) {
		var data = "";
		$.each(obj, function(i){
			data += paraName + "[" + i + "]=" + encodeURI(obj[i]) + "&";
		});
		
		return data;
	}
	
	function doPlScSendSuc(data) {
		hideMessage();
		showAlert("����ȷ�ϳɹ���");
		onRefresh();
	}
	
    function onUpdate(psfDjxh){
    	if(psfDjxh == '' || psfDjxh == null || psfDjxh == undefined){
    		showAlert("�����ͷѿ�ȷ�ϣ�");
    		return;
    	}
    	var url = jcontextPath+"/hygl/jspsfqr!initMx?domain.psfDjxh="+psfDjxh;    	
    	window.showModalDialog(url,window,"dialogHeight:350px;dialogWidth:500px;center:yes;resizable:no;status:no;scroll:no;help:no;maximize:yes;minimize:yes;")
    	onRefresh();
    }
	
    function onView(psfDjxh){
    	var url = jcontextPath+"/hygl/jspsfqr!viewMx?domain.psfDjxh="+psfDjxh;
    	window.showModalDialog(url,window,"dialogHeight:350px;dialogWidth:500px;center:yes;resizable:no;status:no;scroll:no;help:no;maximize:yes;minimize:yes;")
    	onRefresh();
    }
    
    var parPsf = '';
    function onBack(psfDjxh){
    	parPsf = psfDjxh;    	
    	showConfirm("ȷ��Ҫ�˻���","doBack");
    }
    
    function doBack(){
    	var jsonObj = {"domain.psfDjxh":parPsf};
    	var url = jcontextPath+"/hygl/jspsfqr!delete";   
        ajaxCommon(url,jsonObj , "doSuccess");  
    }
    
    function doSuccess(){     
        showAlert("�˻سɹ���");
        onRefresh();
	}	
    
    function init() {
    	var xtcs20030 = $("#mainForm_domain_xtcs20030").val();    	
    	if(xtcs20030 == 'N'){
    		$("#plScSendBtn").hide();    		
    	}
    }
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		pcrqq = $("#mainForm_domain_pcrqq").val();
		pcrqz = $("#mainForm_domain_pcrqz").val(); 
		if (pcrqq == "" || pcrqz == "") {
			showAlert("�ɳ����ڲ���Ϊ�գ�");
			return;
		}
		pcdh = $("#mainForm_domain_pcdh").val();
		
		
		//����������
		var url = jcontextPath+"/hygl/jspsfqr!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.pcrqq":pcrqq,"domain.pcrqz":pcrqz, "domain.pcdh":encodeURI(pcdh)
		 			}								//����Ĳ�����json��ʽ
		 }
		 ).trigger("reloadGrid");		//��ʾ����һҳ�������������ݷ����仯��ʱ�򣬷�ҳ����Ϣ����
	}
	
	//���˵���Ϣ�鿴
	function onViewMx(ddDjxh) {
		var url = jcontextPath+"/jcgl/jctydgl!initMx.action?domain.ddDjxh="+ddDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    	//window.open(url);
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
		    colNames:['���','�ɳ�����','','','','','', '�ɳ�����','�������','�ͻ�����','ddDjxh', '��������','�ջ���','�ջ���ַ',
		              '���ε�λ','����', '����', '���','������Ϣ','�˷�/Ԥ��', '����/����','����','���','�ͻ���ʽ','','���ͷ�','�Ƿ�ȷ��','���ͷ�ȷ��',
		              '<input title="ѡ��/ȡ��ѡ��" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />','�ɳ���', '�ɳ�����', '��������'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[
			  {name:'pageXh', index:'pageXh', sortable:false, width:'30', align:'center',
				  cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pageXh' + rowId + '\'';
				    }  
			  },
			  {name:'pcdh', index:'pcdh', width:'70', align:'center',
				  cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcdh' + rowId + '\'';
				    }
			  },
			  {name:'ssJgbm', index:'ssJgbm', width:'70', align:'center',hidden:true},
			  {name:'wfhDjxh', index:'wfhDjxh', width:'70', align:'center',hidden:true},
			  {name:'pcDjxh', index:'pcDjxh', width:'70', align:'center',hidden:true},
			  {name:'zrbmDjxh', index:'zrbmDjxh', width:'70', align:'center',hidden:true},
			  {name:'pchwLsxh', index:'pchwLsxh', width:'70', align:'center',hidden:true},
			  {name:'pcrq', index:'pcrq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
				  cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcrq' + rowId + '\'';
				    }  
			  },
			  {name:'dingdan',index:'dingdan', width:'80', align:'center'},
			  {name:'fhrMc', index:'fhrMc', width:'120', align:'center'},
			  {name:'ddDjxh', index:'ddDjxh',hidden:true, width:'120', align:'center'},
			  {name:'hwMc', index:'hwMc', width:'100', align:'center'},
			  {name:'shrMc', index:'shrMc', width:'100', align:'center'},
		      {name:'mddMc', index:'mddMc', width:'200', align:'center'},
		      {name:'zrbmMc', index:'zrbmMc', width:'80', align:'center'},
		      {name:'sl', index:'sl', width:'50', align:'right'},  
		      {name:'zl', index:'zl', width:'50', align:'right'},
		      {name:'tj', index:'tj', width:'50', align:'right'},
		      {name:'clxx', index:'clxx', width:'200', align:'left'},
			  
		      {name:'yfhjyf', index:'yfhjyf', width:'60', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'yfhjyf' + rowId + '\'';
				    }
				  },
			  {name:'srhjdf', index:'srhjdf', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'srhjdf' + rowId + '\'';
			    }
			  },
			  {name:'zcZl', index:'zcZl', width:'40', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'zcZl' + rowId + '\'';
				    }
			  },
			  {name:'zcTj', index:'zcTj', width:'40', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'zcTj' + rowId + '\'';
				    }
			  },
			  {name:'zs', index:'zs', width:'60', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'zs' + rowId + '\'';
				    }
			  },
			  {name:'psfDjxh', index:'psfDjxh', width:'60', align:'center',hidden:true},
			  {name:'srPsf', index:'srPsf', width:'60', align:'center'},
			  {name:'sfQr', index:'sfQr', width:'60', align:'center'},
			  {name:'hstoperationcol', index:'', width:'80',sortable:false,align:'center'},
			  {name:'fsspCheck',index:'fsspCheck',align:'center',width:'20'},
			  {name:'pcrMc', index:'pcrMc', width:'60', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcrMc' + rowId + '\'';
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/jspsfqr!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var pcDjxh = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh"); 
                var ssJgbm = jQuery("#dataList").jqGrid('getCell', cl,"ssJgbm"); 
                var wfhDjxh = jQuery("#dataList").jqGrid('getCell', cl,"wfhDjxh"); 
                var zrbmDjxh = jQuery("#dataList").jqGrid('getCell', cl,"zrbmDjxh");
                var xh = jQuery("#dataList").jqGrid('getCell', cl,"pchwLsxh");
                var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh");
                var srPsf = jQuery("#dataList").jqGrid('getCell', cl,"srPsf");
                var hwMc = jQuery("#dataList").jqGrid('getCell', cl,"hwMc");
                var zs = jQuery("#dataList").jqGrid('getCell', cl,"zs");
                var psfDjxh = jQuery("#dataList").jqGrid('getCell', cl,"psfDjxh");
                
                var spLink = '<input type="checkbox" name="xhs" value="'+psfDjxh+'" />';
                var link = "";
                var linkT = "";
                var qrbz = jQuery("#dataList").jqGrid('getCell', cl,"sfQr");
                if(qrbz == 'Y'){
					if(zs == '����'){
						link = "";
						linkT = "";
						spLink = "";
                	}else{
                		link += "<a href=\"javascript:onView('"+psfDjxh+"')\"><font color=\"blue\">�鿴</font></a>" +
                				" <a href=\"javascript:onBack('"+psfDjxh+"')\"><font color=\"blue\">�˻�</font></a>";
                		linkT = "��ȷ��"; 
                		spLink = '<input type="checkbox" name="xhs" value="'+psfDjxh+'" disabled="disabled" />';
                	}
                }else{
                	if(zs == '����'){
						link = "";
						linkT = "";
						spLink = "";
                	}else{
                		link += "<a href=\"javascript:onUpdate('"+psfDjxh+"')\"><font color=\"blue\">ȷ��</font></a>";
                		linkT = "<font color=\"red\">δȷ��</font>";
                	}
                }
                if(psfDjxh == '' || psfDjxh == null || psfDjxh == undefined){
                	spLink = '<input type="checkbox" name="xhs" value="'+psfDjxh+'" disabled="disabled" />';
            	}
                
                //������˵���Ϣ�鿴����
                var dingdan = jQuery("#dataList").jqGrid('getCell', cl,"dingdan");
                var str="<a href=\"javascript:onViewMx("+ddDjxh+")\"><font color=\"blue\">"+dingdan+"</font></a>";
                var xtcs20030 = $("#mainForm_domain_xtcs20030").val();    	                
            	if(xtcs20030 == 'N'){            		
            		jQuery("#dataList").setGridParam().hideCol("sfQr");
            		jQuery("#dataList").setGridParam().hideCol("fsspCheck");
            		link = "<a href=\"javascript:onView('"+psfDjxh+"')\"><font color=\"blue\">�鿴</font></a>";
            	}
                $("#dataList").jqGrid('setRowData', cl, { 'dingdan': str });
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
                $("#dataList").jqGrid('setRowData', cl, { 'sfQr': linkT }); 
                $("#dataList").jqGrid('setRowData', cl, { 'fsspCheck': spLink }); 
            }
            var gridName = "dataList";
     	    var a = ['pageXh','pcdh','pcrq','yfhjyf','srhjdf','zcZl','zcTj','zs','pcrMc','pcJgbmMc','ssJgbmMc'];
      		
            Merger(gridName, 'pcDjxh', a);
     }
     
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������

       
   	
     /**************************************��ҳ����****************************************/
</script>
</head>
<body>
<s:form action="jspsfqr!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">	
	<s:hidden name="jsonData" />
	<s:hidden name="domain.xtcs20030" />
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">������</li>
		    <li><a href="#" id="queryBtn" class="licon04">�� ��</a></li>
		    <li><a href="#" id="plScSendBtn" class="licon10">����ȷ��</a></li>
		    <li><a href="#" id="psfBtn" class="licon10">���ͷ��˵�</a></li>
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
		        	<td width="8%" align="right">�ɳ����ţ�</td>
          			<td width="15%">
          				<s:textfield name="domain.pcdh" cssClass="pop_input noborder" />
		  			</td>
		  			<td width="8%" align="right">�ɳ����ڣ�</td>
          			<td width="21%">
          				<sys:dateCurrentDayTag myName="domain.pcrqq" myId="mainForm_domain_pcrqq" myClass="ymdate" />
	          			��
	          			<sys:dateCurrentDayTag myName="domain.pcrqz" myId="mainForm_domain_pcrqz" myClass="ymdate" />
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
