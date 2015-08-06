<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>����-���˵�-δ����(���)��Ϣ</title>
<style type="text/css">
html,body {overflow:hidden;}
.hiddenCss {display: none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		initHykhData(300);
		initXzqhData(200);
		initXzqhInputSel();
		
		$("#pcBtn").click(function(){
			var wfhXhs = getCheckedWfhXhs();
			saveWfhxx4Pc(wfhXhs)
		});
		
		$("#closeBtn").click(function() {
			window.close();
		});
		
		$("#mainForm_domain_djJgbm4Query").change(function(){
			initRy();
		});
		
		//��ѯ��ť�¼�
		$("#queryBtn").click(function(){
			onRefresh();
		});
		
		var rem=new Array();
		$(":checkbox[name='wfhXhs']").click(function(){
			alert(0);
		 	var arr = $(":checkbox[name='wfhXhs']");
         	rem.push($(":checkbox[name='wfhXhs']").index($(this)));
         	if(e.shiftKey){
            	if(arr[$(":checkbox[name='wfhXhs']").index($(this))].checked == true){
                	if(rem.length>=2){
                     	var iMin =  Math.min(rem[rem.length-2],rem[rem.length-1])
                     	var iMax =  Math.max(rem[rem.length-2],rem[rem.length-1])
                     	for(i=iMin;i<=iMax;i++){
                         	arr[i].checked = true;
                     	}
                 	}
             	}else{
                 	if(rem.length>=2){
	                     var iMin =  Math.min(rem[rem.length-2],rem[rem.length-1])
	                     var iMax =  Math.max(rem[rem.length-2],rem[rem.length-1])
	                     for(i=iMin;i<=iMax;i++){
	                         arr[i].checked = false;
	                     }
                 	}
             	}
         	}
     	});
		
		//��ʼ�����
		initDataGrid();
		initRy();	
		var sjJgbm = $("#mainForm_domain_dw4Query").val();
		bmInit(sjJgbm, '', "domain.djJgbm4Query", "mainForm_domain_djJgbm4Query", "Y", "Y");
	});
	
	function saveWfhxx4Pc(wfhXhs) {
		if (wfhXhs != null && wfhXhs.length > 0) {
			var url = jcontextPath+"/hygl/hypcxxgl!saveWfhxx4Pc";
			var jsonObj = {"domain.wfhXhs":wfhXhs};
			ajaxCommon(url,jsonObj,"saveWfhxx4PcSuc");
		}
	}
	
	function saveWfhxx4PcSuc(data) {
		var pchwLsxh = data.domain.pchwLsxh;
		var url = jcontextPath+"/hygl/hypcxxgl!initMx.action?domain.pchwLsxh="+pchwLsxh;
    	window.showModalDialog(url,window,"dialogHeight:580px;dialogWidth:895px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
    	onRefresh();
	}
	
	function initRy() {
		var sj = $("#mainForm_domain_djJgbm4Query").val();
		commonInit("BMYH", sj, '', "domain.djrCzyDjxh4Query", "mainForm_domain_djrCzyDjxh4Query", "Y", false);
	}
	
	function getCheckedWfhXhs() {
		var checks = $(":input:checked[name='wfhXhs']");
		var xhs = "";
		$.each(checks, function(i, obj){
			xhs += obj.value + ",";
		});
		if (xhs.length > 0) {
			xhs = xhs.substring(0, xhs.length-1);
		}
		
		return xhs;
	}
	
	function onViewMx(ddDjxh) {
		var url = jcontextPath+"/jcgl/jctydgl!initMx.action?domain.ddDjxh="+ddDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    	//window.open(url);
	}
	
	function onUpdate(wfhDjxh){
		var url = jcontextPath+"/hygl/hytydwfhxx!doUpdate";
		var jsonObj = {"domain.wfhDjxh":wfhDjxh};
		ajaxCommon(url,jsonObj,"upSucc");
	}
	
	function upSucc(){
		showAlert("�޸ĳɹ���");
		window.close();
		onRefresh();
	}
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var lb4Query = $("input[name='domain.lb4Query']:checked").val();
		 dw4Query = $("#mainForm_domain_dw4Query").val();
		 fhrXzqhDm = $("#mainForm_domain_fhrXzqhDm").val();
		 shrXzqhDm = $("#mainForm_domain_shrXzqhDm").val();
		 ddbh4Query = $("#mainForm_domain_ddbh4Query").val();
		 fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		 khMc4Query = $("#mainForm_domain_fhrMc").val();
		 hwztDm4Query = $("#mainForm_domain_hwztDm4Query").val();
		 djJgbm4Query = $("#mainForm_domain_djJgbm4Query").val();
		 djrCzyDjxh4Query = $("#mainForm_domain_djrCzyDjxh4Query").val();
		 xdrqQ = $("#mainForm_domain_xdrqQ").val();
		 xdrqZ = $("#mainForm_domain_xdrqZ").val();
		 if(xdrqQ>xdrqZ){
			 showError("�µ���ʼ���ڲ��ܴ����µ���ֹ���ڣ�");
			 return ;
		 }
		 fhrqQ = $("#mainForm_domain_fhrqQ").val();
		 fhrqZ = $("#mainForm_domain_fhrqZ").val();
		 if(fhrqQ>fhrqZ){
			 showError("Ҫ��ʼ���ڲ��ܴ���Ҫ���ֹ���ڣ�");
			 return ;
		 }
		//����������
		var url = jcontextPath+"/hygl/hytydwfhxx!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.dw4Query":encodeURI(dw4Query),"domain.fhrXzqhDm":encodeURI(fhrXzqhDm),"domain.shrXzqhDm":encodeURI(shrXzqhDm),"domain.ddbh4Query":ddbh4Query,
		 				"domain.khMc4Query":encodeURI(khMc4Query),"domain.hwztDm4Query":hwztDm4Query,"domain.lb4Query":encodeURI(lb4Query),"domain.djJgbm4Query":djJgbm4Query,
		 				"domain.djrCzyDjxh4Query":djrCzyDjxh4Query,"domain.xdrqQ":xdrqQ,"domain.xdrqZ":xdrqZ,"domain.fhrqQ":fhrqQ,"domain.fhrqZ":fhrqZ,"domain.fhrDjxh":fhrDjxh}
		 			//����Ĳ�����json��ʽ
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
	    gridComplete: myGridComplete,		//����������¼�
	    shrinkToFit:false, 
	    colNames:['���','�����Ǽ����','����˳���','δ�����Ǽ����', '�������','�ͻ�����','����','״̬','ʼ����','Ŀ�ĵ�','��������','��������',
	    		'����','����','���','��װ','���','������ַ','Ҫ�󷢻�����','�ջ���',
	    		'�ջ���ַ','Ҫ�󵽴�����','�Ǽ���','�Ǽ�����','�Ǽǲ���','��������'],			 //name ����ʾ������
	     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
	    colModel :[ 
		  {name:'pageXh', index:'pageXh', sortable:false, width:'30', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'pageXh' + rowId + '\'';
		    }
		  },
		  {name:'ddDjxh', index:'ddDjxh', hidden:true,width:'60', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'ddDjxh' + rowId + '\'';
		    }
		  },
		  {name:'xh', index:'xh', width:'100', hidden:true, align:'center'}, 
		  {name:'wfhDjxh', index:'wfhDjxh', hidden:true, width:'100', align:'center'},
		  {name:'ddbh', index:'ddbh', width:'70', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'ddbh' + rowId + '\'';
		    }
		  },
		  {name:'fhrMc', index:'fhrMc', width:'150', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'fhrMc' + rowId + '\'';
		    }
		  },
		  {name:'hstoperationcol',index:'',width:'40',align:'center',sortable:false},
	      {name:'hwztMc', index:'hwztMc', width:'60', align:'center'}, 
	      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'40', align:'center'}, 
	
	      {name:'shrXzqhMc', index:'shrXzqhMc', width:'40', align:'center'}, 
	      {name:'hwmc', index:'hwmc', width:'100', align:'center'}, 
	      {name:'jssl', index:'jssl', width:'50', align:'right'},
	      {name:'sl', index:'sl', width:'50', align:'center'}, 
	      {name:'zl', index:'zl', width:'50', align:'center'}, 
	      {name:'tj', index:'tj', width:'50', align:'center'}, 
	      {name:'bz', index:'bz', width:'50', align:'center'}, 
	      {name:'lb', index:'lb', width:'40', align:'center'}, 
	      {name:'fhrDz', index:'fhrDz', width:'100', align:'center'}, 
	      {name:'fhRq', index:'fhRq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}, 
	      {name:'shrMc', index:'shrMc', width:'60', align:'center'}, 
	      {name:'shDz', index:'shDz', width:'100', align:'center'}, 
	      {name:'yqDdrq', index:'yqDdrq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}, 
	      {name:'djrMc', index:'djrMc', width:'70', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'djrMc' + rowId + '\'';
		    }
		  },
		  {name:'djRq', index:'djRq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'djRq' + rowId + '\'';
		    }
		  },
		  {name:'djJgmc', index:'djJgmc', width:'100', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'djJgmc' + rowId + '\'';
		    }
		  },
		  {name:'ssJgmc', index:'ssJgmc', width:'100', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'ssJgmc' + rowId + '\'';
		    }
		  }
	    ],
	    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
	    rowNum: <%=rowNum %>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
	    rowList:[10,20,50,100],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
	    sortname: 'DD_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
	    sortorder: 'DESC',				//Ĭ��������
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
	       	   $("#mainForm").attr("action",jcontextPath+"/hygl/hytydwfhxx!download");
			   $("#mainForm").submit();
	       } 
	 });
	 
	}
	
	//��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
		 var graduateIds = $("#dataList").jqGrid('getDataIDs');
         for (var i = 0; i < graduateIds.length; i++) {
             var cl = graduateIds[i];
             
             var pageXh = jQuery("#dataList").jqGrid('getCell', cl,"pageXh");
             if(pageXh=='0'){
            	 pageXh='�ϼ�';
            	 $("#dataList").jqGrid('setRowData', cl, { 'pageXh': pageXh });
            	 continue;
             }
             var ddbh = jQuery("#dataList").jqGrid('getCell', cl,"ddbh");
             var wfhDjxh = jQuery("#dataList").jqGrid('getCell', cl,"wfhDjxh");
             var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh"); 
             var hwztMc = jQuery("#dataList").jqGrid('getCell', cl,"hwztMc");
             var str="<a href=\"javascript:onViewMx("+ddDjxh+")\"><font color=\"blue\">"+ddbh+"</font></a>";
             var opeStr = "";
             if(hwztMc == "δ��"){
            	 opeStr = "<a href=\"javascript:onUpdate("+wfhDjxh+")\"><font color=\"blue\">������</font></a>";
             }else if(hwztMc == "δ��"){
            	 opeStr = "<a href=\"javascript:onUpdate("+wfhDjxh+")\"><font color=\"blue\">�����</font></a>";
             }
             $("#dataList").jqGrid('setRowData', cl, { 'ddbh': str });
             $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': opeStr });
         }
	    
	   var gridName = "dataList";
		   var a = ['pageXh','ddDjxh','ddbh','fhrMc','djRq','djrMc','djJgmc','ssJgmc'];
			
	    Merger(gridName, 'ddDjxh', a);
	}
/**************************************��ҳ����****************************************/
</script>
</head>
<body>
<s:form action="hytydwfhxx!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.pcOpenFlag"></s:hidden>
	<s:hidden name="jsonData" />
	<s:hidden name="fhrData" />
	
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
	<div class="right_cont" id="maincont">
	 <div id="divQuery">
		<fieldset>
			<legend>��ѯ����</legend>
		   <table width="99%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		          <td width="10%" align="right">ҵ��λ��</td>
		          <td width="21%">
		          	<sys:gsList myName="domain.dw4Query" myId="mainForm_domain_dw4Query" onChange="bmInit(this.value, '', 'domain.djJgbm4Query', 'mainForm_domain_djJgbm4Query', 'Y', 'Y')" mcContainDmBz="Y" contaisQxz="false" myClass="select" />
		          </td>
		          <td width="10%" align="right">�Ǽǲ��ţ�</td>
		          <td width="21%">
		          		<select id="mainForm_domain_djJgbm4Query" name="domain.djJgbm4Query" class="select" >
          					<option value="${domain.djJgbm4Query }" selected="selected"></option>
          				</select>
		          </td>
		          <td width="10%" align="right">�µ����ڣ�</td>
		          <td width="27%">
		            <sys:dateFirstDLastMonthTag myName="domain.xdrqQ" myId="mainForm_domain_xdrqQ" myClass="ymdate" />
	          			��
	          		<sys:dateCurrentDayTag myName="domain.xdrqZ" myId="mainForm_domain_xdrqZ" myClass="ymdate" />
	          	  </td>
		        </tr>
		        <tr>
		          <td align="right">�ͻ����ƣ�</td>
		          <td>
		          	<s:hidden name="domain.fhrDjxh"></s:hidden>
  					<div class="inputsel" style="width: 240px; ">
  						<s:textfield name="domain.fhrMc" cssClass="pop_input noborder inputext" cssStyle="width:210px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="fhr" onFocus="this.blur()"></a>
  					</div>
			  		<div class="inputsc">
		              <div id="inputSel_fhr" class="inputselcont inputselFixedSize ac_results">
		              </div>
		            </div>
		          </td>
	        	  <td align="right">������ţ�</td>
		          <td><s:textfield name="domain.ddbh4Query" cssClass="pop_input noborder" /></td>
		           <td align="right">Ҫ�����ڣ�</td>
		          <td><s:textfield name="domain.fhrqQ" cssClass="ymdate" />
	          			��
	          		<s:textfield name="domain.fhrqZ" cssClass="ymdate" /></td>
		        </tr>
		        <tr>
		          <td align="right">�������</td>
		          <td>
		          	<s:radio name="domain.lb4Query" list='#{"":"����","1":"�ػ�","2":"�ݻ�"}' listKey="key" listValue="value"></s:radio>
		          </td>
	        	  <td align="right">ʼ���أ�</td>
		          <td colspan="3">
			          <div style="float: left;">
			          	<div style="float: left;"></div>
			          	<s:hidden name="domain.fhrXzqhDm" />
	  					<div class="inputsel" style="width: 100px;">
	  						<s:textfield name="domain.fhrXzqhMc" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width: 70px;"></s:textfield>
	  						<a href="#" class="icon_arrow" id="xzqh" xzqh="fhrXzqh" onFocus="this.blur()"></a>
			            </div>
			           </div>
		            
		            <div style="float: left;">
		            	<div style="float: left;">&nbsp;&nbsp;&nbsp;Ŀ�ĵأ�</div>
		            	<s:hidden name="domain.shrXzqhDm"></s:hidden>
  						<div class="inputsel" style="width: 100px;">
	  						<s:textfield name="domain.shrXzqhMc" cssClass="inputext pop_input noborder bgstyle_optional" cssStyle="width: 70px;"></s:textfield>
	  						<a href="#" class="icon_arrow" id="xzqh" xzqh="shrXzqh" onFocus="this.blur()"></a>
		            	</div>
		            </div>
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
	<div id="inputSel_xzqh" class="inputselcont" style="position: absolute; top: 159px; left: 94px; display: none;" >
		                
      <iframe src="" scrolling="no" frameborder="0" style="position:absolute; top:0; left:0; z-index:-1; width:274px; height:100px;"></iframe>
    </div>
</s:form>
</body>
</html>