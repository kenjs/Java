<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<html>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>����-�����ʲ� ��ˮ��¼</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript">
      $(document).ready(function(){
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			$("#mainForm_domain_djJgbm").change(function(){
			    initRy();
				initHykhData(300, $("#mainForm_domain_ssJgbm").val(), $(this).val());
			});				
			//��ʼ�����
			initDataGrid();
			initRy();
			var sjJgbm = $("#mainForm_domain_ssJgbm").val();
			bmInit(sjJgbm, '', "domain.djJgbm", "mainForm_domain_djJgbm", "Y", "Y");

	});	
	function initRy() {
		var sj = $("#mainForm_domain_djJgbm").val();
		commonInit("BMYH", sj, '', "domain.jbrCzyDjxh", "mainForm_domain_jbrCzyDjxh", "Y", false);
	}
	function onUpdate(id){
		var url = jcontextPath+"/cwhbzclsjl!initMx.action?domain.cwbdDjXh="+id;
		window.showModalDialog(url,window,"dialogHeight:400px;dialogWidth:600px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		onRefresh();
  }
  /**************************************��ҳ��ʼ****************************************/
  //ˢ�±��
	function onRefresh(){
		var sj = $("#mainForm_domain_ssJgbm").val();
		var djJgbm = $("#mainForm_domain_djJgbm").val();
		var jbrCzyDjxh = $("#mainForm_domain_jbrCzyDjxh").val();
		var rqQ = $("#mainForm_domain_rqQ").val();
		var rqZ = $("#mainForm_domain_rqZ").val(); 
		var zcflDm = $("#mainForm_domain_zcflDm").val();
		var zh = $("#mainForm_domain_zh").val();
		var rqQ = $("#mainForm_domain_rqQ").val();
		var rqZ = $("#mainForm_domain_rqZ").val();
  		//����������
		var url = jcontextPath+"/cwhbzclsjl!query.action";   
		 $("#dataList").jqGrid("setGridParam",{ 
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":sj,"domain.djJgbm":djJgbm,"domain.jbrCzyDjxh":jbrCzyDjxh,"domain.rqQ":rqQ,"domain.rqZ":rqZ,
		 	          "domain.zcflDm":zcflDm,"domain.zh":zh,"domain.rqQ":rqQ,"domain.rqZ":rqZ}								//����Ĳ�����json��ʽ
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
		    colNames:['����','����Ǽ����','���','��־','����','���','������','��������','����','�˺�','˵��'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false,hidden:true, align:'center'},
			  {name:'cwbdDjXh', index:'cwbdDjXh',hidden:true, align:'center'},
		      {name:'zcflMc', index:'zcflMc', width:'80', align:'center'},
		      {name:'bzmc', index:'bzmc', width:'80', align:'center'}, 	
		      {name:'rq', index:'rq', width:'60', align:'center'},
		      {name:'bdje', index:'bdje', width:'80', align:'right'},
		      {name:'jbrCzyMc', index:'jbrCzyMc', width:'80', align:'center'},  
		      {name:'yhmc', index:'yhmc', width:'200', align:'center'}, 	      
		      {name:'hm', index:'hm', width:'80', align:'center'},			  
			  {name:'zh', index:'zh', width:'80', align:'center'}, 	  
			  {name:'sm', index:'sm', width:'300', align:'left'}
		     ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,						
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'cwbdDjXh',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		  
	  	  // add custom button to export the data to excel
		  $("#dataList").jqGrid('navButtonAdd','#pager',{
		       caption:"", 
		       onClickButton : function () { 
		       	   $("#mainForm").attr("action",jcontextPath+"/cwhbzclsjl!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
		    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����������
			function myGridComplete() {
		            var graduateIds = $("#dataList").jqGrid('getDataIDs');
		            for (var i = 0; i < graduateIds.length; i++) {
		                var cl = graduateIds[i];
		                var val = jQuery("#dataList").jqGrid('getCell', cl,"cwbdDjXh"); 	  //��ȡ��ǰ��Ԫ������Ĳ������ 
		                var link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">��ϸ</font></a>";
		                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
		            }
		     }
	}
</script>
</head>
<body>
<s:form action="cwhbzclsjl!query" theme="simple" namespace="cwgl" method="post" id="mainForm" name="mainForm">
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
						   <sys:gsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y" 
						   myClass="select" onChange="bmInit(this.value, '', 'domain.djJgbm', 'mainForm_domain_djJgbm', 'Y', 'Y')" />
		  			    </td>
		  			    <td width="10%" align="right">���ţ�</td>
          			    <td width="22%">
		          			<select id="mainForm_domain_djJgbm" name="domain.djJgbm" class="select" > </select>
		  			    </td>
						<td width="10%" align="right">�����ˣ�</td>
						<td width="26%">
						<select name="domain.jbrCzyDjxh" id="mainForm_domain_jbrCzyDjxh" class="select" ></select>
						</td> 						
					</tr>
					 <tr> 
						<td align="right">���</td>
						<td>
						    <sys:Zcfl myName="domain.zcflDm" myId="mainForm_domain_zcflDm" myClass="select" contaisQxz="true" >
						      <option value="${domain.zcflDm }" selected="selected"></option>
						    </sys:Zcfl>
						</td>
					    <td  align="right">�����˺ţ�</td>
					    <td ><s:textfield name="domain.zh" cssClass="pop_input bgstyle_optional" /></td> 
					     <td  align="right">���ڣ�</td>
		                <td >
		                  <s:textfield name="domain.rqQ" cssClass="ymdate" readonly="true"></s:textfield> <strong>~</strong> 
		                  <s:textfield name="domain.rqZ" cssClass="ymdate" readonly="true"></s:textfield> 
		                </td>              
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
