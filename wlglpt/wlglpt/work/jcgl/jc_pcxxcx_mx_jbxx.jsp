<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<head>
<title>派车基本信息</title>

<style type="text/css">
html,body {background:none;}
.hiddenCss {display: none;}
</style>

<script type="text/javascript">
$(function(){
	initDataGrid();
	
	onQueryPcHwxx();
	
	$("#closeBtn").click(function(){
		window.close();			
	});
	
	$("select").attr("disabled", true);
	$(":text").attr("readonly", true);
	var clsxDm = $("#mainForm_domain_clsxDm").val();
	$("#clsxDm").val(clsxDm);
});

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
function onQueryPcHwxx(){
	var pcfsDm = $("#mainForm_domain_pcfsDm").val();
	if (pcfsDm == "1") {
		$("#dataList").jqGrid('hideCol',["srHj"]);
	}
	 
	var pcDjxh = $("#mainForm_domain_pcDjxh").val();
	var pchwLsxh = $("#mainForm_domain_pchwLsxh").val();
	//请求表格数据
	 
	//请求表格数据
	var url = jcontextPath+"/hygl/hypcxxgl!queryPcHwxx.action";   
	 $("#dataList").jqGrid("setGridParam",{
	 	url:url,
	 	datatype:'json',
	 	postData:{"domain.pcDjxh":pcDjxh,"domain.pchwLsxh":pchwLsxh}
	 	//请求的参数，json格式
	 }
	 ).trigger("reloadGrid");		//显示到第一页，以免总体数据发生变化的时候，分页的信息混乱
}

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
    colNames:['订单登记序号','pchwLsxh','pcDjxh','订单编号','发货人',
            '货物名称','类别','未发货序号input','未发货序号',
            '数量','重量','体积','数量','重量','体积','包装','送货',
    		'回单号','始发地','目的地','发货日期','到达日期','总收入','到付','现付','月结','回扣',
    		'收货人','收货地址','发货地址','登记人','登记日期','登记部门','所属机构'],			 //name 列显示的名称
     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
    colModel :[
	  {name:'ddDjxh', index:'ddDjxh', hidden:true, width:'70', align:'center'},
	  {name:'pchwLsxh', index:'pchwLsxh', hidden:true, width:'70', align:'center'},
	  {name:'pcDjxh', index:'pcDjxh', hidden:true, width:'70', align:'center'},
	  {name:'ddbh', index:'ddbh', width:'70', sortable:false, align:'center', 
		cellattr: function(rowId, tv, rawObject, cm, rdata) {
		   return 'id=\'ddbh' + rowId + '\'';
	    }
	  },
	  {name:'fhrMc', index:'fhrMc', width:'130', sortable:false, align:'center', 
		cellattr: function(rowId, tv, rawObject, cm, rdata) {
		   return 'id=\'fhrMc' + rowId + '\'';
	    }
	  },
      {name:'hwmc', index:'hwmc', width:'100', sortable:false, align:'center'}, 
      {name:'lb', index:'lb', width:'40', sortable:false, align:'center'},
      {name:'xh', index:'xh', width:'100', hidden:true, align:'center'},
      {name:'wfhDjxh', index:'wfhDjxh', width:'100', hidden:true, align:'center'},
      {name:'sl', index:'sl', width:'50', sortable:false, align:'right'},
      {name:'zl', index:'zl', width:'50', sortable:false, align:'right'}, 
      {name:'tj', index:'tj', width:'50', sortable:false, align:'right'},
      {name:'hwSl', index:'hwSl', width:'30', hidden:true, sortable:false, align:'right'},
      {name:'hwZl', index:'hwZl', width:'30', hidden:true, sortable:false, align:'right'}, 
      {name:'hwTj', index:'hwTj', width:'30', hidden:true, sortable:false, align:'right'},
      {name:'bz', index:'bz', width:'30', sortable:false, align:'center'},
      {name:'pchwClfsDm', index:'pchwClfsDm', width:'30', sortable:false, align:'center'},

      {name:'hdbh', index:'hdbh', width:'70', sortable:false, align:'right'},
      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'40', sortable:false, align:'center'}, 
      {name:'shrXzqhMc', index:'shrXzqhMc', width:'40', sortable:false, align:'center'},
      {name:'fhRq', index:'fhRq', width:'70', sortable:false, align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}, 
      {name:'yqDdrq', index:'yqDdrq', width:'70', sortable:false, align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
      {name:'srHj', index:'srHj', width:'60', sortable:false, align:'center'},
      {name:'df', index:'df', width:'60', sortable:false, align:'center'},
      {name:'srXf', index:'srXf', width:'60', sortable:false, align:'center'},
      {name:'srYj', index:'srYj', width:'60', sortable:false, align:'center'},
      {name:'srHk', index:'srHk', width:'60', sortable:false, align:'center'},
      {name:'shrMc', index:'shrMc', width:'60', sortable:false, align:'center'}, 
      {name:'shDz', index:'shDz', width:'100', sortable:false, align:'center'}, 
      {name:'fhrDz', index:'fhrDz', width:'100', sortable:false, align:'center'},
      {name:'djrMc', index:'djrMc', width:'70', sortable:false, align:'center', 
		cellattr: function(rowId, tv, rawObject, cm, rdata) {
		   return 'id=\'djrMc' + rowId + '\'';
	    }
	  },
	  {name:'djRq', index:'djRq', width:'70', sortable:false, align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
		cellattr: function(rowId, tv, rawObject, cm, rdata) {
		   return 'id=\'djRq' + rowId + '\'';
	    }
	  },
	  {name:'djJgmc', index:'djJgmc', width:'100', sortable:false, align:'center', 
		cellattr: function(rowId, tv, rawObject, cm, rdata) {
		   return 'id=\'djJgmc' + rowId + '\'';
	    }
	  },
	  {name:'ssJgmc', index:'ssJgmc', width:'100', sortable:false, align:'center', 
		cellattr: function(rowId, tv, rawObject, cm, rdata) {
		   return 'id=\'ssJgmc' + rowId + '\'';
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
 	 	root: 	 "domain.pcHwxxList",   				// 数据行（默认为：rows）
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
  
}

//表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
function myGridComplete() {
    var graduateIds = $("#dataList").jqGrid('getDataIDs');
    var pcfsDm = $("#mainForm_domain_pcfsDm").val();
    
    var heightT = getAutoGridHeight(graduateIds.length);
    $("#dataList").setGridHeight(heightT);
    
    var srHjs = 0.00;
    var zls = 0.00;
    var tjs = 0.00;
    for (var i = 0; i < graduateIds.length; i++) {
        var cl = graduateIds[i];
        var pchwClfsDm = jQuery("#dataList").jqGrid('getCell', cl,"pchwClfsDm");
        
        var shStr = "";
        if (pchwClfsDm == "21" || pchwClfsDm == "31" ) {
        	shStr = "直送";
        }
        $("#dataList").jqGrid('setRowData', cl, { 'pchwClfsDm': shStr });
        
        var hj = jQuery("#dataList").jqGrid('getCell', cl,"srHj");
        var hwzl = jQuery("#dataList").jqGrid('getCell', cl,"hwZl");
        var hwtj = jQuery("#dataList").jqGrid('getCell', cl,"hwTj");
        // 提货没有收入
        if (hj != "" && pcfsDm != "1") {
        	srHjs += parseFloat(hj);
        }
        if (hwzl != "") {
        	zls += parseFloat(hwzl);
        }
        if (hwtj != "") {
        	tjs += parseFloat(hwtj);
        }
       
    }
    $("#mainForm_domain_zl").val(zls.toFixed(2));
    $("#mainForm_domain_tj").val(tjs.toFixed(2));
    $("#mainForm_domain_srHj").val(srHjs.toFixed(2));
    
   var gridName = "dataList";
	   var a = ['ddbh','fhrMc','djRq','djrMc','djJgmc','ssJgmc'];
		
    Merger(gridName, 'ddDjxh', a);
}
/**************************************分页结束****************************************/
	
</script>
</head>

<body>
<%try{ %>
	<s:hidden name="domain.pchwLsxh" />
	<s:hidden name="domain.pcfsDm" />
	<s:hidden name="domain.yfjsfDm" />
	<s:hidden name="domain.cyrClhmXh" />
	<s:hidden name="domain.cyrGchmXh" />
	<s:hidden name="domain.clsxDm"></s:hidden>
	<div class="pop_contc">
		<div id="divQuery">
			<fieldset>
			<legend>派车信息</legend>
			<table width="98%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
      				<td width="10%" align="right">派车单号：</td>
      				<td width="13%">
      					<s:if test="domain.xtcs20004==0">
      						<s:textfield name="domain.pcdh" cssClass="pop_input noborder inputext" ></s:textfield>
      					</s:if>
      					<s:else>
	  						<s:textfield name="domain.pcdh" cssClass="pop_input noborder" readonly="true"></s:textfield>
  						</s:else>
      				</td>
  					<td width="8%" align="right">重量：</td>
      				<td width="12%">
      					<s:textfield name="domain.zl" cssClass="pop_input noborder inputext"></s:textfield>
      				</td>
      				<td width="8%" align="right">体积：</td>
      				<td width="11%">
      					<s:textfield name="domain.tj" cssClass="pop_input noborder inputext"></s:textfield>
      				</td>
      				<td width="8%" align="right">收入：</td>
      				<td width="10%">
      					<s:textfield name="domain.srHj" cssClass="pop_input noborder inputext"></s:textfield>
      				</td>
      				<td width="10%" align="right"></td>
      				<td width="10%">
      				</td>
      			</tr>
    			<tr>
    				<td align="right">车辆：</td>
    				<td>
    					<select id="clsxDm" onchange="changeClxx();" class="select">
    						<option value="1">自营车辆</option>
    						<option value="2">社会车辆</option>
    					</select>
    				</td>
    				<td align="right">号码：</td>
    				<td>
 						<s:textfield name="domain.cyrClhm" cssClass="pop_input noborder inputext"></s:textfield>
    				</td>
    				<td align="right">挂车：</td>
    				<td>
 						<s:textfield name="domain.cyrGchm" cssClass="pop_input noborder inputext"></s:textfield>
    				</td>
    				<td align="right">车主：</td>
    				<td>
    					<s:textfield name="domain.cyrCzxm" cssClass="pop_input noborder" />
    				</td>
    				<td align="right">到站日期：</td>
    				<td>
    					<input type="text" name="domain.dzrq" id="mainForm_domain_dzrq" value="<s:date name="domain.dzrq" format="yyyy-MM-dd" />" class="pop_input noborder"  />
    				</td>
    			</tr>
    			<tr>
    				<td align="right">司机：</td>
    				<td>
    					<s:textfield name="domain.cyrSjxm" cssClass="pop_input noborder" ></s:textfield>
    				</td>
    				<td align="right">身份证：</td>
    				<td>
    					<s:textfield name="domain.cyrSjsfz" cssClass="pop_input noborder" ></s:textfield>
    				</td>
    				<td align="right">手机：</td>
    				<td>
    					<s:textfield name="domain.cyrSjsjhm" cssClass="pop_input noborder" ></s:textfield>
    				</td>
    				<td align="right">电话1：</td>
    				<td>
    					<s:textfield name="domain.cyrQtlxdh" cssClass="pop_input noborder" />
    				</td>
    				<td align="right">电话2：</td>
    				<td>
    					<s:textfield name="domain.cyrQtlxdh2" cssClass="pop_input noborder" />
    				</td>
    			</tr>
   			</table>
			<table width="98%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      			<tr>
      				<td align="right" width="6%">运费：</td>
      				<td width="8%">
      					<s:textfield name="domain.yfHj" cssClass="yfxx pop_input inputright noborder" ></s:textfield>
      				</td>
      				<td align="right" width="6%">预付：</td>
      				<td width="8%">
      					<s:textfield name="domain.yfYfyf" cssClass="yfxx pop_input inputright noborder" ></s:textfield>
      				</td>
      				<td align="right" width="6%">到付：</td>
      				<td width="8%">
      					<s:textfield name="domain.yfHdyf" cssClass="yfxx pop_input inputright noborder" ></s:textfield>
      				</td>
      				<td align="right" width="6%">回付：</td>
      				<td width="8%">
      					<s:textfield name="domain.yfHdf" cssClass="yfxx pop_input inputright noborder" ></s:textfield>
      				</td>
      				<td align="right" width="8%">司机收：</td>
      				<td width="7%">
      					<s:textfield name="domain.yfSjs" cssClass="yfxx pop_input inputright noborder" ></s:textfield>
      				</td>
      				<td align="right" width="8%">信息费：</td>
      				<td width="7%">
      					<s:textfield name="domain.yfXxf" cssClass="pop_input inputright noborder" ></s:textfield>
      				</td>
      				<td align="right" width="6%">押金：</td>
      				<td width="8%">
      					<s:textfield name="domain.yfYj" cssClass="pop_input inputright noborder" ></s:textfield>
      				</td>
      			</tr>
      			<tr>
      				<td align="right">备注：</td>
      				<td colspan="13">
      					<s:textarea name="domain.bz" rows="3" cssClass="pop_textarea_colspan2 noborder" ></s:textarea>
      				</td>
      			</tr>
			</table>
			</fieldset>
			</div>
		  </div>
	    <div class="pop_btn">
		    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
	    </div>
	<%@include file="/common/message.jsp" %>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
