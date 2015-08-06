<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>����-����</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>

<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		initHykhData(300);
		
		var sjJgbm = $("#mainForm_domain_ssJgbm").val();
		bmInit(sjJgbm, '', "domain.djJgbm", "mainForm_domain_djJgbm", "Y", "Y");
		
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//������ť�¼�
			$("#addBtn").click(function(){
				popwindow(jcontextPath+"/hygl/ddpzxxgl!initMx");
			});
			//��ʼ�����
			initDataGrid();
						

	});

    function onUpdate(pzDjxh){
    	var url = jcontextPath+"/hygl/ddpzxxgl!initMx.action?domain.pzDjxh="+pzDjxh+"&num="+Math.random();
    	navigateMenu(url,'���������޸�','true');
    	//popwindow(jcontextPath+"/hygl/ddpzxxgl!initMx?domain.pzDjxh="+pzDjxh);
    }
    
    var keyValue = "";
	function onDelete( pzDjxh){
		keyValue = pzDjxh;
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.pzDjxh":keyValue};
		 var url = jcontextPath+"/hygl/ddpzxxgl!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}	
	
	function onPzToPc(pzDjxh) {
		var jsonObj = {"domain.pzDjxh":pzDjxh, "domain.listPc":"Y"};
		var url = jcontextPath+"/hygl/ddpzxxgl!initPcxxFromPz";
		showMessage();
		ajaxCommon(url,jsonObj,"doInitPcSuc");
	}
	
	function doInitPcSuc(data) {
		var pchwLsxh = data.domain.pchwLsxh;
		var url = jcontextPath + "/hygl/hypcxxgl!initMx.action?domain.pcfsDm=2&domain.pchwLsxh="+pchwLsxh;
		navigateMenu(url,'�����ɳ�','true');
	}
    
	
	function onQingdan(pzDjxh){
		var url = jcontextPath+"/hygl/ddpzxxgl!onQingdan.action?domain.pzDjxh="+pzDjxh;
		//navigateMenu(url,'�����嵥','true');
		window.open(url, "_blank");
	}
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var djJgbm = $("#mainForm_domain_djJgbm").val();
		var fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		var fhrMc = $("#mainForm_domain_fhrMc").val();
		var ddbh = $("#mainForm_domain_ddbh").val();  
		
		//����������
		var url = jcontextPath+"/hygl/ddpzxxgl!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.djJgbm":djJgbm,
	 			"domain.fhrMc":encodeURI(fhrMc),"domain.ddbh":encodeURI(ddbh),"domain.fhrDjxh":fhrDjxh}								//����Ĳ�����json��ʽ
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
		    colNames:['���','����', '���صǼ����','��վ','�����ͺ�','���س���(��)','�������(��)','��������',
		              '�ͻ�','��������', 'ʼ����', 'Ŀ�ĵ�', '����','����', '���','��װ', 
				     '������ַ', 'Ҫ�󷢻�����', '�ջ���','�ջ���ַ','Ҫ�󵽴�����'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[
				{name:'pageXh', index:'pageXh', sortable:false, width:'30', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pageXh' + rowId + '\'';
				    }
			    },
				{name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'hstoperationcol' + rowId + '\'';
				    }
				},
		        {name:'pzDjxh', index:'pzDjxh',hidden:true, width:'100', align:'center'}, 
		        {name:'hzmc', index:'hzmc', width:'100', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'hzmc' + rowId + '\'';
				    }
				},
				{name:'clxh', index:'clxh', width:'60', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'clxh' + rowId + '\'';
				    }
				}, 
		        {name:'pzCz', index:'pzCz', width:'70', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pzCz' + rowId + '\'';
				    }
				},  
		        {name:'pzTj', index:'pzTj', width:'70', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pzTj' + rowId + '\'';
				    }
				},  
		        {name:'pzsr', index:'pzsr', width:'70', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pzsr' + rowId + '\'';
				    }
				},  

		      {name:'fhrMc', index:'fhrMc', width:'120', align:'center'},
			  {name:'hwMc', index:'hwMc', width:'100', align:'center'},
		      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'40', align:'center'},
		      {name:'shrXzqhMc', index:'shrXzqhMc', width:'40', align:'center'},
		      {name:'sl', index:'sl', width:'50', align:'right'},  
		      {name:'zl', index:'zl', width:'50', align:'right'},
		      {name:'tj', index:'tj', width:'50', align:'right'},
		      {name:'hwbz', index:'hwbz', width:'50', align:'center'},
		      {name:'fhrDz', index:'sfdMc', width:'200', align:'center'},
		      {name:'yqFhrq', index:'yqFhrq', width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      {name:'shrMc', index:'shrMc', width:'100', align:'center'},
		      {name:'shrDz', index:'mddMc', width:'200', align:'center'},
		      {name:'yqDdrq', index:'yqDdrq', width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}		      
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'PZ_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/ddpzxxgl!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"pzDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">�޸�</font></a>"
                		+ " <a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">ɾ��</font></a>"
                		+ "<br/><a href=\"javascript:onPzToPc('"+val+"')\"><font color=\"blue\">�ɳ�</font></a>"
                		+" <a href=\"javascript:onQingdan('"+val+"')\"><font color=\"blue\">�嵥</font></a>";
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
            
            var gridName = "dataList";
     	    var a = ['pageXh','hstoperationcol','hzmc','clxh','pzCz','pzTj','pzsr'];
      		
            Merger(gridName, 'pzDjxh', a);
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="ddpzxxgl!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.ssJgbm" />
	
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

	<div class="right_cont" id="maincont">
		<div id="divQuery">
		<fieldset>
			<legend>��ѯ����</legend>
		   <table width="99%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		  			<td width="8%" align="right">���ز��ţ�</td>
          			<td width="21%">
          				<select id="mainForm_domain_djJgbm" name="domain.djJgbm" class="select" >
          				</select>
		  			</td>
		        	  <td width="8%" align="right">�ͻ����ƣ�</td>
			          <td width="21%">
			          	<s:hidden name="domain.fhrDjxh"></s:hidden>
	  					<div class="inputsel" style="width: 230px; ">
	  						<s:textfield name="domain.fhrMc" cssClass="pop_input noborder inputext" cssStyle="width:200px;"></s:textfield>
	  						<a href="#" class="icon_arrow" id="fhr" onFocus="this.blur()"></a>
	  					</div>
				  		<div class="inputsc">
			              <div id="inputSel_fhr" class="inputselcont inputselFixedSize ac_results">
			              </div>
			            </div>
			          </td>
			          <td width="8%" align="right">�������ţ�</td>
			          <td width="21%"><s:textfield name="domain.ddbh" cssClass="pop_input noborder" /></td>
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
