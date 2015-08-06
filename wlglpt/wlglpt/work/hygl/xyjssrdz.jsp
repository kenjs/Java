<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>下游结算-收入对帐</title>
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
			$("#pldzBtn").click(function(){
				var xhs = $("[name='xhs']:checked");
				if (xhs == null || xhs.length <= 0) {
					showAlert("请先选择需要对账的记录！");
					return;
				}
				
				doPldz();
			});
			
			//初始化表格
			initDataGrid();
			initRadio();	
			changeQdList();
			onRefresh();
	});
	
	function initRadio() {
		$("[name='domain.dzztDm']")[0].checked = true;
		if ($("#mainForm_domain_fylbDm").val() != "") {
			$("[name='domain.fylbDm'][value='"+$("#mainForm_domain_fylbDm").val()+"']")[0].checked = true;
		}else {
			$("[name='domain.fylbDm']")[1].checked = true;
		}
		
	}
	
	function doPldz() {
		var fylbDm = $("[name='domain.fylbDm']:checked").val();
		var xhs = $(":checked[name='xhs'][value!='']");
		if (xhs.length > 0) {
			var jsonStr = getJqueryParam(xhs, "domain.ywDjxhs");
			var jsonObj = {"domain.fylbDm":fylbDm};
			
			jsonStr += jQuery.param(jsonObj);
			var url = jcontextPath+"/hygl/xyjssrdz!savePldz";  
			showMessage();
			ajaxCommon(url,encodeURI(jsonStr),"savePldzSuc", false);
		}
	}
	
	function savePldzSuc(data) {
		hideMessage();
		showAlert("批量对账成功！");
		onRefresh();
	}
	
	function changeQdList() {
		var fylbDm = $("[name='domain.fylbDm']:checked").val();
		var qdDjxh = $("#mainForm_domain_qdDjxh").val();
		var jsonObj = {"domain.fylbDm":fylbDm, "domain.qdDjxh":qdDjxh};
		var url = jcontextPath+"/hygl/xyjssrdz!queryDzqdList";
		ajaxCommon(url,jsonObj,"changeQdListSuc");
	}
	
	function changeQdListSuc(data) {
		var list = data.domain.dzqdList;
		$("#mainForm_domain_qdDjxh").empty();
		var option = document.createElement('option');
		$("#mainForm_domain_qdDjxh")[0].add(option);
	    $(option).text("--请选择--").val("");
		
		$.each(list, function(i,domain){
		    var option = document.createElement('option');
		    $("#mainForm_domain_qdDjxh")[0].add(option);
		    $(option).text(domain.qdmc).val(domain.qdDjxh);
		    //默认选中
		    if(data.domain.qdDjxh==domain.qdDjxh){
		    	$(option).attr("selected", "selected");
		    	$(option).text(domain.qdmc).val(domain.qdDjxh);
		    }
		    
		});
	}
	
	function onDz(ywDjxh) {
		var fylbDm = $("[name='domain.fylbDm']:checked").val();
		onUpdate('',ywDjxh,fylbDm);
	}

    function onUpdate(dzDjxh,ywDjxh,fylbDm){
    	var url = jcontextPath+"/hygl/xyjssrdz!initMx?domain.dzDjxh="+dzDjxh+"&domain.ywDjxh="+ywDjxh+
    			"&domain.ywMxXh="+fylbDm+"&num="+Math.random();
    	window.showModalDialog(url,window,"dialogHeight:350px;dialogWidth:720px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;");
    	onRefresh();
    }
    
    var keyValue = "";
	function onDelete( dzDjxh){
		keyValue = dzDjxh;
		showConfirm("确定要删除么？","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.dzDjxh":keyValue};
		 var url = jcontextPath+"/hygl/xyjssrdz!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("删除成功！");
        onRefresh();
	}	
    
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var xyDjxh = trim($("#mainForm_domain_xyDjxh").val()); 
		var pcdh = $("#mainForm_domain_pcdh").val();
		var pcrqQ = trim($("#mainForm_domain_pcrqQ").val()); 
		var pcrqZ = trim($("#mainForm_domain_pcrqZ").val()); 
		var ddbh = $("#mainForm_domain_ddbh").val();
		var hwSl = $("#mainForm_domain_hwSl").val();
		var hdbh = $("#mainForm_domain_hdbh").val();
		var xdrqQ = trim($("#mainForm_domain_xdrqQ").val()); 
		var xdrqZ = trim($("#mainForm_domain_xdrqZ").val()); 
		var dzztDm = $("[name='domain.dzztDm']:checked").val();
		var fylbDm = $("[name='domain.fylbDm']:checked").val();
		var qdDjxh = trim($("#mainForm_domain_qdDjxh").val()); 
		
		if ("1" == fylbDm) {
			$("#dataList").jqGrid('showCol',["zcPsf"]);
			$("#dataList").jqGrid('hideCol',["zcDf","zcDshk"]);
		}else if ("2" == fylbDm) {
			$("#dataList").jqGrid('showCol',["zcDf"]);
			$("#dataList").jqGrid('hideCol',["zcDshk","zcPsf"]);
		}else if ("3" == fylbDm) {
			$("#dataList").jqGrid('showCol',["zcDshk"]);
			$("#dataList").jqGrid('hideCol',["zcPsf","zcDf"]);
		}
		
		if ("1" == dzztDm) {
			$("#dataList").jqGrid('hideCol',["dzcyje"]);
		}else if ("2" == dzztDm) {
			$("#dataList").jqGrid('showCol',["dzje","dzcyje"]);
		}
		
		
		//请求表格数据
		var url = jcontextPath+"/hygl/xyjssrdz!query";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.xyDjxh":xyDjxh,"domain.pcdh":encodeURI(pcdh),
		 		"domain.pcrqQ":encodeURI(pcrqQ),"domain.pcrqZ":encodeURI(pcrqZ),
			      "domain.ddbh":encodeURI(ddbh),"domain.hwSl":hwSl,"domain.hdbh":hdbh,
			      "domain.xdrqQ":encodeURI(xdrqQ),"domain.xdrqZ":encodeURI(xdrqZ),"domain.dzztDm":dzztDm,
			      "domain.fylbDm":fylbDm,"domain.qdDjxh":qdDjxh}								//请求的参数，json格式
		 }
		 ).trigger("reloadGrid", [{page:1}]);		//显示到第一页，以免总体数据发生变化的时候，分页的信息混乱
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
		    colNames:['<input title="选中/取消选中" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />',
			    		'对账登记序号','结算登记序号','配送费','到付款','代收货款','对账金额','差异','对账备注',
			    		'订单编号','下单日期','始发地','目的地',
			              "货物名称","数量","重量","体积","发货人","收货人","收货地址",'下游单位','对账单位',"派车日期","派车单号"],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'25', align:'center'},
			  {name:'dzDjxh', index:'dzDjxh', width:'30', hidden:true, align:'center'},
		      {name:'ywDjxh', index:'ywDjxh', width:'30', hidden:true, align:'center'},
		      {name:'zcPsf', index:'zcPsf', width:'50', align:'center', hidden:true},
		      {name:'zcDf', index:'zcDf', width:'50', align:'center', hidden:true}, 
		      {name:'zcDshk', index:'zcDshk', width:'50', align:'center', hidden:true}, 
		      {name:'dzje', index:'dzje', width:'50', align:'center'},
		      {name:'dzcyje', index:'dzcyje', width:'50', align:'center', hidden:true},
		      {name:'bz', index:'bz', width:'90', align:'center'},
		      {name:'ddbh', index:'ddbh', width:'70', align:'center'}, 
		      {name:'xdrq', index:'xdrq', width:'65', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      {name:'sfdXzqhMc', index:'sfdXzqhMc', width:'50', align:'center'},
		      {name:'mddXzqhMc', index:'mddXzqhMc', width:'50', align:'center'}, 

		      {name:'hwmc', index:'hwmc', width:'70', align:'center'},
		      {name:'hwSl', index:'hwSl', width:'50', align:'center'},
		      {name:'hwZl', index:'hwZl', width:'50', align:'center'},
		      {name:'hwTj', index:'hwTj', width:'50', align:'center'},
		      {name:'fhrLxr', index:'fhrLxr', width:'50', align:'center'},
		      {name:'shrLxr', index:'shrLxr', width:'50', align:'center'},
		      {name:'shrDz', index:'shrDz', width:'60', align:'center'},
		      {name:'xyMc', index:'xyMc', width:'70', align:'center'},
		      {name:'ssJgmc', index:'ssJgmc', width:'70', align:'center'},
		      
		      {name:'pcrq', index:'pcrq', width:'65', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}, 
		      {name:'pcdh', index:'pcdh', width:'70', align:'center'}
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum%>,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[20,50,100,300,500],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'XDRQ,ddbh',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
		    sortorder: 'ASC,ASC',				//默认排序方向
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/xyjssrdz!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            var dzztDm = $("[name='domain.dzztDm']:checked").val();
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var ywDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ywDjxh"); 	  //获取当前单元格里面的登记序号
                var dzDjxh = jQuery("#dataList").jqGrid('getCell', cl,"dzDjxh");
                var dzje= jQuery("#dataList").jqGrid('getCell', cl,"dzje");
                
                var link = "<input type='checkbox' name='xhs' value='"+ywDjxh+"' />";
                var dzLink = "<a href=\"javascript:onDz('"+ywDjxh+"')\"><font color=\"blue\">对账</font></a>";
                
                if (dzje != "") {
                	link = "<input type='checkbox' disabled='disabled' name='xhs' value='"+ywDjxh+"' />";
                	dzLink = "<a href=\"javascript:onUpdate('"+dzDjxh+"')\"><font color=\"blue\">"+dzje+"</font></a>"
                }
            	   
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
                $("#dataList").jqGrid('setRowData', cl, { 'dzje': dzLink }); 
            }
     }
     /**************************************分页结束****************************************/

</script>
</head>
<body>
<s:form action="xyjssrdz!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.fylbDm"></s:hidden>
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">操作：</li>
		    <li><a href="#" id="queryBtn" class="licon04">检 索</a></li>
		    <li><a href="#" id="pldzBtn" class="licon01">批量对账</a></li>
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
		   <table width="100%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		          <td width="8%" align="right">业务单位：</td>
					<td width="15%">
						<sys:gsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="N" myClass="select"/></td>
					<td width="8%" align="right">下游单位：</td>
					<td width="15%">
						<sys:fgsList myId="mainForm_domain_xyDjxh" myName="domain.xyDjxh" contaisQxz="true" contaisDq="N" myClass="select"></sys:fgsList>
					</td>
					<td width="8%" align="right">派车单号：</td>
      				<td width="12%">
      					<s:textfield name="domain.pcdh" cssClass="pop_input noborder bgstyle_optional"></s:textfield>
      				</td>
					<td width="8%" align="right">派车日期：</td>
					<td width="25%">
      					<input type="text" name="domain.pcrqQ" id="mainForm_domain_pcrqQ" value="<s:date name="domain.pcrqQ" format="yyyy-MM-dd" />" class="ymdate" />
			          	～
			          	<input type="text" name="domain.pcrqZ" id="mainForm_domain_pcrqZ" value="<s:date name="domain.pcrqZ" format="yyyy-MM-dd" />" class="ymdate" />
      				</td>
		        </tr>
		        <tr>
		        	<td align="right">订单编号：</td>
      				<td>
      					<s:textfield name="domain.ddbh" cssClass="pop_input noborder bgstyle_optional"></s:textfield>
      				</td>
      				<td align="right">件数：</td>
      				<td>
      					<s:textfield name="domain.hwSl" cssClass="pop_input noborder bgstyle_optional"></s:textfield>
      				</td>
      				<td align="right">回单编号：</td>
      				<td>
      					<s:textfield name="domain.hdbh" cssClass="pop_input noborder bgstyle_optional"></s:textfield>
      				</td>
      				<td align="right">下单日期：</td>
      				<td>
      					<input type="text" name="domain.xdrqQ" id="mainForm_domain_xdrqQ" value="<s:date name="domain.xdrqQ" format="yyyy-MM-dd" />" class="ymdate" />
			          	～
			          	<input type="text" name="domain.xdrqZ" id="mainForm_domain_xdrqZ" value="<s:date name="domain.xdrqZ" format="yyyy-MM-dd" />" class="ymdate" />
      				</td>
      			</tr>
      			<tr>
	    			<td align="right">对帐状态：</td>
			        <td colspan="2">
			          	<s:radio name="domain.dzztDm" list='#{"":"所有","1":"未对帐","2":"已对帐"}' listKey="key" listValue="value"></s:radio>
			        </td>
			        <td colspan="3">费用类别：
						<s:radio name="domain.fylbDm" onclick="changeQdList();" list="#{'1':'配送费','2':'到付款','3':'代收货款' }"></s:radio>
					</td>
			        <td align="right">对账清单：</td>
			        <td>
			          	<select name="domain.qdDjxh" id="mainForm_domain_qdDjxh" class="select">
			          		<option value="${domain.qdDjxh }"></option>
			          	</select>
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
