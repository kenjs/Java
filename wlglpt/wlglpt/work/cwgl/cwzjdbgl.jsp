<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>����-�ʽ��������</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>

<script type="text/javascript">
	$(document).ready(function(){
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//������ť�¼�
			$("#addBtn").click(function(){
				onUpdate('');
			});
			//��ʼ�����
			initDataGrid();
			
			onRefresh();			

	});

    function onUpdate(zjdbDjxh){
    	var url = jcontextPath+"/cwgl/cwzjdbgl!initMx.action?domain.zjdbDjxh="+zjdbDjxh;
    	window.showModalDialog(url,window,"dialogHeight:300px;dialogWidth:420px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
    	onRefresh();
    }
    
    var keyValue = "";
	function onDelete( zjdbDjxh){
		keyValue = zjdbDjxh;
		showConfirm("ȷ��Ҫ����ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.zjdbDjxh":keyValue};
		 var url = jcontextPath+"/cwgl/cwzjdbgl!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("�����ɹ���");
        onRefresh();
	}	
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var drDwDjxh = trim($("#mainForm_domain_drDwDjxh").val()); 
		var rqQ = trim($("#mainForm_domain_rqQ").val()); 
		var rqZ = trim($("#mainForm_domain_rqZ").val()); 
		//����������
		var url = jcontextPath+"/cwgl/cwzjdbgl!query";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.drDwDjxh":encodeURI(drDwDjxh),
				      "domain.rqQ":rqQ,"domain.rqZ":rqZ}								//����Ĳ�����json��ʽ
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
		    colNames:['����','�������', 'ʵ�ʵ������','ʵ�ʵ�������','���ս��','��������','ת����λ','ת�뵥λ','�ƻ�����',
				     '��ע˵��','������',
				     '��������','�޸���','�޸�����','����'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'40', align:'center'},
		      {name:'je', index:'je', width:'80', align:'center'},
		      {name:'yfJe', index:'yfJe', width:'80', align:'center'},
		      {name:'dbrq', index:'dbrq', width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      {name:'ysJe', index:'ysJe', width:'80', align:'center'},
		      {name:'jsrq', index:'jsrq', width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      {name:'dcDwMc', index:'zcDwMc', width:'100', align:'center'}, 
		      {name:'drDwMc', index:'zrDwMc', width:'100', align:'center'}, 
		       

		      {name:'rq', index:'rq', width:'80', align:'center'}, 
		      {name:'bz', index:'bzsm', width:'180', align:'center'}, 
		      
		      {name:'cjrMc', index:'cjrMc', width:'80', align:'center'}, 
		      {name:'cjrq', index:'cjrq', width:'80', align:'center'}, 
		      {name:'xgrMc', index:'xgrMc', width:'80', align:'center'}, 
		      {name:'xgrq', index:'xgrq', width:'80', align:'center'}, 
		      {name:'zjdbDjxh', index:'zjdbDjxh', width:'0', align:'center', hidden:true}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'ZJDB_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/cwgl/cwzjdbgl!download.action");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"zjdbDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var link = "<a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">����</font></a>";
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="cwzjdbgl!query" namespace="" method="post" id="mainForm" name="mainForm">
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">������</li>
		    <li><a href="#" id="queryBtn" class="licon04">�� ��</a></li>
		    <li><a href="#" id="addBtn" class="licon01">����</a></li>
		    <li><a href="#" id="closeBtn" class="licon03">�ر�</a></li>
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
	   <table width="95%" border="0" cellspacing="0" cellpadding="0">
	        <tr>
	          <td width="12%" align="right">ת�뵥λ��</td>
	          <td width="21%"><sys:fgsList myId="mainForm_domain_drDwDjxh" myName="domain.drDwDjxh" myClass="select" contaisDq="N" contaisQxz="true"></sys:fgsList></td>
	          <td align="right" width="15%">�ƻ�������ֹ��</td>
        			<td width="25%">
        				<sys:dateFirstDMonth myName="domain.rqQ" myId="mainForm_domain_rqQ" myClass="ymdate" />
         					��
         				<sys:dateCurrentDayTag myName="domain.rqZ" myId="mainForm_domain_rqZ" myClass="ymdate" />
        			</td>
	          <td width="27%"></td>
	        </tr>
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
