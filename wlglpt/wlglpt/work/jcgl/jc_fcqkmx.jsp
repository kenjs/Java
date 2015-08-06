<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>决策-派车信息管理</title>
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
	
	function onView(pcDjxh) {
		var url = jcontextPath+"/jcgl/jcpcxxgl!initMx.action?domain.pcDjxh="+pcDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    	//window.open(url);
	}
	
	function onViewDd(ddDjxh) {
		var url = jcontextPath+"/jcgl/jctydgl!initMx.action?domain.ddDjxh="+ddDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    	//window.open(url);
	}
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var hwmc = $("#mainForm_domain_hwmc").val();
		var sl = $("#mainForm_domain_sl").val();
		
		var pcdh = $("#mainForm_domain_pcdh").val();
		var tydh = $("#mainForm_domain_tydh").val();
		var hdh = $("#mainForm_domain_hdh").val();
		
		var fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		var fhrMc = $("#mainForm_domain_fhrMc").val();
		var sfd = $("#mainForm_domain_sfd").val();
		var mdd = $("#mainForm_domain_mdd").val();
		
		var clhm = $("#mainForm_domain_clhm").val();
		var rqq = $("#mainForm_domain_rqq").val();
		var rqz = $("#mainForm_domain_rqz").val();
		var pcfs = $(":radio[name='domain.pcfs']:visible:checked").val();
		//alert(pcfs);
		//请求表格数据
		var url = jcontextPath+"/jcgl/fcqkmx!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.hwmc":encodeURI(hwmc),"domain.sl":sl,"domain.pcdh":encodeURI(pcdh),"domain.tydh":tydh,
		 		"domain.hdh":hdh,"domain.fhrDjxh":fhrDjxh,"domain.fhrMc":encodeURI(fhrMc),"domain.sfd":encodeURI(sfd),
		 		"domain.mdd":encodeURI(mdd),"domain.clhm":encodeURI(clhm),"domain.rqq":rqq,"domain.rqz":rqz,"domain.pcfs":pcfs}								//请求的参数，json格式
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
		    colNames:['序号','','派车单号','承运单位','派车人','派车日期','托运单号','','客户单位','下单日期','到站','收货方','货物名称',
		              '数量','重量','收款方式','支出合计','支出现付','回单号','司机运费','预付运费','状态'],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[
				{name:'pageXh', index:'pageXh', sortable:false, width:'30', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pageXh' + rowId + '\'';
				    }
				},
				{name:'pcDjxh', index:'pcDjxh', hidden:true, width:'80', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcDjxh' + rowId + '\'';
				    }
				},
				{name:'pcdh', index:'pcdh', sortable:false, width:'80', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcdh' + rowId + '\'';
				    }
				},
				{name:'clhm', index:'clhm', sortable:false, width:'80', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'clhm' + rowId + '\'';
				    }
				},
				{name:'pcrMc', index:'pcrMc', sortable:false, width:'80', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcrMc' + rowId + '\'';
				    }
				},
				{name:'pcrq', index:'pcrq', sortable:false, width:'80', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcrq' + rowId + '\'';
				    }
				},
				{name:'tydh', index:'tydh', width:'80', align:'center'},
				{name:'ddDjxh', index:'ddDjxh', width:'80', align:'center',hidden:true},
				{name:'khmc', index:'khmc', width:'80', align:'center'},
				{name:'xdrq', index:'xdrq', width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
				{name:'shrDz', index:'shrDz', width:'120', align:'center'},
				{name:'shrMc', index:'shrMc', width:'80', align:'center'},
				{name:'hwmc', index:'hwmc', width:'80', align:'center'},
				{name:'sl', index:'sl', width:'50', align:'center'},
				{name:'zl', index:'zl', width:'50', align:'center'},
				{name:'skfs', index:'skfs', width:'80', align:'center',hidden:true},
				{name:'zcHj', index:'zcHj', width:'80', align:'center'},
				{name:'zcXf', index:'zcXf', width:'80', align:'center'},
				{name:'hdh', index:'hdh', width:'80', align:'center'},
				{name:'sjyf', index:'sjyf', sortable:false, width:'80', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'sjyf' + rowId + '\'';
				    }
				},
				{name:'yfyf', index:'yfyf', sortable:false, width:'80', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'yfyf' + rowId + '\'';
				    }
				},
				{name:'spzt', index:'spzt', sortable:false, width:'50', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'spzt' + rowId + '\'';
				    }
				}
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: 10,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[10,20,50,100],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'PC_DJXH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
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
		       	   $("#mainForm").attr("action",jcontextPath+"/jcgl/fcqkmx!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
	function myGridComplete() {
        var graduateIds = $("#dataList").jqGrid('getDataIDs');
		
        for (var i = 0; i < graduateIds.length; i++) {
            var cl = graduateIds[i];
            var pcDjxh = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh"); 
            var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh"); 
            var pcdh = jQuery("#dataList").jqGrid('getCell', cl, "pcdh");
            var tydh = jQuery("#dataList").jqGrid('getCell', cl, "tydh"); 
            var pageXh = jQuery("#dataList").jqGrid('getCell', cl, "pageXh"); 
            if(pageXh == 0){
            	$("#dataList").jqGrid('setRowData', cl, { 'pageXh': '合计' });
            }
            
            var link = "<a href=\"javascript:onView('"+pcDjxh+"')\"><font color=\"blue\">"+pcdh+"</font></a>";
            var link2 = "<a href=\"javascript:onViewDd('"+ddDjxh+"')\"><font color=\"blue\">"+tydh+"</font></a>";
           $("#dataList").jqGrid('setRowData', cl, { 'pcdh': link }); 
           $("#dataList").jqGrid('setRowData', cl, { 'tydh': link2 }); 
        } 
      
        var gridName = "dataList";
 	    var a = ["pageXh","pcDjxh","pcdh","clhm","pcrMc","pcrq","sjyf","yfyf","spzt"];
  		
        Merger(gridName, 'pcDjxh', a);
 }
	
     /**************************************分页结束****************************************/
</script>
</head>
<body>
<s:form action="fcqkmx!query" namespace="/jcgl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="jsonData" />
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
	<div class="right_cont" id="maincont">
	  <div id="divQuery">
		<fieldset>
			<legend>查询条件</legend>
		   <table width="99%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		        	<td width="8%" align="right">派车单位：</td>
          			<td width="21%">
          				<sys:csGsList myClass="select" myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y"/>
		  			</td>
		  			<td width="8%" align="right">货物名称：</td>
		          <td width="25%"><s:textfield name="domain.hwmc" cssClass="pop_input noborder" /></td>
		          <td width="8%" align="right">数量：</td>	
		          <td width="25%"><s:textfield name="domain.sl" cssClass="pop_input noborder" /></td>	          
	          	</tr>
	          	<tr>	          		
		  			<td align="right">派车单号：</td>
          			<td>
          				<s:textfield name="domain.pcdh" cssClass="pop_input noborder" />
		  			</td>
	        	  <td align="right">托运单号：</td>
		          <td>
		          		<s:textfield name="domain.tydh" cssClass="pop_input noborder" />
	          	  </td>
	          	  <td align="right">回单号：</td>
		          <td>
		          		<s:textfield name="domain.hdh" cssClass="pop_input noborder" />
	          	  </td>
	          	</tr>
	          	<tr>
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
		          <td align="right">始发地：</td>
		          <td><s:textfield name="domain.sfd" cssClass="pop_input noborder" /></td>
		          <td align="right">目的地：</td>
		          <td><s:textfield name="domain.mdd" cssClass="pop_input noborder" /></td>
		        </tr>
		        <tr>
		        	<td align="right">承运商：</td>
		          <td >
		          	<s:textfield name="domain.clhm" cssClass="pop_input noborder" />
		          </td>
		           <td align="right">派车日期：</td>
		          <td >
		          <sys:dateFirstDMonth myName="domain.rqq" myId="mainForm_domain_rqq" myClass="ymdate" />
	          			～
	          		<sys:dateCurrentDayTag myName="domain.rqz" myId="mainForm_domain_rqz" myClass="ymdate" />
		         
		          </td>
		          <td align="right">派车方式：</td>
		          <td>
		          	<s:radio list="#{1:'提货',2:'运输' }" listKey="key" listValue="value" name="domain.pcfs" value="2"></s:radio>
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
