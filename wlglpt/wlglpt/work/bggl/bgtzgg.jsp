<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�칫-֪ͨ����</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<%@ include file="/common/meta.jsp"%>
<script type="text/javascript">
	$(document).ready(function(){
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//������ť�¼�
			$("#addBtn").click(function(){
				var url = jcontextPath+"/bggl/bgtzgg!initMx.action";
		    	window.showModalDialog(url,window,"dialogHeight:420px;dialogWidth:760px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
		    	onRefresh();
			});
			//��ʼ�����
			initDataGrid();
						

	});

    function onUpdate(tzggXh){
    	var url = jcontextPath+"/bggl/bgtzgg!initMx.action?domain.tzggXh="+tzggXh;
		window.showModalDialog(url,window,"dialogHeight:420px;dialogWidth:760px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
		onRefresh();
    }
    
    var keyValue = "";
	function onDelete( tzggXh){
		keyValue = tzggXh;
		showConfirm("��ȷ��Ҫɾ����֪ͨ������","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.tzggXh":keyValue};
		 var url = jcontextPath+"/bggl/bgtzgg!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}	
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var zt = trim($("#mainForm_domain_zt").val()); 
		var rqQ = trim($("#mainForm_domain_rqQ").val()); 
		var rqZ = trim($("#mainForm_domain_rqZ").val()); 
  
		//����������
		var url = jcontextPath+"/bggl/bgtzgg!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{
				      "domain.zt":encodeURI(zt),"domain.rqQ":rqQ,"domain.rqZ":rqZ}								//����Ĳ�����json��ʽ
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
		    colNames:['����', '֪ͨ�������','״̬','����','������Χ',
				     '�¼�����','������','��������','�޸���',
				     '�޸�����'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
		      {name:'tzggXh', index:'tzggXh', width:'40', align:'center',hidden:true}, 
		      {name:'bcztDm', index:'bcztDm', width:'60', align:'center'}, 
		      {name:'zt', index:'zt', width:'220', align:'left'}, 
		      {name:'jgmc', index:'jgmc', width:'180', align:'left'}, 
		      
		      {name:'xjgxbz', index:'xjgxbz', width:'60', align:'center'}, 
		      {name:'cjrMc', index:'cjrMc', width:'70', align:'center'}, 
		      {name:'cjrq', index:'cjrq', width:'70', align:'center'}, 

		      {name:'xgrMc', index:'xgrMc', width:'70', align:'center'}, 
		      {name:'xgrq', index:'xgrq', width:'70', align:'center'}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'TZGG_XH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/bggl/bgtzgg!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"tzggXh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var bcztDm = jQuery("#dataList").jqGrid('getCell', cl,"bcztDm");
                var xjgxbz = jQuery("#dataList").jqGrid('getCell', cl,"xjgxbz");
                var link="";
                var zt="";
                var xjgx="";
                if("2"==bcztDm){
                	link = "<a href=\"javascript:onQurtyMx('"+val+"')\"><font color=\"blue\">�鿴</font></a>"
                	+ " <a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">ɾ��</font></a>";
                	zt="�ѷ���";
                }
                if("1"==bcztDm){
                	link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">�޸�</font></a>"
                	+ " <a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">ɾ��</font></a>";
                	zt="�ݴ�";
                }
                if("Y"==xjgxbz){
                	xjgx="��";
                }else{
                	xjgx="��";
                }
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
                $("#dataList").jqGrid('setRowData', cl, { 'bcztDm': zt }); 
                 $("#dataList").jqGrid('setRowData', cl, { 'xjgxbz': xjgx }); 
            }
     }
     /**************************************��ҳ����****************************************/

    function onQurtyMx(tzggXh){
    	var url =jcontextPath+"/bggl/bgtzgg!queryMx.action?domain.tzggXh="+tzggXh;
		window.showModalDialog(url,window,"dialogHeight:420px;dialogWidth:760px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
		onRefresh();
    }
</script>
</head>
<body>
<s:form action="bgtzgg!query" namespace="/bggl" method="post" id="mainForm" name="mainForm">
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
	   <table width="99%" border="0" cellspacing="0" cellpadding="0">
	        <tr>
	          <td width="8%" align="right">�������ڣ�</td>
	          <td width="27%">
	          	<s:textfield name="domain.rqQ" cssClass="ymdate" readonly="true"></s:textfield>
	          	<strong>~</strong>
	          	<s:textfield name="domain.rqZ" cssClass="ymdate" readonly="true"></s:textfield> 
	          </td>
	          <td width="8%" align="right">���⣺</td>
	          <td width="21%">
	          	<s:textfield name="domain.zt" cssClass="input bgstyle_optional"></s:textfield>
	          </td>
	          <td colspan="2"></td>
	        <tr>
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
