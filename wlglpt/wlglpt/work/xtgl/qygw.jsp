<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>企业-岗位</title>
<%@ include file="/common/meta.jsp"%>
<style type="text/css">
html,body {overflow:hidden;}
.bzsm {width:100%;height:18px;border:0;background:transparent;float:left;}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		//查询按钮事件
		$("#queryBtn").click(function(){
			onRefresh();
		});
			
		//新增按钮事件
		$("#addBtn").click(function(){
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val());
			if(ssJgbm == "" || ssJgbm == null || ssJgbm == "0"){
				showAlert("请选择部门！");
				return;
			}
			var url = jcontextPath+"/xtgl/gwwh!initMx.action?domain.ssJgbm="+ssJgbm;
			window.showModalDialog(url,window,"dialogHeight:420px;dialogWidth:520px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
			onRefresh();
		});
		
		$("#xtgwBtn").click(function(){
			var ssJgbm = trim($("#mainForm_domain_ssJgbm").val());
			if(ssJgbm == "" || ssJgbm == null || ssJgbm == "0"){
				showAlert("请选择部门！");
				return;
			}
			var url = jcontextPath+"/xtgl/gwwh!initXtgwMx.action?domain.ssJgbm="+ssJgbm;
			window.showModalDialog(url,window,"dialogHeight:380px;dialogWidth:580px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
			onRefresh();
		});
			
		var sjJgbm = $("#mainForm_domain_dwDjxh").val();
		bmInit(sjJgbm, '', "domain.ssJgbm", "mainForm_domain_ssJgbm", "Y", "Y");
			
		//初始化表格
		initDataGrid();
			
	});
	
	 function onUpdate(gwDjxh){
		var ssJgbm = trim($("#mainForm_domain_ssJgbm").val());
    	var url = jcontextPath+"/xtgl/gwwh!initMx.action?domain.gwDjxh="+gwDjxh+"&domain.ssJgbm="+ssJgbm;
    	window.showModalDialog(url,window,"dialogHeight:420px;dialogWidth:520px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		onRefresh();
    }
    
    var keyValue = "";
	function onDelete(gwDjxh){
		keyValue = gwDjxh;
		showConfirm("确定要删除么？","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.gwDjxh":keyValue};
		 var url = jcontextPath+"/xtgl/gwwh!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("删除成功！");
        onRefresh();
	}	
	
	var val1 = ""
	function onStart(gwDjxh){
		val1 = gwDjxh;
		showConfirm("您确定要启用么？","start");
	}
	
	function start(){
		var url = jcontextPath+"/xtgl/gwwh!saveEnable";
		var jsonObj = {"domain.gwDjxh":val1,"domain.qybz":"Y"};
		ajaxCommon(url,jsonObj , "startSuccess");
	}
	
	function startSuccess(){     
        showAlert("启用成功！");
        onRefresh();
	}	
	
	var val2 = "";
	function onStop(gwDjxh){
		val2 = gwDjxh;
		showConfirm("您确定要停用么？","stop");
	}
	
	function stop(){
		var url = jcontextPath+"/xtgl/gwwh!saveDisable";
		var jsonObj = {"domain.gwDjxh":val2,"domain.qybz":"N"};
		ajaxCommon(url,jsonObj , "stopSuccess");
	}
	
	function stopSuccess(){     
        showAlert("停用成功！");
        onRefresh();
	}	
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		var dwDjxh = $("#mainForm_domain_dwDjxh").val();
		var ssJgbm = trim($("#mainForm_domain_ssJgbm").val());
		//ssJgbm = "1000000124";
		if(ssJgbm == "" || ssJgbm == null || ssJgbm == "0"){
			ssJgbm = dwDjxh;
		}
		//请求表格数据
		var url = jcontextPath+"/xtgl/gwwh!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm}								//请求的参数，json格式
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
	    colNames:['操作','标志','状态','','岗位名称','岗位简称','所在部门','系统岗位代码','','创建人','创建日期','修改人','修改日期','备注说明'],			 //name 列显示的名称
	     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
	    colModel :[ 
		  {name:'hstoperationcol', index:'', sortable:false, width:'100', align:'center'},
		  
		  {name:'lybzStr', index:'lybzStr', width:'40', align:'center'},
		  {name:'qybzZt', index:'qybzZt', width:'40', align:'center'}, 
		  {name:'qybz', index:'qybz', width:'0', align:'center',hidden:true},
	      {name:'gwMc', index:'gwMc', width:'150', align:'center'}, 
	      {name:'gwJc', index:'gwJc', width:'150', align:'center'},
	      {name:'bmMc', index:'bmMc', width:'150', align:'center'},
	      {name:'gwDm', index:'gwDm', width:'90', align:'center'},
	      {name:'gwDjxh', index:'gwDjxh', width:'0', align:'center',hidden:true},
	      {name:'cjrMc', index:'cjrMc', width:'80', align:'center'},
	      {name:'cjrq', index:'cjrq', width:'80', align:'center'},
	      {name:'xgrMc', index:'xgrMc', width:'80', align:'center'},
	      {name:'xgrq', index:'xgrq', width:'80', align:'center'},   
	      {name:'bzsm', index:'bzsm', width:'150', align:'center'}
	      
	   
	    ],
	    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
	    rowNum: <%=rowNum%>,							//在grid上显示记录条数，这个参数是要被传递到后台
	    rowList:[20,50,100,300,500],		                //一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
	    sortname: 'GW_DM',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
	    sortorder: 'ASC',				//默认排序方向
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
	       	   $("#mainForm").attr("action",jcontextPath+"/xtgl/gwwh!download");
			   $("#mainForm").submit();
	       } 
	 });
		 
	}
	
    //表格加载完毕后触发事件
	function myGridComplete() {
           var graduateIds = $("#dataList").jqGrid('getDataIDs');
           for (var i = 0; i < graduateIds.length; i++) {
               var cl = graduateIds[i];
               var val = jQuery("#dataList").jqGrid('getCell', cl,"gwDjxh"); 	  //获取当前单元格里面的登记序号 
               var value = jQuery("#dataList").jqGrid('getCell', cl,"qybz");
                var bzVal = jQuery("#dataList").jqGrid('getCell', cl,"lybzStr");
               var lin1 = "";
               var lin2 = "";
               if(value == "N"){
            	   lin1 = "<font color=\"red\">停用</font>";
            	   lin2= "<a href=\"javascript:onStart('"+val+"')\"><font color=\"blue\">启用</font></a>";
               }else{
            	   lin1 = "<font color=\"black\">启用</font>";
            	   lin2= "<a href=\"javascript:onStop('"+val+"')\"><font color=\"blue\">停用</font></a>";
               }
               if(bzVal=="系统"){
               		var link = " <a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">删除</font></a> " + lin2;
               }else{
               		var link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">修改</font></a>"
               	 	+ " <a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">删除</font></a> " + lin2;
               }
               
               $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link });
               $("#dataList").jqGrid('setRowData', cl, { 'qybzZt': lin1 });
           }
     }
     /**************************************分页结束****************************************/


</script>
</head>

<body>
	<s:form action="gwwh!query" namespace="/xtgl" method="post" id="mainForm" name="mainForm">
		<!-- 操作区 -->
		<div class="right_btnbg">
  			<ul class="lcont">
    			<li class="no">操作：</li>
    			<li><a href="#" id="queryBtn" class="licon04">检 索</a></li>
    			<li><a href="#" id="addBtn" class="licon01">增 加</a></li>
    			<li><a href="#" id="xtgwBtn" class="licon07">系统岗位</a></li>
    			<li><a href="#" id="closeBtn" class="licon03">关 闭</a></li>
  			</ul>
  			<ul class="rcont">
    			<li class="ricon02" onClick="slideToggle('syquery')">显示/隐藏查询条件</li>
    			<li class="ricon03">帮助</li>
  			</ul>
		</div>
		
		<div class="right_cont">  
  			<div id="divQuery">
	    		<fieldset>
		      		<legend>查询条件</legend>
		      		<table width="95%" border="0" cellspacing="0" cellpadding="0">
		        		<tr>
		          			<td width="2%" align="right">单位：</td>
		          			<td width="15%">
		          				<sys:qxGsList myId="mainForm_domain_dwDjxh" myName="domain.dwDjxh" mcContainDmBz="Y" myClass="select" onChange="bmInit(this.value, '', 'domain.ssJgbm', 'mainForm_domain_ssJgbm', 'Y', 'Y')" ></sys:qxGsList>
				  			</td>
				  			<td width="2%" align="right">部门：</td>
		          			<td width="15%">
		          				<select name="domain.ssJgbm" id="mainForm_domain_ssJgbm" class="select" />
				  			</td>
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
