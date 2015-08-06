<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>�û�ά��</title>
<%@ include file="/common/meta.jsp"%>
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
			var ssJgbm= $("#mainForm_domain_ssJgbm").val(); 
			var gsbm = $("#mainForm_domain_dwDjxh").val();
				var url = jcontextPath+"/yhwh!initMx.action?domain.ssJgbm="+ssJgbm+"&domain.gsbm="+gsbm;
		    	window.showModalDialog(url,window,"dialogHeight:480px;dialogWidth:700px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		    	onRefresh();
			});
			//��ʼ�����
			var sjJgbm = $("#mainForm_domain_dwDjxh").val();
			bmInit(sjJgbm, '', "domain.ssJgbm", "mainForm_domain_ssJgbm", "Y", "Y");;
			initDataGrid();
	});

    function onUpdate(czyDjxh){
    	var url = jcontextPath+"/yhwh!initMx.action?domain.czyDjxh="+czyDjxh;
		window.showModalDialog(url,window,"dialogHeight:480px;dialogWidth:700px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		onRefresh();
    }
        
    var keyValue = "";
	function onDelete( czyDjxh){
		keyValue = czyDjxh;
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.czyDjxh":keyValue};
		 var url = jcontextPath+"/yhwh!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}	
	
     var val1="";
   function stop(id){
   		val1=id
   		showConfirm("��ȷ��Ҫͣ��ô��","stopCallBack");
   }  
    
   function stopCallBack(){
        var url=jcontextPath+"/yhwh!saveDisable";
   		jsonObj={"domain.czyDjxh":val1};
		ajaxCommon(url,jsonObj,"stopSuccess");
   }  
    
  function stopSuccess(){
  	   showAlert("ͣ�óɹ���");
  	   onRefresh();
  }  
  
   var val2="";
   function start(id){
    	val2=id;
    	showConfirm("��ȷ��Ҫ����ô��","startCallBack");
   }   
   
   function startCallBack(){
   		var url=jcontextPath+"/yhwh!saveEnable";
		jsonObj={"domain.czyDjxh":val2};
		ajaxCommon(url,jsonObj,"startSuccess");
   }   
   
   function startSuccess(){
  	   showAlert("���óɹ���");
  	   onRefresh();
  }
  
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var dw =trim($("#mainForm_domain_dwDjxh").val()); 
		var bm =trim($("#mainForm_domain_ssJgbm").val()); 
		if(bm==""||bm==undefined){
			bm = dw;
		}
		//����������
		var url = jcontextPath+"/yhwh!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.jgbm":bm}								//����Ĳ�����json��ʽ
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
		    colNames:['����', '״̬','��Ա����','��½�˺�','��λ','���ڲ���', '�ֻ�����',
		             '�칫�绰', '��ͥ�绰','QQ����','MSN����','EMAIL��ַ',
		            '������','����ʱ��','�޸���','�޸�ʱ��','ID'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'100', align:'center'},
		      {name:'qystr', index:'qystr', width:'50', align:'center'}, 
		      {name:'mc', index:'mc', width:'80', align:'center'}, 
		      {name:'zh', index:'zh', width:'80', align:'center'}, 
		      {name:'gwMc', index:'gwMc', width:'100', align:'center'}, 
		      {name:'jc', index:'jc', width:'100', align:'center'}, 
		      {name:'sjhm', index:'sjhm', width:'80', align:'center'}, 		       
		      {name:'bgdh', index:'bgdh', width:'70', align:'center'}, 		     
		      {name:'jtdh', index:'jtdh', width:'70', align:'center'}, 
		      {name:'qq', index:'qq', width:'60', align:'center'}, 
		      {name:'msn', index:'msn', width:'60', align:'center'}, 
		      {name:'email', index:'email', width:'110', align:'center'}, 
		      {name:'cjrMc', index:'cjrMc', width:'70', align:'center'}, 
		      {name:'cjrq', index:'cjrq', width:'70', align:'center'}, 
		      {name:'xgrMc', index:'xgrMc', width:'70', align:'center'}, 
		      {name:'xgrq', index:'xgrq', width:'70', align:'center'}, 
		      {name:'czyDjxh', index:'czyDjxh', width:'0', align:'center', hidden:true}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,						
		   rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'czyDjxh',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
		    sortorder: 'DESC',				//Ĭ��������
		    viewrecords: true,					//�����Ƿ�Ҫ��ʾ�ܼ�¼��
		    gridview: false,					//Ĭ��false ����һ�����ݺ���ӵ�grid�У������Ϊtrue���ǽ������������ݶ�������ɺ�����ӵ�grid�У���treeGrid, subGrid, or afterInsertRow ������	
		    jsonReader: {     
	     	 	root: 	 "domain.dataList",   				// �����У�Ĭ��Ϊ��rows��
	     	 	page:	 "domain.page.curPage",					// ��ǰҳ
	     	 	total: 	 "domain.page.totalPages",				// ��ҳ��
	     	 	records: "domain.page.totalRecords", 			// �ܼ�¼��
	     	 	//userdata: "userdata",						    // ��ĳЩ����£�������Ҫ�ӷ������˷���һЩ������������ֱ�Ӱ�������ʾ������У��������ڱ�ĵط���ʾ
	        	repeatitems : false   	     	},
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
		       	   $("#mainForm").attr("action",jcontextPath+"/xtgl/yhwh!download");
				   $("#mainForm").submit();
		       } 
		 });
		
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');      
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"czyDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var str = jQuery("#dataList").jqGrid('getCell', cl,"qystr");
                var link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">�޸�</font></a>"
                + " <a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">ɾ��</font></a>";
                 if(str=="����"){
                	link=link+" <a href=\"javascript:stop('"+val+"')\"><font color=\"blue\">ͣ��</font></a>"
                }else{
                	link=link+" <a href=\"javascript:start('"+val+"')\"><font color=\"blue\">����</font></a>"
                }
                var zt;
                 if(str=="ͣ��"){
                	zt=" <font  color=\"red\" >ͣ��</font>"
                }else{
                	zt=" <font>����</font>"
                }                
                
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
                $("#dataList").jqGrid('setRowData', cl, { 'qystr': zt }); 
            }
     }
     /**************************************��ҳ����****************************************/


</script>
</head>

<body>
<s:form action="yhwh!query" namespace="xtgl" method="post" id="mainForm" name="mainForm">
<div class="right_btnbg">
	    <ul class="lcont">
		    <li class="no" >������</li>
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
		          			<td width="2%" align="right">��λ��</td>
		          			<td width="15%">
		          					<sys:qxGsList myId="mainForm_domain_dwDjxh" myName="domain.dwDjxh" mcContainDmBz="Y" myClass="select" onChange="bmInit(this.value, '', 'domain.ssJgbm', 'mainForm_domain_ssJgbm', 'Y', 'Y')"></sys:qxGsList>
		          			
				  			</td>
				  			<td width="2%" align="right">���ţ�</td>
		          			<td width="15%">
		          				<select name="domain.ssJgbm" id="mainForm_domain_ssJgbm" class="select" />
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
