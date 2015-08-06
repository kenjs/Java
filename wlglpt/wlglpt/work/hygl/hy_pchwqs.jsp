<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>派车-货物签收</title>
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
				popwindow(jcontextPath+"/hygl/hypchwqs!initMx");
			});
			//初始化表格
			initDataGrid();
			$(":radio[name='domain.sfJs']")[1].checked=true;			

	});

    function onUpdate(pcDjxh,wfhDjxh,ddDjxh,xh,shfsDm,xybz){
    	var xtcs20016 = $("#mainForm_domain_xtcs20016").val();
		if(xtcs20016=='Y'){
			if(xybz=='N'){
				showAlert("请先协议登记！");
				return;
			}
		}
    	var url = jcontextPath+"/hygl/hypchwqs!initMx.action?domain.pcDjxh="+pcDjxh+"&domain.wfhDjxh="+wfhDjxh+
    			"&domain.ddDjxh="+ddDjxh+"&domain.xh="+xh+"&domain.shfsDm="+shfsDm;
    	window.showModalDialog(url,window,"dialogHeight:300px;dialogWidth:450px;center:yes;resizable:no;status:no;scroll:no;help:no;maximize:yes;minimize:yes;")
    	//window.open(url);
    	onRefresh();
    }
    
    function onPsf(pcDjxh,wfhDjxh){
    	var url = jcontextPath+"/hygl/hypchwqs!initPsfMx.action?domain.pcDjxh="+pcDjxh+"&domain.wfhDjxh="+wfhDjxh;
    	window.showModalDialog(url,window,"dialogHeight:300px;dialogWidth:450px;center:yes;resizable:no;status:no;scroll:no;help:no;maximize:yes;minimize:yes;")
    	//window.open(url);
    	onRefresh();
    }
    
    var keyValue = "";
    var kV = "";
    var keyVa = "";
	function onDelete( hwqsDjxh,pcDjxh,wlssDjxh){
		keyValue = hwqsDjxh;
		kV = wlssDjxh;
		keyVa = pcDjxh;
		showConfirm("确定要删除么？","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.hwqsDjxh":keyValue,"domain.wlssDjxh":kV,"domain.pcDjxh":keyVa};
		 var url = jcontextPath+"/hygl/hypchwqs!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("删除成功！");
        onRefresh();
	}	
    
	function onWlssDj(pcDjxh,wfhDjxh,ddDjxh,xh,wlssDjxh){
		var url = jcontextPath+"/hygl/wlssdj!initMx.action?domain.pcDjxh="+pcDjxh+"&domain.wfhDjxh="+wfhDjxh+
				"&domain.wlssLybz=2"+"&domain.ddDjxh="+ddDjxh+"&domain.xh="+xh+"&domain.wlssDjxh="+wlssDjxh+"&number="+Math.random();
    	window.showModalDialog(url,window,"dialogHeight:600px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
    	onRefresh();
	}
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		pcrqq = $("#mainForm_domain_pcrqq").val();
		pcrqz = $("#mainForm_domain_pcrqz").val();
		//var sfJs=$(":radio[name='sfJs']")[0].checked?"1":"2";
		var radio=$(":checked[name='domain.sfJs']");
		var sfJs=radio[0].value;
		//请求表格数据
		var url = jcontextPath+"/hygl/hypchwqs!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.pcrqq":pcrqq,"domain.pcrqz":pcrqz,"domain.sfJs":sfJs}								//请求的参数，json格式
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
		    colNames:['序号','操作','派车登记序号','wfhDjxh','psfy', 'wlssDjxh','xh','派车单号','状态','派车日期','ddDjxh','物流损失登记','货物签收序号','订单编号',
		              '','送货方式','配送费','是否确认','确认说明','回单编号','xybz',
		              '货物名称','包装', '','','','数量', '重量', '体积', '到付','始发地', '目的地',
		              '收货人','收货地址','收货人联系电话','车辆号码','司机','联系电话','签收人','签收日期'],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[ 
			  {name:'pageXh', index:'pageXh', sortable:false, width:'30', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pageXh' + rowId + '\'';
			    }
			  },
			  {name:'hstoperationcol', index:'', sortable:false, width:'30', align:'center',
				  cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'hstoperationcol' + rowId + '\'';
				    } 
			  },
		      {name:'pcDjxh', index:'pcDjxh', width:'100', hidden:true, align:'center'},
		      {name:'wfhDjxh', index:'wfhDjxh', width:'100', hidden:true, align:'center'},
		      {name:'psfy', index:'psfy', width:'100', hidden:true, align:'center'},
		      {name:'wlssDjxh', index:'wlssDjxh', width:'100', hidden:true, align:'center'},
		      {name:'xh', index:'xh', width:'100', hidden:true, align:'center'},
		      {name:'pcdh', index:'pcdh', width:'65', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcdh' + rowId + '\'';
				    }
			  },
			  {name:'jszt', index:'', sortable:false, width:'45', align:'center',
			  	cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'jszt' + rowId + '\'';
				    }
			  },
			  {name:'pcrq', index:'pcrq', width:'65', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcrq' + rowId + '\'';
				    }
			  },
			  {name:'ddDjxh', index:'ddDjxh', width:'100', hidden:true, align:'center'},
			  {name:'wlssOPera',index:'',width:'80',align:'center'},
		      {name:'hwqsDjxh', index:'hwqsDjxh', width:'100',hidden:true, align:'center'}, 
		      {name:'ddbh', index:'ddbh', width:'70', align:'center'},
		      {name:'shfsDm', index:'shfsDm', width:'0',hidden:true, align:'center'},
			  {name:'shfsMc', index:'shfsMc', width:'60', align:'center'},
			  {name:'psf',index:'',width:'40',sortable:false,align:'center'},
			  {name:'sfqr', index:'sfqr', width:'60', align:'center'},
		      {name:'qrsm', index:'qrsm', width:'100', align:'center'},
		      {name:'hdbh', index:'hdbh', width:'60', align:'center'},
		      {name:'xybz', index:'xybz', width:'100', align:'center',hidden:true},
			  {name:'hwMc', index:'hwMc', width:'100', align:'center'},
			  {name:'hwbz', index:'hwbz', width:'50', align:'center'},
			  {name:'sl', index:'sl', width:'0', align:'right',hidden:true},
			  {name:'zl', index:'zl', width:'0', align:'right',hidden:true},
			  {name:'tj', index:'tj', width:'0', align:'right',hidden:true},
		      {name:'slStr', index:'slStr', width:'50', align:'right'},  
		      {name:'zlStr', index:'zlStr', width:'50', align:'right'},
		      {name:'tjStr', index:'tjStr', width:'50', align:'right'},
		      {name:'srDf', index:'srDf', width:'50', align:'center'},
		      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'40', align:'center'},
		      {name:'shrXzqhMc', index:'shrXzqhMc', width:'40', align:'center'},
		      
		      {name:'shrMc', index:'shrMc', width:'100', align:'center'},
		      {name:'shrDz', index:'shrDz', width:'200', align:'center'},
		      {name:'shrLxdh', index:'shrLxdh', width:'110', align:'center'}, 
		      {name:'qsrMc', index:'qsrMc', width:'50', align:'center'},
			  {name:'qsrq', index:'qsrq', width:'65', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
			  {name:'cyrClhm', index:'cyrClhm', width:'60', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'cyrClhm' + rowId + '\'';
				    }
			  },

			  {name:'cyrSjxm', index:'cyrSjxm', width:'50', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'cyrSjxm' + rowId + '\'';
				    }
			  },
			  {name:'cyrSjsjhm', index:'cyrSjsjhm', width:'50', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'cyrSjsjhm' + rowId + '\'';
				    }
			  }			  
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum%>,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[20,50,100,300,500],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'HWQS_DJXH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/hypchwqs!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var hwqsDjxh = jQuery("#dataList").jqGrid('getCell', cl,"hwqsDjxh"); 	  //获取当前单元格里面的登记序号
                var pcDjxh = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh");
                var wlssDjxh = jQuery("#dataList").jqGrid('getCell', cl,"wlssDjxh");
                var wfhDjxh = jQuery("#dataList").jqGrid('getCell', cl,"wfhDjxh");
                var psfy = jQuery("#dataList").jqGrid('getCell', cl,"psfy");
                var sfqr = jQuery("#dataList").jqGrid('getCell', cl,"sfqr");
                var shfsMc = jQuery("#dataList").jqGrid('getCell', cl,"shfsMc");
				var shfsDm = jQuery("#dataList").jqGrid('getCell', cl,"shfsDm");
                var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh");
                var xh = jQuery("#dataList").jqGrid('getCell', cl,"xh");
                var xybz = jQuery("#dataList").jqGrid('getCell', cl,"xybz");
                var psfLink = "";
                var qrLink = "";
                var jsStr = "<font color=\"red\">未接收</font>";
                var link = "<a href=\"javascript:onUpdate('"+pcDjxh+"','"+wfhDjxh+"','"+ddDjxh+"','"+xh+"','"+shfsDm+"','"+xybz+"')\"><font color=\"blue\">接收</font></a>";
                var wlssLink = "<a href=\"javascript:onWlssDj('"+pcDjxh+"','"+wfhDjxh+"','"+ddDjxh+"','"+xh+"','"+wlssDjxh+"')\"><font color=\"blue\">登记</font></a>";
                if (hwqsDjxh != "") {
                	jsStr = "已接收";
                	wlssLink = "已登记";
                	link = "<a href=\"javascript:onDelete('"+hwqsDjxh+"','"+pcDjxh+"','"+wlssDjxh+"')\"><font color=\"blue\">取消</font></a>";
                }
                //配送费累加标志 控制是否允许录入配送费 2013-09-29 by xiay
                if(${domain.ljbz=="N"}){
                	psfLink = ""; 
                }else{
	                if(psfy == ""){
	                	psfLink = "<a href=\"javascript:onPsf('"+pcDjxh+"','"+wfhDjxh+"')\"><font color=\"blue\">录入</font></a>";
	                }else{
	                	psfLink = "<a href=\"javascript:onPsf('"+pcDjxh+"','"+wfhDjxh+"')\"><font color=\"blue\">"+psfy+"</font></a>";
	                }
                }
                if(sfqr == 'Y'){
                	qrLink = "<font color=\"black\">已确认</font>";
                	psfLink = psfy;
                }else{
                	qrLink = "<font color=\"red\">未确认</font>";
                }
                if(shfsMc == '自提'){
                	qrLink = "";
                	psfLink = "";
                }
                
                $("#dataList").jqGrid('setRowData', cl, { 'jszt': jsStr }); 
                $("#dataList").jqGrid('setRowData', cl, { 'psf': psfLink }); 
                $("#dataList").jqGrid('setRowData', cl, { 'sfqr': qrLink }); 
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
                $("#dataList").jqGrid('setRowData', cl, { 'wlssOPera': wlssLink }); 
            }
            
            var gridName = "dataList";
     	    var a = ['pageXh','hstoperationcol','pcdh','jszt','pcrq','cyrClhm','cyrSjxm','cyrSjsjhm'];
      		
            Merger(gridName, 'pcDjxh', a);
     }
     /**************************************分页结束****************************************/

</script>
</head>
<body>
<s:form action="hypchwqs!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.ljbz" />
	<s:hidden name="domain.xtcs20016" />
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
		   <table width="99%" border="0" cellspacing="0" cellpadding="0">
	          	<tr>
	          		<td width="5%" align="right">状态：</td>   
		          <td width="15%">
		         		<s:radio name="domain.sfJs" list='#{"":"所有","1":"未接收","2":"已接收"}' listKey="key" listValue="value"></s:radio>
		          </td>
		          <td width="5%" align="right">派车日期：</td>
		          <td width="35%">
		          <sys:dateFirstDLastMonthTag myName="domain.pcrqq" myId="mainForm_domain_pcrqq" myClass="ymdate" />
	          			～
	          		<sys:dateCurrentDayTag myName="domain.pcrqz" myId="mainForm_domain_pcrqz" myClass="ymdate" />
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
