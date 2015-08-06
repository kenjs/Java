<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>办公-即时通讯</title>
<%@ include file="/common/meta.jsp"%>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>

<style type="text/css">
html,body {overflow:hidden;}
</style>

<script type="text/javascript">
	$(document).ready(function(){
			//查询按钮事件
			$("#queryBtn").click(function(){
				var lxrDjxh = $("#mainForm_domain_lxrDjxh").val();
				if (lxrDjxh == null || lxrDjxh == "") {
					showAlert("请先选择一个联系人！");
					return;
				}
				onRefresh();
			});
			
			//新增按钮事件
			$("#addBtn").click(function(){
				//popwindow(jcontextPath+"/bggl/jstx!initMx");
				var url = jcontextPath+"/bggl/jstx!initMx.action";
				window.showModalDialog(url,window,"dialogHeight:480px;dialogWidth:760px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
				onRefresh();
			});
			//初始化表格
			initDataGrid();
						

	});

    function onUpdate(jstxXh){
    	//popwindow(jcontextPath+"/bggl/jstx!initMx?domain.jstxXh="+jstxXh);
    	var url = jcontextPath+"/bggl/jstx!initMx.action?domain.jstxXh="+jstxXh;
		window.showModalDialog(url,window,"dialogHeight:480px;dialogWidth:760px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;");
		onRefresh();
    }
    
    var keyValue = "";
	function onDelete( jstxXh){
		keyValue = jstxXh;
		showConfirm("确定要删除么？","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.jstxXh":keyValue};
		 var url = jcontextPath+"/bggl/jstx!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("删除成功！");
        onRefresh();
	}	
	
	function initXzqhData(){
		var url = jcontextPath+"/common/wlglptCommon!queryRyList";	
		var targetObjName="domain.xzqhMc";
		var targetDmObjName="domain.xzqhDm";
		var dropDownSelectedCallback="onDropDownSelected";
		var jsonObj = {"targetObjName":targetObjName,"targetDmObjName":targetDmObjName,"itemIndex":0,"dropDownSelectedCallback":dropDownSelectedCallback};
		initData(url,jsonObj);
	}
	
	function onDropDownSelected(li, itemIndex) {
		var sValue = li.selectValue;
		var jsonStr=$("#mainForm_jsonData").val();
	   	if("[]"!=jsonStr){
	    	var data=eval(jsonStr);
	        $(data).each(function(i,item){
	        	if(sValue==item.xzqhQc){
	        		$("#mainForm_domain_xzqhDm").val(item.xzqhDm);
		        	return;
	        	}
	    	});
		}
	}
    
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		//请求表格数据
		var url = jcontextPath+"/bggl/jstx!query.action";   
		var lxrDjxh = $("#mainForm_domain_lxrDjxh").val();
		var jsrqQ = $("#mainForm_domain_jsrqQ").val();
		
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.lxrDjxh":lxrDjxh,"domain.jsrqQ":jsrqQ}								//请求的参数，json格式
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
		    colNames:['即时通讯序号','内容','发送人','发送日期','接收人',
				     '接收标志','接收日期'],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[ 
		      {name:'jstxXh', index:'jstxXh', hidden:true, align:'center'}, 
		      {name:'nr', index:'nr', width:'500', align:'center'},
		      {name:'fsrCzyMc', index:'fsrCzyMc', width:'80', align:'center'}, 
		      {name:'fsrq', index:'fsrq', width:'120', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'}},
		      
		      {name:'czyMc', index:'czyMc', width:'80', align:'center'}, 
		      {name:'jsbz', index:'jsbz', width:'50', align:'center'}, 
		      {name:'jsrq', index:'jsrq', width:'120', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d H:i:s',newformat:'Y-m-d H:i:s'}}
		      ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum%>,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[20,50,100,300,500],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'jstxXh',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
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
		       	   $("#mainForm").attr("action",jcontextPath+"/bggl/jstx!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                
                var jsbz = jQuery("#dataList").jqGrid('getCell', cl,"jsbz"); 
                if (jsbz == "N") {
                	jsbz = "<font color='red'>未接收</font>";
                }else {
                	jsbz = "已接收";
                }
                $("#dataList").jqGrid('setRowData', cl, { 'jsbz': jsbz });
            }
     }
     /**************************************分页结束****************************************/

</script>
</head>
<body>
<s:form action="jstx!query" namespace="/bggl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="dropDownData"></s:hidden>
	<s:hidden name="jsonData"></s:hidden>
	<s:hidden name="targetObjName"></s:hidden>
	<s:hidden name="targetDmObjName"></s:hidden>
	<s:hidden name="itemIndex"></s:hidden>
	<s:hidden name="dropDownSelectedCallback"></s:hidden>
	
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
      	<table width="95%" border="0" cellspacing="0" cellpadding="0">
      		<tr>
	          <td width="15%" align="right">联系人：</td>
	          <td width="35%"><s:textfield name="domain.lxrDjxh" cssClass="pop_input" /></td>
	          <td width="15%" align="right">检索日期起：</td>
	          <td width="35%"><s:textfield name="domain.jsrqQ" cssClass="ymdate" /> </td>
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
