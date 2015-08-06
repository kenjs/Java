<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>决策-车辆跟踪</title>
<style type="text/css">

</style>
<script type="text/javascript">
	$(document).ready(function(){
		//$(window).unbind("resize");
		//初始化表格
		initClgzxxDataGrid();
		
		onClgzxxRefresh();
		
	});
	
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onClgzxxRefresh(){
		var xtcs20201 = $("#mainForm_domain_xtcs20201").val();
		if ("Y" == xtcs20201) {
			$("#clgzxxList").jqGrid('showCol',["wsspztMc"]);
		}
		var ddDjxh = $("#mainForm_domain_ddDjxh").val();
		var pcDjxh = $("#mainForm_domain_pcDjxh").val();
		
		//请求表格数据
		var url = jcontextPath+"/jcgl/jctydgl!queryJcSjcxClgzxx.action";   
		 $("#clgzxxList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ddDjxh":ddDjxh,"domain.pcDjxh":pcDjxh}								//请求的参数，json格式
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
	function initClgzxxDataGrid(){ 
		  $("#clgzxxList").jqGrid({
		    url:"",
		    datatype: 'local',
		    mtype: 'POST',
		    rownumbers : false,					//序号列
			width:pageWidth()-10, 
			height:pageTableHeight()-90,	
		    gridComplete: myClgzxxGridComplete,		//表格加载完毕事件
		    shrinkToFit:false, 
		    colNames:['序号', '派车登记序号','派车单号','派车方式名称', '装车方式名称', '车辆号码', '挂车号码', 
		              '司机','派车日期', '日期', '说明',
		              '详细地址','预到日期', '所在区域', '登记部门'],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[
			  {name:'pageXh', index:'pageXh', sortable:false, width:'30', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pageXhClgz' + rowId + '\'';
			    }
			  },
			  {name:'pcDjxh', index:'pcDjxh',hidden:true, width:'80', align:'center'},
			  {name:'pcdh', index:'pcdh', width:'65', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcdhClgz' + rowId + '\'';
			    }
			  },
			  {name:'pcfsMc', index:'pcfsMc', width:'75', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcfsMcClgz' + rowId + '\'';
			    }
			  },
			  {name:'zcfsMc', index:'zcfsMc', width:'75', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'zcfsMcClgz' + rowId + '\'';
			    }
			  },
			  {name:'cyrClhm', index:'cyrClhm', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'cyrClhmClgz' + rowId + '\'';
			    }
			  },
			  {name:'cyrGchm', index:'cyrGchm', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'cyrGchmClgz' + rowId + '\'';
			    }
			  },
			  {name:'cyrSjxm', index:'cyrSjxm', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'cyrSjxmClgz' + rowId + '\'';
			    }
			  },
			  {name:'pcrq', index:'pcrq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcrqClgz' + rowId + '\'';
				    }
			   },
			  {name:'rq', index:'rqq', width:'125', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'}},
			  {name:'sm', index:'sm', width:'120', align:'center'},
		      {name:'xxdz', index:'xxdz', width:'80', align:'center'},
		      {name:'yjDdrq', index:'yjDdrq', width:'100', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      {name:'szqyXzqhMc', index:'szqyXzqhMc', width:'80', align:'center'},
		      {name:'djJgmc', index:'djJgmc', width:'80', align:'center'}
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: -1,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[-1],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'DD_DJXH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
		    sortorder: 'DESC',				//默认排序方向
		    viewrecords: true,					//定义是否要显示总记录数
		    gridview: false,					//默认false 构造一行数据后添加到grid中，如果设为true则是将整个表格的数据都构造完成后再添加到grid中，但treeGrid, subGrid, or afterInsertRow 不能用	
		    jsonReader: {     
	     	 	root: 	 "domain.clgzList",   				// 数据行（默认为：rows）
	     	 	page:	 "domain.page.curPage",					// 当前页
	     	 	total: 	 "domain.page.totalPages",				// 总页数
	     	 	records: "domain.page.totalRecords", 			// 总记录数
	     	 	reccount: "domain.page.reccount",
	     	 	//userdata: "userdata",						    // 在某些情况下，我们需要从服务器端返回一些参数但并不想直接把他们显示到表格中，而是想在别的地方显示
	        	repeatitems : false     						// 设置成false，在后台设置值的时候，可以乱序。且并非每个值都得设
	     	},
	     	prmNames:{rows:"domain.page.pageSize",page:"domain.page.curPageNo",sort:"domain.page.orderBy",
	     	order:"domain.page.order",search:"domain.page.search"}
		    //caption: '数据信息'							//表格名称,
		  }); 
		  
		  //设置page选项为1
		  jQuery("#clgzxxList").jqGrid('navGrid','#pager',
		 		 {edit:false,add:false,del:false}
		  );
		  
	}
	
	function myClgzxxGridComplete() {
        var graduateIds = $("#clgzxxList").jqGrid('getDataIDs');
		
        var heightT = getAutoGridHeight(graduateIds.length);
	    $("#clgzxxList").setGridHeight(heightT);
        
        var gridName = "clgzxxList";
 	    var aClgz = ['pageXhClgz','pcdhClgz','pcfsMcClgz','zcfsMcClgz','cyrClhmClgz','cyrGchmClgz','cyrSjxmClgz','pcrqClgz'];
	    var cellNames = ['pageXh','pcdh','pcfsMc','zcfsMc','cyrClhm','cyrGchm','cyrSjxm','pcrq'];
  		
        Merger(gridName, 'pcDjxh', aClgz,cellNames);
 }
	
     /**************************************分页结束****************************************/
</script>
</head>
<body>
	<s:hidden name="domain.xtcs20201" />
		<!-- 分页表格 id必须为clgzxxList -->
		<table id="clgzxxList"><tr><td/></tr></table> 
		<!-- 分页导航 -->
		<%@include file="/common/message.jsp" %>
</body>
</html>
