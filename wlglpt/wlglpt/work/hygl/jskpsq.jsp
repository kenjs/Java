<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>��Ʊ����</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
			document.getElementById("maincont").onmousewheel=hideHelpWindow;
			
			initHykhData(300,$("#mainForm_domain_ssJgbm").val(), $("#mainForm_domain_djJgbm").val(),"jsonData","khMc","khDjxh");
			
			$("#mainForm_domain_djJgbm").change(function(){
				initHykhData(300, $("#mainForm_domain_ssJgbm").val(), $(this).val(),"jsonData","khMc","khDjxh");
			});
			
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//��������
			$("#plScSendBtn").click(function(){
				var wsDm="305003";//��Ʊ������˱�
				plScSend(wsDm,"");
			});
			
			$("#srkpBtn").click(function(){
				var dwDm = trim($("#mainForm_domain_ssJgbm").val()); 
				var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
				var kpsqDjxh="";
				var kpsqfsDm='3';
				if(undefined==dwDm || null==dwDm || ""==dwDm){
					showAlert("����ѡ��ҵ��λ��");
					return;
				}
				
				if(undefined==khDjxh){
					khDjxh ="";
				}
				var dwMcStr=$("select[name='domain.ssJgbm']").find("option:selected").text();
				var dwMc=dwMcStr.split(" ")[1];
				var khMc = trim($("#mainForm_domain_khMc").val()); 
				var h=650;
		    	var w=860;
		    	//popwindow(jcontextPath+"/hygl/jskpsq!initMx?domain.kpsqDjxh="+kpsqDjxh);
		    	var url = jcontextPath+"/hygl/jskpsq!initMx.action?domain.kpsqDjxh="+kpsqDjxh+"&domain.kpsqfsDm="+kpsqfsDm;
		    	url+="&domain.ssJgbm="+dwDm+"&domain.dwMc="+dwMc+"&domain.khDjxh="+khDjxh+"&domain.khMc="+khMc+"&num="+Math.random();
		    	url = encodeURI(encodeURI(url));
		    	var parm="dialogHeight:"+h+"px;dialogWidth:"+w+"px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;"
		    	var val=window.showModalDialog(url,window,parm);
		    	if(val!=''&&val!=undefined){
		    		var url=jcontextPath+"/hygl/jskpsq!deleteSqKpTemp"
			    	var jsonObj={"domain.flag":val};
		    		ajaxCommon(url,jsonObj,"afterDelete");
		    	}
		    	onRefresh();
		    	//window.open(url);
		    	
			})
			
			//���˿�Ʊ��ť�¼�
			$("#dzkpBtn").click(function(){
				//var url=jcontextPath+"/hygl/jskpsq!initMx?domain.kpsqfsDm=1";
				//popwindow(url);
				var dwDm = trim($("#mainForm_domain_ssJgbm").val()); 
				var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
				
		  		if(undefined==dwDm || null==dwDm || ""==dwDm){
					showAlert("����ѡ��ҵ��λ��");
					return;
				}
				
				//if(undefined==khDjxh || null==khDjxh || ""==khDjxh){
				//	showAlert("����ѡ��ͻ����ƣ�");
				//	return;
				//}
				//var dwMcStr = $("select[name='domain.ssJgbm'] option[selected]").text(); 
				var dwMcStr = $("select[name='domain.ssJgbm']").find("option:selected").text(); 
				var dwMc=dwMcStr.split(" ")[1];
				var khMc = trim($("#mainForm_domain_khMc").val()); 
				onUpdate("","1",600,760,dwDm,dwMc,khDjxh,khMc);
			});
			//Ԥ��Ʊ
			$("#ykpBtn").click(function(){
				var dwDm = trim($("#mainForm_domain_ssJgbm").val()); 
				var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
				
		  		if(undefined==dwDm || null==dwDm || ""==dwDm){
					showAlert("����ѡ��ҵ��λ��");
					return;
				}
				
				//if(undefined==khDjxh || null==khDjxh || ""==khDjxh){
				//	showAlert("����ѡ��ͻ����ƣ�");
				//	return;
				//}
				//var dwMcStr = $("select[name='domain.ssJgbm'] option[selected]").text(); 
				var dwMcStr = $("select[name='domain.ssJgbm']").find("option:selected").text(); 
				var dwMc=dwMcStr.split(" ")[1];
				var khMc = trim($("#mainForm_domain_khMc").val()); 
				onUpdate("","2",500,620,dwDm,dwMc,'',khMc);
			});
			//��ʼ�����
			initDataGrid();
			initList();					

	});
	
	function afterDelete(){
		onRefresh();
	}
	function initList() {
		var ssJgbm = $("#mainForm_domain_ssJgbm").val(); 
		var djJgbm =$("#mainForm_domain_djJgbm").val();
		var jsonObj = {"domain.paramdm":ssJgbm,
			"domain.defaultValue":djJgbm,
			"domain.currentObjName":"domain.djJgbm",
			"domain.currentObjId":"mainForm_domain_djJgbm",
			"domain.containQbBz":"Y",
			"domain.mcContainDmBz":"Y"};
	
		var url=jcontextPath+"/common/wlglptCommon!bmInit";	
		ajaxCommon(url,jsonObj,"changeBmList");
	}
	function changeBmList(data){
		var list = data.domain.dataList;
		$("#"+data.domain.currentObjId).empty();
		$.each(list, function(i,domain){
		    var option = $("<option>").text(domain.mc).val(domain.dm);
		    //Ĭ��ѡ��
		    if(data.domain.defaultValue==domain.dm){
		    	option = $("<option selected='selected'>").text(domain.mc).val(domain.dm);
		    }
		    
		    $("#"+data.domain.currentObjId).append(option);
		});
	}
    function onUpdate(kpsqDjxh,kpsqfsDm,height,width,ssJgbm,dwMc,khDjxh,khMc){
    	var h=600;
    	var w=760;
    	if(undefined==ssJgbm)
    		ssJgbm="";
    	if(undefined==dwMc)
    		dwMc="";
    	if(undefined==khDjxh)
    		khDjxh="";
    	if(undefined==khMc)
    		khMc="";
    	if(undefined!=height)
    		h=height;
    	if(undefined!=width)
    		w=width;
    	//popwindow(jcontextPath+"/hygl/jskpsq!initMx?domain.kpsqDjxh="+kpsqDjxh);
    	var url = jcontextPath+"/hygl/jskpsq!initMx.action?domain.kpsqDjxh="+kpsqDjxh+"&domain.kpsqfsDm="+kpsqfsDm;
    	url+="&domain.ssJgbm="+ssJgbm+"&domain.dwMc="+dwMc+"&domain.khDjxh="+khDjxh+"&domain.khMc="+khMc;
    	url = encodeURI(encodeURI(url));
    	var parm="dialogHeight:"+h+"px;dialogWidth:"+w+"px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;"
    	window.showModalDialog(url,window,parm)
    	//window.open(url);
    	onRefresh();
    }
    function onUpdateToSrKp(kpsqDjxh,kpsqfsDm,height,width,ssJgbm,dwMc,khDjxh,khMc){
    	var h=600;
    	var w=760;
    	if(undefined==ssJgbm)
    		ssJgbm="";
    	if(undefined==dwMc)
    		dwMc="";
    	if(undefined==khDjxh)
    		khDjxh="";
    	if(undefined==khMc)
    		khMc="";
    	if(undefined!=height)
    		h=height;
    	if(undefined!=width)
    		w=width;
    	//popwindow(jcontextPath+"/hygl/jskpsq!initMx?domain.kpsqDjxh="+kpsqDjxh);
    	var url = jcontextPath+"/hygl/jskpsq!initMx.action?domain.kpsqDjxh="+kpsqDjxh+"&domain.kpsqfsDm="+kpsqfsDm;
    	url+="&domain.ssJgbm="+ssJgbm+"&domain.dwMc="+dwMc+"&domain.khDjxh="+khDjxh+"&domain.khMc="+khMc+"&num"+Math.random();
    	url = encodeURI(encodeURI(url));
    	var parm="dialogHeight:"+h+"px;dialogWidth:"+w+"px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;"
    	var val=window.showModalDialog(url,window,parm);
    	if(val!=''&&val!=undefined){
    		var url=jcontextPath+"/hygl/jskpsq!deleteSqKpTemp"
	    	var jsonObj={"domain.flag":val};
    		ajaxCommon(url,jsonObj,"afterDelete");
    	}
    	onRefresh();
    }
    
     function onView(kpsqDjxh,kpsqfsDm,height,width,ssJgbm,dwMc,khDjxh,khMc){
    	var h=600;
    	var w=760;
    	if(undefined==ssJgbm)
    		ssJgbm="";
    	if(undefined==dwMc)
    		dwMc="";
    	if(undefined==khDjxh)
    		khDjxh="";
    	if(undefined==khMc)
    		khMc="";
    	if(undefined!=height)
    		h=height;
    	if(undefined!=width)
    		w=width;
    	//popwindow(jcontextPath+"/hygl/jskpsq!initMx?domain.kpsqDjxh="+kpsqDjxh);
    	var url = jcontextPath+"/hygl/jskpsq!initMxCk.action?domain.kpsqDjxh="+kpsqDjxh;
    	url = encodeURI(encodeURI(url));
    	var parm="dialogHeight:"+h+"px;dialogWidth:"+w+"px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;"
    	window.showModalDialog(url,window,parm)
    	//window.open(url);
    	onRefresh();
    }
    var keyValue = "";
	function onDelete( kpsqDjxh){
		keyValue = kpsqDjxh;
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.kpsqDjxh":keyValue};
		 var url = jcontextPath+"/hygl/jskpsq!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}	
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var xtcsSfsp = $("#mainForm_domain_xtcsSfsp").val();
		if ("Y" == xtcsSfsp) {
			$("#dataList").jqGrid('showCol',["wsspztMc"]);
			$("#dataList").jqGrid('showCol',["fsspCheck"]);
		}
		
		var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
		var rqQ = trim($("#mainForm_domain_rqQ").val()); 
		var rqZ = trim($("#mainForm_domain_rqZ").val()); 
		var djJgbm = trim($("#mainForm_domain_djJgbm").val()); 
		var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
		
		if(undefined==ssJgbm || null==ssJgbm || ""==ssJgbm){
			showAlert("����ѡ��ҵ��λ��");
			return;
		}
		
		//if(undefined==khDjxh || null==khDjxh || ""==khDjxh){
			//showAlert("����ѡ��ͻ���");
			//return;
		//}
		if(undefined==rqQ || null==rqQ || ""==rqQ){
				showAlert("����ѡ�񴴽�������");
				return;
		}
		if(undefined==rqZ || null==rqZ || ""==rqZ){
				showAlert("����ѡ�񴴽�����ֹ��");
				return;
		}	
  
		//����������
		var url = jcontextPath+"/hygl/jskpsq!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.khDjxh":encodeURI(khDjxh),"domain.rqQ":rqQ,"domain.rqZ":rqZ,"domain.djJgbm":djJgbm,
				      "domain.ssJgbm":ssJgbm}								//����Ĳ�����json��ʽ
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
		    colNames:['<input title="ѡ��/ȡ��ѡ��" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />',
		    		'����', '�����������','����','����״̬����','��Ʊ����Ǽ����','���뷽ʽdm','���뷽ʽ','�ͻ�����','���뿪Ʊ���','���뿪Ʊ����',
				     '�Ǽ���','�Ǽ�����','�Ǽǲ���','������λ',
				     '��Ʊ��λ'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'fsspCheck', index:'fsspCheck', hidden:true, width:'20', align:'center'},
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
			  {name:'wsSpxh', index:'wsSpxh',hidden:true, width:'80', align:'center'},
			  {name:'wsspztMc', index:'wsspztMc', hidden:true,width:'45', align:'center'},
			  {name:'wsspztDm', index:'wsspztDm', hidden:true,width:'80', align:'center'},
		      {name:'kpsqDjxh', index:'kpsqDjxh', width:'80', align:'center',hidden:true}, 
		      {name:'kpsqfsDm', index:'kpsqfsDm', width:'40', align:'center',hidden:true},
		      {name:'kpsqfsMc', index:'kpsqfsMc', width:'70', align:'center'},  
		      {name:'khMc', index:'khMc', width:'100', align:'center',hidden:true}, 
		      {name:'sqKpjeHj', index:'sqKpjeHj', width:'80', align:'center'}, 

		      {name:'sqKprq', index:'sqKprq', width:'80', align:'center'}, 
		      {name:'cjrMc', index:'cjrMc', width:'70', align:'center'}, 
		      {name:'djrq', index:'djrq', width:'60', align:'center'}, 
		      {name:'bmMc', index:'bmMc', width:'100', align:'center'}, 

		      {name:'dwMc', index:'dwMc', width:'80', align:'center'}, 
		      {name:'kpDwJgMc', index:'kpDwJgMc', width:'80', align:'center'}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'KPSQ_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/jskpsq!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            var xtcsSfsp = $("#mainForm_domain_xtcsSfsp").val();
            
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"kpsqDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var kpsqfsDm = jQuery("#dataList").jqGrid('getCell', cl,"kpsqfsDm");
                var link="";
                if("1"==kpsqfsDm){
                	link += "<a href=\"javascript:onUpdate('"+val+"','"+kpsqfsDm+"')\"><font color=\"blue\">�޸�</font></a>"; 
                }
                //Ԥ��Ʊ
                if("2"==kpsqfsDm){
                	link += "<a href=\"javascript:onUpdate('"+val+"','"+kpsqfsDm+"',"+480+","+600+")\"><font color=\"blue\">�޸�</font></a>";
                }
               
                if("3"==kpsqfsDm){
                	link += "<a href=\"javascript:onUpdateToSrKp('"+val+"','"+kpsqfsDm+"',"+650+","+860+")\"><font color=\"blue\">�޸�</font></a>";
                }
                
               link+= " <a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">ɾ��</font></a>";
               /*if("1"==kpsqfsDm){
                	link += " <a href=\"javascript:onView('"+val+"','"+kpsqfsDm+"')\"><font color=\"blue\">�鿴</font></a>"; 
                }
                //Ԥ��Ʊ
                if("2"==kpsqfsDm){
                	link += " <a href=\"javascript:onView('"+val+"','"+kpsqfsDm+"',"+480+","+600+")\"><font color=\"blue\">�鿴</font></a>";
                }*/
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
                
                var wsSpxh = jQuery("#dataList").jqGrid('getCell', cl,"wsSpxh");
                if ("Y" == xtcsSfsp) {
					var wsspztDm = jQuery("#dataList").jqGrid('getCell', cl,"wsspztDm"); 
					var spLink = '<input type="checkbox" name="xhs" value="'+val+'#'+wsSpxh+'" />';
					if ("1" == wsspztDm || "3" == wsspztDm) {
						spLink = '<input type="checkbox" name="xhs" value="'+val+'#'+wsSpxh+'" disabled="disabled" />';
					}
					
					$("#dataList").jqGrid('setRowData', cl, { 'fsspCheck': spLink }); 
	    		}
            }
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="jskpsq!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.xtcsSfsp" />
	<s:hidden name="jsonData" />
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">������</li>
		    <li><a href="#" id="queryBtn" class="licon04">�� ��</a></li>
		    <li><a href="#" id="dzkpBtn" class="licon11">���˿�Ʊ</a></li>
		    <li><a href="#" id="srkpBtn" class="licon11">���뿪Ʊ</a></li>
		    <li><a href="#" id="ykpBtn" class="licon12">Ԥ��Ʊ</a></li>
		    <s:if test='domain.xtcsSfsp == "Y"'>
			    <li><a href="#" id="plScSendBtn" class="licon10">������������</a></li>
		    </s:if>
		    <li><a href="#" id="closeBtn" class="licon03">�� ��</a></li>
	  	</ul>
	
	    <ul class="rcont">
		    <li class="ricon02" onclick="slideToggle('syquery')">��ʾ/���ز�ѯ����</li>
		    <li class="ricon03">����</li>
	  	</ul>
	</div> 

	<div class="right_cont" id="maincont">
		<div id="divQuery">
	<fieldset>
		<legend>��ѯ����</legend>
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
				<td width="8%" align="right">�ͻ����ƣ�</td>
				<td width="21%">
				<s:hidden name="domain.khDjxh"></s:hidden>
				<div class="inputsel" style="width: 230px; ">
					<s:textfield name="domain.khMc" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width:200px;"></s:textfield> 
					<a href="#" class="icon_arrow" id="fhr" onfocus="this.blur()"></a></div>
				<div class="inputsc">
				<div id="inputSel_fhr"
					class="inputselcont inputselFixedSize ac_results"></div>
				</div>
				</td>
			</tr>
			<tr>
				<td align="right">�������ڣ�</td>
				<td>
					<s:textfield name="domain.rqQ" readonly="true" cssClass="ymdate"></s:textfield>
					 �� 
					<s:textfield name="domain.rqZ" readonly="true" cssClass="ymdate"></s:textfield></td>
				<td colspan="4"></td>
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
