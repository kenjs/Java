<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>��ҵ-��λ</title>
<%@ include file="/common/meta.jsp"%>
<style type="text/css">
html,body {overflow:hidden;}
.bzsm {width:100%;height:18px;border:0;background:transparent;float:left;}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		//��ѯ��ť�¼�
		$("#queryBtn").click(function(){
			onRefresh();
		});
			
		//������ť�¼�
		$("#addBtn").click(function(){
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val());
			if(ssJgbm == "" || ssJgbm == null || ssJgbm == "0"){
				showAlert("��ѡ���ţ�");
				return;
			}
			var url = jcontextPath+"/xtgl/gwwh!initMx.action?domain.ssJgbm="+ssJgbm;
			window.showModalDialog(url,window,"dialogHeight:420px;dialogWidth:520px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
			onRefresh();
		});
		
		$("#xtgwBtn").click(function(){
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val());
			if(ssJgbm == "" || ssJgbm == null || ssJgbm == "0"){
				showAlert("��ѡ���ţ�");
				return;
			}
			var url = jcontextPath+"/xtgl/gwwh!initXtgwMx.action?domain.ssJgbm="+ssJgbm;
			window.showModalDialog(url,window,"dialogHeight:380px;dialogWidth:580px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
			onRefresh();
		});
			
		var sjJgbm = $("#mainForm_domain_dwDjxh").val();
		bmInit(sjJgbm, '', "domain.ssJgbm", "mainForm_domain_ssJgbm", "Y", "Y");
			
		//��ʼ�����
		initDataGrid();
			
	});
	
	 function onUpdate(gwDjxh){
		var ssJgbm = trim($("#mainForm_domain_ssJgbm").val());
    	var url = jcontextPath+"/xtgl/gwwh!initMx.action?domain.gwDjxh="+gwDjxh+"&domain.ssJgbm="+ssJgbm;
    	window.showModalDialog(url,window,"dialogHeight:420px;dialogWidth:520px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		onRefresh();
    }
    
    var keyValue = "";
	function onDelete(gwDjxh){
		keyValue = gwDjxh;
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.gwDjxh":keyValue};
		 var url = jcontextPath+"/xtgl/gwwh!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}	
	
	var val1 = ""
	function onStart(gwDjxh){
		val1 = gwDjxh;
		showConfirm("��ȷ��Ҫ����ô��","start");
	}
	
	function start(){
		var url = jcontextPath+"/xtgl/gwwh!saveEnable";
		var jsonObj = {"domain.gwDjxh":val1,"domain.qybz":"Y"};
		ajaxCommon(url,jsonObj , "startSuccess");
	}
	
	function startSuccess(){     
        showAlert("���óɹ���");
        onRefresh();
	}	
	
	var val2 = "";
	function onStop(gwDjxh){
		val2 = gwDjxh;
		showConfirm("��ȷ��Ҫͣ��ô��","stop");
	}
	
	function stop(){
		var url = jcontextPath+"/xtgl/gwwh!saveDisable";
		var jsonObj = {"domain.gwDjxh":val2,"domain.qybz":"N"};
		ajaxCommon(url,jsonObj , "stopSuccess");
	}
	
	function stopSuccess(){     
        showAlert("ͣ�óɹ���");
        onRefresh();
	}	
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var dwDjxh = $("#mainForm_domain_dwDjxh").val();
		var ssJgbm = trim($("#mainForm_domain_ssJgbm").val());
		//ssJgbm = "1000000124";
		if(ssJgbm == "" || ssJgbm == null || ssJgbm == "0"){
			ssJgbm = dwDjxh;
		}
		//����������
		var url = jcontextPath+"/xtgl/gwwh!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm}								//����Ĳ�����json��ʽ
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
	    colNames:['����','��־','״̬','','��λ����','��λ���','���ڲ���','ϵͳ��λ����','','������','��������','�޸���','�޸�����','��ע˵��'],			 //name ����ʾ������
	     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
	    colModel :[ 
		  {name:'hstoperationcol', index:'', sortable:false, width:'100', align:'center'},
		  
		  {name:'lybzStr', index:'lybzStr', width:'40', align:'center'},
		  {name:'qybzZt', index:'qybzZt', width:'40', align:'center'}, 
		  {name:'qybz', index:'qybz', width:'0', align:'center',hidden:true},
	      {name:'gwMc', index:'gwMc', width:'150', align:'center'}, 
	      {name:'gwJc', index:'gwJc', width:'150', align:'center'},
	      {name:'bmMc', index:'bmMc', width:'150', align:'center'},
	      {name:'gwDm', index:'gwDm', width:'90', align:'center'},
	      {name:'gwDjxh', index:'gwDjxh', width:'0', align:'center',hidden:true},
	      {name:'cjrMc', index:'cjrMc', width:'80', align:'center'},
	      {name:'cjrq', index:'cjrq', width:'80', align:'center'},
	      {name:'xgrMc', index:'xgrMc', width:'80', align:'center'},
	      {name:'xgrq', index:'xgrq', width:'80', align:'center'},   
	      {name:'bzsm', index:'bzsm', width:'150', align:'center'}
	      
	   
	    ],
	    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
	    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
	    rowList:[20,50,100,300,500],		                //һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
	    sortname: 'GW_DM',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
	    sortorder: 'ASC',				//Ĭ��������
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
	       	   $("#mainForm").attr("action",jcontextPath+"/xtgl/gwwh!download");
			   $("#mainForm").submit();
	       } 
	 });
		 
	}
	
    //��������Ϻ󴥷��¼�
	function myGridComplete() {
           var graduateIds = $("#dataList").jqGrid('getDataIDs');
           for (var i = 0; i < graduateIds.length; i++) {
               var cl = graduateIds[i];
               var val = jQuery("#dataList").jqGrid('getCell', cl,"gwDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
               var value = jQuery("#dataList").jqGrid('getCell', cl,"qybz");
                var bzVal = jQuery("#dataList").jqGrid('getCell', cl,"lybzStr");
               var lin1 = "";
               var lin2 = "";
               if(value == "N"){
            	   lin1 = "<font color=\"red\">ͣ��</font>";
            	   lin2= "<a href=\"javascript:onStart('"+val+"')\"><font color=\"blue\">����</font></a>";
               }else{
            	   lin1 = "<font color=\"black\">����</font>";
            	   lin2= "<a href=\"javascript:onStop('"+val+"')\"><font color=\"blue\">ͣ��</font></a>";
               }
               if(bzVal=="ϵͳ"){
               		var link = " <a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">ɾ��</font></a> " + lin2;
               }else{
               		var link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">�޸�</font></a>"
               	 	+ " <a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">ɾ��</font></a> " + lin2;
               }
               
               $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link });
               $("#dataList").jqGrid('setRowData', cl, { 'qybzZt': lin1 });
           }
     }
     /**************************************��ҳ����****************************************/


</script>
</head>

<body>
	<s:form action="gwwh!query" namespace="/xtgl" method="post" id="mainForm" name="mainForm">
		<!-- ������ -->
		<div class="right_btnbg">
  			<ul class="lcont">
    			<li class="no">������</li>
    			<li><a href="#" id="queryBtn" class="licon04">�� ��</a></li>
    			<li><a href="#" id="addBtn" class="licon01">�� ��</a></li>
    			<li><a href="#" id="xtgwBtn" class="licon07">ϵͳ��λ</a></li>
    			<li><a href="#" id="closeBtn" class="licon03">�� ��</a></li>
  			</ul>
  			<ul class="rcont">
    			<li class="ricon02" onClick="slideToggle('syquery')">��ʾ/���ز�ѯ����</li>
    			<li class="ricon03">����</li>
  			</ul>
		</div>
		
		<div class="right_cont">  
  			<div id="divQuery">
	    		<fieldset>
		      		<legend>��ѯ����</legend>
		      		<table width="95%" border="0" cellspacing="0" cellpadding="0">
		        		<tr>
		          			<td width="2%" align="right">��λ��</td>
		          			<td width="15%">
		          				<sys:qxGsList myId="mainForm_domain_dwDjxh" myName="domain.dwDjxh" mcContainDmBz="Y" myClass="select" onChange="bmInit(this.value, '', 'domain.ssJgbm', 'mainForm_domain_ssJgbm', 'Y', 'Y')" ></sys:qxGsList>
				  			</td>
				  			<td width="2%" align="right">���ţ�</td>
		          			<td width="15%">
		          				<select name="domain.ssJgbm" id="mainForm_domain_ssJgbm" class="select" />
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
