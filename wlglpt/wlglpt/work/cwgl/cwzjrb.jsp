<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<html>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>

<head>
<title>����-�ʽ��ձ�</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript">
	<% 
		UserDomain userDomain1=(UserDomain) request.getSession().getAttribute(WebConstants.SES_USER_INFO);
		String gsbm = userDomain1.getGsbm();
	%>
      var bz="";
      $(document).ready(function(){
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				bz="";
				initMx();
				onRefresh();
			});
			$("#cxTjBtn").click(function(){
				cxTjMx();
			});
			$("#addBtn").click(function(){
				if(<%=gsbm%>!=$("#mainForm_domain_ssJgbm").val()){
					showAlert("ֻ�����ɱ���˾���ձ���");
					return;
				}
				if(!checkdata()){
					return;
				}
				doSave();
			})
			$("#qtsrBtn").click(function(){
				onUpdateQtsr();
			});
			$("#mainForm_domain_ssJgbm").change(function(){
				if(<%=gsbm%>!=$("#mainForm_domain_ssJgbm").val()){
					$("#qtsrBtn").hide();
				}else{
					$("#qtsrBtn").show();
				}
			})
			$(".zr").change(function(){
				var zrKcXj = trim($("#mainForm_domain_zrKcXj").val());
				var zrKcYh = trim($("#mainForm_domain_zrKcYh").val());
				
				var srXj = $("#mainForm_domain_srXj").val()/1;
				var srYh = $("#mainForm_domain_srYh").val()/1;
				var zcXj = $("#mainForm_domain_zcXj").val()/1;
				var zcYh = $("#mainForm_domain_zcYh").val()/1;
				if(isNaN(zrKcXj)){
					showAlert("�����������֣�");
					$("#mainForm_domain_zrKcXj").select();
					return;
				}
				if(isNaN(zrKcYh)){
					showAlert("�����������֣�");
					$("#mainForm_domain_zrKcYh").select();
					return;
				}
				$("#mainForm_domain_kcXj").val(zrKcXj/1+srXj-zcXj);
				$("#mainForm_domain_kcYh").val(zrKcYh/1+srYh-zcYh);
			})
			$(".srz").change(function(){
				showColor();
			})		
			//��ʼ�����
			initDataGrid();
			var objs = $(".td1");
			$.each(objs,function(i,obj){
				obj.bgColor='yellow';
			})
			initMx();
			onRefresh();
			
			initZrKc();
			if(<%=gsbm%>!=$("#mainForm_domain_ssJgbm").val()){
				$("#qtsrBtn").hide();
			}else{
				$("#qtsrBtn").show();
			}
	});
	
	function onUpdateQtsr(){
    	var ssJgbm = $("#mainForm_domain_ssJgbm").val();
    	bz="";
    	//alert(ssJgbm);
		var rq = $("#mainForm_domain_rq").val();
    	var url = jcontextPath+"/hygl/cwzjrb!qtsrMx.action?domain.qtsrDomain.ssJgbm="+ssJgbm+"&domain.qtsrDomain.rq="+rq+"&number="+Math.random();
    	window.showModalDialog(url,window,"dialogHeight:600px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		initMx();
		onRefresh();
    }
    function onSzMx(val){
    	bz=val;
    	var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var rq = $("#mainForm_domain_rq").val();
    	var url = jcontextPath+"/hygl/cwzjrb!initSzMx.action?domain.ssJgbm="+ssJgbm+"&domain.rq="+rq+"&domain.bz="+bz+"&number="+Math.random();
    	window.showModalDialog(url,window,"dialogHeight:800px;dialogWidth:960px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
    }
    function onXxSzMx(val){
    	//alert(val);
    	bz=val;
    	var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var rq = $("#mainForm_domain_rq").val();
    	var url = jcontextPath+"/hygl/cwzjrb!initXxSzMx.action?domain.ssJgbm="+ssJgbm+"&domain.rq="+rq+"&domain.bz="+bz+"&number="+Math.random();
    	window.showModalDialog(url,window,"dialogHeight:800px;dialogWidth:960px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
    }
	function initZrKc() {
		var zrKcXj = $("#mainForm_domain_zrKcXj").val();
		var zrKcYh = $("#mainForm_domain_zrKcYh").val();
		if(zrKcXj==""&&zrKcYh==""){
			$(".zr").removeClass("bgstyle_readonly");
			$(".zr").addClass("bgstyle_optional");
			$(".zr").attr("readonly",false);
		}
	}
	function doSave(){
		var cwDjxh = $("#mainForm_domain_cwDjxh").val();
		//alert(cwDjxh);
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var rq = $("#mainForm_domain_rq").val();
		var zrKcXj = $("#mainForm_domain_zrKcXj").val();
		var zrKcYh = $("#mainForm_domain_zrKcYh").val();
		
		var  srXj= $("#mainForm_domain_srXj").val();
		var  srYh= $("#mainForm_domain_srYh").val();
		var  zcXj= $("#mainForm_domain_zcXj").val();
		var  zcYh= $("#mainForm_domain_zcYh").val();
		var  kcXj= $("#mainForm_domain_kcXj").val();
		var  kcYh= $("#mainForm_domain_kcYh").val();
		
		var  srXjSrz= trim($("#mainForm_domain_srXjSrz").val());
		var  srYhSrz= trim($("#mainForm_domain_srYhSrz").val());
		var  zcXjSrz= trim($("#mainForm_domain_zcXjSrz").val());
		var  zcYhSrz= trim($("#mainForm_domain_zcYhSrz").val());
		var  kcXjSrz= trim($("#mainForm_domain_kcXjSrz").val());
		var  kcYhSrz= trim($("#mainForm_domain_kcYhSrz").val());

		
		var xfSrXj = $("#xfSrXj").text();
		var dfSrXj = $("#dfSrXj").text();
		var yjSrXj = $("#yjSrXj").text();
		var ysSrXj = $("#ysSrXj").text();
		var qtSrXj = $("#qtSrXj").text();
		var dshkSrXj= $("#dshkSrXj").text();
		
		var xfSrYh = $("#xfSrYh").text();
		var dfSrYh = $("#dfSrYh").text();
		var yjSrYh = $("#yjSrYh").text();
		var ysSrYh = $("#ysSrYh").text();
		var qtSrYh = $("#qtSrYh").text();
		var dshkSrYh = $("#dshkSrYh").text();
		var url = jcontextPath+"/cwgl/cwzjrb!save";  
        var jsonObj = {"domain.ssJgbm":ssJgbm,"domain.rq":rq,"domain.zrKcXj":zrKcXj,"domain.zrKcYh":zrKcYh,
        			   "domain.srXj":srXj,"domain.srYh":srYh,"domain.zcXj":zcXj,"domain.zcYh":zcYh,"domain.kcXj":kcXj,"domain.kcYh":kcYh,
        			   "domain.srXjSrz":srXjSrz,"domain.srYhSrz":srYhSrz,"domain.zcXjSrz":zcXjSrz,"domain.zcYhSrz":zcYhSrz,"domain.kcXjSrz":kcXjSrz,"domain.kcYhSrz":kcYhSrz,
        			   "domain.xfSrXj":xfSrXj,"domain.dfSrXj":dfSrXj,"domain.yjSrXj":yjSrXj,"domain.ysSrXj":ysSrXj,"domain.qtSrXj":qtSrXj,"domain.dshkSrXj":dshkSrXj,
        			   "domain.xfSrYh":xfSrYh,"domain.dfSrYh":dfSrYh,"domain.yjSrYh":yjSrYh,"domain.ysSrYh":ysSrYh,"domain.qtSrYh":qtSrYh,"domain.dshkSrYh":dshkSrYh};
        ajaxCommon(url,jsonObj,"saveSuc");
	}
	function saveSuc(obj){
		//alert(obj.domain.cwDjxh);
		$("#mainForm_domain_cwDjxh").val(obj.domain.cwDjxh);
		showAlert("�ձ�����ɹ���");
	}
	function cxTjMx(){
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var rq = $("#mainForm_domain_rq").val();
		var jsonObj = {"domain.ssJgbm":ssJgbm,"domain.rq":rq};
		var url = jcontextPath+"/hygl/cwzjrb!cxTjMx";
		ajaxCommon(url,jsonObj,"cxTjSuc",true,false);
	}
	function initMx() {
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var rq = $("#mainForm_domain_rq").val();
		var jsonObj = {"domain.ssJgbm":ssJgbm,"domain.rq":rq};
		var url = jcontextPath+"/hygl/cwzjrb!initMx";
		ajaxCommon(url,jsonObj,"initSuc",true,false);
	}
	function initSuc(obj){
		//alert(obj.domain.srXj);
		$("#mainForm_domain_cwDjxh").val(obj.domain.cwDjxh);
		$("#mainForm_domain_zrKcXj").val(obj.domain.zrKcXj);
		$("#mainForm_domain_zrKcYh").val(obj.domain.zrKcYh);
		
		$("#mainForm_domain_srXj").val(obj.domain.srXj);
		$("#mainForm_domain_srYh").val(obj.domain.srYh);
		$("#mainForm_domain_zcXj").val(obj.domain.zcXj);
		$("#mainForm_domain_zcYh").val(obj.domain.zcYh);
		$("#mainForm_domain_kcXj").val(obj.domain.kcXj);
		$("#mainForm_domain_kcYh").val(obj.domain.kcYh);
		
		$("#mainForm_domain_srXjSrz").val(obj.domain.srXjSrz);
		$("#mainForm_domain_srYhSrz").val(obj.domain.srYhSrz);
		$("#mainForm_domain_zcXjSrz").val(obj.domain.zcXjSrz);
		$("#mainForm_domain_zcYhSrz").val(obj.domain.zcYhSrz);
		$("#mainForm_domain_kcXjSrz").val(obj.domain.kcXjSrz);
		$("#mainForm_domain_kcYhSrz").val(obj.domain.kcYhSrz);
		
		$("#xfSrXjTd").html(obj.domain.xfSrXj==null?"":"<a id=\"xfSrXj\" href=\"javascript:onXxSzMx('xfSrXj')\"  style=\"color: blue;\">"+obj.domain.xfSrXj+"</a>");
		$("#dfSrXjTd").html(obj.domain.dfSrXj==null?"":"<a id=\"dfSrXj\" href=\"javascript:onXxSzMx('dfSrXj')\"  style=\"color: blue;\">"+obj.domain.dfSrXj+"</a>");
		$("#yjSrXjTd").html(obj.domain.yjSrXj==null?"":"<a id=\"yjSrXj\" href=\"javascript:onXxSzMx('yfSrXj')\"  style=\"color: blue;\">"+obj.domain.yjSrXj+"</a>");
		$("#ysSrXjTd").html(obj.domain.ysSrXj==null?"":"<a id=\"ysSrXj\" href=\"javascript:onXxSzMx('ysSrXj')\"  style=\"color: blue;\">"+obj.domain.ysSrXj+"</a>");
		$("#qtSrXjTd").html(obj.domain.qtSrXj==null?"":"<a id=\"qtSrXj\" href=\"javascript:onXxSzMx('qtSrXj')\"  style=\"color: blue;\">"+obj.domain.qtSrXj+"</a>");
		$("#dshkSrXjTd").html(obj.domain.dshkSrXj==null?"":"<a id=\"dshkSrXj\" href=\"javascript:onXxSzMx('dshkSrXj')\"  style=\"color: blue;\">"+obj.domain.dshkSrXj+"</a>");
		$("#zzcXjTd").html(obj.domain.zcXj==null?"":"<a id=\"zcXj\" href=\"javascript:onXxSzMx('zzcXj')\"  style=\"color: blue;\">"+obj.domain.zcXj+"</a>");
		
		$("#xfSrYhTd").html(obj.domain.xfSrYh==null?"":"<a id=\"xfSrYh\" href=\"javascript:onXxSzMx('xfSrYh')\"  style=\"color: blue;\">"+obj.domain.xfSrYh+"</a>");
		$("#dfSrYhTd").html(obj.domain.dfSrYh==null?"":"<a id=\"dfSrYh\" href=\"javascript:onXxSzMx('dfSrYh')\"  style=\"color: blue;\">"+obj.domain.dfSrYh+"</a>");
		$("#yjSrYhTd").html(obj.domain.yjSrYh==null?"":"<a id=\"yjSrYh\" href=\"javascript:onXxSzMx('yjSrYh')\"  style=\"color: blue;\">"+obj.domain.yjSrYh+"</a>");
		$("#ysSrYhTd").html(obj.domain.ysSrYh==null?"":"<a id=\"ysSrYh\" href=\"javascript:onXxSzMx('ysSrYh')\"  style=\"color: blue;\">"+obj.domain.ysSrYh+"</a>");
		$("#qtSrYhTd").html(obj.domain.qtSrYh==null?"":"<a id=\"qtSrYh\" href=\"javascript:onXxSzMx('qtSrYh')\"  style=\"color: blue;\">"+obj.domain.qtSrYh+"</a>");
		$("#dshkSrYhTd").html(obj.domain.dshkSrYh==null?"":"<a id=\"dshkSrYh\" href=\"javascript:onXxSzMx('dshkSrYh')\"  style=\"color: blue;\">"+obj.domain.dshkSrYh+"</a>");
		$("#zzcYhTd").html(obj.domain.zcYh==null?"":"<a id=\"zcYh\" href=\"javascript:onXxSzMx('zzcYh')\"  style=\"color: blue;\">"+obj.domain.zcYh+"</a>");
		
		
		showColor();
	}
	function cxTjSuc(obj){
		
		var zrKcXj = trim($("#mainForm_domain_zrKcXj").val())/1;
		var zrKcYh = trim($("#mainForm_domain_zrKcYh").val())/1;
		
		$("#mainForm_domain_srXj").val(obj.domain.srXj);
		$("#mainForm_domain_srYh").val(obj.domain.srYh);
		$("#mainForm_domain_zcXj").val(obj.domain.zcXj);
		$("#mainForm_domain_zcYh").val(obj.domain.zcYh);
		$("#mainForm_domain_kcXj").val((zrKcXj+obj.domain.srXj-obj.domain.zcXj).toFixed(2));
		$("#mainForm_domain_kcYh").val((zrKcYh+obj.domain.srYh-obj.domain.zcYh).toFixed(2));
		
		
		$("#xfSrXj").text(obj.domain.xfSrXj==null?"":obj.domain.xfSrXj);
		$("#dfSrXj").text(obj.domain.dfSrXj==null?"":obj.domain.dfSrXj);
		$("#yjSrXj").text(obj.domain.yjSrXj==null?"":obj.domain.yjSrXj);
		$("#ysSrXj").text(obj.domain.ysSrXj==null?"":obj.domain.ysSrXj);
		$("#qtSrXj").text(obj.domain.qtSrXj==null?"":obj.domain.qtSrXj);
		$("#dshkSrXj").text(obj.domain.dshkSrXj==null?"":obj.domain.dshkSrXj);
		$("#zzcXj").text(obj.domain.zcXj==null?"":obj.domain.zcXj);
		
		$("#xfSrYh").text(obj.domain.xfSrYh==null?"":obj.domain.xfSrYh);
		$("#dfSrYh").text(obj.domain.dfSrYh==null?"":obj.domain.dfSrYh);
		$("#yjSrYh").text(obj.domain.yjSrYh==null?"":obj.domain.yjSrYh);
		$("#ysSrYh").text(obj.domain.ysSrYh==null?"":obj.domain.ysSrYh);
		$("#qtSrYh").text(obj.domain.qtSrYh==null?"":obj.domain.qtSrYh);
		$("#dshkSrYh").text(obj.domain.dshkSrYh==null?"":obj.domain.dshkSrYh);
		$("#zzcYh").text(obj.domain.zcYh==null?"":obj.domain.zcYh);
		
		
		showColor();
	}
	function showColor() {
		var xtzs = $(".xtz")
				var objs = $(".srz");
				$.each(objs,function(i,obj){
					if(xtzs[i].value!=obj.value&&obj.value!=""){
						xtzs[i].style.backgroundColor="#ff0000";
					}else{
						xtzs[i].style.backgroundColor="#efefef";
					}
				})
	}
	function checkdata(){
		var controlNameArray = ["domain.srXjSrz","domain.srYhSrz","domain.zcXjSrz","domain.zcYhSrz","domain.kcXjSrz","domain.kcYhSrz"];
		var labelNameArray = ["�����ֽ�","��������","֧���ֽ�","֧������","����ֽ�","�������"];
		var compareValueArray = [14.2,14.2,14.2,14.2,14.2,14.2];
		var nodeTypeArray = [NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL,NodeType.DECIMAL];
		var notNullArray = [false,false,false,false,false,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
  /**************************************��ҳ��ʼ****************************************/
  //ˢ�±��
	function onRefresh(){
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var rq = $("#mainForm_domain_rq").val();
  		//����������
		var url = jcontextPath+"/cwzjrb!query.action";   
		 $("#dataList").jqGrid("setGridParam",{ 
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.rq":rq,"domain.bz":bz}								//����Ĳ�����json��ʽ
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
		    colNames:['����Ǽ����','���','��־','��֧�Ǽ�����','���','������','��������','����','�˺�','˵��','����'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'cwbdDjXh', index:'cwbdDjXh',hidden:true, align:'center'},
		      {name:'zcflMc', index:'zcflMc', width:'60', align:'center'},
		      {name:'bzmc', index:'bzmc', width:'60', align:'center'}, 	
		      {name:'szDjrq', index:'szDjrq', width:'100', align:'center'},
		      {name:'bdje', index:'bdje', width:'80', align:'center'},
		      {name:'jbrCzyMc', index:'jbrCzyMc', width:'80', align:'center'},  
		      {name:'yhmc', index:'yhmc', width:'100', align:'center'}, 	      
		      {name:'hm', index:'hm', width:'80', align:'center'},			  
			  {name:'zh', index:'zh', width:'80', align:'center'}, 	  
			  {name:'sm', index:'sm', width:'306', align:'left'},
			  {name:'rq', index:'rq', width:'70', align:'center'},
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
		  
	  	  /*// add custom button to export the data to excel
		  $("#dataList").jqGrid('navButtonAdd','#pager',{
		       caption:"", 
		       onClickButton : function () { 
		       	   $("#mainForm").attr("action",jcontextPath+"/cwhbzclsjl!download");
				   $("#mainForm").submit();
		       } 
		 });*/
		 
		    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����������
			function myGridComplete() {
		            var graduateIds = $("#dataList").jqGrid('getDataIDs');
		            for (var i = 0; i < graduateIds.length; i++) {
		                var cl = graduateIds[i];
		                var val = jQuery("#dataList").jqGrid('getCell', cl,"cwbdDjXh"); 	  //��ȡ��ǰ��Ԫ������Ĳ������ 
		                var link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">��ϸ</font></a>";
		                //$("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
		            }
		     }
	}
</script>
</head>
<body>
<s:form action="cwhbzclsjl!query" theme="simple" namespace="cwgl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.cwDjxh"></s:hidden>
	<div class="right_btnbg">
	    <ul class="lcont">
		    <li class="no">������</li>
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
				<table width="96%" border="0" cellspacing="0" cellpadding="0">
					
					<tr>
		  				<td width="12%" class="td_noborder"></td>
		  				<td width="10%" class="td_noborder"></td>
		  				<td width="10%" class="td_noborder"></td>
		  				<td width="10%" class="td_noborder"></td>
		  				<td width="10%" class="td_noborder"></td>
		  				
		  				<td width="4%" class="td_noborder"></td>
		  				<td width="10%" class="td_noborder"></td>
		  				<td width="10%" class="td_noborder"></td>
		  				<td width="10%" class="td_noborder"></td>
		  				<td width="10%" class="td_noborder"></td>
		  			</tr>
					<tr>
						<td  align="right">��λ��</td> 
						<td colspan="2">
							<input type="hidden" id="userGsbm" value="${user }"/>
						  	<sys:qxGsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y" myClass="select" ></sys:qxGsList>
		  			    </td>
		  			    <td  align="right">���ڣ�</td>
		  			    <td><sys:dateCurrentDayTag myName="domain.rq" myId="mainForm_domain_rq" myClass="ymdate" /></td>
		  			    <td></td>
		  			    <td align="left"><button type="button" class="pop_btnbg noTabSelect" id="queryBtn">�鿴�ձ�</button></td>
  						<td align="left"><button type="button" class="pop_btnbg noTabSelect" id="addBtn">�����ձ�</button></td> 
  						<td colspan="3"></td>     
		            </tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>ͳ����Ϣ</legend>
				<table width="96%" border="0" cellspacing="0" cellpadding="0">
					<tr>
		  				<td width="12%" class="td_noborder"></td>
		  				<td width="10%" class="td_noborder"></td>
		  				<td width="10%" class="td_noborder"></td>
		  				<td width="10%" class="td_noborder"></td>
		  				<td width="10%" class="td_noborder"></td>
		  				
		  				<td width="4%" class="td_noborder"></td>
		  				<td width="10%" class="td_noborder"></td>
		  				<td width="10%" class="td_noborder"></td>
		  				<td width="10%" class="td_noborder"></td>
		  				<td width="10%" class="td_noborder"></td>
		  			</tr>
		  			<tr>
		            	<td align="right">�����ʽ������</td>
		            	<td></td>
		            	<td  align="right">�ֽ�</td> 
						<td><s:textfield name="domain.zrKcXj" cssClass="pop_input noborder inputext bgstyle_readonly zr" ></s:textfield></td>
						<td></td>
						<td  align="right">���У�</td> 
						<td><s:textfield name="domain.zrKcYh" cssClass="pop_input noborder inputext bgstyle_readonly zr" ></s:textfield></td>
						<td colspan="2"></td>
						<td><button type="button" class="pop_btnbg noTabSelect" id="cxTjBtn">����ͳ����֧</button></td>
		            </tr>
		            <tr>
		            	<td></td>
		            	<td align="right">����ֵ��</td>
		            	<td colspan="4"></td>
		            	<td align="right">�ֹ�����ֵ��</td>
						<td colspan="3"></td>
		            </tr>
		            <tr>
		            	<td align="right"><a href="javascript:onSzMx(1)"><font color="blue">����</font></a>&nbsp;&nbsp;&nbsp; -- ></td>
		            	<td  align="right">�ֽ�</td> 
						<td><s:textfield name="domain.srXj" cssClass="pop_input noborder inputext bgstyle_readonly xtz"></s:textfield></td>
						<td  align="right">���У�</td> 
						<td><s:textfield name="domain.srYh" cssClass="pop_input noborder inputext bgstyle_readonly xtz" ></s:textfield></td>
						<td></td>
						<td  align="right">�ֽ�</td> 
						<td><s:textfield name="domain.srXjSrz" cssClass="pop_input noborder inputext bgstyle_optional srz" ></s:textfield></td>
						<td  align="right">���У�</td> 
						<td><s:textfield name="domain.srYhSrz" cssClass="pop_input noborder inputext bgstyle_optional srz" ></s:textfield></td>
		            </tr>
		            <tr>
		            	<td align="right"><a href="javascript:onSzMx(2)"><font color="blue">֧��</font></a>&nbsp;&nbsp;&nbsp; -- ></td>
		            	<td  align="right">�ֽ�</td> 
						<td><s:textfield name="domain.zcXj" cssClass="pop_input noborder inputext bgstyle_readonly xtz" ></s:textfield></td>
						<td  align="right">���У�</td> 
						<td><s:textfield name="domain.zcYh" cssClass="pop_input noborder inputext bgstyle_readonly xtz" ></s:textfield></td>
						<td></td>
						<td  align="right">�ֽ�</td> 
						<td><s:textfield name="domain.zcXjSrz" cssClass="pop_input noborder inputext bgstyle_optional srz" ></s:textfield></td>
						<td  align="right">���У�</td> 
						<td><s:textfield name="domain.zcYhSrz" cssClass="pop_input noborder inputext bgstyle_optional srz" ></s:textfield></td>
		            </tr>
		            <tr>
		            	<td align="right">���&nbsp;&nbsp;&nbsp; -- ></td>
		            	<td  align="right">�ֽ�</td> 
						<td><s:textfield name="domain.kcXj" cssClass="pop_input noborder inputext bgstyle_readonly xtz" ></s:textfield></td>
						<td  align="right">���У�</td> 
						<td><s:textfield name="domain.kcYh" cssClass="pop_input noborder inputext bgstyle_readonly xtz" ></s:textfield></td>
						<td></td>
						<td  align="right">�ֽ�</td> 
						<td><s:textfield name="domain.kcXjSrz" cssClass="pop_input noborder inputext bgstyle_optional srz" ></s:textfield></td>
						<td  align="right">���У�</td> 
						<td><s:textfield name="domain.kcYhSrz" cssClass="pop_input noborder inputext bgstyle_optional srz" ></s:textfield></td>
		            </tr>
				</table>
			</fieldset>
			<fieldset>
				<legend>��ϸ��Ϣ</legend>
				<table width="96%" border="0" cellspacing="0" cellpadding="0">
					<tr>
		  				<td width="12%" class="td_noborder"></td>
		  				<td width="10%" class="td_noborder"></td>
		  				<td width="10%" class="td_noborder"></td>
		  				<td width="10%" class="td_noborder"></td>
		  				<td width="10%" class="td_noborder"></td>
		  				
		  				<td width="4%" class="td_noborder"></td>
		  				<td width="10%" class="td_noborder"></td>
		  				<td width="10%" class="td_noborder"></td>
		  				<td width="10%" class="td_noborder"></td>
		  				<td width="10%" class="td_noborder"></td>
		  			</tr>
		  			<tr>
		            	<td></td>
		            	<td align="center" class="td1">��������</td>
		            	<td  align="center" class="td1">�ֽ�</td> 
						<td  align="center" class="td1">����</td>
						<td colspan="2"></td>
						<td align="center" class="td1">����֧��</td>
		            	<td  align="center" class="td1">�ֽ�</td> 
						<td  align="center" class="td1">����</td>
						<td ></td>
		            </tr>
		            <tr>
		            	<td></td>
		            	<td align="center">�ָ�����</td>
		            	<td  align="center" id="xfSrXjTd"></td> 
						<td  align="center" id="xfSrYhTd"></td>
						<td></td>
						<td></td>
						<td align="center">��֧��</td>
		            	<td  align="center" id="zzcXjTd"></td> 
						<td  align="center" id="zzcYhTd"></td>
						<td></td>
		            </tr>
		            <tr>
		            	<td></td>
		            	<td align="center">�½��ջ�</td>
		            	<td  align="center" id="yjSrXjTd"></td> 
						<td  align="center" id="yjSrYhTd"></td>
						<td></td>
						<td></td>
						<td align="center" class="td1">��������</td>
		            	<td  align="center" class="td1">�ֽ�</td> 
						<td  align="center" class="td1">����</td>
						<td></td>
		            </tr>
		             <tr>
		            	<td></td>
		            	<td align="center">�����ջ�</td>
		            	<td  align="center" id="dfSrXjTd"></td> 
						<td  align="center" id="dfSrYhTd"></td>
						<td></td>
						<td></td>
						<td align="center">Ԥ������</td>
		            	<td  align="center" id="ysSrXjTd"></td> 
						<td  align="center"id="ysSrYhTd"></td>
						<td></td>
		            </tr>
		             <tr>
		            	<td></td>
		            	<td align="center">���ջ����ջ�</td>
		            	<td  align="center" id="dshkSrXjTd"></td> 
						<td  align="center" id="dshkSrYhTd"></td>
						<td></td>
						<td></td>
						<td align="center">��������</td>
		            	<td  align="center" id="qtSrXjTd"></td> 
						<td  align="center" id="qtSrYhTd"></td>
						<td align="center"><button type="button" class="pop_btnbg noTabSelect" id="qtsrBtn">���¼��</button></td>
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
