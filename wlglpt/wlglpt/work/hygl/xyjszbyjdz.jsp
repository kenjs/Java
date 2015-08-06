<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>���ν���-ת��-�½����</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//��������
			$("#plDzBtn").click(function(){
				//alert(0);
				var checkedVal = new Array();
				var pldz = $(":checked[name='pldz']");
				if (pldz.length <= 0) {
					showAlert("��ѡ����Ҫ�������˵ļ�¼��");
					return;
				}
				var dzCheckBoxs = $(":checkbox[name='pldz']:checked:visible:enabled");
				$.each(dzCheckBoxs,function(i){
					checkedVal[i] = $(dzCheckBoxs[i]).val();
				});
				var url = jcontextPath+"/hygl/xyjszbyjdz!plDz";	
				var jsonObj = {"domain.pldzStr":checkedVal};
				ajaxCommon(url,jsonObj,"plDzSuccess");
			});
			
			//������������
			$("#plScSendBtn").click(function(){
				var wsDm="306001"; //�ְ����½���˱�
				plScSend(wsDm,"");
			});
			
			$("#dyylBtn").click(function(){
				var ssJgbm = $("#mainForm_domain_ssJgbm").val();
				var zrbmDm = $("#mainForm_domain_zrbmDm").val();
				var zrbmDjxh = $("#mainForm_domain_zrbmDjxh").val();
				var pcrqQ = $("#mainForm_domain_pcrqQ").val();
				var pcrqZ = $("#mainForm_domain_pcrqZ").val();
				var dzbz = trim($(":checked[name='domain.dzbz']").val());
				
				var url = jcontextPath+"/hygl/xyjszbyjdz!dyyl?domain.ssJgbm="+ssJgbm+"&domain.zrbmDm="+zrbmDm+
					"&domain.zrbmDjxh="+zrbmDjxh+"&domain.pcrqQ="+pcrqQ+"&domain.pcrqZ="+pcrqZ+"&domain.dzbz="+dzbz;
				window.open(url,"_blank")
			});
			
			//��ʼ�����
			initDataGrid();
	});
	
	function plDzSuccess(){
		showAlert("�������˳ɹ���");
        onRefresh();
	}

    function onUpdate(dzDjxh){
    	var url = jcontextPath+"/hygl/xyjszbyjdz!initMx?domain.dzDjxh="+dzDjxh;
    	window.showModalDialog(url, window,"dialogHeight:180px;dialogWidth:470px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;");
    	//popwindow(jcontextPath+"/hygl/xyjszbyjdz!initMx?domain.dzDjxh="+dzDjxh);
    	onRefresh();
    }
    
    var keyValue = "";
	function onDelete( dzDjxh){
		keyValue = dzDjxh;
		showConfirm("ȷ��Ҫɾ��ô��","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.dzDjxh":keyValue};
		 var url = jcontextPath+"/hygl/xyjszbyjdz!delete";   
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
		var zrbmDm = $("#mainForm_domain_zrbmDm").val();
		var zrbmDjxh = $("#mainForm_domain_zrbmDjxh").val();
		var pcrqQ = $("#mainForm_domain_pcrqQ").val();
		var pcrqZ = $("#mainForm_domain_pcrqZ").val();
		var dzbz = trim($(":checked[name='domain.dzbz']").val());
		//����������
		var url = jcontextPath+"/hygl/xyjszbyjdz!query";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.zrbmDm":zrbmDm,
		 		"domain.zrbmDjxh":zrbmDjxh,"domain.pcrqQ":pcrqQ,"domain.pcrqZ":pcrqZ,"domain.dzbz":dzbz} //����Ĳ�����json��ʽ
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
		    colNames:['����<input title="ȫ/��ѡ" type="checkbox" onclick="checkAllJGridChk(event,this,\'pldz\');" />',
		    		'����<input title="ȫ/��ѡ" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />',
		    		'','','','����','״̬','��������','�ɳ�����','�ɳ�����','��������','�ְ������','�ְ���',
				    '�½�','���˽��','������','��������','���ʲ���','�Ƿ����','������'/*,'�Ƿ���Ҫ����','����״̬','�������'*/],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  /*{name:'pageXh', index:'pageXh', sortable:false, width:'35', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pageXh' + rowId + '\'';
			    }
			  },*/
			  {name:'pldz', index:'pldz', sortable:false, width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pldz' + rowId + '\'';
			    }
			  },
			  {name:'plsp', index:'plsp', sortable:false, width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'plsp' + rowId + '\'';
			    }
			  },
			  {name:'dzDjxh', index:'dzDjxh',hidden:true, align:'center'},
			  {name:'dzbz', index:'dzbz',hidden:true, align:'center'},
			  {name:'wsspztDm', index:'wsspztDm',hidden:true, align:'center'},
		      {name:'dz', index:'', width:'60', align:'center'},
		      {name:'status', index:'', width:'60', align:'center'},
		      {name:'jgmc', index:'jgmc', width:'130', align:'center'},
		      {name:'pcdh', index:'pcdh', width:'100', align:'center'}, 
		      
		      {name:'pcrq', index:'pcrq', width:'100', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}, 
		      {name:'hwmc', index:'hwmc', width:'100', align:'center'}, 
		      {name:'zrbmmc', index:'zrbmmc', width:'100', align:'center'},
		      {name:'fbsmc', index:'fbsmc', width:'100', align:'center'}, 
		      {name:'jsYj', index:'jsYj', width:'100', align:'center'}, 

		      {name:'dzje', index:'dzje', width:'100', align:'center'},
		      {name:'dzrmc', index:'dzrmc', width:'100', align:'center'},
		      {name:'dzrq', index:'dzrq', width:'100', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}, 
		      {name:'dzjgmc', index:'dzjgmc', width:'100', align:'center'},

		      {name:'dzCybz', index:'dzCybz', width:'100', align:'center'}, 
		      {name:'dzcyje', index:'dzcyje', width:'100', align:'center'}, 
		      /*{name:'spbz', index:'spbz', width:'100', align:'center'}, 
		      {name:'wsspztDm', index:'wsspztDm', width:'100', align:'center'}, 
		      {name:'wsSpxh', index:'wsSpxh', width:'100', align:'center'}*/
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'DZ_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/xyjszbyjdz!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"dzDjxh"); 	  //��ȡ��ǰ��Ԫ������ĵǼ���� 
                //var link = "<a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">ɾ��</font></a>";
                //$("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link });
                var dzlink="��",pldzlink="",plsplink="",stslink="";
                var dzbz = jQuery("#dataList").jqGrid('getCell', cl,"dzbz");
                var wsspztDm = jQuery("#dataList").jqGrid('getCell', cl,"wsspztDm");
                if(dzbz=="N"){//δ����
                	dzlink = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">����</font></a>";
                	stslink = "<font color=\"red\">δ����</font>";
                	pldzlink = "<input type=\"checkbox\" name=\"pldz\" value=\""+val+"\" />";
                	plsplink = "<input type=\"checkbox\" name=\"xhs\" disabled=\"disabled\" value=\""+val+"\" />";
                }else if(dzbz=="Y"){//�Ѷ���
                	stslink = "<font color=\"red\">�Ѷ���</font>";
                	plsplink = "<input type=\"checkbox\" name=\"xhs\" value=\""+val+"\" />";
                	if(${domain.sfsp=="Y"}){//��Ҫ����
                		pldzlink = "<input type=\"checkbox\" name=\"pldz\" value=\""+val+"\" />";
                		dzlink = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">�Ѷ���</font></a>";
                		if(wsspztDm==1){//�ѷ�������
                			plsplink = "<input type=\"checkbox\" name=\"xhs\" disabled=\"disabled\" value=\""+val+"\" />";
                			dzlink = "�Ѷ���";
                			pldzlink = "<input type=\"checkbox\" name=\"pldz\" disabled=\"disabled\" value=\""+val+"\" />";
                			stslink = "<font color=\"red\">������</font>";
                		}else if(wsspztDm==2){//�˻�
                			plsplink = "<input type=\"checkbox\" name=\"xhs\" value=\""+val+"\" />";
                			stslink = "<font color=\"red\">���˻�</font>";
                		}else if(wsspztDm==3){//����
                			plsplink = "<input type=\"checkbox\" name=\"xhs\" disabled=\"disabled\" value=\""+val+"\" />";
                			dzlink = "�Ѷ���";
                			pldzlink = "<input type=\"checkbox\" name=\"pldz\" disabled=\"disabled\" value=\""+val+"\" />";
                			stslink = "<font color=\"red\">������</font>";
                		}else if(wsspztDm==4){//����Ҫ����ʱ�Ѷ��ˣ��Ѿ����ɲ�����Ϣ�������޸�
                			dzlink = "�Ѷ���";
                			plsplink = "<input type=\"checkbox\" name=\"xhs\" disabled=\"disabled\" value=\""+val+"\" />";
                			pldzlink = "<input type=\"checkbox\" name=\"pldz\" disabled=\"disabled\" value=\""+val+"\" />";
                			stslink = "<font color=\"red\">�Ѷ���</font>";
                		}
                	}else if(${domain.sfsp=="N"}){//����Ҫ����
                		dzlink = "�Ѷ���";
                		stslink = "<font color=\"red\">�Ѷ���</font>";
	                	pldzlink = "<input type=\"checkbox\" name=\"pldz\" disabled=\"disabled\" value=\""+val+"\" />";
                	}
                }
                $("#dataList").jqGrid('setRowData', cl, { 'dz': dzlink });
                var dzCybz = jQuery("#dataList").jqGrid('getCell', cl,"dzCybz");
                var cybz = "";
                if(dzCybz=="Y"){
                	cybz = "��";
                }else if(dzCybz=="N"){
                	cybz = "��"
                }
                $("#dataList").jqGrid('setRowData', cl, { 'dzCybz': cybz });
                //��������
                $("#dataList").jqGrid('setRowData', cl, { 'pldz': pldzlink });
                //��������
                $("#dataList").jqGrid('setRowData', cl, { 'plsp': plsplink });
                //��������
                $("#dataList").jqGrid('setRowData', cl, { 'status': stslink });
            }
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="hygl/xyjszbyjdz!query" namespace="" method="post" id="mainForm" name="mainForm">
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">������</li>
		    <li><a href="#" id="queryBtn" class="licon04">�� ��</a></li>
		    <li><a href="#" id="plScSendBtn" class="licon10">������������</a></li>
		    <li><a href="#" id="plDzBtn" class="licon10">��������</a></li>
		    <li><a href="#" id="dyylBtn" class="licon10">�½��˵�</a></li>
		    <li><a href="#" id="closeBtn" class="licon03">�ر�</a></li>
	  	</ul>
	    <ul class="rcont">
		    <li class="ricon02" onclick="slideToggle('syquery')">��ʾ/���ز�ѯ����</li>
		    <li class="ricon03">����</li>
	  	</ul>
	</div> 

	<div class="right_cont">
		<div id="divQuery">
	<fieldset><legend>��ѯ����</legend>
	<table width="99%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="8%" align="right">ҵ��λ��</td>
			<td width="25%">
				<sys:gsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y" myClass="select" onChange="initList()" /></td>
			<td width="8%" align="right">�ְ������ͣ�</td>
			<td width="21%">
				<sys:Zrbm myId="mainForm_domain_zrbmDm" myName="domain.zrbmDm" contaisQxz="true" myClass="select"></sys:Zrbm>
			</td>
			<td width="8%" align="right">���Σ�</td>
			<td width="21%">
				<sys:xyzbList myId="mainForm_domain_zrbmDjxh" myName="domain.zrbmDjxh" contaisQxz="true" contaisDq="N" myClass="select"></sys:xyzbList>
			</td>
		</tr>
		<tr>
			<td align="right">�ɳ����ڣ�</td>
			<td>
				<sys:dateFirstDMonth myName="domain.pcrqQ" myId="mainForm_domain_pcrqQ" myClass="ymdate"></sys:dateFirstDMonth>
				 �� 
				<sys:dateCurrentDayTag myName="domain.pcrqZ" myId="mainForm_domain_pcrqZ" myClass="ymdate"></sys:dateCurrentDayTag>
			</td>
			<td align="right">����״̬��</td>
			<td colspan="3">
				<s:radio id="mainForm_domain_dzbz" name="domain.dzbz" list="#{'':'����','Y':'�Ѷ���','N':'δ����'}"></s:radio>
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
