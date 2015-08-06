<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>货物自提</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
$(document).ready(function(){

	//查询按钮事件
	$("#queryBtn").click(function(){
		onRefresh();
	});
	
		//初始化表格
		initDataGrid();
});

function onDisplay(){
	var thbz = $("[name='domain.thbz']:checked").val();
	if("1"==thbz){
		$("#thrqTrId").show();
	}else{
		$("#thrqTrId").hide();
	}
}
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		//请求表格数据
		var thbz = $("input[name='domain.thbz']:checked").val();
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var shrMc = $("#mainForm_domain_shrMc").val();
		var thrqQ = $("#mainForm_domain_thrqQ").val();
		var thrqZ = $("#mainForm_domain_thrqZ").val(); 
		var url = jcontextPath+"/hygl/hwzt!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.shrMc":encodeURI(shrMc),
		 	"domain.thbz":thbz,"domain.thrqQ":thrqQ,"domain.thrqZ":thrqZ}		//请求的参数，json格式
		 }
		 ).trigger("reloadGrid");		//显示到第一页，以免总体数据发生变化的时候，分页的信息混乱
	}
	
	function onRegister(hwztDjxh){
	    var ssJgbm = $("#mainForm_domain_ssJgbm").val(); 	
    	var url = jcontextPath+"/hygl/hwzt!initMx.action?domain.ssJgbm=" + ssJgbm + "&domain.hwztDjxh=" + hwztDjxh;
		window.showModalDialog(url,window,"dialogHeight:250px;dialogWidth:540px;center:yes;resizable:no;status:no;scroll:yes;help:no;");
		onRefresh();
	}
	
	function registerSuccess(){
		showAlert("登记成功！", "onRefresh");
	}
	
	function onDelete(hwztDjxh){
    	var url = jcontextPath+"/hygl/hwzt!delete";
    	var jsonObj = {"domain.hwztDjxh":hwztDjxh};
		ajaxCommon(url,jsonObj,"deleteSuccess");
	}
	
	function deleteSuccess(){
		showAlert("撤销成功！", "onRefresh");
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
		    colNames:['操作','货物自提登记序号','订单编号','状态','收货人','收货地址','收货联系电话','货物','包装','数量','重量','体积','到付','代收货款','结算数量',
		    			'经办人登记序号','经办人','提货日期','提货人','联系电话','身份证号'
			    		],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[
			  {name:'hstoperationcol', index:'', sortable:false, width:'40', align:'center'},
		      {name:'hwztDjxh', index:'hwztDjxh', hidden:true},
		      {name:'ddbh', index:'ddbh', width:'70', align:'center'},
		      {name:'zt', index:'zt', width:'40', align:'center'},
		      {name:'shrMc', index:'shrMc', width:'120', align:'center'}, 
		      {name:'shrDz', index:'shrDz', width:'150', align:'center'},  
		      {name:'shrLxdh', index:'shrLxdh', width:'100', align:'center'},  
		      {name:'hwmc', index:'hwmc', width:'120', sortable:false, align:'center'}, 
		      {name:'bz', index:'bz', width:'60', align:'center'},  
		      {name:'sl', index:'sl', width:'60', align:'center'},  
		      {name:'zl', index:'zl', width:'60', align:'center'},  
		      {name:'tj', index:'tj', width:'60', align:'center'},  
		      {name:'srDf', index:'srDf', width:'60', align:'center'},  
		      {name:'dsHk', index:'dsHk', width:'60', align:'center'},  
		     
		      
		      {name:'jsSl', index:'jsSl', width:'60', align:'center'},  
		      {name:'jbrCzyDjxh', index:'jbrCzyDjxh', hidden:true},
		      {name:'jbrmc', index:'jbrmc', width:'100', sortable:false, align:'center'}, 
		      {name:'thrq', index:'thrq', width:'60', sortable:false, align:'center'},
		      {name:'thrMc', index:'thrMc', width:'60', sortable:false, align:'center'},
		      {name:'thrLxdh', index:'thrLxdh', width:'60', sortable:false, align:'center'},
		      {name:'thrSfzh', index:'thrSfzh', width:'60', sortable:false, align:'center'}
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum%>,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[20,50,100,300,500],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'hwztDjxh',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/hwzt!download");
				   $("#mainForm").submit();
		       } 
		 });
	}
	
    //表格加载完毕后触发事件：修改操作列的内容，显示“登记”、“撤销”，并增加功能
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"hwztDjxh");
                var thbz = $("[name='domain.thbz']:checked").val();
                if(thbz == '2'){
                	var link = "<a href=\"javascript:onRegister('"+val+"')\"><font color=\"blue\">登记</font></a>";
                }
                else{
                	var link = "<a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">撤销</font></a>";
                }
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
     /**************************************分页结束****************************************/

</script>
</head>
<body>
<s:form action="hwzt!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
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
		        <tr>
		        	<td width="7%" align="right">业务单位：</td>
          			<td width="26%">
          				<sys:gsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y" myClass="select" />
		  			</td>
		  			<td width="7%" align="right">收货人：</td>
		  			<td width="21%">
		  				<s:textfield name="domain.shrMc"></s:textfield>
		  			</td>
		          <td width="7%" align="right">状态：</td>
		          <td width="15%">
		          	<s:radio name="domain.thbz" list='#{"2":"未提","1":"已提"}' listKey="key" listValue="value" onclick="javascript:onDisplay();"></s:radio>
		          </td>
		        </tr>
		        <tr id="thrqTrId" style="display: none;">
		          <td align="right" id="thrqText">提货日期：</td>
		          <td id="thrqTdId">
		          <sys:dateFirstDLastMonthTag myName="domain.thrqQ" myId="mainForm_domain_thrqQ" myClass="ymdate" />
	          			～
	          		<sys:dateCurrentDayTag myName="domain.thrqZ" myId="mainForm_domain_thrqZ" myClass="ymdate" />
		          </td>
		        </tr>
		    </table>
		</fieldset>
  </div>
		<!-- 分页表格 id必须为dataList -->
		<table id="dataList"><tr><td/></tr></table> 
		<div id="pager"></div>
		<%@include file="/common/message.jsp" %>
	</div>
</s:form>
</body>
</html>
