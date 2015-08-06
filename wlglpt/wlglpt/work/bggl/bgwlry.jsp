<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�칫-������Ա</title>
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
				var url = jcontextPath+"/bggl/bgwlry!initMx.action";
				window.showModalDialog(url,window,"dialogHeight:375px;dialogWidth:675px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
				onRefresh();
			});
			//��ʼ�����
			initDataGrid();
			
			var jgbm = $("#mainForm_domain_jgbm").val();
			if(jgbm != null && jgbm !="" && jgbm != undefined){
				commonInit('gs-wlryfl', jgbm,'', 'domain.wlryFlxh', 'mainForm_domain_wlryFlxh', 'Y', 'Y');
			}			

	});

    function onUpdate(wlryDjxh,jgbm){
    	var url = jcontextPath+"/bggl/bgwlry!initMx.action?domain.wlryDjxh="+wlryDjxh+"&domain.jgbm="+jgbm;
    	window.showModalDialog(url,window,"dialogHeight:375px;dialogWidth:675px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		onRefresh();
    }
    
    var keyValue = "";
	function onDelete( wlryDjxh){
		keyValue = wlryDjxh;
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.wlryDjxh":keyValue};
		 var url = jcontextPath+"/bggl/bgwlry!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}	
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		 var jgbm = $("#mainForm_domain_jgbm").val();
		 if(jgbm == null || jgbm == "" || jgbm == "0"){
		 	showAlert("��ѡ��˾!");
		 	return;
		 }
		 var wlryFlxh = $("#mainForm_domain_wlryFlxh").val();
		 var xm = $("#mainForm_domain_xm").val();
		 var dz = $("#mainForm_domain_dz").val(); 
		 //wlryFlxh="1000000026";
		//����������
		var url = jcontextPath+"/bggl/bgwlry!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.jgbm":jgbm,"domain.wlryFlxh":wlryFlxh,
		 	"domain.xm":encodeURI(xm),"domain.dz":encodeURI(dz)}	//����Ĳ�����json��ʽ
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
		    colNames:['����', '������Ա�Ǽ����(SEQ_BG_DJXH)','��������','����','��ַ','�绰','����','�ֻ�','E-MAIL',
				   '������','��������','�޸���','�޸�����',''],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
		      {name:'wlryDjxh', index:'wlryDjxh', width:'0',hidden:true}, 
		      {name:'flmc', index:'flmc', width:'80', align:'center'}, 
		      {name:'xm', index:'xm', width:'60', align:'center'}, 
		      {name:'dz', index:'dz', width:'100', align:'center'}, 

		      {name:'dh', index:'dh', width:'100', align:'center'}, 
		      {name:'cz', index:'cz', width:'90', align:'center'}, 
		      {name:'sj', index:'sj', width:'80', align:'center'}, 
		      {name:'dy', index:'dy', width:'90', align:'center'}, 
		      
		      {name:'cjrMc', index:'cjrMc', width:'80', align:'center'}, 
		      {name:'cjrq', index:'cjrq', width:'90', align:'center'}, 
		      {name:'xgrMc', index:'xgrMc', width:'80', align:'center'}, 
		      {name:'xgrq', index:'xgrq', width:'90', align:'center'},
		      {name:'jgbm', index:'jgbm', width:'0',hidden:true}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'WLRY_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/bggl/bgwlry!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val1 = jQuery("#dataList").jqGrid('getCell', cl,"wlryDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ����
                var val2 = jQuery("#dataList").jqGrid('getCell', cl,"jgbm"); 	  	  //��ȡ��ǰ��Ԫ������Ļ������� 
                var link = "<a href=\"javascript:onUpdate('"+val1+"','"+val2+"')\"><font color=\"blue\">�޸�</font></a>"
                + " <a href=\"javascript:onDelete('"+val1+"')\"><font color=\"blue\">ɾ��</font></a>";
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="bgwlry!query" namespace="/bggl" method="post" id="mainForm" name="mainForm">
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
		          		<td width="21%">
		          			<sys:gsList myName="domain.jgbm" myId="mainForm_domain_jgbm" mcContainDmBz="Y" contaisQxz="true" myClass="select" onChange="commonInit('gs-wlryfl',this.value, '', 'domain.wlryFlxh', 'mainForm_domain_wlryFlxh','Y', 'Y')"/>
		          		</td>
		          		<td width="10%" align="right">������Ա���ࣺ</td>
		          		<td width="21%"><select name="domain.wlryFlxh" id="mainForm_domain_wlryFlxh" class="select"/></td>
		        		<td width="8%" align="right">������</td>
		          		<td width="21%"><s:textfield name="domain.xm" cssClass="pop_input bgstyle_optional"></s:textfield></td>
		        	<tr>
		        	<tr>
		          		<td width="8%" align="right">��ַ��</td>
		          		<td width="21%"><s:textfield name="domain.dz" cssClass="pop_input bgstyle_optional"></s:textfield></td>
		          		<td colspan="4"></td>
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
