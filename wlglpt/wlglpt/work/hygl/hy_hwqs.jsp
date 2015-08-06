<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>����-����ǩ��</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
			
			
			$("#mainForm_domain_ssJgbm").change(function(){
				$("[name='domain.fhrMc']").unbind();
				initHykhData(300, $(this).val(), $("#mainForm_domain_pcJgbm").val());
			});
			
			$("#mainForm_domain_pcJgbm").change(function(){
				initRy();
				$("[name='domain.fhrMc']").unbind();
				initHykhData(300, $("#mainForm_domain_ssJgbm").val(), $(this).val());
			});
			
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//��������
			$("#plScSendBtn").click(function(){
				var wsDm="303004";//���õǼ�������
				plScSend(wsDm,"");
			});
			
			//��������
			$("#plJsBtn").click(function(){
				var pljsxhs = $(":checked[name='pljs']");
	  			if (pljsxhs.length <= 0) {
					showAlert("��ѡ����Ҫ�������յļ�¼��");
					return;
	  			}
				var pljsStr = '';
				var pljs = $('[name=pljs]:checkbox');
				pljs.each(function(){
					if(this.checked){
						pljsStr += this.value + '|';
					}
				});
				//alert(pljsStr);
				var jsonObj = {"domain.pljsStr":pljsStr};
		 		var url = jcontextPath+"/hygl/hypchddj!plJs";   
         		ajaxCommon(url,jsonObj,"doPljsSu");   
			});
			
			//��ʼ�����
			initDataGrid();
			initRy();
			var sjJgbm = $("#mainForm_domain_ssJgbm").val();
			bmInit(sjJgbm, '', "domain.pcJgbm", "mainForm_domain_pcJgbm", "Y", "Y");			

	});

	function initRy() {
		var sj = $("#mainForm_domain_pcJgbm").val();
		commonInit("BMYH", sj, '', "domain.pcrCzyDjxh", "mainForm_domain_pcrCzyDjxh", "Y", false);
	}
	
	function onHddj(pcDjxh, wfhDjxh,hdDjxh) {
		var url = jcontextPath+"/hygl/hypchddj!initMx.action?domain.pcDjxh="+pcDjxh+"&domain.wfhDjxh="+wfhDjxh
								+"&domain.hdDjxh="+hdDjxh+"&num="+Math.random();
    	window.showModalDialog(url,window,"dialogHeight:450px;dialogWidth:555px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
    	//window.open(url);
    	onRefresh();
	}
	
	var hdDjxhGolbal;
	function onDelHddj(hdDjxh) {
		hdDjxhGolbal = hdDjxh;
		showConfirm("ȷ��Ҫ������","doDelHddj");
	}
	
	function doDelHddj() {
		 var jsonObj = {"domain.hdDjxh":hdDjxhGolbal};
		 var url = jcontextPath+"/hygl/hypchddj!delete";   
         ajaxCommon(url,jsonObj , "doDelSuc");  
	}
	
    function doDelSuc(){     
        showAlert("�����ɹ���");
        onRefresh();
	}	
    
    function doPljsSu() {
    	showAlert("�������ճɹ���");
        onRefresh();
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
	 
	 function toUpdate(pcDjxh,wfhDjxh,ddDjxh,xh,qsDjxh){
		// alert(pcDjxh+wfhDjxh+ddDjxh+"=="+xh)
		var url=jcontextPath+"/hygl/hwqs!initMx.action?domain.pcDjxh="+pcDjxh+"&domain.wfhdjxh="+wfhDjxh+"&domain.ddDjxh="+ddDjxh+"&domain.xh="+xh+"&domain.hwqsDjxh="+qsDjxh;
		window.showModalDialog(url,window,"dialogHeight:300px;dialogWidth:550px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
		onRefresh();
	 }
	var val="";
	var djxh="";
	 function toDlete(qsDjxh,pcDjxh){
		 val=qsDjxh;
		 djxh=pcDjxh
		 showConfirm("ȷ��Ҫ����ô��","okDlete");
		 
	 }
	 
	 function okDlete(){
		 var url=jcontextPath+"/hygl/hwqs!delete";
		 var jsonObj={"domain.hwqsDjxh":val,"domain.pcDjxh":djxh};
		 ajaxCommon(url,jsonObj,"deleteAfter");
		
	 }
	 function deleteAfter(){
		 showSuccess("�����ɹ���", "onRefresh");
		
	 }
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var zt = $("input[name='domain.zt']:checked").val();
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		pcrqq = $("#mainForm_domain_pcrqq").val();
		pcrqz = $("#mainForm_domain_pcrqz").val(); 
		if (pcrqq == "" || pcrqz == "") {
			showAlert("�ɳ����ڲ���Ϊ�գ�");
			return;
		}
		var pcdh = $("#mainForm_domain_pcdh").val();
		var hdbh = $("#mainForm_domain_hdbh").val();
		var hwMc = $("#mainForm_domain_hwMc").val();
		var shrMc = $("#mainForm_domain_shrMc").val();
		//var sfXsFgs=$(":checkbox[name='domain.sfXsFgs']")[0].checked?'1':'2';
		//����������
		var url = jcontextPath+"/hygl/hwqs!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.pcrqq":pcrqq,
	 			"domain.pcrqz":pcrqz,"domain.pcdh":encodeURI(pcdh),
	 			"domain.zt":zt,"domain.hdbh":encodeURI(hdbh),"domain.hwMc":encodeURI(hwMc),"domain.shrMc":encodeURI(shrMc)}							//����Ĳ�����json��ʽ
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
		    colNames:['���','�ɳ��Ǽ����',
		              '����','״̬','�ص����','�ͻ�����','�������','��������','δ�����Ǽ����','����','ʼ����', 
		              'Ŀ�ĵ�','�ջ���','�ջ���ַ', 'ǩ����', 'ǩ������', '�ɳ�����','������', 
		              '�ɳ�����' , '�����Ǽ����','�������','ǩ�յǼ����'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[
		       {name:'px', index:'px', width:'25', sortable:false,align:'center'},
			  {name:'pcDjxh', index:'pcDjxh', hidden:true,width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcDjxh' + rowId + '\'';
			    }
			  },
			  {name:'hstoperationcol', index:'', width:'65', sortable:false,align:'center'},
			  {name:'zt', index:'zt', width:'45', align:'center'},
			  {name:'hdbh', index:'hdbh',width:'80', align:'center'},
			  {name:'fhrMc', index:'fhrMc', width:'130', align:'center'},
			  {name:'ddbh', index:'ddbh', width:'70', align:'center'},
			  {name:'hwMc', index:'hwMc', width:'100', align:'center'},
			  {name:'wfhdjxh', index:'wfhdjxh',hidden:true, width:'', align:'center'},
			  {name:'hwSl', index:'hwSl', width:'50', align:'center'},
			  {name:'sfd', index:'sfd', width:'70', align:'center'},
			  {name:'mdd', index:'mdd', width:'70', align:'center'},
			  {name:'shrMc', index:'shrMc', width:'100', align:'center'},
			  {name:'shrDz', index:'shrDz', width:'150', align:'center'},
		      {name:'qsrmc', index:'qsrmc', width:'70', align:'center'},
		      {name:'qsrq', index:'qsrq', width:'70', align:'center'},
		      
		    
			
			  {name:'pcdh', index:'pcdh', width:'85', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcdh' + rowId + '\'';
			    }
			  },
			  {name:'sjXm', index:'sjXm', width:'130', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'sjXm' + rowId + '\'';
			    }
			  },
			  {name:'pcrq', index:'pcrq', width:'70', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcrq' + rowId + '\'';
			    }
			  },
			  {name:'ddDjxh', index:'ddDjxh',hidden:true, width:'60', align:'center'},
			  {name:'xh', index:'xh',hidden:true, width:'60', align:'center'},
			  {name:'hwqsDjxh', index:'hwqsDjxh',hidden:true, width:'60', align:'center'}
			
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum %>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[10,20,50,100],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/hwqs!download");
				   $("#mainForm").submit();
		       } 
		 }); 
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
       var graduateIds = $("#dataList").jqGrid('getDataIDs');
       var xtcsSfsp = $("#mainForm_domain_xtcsSfsp").val();
		
       for (var i = 0; i < graduateIds.length; i++) {
           var cl = graduateIds[i];
           var pcDjxh = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh"); 
           var pcdh = jQuery("#dataList").jqGrid('getCell', cl,"pcdh"); 
           var wfhDjxh = jQuery("#dataList").jqGrid('getCell', cl,"wfhdjxh");
           var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh"); 
           var qsrmc = jQuery("#dataList").jqGrid('getCell', cl,"qsrmc"); 
           var xh = jQuery("#dataList").jqGrid('getCell', cl,"xh"); 
           var qsDjxh = jQuery("#dataList").jqGrid('getCell', cl,"hwqsDjxh"); 
           var pcdh = jQuery("#dataList").jqGrid('getCell', cl,"pcdh"); 
           var zt="";
           var str="";
           if(qsrmc==''){
        	   zt="<font color=\"red\">δǩ��</font>"
        	   str="<a href=\"javascript:toUpdate('"+pcDjxh+"','"+wfhDjxh+"','"+ddDjxh+"','"+xh+"','')\"><font color=\"blue\">�Ǽ�</font></a>";
           }
           else{
        	   zt="<font color=\"black\">ǩ��</font>";
        	   str="<a href=\"javascript:toUpdate('"+pcDjxh+"','"+wfhDjxh+"','"+ddDjxh+"','"+xh+"','"+qsDjxh+"')\"><font color=\"blue\">�޸�</font></a>"+
        	   " <a href=\"javascript:toDlete('"+qsDjxh+"','"+pcDjxh+"')\"><font color=\"blue\">����</font></a>"
        	   ;
           }
           var strPc="<a href=\"javascript:onViewPc("+pcDjxh+")\"><font color=\"blue\">"+pcdh+"</font></a>";
           $("#dataList").jqGrid('setRowData', cl, { 'pcdh': strPc });
           $("#dataList").jqGrid('setRowData', cl, { 'px': i+1 });
           $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': str });
   			$("#dataList").jqGrid('setRowData', cl, { 'zt': zt });
       }
        
       var gridName = "dataList";
	   var a = ['pcdh','sjXm','pcrq'];
 		
       Merger(gridName, 'pcDjxh', a);
    }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="hypchddj!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	
	<s:hidden name="dropDownData"></s:hidden>
	
	
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
		        	<td width="10%" align="right">ҵ��λ��</td>
          			<td width="21%">
          				<sys:gsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y" myClass="select"></sys:gsList>
		  			</td>
		  		  <td align="right" width="10%">�ɳ����ţ�</td>
		          <td  width="21%"><s:textfield name="domain.pcdh" cssClass="pop_input noborder" /></td>
	        	  <td align="right" width="10%">�ɳ����ڣ�</td>
		          <td width="21%">
		          <sys:dateFirstDLastMonthTag myName="domain.pcrqq" myId="mainForm_domain_pcrqq" myClass="ymdate" />
	          			��
	          		<sys:dateCurrentDayTag myName="domain.pcrqz" myId="mainForm_domain_pcrqz" myClass="ymdate" />
		         
		          </td>
		        </tr>
		        <tr>
	        	  <td align="right">�ص���ţ�</td>
		          <td><s:textfield name="domain.hdbh" cssClass="pop_input noborder" /></td>
		       		<td align="right">�������ƣ�</td>
		          <td><s:textfield name="domain.hwMc" cssClass="pop_input noborder" /></td>
		           <td align="right">�ջ��ˣ�</td>
		          <td><s:textfield name="domain.shrMc" cssClass="pop_input noborder" /></td>
		        </tr>
		        <tr>
	        	  <td align="right">״̬��</td>
		          <td>
		          	<s:radio name="domain.zt" list='#{"":"����","1":"��ǩ��","2":"δǩ��"}' listKey="key" listValue="value"></s:radio>
		          </td>
		        </tr>
		   </table>
		</fieldset>
	  </div>
		<!-- ��ҳ��� id����ΪdataList -->
		<table id="dataList"><tr><td/></tr></table> 
		<!-- ��ҳ���� -->
		<div id="pager"></div>
		</div>
		
		<%@include file="/common/message.jsp" %>
</s:form>
</body>
</html>
