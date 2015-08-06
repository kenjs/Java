<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>配载预览</title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	$(function(){	
		initDataGrid4View();
		onQueryPzHwxx4View();
		
		$("#saveBtn").click(function(){
			window.dialogArguments.doSave();
			window.close();
		});
		
		$("#closeBtn").click(function(){
			window.close();
		});
	});
	
	function getAutoGridHeight(length) {
		var heightT = 260;
	    if (length <= 2) {
	    	heightT = 2 * 25 + 15;
	    }else if (length <= 10) {
	    	heightT = length * 25 + 15;
	    }
	    
	    return heightT;
	}
	
	function onQueryPzHwxx4View(){
		 var pzDjxh = $("#mainForm_domain_pzDjxh").val();
		 var pchwLsxh = $("#mainForm_domain_pchwLsxh").val();
		 
		//请求表格数据
		var url = jcontextPath+"/hygl/ddpzxxgl!queryPz4View.action";   
		 $("#pzHwxxList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.pzDjxh":pzDjxh,"domain.pchwLsxh":pchwLsxh}
		 	//请求的参数，json格式
		 }
		 ).trigger("reloadGrid");		//显示到第一页，以免总体数据发生变化的时候，分页的信息混乱
	}
	
	//jqGrid  初始化表格
	function initDataGrid4View(){ 
	  $("#pzHwxxList").jqGrid({
	    url:"",
	    datatype: 'local',
	    mtype: 'POST',
	    rownumbers : true,					//序号列
		width:pageWidth()-10,  
		height:pageTableHeight()-90,	
	    gridComplete: myGridComplete,		//表格加载完毕事件
	    shrinkToFit:false, 
	    colNames:['货物名称','始发地','目的地','包装','类别','数量','重量','体积','收入','到付','回单号','收货人','收货地址','发货日期',
	    		'发货地址','登记人','登记日期','登记部门','所属机构'],			 //name 列显示的名称
	     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
	    colModel :[
	      {name:'hwmc', index:'hwmc', width:'100', sortable:false, align:'center'}, 
	      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'40', sortable:false, align:'center'}, 
	      {name:'shrXzqhMc', index:'shrXzqhMc', width:'40', sortable:false, align:'center'},
	      {name:'bz', index:'bz', width:'30', sortable:false, align:'center'},
	      {name:'lb', index:'lb', width:'40', sortable:false, align:'center'},
	      {name:'hwSl', index:'hwSl', width:'40', sortable:false, align:'center'},
	      {name:'hwZl', index:'hwZl', width:'40', sortable:false, align:'center'}, 
	      {name:'hwTj', index:'hwTj', width:'40', sortable:false, align:'center'},	      
	      {name:'srHj', index:'srHj', width:'45', sortable:false, align:'center'},
	      {name:'df', index:'df', width:'45', sortable:false, align:'center'},
	      
	      {name:'hdbh', index:'hdbh', width:'70', sortable:false, align:'right'},
	      {name:'shrMc', index:'shrMc', width:'60', sortable:false, align:'center'}, 
	      {name:'shDz', index:'shDz', width:'100', sortable:false, align:'center'}, 
	      {name:'fhRq', index:'fhRq', width:'70', sortable:false, align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}, 
	      {name:'fhrDz', index:'fhrDz', width:'100', sortable:false, align:'center'},
	      {name:'djrMc', index:'djrMc', width:'70', sortable:false, align:'center'},
		  {name:'djRq', index:'djRq', width:'70', sortable:false, align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		  {name:'djJgmc', index:'djJgmc', width:'100', sortable:false, align:'center'},
		  {name:'ssJgmc', index:'ssJgmc', width:'100', sortable:false, align:'center'}
	    ],
	    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
	    rowNum: -1,							//在grid上显示记录条数，这个参数是要被传递到后台
	    rowList:[-1],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
	    sortname: 'DD_DJXH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
	    sortorder: 'DESC',				//默认排序方向
	    viewrecords: true,					//定义是否要显示总记录数
	    gridview: false,					//默认false 构造一行数据后添加到grid中，如果设为true则是将整个表格的数据都构造完成后再添加到grid中，但treeGrid, subGrid, or afterInsertRow 不能用	
	    jsonReader: {     
	 	 	root: 	 "domain.pzHwxxList",   				// 数据行（默认为：rows）
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
	  jQuery("#pzHwxxList").jqGrid('navGrid','#pager',
	 		 {edit:false,add:false,del:false}
	  );
	  
	}
	
	//表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
	function myGridComplete() {
	    var graduateIds = $("#pzHwxxList").jqGrid('getDataIDs');
	    var heightT = getAutoGridHeight(graduateIds.length);
	    $("#pzHwxxList").setGridHeight(heightT);
	}		
</script>
</head>

<body>
<%try{ %>
<s:form action="ddpzxxgl!viewPzXx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
		<s:hidden name="domain.pzDjxh"></s:hidden>
		<s:hidden name="domain.pchwLsxh"></s:hidden>
		<div class="pop_contc">
			<fieldset>
				<legend>配载信息</legend>
				<table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
					<tr>
	      				<td width="15%" align="right">货站：</td>
	      				<td width="35%">
	      					<s:textfield name="domain.hzmc" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
	      				</td>
	      				<td width="15%" align="right">车辆型号：</td>
	      				<td width="35%" id="hzfsTd">
	      				 	<s:textfield name="domain.clxh" cssClass="pop_input noborder bgstyle_readonly" ></s:textfield>
	      				</td>
	      			</tr>
	      			<tr>
	      				<td align="right">配载承重：</td>
	      				<td>
	      					<s:textfield name="domain.pzCz" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
	      				</td>
	      				<td width="15%" align="right">配载体积：</td>
	      				<td width="35%">
	      					<s:textfield name="domain.pzTj" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>	      				
	      				</td>	      				
	      			</tr>
					<tr>
						<td align="right">配载收入：</td>
	      				<td>
	      					<s:textfield name="domain.pzsr" cssClass="pop_input noborder bgstyle_readonly"></s:textfield>
	      				</td>
	      				<td align="right"></td>
	      				<td>
	      				</td>
	      			</tr>	      	
				</table>
			</fieldset>
			<br />
			<h2>已选货物信息：</h2>
			<table id="pzHwxxList"><tr><td/></tr></table>
			<div class="pop_btn">
			 	<button type="button" class="pop_btnbg" id="saveBtn">保 存</button>
			 	&nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		    </div>
	</div>
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
