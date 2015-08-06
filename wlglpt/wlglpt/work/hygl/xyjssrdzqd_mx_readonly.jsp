<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>���ν���-�������-�嵥</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		//��ʼ�����
		initDataGrid();
		var tempFlag = $("#mainForm_domain_tempFlag").val();
		if ("N" == tempFlag) {
			onRefresh();
		}
	});
	
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var qdDjxh = trim($("#mainForm_domain_qdDjxh").val()); 
		var tempFlag = $("#mainForm_domain_tempFlag").val();
		var fylbDm = $("#mainForm_domain_fylbDm").val();
		
		if ("1" == fylbDm) {
			$("#dataList").jqGrid('showCol',["zcPsf"]);
			$("#dataList").jqGrid('hideCol',["zcDf","zcDshk"]);
		}else if ("2" == fylbDm) {
			$("#dataList").jqGrid('showCol',["zcDf"]);
			$("#dataList").jqGrid('hideCol',["zcDshk","zcPsf"]);
		}else if ("3" == fylbDm) {
			$("#dataList").jqGrid('showCol',["zcDshk"]);
			$("#dataList").jqGrid('hideCol',["zcPsf","zcDf"]);
		}
		
		//����������
		var url = jcontextPath+"/hygl/xyjssrdzqd!queryQdMx";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.qdDjxh":qdDjxh,"domain.tempFlag":tempFlag,"domain.fylbDm":fylbDm
		 		}								//����Ĳ�����json��ʽ
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
			height:pageTableHeight()-150,	
		    gridComplete: myGridComplete,		//����������¼�
		    shrinkToFit:false, 
		    colNames:['����Ǽ����','���ͷ�','������','���ջ���','���˽��','���˲�����',
		    		'�������','�µ�����','ʼ����','Ŀ�ĵ�',
		              "��������","����","����","���","������","�ջ���λ","�ջ���","�ջ���ַ","�ɳ�����","�ɳ�����"],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
		      {name:'jsDjxh', index:'jsDjxh', width:'30', hidden:true, align:'center'},
		      {name:'zcPsf', index:'zcPsf', width:'60', align:'center', hidden:true},
		      {name:'zcDf', index:'zcDf', width:'60', align:'center', hidden:true}, 
		      {name:'zcDshk', index:'zcDshk', width:'60', align:'center', hidden:true}, 
		      {name:'dzje', index:'dzje', width:'60', align:'center'}, 
		      {name:'dzcyje', index:'dzcyje', width:'60', align:'center'}, 
		      {name:'ddbh', index:'ddbh', width:'100', align:'center'}, 
		      {name:'xdrq', index:'xdrq', width:'65', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      {name:'sfdXzqhMc', index:'sfdXzqhMc', width:'70', align:'center'},
		      {name:'mddXzqhMc', index:'mddXzqhMc', width:'70', align:'center'}, 

		      {name:'hwmc', index:'hwmc', width:'100', align:'center'},
		      {name:'hwSl', index:'hwSl', width:'50', align:'center'},
		      {name:'hwZl', index:'hwZl', width:'50', align:'center'},
		      {name:'hwTj', index:'hwTj', width:'50', align:'center'},
		      {name:'fhrLxr', index:'fhrLxr', width:'50', align:'center'},
		      {name:'shrMc', index:'shrMc', width:'60', align:'center'},
		      {name:'shrLxr', index:'shrLxr', width:'50', align:'center'},
		      {name:'shrDz', index:'shrDz', width:'60', align:'center'},

		      {name:'pcrq', index:'pcrq', width:'65', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}, 
		      {name:'pcdh', index:'pcdh', width:'70', align:'center'}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'ddbh',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
		    sortorder: 'DESC',				//Ĭ��������
		    viewrecords: true,					//�����Ƿ�Ҫ��ʾ�ܼ�¼��
		    gridview: false,					//Ĭ��false ����һ�����ݺ���ӵ�grid�У������Ϊtrue���ǽ������������ݶ�������ɺ�����ӵ�grid�У���treeGrid, subGrid, or afterInsertRow ������	
		    jsonReader: {     
	     	 	root: 	 "domain.qdmxList",   				// �����У�Ĭ��Ϊ��rows��
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
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var jsDjxh = jQuery("#dataList").jqGrid('getCell', cl,"jsDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
               	
            }
     }
     /**************************************��ҳ����****************************************/
</script>
</head>

<body>
<%try{ %>
<s:form action="xyjssrdzqd!initMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.qdDjxh"></s:hidden>
	<s:hidden name="domain.xyDjxh"></s:hidden>
	<s:hidden name="domain.tempFlag"></s:hidden>
	<s:hidden name="domain.fylbDm"></s:hidden>
	
	<div class="pop_contc">
		<fieldset>
			<legend>������Ϣ</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
					<td width="12%" align="right"><font class="font_red">*</font>�嵥���ƣ�</td>
      				<td width="21%">
      					<s:textfield name="domain.qdmc" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
      				</td>
      				<td width="12%" align="right">�ϼƽ�</td>
      				<td width="21%">
      					<s:textfield name="domain.heJe" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
      				</td>      				
      				<td width="12%"></td>
      				<td width="21%"></td>
      			</tr>
      			<tr>
      				<td align="right">���˽�</td>
      				<td>
      					<s:textfield name="domain.dzJe" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
      				</td>
      				<td align="right">�����</td>
      				<td>
      					<s:textfield name="domain.dzcyJe" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
      				</td>
      			</tr>
			</table>
		</fieldset>	
		<div class="pop_btn">			
		    <button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
	    </div>
		<table id="dataList"><tr><td/></tr></table>
	</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
