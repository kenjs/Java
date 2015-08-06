<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>����-������ʧ��Ϣ</title>
<style type="text/css">

</style>
<script type="text/javascript">
	$(document).ready(function(){
		//$(window).unbind("resize");
		//��ʼ�����
		initWlssxxDataGrid();
		onWlssxxRefresh();
	});
	
	function onViewMx(wlssDjxh){
		var url = jcontextPath+"/jcgl/jctydgl!viewWlssMx.action?domain.wlssDjxh="+wlssDjxh;
		window.showModalDialog(url,window,"dialogHeight:540px;dialogWidth:690px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
	}
	
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onWlssxxRefresh(){
		var ddDjxh = $("#mainForm_domain_ddDjxh").val();
		var pcDjxh = $("#mainForm_domain_pcDjxh").val();
		
		//����������
		var url = jcontextPath+"/jcgl/jctydgl!queryJcWlssxx.action";   
		 $("#wlssList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ddDjxh":ddDjxh,"domain.pcDjxh":pcDjxh}								//����Ĳ�����json��ʽ
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
	function initWlssxxDataGrid(){ 
		  $("#wlssList").jqGrid({
		    url:"",
		    datatype: 'local',
		    mtype: 'POST',
		    rownumbers : false,					//�����
			width:pageWidth()-10, 
			height:pageTableHeight()-90,	
		    gridComplete: myWlssxxGridComplete,		//����������¼�
		    shrinkToFit:false, 
		    colNames:['���','','','','','��������','����','����','���','��ʧ����','��ʧ����','��ʧ���','��ʧ���',
		              '��ʧ��Դ','����״̬','�ͻ�����','������������','�Ǽǻ�������'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[
			  {name:'pageXh', index:'pageXh', sortable:false, width:'30', align:'center',
				  cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pageXh' + rowId + '\''; 
				    }
			  },
			  {name:'ddDjxh', index:'ddDjxh', hidden:true, width:'30', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'ddDjxh' + rowId + '\''; 
				    }
			  },			  
			  {name:'hwmxxh', index:'hwmxxh', hidden:true, width:'30', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'hwmxxh' + rowId + '\''; 
				    }
			  },
			  {name:'wlssLybz', index:'wlssLybz', hidden:true, width:'30', align:'center'},
			  {name:'wlssDjxh', index:'wlssDjxh', hidden:true, width:'30', align:'center'},
			  {name:'hwMc', index:'hwMc', sortable:false, width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'hwMc' + rowId + '\''; 
				    }
			  },
			  {name:'sl', index:'sl', sortable:false, width:'30', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'sl' + rowId + '\''; 
				    }
			  },
			  {name:'zl', index:'zl', sortable:false, width:'30', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'zl' + rowId + '\''; 
				    }
			  },
			  {name:'tj', index:'tj', sortable:false, width:'30', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'tj' + rowId + '\''; 
				    }
			  },
		      {name:'wlssSl', index:'wlssSl', sortable:false,width:'80', align:'center'},
		      {name:'wlssZl', index:'wlssZl', sortable:false,width:'80', align:'center'},
		      {name:'wlssTj', index:'wlssTj', sortable:false,width:'80', align:'center'},
		      {name:'je', index:'je', sortable:false,width:'60', align:'center'},
		      {name:'ssly', index:'ssly', sortable:false,width:'80', align:'center'},
		      {name:'wsspztMc', index:'wsspztMc', sortable:false,width:'80', align:'center'},
		      {name:'khmc', index:'khmc', sortable:false,width:'80', align:'center'},
		      {name:'ssJgmc', index:'ssJgmc', sortable:false,width:'120', align:'center'},
		      {name:'pcJgmc', index:'pcJgmc', sortable:false,width:'120', align:'center'}		     
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: -1,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[-1],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'DD_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
		    sortorder: 'DESC',				//Ĭ��������
		    viewrecords: true,					//�����Ƿ�Ҫ��ʾ�ܼ�¼��
		    gridview: false,					//Ĭ��false ����һ�����ݺ���ӵ�grid�У������Ϊtrue���ǽ������������ݶ�������ɺ�����ӵ�grid�У���treeGrid, subGrid, or afterInsertRow ������	
		    jsonReader: {     
	     	 	root: 	 "domain.wlssList",   				// �����У�Ĭ��Ϊ��rows��
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
		  jQuery("#wlssList").jqGrid('navGrid','#pager',
		 		 {edit:false,add:false,del:false}
		  );
		  
	}
	
	function myWlssxxGridComplete() {
        var graduateIds = $("#wlssList").jqGrid('getDataIDs');
		
        var heightT = getAutoGridHeight(graduateIds.length);
	    $("#wlssList").setGridHeight(heightT);
        
         for (var i = 0; i < graduateIds.length; i++) {
            var cl = graduateIds[i];
            var je = jQuery("#wlssList").jqGrid('getCell', cl,"je");   
            var wlssLybz = jQuery("#wlssList").jqGrid('getCell', cl,"wlssLybz");   
            var wlssDjxh = jQuery("#wlssList").jqGrid('getCell', cl,"wlssDjxh");       
			var link = "";
			if(wlssLybz == 0){
				link = "ֱ�ӵǼ�";
			} else if(wlssLybz == 1){
				link = "�ص�ʱ";
			} else {
				link = "��������ʱ";
			}
			var link2 = "<a href=\"javascript:onViewMx('"+wlssDjxh+"')\"><font color=\"blue\">"+je+"</font></a>";
            $("#wlssList").jqGrid('setRowData', cl, { 'ssly': link }); 
            $("#wlssList").jqGrid('setRowData', cl, { 'je': link2 }); 
        } 
       var gridName = "wlssList";
	   var cellNames = ['pageXh','ddDjxh','hwmxxh','hwMc','sl','zl','tj'];
  		
       Merger(gridName, 'pageXh',cellNames);
 }
	
     /**************************************��ҳ����****************************************/
</script>
</head>
<body>
		<!-- ��ҳ��� id����ΪyfList -->
		<table id="wlssList"><tr><td/></tr></table> 
		<!-- ��ҳ���� -->
		<%@include file="/common/message.jsp" %>
</body>
</html>
