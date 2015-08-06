<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>����-��������</title>
<style type="text/css">

</style>
<script type="text/javascript">
	$(document).ready(function(){
		//$(window).unbind("resize");
		//��ʼ�����
		initClgzxxDataGrid();
		
		onClgzxxRefresh();
		
	});
	
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onClgzxxRefresh(){
		var xtcs20201 = $("#mainForm_domain_xtcs20201").val();
		if ("Y" == xtcs20201) {
			$("#clgzxxList").jqGrid('showCol',["wsspztMc"]);
		}
		var ddDjxh = $("#mainForm_domain_ddDjxh").val();
		var pcDjxh = $("#mainForm_domain_pcDjxh").val();
		
		//����������
		var url = jcontextPath+"/jcgl/jctydgl!queryJcSjcxClgzxx.action";   
		 $("#clgzxxList").jqGrid("setGridParam",{
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
	function initClgzxxDataGrid(){ 
		  $("#clgzxxList").jqGrid({
		    url:"",
		    datatype: 'local',
		    mtype: 'POST',
		    rownumbers : false,					//�����
			width:pageWidth()-10, 
			height:pageTableHeight()-90,	
		    gridComplete: myClgzxxGridComplete,		//����������¼�
		    shrinkToFit:false, 
		    colNames:['���', '�ɳ��Ǽ����','�ɳ�����','�ɳ���ʽ����', 'װ����ʽ����', '��������', '�ҳ�����', 
		              '˾��','�ɳ�����', '����', '˵��',
		              '��ϸ��ַ','Ԥ������', '��������', '�Ǽǲ���'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[
			  {name:'pageXh', index:'pageXh', sortable:false, width:'30', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pageXhClgz' + rowId + '\'';
			    }
			  },
			  {name:'pcDjxh', index:'pcDjxh',hidden:true, width:'80', align:'center'},
			  {name:'pcdh', index:'pcdh', width:'65', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcdhClgz' + rowId + '\'';
			    }
			  },
			  {name:'pcfsMc', index:'pcfsMc', width:'75', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcfsMcClgz' + rowId + '\'';
			    }
			  },
			  {name:'zcfsMc', index:'zcfsMc', width:'75', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'zcfsMcClgz' + rowId + '\'';
			    }
			  },
			  {name:'cyrClhm', index:'cyrClhm', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'cyrClhmClgz' + rowId + '\'';
			    }
			  },
			  {name:'cyrGchm', index:'cyrGchm', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'cyrGchmClgz' + rowId + '\'';
			    }
			  },
			  {name:'cyrSjxm', index:'cyrSjxm', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'cyrSjxmClgz' + rowId + '\'';
			    }
			  },
			  {name:'pcrq', index:'pcrq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcrqClgz' + rowId + '\'';
				    }
			   },
			  {name:'rq', index:'rqq', width:'125', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'}},
			  {name:'sm', index:'sm', width:'120', align:'center'},
		      {name:'xxdz', index:'xxdz', width:'80', align:'center'},
		      {name:'yjDdrq', index:'yjDdrq', width:'100', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      {name:'szqyXzqhMc', index:'szqyXzqhMc', width:'80', align:'center'},
		      {name:'djJgmc', index:'djJgmc', width:'80', align:'center'}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: -1,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[-1],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'DD_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
		    sortorder: 'DESC',				//Ĭ��������
		    viewrecords: true,					//�����Ƿ�Ҫ��ʾ�ܼ�¼��
		    gridview: false,					//Ĭ��false ����һ�����ݺ���ӵ�grid�У������Ϊtrue���ǽ������������ݶ�������ɺ�����ӵ�grid�У���treeGrid, subGrid, or afterInsertRow ������	
		    jsonReader: {     
	     	 	root: 	 "domain.clgzList",   				// �����У�Ĭ��Ϊ��rows��
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
		  jQuery("#clgzxxList").jqGrid('navGrid','#pager',
		 		 {edit:false,add:false,del:false}
		  );
		  
	}
	
	function myClgzxxGridComplete() {
        var graduateIds = $("#clgzxxList").jqGrid('getDataIDs');
		
        var heightT = getAutoGridHeight(graduateIds.length);
	    $("#clgzxxList").setGridHeight(heightT);
        
        var gridName = "clgzxxList";
 	    var aClgz = ['pageXhClgz','pcdhClgz','pcfsMcClgz','zcfsMcClgz','cyrClhmClgz','cyrGchmClgz','cyrSjxmClgz','pcrqClgz'];
	    var cellNames = ['pageXh','pcdh','pcfsMc','zcfsMc','cyrClhm','cyrGchm','cyrSjxm','pcrq'];
  		
        Merger(gridName, 'pcDjxh', aClgz,cellNames);
 }
	
     /**************************************��ҳ����****************************************/
</script>
</head>
<body>
	<s:hidden name="domain.xtcs20201" />
		<!-- ��ҳ��� id����ΪclgzxxList -->
		<table id="clgzxxList"><tr><td/></tr></table> 
		<!-- ��ҳ���� -->
		<%@include file="/common/message.jsp" %>
</body>
</html>
