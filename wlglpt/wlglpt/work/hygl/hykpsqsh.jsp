<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>��Ʊ�������</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
	//var myArray=new Array();
	$(document).ready(function(){
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//����ͨ����ť�¼�
			$("#pltgBtn").click(function(){
				plJudge();
			});
			//�����˻ذ�ť�¼�
			$("#plthBtn").click(function(){
				plBack();
			});
					
			initDataGrid();
			var shbz = $("input[name='domain.shbz']:checked").val();
			if("Y"==shbz){
				$("#dataList").jqGrid('showCol',["sprMc","sprq","spjg"]);
			}else{
				$("#dataList").jqGrid('hideCol',["sprMc","sprq","spjg"]);
			}
			onDisplay();
	});
	
	function onDisplay(){
		var shbz = $("input[name='domain.shbz']:checked").val();
		if("Y"==shbz){
			$("#shrqTdText").show();
			$("#shrqTdId").show();
		}else{
			$("#shrqTdText").hide();
			$("#shrqTdId").hide();
		}
	}
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var rqQ = trim($("#mainForm_domain_rqQ").val()); 
		var rqZ = trim($("#mainForm_domain_rqZ").val()); 
		var shbz = $("input[name='domain.shbz']:checked").val();
		if(undefined==shbz || null==shbz || ""==shbz){
			showAlert("����ѡ��δ��˻�����ˣ�");
			return;
		}
		if("Y"==shbz){
			$("#dataList").jqGrid('showCol',["sprMc","sprq","spjg"]);
		}else{
			$("#dataList").jqGrid('hideCol',["sprMc","sprq","spjg"]);
		}
  
		//����������
		var url = jcontextPath+"/hygl/hykpsqsh!query.action";   
		//$("#mainForm").attr("action", "hyfydjsh!query");
		//mainForm.submit();
		$("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.shbz":shbz,"domain.rqQ":rqQ,"domain.rqZ":rqZ}
		 	//����Ĳ�����json��ʽ
		 }
		 ).trigger("reloadGrid");		//��ʾ����һҳ�������������ݷ����仯��ʱ�򣬷�ҳ����Ϣ����
	}
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
	    colNames:['���','�����������','�������','�ڵ����','<input title="ѡ��/ȡ��ѡ��" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />',
	    		'����','��־','������','��������','������','��������','�������','��Ʊ���뷽ʽ','�ͻ�����','��Ʊ���ϼ�','���뿪Ʊ����','��ע˵��','�Ǽ�������',
	    		'�Ǽǲ���','��������'
	    		],			 //name ����ʾ������
	     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
	    colModel :[
	      {name:'xh', index:'xh', sortable:false, width:'35', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'xh' + rowId + '\'';
		    }
		  },
		  {name:'wsSpxh', index:'wsSpxh', hidden:true, width:'70', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'wsSpxh' + rowId + '\'';
		    }
		  },
		  {name:'spxh', index:'spxh', width:'30', align:'center', hidden:true,
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'spxh' + rowId + '\'';
		    }
		  },
		  {name:'jdxh', index:'jdxh', width:'30', align:'center', hidden:true,
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'jdxh' + rowId + '\'';
		    }
		  },
		  {name:'checkboxoperationcol', index:'checkboxoperationcol', width:'35', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'checkboxoperationcol' + rowId + '\'';
		    }
		  },
		  {name:'hstoperationcol', index:'hstoperationcol', width:'35', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'hstoperationcol' + rowId + '\'';
		    }
		  },
		  {name:'fsthbz', index:'fsthbz', width:'35', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'fsthbz' + rowId + '\'';
		    }
		  },
		  {name:'fsrMc', index:'fsrMc', width:'60', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'fsrmc' + rowId + '\'';
		    }
		  },
		  {name:'fsrq', index:'fsrq', width:'70', sortable:false, align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'fsrq' + rowId + '\'';
		    }
		  },
		 {name:'sprMc', index:'sprMc', width:'65', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'sprMc' + rowId + '\'';
		    }
		  },
		  {name:'sprq', index:'sprq', width:'70', sortable:false, align:'center',  formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'sprq' + rowId + '\'';
		    }
		  },
		  {name:'spjg', index:'spjg', width:'70', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'spjg' + rowId + '\'';
		    }
		  },
		  
		  {name:'kpsqfsMc', index:'kpsqfsMc', width:'85', sortable:false, align:'center'},
		  {name:'khMc', index:'khMc', width:'70', sortable:false, align:'center'},
		  {name:'sqKpjeHj', index:'sqKpjeHj', width:'85', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'sqKpjeHj' + rowId + '\'';
		    }
		  },
		  {name:'sqKprq', index:'sqKprq', width:'85', sortable:false, align:'center',  formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}, 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'sqKprq' + rowId + '\'';
		    }
		  },
		  {name:'bzsm', index:'bzsm', width:'70', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'bzsm' + rowId + '\'';
		    }
		  },
		  {name:'djrMc', index:'djrMc', width:'70', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'djrMc' + rowId + '\'';
		    }
		  },
	      
		  {name:'bmmc', index:'bmmc', width:'120', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'bmmc' + rowId + '\'';
		    }
		  },
		  {name:'dwmc', index:'dwmc', width:'120', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'dwmc' + rowId + '\'';
		    }
		  }
	    ],
	    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
	    rowNum: -1,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
	    rowList:[-1],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
	    //sortname: 'DD_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
	}
	
	//��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
	    var graduateIds = $("#dataList").jqGrid('getDataIDs');
	    for (var i = 0; i < graduateIds.length; i++) {
	        var cl = graduateIds[i];
	        var wsSpxh = jQuery("#dataList").jqGrid('getCell', cl,"wsSpxh");
	        var spxh = jQuery("#dataList").jqGrid('getCell', cl,"spxh");
	        var jdxh = jQuery("#dataList").jqGrid('getCell', cl,"jdxh");
	        var fsthbz = jQuery("#dataList").jqGrid('getCell', cl,"fsthbz");
	        var input = "";
	        var shbz = $("input[name='domain.shbz']:checked").val();
	        var link="";
	        if("N"==shbz){
	        	input = "<input type=\"checkbox\" name=\"xhs\" value=\""+wsSpxh+"#"+spxh+"#"+jdxh+"\" />";
	        	$("#dataList").jqGrid('setRowData', cl, { 'checkboxoperationcol': input }); 
	        	
	         	link = "<a href=\"javascript:doWsSh('"+wsSpxh+"','"+spxh+"')\"><font color=\"blue\">���</font></a>";
	        	$("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
	        }else{
	        	input = "<input type=\"checkbox\" name=\"xhs\" disabled=\"disabled\" value=\""+wsSpxh+"#"+spxh+"#"+jdxh+"\" />";
	        	$("#dataList").jqGrid('setRowData', cl, { 'checkboxoperationcol': input }); 
	        	
	        	link = "<a href=\"javascript:doWsSh('"+wsSpxh+"','"+spxh+"')\"><font color=\"blue\">�鿴</font></a>";
	            $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
	        }    
	        if("1"==fsthbz)
	        	$("#dataList").jqGrid('setRowData', cl, { 'fsthbz': "����" }); 
	        else
	        	$("#dataList").jqGrid('setRowData', cl, { 'fsthbz': "�˻�" }); 
	    }
	    
	   var gridName = "dataList";
		   var a = ['xh','wsSpxh','spxh','jdxh','hstoperationcol','checkboxoperationcol','fsthbz','fsrMc',
		   'fsrq','spjzsj','cqbz','sprMc','sprq','spjg','kpsqfsMc','khMc','sqKpjeHj','sqKprq','bzsm','djrMc','bmmc','dwmc'];
			
	    Merger(gridName, 'xh', a);
	}
/**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="hykpsqsh!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">������</li>
		    <li><a href="#" id="queryBtn" class="licon04">�� ��</a></li>
		    <li><a href="#" id="pltgBtn" class="licon08">�������ͨ��</a></li>
		    <li><a href="#" id="plthBtn" class="licon09">�����˻�����</a></li>
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
			          <td width="20%" align="left">
			          &nbsp;&nbsp;&nbsp;&nbsp;
			          	<s:radio name="domain.shbz" list='#{"N":"δ���","Y":"�����"}' listKey="key" listValue="value" onclick="javascript:onDisplay();"></s:radio>
			          </td>
			          <td width="15%" align="right" id="shrqTdText">������ڣ�</td>
			          <td width="65%" id="shrqTdId">
			          		<s:textfield name="domain.rqQ" readonly="true" cssClass="ymdate"></s:textfield>
							 �� 
							<s:textfield name="domain.rqZ" readonly="true" cssClass="ymdate"></s:textfield>
			          </td>
			        <tr>
			    </table>
			</fieldset>
	  	</div>
	  	<table id="dataList"><tr><td/></tr></table> 
		<%@include file="/common/message.jsp" %>
	</div>
</s:form>
</body>
</html>
