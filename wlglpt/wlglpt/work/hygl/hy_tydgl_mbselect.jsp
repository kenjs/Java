<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>货运-托运单管理-模板选择</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>

<script type="text/javascript">
	$(document).ready(function(){
		//onRefresh();
		$("#closeBtn").click(function() {
			window.close();
		});
		
		//查询按钮事件
		$("#queryBtn").click(function(){
			onRefresh();
		});
		
		//新增按钮事件
		$("#yesBtn").click(function(){
			var checkbox=$(":checked[name='box']");
			if (checkbox.length <= 0) {
				showAlert("请选择一个模板！");
				return;
			}
			window.dialogArguments.initTydFromMb(checkbox.val());
			window.close();
		});
		//初始化表格
		initDataGrid();
	});
	
	window.onload = function init() {
		onRefresh();
	}

	function checkSingle(obj){
		var checkbox=$(":checkbox[name='box']").attr("checked", false);
		obj.checked = true;
	}
	
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		var fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		var fhrMc = $("#mainForm_domain_fhrMc").val();
		var shrMc = $("#mainForm_domain_shrMc").val();
		
		//请求表格数据
		var url = jcontextPath+"/hygl/hytydmbgl!queryMb4Tydgl.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.fhrDjxh":fhrDjxh,"domain.fhrMc":encodeURI(fhrMc), "domain.shrMc":encodeURI(shrMc)
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
		    rownumbers : false,					//序号列
			width:pageWidth()-10,  
			height:pageTableHeight()-90,	
		    gridComplete: myGridComplete,		//表格加载完毕事件
		    shrinkToFit:false, 
		    colNames:['操作', '客户名称','模板名称','始发地', '目的地', 
		              '发货地址', '收货人','收货地址', '登记部门', '所属机构', '创建人', '创建日期', '修改人', '修改日期',
		             'ID' ],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'30', align:'center'},
			  {name:'fhrMc', index:'fhrMc', width:'100', align:'center'}, 
		      {name:'mbmc', index:'mbmc', width:'200', align:'center'}, 
		      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'50', align:'center'}, 
		      
		      {name:'shrXzqhMc', index:'shrXzqhMc', width:'50', align:'center'}, 
		       {name:'fhrDz', index:'fhrDz', width:'80', align:'center'}, 
		        {name:'shrMc', index:'shrMc', width:'80', align:'center'}, 
		      {name:'shrDz', index:'shrDz', width:'80', align:'center'}, 
		      {name:'djJgmc', index:'djJgmc', width:'80', align:'center'},
		      {name:'ssJgmc', index:'ssJgmc', width:'80', align:'center'},
		      {name:'cjrMc', index:'cjrMc', width:'80', align:'center'},
		      
		      {name:'cjrq', index:'cjrq', width:'70', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      {name:'xgrMc', index:'xgrMc', width:'80', align:'center'}, 
		      {name:'xgrq', index:'xgrq', width:'70', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		       {name:'mbDjxh', index:'mbDjxh', width:'0',hidden:true, align:'center'}
		      
		      
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: -1,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[-1],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'mbDjxh,fhrMc',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
		    sortorder: 'DESC,ASC',				//默认排序方向
		    viewrecords: true,					//定义是否要显示总记录数
		    gridview: false,					//默认false 构造一行数据后添加到grid中，如果设为true则是将整个表格的数据都构造完成后再添加到grid中，但treeGrid, subGrid, or afterInsertRow 不能用	
		    jsonReader: {     
	     	 	root: 	 "domain.dataList",   				// 数据行（默认为：rows）
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
		  jQuery("#dataList").jqGrid('navGrid','#pager',
		 		 {edit:false,add:false,del:false}
		  );
		  
	  	  // add custom button to export the data to excel
		  /* $("#dataList").jqGrid('navButtonAdd','#pager',{
		       caption:"", 
		       onClickButton : function () { 
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/hytydmbgl!download.action");
				   $("#mainForm").submit();
		       } 
		 }); */
		 
	}
	
    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"mbDjxh"); 	  //获取当前单元格里面的登记序号 
                var link = '<input type="checkbox" name="box" value="'+val+'"  onclick="checkSingle(this)"/>';
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
    }
       
     /**************************************分页结束****************************************/

</script>
</head>
<body>
<s:form action="hytydgl!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.fhrDjxh" />
	<s:hidden name="domain.fhrMc" />
	
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">操作：</li>
		    <li><a href="#" id="queryBtn" class="licon04">检 索</a></li>
		    <li><a href="#" id="yesBtn" class="licon01">确 定</a></li>
		    <li><a href="#" id="closeBtn" class="licon03">关 闭</a></li>
	  	</ul>
	
	    <ul class="rcont">
		    <li class="ricon02" onclick="slideToggle('syquery')">显示/隐藏查询条件</li>
		    <li class="ricon03">帮助</li>
	  	</ul>
	</div> 

	<div class="right_cont">
	  <div id="divQuery">
		<fieldset>
			<legend>查询条件</legend>
		   <table width="95%" border="0" cellspacing="0" cellpadding="0">
		       <tr>
		          <td width="15%" align="right">收货单位：</td>
		          <td width="35%">
		          	<s:textfield name="domain.shrMc" cssClass="pop_input bgstyle_optional"></s:textfield>
		          </td>
		          <td width="15%" align="right"></td>
		          <td width="35%">
		          </td>
		        </tr>
		   </table>
		</fieldset>
	  </div>
  
		<!-- 分页表格 id必须为dataList -->
		<table id="dataList"><tr><td/></tr></table> 
		<!-- 分页导航 -->
		<%@include file="/common/message.jsp" %>
	</div>
</s:form>
</body>
</html>
