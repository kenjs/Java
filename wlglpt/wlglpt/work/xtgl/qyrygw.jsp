<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>��ҵ-��Ա��λ</title>
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
				var czyDjxh = $("#mainForm_domain_czyDjxh").val();
				if(czyDjxh==''||czyDjxh==null){
					showAlert("��ѡ��һ���û���");
					return;
				}
				var url = jcontextPath+"/xtgl/qyrygw!initMx.action?domain.czyDjxh="+czyDjxh;
				window.showModalDialog(url,window,"dialogHeight:350px;dialogWidth:480px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
				onRefresh();
			});
			
			//��ʼ�����
			initDataGrid();
						
			var ssJgbm = $("#mainForm_domain_gsbm").val();
		    if(ssJgbm != null && ssJgbm !="" && ssJgbm != undefined){
		        bmInit(ssJgbm, '', "domain.ssJgbm", "mainForm_domain_ssJgbm", "Y", "Y");
		    }
			
			var czyDjxh = $("#mainForm_domain_ssJgbm").val();
			if(czyDjxh != null && czyDjxh !="" && czyDjxh != undefined){
				commonInit('bm-yh', czyDjxh,'', 'domain.czyDjxh', 'mainForm_domain_czyDjxh', 'Y', 'Y');
			}
	});

    function onUpdate(czyDjxh,oldGwDjxh){
    	var url = jcontextPath+"/xtgl/qyrygw!initMx.action?domain.czyDjxh="+czyDjxh+"&domain.oldGwDjxh="+oldGwDjxh;
    	window.showModalDialog(url,window,"dialogHeight:350px;dialogWidth:480px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
    	onRefresh();
    }
    
    var k1 = "";
    var k2 = "";
	function onDelete(czyDjxh,oldGwDjxh){
		k1 = czyDjxh;
		k2 = oldGwDjxh;
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.czyDjxh":k1,"domain.oldGwDjxh":k2};
		 var url = jcontextPath+"/xtgl/qyrygw!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}	
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var gs = $("#mainForm_domain_gsbm").val();	
		var bm = $("#mainForm_domain_ssJgbm").val();	
		var czyDjxh = $("#mainForm_domain_czyDjxh").val();	
		if(bm==''||bm==null){
			bm='';
		}
		if(czyDjxh==null||czyDjxh==''){
			czyDjxh='';
		}
		//����������
		var url = jcontextPath+"/xtgl/qyrygw!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.czyDjxh":czyDjxh,"domain.ssJgbm":bm,"domain.sjJgbm":gs}								//����Ĳ�����json��ʽ
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
		    colNames:['����','�û�����','��λ����', '��/���־','����Ա�Ǽ����(QY_RYDJ.CZY_DJXH)','Ȩ�޻���','��λ����','��������','�����־(Y��Ҫ����/N��ְ����)','��λ�Ǽ����'],
		    			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
			  {name:'mc', index:'mc', width:'80', align:'center'}, 
			  {name:'gwmc', index:'gwmc', width:'150', align:'center'}, 
			  {name:'zjbzStr', index:'zjbzStr', width:'100', align:'center'}, 
		      {name:'czyDjxh', index:'czyDjxh', width:'0', align:'center',hidden:true}, 
		      {name:'qxjgMc', index:'qxjgMc', width:'150', align:'center'},
		      {name:'sjMc', index:'sjMc', width:'150', align:'center'}, 
		      {name:'ssjgMc', index:'ssjgMc', width:'150', align:'center'}, 
		   
		      {name:'zjbz', index:'zjbz', width:'0', align:'center',hidden:true}, 
		    
		      {name:'oldGwDjxh', index:'oldGwDjxh', width:'0', align:'center',hidden:true} 
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'GW_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/xtgl/qyrygw!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val1 = jQuery("#dataList").jqGrid('getCell', cl,"czyDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var val2 = jQuery("#dataList").jqGrid('getCell', cl,"zjbz"); 	  //��ȡ��ǰ��Ԫ������������־
                var val3 = jQuery("#dataList").jqGrid('getCell', cl,"oldGwDjxh"); 	  //��ȡ��ǰ��Ԫ������ĸ�λ�Ǽ����
                if(val2 == "Y"){
                	var link1 = "<font color=\"black\">������</font>";
                	var link2 = "<a href=\"javascript:onUpdate('"+val1+"','"+val3+"')\"><font color=\"blue\">�޸�</font></a>";
                } else {
                	var link1 = "<font color=\"black\">��ְ����</font>";
                	var link2 = "<a href=\"javascript:onUpdate('"+val1+"','"+val3+"')\"><font color=\"blue\">�޸�</font></a>"
                + " <a href=\"javascript:onDelete('"+val1+"','"+val3+"')\"><font color=\"blue\">ɾ��</font></a>";
                }
                $("#dataList").jqGrid('setRowData', cl, { 'zjbzStr': link1 }); 
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link2 }); 
            }
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="qyrygw!query" namespace="/xtgl" method="post" id="mainForm" name="mainForm">
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
		      		<table width="90%" border="0" cellspacing="0" cellpadding="0">
		        		<tr>
		          			<td width="8%" align="right">��λ��</td>
		          			<td width="28%">
		          				<sys:qxGsList myId="mainForm_domain_gsbm" myName="domain.gsbm" mcContainDmBz="Y" myClass="select" onChange="bmInit(this.value, '', 'domain.ssJgbm', 'mainForm_domain_ssJgbm', 'Y', 'Y')" ></sys:qxGsList>
		          				
				  			</td>
				  			<td width="100" align="right">���ţ�</td>
		          			<td width="350">
		          				<select name="domain.ssJgbm" id="mainForm_domain_ssJgbm" class="select" onChange="commonInit('bm-yh', this.value, '', 'domain.czyDjxh', 'mainForm_domain_czyDjxh', 'Y', 'Y')"/>
				  			</td>
				  			<td width="100" align="right">�û���</td>
		          			<td width="350">
		          				<select name="domain.czyDjxh" id="mainForm_domain_czyDjxh" class="select" />
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
