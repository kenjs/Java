<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>����Ԥ��</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){	
		initDataGrid4View();
		onQueryPzHwxx4View();
		
		$("#saveBtn").click(function(){
			window.dialogArguments.doSave();
			window.close();
		});
		
		$("#closeBtn").click(function(){
			window.close();
		});
	});
	
	function getAutoGridHeight(length) {
		var heightT = 260;
	    if (length <= 2) {
	    	heightT = 2 * 25 + 15;
	    }else if (length <= 10) {
	    	heightT = length * 25 + 15;
	    }
	    
	    return heightT;
	}
	
	function onQueryPzHwxx4View(){
		 var pzDjxh = $("#mainForm_domain_pzDjxh").val();
		 var pchwLsxh = $("#mainForm_domain_pchwLsxh").val();
		 
		//����������
		var url = jcontextPath+"/hygl/ddpzxxgl!queryPz4View.action";   
		 $("#pzHwxxList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.pzDjxh":pzDjxh,"domain.pchwLsxh":pchwLsxh}
		 	//����Ĳ�����json��ʽ
		 }
		 ).trigger("reloadGrid");		//��ʾ����һҳ�������������ݷ����仯��ʱ�򣬷�ҳ����Ϣ����
	}
	
	//jqGrid  ��ʼ�����
	function initDataGrid4View(){ 
	  $("#pzHwxxList").jqGrid({
	    url:"",
	    datatype: 'local',
	    mtype: 'POST',
	    rownumbers : true,					//�����
		width:pageWidth()-10,  
		height:pageTableHeight()-90,	
	    gridComplete: myGridComplete,		//����������¼�
	    shrinkToFit:false, 
	    colNames:['��������','ʼ����','Ŀ�ĵ�','��װ','���','����','����','���','����','����','�ص���','�ջ���','�ջ���ַ','��������',
	    		'������ַ','�Ǽ���','�Ǽ�����','�Ǽǲ���','��������'],			 //name ����ʾ������
	     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
	    colModel :[
	      {name:'hwmc', index:'hwmc', width:'100', sortable:false, align:'center'}, 
	      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'40', sortable:false, align:'center'}, 
	      {name:'shrXzqhMc', index:'shrXzqhMc', width:'40', sortable:false, align:'center'},
	      {name:'bz', index:'bz', width:'30', sortable:false, align:'center'},
	      {name:'lb', index:'lb', width:'40', sortable:false, align:'center'},
	      {name:'hwSl', index:'hwSl', width:'40', sortable:false, align:'center'},
	      {name:'hwZl', index:'hwZl', width:'40', sortable:false, align:'center'}, 
	      {name:'hwTj', index:'hwTj', width:'40', sortable:false, align:'center'},	      
	      {name:'srHj', index:'srHj', width:'45', sortable:false, align:'center'},
	      {name:'df', index:'df', width:'45', sortable:false, align:'center'},
	      
	      {name:'hdbh', index:'hdbh', width:'70', sortable:false, align:'right'},
	      {name:'shrMc', index:'shrMc', width:'60', sortable:false, align:'center'}, 
	      {name:'shDz', index:'shDz', width:'100', sortable:false, align:'center'}, 
	      {name:'fhRq', index:'fhRq', width:'70', sortable:false, align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}, 
	      {name:'fhrDz', index:'fhrDz', width:'100', sortable:false, align:'center'},
	      {name:'djrMc', index:'djrMc', width:'70', sortable:false, align:'center'},
		  {name:'djRq', index:'djRq', width:'70', sortable:false, align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		  {name:'djJgmc', index:'djJgmc', width:'100', sortable:false, align:'center'},
		  {name:'ssJgmc', index:'ssJgmc', width:'100', sortable:false, align:'center'}
	    ],
	    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
	    rowNum: -1,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
	    rowList:[-1],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
	    sortname: 'DD_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
	    sortorder: 'DESC',				//Ĭ��������
	    viewrecords: true,					//�����Ƿ�Ҫ��ʾ�ܼ�¼��
	    gridview: false,					//Ĭ��false ����һ�����ݺ���ӵ�grid�У������Ϊtrue���ǽ������������ݶ�������ɺ�����ӵ�grid�У���treeGrid, subGrid, or afterInsertRow ������	
	    jsonReader: {     
	 	 	root: 	 "domain.pzHwxxList",   				// �����У�Ĭ��Ϊ��rows��
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
	  jQuery("#pzHwxxList").jqGrid('navGrid','#pager',
	 		 {edit:false,add:false,del:false}
	  );
	  
	}
	
	//��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
	    var graduateIds = $("#pzHwxxList").jqGrid('getDataIDs');
	    var heightT = getAutoGridHeight(graduateIds.length);
	    $("#pzHwxxList").setGridHeight(heightT);
	}		
</script>
</head>

<body>
<%try{ %>
<s:form action="ddpzxxgl!viewPzXx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
		<s:hidden name="domain.pzDjxh"></s:hidden>
		<s:hidden name="domain.pchwLsxh"></s:hidden>
		<div class="pop_contc">
			<fieldset>
				<legend>������Ϣ</legend>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
					<tr>
	      				<td width="15%" align="right">��վ��</td>
	      				<td width="35%">
	      					<s:textfield name="domain.hzmc" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
	      				</td>
	      				<td width="15%" align="right">�����ͺţ�</td>
	      				<td width="35%" id="hzfsTd">
	      				 	<s:textfield name="domain.clxh" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
	      				</td>
	      			</tr>
	      			<tr>
	      				<td align="right">���س��أ�</td>
	      				<td>
	      					<s:textfield name="domain.pzCz" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
	      				</td>
	      				<td width="15%" align="right">���������</td>
	      				<td width="35%">
	      					<s:textfield name="domain.pzTj" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>	      				
	      				</td>	      				
	      			</tr>
					<tr>
						<td align="right">�������룺</td>
	      				<td>
	      					<s:textfield name="domain.pzsr" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
	      				</td>
	      				<td align="right"></td>
	      				<td>
	      				</td>
	      			</tr>	      	
				</table>
			</fieldset>
			<br />
			<h2>��ѡ������Ϣ��</h2>
			<table id="pzHwxxList"><tr><td/></tr></table>
			<div class="pop_btn">
			 	<button type="button" class="pop_btnbg" id="saveBtn">�� ��</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">�� ��</button>
		    </div>
	</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
