<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>����-��Ʊ��ѯ</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
			document.getElementById("maincont").onmousewheel=hideHelpWindow;
			initHykhData(300, $("#mainForm_domain_ssJgbm").val());
			
			$("#mainForm_domain_ssJgbm").change(function(){
				initHykhData(300,$(this).val());
			});
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
		var khDjxh = $("#mainForm_domain_fhrDjxh").val();
		var fpdm = $("#mainForm_domain_fpdm").val();
		var fphmQ = $("#mainForm_domain_fphmQ").val();
		var fphmZ = $("#mainForm_domain_fphmZ").val();
		if((fphmQ != "" && !IsNumber(fphmQ))||(fphmZ != "" && !IsNumber(fphmZ))){
			showAlert("��Ʊ�������Ϊ���֣�");
			return;
		}
		var rqQ = $("#mainForm_domain_rqQ").val();
		var rqZ = $("#mainForm_domain_rqZ").val();
		//����������
		var url = jcontextPath+"/cwgl/cwkpcx!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.khDjxh":khDjxh,"domain.fpdm":fpdm,
		 			"domain.fphmQ":fphmQ,"domain.fphmZ":fphmZ,
		 		    "domain.rqQ":rqQ,"domain.rqZ":rqZ}
		 											//����Ĳ�����json��ʽ
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
		    shrinkToFit:false, 
		    colNames:['��Ʊ�Ǽ����(SEQ_KP_DJXH)','��Ʊ����Ǽ����(SEQ_QD_DJXH)','�ͻ�����','��Ʊ����','��Ʊ����',
				     '��Ʊ��','��Ʊ����','��Ʊ���','˰��','˰��',
				     '���ϱ�־','���ֱ�־','��Ӧ���ַ�Ʊ����','��Ӧ���ַ�Ʊ����',
				     '��������','�Ǽǲ���','������','��������','�޸���','�޸�����'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
		      {name:'kpDjxh', index:'kpDjxh', width:'0', align:'center',hidden:true}, 
		      {name:'kpsqDjxh', index:'kpsqDjxh', width:'100', align:'center',hidden:true}, 
		      {name:'khmc', index:'khmc', width:'150', align:'center'}, 
		      {name:'fpdm', index:'fpdm', width:'60', align:'center'}, 

		      {name:'fphm', index:'fphm', width:'60', align:'center'}, 
		      {name:'kprMc', index:'kprMc', width:'80', align:'center'}, 
		      {name:'kprq', index:'kprq', width:'80', align:'center'},
		      {name:'kpje', index:'kpje', width:'60', align:'center'}, 
		      {name:'sl', index:'sl', width:'60', align:'center'}, 

		      {name:'se', index:'se', width:'60', align:'center'}, 
		      {name:'zfbz', index:'zfbz', width:'60', align:'center'}, 
		      {name:'hzbz', index:'hzbz', width:'60', align:'center'}, 
		      {name:'lzFpdm', index:'lzFpdm', width:'120', align:'center'}, 
		      {name:'lzFphm', index:'lzFphm', width:'120', align:'center'}, 

		      {name:'ssJgmc', index:'ssJgmc', width:'120', align:'center'}, 
		      {name:'djJgmc', index:'djJgmc', width:'120', align:'center'}, 
		      {name:'cjrMc', index:'cjrMc', width:'80', align:'center'}, 
		      {name:'cjrq', index:'cjrq', width:'80', align:'center'}, 
		      {name:'xgrMc', index:'xgrMc', width:'80', align:'center'}, 
		      {name:'xgrq', index:'xgrq', width:'80', align:'center'}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'kpDjxh',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	    var fphmQ = $("#mainForm_domain_fphmQ").val();
					var fphmZ = $("#mainForm_domain_fphmZ").val();
					if((!isNaN(fphmQ))&&(!isNaN(fphmZ))){
					}else{
						showAlert("��Ʊ�������Ϊ���֣�");
						return;
					}
		       	   $("#mainForm").attr("action",jcontextPath+"/cwgl/cwkpcx!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="cwkpcx!query" namespace="/cwgl" method="post" id="mainForm" name="mainForm">
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
	<div style="display: none;" id="maincont"></div>
	<div class="right_cont">
		<div id="divQuery">
	    		<fieldset>
		      		<legend>��ѯ����</legend>
		      		<table width="99%" border="0" cellspacing="0" cellpadding="0">
		        		<tr>
		          			<td width="8%" align="right">��λ��</td>
		          			<td width="21%">
		          				<sys:gsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y" myClass="select" />
				  			</td>
				  			<td width="8%" align="right">�ͻ���</td>
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
		        			<td width="8%" align="right">��Ʊ���룺</td>
		        			<td width="25%"><s:textfield name="domain.fpdm" cssClass="pop_input bgstyle_optional"></s:textfield></td>
		        		</tr>
		        		<tr>
		        			<td align="right">��Ʊ���룺</td>
		        			<td colspan="3"><s:textfield name="domain.fphmQ" />
	          					��
	          					<s:textfield name="domain.fphmZ" />
		        			</td>
		        			<td align="right">��Ʊ���ڣ�</td>
		        			<td><sys:dateFirstDLastMonthTag myName="domain.rqQ" myId="mainForm_domain_rqQ" myClass="ymdate" />
	          					��
	          					<sys:dateCurrentDayTag myName="domain.rqZ" myId="mainForm_domain_rqZ" myClass="ymdate" /></td>
	          					<td colspan="2"></td>
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
