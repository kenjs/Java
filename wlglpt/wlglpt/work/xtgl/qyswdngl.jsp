<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>�������Թ���</title>
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
    function onDelete(ggDjxh){
		keyValue = ggDjxh;
		showConfirm("��ȷ��Ҫɾ��������������","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.ggDjxh":keyValue};
		 var url = jcontextPath+"/xtgl/swdngl!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}
	
	function startUse(ggDjxh){
		keyValue = ggDjxh;
		showConfirm("��ȷ��Ҫ���ø���������������","startCallBack");
	}	
	function startCallBack(){
		 var jsonObj = {"domain.ggDjxh":keyValue};
		 var url = jcontextPath+"/xtgl/swdngl!startUse";   
         ajaxCommon(url,jsonObj , "startSuccess");  
	}
	
	function startSuccess(){     
        showAlert("���óɹ�!");
        onRefresh();
	}	
		
	function stopUse(ggDjxh){
		//alert(ggDjxh);
		keyValue = ggDjxh;
		showConfirm("��ȷ��Ҫͣ�ø���������������","stopCallBack");
	}	
	function stopCallBack(){
		 var jsonObj = {"domain.ggDjxh":keyValue};
		 var url = jcontextPath+"/xtgl/swdngl!stopUse";   
         ajaxCommon(url,jsonObj , "stopSuccess");  
	}
	
	function stopSuccess(){     
        showAlert("ͣ�óɹ�!");
        onRefresh();
	}	
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		  
		//����������
		var url = jcontextPath+"/xtgl/swdngl!query.action";   
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
		    colNames:['����','�����Ǽ����','���ñ�־','״̬','������','��������','�����','�������','MAC��ַ','��ע'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'80', align:'center'},
			  {name:'ggDjxh', index:'ggDjxh', width:'0', align:'center',hidden:true}, 
			  {name:'qybz', index:'qybz', width:'0', align:'center',hidden:true}, 
			  {name:'status', index:'', width:'40', align:'center'},
			  {name:'sqrMc', index:'sqrMc', width:'80', align:'center'}, 
			  {name:'sqrq', index:'sqrq', width:'80', align:'center'}, 
		      {name:'shrMc', index:'shrMc', width:'80', align:'center'},
		      {name:'shrq', index:'shrq', width:'80', align:'center'},
		      {name:'mac', index:'mac', width:'160', align:'center'},
		      {name:'bzsm', index:'bzsm', width:'280', align:'center'}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'GG_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/xtgl/swdngl!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ��ɾ������������/ͣ�á�������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"ggDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var valu = jQuery("#dataList").jqGrid('getCell', cl,"qybz"); 	  //��ȡ��ǰ��Ԫ����������ñ�־
                if(valu == "Y") {
                	var link1 = "<a href=\"javascript:stopUse('"+val+"')\"><font color=\"blue\">ͣ��</font></a>";
                	var link2 = "<font color=\"black\">����</font>";
                } else {
                	var link1 = "<a href=\"javascript:startUse('"+val+"')\"><font color=\"blue\">����</font></a>";
                	var link2 = "<font color=\"red\">ͣ��</font>";
                }
                var link = "<a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">ɾ��</font></a>" + " " +link1;
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
                $("#dataList").jqGrid('setRowData', cl, { 'status': link2 }); 
            }
     }
     /**************************************��ҳ����****************************************/


</script>
</head>

<body>
	<s:form action="swdngl!query" namespace="/xtgl" method="post" id="mainForm" name="mainForm">
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
