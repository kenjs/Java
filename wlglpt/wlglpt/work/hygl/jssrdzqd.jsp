<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"
	pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>����-�������-�嵥</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
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
			
			//������ť�¼�
			$("#addBtn").click(function(){
				var djJgbm = trim($("#mainForm_domain_djJgbm").val()); 
				var dwDm = trim($("#mainForm_domain_ssJgbm").val()); 
				var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
				
		  		if(undefined==dwDm || null==dwDm || ""==dwDm){
					showAlert("����ѡ��ҵ��λ��");
					return;
				}
				
				//if(undefined==djJgbm || null==djJgbm || ""==djJgbm){
					//showAlert("����ѡ���ţ�");
					//return;
				//}
				
				if(undefined==khDjxh || null==khDjxh || ""==khDjxh){
					khDjxh = '';
				}
				//var dwMcStr = $("select[name='domain.ssJgbm'] option[selected]").text(); 
				var dwMcStr = $("select[name='domain.ssJgbm']").find("option:selected").text(); 
				var dwMc=dwMcStr.split(" ")[1];
				var khMc = trim($("#mainForm_domain_khMc").val()); 
				//popwindow(jcontextPath+"/hygl/jssrdzqd!initMx");
				onUpdate("",dwDm,khDjxh,dwMc,khMc);
			});
			//��ʼ�����
			initDataGrid();
			initList();				

	});
	
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
	
	function onView(qdDjxh) {
		var url = jcontextPath+"/hygl/jssrdzqd!viewMx?domain.qdDjxh="+qdDjxh;
		window.open(url,"_blank")
	}
	
    function onUpdate(qdDjxh,ssJgbm,khDjxh,dwMc,khMc){
    	var url = jcontextPath+"/hygl/jssrdzqd!initMx.action?domain.qdDjxh="+qdDjxh+"&domain.ssJgbm="+ssJgbm+"&domain.khDjxh="+khDjxh;
    	url+="&domain.dwMc="+dwMc+"&domain.khMc="+khMc+"&num="+Math.random();
    	url = encodeURI(encodeURI(url));
    	
    	window.showModalDialog(url,window,"dialogHeight:600px;dialogWidth:760px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
    	//window.open(url);
    	onRefresh();
    	//popwindow(jcontextPath+"/hygl/jssrdzqd!initMx?domain.qdDjxh="+qdDjxh);
    }
    
    var keyValue = "";
	function onDelete( qdDjxh){
		keyValue = qdDjxh;
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.qdDjxh":keyValue};
		 var url = jcontextPath+"/hygl/jssrdzqd!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}	
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var djJgbm = trim($("#mainForm_domain_djJgbm").val()); 
		var dwDm = trim($("#mainForm_domain_ssJgbm").val()); 
		var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
		var rqQ = trim($("#mainForm_domain_rqQ").val()); 
		var rqZ = trim($("#mainForm_domain_rqZ").val()); 
		
  		if(undefined==dwDm || null==dwDm || ""==dwDm){
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
		var url = jcontextPath+"/hygl/jssrdzqd!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.khDjxh":encodeURI(khDjxh),"domain.rqQ":encodeURI(rqQ),"domain.rqZ":encodeURI(rqZ),
				      "domain.djJgbm":djJgbm,"domain.ssJgbm":dwDm}								//����Ĳ�����json��ʽ
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
		    colNames:['����', '�嵥�Ǽ����','�嵥����','�嵥���','�����ϸ','���ܷ�ʽ',
				     '�Ǽ���','�Ǽ�����','����',
				     '��λ'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'80', align:'center'},
		      {name:'qdDjxh', index:'qdDjxh', width:'30', align:'center',hidden:true}, 		     
		      {name:'qdmc', index:'qdmc', width:'250', align:'center'}, 
		      {name:'heJe', index:'heJe', width:'80', align:'center'}, 
		      {name:'heJe', index:'heJe', width:'80', align:'center',hidden:true}, 
		      {name:'dzqdhzfsMc', index:'dzqdhzfsMc', width:'80', align:'center'}, 
		      
		      {name:'cjrMc', index:'cjrMc', width:'70', align:'center'}, 
		      {name:'djrq', index:'djrq', width:'70', align:'center'}, 

		      {name:'bmMc', index:'bmMc', width:'180', align:'center'}, 
		      {name:'dwMc', index:'dwMc', width:'120', align:'center'}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'QD_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		  
	  
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"qdDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">�޸�</font></a>"
                + " <a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">ɾ��</font></a>"
                + " <a href=\"javascript:onView('"+val+"')\"><font color=\"blue\">�鿴</font></a>";
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="jssrdzqd!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="jsonData" />
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
	
	<div class="right_cont" id="maincont">
	<div id="divQuery">
	<fieldset><legend>��ѯ����</legend>
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
		<table id="dataList"><tr><td></td></tr>
		</table>
		<!-- ��ҳ���� -->
		<div id="pager"></div>
		<%@include file="/common/message.jsp"%>
	</div>

</s:form>
</body>
</html>
