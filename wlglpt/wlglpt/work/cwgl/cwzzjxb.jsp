<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>财务-周转金下拨</title>
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
			onRefresh();	

	});
	function onUpdate(zjdbDjxh){
		//alert(zjdbDjxh);
    	var url = jcontextPath+"/cwgl/cwzzjxb!initMx.action?domain.zjdbDjxh="+zjdbDjxh;
    	window.showModalDialog(url,window,"dialogHeight:420px;dialogWidth:580px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
    	onRefresh();
    }
    function onAdd(zjdbDjxh,jsDwDjxh){
    	//alert(jsDwDjxh);
    	zjdbDjxh = "";
    	var url = jcontextPath+"/cwgl/cwzzjxb!initMx.action?domain.zjdbDjxh="+zjdbDjxh+"&domain.jsDwDjxh="+jsDwDjxh;
    	window.showModalDialog(url,window,"dialogHeight:420px;dialogWidth:580px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
    	onRefresh();
    }
    
    var keyValue = "";
	function onDelete(zjdbDjxh){
		keyValue = zjdbDjxh;
		showConfirm("确定要撤销么？","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.zjdbDjxh":keyValue};
		 var url = jcontextPath+"/cwgl/cwzzjxb!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("撤销成功！");
        onRefresh();
	}	
    
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		var rq = trim($("#mainForm_domain_rq").val()); 
		//请求表格数据
		var url = jcontextPath+"/cwgl/cwzzjxb!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.rq":rq}								//请求的参数，json格式
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
		    colNames:['序号','资金调拨登记序号(SEQ_CW_DJXH)','接收单位序号','接收单位','资金需求','结存合计','结存现金',
				     '结存油卡','结存存款','支付合计','支付预付款','支付余款',
				     '支付报销费用','支付其他','备用金','下拨金额','下拨日期', '下拨人','备注','操作'],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[ 
		      {name:'pageXh', index:'pageXh', width:'30', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pageXh' + rowId + '\'';
			    }
			  },
		      {name:'zjdbDjxh', index:'zjdbDjxh', width:'100', align:'center',hidden:true}, 
		      {name:'jsDwDjxh', index:'jsDwDjxh', width:'100', align:'center',hidden:true}, 
		      {name:'jsDwMc', index:'jsDwMc', width:'120', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'jsDwMc' + rowId + '\'';
			    }
			  },
		     
		      {name:'zjxq', index:'zjxq', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'zjxq' + rowId + '\'';
			    }
			  },
		     
		      {name:'jcHj', index:'jcHj', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'jcHj' + rowId + '\'';
			    }
			  },
		      {name:'jcXj', index:'jcXj', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'jcXj' + rowId + '\'';
			    }
			  },
		      {name:'jcYk', index:'jcYk', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'jcYk' + rowId + '\'';
			    }
			  },
		      {name:'jcCk', index:'jcCk', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'jcCk' + rowId + '\'';
			    }
			  },
		     
		      {name:'zfHj', index:'zfHj', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'zfHj' + rowId + '\'';
			    }
			  },
		      {name:'zfYfk', index:'zfYfk', width:'80', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'zfYfk' + rowId + '\'';
			    }
			  },
		      {name:'zfYk', index:'zfYk', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'zfYk' + rowId + '\'';
			    }
			  },
		      {name:'zfBxfy', index:'zfBxfy', width:'90', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'zfBxfy' + rowId + '\'';
			    }
			  },
		      {name:'zfQt', index:'zfQt', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'zfQt' + rowId + '\'';
			    }
			  },
		      
		      {name:'byj', index:'byj', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'byj' + rowId + '\'';
			    }
			  },
			  {name:'xbje', index:'xbje', width:'60', align:'center'}, 
			  {name:'xbrq', index:'xbrq', width:'80', align:'center'},
			  {name:'xbrMc', index:'xbrMc', width:'80', align:'center'},
		      {name:'bz', index:'bz', width:'180', align:'center'},		      
		       
		      
		      {name:'hstoperationcol', index:'', sortable:false, width:'90', align:'center'}
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum%>,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[20,50,100,300,500],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'ZJDB_DJXH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
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
		       	   $("#mainForm").attr("action",jcontextPath+"/cwgl/cwzzjxb!download.action");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"zjdbDjxh"); 	  //获取当前单元格里面的登记序号 
                var jsDwDjxh = jQuery("#dataList").jqGrid('getCell', cl,"jsDwDjxh"); 	  //获取当前单元格里面的登记序号 
                if(val==null||val==""){
                	 var link = "<a href=\"javascript:onAdd('"+val+"','"+jsDwDjxh+"')\"><font color=\"blue\">下拨</font></a>"
                }else{
                	 var link = "<a href=\"javascript:onAdd('"+val+"','"+jsDwDjxh+"')\"><font color=\"blue\">下拨</font></a>"
                	 			+ " <a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">修改</font></a>"
                				+ " <a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">撤销</font></a>";
                }
                
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
       var gridName = "dataList";
	   var a = ['pageXh','jsDwMc','zjxq','jcHj','jcXj','jcYk','jcCk','zfHj','zfYfk','zfYk','zfBxfy','zfQt','byj']; 

       Merger(gridName, 'pageXh', a);

     }
     /**************************************分页结束****************************************/

</script>
</head>
<body>
<s:form action="cwzzjxb!query" namespace="" method="post" id="mainForm" name="mainForm">
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">操作：</li>
		    <li><a href="#" id="queryBtn" class="licon04">检 索</a></li>
		    <li><a href="#" id="closeBtn" class="licon03">关闭</a></li>
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
	          <td align="right" width="12%">日期：</td>
     		  <td width="21%">
   				   <s:textfield name="domain.rq" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
     		  </td>
	          <td width="66%"></td>
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
