<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>企业-审批文书</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>

<script type="text/javascript">
	$(document).ready(function(){
			//查询按钮事件
			$("#queryBtn").click(function(){
				onRefresh();
			});
			$("#saveBtn").click(function(){
				if(check()){
					showAlert("请先选择“文书审批模式”！");
					return;
				}
				var cb = $("input[name='opera']");
				var wd = $("input[name='wsDm']");
				var jb = $("input[name='jgbm']");
				var wpd = $("[name='wsspms']");
				var zt = "";
				var wsDm = "";
				var jgbm = "";
				var wsspmsDm = "";
				var strObj = "";
				for(var i=0;i<cb.length;i++){
					if(cb[i].checked){
						zt = "Y";
					}else{
						zt = "N";
					}
					wsDm = wd[i].value;
					jgbm = jb[i].value;
					wsspmsDm = wpd[i].value;
					strObj += zt+","+wsspmsDm+","+jgbm+","+wsDm+"|";
				}
				if(strObj == ""){
					return;
				}
				var url = jcontextPath+"/dzgl/qyspws!save";
				var jsonObj = {"domain.strObj":strObj};				
				ajaxCommon(url,jsonObj,"doSuccess");
			});
			
			//初始化表格
			initDataGrid();
			
			var ywflDm = $("#mainForm_domain_ywflDm").val();
			if(ywflDm != "" || ywflDm != null || ywflDm != "0"){
				commonInit('ywhj',ywflDm ,'' , 'domain.ywhjDm', 'mainForm_domain_ywhjDm', 'Y', 'Y')
			}
	});
	
	function check(){
		var num = 0;
		var cb = $("input[name='opera']");
		var wpd = $("[name='wsspms']");
		for(var i=0;i<cb.length;i++){
			if(cb[i].checked && wpd[i].value == ""){
				++ num;
			}			
		}
		if(num>0){
			return true;
		}else{
			return false;
		}
	}
	
	function doSuccess(){
		hideMessage();
		showAlert("保存成功！","refresh");
	}
	
	function refresh(){
		onRefresh();
	}
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		var ywflDm = $("#mainForm_domain_ywflDm").val();
		var ywhjDm = $("#mainForm_domain_ywhjDm").val();  
		//请求表格数据
		var url = jcontextPath+"/dzgl/qyspws!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ywflDm":ywflDm,"domain.ywhjDm":ywhjDm}								//请求的参数，json格式
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
		    colNames:['操作','文书代码','业务分类','业务环节','文书名称','文书简称','文书审批模式','','单位','项目分类','创建人','创建日期','修改人','修改日期','说明','','','',''],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[ 
		      {name:'hstoperationcol', index:'', sortable:false, width:'40', align:'center'},
		      {name:'wsDm',index:'wsDm', width:'80', align:'center'}, 
		      {name:'ywflMc', index:'ywflMc', width:'80', align:'center'}, 
		      {name:'ywhjMc', index:'ywhjMc', width:'80', align:'center'}, 
		      {name:'wsMc', index:'wsMc', width:'120', align:'center'}, 
		      {name:'wsJc', index:'wsJc', width:'120', align:'center'}, 
		      {name:'wsspms',index:'',width:'150',align:'center'},
		      {name:'wsspmsDm',index:'wsspmsDm',width:'100',align:'center',hidden:true},
		      
		      {name:'dwMc', index:'dwMc', width:'120', align:'center'},
		      {name:'flbz', index:'flbz', width:'80', align:'center'}, 
		      
		      {name:'cjrMc', index:'cjrMc', width:'80', align:'center'},
		      {name:'cjrq', index:'cjrq', width:'80', align:'center'},
		      {name:'xgrMc', index:'xgrMc', width:'80', align:'center'},
		      {name:'xgrq', index:'xgrq', width:'80', align:'center'},
		      {name:'sm', index:'sm', width:'280', align:'center'},
		      
		      {name:'jgbm', index:'jgbm', width:'0', align:'center',hidden:true},
		      {name:'fzh1', index:'', width:'0', align:'center',hidden:true},
		      {name:'fzh2', index:'', width:'0', align:'center',hidden:true},
		      {name:'fzh3', index:'', width:'0', align:'center',hidden:true}
		     
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum%>,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[20,50,100,300,500],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'JGBM',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
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
		       	   $("#mainForm").attr("action",jcontextPath+"/dzgl/qyspws!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val1 = jQuery("#dataList").jqGrid('getCell', cl,"jgbm"); 
				var val2 = jQuery("#dataList").jqGrid('getCell', cl,"wsDm");	  //获取当前单元格里面的文书代码
				var val3 = jQuery("#dataList").jqGrid('getCell', cl,"wsspmsDm");
				var link = "";
				if(val1 != ""){
					link = "<input type='checkbox' name='opera' checked='checked' />";
				}else{
					link = "<input type='checkbox' name='opera' />";
				}
				var link2 = "<input type='hidden' name='wsDm' value='"+val2+"'/>";
				var link3 = "<input type='hidden' name='wsspmsDm' value='"+val3+"'/>";
				var link4 = "<input type='hidden' name='jgbm' value='"+val1+"'/>";
				var link5 = $("#wsspmsDiv").html();
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
                $("#dataList").jqGrid('setRowData', cl, { 'fzh1': link2 }); 
                $("#dataList").jqGrid('setRowData', cl, { 'fzh2': link3 }); 
                $("#dataList").jqGrid('setRowData', cl, { 'fzh3': link4 }); 
                $("#dataList").jqGrid('setRowData', cl, { 'wsspms': link5 }); 
                $("[name='wsspms']")[i].value = val3;
            }
     }
     /**************************************分页结束****************************************/

</script>
</head>
<body>
<s:form action="qyspws!query" namespace="/dzgl" method="post" id="mainForm" name="mainForm">
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">操作：</li>
		    <li><a href="#" id="queryBtn" class="licon04">检 索</a></li>
		    <li><a href="#" id="saveBtn" class="licon06">保 存</a></li>
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
		      		<table width="65%" border="0" cellspacing="0" cellpadding="0">
		        		<tr>
		          			<td width="15%" align="right">业务分类：</td>
		          			<td width="35%">
		          				<sys:Ywfl myName="domain.ywflDm" myId="mainForm_domain_ywflDm" contaisQxz="true" mcContainDmBz="Y"  myClass="select" onChange="commonInit('ywhj', this.value,'' , 'domain.ywhjDm', 'mainForm_domain_ywhjDm', 'Y', 'Y')"/>
				  			</td>
				  			<td width="100" align="right">业务环节：</td>
		          			<td width="350">
		          				<select id="mainForm_domain_ywhjDm" name="domain.ywhjDm" class="select"></select>
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
	<div id="wsspmsDiv" style="display: none;">
		<sys:Wsspms myName="wsspms" contaisQxz="true" mcContainDmBz="Y"  myClass="select" />
	</div>
</body>

</html>
