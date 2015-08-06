<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<html>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>����-������ʧ�鿴</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript">
    $(document).ready(function(){
    	$("#closeBtn").click(function(){
			window.close();
		});
    	
		//��ѯ��ť�¼�
		$("#queryBtn").click(function(){
			onRefresh();
		});
		
		$("#saveMxBtn").click(function(){
			var xhs = $(":checked[name='xhs']");
			if (xhs.length <= 0) {
				showAlert("����ѡ����Ҫ�γ��嵥��������ʧ�ǼǼ�¼��");
				return;
			}
			
			var existBz = trim($("#mainForm_domain_existBz").val()); 
			var qdDjxh = trim($("#mainForm_domain_qdDjxh").val()); 
			var xhs = $(":checked[name='xhs'][value!='']");
			if (xhs.length > 0) {
				var jsonStr = getJqueryParam(xhs, "domain.ywDjxhs");
				var jsonObj = {"domain.qdDjxh":qdDjxh,"domain.existBz":existBz};
				
				jsonStr += jQuery.param(jsonObj);
				var url = jcontextPath+"/hygl/jssrdzqd!saveWlssMx";  
				showMessage();
				ajaxCommon(url,encodeURI(jsonStr),"doSaveMxSuc", false);
			}
		});
					
		//��ʼ�����
		initDataGrid();
	});	
    
    function doSaveMxSuc(){ 
		hideMessage();
		window.close();
	}
    
  /**************************************��ҳ��ʼ****************************************/
  //ˢ�±��
	function onRefresh(){
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var khDjxh = $("#mainForm_domain_khDjxh").val();
		var pcrqQ = $("#mainForm_domain_pcrqQ").val();
		var pcrqZ = $("#mainForm_domain_pcrqZ").val();
  		//����������
		var url = jcontextPath+"/hygl/wlssdj!query.action";   
		 $("#dataList").jqGrid("setGridParam",{ 
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.khDjxh":khDjxh,"domain.pcrqQ":pcrqQ,"domain.pcrqZ":pcrqZ}								//����Ĳ�����json��ʽ
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
			height:pageTableHeight()-130,	
		    gridComplete: myGridComplete,		//����������¼�
		    shrinkToFit:false, 
		    colNames:['<input title="ѡ��/ȡ��ѡ��" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />',
		    	'������ʧ�Ǽ����','��ʧ���','������ʧԭ��','��ʧ����ʽ','�ͻ�����','��������','ʼ����','Ŀ�ĵ�','����','����','���','��װ','�ɳ�����'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'',width:'30', sortable:false, align:'center'},
			  {name:'wlssDjxh', index:'wlssDjxh',hidden:true, align:'center'},
		      {name:'je', index:'je', width:'80', align:'center'},
		      {name:'wlssyy', index:'wlssyy', width:'100', align:'center'}, 	
		      {name:'wlssclfsMc', index:'wlssclfsMc', width:'100', align:'center'},
		      {name:'khmc', index:'khmc', width:'150', align:'center'},
		      {name:'hwmc', index:'hwmc', width:'150', align:'center'},
		      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'80', align:'center'},
		      {name:'shrXzqhMc', index:'shrXzqhMc', width:'80', align:'center'},  
		      {name:'sl', index:'sl', width:'60', align:'center'}, 	      
		      {name:'zl', index:'zl', width:'60', align:'center'},			  
			  {name:'tj', index:'tj', width:'60', align:'center'}, 	  
			  {name:'bz', index:'bz', width:'60', align:'center'},
			  {name:'pcrq', index:'pcrq', width:'80', align:'center'}
		     ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: -1,						
		    rowList:[-1],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: '',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
		    sortorder: '',				//Ĭ��������
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/wlssdj!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
		    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����������
			function myGridComplete() {
	            var graduateIds = $("#dataList").jqGrid('getDataIDs');
	            for (var i = 0; i < graduateIds.length; i++) {
	                var cl = graduateIds[i];
	                var wlssDjxh = jQuery("#dataList").jqGrid('getCell', cl,"wlssDjxh"); 	  //��ȡ��ǰ��Ԫ������Ĳ������ 
	                var link = "<input type=\"checkbox\" name=\"xhs\" value=\""+wlssDjxh+"\" />";
	                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
	            }
		     }
	}
</script>
</head>
<body>
<s:form action="hygl/wlssdj!query" theme="simple" namespace="cwgl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.qdDjxh" />
	<s:hidden name="domain.existBz" />
	<s:hidden name="domain.ssJgbm" />
	
	<div class="pop_contc">
		<fieldset>
			<legend>��ѯ����</legend>
			<table width="99%" border="0" cellspacing="0" cellpadding="0">
				<tr>
				    <td width="10%" align="right">�ɳ����ڣ�</td>
	                <td width="22%">
	                  <sys:dateFirstDMonth myName="domain.pcrqQ" myId="mainForm_domain_pcrqQ" myClass="ymdate" />
          				��
          			  <sys:dateCurrentDayTag myName="domain.pcrqZ" myId="mainForm_domain_pcrqZ" myClass="ymdate" />
	                </td>     
	            </tr>
			</table>
		</fieldset>
	
		<div class="pop_btn" style="width: 700px;">
			<button type="button" class="pop_btnbg" id="queryBtn">�� ��</button>
		 	&nbsp;
		 	<button type="button" class="pop_btnbg" id="saveMxBtn">ȷ ��</button>
		 	&nbsp;
		    <button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
	    </div>
		<!-- ��ҳ��� id����ΪdataList -->
		<table id="dataList"><tr><td /></tr></table>
	</div>
</s:form>
</body>
