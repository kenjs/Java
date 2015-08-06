<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>��ҵ-�ͻ�-����۸�</title>
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
				var bmDjxh = trim($("#mainForm_domain_bmDjxh").val());
				if(bmDjxh==''||bmDjxh==undefined){
					bmDjxh=ssJgbm
				}
				var fhrDjxh = trim($("#mainForm_domain_fhrDjxh").val()); 
				var fhrMc = trim($("#mainForm_domain_fhrMc").val()); 
				if(fhrDjxh==''||fhrDjxh==null){
					showError("�ͻ�����Ϊ�գ�");
					return ;
				}
				var url = jcontextPath+"/zygl/qykhjsjg!initMx.action?domain.ssJgbm="+bmDjxh+"&domain.khDjxh="+fhrDjxh+"&domain.khMc="+fhrMc;
		    	window.showModalDialog(url,window,"dialogHeight:570px;dialogWidth:700px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		    	onRefresh();
			});
			//��ʼ�����
			initDataGrid();
			
			var gs = $("#mainForm_domain_ssJgbm").val();
			bmInit(gs, '', "domain.bmDjxh", "mainForm_domain_bmDjxh", "false", "Y");
	});

    function onUpdate(khDjxh){
    	var url =jcontextPath+"/zygl/qykhjsjg!initMx.action?domain.jsjgDjxh="+khDjxh;
		window.showModalDialog(url,window,"dialogHeight:570px;dialogWidth:700px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		onRefresh();
    }
    
    var keyValue = "";
	function onDelete( khDjxh){
		keyValue = khDjxh;
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.jsjgDjxh":keyValue};
		 var url = jcontextPath+"/zygl/qykhjsjg!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}	
    
	function onDropDownSelected4Fhr(li, itemIndex) {
		var sValue = li.selectValue;
		var jsonStr=$("#mainForm_fhrData").val();
	   	if("[]"!=jsonStr){
	    	var data=eval(jsonStr);
	        $(data).each(function(i,item){
	        	if(sValue==item.fhrMc){
	        		$("#mainForm_domain_fhrDjxh").val(item.fhrDjxh);
	        		commonInit('kh-hw', item.fhrDjxh, '', 'domain.hwDjxh', 'mainForm_domain_hwDjxh', 'Y', 'Y');
	        		
		        	return;
	        	}
	    	});
		}
	}
	
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		
		var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
		var bmDjxh = trim($("#mainForm_domain_bmDjxh").val());
		var fhrMc = trim($("#mainForm_domain_fhrDjxh").val()); 
		var hwDjxh='';
		if(fhrMc!=''&&fhrMc!=null){
			 hwDjxh = trim($("#mainForm_domain_hwDjxh").val());
		}
		if(bmDjxh!=null && bmDjxh!=""){
		  ssJgbm=bmDjxh;
	    }
		//����������
		var url = jcontextPath+"/zygl/qykhjsjg!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.khDjxh":fhrMc,"domain.hwDjxh":hwDjxh}								//����Ĳ�����json��ʽ
		 }
		 ).trigger("reloadGrid");	
		
			//��ʾ����һҳ�������������ݷ����仯��ʱ�򣬷�ҳ����Ϣ����
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
		    colNames:['����', '��������','�ͻ�����','�Ƿ�����ȫ������','ʼ����','Ŀ�ĵ�',
				     '��������','�۸���㹫ʽ','�۸����ϵͳ��ʽ','��Ч����','��Ч��ֹ',
				     '������','��������','�޸���','�޸�����',''],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
		      {name:'sjMc', index:'sjMc', width:'110', align:'center'}, 
		      {name:'khMc', index:'khMc', width:'160', align:'center'}, 
		      {name:'syfw', index:'syfw', width:'110', align:'center'}, 
		      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'70', align:'center'}, 
		      {name:'shrXzqhMc', index:'shrXzqhMc', width:'70', align:'center'}, 
			  {name:'hwMc', index:'hwMc', width:'130', align:'center'},
				
		      {name:'jgjsgs', index:'jgjsgs', width:'150', align:'center'},
		      {name:'xtgs', index:'xtgs', width:'150', align:'center'},
		      {name:'yxqQ', index:'yxqQ', width:'80', align:'center'}, 
		      {name:'yxqZ', index:'yxqZ', width:'80', align:'center'}, 
		      
			  {name:'cjrMc', index:'cjrMc', width:'80', align:'center'},
			  {name:'cjrq', index:'cjrq', width:'80', align:'center'}, 
		      {name:'xgrMc', index:'xgrMc', width:'80', align:'center'}, 
		      {name:'xgrq', index:'xgrq', width:'80', align:'center'},
		      {name:'jsjgDjxh', index:'jsjgDjxh', width:'80', align:'center',hidden:true}
			 ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'KH_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/zygl/qykhjsjg!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"khDjxh"); 
                var syfw = jQuery("#dataList").jqGrid('getCell', cl,"syfw"); 
                //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var id = jQuery("#dataList").jqGrid('getCell', cl,"jsjgDjxh"); 
                var link = "<a href=\"javascript:onUpdate('"+id+"')\"><font color=\"blue\">�޸�</font></a>"
                + " <a href=\"javascript:onDelete('"+id+"')\"><font color=\"blue\">ɾ��</font></a>";
                var str='';
                if(syfw=='Y'){
                	str="��";
                }
                else{
                	str="��";
                }
                $("#dataList").jqGrid('setRowData', cl, { 'syfw': str }); 
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="qykhdjxx!query" namespace="/zygl" method="post" id="mainForm" name="mainForm">
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
	<div class="right_cont" id="maincont">
		<div id="divQuery">
	<fieldset>
		<legend>��ѯ����</legend>
	   <table width="99%" border="0" cellspacing="0" cellpadding="0">
	        <tr>
	          <td width="8%" align="right">��λ��</td>
	          <td width="21%"><sys:gsList myName="domain.ssJgbm" myId="mainForm_domain_ssJgbm" mcContainDmBz="Y" myClass="select" onChange="bmInit(this.value, '', 'domain.bmDjxh', 'mainForm_domain_bmDjxh', 'Y', 'Y')"/></td>
	          <td width="8%" align="right">���ţ�</td>
	          <td width="21%"><select id="mainForm_domain_bmDjxh" name="domain.bmDjxh" class="select"/></td>
	          <td width="8%" align="right">�ͻ���</td>
	          <td width="21%">
  					<div class="inputsel" style="width: 230px; ">
  						<s:hidden name="domain.fhrDjxh"/>
  						<s:textfield name="domain.fhrMc" cssClass="pop_input noborder inputext bgstyle_optional" style="width:200px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="fhr" onFocus="this.blur()"></a>
  					</div>
			  		<div class="inputsc">
		              <div id="inputSel_fhr" class="inputselcont 
		              
		              inputselFixedSize ac_results" style="width:270px;">
		              </div>
		            </div>
		          </td>
		      </tr>
		      <tr>
	          <td width="10%" align="right">���</td>
	          <td width="25%"><select name="domain.hwDjxh" id="mainForm_domain_hwDjxh" class="select"/></td>
	        </tr>
	   </table>
	</fieldset>
  
		<!-- ��ҳ��� id����ΪdataList -->
		<table id="dataList"><tr><td/></tr></table> 
		<!-- ��ҳ���� -->
		<div id="pager"></div>
		</div>
		<%@include file="/common/message.jsp" %>
	</div>
</s:form>
</body>
</html>
