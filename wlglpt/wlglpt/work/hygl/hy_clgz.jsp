<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>��������</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<link rel="stylesheet" href="<sys:context />/resource/pageframe/com/jquery.ztree/demo.css" type="text/css" />
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
			
			//������ť�¼�
			$("#addBtn").click(function(){
				onUpdate('');
				//popwindow(jcontextPath+"/hygl/hytydgl!initMx");
			});
			//��ʼ�����
			initDataGrid();
			var sjJgbm = $("#mainForm_domain_pcJgbm4Query").val();
			bmInit(sjJgbm, '', "domain.djJgbm4Query", "mainForm_domain_djJgbm4Query", "Y", "Y");
	});

	function onUpdate(pcDjxh){
		var pcJgbm4Query = $("#mainForm_domain_pcJgbm4Query").val();
		var url = jcontextPath+"/hygl/hyclgz!initMx.action?domain.pcDjxh="+pcDjxh+"&domain.ssJgbm="+pcJgbm4Query;
		window.showModalDialog(url,window,"dialogHeight:550px;dialogWidth:750px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
    	onRefresh();
	}	
    
	  function onViewPc(pcDjxh){
	    	var url = jcontextPath+"/jcgl/jcpcxxgl!initMx.action?domain.pcDjxh="+pcDjxh;
	    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
	    }
	
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		pcJgbm4Query = $("#mainForm_domain_pcJgbm4Query").val();
		djJgbm4Query = $("#mainForm_domain_djJgbm4Query").val();
		fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		fhrMc = $("#mainForm_domain_fhrMc").val();
		pcrqQ = $("#mainForm_domain_pcrqQ").val();
		pcrqZ = $("#mainForm_domain_pcrqZ").val();
		pcdh4Query = $("#mainForm_domain_pcdh4Query").val();
		clhm4Query = $("#mainForm_domain_clhm4Query").val();
		//����������
		var url = jcontextPath+"/hygl/hyclgz!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.pcJgbm4Query":pcJgbm4Query,"domain.djJgbm4Query":djJgbm4Query, "domain.fhrDjxh":fhrDjxh,"domain.fhrMc":encodeURI(fhrMc),
		 			"domain.pcrqQ":pcrqQ,"domain.pcrqZ":pcrqZ,"domain.pcdh4Query":pcdh4Query,"domain.clhm4Query":clhm4Query
		 			}								//����Ĳ�����json��ʽ
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
		    colNames:['���','����','����','����������','�ɳ��Ǽ����', '�ɳ�����','��������', '�ҳ�����', '˾��', '�������', 
		              '�ͻ�����', '��������','ʼ����', 'Ŀ�ĵ�', 'ת�벿��', '��������', '������ַ', '�ջ���ַ','�ɳ���', 
		              '�ɳ�����', '�ɳ�����', 'ҵ��λ'
		              ],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'pageXh', index:'pageXh',sortable:false, width:'30', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pageXh' + rowId + '\'';
			    }
			  },
			   {name:'hstoperationcol', index:'', width:'30', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'hstoperationcol' + rowId + '\'';
			    }
			  },
			  {name:'gzcs', index:'gzcs', width:'30', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'gzcs' + rowId + '\'';
			    }
			  },
			  {name:'zhgzrq', index:'zhgzrq', width:'130', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'},
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'zhgzrq' + rowId + '\'';
			    }
			  },
			   {name:'pcDjxh', index:'pcDjxh', width:'80', align:'center', hidden:true,
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcDjxh' + rowId + '\'';
			    }
			  },
			   {name:'pcdh', index:'pcdh', width:'80', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcdh' + rowId + '\'';
			    }
			  },
			   {name:'cyrClhm', index:'cyrClhm', width:'65', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'cyrClhm' + rowId + '\'';
			    }
			  },
			   {name:'cyrGchm', index:'cyrGchm', width:'50', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'cyrGchm' + rowId + '\'';
			    }
			  },
			   {name:'cyrSjxm', index:'cyrSjxm', width:'80', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'cyrSjxm' + rowId + '\'';
			    }
			  },
			  
			  {name:'ddbh', index:'ddbh', width:'80', align:'center'},
			  {name:'khmc', index:'khmc', width:'120', align:'center'},
			  {name:'hwmc', index:'hwmc', width:'80', align:'center'},
			  {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'60', align:'center'},
			  {name:'shrXzqhMc', index:'shrXzqhMc', width:'60', align:'center'},
			  {name:'zrbmMc', index:'zrbmMc', width:'100', align:'center'},
			  {name:'jsSl', index:'jsSl', width:'60', align:'center'},
			  {name:'fhrDz', index:'fhrDz', width:'80', align:'center'},
			  {name:'shrDz', index:'shrDz', width:'80', align:'center'},
			  
		      {name:'pcrMc', index:'pcrMc', width:'60', align:'center',
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcrMc' + rowId + '\'';
			    }
			  },
		      {name:'pcrq', index:'pcrq', width:'60', align:'center',
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcrq' + rowId + '\'';
			    }
			  },		      
		      {name:'pcJgmc', index:'pcJgmc', width:'80', align:'center',
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcJgmc' + rowId + '\'';
			    }
			  }, 
		      {name:'ssJgmc', index:'ssJgmc', width:'80', align:'center',
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'ssJgmc' + rowId + '\'';
			    }
			  }
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: 10,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[10,20,50,100],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'PC_DH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/hyclgz!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���Ǽǡ�������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var pcdh = jQuery("#dataList").jqGrid('getCell', cl,"pcdh"); 
                var val = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var strPc="<a href=\"javascript:onViewPc("+val+")\"><font color=\"blue\">"+pcdh+"</font></a>";
                var link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">�Ǽ�</font></a>";
                $("#dataList").jqGrid('setRowData', cl, { 'pcdh': strPc }); 
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
       var gridName = "dataList";
	   var a = ['pageXh','hstoperationcol','gzcs','zhgzrq','pcdh','cyrClhm','cyrGchm','cyrSjxm','pcrMc','pcrq','pcJgmc','ssJgmc']; 

       Merger(gridName, 'pageXh', a);

   }
       
     /**************************************��ҳ����****************************************/
     
</script>
</head>
<body>
<s:form action="hyclgz!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
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
          			<td width="21%">
          				<sys:gsList myId="mainForm_domain_pcJgbm4Query" myName="domain.pcJgbm4Query" mcContainDmBz="Y" myClass="select" onChange="bmInit(this.value, '', 'domain.djJgbm4Query', 'mainForm_domain_djJgbm4Query', 'Y', 'Y')" />
		  			</td>
		  			<td width="8%" align="right">�ɳ����ţ�</td>
          			<td width="21%">
          				<select id="mainForm_domain_djJgbm4Query" name="domain.djJgbm4Query" class="select" >
          				
          				</select>
		  			</td>
	        	 <td width="8%" align="right">�ͻ����ƣ�</td>
		          <td width="25%">
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
        		</tr>
		        <tr>
	        	  <td align="right">�ɳ����ţ�</td>
		          <td><s:textfield name="domain.pcdh4Query" cssClass="pop_input noborder" /></td>
		          <td align="right">�������룺</td>
		          <td><s:textfield name="domain.clhm4Query" cssClass="pop_input noborder" /></td>
	        	  <td align="right">�ɳ����ڣ�</td>
		          <td><sys:dateFirstDMonth myName="domain.pcrqQ" myId="mainForm_domain_pcrqQ" myClass="ymdate" />
	          			��
	          		<sys:dateCurrentDayTag myName="domain.pcrqZ" myId="mainForm_domain_pcrqZ" myClass="ymdate" /></td>
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
