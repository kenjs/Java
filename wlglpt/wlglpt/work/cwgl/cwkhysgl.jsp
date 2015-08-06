<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>����-�ͻ�Ԥ������</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		initHykhData(300, $("#mainForm_domain_ssJgbm4Query").val(), $("#mainForm_domain_djJgbm4Query").val());
		
		$("#mainForm_domain_ssJgbm4Query").change(function(){
			$("[name='domain.fhrMc']").unbind();
			initHykhData(300, $(this).val(), $("#mainForm_domain_djJgbm4Query").val());
		});
		
		$("#mainForm_domain_djJgbm4Query").change(function(){
			initRy();
			$("[name='domain.fhrMc']").unbind();
			initHykhData(300, $("#mainForm_domain_ssJgbm4Query").val(), $(this).val());
		});
		
		
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//������ť�¼�
			$("#addBtn").click(function(){
				var kh=$("#mainForm_domain_fhrDjxh").val();
				if(kh==""){
					showError("��ѡ��һ���ͻ���");
					return;
				}
				var url = jcontextPath+"/cwgl/khyslr!initMx.action?domain.khDjxh="+kh;
				window.showModalDialog(url,window,"dialogHeight:480px;dialogWidth:700px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
				onRefresh();
			});
			//��ʼ�����
			initDataGrid();
						

	});

    function onUpdate(srDjxh){
    	popwindow(jcontextPath+"/cwkhysgl!initMx?domain.srDjxh="+srDjxh);
    }
    
    function onLook(srDjxh){
    	var url = jcontextPath+"/cwgl/khyslr!onLook.action?domain.srDjxh="+srDjxh+"&num="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:400px;dialogWidth:700px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		onRefresh();
    }
    
    var keyValue = "";
	function onDelete( srDjxh){
		keyValue = srDjxh;
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.srDjxh":keyValue};
		 var url = jcontextPath+"/khyslr!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}	
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		  var jgbm=$("#mainForm_domain_ssJgbm4Query").val();
		  var fhrDjxh=$("#mainForm_domain_fhrDjxh").val();
		  var fhrMc = $("#mainForm_domain_fhrMc").val();
		  var rqq=$("#mainForm_domain_rqq").val();
		  var rqz=$("#mainForm_domain_rqz").val();
		  if(rqq>rqz){
			  showError("�Ǽ��������ܴ��ڵǼ�����ֹ��");
			  return;
		  }
		//����������
		var url = jcontextPath+"/khyslr!query";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.djJgbm":jgbm,"domain.khDjxh":fhrDjxh,"domain.rqq":rqq,"domain.rqz":rqz,"domain.fhrMc":encodeURI(fhrMc)}								//����Ĳ�����json��ʽ
		 }
		 ).trigger("reloadGrid", [{page:1}]);		//��ʾ����һҳ�������������ݷ����仯��ʱ�򣬷�ҳ����Ϣ����
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
		    colNames:['����', '����Ǽ����(SEQ_SR_DJXH)','�ͻ�����','���','�Ǽ���','�Ǽ�����',
				     '�Ǽǲ���','��������','�޸���','�޸�����'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
		      {name:'srDjxh', index:'srDjxh', width:'100',hidden:true, align:'center'}, 
		      {name:'khMc', index:'khMc', width:'150', align:'center'}, 
		      {name:'je', index:'je', width:'100', align:'center'}, 
		      {name:'djrMc', index:'djrMc', width:'100', align:'center'}, 
		      {name:'djrq', index:'djrq', width:'100', align:'center'}, 
		      {name:'djBm', index:'djBm', width:'100', align:'center'}, 
		      {name:'ssJgmc', index:'ssJgmc', width:'100', align:'center'}, 
		      {name:'xgrMc', index:'xgrMc', width:'80', align:'center'}, 
		      {name:'xgrq', index:'xgrq', width:'80', align:'center'}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'SR_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/khyslr!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"srDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var link = "<a href=\"javascript:onLook('"+val+"')\"><font color=\"blue\">�鿴</font></a>";
              
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="cwkhysgl!query" namespace="" method="post" id="mainForm" name="mainForm">
	<div class="right_btnbg">
	<s:hidden name="fhrData" />
		<ul class="lcont">
		    <li class="no">������</li>
		    <li><a href="#" id="queryBtn" class="licon04">�� ��</a></li>
		    <li><a href="#" id="addBtn" class="licon01">����</a></li>
		    <li><a href="#" id="closeBtn" class="licon03">�ر�</a></li>
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
			<td width="8%" align="right">ҵ���ţ�</td>
			<td width="30%">
				<sys:gsList myId="mainForm_domain_ssJgbm4Query" myName="domain.ssJgbm4Query" mcContainDmBz="Y" myClass="select" onChange="bmInit(this.value, '', 'domain.djJgbm4Query', 'mainForm_domain_djJgbm4Query', 'Y', 'Y')" />
				
				
			</td>
			<td width="8%" align="right">�ͻ����ƣ�</td>
			<td width="20%">
					<s:hidden name="domain.fhrDjxh"></s:hidden>
  					<div class="inputsel" style="width: 215px; ">
  						<s:textfield name="domain.fhrMc" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width:185px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="fhr" onFocus="this.blur()"></a>
  					</div>
			  		<div class="inputsc">
		              <div id="inputSel_fhr" class="inputselcont inputselFixedSize ac_results">
		              </div>
		            </div></td>
		    <td width="8%" align="right">�Ǽ����ڣ�</td>
		     <td width="35%"><sys:dateFirstDLastMonthTag myName="domain.rqq" myId="mainForm_domain_rqq" myClass="ymdate" />
	          			��
	          		<sys:dateCurrentDayTag myName="domain.rqz" myId="mainForm_domain_rqz" myClass="ymdate" /></td></td>
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
