<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<html>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>回单清单打包</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript">
    $(document).ready(function(){
    	$("#closeBtn").click(function(){
    		window.close();
    	});
		initDataGrid();
		onRefresh();
	});	
  /**************************************分页开始****************************************/
  //刷新表格
	function onRefresh(){
		var hdqdDjxh = $("#mainForm_domain_hdqdDjxh").val();
  		//请求表格数据
		var url = jcontextPath+"/hypchwxxhdqd!queryHdByQd.action";   
		 $("#dataList").jqGrid("setGridParam",{ 
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.hdqdDjxh":hdqdDjxh}								//请求的参数，json格式
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
			height:pageTableHeight()-90,	
		    gridComplete: myGridComplete,		//表格加载完毕事件
		    shrinkToFit:false,
		    colNames:['<input title="选中/取消选中" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />', '回单登记序号','派车登记序号','未发货登记序号','订单登记序号','货物明细序号',
				     '回单编号','实装数量','实装重量','实装体积','回单接收日期','要求到达日期','收货方式代码',
				     '实装_结算数量',
				     '派车单号','派车日期','派车方式', '装车方式','车辆号码','挂车号码',
				     '司机','运费合计','预付运费', '备注','派车人','派车部门','业务公司'
				     
				    ],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
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
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum%>,						
		    rowList:[20,50,100,300,500],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'HD_DJXH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
		    sortorder: 'asc',				//默认排序方向
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
		 
		    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”并增加链接
			function myGridComplete() {
		            var graduateIds = $("#dataList").jqGrid('getDataIDs');
		            for (var i = 0; i < graduateIds.length; i++) {
		                var cl = graduateIds[i];
		                var val = jQuery("#dataList").jqGrid('getCell', cl,"hdDjxh"); 	  //获取当前单元格里面的参数序号 
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
	<s:hidden name="domain.hdqdDjxh"></s:hidden>
		<div class="right_cont">
		<div id="divQuery" align="center">
			<button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		</div>
	
		<!-- 分页表格 id必须为dataList -->
		<table id="dataList"><tr><td /></tr></table>
		<!-- 分页导航 -->
		<div id="pager" style="display: none;"></div>
	</div>
</s:form>
</body>
