<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>货运-托运单-未发货(提货)信息</title>
<style type="text/css">
html,body {overflow:hidden;}
.hiddenCss {display: none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		initHykhData(300);
		initXzqhData(200);
		initXzqhInputSel();
		
		$("#pcBtn").click(function(){
			var wfhXhs = getCheckedWfhXhs();
			saveWfhxx4Pc(wfhXhs)
		});
		
		$("#closeBtn").click(function() {
			window.close();
		});
		
		$("#mainForm_domain_djJgbm4Query").change(function(){
			initRy();
		});
		
		//查询按钮事件
		$("#queryBtn").click(function(){
			onRefresh();
		});
		
		var rem=new Array();
		$(":checkbox[name='wfhXhs']").click(function(){
			alert(0);
		 	var arr = $(":checkbox[name='wfhXhs']");
         	rem.push($(":checkbox[name='wfhXhs']").index($(this)));
         	if(e.shiftKey){
            	if(arr[$(":checkbox[name='wfhXhs']").index($(this))].checked == true){
                	if(rem.length>=2){
                     	var iMin =  Math.min(rem[rem.length-2],rem[rem.length-1])
                     	var iMax =  Math.max(rem[rem.length-2],rem[rem.length-1])
                     	for(i=iMin;i<=iMax;i++){
                         	arr[i].checked = true;
                     	}
                 	}
             	}else{
                 	if(rem.length>=2){
	                     var iMin =  Math.min(rem[rem.length-2],rem[rem.length-1])
	                     var iMax =  Math.max(rem[rem.length-2],rem[rem.length-1])
	                     for(i=iMin;i<=iMax;i++){
	                         arr[i].checked = false;
	                     }
                 	}
             	}
         	}
     	});
		
		//初始化表格
		initDataGrid();
		initRy();	
		var sjJgbm = $("#mainForm_domain_dw4Query").val();
		bmInit(sjJgbm, '', "domain.djJgbm4Query", "mainForm_domain_djJgbm4Query", "Y", "Y");
	});
	
	function saveWfhxx4Pc(wfhXhs) {
		if (wfhXhs != null && wfhXhs.length > 0) {
			var url = jcontextPath+"/hygl/hypcxxgl!saveWfhxx4Pc";
			var jsonObj = {"domain.wfhXhs":wfhXhs};
			ajaxCommon(url,jsonObj,"saveWfhxx4PcSuc");
		}
	}
	
	function saveWfhxx4PcSuc(data) {
		var pchwLsxh = data.domain.pchwLsxh;
		var url = jcontextPath+"/hygl/hypcxxgl!initMx.action?domain.pchwLsxh="+pchwLsxh;
    	window.showModalDialog(url,window,"dialogHeight:580px;dialogWidth:895px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
    	onRefresh();
	}
	
	function initRy() {
		var sj = $("#mainForm_domain_djJgbm4Query").val();
		commonInit("BMYH", sj, '', "domain.djrCzyDjxh4Query", "mainForm_domain_djrCzyDjxh4Query", "Y", false);
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
	
	function onViewMx(ddDjxh) {
		var url = jcontextPath+"/jcgl/jctydgl!initMx.action?domain.ddDjxh="+ddDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    	//window.open(url);
	}
	
	function onUpdate(wfhDjxh){
		var url = jcontextPath+"/hygl/hytydwfhxx!doUpdate";
		var jsonObj = {"domain.wfhDjxh":wfhDjxh};
		ajaxCommon(url,jsonObj,"upSucc");
	}
	
	function upSucc(){
		showAlert("修改成功！");
		window.close();
		onRefresh();
	}
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		var lb4Query = $("input[name='domain.lb4Query']:checked").val();
		 dw4Query = $("#mainForm_domain_dw4Query").val();
		 fhrXzqhDm = $("#mainForm_domain_fhrXzqhDm").val();
		 shrXzqhDm = $("#mainForm_domain_shrXzqhDm").val();
		 ddbh4Query = $("#mainForm_domain_ddbh4Query").val();
		 fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		 khMc4Query = $("#mainForm_domain_fhrMc").val();
		 hwztDm4Query = $("#mainForm_domain_hwztDm4Query").val();
		 djJgbm4Query = $("#mainForm_domain_djJgbm4Query").val();
		 djrCzyDjxh4Query = $("#mainForm_domain_djrCzyDjxh4Query").val();
		 xdrqQ = $("#mainForm_domain_xdrqQ").val();
		 xdrqZ = $("#mainForm_domain_xdrqZ").val();
		 if(xdrqQ>xdrqZ){
			 showError("下单开始日期不能大于下单截止日期！");
			 return ;
		 }
		 fhrqQ = $("#mainForm_domain_fhrqQ").val();
		 fhrqZ = $("#mainForm_domain_fhrqZ").val();
		 if(fhrqQ>fhrqZ){
			 showError("要求开始日期不能大于要求截止日期！");
			 return ;
		 }
		//请求表格数据
		var url = jcontextPath+"/hygl/hytydwfhxx!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.dw4Query":encodeURI(dw4Query),"domain.fhrXzqhDm":encodeURI(fhrXzqhDm),"domain.shrXzqhDm":encodeURI(shrXzqhDm),"domain.ddbh4Query":ddbh4Query,
		 				"domain.khMc4Query":encodeURI(khMc4Query),"domain.hwztDm4Query":hwztDm4Query,"domain.lb4Query":encodeURI(lb4Query),"domain.djJgbm4Query":djJgbm4Query,
		 				"domain.djrCzyDjxh4Query":djrCzyDjxh4Query,"domain.xdrqQ":xdrqQ,"domain.xdrqZ":xdrqZ,"domain.fhrqQ":fhrqQ,"domain.fhrqZ":fhrqZ,"domain.fhrDjxh":fhrDjxh}
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
	    colNames:['序号','订单登记序号','货物顺序号','未发货登记序号', '订单编号','客户名称','操作','状态','始发地','目的地','货物名称','结算数量',
	    		'数量','重量','体积','包装','类别','发货地址','要求发货日期','收货人',
	    		'收货地址','要求到达日期','登记人','登记日期','登记部门','所属机构'],			 //name 列显示的名称
	     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
	    colModel :[ 
		  {name:'pageXh', index:'pageXh', sortable:false, width:'30', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'pageXh' + rowId + '\'';
		    }
		  },
		  {name:'ddDjxh', index:'ddDjxh', hidden:true,width:'60', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'ddDjxh' + rowId + '\'';
		    }
		  },
		  {name:'xh', index:'xh', width:'100', hidden:true, align:'center'}, 
		  {name:'wfhDjxh', index:'wfhDjxh', hidden:true, width:'100', align:'center'},
		  {name:'ddbh', index:'ddbh', width:'70', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'ddbh' + rowId + '\'';
		    }
		  },
		  {name:'fhrMc', index:'fhrMc', width:'150', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'fhrMc' + rowId + '\'';
		    }
		  },
		  {name:'hstoperationcol',index:'',width:'40',align:'center',sortable:false},
	      {name:'hwztMc', index:'hwztMc', width:'60', align:'center'}, 
	      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'40', align:'center'}, 
	
	      {name:'shrXzqhMc', index:'shrXzqhMc', width:'40', align:'center'}, 
	      {name:'hwmc', index:'hwmc', width:'100', align:'center'}, 
	      {name:'jssl', index:'jssl', width:'50', align:'right'},
	      {name:'sl', index:'sl', width:'50', align:'center'}, 
	      {name:'zl', index:'zl', width:'50', align:'center'}, 
	      {name:'tj', index:'tj', width:'50', align:'center'}, 
	      {name:'bz', index:'bz', width:'50', align:'center'}, 
	      {name:'lb', index:'lb', width:'40', align:'center'}, 
	      {name:'fhrDz', index:'fhrDz', width:'100', align:'center'}, 
	      {name:'fhRq', index:'fhRq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}, 
	      {name:'shrMc', index:'shrMc', width:'60', align:'center'}, 
	      {name:'shDz', index:'shDz', width:'100', align:'center'}, 
	      {name:'yqDdrq', index:'yqDdrq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}, 
	      {name:'djrMc', index:'djrMc', width:'70', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'djrMc' + rowId + '\'';
		    }
		  },
		  {name:'djRq', index:'djRq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'djRq' + rowId + '\'';
		    }
		  },
		  {name:'djJgmc', index:'djJgmc', width:'100', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'djJgmc' + rowId + '\'';
		    }
		  },
		  {name:'ssJgmc', index:'ssJgmc', width:'100', align:'center', 
			cellattr: function(rowId, tv, rawObject, cm, rdata) {
			   return 'id=\'ssJgmc' + rowId + '\'';
		    }
		  }
	    ],
	    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
	    rowNum: <%=rowNum %>,							//在grid上显示记录条数，这个参数是要被传递到后台
	    rowList:[10,20,50,100],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
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
	       	   $("#mainForm").attr("action",jcontextPath+"/hygl/hytydwfhxx!download");
			   $("#mainForm").submit();
	       } 
	 });
	 
	}
	
	//表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
	function myGridComplete() {
		 var graduateIds = $("#dataList").jqGrid('getDataIDs');
         for (var i = 0; i < graduateIds.length; i++) {
             var cl = graduateIds[i];
             
             var pageXh = jQuery("#dataList").jqGrid('getCell', cl,"pageXh");
             if(pageXh=='0'){
            	 pageXh='合计';
            	 $("#dataList").jqGrid('setRowData', cl, { 'pageXh': pageXh });
            	 continue;
             }
             var ddbh = jQuery("#dataList").jqGrid('getCell', cl,"ddbh");
             var wfhDjxh = jQuery("#dataList").jqGrid('getCell', cl,"wfhDjxh");
             var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh"); 
             var hwztMc = jQuery("#dataList").jqGrid('getCell', cl,"hwztMc");
             var str="<a href=\"javascript:onViewMx("+ddDjxh+")\"><font color=\"blue\">"+ddbh+"</font></a>";
             var opeStr = "";
             if(hwztMc == "未提"){
            	 opeStr = "<a href=\"javascript:onUpdate("+wfhDjxh+")\"><font color=\"blue\">改运输</font></a>";
             }else if(hwztMc == "未发"){
            	 opeStr = "<a href=\"javascript:onUpdate("+wfhDjxh+")\"><font color=\"blue\">改提货</font></a>";
             }
             $("#dataList").jqGrid('setRowData', cl, { 'ddbh': str });
             $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': opeStr });
         }
	    
	   var gridName = "dataList";
		   var a = ['pageXh','ddDjxh','ddbh','fhrMc','djRq','djrMc','djJgmc','ssJgmc'];
			
	    Merger(gridName, 'ddDjxh', a);
	}
/**************************************分页结束****************************************/
</script>
</head>
<body>
<s:form action="hytydwfhxx!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.pcOpenFlag"></s:hidden>
	<s:hidden name="jsonData" />
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
	<div class="right_cont" id="maincont">
	 <div id="divQuery">
		<fieldset>
			<legend>查询条件</legend>
		   <table width="99%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		          <td width="10%" align="right">业务单位：</td>
		          <td width="21%">
		          	<sys:gsList myName="domain.dw4Query" myId="mainForm_domain_dw4Query" onChange="bmInit(this.value, '', 'domain.djJgbm4Query', 'mainForm_domain_djJgbm4Query', 'Y', 'Y')" mcContainDmBz="Y" contaisQxz="false" myClass="select" />
		          </td>
		          <td width="10%" align="right">登记部门：</td>
		          <td width="21%">
		          		<select id="mainForm_domain_djJgbm4Query" name="domain.djJgbm4Query" class="select" >
          					<option value="${domain.djJgbm4Query }" selected="selected"></option>
          				</select>
		          </td>
		          <td width="10%" align="right">下单日期：</td>
		          <td width="27%">
		            <sys:dateFirstDLastMonthTag myName="domain.xdrqQ" myId="mainForm_domain_xdrqQ" myClass="ymdate" />
	          			～
	          		<sys:dateCurrentDayTag myName="domain.xdrqZ" myId="mainForm_domain_xdrqZ" myClass="ymdate" />
	          	  </td>
		        </tr>
		        <tr>
		          <td align="right">客户名称：</td>
		          <td>
		          	<s:hidden name="domain.fhrDjxh"></s:hidden>
  					<div class="inputsel" style="width: 240px; ">
  						<s:textfield name="domain.fhrMc" cssClass="pop_input noborder inputext" cssStyle="width:210px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="fhr" onFocus="this.blur()"></a>
  					</div>
			  		<div class="inputsc">
		              <div id="inputSel_fhr" class="inputselcont inputselFixedSize ac_results">
		              </div>
		            </div>
		          </td>
	        	  <td align="right">订单编号：</td>
		          <td><s:textfield name="domain.ddbh4Query" cssClass="pop_input noborder" /></td>
		           <td align="right">要求日期：</td>
		          <td><s:textfield name="domain.fhrqQ" cssClass="ymdate" />
	          			～
	          		<s:textfield name="domain.fhrqZ" cssClass="ymdate" /></td>
		        </tr>
		        <tr>
		          <td align="right">货物类别：</td>
		          <td>
		          	<s:radio name="domain.lb4Query" list='#{"":"所有","1":"重货","2":"泡货"}' listKey="key" listValue="value"></s:radio>
		          </td>
	        	  <td align="right">始发地：</td>
		          <td colspan="3">
			          <div style="float: left;">
			          	<div style="float: left;"></div>
			          	<s:hidden name="domain.fhrXzqhDm" />
	  					<div class="inputsel" style="width: 100px;">
	  						<s:textfield name="domain.fhrXzqhMc" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width: 70px;"></s:textfield>
	  						<a href="#" class="icon_arrow" id="xzqh" xzqh="fhrXzqh" onFocus="this.blur()"></a>
			            </div>
			           </div>
		            
		            <div style="float: left;">
		            	<div style="float: left;">&nbsp;&nbsp;&nbsp;目的地：</div>
		            	<s:hidden name="domain.shrXzqhDm"></s:hidden>
  						<div class="inputsel" style="width: 100px;">
	  						<s:textfield name="domain.shrXzqhMc" cssClass="inputext pop_input noborder bgstyle_optional" cssStyle="width: 70px;"></s:textfield>
	  						<a href="#" class="icon_arrow" id="xzqh" xzqh="shrXzqh" onFocus="this.blur()"></a>
		            	</div>
		            </div>
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
	<div id="inputSel_xzqh" class="inputselcont" style="position: absolute; top: 159px; left: 94px; display: none;" >
		                
      <iframe src="" scrolling="no" frameborder="0" style="position:absolute; top:0; left:0; z-index:-1; width:274px; height:100px;"></iframe>
    </div>
</s:form>
</body>
</html>