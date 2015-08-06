<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>��ҵ-��������-�����</title>
<%@ include file="/common/meta.jsp"%>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript">
	$(document).ready(function(){
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//��ʼ�����
			initDataGrid();
	});
    
    var keyValue = "";
	function pass( ggDjxh){
		//alert(ggDjxh);
		keyValue = ggDjxh;
		showConfirm("��ȷ��Ҫ���ͨ������������������","passCallBack");
	}	
	function passCallBack(){
		 var jsonObj = {"domain.ggDjxh":keyValue};
		 var url = jcontextPath+"/xtgl/qyswdndsh!pass";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function dispass( ggDjxh){
		keyValue = ggDjxh;
		showConfirm("��ȷ��Ҫ��˲�ͨ������������������","dispassCallBack");
	}	
	function dispassCallBack(){
		 var jsonObj = {"domain.ggDjxh":keyValue};
		 var url = jcontextPath+"/xtgl/qyswdndsh!dispass";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("��˳ɹ�!");
        onRefresh();
	}	
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		  
		//����������
		var url = jcontextPath+"/xtgl/qyswdndsh!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{}								//����Ĳ�����json��ʽ
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
		    colNames:['����','�����Ǽ����','������','��������','����Ա����','��ע'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'80', align:'center'},
			  {name:'ggDjxh', index:'ggDjxh', width:'100', align:'center',hidden:true}, 
			  {name:'sqrMc', index:'sqrMc', width:'160', align:'center'}, 
			  {name:'sqrq', index:'sqrq', width:'170', align:'center'}, 
		      {name:'czyMc', index:'czyMc', width:'170', align:'center'}, 
		      {name:'bzsm', index:'bzsm', width:'270', align:'center'}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'SQRQ',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
		    sortorder: 'DESC',				//Ĭ��������
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
		  
	  	  // add custom button to export the data to excel
		  $("#dataList").jqGrid('navButtonAdd','#pager',{
		       caption:"", 
		       onClickButton : function () { 
		       	   $("#mainForm").attr("action",jcontextPath+"/xtgl/qyswdndsh!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е�����
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"ggDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var link = "<a href=\"javascript:pass('"+val+"')\"><font color=\"blue\">ͨ��</font></a>"
                + " <a href=\"javascript:dispass('"+val+"')\"><font color=\"blue\">��ͨ��</font></a>";
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
     /**************************************��ҳ����****************************************/


</script>
</head>

<body>
	<s:form action="qyswdndsh!query" namespace="/xtgl" method="post" id="mainForm" name="mainForm">
		<!-- ������ -->
		<div class="right_btnbg">
  			<ul class="lcont">
    			<li class="no">������</li>
    			<li><a href="#" id="queryBtn" class="licon04">�� ��</a></li>
    			<li><a href="#" id="closeBtn" class="licon03">�� ��</a></li>
  			</ul>
  			<ul class="rcont">
    			<li class="ricon03">����</li>
  			</ul>
		</div>
		
		<div class="right_cont">  
  			<!-- ��ҳ��� id����ΪdataList -->
  			<table id="dataList"><tr><td/></tr></table> 
  			<!-- ��ҳ���� -->
  			<div id="pager"></div>
  			<%@include file="/common/message.jsp" %>
		</div>
	</s:form>
</body>
</html>
