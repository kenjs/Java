<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>����-�ɳ���Ϣ����</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
			document.getElementById("maincont").onmousewheel=hideHelpWindow;
			
			initHykhData(300);
			$("#mainForm_domain_pcJgbm").change(function(){
				initRy();
			});
		
			//��ѯ��ť�¼�
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//������ť�¼�
			$("#addBtn").click(function(){
				
			});
			//��ʼ�����
			initDataGrid();
			initRy();	
			var sjJgbm = $("#mainForm_domain_ssJgbm").val();
			bmInit(sjJgbm, '', "domain.pcJgbm", "mainForm_domain_pcJgbm", "Y", "Y");
	});
	
	function initRy() {
		var sj = $("#mainForm_domain_pcJgbm").val();
		commonInit("BMYH", sj, '', "domain.pcrCzyDjxh", "mainForm_domain_pcrCzyDjxh", "Y", false);
	}

    function onUpdate(ddDjxh){
    	var url = jcontextPath+"/hyzpaj!initMx.action?domain.pcDjxh="+ddDjxh;
    	window.showModalDialog(url,window,"dialogHeight:780px;dialogWidth:820px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
    	onRefresh();
    	//popwindow(jcontextPath+"/hygl/hytydgl!initMx?domain.ddDjxh="+ddDjxh);
    }
    
    function onViewPc(pcDjxh) {
		var url = jcontextPath+"/jcgl/jcpcxxgl!initMx.action?domain.pcDjxh="+pcDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    	//window.open(url);
	}
    
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		sj = $("#mainForm_domain_ssJgbm").val();
		bm = $("#mainForm_domain_pcJgbm").val();
		var bz='';
		if(bm!=null&&bm!=''){
			sj=bm;
			bz='B';
		}
		else{
			bz='D';
		}
		djrCzyDjxh4Query = $("#mainForm_domain_pcrCzyDjxh").val();
		//fhrDjxh4Query
		rqq = $("#mainForm_domain_pcrqq").val();
		//shrDjxh4Query
		rqz = $("#mainForm_domain_pcrqz").val(); 
		if(rqq>rqz){
			showError("�ɳ���ʼ���ڲ��ܴ����ɳ���ֹ���ڣ�");
			return;
		}
		fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		clhm = $("#mainForm_domain_clhm4Query").val();
		sjxm = $("#mainForm_domain_sjXm").val();
		pcdh = $("#mainForm_domain_pcDh").val();
		fhrMc = $("#mainForm_domain_fhrMc").val();
	
		
		//����������
		var url = jcontextPath+"/hygl/hyzpaj!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.pcJgbm":sj,"domain.bz":bz, "domain.pcrCzyDjxh":encodeURI(djrCzyDjxh4Query),"domain.pcrqq":rqq,"domain.fhrDjxh":fhrDjxh,
		 			"domain.pcrqz":rqz, "domain.clHm":encodeURI(clhm),"domain.sjXm":encodeURI(sjxm),"domain.pcDh":encodeURI(pcdh)
		 			,"domain.fhrMc":encodeURI(fhrMc)
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
		    rownumbers : true,					//�����
			width:pageWidth()-10,  
			height:pageTableHeight()-90,	
		    gridComplete: myGridComplete,		//����������¼�
		    shrinkToFit:false, 
		    colNames:['����', '״̬','�ɳ�����','�ɳ�����', '��������', '��������', '�ҳ�����', 
		              '˾������', '�ֻ�����', '������ϵ�绰', '�ɳ���','������λ','��������','ID' 
		              ],			 //name ����ʾ������
		     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'50', align:'center'},
			  {name:'ajCs', index:'ajCs', width:'60', align:'center'}, 
			  {name:'pcDh', index:'pcDh', width:'90', align:'center'}, 
		      {name:'pcrq', index:'pcrq', width:'80',align:'center'}, 
		      {name:'czxm', index:'czxm', width:'80', align:'center'}, 
			  {name:'clHm', index:'clHm', width:'80', align:'center'},
			  
			  {name:'gcHm', index:'gcHm', width:'90', align:'center'}, 
			  {name:'sjXm', index:'sjXm', width:'80', align:'center'}, 
		      {name:'sjHm', index:'sjHm', width:'100', align:'center'}, 
		      {name:'dianhua', index:'dianhua', width:'100', align:'center'},
		      {name:'pcrXm', index:'pcrXm', width:'80', align:'center'},
		      {name:'sjMc', index:'sjMc', width:'90', align:'center'}, 
		      {name:'bmMc', index:'bmMc', width:'90', align:'center'}, 
		      {name:'pcDjxh', index:'pcDjxh', width:'0', hidden:true, align:'center'}
		      
		    ],
		    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
		    rowNum: <%=rowNum%>,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
		    rowList:[20,50,100,300,500],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
		    sortname: 'DD_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/hyzpaj!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
               var val = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh"); 
                var ajCs = jQuery("#dataList").jqGrid('getCell', cl,"ajCs"); 
                var pcDjxh = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh"); 
                var pcdh = jQuery("#dataList").jqGrid('getCell', cl, "pcDh");
                var pcStr="<a href=\"javascript:onViewPc("+pcDjxh+")\"><font color=\"blue\">"+pcdh+"</font></a>";
				var link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">�Ǽ�</font></a>";
         
               	if(ajCs==0){
               		var zt="<font color=\"red\">δ����</font>";
               	}
               	else{
               		var zt="<font color=\"black\">�Ѱ���</font>";
               	}
                $("#dataList").jqGrid('setRowData', cl, { 'pcDh': pcStr }); 
               	 $("#dataList").jqGrid('setRowData', cl, { 'ajCs': zt }); 
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
	
    //��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������

       
   	
     /**************************************��ҳ����****************************************/

</script>
</head>
<body>
<s:form action="hytydgl!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="dropDownData"></s:hidden>
	<s:hidden name="jsonData" />
	
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
	<div style="display: none;" id="maincont"></div>
	<div class="right_cont">
	  <div id="divQuery">
		<fieldset>
			<legend>��ѯ����</legend>
		   <table width="99%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		        	<td width="8%" align="right">ҵ��λ��</td>
          			<td width="21%">
          				<sys:gsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y" myClass="select" onChange="bmInit(this.value, '', 'domain.pcJgbm', 'mainForm_domain_pcJgbm', 'Y', 'Y')" />
		  			</td>
		  			<td width="8%" align="right">�ɳ����ţ�</td>
          			<td width="21%">
          				<select id="mainForm_domain_pcJgbm" name="domain.pcJgbm" class="select" >
          					<option value="${domain.pcJgbm }" selected="selected"></option>
          				</select>
		  			</td>
	        	  <td width="8%" align="right">�ɳ��ˣ�</td>
		          <td width="25%">
		          		<select name="domain.pcrCzyDjxh" id="mainForm_domain_pcrCzyDjxh" class="select" />
	          	  </td>
	          	</tr>
		        <tr>
		        	<td align="right">�ͻ����ƣ�</td>
		          <td>
		          	<s:hidden name="domain.fhrDjxh"></s:hidden>
  					<div class="inputsel" style="width: 230px; ">
  						<s:textfield name="domain.fhrMc" cssClass="pop_input noborder inputext" cssStyle="width:200px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="fhr" onFocus="this.blur()"></a>
  					</div>
			  		<div class="inputsc">
		              <div id="inputSel_fhr" class="inputselcont inputselFixedSize ac_results">
		              </div>
		            </div>
		          </td>
		          <td align="right">�ɳ����ţ�</td>
		          <td><s:textfield name="domain.pcDh" cssClass="pop_input noborder" /></td>
		          <td width="8%" align="right">�ɳ����ڣ�</td>
		          <td width="21%">
		          <sys:dateFirstDLastMonthTag myName="domain.pcrqq" myId="mainForm_domain_pcrqq" myClass="ymdate" />
	          			��
	          		<sys:dateCurrentDayTag myName="domain.pcrqz" myId="mainForm_domain_pcrqz" myClass="ymdate" />
		          </td>
		          <td colspan="4"></td>
			    </tr>
		        <tr>
		        	 <td align="right">˾��������</td>
		          <td><s:textfield name="domain.sjXm" cssClass="pop_input noborder" /></td>
	        	  <td align="right">�������룺</td>
		          	 <td><s:textfield name="domain.clhm4Query" cssClass="pop_input noborder" /></td>
		          
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
