<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>������ʧ</title>
<style type="text/css">
.hiddenCss {display: none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		})
		
		$("#addBtn").click(function(){
			var pcDjxh = $("#mainForm_domain_pcDjxh").val();
			var wfhDjxh =$("#mainForm_domain_wfhDjxh").val();
			var ddDjxh = $("#mainForm_domain_ddDjxh").val();
			var hwmxxh = $("#mainForm_domain_hwmxxh").val();
            var khDjxh = $("#mainForm_domain_khDjxh").val();
			onUpdate(pcDjxh,"",wfhDjxh,ddDjxh,hwmxxh,khDjxh);
		})
		//��ʼ�����
		initDataGrid();
		
		onRefresh();
		
	 })
	 function onUpdate(pcDjxh,wlDjxh,wfhDjxh,ddDjxh,hwmxxh,khDjxh){
    	//alert(pcdjxh+"--"+ddDjxh+"--"+hwmxxh+"--"+wfhDjxh)
    	var url = jcontextPath+"/hygl/wlssdj!initMx.action?domain.pcDjxh="+pcDjxh+"&domain.wlssDjxh="+wlDjxh+"&domain.wfhDjxh="+wfhDjxh
    			+"&domain.ddDjxh="+ddDjxh+"&domain.xh="+hwmxxh+"&domain.wlssLybz=0"+"&number="+Math.random();
    	window.showModalDialog(url,window,"dialogHeight:600px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
    	onRefresh();
    }  
	function onUpload(pcDjxh,wlDjxh,wfhDjxh,ddDjxh,hwmxxh,khDjxh){
	    	//alert(pcdjxh+"--"+ddDjxh+"--"+hwmxxh+"--"+wfhDjxh)
	    	var url = jcontextPath+"/hygl/wlssdjzp!initMx.action?domain.pcDjxh="+pcDjxh+"&domain.wlssDjxh="+wlDjxh+"&domain.wfhDjxh="+wfhDjxh
	    			+"&domain.ddDjxh="+ddDjxh+"&domain.xh="+hwmxxh+"&domain.wlssLybz=0"+"&number="+Math.random();
	    	window.showModalDialog(url,window,"dialogHeight:600px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
	    	onRefresh();
	}  
	function onSend(wlssDjxh,oldWsspxh){
		var wsDm="303002";//������ʧ�Ǽ�������
		//alert(wlssDjxh+"-"+oldWsspxh);
		scSend(wsDm,"",wlssDjxh,oldWsspxh);
	}
	//��д ���͹��������Ļص�  ȥ����close ����onRefresh
	function doScSendSuc(data) {
		hideMessage();
		showAlert("���ͳɹ���");
		onRefresh();
	}
	
	var dd='';
    function onDelete(djxh){
    	dd=djxh;
    	showConfirm("ȷ��Ҫɾ��ô��","toDelete");
    }
    function toDelete()
    {
    	var url=jcontextPath+"/hygl/wlssdj!delete";
    	json={"domain.wlssDjxh":dd};
    	ajaxCommon(url,json,"deletePhoto");
    }
    function okDelete(){
    	showAlert("ɾ���ɹ���");
    	onRefresh();
    }
    function deletePhoto(){
    	var url=jcontextPath+"/hygl/wlssdjzp!deletePhotoes";
    	json={"domain.wlssDjxh":dd};
    	ajaxCommon(url,json,"okDelete");
    }
  /**************************************��ҳ��ʼ****************************************/
  //ˢ�±��
	function onRefresh(){
		var xtcsSfsp = $("#mainForm_domain_xtcsSfsp").val();
		if ("Y" == xtcsSfsp) {
			$("#dataList").jqGrid('showCol',["wsspztMc"]);
		}
		var pcDjxh = $("#mainForm_domain_pcDjxh").val();
		var wfhDjxh = $("#mainForm_domain_wfhDjxh").val();
  		//����������
		var url = jcontextPath+"/wlssdj!querySsMx.action";   
		 $("#dataList").jqGrid("setGridParam",{ 
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.pcDjxh":pcDjxh,"domain.wfhDjxh":wfhDjxh}							//����Ĳ�����json��ʽ
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
			width:pageWidth()-20,  
			height:pageHeight()-90,	
		    gridComplete: myGridComplete,		//����������¼�
		    shrinkToFit:false, 
		    colNames:['���','����','������ʧ�Ǽ����','�����������','��������״̬����','���','��ʧ���','���',
		    		  '��ʧԭ��','����ʽ','������','��ʧ��Դ','��ʧ����','��ʧ����',
		    		  '��ʧ���','��ע','�Ǽǵ�λ','�޸���','�޸�ʱ��',
		    		  '������','����ʱ��'],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'pageXh', index:'pageXh', width:'35', sortable:false,align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pageXh' + rowId + '\'';
				    }
				},
			   {name:'hstoperationcol', index:'',width:'120', sortable:false,align:'center',
			   		cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'hstoperationcol' + rowId + '\'';
				    }
			   },
			   {name:'wlssDjxh', index:'wlssDjxh', width:'80', align:'center', hidden:true,
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'wlssDjxh' + rowId + '\'';
			    }
			  },
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
		      {name:'wsspztMc', index:'wsspztMc', width:'60',align:'center',
		      	cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'wsspztMc' + rowId + '\'';
			    }
		      },
			  {name:'zje', index:'zje', width:'60', sortable:false,align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'zje' + rowId + '\'';
				    }
			 },
			  
		      {name:'je', index:'je', width:'40', align:'center'},
		      {name:'wlssyy', index:'wlssyy', width:'80', align:'center'},
		      {name:'wlssclfsMc', index:'wlssclfsMc', width:'60', align:'center'},
		      {name:'zrr', index:'zrr', width:'100', align:'center'},
		      {name:'wlssLymc', index:'wlssLymc', width:'80', sortable:false,align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'wlssLymc' + rowId + '\'';
				    }
			  },
			  {name:'ssSl', index:'ssSl', width:'60', sortable:false,align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'ssSl' + rowId + '\'';
				    }
			  },
			  {name:'ssZl', index:'ssZl', width:'60', sortable:false,align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'ssZl' + rowId + '\'';
				    }
			  },
			  {name:'ssTj', index:'ssTj', width:'60', sortable:false,align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'ssTj' + rowId + '\'';
				    }
			  },
			  {name:'bz', index:'bz', width:'200', sortable:false,align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'bz' + rowId + '\'';
				    }
			  },
			  {name:'ssJgmc', index:'ssJgmc', width:'80', sortable:false,align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'ssJgmc' + rowId + '\'';
				    }
			  },
		      {name:'xgrMc', index:'xgrMc', width:'70', sortable:false,align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'xgrMc' + rowId + '\'';
				    }
			  },
			  {name:'xgrq', index:'xgrq', width:'70', sortable:false,align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'xgrq' + rowId + '\'';
				    }
			  },
			  {name:'cjrMc', index:'cjrMc', width:'70', sortable:false,align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'cjrMc' + rowId + '\'';
				    }
			  },
			  {name:'cjrq', index:'cjrq', width:'70', sortable:false,align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'cjrq' + rowId + '\'';
				    }
			  }
		     ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,						
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'PAGEXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		}
		    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����������
			function myGridComplete() {
		            var graduateIds = $("#dataList").jqGrid('getDataIDs');

		            var pcDjxh = $("#mainForm_domain_pcDjxh").val();
					var wfhDjxh = $("#mainForm_domain_wfhDjxh").val();
					var ddDjxh = $("#mainForm_domain_ddDjxh").val();
					var hwmxxh = $("#mainForm_domain_hwmxxh").val();
		            var khDjxh = $("#mainForm_domain_khDjxh").val();
		            
		            for (var i = 0; i < graduateIds.length; i++) {
		                var cl = graduateIds[i];
		                var wlssDjxh = jQuery("#dataList").jqGrid('getCell', cl,"wlssDjxh"); 	  //��ȡ��ǰ��Ԫ������Ĳ������ 
	                	var wsspztDm = jQuery("#dataList").jqGrid('getCell', cl,"wsspztDm");
	                	var wsSpxh = jQuery("#dataList").jqGrid('getCell', cl,"wsSpxh");
	                	//alert(wlssDjxh);
	                	link = "<a href=\"javascript:onUpdate('"+pcDjxh+"','" + wlssDjxh + "','"+wfhDjxh+"','"+ddDjxh+"','"+hwmxxh+"','"+khDjxh+"')\"><font color=\"blue\">�޸�</font></a>"
	                		 	+ " <a href=\"javascript:onDelete('"+wlssDjxh+"')\"><font color=\"blue\">ɾ��</font></a>"
	                		 	+ " <a href=\"javascript:onUpload('"+pcDjxh+"','" + wlssDjxh + "','"+wfhDjxh+"','"+ddDjxh+"','"+hwmxxh+"','"+khDjxh+"')\"><font color=\"blue\">��Ƭ</font></a>";
	                	var xtcsSfsp = $("#mainForm_domain_xtcsSfsp").val();
						if ("Y" == xtcsSfsp) {
							if(wsspztDm=="0"||wsspztDm=="2"){
	                		link = "<a href=\"javascript:onUpdate('"+pcDjxh+"','" + wlssDjxh + "','"+wfhDjxh+"','"+ddDjxh+"','"+hwmxxh+"','"+khDjxh+"')\"><font color=\"blue\">�޸�</font></a>"
	                		 	+ " <a href=\"javascript:onDelete('"+wlssDjxh+"')\"><font color=\"blue\">ɾ��</font></a>"
	                		 	+" <a href=\"javascript:onSend('"+wlssDjxh+"','"+wsSpxh+"')\"><font color=\"blue\">����</font></a>"
	                		 	+ " <a href=\"javascript:onUpload('"+pcDjxh+"','" + wlssDjxh + "','"+wfhDjxh+"','"+ddDjxh+"','"+hwmxxh+"','"+khDjxh+"')\"><font color=\"blue\">��Ƭ</font></a>";
	                		}
						}
		                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
		            }
		            
		            
		            
		            var gridName = "dataList";
			   		var a = ['pageXh','hstoperationcol','wlssDjxh','wsSpxh','wsspztDm','wsspztMc',
			   				 'zje','wlssLymc','ssSl','ssZl','ssTj','bz',
			   				 'ssJgmc','xgrMc','xgrq','cjrMc','cjrq'];
		 		
		       		Merger(gridName, 'pageXh', a);
		     }
		     
		     
</script>
</head>

<body>
<%try{ %>
   <s:form action="wlssdj!initSsMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
   <s:hidden name="domain.pcDjxh"></s:hidden>
   <s:hidden name="domain.wfhDjxh"></s:hidden>
   <s:hidden name="domain.ddDjxh"></s:hidden>
   <s:hidden name="domain.hwmxxh"></s:hidden>
   <s:hidden name="domain.khDjxh"></s:hidden>
   <s:hidden name="domain.xtcsSfsp"></s:hidden>
   <s:hidden name="domain.xydjBz"></s:hidden>
   	<div class="pop_contc">
   		<div  id="divQuery">
			<fieldset>
		    <legend>������Ϣ</legend>
			<table width="96%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
					<td width="14%" align="right">�ɳ����ţ�</td>
      				<td width="18%" align="left"><s:textfield name="domain.pcdh" cssClass="pop_input bgstyle_readonly"></s:textfield></td>
      				<td  align="right">�ͻ���</td>
      				<td ><s:textfield name="domain.Khmc" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
      				<td  align="right">���</td>
      				<td ><s:textfield name="domain.hwmc" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
				</tr>
				<tr>
					<td  align="right">˾����</td>
      				<td  align="left"><s:textfield name="domain.cyrSjxm" cssClass="pop_input bgstyle_readonly"></s:textfield></td>
					<td width="14%" align="right">�������룺</td>
      				<td width="18%" align="left"><s:textfield name="domain.clhm4Query" cssClass="pop_input bgstyle_readonly"></s:textfield></td>
					<td width="14%" align="right">�ҳ����룺</td>
      				<td width="18%" align="left"><s:textfield name="domain.cyrGchm" cssClass="pop_input bgstyle_readonly"></s:textfield></td>
				</tr>
				<tr>
      				<td align="right">ҵ��λ��</td>
      				<td ><s:textfield name="domain.ssJgmc" cssClass="pop_input bgstyle_readonly"></s:textfield></td>
      				<td  align="right">�ɳ��ˣ�</td>
      				<td ><s:textfield name="domain.pcrMc" cssClass="pop_input bgstyle_readonly"></s:textfield></td>
      				<td align="right">�ɳ����ڣ�</td>
      				<td><s:textfield name="domain.pcrq" cssClass="pop_input bgstyle_readonly"></s:textfield></td>
				</tr>
			</table>
			</fieldset>
			
			<div class="pop_btn">
			    <button type="button" class="pop_btnbg" id="addBtn">�� ��</button>
			    &nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
		    </div>
		   </div>
	 <!-- ��ҳ��� id����ΪdataList -->
		<table id="dataList"><tr><td /></tr></table>
		<!-- ��ҳ���� -->
		<div id="pager"></div>
    </div>
   <%@include file="/common/message.jsp" %>
   </s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>