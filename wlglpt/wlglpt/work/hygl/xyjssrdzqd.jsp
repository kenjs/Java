<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>下游结算-收入对帐-清单</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>

<script type="text/javascript">
	$(document).ready(function(){
			//查询按钮事件
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//新增按钮事件
			$("#addBtn").click(function(){
				var xyDjxh = trim($("#mainForm_domain_xyDjxh").val());
				
				if(undefined==xyDjxh || null==xyDjxh || ""==xyDjxh){
					showAlert("请您选择下游单位！");
					return;
				}
				
				var fylbDm = $("[name='domain.fylbDm']:checked").val();
				
				if (fylbDm == "") {
					showAlert("请选择一个费用类别！");
					return;
				}
				
				onUpdate('',xyDjxh,fylbDm);
			});
			
			//初始化表格
			initDataGrid();
						
			var sjJgbm = $("#mainForm_domain_ssJgbm").val();
			bmInit(sjJgbm, '', "domain.djJgbm", "mainForm_domain_djJgbm", "Y", "Y");
	});
	
    function onUpdate(qdDjxh,xyDjxh,fylbDm){
    	var url = jcontextPath+"/hygl/xyjssrdzqd!initMx?domain.qdDjxh="+qdDjxh+"&domain.xyDjxh="+xyDjxh+"&domain.fylbDm="+fylbDm;
    	navigateMenu(url,'下游对账清单维护','true');
    }
    
    var keyValue = "";
	function onDelete( qdDjxh){
		keyValue = qdDjxh;
		showConfirm("确定要删除么？","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.qdDjxh":keyValue};
		 var url = jcontextPath+"/hygl/xyjssrdzqd!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("删除成功！");
        onRefresh();
	}
	
	function onDz(qdDjxh, fylbDm) {
		var url = jcontextPath+"/hygl/xyjssrdz!init?domain.qdDjxh="+qdDjxh+"&domain.fylbDm="+fylbDm;
    	navigateMenu(url,'下游对账','true');
	}
	
	function onSend(qdDjxh) {
		var jsonObj = {"domain.qdDjxh":qdDjxh};
		 var url = jcontextPath+"/hygl/xyjssrdzqd!sendDzqd";   
        ajaxCommon(url,jsonObj , "doSendSuc");  
	}
	
	function doSendSuc(data) {
		showAlert("发送成功，下游单位将可对该清单进行确认。");
		onRefresh();
	}
	
	function onView(qdDjxh,xyDjxh,fylbDm){
    	var url = jcontextPath+"/hygl/xyjssrdzqd!viewQrMx?domain.qdDjxh="+qdDjxh+"&domain.xyDjxh="+xyDjxh+"&domain.fylbDm="+fylbDm;
    	navigateMenu(url,'清单查看','true');
    }
    
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		var djJgbm = trim($("#mainForm_domain_djJgbm").val()); 
		var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
		var xyDjxh = trim($("#mainForm_domain_xyDjxh").val()); 
		var rqQ = trim($("#mainForm_domain_rqQ").val()); 
		var rqZ = trim($("#mainForm_domain_rqZ").val()); 
		var fylbDm = trim($(":checked[name='domain.fylbDm']").val());
		
		//请求表格数据
		var url = jcontextPath+"/hygl/xyjssrdzqd!query";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.xyDjxh":xyDjxh,"domain.rqQ":encodeURI(rqQ),"domain.rqZ":encodeURI(rqZ),
			      "domain.djJgbm":djJgbm,"domain.ssJgbm":ssJgbm,"domain.fylbDm":fylbDm}								//请求的参数，json格式
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
		    colNames:['操作', '清单登记序号','fylbDm','清单名称','下游名称','合计金额','对账金额','差异金额',
				     '登记人','登记日期','登记部门','所属机构','状态','下游意见',''],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
		      {name:'qdDjxh', index:'qdDjxh', width:'100', hidden:true, align:'center'},
		      {name:'fylbDm', index:'fylbDm', width:'100', hidden:true, align:'center'}, 
		      {name:'qdmc', index:'qdmc', width:'140', align:'center'}, 
		      {name:'xyMc', index:'xyMc', width:'100', align:'center'},

		      {name:'heJe', index:'heJe', width:'60', align:'center'}, 
		      {name:'dzJe', index:'dzJe', width:'60', align:'center'},
		      {name:'dzcyJe', index:'dzcyJe', width:'60', align:'center'},

		      {name:'djrMc', index:'djrMc', width:'45', align:'center'}, 
		      {name:'djrq', index:'djrq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}, 
		      {name:'djJgmc', index:'djJgmc', width:'80', align:'center'}, 
		      {name:'ssJgmc', index:'ssJgmc', width:'80', align:'center'},
		      {name:'ztStr', index:'ztStr', width:'80', align:'center'},
		      {name:'bz', index:'bz', width:'80', align:'center'},
		      {name:'zt', index:'zt', width:'80', align:'center',hidden:true}
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum%>,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[20,50,100,300,500],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'QD_DJXH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/xyjssrdzqd!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"qdDjxh"); 	  //获取当前单元格里面的登记序号
                var fylbDm = jQuery("#dataList").jqGrid('getCell', cl,"fylbDm");
                var zt = jQuery("#dataList").jqGrid('getCell', cl,"zt");
                var link = "";
                if(zt == '3'){
                	link = "<a href=\"javascript:onView('"+val+"')\"><font color=\"blue\">查看</font></a>";
                }else 
                if(zt == '1' || zt == '4' || zt == ''){
                	link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">修改</font></a>"
                	+ " <a href=\"javascript:onDz('"+val+"','"+fylbDm+"')\"><font color=\"blue\">对账</font></a>"
                    + "<br/><a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">删除</font></a>"
                    + " <a href=\"javascript:onSend('"+val+"')\"><font color=\"blue\">发送</font></a>";
                }else {
                	link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">修改</font></a>"
                	+ " <a href=\"javascript:onDz('"+val+"','"+fylbDm+"')\"><font color=\"blue\">对账</font></a>"
                    + "<br/><a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">删除</font></a>";
                }
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
     /**************************************分页结束****************************************/

</script>
</head>
<body>
<s:form action="xyjssrdzqd!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">操作：</li>
		    <li><a href="#" id="queryBtn" class="licon04">检 索</a></li>
		    <li><a href="#" id="addBtn" class="licon01">增加</a></li>
		    <li><a href="#" id="closeBtn" class="licon03">关闭</a></li>
	  	</ul>
	
	    <ul class="rcont">
		    <li class="ricon02" onclick="slideToggle('syquery')">显示/隐藏查询条件</li>
		    <li class="ricon03">帮助</li>
	  	</ul>
	</div> 

	<div class="right_cont">
	<div id="divQuery">
	<fieldset><legend>查询条件</legend>
	<table width="99%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="8%" align="right">业务单位：</td>
			<td width="25%">
				<sys:gsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y" myClass="select" onChange="initList()" /></td>
			<td width="8%" align="right">登记部门：</td>
			<td width="21%">
				<select name="domain.djJgbm" id="mainForm_domain_djJgbm" class="select">
					<option value="${domain.djJgbm }" selected="selected"></option>
				</select>
			</td>
			<td width="8%" align="right">下游单位：</td>
			<td width="21%">
				<sys:fgsList myId="mainForm_domain_xyDjxh" myName="domain.xyDjxh" contaisQxz="true" contaisDq="N" myClass="select"></sys:fgsList>
			</td>
		</tr>
		<tr>
			<td align="right">创建日期：</td>
			<td>
				<sys:dateFirstDMonth myName="domain.rqQ" myId="mainForm_domain_rqQ" myClass="ymdate"></sys:dateFirstDMonth>
				 ～ 
				<sys:dateCurrentDayTag myName="domain.rqZ" myId="mainForm_domain_rqZ" myClass="ymdate"></sys:dateCurrentDayTag>
			</td>
			<td>费用类别：</td>
			<td colspan="3">
				<s:radio name="domain.fylbDm" list="#{'':'所有','1':'配送费','2':'到付款','3':'代收货款' }"></s:radio>
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
