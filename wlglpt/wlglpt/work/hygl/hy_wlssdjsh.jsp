<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>����-�ɳ�-�ص�</title>
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
					
			
			var sjJgbm = $("#mainForm_domain_dwDm").val();
			bmInit(sjJgbm, '', "domain.pcbm4Query", "mainForm_domain_pcbm4Query", "Y", "Y");
			//��ʼ�����
			initDataGrid();
			onDisplay();
			

	});

	function checkNum() {
		var ch = $(":checkbox[name='dj']");
		var num = 0;
		for(var i=0;i<ch.length;i++){
			if(ch[i].checked){
				++num; 
			}
		}
		if(num < 1){
			return false;
		}
		return true;
	}
	
	var pc='';
    function onUpdate(pcDjxh){
    	pc=pcDjxh;
    	var ul=jcontextPath+"/hygl/wlssdj!checkWlDj";
    	var json={"domain.pcDjxh":pcDjxh};
    	ajaxCommon(ul,json,"toUpdate");
    }
    function toUpdate(data){
    	if(data.domain.tager=='N'){
    		var url = jcontextPath+"/hygl/wlssdj!initMx.action?domain.pcDjxh="+pc;
        	window.showModalDialog(url,window,"dialogHeight:405px;dialogWidth:675px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
        	onRefresh();
    	}
    	else{
    		showConfirm("���ɳ����ѵǼ�������ʧ,������ͨ��������ʧ�ǼǵǼǹ�������޸�,Ҳ���Ե��ȷ������������ʧ�Ǽ�","okUpdate");
    	} 
    
    }
    function okUpdate(){
    	var url = jcontextPath+"/hygl/wlssdj!initMx.action?domain.pcDjxh="+pc;
    	window.showModalDialog(url,window,"dialogHeight:405px;dialogWidth:675px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
    	onRefresh();
    }
 
    
    function onWlSsSh(wsspxh,spxh){  
        var url = jcontextPath+"/common/wsspCommon!init.action?domain.wsspxh="+wsspxh+"&domain.spxh="+spxh;
    	window.showModalDialog(url,window,"dialogHeight:900px;dialogWidth:920px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
    	//window.open(url);
    	//onRefresh();
} 
    
    function checkBoxAll(obj){
    	$(":checkbox[name='checkTel']").attr("checked",obj.checked);
    }
	
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
    function onViewPc(pcDjxh){
    	var url = jcontextPath+"/jcgl/jcpcxxgl!initMx.action?domain.pcDjxh="+pcDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    }
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var radio=$(":radio[name='domain.shbz']")[0].checked?"N":"Y";
		//alert(radio);
		var rqQ = trim($("#mainForm_domain_rqQ").val()); 
		var rqZ = trim($("#mainForm_domain_rqZ").val()); 
		//����������
		var url = jcontextPath+"/hygl/wlssdjsh!query.action";   
	
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.rqQ":rqQ,"domain.rqZ":rqZ,"domain.shbz":radio}								//����Ĳ�����json��ʽ
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
		    colNames:['���','<input title="ѡ��/ȡ��ѡ��" type="checkbox"  onclick="checkAllJGridChk(event,this,\'xhs\');" />','����','��־','������','��������',
		             '������','��������','�������','���úϼ�','�ɳ�����','�ɳ�����'
		              ,'��������','�ҳ�����','˾������','���˷�','�ɳ�������','�ɳ�����','��������','','','ת�벿��','ʼ����','Ŀ�ĵ�','��������','��װ','����'
		              ,'����','���','��������','����������','�����˵�ַ','Ҫ�󷢻�����','�ջ�������','�ջ��˵�ַ','Ҫ�󵽴�����'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[

		        {name:'xh', index:'xh', sortable:false, width:'35', align:'center', 
		       			cellattr: function(rowId, tv, rawObject, cm, rdata) {
		       			   return 'id=\'xh' + rowId + '\'';
		       		    }
		       	},        
		        {name:'checkbox', index:'checkbox', width:'35', sortable:false, align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'checkbox' + rowId + '\'';
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
			
			   {name:'fsrmc', index:'fsrmc', width:'80', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'fsrmc' + rowId + '\'';
				    }
			   },
			   {name:'fsrq', index:'fsrq', width:'70', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'fsrq' + rowId + '\'';
				    }
			   },
			 
			   {name:'sprmc', index:'sprmc', width:'80', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'sprmc' + rowId + '\'';
				    }
			   },
			   {name:'sprq', index:'sprq', width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}, 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'sprq' + rowId + '\'';
				    }
			   },
			   {name:'spjg', index:'spjg', width:'80', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'spjg' + rowId + '\'';
				    }
			   },
			   {name:'fyhj', index:'fyhj', width:'80', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'fyhj' + rowId + '\'';
				    }
			   },
			   {name:'pcdh', index:'pcdh', width:'70', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcdh' + rowId + '\'';
				    }
			   },
			   {name:'pcrq', index:'pcrq', width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcrq' + rowId + '\'';
				    }
			   },
			 
			   {name:'clhm', index:'clhm', width:'60', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'clhm' + rowId + '\'';
				    }
			   },
			   {name:'gchm', index:'gchm', width:'60', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'gchm' + rowId + '\'';
				    }
			   },
			   {name:'sjxm', index:'sjxm', width:'60', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'sjxm' + rowId + '\'';
				    }
			   },
			   {name:'yfhj', index:'yfhj', width:'50', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'yfhj' + rowId + '\'';
				    }
			   },
			   {name:'pcrmc', index:'pcrmc', width:'70', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcrmc' + rowId + '\'';
				    }
			   },
			   {name:'pcbmmc', index:'pcbmmc', width:'100', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcbmmc' + rowId + '\'';
				    }
			   },
			   {name:'ssjgmc', index:'ssjgmc', width:'100', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'ssjgmc' + rowId + '\'';
				    }
			   },
			   {name:'wsspxh', index:'wsspxh', sortable:false,hidden:true, width:'0', align:'center'},
			   {name:'spxh', index:'spxh', sortable:false,hidden:true, width:'0', align:'center'},
			   {name:'zrbmmc', index:'zrbmmc', sortable:false, width:'100', align:'center'},
			   {name:'sfd', index:'sfd', sortable:false, width:'60', align:'center'},
			   {name:'mdd', index:'mdd', sortable:false, width:'60', align:'center'},
			   {name:'hwmc', index:'hwmc', sortable:false, width:'100', align:'center'},
			   {name:'bz', index:'bz', sortable:false, width:'80', align:'center'},
			   {name:'sl', index:'sl', sortable:false, width:'60', align:'center'},
			   {name:'zl', index:'zl', sortable:false, width:'60', align:'center'},
			   {name:'tj', index:'tj', sortable:false, width:'60', align:'center'},
			   {name:'jssl', index:'jssl', sortable:false, width:'100', align:'right'},
			   {name:'fhrmc', index:'fhrmc', sortable:false, width:'100', align:'center'},
			   {name:'fhrdz', index:'fhrdz', sortable:false, width:'100', align:'center'},
			   {name:'yqfhrq', index:'yqfhrq', sortable:false, width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
			   {name:'shrmc', index:'shrmc', sortable:false, width:'100', align:'center'},
			   {name:'shrdz', index:'shrdz', sortable:false, width:'120', align:'center'},
			   {name:'yqddrq', index:'yqddrq', sortable:false, width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}
		  
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: -1,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:-1,		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'PCDH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
		    sortorder: 'DESC',				//Ĭ��������
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
		  $("#dataList").jqGrid('navButtonAdd','#pager',{
		       caption:"", 
		       onClickButton : function () { 
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/hypchd!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
    		var radio=$(":radio[name='domain.shbz']")[0].checked?'N':'Y';
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var pcdh = jQuery("#dataList").jqGrid('getCell', cl,"pcdh"); 
                var xh = jQuery("#dataList").jqGrid('getCell', cl,"xh"); 
                var fsthbz = jQuery("#dataList").jqGrid('getCell', cl,"fsthbz");
                var wsspxh = jQuery("#dataList").jqGrid('getCell', cl,"wsspxh"); 
                var jdxh = jQuery("#dataList").jqGrid('getCell', cl,"jdxh");
                var spxh = jQuery("#dataList").jqGrid('getCell', cl,"spxh"); //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var check="<input type=\"checkbox\" name=\"xhs\" value=\""+wsspxh+"#"+spxh+"#"+jdxh+"\"/>";
                var strPc="<a href=\"javascript:onViewPc("+1+")\"><font color=\"blue\">"+pcdh+"</font></a>";
                var input = "";
               if(radio=='N'){
            	   input = "<input type=\"checkbox\" name=\"xhs\" value=\""+wsspxh+"#"+spxh+"#"+jdxh+"\" />";
			       $("#dataList").jqGrid('setRowData', cl, { 'checkbox': input }); 
			        
            	   var link = "<a href=\"javascript:doWsSh('"+wsspxh+"','"+spxh+"')\"><font color=\"blue\">���</font></a>";
               }
               else{
	               input = "<input type=\"checkbox\" name=\"xhs\" disabled=\"disabled\" value=\""+wsspxh+"#"+spxh+"#"+jdxh+"\" />";
		           $("#dataList").jqGrid('setRowData', cl, { 'checkbox': input });
		           
            	   var link = "<a href=\"javascript:doWsSh('"+wsspxh+"','"+spxh+"')\"><font color=\"blue\">�鿴</font></a>";
               }
              if(fsthbz=='1'){
            	  $("#dataList").jqGrid('setRowData', cl, { 'fsthbz': "����" }); 
              }
              else{
            	  $("#dataList").jqGrid('setRowData', cl, { 'fsthbz': "�˻�" }); 
              }
              $("#dataList").jqGrid('setRowData', cl, { 'pcdh': strPc });
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link });
                $("#dataList").jqGrid('setRowData', cl, { 'xuhao': i+1 });  

            }
            var gridName = "dataList";
	   		var a = ['checkbox','hstoperationcol','fsthbz','xh','fsrmc','fsrq','sprmc','sprq','spjg','fyhj','pcdh','pcrq','pcfsmc','zcfsmc','clhm','gchm','sjxm','yfhj','pcrmc','pcbmmc','ssjgmc'];
 		
       		Merger(gridName, 'xh', a);
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="hypchd!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	
	<s:hidden name="fhrData" />
	
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
	<div id="maincont" style="display: none;"></div>
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
			          <td align="right" id="shrqTdText">������ڣ�</td>
			          <td id="shrqTdId">
			          		<s:textfield name="domain.rqQ" readonly="true" cssClass="ymdate"></s:textfield>
							 �� 
							<s:textfield name="domain.rqZ" readonly="true" cssClass="ymdate"></s:textfield>
			          </td>
			  </tr>
		   </table>
		 
		 
		</fieldset>
	  </div>
	  </div>
		<!-- ��ҳ��� id����ΪdataList -->
		<table id="dataList"><tr><td/></tr></table> 
		<!-- ��ҳ���� -->
	
		
		
		<%@include file="/common/message.jsp" %>
</s:form>
</body>
</html>
