<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Ԥ��Ʊ�����嵥����</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
			
			document.getElementById("maincont").onmousewheel=hideHelpWindow;
			initHykhData(300,$("#mainForm_domain_ssJgbm").val(), $("#mainForm_domain_djJgbm").val(),"jsonData","khMc","khDjxh");
			$("#mainForm_domain_djJgbm").change(function(){
				initHykhData(300, $("#mainForm_domain_ssJgbm").val(), $(this).val(),"jsonData","khMc","khDjxh");
			});
			var sjJgbm = $("#mainForm_domain_ssJgbm").val();
			bmInit(sjJgbm, '', "domain.djJgbm", "mainForm_domain_djJgbm", "Y", "Y");
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//��ʼ�����
			initDataGrid();				
			
	})
	
	  function onUpdate(kpsqDjxh,khDjxh){
	    var ssJgbm = $("#mainForm_domain_ssJgbm").val();
    	var url = jcontextPath+"/hygl/jskpdzqd!initMx.action?domain.kpsqDjxh="+kpsqDjxh+"&domain.ssJgbm="+ssJgbm+"&domain.khDjxh="+khDjxh;
    	window.showModalDialog(url,window,"dialogHeight:540px;dialogWidth:680px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
    	onRefresh();
    }
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var khDjxh = $("#mainForm_domain_khDjxh").val();
		var khmc = $("#mainForm_domain_khMc").val(); 
		var djrqQ = $("#mainForm_domain_djrqQ").val(); 
		var djrqZ = $("#mainForm_domain_djrqZ").val(); 
		var djJgbm = $("#mainForm_domain_djJgbm").val(); 
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		if(undefined==ssJgbm || null==ssJgbm || ""==ssJgbm){
			showAlert("����ѡ��λ��");
			return;
		}
  
		//����������
		var url = jcontextPath+"/hygl/jskpdzqd!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.khDjxh":encodeURI(khDjxh),"domain.khmc":encodeURI(khmc),"domain.djrqQ":djrqQ,"domain.djrqZ":djrqZ,"domain.djJgbm":djJgbm,
				      "domain.ssJgbm":ssJgbm}								//����Ĳ�����json��ʽ
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
		    colNames:['����', '��Ʊ����Ǽ����','�ͻ��Ǽ����','�ͻ�����','���뿪Ʊ���','���뿪Ʊ����',
		             '��Ʊ״̬','��Ʊ���','��Ʊ����','������־','�������',
				     '�Ǽ���','�Ǽ�����','�Ǽǲ���','������λ'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
		      {name:'kpsqDjxh', index:'kpsqDjxh', width:'100', align:'center',hidden:true}, 
		      {name:'khDjxh', index:'khDjxh', width:'100', align:'center',hidden:true}, 
		      {name:'khmc', index:'khmc', width:'100', align:'center'}, 
		      {name:'sqKpjeHj', index:'sqKpjeHj', width:'100', align:'right'}, 
		      {name:'sqKprq', index:'sqKprq', width:'80', align:'center'}, 
		      
		      {name:'fpkjbz', index:'fpkjbz', width:'40', align:'center'}, 
		      {name:'fpkjje', index:'fpkjje', width:'100', align:'right'}, 
		      {name:'fpkjrq', index:'fpkjrq', width:'80', align:'center'}, 
		      {name:'ykpQdhx', index:'ykpQdhx', width:'40', align:'center'}, 
		      {name:'yhxje', index:'yhxje', width:'100', align:'right'}, 
		      
		      {name:'djrMc', index:'djrMc', width:'100', align:'center'}, 
		      {name:'djrq', index:'djrq', width:'100', align:'center'}, 
		      {name:'djJgmc', index:'djJgmc', width:'100', align:'center'}, 
		      {name:'ssJgmc', index:'ssJgmc', width:'100', align:'center'}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'KPSQ_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/jskpdzqd!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"kpsqDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ����
                var khDjxh = jQuery("#dataList").jqGrid('getCell', cl,"khDjxh");  
                var link = "<a href=\"javascript:onUpdate('"+val+"','"+khDjxh+"')\"><font color=\"blue\">����</font></a>";
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
     /**************************************��ҳ����****************************************/
	
</script>
</head>
<body>
<s:form action="jskpdzqd!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
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
		        <td width="8%" align="right">ҵ��λ��</td>
				<td width="25%">
					<sys:gsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y" 
						   myClass="select" onChange="bmInit(this.value, '', 'domain.djJgbm', 'mainForm_domain_djJgbm', 'Y', 'Y')" />
				</td>
				<td width="8%" align="right">�Ǽǲ��ţ�</td>
				<td width="21%">
					<select name="domain.djJgbm" id="mainForm_domain_djJgbm" class="select"></select>
				</td>
				<td width="8%" align="right">�ͻ����ƣ�</td>
				<td width="21%">
				<s:hidden name="domain.khDjxh"></s:hidden>
				<div class="inputsel" style="width: 230px; ">
					<s:textfield name="domain.khMc" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width:200px;"></s:textfield> 
					<a href="#" class="icon_arrow" id="fhr" onfocus="this.blur()"></a></div>
				<div class="inputsc">
				<div id="inputSel_fhr"
					class="inputselcont inputselFixedSize ac_results"></div>
				</div>
				</td>
			</tr>
			<tr>
				<td align="right">�Ǽ����ڣ�</td>
				<td>
					<s:textfield name="domain.djrqQ"  cssClass="ymdate"></s:textfield>
					 �� 
					<s:textfield name="domain.djrqZ"  cssClass="ymdate"></s:textfield></td>
				<td colspan="4"></td>
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