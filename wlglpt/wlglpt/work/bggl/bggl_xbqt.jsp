<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"
	pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�칫-�ϰ�ǩ��</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>

<script type="text/javascript">
	$(document).ready(function(){
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
					var start=$("#startTime").val();
					var end=$("#endTime").val();
					onRefresh();
					
			});
			
			$("#addBtn").click(function(){
 				showConfirm("��ȷ��Ҫǩ��ô��","YesQd")					
			});
			//��ʼ�����
			initDataGrid();
	 });
	
	function YesQd(){
		var sj=$("#mainForm_domain_xbSj").val();
		var url=jcontextPath+"/bggl/bgxbqt!save";
		var obj={"domain.xbSj":sj}
		ajaxCommon(url,obj,"QdSuccess");
	}
   
    function QdSuccess(){
		showAlert("ǩ�˳ɹ���");
	}	
    
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		 //����������
			var start=$("#startTime").val();
			var end=$("#endTime").val();
			var url=jcontextPath+"/bggl/bgxbqt!query.action"  
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.startTime":start,"domain.endTime":end}								//����Ĳ�����json��ʽ
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
			width:pageWidth()-10,  
			height:pageTableHeight()-90,	
		    gridComplete: myGridComplete,		//����������¼�
		    shrinkToFit:false, 
		    colNames:['���','ID','����','Ӧǩ��ʱ��','ʵ��ǩ��ʱ��',
		    		  '�Ƿ�����'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'85', align:'center'},
			  {name:'bgDjxh', index:'bgDjxh', width:'0',hidden:true, align:'center'},
		      {name:'rq', index:'rq', width:'140', align:'center'}, 
		 	  {name:'yqdSj', index:'yqdSj', width:'160', align:'center'}, 
		      {name:'sjQdqtSj', index:'sjQdqtSj', width:'160', align:'center'}, 
		      {name:'tagg', index:'tagg', width:'100', align:'center'}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'bgDjxh',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	    $("#mainForm").attr("action",jcontextPath+"/xtgl/bgxbqt!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            var j=1;
            for (var i = 0; i < graduateIds.length; i++) {
           		 var cl = graduateIds[i];
           		 var tagg = jQuery("#dataList").jqGrid('getCell', cl,"tagg"); 
           		 var riqi = jQuery("#dataList").jqGrid('getCell', cl,"rq");
           		 var zt;
           		 if(tagg == '����'){
           		 	zt=" <font color=\"red\">"+riqi+"</font>"
           		 }
           		 else{
           		 	zt=riqi;
           		 }
           		$("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol':j });
                $("#dataList").jqGrid('setRowData', cl, { 'rq':zt });
                j++; 
             }
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="bgsbqd!query" namespace="" method="post" id="mainForm"
	name="mainForm">
	<div class="right_btnbg">
	<ul class="lcont">
		<li class="no">������</li>
		<li><a href="#" id="queryBtn" class="licon04">�� ��</a></li>
		<li><a href="#" id="addBtn" class="licon01">ǩ ��</a></li>
		<li><a href="#" id="closeBtn" class="licon03">�� ��</a></li>
	</ul>
	<ul class="rcont">
		<li class="ricon02" onclick="slideToggle('syquery')">��ʾ/���ز�ѯ����</li>
		 <li class="ricon03">����</li>
	</ul>
	</div>
	<div id="divQuery"><s:hidden name="domain.xbSj"></s:hidden>
	<div id="divQuery">
	<fieldset>
	<table width="60%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="12%" align="left">&nbsp;&nbsp;&nbsp;����ǩ��״̬��</td>
			<td width="8%" align="center">Ӧǩ��ʱ�䣺</td>
			<td width="8%" align="left"><s:property value="domain.xbSj" /></td>
			<td width="8%" align="center">ʵ��ǩ��ʱ�䣺</td>
			<td width="8%" align="left"><s:property value="domain.sjXbSj" /></td>
		<tr>
	</table>
	<legend>��ѯ����</legend>

	<table width="95%" border="0" align="center" cellspacing="0"
		cellpadding="0">
		<tr>
			<td width="15%" align="right">ʱ�䣺</td>
			<td width="35%"><input class="ymdate" id="startTime"
				name="domain.startTime" type="text"
				value="<s:property value="domain.oldDate"/>" /> ��<input
				class="ymdate" id="endTime" name="domain.endTime" type="text"
				value="<s:property value="domain.newDate"/>" /></td>
		<tr>
	</table>
	</fieldset>
	</div>
	</div>


	<div class="right_cont"><!-- ��ҳ��� id����ΪdataList -->
	<table id="dataList">
		<tr>
			<td />
		</tr>
	</table>
	<!-- ��ҳ���� -->
	<div id="pager"></div>
	<%@include file="/common/message.jsp"%></div>
</s:form>
</body>
</html>
