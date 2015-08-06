<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>����-�����ʲ���ʼ��</title>
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
				 var gsbm=$("#mainForm_domain_gsbm").val();
				var url=jcontextPath+"/cwhbcsh!initMx.action?domain.ssJgbm="+gsbm;
				window.showModalDialog(url,window,"dialogHeight:330px;dialogWidth:620px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		    	onRefresh();
			});
			//��ʼ�����
			initDataGrid();
						

	});

    function onUpdate(cshDjxh){
    	popwindow(jcontextPath+"/cwhbzccsh!initMx?domain.cshDjxh="+cshDjxh);
    }
    
    var keyValue = "";
	function onDelete( cshDjxh,val){
		keyValue = cshDjxh;
		if(val!='���д��'){
			showError("���ʲ����಻��ɾ����");
		}
		else{
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
		}
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.cshDjxh":keyValue};
		 var url = jcontextPath+"/cwhbcsh!delete";   
        ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}	
    
	var val1="";
	function stop(id){
		val1=id;
		showConfirm("��ȷ��Ҫͣ��ô��","stopCallBack");
	}
	function stopCallBack(){
		var url=jcontextPath+"/cwhbcsh!saveDisable";
		var obj={"domain.cshDjxh":val1};
		ajaxCommon(url,obj,"stopOk");
	}
	function stopOk(){
		showAlert("ͣ�óɹ���");
		onRefresh();
	}
	
	 var val2="";
	   function start(id){
	    	val2=id;
	    	showConfirm("��ȷ��Ҫ����ô��","startCallBack");
	   }
	   
	   function startCallBack(){
	   		var url=jcontextPath+"/cwhbcsh!saveEnable";
			jsonObj={"domain.cshDjxh":val2};
			ajaxCommon(url,jsonObj,"startSuccess");
	   }
	   
	   function startSuccess(){
	  	   showAlert("���óɹ���");
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
		  var gsbm=$("#mainForm_domain_gsbm").val();
		//����������
		var url = jcontextPath+"/cwhbcsh!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":gsbm}								//����Ĳ�����json��ʽ
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
		    colNames:['����','״̬','��������','�ʲ���������','��������','�û���',
				     '�˺�','��ʼ���','������',
				     '��������','�޸���','�޸�����','',''],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
			  {name:'qyStr', index:'qyStr', width:'100', align:'center'}, 
		      {name:'sjMc', index:'sjMc', width:'100', align:'center'}, 
		      {name:'flMc', index:'flMc', width:'150', align:'center'}, 
		      {name:'yhmc', index:'yhmc', width:'150', align:'center'}, 

		      {name:'hm', index:'hm', width:'100', align:'center'}, 
		      {name:'zh', index:'zh', width:'100', align:'center'}, 
		      {name:'csje', index:'csje', width:'100', align:'center'}, 
		

		      {name:'cjrMc', index:'cjrMc', width:'100', align:'center'}, 
		      {name:'cjrq', index:'cjrq', width:'100', align:'center'}, 
		      {name:'xgrMc', index:'xgrMc', width:'100', align:'center'}, 
		      {name:'xgrq', index:'xgrq', width:'100', align:'center'},
		      {name:'qybz', index:'qybz', width:'100', align:'center',hidden:true},
		      {name:'cshDjxh', index:'cshDjxh', width:'100', align:'center',hidden:true}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'CSH_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/cwhbcsh!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"cshDjxh"); 	 
                var flMc = jQuery("#dataList").jqGrid('getCell', cl,"flMc"); 	 
                var qybz = jQuery("#dataList").jqGrid('getCell', cl,"qybz");//��ȡ��ǰ��Ԫ������ĵǼ���� 
                var link ="<a href=\"javascript:onDelete('"+val+"','"+flMc+"')\"><font color=\"blue\">ɾ��</font></a>";
                if(qybz=='Y'){
                	link+=" <a href=\"javascript:stop('"+val+"')\"><font color=\"blue\">ͣ��</font></a>"
                }
                else{
                	link+=" <a href=\"javascript:start('"+val+"')\"><font color=\"blue\">����</font>";
                }
                var str="";
                if(qybz=='Y'){
                	str="<font>����</font>"
                }
                else{
                	str="<font color=\"red\">ͣ��</font>"
                }
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
                $("#dataList").jqGrid('setRowData', cl, { 'qyStr': str }); 
            }
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="cwhbcsh!query" namespace="" method="post" id="mainForm" name="mainForm">
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
		      		<table width="33%" border="0" cellspacing="0" cellpadding="0">
		        		<tr>
		          			<td width="3%" align="right">��λ��</td>
		          			<td width="15%">
		          				<sys:gsList myId="mainForm_domain_gsbm" myName="domain.ssJgbm" mcContainDmBz="Y" myClass="select" />
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
