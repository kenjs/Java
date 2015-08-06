<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>����-���ñ���-����</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//������ť�¼�
			$("#addBtn").click(function(){
				
				var url = jcontextPath+"/cwgl/cwfybxsq!initMx.action";
				window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
				onRefresh();
			});
			//��ʼ�����
			initDataGrid();
						
			//��������
			$("#plScSendBtn").click(function(){
				var wsDm="100002";//���õǼ�������
				var check=$(":checked[name='xhs']");
				var str="";
				var jzDjxh="";
				for(var i=0;i<check.length;i++){
					var before=check[i].value; 
					var ary=before.split("#"); 
					str=ary[1];
					jzDjxh=ary[2];
					for(var j=i;j<check.length;j++){
						var after=check[j].value;
						var ary1=after.split("#");
						//alert(ary[1]+"=="+ary1[1])
						if(ary1[1]!=ary[1]){
							showAlert('ֻ����ͬ"������Ŀ����"�ļ�¼����һ����������������');
							return;
						}
						if(ary1[2]!=ary[2]){
							showAlert('ֻ����ͬ"���˵�λ"�ļ�¼����һ����������������');
							return;
						}
					}
				}
				//alert(str+"****"+jzDjxh)
				plScSend(wsDm,str,jzDjxh);
				
			});
			

	});

    function onUpdate(cwDjxh){
    	var url = jcontextPath+"/cwgl/cwfybxsq!initMx.action?domain.cwDjxh="+cwDjxh+"&num="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		onRefresh();
    }
    
    var keyValue = "";
    var fyjzDwDjxh="";
	function onDelete( cwDjxh,jzdw){
		keyValue = cwDjxh;
		fyjzDwDjxh=jzdw;
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.cwDjxh":keyValue,"domain.fyjzDwDjxh":fyjzDwDjxh};
		 var url = jcontextPath+"/cwfybxsq!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}	
    
	function onViewCk(value){
		var url=jcontextPath+"/cwfybxsq!onView.action?domain.cwDjxh="+value+"&num="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:750px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		onRefresh();
	}
	
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		 var sqrq=$("#mainForm_domain_rqQ").val();
		 var sqrz=$("#mainForm_domain_rqZ").val();
		//����������
		var url = jcontextPath+"/cwfybxsq!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.sqrq":sqrq,"domain.rqz":sqrz}								//����Ĳ�����json��ʽ
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
		    colNames:['����','<input title="ѡ��/ȡ��ѡ��" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />', '����Ǽ����(SEQ_CW_DJXH)','���','��������','������Ŀ����','���ñ����ܼ�'
		    		,'���ü��˵�λ','����֧����λ','������','��������',"���ñ�����ϸ"
				    ,'���˵�λ����','�������','����״̬����','�����������'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'65', align:'center'},
			  {name:'checkboxoperationcol', index:'checkboxoperationcol', width:'30', align:'center'
			},
		      {name:'cwDjxh', index:'cwDjxh', width:'0', align:'center',hidden:true}, 
		      {name:'spztMc', index:'spztMc', width:'70', align:'center'}, 
		      {name:'bxdh', index:'bxdh', width:'90', align:'center'}, 
		      {name:'spxmFl', index:'spxmFl', width:'90', align:'center'}, 
		      {name:'fybxje', index:'fybxje', width:'80', align:'center'}, 
		      {name:'jzdw', index:'jzdw', width:'100', align:'center'}, 
		      {name:'jfdw', index:'jfdw', width:'100', align:'center'}, 
		      {name:'sqr', index:'sqr', width:'80', align:'center'}, 
		      {name:'sqrq', index:'sqrq', width:'80', align:'center'}, 
		      {name:'mxBz', index:'mxBz', width:'450', align:'center'}, 
		     
		     

		      
		      {name:'fyjzDwDjxh', index:'fyjzDwDjxh', width:'0', align:'center',hidden:true},
		      {name:'wsSpxh', index:'wsSpxh', width:'0', align:'center',hidden:true},
		      {name:'wsspztDm', index:'wsspztDm', width:'0', align:'center',hidden:true},
		      {name:'xmflDjxh', index:'xmflDjxh', width:'0', align:'center',hidden:true},
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'cwDjxh',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/cwfybxsq!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var xtcsSfsp = $("#mainForm_domain_xtcsSfsp").val();
                var val = jQuery("#dataList").jqGrid('getCell', cl,"cwDjxh"); 	
                var xmflDjxh = jQuery("#dataList").jqGrid('getCell', cl,"xmflDjxh");
                var jzDw = jQuery("#dataList").jqGrid('getCell', cl,"fyjzDwDjxh"); 
                var input ="";
                if ("Y" == xtcsSfsp) {
                	var wsspztDm = jQuery("#dataList").jqGrid('getCell', cl,"wsspztDm");
                	if("0"==wsspztDm || "2"==wsspztDm){
                    	input="<input type=\"checkbox\" name=\"xhs\" value=\""+val+"#"+xmflDjxh+"#"+jzDw+"\" />";
                    }else{
                    	input="<input type=\"checkbox\" name=\"xhs\" disabled=\"disabled\" value=\""+val+"#"+xmflDjxh+"#"+jzDw+"\" />";
                    }
                	$("#dataList").jqGrid('setRowData', cl, { 'checkboxoperationcol': input });
                }
                
             //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">�޸�</font></a>"
                + " <a href=\"javascript:onDelete('"+val+"','"+jzDw+"')\"><font color=\"blue\">ɾ��</font></a>"
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="cwfylb!query" namespace="" method="post" id="mainForm" name="mainForm">
<s:hidden name="domain.xtcsSfsp" />
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">������</li>
		    <li><a href="#" id="queryBtn" class="licon04">�� ��</a></li>
		    <li><a href="#" id="addBtn" class="licon01">��������</a></li>
		      <s:if test='domain.xtcsSfsp == "Y"'>
		    <li><a href="#" id="plScSendBtn" class="licon10">������������</a></li>
	    </s:if>
		    <li><a href="#" id="closeBtn" class="licon03">�ر�</a></li>
	  	</ul>
	
	  <ul class="rcont">
		    <li class="ricon02" onclick="slideToggle('syquery')">��ʾ/���ز�ѯ����</li>
		   
	  	</ul>
	</div> 

<div class="right_cont">
		 <div id="divQuery">
	<fieldset>
		<legend>��ѯ����</legend>
		   <table width="95%" border="0" cellspacing="0" cellpadding="0">
	        <tr>
			<td width="12%" align="right">�������ڣ�</td>
			<td width="30%">
				
			
				
				 <sys:dateFirstDMonth myName="domain.rqQ" myId="mainForm_domain_rqQ" myClass="ymdate" />
	          			      ��
	          		      <sys:dateCurrentDayTag myName="domain.rqZ" myId="mainForm_domain_rqZ" myClass="ymdate" />
				
			</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
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
