<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>���ν���-�������</title>
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
			$("#pldzBtn").click(function(){
				var xhs = $("[name='xhs']:checked");
				if (xhs == null || xhs.length <= 0) {
					showAlert("����ѡ����Ҫ���˵ļ�¼��");
					return;
				}
				
				doPldz();
			});
			
			//��ʼ�����
			initDataGrid();
			initRadio();	
			changeQdList();
			onRefresh();
	});
	
	function initRadio() {
		$("[name='domain.dzztDm']")[0].checked = true;
		if ($("#mainForm_domain_fylbDm").val() != "") {
			$("[name='domain.fylbDm'][value='"+$("#mainForm_domain_fylbDm").val()+"']")[0].checked = true;
		}else {
			$("[name='domain.fylbDm']")[1].checked = true;
		}
		
	}
	
	function doPldz() {
		var fylbDm = $("[name='domain.fylbDm']:checked").val();
		var xhs = $(":checked[name='xhs'][value!='']");
		if (xhs.length > 0) {
			var jsonStr = getJqueryParam(xhs, "domain.ywDjxhs");
			var jsonObj = {"domain.fylbDm":fylbDm};
			
			jsonStr += jQuery.param(jsonObj);
			var url = jcontextPath+"/hygl/xyjssrdz!savePldz";  
			showMessage();
			ajaxCommon(url,encodeURI(jsonStr),"savePldzSuc", false);
		}
	}
	
	function savePldzSuc(data) {
		hideMessage();
		showAlert("�������˳ɹ���");
		onRefresh();
	}
	
	function changeQdList() {
		var fylbDm = $("[name='domain.fylbDm']:checked").val();
		var qdDjxh = $("#mainForm_domain_qdDjxh").val();
		var jsonObj = {"domain.fylbDm":fylbDm, "domain.qdDjxh":qdDjxh};
		var url = jcontextPath+"/hygl/xyjssrdz!queryDzqdList";
		ajaxCommon(url,jsonObj,"changeQdListSuc");
	}
	
	function changeQdListSuc(data) {
		var list = data.domain.dzqdList;
		$("#mainForm_domain_qdDjxh").empty();
		var option = document.createElement('option');
		$("#mainForm_domain_qdDjxh")[0].add(option);
	    $(option).text("--��ѡ��--").val("");
		
		$.each(list, function(i,domain){
		    var option = document.createElement('option');
		    $("#mainForm_domain_qdDjxh")[0].add(option);
		    $(option).text(domain.qdmc).val(domain.qdDjxh);
		    //Ĭ��ѡ��
		    if(data.domain.qdDjxh==domain.qdDjxh){
		    	$(option).attr("selected", "selected");
		    	$(option).text(domain.qdmc).val(domain.qdDjxh);
		    }
		    
		});
	}
	
	function onDz(ywDjxh) {
		var fylbDm = $("[name='domain.fylbDm']:checked").val();
		onUpdate('',ywDjxh,fylbDm);
	}

    function onUpdate(dzDjxh,ywDjxh,fylbDm){
    	var url = jcontextPath+"/hygl/xyjssrdz!initMx?domain.dzDjxh="+dzDjxh+"&domain.ywDjxh="+ywDjxh+
    			"&domain.ywMxXh="+fylbDm+"&num="+Math.random();
    	window.showModalDialog(url,window,"dialogHeight:350px;dialogWidth:720px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;");
    	onRefresh();
    }
    
    var keyValue = "";
	function onDelete( dzDjxh){
		keyValue = dzDjxh;
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.dzDjxh":keyValue};
		 var url = jcontextPath+"/hygl/xyjssrdz!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("ɾ���ɹ���");
        onRefresh();
	}	
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var xyDjxh = trim($("#mainForm_domain_xyDjxh").val()); 
		var pcdh = $("#mainForm_domain_pcdh").val();
		var pcrqQ = trim($("#mainForm_domain_pcrqQ").val()); 
		var pcrqZ = trim($("#mainForm_domain_pcrqZ").val()); 
		var ddbh = $("#mainForm_domain_ddbh").val();
		var hwSl = $("#mainForm_domain_hwSl").val();
		var hdbh = $("#mainForm_domain_hdbh").val();
		var xdrqQ = trim($("#mainForm_domain_xdrqQ").val()); 
		var xdrqZ = trim($("#mainForm_domain_xdrqZ").val()); 
		var dzztDm = $("[name='domain.dzztDm']:checked").val();
		var fylbDm = $("[name='domain.fylbDm']:checked").val();
		var qdDjxh = trim($("#mainForm_domain_qdDjxh").val()); 
		
		if ("1" == fylbDm) {
			$("#dataList").jqGrid('showCol',["zcPsf"]);
			$("#dataList").jqGrid('hideCol',["zcDf","zcDshk"]);
		}else if ("2" == fylbDm) {
			$("#dataList").jqGrid('showCol',["zcDf"]);
			$("#dataList").jqGrid('hideCol',["zcDshk","zcPsf"]);
		}else if ("3" == fylbDm) {
			$("#dataList").jqGrid('showCol',["zcDshk"]);
			$("#dataList").jqGrid('hideCol',["zcPsf","zcDf"]);
		}
		
		if ("1" == dzztDm) {
			$("#dataList").jqGrid('hideCol',["dzcyje"]);
		}else if ("2" == dzztDm) {
			$("#dataList").jqGrid('showCol',["dzje","dzcyje"]);
		}
		
		
		//����������
		var url = jcontextPath+"/hygl/xyjssrdz!query";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.xyDjxh":xyDjxh,"domain.pcdh":encodeURI(pcdh),
		 		"domain.pcrqQ":encodeURI(pcrqQ),"domain.pcrqZ":encodeURI(pcrqZ),
			      "domain.ddbh":encodeURI(ddbh),"domain.hwSl":hwSl,"domain.hdbh":hdbh,
			      "domain.xdrqQ":encodeURI(xdrqQ),"domain.xdrqZ":encodeURI(xdrqZ),"domain.dzztDm":dzztDm,
			      "domain.fylbDm":fylbDm,"domain.qdDjxh":qdDjxh}								//����Ĳ�����json��ʽ
		 }
		 ).trigger("reloadGrid", [{page:1}]);		//��ʾ����һҳ�������������ݷ����仯��ʱ�򣬷�ҳ����Ϣ����
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
			    		'���˵Ǽ����','����Ǽ����','���ͷ�','������','���ջ���','���˽��','����','���˱�ע',
			    		'�������','�µ�����','ʼ����','Ŀ�ĵ�',
			              "��������","����","����","���","������","�ջ���","�ջ���ַ",'���ε�λ','���˵�λ',"�ɳ�����","�ɳ�����"],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'25', align:'center'},
			  {name:'dzDjxh', index:'dzDjxh', width:'30', hidden:true, align:'center'},
		      {name:'ywDjxh', index:'ywDjxh', width:'30', hidden:true, align:'center'},
		      {name:'zcPsf', index:'zcPsf', width:'50', align:'center', hidden:true},
		      {name:'zcDf', index:'zcDf', width:'50', align:'center', hidden:true}, 
		      {name:'zcDshk', index:'zcDshk', width:'50', align:'center', hidden:true}, 
		      {name:'dzje', index:'dzje', width:'50', align:'center'},
		      {name:'dzcyje', index:'dzcyje', width:'50', align:'center', hidden:true},
		      {name:'bz', index:'bz', width:'90', align:'center'},
		      {name:'ddbh', index:'ddbh', width:'70', align:'center'}, 
		      {name:'xdrq', index:'xdrq', width:'65', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      {name:'sfdXzqhMc', index:'sfdXzqhMc', width:'50', align:'center'},
		      {name:'mddXzqhMc', index:'mddXzqhMc', width:'50', align:'center'}, 

		      {name:'hwmc', index:'hwmc', width:'70', align:'center'},
		      {name:'hwSl', index:'hwSl', width:'50', align:'center'},
		      {name:'hwZl', index:'hwZl', width:'50', align:'center'},
		      {name:'hwTj', index:'hwTj', width:'50', align:'center'},
		      {name:'fhrLxr', index:'fhrLxr', width:'50', align:'center'},
		      {name:'shrLxr', index:'shrLxr', width:'50', align:'center'},
		      {name:'shrDz', index:'shrDz', width:'60', align:'center'},
		      {name:'xyMc', index:'xyMc', width:'70', align:'center'},
		      {name:'ssJgmc', index:'ssJgmc', width:'70', align:'center'},
		      
		      {name:'pcrq', index:'pcrq', width:'65', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}, 
		      {name:'pcdh', index:'pcdh', width:'70', align:'center'}
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'XDRQ,ddbh',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
		    sortorder: 'ASC,ASC',				//Ĭ��������
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/xyjssrdz!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            var dzztDm = $("[name='domain.dzztDm']:checked").val();
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var ywDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ywDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ����
                var dzDjxh = jQuery("#dataList").jqGrid('getCell', cl,"dzDjxh");
                var dzje= jQuery("#dataList").jqGrid('getCell', cl,"dzje");
                
                var link = "<input type='checkbox' name='xhs' value='"+ywDjxh+"' />";
                var dzLink = "<a href=\"javascript:onDz('"+ywDjxh+"')\"><font color=\"blue\">����</font></a>";
                
                if (dzje != "") {
                	link = "<input type='checkbox' disabled='disabled' name='xhs' value='"+ywDjxh+"' />";
                	dzLink = "<a href=\"javascript:onUpdate('"+dzDjxh+"')\"><font color=\"blue\">"+dzje+"</font></a>"
                }
            	   
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
                $("#dataList").jqGrid('setRowData', cl, { 'dzje': dzLink }); 
            }
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="xyjssrdz!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.fylbDm"></s:hidden>
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">������</li>
		    <li><a href="#" id="queryBtn" class="licon04">�� ��</a></li>
		    <li><a href="#" id="pldzBtn" class="licon01">��������</a></li>
		    <li><a href="#" id="closeBtn" class="licon03">�ر�</a></li>
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
		   <table width="100%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		          <td width="8%" align="right">ҵ��λ��</td>
					<td width="15%">
						<sys:gsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="N" myClass="select"/></td>
					<td width="8%" align="right">���ε�λ��</td>
					<td width="15%">
						<sys:fgsList myId="mainForm_domain_xyDjxh" myName="domain.xyDjxh" contaisQxz="true" contaisDq="N" myClass="select"></sys:fgsList>
					</td>
					<td width="8%" align="right">�ɳ����ţ�</td>
      				<td width="12%">
      					<s:textfield name="domain.pcdh" cssClass="pop_input noborder bgstyle_optional"></s:textfield>
      				</td>
					<td width="8%" align="right">�ɳ����ڣ�</td>
					<td width="25%">
      					<input type="text" name="domain.pcrqQ" id="mainForm_domain_pcrqQ" value="<s:date name="domain.pcrqQ" format="yyyy-MM-dd" />" class="ymdate" />
			          	��
			          	<input type="text" name="domain.pcrqZ" id="mainForm_domain_pcrqZ" value="<s:date name="domain.pcrqZ" format="yyyy-MM-dd" />" class="ymdate" />
      				</td>
		        </tr>
		        <tr>
		        	<td align="right">������ţ�</td>
      				<td>
      					<s:textfield name="domain.ddbh" cssClass="pop_input noborder bgstyle_optional"></s:textfield>
      				</td>
      				<td align="right">������</td>
      				<td>
      					<s:textfield name="domain.hwSl" cssClass="pop_input noborder bgstyle_optional"></s:textfield>
      				</td>
      				<td align="right">�ص���ţ�</td>
      				<td>
      					<s:textfield name="domain.hdbh" cssClass="pop_input noborder bgstyle_optional"></s:textfield>
      				</td>
      				<td align="right">�µ����ڣ�</td>
      				<td>
      					<input type="text" name="domain.xdrqQ" id="mainForm_domain_xdrqQ" value="<s:date name="domain.xdrqQ" format="yyyy-MM-dd" />" class="ymdate" />
			          	��
			          	<input type="text" name="domain.xdrqZ" id="mainForm_domain_xdrqZ" value="<s:date name="domain.xdrqZ" format="yyyy-MM-dd" />" class="ymdate" />
      				</td>
      			</tr>
      			<tr>
	    			<td align="right">����״̬��</td>
			        <td colspan="2">
			          	<s:radio name="domain.dzztDm" list='#{"":"����","1":"δ����","2":"�Ѷ���"}' listKey="key" listValue="value"></s:radio>
			        </td>
			        <td colspan="3">�������
						<s:radio name="domain.fylbDm" onclick="changeQdList();" list="#{'1':'���ͷ�','2':'������','3':'���ջ���' }"></s:radio>
					</td>
			        <td align="right">�����嵥��</td>
			        <td>
			          	<select name="domain.qdDjxh" id="mainForm_domain_qdDjxh" class="select">
			          		<option value="${domain.qdDjxh }"></option>
			          	</select>
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
