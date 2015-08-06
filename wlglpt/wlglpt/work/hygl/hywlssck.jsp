<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<html>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>货运-物流损失查看</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript">
    $(document).ready(function(){
    	$("#closeBtn").click(function(){
			window.close();
		});
    	
		//查询按钮事件
		$("#queryBtn").click(function(){
			onRefresh();
		});
		
		$("#saveMxBtn").click(function(){
			var xhs = $(":checked[name='xhs']");
			if (xhs.length <= 0) {
				showAlert("请先选择需要形成清单的物流损失登记记录！");
				return;
			}
			
			var existBz = trim($("#mainForm_domain_existBz").val()); 
			var qdDjxh = trim($("#mainForm_domain_qdDjxh").val()); 
			var xhs = $(":checked[name='xhs'][value!='']");
			if (xhs.length > 0) {
				var jsonStr = getJqueryParam(xhs, "domain.ywDjxhs");
				var jsonObj = {"domain.qdDjxh":qdDjxh,"domain.existBz":existBz};
				
				jsonStr += jQuery.param(jsonObj);
				var url = jcontextPath+"/hygl/jssrdzqd!saveWlssMx";  
				showMessage();
				ajaxCommon(url,encodeURI(jsonStr),"doSaveMxSuc", false);
			}
		});
					
		//初始化表格
		initDataGrid();
	});	
    
    function doSaveMxSuc(){ 
		hideMessage();
		window.close();
	}
    
  /**************************************分页开始****************************************/
  //刷新表格
	function onRefresh(){
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var khDjxh = $("#mainForm_domain_khDjxh").val();
		var pcrqQ = $("#mainForm_domain_pcrqQ").val();
		var pcrqZ = $("#mainForm_domain_pcrqZ").val();
  		//请求表格数据
		var url = jcontextPath+"/hygl/wlssdj!query.action";   
		 $("#dataList").jqGrid("setGridParam",{ 
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.khDjxh":khDjxh,"domain.pcrqQ":pcrqQ,"domain.pcrqZ":pcrqZ}								//请求的参数，json格式
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
			height:pageTableHeight()-130,	
		    gridComplete: myGridComplete,		//表格加载完毕事件
		    shrinkToFit:false, 
		    colNames:['<input title="选中/取消选中" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />',
		    	'物流损失登记序号','损失金额','物流损失原因','损失处理方式','客户名称','货物名称','始发地','目的地','数量','重量','体积','包装','派车日期'],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[ 
			  {name:'hstoperationcol', index:'',width:'30', sortable:false, align:'center'},
			  {name:'wlssDjxh', index:'wlssDjxh',hidden:true, align:'center'},
		      {name:'je', index:'je', width:'80', align:'center'},
		      {name:'wlssyy', index:'wlssyy', width:'100', align:'center'}, 	
		      {name:'wlssclfsMc', index:'wlssclfsMc', width:'100', align:'center'},
		      {name:'khmc', index:'khmc', width:'150', align:'center'},
		      {name:'hwmc', index:'hwmc', width:'150', align:'center'},
		      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'80', align:'center'},
		      {name:'shrXzqhMc', index:'shrXzqhMc', width:'80', align:'center'},  
		      {name:'sl', index:'sl', width:'60', align:'center'}, 	      
		      {name:'zl', index:'zl', width:'60', align:'center'},			  
			  {name:'tj', index:'tj', width:'60', align:'center'}, 	  
			  {name:'bz', index:'bz', width:'60', align:'center'},
			  {name:'pcrq', index:'pcrq', width:'80', align:'center'}
		     ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: -1,						
		    rowList:[-1],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: '',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
		    sortorder: '',				//默认排序方向
		    viewrecords: true,					//定义是否要显示总记录数
		    gridview: false,					//默认false 构造一行数据后添加到grid中，如果设为true则是将整个表格的数据都构造完成后再添加到grid中，但treeGrid, subGrid, or afterInsertRow 不能用	
		    jsonReader: {     
	     	 	root: 	 "domain.dataList",   				// 数据行（默认为：rows）
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
		  $("#dataList").jqGrid('navButtonAdd','#pager',{
		       caption:"", 
		       onClickButton : function () { 
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/wlssdj!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
		    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”并增加链接
			function myGridComplete() {
	            var graduateIds = $("#dataList").jqGrid('getDataIDs');
	            for (var i = 0; i < graduateIds.length; i++) {
	                var cl = graduateIds[i];
	                var wlssDjxh = jQuery("#dataList").jqGrid('getCell', cl,"wlssDjxh"); 	  //获取当前单元格里面的参数序号 
	                var link = "<input type=\"checkbox\" name=\"xhs\" value=\""+wlssDjxh+"\" />";
	                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
	            }
		     }
	}
</script>
</head>
<body>
<s:form action="hygl/wlssdj!query" theme="simple" namespace="cwgl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.qdDjxh" />
	<s:hidden name="domain.existBz" />
	<s:hidden name="domain.ssJgbm" />
	
	<div class="pop_contc">
		<fieldset>
			<legend>查询条件</legend>
			<table width="99%" border="0" cellspacing="0" cellpadding="0">
				<tr>
				    <td width="10%" align="right">派车日期：</td>
	                <td width="22%">
	                  <sys:dateFirstDMonth myName="domain.pcrqQ" myId="mainForm_domain_pcrqQ" myClass="ymdate" />
          				～
          			  <sys:dateCurrentDayTag myName="domain.pcrqZ" myId="mainForm_domain_pcrqZ" myClass="ymdate" />
	                </td>     
	            </tr>
			</table>
		</fieldset>
	
		<div class="pop_btn" style="width: 700px;">
			<button type="button" class="pop_btnbg" id="queryBtn">检 索</button>
		 	&nbsp;
		 	<button type="button" class="pop_btnbg" id="saveMxBtn">确 定</button>
		 	&nbsp;
		    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
	    </div>
		<!-- 分页表格 id必须为dataList -->
		<table id="dataList"><tr><td /></tr></table>
	</div>
</s:form>
</body>
