<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>����-�ɳ���Ϣ����</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
			document.getElementById("maincont").onmousewheel=hideHelpWindow;
			initHykhData(300);
			
		
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//��ʼ�����
			initDataGrid();
	});
	
	function onView(pcDjxh) {
		var url = jcontextPath+"/jcgl/jcpcxxgl!initMx.action?domain.pcDjxh="+pcDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    	//window.open(url);
	}
	
	function onViewDd(ddDjxh) {
		var url = jcontextPath+"/jcgl/jctydgl!initMx.action?domain.ddDjxh="+ddDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    	//window.open(url);
	}
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var hwmc = $("#mainForm_domain_hwmc").val();
		var sl = $("#mainForm_domain_sl").val();
		
		var pcdh = $("#mainForm_domain_pcdh").val();
		var tydh = $("#mainForm_domain_tydh").val();
		var hdh = $("#mainForm_domain_hdh").val();
		
		var fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		var fhrMc = $("#mainForm_domain_fhrMc").val();
		var sfd = $("#mainForm_domain_sfd").val();
		var mdd = $("#mainForm_domain_mdd").val();
		
		var clhm = $("#mainForm_domain_clhm").val();
		var rqq = $("#mainForm_domain_rqq").val();
		var rqz = $("#mainForm_domain_rqz").val();
		var pcfs = $(":radio[name='domain.pcfs']:visible:checked").val();
		//alert(pcfs);
		//����������
		var url = jcontextPath+"/jcgl/fcqkmx!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.hwmc":encodeURI(hwmc),"domain.sl":sl,"domain.pcdh":encodeURI(pcdh),"domain.tydh":tydh,
		 		"domain.hdh":hdh,"domain.fhrDjxh":fhrDjxh,"domain.fhrMc":encodeURI(fhrMc),"domain.sfd":encodeURI(sfd),
		 		"domain.mdd":encodeURI(mdd),"domain.clhm":encodeURI(clhm),"domain.rqq":rqq,"domain.rqz":rqz,"domain.pcfs":pcfs}								//����Ĳ�����json��ʽ
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
		    colNames:['���','','�ɳ�����','���˵�λ','�ɳ���','�ɳ�����','���˵���','','�ͻ���λ','�µ�����','��վ','�ջ���','��������',
		              '����','����','�տʽ','֧���ϼ�','֧���ָ�','�ص���','˾���˷�','Ԥ���˷�','״̬'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[
				{name:'pageXh', index:'pageXh', sortable:false, width:'30', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pageXh' + rowId + '\'';
				    }
				},
				{name:'pcDjxh', index:'pcDjxh', hidden:true, width:'80', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcDjxh' + rowId + '\'';
				    }
				},
				{name:'pcdh', index:'pcdh', sortable:false, width:'80', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcdh' + rowId + '\'';
				    }
				},
				{name:'clhm', index:'clhm', sortable:false, width:'80', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'clhm' + rowId + '\'';
				    }
				},
				{name:'pcrMc', index:'pcrMc', sortable:false, width:'80', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcrMc' + rowId + '\'';
				    }
				},
				{name:'pcrq', index:'pcrq', sortable:false, width:'80', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcrq' + rowId + '\'';
				    }
				},
				{name:'tydh', index:'tydh', width:'80', align:'center'},
				{name:'ddDjxh', index:'ddDjxh', width:'80', align:'center',hidden:true},
				{name:'khmc', index:'khmc', width:'80', align:'center'},
				{name:'xdrq', index:'xdrq', width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
				{name:'shrDz', index:'shrDz', width:'120', align:'center'},
				{name:'shrMc', index:'shrMc', width:'80', align:'center'},
				{name:'hwmc', index:'hwmc', width:'80', align:'center'},
				{name:'sl', index:'sl', width:'50', align:'center'},
				{name:'zl', index:'zl', width:'50', align:'center'},
				{name:'skfs', index:'skfs', width:'80', align:'center',hidden:true},
				{name:'zcHj', index:'zcHj', width:'80', align:'center'},
				{name:'zcXf', index:'zcXf', width:'80', align:'center'},
				{name:'hdh', index:'hdh', width:'80', align:'center'},
				{name:'sjyf', index:'sjyf', sortable:false, width:'80', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'sjyf' + rowId + '\'';
				    }
				},
				{name:'yfyf', index:'yfyf', sortable:false, width:'80', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'yfyf' + rowId + '\'';
				    }
				},
				{name:'spzt', index:'spzt', sortable:false, width:'50', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'spzt' + rowId + '\'';
				    }
				}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: 10,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[10,20,50,100],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'PC_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/jcgl/fcqkmx!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
	function myGridComplete() {
        var graduateIds = $("#dataList").jqGrid('getDataIDs');
		
        for (var i = 0; i < graduateIds.length; i++) {
            var cl = graduateIds[i];
            var pcDjxh = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh"); 
            var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh"); 
            var pcdh = jQuery("#dataList").jqGrid('getCell', cl, "pcdh");
            var tydh = jQuery("#dataList").jqGrid('getCell', cl, "tydh"); 
            var pageXh = jQuery("#dataList").jqGrid('getCell', cl, "pageXh"); 
            if(pageXh == 0){
            	$("#dataList").jqGrid('setRowData', cl, { 'pageXh': '�ϼ�' });
            }
            
            var link = "<a href=\"javascript:onView('"+pcDjxh+"')\"><font color=\"blue\">"+pcdh+"</font></a>";
            var link2 = "<a href=\"javascript:onViewDd('"+ddDjxh+"')\"><font color=\"blue\">"+tydh+"</font></a>";
           $("#dataList").jqGrid('setRowData', cl, { 'pcdh': link }); 
           $("#dataList").jqGrid('setRowData', cl, { 'tydh': link2 }); 
        } 
      
        var gridName = "dataList";
 	    var a = ["pageXh","pcDjxh","pcdh","clhm","pcrMc","pcrq","sjyf","yfyf","spzt"];
  		
        Merger(gridName, 'pcDjxh', a);
 }
	
     /**************************************��ҳ����****************************************/
</script>
</head>
<body>
<s:form action="fcqkmx!query" namespace="/jcgl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="jsonData" />
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">������</li>
		    <li><a href="#" id="queryBtn" class="licon04">�� ��</a></li>
		    <li><a href="#" id="closeBtn" class="licon03">�� ��</a></li>
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
		        	<td width="8%" align="right">�ɳ���λ��</td>
          			<td width="21%">
          				<sys:csGsList myClass="select" myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y"/>
		  			</td>
		  			<td width="8%" align="right">�������ƣ�</td>
		          <td width="25%"><s:textfield name="domain.hwmc" cssClass="pop_input noborder" /></td>
		          <td width="8%" align="right">������</td>	
		          <td width="25%"><s:textfield name="domain.sl" cssClass="pop_input noborder" /></td>	          
	          	</tr>
	          	<tr>	          		
		  			<td align="right">�ɳ����ţ�</td>
          			<td>
          				<s:textfield name="domain.pcdh" cssClass="pop_input noborder" />
		  			</td>
	        	  <td align="right">���˵��ţ�</td>
		          <td>
		          		<s:textfield name="domain.tydh" cssClass="pop_input noborder" />
	          	  </td>
	          	  <td align="right">�ص��ţ�</td>
		          <td>
		          		<s:textfield name="domain.hdh" cssClass="pop_input noborder" />
	          	  </td>
	          	</tr>
	          	<tr>
	        	  <td align="right">�ͻ����ƣ�</td>
		          <td>
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
		          <td align="right">ʼ���أ�</td>
		          <td><s:textfield name="domain.sfd" cssClass="pop_input noborder" /></td>
		          <td align="right">Ŀ�ĵأ�</td>
		          <td><s:textfield name="domain.mdd" cssClass="pop_input noborder" /></td>
		        </tr>
		        <tr>
		        	<td align="right">�����̣�</td>
		          <td >
		          	<s:textfield name="domain.clhm" cssClass="pop_input noborder" />
		          </td>
		           <td align="right">�ɳ����ڣ�</td>
		          <td >
		          <sys:dateFirstDMonth myName="domain.rqq" myId="mainForm_domain_rqq" myClass="ymdate" />
	          			��
	          		<sys:dateCurrentDayTag myName="domain.rqz" myId="mainForm_domain_rqz" myClass="ymdate" />
		         
		          </td>
		          <td align="right">�ɳ���ʽ��</td>
		          <td>
		          	<s:radio list="#{1:'���',2:'����' }" listKey="key" listValue="value" name="domain.pcfs" value="2"></s:radio>
		          </td>
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
