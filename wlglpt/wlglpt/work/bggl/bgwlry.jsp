<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>办公-外联人员</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>

<script type="text/javascript">
	$(document).ready(function(){
			//查询按钮事件
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//新增按钮事件
			$("#addBtn").click(function(){
				var url = jcontextPath+"/bggl/bgwlry!initMx.action";
				window.showModalDialog(url,window,"dialogHeight:375px;dialogWidth:675px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
				onRefresh();
			});
			//初始化表格
			initDataGrid();
			
			var jgbm = $("#mainForm_domain_jgbm").val();
			if(jgbm != null && jgbm !="" && jgbm != undefined){
				commonInit('gs-wlryfl', jgbm,'', 'domain.wlryFlxh', 'mainForm_domain_wlryFlxh', 'Y', 'Y');
			}			

	});

    function onUpdate(wlryDjxh,jgbm){
    	var url = jcontextPath+"/bggl/bgwlry!initMx.action?domain.wlryDjxh="+wlryDjxh+"&domain.jgbm="+jgbm;
    	window.showModalDialog(url,window,"dialogHeight:375px;dialogWidth:675px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		onRefresh();
    }
    
    var keyValue = "";
	function onDelete( wlryDjxh){
		keyValue = wlryDjxh;
		showConfirm("确定要删除么？","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.wlryDjxh":keyValue};
		 var url = jcontextPath+"/bggl/bgwlry!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("删除成功！");
        onRefresh();
	}	
    
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		 var jgbm = $("#mainForm_domain_jgbm").val();
		 if(jgbm == null || jgbm == "" || jgbm == "0"){
		 	showAlert("请选择公司!");
		 	return;
		 }
		 var wlryFlxh = $("#mainForm_domain_wlryFlxh").val();
		 var xm = $("#mainForm_domain_xm").val();
		 var dz = $("#mainForm_domain_dz").val(); 
		 //wlryFlxh="1000000026";
		//请求表格数据
		var url = jcontextPath+"/bggl/bgwlry!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.jgbm":jgbm,"domain.wlryFlxh":wlryFlxh,
		 	"domain.xm":encodeURI(xm),"domain.dz":encodeURI(dz)}	//请求的参数，json格式
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
		    colNames:['操作', '外联人员登记序号(SEQ_BG_DJXH)','分类名称','姓名','地址','电话','传真','手机','E-MAIL',
				   '创建人','创建日期','修改人','修改日期',''],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
		      {name:'wlryDjxh', index:'wlryDjxh', width:'0',hidden:true}, 
		      {name:'flmc', index:'flmc', width:'80', align:'center'}, 
		      {name:'xm', index:'xm', width:'60', align:'center'}, 
		      {name:'dz', index:'dz', width:'100', align:'center'}, 

		      {name:'dh', index:'dh', width:'100', align:'center'}, 
		      {name:'cz', index:'cz', width:'90', align:'center'}, 
		      {name:'sj', index:'sj', width:'80', align:'center'}, 
		      {name:'dy', index:'dy', width:'90', align:'center'}, 
		      
		      {name:'cjrMc', index:'cjrMc', width:'80', align:'center'}, 
		      {name:'cjrq', index:'cjrq', width:'90', align:'center'}, 
		      {name:'xgrMc', index:'xgrMc', width:'80', align:'center'}, 
		      {name:'xgrq', index:'xgrq', width:'90', align:'center'},
		      {name:'jgbm', index:'jgbm', width:'0',hidden:true}
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum%>,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[20,50,100,300,500],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'WLRY_DJXH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
		    sortorder: 'DESC',				//默认排序方向
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
		       	   $("#mainForm").attr("action",jcontextPath+"/bggl/bgwlry!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val1 = jQuery("#dataList").jqGrid('getCell', cl,"wlryDjxh"); 	  //获取当前单元格里面的登记序号
                var val2 = jQuery("#dataList").jqGrid('getCell', cl,"jgbm"); 	  	  //获取当前单元格里面的机构编码 
                var link = "<a href=\"javascript:onUpdate('"+val1+"','"+val2+"')\"><font color=\"blue\">修改</font></a>"
                + " <a href=\"javascript:onDelete('"+val1+"')\"><font color=\"blue\">删除</font></a>";
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
     /**************************************分页结束****************************************/

</script>
</head>
<body>
<s:form action="bgwlry!query" namespace="/bggl" method="post" id="mainForm" name="mainForm">
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">操作：</li>
		    <li><a href="#" id="queryBtn" class="licon04">检 索</a></li>
		    <li><a href="#" id="addBtn" class="licon01">增 加</a></li>
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
		          		<td width="8%" align="right">单位：</td>
		          		<td width="21%">
		          			<sys:gsList myName="domain.jgbm" myId="mainForm_domain_jgbm" mcContainDmBz="Y" contaisQxz="true" myClass="select" onChange="commonInit('gs-wlryfl',this.value, '', 'domain.wlryFlxh', 'mainForm_domain_wlryFlxh','Y', 'Y')"/>
		          		</td>
		          		<td width="10%" align="right">外联人员分类：</td>
		          		<td width="21%"><select name="domain.wlryFlxh" id="mainForm_domain_wlryFlxh" class="select"/></td>
		        		<td width="8%" align="right">姓名：</td>
		          		<td width="21%"><s:textfield name="domain.xm" cssClass="pop_input bgstyle_optional"></s:textfield></td>
		        	<tr>
		        	<tr>
		          		<td width="8%" align="right">地址：</td>
		          		<td width="21%"><s:textfield name="domain.dz" cssClass="pop_input bgstyle_optional"></s:textfield></td>
		          		<td colspan="4"></td>
		        	</tr>
		   		</table>
			</fieldset>
	  	</div>
	  	
		<!-- 分页表格 id必须为dataList -->
		<table id="dataList"><tr><td/></tr></table> 
		<!-- 分页导航 -->
		<div id="pager"></div>
		<%@include file="/common/message.jsp" %>
	</div>
</s:form>
</body>
</html>
