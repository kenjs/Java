<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�ռ���</title>
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

    function onUpdate(gzlxXh,ckbz){
    
        //�޸Ĳ鿴��־
        if(ckbz == "N"){
	    	var url = jcontextPath+"/bggl/bggzlx!updateCkbz";  
		    var jsonObj = {"domain.gzlxXh":gzlxXh};   			
			ajaxCommon(url,jsonObj,"doUpdate",false);
		}
 
    	var url = jcontextPath+"/bggl/bggzlx!initMx.action?domain.gzlxXh="+gzlxXh;
		window.showModalDialog(url,window,"dialogHeight:580px;dialogWidth:760px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
		onRefresh();
    }
    
    function doUpdate(){
    }
    
    var keyValue = "";
	function onDelete( gzlxXh){
		keyValue = gzlxXh;
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.gzlxXh":keyValue};
		 var url = jcontextPath+"/bggl/bggzlx!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}	
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		 var rqQ = $("#rqQId").val(); 
		 var rqZ = $("#rqZId").val(); 
		//����������
		var url = jcontextPath+"/bggl/bggzlx!queryForSjx.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.rqQ":rqQ,"domain.rqZ":rqZ}								//����Ĳ�����json��ʽ
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
		    colNames:['����','�鿴��־','�鿴����','����','������','����ʱ��','����',''],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'50', align:'center'},
			  {name:'ckbzMc', index:'ckbzMc', width:'60', align:'center'}, 
		      {name:'ckrq', index:'ckrq', width:'70', align:'center'}, 
		      {name:'zt', index:'zt', width:'400', align:'left'}, 
		      {name:'xgrMc', index:'xgrMc', width:'70', align:'center'}, 
		      {name:'xgrq', index:'xgrq', width:'70', align:'center'}, 
		      {name:'gzlxXh', index:'gzlxXh', width:'0', align:'center', hidden:true},
		      {name:'ckbz', index:'ckbz', width:'0', align:'center', hidden:true}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'A.GZLX_XH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/bggl/bggzlx!downloadForSjx");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"gzlxXh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var ckbz = jQuery("#dataList").jqGrid('getCell', cl,"ckbz"); 	  //��ȡ��ǰ��Ԫ������Ĳ鿴��־ 
                //CKBZ=��Y������ʾ�Ѳ鿴����ɫ����������ʾΪδ�鿴����ɫ��
                var color = "blue";
                if(ckbz =="Y"){
                	color = "black";
                }
                var link = "<a href=\"javascript:onUpdate('"+val+"','"+ckbz+"')\"><font color=\""+color+"\">�鿴</font></a>";
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="bggzlx!queryForSjx" namespace="/bggl" method="post" id="mainForm" name="mainForm">
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">������</li>
		    <li><a href="#" id="queryBtn" class="licon04">�� ��</a></li>
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
		   <table width="95%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		          <td width="15%" align="right">�������ڣ�</td>
		          <td width="35%"><s:textfield name="domain.rqQ" cssClass="ymdate" readonly="true" id="rqQId"></s:textfield> <strong>~</strong> <s:textfield name="domain.rqZ" cssClass="ymdate" readonly="true" id="rqZId"></s:textfield> </td>
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
