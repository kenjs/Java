<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>��ҵ-�ͻ�-�ջ���ַ</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
			document.getElementById("maincont").onmousewheel=hideHelpWindow;
			initHykhData(300, $("#mainForm_domain_ssJgbm").val(), $("#mainForm_domain_bmDjxh").val());
			
			$("#mainForm_domain_bmDjxh").change(function(){
				initHykhData(300, $("#mainForm_domain_ssJgbm").val(), $(this).val());
			});
			
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//������ť�¼�
			$("#addBtn").click(function(){
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val());
			var khDjxh = trim($("#mainForm_domain_fhrDjxh").val());  
				if(khDjxh==""||khDjxh==undefined){
					showAlert("��ѡ��ͻ�!");
					return false;
				}
				var url = jcontextPath+"/zygl/qykhshdz!initMx.action?domain.ssJgbm="+ssJgbm+"&domain.khDjxh="+khDjxh;
		    	window.showModalDialog(url,window,"dialogHeight:380px;dialogWidth:640px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		    	onRefresh();
			});
			
			var khDjxh = $("#mainForm_domain_ssJgbm").val();
			if(khDjxh != null && khDjxh !="" && khDjxh != undefined){
				bmInit(khDjxh, '', "domain.bmDjxh", "mainForm_domain_bmDjxh", "Y", "Y");
			}
			
			//��ʼ�����
			initDataGrid();
						

	});

    function onUpdate(shdzDjxh){
    	var url = jcontextPath+"/zygl/qykhshdz!initMx.action?domain.shdzDjxh="+shdzDjxh;
		window.showModalDialog(url,window,"dialogHeight:380px;dialogWidth:640px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		onRefresh();
    }
    
    var keyValue = "";
	function onDelete(shdzDjxh){
		keyValue = shdzDjxh;
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.shdzDjxh":keyValue};
		 var url = jcontextPath+"/zygl/qykhshdz!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}	
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
	   var ssJgbm = trim($("#mainForm_domain_ssJgbm").val());
	   var djJgbm = trim($("#mainForm_domain_bmDjxh").val());
	   var khDjxh = trim($("#mainForm_domain_fhrDjxh").val()); 	
		//����������
		var url = jcontextPath+"/zygl/qykhshdz!query.action";   
		 $("#dataList").jqGrid("setGridParam",{	
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.djJgbm":djJgbm,"domain.khDjxh":khDjxh}								//����Ĳ�����json��ʽ
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
		    colNames:['����', '�ͻ�����','��λ����','��������','�ջ���λ','��������','��ϸ��ַ','�ʱ�','��ϵ��',
		              '�ƶ��绰','�̶��绰','������ϵ�绰','��ע',
				      '������','��������','�޸���','�޸�����','�ջ���ַ�Ǽ����'],		 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
			  {name:'khmc', index:'khmc', width:'180', align:'center'},
			  {name:'ssJgmc', index:'ssJgmc', width:'100', align:'center'},
			  {name:'djJgmc', index:'djJgmc', width:'100', align:'center'},
			  {name:'shdwMc', index:'shdwMc', width:'120', align:'center'},
		      {name:'xzqhMc', index:'xzqhMc', width:'80', align:'center'}, 
		      {name:'xxdz', index:'qtlxdh', width:'180', align:'left'}, 
		      {name:'yb', index:'yb', width:'40', align:'center'}, 
		      {name:'lxr', index:'xzqhDm', width:'60', align:'center'}, 

		      {name:'lxrYddh', index:'xxdz', width:'80', align:'center'}, 
		      {name:'lxrGddh', index:'pyqc', width:'80', align:'center'}, 
		      {name:'qtlxdh', index:'pyjc', width:'80', align:'center'}, 
		      {name:'bz', index:'yb', width:'180', align:'left'}, 
		      
		      {name:'cjrMc', index:'cjrMc', width:'70', align:'center'}, 
		      {name:'cjrq', index:'cjrq', width:'70', align:'center'}, 
		      {name:'xgrMc', index:'xgrMc', width:'70', align:'center'}, 
		      {name:'xgrq', index:'xgrq', width:'70', align:'center'},
		      {name:'shdzDjxh', index:'shdzDjxh', width:'0', align:'center',hidden:true}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'KHMC',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/zygl/qykhshdz!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"shdzDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">�޸�</font></a>"
                + " <a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">ɾ��</font></a>";
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="qykhshdz!query" namespace="/zygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="fhrData" />
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">������</li>
		    <li><a href="#" id="queryBtn" class="licon04">�� ��</a></li>
		    <li><a href="#" id="addBtn" class="licon01">�� ��</a></li>
		    <li><a href="#" id="closeBtn" class="licon03">�� ��</a></li>
	  	</ul>	
	    <ul class="rcont">
		    <li class="ricon02" onclick="slideToggle('syquery')">��ʾ/���ز�ѯ����</li>
		    <li class="ricon03">����</li>
	  	</ul>
	</div> 
	<div style="display: none;" id="maincont"></div>
	<div class="right_cont">
		<div id="divQuery">
	       <fieldset><legend>��ѯ����</legend>
	       <table width="99%" border="0" cellspacing="0" cellpadding="0">
		      <tr>
	          <td width="8%" align="right">��λ��</td>
	          <td width="21%"><sys:gsList myName="domain.ssJgbm" myId="mainForm_domain_ssJgbm" mcContainDmBz="Y" myClass="select" onChange="bmInit(this.value, '', 'domain.bmDjxh', 'mainForm_domain_bmDjxh', 'Y', 'Y')"/></td>
	          <td width="8%" align="right">���ţ�</td>
	          <td width="21%"><select id="mainForm_domain_bmDjxh" name="domain.bmDjxh" class="select"/></td>
	          <td width="8%" align="right">�ͻ����ƣ�</td>
	          <td width="21%">
  					<div class="inputsel" style="width: 230px; ">
  						<s:hidden name="domain.fhrDjxh"/>
  						<s:textfield name="domain.fhrMc" cssClass="pop_input noborder inputext bgstyle_optional" style="width:200px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="fhr" onFocus="this.blur()"></a>
  					</div>
			  		<div class="inputsc">
		              <div id="inputSel_fhr" class="inputselcont inputselFixedSize ac_results" style="width:350px;">
		              </div>
		            </div>
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
