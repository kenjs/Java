<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>下游结算-收入对帐-清单</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){
		//初始化表格
		initDataGrid();
		var tempFlag = $("#mainForm_domain_tempFlag").val();
		if ("N" == tempFlag) {
			onRefresh();
		}
		
		$("#addBtn").click(function(){
			var qdDjxh = $("#mainForm_domain_qdDjxh").val();
			var xyDjxh = $("#mainForm_domain_xyDjxh").val(); 
			var tempFlag = $("#mainForm_domain_tempFlag").val();
			var fylbDm = $("#mainForm_domain_fylbDm").val();
			
			var url = jcontextPath+"/hygl/xyjssrdzqd!initQueryJsxxMx?domain.qdDjxh="+qdDjxh+"&domain.xyDjxh="+xyDjxh
					+"&domain.tempFlag="+tempFlag+"&domain.fylbDm="+fylbDm;
			window.showModalDialog(url,window,"dialogHeight:640px;dialogWidth:950px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
			queryHjjeCalculate();
			onRefresh();
		});
		
		$("#deleteBtn").click(function(){
			var xhs = $(":checked[name='jsDjxhs']");
			if (xhs.length <= 0) {
				showAlert("请先选择需要删除的记录！");
				return;
			}
			
			showConfirm("是否确定删除选中记录？", "doDeleteMx");
		});
		
		$("#saveBtn").click(function(){
			if(!checkdata()){
				return;
			}
			var qdDjxh = trim($("#mainForm_domain_qdDjxh").val()); 
			var xyDjxh = trim($("#mainForm_domain_xyDjxh").val()); 
			var qdmc = trim($("#mainForm_domain_qdmc").val()); 
			var heJe = trim($("#mainForm_domain_heJe").val()); 
			var dzJe = $("#mainForm_domain_dzJe").val();
			var dzcyJe = $("#mainForm_domain_dzcyJe").val();
			var tempFlag = trim($("#mainForm_domain_tempFlag").val()); 
			var fylbDm = $("#mainForm_domain_fylbDm").val();

			var url = jcontextPath+"/hygl/xyjssrdzqd!save";  
	    	var jsonObj = {"domain.qdDjxh":qdDjxh,"domain.xyDjxh":xyDjxh,"domain.qdmc":qdmc,
                           "domain.heJe":heJe,"domain.dzJe":dzJe,"domain.dzcyJe":dzcyJe,
                           "domain.tempFlag":tempFlag,"domain.fylbDm":fylbDm};   	
	    	showMessage();
			ajaxCommon(url,jsonObj, "saveSuc");
		});
	});
	
	function saveSuc() {
		hideMessage();
		$("#mainForm_domain_tempFlag").val("N");
		showAlert("保存成功！");
	}
	
	function doDeleteMx() {
		var tempFlag = trim($("#mainForm_domain_tempFlag").val()); 
		var qdDjxh = trim($("#mainForm_domain_qdDjxh").val()); 
		var fylbDm = $("#mainForm_domain_fylbDm").val();
		var xhs = $(":checked[name='jsDjxhs'][value!='']");
		if (xhs.length > 0) {
			var jsonStr = getJqueryParam(xhs, "domain.ywDjxhs");
			var jsonObj = {"domain.qdDjxh":qdDjxh,"domain.tempFlag":tempFlag,"domain.fylbDm":fylbDm};
			
			jsonStr += jQuery.param(jsonObj);
			var url = jcontextPath+"/hygl/xyjssrdzqd!deleteQdmx";  
			showMessage();
			ajaxCommon(url,encodeURI(jsonStr),"deleteQdmxSuc", false);
		}
	}
	
	function deleteQdmxSuc() {
		hideMessage();
		onRefresh();
		queryHjjeCalculate();
		showAlert("删除成功！");
	}
	
	function checkdata(){
		var controlNameArray = ["domain.qdmc"];
		var labelNameArray = ["清单名称"];
		var compareValueArray = [200];
		var nodeTypeArray = [NodeType.STRING];
		var notNullArray = [true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
	
	function queryHjjeCalculate() {
		var qdDjxh = trim($("#mainForm_domain_qdDjxh").val()); 
		var tempFlag = $("#mainForm_domain_tempFlag").val();
		var fylbDm = $("#mainForm_domain_fylbDm").val();
		var jsonObj = {"domain.qdDjxh":qdDjxh, "domain.tempFlag":tempFlag,"domain.fylbDm":fylbDm};
		var url = jcontextPath+"/hygl/xyjssrdzqd!queryHjjeCalculate";  
		
		ajaxCommon(url,jsonObj,"queryHjjeSuc");
	}
	
	function queryHjjeSuc(data) {
		var heJe = data.domain.heJe;
		var dzJe = data.domain.dzJe;
		var dzcyJe = data.domain.dzcyJe;
		if (heJe != null && heJe != "null") {
			$("#mainForm_domain_heJe").val(heJe);
		}
		
		if (dzJe != null && dzJe != "null") {
			$("#mainForm_domain_dzJe").val(dzJe);
		}
		
		if (dzcyJe != null && dzcyJe != "null") {
			$("#mainForm_domain_dzcyJe").val(dzcyJe);
		}
	}
	
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		var qdDjxh = trim($("#mainForm_domain_qdDjxh").val()); 
		var tempFlag = $("#mainForm_domain_tempFlag").val();
		var fylbDm = $("#mainForm_domain_fylbDm").val();
		
		if ("1" == fylbDm) {
			$("#dataList").jqGrid('showCol',["zcPsf"]);
			$("#dataList").jqGrid('hideCol',["zcDf","zcDshk"]);
		}else if ("2" == fylbDm) {
			$("#dataList").jqGrid('showCol',["zcDf"]);
			$("#dataList").jqGrid('hideCol',["zcDshk","zcPsf"]);
		}else if ("3" == fylbDm) {
			$("#dataList").jqGrid('showCol',["zcDshk"]);
			$("#dataList").jqGrid('hideCol',["zcPsf","zcDf"]);
		}
		
		//请求表格数据
		var url = jcontextPath+"/hygl/xyjssrdzqd!queryQdMx";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.qdDjxh":qdDjxh,"domain.tempFlag":tempFlag,"domain.fylbDm":fylbDm
		 		}								//请求的参数，json格式
		 }
		 ).trigger("reloadGrid");		//显示到第一页，以免总体数据发生变化的时候，分页的信息混乱
	}
	
	<% 
	    //获取每个用户的每页显示参数值
		UserDomain userDomain=(UserDomain) request.getSession().getAttribute(WebConstants.SES_USER_INFO);
		String rowNum = "20";
		if (userDomain != null && userDomain.getRowNum() != null && !"".equals(userDomain.getRowNum().trim()))
			rowNum = userDomain.getRowNum();
	%>
	
	//jqGrid  初始化表格
	function initDataGrid(){ 
		  $("#dataList").jqGrid({
		    url:"",
		    datatype: 'local',
		    mtype: 'POST',
		    rownumbers : true,					//序号列
			width:pageWidth()-10,  
			height:pageTableHeight()-150,	
		    gridComplete: myGridComplete,		//表格加载完毕事件
		    shrinkToFit:false, 
		    colNames:['<input title="选中/取消选中" type="checkbox" onclick="checkAllJGridChk(event,this,\'jsDjxhs\');" />',
		    		'结算登记序号','配送费','到付款','代收货款','对账金额','对账差异金额',
		    		'订单编号','下单日期','始发地','目的地',
		              "货物名称","数量","重量","体积","发货人","收货单位","收货人","收货地址","派车日期","派车单号"],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'30', align:'center'},
		      {name:'jsDjxh', index:'jsDjxh', width:'30', hidden:true, align:'center'},
		      {name:'zcPsf', index:'zcPsf', width:'60', align:'center', hidden:true},
		      {name:'zcDf', index:'zcDf', width:'60', align:'center', hidden:true}, 
		      {name:'zcDshk', index:'zcDshk', width:'60', align:'center', hidden:true}, 
		      {name:'dzje', index:'dzje', width:'60', align:'center'}, 
		      {name:'dzcyje', index:'dzcyje', width:'60', align:'center'}, 
		      {name:'ddbh', index:'ddbh', width:'100', align:'center'}, 
		      {name:'xdrq', index:'xdrq', width:'65', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      {name:'sfdXzqhMc', index:'sfdXzqhMc', width:'70', align:'center'},
		      {name:'mddXzqhMc', index:'mddXzqhMc', width:'70', align:'center'}, 

		      {name:'hwmc', index:'hwmc', width:'100', align:'center'},
		      {name:'hwSl', index:'hwSl', width:'50', align:'center'},
		      {name:'hwZl', index:'hwZl', width:'50', align:'center'},
		      {name:'hwTj', index:'hwTj', width:'50', align:'center'},
		      {name:'fhrLxr', index:'fhrLxr', width:'50', align:'center'},
		      {name:'shrMc', index:'shrMc', width:'60', align:'center'},
		      {name:'shrLxr', index:'shrLxr', width:'50', align:'center'},
		      {name:'shrDz', index:'shrDz', width:'60', align:'center'},

		      {name:'pcrq', index:'pcrq', width:'65', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}, 
		      {name:'pcdh', index:'pcdh', width:'70', align:'center'}
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum%>,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[20,50,100,300,500],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'ddbh',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
		    sortorder: 'DESC',				//默认排序方向
		    viewrecords: true,					//定义是否要显示总记录数
		    gridview: false,					//默认false 构造一行数据后添加到grid中，如果设为true则是将整个表格的数据都构造完成后再添加到grid中，但treeGrid, subGrid, or afterInsertRow 不能用	
		    jsonReader: {     
	     	 	root: 	 "domain.qdmxList",   				// 数据行（默认为：rows）
	     	 	page:	 "domain.page.curPage",					// 当前页
	     	 	total: 	 "domain.page.totalPages",				// 总页数
	     	 	records: "domain.page.totalRecords", 			// 总记录数
	     	 	//userdata: "userdata",						    // 在某些情况下，我们需要从服务器端返回一些参数但并不想直接把他们显示到表格中，而是想在别的地方显示
	        	repeatitems : false     						// 设置成false，在后台设置值的时候，可以乱序。且并非每个值都得设
	     	},
	     	prmNames:{rows:"domain.page.pageSize",page:"domain.page.curPageNo",sort:"domain.page.orderBy",
	     	order:"domain.page.order",search:"domain.page.search"}
		    //caption: '数据信息'							//表格名称,
		  }); 
		  
		  //设置page选项为1
		  jQuery("#dataList").jqGrid('navGrid','#pager',
		 		 {edit:false,add:false,del:false}
		  );
		  
	  	  // add custom button to export the data to excel
		 
	}
	
    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var jsDjxh = jQuery("#dataList").jqGrid('getCell', cl,"jsDjxh"); 	  //获取当前单元格里面的登记序号 
                var link = "<input type='checkbox' name='jsDjxhs' value='"+jsDjxh+"' />"
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
     /**************************************分页结束****************************************/
</script>
</head>

<body>
<%try{ %>
<s:form action="xyjssrdzqd!initMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.qdDjxh"></s:hidden>
	<s:hidden name="domain.xyDjxh"></s:hidden>
	<s:hidden name="domain.tempFlag"></s:hidden>
	<s:hidden name="domain.fylbDm"></s:hidden>
	
	<div class="pop_contc">
		<fieldset>
			<legend>基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="12%" align="right">下游名称：</td>
      				<td width="21%">
      					<s:textfield name="domain.xyMc" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
      				</td>
      				<td width="12%" align="right"><font class="font_red">*</font>清单名称：</td>
      				<td width="21%">
      					<s:textfield name="domain.qdmc" cssClass="pop_input noborder bgstyle_required" ></s:textfield>
      				</td>
      				<td width="12%"></td>
      				<td width="21%"></td>
      			</tr>
      			<tr>
      				<td align="right">合计金额：</td>
      				<td>
      					<s:textfield name="domain.heJe" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
      				</td>
      				<td align="right">对账金额：</td>
      				<td>
      					<s:textfield name="domain.dzJe" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
      				</td>
      				<td align="right">差异金额：</td>
      				<td>
      					<s:textfield name="domain.dzcyJe" cssClass="pop_input noborder bgstyle_readonly" readonly="true"></s:textfield>
      				</td>
      			</tr>
			</table>
		</fieldset>	
		<div class="pop_btn">
			<button type="button" class="pop_btnbg" id="addBtn">添 加</button>
		 	&nbsp;
		 	<button type="button" class="pop_btnbg" id="deleteBtn">删 除</button>
		 	&nbsp;
		 	<button type="button" class="pop_btnbg" id="saveBtn">保 存</button>
		 	&nbsp;
		    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
	    </div>
		<table id="dataList"><tr><td/></tr></table>
	</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
