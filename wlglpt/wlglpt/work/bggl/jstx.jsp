<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�칫-��ʱͨѶ</title>
<%@ include file="/common/meta.jsp"%>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>

<style type="text/css">
html,body {overflow:hidden;}
</style>

<script type="text/javascript">
	$(document).ready(function(){
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				var lxrDjxh = $("#mainForm_domain_lxrDjxh").val();
				if (lxrDjxh == null || lxrDjxh == "") {
					showAlert("����ѡ��һ����ϵ�ˣ�");
					return;
				}
				onRefresh();
			});
			
			//������ť�¼�
			$("#addBtn").click(function(){
				//popwindow(jcontextPath+"/bggl/jstx!initMx");
				var url = jcontextPath+"/bggl/jstx!initMx.action";
				window.showModalDialog(url,window,"dialogHeight:480px;dialogWidth:760px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
				onRefresh();
			});
			//��ʼ�����
			initDataGrid();
						

	});

    function onUpdate(jstxXh){
    	//popwindow(jcontextPath+"/bggl/jstx!initMx?domain.jstxXh="+jstxXh);
    	var url = jcontextPath+"/bggl/jstx!initMx.action?domain.jstxXh="+jstxXh;
		window.showModalDialog(url,window,"dialogHeight:480px;dialogWidth:760px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		onRefresh();
    }
    
    var keyValue = "";
	function onDelete( jstxXh){
		keyValue = jstxXh;
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.jstxXh":keyValue};
		 var url = jcontextPath+"/bggl/jstx!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}	
	
	function initXzqhData(){
		var url = jcontextPath+"/common/wlglptCommon!queryRyList";	
		var targetObjName="domain.xzqhMc";
		var targetDmObjName="domain.xzqhDm";
		var dropDownSelectedCallback="onDropDownSelected";
		var jsonObj = {"targetObjName":targetObjName,"targetDmObjName":targetDmObjName,"itemIndex":0,"dropDownSelectedCallback":dropDownSelectedCallback};
		initData(url,jsonObj);
	}
	
	function onDropDownSelected(li, itemIndex) {
		var sValue = li.selectValue;
		var jsonStr=$("#mainForm_jsonData").val();
	   	if("[]"!=jsonStr){
	    	var data=eval(jsonStr);
	        $(data).each(function(i,item){
	        	if(sValue==item.xzqhQc){
	        		$("#mainForm_domain_xzqhDm").val(item.xzqhDm);
		        	return;
	        	}
	    	});
		}
	}
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		//����������
		var url = jcontextPath+"/bggl/jstx!query.action";   
		var lxrDjxh = $("#mainForm_domain_lxrDjxh").val();
		var jsrqQ = $("#mainForm_domain_jsrqQ").val();
		
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.lxrDjxh":lxrDjxh,"domain.jsrqQ":jsrqQ}								//����Ĳ�����json��ʽ
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
		    colNames:['��ʱͨѶ���','����','������','��������','������',
				     '���ձ�־','��������'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
		      {name:'jstxXh', index:'jstxXh', hidden:true, align:'center'}, 
		      {name:'nr', index:'nr', width:'500', align:'center'},
		      {name:'fsrCzyMc', index:'fsrCzyMc', width:'80', align:'center'}, 
		      {name:'fsrq', index:'fsrq', width:'120', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'}},
		      
		      {name:'czyMc', index:'czyMc', width:'80', align:'center'}, 
		      {name:'jsbz', index:'jsbz', width:'50', align:'center'}, 
		      {name:'jsrq', index:'jsrq', width:'120', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'}}
		      ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'jstxXh',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/bggl/jstx!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                
                var jsbz = jQuery("#dataList").jqGrid('getCell', cl,"jsbz"); 
                if (jsbz == "N") {
                	jsbz = "<font color='red'>δ����</font>";
                }else {
                	jsbz = "�ѽ���";
                }
                $("#dataList").jqGrid('setRowData', cl, { 'jsbz': jsbz });
            }
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="jstx!query" namespace="/bggl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="dropDownData"></s:hidden>
	<s:hidden name="jsonData"></s:hidden>
	<s:hidden name="targetObjName"></s:hidden>
	<s:hidden name="targetDmObjName"></s:hidden>
	<s:hidden name="itemIndex"></s:hidden>
	<s:hidden name="dropDownSelectedCallback"></s:hidden>
	
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

	<div class="right_cont">
	<div id="divQuery">
	<fieldset>
      <legend>��ѯ����</legend>
      	<table width="95%" border="0" cellspacing="0" cellpadding="0">
      		<tr>
	          <td width="15%" align="right">��ϵ�ˣ�</td>
	          <td width="35%"><s:textfield name="domain.lxrDjxh" cssClass="pop_input" /></td>
	          <td width="15%" align="right">����������</td>
	          <td width="35%"><s:textfield name="domain.jsrqQ" cssClass="ymdate" /> </td>
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
