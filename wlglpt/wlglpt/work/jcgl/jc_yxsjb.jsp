<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>Ӫ�����ݱ�</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>

<script type="text/javascript">
	$(document).ready(function(){
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//��ʼ�����
			initDataGrid();

	});
	
	function onViewPc(pcDjxh){
    	var url = jcontextPath+"/jcgl/jcpcxxgl!initMx.action?domain.pcDjxh="+pcDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    }
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var fcrqS = $("#mainForm_domain_fcrqS").val();
		var fcrqZ = $("#mainForm_domain_fcrqZ").val();
		var clhm = $("#mainForm_domain_clhm").val();
		//����������
		var url = jcontextPath+"/jcgl/yxsjb!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.fcrqS":fcrqS,"domain.fcrqZ":fcrqZ,"domain.clhm":encodeURI(clhm)}								//����Ĳ�����json��ʽ
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
		    colNames:['���','�ɳ��Ǽ����','���˵�λ','�ɳ�����','��������','������','�ָ�','����','�½�','��֧��','�ؿ�','�����','�����','���ͷ�',
				     '������ʧ����','������ʧ֧��','����'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'pageXh', index:'pageXh', sortable:false, width:'60', align:'center'},
			  {name:'pcDjxh',index:'pcDjxh',width:'80',align:'center',hidden:true},
		      {name:'clhm',index:'clhm',width:'100', align:'center',sortable:false}, 
		      {name:'pcdh',index:'pcdh',width:'100', align:'center'}, 
		      {name:'pcrq', index:'pcrq', width:'100', align:'center',formatter:'date',
		    	  formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}, 
		      
		      {name:'zsr', index:'zsr', width:'80', align:'center'},
		      {name:'xf', index:'xf', width:'60', align:'center',hidden:true},
		      {name:'df', index:'df', width:'60', align:'center',hidden:true},
		      {name:'hf', index:'hf', width:'60', align:'center',hidden:true},
		      {name:'zzc',index:'zzc',width:'80',align:'center',sortable:false},
			  {name:'hk', index:'hk', width:'60', align:'center',sortable:false},
		      {name:'ysf', index:'ysf', width:'60', align:'center',sortable:false}, 
		      {name:'thf', index:'thf', width:'60', align:'center',sortable:false}, 
		      {name:'psf', index:'psf', width:'60', align:'center',sortable:false}, 

		      {name:'wlssSr', index:'wlssSr', width:'100', align:'center',sortable:false}, 
		      {name:'wlssZc', index:'wlssZc', width:'100', align:'center',sortable:false}, 
		      {name:'lr', index:'lr', width:'80', align:'center',sortable:false}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: '',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
		    sortorder: '',				//Ĭ��������
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
		       	   $("#mainForm").attr("action",jcontextPath+"/jcgl/yxsjb!download.action");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i]; 
                var pcDjxh = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh"); 
                var pcdh = jQuery("#dataList").jqGrid('getCell', cl,"pcdh");
                var xh = jQuery("#dataList").jqGrid('getCell',cl,"pageXh");
                if (xh == 0) {
                	$("#dataList").jqGrid('setRowData', cl, { 'pageXh': '�ϼ�' });
                	continue;
               }
                var strPc="<a href=\"javascript:onViewPc("+pcDjxh+")\"><font color=\"blue\">"+pcdh+"</font></a>";
                $("#dataList").jqGrid('setRowData', cl, { 'pcdh': strPc }); 
            }

     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="yxsjb!query" namespace="/jcgl" method="post" id="mainForm" name="mainForm">
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
		          			<td width="8%" align="right">ҵ��λ��</td>
		          			<td width="21%">
		          				<sys:csGsList myClass="select" myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y"/>
				  			</td>
				  			<td width="8%" align="right">�������ڣ�</td>
		          			<td width="21%">
		          				 <sys:dateFirstDMonth myName="domain.fcrqS" myId="mainForm_domain_fcrqS" myClass="ymdate" />
          							��
          			  			 <sys:dateCurrentDayTag myName="domain.fcrqZ" myId="mainForm_domain_fcrqZ" myClass="ymdate" />
				  			</td>
				  			<td width="8%" align="right">�������룺</td>
		          			<td width="21%">
		          				<s:textfield name="domain.clhm" cssClass="pop_input bgstyle_optional"></s:textfield>
				  			</td>
		        		</tr>
		      		</table>
	    		</fieldset>
	    		<div />
	    		<fieldset>
	    		˵����1.<font color="red">��֧��</font> =�ؿ�+�����+�����+���ͷ�
	    			2.<font color="red">����</font> =��������+������ʧ���룩-����֧��+������ʧ֧������
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
