<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>��ˮ��¼��ϸ</title>
   
<style type="text/css">
html,body {background:none;}
.tydSel {width:68px;}
</style>
<script type="text/javascript">
    $(document).ready(function(){		
		$("#closeBtn").click(function(){
			window.close();
		})
		initDataGrid()
		onRefresh();
	});
  /**************************************��ҳ��ʼ****************************************/
  //ˢ�±��
	function onRefresh(){
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var rq = $("#mainForm_domain_rq").val();
		var bz = $("#mainForm_domain_bz").val();
		//alert(ssJgbm+rq+bz)
  		//����������
		var url = jcontextPath+"/cwzjrb!query.action";   
		 $("#dataList").jqGrid("setGridParam",{ 
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.rq":rq,"domain.bz":bz}								//����Ĳ�����json��ʽ
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
			width:pageWidth()-25,  
			height:pageHeight()-120,
		    gridComplete: myGridComplete,		//����������¼�
		    shrinkToFit:false, 
		    colNames:['����Ǽ����','���','��־','��֧�Ǽ�����','���','������','��������','����','�˺�','˵��','����'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'cwbdDjXh', index:'cwbdDjXh',hidden:true, align:'center'},
		      {name:'zcflMc', index:'zcflMc', width:'60', align:'center'},
		      {name:'bzmc', index:'bzmc', width:'60', align:'center'}, 	
		      {name:'szDjrq', index:'szDjrq', width:'100', align:'center'},
		      {name:'bdje', index:'bdje', width:'80', align:'center'},
		      {name:'jbrCzyMc', index:'jbrCzyMc', width:'80', align:'center'},  
		      {name:'yhmc', index:'yhmc', width:'100', align:'center'}, 	      
		      {name:'hm', index:'hm', width:'80', align:'center'},			  
			  {name:'zh', index:'zh', width:'80', align:'center'}, 	  
			  {name:'sm', index:'sm', width:'306', align:'left'},
			  {name:'rq', index:'rq', width:'70', align:'center'},
		     ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,						
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'cwbdDjXh',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		  
	  	  /*// add custom button to export the data to excel
		  $("#dataList").jqGrid('navButtonAdd','#pager',{
		       caption:"", 
		       onClickButton : function () { 
		       	   $("#mainForm").attr("action",jcontextPath+"/cwhbzclsjl!download");
				   $("#mainForm").submit();
		       } 
		 });*/
		 
		    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����������
			function myGridComplete() {
		            var graduateIds = $("#dataList").jqGrid('getDataIDs');
		            for (var i = 0; i < graduateIds.length; i++) {
		                var cl = graduateIds[i];
		                var val = jQuery("#dataList").jqGrid('getCell', cl,"cwbdDjXh"); 	  //��ȡ��ǰ��Ԫ������Ĳ������ 
		                var link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">��ϸ</font></a>";
		                //$("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
		            }
		     }
	}
</script>
</head>

<body>
<%try{ %>
   <s:form action="cwzjrb!querySzMx" namespace="/cwgl" method="post" id="mainForm" name="mainForm">
   <s:hidden name="domain.ssJgbm"></s:hidden>
   <s:hidden name="domain.bz"></s:hidden>
   <s:hidden name="domain.rq"></s:hidden>
   	<div class="pop_contc" style="height:40px; overflow:auto;">
			<div class="pop_btn">
			    <button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
		    </div>
	 </div>
	<div class="pop_contc" style="height:740px; overflow:auto;">
	 <!-- ��ҳ��� id����ΪdataList -->
		<table id="dataList"><tr><td /></tr></table>
		<!-- ��ҳ���� -->
		<div id="pager"></div>
    </div>
   <%@include file="/common/message.jsp" %>
   </s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
