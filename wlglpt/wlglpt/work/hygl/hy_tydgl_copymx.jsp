<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>����-���˵�����-����</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
		
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				
				if(!checkRq()){
					return;
				}
				onRefresh();
			});
			
			$("#closeBtn").click(function(){
				window.close();
			});
			//��ʼ�����
			initDataGrid();	
			
			$("#readyBtn").click(function(){
				var checkbox=$(":checked[name='box']");
				if (checkbox.length <= 0) {
					showAlert("��ѡ��һ�����ڸ��Ƶ����˵���");
					return;
				}
				window.dialogArguments.initTydFromCopy(checkbox.val());
				window.close();
			})
	});
	
	window.onload = function init() {
		onRefresh();
	}
	
	function checkRq(){
		var xdrqQ = $("#mainForm_domain_xdrqQ").val();
		var xdrqZ = $("#mainForm_domain_xdrqZ").val();
		if(trim(xdrqQ)==''||trim(xdrqZ)==''){
			showError("�������µ�����");
			return false;
		}
		return true;
	}
	function getDdDjhx(ddDjxh,index){
		//alert(ddDjxh);
		var checkbox=$(":checkbox[name='box']");
		for(var i=0;i<checkbox.length;i++){
			checkbox[i].checked=false;
		}
		checkbox[index].checked=true;
	}	

    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		var fhrMc = $("#mainForm_domain_fhrMc").val();
		var shrMc = $("#mainForm_domain_shrMc").val();
		var xdrqQ = $("#mainForm_domain_xdrqQ").val();
		var xdrqZ = $("#mainForm_domain_xdrqZ").val();
		//����������
		var url = jcontextPath+"/hygl/hytydgl!queryCopy.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.fhrDjxh":fhrDjxh,"domain.fhrMc":encodeURI(fhrMc), "domain.shrMc":encodeURI(shrMc),
		 			"domain.xdrqQ":xdrqQ,"domain.xdrqZ":xdrqZ
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
			height:pageTableHeight()-90,	
		    gridComplete: myGridComplete,		//����������¼�
		    shrinkToFit:false, 
		    colNames:['����', '�����Ǽ����','�������','ʼ����', 'Ŀ�ĵ�', '������', '�ͻ�����', 
		              '�µ�����', 'Ҫ�󷢻�����', '�ջ�������', 'Ҫ�󵽻�����', '�ջ���ʽ','�Ǽ���',
		              '�Ǽǲ���','��������'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
			  {name:'ddDjxh', index:'ddDjxh', width:'0', hidden:true, align:'center'}, 
			  {name:'ddbh', index:'ddbh', width:'90', align:'center'}, 
		      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'50',align:'center'}, 
		      {name:'shrXzqhMc', index:'shrXzqhMc', width:'50', align:'center'}, 
			  {name:'srHj', index:'srHj', width:'70', align:'center'},
			  
			  {name:'fhrMc', index:'fhrMc', width:'100', align:'center'}, 
			  {name:'xdrq', index:'xdrq', width:'70', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}, 
		      {name:'yqFhrq', index:'yqFhrq', width:'70', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}, 
		      {name:'shrMc', index:'shrMc', width:'100', align:'center'},
		      {name:'yqDdrq', index:'yqDdrq', width:'70', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      {name:'shfsMc', index:'shfsMc', width:'70', align:'center'},
		      {name:'djrMc', index:'djrMc', width:'80',  align:'center'},
		      {name:'djJgmc', index:'djJgmc', width:'80',  align:'center'},
		      {name:'ssJgmc', index:'ssJgmc', width:'80', align:'center'}
		      ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: -1,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[-1],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'ddDjxh,fhrMc',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
		    sortorder: 'DESC,ASC',				//Ĭ��������
		    viewrecords: true,					//�����Ƿ�Ҫ��ʾ�ܼ�¼��
		    gridview: false,					//Ĭ��false ����һ�����ݺ���ӵ�grid�У������Ϊtrue���ǽ������������ݶ�������ɺ�����ӵ�grid�У���treeGrid, subGrid, or afterInsertRow ������	
		    jsonReader: {     
	     	 	root: 	 "domain.copyList",   				// �����У�Ĭ��Ϊ��rows��
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
		  /* $("#dataList").jqGrid('navButtonAdd','#pager',{
		       caption:"", 
		       onClickButton : function () { 
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/hytydgl!download");
				   $("#mainForm").submit();
		       } 
		 }); */
		 
	}
	
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
               var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh"); 
				var link = '<input type="checkbox" name="box" value="'+ddDjxh+'"  onclick="getDdDjhx('+ddDjxh+','+i+')"/>';
         
             
               	
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������

       
   	
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="hytydgl!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.fhrDjxh" />
	<s:hidden name="domain.fhrMc" />
	
	<div class="right_cont">
	  <div id="divQuery">
		<fieldset>
			<legend>��ѯ����</legend>
		   <table width="95%" border="0" cellspacing="0" cellpadding="0">
		        
		        <tr>
	        	  <td width="15%" align="right">�µ����ڣ�</td>
		          <td width="35%">
		          		   <sys:dateFirstDLastMonthTag myName="domain.xdrqQ" myId="mainForm_domain_xdrqQ" myClass="ymdate" />
	          			��
	          		<sys:dateCurrentDayTag myName="domain.xdrqZ" myId="mainForm_domain_xdrqZ" myClass="ymdate" />
	          		</td>
		          <td width="15%" align="right">�ջ���λ��</td>
		          <td width="35%">
		          	<s:textfield name="domain.shrMc" cssClass="pop_input bgstyle_optional"></s:textfield>
		          </td>
		        </tr>
		   </table>
		</fieldset>
	  </div>
  		 <div class="pop_btn">
	    	<button type="button" class="pop_btnbg" id="queryBtn">�� ��</button>
		 	&nbsp;
		 	<button type="button" class="pop_btnbg" id="readyBtn">ȷ ��</button>
		 	&nbsp;
		    <button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
	    </div>
  
		<!-- ��ҳ��� id����ΪdataList -->
		<table id="dataList"><tr><td/></tr></table> 
		<!-- ��ҳ���� -->
		</div>
		<%@include file="/common/message.jsp" %>
	</div>
</s:form>
</body>
</html>
