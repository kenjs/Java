<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>��ҵ��ɫ</title>
<%@ include file="/common/meta.jsp"%>
<style type="text/css">
html,body {overflow:hidden;}
</style>

<!-- ʵ��IE6��pngͼƬ͸�� -->
<!--[if IE 6]>
<script src="<sys:context />/resource/pageframe/js/DD_belatedPNG_0.0.8a-min.js" type="text/javascript"></script>
<script type="text/javascript">
	DD_belatedPNG.fix('h2, a');
</script>
<![endif]-->
<script type="text/javascript">
	$(document).ready(function(){
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//������ť�¼�
			$("#addBtn").click(function(){
				var ssJgbm = trim($("#mainForm_domain_ssJgbm").val());  
				if(ssJgbm==""||ssJgbm==undefined)
				{
					showAlert("��ѡ���ܹ�˾/�ֹ�˾!");
					return false;
				}
				var url = jcontextPath+"/qyjs!initMx.action?domain.ssJgbm="+ssJgbm;
		    	window.showModalDialog(url,window,"dialogHeight:400px;dialogWidth:500px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		    	onRefresh();
			});
			//��ʼ�����
			initDataGrid();
	});

    function onUpdate(jsDjxh){
    	var url = jcontextPath+"/qyjs!initMx.action?domain.jsDjxh="+jsDjxh;
    	window.showModalDialog(url,window,"dialogHeight:400px;dialogWidth:500px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		    	onRefresh();
    }
    
    var keyValue = "";
	function onDelete(jsDjxh){
		keyValue = jsDjxh;
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.jsDjxh":keyValue};
		 var url = jcontextPath+"/qyjs!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}	
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
		if(ssJgbm==""||ssJgbm==undefined)
		{
			showAlert("��ѡ���ܹ�˾/�ֹ�˾!");
			return false;
		}
  
		//����������
		var url = jcontextPath+"/qyjs!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":encodeURI(ssJgbm)}								//����Ĳ�����json��ʽ
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
		    colNames:['����','״̬', '��ɫ����', '��ɫ���','������λ','��ע˵��','������',
				     '��������','�޸���','�޸�����','����'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'100', align:'center'},
			  {name:'qystr', index:'qystr', width:'50', align:'center'},
		      {name:'jsMc', index:'jsMc', width:'180', align:'left'}, 
		      {name:'jsJc', index:'jsJc', width:'130', align:'center'},
		      {name:'sjMc', index:'sjMc', width:'150', align:'center'},
		      
		      {name:'bzsm', index:'bzsm', width:'180', align:'left'}, 
			  {name:'cjrMc', index:'cjrMc', width:'80', align:'center'}, 
		      {name:'cjrq', index:'cjrq', width:'80', align:'center'}, 
		      {name:'xgrMc', index:'xgrMc', width:'80', align:'center'}, 
		      {name:'xgrq', index:'xgrq', width:'80', align:'center'}, 
		      {name:'jsDjxh', index:'jsDjxh', width:'0', align:'center',hidden:true}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'jsDjxh',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/xtgl/qyjs!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ��������ͣ�á������á�������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var str = jQuery("#dataList").jqGrid('getCell', cl,"qystr");    
                var val = jQuery("#dataList").jqGrid('getCell', cl,"jsDjxh");//��ȡ��ǰ��Ԫ������ĵǼ���� 
                var link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">�޸�</font></a>"
                + " <a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">ɾ��</font></a>";
                if(str == "����"){
                	link=link+" <a href=\"javascript:stop('"+val+"')\"><font color=\"blue\">ͣ��</font></a>"
                }
                else
                {
                	link=link+" <a href=\"javascript:start('"+val+"')\"><font color=\"blue\">����</font></a>"
                }
                var zt;
                 if(str == "ͣ��")
                {
                	zt=" <font color=\"red\">ͣ��</font>"
                }
                else
                {
                	zt=" <font color=\"black\">����</font>"
                }
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
                $("#dataList").jqGrid('setRowData', cl, { 'qystr': zt }); 
            }
     }
     
     //ͣ��  
      var val1="";
   function stop(jsDjxh){
   		val1=jsDjxh;
   		showConfirm("��ȷ��Ҫͣ��ô��","stopCallBack");
   }
   
   function stopCallBack(){
        var url=jcontextPath+"/qyjs!saveDisable";
   		jsonObj={"domain.jsDjxh":val1};
		ajaxCommon(url,jsonObj,"stopSuccess");
   }
   
  function stopSuccess(){
  	   showAlert("ͣ�óɹ���");
  	   onRefresh();
  }
  
  //����
   var val2="";
   function start(jsDjxh){
    	val2=jsDjxh;
    	showConfirm("��ȷ��Ҫ����ô��","startCallBack");
   }
   
   function startCallBack(){
   		var url=jcontextPath+"/qyjs!saveEnable";
		jsonObj={"domain.jsDjxh":val2};
		ajaxCommon(url,jsonObj,"startSuccess");
   }
   
   function startSuccess(){
  	   showAlert("���óɹ���");
  	   onRefresh();
  }
  
    
   
    var keyValue = "";
	function onDelete(jsDjxh){
		keyValue = jsDjxh;
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.jsDjxh":keyValue};
		 var url = jcontextPath+"/qyjs!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}	
     /**************************************��ҳ����****************************************/


</script>
</head>

<body>
<s:form action="qyjs!query" namespace="/xtgl" method="post"
	id="mainForm" name="mainForm">

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
	<fieldset><legend>��ѯ����</legend>
	<table width="95%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="7%" align="right">��λ��</td>
			<td width="35%">
				
				<sys:qxGsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y" myClass="select" ></sys:qxGsList>
				
			</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
		</tr>
	</table>
	</fieldset>
	</div>
	<!-- ��ҳ��� id����ΪdataList -->
	<table id="dataList">
		<tr>
			<td />
		</tr>
	</table>
	<!-- ��ҳ���� -->
	<div id="pager"></div>
	<%@include file="/common/message.jsp"%></div>
</s:form>
</body>
</html>
