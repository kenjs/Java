<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�칫-����ʱ��</title>
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
				var jgbm = trim($("#mainForm_domain_jgbm").val()); 
				var url = jcontextPath+"/bggl/bggzsj!initMx.action?domain.jgbm="+jgbm;
		    	window.showModalDialog(url,window,"dialogHeight:370px;dialogWidth:760px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
		    	onRefresh();
			});
			//��ʼ�����
			initDataGrid();
						

	});

    function onUpdate(jgbm,yxqQ){
    	var url =jcontextPath+"/bggl/bggzsj!initMx.action?domain.jgbm="+jgbm+"&domain.yxqQ="+yxqQ;
		window.showModalDialog(url,window,"dialogHeight:370px;dialogWidth:760px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
		onRefresh();
    }
    
    var keyValue = "";
    var keyValue2 = "";
	function onDelete( jgbm,yxqQ){
		keyValue = jgbm;
		keyValue2 = yxqQ;
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.jgbm":keyValue,"domain.yxqQ":keyValue2};
		 var url = jcontextPath+"/bggl/bggzsj!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}	
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var jgbm = trim($("#mainForm_domain_jgbm").val()); 
		var yxqZ = "";
    	if($("#checkBoxId").attr("checked")){
  			yxqZ = "1";
  		}else{
  			yxqZ = "2";
  		}
  		
		//����������
		var url = jcontextPath+"/bggl/bggzsj!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.jgbm":jgbm,"domain.yxqZ":yxqZ}								//����Ĳ�����json��ʽ
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
		    colNames:['����', '��������','״̬','��Ч����','��Ч��ֹ','�����ϰ�ʱ��','�����°�ʱ��','�����ϰ�ʱ��-ʱ','�����ϰ�ʱ��-��',
				     '�����°�ʱ��-ʱ','�����°�ʱ��-��','������','��������','�޸���',
				     '�޸�����'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
		      {name:'jgbm', index:'jgbm', width:'100',hidden:true, align:'center'}, 
		      {name:'zt', index:'zt', width:'70', align:'center'},
		      {name:'yxqQ', index:'yxqQ', width:'100', align:'center'}, 
		      {name:'yxqZ', index:'yxqZ', width:'100', align:'center'}, 
		      {name:'sbsj', index:'sbsj', width:'100', align:'center'},
		      {name:'xbsj', index:'xbsj', width:'100', align:'center'},
		      {name:'amSbsjS', index:'amSbsjS', width:'100',hidden:true, align:'center'}, 
		      {name:'amSbsjF', index:'amSbsjF', width:'100',hidden:true, align:'center'}, 
		      {name:'pmSbsjS', index:'pmSbsjS', width:'100',hidden:true, align:'center'}, 
		      {name:'pmSbsjF', index:'pmSbsjF', width:'100',hidden:true, align:'center'}, 
		      {name:'cjrMc', index:'cjrMc', width:'100',hidden:true, align:'center'}, 
		      {name:'cjrq', index:'cjrq', width:'100',hidden:true, align:'center'}, 

		      {name:'xgrMc', index:'xgrMc', width:'100',hidden:true, align:'center'}, 
		      {name:'xgrq', index:'xgrq', width:'100',hidden:true, align:'center'}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'yxq_Q',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/bggl/bggzsj!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"jgbm"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var val2 = jQuery("#dataList").jqGrid('getCell', cl,"yxqQ"); 
                var link = "<a href=\"javascript:onUpdate('"+val+"','"+val2+"')\"><font color=\"blue\">�޸�</font></a>"
                + " <a href=\"javascript:onDelete('"+val+"','"+val2+"')\"><font color=\"blue\">ɾ��</font></a>";
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
                
                var zt = jQuery("#dataList").jqGrid('getCell', cl,"zt"); 
                var link2 = "";
                if("1"==zt){
                	link2 = "<font color=\"red\">ʧЧ</font>"
                }else{
                	link2 = "��Ч"
                }
                $("#dataList").jqGrid('setRowData', cl, { 'zt': link2 }); 
            }
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="bggzsj!query" namespace="/bggl" method="post" id="mainForm" name="mainForm">
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
	          <td width="8%" align="right">�������룺</td>
	          <td width="21%"><sys:gsList myId="mainForm_domain_jgbm" myName="domain.jgbm" mcContainDmBz="N" myClass="select"/></td>
	          <td  width="8%" align="right">��ǰ��Ч��</td>
	          <td align="left"><input type="checkbox" name="checkbox" id="checkBoxId" checked="checked"/></td>
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
