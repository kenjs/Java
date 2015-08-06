<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>����-��ת���²�</title>
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
			onRefresh();	

	});
	function onUpdate(zjdbDjxh){
		//alert(zjdbDjxh);
    	var url = jcontextPath+"/cwgl/cwzzjxb!initMx.action?domain.zjdbDjxh="+zjdbDjxh;
    	window.showModalDialog(url,window,"dialogHeight:420px;dialogWidth:580px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
    	onRefresh();
    }
    function onAdd(zjdbDjxh,jsDwDjxh){
    	//alert(jsDwDjxh);
    	zjdbDjxh = "";
    	var url = jcontextPath+"/cwgl/cwzzjxb!initMx.action?domain.zjdbDjxh="+zjdbDjxh+"&domain.jsDwDjxh="+jsDwDjxh;
    	window.showModalDialog(url,window,"dialogHeight:420px;dialogWidth:580px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
    	onRefresh();
    }
    
    var keyValue = "";
	function onDelete(zjdbDjxh){
		keyValue = zjdbDjxh;
		showConfirm("ȷ��Ҫ����ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.zjdbDjxh":keyValue};
		 var url = jcontextPath+"/cwgl/cwzzjxb!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("�����ɹ���");
        onRefresh();
	}	
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var rq = trim($("#mainForm_domain_rq").val()); 
		//����������
		var url = jcontextPath+"/cwgl/cwzzjxb!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.rq":rq}								//����Ĳ�����json��ʽ
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
		    colNames:['���','�ʽ�����Ǽ����(SEQ_CW_DJXH)','���յ�λ���','���յ�λ','�ʽ�����','���ϼ�','����ֽ�',
				     '����Ϳ�','�����','֧���ϼ�','֧��Ԥ����','֧�����',
				     '֧����������','֧������','���ý�','�²����','�²�����', '�²���','��ע','����'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
		      {name:'pageXh', index:'pageXh', width:'30', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pageXh' + rowId + '\'';
			    }
			  },
		      {name:'zjdbDjxh', index:'zjdbDjxh', width:'100', align:'center',hidden:true}, 
		      {name:'jsDwDjxh', index:'jsDwDjxh', width:'100', align:'center',hidden:true}, 
		      {name:'jsDwMc', index:'jsDwMc', width:'120', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'jsDwMc' + rowId + '\'';
			    }
			  },
		     
		      {name:'zjxq', index:'zjxq', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'zjxq' + rowId + '\'';
			    }
			  },
		     
		      {name:'jcHj', index:'jcHj', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'jcHj' + rowId + '\'';
			    }
			  },
		      {name:'jcXj', index:'jcXj', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'jcXj' + rowId + '\'';
			    }
			  },
		      {name:'jcYk', index:'jcYk', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'jcYk' + rowId + '\'';
			    }
			  },
		      {name:'jcCk', index:'jcCk', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'jcCk' + rowId + '\'';
			    }
			  },
		     
		      {name:'zfHj', index:'zfHj', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'zfHj' + rowId + '\'';
			    }
			  },
		      {name:'zfYfk', index:'zfYfk', width:'80', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'zfYfk' + rowId + '\'';
			    }
			  },
		      {name:'zfYk', index:'zfYk', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'zfYk' + rowId + '\'';
			    }
			  },
		      {name:'zfBxfy', index:'zfBxfy', width:'90', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'zfBxfy' + rowId + '\'';
			    }
			  },
		      {name:'zfQt', index:'zfQt', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'zfQt' + rowId + '\'';
			    }
			  },
		      
		      {name:'byj', index:'byj', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'byj' + rowId + '\'';
			    }
			  },
			  {name:'xbje', index:'xbje', width:'60', align:'center'}, 
			  {name:'xbrq', index:'xbrq', width:'80', align:'center'},
			  {name:'xbrMc', index:'xbrMc', width:'80', align:'center'},
		      {name:'bz', index:'bz', width:'180', align:'center'},		      
		       
		      
		      {name:'hstoperationcol', index:'', sortable:false, width:'90', align:'center'}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'ZJDB_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/cwgl/cwzzjxb!download.action");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"zjdbDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var jsDwDjxh = jQuery("#dataList").jqGrid('getCell', cl,"jsDwDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                if(val==null||val==""){
                	 var link = "<a href=\"javascript:onAdd('"+val+"','"+jsDwDjxh+"')\"><font color=\"blue\">�²�</font></a>"
                }else{
                	 var link = "<a href=\"javascript:onAdd('"+val+"','"+jsDwDjxh+"')\"><font color=\"blue\">�²�</font></a>"
                	 			+ " <a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">�޸�</font></a>"
                				+ " <a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">����</font></a>";
                }
                
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
       var gridName = "dataList";
	   var a = ['pageXh','jsDwMc','zjxq','jcHj','jcXj','jcYk','jcCk','zfHj','zfYfk','zfYk','zfBxfy','zfQt','byj']; 

       Merger(gridName, 'pageXh', a);

     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="cwzzjxb!query" namespace="" method="post" id="mainForm" name="mainForm">
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">������</li>
		    <li><a href="#" id="queryBtn" class="licon04">�� ��</a></li>
		    <li><a href="#" id="closeBtn" class="licon03">�ر�</a></li>
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
	          <td align="right" width="12%">���ڣ�</td>
     		  <td width="21%">
   				   <s:textfield name="domain.rq" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
     		  </td>
	          <td width="66%"></td>
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
