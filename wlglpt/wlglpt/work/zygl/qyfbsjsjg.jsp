<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>��ҵ-�ְ���-����۸�</title>
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
				//var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
				var fbsDjxh = trim($("#mainForm_domain_fbsDjxh").val()); 
				if(null==fbsDjxh||""==fbsDjxh){
					showAlert("����ѡ��ְ��̣�");
					return;
				}
				var url = jcontextPath+"/zygl/qyfbsjsjg!initMx.action?domain.fbsDjxh="+fbsDjxh;
		    	window.showModalDialog(url,window,"dialogHeight:320px;dialogWidth:580px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		    	onRefresh();
			});
			//��ʼ�����
			initDataGrid();
			var gs = $("#mainForm_domain_ssJgbm").val();
			commonInit('gs-fbs', gs, '', 'domain.fbsDjxh', 'mainForm_domain_fbsDjxh', 'Y', 'Y');			
			commonInit('fbs-xl', '', '', 'domain.lxDjxh', 'mainForm_domain_lxDjxh', 'Y', 'Y');
	});

    function onUpdate(jsjgDjxh){
    	var url = jcontextPath+"/zygl/qyfbsjsjg!initMx.action?domain.jsjgDjxh="+jsjgDjxh;
		window.showModalDialog(url,window,"dialogHeight:320px;dialogWidth:580px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		onRefresh();
    }
    
    var keyValue = "";
	function onDelete( jsjgDjxh){
		keyValue = jsjgDjxh;
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.jsjgDjxh":keyValue};
		 var url = jcontextPath+"/zygl/qyfbsjsjg!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}	
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		//var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
		var fbsDjxh = trim($("#mainForm_domain_fbsDjxh").val()); 
		var lxDjxh = trim($("#mainForm_domain_lxDjxh").val()); 
		if(null==fbsDjxh||""==fbsDjxh){
			showAlert("����ѡ��ְ��̣�");
			return;
		}
		
  		if($("#checkBoxId").attr("checked")){
  			yxqZ = "1";
  		}else{
  			yxqZ = "2";
  		}
		//����������
		var url = jcontextPath+"/zygl/qyfbsjsjg!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.fbsDjxh":fbsDjxh,"domain.lxDjxh":lxDjxh,"domain.yxqZ":yxqZ}								//����Ĳ�����json��ʽ
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
		    colNames:['����', '����۸�Ǽ����(SEQ_ZY_DJXH)','�ְ��̵Ǽ����','״̬','ʼ����','Ŀ�ĵ�','�����','�ﵽ����','���������λ',
				     '�۸���㹫ʽ(�������Ĺ�ʽ)','��Ч����','��Ч��ֹ','�۸�˵��','��Ч����','��Ч��ֹ',
				     '��Ч��־(Y/N)','������','��������','�޸���','�޸�����'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
		      {name:'jsjgDjxh', index:'jsjgDjxh',hidden:true, width:'100', align:'center'}, 
		      {name:'fbsDjxh', index:'fbsDjxh',hidden:true, width:'100', align:'center'}, 
		      {name:'zt', index:'zt', width:'100', align:'center',hidden:true}, 
			  {name:'sfdXzqhMc', index:'sfdXzqhMc', width:'80', align:'left'}, 
			  {name:'mddXzqhMc', index:'mddXzqhMc', width:'80', align:'left'}, 
			  {name:'lcs', index:'lcs', width:'80', align:'left'}, 
			  {name:'ddts', index:'ddts', width:'80', align:'left'}, 
			  
		      {name:'jsJldwMc', index:'jsJldwMc', width:'100', align:'center'}, 
		      {name:'jgjsgs', index:'jgjsgs', width:'120', align:'right'}, 
		      {name:'yxqQ', index:'yxqQ', width:'80', align:'center'}, 
		      {name:'yxqZ', index:'yxqZ', width:'80', align:'center'}, 
		      {name:'jgsm', index:'jgsm', width:'100', align:'left'}, 

		      {name:'yxqQ', index:'yxqQ', width:'80', align:'center'}, 
		      {name:'yxqZ', index:'yxqZ', width:'80',align:'center'}, 
		      {name:'yxbz', index:'yxbz', width:'100',hidden:true, align:'center'}, 
		      {name:'cjrMc', index:'cjrMc', width:'80', align:'center'}, 

		      {name:'cjrq', index:'cjrq', width:'80', align:'center'}, 
		      {name:'xgrMc', index:'xgrMc', width:'80', align:'center'}, 
		      {name:'xgrq', index:'xgrq', width:'80', align:'center'}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'JSJG_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/zygl/qyfbsjsjg!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"jsjgDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">�޸�</font></a>"
                + " <a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">ɾ��</font></a>";
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link });                            
            }
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="qyfbsjsjg!query" namespace="/zygl" method="post" id="mainForm" name="mainForm">
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
	          <td width="21%"><sys:gsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" onChange="commonInit('gs-fbs', this.value, '', 'domain.fbsDjxh', 'mainForm_domain_fbsDjxh', 'Y', 'Y')" mcContainDmBz="Y" myClass="select"/></td>
	          <td width="8%" align="right">�ְ��̣�</td>
	          <td width="21%"><select name="domain.fbsDjxh" onChange="commonInit('fbs-xl', this.value, '', 'domain.lxDjxh', 'mainForm_domain_lxDjxh', 'Y', 'Y')" id="mainForm_domain_fbsDjxh" class="select"/></td>
	          <td width="8%" align="right">��·��</td>
	          <td width="21%"><select name="domain.lxDjxh" id="mainForm_domain_lxDjxh" class="select"/></td>
	        </tr>
	        <tr>
	          <td align="right"><input type="checkbox" id="checkBoxId"/></td>
	          <td align="left">&nbsp;��ʾ��ʷ</td>
	          <td colspan="4"></td>
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
