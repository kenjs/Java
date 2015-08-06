<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>���ȳɱ����</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
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
		$("#dataList").jqGrid('showCol',["sprmc","sprq","spjg"]);
	}else{
		$("#dataList").jqGrid('hideCol',["sprmc","sprq","spjg"]);
	}
	onDisplay();
});

function onDisplay(){
	var shbz = $("[name='domain.shbz']:checked").val();
	if("Y"==shbz){
		$("#shrqTdText").show();
		$("#shrqTdId").show();
	}else{
		$("#shrqTdText").hide();
		$("#shrqTdId").hide();
	}
}

function onViewMx(ddDjxh) {
	var url = jcontextPath+"/jcgl/jctydgl!initMx.action?domain.ddDjxh="+ddDjxh;
	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
	//window.open(url);
}

function onViewPc(pcDjxh){
	var url = jcontextPath+"/jcgl/jcpcxxgl!initMx.action?domain.pcDjxh="+pcDjxh;
	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
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
			$("#dataList").jqGrid('showCol',["sprmc","sprq","spjg"]);
		}else{
			$("#dataList").jqGrid('hideCol',["sprmc","sprq","spjg"]);
		}
  
		//����������
		var url = jcontextPath+"/hygl/ddcbsh!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.shbz":shbz,"domain.rqQ":rqQ,"domain.rqZ":rqZ}		//����Ĳ�����json��ʽ
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
		    colNames:['���','pcDjxh','�����������','�������','�ڵ����','<input title="ѡ��/ȡ��ѡ��" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />',
			    		'����','��־','������','��������','����','�˷�','Ԥ��','���','������','��������','�������','�ɳ�����','��������','�ҳ�����','˾������',
			    		'�������','�ͻ�����','ddDjxh','��������','ʼ����','Ŀ�ĵ�','����','����','���',
			    		'����ʽ','ת�벿��','�ϼ�','�ָ�','�½�','����','��Ϣ��',
			    		'�����˵�ַ','�ջ��˵�ַ',
			    		'�ɳ���','�ɳ�����','�ɳ�����','��������'
			    		],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[
		      {name:'xh', index:'xh', sortable:false, width:'35', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'xh' + rowId + '\'';
			    }
			  },
			  {name:'pcDjxh',hidden:true},
			  {name:'wsspxh', index:'wsspxh', hidden:true, width:'70', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'wsspxh' + rowId + '\'';
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
			  {name:'fsrmc', index:'fsrmc', width:'60', sortable:false, align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'fsrmc' + rowId + '\'';
			    }
			  },
			  {name:'fsrq', index:'fsrq', width:'70', sortable:false, align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'fsrq' + rowId + '\'';
			    }
			  },
			  {name:'zsr', index:'zsr', width:'60', sortable:false, align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'zsr' + rowId + '\'';
			    }
			  },
			  {name:'yfHj', index:'yfHj', width:'60', sortable:false, align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'yfHj' + rowId + '\'';
			    }
			  },
			  {name:'yfYfyf', index:'yfYfyf', width:'60', sortable:false, align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'yfYfyf' + rowId + '\'';
			    }
			  },
			  {name:'pcfsMc', index:'pcfsMc', width:'35', sortable:false, align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcfsMc' + rowId + '\'';
			    }
			  },
			 {name:'sprmc', index:'sprmc', width:'60', sortable:false, align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'sprmc' + rowId + '\'';
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
			  {name:'pcdh', index:'pcdh', width:'70', sortable:false, align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcdh' + rowId + '\'';
			    }
			  },
			  {name:'clhm', index:'clhm', width:'60', sortable:false, align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'clhm' + rowId + '\'';
			    }
			  },
			  {name:'gchm', index:'gchm', width:'60', sortable:false, align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'gchm' + rowId + '\'';
			    }
			  },
			  {name:'sjxm', index:'sjxm', width:'60', sortable:false, align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'sjxm' + rowId + '\'';
			    }
			  },
			  {name:'ddbh', index:'ddbh', width:'70', sortable:false, align:'center'},
		      {name:'fhrmc', index:'fhrmc', width:'120', sortable:false, align:'center'},
		      {name:'ddDjxh', index:'ddDjxh',hidden:true, width:'100', sortable:false, align:'center'},  
		      {name:'hwmc', index:'hwmc', width:'100', sortable:false, align:'center'},  
		      {name:'sfd', index:'sfd', width:'50', sortable:false, align:'center'}, 
		      {name:'mdd', index:'mdd', width:'50', sortable:false, align:'center'},
		      {name:'sl', index:'sl', width:'60', sortable:false, align:'right'},
		      {name:'zl', index:'zl', width:'60', sortable:false, align:'right'},
		      {name:'tj', index:'tj', width:'60', sortable:false, align:'right'},
		      {name:'pchwClfsDm', index:'pchwClfsDm', width:'60', align:'center',hidden:true},
		      {name:'zrbmmc', index:'zrbmmc', width:'100', sortable:false, align:'center'},
			  {name:'zcHj', index:'zcHj', width:'40', align:'center'},
			  {name:'zcXf', index:'zcXf', width:'40', align:'center'},
			  {name:'zcYj', index:'zcYj', width:'40', align:'center'},
			  {name:'zcDf', index:'zcDf', width:'40', align:'center'},
			  {name:'zcHk', index:'zcHk', width:'40', align:'center'},
		      {name:'fhrdz', index:'fhrdz', width:'100', sortable:false, align:'right'},
		      {name:'shrdz', index:'shrdz', width:'100', sortable:false, align:'center'}, 
		      
			  {name:'pcrmc', index:'pcrmc', width:'60', sortable:false, align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcrmc' + rowId + '\'';
			    }
			  },
			  {name:'pcrq', index:'pcrq', width:'70', sortable:false, align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcrq' + rowId + '\'';
			    }
			  },
			  {name:'pcbmmc', index:'pcbmmc', width:'100', sortable:false, align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcbmmc' + rowId + '\'';
			    }
			  },
			  {name:'ssjgmc', index:'ssjgmc', width:'120', sortable:false, align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'ssjgmc' + rowId + '\'';
			    }
			  }
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: -1,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[-1],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'PC_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/ddcbsh!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
         var graduateIds = $("#dataList").jqGrid('getDataIDs');
         for (var i = 0; i < graduateIds.length; i++) {
 	        var cl = graduateIds[i];
 	        var wsspxh = jQuery("#dataList").jqGrid('getCell', cl,"wsspxh");
 	        var spxh = jQuery("#dataList").jqGrid('getCell', cl,"spxh");
 	        var jdxh = jQuery("#dataList").jqGrid('getCell', cl,"jdxh");
 	        var fsthbz = jQuery("#dataList").jqGrid('getCell', cl,"fsthbz");
 	        
 	        var ddbh = jQuery("#dataList").jqGrid('getCell', cl,"ddbh");
 	        var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh"); 
 	       var pcdh = jQuery("#dataList").jqGrid('getCell', cl,"pcdh");
 	       var pcDjxh = jQuery("#dataList").jqGrid('getCell',cl,"pcDjxh");
 	        var str="<a href=\"javascript:onViewMx("+ddDjxh+")\"><font color=\"blue\">"+ddbh+"</font></a>";
 	        var strPc="<a href=\"javascript:onViewPc("+pcDjxh+")\"><font color=\"blue\">"+pcdh+"</font></a>";
 	        
	        $("#dataList").jqGrid('setRowData', cl, { 'checkboxoperationcol': input }); 
	        var shbz = $("input[name='domain.shbz']:checked").val();
	        var link="";
	        var input = "";
	        if("N"==shbz){
	        	input = "<input type=\"checkbox\" name=\"xhs\" value=\""+wsspxh+"#"+spxh+"#"+jdxh+"\" />";
	         	link = "<a href=\"javascript:doWsSh('"+wsspxh+"','"+spxh+"')\"><font color=\"blue\">���</font></a>";
	        }else{
	        	input = "<input type=\"checkbox\" disabled=\"disabled\" name=\"xhs\" value=\""+wsspxh+"#"+spxh+"#"+jdxh+"\" />";
	        	link = "<a href=\"javascript:doWsSh('"+wsspxh+"','"+spxh+"')\"><font color=\"blue\">�鿴</font></a>";
	        }    
	        $("#dataList").jqGrid('setRowData', cl, { 'checkboxoperationcol': input }); 
	        $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link });
	        
	        if("1"==fsthbz)
	        	$("#dataList").jqGrid('setRowData', cl, { 'fsthbz': "����" }); 
	        else
	        	$("#dataList").jqGrid('setRowData', cl, { 'fsthbz': "�˻�" });  
	        $("#dataList").jqGrid('setRowData', cl, { 'ddbh': str });  
	        $("#dataList").jqGrid('setRowData', cl, { 'pcdh': strPc });  
 	    }
            
         var gridName = "dataList";
 		 var a = ['xh','hstoperationcol','checkboxoperationcol','fsthbz','fsrmc','fsrq',
 		          'spjzsj','cqbz','sprmc','sprq','spjg','zsr','yfHj','yfYfyf','pcfsMc','pcdh','pcrq','pcfsmc','zcfsmc',
 		          'clhm','gchm','sjxm','pcrmc','pcbmmc','ssjgmc'];
 			
 	     Merger(gridName, 'xh', a);
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="ddcbsh!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
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
		        </tr>
		    </table>
		</fieldset>
  </div>
		<!-- ��ҳ��� id����ΪdataList -->
		<table id="dataList"><tr><td/></tr></table> 
		<%@include file="/common/message.jsp" %>
	</div>
</s:form>
</body>
</html>
