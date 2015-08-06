<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>��ˮ��¼��ϸ</title>
   
<style type="text/css">
html,body {background:none;}
.tydSel {width:68px;}
</style>
<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		})
		//��ʼ�����
		initDataGrid();
		
		onRefresh();	
	 })
	     
	
  /**************************************��ҳ��ʼ****************************************/
  //ˢ�±��
	function onRefresh(){
		var djxh = $("#mainForm_domain_yhCshDjxh").val(); 
  		//����������
		var url = jcontextPath+"/cwhbzclsjl!query.action";   
		 $("#dataList").jqGrid("setGridParam",{ 
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.yhCshDjxh":djxh}								//����Ĳ�����json��ʽ
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
			width:pageWidth()-25,  
			height:pageHeight()-260,	
		    gridComplete: myGridComplete,		//����������¼�
		    shrinkToFit:false, 
		    colNames:['����','����Ǽ����','���','��־','����','���','������','��������','����','�˺�','˵��'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false,hidden:true, align:'center'},
			  {name:'cwbdDjXh', index:'cwbdDjXh',hidden:true, align:'center'},
		      {name:'zcflMc', index:'zcflMc', width:'80', align:'center'},
		      {name:'bzmc', index:'bzmc', width:'80', align:'center'}, 	
		      {name:'rq', index:'rq', width:'60', align:'center'},
		      {name:'bdje', index:'bdje', width:'80', align:'right'},
		      {name:'jbrCzyMc', index:'jbrCzyMc', width:'80', align:'center'},  
		      {name:'yhmc', index:'yhmc', width:'200', align:'center'}, 	      
		      {name:'hm', index:'hm', width:'80', align:'center'},			  
			  {name:'zh', index:'zh', width:'80', align:'center'}, 	  
			  {name:'sm', index:'sm', width:'300', align:'left'}
		     ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,						
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'cwbdDjXh',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		 
		    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����������
			function myGridComplete() {
		            var graduateIds = $("#dataList").jqGrid('getDataIDs');
		            for (var i = 0; i < graduateIds.length; i++) {
		                var cl = graduateIds[i];
		                var val = jQuery("#dataList").jqGrid('getCell', cl,"cwbdDjXh"); 	  //��ȡ��ǰ��Ԫ������Ĳ������ 
		                var link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">�޸�</font></a>";
		                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
		            }
		     }
	}
</script>
</head>

<body>
<%try{ %>
   <s:form action="qykhhwxx!initMx" namespace="/cwgl" method="post" id="mainForm" name="mainForm">
   <s:hidden name="domain.yhCshDjxh"></s:hidden>
   	<div class="pop_contc" style="height:170px; overflow:auto;">
			<fieldset>
		    <legend>������Ϣ</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				
      			<tr>
      				<td width="15%" align="right">���</td>
      				<td width="35%">
      					<s:textfield name="domain.zcflMc"  cssClass="pop_input  bgstyle_readonly" readonly="true"></s:textfield>
      				</td>
      				<td width="15%" align="right">������</td>
      				<td width="35%">
      					<s:textfield name="domain.hm"  cssClass="pop_input  bgstyle_readonly" readonly="true"></s:textfield>
      				</td>
      		    </tr> 
      		    <tr>
      				<td width="15%" align="right">��</td>
      				<td width="35%">
      					<s:textfield name="domain.je"  cssClass="pop_input  bgstyle_readonly" readonly="true"></s:textfield>
      				</td>
      				<td width="15%" align="right">�˺ţ�</td>
      				<td width="35%">
      					<s:textfield name="domain.zh"  cssClass="pop_input  bgstyle_readonly" readonly="true"></s:textfield>
      				</td>
      		    </tr>     		    
      			<tr>
      				<td width="15%" align="right">�������ƣ�</td>
      				<td width="85%" colspan="3">
      				    <s:textfield name="domain.yhmc"  cssClass="pop_input_colspan2  bgstyle_readonly" readonly="true"></s:textfield>
      				</td>
      			</tr>
			</table>
			</fieldset>
			<div class="pop_btn">
			    <button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
		    </div>
	 </div>
	<div class="pop_contc" style="height:600px; overflow:auto;">
	 <!-- ��ҳ��� id����ΪdataList -->
		<table id="dataList"><tr><td /></tr></table>
		<!-- ��ҳ���� -->
		<div id="pager"></div>
    </div>
   <%@include file="/common/message.jsp" %>
   </s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>