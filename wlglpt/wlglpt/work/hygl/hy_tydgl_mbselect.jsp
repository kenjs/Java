<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>����-���˵�����-ģ��ѡ��</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>

<script type="text/javascript">
	$(document).ready(function(){
		//onRefresh();
		$("#closeBtn").click(function() {
			window.close();
		});
		
		//��ѯ��ť�¼�
		$("#queryBtn").click(function(){
			onRefresh();
		});
		
		//������ť�¼�
		$("#yesBtn").click(function(){
			var checkbox=$(":checked[name='box']");
			if (checkbox.length <= 0) {
				showAlert("��ѡ��һ��ģ�壡");
				return;
			}
			window.dialogArguments.initTydFromMb(checkbox.val());
			window.close();
		});
		//��ʼ�����
		initDataGrid();
	});
	
	window.onload = function init() {
		onRefresh();
	}

	function checkSingle(obj){
		var checkbox=$(":checkbox[name='box']").attr("checked", false);
		obj.checked = true;
	}
	
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		var fhrMc = $("#mainForm_domain_fhrMc").val();
		var shrMc = $("#mainForm_domain_shrMc").val();
		
		//����������
		var url = jcontextPath+"/hygl/hytydmbgl!queryMb4Tydgl.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.fhrDjxh":fhrDjxh,"domain.fhrMc":encodeURI(fhrMc), "domain.shrMc":encodeURI(shrMc)
	 			}								//����Ĳ�����json��ʽ
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
		    rownumbers : false,					//�����
			width:pageWidth()-10,  
			height:pageTableHeight()-90,	
		    gridComplete: myGridComplete,		//����������¼�
		    shrinkToFit:false, 
		    colNames:['����', '�ͻ�����','ģ������','ʼ����', 'Ŀ�ĵ�', 
		              '������ַ', '�ջ���','�ջ���ַ', '�Ǽǲ���', '��������', '������', '��������', '�޸���', '�޸�����',
		             'ID' ],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'30', align:'center'},
			  {name:'fhrMc', index:'fhrMc', width:'100', align:'center'}, 
		      {name:'mbmc', index:'mbmc', width:'200', align:'center'}, 
		      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'50', align:'center'}, 
		      
		      {name:'shrXzqhMc', index:'shrXzqhMc', width:'50', align:'center'}, 
		       {name:'fhrDz', index:'fhrDz', width:'80', align:'center'}, 
		        {name:'shrMc', index:'shrMc', width:'80', align:'center'}, 
		      {name:'shrDz', index:'shrDz', width:'80', align:'center'}, 
		      {name:'djJgmc', index:'djJgmc', width:'80', align:'center'},
		      {name:'ssJgmc', index:'ssJgmc', width:'80', align:'center'},
		      {name:'cjrMc', index:'cjrMc', width:'80', align:'center'},
		      
		      {name:'cjrq', index:'cjrq', width:'70', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      {name:'xgrMc', index:'xgrMc', width:'80', align:'center'}, 
		      {name:'xgrq', index:'xgrq', width:'70', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		       {name:'mbDjxh', index:'mbDjxh', width:'0',hidden:true, align:'center'}
		      
		      
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: -1,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[-1],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'mbDjxh,fhrMc',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
		    sortorder: 'DESC,ASC',				//Ĭ��������
		    viewrecords: true,					//�����Ƿ�Ҫ��ʾ�ܼ�¼��
		    gridview: false,					//Ĭ��false ����һ�����ݺ���ӵ�grid�У������Ϊtrue���ǽ������������ݶ�������ɺ�����ӵ�grid�У���treeGrid, subGrid, or afterInsertRow ������	
		    jsonReader: {     
	     	 	root: 	 "domain.dataList",   				// �����У�Ĭ��Ϊ��rows��
	     	 	page:	 "domain.page.curPage",					// ��ǰҳ
	     	 	total: 	 "domain.page.totalPages",				// ��ҳ��
	     	 	records: "domain.page.totalRecords", 			// �ܼ�¼��
	     	 	reccount: "domain.page.reccount",
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
		  /* $("#dataList").jqGrid('navButtonAdd','#pager',{
		       caption:"", 
		       onClickButton : function () { 
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/hytydmbgl!download.action");
				   $("#mainForm").submit();
		       } 
		 }); */
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"mbDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var link = '<input type="checkbox" name="box" value="'+val+'"  onclick="checkSingle(this)"/>';
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
    }
       
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="hytydgl!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.fhrDjxh" />
	<s:hidden name="domain.fhrMc" />
	
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">������</li>
		    <li><a href="#" id="queryBtn" class="licon04">�� ��</a></li>
		    <li><a href="#" id="yesBtn" class="licon01">ȷ ��</a></li>
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
		          <td width="15%" align="right">�ջ���λ��</td>
		          <td width="35%">
		          	<s:textfield name="domain.shrMc" cssClass="pop_input bgstyle_optional"></s:textfield>
		          </td>
		          <td width="15%" align="right"></td>
		          <td width="35%">
		          </td>
		        </tr>
		   </table>
		</fieldset>
	  </div>
  
		<!-- ��ҳ��� id����ΪdataList -->
		<table id="dataList"><tr><td/></tr></table> 
		<!-- ��ҳ���� -->
		<%@include file="/common/message.jsp" %>
	</div>
</s:form>
</body>
</html>
