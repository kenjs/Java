<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>��ҵ-��������-������λ</title>
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
	
	function onUpdate(ssJgbm,jldwFlDm){	
    	var url = jcontextPath+"/zygl/qyjcbmjldw!initMx.action?domain.ssJgbm="+ssJgbm+"&domain.jldwFlDm="+jldwFlDm;
    	window.showModalDialog(url,window,"dialogHeight:375px;dialogWidth:675px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		onRefresh();
    }

	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		 var jldwFlDm = $("#mainForm_domain_jldwFlDm").val();
		//����������
		var url = jcontextPath+"/zygl/qyjcbmjldw!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.jldwFlDm":jldwFlDm}								//����Ĳ�����json��ʽ
		 }
		 ).trigger("reloadGrid");		//��ʾ����һҳ�������������ݷ����仯��ʱ�򣬷�ҳ����Ϣ����
	}
	
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
		    colNames:['����','״̬','','������λ����','������λ����','������λ���','������λ����','������λ��־','','�������λ�������','������','��������','�޸���','�޸�����','��������(SEQ_JG_DJXH)',''],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
			  {name:'ztH', index:'ztH', width:'80', align:'center'},
			  {name:'qybz', index:'qybz', width:'0', align:'center',hidden:true},
			  {name:'jldwDm', index:'jldwDm', width:'100', align:'center'}, 
			  {name:'jldwMc',index:'jldwMc',width:'100',align:'center'},
			  {name:'jldwJc', index:'jldwJc', width:'100', align:'center'},
			  {name:'jldwFlMc', index:'jldwFlMc', width:'100', align:'center'},
			  {name:'jbdwH',index:'jbdwH',width:'100',align:'center'},
			  {name:'jbdwbz',index:'jbdwbz',width:'0',align:'center',hidden:true},
			  
		      {name:'hsbl', index:'hsbl', width:'100', align:'center'},
		      {name:'cjrMc', index:'cjrMc', width:'80', align:'center'},
		      {name:'cjrq', index:'cjrq', width:'80', align:'center'},
		      {name:'xgrMc', index:'xgrMc', width:'80', align:'center'},
		      {name:'xgrq', index:'xgrq', width:'80', align:'center'},
		      {name:'ssJgbm', index:'ssJgbm', width:'0', align:'center',hidden:true},
		      {name:'jldwFlDm', index:'jldwFlDm', width:'100', align:'center',hidden:true}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: 9999,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[9999],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'JLDW_DM',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/zygl/qyjcbmjldw!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val1 = jQuery("#dataList").jqGrid('getCell', cl,"ssJgbm"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var val2 = jQuery("#dataList").jqGrid('getCell', cl,"jldwFlDm");
                var val3 = jQuery("#dataList").jqGrid('getCell', cl,"qybz");
                var val4 = jQuery("#dataList").jqGrid('getCell', cl,"jbdwbz");
                var ztLink = "";
                var jbdmLink = "";
                if(val3 == "N"){
                	ztLink = "<font color=\"red\">δ����</font>";
                }else{
                	ztLink = "<font color=\"black\">������</font>";
                }
                if(val4 == "Y"){
                	jbdmLink = "<font color=\"blue\">��</font>";
                }else{
                	jbdmLink = "<font color=\"black\">��</font>";
                }
                var link = "<a href=\"javascript:onUpdate('"+val1+"','"+val2+"')\"><font color=\"blue\">�޸�</font></a>";
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
                $("#dataList").jqGrid('setRowData', cl, { 'ztH': ztLink });
                $("#dataList").jqGrid('setRowData', cl, { 'jbdwH': jbdmLink });
            }
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="qyjcbmjldw!query" namespace="/zygl" method="post" id="mainForm" name="mainForm">
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">������</li>
		    <li><a href="#" id="queryBtn" class="licon04">�� ��</a></li>
		    <li><a href="#" id="closeBtn" class="licon03">�� ��</a></li>
	  	</ul>
	
	    <ul class="rcont">
	    	<li class="ricon02" onClick="slideToggle('syquery')">��ʾ/���ز�ѯ����</li>
		    <li class="ricon03">����</li>
	  	</ul>
	</div> 

	<div class="right_cont">
	  	<div id="divQuery">
	    		<fieldset>
		      		<legend>��ѯ����</legend>
		      		<table width="75%" border="0" cellspacing="0" cellpadding="0">
		        		<tr>
		          			<td width="15%" align="right">����������λ��</td>
		          			<td width="35%">
		          				<sys:JldwFl myId="mainForm_domain_jldwFlDm" myName="domain.jldwFlDm" contaisQxz="false" myClass="select" mcContainDmBz="Y"/>
				  			</td>
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
