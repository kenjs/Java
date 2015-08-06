<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>决策-运费支付信息</title>
<style type="text/css">

</style>
<script type="text/javascript">
	$(document).ready(function(){
		//$(window).unbind("resize");
		//初始化表格
		initYfzfxxDataGrid();
		onYfzfxxRefresh();
		
	});
	
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onYfzfxxRefresh(){
		var xtcs20201 = $("#mainForm_domain_xtcs20201").val();
		if ("Y" == xtcs20201) {
			$("#yfList").jqGrid('showCol',["wsspztMc"]);
		}
		var ddDjxh = $("#mainForm_domain_ddDjxh").val();
		var pcDjxh = $("#mainForm_domain_pcDjxh").val();
		
		//请求表格数据
		var url = jcontextPath+"/jcgl/jctydgl!queryJcYfZfxx.action";   
		 $("#yfList").jqGrid("setGridParam",{
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
	function initYfzfxxDataGrid(){ 
		  $("#yfList").jqGrid({
		    url:"",
		    datatype: 'local',
		    mtype: 'POST',
		    rownumbers : false,					//序号列
			width:pageWidth()-10, 
			height:pageTableHeight()-90,	
		    gridComplete: myYfzfxxGridComplete,		//表格加载完毕事件
		    shrinkToFit:false, 
		    colNames:['序号', '派车登记序号','派车单号','状态', '结算方', '名称', '金额', 
		              '已付金额', '未付金额','经办日期','生成日期', '类别',
		              '项目','来源', '收款方', '金额', '支付方式','资产分类','经办人','银行名称','账号','备注',
		             '说明','登记部门','所属单位'],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[
			  {name:'pageXh', index:'pageXh', sortable:false, width:'30', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pageXhYfzfxx' + rowId + '\''; 
			    }
			  },
			  {name:'yfDjxh', index:'yfDjxh',hidden:true, width:'80', align:'center'},
			  {name:'pcdh', index:'pcdh', width:'65', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcdhYfzfxx' + rowId + '\'';
			    }
			  },
			  {name:'yfztMc', index:'yfztMc', width:'30', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'yfztMc' + rowId + '\'';
			    }
			  },
			  {name:'yfjsfMc', index:'yfjsfMc', width:'50', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'yfjsfMc' + rowId + '\'';
			    }
			  },
			  {name:'yfjsfDjMc', index:'yfjsfDjMc', width:'100', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'yfjsfDjMc' + rowId + '\'';
			    }
			  },
			  {name:'jsfJe', index:'jsfJe', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'jsfJe' + rowId + '\'';
			    }
			  },
			  {name:'yisfJe', index:'yisfJe', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'yisfJe' + rowId + '\'';
			    }
			  },
			  {name:'wsfJe', index:'wsfJe', width:'70', align:'center',
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'wsfJe' + rowId + '\'';
				    }
			   },
			   {name:'rq', index:'rq', width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
			   {name:'csRq', index:'csRq', width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
			  {name:'kmdlMc', index:'kmdlMc', width:'80', align:'center'},
			  {name:'kmxlMc', index:'kmxlMc', width:'120', align:'center'},
		      {name:'ysyflyMc', index:'ysyflyMc', width:'80', align:'center'},
		      {name:'skfMc', index:'skfMc', width:'100', align:'center'},
		      {name:'je', index:'je', width:'80', align:'center'},
		      {name:'zffsMc', index:'zffsMc', width:'80', align:'center'},
		      {name:'zcflMc', index:'zcflMc', width:'80', align:'center'},
		      {name:'jbrMc', index:'jbrMc', width:'80', align:'center'},
		      
		      {name:'yhMc', index:'yhMc', width:'80', align:'center'},
		      {name:'zh', index:'zh', width:'80', align:'center'},
		      {name:'bz', index:'bz', width:'80', align:'center'},
		   
		      {name:'sm', index:'sm', width:'230', align:'center'},
		      {name:'djJgmc', index:'djJgmc', width:'80', align:'center'},
		      {name:'ssJgmc', index:'ssJgmc', width:'80', align:'center'}
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: -1,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[-1],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'DD_DJXH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
		    sortorder: 'DESC',				//默认排序方向
		    viewrecords: true,					//定义是否要显示总记录数
		    gridview: false,					//默认false 构造一行数据后添加到grid中，如果设为true则是将整个表格的数据都构造完成后再添加到grid中，但treeGrid, subGrid, or afterInsertRow 不能用	
		    jsonReader: {     
	     	 	root: 	 "domain.yfList",   				// 数据行（默认为：rows）
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
		  jQuery("#yfList").jqGrid('navGrid','#pager',
		 		 {edit:false,add:false,del:false}
		  );
		  
	}
	
	function myYfzfxxGridComplete() {
        var graduateIds = $("#yfList").jqGrid('getDataIDs');
		
        var heightT = getAutoGridHeight(graduateIds.length);
	    $("#yfList").setGridHeight(heightT);
        
        /* for (var i = 0; i < graduateIds.length; i++) {
            var cl = graduateIds[i];
            var pcDjxh = jQuery("#yfList").jqGrid('getCell', cl,"pcDjxh"); 
            
			var link = "<a href=\"javascript:onUpdate('"+pcDjxh+"')\"><font color=\"blue\">修改</font></a>"
					+ "&nbsp;<a href=\"javascript:onDelete('"+pcDjxh+"')\"><font color=\"blue\">作废</font></a>";
            $("#yfList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
        } */
        var gridName = "yfList";
	    var aClgz = ['pageXhYfzfxx','pcdhYfzfxx','yfztMc','yfjsfMc','yfjsfDjMc','jsfJe','yisfJe','wsfJe'];
	    var cellNames = ['pageXh','pcdh','yfztMc','yfjsfMc','yfjsfDjMc','jsfJe','yisfJe','wsfJe'];
  		
        Merger(gridName, 'yfDjxh', aClgz,cellNames);
 }
	
     /**************************************分页结束****************************************/
</script>
</head>
<body>
		<!-- 分页表格 id必须为yfList -->
		<table id="yfList"><tr><td/></tr></table> 
		<!-- 分页导航 -->
		<%@include file="/common/message.jsp" %>
</body>
</html>
