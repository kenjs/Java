<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>��������</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
$(document).ready(function(){

	//��ѯ��ť�¼�
	$("#queryBtn").click(function(){
		onRefresh();
	});
	
		//��ʼ�����
		initDataGrid();
});

function onDisplay(){
	var thbz = $("[name='domain.thbz']:checked").val();
	if("1"==thbz){
		$("#thrqTrId").show();
	}else{
		$("#thrqTrId").hide();
	}
}
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		//����������
		var thbz = $("input[name='domain.thbz']:checked").val();
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var shrMc = $("#mainForm_domain_shrMc").val();
		var thrqQ = $("#mainForm_domain_thrqQ").val();
		var thrqZ = $("#mainForm_domain_thrqZ").val(); 
		var url = jcontextPath+"/hygl/hwzt!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.shrMc":encodeURI(shrMc),
		 	"domain.thbz":thbz,"domain.thrqQ":thrqQ,"domain.thrqZ":thrqZ}		//����Ĳ�����json��ʽ
		 }
		 ).trigger("reloadGrid");		//��ʾ����һҳ�������������ݷ����仯��ʱ�򣬷�ҳ����Ϣ����
	}
	
	function onRegister(hwztDjxh){
	    var ssJgbm = $("#mainForm_domain_ssJgbm").val(); 	
    	var url = jcontextPath+"/hygl/hwzt!initMx.action?domain.ssJgbm=" + ssJgbm + "&domain.hwztDjxh=" + hwztDjxh;
		window.showModalDialog(url,window,"dialogHeight:250px;dialogWidth:540px;center:yes;resizable:no;status:no;scroll:yes;help:no;");
		onRefresh();
	}
	
	function registerSuccess(){
		showAlert("�Ǽǳɹ���", "onRefresh");
	}
	
	function onDelete(hwztDjxh){
    	var url = jcontextPath+"/hygl/hwzt!delete";
    	var jsonObj = {"domain.hwztDjxh":hwztDjxh};
		ajaxCommon(url,jsonObj,"deleteSuccess");
	}
	
	function deleteSuccess(){
		showAlert("�����ɹ���", "onRefresh");
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
		    colNames:['����','��������Ǽ����','�������','״̬','�ջ���','�ջ���ַ','�ջ���ϵ�绰','����','��װ','����','����','���','����','���ջ���','��������',
		    			'�����˵Ǽ����','������','�������','�����','��ϵ�绰','���֤��'
			    		],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[
			  {name:'hstoperationcol', index:'', sortable:false, width:'40', align:'center'},
		      {name:'hwztDjxh', index:'hwztDjxh', hidden:true},
		      {name:'ddbh', index:'ddbh', width:'70', align:'center'},
		      {name:'zt', index:'zt', width:'40', align:'center'},
		      {name:'shrMc', index:'shrMc', width:'120', align:'center'}, 
		      {name:'shrDz', index:'shrDz', width:'150', align:'center'},  
		      {name:'shrLxdh', index:'shrLxdh', width:'100', align:'center'},  
		      {name:'hwmc', index:'hwmc', width:'120', sortable:false, align:'center'}, 
		      {name:'bz', index:'bz', width:'60', align:'center'},  
		      {name:'sl', index:'sl', width:'60', align:'center'},  
		      {name:'zl', index:'zl', width:'60', align:'center'},  
		      {name:'tj', index:'tj', width:'60', align:'center'},  
		      {name:'srDf', index:'srDf', width:'60', align:'center'},  
		      {name:'dsHk', index:'dsHk', width:'60', align:'center'},  
		     
		      
		      {name:'jsSl', index:'jsSl', width:'60', align:'center'},  
		      {name:'jbrCzyDjxh', index:'jbrCzyDjxh', hidden:true},
		      {name:'jbrmc', index:'jbrmc', width:'100', sortable:false, align:'center'}, 
		      {name:'thrq', index:'thrq', width:'60', sortable:false, align:'center'},
		      {name:'thrMc', index:'thrMc', width:'60', sortable:false, align:'center'},
		      {name:'thrLxdh', index:'thrLxdh', width:'60', sortable:false, align:'center'},
		      {name:'thrSfzh', index:'thrSfzh', width:'60', sortable:false, align:'center'}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'hwztDjxh',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/hwzt!download");
				   $("#mainForm").submit();
		       } 
		 });
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���Ǽǡ������������������ӹ���
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"hwztDjxh");
                var thbz = $("[name='domain.thbz']:checked").val();
                if(thbz == '2'){
                	var link = "<a href=\"javascript:onRegister('"+val+"')\"><font color=\"blue\">�Ǽ�</font></a>";
                }
                else{
                	var link = "<a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">����</font></a>";
                }
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="hwzt!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
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
		        <tr>
		        	<td width="7%" align="right">ҵ��λ��</td>
          			<td width="26%">
          				<sys:gsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y" myClass="select" />
		  			</td>
		  			<td width="7%" align="right">�ջ��ˣ�</td>
		  			<td width="21%">
		  				<s:textfield name="domain.shrMc"></s:textfield>
		  			</td>
		          <td width="7%" align="right">״̬��</td>
		          <td width="15%">
		          	<s:radio name="domain.thbz" list='#{"2":"δ��","1":"����"}' listKey="key" listValue="value" onclick="javascript:onDisplay();"></s:radio>
		          </td>
		        </tr>
		        <tr id="thrqTrId" style="display: none;">
		          <td align="right" id="thrqText">������ڣ�</td>
		          <td id="thrqTdId">
		          <sys:dateFirstDLastMonthTag myName="domain.thrqQ" myId="mainForm_domain_thrqQ" myClass="ymdate" />
	          			��
	          		<sys:dateCurrentDayTag myName="domain.thrqZ" myId="mainForm_domain_thrqZ" myClass="ymdate" />
		          </td>
		        </tr>
		    </table>
		</fieldset>
  </div>
		<!-- ��ҳ��� id����ΪdataList -->
		<table id="dataList"><tr><td/></tr></table> 
		<div id="pager"></div>
		<%@include file="/common/message.jsp" %>
	</div>
</s:form>
</body>
</html>
