<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>派车信息管理-选择货物</title>
<style type="text/css">
.hiddenCss {display: none;}
</style>
<script type="text/javascript">
	$(document).ready(function(){
		/***********************初始化dropdownInputSel下拉begin************************/
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		initXzqhData(200);
		initHykhData(300);
		initXzqhInputSel();
		/***********************初始化dropdownInputSel下拉end************************/
		
		//查询按钮事件
		$("#queryBtn").click(function(){
			onRefresh();
		});
		
		//初始化表格
		initWfhDataGrid();
		onRefresh();
		
		$("#yesBtn").click(function(){
			checkSaveWfhxx4Pc();
		});
		
	});
	
	function checkSaveWfhxx4Pc() {
		var checks = $(":input:checked[name='wfhXhs']");
		if (checks.length <= 0) {
			showAlert("请先选择需要派车的货物！");
			return;
		}
		
		if (!checkZrDzdata()) {
			return;
		}
		
		if ($("#mainForm_domain_zrbmDm").val() == "3" || $("#sfjsId")[0].checked) {
			if (!checkZc()) {
				return;
			}
			var xhs = $(":input:checked[name='wfhXhs']");
			if(xhs.length!=1){
				showAlert("转分包商一次只能添加一条货物！");
				return;
			}
			var hk = $("#mainForm_domain_pcHwxxDomain_zcHk").val();
			if (hk/1 > 0) {
				showConfirm("该支出中有产生回扣，是否确认添加货物？","doSaveWfhxx4Pc");
			}else {
				doSaveWfhxx4Pc();
			}
		}else {
			doSaveWfhxx4Pc();
		}
		
	}
	
	function doSaveWfhxx4Pc() {
		var wfhXhs = getCheckedWfhXhs();
		saveWfhxx4Pc(wfhXhs);
	}
	
	function checkZc() {
		var hk = $("#mainForm_domain_pcHwxxDomain_zcHk").val();
		if (hk/1 == 0) {
			if (($("#mainForm_domain_pcHwxxDomain_zcXf").val()/1+$("#zcDf").val()/1+$("#mainForm_domain_pcHwxxDomain_zcHf").val()/1)+$("#mainForm_domain_pcHwxxDomain_zcYj").val()/1 != $("#mainForm_domain_pcHwxxDomain_zcHj").val()/1) {
				alert("支出录入有误，支出需满足公式：\"总支出=现付\+到付\+月结\"，请检查！");
				return false;
			}			
		}
		return true;
	}
	
	function getCheckedWfhXhs() {
		var checks = $(":input:checked[name='wfhXhs']");
		
		var xhs = "";
		$.each(checks, function(i, obj){
			xhs += obj.value + ",";
		});
		if (xhs.length > 0) {
			xhs = xhs.substring(0, xhs.length-1);
		}
		
		return xhs;
	}
	
	function setDf(obj,isAllClick) {
		if (!($("#mainForm_domain_zrbmDm").val() == "3" || $("#sfjsId")[0].checked)) {
			return;
		}
		var graduateIds = $("#wfhList").jqGrid('getDataIDs');
		if (isAllClick) {
			var checks = $("[name='wfhXhs']:checked");
			var dfHj = 0.0;
			$.each(checks, function(i, obj){
				var rowIndex = $(obj).parents("tr").index() - 1;
				if (rowIndex >= 0) {
					var df = jQuery("#wfhList").jqGrid('getCell', graduateIds[rowIndex],"df")/1;
					if (df != "") {
						dfHj += parseFloat(df);
					}
				}
			});
			$("#zcDf").val(dfHj.toFixed(2));
			calHk();
		}else {
			var rowIndex = $(obj).parents("tr").index() - 1;
			if (rowIndex >= 0) {
				var df = jQuery("#wfhList").jqGrid('getCell', graduateIds[rowIndex],"df")/1;
				if (df != "") {
					if (obj.checked) {
						$("#zcDf").val(($("#zcDf").val()/1 + parseFloat(df)).toFixed(2));
					}else {
						$("#zcDf").val(($("#zcDf").val()/1 - parseFloat(df)).toFixed(2));
					}
					calHk();
				}
			}
		}
		
	}

	function onViewMx(ddDjxh) {
		var url = jcontextPath+"/jcgl/jctydgl!initMx.action?domain.ddDjxh="+ddDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    	//window.open(url);
	}
	
	// 重写base.js中的setGridAuto方法
	function setGridAuto(){  
	    var gridTabWidth=pageWidth()-10;  	//去掉宽度的计算，只保留高度
	    $("#dataList").setGridWidth(gridTabWidth);  
	    $("#wfhList").setGridWidth(gridTabWidth);  
	} 
	
	function getAutoGridHeight(length) {
		var heightT = 260;
	    if (length <= 2) {
	    	heightT = 2 * 25 + 15;
	    }else if (length <= 10) {
	    	heightT = length * 25 + 15;
	    }
	    
	    return heightT;
	}
	
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		var pcfsDm = $("#mainForm_domain_pcfsDm").val();
		if (pcfsDm == undefined) {
			pcfsDm = "";
		}
		if (pcfsDm == "1") {
			$("#wfhList").jqGrid('hideCol',["srHj","df"]);
		}
		
		 dw4Query = $("#mainForm_domain_dw4Query").val();
		 fhrXzqhDm = $("#mainForm_domain_fhrXzqhDm").val();
		 shrXzqhDm = $("#mainForm_domain_shrXzqhDm").val();
		 ddbh4Query = $("#mainForm_domain_ddbh4Query").val();
		 fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		 fhrMc = $("#mainForm_domain_fhrMc").val();
		 hwztDm4Query = '';//$("#mainForm_domain_hwztDm4Query").val();
		 lb4Query = $("#mainForm_domain_lb4Query").val();
		 djJgbm4Query = $("#mainForm_domain_djJgbm4Query").val();
		 djJgbm4Query = djJgbm4Query == null ? "" : djJgbm4Query;
		 fhrqQ = '';//$("#mainForm_domain_fhrqQ").val();
		 fhrqZ = '';//$("#mainForm_domain_fhrqZ").val();
		 var pcfsDm = $("#mainForm_domain_pcfsDm").val();
		 var pchwLsxh = $("#mainForm_domain_pchwLsxh").val();
		 var pcDjxh = $("#mainForm_domain_pcDjxh").val();
		//请求表格数据
		var url = jcontextPath+"/hygl/hypcxxgl!queryWfhxx.action";   
		 $("#wfhList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.dw4Query":encodeURI(dw4Query),"domain.fhrXzqhDm":encodeURI(fhrXzqhDm),"domain.shrXzqhDm":encodeURI(shrXzqhDm),
		 			"domain.ddbh4Query":ddbh4Query,"domain.fhrDjxh":fhrDjxh,"domain.fhrMc":encodeURI(fhrMc),
		 			"domain.hwztDm4Query":hwztDm4Query,"domain.lb4Query":encodeURI(lb4Query),"domain.djJgbm4Query":djJgbm4Query,
		 			"domain.fhrqQ":fhrqQ,"domain.fhrqZ":fhrqZ,"domain.pcfsDm":pcfsDm,
		 			"domain.pchwLsxh":pchwLsxh,"domain.pcDjxh":pcDjxh}
		 			//请求的参数，json格式
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
	function initWfhDataGrid(){ 
	  $("#wfhList").jqGrid({
	    url:"",
	    datatype: 'local',
	    mtype: 'POST',
	    rownumbers : false,					//序号列
		width:pageWidth()-20,  
		height:pageTableHeight()-90,	
	    gridComplete: myWfhGridComplete,		//表格加载完毕事件
	    shrinkToFit:false, 
	    colNames:['序号','订单登记序号','货物顺序号','未发货登记序号', '订单编号','客户名称','<input title="选中/取消选中" type="checkbox" onclick="checkAllJGridChk(event,this,\'wfhXhs\');setDf(null,true);" />',
	    		  '货物名称','始发地','目的地','包装','类别','结算数量','数量','重量','体积','收入','到付','提货付','货到付',
	    		  'shfsDm','送货方式','收货人','收货地址','要求发货日期','要求到达日期','状态',
	    		  '发货地址','登记人','登记日期','登记部门','所属机构'],			 //name 列显示的名称
	     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
	    colModel :[ 
		  {name:'pageXh', index:'pageXh', sortable:false, width:'30', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'pageXhWfh' + rowId + '\'';
		    }
		  },
		  {name:'ddDjxh', index:'ddDjxh', hidden:true,width:'60', align:'center'},
		  {name:'xh', index:'xh', width:'100', hidden:true, align:'center'}, 
		  {name:'wfhDjxh', index:'wfhDjxh', hidden:true, width:'100', align:'center'},
		  {name:'ddbh', index:'ddbh', width:'70', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'ddbhWfh' + rowId + '\'';
		    }
		  },
		  {name:'fhrMc', index:'fhrMc', width:'120', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'fhrMcWfh' + rowId + '\'';
		    }
		  },
		  {name:'hstoperationcol', index:'hstoperationcol', width:'25', align:'center'},
	      {name:'hwmc', index:'hwmc', width:'100', align:'center'}, 
	      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'40', align:'center'}, 
	      {name:'shrXzqhMc', index:'shrXzqhMc', width:'40', align:'center'}, 
	      {name:'bz', index:'bz', width:'50', align:'center'}, 
	      {name:'lb', index:'lb', width:'40', align:'center'},
	      {name:'jssl', index:'jssl',hidden:true, width:'50', align:'right'},
	      {name:'sl', index:'sl', width:'50', align:'right'}, 
	      {name:'zl', index:'zl', width:'50', align:'right'}, 
	      {name:'tj', index:'tj', width:'50', align:'right'}, 
	      {name:'srHj', index:'srHj', width:'45', align:'center'},
	      {name:'df', index:'df', width:'45', align:'center'},
	      {name:'thf', index:'thf', width:'45',hidden:true, align:'center'},
	      {name:'hdf', index:'hdf', width:'45',hidden:true, align:'center'},
	      {name:'shfsDm', index:'shfsDm', width:'60',hidden:true, align:'center'},
	      {name:'shfsMc', index:'shfsMc', width:'60', align:'center'},
	      {name:'shrMc', index:'shrMc', width:'60', align:'center'}, 
	      {name:'shDz', index:'shDz', width:'100', align:'center'}, 
	      {name:'fhRq', index:'fhRq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
	      {name:'yqDdrq', index:'yqDdrq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
	      {name:'hwztMc', index:'hwztMc', width:'40', align:'center'},
	      {name:'fhrDz', index:'fhrDz', width:'100', align:'center'},
	      {name:'djrMc', index:'djrMc', width:'70', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'djrMcWfh' + rowId + '\'';
		    }
		  },
		  {name:'djRq', index:'djRq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'djRqWfh' + rowId + '\'';
		    }
		  },
		  {name:'djJgmc', index:'djJgmc', width:'100', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'djJgmcWfh' + rowId + '\'';
		    }
		  },
		  {name:'ssJgmc', index:'ssJgmc', width:'100', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'ssJgmcWfh' + rowId + '\'';
		    }
		  }
	    ],
	    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
	    rowNum: -1,							//在grid上显示记录条数，这个参数是要被传递到后台
	    rowList:[-1],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
	    sortname: 'DD_DJXH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
	    sortorder: 'DESC',				//默认排序方向
	    viewrecords: true,					//定义是否要显示总记录数
	    gridview: false,					//默认false 构造一行数据后添加到grid中，如果设为true则是将整个表格的数据都构造完成后再添加到grid中，但treeGrid, subGrid, or afterInsertRow 不能用	
	    jsonReader: {     
	 	 	root: 	 "domain.wfhList",   				// 数据行（默认为：rows）
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
	  jQuery("#wfhList").jqGrid('navGrid','#pager',
	 		 {edit:false,add:false,del:false}
	  );
	}
	
	//表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
	function myWfhGridComplete() {
	    var graduateIds = $("#wfhList").jqGrid('getDataIDs');
	    
	    var heightT = getAutoGridHeight(graduateIds.length);
	    $("#wfhList").setGridHeight(heightT);
	    
	    for (var i = 0; i < graduateIds.length; i++) {
	        var cl = graduateIds[i];
	        var wfhDjxh = jQuery("#wfhList").jqGrid('getCell', cl,"wfhDjxh");
	        var ddbh = jQuery("#wfhList").jqGrid('getCell', cl,"ddbh");
	        var ddDjxh = jQuery("#wfhList").jqGrid('getCell', cl,"ddDjxh");
	        //var link = "<input type=\"checkbox\" onclick=\"setDf(this)\" name=\"wfhXhs\" value=\""+wfhDjxh+"\" />";
	        var link = "<input type=\"checkbox\" onclick=\"setDf(this)\" name=\"wfhXhs\" value=\""+wfhDjxh+"\" />";
	        var str="<a href=\"javascript:onViewMx("+ddDjxh+")\"><font color=\"blue\">"+ddbh+"</font></a>";
	        $("#wfhList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
	        $("#wfhList").jqGrid('setRowData', cl, { 'ddbh': str }); 
	    }
	    
	   var gridNameWfh = "wfhList";
		   var aWfh = ['pageXhWfh','ddbhWfh','fhrMcWfh','djRqWfh','djrMcWfh','djJgmcWfh','ssJgmcWfh'];
		   var cellNames = ['pageXh','ddbh','fhrMc','djRq','djrMc','djJgmc','ssJgmc'];
			
	    Merger(gridNameWfh, 'ddDjxh', aWfh,cellNames);
	}
/**************************************分页结束****************************************/
</script>
</head>
<body>
	<s:hidden name="domain.dw4Query" cssClass="reserveText"></s:hidden>
	<s:hidden name="jsonData" cssClass="reserveText" />
	<s:hidden name="fhrData" cssClass="reserveText" />
	
	<div class="right_cont">
	 <div id="divQuery">
	 	<fieldset>
			<legend>未发货查询</legend>
		   <table width="98%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
	  				<td width="6%" class="td_noborder"></td>
	  				<td width="10%" class="td_noborder"></td>
	  				<td width="8%" class="td_noborder"></td>
	  				<td width="8%" class="td_noborder"></td>
	  				<td width="8%" class="td_noborder"></td>
	  				<td width="8%" class="td_noborder"></td>
	  				<td width="6%" class="td_noborder"></td>
	  				<td width="8%" class="td_noborder"></td>
	  				<td width="6%" class="td_noborder"></td>
	  				<td width="15%" class="td_noborder"></td>
	  				<td width="9%" class="td_noborder"></td>
	  				<td width="8%" class="td_noborder"></td>
	  			</tr>
		        <tr>
		          <td align="right">部门：</td>
		          <td>
		          	<sys:bmList myId="mainForm_domain_djJgbm4Query" myName="domain.djJgbm4Query" sjJgbm="domain.dw4Query" contaisQxz="true" myClass="select"></sys:bmList>
		          </td>
		          <td align="right">始发地：</td>
		          <td>
		          	<s:hidden name="domain.fhrXzqhDm" />
  					<div class="inputsel" style="width: 60px;">
  						<s:textfield name="domain.fhrXzqhMc" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width: 30px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="xzqh" xzqh="fhrXzqh" onFocus="this.blur()"></a>
		            </div>
		          </td>
		          <td align="right">目的地：</td>
		          <td>
		          	<s:hidden name="domain.shrXzqhDm"></s:hidden>
  					<div class="inputsel" style="width: 60px;">
  						<s:textfield name="domain.shrXzqhMc" cssClass="inputext pop_input noborder bgstyle_optional" cssStyle="width: 30px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="xzqh" xzqh="shrXzqh" onFocus="this.blur()"></a>
		            </div>
		          </td>
		          <td align="right">类别：</td>
		          <td align="right">
		          	<sys:Hwfl myName="domain.lb4Query" myId="mainForm_domain_lb4Query" contaisQxz="true" myClass="select"></sys:Hwfl>
		          </td>
		          <td align="right">客户：</td>
		          <td>
		          	<s:hidden name="domain.fhrDjxh"></s:hidden>
  					<div class="inputsel" style="width: 120px; ">
  						<s:textfield name="domain.fhrMc" cssClass="pop_input noborder inputext" cssStyle="width:90px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="fhr" onFocus="this.blur()"></a>
  					</div>
			  		<div class="inputsc">
		              <div id="inputSel_fhr" class="inputselcont inputselFixedSize ac_results">
		              </div>
		            </div>
		          </td>
		          <td align="right">订单编号：</td>
		          <td><s:textfield name="domain.ddbh4Query" cssClass="pop_input noborder" /></td>
		        </tr>
		   </table>
		  </fieldset>
	  </div>
	    <table id="wfhList"><tr><td/></tr></table> 
		<!-- 分页导航 -->
		<%@include file="/common/message.jsp" %>
	</div>
	<div id="inputSel_xzqh" class="inputselcont" style="position: absolute; top: 159px; left: 94px; display: none;" >
		                
      <iframe src="" scrolling="no" frameborder="0" style="position:absolute; top:0; left:0; z-index:-1; width:274px; height:100px;"></iframe>
    </div>
</body>
</html>
