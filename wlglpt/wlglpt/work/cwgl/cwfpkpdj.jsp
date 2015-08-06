<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>����-��Ʊ��Ʊ�Ǽ�</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>

<script type="text/javascript">
	$(document).ready(function(){
			initHykhData(300, $("#mainForm_domain_ssJgbm").val(), $("#mainForm_domain_bmDjxh").val());
			
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//��ʼ�����
			initDataGrid();
			var ssJgbm = $("#mainForm_domain_ssJgbm").val();
			bmInit(ssJgbm, '', "domain.bmDjxh", "mainForm_domain_bmDjxh", "Y", "Y");
	});

    function onRegister(kpsqDjxh){
    	var url = jcontextPath+"/cwgl/cwfpkpdj!initMx.action?domain.kpsqDjxh="+kpsqDjxh;
		window.showModalDialog(url,window,"dialogHeight:350px;dialogWidth:580px;center:yes;resizable:no;status:no;scroll:yes;help:no;");
		onRefresh();
    }
    
    function onUpdate(kpsqDjxh){
    	var url = jcontextPath+"/cwgl/cwfpkpdj!initMx.action?domain.kpsqDjxh="+kpsqDjxh;
		window.showModalDialog(url,window,"dialogHeight:350px;dialogWidth:580px;center:yes;resizable:no;status:no;scroll:yes;help:no;");
		onRefresh();
    }
    
	   function ce(){
		  // showAlert(11.04*100*100);
		   //showAlert(9.2*10000);
		   showAlert(5.2*1000*10+"--");
		 //  showAlert(10.1*100*100);
	   }
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var kpdwJgbm = $("#mainForm_domain_kpdwJgbm").val();
		var khDjxh = $("#mainForm_domain_fhrDjxh").val();
		var khmc = $("#mainForm_domain_fhrMc").val();
		//����������
		var url = jcontextPath+"/cwgl/cwfpkpdj!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.kpdwJgbm":encodeURI(kpdwJgbm), "domain.khDjxh":khDjxh,"domain.khmc":encodeURI(khmc)}								//����Ĳ�����json��ʽ
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
		    colNames:['����','��Ʊ����Ǽ����','��Ʊ���뷽ʽ����','�ͻ��Ǽ����','���뿪Ʊ���ϼ�','���뿪Ʊ����','��Ʊ���߽��',
				     '������', '���벿��', '����ʱ��', '��Ʊ��λ', '��ע˵��'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
			  {name:'kpsqDjxh', index:'kpsqDjxh', hidden:true}, 
			  {name:'kpsqfsDm', index:'kpsqfsDm', hidden:true}, 
		      {name:'khDjxh', index:'khDjxh', hidden:true}, 		      
		      {name:'sqKpjeHj', index:'sqKpjeHj', width:'110', align:'center'}, 
		      {name:'sqKprq', index:'sqKprq', width:'110', align:'center'}, 
		      {name:'fpkjje', index:'fpkjje', width:'100', align:'center'}, 
		      {name:'sqrMc', index:'sqrMc', width:'60', align:'center'}, 
		      {name:'bmMc', index:'bmMc', width:'110', align:'center'},
		      {name:'djrq', index:'djrq', width:'100', align:'center'},
		      {name:'dwMc', index:'dwMc', width:'100', align:'center'},
		      {name:'bzsm', index:'bzsm', width:'100', align:'center'}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'kpsqDjxh',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/cwfpkpdj!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ������Ǽǡ�������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"kpsqDjxh"); 	 
                var link = "<a href=\"javascript:onRegister('"+val+"')\"><font color=\"blue\">�Ǽ�</font></a>";
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="cwfpkpdj!query" namespace="/cwgl" method="post" id="mainForm" name="mainForm">
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
		          			<td width="21%">
		          				<sys:gsList myId="mainForm_domain_kpdwJgbm" myName="domain.kpdwJgbm" mcContainDmBz="Y" myClass="select" />
				  			</td>
							<td width="8%" align="right">�ͻ����ƣ�</td>
					          <td width="21%">
				  					<div class="inputsel" style="width: 230px; ">
				  						<s:hidden name="domain.fhrDjxh"/>
				  						<s:textfield name="domain.fhrMc" cssClass="pop_input noborder inputext bgstyle_optional" style="width:200px;"></s:textfield>
				  						<a href="#" class="icon_arrow" id="fhr" onFocus="this.blur()"></a>
				  					</div>
							  		<div class="inputsc">
						              <div id="inputSel_fhr" class="inputselcont inputselFixedSize ac_results" style="width:390px;">
						              </div>
						            </div>
						          </td>
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
