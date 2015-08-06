<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>托运单管理</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
			document.getElementById("maincont").onmousewheel=hideHelpWindow;
			initHykhData(300, $("#mainForm_domain_ssJgbm4Query").val(), $("#mainForm_domain_djJgbm4Query").val());
			
			$("#mainForm_domain_ssJgbm4Query").change(function(){
				$("[name='domain.fhrMc']").unbind();
				initHykhData(300, $(this).val(), $("#mainForm_domain_djJgbm4Query").val());
			});
			
			$("#mainForm_domain_djJgbm4Query").change(function(){
				initRy();
				$("[name='domain.fhrMc']").unbind();
				initHykhData(300, $("#mainForm_domain_ssJgbm4Query").val(), $(this).val());
			});
		
			//查询按钮事件
			$("#queryBtn").click(function(){
				xdrqQ = $("#mainForm_domain_xdrqQ").val();
				xdrqZ = $("#mainForm_domain_xdrqZ").val();
				xdrqQs=xdrqQ.split("-");
				xdrqZs=xdrqZ.split("-");
				if(xdrqQs.length!=3||xdrqZs.length!=3){
					showAlert("下单日期格式有误！");
					return;
				}
				onRefresh();
			});
			
			//新增按钮事件
			$("#addBtn").click(function(){
				onUpdate('');
				//popwindow(jcontextPath+"/hygl/hytydgl!initMx");
			});
			
			$("#mainForm_domain_ddbhQ").change(function(){
				$("#mainForm_domain_ddbhZ").val($(this).val())
			});
			//初始化表格
			initDataGrid();
			initRy();	
			var sjJgbm = $("#mainForm_domain_ssJgbm4Query").val();
			bmInit(sjJgbm, '', "domain.djJgbm4Query", "mainForm_domain_djJgbm4Query", "Y", "Y");
	});
	
	function initRy() {
		var sj = $("#mainForm_domain_djJgbm4Query").val();
		commonInit("BMYH", sj, '', "domain.djrCzyDjxh4Query", "mainForm_domain_djrCzyDjxh4Query", "Y", "Y");
	}
	

	function onViewMx(ddDjxh) {
		var url = jcontextPath+"/jcgl/jctydgl!initMx.action?domain.ddDjxh="+ddDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    	//window.open(url);
	}
	
	var keyValue = "";
	function onDelete( ddDjxh){
		keyValue = ddDjxh;
		showConfirm("确定要删除么？","doDelete");
	}	
	
	function onUpdate(ddDjxh) {
		var url = jcontextPath + '/hygl/hytydgl!initMxCy.action?domain.ddDjxh='+ddDjxh
		navigateMenu(url,'托运单修改','true');
    	//window.open(url);
    	//onRefresh();
	}
	
	function doDelete(){		
		showMessage();
		 var jsonObj = {"domain.ddDjxh":keyValue};
		 var url = jcontextPath+"/hygl/hytydgl!delete";   
        ajaxCommon(url,jsonObj , "deleteSuc");  
	}
	
	function deleteSuc(){  
		hideMessage();
        showAlert("删除成功！");
        onRefresh();
	}	
	
	function onUpdateHw(ddDjxh, xh) {
		var url = jcontextPath + '/hygl/hytydgl!initMxCy.action?domain.ddDjxh='+ddDjxh+'&domain.hwmxDomain.xh='+xh;
		navigateMenu(url,'托运单修改','true');
	}
	
	function onView(ddDjxh) {
		var url = jcontextPath+"/jcgl/jctydgl!initMx.action?domain.ddDjxh="+ddDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    	//window.open(url);
	}
	function onPrintView(ddDjxh, xh){
		var url = jcontextPath + '/hygl/hytydgl!printView.action?domain.hwmxDomain.ddDjxh='+ddDjxh+'&domain.hwmxDomain.xh='+xh;
		window.open(url, "_blank");
		//navigateMenu(url,'托运单打印','true');
    	//window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
	}
	
	function onPrintAll(ddDjxh, xh){
		var url = jcontextPath + '/hygl/hytydgl!printAll.action?domain.hwmxDomain.ddDjxh='+ddDjxh+'&domain.hwmxDomain.xh='+xh;
		window.open(url, "_blank");
	}
    
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		xdrqQ = $("#mainForm_domain_xdrqQ").val();
		xdrqZ = $("#mainForm_domain_xdrqZ").val();
		if(xdrqQ>xdrqZ){
			showError("下单开始日期不能大于下单截止日期！");
			return;
		}
		if (xdrqQ == "" || xdrqZ == "") {
			showAlert("下单日期不能为空！");
			return;
		}
		djJgbm4Query = $("#mainForm_domain_djJgbm4Query").val();
		ssJgbm4Query = $("#mainForm_domain_ssJgbm4Query").val();
		djrCzyDjxh4Query = $("#mainForm_domain_djrCzyDjxh4Query").val();
		fhrDjxh4Query = $("#mainForm_domain_fhrDjxh").val();
		fhrMc4Query = $("#mainForm_domain_fhrMc").val();
		
		ddbhQ = $("#mainForm_domain_ddbhQ").val();
		ddbhZ = $("#mainForm_domain_ddbhZ").val();
		fhrMc4 = $("#mainForm_domain_fhrMc4").val();
		shrMc4 = $("#mainForm_domain_shrMc4").val();
		hwMc4 = $("#mainForm_domain_hwMc4").val();
		hwSl4 = $("#mainForm_domain_hwSl4").val();
		hwZl4 = $("#mainForm_domain_hwZl4").val();
		hwTj4 = $("#mainForm_domain_hwTj4").val();
		zsr4 = $("#mainForm_domain_zsr4").val();
		shfsDm4 = $("#mainForm_domain_shfsDm4").val();
		yjWjBz4 = $("#mainForm_domain_yjWjBz4").val();

		var xjBz4 = ""; 
		var dfBz4 = ""; 
		var yjBz4 = "";
		var e1 = document.getElementById("xjBz4");
		if(e1.checked){
			xjBz4="1";
		} else {
			xjBz4="0";
		}
		var e2 = document.getElementById("dfBz4");
		if(e2.checked){
			dfBz4="1";
		} else {
			dfBz4="0";
		}
		var e3 = document.getElementById("yjBz4");
		if(e3.checked){
			yjBz4="1";
		} else {
			yjBz4="0";
		}

		//请求表格数据
		var url = jcontextPath+"/hygl/hytydgl!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.djJgbm4Query":djJgbm4Query,"domain.ssJgbm4Query":ssJgbm4Query, "domain.djrCzyDjxh4Query":djrCzyDjxh4Query,"domain.fhrMc4Query":encodeURI(fhrMc4Query),
		 			"domain.xdrqQ":xdrqQ,"domain.xdrqZ":xdrqZ,"domain.fhrDjxh4Query":fhrDjxh4Query,
		 			"domain.ddbhQ":ddbhQ,"domain.ddbhZ":ddbhZ,"domain.fhrMc4":encodeURI(fhrMc4),"domain.shrMc4":encodeURI(shrMc4),"domain.hwMc4":encodeURI(hwMc4),
		 			"domain.hwSl4":hwSl4,"domain.hwZl4":hwZl4,"domain.hwTj4":hwTj4,"domain.zsr4":zsr4,"domain.xjBz4":xjBz4,
		 			"domain.dfBz4":dfBz4,"domain.yjBz4":yjBz4,"domain.shfsDm4":shfsDm4,"domain.yjWjBz4":yjWjBz4
		 			}								//请求的参数，json格式
		 }
		 ).trigger("reloadGrid");		//显示到第一页，以免总体数据发生变化的时候，分页的信息混乱
	}
	
	<% 
	    //获取每个用户的每页显示参数值
		UserDomain userDomain=(UserDomain) request.getSession().getAttribute(WebConstants.SES_USER_INFO);
		String rowNum = "50";
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
		    colNames:['序号','操作', '订单登记序号','订单编号','下单日期', '发货单位','结算标志','打印',
		              '货物名称', '发货人','货物序号','始发地', '目的地','包装','结算数量', '数量', '重量', '体积','收入','到付','现付','月结',
		              '回扣','运输费','配送费','保价费','代收货款',
		              '收货单位','收货人', '送货方式', '要求发货日期', '要求到达日期', '状态', '登记人', '登记部门', '所属机构'
		              ],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[ 
			  {name:'pageXh', index:'pageXh', sortable:false, width:'30', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pageXh' + rowId + '\'';
			    }
			  },
			  {name:'hstoperationcol', index:'', sortable:false, width:'55', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'hstoperationcol' + rowId + '\'';
			    }
			  },
			  {name:'ddDjxh', index:'ddDjxh', hidden:true,width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'ddDjxh' + rowId + '\'';
			    }
			  },
			  {name:'ddbh', index:'ddbh', width:'65', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'ddbh' + rowId + '\'';
			    }
			  },
			  {name:'xdrq', index:'xdrq', width:'65', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'xdrq' + rowId + '\'';
				    }
			  },
			  {name:'fhrMc', index:'fhrMc', width:'130', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'fhrMc' + rowId + '\'';
				    }
			  },
			  {name:'yjWjBz', index:'yjWjBz', width:'70', align:'center',
			  		cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'yjWjBz' + rowId + '\'';
				    }
			  },
			  {name:'print', index:'', sortable:false, width:'55', align:'center'},
		      {name:'hwmc', index:'hwmc', width:'100', align:'center'}, 
		      {name:'fhrLxr', index:'fhrLxr', width:'40', align:'center'},
		      {name:'xh', index:'xh', hidden:true,width:'', align:'center'},
		      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'40', align:'center'},
			  {name:'shrXzqhMc', index:'shrXzqhMc', width:'40', align:'center'},
			 
			  
			  {name:'hwbz', index:'hwbz', width:'50', align:'center'}, 
		      {name:'jsSl', index:'jsSl', hidden:true,width:'60',align:'right'},		      
		      {name:'sl', index:'sl', width:'50', align:'right'}, 
		      {name:'zl', index:'zl', width:'50', align:'right'}, 
		      {name:'tj', index:'tj', width:'50', align:'right'}, 
		      {name:'srHj', index:'srHj', width:'70', align:'center'},
		      {name:'srDf', index:'srDf', width:'70', align:'center'},
		      {name:'srXf', index:'srXf', width:'70', align:'center'},
		      {name:'srYj', index:'srYj', width:'70', align:'center'},
		      
		      {name:'srHk', index:'srHk', width:'70', align:'center'},
		      {name:'srYsf', index:'srYsf', width:'70', align:'center'},
		      {name:'srPsf', index:'srPsf', width:'70', align:'center'},
		      {name:'srBjf', index:'srBjf', width:'70', align:'center'},
		      {name:'fyDshk', index:'fyDshk', width:'70', align:'center'},	
		      
		      
		      {name:'shrMc', index:'shrMc', width:'130', align:'center'},
		      {name:'shrLxr', index:'shrLxr', width:'40', align:'center'},
			  {name:'shfsMc', index:'shfsMc', width:'60', align:'center'},
			  {name:'yqFhrq', index:'yqFhrq', width:'75', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
			  {name:'yqDdrq', index:'yqDdrq', width:'75', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
			  {name:'hwztMc', index:'hwztMc', width:'50', align:'center'},
			  {name:'djrMc', index:'djrMc', width:'70', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'djrMc' + rowId + '\'';
			    }
			  },
			  {name:'djJgmc', index:'djJgmc', width:'130', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'djJgmc' + rowId + '\'';
			    }
			  },
			  {name:'ssJgmc', index:'ssJgmc', width:'130', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'ssJgmc' + rowId + '\'';
			    }
			  }
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum %>,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[20,50,100,200],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'DD_DJXH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/hytydgl!download.action");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                
                var pageXh = jQuery("#dataList").jqGrid('getCell',cl,"pageXh");
                if (pageXh == 0) {
                	$("#dataList").jqGrid('setRowData', cl, { 'pageXh': '合计' });
                	continue;
                }
                
                var val = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh"); 	  //获取当前单元格里面的登记序号 
                var hwmc = jQuery("#dataList").jqGrid('getCell', cl,"hwmc"); 
                var xh = jQuery("#dataList").jqGrid('getCell', cl,"xh");
                var ddbh = jQuery("#dataList").jqGrid('getCell', cl,"ddbh");
                var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh"); 
                var link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">修改</font></a>"
                + " <a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">删除</font></a>";
                var str="<a href=\"javascript:onViewMx("+ddDjxh+")\"><font color=\"blue\">"+ddbh+"</font></a>";
                var hwLink = "<a href=\"javascript:onUpdateHw('"+val+"','"+xh+"')\"><font color=\"blue\">"+hwmc+"</font></a>";
                var link2 = "<a href=\"javascript:onPrintView('"+val+"','"+xh+"')\"><font color=\"blue\">打印</font></a>"
                			+ "&nbsp;<a href=\"javascript:onPrintAll('"+val+"','"+xh+"')\"><font color=\"blue\">补打</font></a>";
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
                $("#dataList").jqGrid('setRowData', cl, { 'print': link2 }); 
                $("#dataList").jqGrid('setRowData', cl, { 'hwmc': hwLink });
                $("#dataList").jqGrid('setRowData', cl, { 'ddbh': str });
            }
       var gridName = "dataList";
	   var a = ['pageXh','hstoperationcol','ddDjxh','ddbh',
	            'fhrMc','xdrq','yjWjBz','djrMc','djJgmc','ssJgmc'
	            ];
 		
       Merger(gridName, 'pageXh', a);
   }
       
     /**************************************分页结束****************************************/
     
</script>
</head>
<body>
<s:form action="hytydgl!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<input type="hidden" name="ypcHwNum" id="ypcHwNum" />
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
		        <tr style="display: none;">
          			<td width="9%" align="right">业务单位：</td>
          			<td width="20%">
          				<sys:gsList myId="mainForm_domain_ssJgbm4Query" myName="domain.ssJgbm4Query" mcContainDmBz="Y" myClass="select" onChange="bmInit(this.value, '', 'domain.djJgbm4Query', 'mainForm_domain_djJgbm4Query', 'Y', 'Y')" />
		  			</td>
		  			<td width="10%" align="right">登记部门：</td>
          			<td width="20%">
          				<select id="mainForm_domain_djJgbm4Query" name="domain.djJgbm4Query" class="select" >
          					<option value="${domain.djJgbm4Query }" selected="selected"></option>
          				</select>
		  			</td>
	        	    <td width="10%" align="right">登记人：</td>
		            <td width="24%"><select name="domain.djrCzyDjxh4Query" id="mainForm_domain_djrCzyDjxh4Query" class="select" /></td>
        		</tr>
		        <tr>
	        	  <td width="10%" align="right">发货单位：</td>
		          <td width="20%">
		          	<s:hidden name="domain.fhrDjxh"></s:hidden>
  					<div class="inputsel" style="width: 215px; ">
  						<s:textfield name="domain.fhrMc" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width:185px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="fhr" onFocus="this.blur()"></a>
  					</div>
			  		<div class="inputsc">
		              <div id="inputSel_fhr" class="inputselcont inputselFixedSize ac_results">
		              </div>
		            </div>
		          </td>
		          <td  width="10%" align="right">货物名称：</td>
		          <td width="23%"><s:textfield name="domain.hwMc4" cssClass="pop_input noborder inputext bgstyle_optional" ></s:textfield></td>
	        	  <td width="10%" align="right">下单日期：</td>
		          <td width="26%"><sys:dateCurrentDayTag myName="domain.xdrqQ" myId="mainForm_domain_xdrqQ" myClass="ymdate" />
	          			～
	          		<sys:dateCurrentDayTag myName="domain.xdrqZ" myId="mainForm_domain_xdrqZ" myClass="ymdate" /></td>
		          </tr>
	           	  <tr>
	           		<td  align="right">发货人：</td>
		            <td><s:textfield name="domain.fhrMc4" cssClass="pop_input noborder inputext bgstyle_optional" ></s:textfield></td>
		            <td  align="right">收货人：</td>
		            <td><s:textfield name="domain.shrMc4" cssClass="pop_input noborder inputext bgstyle_optional" ></s:textfield></td>
	           		<td  align="right">订单编号起止：</td>
		            <td><s:textfield name="domain.ddbhQ" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width:40%"></s:textfield>
		            	～
		            <s:textfield name="domain.ddbhZ" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width:40%"></s:textfield></td>
	              </tr>
	              <tr>
	           		<td  align="right">数量：</td>
		            <td><s:textfield name="domain.hwSl4" cssClass="pop_input noborder inputext bgstyle_optional" ></s:textfield></td>
		            <td  align="right">重量：</td>
		            <td><s:textfield name="domain.hwZl4" cssClass="pop_input noborder inputext bgstyle_optional" ></s:textfield></td>
	           		<td  align="right">体积：</td>
		            <td><s:textfield name="domain.hwTj4" cssClass="pop_input noborder inputext bgstyle_optional" ></s:textfield></td>
	              </tr>
	        </table>
	        <table width="99%" border="0" cellspacing="0" cellpadding="0">
	              <tr>
		            <td  align="right" width="10%">总收入：</td>
		            <td width="20%"><s:textfield name="domain.zsr4" cssClass="pop_input noborder inputext bgstyle_optional" ></s:textfield></td>
	           		<td  align="right" width="10%">送货方式：</td>
		            <td width="9%"><s:select name="domain.shfsDm4" list="#{'':'所有',1:'自提',2:'送货'}" cssClass="select"/></td>
		            <td  align="right" width="10%">结算标志：</td>
		            <td width="9%"><s:select name="domain.yjWjBz4" list="#{'':'所有','Y':'已结','N':'未结'}" cssClass="select"/></td>
		            <td  align="right" width="10%">付款方式：</td>
		            <td width="21%">
		            	<s:checkbox name="domain.xjBz4" id="xjBz4"  checked="true" style="width:20px;"></s:checkbox>现付
		            	<s:checkbox name="domain.dfBz4" id="dfBz4" checked="true" style="width:20px;"></s:checkbox>到付
		            	<s:checkbox name="domain.yjBz4" id="yjBz4" checked="true" style="width:20px;"></s:checkbox>月结
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
