<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>企业-审批文书-审批流程设置</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>

<script type="text/javascript">
	$(document).ready(function(){
			//查询按钮事件
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//初始化表格
			initDataGrid();
			
			//initSpwsXmfl();
			initList();
			initWs();
			$("#mainForm_domain_wsDm").change(function(){
				initSpwsXmfl();
			});
					
	});
	function initList() {
		var dwDm = $("#mainForm_domain_dwDm").val(); 
		var jsonObj = {"domain.paramdm":dwDm,
			"domain.defaultValue":"",
			"domain.currentObjName":"domain.ssJgbm",
			"domain.currentObjId":"mainForm_domain_ssJgbm",
			"domain.containQbBz":"Y",
			"domain.mcContainDmBz":"Y"};
	
		var url=jcontextPath+"/common/wlglptCommon!bmInit";	
		ajaxCommon(url,jsonObj,"changeBmList");
	}
	function changeBmList(data){
		var list = data.domain.dataList;
		$("#"+data.domain.currentObjId).empty();
		$.each(list, function(i,domain){
		   var option = document.createElement('option');
		    $("#"+data.domain.currentObjId)[0].add(option);
		    
		    $(option).text(domain.mc).val(domain.dm);
		    //默认选中
		    if(data.domain.defaultValue==domain.dm){
		    	$(option).attr("selected","selected");
		    	$(option).text(domain.mc).val(domain.dm);
		    }
		});
	}
	function initWs() {
		var zgsbm = $("#mainForm_domain_zgsbm").val(); 
		var jsonObj = {"domain.paramdm":zgsbm,
			"domain.defaultValue":"",
			"domain.currentObjName":"domain.wsDm",
			"domain.currentObjId":"mainForm_domain_wsDm",
			"domain.containQbBz":"Y",
			"domain.mcContainDmBz":"Y"};
	
		var url=jcontextPath+"/common/wlglptCommon!wsInit";	
		ajaxCommon(url,jsonObj,"changeWsList");
	}
	
	function changeWsList(data){
		var list = data.domain.dataList;
		$("#"+data.domain.currentObjId).empty();
		$.each(list, function(i,domain){
		    var option = document.createElement('option');
		    $("#"+data.domain.currentObjId)[0].add(option);
		    
		    $(option).text(domain.mc).val(domain.dm);
		    //默认选中
		    if(data.domain.defaultValue==domain.dm){
		    	$(option).attr("selected","selected");
		    	$(option).text(domain.mc).val(domain.dm);
		    }
		});
		//改变项目分类list
		initSpwsXmfl();
	}
	
	function initSpwsXmfl() {
		var wsDm = $("#mainForm_domain_wsDm").val();
		commonInit("SpwsXmfl", wsDm, "", "domain.xmflDjxh", "mainForm_domain_xmflDjxh", "Y", "Y");
	}

    function onUpdate(splcSzxh,wsDm,xmflDjxh,dwDm,ssJgbm){
    	var curDwbm="";
    	if(undefined==ssJgbm || null==ssJgbm || ""==ssJgbm){
    		curDwbm=dwDm;
    	}else{
    		curDwbm=ssJgbm;
    	}
    	var url = jcontextPath+"/dzgl/qyspwssplcsz!initMx?domain.splcSzxh="+splcSzxh+"&domain.curDwbm="+curDwbm+"&domain.wsDm="+wsDm+"&domain.xmflDjxh="+xmflDjxh;
    	if(undefined==splcSzxh || null==splcSzxh || ""==splcSzxh){
    		url+="&domain.dwDm="+dwDm+"&domain.ssJgbm="+ssJgbm
    	}
    	window.showModalDialog(url,window,"dialogHeight:620px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
    	onRefresh();
    	//window.open(url);
    	//popwindow(jcontextPath+"/dzgl/qyspwssplcsz!initMx?domain.splcSzxh="+splcSzxh, 760, 488);
    }
    
    var keyValue = "";
	function onDelete( splcSzxh){
		keyValue = splcSzxh;
		showConfirm("确定要删除么？","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.splcSzxh":keyValue};
		 var url = jcontextPath+"/dzgl/qyspwssplcsz!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("删除成功！");
        onRefresh();
	}	
    
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		var dwDm = $("#mainForm_domain_dwDm").val(); 
		var ssJgbm = $("#mainForm_domain_ssJgbm").val(); 
		var wsDm = $("#mainForm_domain_wsDm").val(); 
		var xmflDjxh = $("#mainForm_domain_xmflDjxh").val(); 
  
		//请求表格数据
		var url = jcontextPath+"/dzgl/qyspwssplcsz!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.dwDm":dwDm,"domain.ssJgbm":ssJgbm,"domain.wsDm":encodeURI(wsDm),"domain.xmflDjxh":xmflDjxh}								//请求的参数，json格式
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
		    colNames:['操作', '审批流程设置序号','文书所属部门','文书代码','项目分类代码','单位','部门','业务分类','业务环节','文书','项目','审批流程','审批时限','工作日标志','权重系数','创建人',
				     '创建日期','修改人','修改日期'],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
		      {name:'splcSzxh', index:'splcSzxh', hidden:true, width:'80', align:'center'}, 
		      {name:'ssJgbm', index:'ssJgbm', hidden:true, width:'80', align:'center'}, 
		      {name:'wsDm', index:'wsDm', hidden:true, width:'80', align:'center'},
		      {name:'xmflDjxh', index:'xmflDjxh', hidden:true, width:'80', align:'center'},  
		      {name:'dwMc', index:'dwMc', width:'150', align:'center'}, 
		      {name:'ssJgmc', index:'ssJgmc', width:'100', align:'center'}, 
		      {name:'ywflMc', index:'ywflMc', width:'80', align:'center'},
			  {name:'ywhjMc', index:'ywhjMc', width:'80', align:'center'},
		      {name:'wsMc', index:'wsMc', width:'100', align:'center'}, 
		      {name:'xmflmc', index:'xmflmc', width:'100', align:'center'}, 
		      {name:'splc', index:'splc', width:'300', align:'center'}, 
		      {name:'zssx', index:'zssx', width:'80', align:'center'}, 
		      {name:'gzrbz', index:'gzrbz', width:'70', align:'center',hidden:true},
			  {name:'qzxsbz', index:'qzxsbz', width:'80', align:'center'},
			 
			  
			  
		      {name:'cjrMc', index:'cjrMc', width:'80', align:'center'}, 
		      {name:'cjrq', index:'cjrq', width:'80', align:'center'}, 
		      {name:'xgrMc', index:'xgrMc', width:'80', align:'center'}, 
		      {name:'xgrq', index:'xgrq', width:'80', align:'center'}
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum%>,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[20,50,100,300,500],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'SPLC_SZXH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
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
		       	   $("#mainForm").attr("action",jcontextPath+"/dzgl/qyspwssplcsz!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            var dwDm = $("#mainForm_domain_dwDm").val(); 
            var ssJgbm = $("#mainForm_domain_ssJgbm").val(); 
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"splcSzxh"); 	  //获取当前单元格里面的登记序号 
                var wsDm = jQuery("#dataList").jqGrid('getCell', cl,"wsDm");
                var xmflDjxh = jQuery("#dataList").jqGrid('getCell', cl,"xmflDjxh");
                if("0"==xmflDjxh)
                	xmflDjxh="";
                
                var qzxsbz = jQuery("#dataList").jqGrid('getCell', cl,"qzxsbz"); 
                if("Y"==qzxsbz){
                	$("#dataList").jqGrid('setRowData', cl, { 'qzxsbz': "是" });
                }else{
                	$("#dataList").jqGrid('setRowData', cl, { 'qzxsbz': "否" });
                }
                
                var gzrbz = jQuery("#dataList").jqGrid('getCell', cl,"gzrbz"); 
                var zssx = jQuery("#dataList").jqGrid('getCell', cl,"zssx"); 
                if("1"==gzrbz){
                	$("#dataList").jqGrid('setRowData', cl, { 'zssx': zssx+" 工作日" });
                }
                if("2"==gzrbz){
                	$("#dataList").jqGrid('setRowData', cl, { 'zssx': zssx+" 自然日" });
                }	
                var link = "<a href=\"javascript:onUpdate('"+val+"','"+wsDm+"','"+xmflDjxh+"','"+dwDm+"','"+ssJgbm+"')\"><font color=\"blue\">设置</font></a>";
                //+ " <a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">删除</font></a>";
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
     /**************************************分页结束****************************************/

</script>
</head>
<body>
<s:form action="qyspwssplcsz!query" namespace="/dzgl" method="post" id="mainForm" name="mainForm">
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
	   <table width="95%" border="0" cellspacing="0" cellpadding="0">
	   <s:hidden name="domain.zgsbm"></s:hidden>
	        <tr>
	          <td width="10%" align="right">单位：</td>
	          <td width="40%">
	          	<sys:gsList myId="mainForm_domain_dwDm" myName="domain.dwDm" mcContainDmBz="Y" myClass="select" onChange="initList()" />
	          </td>
	          <td width="15%" align="right">部门：</td>
	          <td width="35%">
	          	<select name="domain.ssJgbm" id="mainForm_domain_ssJgbm" class="select"/>
	          </td>
	        </tr>
	        <tr>
        	  <td align="right">文书：</td>
	          <td>
	          	<select name="domain.wsDm" id="mainForm_domain_wsDm" class="select"/>
	          </td>
	          <td align="right">项目分类：</td>
	          <td><select name="domain.xmflDjxh" id="mainForm_domain_xmflDjxh" class="select" /></td>
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
