<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>����-������ʧ�Ǽ�</title>
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
		
		$("#mainForm_domain_dwDm").change(function(){
			$("[name='domain.fhrMc']").unbind();
			initHykhData(300, $(this).val(), $("#mainForm_domain_pcbm4Query").val());
		});
		
		$("#mainForm_domain_pcbm4Query").change(function(){
			$("[name='domain.fhrMc']").unbind();
			initHykhData(300, $("#mainForm_domain_dwDm").val(), $(this).val());
		});
		
		//��ѯ��ť�¼�
		$("#queryBtn").click(function(){
			onRefresh();
		});
		
		//��������
		$("#plScSendBtn").click(function(){
			var wsDm="303002";//���õǼ�������
			plScSend(wsDm,"");
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
	
    function onUpdate(pcDjxh,wlDjxh,wfhDjxh,ddDjxh,hwmxxh,khDjxh,xybz){
    	//alert(pcdjxh+"--"+ddDjxh+"--"+hwmxxh+"--"+wfhDjxh)
    	var xtcs20016 = $("#mainForm_domain_xtcs20016").val();
		if(xtcs20016=='Y'){
			if(xybz=='N'){
				showAlert("����Э��Ǽǣ�")
				return;
			}
		}
    	var url = jcontextPath+"/hygl/wlssdj!initSsMx.action?domain.pcDjxh="+pcDjxh+"&domain.wlssDjxh="+wlDjxh+"&domain.wfhDjxh="+wfhDjxh
    			+"&domain.ddDjxh="+ddDjxh+"&domain.xh="+hwmxxh+"&domain.wlssLybz=0"+"&number="+Math.random();
    	window.showModalDialog(url,window,"dialogHeight:760px;dialogWidth:900px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
    	onRefresh();
    }
    
    function onLook(pcDjxh,wlDjxh){
    	var url = jcontextPath+"/hygl/wlssdj!toLook.action?domain.pcDjxh="+pcDjxh+"&domain.wlssDjxh="+wlDjxh;
    	window.showModalDialog(url,window,"dialogHeight:405px;dialogWidth:675px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
    	onRefresh();
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
		/*var xtcsSfsp = $("#mainForm_domain_xtcsSfsp").val();
		if ("Y" == xtcsSfsp) {
			$("#dataList").jqGrid('showCol',["wsspztMc"]);
			$("#dataList").jqGrid('showCol',["checkboxoperationcol"]);
		}*/
		
		var dwDm = $("#mainForm_domain_dwDm").val();
		var	pcbm4Query = $("#mainForm_domain_pcbm4Query").val();
		var	pcrqQ = $("#mainForm_domain_pcrqQ").val();
		var	pcrqZ = $("#mainForm_domain_pcrqZ").val();
		var fhrDjxh= $("#mainForm_domain_fhrDjxh").val(); 
		var fhrMc= $("#mainForm_domain_fhrMc").val(); 
		var	pcdh4Qyuery = $("#mainForm_domain_pcdh4Query").val();
		var	clhm4Query = $("#mainForm_domain_clhm4Query").val();
		var zt = $(":input[name='domain.zt']:checked").val();
		//����������
		var url = jcontextPath+"/hygl/wlssdjgl!query.action";   
	
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.dwDm":dwDm,"domain.pcbm4Query":pcbm4Query,"domain.pcrqQ":pcrqQ,"domain.pcrqZ":pcrqZ,
		 		"domain.fhrDjxh":fhrDjxh,"domain.fhrMc":encodeURI(fhrMc),"domain.clhm4Query":encodeURI(clhm4Query),
		 		"domain.pcdh4Query":encodeURI(pcdh4Qyuery),"domain.zt":zt}								//����Ĳ�����json��ʽ
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
		    colNames:['���','�ɳ�����','������ʧ���','<input title="ѡ��/ȡ��ѡ��" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />',
		    		'����','�ɳ��Ǽ����','״̬','�����������','��������״̬dm','���',
		    		
		    		'�������','�ص����','xybz','�ͻ�����','��������','ʼ����','Ŀ�ĵ�','ת�벿��','��������','������ַ','�ջ���ַ',//'���','��ʧԭ��',
		    		'��������','�ҳ�����','˾��','�˷�','Ԥ��',
		    		
		           '�ɳ���','�ɳ�����','�ɳ�����','ҵ��λ','δ�����Ǽ����','����','������ϸ','�ͻ�'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[
				{name:'pageXh', index:'pageXh', width:'35', sortable:false,align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pageXh' + rowId + '\'';
				    }
				},
				{name:'pcdh', index:'pcdh', width:'70', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcdh' + rowId + '\'';
				    }
			   },
			  {name:'wlssDjxh', index:'wlssDjxh', sortable:false,hidden:true, width:'80', align:'center'},
			  {name:'checkboxoperationcol', index:'checkboxoperationcol',hidden:true, sortable:false, width:'20', align:'center'},
			  {name:'hstoperationcol', index:'hstoperationcol', width:'60', align:'center'},
		   
			  {name:'pcDjxh', index:'pcDjxh', width:'80', align:'center', hidden:true},					
			  {name:'djzt', index:'djzt', width:'40', align:'center', hidden:true},
			  {name:'wsSpxh', index:'wsSpxh', width:'80', align:'center', hidden:true,
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'wsSpxh' + rowId + '\'';
			    }
			  },
			  {name:'wsspztDm', index:'wsspztDm', width:'80', align:'center', hidden:true,
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'wsspztDm' + rowId + '\'';
			    }
			  },
			  {name:'wsspztMc', index:'wsspztMc', width:'50', hidden:true,align:'center'},
			 
			   {name:'ddbh', index:'ddbh', width:'70', align:'center'},
			   {name:'hdbh', index:'hdbh', width:'70', align:'center'},
			   {name:'xybz', index:'xybz', width:'70', align:'center',hidden:true},
			   {name:'khmc', index:'khmc', width:'120', align:'center'},
			   {name:'hwMc', index:'hwMc', sortable:false, width:'100', align:'center'},
				{name:'fhrXzqhMc', index:'fhrXzqhMc', sortable:false, width:'50', align:'center'},
				{name:'shrXzqhMc', index:'shrXzqhMc', sortable:false, width:'50', align:'center'},
				{name:'zrbmMc', index:'zrbmMc', sortable:false, width:'100', align:'center'},
			    {name:'jsSl', index:'jsSl', sortable:false, width:'60', align:'right'},
				{name:'fhrDz', index:'fhrDz', sortable:false, width:'150', align:'center'},
				{name:'shrDz', index:'shrDz', sortable:false, width:'150', align:'center'},
				//{name:'je', index:'je', width:'70', align:'center'},
				//{name:'wlssyyMc', index:'wlssyyMc', width:'70', align:'center'},
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
			   {name:'yfHj', index:'yfHj', width:'50', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'yfHj' + rowId + '\'';
			    }
			  },
			  {name:'yfYfYf', index:'yfYfYf', width:'40', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'yfYfYf' + rowId + '\'';
			    }
			  },
			 
			
			    {name:'pcrMc', index:'pcrMc', width:'70', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcrMc' + rowId + '\'';
				    }
			   },
			   {name:'pcrQ', index:'pcrQ', width:'80', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcrQ' + rowId + '\'';
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
			   },
				{name:'wfhDjxh', index:'wfhDjxh', sortable:false, width:'0',hidden:true, align:'center'},
				{name:'ddDjxh', index:'ddDjxh', sortable:false, width:'0',hidden:true, align:'center'},
				{name:'hwmxxh', index:'hwmxxh', sortable:false, width:'0',hidden:true, align:'center'},
				{name:'khDjxh', index:'khDjxh', sortable:false, width:'0',hidden:true, align:'center'}
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/wlssdjgl!download");
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
                var val = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                var wlssDjxh = jQuery("#dataList").jqGrid('getCell', cl,"wlssDjxh"); 
                var wsSpxh = jQuery("#dataList").jqGrid('getCell', cl,"wsSpxh"); 
                var wfhDjxh = jQuery("#dataList").jqGrid('getCell', cl,"wfhDjxh"); 
                var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh"); 
                var hwmxxh = jQuery("#dataList").jqGrid('getCell', cl,"hwmxxh"); 
                var khDjxh = jQuery("#dataList").jqGrid('getCell', cl,"khDjxh"); 
                var ddbh = jQuery("#dataList").jqGrid('getCell', cl,"ddbh");
                var pcdh = jQuery("#dataList").jqGrid('getCell', cl,"pcdh");
                var xybz = jQuery("#dataList").jqGrid('getCell', cl,"xybz");
                var djztStr = "<font color=\"red\">δ�Ǽ�</font>";
                var strPc="<a href=\"javascript:onViewPc("+val+")\"><font color=\"blue\">"+pcdh+"</font></a>";
                var str="<a href=\"javascript:onViewMx("+ddDjxh+")\"><font color=\"blue\">"+ddbh+"</font></a>";
                var link = "<a href=\"javascript:onUpdate('"+val+"','" + wlssDjxh + "','"+wfhDjxh+"','"+ddDjxh+"','"+hwmxxh+"','"+khDjxh+"','"+xybz+"')\"><font color=\"blue\">�Ǽ�</font></a>" ;
                if (wlssDjxh != "") {
                	djztStr = "�ѵǼ�";
                	link = "<a href=\"javascript:onUpdate('"+val+"','" + wlssDjxh + "','"+wfhDjxh+"','"+ddDjxh+"','"+hwmxxh+"','"+khDjxh+"','"+xybz+"')\"><font color=\"blue\">�޸�</font></a>"
                		 	+ " <a href=\"javascript:onDelete('"+wlssDjxh+"')\"><font color=\"blue\">����</font></a>";
                }
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link });
                $("#dataList").jqGrid('setRowData', cl, { 'djzt': djztStr });
                $("#dataList").jqGrid('setRowData', cl, { 'ddbh': str });
                $("#dataList").jqGrid('setRowData', cl, { 'pcdh': strPc });
        		if ("Y" == xtcsSfsp) {
                	var wsspztDm = jQuery("#dataList").jqGrid('getCell', cl,"wsspztDm");
                	if("0"==wsspztDm || "2"==wsspztDm){
                    	input="<input type=\"checkbox\" name=\"xhs\" value=\""+wlssDjxh+"#"+wsSpxh+"\" />";
                    }else{
                    	input="<input type=\"checkbox\" name=\"xhs\" disabled=\"disabled\" value=\""+wlssDjxh+"#"+wsSpxh+"\" />";
                    }
                	$("#dataList").jqGrid('setRowData', cl, { 'checkboxoperationcol': input });
                }
            }
            var gridName = "dataList";
	   		var a = ['pageXh',
	   		         ,'yfHj','yfYfYf',
	   		         'pcdh','cyrClhm','cyrGchm','cyrSjxm','pcDjxh','pcrMc','pcrQ','pcJgmc','ssJgmc'];
 		
       		Merger(gridName, 'pcDjxh', a);
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="wlssdjgl!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
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
		           <td width="8%" align="right">�ɳ���λ��</td>
		          <td width="21%">
		          	<sys:gsList myId="mainForm_domain_dwDm" myName="domain.dwDm" mcContainDmBz="Y" myClass="select" onChange="bmInit(this.value, '', 'domain.pcbm4Query', 'mainForm_domain_pcbm4Query', 'Y', 'Y')"/>
		          </td>
		          <td width="8%" align="right" >�ɳ����ţ�</td>
		          <td width="21%"><sys:gsBmList myName="domain.pcbm4Query" myId="mainForm_domain_pcbm4Query"  contaisQxz="false" myClass="select" /></td>
		          <td width="8%" align="right">�ɳ����ڣ�</td>
		          <td width="25%">
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
		        	<td align="right">�ɳ����ţ�</td>
		         	<td><s:textfield name="domain.pcdh4Query" cssClass="pop_input noborder" /></td>
		        	<td align="right">�������룺</td>
		        	<td><s:textfield name="domain.clhm4Query" cssClass="pop_input noborder" /></td>	
		        </tr>
		        <tr>
	        	  <td align="right" style="display: none;">״̬��</td>
		          <td style="display: none;">
		          	<s:radio name="domain.zt" list='#{"":"����","1":"�ѵǼ�","2":"δ�Ǽ�"}' listKey="key" listValue="value"></s:radio>
		          </td>
		          <td align="right"></td>
		          <td></td>
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
