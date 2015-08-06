<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�칫-������</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>

<script type="text/javascript">
	$(document).ready(function(){
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				var jgbm=$("#mainForm_domain_sjJgbm").val();
				if(jgbm == ''||jgbm==undefined){
					showError("��ѡ��˾��");
					return false;
				}
				onRefresh();
			});
			
			//������ť�¼�
			$("#addBtn").click(function(){
				//popwindow(jcontextPath+"/bggzr!initMx");
			});
			//��ʼ�����
			initDataGrid();
	});

    
    var keyValue = "";
    var keyRq ="";
    var keyGzrDm ="";
	function onUpdate(jgbm,rq,gzdm){
		keyValue = jgbm;
		keyRq = rq;
		keyGzrDm = gzdm;
		showConfirm("ȷ��Ҫ����ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.jgbm":keyValue,"domain.rq":keyRq,"domain.gzrDm":keyGzrDm};
		 var url = jcontextPath+"/bggl/bggzr!updateGzrByJgbm";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("�����ɹ���");
        onRefresh();
	}	
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		 var jgbm=$("#mainForm_domain_sjJgbm").val();
		 var start=$("#startTime").val();
		 var end=$("#endTime").val();
		//����������
		var url = jcontextPath+"/bggl/bggzr!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.jgbm":jgbm,"domain.startTime":start,"domain.endTime":end}								//����Ĳ�����json��ʽ
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
		    colNames:['����','����','�ڼ���','���ڼ�','ID'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'100', align:'center'},
		      {name:'rq', index:'rq', width:'150', align:'center'}, 
		      {name:'gzrDm', index:'gzrDm', width:'150', align:'center'}, 
		      {name:'weekdayDm', index:'weekdayDm', width:'150', align:'center'},
		      {name:'jgbm', index:'jgbm', width:'0', align:'center',hidden:true}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'rq',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		  
	  	  // add custom button to export the data to excel
		  $("#dataList").jqGrid('navButtonAdd','#pager',{
		       caption:"", 
		       onClickButton : function () { 
		       	   $("#mainForm").attr("action",jcontextPath+"/bggl/bggzr!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"jgbm"); 
                var rq = jQuery("#dataList").jqGrid('getCell', cl,"rq"); 
                var gzrDm = jQuery("#dataList").jqGrid('getCell', cl,"gzrDm"); 
                	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var tag;	  
                if(gzrDm == '�ڼ���'){
                	tag="<font color=\"red\">"+gzrDm+"</font>"
                }	  
                else{
                	tag=gzrDm
                }
                var link = "<a href=\"javascript:onUpdate('"+val+"','"+rq+"','"+gzrDm+"')\"><font color=\"blue\">����</font></a>"
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
                 $("#dataList").jqGrid('setRowData', cl, { 'gzrDm': tag }); 
            }
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="bggzr!query" namespace="" method="post" id="mainForm" name="mainForm">
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">������</li>
		    <li><a href="#" id="queryBtn" class="licon04">�� ��</a></li>
		    <li><a href="#" id="addBtn" class="licon01">�� ��</a></li>
		    <li><a href="#" id="closeBtn" class="licon03">�� ��</a></li>
	  	</ul>
	
	    <ul class="rcont">
		    <li class="ricon02" onclick="slideToggle('syquery')">��ʾ/���ز�ѯ����</li>
		    <li class="ricon03">����</li>
	  	</ul>
	</div> 
	<div class="right_cont">
		  <div id="divQuery">
			<fieldset>
				<legend>��ѯ����</legend>
		  			 <table width="99%" border="0" align="left" cellspacing="0" cellpadding="0">
		       			 <tr>
		         			 <td width="12%" align="right">�ܹ�˾/�ֹ�˾��</td>
		         			  <td width="21%"><sys:gsList myName="domain.jgbm"
				myId="mainForm_domain_sjJgbm" contaisQxz="false" myClass="select" /></td>
						       <td width="8%" align="center">ʱ�䣺</td>
						       <td width="27%"><input class="ymdate" id="startTime" name="domain.startTime" type="text" value="<s:property value="domain.nowDate"/>" />��<input class="ymdate" id="endTime" type="text" name="domain.endTime" value="<s:property value="domain.nowDate"/>" /></td>
						       <td colspan="2"></td>
						 <tr>
		  			 </table>
			</fieldset>
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
