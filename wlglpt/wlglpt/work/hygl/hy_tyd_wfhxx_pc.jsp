<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>�ɳ���Ϣ����-ѡ�����</title>
<style type="text/css">
.hiddenCss {display: none;}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		/***********************��ʼ��dropdownInputSel����begin************************/
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		initXzqhData(200);
		initHykhData(300);
		initXzqhInputSel();
		/***********************��ʼ��dropdownInputSel����end************************/
		
		//��ѯ��ť�¼�
		$("#queryBtn").click(function(){
			onRefresh();
		});
		
		//��ʼ�����
		initWfhDataGrid();
		onRefresh();
		
		$("#yesBtn").click(function(){
			checkSaveWfhxx4Pc();
		});
		
	});
	
	function checkSaveWfhxx4Pc() {
		var checks = $(":input:checked[name='wfhXhs']");
		if (checks.length <= 0) {
			showAlert("����ѡ����Ҫ�ɳ��Ļ��");
			return;
		}
		
		if (!checkZrDzdata()) {
			return;
		}
		
		if ($("#mainForm_domain_zrbmDm").val() == "3" || $("#sfjsId")[0].checked) {
			if (!checkZc()) {
				return;
			}
			var xhs = $(":input:checked[name='wfhXhs']");
			if(xhs.length!=1){
				showAlert("ת�ְ���һ��ֻ�����һ�����");
				return;
			}
			var hk = $("#mainForm_domain_pcHwxxDomain_zcHk").val();
			if (hk/1 > 0) {
				showConfirm("��֧�����в����ؿۣ��Ƿ�ȷ����ӻ��","doSaveWfhxx4Pc");
			}else {
				doSaveWfhxx4Pc();
			}
		}else {
			doSaveWfhxx4Pc();
		}
		
	}
	
	function doSaveWfhxx4Pc() {
		var wfhXhs = getCheckedWfhXhs();
		saveWfhxx4Pc(wfhXhs);
	}
	
	function checkZc() {
		var hk = $("#mainForm_domain_pcHwxxDomain_zcHk").val();
		if (hk/1 == 0) {
			if (($("#mainForm_domain_pcHwxxDomain_zcXf").val()/1+$("#zcDf").val()/1+$("#mainForm_domain_pcHwxxDomain_zcHf").val()/1)+$("#mainForm_domain_pcHwxxDomain_zcYj").val()/1 != $("#mainForm_domain_pcHwxxDomain_zcHj").val()/1) {
				alert("֧��¼������֧�������㹫ʽ��\"��֧��=�ָ�\+����\+�½�\"�����飡");
				return false;
			}			
		}
		return true;
	}
	
	function getCheckedWfhXhs() {
		var checks = $(":input:checked[name='wfhXhs']");
		
		var xhs = "";
		$.each(checks, function(i, obj){
			xhs += obj.value + ",";
		});
		if (xhs.length > 0) {
			xhs = xhs.substring(0, xhs.length-1);
		}
		
		return xhs;
	}
	
	function setDf(obj,isAllClick) {
		if (!($("#mainForm_domain_zrbmDm").val() == "3" || $("#sfjsId")[0].checked)) {
			return;
		}
		var graduateIds = $("#wfhList").jqGrid('getDataIDs');
		if (isAllClick) {
			var checks = $("[name='wfhXhs']:checked");
			var dfHj = 0.0;
			$.each(checks, function(i, obj){
				var rowIndex = $(obj).parents("tr").index() - 1;
				if (rowIndex >= 0) {
					var df = jQuery("#wfhList").jqGrid('getCell', graduateIds[rowIndex],"df")/1;
					if (df != "") {
						dfHj += parseFloat(df);
					}
				}
			});
			$("#zcDf").val(dfHj.toFixed(2));
			calHk();
		}else {
			var rowIndex = $(obj).parents("tr").index() - 1;
			if (rowIndex >= 0) {
				var df = jQuery("#wfhList").jqGrid('getCell', graduateIds[rowIndex],"df")/1;
				if (df != "") {
					if (obj.checked) {
						$("#zcDf").val(($("#zcDf").val()/1 + parseFloat(df)).toFixed(2));
					}else {
						$("#zcDf").val(($("#zcDf").val()/1 - parseFloat(df)).toFixed(2));
					}
					calHk();
				}
			}
		}
		
	}

	function onViewMx(ddDjxh) {
		var url = jcontextPath+"/jcgl/jctydgl!initMx.action?domain.ddDjxh="+ddDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    	//window.open(url);
	}
	
	// ��дbase.js�е�setGridAuto����
	function setGridAuto(){  
	    var gridTabWidth=pageWidth()-10;  	//ȥ����ȵļ��㣬ֻ�����߶�
	    $("#dataList").setGridWidth(gridTabWidth);  
	    $("#wfhList").setGridWidth(gridTabWidth);  
	} 
	
	function getAutoGridHeight(length) {
		var heightT = 260;
	    if (length <= 2) {
	    	heightT = 2 * 25 + 15;
	    }else if (length <= 10) {
	    	heightT = length * 25 + 15;
	    }
	    
	    return heightT;
	}
	
	/**************************************��ҳ��ʼ****************************************/
	//ˢ�µ�ǰҳ��
	function onRefresh(){
		var pcfsDm = $("#mainForm_domain_pcfsDm").val();
		if (pcfsDm == undefined) {
			pcfsDm = "";
		}
		if (pcfsDm == "1") {
			$("#wfhList").jqGrid('hideCol',["srHj","df"]);
		}
		
		 dw4Query = $("#mainForm_domain_dw4Query").val();
		 fhrXzqhDm = $("#mainForm_domain_fhrXzqhDm").val();
		 shrXzqhDm = $("#mainForm_domain_shrXzqhDm").val();
		 ddbh4Query = $("#mainForm_domain_ddbh4Query").val();
		 fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		 fhrMc = $("#mainForm_domain_fhrMc").val();
		 hwztDm4Query = '';//$("#mainForm_domain_hwztDm4Query").val();
		 lb4Query = $("#mainForm_domain_lb4Query").val();
		 djJgbm4Query = $("#mainForm_domain_djJgbm4Query").val();
		 djJgbm4Query = djJgbm4Query == null ? "" : djJgbm4Query;
		 fhrqQ = '';//$("#mainForm_domain_fhrqQ").val();
		 fhrqZ = '';//$("#mainForm_domain_fhrqZ").val();
		 var pcfsDm = $("#mainForm_domain_pcfsDm").val();
		 var pchwLsxh = $("#mainForm_domain_pchwLsxh").val();
		 var pcDjxh = $("#mainForm_domain_pcDjxh").val();
		//����������
		var url = jcontextPath+"/hygl/hypcxxgl!queryWfhxx.action";   
		 $("#wfhList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.dw4Query":encodeURI(dw4Query),"domain.fhrXzqhDm":encodeURI(fhrXzqhDm),"domain.shrXzqhDm":encodeURI(shrXzqhDm),
		 			"domain.ddbh4Query":ddbh4Query,"domain.fhrDjxh":fhrDjxh,"domain.fhrMc":encodeURI(fhrMc),
		 			"domain.hwztDm4Query":hwztDm4Query,"domain.lb4Query":encodeURI(lb4Query),"domain.djJgbm4Query":djJgbm4Query,
		 			"domain.fhrqQ":fhrqQ,"domain.fhrqZ":fhrqZ,"domain.pcfsDm":pcfsDm,
		 			"domain.pchwLsxh":pchwLsxh,"domain.pcDjxh":pcDjxh}
		 			//����Ĳ�����json��ʽ
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
	function initWfhDataGrid(){ 
	  $("#wfhList").jqGrid({
	    url:"",
	    datatype: 'local',
	    mtype: 'POST',
	    rownumbers : false,					//�����
		width:pageWidth()-20,  
		height:pageTableHeight()-90,	
	    gridComplete: myWfhGridComplete,		//����������¼�
	    shrinkToFit:false, 
	    colNames:['���','�����Ǽ����','����˳���','δ�����Ǽ����', '�������','�ͻ�����','<input title="ѡ��/ȡ��ѡ��" type="checkbox" onclick="checkAllJGridChk(event,this,\'wfhXhs\');setDf(null,true);" />',
	    		  '��������','ʼ����','Ŀ�ĵ�','��װ','���','��������','����','����','���','����','����','�����','������',
	    		  'shfsDm','�ͻ���ʽ','�ջ���','�ջ���ַ','Ҫ�󷢻�����','Ҫ�󵽴�����','״̬',
	    		  '������ַ','�Ǽ���','�Ǽ�����','�Ǽǲ���','��������'],			 //name ����ʾ������
	     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
	    colModel :[ 
		  {name:'pageXh', index:'pageXh', sortable:false, width:'30', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'pageXhWfh' + rowId + '\'';
		    }
		  },
		  {name:'ddDjxh', index:'ddDjxh', hidden:true,width:'60', align:'center'},
		  {name:'xh', index:'xh', width:'100', hidden:true, align:'center'}, 
		  {name:'wfhDjxh', index:'wfhDjxh', hidden:true, width:'100', align:'center'},
		  {name:'ddbh', index:'ddbh', width:'70', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'ddbhWfh' + rowId + '\'';
		    }
		  },
		  {name:'fhrMc', index:'fhrMc', width:'120', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'fhrMcWfh' + rowId + '\'';
		    }
		  },
		  {name:'hstoperationcol', index:'hstoperationcol', width:'25', align:'center'},
	      {name:'hwmc', index:'hwmc', width:'100', align:'center'}, 
	      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'40', align:'center'}, 
	      {name:'shrXzqhMc', index:'shrXzqhMc', width:'40', align:'center'}, 
	      {name:'bz', index:'bz', width:'50', align:'center'}, 
	      {name:'lb', index:'lb', width:'40', align:'center'},
	      {name:'jssl', index:'jssl',hidden:true, width:'50', align:'right'},
	      {name:'sl', index:'sl', width:'50', align:'right'}, 
	      {name:'zl', index:'zl', width:'50', align:'right'}, 
	      {name:'tj', index:'tj', width:'50', align:'right'}, 
	      {name:'srHj', index:'srHj', width:'45', align:'center'},
	      {name:'df', index:'df', width:'45', align:'center'},
	      {name:'thf', index:'thf', width:'45',hidden:true, align:'center'},
	      {name:'hdf', index:'hdf', width:'45',hidden:true, align:'center'},
	      {name:'shfsDm', index:'shfsDm', width:'60',hidden:true, align:'center'},
	      {name:'shfsMc', index:'shfsMc', width:'60', align:'center'},
	      {name:'shrMc', index:'shrMc', width:'60', align:'center'}, 
	      {name:'shDz', index:'shDz', width:'100', align:'center'}, 
	      {name:'fhRq', index:'fhRq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
	      {name:'yqDdrq', index:'yqDdrq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
	      {name:'hwztMc', index:'hwztMc', width:'40', align:'center'},
	      {name:'fhrDz', index:'fhrDz', width:'100', align:'center'},
	      {name:'djrMc', index:'djrMc', width:'70', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'djrMcWfh' + rowId + '\'';
		    }
		  },
		  {name:'djRq', index:'djRq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'djRqWfh' + rowId + '\'';
		    }
		  },
		  {name:'djJgmc', index:'djJgmc', width:'100', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'djJgmcWfh' + rowId + '\'';
		    }
		  },
		  {name:'ssJgmc', index:'ssJgmc', width:'100', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'ssJgmcWfh' + rowId + '\'';
		    }
		  }
	    ],
	    pager: '#pager',					//���巭ҳ�õĵ���������������Ч��htmlԪ�ء���ҳ���������Է�����htmlҳ������λ��
	    rowNum: -1,							//��grid����ʾ��¼���������������Ҫ�����ݵ���̨
	    rowList:[-1],		//һ������ѡ��������ı���ʾ��¼������ѡ��ʱ�Ḳ��rowNum�������ݵ���̨
	    sortname: 'DD_DJXH',				//Ĭ������ �����������ƻ�����һ�����֣���������ᱻ�ύ����̨
	    sortorder: 'DESC',				//Ĭ��������
	    viewrecords: true,					//�����Ƿ�Ҫ��ʾ�ܼ�¼��
	    gridview: false,					//Ĭ��false ����һ�����ݺ���ӵ�grid�У������Ϊtrue���ǽ������������ݶ�������ɺ�����ӵ�grid�У���treeGrid, subGrid, or afterInsertRow ������	
	    jsonReader: {     
	 	 	root: 	 "domain.wfhList",   				// �����У�Ĭ��Ϊ��rows��
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
	  jQuery("#wfhList").jqGrid('navGrid','#pager',
	 		 {edit:false,add:false,del:false}
	  );
	}
	
	//��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myWfhGridComplete() {
	    var graduateIds = $("#wfhList").jqGrid('getDataIDs');
	    
	    var heightT = getAutoGridHeight(graduateIds.length);
	    $("#wfhList").setGridHeight(heightT);
	    
	    for (var i = 0; i < graduateIds.length; i++) {
	        var cl = graduateIds[i];
	        var wfhDjxh = jQuery("#wfhList").jqGrid('getCell', cl,"wfhDjxh");
	        var ddbh = jQuery("#wfhList").jqGrid('getCell', cl,"ddbh");
	        var ddDjxh = jQuery("#wfhList").jqGrid('getCell', cl,"ddDjxh");
	        //var link = "<input type=\"checkbox\" onclick=\"setDf(this)\" name=\"wfhXhs\" value=\""+wfhDjxh+"\" />";
	        var link = "<input type=\"checkbox\" onclick=\"setDf(this)\" name=\"wfhXhs\" value=\""+wfhDjxh+"\" />";
	        var str="<a href=\"javascript:onViewMx("+ddDjxh+")\"><font color=\"blue\">"+ddbh+"</font></a>";
	        $("#wfhList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
	        $("#wfhList").jqGrid('setRowData', cl, { 'ddbh': str }); 
	    }
	    
	   var gridNameWfh = "wfhList";
		   var aWfh = ['pageXhWfh','ddbhWfh','fhrMcWfh','djRqWfh','djrMcWfh','djJgmcWfh','ssJgmcWfh'];
		   var cellNames = ['pageXh','ddbh','fhrMc','djRq','djrMc','djJgmc','ssJgmc'];
			
	    Merger(gridNameWfh, 'ddDjxh', aWfh,cellNames);
	}
/**************************************��ҳ����****************************************/
</script>
</head>
<body>
	<s:hidden name="domain.dw4Query" cssClass="reserveText"></s:hidden>
	<s:hidden name="jsonData" cssClass="reserveText" />
	<s:hidden name="fhrData" cssClass="reserveText" />
	
	<div class="right_cont">
	 <div id="divQuery">
	 	<fieldset>
			<legend>δ������ѯ</legend>
		   <table width="98%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
	  				<td width="6%" class="td_noborder"></td>
	  				<td width="10%" class="td_noborder"></td>
	  				<td width="8%" class="td_noborder"></td>
	  				<td width="8%" class="td_noborder"></td>
	  				<td width="8%" class="td_noborder"></td>
	  				<td width="8%" class="td_noborder"></td>
	  				<td width="6%" class="td_noborder"></td>
	  				<td width="8%" class="td_noborder"></td>
	  				<td width="6%" class="td_noborder"></td>
	  				<td width="15%" class="td_noborder"></td>
	  				<td width="9%" class="td_noborder"></td>
	  				<td width="8%" class="td_noborder"></td>
	  			</tr>
		        <tr>
		          <td align="right">���ţ�</td>
		          <td>
		          	<sys:bmList myId="mainForm_domain_djJgbm4Query" myName="domain.djJgbm4Query" sjJgbm="domain.dw4Query" contaisQxz="true" myClass="select"></sys:bmList>
		          </td>
		          <td align="right">ʼ���أ�</td>
		          <td>
		          	<s:hidden name="domain.fhrXzqhDm" />
  					<div class="inputsel" style="width: 60px;">
  						<s:textfield name="domain.fhrXzqhMc" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width: 30px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="xzqh" xzqh="fhrXzqh" onFocus="this.blur()"></a>
		            </div>
		          </td>
		          <td align="right">Ŀ�ĵأ�</td>
		          <td>
		          	<s:hidden name="domain.shrXzqhDm"></s:hidden>
  					<div class="inputsel" style="width: 60px;">
  						<s:textfield name="domain.shrXzqhMc" cssClass="inputext pop_input noborder bgstyle_optional" cssStyle="width: 30px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="xzqh" xzqh="shrXzqh" onFocus="this.blur()"></a>
		            </div>
		          </td>
		          <td align="right">���</td>
		          <td align="right">
		          	<sys:Hwfl myName="domain.lb4Query" myId="mainForm_domain_lb4Query" contaisQxz="true" myClass="select"></sys:Hwfl>
		          </td>
		          <td align="right">�ͻ���</td>
		          <td>
		          	<s:hidden name="domain.fhrDjxh"></s:hidden>
  					<div class="inputsel" style="width: 120px; ">
  						<s:textfield name="domain.fhrMc" cssClass="pop_input noborder inputext" cssStyle="width:90px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="fhr" onFocus="this.blur()"></a>
  					</div>
			  		<div class="inputsc">
		              <div id="inputSel_fhr" class="inputselcont inputselFixedSize ac_results">
		              </div>
		            </div>
		          </td>
		          <td align="right">������ţ�</td>
		          <td><s:textfield name="domain.ddbh4Query" cssClass="pop_input noborder" /></td>
		        </tr>
		   </table>
		  </fieldset>
	  </div>
	    <table id="wfhList"><tr><td/></tr></table> 
		<!-- ��ҳ���� -->
		<%@include file="/common/message.jsp" %>
	</div>
	<div id="inputSel_xzqh" class="inputselcont" style="position: absolute; top: 159px; left: 94px; display: none;" >
		                
      <iframe src="" scrolling="no" frameborder="0" style="position:absolute; top:0; left:0; z-index:-1; width:274px; height:100px;"></iframe>
    </div>
</body>
</html>
