<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>��ҵ-��������-������������</title>
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
			
			//initSpwsXmfl();
			initList();
			initWs();
			$("#mainForm_domain_wsDm").change(function(){
				initSpwsXmfl();
			});
					
	});
	function initList() {
		var dwDm = $("#mainForm_domain_dwDm").val(); 
		var jsonObj = {"domain.paramdm":dwDm,
			"domain.defaultValue":"",
			"domain.currentObjName":"domain.ssJgbm",
			"domain.currentObjId":"mainForm_domain_ssJgbm",
			"domain.containQbBz":"Y",
			"domain.mcContainDmBz":"Y"};
	
		var url=jcontextPath+"/common/wlglptCommon!bmInit";	
		ajaxCommon(url,jsonObj,"changeBmList");
	}
	function changeBmList(data){
		var list = data.domain.dataList;
		$("#"+data.domain.currentObjId).empty();
		$.each(list, function(i,domain){
		   var option = document.createElement('option');
		    $("#"+data.domain.currentObjId)[0].add(option);
		    
		    $(option).text(domain.mc).val(domain.dm);
		    //Ĭ��ѡ��
		    if(data.domain.defaultValue==domain.dm){
		    	$(option).attr("selected","selected");
		    	$(option).text(domain.mc).val(domain.dm);
		    }
		});
	}
	function initWs() {
		var zgsbm = $("#mainForm_domain_zgsbm").val(); 
		var jsonObj = {"domain.paramdm":zgsbm,
			"domain.defaultValue":"",
			"domain.currentObjName":"domain.wsDm",
			"domain.currentObjId":"mainForm_domain_wsDm",
			"domain.containQbBz":"Y",
			"domain.mcContainDmBz":"Y"};
	
		var url=jcontextPath+"/common/wlglptCommon!wsInit";	
		ajaxCommon(url,jsonObj,"changeWsList");
	}
	
	function changeWsList(data){
		var list = data.domain.dataList;
		$("#"+data.domain.currentObjId).empty();
		$.each(list, function(i,domain){
		    var option = document.createElement('option');
		    $("#"+data.domain.currentObjId)[0].add(option);
		    
		    $(option).text(domain.mc).val(domain.dm);
		    //Ĭ��ѡ��
		    if(data.domain.defaultValue==domain.dm){
		    	$(option).attr("selected","selected");
		    	$(option).text(domain.mc).val(domain.dm);
		    }
		});
		//�ı���Ŀ����list
		initSpwsXmfl();
	}
	
	function initSpwsXmfl() {
		var wsDm = $("#mainForm_domain_wsDm").val();
		commonInit("SpwsXmfl", wsDm, "", "domain.xmflDjxh", "mainForm_domain_xmflDjxh", "Y", "Y");
	}

    function onUpdate(splcSzxh,wsDm,xmflDjxh,dwDm,ssJgbm){
    	var curDwbm="";
    	if(undefined==ssJgbm || null==ssJgbm || ""==ssJgbm){
    		curDwbm=dwDm;
    	}else{
    		curDwbm=ssJgbm;
    	}
    	var url = jcontextPath+"/dzgl/qyspwssplcsz!initMx?domain.splcSzxh="+splcSzxh+"&domain.curDwbm="+curDwbm+"&domain.wsDm="+wsDm+"&domain.xmflDjxh="+xmflDjxh;
    	if(undefined==splcSzxh || null==splcSzxh || ""==splcSzxh){
    		url+="&domain.dwDm="+dwDm+"&domain.ssJgbm="+ssJgbm
    	}
    	window.showModalDialog(url,window,"dialogHeight:620px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
    	onRefresh();
    	//window.open(url);
    	//popwindow(jcontextPath+"/dzgl/qyspwssplcsz!initMx?domain.splcSzxh="+splcSzxh, 760, 488);
    }
    
    var keyValue = "";
	function onDelete( splcSzxh){
		keyValue = splcSzxh;
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.splcSzxh":keyValue};
		 var url = jcontextPath+"/dzgl/qyspwssplcsz!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}	
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var dwDm = $("#mainForm_domain_dwDm").val(); 
		var ssJgbm = $("#mainForm_domain_ssJgbm").val(); 
		var wsDm = $("#mainForm_domain_wsDm").val(); 
		var xmflDjxh = $("#mainForm_domain_xmflDjxh").val(); 
  
		//����������
		var url = jcontextPath+"/dzgl/qyspwssplcsz!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.dwDm":dwDm,"domain.ssJgbm":ssJgbm,"domain.wsDm":encodeURI(wsDm),"domain.xmflDjxh":xmflDjxh}								//����Ĳ�����json��ʽ
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
		    colNames:['����', '���������������','������������','�������','��Ŀ�������','��λ','����','ҵ�����','ҵ�񻷽�','����','��Ŀ','��������','����ʱ��','�����ձ�־','Ȩ��ϵ��','������',
				     '��������','�޸���','�޸�����'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
		      {name:'splcSzxh', index:'splcSzxh', hidden:true, width:'80', align:'center'}, 
		      {name:'ssJgbm', index:'ssJgbm', hidden:true, width:'80', align:'center'}, 
		      {name:'wsDm', index:'wsDm', hidden:true, width:'80', align:'center'},
		      {name:'xmflDjxh', index:'xmflDjxh', hidden:true, width:'80', align:'center'},  
		      {name:'dwMc', index:'dwMc', width:'150', align:'center'}, 
		      {name:'ssJgmc', index:'ssJgmc', width:'100', align:'center'}, 
		      {name:'ywflMc', index:'ywflMc', width:'80', align:'center'},
			  {name:'ywhjMc', index:'ywhjMc', width:'80', align:'center'},
		      {name:'wsMc', index:'wsMc', width:'100', align:'center'}, 
		      {name:'xmflmc', index:'xmflmc', width:'100', align:'center'}, 
		      {name:'splc', index:'splc', width:'300', align:'center'}, 
		      {name:'zssx', index:'zssx', width:'80', align:'center'}, 
		      {name:'gzrbz', index:'gzrbz', width:'70', align:'center',hidden:true},
			  {name:'qzxsbz', index:'qzxsbz', width:'80', align:'center'},
			 
			  
			  
		      {name:'cjrMc', index:'cjrMc', width:'80', align:'center'}, 
		      {name:'cjrq', index:'cjrq', width:'80', align:'center'}, 
		      {name:'xgrMc', index:'xgrMc', width:'80', align:'center'}, 
		      {name:'xgrq', index:'xgrq', width:'80', align:'center'}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'SPLC_SZXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/dzgl/qyspwssplcsz!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            var dwDm = $("#mainForm_domain_dwDm").val(); 
            var ssJgbm = $("#mainForm_domain_ssJgbm").val(); 
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"splcSzxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var wsDm = jQuery("#dataList").jqGrid('getCell', cl,"wsDm");
                var xmflDjxh = jQuery("#dataList").jqGrid('getCell', cl,"xmflDjxh");
                if("0"==xmflDjxh)
                	xmflDjxh="";
                
                var qzxsbz = jQuery("#dataList").jqGrid('getCell', cl,"qzxsbz"); 
                if("Y"==qzxsbz){
                	$("#dataList").jqGrid('setRowData', cl, { 'qzxsbz': "��" });
                }else{
                	$("#dataList").jqGrid('setRowData', cl, { 'qzxsbz': "��" });
                }
                
                var gzrbz = jQuery("#dataList").jqGrid('getCell', cl,"gzrbz"); 
                var zssx = jQuery("#dataList").jqGrid('getCell', cl,"zssx"); 
                if("1"==gzrbz){
                	$("#dataList").jqGrid('setRowData', cl, { 'zssx': zssx+" ������" });
                }
                if("2"==gzrbz){
                	$("#dataList").jqGrid('setRowData', cl, { 'zssx': zssx+" ��Ȼ��" });
                }	
                var link = "<a href=\"javascript:onUpdate('"+val+"','"+wsDm+"','"+xmflDjxh+"','"+dwDm+"','"+ssJgbm+"')\"><font color=\"blue\">����</font></a>";
                //+ " <a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">ɾ��</font></a>";
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="qyspwssplcsz!query" namespace="/dzgl" method="post" id="mainForm" name="mainForm">
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
	   <table width="95%" border="0" cellspacing="0" cellpadding="0">
	   <s:hidden name="domain.zgsbm"></s:hidden>
	        <tr>
	          <td width="10%" align="right">��λ��</td>
	          <td width="40%">
	          	<sys:gsList myId="mainForm_domain_dwDm" myName="domain.dwDm" mcContainDmBz="Y" myClass="select" onChange="initList()" />
	          </td>
	          <td width="15%" align="right">���ţ�</td>
	          <td width="35%">
	          	<select name="domain.ssJgbm" id="mainForm_domain_ssJgbm" class="select"/>
	          </td>
	        </tr>
	        <tr>
        	  <td align="right">���飺</td>
	          <td>
	          	<select name="domain.wsDm" id="mainForm_domain_wsDm" class="select"/>
	          </td>
	          <td align="right">��Ŀ���ࣺ</td>
	          <td><select name="domain.xmflDjxh" id="mainForm_domain_xmflDjxh" class="select" /></td>
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
