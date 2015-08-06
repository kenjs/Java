<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>货运-托运单管理-复制</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
		
			//查询按钮事件
			$("#queryBtn").click(function(){
				
				if(!checkRq()){
					return;
				}
				onRefresh();
			});
			
			$("#closeBtn").click(function(){
				window.close();
			});
			//初始化表格
			initDataGrid();	
			
			$("#readyBtn").click(function(){
				var checkbox=$(":checked[name='box']");
				if (checkbox.length <= 0) {
					showAlert("请选择一个用于复制的托运单！");
					return;
				}
				window.dialogArguments.initTydFromCopy(checkbox.val());
				window.close();
			})
	});
	
	window.onload = function init() {
		onRefresh();
	}
	
	function checkRq(){
		var xdrqQ = $("#mainForm_domain_xdrqQ").val();
		var xdrqZ = $("#mainForm_domain_xdrqZ").val();
		if(trim(xdrqQ)==''||trim(xdrqZ)==''){
			showError("请输入下单日期");
			return false;
		}
		return true;
	}
	function getDdDjhx(ddDjxh,index){
		//alert(ddDjxh);
		var checkbox=$(":checkbox[name='box']");
		for(var i=0;i<checkbox.length;i++){
			checkbox[i].checked=false;
		}
		checkbox[index].checked=true;
	}	

    
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		var fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		var fhrMc = $("#mainForm_domain_fhrMc").val();
		var shrMc = $("#mainForm_domain_shrMc").val();
		var xdrqQ = $("#mainForm_domain_xdrqQ").val();
		var xdrqZ = $("#mainForm_domain_xdrqZ").val();
		//请求表格数据
		var url = jcontextPath+"/hygl/hytydgl!queryCopy.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.fhrDjxh":fhrDjxh,"domain.fhrMc":encodeURI(fhrMc), "domain.shrMc":encodeURI(shrMc),
		 			"domain.xdrqQ":xdrqQ,"domain.xdrqZ":xdrqZ
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
		    rownumbers : true,					//序号列
			width:pageWidth()-10,  
			height:pageTableHeight()-90,	
		    gridComplete: myGridComplete,		//表格加载完毕事件
		    shrinkToFit:false, 
		    colNames:['操作', '订单登记序号','订单编号','始发地', '目的地', '总收入', '客户名称', 
		              '下单日期', '要求发货日期', '收货人名称', '要求到货日期', '收货方式','登记人',
		              '登记部门','所属机构'],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
			  {name:'ddDjxh', index:'ddDjxh', width:'0', hidden:true, align:'center'}, 
			  {name:'ddbh', index:'ddbh', width:'90', align:'center'}, 
		      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'50',align:'center'}, 
		      {name:'shrXzqhMc', index:'shrXzqhMc', width:'50', align:'center'}, 
			  {name:'srHj', index:'srHj', width:'70', align:'center'},
			  
			  {name:'fhrMc', index:'fhrMc', width:'100', align:'center'}, 
			  {name:'xdrq', index:'xdrq', width:'70', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}, 
		      {name:'yqFhrq', index:'yqFhrq', width:'70', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}, 
		      {name:'shrMc', index:'shrMc', width:'100', align:'center'},
		      {name:'yqDdrq', index:'yqDdrq', width:'70', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      {name:'shfsMc', index:'shfsMc', width:'70', align:'center'},
		      {name:'djrMc', index:'djrMc', width:'80',  align:'center'},
		      {name:'djJgmc', index:'djJgmc', width:'80',  align:'center'},
		      {name:'ssJgmc', index:'ssJgmc', width:'80', align:'center'}
		      ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: -1,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[-1],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'ddDjxh,fhrMc',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
		    sortorder: 'DESC,ASC',				//默认排序方向
		    viewrecords: true,					//定义是否要显示总记录数
		    gridview: false,					//默认false 构造一行数据后添加到grid中，如果设为true则是将整个表格的数据都构造完成后再添加到grid中，但treeGrid, subGrid, or afterInsertRow 不能用	
		    jsonReader: {     
	     	 	root: 	 "domain.copyList",   				// 数据行（默认为：rows）
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
		  /* $("#dataList").jqGrid('navButtonAdd','#pager',{
		       caption:"", 
		       onClickButton : function () { 
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/hytydgl!download");
				   $("#mainForm").submit();
		       } 
		 }); */
		 
	}
	
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
               var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh"); 
				var link = '<input type="checkbox" name="box" value="'+ddDjxh+'"  onclick="getDdDjhx('+ddDjxh+','+i+')"/>';
         
             
               	
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
	
    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接

       
   	
     /**************************************分页结束****************************************/

</script>
</head>
<body>
<s:form action="hytydgl!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.fhrDjxh" />
	<s:hidden name="domain.fhrMc" />
	
	<div class="right_cont">
	  <div id="divQuery">
		<fieldset>
			<legend>查询条件</legend>
		   <table width="95%" border="0" cellspacing="0" cellpadding="0">
		        
		        <tr>
	        	  <td width="15%" align="right">下单日期：</td>
		          <td width="35%">
		          		   <sys:dateFirstDLastMonthTag myName="domain.xdrqQ" myId="mainForm_domain_xdrqQ" myClass="ymdate" />
	          			～
	          		<sys:dateCurrentDayTag myName="domain.xdrqZ" myId="mainForm_domain_xdrqZ" myClass="ymdate" />
	          		</td>
		          <td width="15%" align="right">收货单位：</td>
		          <td width="35%">
		          	<s:textfield name="domain.shrMc" cssClass="pop_input bgstyle_optional"></s:textfield>
		          </td>
		        </tr>
		   </table>
		</fieldset>
	  </div>
  		 <div class="pop_btn">
	    	<button type="button" class="pop_btnbg" id="queryBtn">检 索</button>
		 	&nbsp;
		 	<button type="button" class="pop_btnbg" id="readyBtn">确 定</button>
		 	&nbsp;
		    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
	    </div>
  
		<!-- 分页表格 id必须为dataList -->
		<table id="dataList"><tr><td/></tr></table> 
		<!-- 分页导航 -->
		</div>
		<%@include file="/common/message.jsp" %>
	</div>
</s:form>
</body>
</html>
