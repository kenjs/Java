<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>����-�˷�֧����Ϣ</title>
<style type="text/css">

</style>
<script type="text/javascript">
	$(document).ready(function(){
		//$(window).unbind("resize");
		//��ʼ�����
		initYfzfxxDataGrid();
		onYfzfxxRefresh();
		
	});
	
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onYfzfxxRefresh(){
		var xtcs20201 = $("#mainForm_domain_xtcs20201").val();
		if ("Y" == xtcs20201) {
			$("#yfList").jqGrid('showCol',["wsspztMc"]);
		}
		var ddDjxh = $("#mainForm_domain_ddDjxh").val();
		var pcDjxh = $("#mainForm_domain_pcDjxh").val();
		
		//����������
		var url = jcontextPath+"/jcgl/jctydgl!queryJcYfZfxx.action";   
		 $("#yfList").jqGrid("setGridParam",{
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
	function initYfzfxxDataGrid(){ 
		  $("#yfList").jqGrid({
		    url:"",
		    datatype: 'local',
		    mtype: 'POST',
		    rownumbers : false,					//�����
			width:pageWidth()-10, 
			height:pageTableHeight()-90,	
		    gridComplete: myYfzfxxGridComplete,		//����������¼�
		    shrinkToFit:false, 
		    colNames:['���', '�ɳ��Ǽ����','�ɳ�����','״̬', '���㷽', '����', '���', 
		              '�Ѹ����', 'δ�����','��������','��������', '���',
		              '��Ŀ','��Դ', '�տ', '���', '֧����ʽ','�ʲ�����','������','��������','�˺�','��ע',
		             '˵��','�Ǽǲ���','������λ'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[
			  {name:'pageXh', index:'pageXh', sortable:false, width:'30', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pageXhYfzfxx' + rowId + '\''; 
			    }
			  },
			  {name:'yfDjxh', index:'yfDjxh',hidden:true, width:'80', align:'center'},
			  {name:'pcdh', index:'pcdh', width:'65', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcdhYfzfxx' + rowId + '\'';
			    }
			  },
			  {name:'yfztMc', index:'yfztMc', width:'30', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'yfztMc' + rowId + '\'';
			    }
			  },
			  {name:'yfjsfMc', index:'yfjsfMc', width:'50', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'yfjsfMc' + rowId + '\'';
			    }
			  },
			  {name:'yfjsfDjMc', index:'yfjsfDjMc', width:'100', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'yfjsfDjMc' + rowId + '\'';
			    }
			  },
			  {name:'jsfJe', index:'jsfJe', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'jsfJe' + rowId + '\'';
			    }
			  },
			  {name:'yisfJe', index:'yisfJe', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'yisfJe' + rowId + '\'';
			    }
			  },
			  {name:'wsfJe', index:'wsfJe', width:'70', align:'center',
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'wsfJe' + rowId + '\'';
				    }
			   },
			   {name:'rq', index:'rq', width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
			   {name:'csRq', index:'csRq', width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
			  {name:'kmdlMc', index:'kmdlMc', width:'80', align:'center'},
			  {name:'kmxlMc', index:'kmxlMc', width:'120', align:'center'},
		      {name:'ysyflyMc', index:'ysyflyMc', width:'80', align:'center'},
		      {name:'skfMc', index:'skfMc', width:'100', align:'center'},
		      {name:'je', index:'je', width:'80', align:'center'},
		      {name:'zffsMc', index:'zffsMc', width:'80', align:'center'},
		      {name:'zcflMc', index:'zcflMc', width:'80', align:'center'},
		      {name:'jbrMc', index:'jbrMc', width:'80', align:'center'},
		      
		      {name:'yhMc', index:'yhMc', width:'80', align:'center'},
		      {name:'zh', index:'zh', width:'80', align:'center'},
		      {name:'bz', index:'bz', width:'80', align:'center'},
		   
		      {name:'sm', index:'sm', width:'230', align:'center'},
		      {name:'djJgmc', index:'djJgmc', width:'80', align:'center'},
		      {name:'ssJgmc', index:'ssJgmc', width:'80', align:'center'}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: -1,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[-1],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'DD_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
		    sortorder: 'DESC',				//Ĭ��������
		    viewrecords: true,					//�����Ƿ�Ҫ��ʾ�ܼ�¼��
		    gridview: false,					//Ĭ��false ����һ�����ݺ���ӵ�grid�У������Ϊtrue���ǽ������������ݶ�������ɺ�����ӵ�grid�У���treeGrid, subGrid, or afterInsertRow ������	
		    jsonReader: {     
	     	 	root: 	 "domain.yfList",   				// �����У�Ĭ��Ϊ��rows��
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
		  jQuery("#yfList").jqGrid('navGrid','#pager',
		 		 {edit:false,add:false,del:false}
		  );
		  
	}
	
	function myYfzfxxGridComplete() {
        var graduateIds = $("#yfList").jqGrid('getDataIDs');
		
        var heightT = getAutoGridHeight(graduateIds.length);
	    $("#yfList").setGridHeight(heightT);
        
        /* for (var i = 0; i < graduateIds.length; i++) {
            var cl = graduateIds[i];
            var pcDjxh = jQuery("#yfList").jqGrid('getCell', cl,"pcDjxh"); 
            
			var link = "<a href=\"javascript:onUpdate('"+pcDjxh+"')\"><font color=\"blue\">�޸�</font></a>"
					+ "&nbsp;<a href=\"javascript:onDelete('"+pcDjxh+"')\"><font color=\"blue\">����</font></a>";
            $("#yfList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
        } */
        var gridName = "yfList";
	    var aClgz = ['pageXhYfzfxx','pcdhYfzfxx','yfztMc','yfjsfMc','yfjsfDjMc','jsfJe','yisfJe','wsfJe'];
	    var cellNames = ['pageXh','pcdh','yfztMc','yfjsfMc','yfjsfDjMc','jsfJe','yisfJe','wsfJe'];
  		
        Merger(gridName, 'yfDjxh', aClgz,cellNames);
 }
	
     /**************************************��ҳ����****************************************/
</script>
</head>
<body>
		<!-- ��ҳ��� id����ΪyfList -->
		<table id="yfList"><tr><td/></tr></table> 
		<!-- ��ҳ���� -->
		<%@include file="/common/message.jsp" %>
</body>
</html>
