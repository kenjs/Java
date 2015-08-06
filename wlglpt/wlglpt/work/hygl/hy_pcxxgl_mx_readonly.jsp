<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>�ɳ���Ϣ����</title>

<style type="text/css">
html,body {background:none;}
.hiddenCss {display: none;}
</style>

<script type="text/javascript">
$(function(){
	initDataGrid();
	
	onQueryPcHwxx();
	
	$("#closeBtn").click(function(){
		window.close();			
	});
	
	$("select").attr("disabled", true);
	$(":text").attr("readonly", true);
});

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
	function onQueryPcHwxx(){
		var pcfsDm = $("#mainForm_domain_pcfsDm").val();
		if (pcfsDm == "1") {
			$("#dataList").jqGrid('hideCol',["srHj"]);
		}
		 
		var pcDjxh = $("#mainForm_domain_pcDjxh").val();
		var pchwLsxh = $("#mainForm_domain_pchwLsxh").val();
		//����������
		var url = jcontextPath+"/hygl/hypcxxgl!queryPcHwxx.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.pcDjxh":pcDjxh,"domain.pchwLsxh":pchwLsxh}
		 	//����Ĳ�����json��ʽ
		 }
		 ).trigger("reloadGrid");		//��ʾ����һҳ�������������ݷ����仯��ʱ�򣬷�ҳ����Ϣ����
	}
	
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
	    colNames:['�����Ǽ����','pchwLsxh','pcDjxh','�������','������','<input title="ѡ��/ȡ��ѡ��" type="checkbox" onclick="checkAllJGridChk(event,this,\'hwXh4PcDel\');" />',
	            '��������','ʼ����','Ŀ�ĵ�','δ�������input','δ�������',
	            '��װ','���','����','�������','����','���','����','����','���','����','����','����','�ͻ���ʽ',
	            'ת��','�ϼ�','�ָ�','�½�','����','��Ϣ��',
	    		'�ص���','�ջ���','�ջ���ַ','��������','��������',
	    		'������ַ','�Ǽ���','�Ǽ�����','�Ǽǲ���','��������'],			 //name ����ʾ������
	     //name ��Ӧ������������������  index ���������������������õ������� width �п�ȣ�align ���뷽ʽ��sortable   �Ƿ��������
	    colModel :[
		  {name:'ddDjxh', index:'ddDjxh', hidden:true, width:'70', align:'center'},
		  {name:'pchwLsxh', index:'pchwLsxh', hidden:true, width:'70', align:'center'},
		  {name:'pcDjxh', index:'pcDjxh', hidden:true, width:'70', align:'center'},
		  {name:'ddbh', index:'ddbh', width:'70', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'ddbh' + rowId + '\'';
		    }
		  },
		  {name:'fhrMc', index:'fhrMc', width:'130', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'fhrMc' + rowId + '\'';
		    }
		  },
		  {name:'hstoperationcol', index:'hstoperationcol', sortable:false, width:'30', align:'center'},
	       
	      {name:'hwmc', index:'hwmc', width:'100', sortable:false, align:'center'}, 
	      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'40', sortable:false, align:'center'}, 
	      {name:'shrXzqhMc', index:'shrXzqhMc', width:'40', sortable:false, align:'center'},
	      {name:'xh', index:'xh', width:'100', hidden:true, align:'center'},
	      {name:'wfhDjxh', index:'wfhDjxh', width:'100', hidden:true, align:'center'},
	      {name:'bz', index:'bz', width:'30', sortable:false, align:'center'},
	      {name:'lb', index:'lb', width:'40', sortable:false, align:'center'},
	      {name:'sl', index:'sl', width:'50', sortable:false, align:'right'},
	      {name:'kcsl', index:'kcsl', width:'50', sortable:false, align:'right'},
	      {name:'zl', index:'zl', width:'50', sortable:false, align:'right'}, 
	      {name:'tj', index:'tj', width:'50', sortable:false, align:'right'},
	      {name:'hwSl', index:'hwSl', width:'30', hidden:true, sortable:false, align:'right'},
	      {name:'hwZl', index:'hwZl', width:'30', hidden:true, sortable:false, align:'right'}, 
	      {name:'hwTj', index:'hwTj', width:'30', hidden:true, sortable:false, align:'right'},
	      {name:'srHj', index:'srHj', width:'45', sortable:false, align:'center'},
	      {name:'df', index:'df', width:'45', sortable:false, align:'center'},
	      {name:'zrbmMc', index:'zrbmMc', width:'80', sortable:false, align:'center'},
	      {name:'shfsMc', index:'shfsMc', width:'60', sortable:false, align:'center'},
	      {name:'pchwClfsDm', index:'pchwClfsDm', width:'40', align:'center',hidden:true},
	      {name:'zcHj', index:'zcHj', width:'40', sortable:false,align:'center'},
		  {name:'zcXf', index:'zcXf', width:'40', sortable:false,align:'center'},
		  {name:'zcYj', index:'zcYj', width:'40', sortable:false,align:'center'},
		  {name:'zcDf', index:'zcDf', width:'40', sortable:false,align:'center'},
		  {name:'zcHk', index:'zcHk', width:'40', sortable:false,align:'center'},
		  
	      {name:'hdbh', index:'hdbh', width:'70', sortable:false, align:'right'},
	      {name:'shrMc', index:'shrMc', width:'60', sortable:false, align:'center'}, 
	      {name:'shDz', index:'shDz', width:'100', sortable:false, align:'center'}, 
	      {name:'fhRq', index:'fhRq', width:'70', sortable:false, align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}, 
	      {name:'yqDdrq', index:'yqDdrq', width:'70', sortable:false, align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
	      {name:'fhrDz', index:'fhrDz', width:'100', sortable:false, align:'center'},
	      {name:'djrMc', index:'djrMc', width:'70', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'djrMc' + rowId + '\'';
		    }
		  },
		  {name:'djRq', index:'djRq', width:'70', sortable:false, align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'djRq' + rowId + '\'';
		    }
		  },
		  {name:'djJgmc', index:'djJgmc', width:'100', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'djJgmc' + rowId + '\'';
		    }
		  },
		  {name:'ssJgmc', index:'ssJgmc', width:'100', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'ssJgmc' + rowId + '\'';
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
	 	 	root: 	 "domain.pcHwxxList",   				// �����У�Ĭ��Ϊ��rows��
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
	
	//��������Ϻ󴥷��¼����޸Ĳ����е����ݣ���ʾ���޸ġ�����ɾ����������������
	function myGridComplete() {
	    var graduateIds = $("#dataList").jqGrid('getDataIDs');
	    var pcfsDm = $("#mainForm_domain_pcfsDm").val();
	    
	    var heightT = getAutoGridHeight(graduateIds.length);
	    $("#dataList").setGridHeight(heightT);
	    
	    var srHjs = 0.00;
	    var zls = 0.00;
	    var tjs = 0.00;
	    for (var i = 0; i < graduateIds.length; i++) {
	        var cl = graduateIds[i];
	        var pchwClfsDm = jQuery("#dataList").jqGrid('getCell', cl,"pchwClfsDm");
	        
	        var shStr = "";
	        if (pchwClfsDm == "21" || pchwClfsDm == "31" ) {
	        	shStr = "ֱ��";
	        }
	        $("#dataList").jqGrid('setRowData', cl, { 'pchwClfsDm': shStr });
	        
	        var hj = jQuery("#dataList").jqGrid('getCell', cl,"srHj");
	        var hwzl = jQuery("#dataList").jqGrid('getCell', cl,"hwZl");
	        var hwtj = jQuery("#dataList").jqGrid('getCell', cl,"hwTj");
	        // ���û������
	        if (hj != "" && pcfsDm != "1") {
	        	srHjs += parseFloat(hj);
	        }
	        if (hwzl != "") {
	        	zls += parseFloat(hwzl);
	        }
	        if (hwtj != "") {
	        	tjs += parseFloat(hwtj);
	        }
	       
	    }
	    $("#mainForm_domain_zl").val(zls.toFixed(2));
	    $("#mainForm_domain_tj").val(tjs.toFixed(2));
	    $("#mainForm_domain_srHj").val(srHjs.toFixed(2));
	    
	   var gridName = "dataList";
		   var a = ['ddbh','fhrMc','djRq','djrMc','djJgmc','ssJgmc'];
			
	    Merger(gridName, 'ddDjxh', a);
	}
/**************************************��ҳ����****************************************/
	
</script>
</head>

<body>
<%try{ %>
<s:form action="hypcxxgl!initViewMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.pchwLsxh" />
	<s:hidden name="domain.pcDjxh" />
	<s:hidden name="domain.pcfsDm" />
	<s:hidden name="domain.yfjsfDm" />
	<s:hidden name="domain.cyrClhmXh" />
	<s:hidden name="domain.cyrGchmXh" />
	
	<div class="pop_contc">
		<div id="divQuery">
			<fieldset>
			<legend>�ɳ���Ϣ</legend>
			<table width="98%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="10%" align="right">�ɳ����ţ�</td>
      				<td width="13%">
      					<s:if test="domain.xtcs20004==0">
      						<s:textfield name="domain.pcdh" cssClass="pop_input noborder inputext" ></s:textfield>
      					</s:if>
      					<s:else>
	  						<s:textfield name="domain.pcdh" cssClass="pop_input noborder" readonly="true"></s:textfield>
  						</s:else>
      				</td>
  					<td width="8%" align="right">������</td>
      				<td width="12%">
      					<s:textfield name="domain.zl" cssClass="pop_input noborder inputext"></s:textfield>
      				</td>
      				<td width="8%" align="right">�����</td>
      				<td width="11%">
      					<s:textfield name="domain.tj" cssClass="pop_input noborder inputext"></s:textfield>
      				</td>
      				<td width="8%" align="right">���룺</td>
      				<td width="10%">
      					<s:textfield name="domain.srHj" cssClass="pop_input noborder inputext"></s:textfield>
      				</td>
      				<td width="10%" align="right"></td>
      				<td width="10%">
      				</td>
      			</tr>
    			<tr>
    				<td align="right">������</td>
    				<td>
    					<select name="domain.clsxDm" id="mainForm_domain_clsxDm" onchange="changeClxx();" class="select">
    						<option value="1"  <s:if test="domain.clsxDm==1">selected="selected"</s:if>>��Ӫ����</option>
    						<option value="2" <s:if test="domain.clsxDm==2">selected="selected"</s:if>>��ᳵ��</option>
    					</select>
    				</td>
    				<td align="right">���룺</td>
    				<td>
 						<s:textfield name="domain.cyrClhm" cssClass="pop_input noborder inputext"></s:textfield>
    				</td>
    				<td align="right">�ҳ���</td>
    				<td>
 						<s:textfield name="domain.cyrGchm" cssClass="pop_input noborder inputext"></s:textfield>
    				</td>
    				<td align="right">������</td>
    				<td>
    					<s:textfield name="domain.cyrCzxm" cssClass="pop_input noborder" />
    				</td>
    				<td align="right">��վ���ڣ�</td>
    				<td>
    					<input type="text" name="domain.dzrq" id="mainForm_domain_dzrq" value="<s:date name="domain.dzrq" format="yyyy-MM-dd" />" class="pop_input noborder"  />
    				</td>
    			</tr>
    			<tr>
    				<td align="right">˾����</td>
    				<td>
    					<s:textfield name="domain.cyrSjxm" cssClass="pop_input noborder" ></s:textfield>
    				</td>
    				<td align="right">���֤��</td>
    				<td>
    					<s:textfield name="domain.cyrSjsfz" cssClass="pop_input noborder" ></s:textfield>
    				</td>
    				<td align="right">�ֻ���</td>
    				<td>
    					<s:textfield name="domain.cyrSjsjhm" cssClass="pop_input noborder" ></s:textfield>
    				</td>
    				<td align="right">�绰1��</td>
    				<td>
    					<s:textfield name="domain.cyrQtlxdh" cssClass="pop_input noborder" />
    				</td>
    				<td align="right">�绰2��</td>
    				<td>
    					<s:textfield name="domain.cyrQtlxdh2" cssClass="pop_input noborder" />
    				</td>
    			</tr>
   			</table>
			<table width="98%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td align="right" width="6%">�˷ѣ�</td>
      				<td width="8%">
      					<s:textfield name="domain.yfHj" cssClass="yfxx pop_input inputright noborder" ></s:textfield>
      				</td>
      				<td align="right" width="6%">Ԥ����</td>
      				<td width="8%">
      					<s:textfield name="domain.yfYfyf" cssClass="yfxx pop_input inputright noborder" ></s:textfield>
      				</td>
      				<td align="right" width="6%">������</td>
      				<td width="8%">
      					<s:textfield name="domain.yfHdyf" cssClass="yfxx pop_input inputright noborder" ></s:textfield>
      				</td>
      				<td align="right" width="6%">�ظ���</td>
      				<td width="8%">
      					<s:textfield name="domain.yfHdf" cssClass="yfxx pop_input inputright noborder" ></s:textfield>
      				</td>
      				<td align="right" width="8%">˾���գ�</td>
      				<td width="7%">
      					<s:textfield name="domain.yfSjs" cssClass="yfxx pop_input inputright noborder" ></s:textfield>
      				</td>
      				<td align="right" width="8%">��Ϣ�ѣ�</td>
      				<td width="7%">
      					<s:textfield name="domain.yfXxf" cssClass="pop_input inputright noborder" ></s:textfield>
      				</td>
      				<td align="right" width="6%">Ѻ��</td>
      				<td width="8%">
      					<s:textfield name="domain.yfYj" cssClass="pop_input inputright noborder" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">��ע��</td>
      				<td colspan="13">
      					<s:textarea name="domain.bz" rows="3" cssClass="pop_textarea_colspan2 noborder" ></s:textarea>
      				</td>
      			</tr>
			</table>
			</fieldset>
			</div>
		  </div>
		<!-- ��ҳ��� id����ΪdataList -->
		<table id="dataList"><tr><td/></tr></table> 
		</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
