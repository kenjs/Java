<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<html>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>�ص��嵥���</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript">
      $(document).ready(function(){
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			$("#dbfsBtn").click(function(){
				//alert($("#mainForm_domain_jsGsbm").val());
				if(!checkdata()){
					return;
				}
				var xhs = $(":checked[name='xhs']");
				if(xhs.length<=0){
					showAlert("��ѡ��Ҫ����Ļص���")
					return;
				}else{
					showConfirm("һ��"+xhs.length+"�Żص���ȷ�ϴ��������ô��","doDbfs")
				}
			});
			//��ʼ�����
			initDataGrid();

	});	
	function onViewPc(pcDjxh){
    	var url = jcontextPath+"/jcgl/jcpcxxgl!initMx.action?domain.pcDjxh="+pcDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    }
    function doDbfs(){
    	var jsGsbm = $("#mainForm_domain_jsGsbm").val();
    	var fsGsbm = $("#mainForm_domain_ssJgbm").val();
    	
    	var qdmc = $("#mainForm_domain_qdmc").val();
    	var bz = $("#mainForm_domain_bz").val();
    	var xhs = $(":checked[name='xhs']");
    	
    	var jsonStr = getJqueryParam(xhs,"domain.hdDjxhs");
		var jsonObj = {"domain.jsGsbm":jsGsbm,"domain.fsGsbm":fsGsbm,"domain.qdmc":encodeURI(qdmc),"domain.bz":encodeURI(bz)};
		jsonStr += jQuery.param(jsonObj);
		var url = jcontextPath+"/hygl/hypchwxxhdqd!dbfs";  
        //alert(jsonStr);
        ajaxCommon(url,encodeURI(jsonStr),"fsOk", false);
    }
    function fsOk(){
    	onRefresh();
    }
    function checkdata(){
		var controlNameArray = ["domain.qdmc","domain.jsGsbm","domain.bz"];
		var labelNameArray = ["�嵥����","���յ�λ","��ע"];
		var compareValueArray = [100,16,500];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
  /**************************************��ҳ��ʼ****************************************/
  //ˢ�±��
	function onRefresh(){
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var rqQ = $("#mainForm_domain_rqQ").val();
		var rqZ = $("#mainForm_domain_rqZ").val(); 
  		//����������
		var url = jcontextPath+"/hypchwxxhdqd!query.action";   
		 $("#dataList").jqGrid("setGridParam",{ 
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.rqQ":rqQ,"domain.rqZ":rqZ}								//����Ĳ�����json��ʽ
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
		    colNames:['<input title="ѡ��/ȡ��ѡ��" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />', '�ص��Ǽ����','�ɳ��Ǽ����','δ�����Ǽ����','�����Ǽ����','������ϸ���',
				     '�ص����','ʵװ����','ʵװ����','ʵװ���','�ص���������','Ҫ�󵽴�����','�ջ���ʽ����',
				     'ʵװ_��������',
				     '�ɳ�����','�ɳ�����','�ɳ���ʽ', 'װ����ʽ','��������','�ҳ�����',
				     '˾��','�˷Ѻϼ�','Ԥ���˷�', '��ע','�ɳ���','�ɳ�����','ҵ��˾'
				     
				    ],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'40', align:'center'},
		      {name:'hdDjxh', index:'hdDjxh', width:'100', align:'center',hidden:true}, 
		      {name:'pcDjxh', index:'pcDjxh', width:'100', align:'center',hidden:true}, 
		      {name:'wfhDjxh', index:'wfhDjxh', width:'100', align:'center',hidden:true}, 
		      {name:'ddDjxh', index:'ddDjxh', width:'100', align:'center',hidden:true}, 
		      {name:'xh', index:'xh', width:'100', align:'center',hidden:true},
		      {name:'hdbh', index:'hdbh', width:'60', align:'center'}, 
		      
		      {name:'szHwSl', index:'szHwSl', width:'60', align:'center'}, 
		      {name:'szHwZl', index:'szHwZl', width:'60', align:'center'}, 
		      {name:'szHwTj', index:'szHwTj', width:'60', align:'center'}, 
		      {name:'hdjsrq', index:'hdjsrq', width:'80', align:'center'},
		      {name:'yqDdrq', index:'yqDdrq', width:'80', align:'center'},
		      {name:'shfsDm', index:'shfsDm', width:'100', align:'center',hidden:true}, 
		      {name:'szJsSl', index:'szJsSl', width:'100', align:'center',hidden:true},
		      {name:'pcdh', index:'pcdh', width:'70', align:'center'},
		      
		      
		      {name:'pcrq', index:'pcrq', width:'80', align:'center'},
		      {name:'pcfsMc', index:'pcfsMc', width:'60', align:'center'},
		      {name:'zcfsMc', index:'zcfsMc', width:'60', align:'center'},
		      {name:'cyrClhm', index:'cyrClhm', width:'70', align:'center'},
		      {name:'cyrGchm', index:'cyrGchm', width:'70', align:'center'},
		      
		      {name:'cyrSjxm', index:'cyrSjxm', width:'100', align:'center'},
		      {name:'yfHj', index:'yfHj', width:'100', align:'center'},
		      {name:'yfYfyf', index:'yfYfyf', width:'100', align:'center'},
		      {name:'bz', index:'bz', width:'150', align:'center'},
		      {name:'pcrMc', index:'pcrMc', width:'100', align:'center'},
		      {name:'pcJgmc', index:'pcJgmc', width:'100', align:'center'},
		      {name:'ssJgmc', index:'ssJgmc', width:'100', align:'center'}	      		      
		      
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,						
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'HD_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		 
		    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����������
			function myGridComplete() {
		            var graduateIds = $("#dataList").jqGrid('getDataIDs');
		            for (var i = 0; i < graduateIds.length; i++) {
		                var cl = graduateIds[i];
		                var val = jQuery("#dataList").jqGrid('getCell', cl,"hdDjxh"); 	  //��ȡ��ǰ��Ԫ������Ĳ������ 
		                var link = '<input type="checkbox" name="xhs" value="'+val+'" />';
		                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link });
		                var pcDjxh = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh");
		                var pcdh = jQuery("#dataList").jqGrid('getCell', cl,"pcdh");
                		var strPc="<a href=\"javascript:onViewPc("+pcDjxh+")\"><font color=\"blue\">"+pcdh+"</font></a>";
                		$("#dataList").jqGrid('setRowData', cl, { 'pcdh': strPc });
		            }
		     }
	}
</script>
</head>
<body>
<s:form action="hypchwxxhdqd!queryQd" theme="simple" namespace="cwgl" method="post" id="mainForm" name="mainForm">
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

	<div class="right_cont">
		<div id="divQuery">
			<fieldset>
				<legend>�ص����</legend>
				<table width="99%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="10%" align="right">�嵥���ƣ�</td>
						<td width="22%" >
						 	<s:textfield name="domain.qdmc" cssClass="inputext pop_input noborder bgstyle_required"></s:textfield>
						</td>
						<td width="10%" align="right">���յ�λ��</td>
						<td width="28%">
							 <sys:fgsList myId="mainForm_domain_jsGsbm" myName="domain.jsGsbm" myClass="select" contaisDq="N"></sys:fgsList>
						</td>
						<td width="29%" align="center" colspan="2">
							<button type="button" class="pop_btnbg" id="dbfsBtn">�������</button>
						</td> 
					</tr>
					<tr>
      				<td align="right">��ע��</td>
      				<td colspan="5">
      					<s:textarea name="domain.bz" rows="3" cssClass="pop_textarea_colspan2" ></s:textarea>
      				</td>
      			</tr>	   
				</table>
			</fieldset>
			<fieldset>
				<legend>��ѯ����</legend>
				<table width="99%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="10%" align="right">��λ��</td> 
						<td width="22%">  
						   <sys:gsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y"  myClass="select"/>
		  			    </td>
		  			    <td  align="right" width="10%">���ڣ�</td>
		                <td width="32%">
		                  	<sys:dateFirstDMonth myName="domain.rqQ" myId="mainForm_domain_rqQ" myClass="ymdate" />
			          		��
			          		<sys:dateCurrentDayTag myName="domain.rqZ" myId="mainForm_domain_rqZ" myClass="ymdate" />
		                </td>  
						<td width="26%" colspan="2"></td>     
		            </tr>
				</table>
			</fieldset>
		</div>
	
		<!-- ��ҳ��� id����ΪdataList -->
		<table id="dataList"><tr><td /></tr></table>
		<!-- ��ҳ���� -->
		<div id="pager"></div>
	</div>
</s:form>
</body>
