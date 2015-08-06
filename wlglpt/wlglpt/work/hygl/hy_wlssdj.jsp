<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>����-�ɳ�-������ʧ�Ǽ�</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>

<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
			
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		initHykhData(300, $("#mainForm_domain_pcJgbm4Query").val(), $("#mainForm_domain_djJgbm4Query").val());
		
		$("#mainForm_domain_pcJgbm4Query").change(function(){
			initHykhData(300, $(this).val(), $("#mainForm_domain_djJgbm4Query").val());
		});
		
		$("#mainForm_domain_djJgbm4Query").change(function(){
			$("[name='domain.fhrMc']").unbind();
			initHykhData(300, $("#mainForm_domain_pcJgbm4Query").val(), $(this).val());
		});
		
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
		
			var sjJgbm = $("#mainForm_domain_dwDm").val();
			bmInit(sjJgbm, '', "domain.pcbm4Query", "mainForm_domain_pcbm4Query", "Y", "Y");
			//��ʼ�����
			initDataGrid();
					

	});

	function checkNum() {
		var ch = $(":checkbox[name='dj']");
		var num = 0;
		for(var i=0;i<ch.length;i++){
			if(ch[i].checked){
				++num; 
			}
		}
		if(num < 1){
			return false;
		}
		return true;
	}
	
	var pc='';
    function onUpdate(pcDjxh){
    	pc=pcDjxh;
    	var ul=jcontextPath+"/hygl/wlssdj!checkWlDj";
    	var json={"domain.pcDjxh":pcDjxh};
    	ajaxCommon(ul,json,"toUpdate");
    }
    function toUpdate(data){
    	if(data.domain.tager=='N'){
    		var url = jcontextPath+"/hygl/wlssdj!initMx.action?domain.pcDjxh="+pc;
        	window.showModalDialog(url,window,"dialogHeight:405px;dialogWidth:675px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
        	onRefresh();
    	}
    	else{
    		showConfirm("���ɳ����ѵǼ�������ʧ,������ͨ��������ʧ�ǼǵǼǹ�������޸�,Ҳ���Ե��ȷ������������ʧ�Ǽ�","okUpdate");
    	} 
    
    }
    function okUpdate(){
    	var url = jcontextPath+"/hygl/wlssdj!initMx.action?domain.pcDjxh="+pc;
    	window.showModalDialog(url,window,"dialogHeight:405px;dialogWidth:675px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
    	onRefresh();
    }
 
    
 
	

	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
	var	pcbm4Query = $("#mainForm_domain_pcbm4Query").val();
	var	pcrqQ = $("#mainForm_domain_pcrqQ").val();
	var	pcrqZ = $("#mainForm_domain_pcrqZ").val();
	var	clhm4Query = $("#mainForm_domain_clhm4Query").val();
	var fhrDjxh= $("#mainForm_domain_fhrDjxh").val(); 
	var fhrMc= $("#mainForm_domain_fhrMc").val(); 
	var	pcdh4Qyuery = $("#mainForm_domain_pcdh4Query").val();
	var dwDm = $("#mainForm_domain_dwDm").val();
	var dwbmBz4Query='';
	var pcJgbm4Query='';
	if(pcbm4Query!=null&&pcbm4Query!=''){
		dwbmBz4Query='B';
		pcJgbm4Query=pcbm4Query;
	}else{
		dwbmBz4Query='D';
		pcJgbm4Query=dwDm;
	}
		//����������
		var url = jcontextPath+"/hygl/wlssdj!query.action";   
	
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.pcrqQ":pcrqQ,"domain.pcrqZ":pcrqZ,
		 		"domain.clhm4Query":encodeURI(clhm4Query),"domain.fhrDjxh":fhrDjxh,"domain.fhrMc4Query":encodeURI(fhrMc),
		 		"domain.pcdh4Query":encodeURI(pcdh4Qyuery),"domain.dwbmBz4Query":dwbmBz4Query,"domain.pcJgbm4Query":pcJgbm4Query}								//����Ĳ�����json��ʽ
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
		    colNames:['���','����','�ɳ�����','��������','�ҳ�����','˾��',
		             '','�������','�ͻ�����','��������','ʼ����','Ŀ�ĵ�','ת�벿��','��������','������ַ'
		              ,'�ջ���ַ','�ɳ���','�ɳ�����','�ɳ�����','ҵ��λ'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[
	
		      {name:'pageXh', index:'pageXh', width:'35', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pageXh' + rowId + '\'';
				    }
			   },
			  {name:'hstoperationcol', index:'hstoperationcol', width:'50', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'hstoperationcol' + rowId + '\'';
				    }
			   },
			   {name:'pcdh', index:'pcdh', width:'70', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcdh' + rowId + '\'';
				    }
			   },
			   {name:'cyrClhm', index:'cyrClhm', width:'60', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'cyrClhm' + rowId + '\'';
				    }
			   },
			   {name:'cyrGchm', index:'cyrGchm', width:'60', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'cyrGchm' + rowId + '\'';
				    }
			   },
			   {name:'cyrSjxm', index:'cyrSjxm', width:'50', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'cyrSjxm' + rowId + '\'';
				    }
			   },
			  {name:'pcDjxh', index:'pcDjxh',hidden:true, width:'0', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcDjxh' + rowId + '\'';
				    }
			   },
			 
			  {name:'ddbh', index:'ddbh', sortable:false, width:'60', align:'center'},
			  {name:'khmc', index:'khmc', sortable:false, width:'120', align:'center'},
			  {name:'hwmc', index:'hwmc', sortable:false, width:'100', align:'center'},
			  {name:'fhrXzqhMc', index:'fhrXzqhMc', sortable:false, width:'50', align:'center'},
			  {name:'shrXzqhMc', index:'shrXzqhMc', sortable:false, width:'50', align:'center'},
			  {name:'zrbmMc', index:'zrbmMc', sortable:false, width:'100', align:'center'},
			  {name:'jsSl', index:'jsSl', sortable:false, width:'60', align:'right'},
			  {name:'fhrDz', index:'fhrDz', sortable:false, width:'120', align:'center'},
			  {name:'shrDz', index:'shrDz', sortable:false, width:'120', align:'center'},
			  {name:'pcrMc', index:'pcrMc', width:'70', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcrMc' + rowId + '\'';
				    }
			   },
			   {name:'pcrq', index:'pcrq', width:'80', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcrq' + rowId + '\'';
				    }
			   },
			   {name:'pcJgmc', index:'pcJgmc', width:'100', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcJgmc' + rowId + '\'';
				    }
			   },
			   {name:'ssJgmc', index:'ssJgmc',width:'100', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'ssJgmc' + rowId + '\'';
				    }
			   }
			 
		  
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'PCDH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/hypchd!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
               
                var link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">�Ǽ�</font></a>";
        
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link });
           
            }
            var gridName = "dataList";
	   		var a = ['hstoperationcol','pageXh','pcdh','cyrClhm','cyrGchm','cyrSjxm','pcDjxh','pcrMc','pcrq','pcJgmc','ssJgmc'];
 		
       		Merger(gridName, 'pcDjxh', a);
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="hypchd!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	
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
	<div id="maincont" style="display: none;"></div>
	<div class="right_cont">
		 <div id="divQuery">
		<fieldset>
			<legend>��ѯ����</legend>
		   <table width="99%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		           <td width="8%" align="right">ҵ��λ��</td>
		          <td width="19%">	<sys:gsList myId="mainForm_domain_dwDm" myName="domain.dwDm" mcContainDmBz="Y" myClass="select" onChange="bmInit(this.value, '', 'domain.pcbm4Query', 'mainForm_domain_pcbm4Query', 'Y', 'Y')"/></td>
		          <td width="8%" align="right">�ɳ����ţ�</td>
		          <td width="19%"><sys:gsBmList myName="domain.pcbm4Query" myId="mainForm_domain_pcbm4Query"  contaisQxz="false" myClass="select" /></td>
		        	 <td width="8%" align="right">�ɳ����ڣ�</td>
		          <td width="28%">
		             	<sys:dateFirstDLastMonthTag myName="domain.pcrqQ" myId="mainForm_domain_pcrqQ" myClass="ymdate" />
		          			��
		          		<sys:dateCurrentDayTag myName="domain.pcrqZ" myId="mainForm_domain_pcrqZ" myClass="ymdate" />
		          </td>
		        </tr>
		        <tr>
		        	<td align="right">�ͻ����ƣ�</td>
		            <td>
			           <s:hidden name="domain.fhrDjxh"></s:hidden>
	  					<div class="inputsel" style="width: 230px; ">
	  						<s:textfield name="domain.fhrMc" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width:200px;"></s:textfield>
	  						<a href="#" class="icon_arrow" id="fhr" onFocus="this.blur()"></a>
	  					</div>
				  		<div class="inputsc">
			              <div id="inputSel_fhr" class="inputselcont inputselFixedSize ac_results" style="width: 230px; ">
			              </div>
			            </div>
	          		</td>
		        	<td align="right">�ɳ����ţ�</td>
		         	<td><s:textfield name="domain.pcdh4Query" cssClass="pop_input noborder" /></td>
		        	<td align="right">�������룺</td>
		        	<td><s:textfield name="domain.clhm4Query" cssClass="pop_input noborder" /></td>	
		        </tr>
		        <tr>	        	  
		        
		        </tr>
		   </table>
		</fieldset>
	  </div>
	  </div>
		<!-- ��ҳ��� id����ΪdataList -->
		<table id="dataList"><tr><td/></tr></table> 
		<!-- ��ҳ���� -->
		<div id="pager"></div>
		
		
		<%@include file="/common/message.jsp" %>
</s:form>
</body>
</html>
