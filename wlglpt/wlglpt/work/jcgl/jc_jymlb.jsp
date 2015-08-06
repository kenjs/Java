<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>ë����</title>
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
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var fcrqS = $("#mainForm_domain_fcrqS").val();
		var fcrqZ = $("#mainForm_domain_fcrqZ").val();
		var fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		var fhrMc = $("#mainForm_domain_fhrMc").val();
		var ddbh = $("#mainForm_domain_ddbh").val();
		//����������
		var url = jcontextPath+"/jcgl/jymlb!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.fcrqS":fcrqS,"domain.fcrqZ":fcrqZ,
		 		"domain.fhrDjxh":fhrDjxh,"domain.fhrMc":encodeURI(fhrMc),"domain.ddbh":ddbh}								//����Ĳ�����json��ʽ
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
			//footerrow: true,
			//userDataOnFooter: true,
		    gridComplete: myGridComplete,		//����������¼�
		    shrinkToFit:false, 
		    colNames:['���','��λ����','�ͻ�����','�µ�����','�е���','��������','Ŀ�ĵ�','�ص���','����','�ؿ�','�����','�����','���ͷ�',
				     '������ʧ����','������ʧ֧��','ë��','ë����'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'pageXh', index:'pageXh', sortable:false, width:'60', align:'center'},
			  {name:'ssjgMc',index:'ssjgMc',width:'120',align:'center'},
		      {name:'khmc',index:'khmc',width:'150', align:'center'}, 
		      {name:'xdrq',index:'xdrq',width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}, 
		      {name:'ddbh', index:'ddbh', width:'100', align:'center'}, 
		      {name:'clhm', index:'clhm', width:'80', align:'center'}, 
		      {name:'mdd', index:'mdd', width:'60', align:'center'}, 
		      {name:'hdbh', index:'hdbh', width:'40', align:'center'}, 
		      
		      {name:'zsr', index:'zsr', width:'60', align:'center'}, 
			  {name:'hk', index:'hk', width:'60', align:'center'},
		      {name:'ysf', index:'ysf', width:'60', align:'center'}, 
		      {name:'thf', index:'thf', width:'60', align:'center'}, 
		      {name:'psf', index:'psf', width:'60', align:'center'}, 

		      {name:'wlssSr', index:'wlssSr', width:'80', align:'center'}, 
		      {name:'wlssZc', index:'wlssZc', width:'80', align:'center'}, 
		      {name:'ml', index:'ml', width:'60', align:'center'},
		      {name:'mll', index:'mll', width:'60', align:'center'}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'XDRQ',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/jcgl/jymlb!download.action");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i]; 
                var xh = jQuery("#dataList").jqGrid('getCell',cl,"pageXh");
                if (xh == 0) {
                	$("#dataList").jqGrid('setRowData', cl, { 'pageXh': '�ϼ�' });
                	continue;
               }               
            }

     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="jymlb!query" namespace="/jcgl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="fhrData" />
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
	
	<div id="maincont" style="display: none;"></div>
	
	<div class="right_cont">
		<div id="divQuery">
	    		<fieldset>
		      		<legend>��ѯ����</legend>
		      		<table width="99%" border="0" cellspacing="0" cellpadding="0">
		        		<tr>
		          			<td width="8%" align="right">ҵ��λ��</td>
		          			<td width="15%">
		          				<sys:csGsList myClass="select" myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y"/>
				  			</td>
				  			<td width="8%" align="right">�µ����ڣ�</td>
		          			<td width="19%">
		          				 <sys:dateFirstDMonth myName="domain.fcrqS" myId="mainForm_domain_fcrqS" myClass="ymdate" />
          							��
          			  			 <sys:dateCurrentDayTag myName="domain.fcrqZ" myId="mainForm_domain_fcrqZ" myClass="ymdate" />
				  			</td>				  			
		        		</tr>
		        		<tr>
		        			<td align="right">�е��ţ�</td>
		          			<td>
		          				<s:textfield name="domain.ddbh" cssClass="pop_input bgstyle_optional"></s:textfield>
				  			</td>
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
