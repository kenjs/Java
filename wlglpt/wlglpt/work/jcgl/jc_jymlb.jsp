<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>毛利表</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
			document.getElementById("maincont").onmousewheel=hideHelpWindow;
			initHykhData(300);
			//查询按钮事件
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//初始化表格
			initDataGrid();

	});
    
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var fcrqS = $("#mainForm_domain_fcrqS").val();
		var fcrqZ = $("#mainForm_domain_fcrqZ").val();
		var fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		var fhrMc = $("#mainForm_domain_fhrMc").val();
		var ddbh = $("#mainForm_domain_ddbh").val();
		//请求表格数据
		var url = jcontextPath+"/jcgl/jymlb!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.fcrqS":fcrqS,"domain.fcrqZ":fcrqZ,
		 		"domain.fhrDjxh":fhrDjxh,"domain.fhrMc":encodeURI(fhrMc),"domain.ddbh":ddbh}								//请求的参数，json格式
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
			//footerrow: true,
			//userDataOnFooter: true,
		    gridComplete: myGridComplete,		//表格加载完毕事件
		    shrinkToFit:false, 
		    colNames:['序号','单位名称','客户名称','下单日期','托单号','车辆号码','目的地','回单号','收入','回扣','运输费','提货费','配送费',
				     '物流损失收入','物流损失支出','毛利','毛利率'],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[ 
			  {name:'pageXh', index:'pageXh', sortable:false, width:'60', align:'center'},
			  {name:'ssjgMc',index:'ssjgMc',width:'120',align:'center'},
		      {name:'khmc',index:'khmc',width:'150', align:'center'}, 
		      {name:'xdrq',index:'xdrq',width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}, 
		      {name:'ddbh', index:'ddbh', width:'100', align:'center'}, 
		      {name:'clhm', index:'clhm', width:'80', align:'center'}, 
		      {name:'mdd', index:'mdd', width:'60', align:'center'}, 
		      {name:'hdbh', index:'hdbh', width:'40', align:'center'}, 
		      
		      {name:'zsr', index:'zsr', width:'60', align:'center'}, 
			  {name:'hk', index:'hk', width:'60', align:'center'},
		      {name:'ysf', index:'ysf', width:'60', align:'center'}, 
		      {name:'thf', index:'thf', width:'60', align:'center'}, 
		      {name:'psf', index:'psf', width:'60', align:'center'}, 

		      {name:'wlssSr', index:'wlssSr', width:'80', align:'center'}, 
		      {name:'wlssZc', index:'wlssZc', width:'80', align:'center'}, 
		      {name:'ml', index:'ml', width:'60', align:'center'},
		      {name:'mll', index:'mll', width:'60', align:'center'}
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum%>,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[20,50,100,300,500],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'XDRQ',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
		    sortorder: 'DESC',				//默认排序方向
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
		  $("#dataList").jqGrid('navButtonAdd','#pager',{
		       caption:"", 
		       onClickButton : function () { 
		       	   $("#mainForm").attr("action",jcontextPath+"/jcgl/jymlb!download.action");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i]; 
                var xh = jQuery("#dataList").jqGrid('getCell',cl,"pageXh");
                if (xh == 0) {
                	$("#dataList").jqGrid('setRowData', cl, { 'pageXh': '合计' });
                	continue;
               }               
            }

     }
     /**************************************分页结束****************************************/

</script>
</head>
<body>
<s:form action="jymlb!query" namespace="/jcgl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="fhrData" />
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
	
	<div id="maincont" style="display: none;"></div>
	
	<div class="right_cont">
		<div id="divQuery">
	    		<fieldset>
		      		<legend>查询条件</legend>
		      		<table width="99%" border="0" cellspacing="0" cellpadding="0">
		        		<tr>
		          			<td width="8%" align="right">业务单位：</td>
		          			<td width="15%">
		          				<sys:csGsList myClass="select" myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y"/>
				  			</td>
				  			<td width="8%" align="right">下单日期：</td>
		          			<td width="19%">
		          				 <sys:dateFirstDMonth myName="domain.fcrqS" myId="mainForm_domain_fcrqS" myClass="ymdate" />
          							～
          			  			 <sys:dateCurrentDayTag myName="domain.fcrqZ" myId="mainForm_domain_fcrqZ" myClass="ymdate" />
				  			</td>				  			
		        		</tr>
		        		<tr>
		        			<td align="right">托单号：</td>
		          			<td>
		          				<s:textfield name="domain.ddbh" cssClass="pop_input bgstyle_optional"></s:textfield>
				  			</td>
		        			<td align="right">客户名称：</td>
		          			<td>
		          				<s:hidden name="domain.fhrDjxh"></s:hidden>
  								<div class="inputsel" style="width: 230px; ">
  									<s:textfield name="domain.fhrMc" cssClass="pop_input noborder inputext" cssStyle="width:200px;"></s:textfield>
  									<a href="#" class="icon_arrow" id="fhr" onFocus="this.blur()"></a>
  								</div>
			  					<div class="inputsc">
		              			<div id="inputSel_fhr" class="inputselcont inputselFixedSize ac_results">
		              			</div>
		            			</div>
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
