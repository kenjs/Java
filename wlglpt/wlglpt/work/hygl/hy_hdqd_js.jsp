<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<html>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>回单清单接收</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript">
      $(document).ready(function(){
			//查询按钮事件
			$("#queryBtn").click(function(){
				onRefresh();
			});
			$("#qdCxBz").val('2');
			//初始化表格
			initDataGrid();

	});	
	var hdqdDjxh;
	function onDelete(val){
		hdqdDjxh=val;
		showConfirm("确定要删除该清单么？删除后不可恢复","doDelete")
	}
	function doDelete(){
		var url = jcontextPath+"/hygl/hypchwxxhdqd!delete";  
        var jsonObj = {"domain.hdqdDjxh":hdqdDjxh};
        ajaxCommon(url,jsonObj,"doSuc");
	}
	function onJs(val){
		var url = jcontextPath+"/hygl/hypchwxxhdqd!qdjs";  
        var jsonObj = {"domain.hdqdDjxh":val};
        ajaxCommon(url,jsonObj,"doSuc");
	}
	function onTh(val){
		hdqdDjxh=val;
		showConfirm("确定要退回该清单么？删除后不可恢复","doTh")
	}
	function doTh(){
		var url = jcontextPath+"/hygl/hypchwxxhdqd!qdth";  
        var jsonObj = {"domain.hdqdDjxh":hdqdDjxh};
        ajaxCommon(url,jsonObj,"doSuc");
	}
	function doSuc(){
		onRefresh();
	}
	
	function onView(val){
		var url = jcontextPath+"/hygl/hypchwxxhdqd!initMx.action?domain.hdqdDjxh="+val;
		window.showModalDialog(url,window,"dialogHeight:800px;dialogWidth:900px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
	}
  /**************************************分页开始****************************************/
  //刷新表格
	function onRefresh(){
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var qdCxBz = $("#qdCxBz").val();
		var rqQ = $("#mainForm_domain_rqQ").val();
		var rqZ = $("#mainForm_domain_rqZ").val(); 
  		//请求表格数据
		var url = jcontextPath+"/hypchwxxhdqd!queryQd.action";   
		 $("#dataList").jqGrid("setGridParam",{ 
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.rqQ":rqQ,"domain.rqZ":rqZ,"domain.qdCxBz":qdCxBz}								//请求的参数，json格式
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
		    colNames:['操作', '回单清单登记序号(SEQ_HD_DJXH)','清单名称','接收状态','','','发送公司','接收公司',
				     '备注','打包人','打包日期','所属机构','登记部门',
				     '有效标志(Y/N)'],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
		      {name:'hdqdDjxh', index:'hdqdDjxh', width:'100', align:'center',hidden:true}, 
		      {name:'qdmc', index:'qdmc', width:'100', align:'center'}, 
		      {name:'jszt', index:'jszt', width:'60', align:'center'}, 
		      {name:'fsGsbm', index:'fsGsbm', width:'100', align:'center',hidden:true},
		      {name:'jsGsbm', index:'jsGsbm', width:'100', align:'center',hidden:true}, 
		      {name:'fsGsmc', index:'fsGsmc', width:'100', align:'center'}, 

		      {name:'jsGsmc', index:'jsGsmc', width:'100', align:'center'}, 
		      {name:'bz', index:'bz', width:'250', align:'center'}, 
		      {name:'dbrMc', index:'dbrMc', width:'60', align:'center'}, 
		      {name:'dbrq', index:'dbrq', width:'70', align:'center'}, 
		      {name:'ssJgmc', index:'ssJgmc', width:'100', align:'center'}, 
		      
		      {name:'djJgmc', index:'djJgmc', width:'100', align:'center'}, 
		      {name:'yxbz', index:'yxbz', width:'100', align:'center',hidden:true}
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum%>,						
		    rowList:[20,50,100,300,500],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'HDQD_DJXH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
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
		            var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		            for (var i = 0; i < graduateIds.length; i++) {
		                var cl = graduateIds[i];
		                var jszt = jQuery("#dataList").jqGrid('getCell', cl,"jszt"); 	  //获取当前单元格里面的参数序号 
		                var val = jQuery("#dataList").jqGrid('getCell', cl,"hdqdDjxh"); 
		                var fsGsbm = jQuery("#dataList").jqGrid('getCell', cl,"fsGsbm");
		                var qdmc = jQuery("#dataList").jqGrid('getCell', cl,"qdmc");
		                var str = '初始';
		                var link;
		                if(jszt=='1'){
		                    str = '发送';
		                    if(fsGsbm==ssJgbm){
			                    link = "<a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">删除</font></a> ";
		                    }else{
		                    	link = "<a href=\"javascript:onJs('"+val+"')\"><font color=\"blue\">接收</font></a> " + 
				                " <a href=\"javascript:onTh('"+val+"')\"><font color=\"blue\">退回</font></a>";
		                    }
		                }else if(jszt=='2'){
		                	str = '接收';
		                	if(fsGsbm==ssJgbm){
		                    }else{
		                    	link =" <a href=\"javascript:onTh('"+val+"')\"><font color=\"blue\">退回</font></a>";
		                    }
		                }else if(jszt=='3'){
		                	str = '退回';
		                }
		                linkStr = "<a href=\"javascript:onView('"+val+"')\"><font color=\"blue\">"+qdmc+"</font></a> ";
		                $("#dataList").jqGrid('setRowData', cl, { 'jszt': str });
		                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link });
		                $("#dataList").jqGrid('setRowData', cl, { 'qdmc': linkStr });
		            }
		     }
	}
</script>
</head>
<body>
<s:form action="hypchwxxhdqd!queryQd" theme="simple" namespace="cwgl" method="post" id="mainForm" name="mainForm">
	<div class="right_btnbg">
	    <ul class="lcont">
		    <li class="no">操作：</li>
		    <li><a href="#" id="queryBtn" class="licon04">检 索</a></li>
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
				<table width="99%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="10%" align="right">单位：</td> 
						<td width="22%">  
						   <sys:gsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y"  myClass="select"/>
		  			    </td>
		  			    <td  align="right" width="10%">日期：</td>
		                <td width="22%">
		                  	<sys:dateFirstDMonth myName="domain.rqQ" myId="mainForm_domain_rqQ" myClass="ymdate" />
			          		～
			          		<sys:dateCurrentDayTag myName="domain.rqZ" myId="mainForm_domain_rqZ" myClass="ymdate" />
		                </td>  
						<td width="11%" align="right">清单归属：</td>
						<td width="15%" >
							<s:select list="#{'0':'--- 全部 ---','1':' 发送的','2':' 接收的' }" id="qdCxBz" cssClass="select"></s:select>
						</td>
						<td width="10%"></td>  
		            </tr>
				</table>
			</fieldset>
		</div>
	
		<!-- 分页表格 id必须为dataList -->
		<table id="dataList"><tr><td /></tr></table>
		<!-- 分页导航 -->
		<div id="pager"></div>
	</div>
</s:form>
</body>
