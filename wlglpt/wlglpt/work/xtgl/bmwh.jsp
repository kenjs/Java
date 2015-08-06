<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<html>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
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
				var sj = $("#mainForm_domain_sjJgbm").val(); 
				var url = jcontextPath+"/bmwh!initMx.action?domain.sjJgbm="+sj;
		    	window.showModalDialog(url,window,"dialogHeight:390px;dialogWidth:520px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		    	onRefresh();
			});
			//��ʼ�����
			initDataGrid();
	});	
		     
   var val1="";
   function stop(id){
   		val1=id
   		showConfirm("��ȷ��Ҫͣ��ô��","stopCallBack");
   }  
    
   function stopCallBack(){
        var url=jcontextPath+"/bmwh!saveDisable";
   		jsonObj={"domain.jgbm":val1};
		ajaxCommon(url,jsonObj,"stopSuccess");
   }  
    
  function stopSuccess(){
  	   showAlert("ͣ�óɹ���");
  	   onRefresh();
  }  
  
   var val2="";
   function start(id){
    	val2=id;
    	showConfirm("��ȷ��Ҫ����ô��","startCallBack");
   }   
   
   function startCallBack(){
   		var url=jcontextPath+"/bmwh!saveEnable";
		jsonObj={"domain.jgbm":val2};
		ajaxCommon(url,jsonObj,"startSuccess");
   }   
   
   function startSuccess(){
  	   showAlert("���óɹ���");
  	   onRefresh();
  }
  
  function onUpdate(id){
		var url = jcontextPath+"/bmwh!initMx.action?domain.jgbm="+id;
		window.showModalDialog(url,window,"dialogHeight:390px;dialogWidth:520px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		onRefresh();
  }
  
  var idd="";
  function onDelete(id){
		idd=id;
		showConfirm("��ȷ��Ҫɾ��ô��","yesCallBack");
  }
  
  function yesCallBack(){
		var url=jcontextPath+"/bmwh!delete";
		jsonObj={"domain.jgbm":idd};
		ajaxCommon(url,jsonObj,"doSuccess");
  }
  
  function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
  } 
    
	//ˢ�±��
	function onRefresh(){
		var sj = $("#mainForm_domain_sjJgbm").val(); 
  		//����������
		var url = jcontextPath+"/bmwh!query.action";   
		 $("#dataList").jqGrid("setGridParam",{ 
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.sjJgbm":sj,"domain.yxbz":"Y"}								//����Ĳ�����json��ʽ
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
		    colNames:['����','ID','�������','״̬','���','����',
		    		  '�ϼ���λ','������','�绰','������','��������','�޸���',
		    		  '�޸�����'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'100', align:'center'},
			  {name:'jgbm', index:'jgbm', width:'0',hidden:true, align:'center'},
			  {name:'jbdm', index:'jbdm', width:'0',hidden:true, align:'center'}, 
		      {name:'qystr', index:'qystr', width:'50', align:'center'}, 
		      {name:'lbMc', index:'lbMc', width:'70', align:'center'}, 
		      {name:'mc', index:'mc', width:'200', align:'left'}, 	
		      {name:'sjMc', index:'sjMc', width:'130', align:'center'}, 	      
		      {name:'fzr', index:'fzr', width:'70', align:'center'}, 
			  {name:'dh', index:'dh', width:'80', align:'center'}, 
			  {name:'cjrMc', index:'cjrMc', width:'80', align:'center'}, 			  
			  {name:'cjrq', index:'cjrq', width:'80', align:'center'}, 	
		      {name:'xgrMc', index:'xgrMc', width:'80', align:'center'}, 
		      {name:'xgrq', index:'xgrq', width:'80', align:'center'}
		     ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,						
		   rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'jgbm',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		  
	  	  // add custom button to export the data to excel
		  $("#dataList").jqGrid('navButtonAdd','#pager',{
		       caption:"", 
		       onClickButton : function () { 
		       	   $("#mainForm").attr("action",jcontextPath+"/bmwh!download");
				   $("#mainForm").submit();
		       } 
		 }); 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var str = jQuery("#dataList").jqGrid('getCell', cl,"qystr"); 
                var val = jQuery("#dataList").jqGrid('getCell', cl,"jgbm"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">�޸�</font></a>"
                + " <a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">ɾ��</font></a>";
                if(str=="����"){
                	link=link+" <a href=\"javascript:stop('"+val+"')\"><font color=\"blue\">ͣ��</font></a>"
                }else{
                	link=link+" <a href=\"javascript:start('"+val+"')\"><font color=\"blue\">����</font></a>"
                }
                var zt;
                 if(str=="ͣ��"){
                	zt=" <font color=\"red\">ͣ��</font>"
                }else{
                	zt=" <font>����</font>"
                }
             	$("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
             	$("#dataList").jqGrid('setRowData', cl, { 'qystr': zt }); 
              }
     }    
</script>
<body>
<s:form action="bmwh!query" theme="simple" namespace="xtgl" method="post" id="mainForm" name="mainForm">
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
				<table width="95%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="7%" align="right">��λ��</td>   
						<td width="35%">
							<sys:qxGsList myId="mainForm_domain_sjJgbm" myName="domain.sjJgbm" mcContainDmBz="Y" myClass="select" ></sys:qxGsList>
						</td>
						<td>&nbsp;</td>
						<td>&nbsp;</td>
					<tr>
				</table>
			</fieldset>
		</div>
	
		<!-- ��ҳ��� id����ΪdataList -->
		<table id="dataList">
			<tr>
				<td />
			</tr>
		</table>
		<!-- ��ҳ���� -->
		<div id="pager"></div>
	</div>
</s:form>
</body>
</html>
