<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>流水记录明细</title>
   
<style type="text/css">
html,body {background:none;}
.tydSel {width:68px;}
</style>
<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		})
		//初始化表格
		initDataGrid();
		
		onRefresh();	
	 })
	     
	
  /**************************************分页开始****************************************/
  //刷新表格
	function onRefresh(){
		var djxh = $("#mainForm_domain_yhCshDjxh").val(); 
  		//请求表格数据
		var url = jcontextPath+"/cwhbzclsjl!query.action";   
		 $("#dataList").jqGrid("setGridParam",{ 
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.yhCshDjxh":djxh}								//请求的参数，json格式
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
			width:pageWidth()-25,  
			height:pageHeight()-260,	
		    gridComplete: myGridComplete,		//表格加载完毕事件
		    shrinkToFit:false, 
		    colNames:['操作','财务登记序号','类别','标志','日期','金额','经办人','银行名称','户名','账号','说明'],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false,hidden:true, align:'center'},
			  {name:'cwbdDjXh', index:'cwbdDjXh',hidden:true, align:'center'},
		      {name:'zcflMc', index:'zcflMc', width:'80', align:'center'},
		      {name:'bzmc', index:'bzmc', width:'80', align:'center'}, 	
		      {name:'rq', index:'rq', width:'60', align:'center'},
		      {name:'bdje', index:'bdje', width:'80', align:'right'},
		      {name:'jbrCzyMc', index:'jbrCzyMc', width:'80', align:'center'},  
		      {name:'yhmc', index:'yhmc', width:'200', align:'center'}, 	      
		      {name:'hm', index:'hm', width:'80', align:'center'},			  
			  {name:'zh', index:'zh', width:'80', align:'center'}, 	  
			  {name:'sm', index:'sm', width:'300', align:'left'}
		     ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum%>,						
		    rowList:[20,50,100,300,500],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'cwbdDjXh',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
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
		                var val = jQuery("#dataList").jqGrid('getCell', cl,"cwbdDjXh"); 	  //获取当前单元格里面的参数序号 
		                var link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">修改</font></a>";
		                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
		            }
		     }
	}
</script>
</head>

<body>
<%try{ %>
   <s:form action="qykhhwxx!initMx" namespace="/cwgl" method="post" id="mainForm" name="mainForm">
   <s:hidden name="domain.yhCshDjxh"></s:hidden>
   	<div class="pop_contc" style="height:170px; overflow:auto;">
			<fieldset>
		    <legend>基本信息</legend>
			<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				
      			<tr>
      				<td width="15%" align="right">类别：</td>
      				<td width="35%">
      					<s:textfield name="domain.zcflMc"  cssClass="pop_input  bgstyle_readonly" readonly="true"></s:textfield>
      				</td>
      				<td width="15%" align="right">户名：</td>
      				<td width="35%">
      					<s:textfield name="domain.hm"  cssClass="pop_input  bgstyle_readonly" readonly="true"></s:textfield>
      				</td>
      		    </tr> 
      		    <tr>
      				<td width="15%" align="right">余额：</td>
      				<td width="35%">
      					<s:textfield name="domain.je"  cssClass="pop_input  bgstyle_readonly" readonly="true"></s:textfield>
      				</td>
      				<td width="15%" align="right">账号：</td>
      				<td width="35%">
      					<s:textfield name="domain.zh"  cssClass="pop_input  bgstyle_readonly" readonly="true"></s:textfield>
      				</td>
      		    </tr>     		    
      			<tr>
      				<td width="15%" align="right">银行名称：</td>
      				<td width="85%" colspan="3">
      				    <s:textfield name="domain.yhmc"  cssClass="pop_input_colspan2  bgstyle_readonly" readonly="true"></s:textfield>
      				</td>
      			</tr>
			</table>
			</fieldset>
			<div class="pop_btn">
			    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		    </div>
	 </div>
	<div class="pop_contc" style="height:600px; overflow:auto;">
	 <!-- 分页表格 id必须为dataList -->
		<table id="dataList"><tr><td /></tr></table>
		<!-- 分页导航 -->
		<div id="pager"></div>
    </div>
   <%@include file="/common/message.jsp" %>
   </s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>