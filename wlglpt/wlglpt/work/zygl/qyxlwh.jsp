<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>��ҵ-��·ά��</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>

<script type="text/javascript">
	$(document).ready(function(){
		
		initDataGrid();
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//������ť�¼�
			$("#addBtn").click(function(){
				var sjJgbm = trim($("#mainForm_domain_sjJgbm").val()); 
				var url = jcontextPath+"/qyxlwh!initMx.action?domain.ssJgbm="+sjJgbm;
		    	window.showModalDialog(url,window,"dialogHeight:320px;dialogWidth:520px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		    	onRefresh();
			});
			//��ʼ�����
			
						

	});

    function onUpdate(ssJgbm,sfdDm,mddDm){
    	var url = jcontextPath+"/qyxlwh!initMx.action?domain.ssJgbm="+ssJgbm+"&domain.sfdXzqhDm="+sfdDm+"&domain.mddXzqhDm="+mddDm;
    	window.showModalDialog(url,window,"dialogHeight:320px;dialogWidth:520px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
    	onRefresh();
    }
    
    var keyValue = "";
    var fhrDm = "";
    var shrDm = "";
	function onDelete( ssJgbm,fhr,shr){
		keyValue = ssJgbm;
		fhrDm = fhr;
		shrDm = shr;
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.ssJgbm":keyValue,"domain.sfdXzqhDm":fhrDm,"domain.mddXzqhDm":shrDm};
		 var url = jcontextPath+"/qyxlwh!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}	
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var sjJgbm = trim($("#mainForm_domain_sjJgbm").val()); 
		//����������
		var url = jcontextPath+"/qyxlwh!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":sjJgbm}								//����Ĳ�����json��ʽ
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
		    colNames:['����', '��������','ʼ����','Ŀ�ĵ�','�����',
		              '�ﵽ����','������','��������','�޸���','�޸�����','','',''],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
		      {name:'sjMc', index:'sjMc', width:'100', align:'center'}, 
		      {name:'sfdMc', index:'sfdMc', width:'100', align:'center'}, 
		      {name:'mddMc', index:'mddMc', width:'100', align:'center'}, 
		      {name:'lcs', index:'lcs', width:'100', align:'center'}, 

		      {name:'ddts', index:'ddts', width:'100', align:'center'}, 
		      {name:'cjrMc', index:'cjrMc', width:'100', align:'center'}, 
		      {name:'cjrq', index:'cjrq', width:'100', align:'center'}, 
		      {name:'xgrMc', index:'xgrMc', width:'100', align:'center'}, 
		      {name:'xgrq', index:'xgrq', width:'100', align:'center'},
		      {name:'ssJgbm', index:'ssJgbm', width:'100', align:'center',hidden:true},
		      {name:'sfdXzqhDm', index:'sfdXzqhDm', width:'100', align:'center',hidden:true},
		      {name:'mddXzqhDm', index:'mddXzqhDm', width:'100', align:'center',hidden:true}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'SS_JGBM',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/qyxlwh!download.action");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"ssJgbm"); 
                var sfdXzqhDm = jQuery("#dataList").jqGrid('getCell', cl,"sfdXzqhDm"); 
                var mddXzqhDm = jQuery("#dataList").jqGrid('getCell', cl,"mddXzqhDm"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var link = "<a href=\"javascript:onUpdate('"+val+"','"+sfdXzqhDm+"','"+mddXzqhDm+"')\"><font color=\"blue\">�޸�</font></a>"
                + " <a href=\"javascript:onDelete('"+val+"','"+sfdXzqhDm+"','"+mddXzqhDm+"')\"><font color=\"blue\">ɾ��</font></a>";
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="qyxlwh!query" namespace="" method="post" id="mainForm" name="mainForm">
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
		   <table width="95%" border="0"  cellspacing="0" cellpadding="0">
		        <tr>
		          <td width="7%" align="right">��λ��</td>
		          <td width="35%" >  <sys:gsList myName="domain.sjJgbm" myId="mainForm_domain_sjJgbm" contaisQxz="false" myClass="select" mcContainDmBz="Y"></sys:gsList></td>
		          <td>&nbsp;</td>
		          <td>&nbsp;</td>
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
