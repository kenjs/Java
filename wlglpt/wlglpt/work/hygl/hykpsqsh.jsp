<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>开票申请审核</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
	//var myArray=new Array();
	$(document).ready(function(){
			//查询按钮事件
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//批量通过按钮事件
			$("#pltgBtn").click(function(){
				plJudge();
			});
			//批量退回按钮事件
			$("#plthBtn").click(function(){
				plBack();
			});
					
			initDataGrid();
			var shbz = $("input[name='domain.shbz']:checked").val();
			if("Y"==shbz){
				$("#dataList").jqGrid('showCol',["sprMc","sprq","spjg"]);
			}else{
				$("#dataList").jqGrid('hideCol',["sprMc","sprq","spjg"]);
			}
			onDisplay();
	});
	
	function onDisplay(){
		var shbz = $("input[name='domain.shbz']:checked").val();
		if("Y"==shbz){
			$("#shrqTdText").show();
			$("#shrqTdId").show();
		}else{
			$("#shrqTdText").hide();
			$("#shrqTdId").hide();
		}
	}
    
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		var rqQ = trim($("#mainForm_domain_rqQ").val()); 
		var rqZ = trim($("#mainForm_domain_rqZ").val()); 
		var shbz = $("input[name='domain.shbz']:checked").val();
		if(undefined==shbz || null==shbz || ""==shbz){
			showAlert("请您选择未审核或已审核！");
			return;
		}
		if("Y"==shbz){
			$("#dataList").jqGrid('showCol',["sprMc","sprq","spjg"]);
		}else{
			$("#dataList").jqGrid('hideCol',["sprMc","sprq","spjg"]);
		}
  
		//请求表格数据
		var url = jcontextPath+"/hygl/hykpsqsh!query.action";   
		//$("#mainForm").attr("action", "hyfydjsh!query");
		//mainForm.submit();
		$("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.shbz":shbz,"domain.rqQ":rqQ,"domain.rqZ":rqZ}
		 	//请求的参数，json格式
		 }
		 ).trigger("reloadGrid");		//显示到第一页，以免总体数据发生变化的时候，分页的信息混乱
	}
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
	    colNames:['序号','文书审批序号','审批序号','节点序号','<input title="选中/取消选中" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />',
	    		'操作','标志','发送人','发送日期','审批人','审批日期','审批结果','开票申请方式','客户名称','开票金额合计','申请开票日期','备注说明','登记人名称',
	    		'登记部门','所属机构'
	    		],			 //name 列显示的名称
	     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
	    colModel :[
	      {name:'xh', index:'xh', sortable:false, width:'35', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'xh' + rowId + '\'';
		    }
		  },
		  {name:'wsSpxh', index:'wsSpxh', hidden:true, width:'70', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'wsSpxh' + rowId + '\'';
		    }
		  },
		  {name:'spxh', index:'spxh', width:'30', align:'center', hidden:true,
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'spxh' + rowId + '\'';
		    }
		  },
		  {name:'jdxh', index:'jdxh', width:'30', align:'center', hidden:true,
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'jdxh' + rowId + '\'';
		    }
		  },
		  {name:'checkboxoperationcol', index:'checkboxoperationcol', width:'35', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'checkboxoperationcol' + rowId + '\'';
		    }
		  },
		  {name:'hstoperationcol', index:'hstoperationcol', width:'35', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'hstoperationcol' + rowId + '\'';
		    }
		  },
		  {name:'fsthbz', index:'fsthbz', width:'35', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'fsthbz' + rowId + '\'';
		    }
		  },
		  {name:'fsrMc', index:'fsrMc', width:'60', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'fsrmc' + rowId + '\'';
		    }
		  },
		  {name:'fsrq', index:'fsrq', width:'70', sortable:false, align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'fsrq' + rowId + '\'';
		    }
		  },
		 {name:'sprMc', index:'sprMc', width:'65', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'sprMc' + rowId + '\'';
		    }
		  },
		  {name:'sprq', index:'sprq', width:'70', sortable:false, align:'center',  formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'sprq' + rowId + '\'';
		    }
		  },
		  {name:'spjg', index:'spjg', width:'70', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'spjg' + rowId + '\'';
		    }
		  },
		  
		  {name:'kpsqfsMc', index:'kpsqfsMc', width:'85', sortable:false, align:'center'},
		  {name:'khMc', index:'khMc', width:'70', sortable:false, align:'center'},
		  {name:'sqKpjeHj', index:'sqKpjeHj', width:'85', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'sqKpjeHj' + rowId + '\'';
		    }
		  },
		  {name:'sqKprq', index:'sqKprq', width:'85', sortable:false, align:'center',  formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}, 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'sqKprq' + rowId + '\'';
		    }
		  },
		  {name:'bzsm', index:'bzsm', width:'70', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'bzsm' + rowId + '\'';
		    }
		  },
		  {name:'djrMc', index:'djrMc', width:'70', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'djrMc' + rowId + '\'';
		    }
		  },
	      
		  {name:'bmmc', index:'bmmc', width:'120', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'bmmc' + rowId + '\'';
		    }
		  },
		  {name:'dwmc', index:'dwmc', width:'120', sortable:false, align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'dwmc' + rowId + '\'';
		    }
		  }
	    ],
	    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
	    rowNum: -1,							//在grid上显示记录条数，这个参数是要被传递到后台
	    rowList:[-1],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
	    //sortname: 'DD_DJXH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
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
	}
	
	//表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
	function myGridComplete() {
	    var graduateIds = $("#dataList").jqGrid('getDataIDs');
	    for (var i = 0; i < graduateIds.length; i++) {
	        var cl = graduateIds[i];
	        var wsSpxh = jQuery("#dataList").jqGrid('getCell', cl,"wsSpxh");
	        var spxh = jQuery("#dataList").jqGrid('getCell', cl,"spxh");
	        var jdxh = jQuery("#dataList").jqGrid('getCell', cl,"jdxh");
	        var fsthbz = jQuery("#dataList").jqGrid('getCell', cl,"fsthbz");
	        var input = "";
	        var shbz = $("input[name='domain.shbz']:checked").val();
	        var link="";
	        if("N"==shbz){
	        	input = "<input type=\"checkbox\" name=\"xhs\" value=\""+wsSpxh+"#"+spxh+"#"+jdxh+"\" />";
	        	$("#dataList").jqGrid('setRowData', cl, { 'checkboxoperationcol': input }); 
	        	
	         	link = "<a href=\"javascript:doWsSh('"+wsSpxh+"','"+spxh+"')\"><font color=\"blue\">审核</font></a>";
	        	$("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
	        }else{
	        	input = "<input type=\"checkbox\" name=\"xhs\" disabled=\"disabled\" value=\""+wsSpxh+"#"+spxh+"#"+jdxh+"\" />";
	        	$("#dataList").jqGrid('setRowData', cl, { 'checkboxoperationcol': input }); 
	        	
	        	link = "<a href=\"javascript:doWsSh('"+wsSpxh+"','"+spxh+"')\"><font color=\"blue\">查看</font></a>";
	            $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
	        }    
	        if("1"==fsthbz)
	        	$("#dataList").jqGrid('setRowData', cl, { 'fsthbz': "发送" }); 
	        else
	        	$("#dataList").jqGrid('setRowData', cl, { 'fsthbz': "退回" }); 
	    }
	    
	   var gridName = "dataList";
		   var a = ['xh','wsSpxh','spxh','jdxh','hstoperationcol','checkboxoperationcol','fsthbz','fsrMc',
		   'fsrq','spjzsj','cqbz','sprMc','sprq','spjg','kpsqfsMc','khMc','sqKpjeHj','sqKprq','bzsm','djrMc','bmmc','dwmc'];
			
	    Merger(gridName, 'xh', a);
	}
/**************************************分页结束****************************************/

</script>
</head>
<body>
<s:form action="hykpsqsh!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">操作：</li>
		    <li><a href="#" id="queryBtn" class="licon04">检 索</a></li>
		    <li><a href="#" id="pltgBtn" class="licon08">批量审核通过</a></li>
		    <li><a href="#" id="plthBtn" class="licon09">批量退回审批</a></li>
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
			          <td width="20%" align="left">
			          &nbsp;&nbsp;&nbsp;&nbsp;
			          	<s:radio name="domain.shbz" list='#{"N":"未审核","Y":"已审核"}' listKey="key" listValue="value" onclick="javascript:onDisplay();"></s:radio>
			          </td>
			          <td width="15%" align="right" id="shrqTdText">审核日期：</td>
			          <td width="65%" id="shrqTdId">
			          		<s:textfield name="domain.rqQ" readonly="true" cssClass="ymdate"></s:textfield>
							 ～ 
							<s:textfield name="domain.rqZ" readonly="true" cssClass="ymdate"></s:textfield>
			          </td>
			        <tr>
			    </table>
			</fieldset>
	  	</div>
	  	<table id="dataList"><tr><td/></tr></table> 
		<%@include file="/common/message.jsp" %>
	</div>
</s:form>
</body>
</html>
