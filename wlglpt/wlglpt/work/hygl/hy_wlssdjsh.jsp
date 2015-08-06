<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>货运-派车-回单</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>


<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
			
		
		
			//查询按钮事件
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//批量通过按钮事件
			$("#pltgBtn").click(function(){
				plJudge();
			});
			//批量退回按钮事件
			$("#plthBtn").click(function(){
				plBack();
			});
					
			
			var sjJgbm = $("#mainForm_domain_dwDm").val();
			bmInit(sjJgbm, '', "domain.pcbm4Query", "mainForm_domain_pcbm4Query", "Y", "Y");
			//初始化表格
			initDataGrid();
			onDisplay();
			

	});

	function checkNum() {
		var ch = $(":checkbox[name='dj']");
		var num = 0;
		for(var i=0;i<ch.length;i++){
			if(ch[i].checked){
				++num; 
			}
		}
		if(num < 1){
			return false;
		}
		return true;
	}
	
	var pc='';
    function onUpdate(pcDjxh){
    	pc=pcDjxh;
    	var ul=jcontextPath+"/hygl/wlssdj!checkWlDj";
    	var json={"domain.pcDjxh":pcDjxh};
    	ajaxCommon(ul,json,"toUpdate");
    }
    function toUpdate(data){
    	if(data.domain.tager=='N'){
    		var url = jcontextPath+"/hygl/wlssdj!initMx.action?domain.pcDjxh="+pc;
        	window.showModalDialog(url,window,"dialogHeight:405px;dialogWidth:675px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
        	onRefresh();
    	}
    	else{
    		showConfirm("该派车单已登记物流损失,您可以通过物流损失登记登记管理进行修改,也可以点击确定新增物流损失登记","okUpdate");
    	} 
    
    }
    function okUpdate(){
    	var url = jcontextPath+"/hygl/wlssdj!initMx.action?domain.pcDjxh="+pc;
    	window.showModalDialog(url,window,"dialogHeight:405px;dialogWidth:675px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
    	onRefresh();
    }
 
    
    function onWlSsSh(wsspxh,spxh){  
        var url = jcontextPath+"/common/wsspCommon!init.action?domain.wsspxh="+wsspxh+"&domain.spxh="+spxh;
    	window.showModalDialog(url,window,"dialogHeight:900px;dialogWidth:920px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
    	//window.open(url);
    	//onRefresh();
} 
    
    function checkBoxAll(obj){
    	$(":checkbox[name='checkTel']").attr("checked",obj.checked);
    }
	
    function onDisplay(){
		var shbz = $("input[name='domain.shbz']:checked").val();
		if("Y"==shbz){
			$("#shrqTdText").show();
			$("#shrqTdId").show();
		}else{
			$("#shrqTdText").hide();
			$("#shrqTdId").hide();
		}
	}
    function onViewPc(pcDjxh){
    	var url = jcontextPath+"/jcgl/jcpcxxgl!initMx.action?domain.pcDjxh="+pcDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    }
    
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		var radio=$(":radio[name='domain.shbz']")[0].checked?"N":"Y";
		//alert(radio);
		var rqQ = trim($("#mainForm_domain_rqQ").val()); 
		var rqZ = trim($("#mainForm_domain_rqZ").val()); 
		//请求表格数据
		var url = jcontextPath+"/hygl/wlssdjsh!query.action";   
	
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.rqQ":rqQ,"domain.rqZ":rqZ,"domain.shbz":radio}								//请求的参数，json格式
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
		    colNames:['序号','<input title="选中/取消选中" type="checkbox"  onclick="checkAllJGridChk(event,this,\'xhs\');" />','操作','标志','发送人','发送日期',
		             '审批人','审批日期','审批结果','费用合计','派车单号','派车日期'
		              ,'车辆号码','挂车号码','司机姓名','总运费','派车人名称','派车部门','所属机构','','','转入部门','始发地','目的地','货物名称','包装','数量'
		              ,'重量','体积','结算数量','发货人名称','发货人地址','要求发货日期','收货人名称','收货人地址','要求到达日期'],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[

		        {name:'xh', index:'xh', sortable:false, width:'35', align:'center', 
		       			cellattr: function(rowId, tv, rawObject, cm, rdata) {
		       			   return 'id=\'xh' + rowId + '\'';
		       		    }
		       	},        
		        {name:'checkbox', index:'checkbox', width:'35', sortable:false, align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'checkbox' + rowId + '\'';
				    }
				  },	
			    {name:'hstoperationcol', index:'hstoperationcol', width:'35', sortable:false, align:'center', 
						cellattr: function(rowId, tv, rawObject, cm, rdata) {
						   return 'id=\'hstoperationcol' + rowId + '\'';
					    }
					  },
				{name:'fsthbz', index:'fsthbz', width:'35', sortable:false, align:'center', 
							cellattr: function(rowId, tv, rawObject, cm, rdata) {
							   return 'id=\'fsthbz' + rowId + '\'';
						    }
				 },
			
			   {name:'fsrmc', index:'fsrmc', width:'80', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'fsrmc' + rowId + '\'';
				    }
			   },
			   {name:'fsrq', index:'fsrq', width:'70', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'fsrq' + rowId + '\'';
				    }
			   },
			 
			   {name:'sprmc', index:'sprmc', width:'80', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'sprmc' + rowId + '\'';
				    }
			   },
			   {name:'sprq', index:'sprq', width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}, 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'sprq' + rowId + '\'';
				    }
			   },
			   {name:'spjg', index:'spjg', width:'80', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'spjg' + rowId + '\'';
				    }
			   },
			   {name:'fyhj', index:'fyhj', width:'80', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'fyhj' + rowId + '\'';
				    }
			   },
			   {name:'pcdh', index:'pcdh', width:'70', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcdh' + rowId + '\'';
				    }
			   },
			   {name:'pcrq', index:'pcrq', width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcrq' + rowId + '\'';
				    }
			   },
			 
			   {name:'clhm', index:'clhm', width:'60', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'clhm' + rowId + '\'';
				    }
			   },
			   {name:'gchm', index:'gchm', width:'60', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'gchm' + rowId + '\'';
				    }
			   },
			   {name:'sjxm', index:'sjxm', width:'60', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'sjxm' + rowId + '\'';
				    }
			   },
			   {name:'yfhj', index:'yfhj', width:'50', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'yfhj' + rowId + '\'';
				    }
			   },
			   {name:'pcrmc', index:'pcrmc', width:'70', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcrmc' + rowId + '\'';
				    }
			   },
			   {name:'pcbmmc', index:'pcbmmc', width:'100', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcbmmc' + rowId + '\'';
				    }
			   },
			   {name:'ssjgmc', index:'ssjgmc', width:'100', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'ssjgmc' + rowId + '\'';
				    }
			   },
			   {name:'wsspxh', index:'wsspxh', sortable:false,hidden:true, width:'0', align:'center'},
			   {name:'spxh', index:'spxh', sortable:false,hidden:true, width:'0', align:'center'},
			   {name:'zrbmmc', index:'zrbmmc', sortable:false, width:'100', align:'center'},
			   {name:'sfd', index:'sfd', sortable:false, width:'60', align:'center'},
			   {name:'mdd', index:'mdd', sortable:false, width:'60', align:'center'},
			   {name:'hwmc', index:'hwmc', sortable:false, width:'100', align:'center'},
			   {name:'bz', index:'bz', sortable:false, width:'80', align:'center'},
			   {name:'sl', index:'sl', sortable:false, width:'60', align:'center'},
			   {name:'zl', index:'zl', sortable:false, width:'60', align:'center'},
			   {name:'tj', index:'tj', sortable:false, width:'60', align:'center'},
			   {name:'jssl', index:'jssl', sortable:false, width:'100', align:'right'},
			   {name:'fhrmc', index:'fhrmc', sortable:false, width:'100', align:'center'},
			   {name:'fhrdz', index:'fhrdz', sortable:false, width:'100', align:'center'},
			   {name:'yqfhrq', index:'yqfhrq', sortable:false, width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
			   {name:'shrmc', index:'shrmc', sortable:false, width:'100', align:'center'},
			   {name:'shrdz', index:'shrdz', sortable:false, width:'120', align:'center'},
			   {name:'yqddrq', index:'yqddrq', sortable:false, width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}
		  
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: -1,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:-1,		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'PCDH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/hypchd!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
	function myGridComplete() {
    		var radio=$(":radio[name='domain.shbz']")[0].checked?'N':'Y';
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var pcdh = jQuery("#dataList").jqGrid('getCell', cl,"pcdh"); 
                var xh = jQuery("#dataList").jqGrid('getCell', cl,"xh"); 
                var fsthbz = jQuery("#dataList").jqGrid('getCell', cl,"fsthbz");
                var wsspxh = jQuery("#dataList").jqGrid('getCell', cl,"wsspxh"); 
                var jdxh = jQuery("#dataList").jqGrid('getCell', cl,"jdxh");
                var spxh = jQuery("#dataList").jqGrid('getCell', cl,"spxh"); //获取当前单元格里面的登记序号 
                var check="<input type=\"checkbox\" name=\"xhs\" value=\""+wsspxh+"#"+spxh+"#"+jdxh+"\"/>";
                var strPc="<a href=\"javascript:onViewPc("+1+")\"><font color=\"blue\">"+pcdh+"</font></a>";
                var input = "";
               if(radio=='N'){
            	   input = "<input type=\"checkbox\" name=\"xhs\" value=\""+wsspxh+"#"+spxh+"#"+jdxh+"\" />";
			       $("#dataList").jqGrid('setRowData', cl, { 'checkbox': input }); 
			        
            	   var link = "<a href=\"javascript:doWsSh('"+wsspxh+"','"+spxh+"')\"><font color=\"blue\">审核</font></a>";
               }
               else{
	               input = "<input type=\"checkbox\" name=\"xhs\" disabled=\"disabled\" value=\""+wsspxh+"#"+spxh+"#"+jdxh+"\" />";
		           $("#dataList").jqGrid('setRowData', cl, { 'checkbox': input });
		           
            	   var link = "<a href=\"javascript:doWsSh('"+wsspxh+"','"+spxh+"')\"><font color=\"blue\">查看</font></a>";
               }
              if(fsthbz=='1'){
            	  $("#dataList").jqGrid('setRowData', cl, { 'fsthbz': "发送" }); 
              }
              else{
            	  $("#dataList").jqGrid('setRowData', cl, { 'fsthbz': "退回" }); 
              }
              $("#dataList").jqGrid('setRowData', cl, { 'pcdh': strPc });
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link });
                $("#dataList").jqGrid('setRowData', cl, { 'xuhao': i+1 });  

            }
            var gridName = "dataList";
	   		var a = ['checkbox','hstoperationcol','fsthbz','xh','fsrmc','fsrq','sprmc','sprq','spjg','fyhj','pcdh','pcrq','pcfsmc','zcfsmc','clhm','gchm','sjxm','yfhj','pcrmc','pcbmmc','ssjgmc'];
 		
       		Merger(gridName, 'xh', a);
     }
     /**************************************分页结束****************************************/

</script>
</head>
<body>
<s:form action="hypchd!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	
	<s:hidden name="fhrData" />
	
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">操作：</li>
		    <li><a href="#" id="queryBtn" class="licon04">检 索</a></li>
		     <li><a href="#" id="pltgBtn" class="licon08">批量审核通过</a></li>
		    <li><a href="#" id="plthBtn" class="licon09">批量退回审批</a></li>
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
		   <table width="95%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
			          <td width="20%" align="left">
			   				&nbsp;&nbsp;&nbsp;&nbsp;
			          	<s:radio name="domain.shbz" list='#{"N":"未审核","Y":"已审核"}' listKey="key" listValue="value" onclick="javascript:onDisplay();"></s:radio>
			          </td>
			          <td align="right" id="shrqTdText">审核日期：</td>
			          <td id="shrqTdId">
			          		<s:textfield name="domain.rqQ" readonly="true" cssClass="ymdate"></s:textfield>
							 ～ 
							<s:textfield name="domain.rqZ" readonly="true" cssClass="ymdate"></s:textfield>
			          </td>
			  </tr>
		   </table>
		 
		 
		</fieldset>
	  </div>
	  </div>
		<!-- 分页表格 id必须为dataList -->
		<table id="dataList"><tr><td/></tr></table> 
		<!-- 分页导航 -->
	
		
		
		<%@include file="/common/message.jsp" %>
</s:form>
</body>
</html>
