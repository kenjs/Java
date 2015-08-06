<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>���ν���-�������-�嵥</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>

<script type="text/javascript">
	$(document).ready(function(){
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//������ť�¼�
			$("#addBtn").click(function(){
				var xyDjxh = trim($("#mainForm_domain_xyDjxh").val());
				
				if(undefined==xyDjxh || null==xyDjxh || ""==xyDjxh){
					showAlert("����ѡ�����ε�λ��");
					return;
				}
				
				var fylbDm = $("[name='domain.fylbDm']:checked").val();
				
				if (fylbDm == "") {
					showAlert("��ѡ��һ���������");
					return;
				}
				
				onUpdate('',xyDjxh,fylbDm);
			});
			
			//��ʼ�����
			initDataGrid();
						
			var sjJgbm = $("#mainForm_domain_ssJgbm").val();
			bmInit(sjJgbm, '', "domain.djJgbm", "mainForm_domain_djJgbm", "Y", "Y");
	});
	
    function onUpdate(qdDjxh,xyDjxh,fylbDm){
    	var url = jcontextPath+"/hygl/xyjssrdzqd!initMx?domain.qdDjxh="+qdDjxh+"&domain.xyDjxh="+xyDjxh+"&domain.fylbDm="+fylbDm;
    	navigateMenu(url,'���ζ����嵥ά��','true');
    }
    
    var keyValue = "";
	function onDelete( qdDjxh){
		keyValue = qdDjxh;
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.qdDjxh":keyValue};
		 var url = jcontextPath+"/hygl/xyjssrdzqd!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}
	
	function onDz(qdDjxh, fylbDm) {
		var url = jcontextPath+"/hygl/xyjssrdz!init?domain.qdDjxh="+qdDjxh+"&domain.fylbDm="+fylbDm;
    	navigateMenu(url,'���ζ���','true');
	}
	
	function onSend(qdDjxh) {
		var jsonObj = {"domain.qdDjxh":qdDjxh};
		 var url = jcontextPath+"/hygl/xyjssrdzqd!sendDzqd";   
        ajaxCommon(url,jsonObj , "doSendSuc");  
	}
	
	function doSendSuc(data) {
		showAlert("���ͳɹ������ε�λ���ɶԸ��嵥����ȷ�ϡ�");
		onRefresh();
	}
	
	function onView(qdDjxh,xyDjxh,fylbDm){
    	var url = jcontextPath+"/hygl/xyjssrdzqd!viewQrMx?domain.qdDjxh="+qdDjxh+"&domain.xyDjxh="+xyDjxh+"&domain.fylbDm="+fylbDm;
    	navigateMenu(url,'�嵥�鿴','true');
    }
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var djJgbm = trim($("#mainForm_domain_djJgbm").val()); 
		var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
		var xyDjxh = trim($("#mainForm_domain_xyDjxh").val()); 
		var rqQ = trim($("#mainForm_domain_rqQ").val()); 
		var rqZ = trim($("#mainForm_domain_rqZ").val()); 
		var fylbDm = trim($(":checked[name='domain.fylbDm']").val());
		
		//����������
		var url = jcontextPath+"/hygl/xyjssrdzqd!query";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.xyDjxh":xyDjxh,"domain.rqQ":encodeURI(rqQ),"domain.rqZ":encodeURI(rqZ),
			      "domain.djJgbm":djJgbm,"domain.ssJgbm":ssJgbm,"domain.fylbDm":fylbDm}								//����Ĳ�����json��ʽ
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
		    colNames:['����', '�嵥�Ǽ����','fylbDm','�嵥����','��������','�ϼƽ��','���˽��','������',
				     '�Ǽ���','�Ǽ�����','�Ǽǲ���','��������','״̬','�������',''],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
		      {name:'qdDjxh', index:'qdDjxh', width:'100', hidden:true, align:'center'},
		      {name:'fylbDm', index:'fylbDm', width:'100', hidden:true, align:'center'}, 
		      {name:'qdmc', index:'qdmc', width:'140', align:'center'}, 
		      {name:'xyMc', index:'xyMc', width:'100', align:'center'},

		      {name:'heJe', index:'heJe', width:'60', align:'center'}, 
		      {name:'dzJe', index:'dzJe', width:'60', align:'center'},
		      {name:'dzcyJe', index:'dzcyJe', width:'60', align:'center'},

		      {name:'djrMc', index:'djrMc', width:'45', align:'center'}, 
		      {name:'djrq', index:'djrq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}, 
		      {name:'djJgmc', index:'djJgmc', width:'80', align:'center'}, 
		      {name:'ssJgmc', index:'ssJgmc', width:'80', align:'center'},
		      {name:'ztStr', index:'ztStr', width:'80', align:'center'},
		      {name:'bz', index:'bz', width:'80', align:'center'},
		      {name:'zt', index:'zt', width:'80', align:'center',hidden:true}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'QD_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/xyjssrdzqd!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"qdDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ����
                var fylbDm = jQuery("#dataList").jqGrid('getCell', cl,"fylbDm");
                var zt = jQuery("#dataList").jqGrid('getCell', cl,"zt");
                var link = "";
                if(zt == '3'){
                	link = "<a href=\"javascript:onView('"+val+"')\"><font color=\"blue\">�鿴</font></a>";
                }else 
                if(zt == '1' || zt == '4' || zt == ''){
                	link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">�޸�</font></a>"
                	+ " <a href=\"javascript:onDz('"+val+"','"+fylbDm+"')\"><font color=\"blue\">����</font></a>"
                    + "<br/><a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">ɾ��</font></a>"
                    + " <a href=\"javascript:onSend('"+val+"')\"><font color=\"blue\">����</font></a>";
                }else {
                	link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">�޸�</font></a>"
                	+ " <a href=\"javascript:onDz('"+val+"','"+fylbDm+"')\"><font color=\"blue\">����</font></a>"
                    + "<br/><a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">ɾ��</font></a>";
                }
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="xyjssrdzqd!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<div class="right_btnbg">
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

	<div class="right_cont">
	<div id="divQuery">
	<fieldset><legend>��ѯ����</legend>
	<table width="99%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="8%" align="right">ҵ��λ��</td>
			<td width="25%">
				<sys:gsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y" myClass="select" onChange="initList()" /></td>
			<td width="8%" align="right">�Ǽǲ��ţ�</td>
			<td width="21%">
				<select name="domain.djJgbm" id="mainForm_domain_djJgbm" class="select">
					<option value="${domain.djJgbm }" selected="selected"></option>
				</select>
			</td>
			<td width="8%" align="right">���ε�λ��</td>
			<td width="21%">
				<sys:fgsList myId="mainForm_domain_xyDjxh" myName="domain.xyDjxh" contaisQxz="true" contaisDq="N" myClass="select"></sys:fgsList>
			</td>
		</tr>
		<tr>
			<td align="right">�������ڣ�</td>
			<td>
				<sys:dateFirstDMonth myName="domain.rqQ" myId="mainForm_domain_rqQ" myClass="ymdate"></sys:dateFirstDMonth>
				 �� 
				<sys:dateCurrentDayTag myName="domain.rqZ" myId="mainForm_domain_rqZ" myClass="ymdate"></sys:dateCurrentDayTag>
			</td>
			<td>�������</td>
			<td colspan="3">
				<s:radio name="domain.fylbDm" list="#{'':'����','1':'���ͷ�','2':'������','3':'���ջ���' }"></s:radio>
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
