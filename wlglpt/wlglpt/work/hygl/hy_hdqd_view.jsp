<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<html>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>�ص��嵥���</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript">
    $(document).ready(function(){
    	$("#closeBtn").click(function(){
    		window.close();
    	});
		initDataGrid();
		onRefresh();
	});	
  /**************************************��ҳ��ʼ****************************************/
  //ˢ�±��
	function onRefresh(){
		var hdqdDjxh = $("#mainForm_domain_hdqdDjxh").val();
  		//����������
		var url = jcontextPath+"/hypchwxxhdqd!queryHdByQd.action";   
		 $("#dataList").jqGrid("setGridParam",{ 
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.hdqdDjxh":hdqdDjxh}								//����Ĳ�����json��ʽ
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
		    rownumbers : true,					//�����
			width:pageWidth()-10,  
			height:pageTableHeight()-90,	
		    gridComplete: myGridComplete,		//����������¼�
		    shrinkToFit:false,
		    colNames:['<input title="ѡ��/ȡ��ѡ��" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />', '�ص��Ǽ����','�ɳ��Ǽ����','δ�����Ǽ����','�����Ǽ����','������ϸ���',
				     '�ص����','ʵװ����','ʵװ����','ʵװ���','�ص���������','Ҫ�󵽴�����','�ջ���ʽ����',
				     'ʵװ_��������',
				     '�ɳ�����','�ɳ�����','�ɳ���ʽ', 'װ����ʽ','��������','�ҳ�����',
				     '˾��','�˷Ѻϼ�','Ԥ���˷�', '��ע','�ɳ���','�ɳ�����','ҵ��˾'
				     
				    ],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'40', align:'center'},
		      {name:'hdDjxh', index:'hdDjxh', width:'100', align:'center',hidden:true}, 
		      {name:'pcDjxh', index:'pcDjxh', width:'100', align:'center',hidden:true}, 
		      {name:'wfhDjxh', index:'wfhDjxh', width:'100', align:'center',hidden:true}, 
		      {name:'ddDjxh', index:'ddDjxh', width:'100', align:'center',hidden:true}, 
		      {name:'xh', index:'xh', width:'100', align:'center',hidden:true},
		      {name:'hdbh', index:'hdbh', width:'60', align:'center'}, 
		      
		      {name:'szHwSl', index:'szHwSl', width:'60', align:'center'}, 
		      {name:'szHwZl', index:'szHwZl', width:'60', align:'center'}, 
		      {name:'szHwTj', index:'szHwTj', width:'60', align:'center'}, 
		      {name:'hdjsrq', index:'hdjsrq', width:'80', align:'center'},
		      {name:'yqDdrq', index:'yqDdrq', width:'80', align:'center'},
		      {name:'shfsDm', index:'shfsDm', width:'100', align:'center',hidden:true}, 
		      {name:'szJsSl', index:'szJsSl', width:'100', align:'center',hidden:true},
		      {name:'pcdh', index:'pcdh', width:'70', align:'center'},
		      
		      
		      {name:'pcrq', index:'pcrq', width:'80', align:'center'},
		      {name:'pcfsMc', index:'pcfsMc', width:'60', align:'center'},
		      {name:'zcfsMc', index:'zcfsMc', width:'60', align:'center'},
		      {name:'cyrClhm', index:'cyrClhm', width:'70', align:'center'},
		      {name:'cyrGchm', index:'cyrGchm', width:'70', align:'center'},
		      
		      {name:'cyrSjxm', index:'cyrSjxm', width:'100', align:'center'},
		      {name:'yfHj', index:'yfHj', width:'100', align:'center'},
		      {name:'yfYfyf', index:'yfYfyf', width:'100', align:'center'},
		      {name:'bz', index:'bz', width:'150', align:'center'},
		      {name:'pcrMc', index:'pcrMc', width:'100', align:'center'},
		      {name:'pcJgmc', index:'pcJgmc', width:'100', align:'center'},
		      {name:'ssJgmc', index:'ssJgmc', width:'100', align:'center'}	      		      
		      
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,						
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'HD_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
		    sortorder: 'asc',				//Ĭ��������
		    viewrecords: true,					//�����Ƿ�Ҫ��ʾ�ܼ�¼��
		    gridview: false,					//Ĭ��false ����һ�����ݺ���ӵ�grid�У������Ϊtrue���ǽ������������ݶ�������ɺ�����ӵ�grid�У���treeGrid, subGrid, or afterInsertRow ������	
		    jsonReader: {     
	     	 	root: 	 "domain.dataList",   				// �����У�Ĭ��Ϊ��rows��
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
		 
		    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����������
			function myGridComplete() {
		            var graduateIds = $("#dataList").jqGrid('getDataIDs');
		            for (var i = 0; i < graduateIds.length; i++) {
		                var cl = graduateIds[i];
		                var val = jQuery("#dataList").jqGrid('getCell', cl,"hdDjxh"); 	  //��ȡ��ǰ��Ԫ������Ĳ������ 
		                var link = '<input type="checkbox" name="xhs" value="'+val+'" />';
		                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link });
		                var pcDjxh = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh");
		                var pcdh = jQuery("#dataList").jqGrid('getCell', cl,"pcdh");
                		var strPc="<a href=\"javascript:onViewPc("+pcDjxh+")\"><font color=\"blue\">"+pcdh+"</font></a>";
                		$("#dataList").jqGrid('setRowData', cl, { 'pcdh': strPc });
		            }
		     }
	}
</script>
</head>
<body>
<s:form action="hypchwxxhdqd!queryQd" theme="simple" namespace="cwgl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.hdqdDjxh"></s:hidden>
		<div class="right_cont">
		<div id="divQuery" align="center">
			<button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
		</div>
	
		<!-- ��ҳ��� id����ΪdataList -->
		<table id="dataList"><tr><td /></tr></table>
		<!-- ��ҳ���� -->
		<div id="pager" style="display: none;"></div>
	</div>
</s:form>
</body>
