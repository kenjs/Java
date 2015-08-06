<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�ְ����û�����</title>
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
				// ����Ƿ���ѡ��ְ���
				var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
				var fbsDjxh = trim($("#mainForm_domain_fbsDjxh").val());
				if (fbsDjxh == null || fbsDjxh == ""){
					showAlert("����ѡ��ְ���");
					return;
				}
				var url = jcontextPath+"/zygl/fbsyhgl!initMx.action?domain.ssJgbm="+ssJgbm+"&domain.fbsDjxh="+fbsDjxh;
		    	window.showModalDialog(url,window,"dialogHeight:320px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		    	onRefresh();
			});
			//��ʼ�����
			initDataGrid();
			
			// ���ݹ�˾��ʼ���ְ��̵�����		
			var gs = $("#mainForm_domain_ssJgbm").val();
			if(gs != null && gs !="" && gs != undefined){
				commonInit('gs-fbs', gs,'', 'domain.fbsDjxh', 'mainForm_domain_fbsDjxh', 'Y', 'Y');
			}
	});

    function onUpdate(yhDjxh){
    	var url = jcontextPath+"/zygl/fbsyhgl!initMx.action?domain.yhDjxh="+yhDjxh;
		window.showModalDialog(url,window,"dialogHeight:320px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		onRefresh();
    }
    
    var keyValue = "";
	function onDelete( yhDjxh){
		keyValue = yhDjxh;
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.yhDjxh":keyValue};
		 var url = jcontextPath+"/zygl/fbsyhgl!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}	
    
   function stop(yhDjxh){
   		keyValue=yhDjxh
   		showConfirm("��ȷ��Ҫͣ�ø��û�ô��","stopCallBack");
   }  
    
   function stopCallBack(){
        var jsonObj = {"domain.yhDjxh":keyValue};
		var url = jcontextPath+"/zygl/fbsyhgl!saveDisable";   
        ajaxCommon(url, jsonObj, "stopSuccess");
   }  
    
   function stopSuccess(){
  	   showAlert("ͣ�óɹ���");
  	   onRefresh();
   }  
  
   function start(yhDjxh){
    	keyValue=yhDjxh
   		showConfirm("��ȷ��Ҫ���ø��û�ô��","startCallBack");
   }   
   
   function startCallBack(){
   		var jsonObj = {"domain.yhDjxh":keyValue};
		var url = jcontextPath+"/zygl/fbsyhgl!saveEnable";   
        ajaxCommon(url, jsonObj, "startSuccess");
   }   
   
   function startSuccess(){
  	   showAlert("���óɹ���");
  	   onRefresh();
  }
  
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
		var fbsDjxh = trim($("#mainForm_domain_fbsDjxh").val()); 
		if (fbsDjxh == null || fbsDjxh == ""){
			showAlert("����ѡ��ְ���");
			return;
		}
  
		//����������
		var url = jcontextPath+"/zygl/fbsyhgl!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.fbsDjxh":encodeURI(fbsDjxh)}								//����Ĳ�����json��ʽ
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
		    colNames:['����', '״̬', '����', '�˺�','��¼��֤��ʽ','������','��������',
		              '�޸���','�޸�����','����'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'100', align:'center'},
		      {name:'qybzStr', index:'qybzStr', width:'50', align:'center'}, 
		      {name:'mc', index:'mc', width:'80', align:'center'}, 
		      {name:'zh', index:'zh', width:'80', align:'center'}, 
		      {name:'dlyzfsMc', index:'dlyzfsMc', width:'90', align:'center'}, 
		      
		      {name:'cjrMc', index:'cjrMc', width:'70', align:'center'}, 
		      {name:'cjrq', index:'cjrq', width:'80', align:'center'}, 
		      {name:'xgrMc', index:'xgrMc', width:'70', align:'center'}, 
		      {name:'xgrq', index:'xgrq', width:'80', align:'center'}, 

		      {name:'yhDjxh', index:'yhDjxh', width:'0', align:'center', hidden:true}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'YH_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/zygl/fbsyhgl!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
         var graduateIds = $("#dataList").jqGrid('getDataIDs');
         for (var i = 0; i < graduateIds.length; i++) {
             var cl = graduateIds[i];
             var str = jQuery("#dataList").jqGrid('getCell', cl,"qybzStr"); 
             var val = jQuery("#dataList").jqGrid('getCell', cl,"yhDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
             var link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">�޸�</font></a>"
             + " <a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">ɾ��</font></a>";
             
             var s;
             if(str=="����"){
             	link=link+" <a href=\"javascript:stop('"+val+"')\"><font color=\"blue\">ͣ��</font></a>"
             	s=" <a  ><font color=\"black\">����</font></a>"
             }else{
             	link=link+" <a href=\"javascript:start('"+val+"')\"><font color=\"blue\">����</font></a>"
             	s=" <a  ><font color=\"red\">ͣ��</font></a>"
             }
             
             $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
             $("#dataList").jqGrid('setRowData', cl, { 'qybzStr': s }); 
         }
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="fbsyhgl!query" namespace="/zygl" method="post" id="mainForm" name="mainForm">
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
	          <td width="8%" align="right">��λ��</td>
	          <td width="21%"><sys:gsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y" myClass="select" onChange="commonInit('gs-fbs', this.value, '', 'domain.fbsDjxh', 'mainForm_domain_fbsDjxh', 'Y', 'Y')"/></td>
	          <td width="8%" align="right">�ְ��̣�</td>
	          <td width="21%">
	          	<select name="domain.fbsDjxh" id="mainForm_domain_fbsDjxh" class="select"/>
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
