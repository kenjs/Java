<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>��ҵ-��������</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>

<script type="text/javascript">
	$(document).ready(function(){
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			$("#saveBtn").click(function(){
				if(check()){
					showAlert("����ѡ����������ģʽ����");
					return;
				}
				var cb = $("input[name='opera']");
				var wd = $("input[name='wsDm']");
				var jb = $("input[name='jgbm']");
				var wpd = $("[name='wsspms']");
				var zt = "";
				var wsDm = "";
				var jgbm = "";
				var wsspmsDm = "";
				var strObj = "";
				for(var i=0;i<cb.length;i++){
					if(cb[i].checked){
						zt = "Y";
					}else{
						zt = "N";
					}
					wsDm = wd[i].value;
					jgbm = jb[i].value;
					wsspmsDm = wpd[i].value;
					strObj += zt+","+wsspmsDm+","+jgbm+","+wsDm+"|";
				}
				if(strObj == ""){
					return;
				}
				var url = jcontextPath+"/dzgl/qyspws!save";
				var jsonObj = {"domain.strObj":strObj};				
				ajaxCommon(url,jsonObj,"doSuccess");
			});
			
			//��ʼ�����
			initDataGrid();
			
			var ywflDm = $("#mainForm_domain_ywflDm").val();
			if(ywflDm != "" || ywflDm != null || ywflDm != "0"){
				commonInit('ywhj',ywflDm ,'' , 'domain.ywhjDm', 'mainForm_domain_ywhjDm', 'Y', 'Y')
			}
	});
	
	function check(){
		var num = 0;
		var cb = $("input[name='opera']");
		var wpd = $("[name='wsspms']");
		for(var i=0;i<cb.length;i++){
			if(cb[i].checked && wpd[i].value == ""){
				++ num;
			}			
		}
		if(num>0){
			return true;
		}else{
			return false;
		}
	}
	
	function doSuccess(){
		hideMessage();
		showAlert("����ɹ���","refresh");
	}
	
	function refresh(){
		onRefresh();
	}
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var ywflDm = $("#mainForm_domain_ywflDm").val();
		var ywhjDm = $("#mainForm_domain_ywhjDm").val();  
		//����������
		var url = jcontextPath+"/dzgl/qyspws!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ywflDm":ywflDm,"domain.ywhjDm":ywhjDm}								//����Ĳ�����json��ʽ
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
		    colNames:['����','�������','ҵ�����','ҵ�񻷽�','��������','������','��������ģʽ','','��λ','��Ŀ����','������','��������','�޸���','�޸�����','˵��','','','',''],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
		      {name:'hstoperationcol', index:'', sortable:false, width:'40', align:'center'},
		      {name:'wsDm',index:'wsDm', width:'80', align:'center'}, 
		      {name:'ywflMc', index:'ywflMc', width:'80', align:'center'}, 
		      {name:'ywhjMc', index:'ywhjMc', width:'80', align:'center'}, 
		      {name:'wsMc', index:'wsMc', width:'120', align:'center'}, 
		      {name:'wsJc', index:'wsJc', width:'120', align:'center'}, 
		      {name:'wsspms',index:'',width:'150',align:'center'},
		      {name:'wsspmsDm',index:'wsspmsDm',width:'100',align:'center',hidden:true},
		      
		      {name:'dwMc', index:'dwMc', width:'120', align:'center'},
		      {name:'flbz', index:'flbz', width:'80', align:'center'}, 
		      
		      {name:'cjrMc', index:'cjrMc', width:'80', align:'center'},
		      {name:'cjrq', index:'cjrq', width:'80', align:'center'},
		      {name:'xgrMc', index:'xgrMc', width:'80', align:'center'},
		      {name:'xgrq', index:'xgrq', width:'80', align:'center'},
		      {name:'sm', index:'sm', width:'280', align:'center'},
		      
		      {name:'jgbm', index:'jgbm', width:'0', align:'center',hidden:true},
		      {name:'fzh1', index:'', width:'0', align:'center',hidden:true},
		      {name:'fzh2', index:'', width:'0', align:'center',hidden:true},
		      {name:'fzh3', index:'', width:'0', align:'center',hidden:true}
		     
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'JGBM',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/dzgl/qyspws!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val1 = jQuery("#dataList").jqGrid('getCell', cl,"jgbm"); 
				var val2 = jQuery("#dataList").jqGrid('getCell', cl,"wsDm");	  //��ȡ��ǰ��Ԫ��������������
				var val3 = jQuery("#dataList").jqGrid('getCell', cl,"wsspmsDm");
				var link = "";
				if(val1 != ""){
					link = "<input type='checkbox' name='opera' checked='checked' />";
				}else{
					link = "<input type='checkbox' name='opera' />";
				}
				var link2 = "<input type='hidden' name='wsDm' value='"+val2+"'/>";
				var link3 = "<input type='hidden' name='wsspmsDm' value='"+val3+"'/>";
				var link4 = "<input type='hidden' name='jgbm' value='"+val1+"'/>";
				var link5 = $("#wsspmsDiv").html();
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
                $("#dataList").jqGrid('setRowData', cl, { 'fzh1': link2 }); 
                $("#dataList").jqGrid('setRowData', cl, { 'fzh2': link3 }); 
                $("#dataList").jqGrid('setRowData', cl, { 'fzh3': link4 }); 
                $("#dataList").jqGrid('setRowData', cl, { 'wsspms': link5 }); 
                $("[name='wsspms']")[i].value = val3;
            }
     }
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="qyspws!query" namespace="/dzgl" method="post" id="mainForm" name="mainForm">
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">������</li>
		    <li><a href="#" id="queryBtn" class="licon04">�� ��</a></li>
		    <li><a href="#" id="saveBtn" class="licon06">�� ��</a></li>
		    <li><a href="#" id="closeBtn" class="licon03">�� ��</a></li>
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
		      		<table width="65%" border="0" cellspacing="0" cellpadding="0">
		        		<tr>
		          			<td width="15%" align="right">ҵ����ࣺ</td>
		          			<td width="35%">
		          				<sys:Ywfl myName="domain.ywflDm" myId="mainForm_domain_ywflDm" contaisQxz="true" mcContainDmBz="Y"  myClass="select" onChange="commonInit('ywhj', this.value,'' , 'domain.ywhjDm', 'mainForm_domain_ywhjDm', 'Y', 'Y')"/>
				  			</td>
				  			<td width="100" align="right">ҵ�񻷽ڣ�</td>
		          			<td width="350">
		          				<select id="mainForm_domain_ywhjDm" name="domain.ywhjDm" class="select"></select>
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
	<div id="wsspmsDiv" style="display: none;">
		<sys:Wsspms myName="wsspms" contaisQxz="true" mcContainDmBz="Y"  myClass="select" />
	</div>
</body>

</html>
