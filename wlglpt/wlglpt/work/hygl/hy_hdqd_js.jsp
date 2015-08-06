<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<html>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>�ص��嵥����</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript">
      $(document).ready(function(){
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			$("#qdCxBz").val('2');
			//��ʼ�����
			initDataGrid();

	});	
	var hdqdDjxh;
	function onDelete(val){
		hdqdDjxh=val;
		showConfirm("ȷ��Ҫɾ�����嵥ô��ɾ���󲻿ɻָ�","doDelete")
	}
	function doDelete(){
		var url = jcontextPath+"/hygl/hypchwxxhdqd!delete";  
        var jsonObj = {"domain.hdqdDjxh":hdqdDjxh};
        ajaxCommon(url,jsonObj,"doSuc");
	}
	function onJs(val){
		var url = jcontextPath+"/hygl/hypchwxxhdqd!qdjs";  
        var jsonObj = {"domain.hdqdDjxh":val};
        ajaxCommon(url,jsonObj,"doSuc");
	}
	function onTh(val){
		hdqdDjxh=val;
		showConfirm("ȷ��Ҫ�˻ظ��嵥ô��ɾ���󲻿ɻָ�","doTh")
	}
	function doTh(){
		var url = jcontextPath+"/hygl/hypchwxxhdqd!qdth";  
        var jsonObj = {"domain.hdqdDjxh":hdqdDjxh};
        ajaxCommon(url,jsonObj,"doSuc");
	}
	function doSuc(){
		onRefresh();
	}
	
	function onView(val){
		var url = jcontextPath+"/hygl/hypchwxxhdqd!initMx.action?domain.hdqdDjxh="+val;
		window.showModalDialog(url,window,"dialogHeight:800px;dialogWidth:900px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
	}
  /**************************************��ҳ��ʼ****************************************/
  //ˢ�±��
	function onRefresh(){
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var qdCxBz = $("#qdCxBz").val();
		var rqQ = $("#mainForm_domain_rqQ").val();
		var rqZ = $("#mainForm_domain_rqZ").val(); 
  		//����������
		var url = jcontextPath+"/hypchwxxhdqd!queryQd.action";   
		 $("#dataList").jqGrid("setGridParam",{ 
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.rqQ":rqQ,"domain.rqZ":rqZ,"domain.qdCxBz":qdCxBz}								//����Ĳ�����json��ʽ
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
		    colNames:['����', '�ص��嵥�Ǽ����(SEQ_HD_DJXH)','�嵥����','����״̬','','','���͹�˾','���չ�˾',
				     '��ע','�����','�������','��������','�Ǽǲ���',
				     '��Ч��־(Y/N)'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
		      {name:'hdqdDjxh', index:'hdqdDjxh', width:'100', align:'center',hidden:true}, 
		      {name:'qdmc', index:'qdmc', width:'100', align:'center'}, 
		      {name:'jszt', index:'jszt', width:'60', align:'center'}, 
		      {name:'fsGsbm', index:'fsGsbm', width:'100', align:'center',hidden:true},
		      {name:'jsGsbm', index:'jsGsbm', width:'100', align:'center',hidden:true}, 
		      {name:'fsGsmc', index:'fsGsmc', width:'100', align:'center'}, 

		      {name:'jsGsmc', index:'jsGsmc', width:'100', align:'center'}, 
		      {name:'bz', index:'bz', width:'250', align:'center'}, 
		      {name:'dbrMc', index:'dbrMc', width:'60', align:'center'}, 
		      {name:'dbrq', index:'dbrq', width:'70', align:'center'}, 
		      {name:'ssJgmc', index:'ssJgmc', width:'100', align:'center'}, 
		      
		      {name:'djJgmc', index:'djJgmc', width:'100', align:'center'}, 
		      {name:'yxbz', index:'yxbz', width:'100', align:'center',hidden:true}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,						
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'HDQD_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
		    sortorder: 'asc',				//Ĭ��������
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
		  
		 
		    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����������
			function myGridComplete() {
		            var graduateIds = $("#dataList").jqGrid('getDataIDs');
		            var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		            for (var i = 0; i < graduateIds.length; i++) {
		                var cl = graduateIds[i];
		                var jszt = jQuery("#dataList").jqGrid('getCell', cl,"jszt"); 	  //��ȡ��ǰ��Ԫ������Ĳ������ 
		                var val = jQuery("#dataList").jqGrid('getCell', cl,"hdqdDjxh"); 
		                var fsGsbm = jQuery("#dataList").jqGrid('getCell', cl,"fsGsbm");
		                var qdmc = jQuery("#dataList").jqGrid('getCell', cl,"qdmc");
		                var str = '��ʼ';
		                var link;
		                if(jszt=='1'){
		                    str = '����';
		                    if(fsGsbm==ssJgbm){
			                    link = "<a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">ɾ��</font></a> ";
		                    }else{
		                    	link = "<a href=\"javascript:onJs('"+val+"')\"><font color=\"blue\">����</font></a> " + 
				                " <a href=\"javascript:onTh('"+val+"')\"><font color=\"blue\">�˻�</font></a>";
		                    }
		                }else if(jszt=='2'){
		                	str = '����';
		                	if(fsGsbm==ssJgbm){
		                    }else{
		                    	link =" <a href=\"javascript:onTh('"+val+"')\"><font color=\"blue\">�˻�</font></a>";
		                    }
		                }else if(jszt=='3'){
		                	str = '�˻�';
		                }
		                linkStr = "<a href=\"javascript:onView('"+val+"')\"><font color=\"blue\">"+qdmc+"</font></a> ";
		                $("#dataList").jqGrid('setRowData', cl, { 'jszt': str });
		                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link });
		                $("#dataList").jqGrid('setRowData', cl, { 'qdmc': linkStr });
		            }
		     }
	}
</script>
</head>
<body>
<s:form action="hypchwxxhdqd!queryQd" theme="simple" namespace="cwgl" method="post" id="mainForm" name="mainForm">
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
				<table width="99%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="10%" align="right">��λ��</td> 
						<td width="22%">  
						   <sys:gsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y"  myClass="select"/>
		  			    </td>
		  			    <td  align="right" width="10%">���ڣ�</td>
		                <td width="22%">
		                  	<sys:dateFirstDMonth myName="domain.rqQ" myId="mainForm_domain_rqQ" myClass="ymdate" />
			          		��
			          		<sys:dateCurrentDayTag myName="domain.rqZ" myId="mainForm_domain_rqZ" myClass="ymdate" />
		                </td>  
						<td width="11%" align="right">�嵥������</td>
						<td width="15%" >
							<s:select list="#{'0':'--- ȫ�� ---','1':' ���͵�','2':' ���յ�' }" id="qdCxBz" cssClass="select"></s:select>
						</td>
						<td width="10%"></td>  
		            </tr>
				</table>
			</fieldset>
		</div>
	
		<!-- ��ҳ��� id����ΪdataList -->
		<table id="dataList"><tr><td /></tr></table>
		<!-- ��ҳ���� -->
		<div id="pager"></div>
	</div>
</s:form>
</body>
