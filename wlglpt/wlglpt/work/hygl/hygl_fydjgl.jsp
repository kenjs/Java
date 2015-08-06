<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>���õǼǹ���</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
			document.getElementById("maincont").onmousewheel=hideHelpWindow;
			initHykhData(300, $("#mainForm_domain_pcJgbm4Query").val());
			
			$("#mainForm_domain_pcJgbm4Query").change(function(){
				$("[name='domain.fhrMc']").unbind();
				initHykhData(300, $(this).val(),$("#mainForm_domain_djJgbm4Query").val());
			});
			
			$("#mainForm_domain_djJgbm4Query").change(function(){
				$("[name='domain.fhrMc']").unbind();
				initHykhData(300, $("#mainForm_domain_pcJgbm4Query").val(), $(this).val());
			});
		
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//��������
			$("#plScSendBtn").click(function(){
				var wsDm="303001";//���õǼ�������
				plScSend(wsDm,"");
			});
			
			//��ʼ�����
			initDataGrid();
			var sjJgbm = $("#mainForm_domain_pcJgbm4Query").val();
			bmInit(sjJgbm, '', "domain.djJgbm4Query", "mainForm_domain_djJgbm4Query", "Y", "Y");
	});
	
	function onUpdate(fyDjxh,pcDjxh,ssJgbm,ddDjxh,xh,khDjxh,wfhDjxh,xybz){
		var xtcs20016 = $("#mainForm_domain_xtcs20016").val();
		if(xtcs20016=='Y'){
			if(xybz=='N'){
				showAlert("����Э��Ǽǣ�");
				return;
			}
		}		
		var url = jcontextPath+"/hygl/fydjgl!initMx.action?domain.fyDjxh="+fyDjxh+"&domain.pcDjxh="+pcDjxh
					+"&domain.ssJgbm="+ssJgbm+"&domain.ddDjxh="+ddDjxh+"&domain.xh="+xh+"&domain.khDjxh="+khDjxh+"&domain.wfhDjxh="+wfhDjxh;
		window.showModalDialog(url,window,"dialogHeight:580px;dialogWidth:900px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
    	onRefresh();
	}
	var v1="";
	var v2="";
	function onDelete(fyDjxh,pcDjxh){
		v1=fyDjxh;
		v2=pcDjxh;
		showConfirm("ȷ��Ҫ������ ��","doDelete");
	}
	
	function doDelete(){
		 var jsonObj = {"domain.fyDjxh":v1,"domain.pcDjxh":v2};
		 var url = jcontextPath+"/hygl/fydjgl!deleteFydj";   
         ajaxCommon(url,jsonObj , "deleteSuc");  
	}
	
	function deleteSuc(){  
		hideMessage();
        showAlert("�����ɹ���");
        onRefresh();
	}	
	
	function onShow(fyDjxh,pcDjxh,selOrUpd){
		var url = jcontextPath+"/hygl/fydjgl!viewMx.action?domain.fyDjxh="+fyDjxh+"&domain.pcDjxh="+pcDjxh+"&domain.selOrUpd="+selOrUpd;
		window.showModalDialog(url,window,"dialogHeight:580px;dialogWidth:740px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
	}
	
	function onViewMx(ddDjxh) {
		var url = jcontextPath+"/jcgl/jctydgl!initMx.action?domain.ddDjxh="+ddDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    	//window.open(url);
	}
	
	  function onViewPc(pcDjxh){
	    	var url = jcontextPath+"/jcgl/jcpcxxgl!initMx.action?domain.pcDjxh="+pcDjxh;
	    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
	    }
	
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var xtcsSfsp = $("#mainForm_domain_xtcsSfsp").val();
		if ("Y" == xtcsSfsp) {
			$("#dataList").jqGrid('showCol',["wsspztMc"]);
			$("#dataList").jqGrid('showCol',["checkboxoperationcol"]);
		}
		
		pcJgbm4Query = $("#mainForm_domain_pcJgbm4Query").val();
		djJgbm4Query = $("#mainForm_domain_djJgbm4Query").val();
		fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		fhrMc = $("#mainForm_domain_fhrMc").val();
		pcrqQ = $("#mainForm_domain_pcrqQ").val();
		pcrqZ = $("#mainForm_domain_pcrqZ").val();
		pcdh4Query = $("#mainForm_domain_pcdh4Query").val();
		clhm4Query = $("#mainForm_domain_clhm4Query").val();
		var zt = $(":input[name='domain.zt']:checked").val();
		//����������
		var url = jcontextPath+"/hygl/fydjgl!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.pcJgbm4Query":pcJgbm4Query,"domain.djJgbm4Query":djJgbm4Query,"domain.fhrDjxh":fhrDjxh,"domain.fhrMc":encodeURI(fhrMc),
		 			"domain.pcrqQ":pcrqQ,"domain.pcrqZ":pcrqZ,"domain.pcdh4Query":encodeURI(pcdh4Query),"domain.clhm4Query":encodeURI(clhm4Query),
		 			"domain.zt":zt}								//����Ĳ�����json��ʽ
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
		    colNames:['���','���õǼ����','�ɳ��Ǽ����',
		    			'�����������','��������״̬dm','wfhDjxh','�ɳ�����',
		    			'����','״̬','�������', '�ص����','�ͻ�����', 'ddDjxh','xh','khDJxh','xybz','��������','ʼ����', 'Ŀ�ĵ�', 'ת�벿��', 
		    			'��������', '������ַ', '�ջ���ַ','��������', '�ҳ�����', '˾��','�ɳ���','�ɳ�����', '�ɳ�����','������������', 'ҵ��λ'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'pageXh', index:'pageXh', sortable:false, width:'30', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pageXh' + rowId + '\'';
			    }
			  },			  		 
			  {name:'fyDjxh', index:'fyDjxh', width:'80', align:'center', hidden:true},
			  {name:'pcDjxh', index:'pcDjxh', width:'80', align:'center', hidden:true},								  
			  {name:'wsSpxh', index:'wsSpxh', width:'80', align:'center', hidden:true},
			  {name:'wsspztDm', index:'wsspztDm', width:'80', align:'center', hidden:true},
			  {name:'wfhDjxh', index:'wfhDjxh', width:'80', align:'center', hidden:true},
			  {name:'pcdh', index:'pcdh', width:'70', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcdh' + rowId + '\'';
			    }
			  },			  
			  {name:'hstoperationcol', index:'hstoperationcol', sortable:false,width:'60', align:'center'},
			  {name:'djzt', index:'djzt', width:'40', hidden:true,align:'center'},		
			  {name:'ddbh', index:'ddbh', width:'70', align:'center'},	
			  {name:'hdbh', index:'hdbh', width:'70', align:'center'},		  	  				 
			  {name:'khmc', index:'khmc', width:'120', align:'center'},
			  {name:'ddDjxh', index:'ddDjxh', hidden:true,width:'120', align:'center'},
			  {name:'xh', index:'xh', hidden:true,width:'120', align:'center'},
			  {name:'khDjxh', index:'khDjxh', hidden:true,width:'120', align:'center'},
			  {name:'xybz', index:'xybz', hidden:true,width:'120', align:'center'},
			  {name:'hwmc', index:'hwmc', width:'80', align:'center'},
			  {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'50', align:'center'},
			  {name:'shrXzqhMc', index:'shrXzqhMc', width:'50', align:'center'},
			  {name:'zrbmMc', index:'zrbmMc', width:'100', align:'center'},
			  {name:'jsSl', index:'jsSl', width:'50', align:'center'},
			  {name:'fhrDz', index:'fhrDz', width:'100', align:'center'},
			  {name:'shrDz', index:'shrDz', width:'100', align:'center'},
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
		      {name:'pcrMc', index:'pcrMc', width:'50', align:'center',
		      	cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcrMc' + rowId + '\'';
			    }
		      }, 
		      {name:'pcrq', index:'pcrq', width:'70',align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
		      	cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcrq' + rowId + '\'';
			    }
		      },		      
		      {name:'pcJgmc', index:'pcJgmc', width:'80', align:'center',
		      	cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcJgmc' + rowId + '\'';
			    }
		      }, 
		      {name:'ssJgbm', index:'ssJgbm', width:'80',hidden:true, align:'center'},
		      {name:'ssJgmc', index:'ssJgmc', width:'80', align:'center',
		      	cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'ssJgmc' + rowId + '\'';
			    }
		      }
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum %>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[10,20,50,100],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'FY_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/fydjgl!download");
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
                
                var val = jQuery("#dataList").jqGrid('getCell', cl,"fyDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var pcDjxh = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh"); 
                var pcdh = jQuery("#dataList").jqGrid('getCell', cl,"pcdh"); 
                var wsSpxh = jQuery("#dataList").jqGrid('getCell', cl,"wsSpxh");
                var ssJgbm = jQuery("#dataList").jqGrid('getCell', cl,"ssJgbm");
                var ddbh = jQuery("#dataList").jqGrid('getCell', cl,"ddbh");
                var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh"); 
                var khDjxh = jQuery("#dataList").jqGrid('getCell', cl,"khDjxh");
                var xh = jQuery("#dataList").jqGrid('getCell', cl,"xh"); 
                var wfhDjxh = jQuery("#dataList").jqGrid('getCell', cl,"wfhDjxh");
                var xybz = jQuery("#dataList").jqGrid('getCell', cl,"xybz"); 
                var input ="";
                
                var strPc="<a href=\"javascript:onViewPc("+pcDjxh+")\"><font color=\"blue\">"+pcdh+"</font></a>";
                var str="<a href=\"javascript:onViewMx("+ddDjxh+")\"><font color=\"blue\">"+ddbh+"</font></a>";
                var djztStr = "<font color=\"red\">δ�Ǽ�</font>";
                var link = "<a href=\"javascript:onUpdate('"+val+"', '" + pcDjxh + "','"+ssJgbm+"','"+ddDjxh+"','"+xh+"','"+khDjxh+"','"+wfhDjxh+"','"+xybz+"')\"><font color=\"blue\">�Ǽ�</font></a>" ;
                if (val != "") {
                	djztStr = "<font color=\"black\">�ѵǼ�</font>";
                	link = "<a href=\"javascript:onUpdate('"+val+"', '" + pcDjxh + "','"+ssJgbm+"','"+ddDjxh+"','"+xh+"','"+khDjxh+"','"+wfhDjxh+"','"+xybz+"')\"><font color=\"blue\">�Ǽ�</font></a>";
                }
                
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
                $("#dataList").jqGrid('setRowData', cl, { 'djzt': djztStr });
                $("#dataList").jqGrid('setRowData', cl, { 'ddbh': str });
                $("#dataList").jqGrid('setRowData', cl, { 'pcdh': strPc });
            }
            
		   var gridName = "dataList";
		   var a = ['pageXh','fyhj','pcdh','cyrClhm','cyrGchm','cyrSjxm','pcrMc','pcrq','pcJgmc','ssJgmc']; 		
		   Merger(gridName, 'pageXh', a);
   }
       
     /**************************************��ҳ����****************************************/
     
</script>
</head>
<body>
<s:form action="fydjgl!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.xtcsSfsp" />
	<s:hidden name="domain.xtcs20016" />
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
          				<sys:fgsList myId="mainForm_domain_pcJgbm4Query" myName="domain.pcJgbm4Query" mcContainDmBz="Y" myClass="select" onChange="bmInit(this.value, '', 'domain.djJgbm4Query', 'mainForm_domain_djJgbm4Query', 'Y', 'Y')" />
		  			</td>
		  			<td width="8%" align="right">�ɳ����ţ�</td>
          			<td width="20%">
          				<select id="mainForm_domain_djJgbm4Query" name="domain.djJgbm4Query" class="select" >
          					<option value="${domain.djJgbm4Query }" selected="selected"></option>
          				</select>
		  			</td>
	        	  <td width="8%" align="right">�ɳ����ڣ�</td>
		          <td width="23%">
		          	<sys:dateFirstDMonth myName="domain.pcrqQ" myId="mainForm_domain_pcrqQ" myClass="ymdate" />
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
		          <td align="right">�������룺</td>
		          <td><s:textfield name="domain.clhm4Query" cssClass="pop_input noborder" /></td>
	        	  <td align="right">�ɳ����ţ�</td>
		          <td><s:textfield name="domain.pcdh4Query" cssClass="pop_input noborder" /></td>
		        </tr>
		        <tr style="display: none;">
	        	  <td align="right">״̬��</td>
		          <td>
		          	<s:radio name="domain.zt" list='#{"":"����","1":"�ѵǼ�","2":"δ�Ǽ�"}' listKey="key" listValue="value"></s:radio>
		          </td>
		          <td align="right"></td>
		          <td></td>
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