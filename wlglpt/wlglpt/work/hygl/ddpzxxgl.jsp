<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>货运-配载</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>

<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		initHykhData(300);
		
		var sjJgbm = $("#mainForm_domain_ssJgbm").val();
		bmInit(sjJgbm, '', "domain.djJgbm", "mainForm_domain_djJgbm", "Y", "Y");
		
			//查询按钮事件
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//新增按钮事件
			$("#addBtn").click(function(){
				popwindow(jcontextPath+"/hygl/ddpzxxgl!initMx");
			});
			//初始化表格
			initDataGrid();
						

	});

    function onUpdate(pzDjxh){
    	var url = jcontextPath+"/hygl/ddpzxxgl!initMx.action?domain.pzDjxh="+pzDjxh+"&num="+Math.random();
    	navigateMenu(url,'调度配载修改','true');
    	//popwindow(jcontextPath+"/hygl/ddpzxxgl!initMx?domain.pzDjxh="+pzDjxh);
    }
    
    var keyValue = "";
	function onDelete( pzDjxh){
		keyValue = pzDjxh;
		showConfirm("确定要删除么？","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.pzDjxh":keyValue};
		 var url = jcontextPath+"/hygl/ddpzxxgl!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("删除成功！");
        onRefresh();
	}	
	
	function onPzToPc(pzDjxh) {
		var jsonObj = {"domain.pzDjxh":pzDjxh, "domain.listPc":"Y"};
		var url = jcontextPath+"/hygl/ddpzxxgl!initPcxxFromPz";
		showMessage();
		ajaxCommon(url,jsonObj,"doInitPcSuc");
	}
	
	function doInitPcSuc(data) {
		var pchwLsxh = data.domain.pchwLsxh;
		var url = jcontextPath + "/hygl/hypcxxgl!initMx.action?domain.pcfsDm=2&domain.pchwLsxh="+pchwLsxh;
		navigateMenu(url,'运输派车','true');
	}
    
	
	function onQingdan(pzDjxh){
		var url = jcontextPath+"/hygl/ddpzxxgl!onQingdan.action?domain.pzDjxh="+pzDjxh;
		//navigateMenu(url,'调度清单','true');
		window.open(url, "_blank");
	}
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var djJgbm = $("#mainForm_domain_djJgbm").val();
		var fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		var fhrMc = $("#mainForm_domain_fhrMc").val();
		var ddbh = $("#mainForm_domain_ddbh").val();  
		
		//请求表格数据
		var url = jcontextPath+"/hygl/ddpzxxgl!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.djJgbm":djJgbm,
	 			"domain.fhrMc":encodeURI(fhrMc),"domain.ddbh":encodeURI(ddbh),"domain.fhrDjxh":fhrDjxh}								//请求的参数，json格式
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
		    colNames:['序号','操作', '配载登记序号','货站','车辆型号','配载承重(吨)','配载体积(方)','配载收入',
		              '客户','货物名称', '始发地', '目的地', '数量','重量', '体积','包装', 
				     '发货地址', '要求发货日期', '收货人','收货地址','要求到达日期'],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[
				{name:'pageXh', index:'pageXh', sortable:false, width:'30', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pageXh' + rowId + '\'';
				    }
			    },
				{name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'hstoperationcol' + rowId + '\'';
				    }
				},
		        {name:'pzDjxh', index:'pzDjxh',hidden:true, width:'100', align:'center'}, 
		        {name:'hzmc', index:'hzmc', width:'100', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'hzmc' + rowId + '\'';
				    }
				},
				{name:'clxh', index:'clxh', width:'60', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'clxh' + rowId + '\'';
				    }
				}, 
		        {name:'pzCz', index:'pzCz', width:'70', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pzCz' + rowId + '\'';
				    }
				},  
		        {name:'pzTj', index:'pzTj', width:'70', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pzTj' + rowId + '\'';
				    }
				},  
		        {name:'pzsr', index:'pzsr', width:'70', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pzsr' + rowId + '\'';
				    }
				},  

		      {name:'fhrMc', index:'fhrMc', width:'120', align:'center'},
			  {name:'hwMc', index:'hwMc', width:'100', align:'center'},
		      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'40', align:'center'},
		      {name:'shrXzqhMc', index:'shrXzqhMc', width:'40', align:'center'},
		      {name:'sl', index:'sl', width:'50', align:'right'},  
		      {name:'zl', index:'zl', width:'50', align:'right'},
		      {name:'tj', index:'tj', width:'50', align:'right'},
		      {name:'hwbz', index:'hwbz', width:'50', align:'center'},
		      {name:'fhrDz', index:'sfdMc', width:'200', align:'center'},
		      {name:'yqFhrq', index:'yqFhrq', width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      {name:'shrMc', index:'shrMc', width:'100', align:'center'},
		      {name:'shrDz', index:'mddMc', width:'200', align:'center'},
		      {name:'yqDdrq', index:'yqDdrq', width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}		      
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum%>,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[20,50,100,300,500],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'PZ_DJXH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/ddpzxxgl!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"pzDjxh"); 	  //获取当前单元格里面的登记序号 
                var link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">修改</font></a>"
                		+ " <a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">删除</font></a>"
                		+ "<br/><a href=\"javascript:onPzToPc('"+val+"')\"><font color=\"blue\">派车</font></a>"
                		+" <a href=\"javascript:onQingdan('"+val+"')\"><font color=\"blue\">清单</font></a>";
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
            
            var gridName = "dataList";
     	    var a = ['pageXh','hstoperationcol','hzmc','clxh','pzCz','pzTj','pzsr'];
      		
            Merger(gridName, 'pzDjxh', a);
     }
     /**************************************分页结束****************************************/

</script>
</head>
<body>
<s:form action="ddpzxxgl!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.ssJgbm" />
	
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

	<div class="right_cont" id="maincont">
		<div id="divQuery">
		<fieldset>
			<legend>查询条件</legend>
		   <table width="99%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		  			<td width="8%" align="right">配载部门：</td>
          			<td width="21%">
          				<select id="mainForm_domain_djJgbm" name="domain.djJgbm" class="select" >
          				</select>
		  			</td>
		        	  <td width="8%" align="right">客户名称：</td>
			          <td width="21%">
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
			          <td width="8%" align="right">订单单号：</td>
			          <td width="21%"><s:textfield name="domain.ddbh" cssClass="pop_input noborder" /></td>
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
