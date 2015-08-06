<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�ɳ�-����ǩ��</title>
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
				popwindow(jcontextPath+"/hygl/hypchwqs!initMx");
			});
			//��ʼ�����
			initDataGrid();
			$(":radio[name='domain.sfJs']")[1].checked=true;			

	});

    function onUpdate(pcDjxh,wfhDjxh,ddDjxh,xh,shfsDm,xybz){
    	var xtcs20016 = $("#mainForm_domain_xtcs20016").val();
		if(xtcs20016=='Y'){
			if(xybz=='N'){
				showAlert("����Э��Ǽǣ�");
				return;
			}
		}
    	var url = jcontextPath+"/hygl/hypchwqs!initMx.action?domain.pcDjxh="+pcDjxh+"&domain.wfhDjxh="+wfhDjxh+
    			"&domain.ddDjxh="+ddDjxh+"&domain.xh="+xh+"&domain.shfsDm="+shfsDm;
    	window.showModalDialog(url,window,"dialogHeight:300px;dialogWidth:450px;center:yes;resizable:no;status:no;scroll:no;help:no;maximize:yes;minimize:yes;")
    	//window.open(url);
    	onRefresh();
    }
    
    function onPsf(pcDjxh,wfhDjxh){
    	var url = jcontextPath+"/hygl/hypchwqs!initPsfMx.action?domain.pcDjxh="+pcDjxh+"&domain.wfhDjxh="+wfhDjxh;
    	window.showModalDialog(url,window,"dialogHeight:300px;dialogWidth:450px;center:yes;resizable:no;status:no;scroll:no;help:no;maximize:yes;minimize:yes;")
    	//window.open(url);
    	onRefresh();
    }
    
    var keyValue = "";
    var kV = "";
    var keyVa = "";
	function onDelete( hwqsDjxh,pcDjxh,wlssDjxh){
		keyValue = hwqsDjxh;
		kV = wlssDjxh;
		keyVa = pcDjxh;
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.hwqsDjxh":keyValue,"domain.wlssDjxh":kV,"domain.pcDjxh":keyVa};
		 var url = jcontextPath+"/hygl/hypchwqs!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}	
    
	function onWlssDj(pcDjxh,wfhDjxh,ddDjxh,xh,wlssDjxh){
		var url = jcontextPath+"/hygl/wlssdj!initMx.action?domain.pcDjxh="+pcDjxh+"&domain.wfhDjxh="+wfhDjxh+
				"&domain.wlssLybz=2"+"&domain.ddDjxh="+ddDjxh+"&domain.xh="+xh+"&domain.wlssDjxh="+wlssDjxh+"&number="+Math.random();
    	window.showModalDialog(url,window,"dialogHeight:600px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
    	onRefresh();
	}
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		pcrqq = $("#mainForm_domain_pcrqq").val();
		pcrqz = $("#mainForm_domain_pcrqz").val();
		//var sfJs=$(":radio[name='sfJs']")[0].checked?"1":"2";
		var radio=$(":checked[name='domain.sfJs']");
		var sfJs=radio[0].value;
		//����������
		var url = jcontextPath+"/hygl/hypchwqs!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.pcrqq":pcrqq,"domain.pcrqz":pcrqz,"domain.sfJs":sfJs}								//����Ĳ�����json��ʽ
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
		    colNames:['���','����','�ɳ��Ǽ����','wfhDjxh','psfy', 'wlssDjxh','xh','�ɳ�����','״̬','�ɳ�����','ddDjxh','������ʧ�Ǽ�','����ǩ�����','�������',
		              '','�ͻ���ʽ','���ͷ�','�Ƿ�ȷ��','ȷ��˵��','�ص����','xybz',
		              '��������','��װ', '','','','����', '����', '���', '����','ʼ����', 'Ŀ�ĵ�',
		              '�ջ���','�ջ���ַ','�ջ�����ϵ�绰','��������','˾��','��ϵ�绰','ǩ����','ǩ������'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'pageXh', index:'pageXh', sortable:false, width:'30', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pageXh' + rowId + '\'';
			    }
			  },
			  {name:'hstoperationcol', index:'', sortable:false, width:'30', align:'center',
				  cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'hstoperationcol' + rowId + '\'';
				    } 
			  },
		      {name:'pcDjxh', index:'pcDjxh', width:'100', hidden:true, align:'center'},
		      {name:'wfhDjxh', index:'wfhDjxh', width:'100', hidden:true, align:'center'},
		      {name:'psfy', index:'psfy', width:'100', hidden:true, align:'center'},
		      {name:'wlssDjxh', index:'wlssDjxh', width:'100', hidden:true, align:'center'},
		      {name:'xh', index:'xh', width:'100', hidden:true, align:'center'},
		      {name:'pcdh', index:'pcdh', width:'65', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcdh' + rowId + '\'';
				    }
			  },
			  {name:'jszt', index:'', sortable:false, width:'45', align:'center',
			  	cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'jszt' + rowId + '\'';
				    }
			  },
			  {name:'pcrq', index:'pcrq', width:'65', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcrq' + rowId + '\'';
				    }
			  },
			  {name:'ddDjxh', index:'ddDjxh', width:'100', hidden:true, align:'center'},
			  {name:'wlssOPera',index:'',width:'80',align:'center'},
		      {name:'hwqsDjxh', index:'hwqsDjxh', width:'100',hidden:true, align:'center'}, 
		      {name:'ddbh', index:'ddbh', width:'70', align:'center'},
		      {name:'shfsDm', index:'shfsDm', width:'0',hidden:true, align:'center'},
			  {name:'shfsMc', index:'shfsMc', width:'60', align:'center'},
			  {name:'psf',index:'',width:'40',sortable:false,align:'center'},
			  {name:'sfqr', index:'sfqr', width:'60', align:'center'},
		      {name:'qrsm', index:'qrsm', width:'100', align:'center'},
		      {name:'hdbh', index:'hdbh', width:'60', align:'center'},
		      {name:'xybz', index:'xybz', width:'100', align:'center',hidden:true},
			  {name:'hwMc', index:'hwMc', width:'100', align:'center'},
			  {name:'hwbz', index:'hwbz', width:'50', align:'center'},
			  {name:'sl', index:'sl', width:'0', align:'right',hidden:true},
			  {name:'zl', index:'zl', width:'0', align:'right',hidden:true},
			  {name:'tj', index:'tj', width:'0', align:'right',hidden:true},
		      {name:'slStr', index:'slStr', width:'50', align:'right'},  
		      {name:'zlStr', index:'zlStr', width:'50', align:'right'},
		      {name:'tjStr', index:'tjStr', width:'50', align:'right'},
		      {name:'srDf', index:'srDf', width:'50', align:'center'},
		      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'40', align:'center'},
		      {name:'shrXzqhMc', index:'shrXzqhMc', width:'40', align:'center'},
		      
		      {name:'shrMc', index:'shrMc', width:'100', align:'center'},
		      {name:'shrDz', index:'shrDz', width:'200', align:'center'},
		      {name:'shrLxdh', index:'shrLxdh', width:'110', align:'center'}, 
		      {name:'qsrMc', index:'qsrMc', width:'50', align:'center'},
			  {name:'qsrq', index:'qsrq', width:'65', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
			  {name:'cyrClhm', index:'cyrClhm', width:'60', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'cyrClhm' + rowId + '\'';
				    }
			  },

			  {name:'cyrSjxm', index:'cyrSjxm', width:'50', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'cyrSjxm' + rowId + '\'';
				    }
			  },
			  {name:'cyrSjsjhm', index:'cyrSjsjhm', width:'50', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'cyrSjsjhm' + rowId + '\'';
				    }
			  }			  
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'HWQS_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/hypchwqs!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var hwqsDjxh = jQuery("#dataList").jqGrid('getCell', cl,"hwqsDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ����
                var pcDjxh = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh");
                var wlssDjxh = jQuery("#dataList").jqGrid('getCell', cl,"wlssDjxh");
                var wfhDjxh = jQuery("#dataList").jqGrid('getCell', cl,"wfhDjxh");
                var psfy = jQuery("#dataList").jqGrid('getCell', cl,"psfy");
                var sfqr = jQuery("#dataList").jqGrid('getCell', cl,"sfqr");
                var shfsMc = jQuery("#dataList").jqGrid('getCell', cl,"shfsMc");
				var shfsDm = jQuery("#dataList").jqGrid('getCell', cl,"shfsDm");
                var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh");
                var xh = jQuery("#dataList").jqGrid('getCell', cl,"xh");
                var xybz = jQuery("#dataList").jqGrid('getCell', cl,"xybz");
                var psfLink = "";
                var qrLink = "";
                var jsStr = "<font color=\"red\">δ����</font>";
                var link = "<a href=\"javascript:onUpdate('"+pcDjxh+"','"+wfhDjxh+"','"+ddDjxh+"','"+xh+"','"+shfsDm+"','"+xybz+"')\"><font color=\"blue\">����</font></a>";
                var wlssLink = "<a href=\"javascript:onWlssDj('"+pcDjxh+"','"+wfhDjxh+"','"+ddDjxh+"','"+xh+"','"+wlssDjxh+"')\"><font color=\"blue\">�Ǽ�</font></a>";
                if (hwqsDjxh != "") {
                	jsStr = "�ѽ���";
                	wlssLink = "�ѵǼ�";
                	link = "<a href=\"javascript:onDelete('"+hwqsDjxh+"','"+pcDjxh+"','"+wlssDjxh+"')\"><font color=\"blue\">ȡ��</font></a>";
                }
                //���ͷ��ۼӱ�־ �����Ƿ�����¼�����ͷ� 2013-09-29 by xiay
                if(${domain.ljbz=="N"}){
                	psfLink = ""; 
                }else{
	                if(psfy == ""){
	                	psfLink = "<a href=\"javascript:onPsf('"+pcDjxh+"','"+wfhDjxh+"')\"><font color=\"blue\">¼��</font></a>";
	                }else{
	                	psfLink = "<a href=\"javascript:onPsf('"+pcDjxh+"','"+wfhDjxh+"')\"><font color=\"blue\">"+psfy+"</font></a>";
	                }
                }
                if(sfqr == 'Y'){
                	qrLink = "<font color=\"black\">��ȷ��</font>";
                	psfLink = psfy;
                }else{
                	qrLink = "<font color=\"red\">δȷ��</font>";
                }
                if(shfsMc == '����'){
                	qrLink = "";
                	psfLink = "";
                }
                
                $("#dataList").jqGrid('setRowData', cl, { 'jszt': jsStr }); 
                $("#dataList").jqGrid('setRowData', cl, { 'psf': psfLink }); 
                $("#dataList").jqGrid('setRowData', cl, { 'sfqr': qrLink }); 
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
                $("#dataList").jqGrid('setRowData', cl, { 'wlssOPera': wlssLink }); 
            }
            
            var gridName = "dataList";
     	    var a = ['pageXh','hstoperationcol','pcdh','jszt','pcrq','cyrClhm','cyrSjxm','cyrSjsjhm'];
      		
            Merger(gridName, 'pcDjxh', a);
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="hypchwqs!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.ljbz" />
	<s:hidden name="domain.xtcs20016" />
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

	<div class="right_cont">
	<div id="divQuery">
		<fieldset>
			<legend>��ѯ����</legend>
		   <table width="99%" border="0" cellspacing="0" cellpadding="0">
	          	<tr>
	          		<td width="5%" align="right">״̬��</td>   
		          <td width="15%">
		         		<s:radio name="domain.sfJs" list='#{"":"����","1":"δ����","2":"�ѽ���"}' listKey="key" listValue="value"></s:radio>
		          </td>
		          <td width="5%" align="right">�ɳ����ڣ�</td>
		          <td width="35%">
		          <sys:dateFirstDLastMonthTag myName="domain.pcrqq" myId="mainForm_domain_pcrqq" myClass="ymdate" />
	          			��
	          		<sys:dateCurrentDayTag myName="domain.pcrqz" myId="mainForm_domain_pcrqz" myClass="ymdate" />
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
