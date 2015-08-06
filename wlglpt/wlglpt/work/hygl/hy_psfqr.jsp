<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>货运-派车信息管理</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript">
	$(document).ready(function(){
			init();
			//查询按钮事件
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			$("#plScSendBtn").click(function(){				
				var xhs = $(":checked:enabled[name='xhs']");
			  	if (xhs.length <= 0) {
					showAlert("请选择需要批量确认的记录！");
					return;
			  	}
			  	var plXhs = new Array();
			  	$.each(xhs,function(i,obj){			  		
			  		plXhs[i] = xhs[i].value;
			  	});
			  	
			  	var jsonStr = getJqueryParamZdy(plXhs,"domain.plqrXhs");
			  	var url = jcontextPath+"/hygl/jspsfqr!plqr"; 
			    ajaxCommon(url,encodeURI(jsonStr),"doPlScSendSuc",false);
			});
			
			 $("#psfBtn").click(function(){
			 	var pcrqq = $("#mainForm_domain_pcrqq").val();
				var pcrqz = $("#mainForm_domain_pcrqz").val(); 
				if (pcrqq == "" || pcrqz == "") {
					showAlert("派车日期不能为空！");
					return;
				}
				var pcdh = $("#mainForm_domain_pcdh").val();
				var url = jcontextPath+"/hygl/jspsfqr!dyyl?domain.pcrqq="+pcrqq+"&domain.pcrqz="+pcrqz+"&domain.pcdh="+pcdh;
				window.open(url,"_blank")
					
			 });
			//初始化表格
			initDataGrid();
	});
	function getJqueryParamZdy(obj, paraName) {
		var data = "";
		$.each(obj, function(i){
			data += paraName + "[" + i + "]=" + encodeURI(obj[i]) + "&";
		});
		
		return data;
	}
	
	function doPlScSendSuc(data) {
		hideMessage();
		showAlert("批量确认成功！");
		onRefresh();
	}
	
    function onUpdate(psfDjxh){
    	if(psfDjxh == '' || psfDjxh == null || psfDjxh == undefined){
    		showAlert("无配送费可确认！");
    		return;
    	}
    	var url = jcontextPath+"/hygl/jspsfqr!initMx?domain.psfDjxh="+psfDjxh;    	
    	window.showModalDialog(url,window,"dialogHeight:350px;dialogWidth:500px;center:yes;resizable:no;status:no;scroll:no;help:no;maximize:yes;minimize:yes;")
    	onRefresh();
    }
	
    function onView(psfDjxh){
    	var url = jcontextPath+"/hygl/jspsfqr!viewMx?domain.psfDjxh="+psfDjxh;
    	window.showModalDialog(url,window,"dialogHeight:350px;dialogWidth:500px;center:yes;resizable:no;status:no;scroll:no;help:no;maximize:yes;minimize:yes;")
    	onRefresh();
    }
    
    var parPsf = '';
    function onBack(psfDjxh){
    	parPsf = psfDjxh;    	
    	showConfirm("确定要退回吗？","doBack");
    }
    
    function doBack(){
    	var jsonObj = {"domain.psfDjxh":parPsf};
    	var url = jcontextPath+"/hygl/jspsfqr!delete";   
        ajaxCommon(url,jsonObj , "doSuccess");  
    }
    
    function doSuccess(){     
        showAlert("退回成功！");
        onRefresh();
	}	
    
    function init() {
    	var xtcs20030 = $("#mainForm_domain_xtcs20030").val();    	
    	if(xtcs20030 == 'N'){
    		$("#plScSendBtn").hide();    		
    	}
    }
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		pcrqq = $("#mainForm_domain_pcrqq").val();
		pcrqz = $("#mainForm_domain_pcrqz").val(); 
		if (pcrqq == "" || pcrqz == "") {
			showAlert("派车日期不能为空！");
			return;
		}
		pcdh = $("#mainForm_domain_pcdh").val();
		
		
		//请求表格数据
		var url = jcontextPath+"/hygl/jspsfqr!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.pcrqq":pcrqq,"domain.pcrqz":pcrqz, "domain.pcdh":encodeURI(pcdh)
		 			}								//请求的参数，json格式
		 }
		 ).trigger("reloadGrid");		//显示到第一页，以免总体数据发生变化的时候，分页的信息混乱
	}
	
	//托运单信息查看
	function onViewMx(ddDjxh) {
		var url = jcontextPath+"/jcgl/jctydgl!initMx.action?domain.ddDjxh="+ddDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    	//window.open(url);
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
		    colNames:['序号','派车单号','','','','','', '派车日期','订单编号','客户名称','ddDjxh', '货物名称','收货人','收货地址',
		              '下游单位','数量', '重量', '体积','车辆信息','运费/预付', '收入/到付','重量','体积','送货方式','','配送费','是否确认','配送费确认',
		              '<input title="选中/取消选中" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />','派车人', '派车部门', '所属机构'],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[
			  {name:'pageXh', index:'pageXh', sortable:false, width:'30', align:'center',
				  cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pageXh' + rowId + '\'';
				    }  
			  },
			  {name:'pcdh', index:'pcdh', width:'70', align:'center',
				  cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcdh' + rowId + '\'';
				    }
			  },
			  {name:'ssJgbm', index:'ssJgbm', width:'70', align:'center',hidden:true},
			  {name:'wfhDjxh', index:'wfhDjxh', width:'70', align:'center',hidden:true},
			  {name:'pcDjxh', index:'pcDjxh', width:'70', align:'center',hidden:true},
			  {name:'zrbmDjxh', index:'zrbmDjxh', width:'70', align:'center',hidden:true},
			  {name:'pchwLsxh', index:'pchwLsxh', width:'70', align:'center',hidden:true},
			  {name:'pcrq', index:'pcrq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
				  cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcrq' + rowId + '\'';
				    }  
			  },
			  {name:'dingdan',index:'dingdan', width:'80', align:'center'},
			  {name:'fhrMc', index:'fhrMc', width:'120', align:'center'},
			  {name:'ddDjxh', index:'ddDjxh',hidden:true, width:'120', align:'center'},
			  {name:'hwMc', index:'hwMc', width:'100', align:'center'},
			  {name:'shrMc', index:'shrMc', width:'100', align:'center'},
		      {name:'mddMc', index:'mddMc', width:'200', align:'center'},
		      {name:'zrbmMc', index:'zrbmMc', width:'80', align:'center'},
		      {name:'sl', index:'sl', width:'50', align:'right'},  
		      {name:'zl', index:'zl', width:'50', align:'right'},
		      {name:'tj', index:'tj', width:'50', align:'right'},
		      {name:'clxx', index:'clxx', width:'200', align:'left'},
			  
		      {name:'yfhjyf', index:'yfhjyf', width:'60', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'yfhjyf' + rowId + '\'';
				    }
				  },
			  {name:'srhjdf', index:'srhjdf', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'srhjdf' + rowId + '\'';
			    }
			  },
			  {name:'zcZl', index:'zcZl', width:'40', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'zcZl' + rowId + '\'';
				    }
			  },
			  {name:'zcTj', index:'zcTj', width:'40', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'zcTj' + rowId + '\'';
				    }
			  },
			  {name:'zs', index:'zs', width:'60', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'zs' + rowId + '\'';
				    }
			  },
			  {name:'psfDjxh', index:'psfDjxh', width:'60', align:'center',hidden:true},
			  {name:'srPsf', index:'srPsf', width:'60', align:'center'},
			  {name:'sfQr', index:'sfQr', width:'60', align:'center'},
			  {name:'hstoperationcol', index:'', width:'80',sortable:false,align:'center'},
			  {name:'fsspCheck',index:'fsspCheck',align:'center',width:'20'},
			  {name:'pcrMc', index:'pcrMc', width:'60', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcrMc' + rowId + '\'';
				    }
				  },
				 
			  {name:'pcJgbmMc', index:'pcJgbmMc', width:'100', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcJgbmMc' + rowId + '\'';
			    }
			  },
			  {name:'ssJgbmMc', index:'ssJgbmMc', width:'100', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'ssJgbmMc' + rowId + '\'';
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/jspsfqr!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var pcDjxh = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh"); 
                var ssJgbm = jQuery("#dataList").jqGrid('getCell', cl,"ssJgbm"); 
                var wfhDjxh = jQuery("#dataList").jqGrid('getCell', cl,"wfhDjxh"); 
                var zrbmDjxh = jQuery("#dataList").jqGrid('getCell', cl,"zrbmDjxh");
                var xh = jQuery("#dataList").jqGrid('getCell', cl,"pchwLsxh");
                var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh");
                var srPsf = jQuery("#dataList").jqGrid('getCell', cl,"srPsf");
                var hwMc = jQuery("#dataList").jqGrid('getCell', cl,"hwMc");
                var zs = jQuery("#dataList").jqGrid('getCell', cl,"zs");
                var psfDjxh = jQuery("#dataList").jqGrid('getCell', cl,"psfDjxh");
                
                var spLink = '<input type="checkbox" name="xhs" value="'+psfDjxh+'" />';
                var link = "";
                var linkT = "";
                var qrbz = jQuery("#dataList").jqGrid('getCell', cl,"sfQr");
                if(qrbz == 'Y'){
					if(zs == '自提'){
						link = "";
						linkT = "";
						spLink = "";
                	}else{
                		link += "<a href=\"javascript:onView('"+psfDjxh+"')\"><font color=\"blue\">查看</font></a>" +
                				" <a href=\"javascript:onBack('"+psfDjxh+"')\"><font color=\"blue\">退回</font></a>";
                		linkT = "已确认"; 
                		spLink = '<input type="checkbox" name="xhs" value="'+psfDjxh+'" disabled="disabled" />';
                	}
                }else{
                	if(zs == '自提'){
						link = "";
						linkT = "";
						spLink = "";
                	}else{
                		link += "<a href=\"javascript:onUpdate('"+psfDjxh+"')\"><font color=\"blue\">确认</font></a>";
                		linkT = "<font color=\"red\">未确认</font>";
                	}
                }
                if(psfDjxh == '' || psfDjxh == null || psfDjxh == undefined){
                	spLink = '<input type="checkbox" name="xhs" value="'+psfDjxh+'" disabled="disabled" />';
            	}
                
                //添加托运单信息查看功能
                var dingdan = jQuery("#dataList").jqGrid('getCell', cl,"dingdan");
                var str="<a href=\"javascript:onViewMx("+ddDjxh+")\"><font color=\"blue\">"+dingdan+"</font></a>";
                var xtcs20030 = $("#mainForm_domain_xtcs20030").val();    	                
            	if(xtcs20030 == 'N'){            		
            		jQuery("#dataList").setGridParam().hideCol("sfQr");
            		jQuery("#dataList").setGridParam().hideCol("fsspCheck");
            		link = "<a href=\"javascript:onView('"+psfDjxh+"')\"><font color=\"blue\">查看</font></a>";
            	}
                $("#dataList").jqGrid('setRowData', cl, { 'dingdan': str });
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
                $("#dataList").jqGrid('setRowData', cl, { 'sfQr': linkT }); 
                $("#dataList").jqGrid('setRowData', cl, { 'fsspCheck': spLink }); 
            }
            var gridName = "dataList";
     	    var a = ['pageXh','pcdh','pcrq','yfhjyf','srhjdf','zcZl','zcTj','zs','pcrMc','pcJgbmMc','ssJgbmMc'];
      		
            Merger(gridName, 'pcDjxh', a);
     }
     
    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接

       
   	
     /**************************************分页结束****************************************/
</script>
</head>
<body>
<s:form action="jspsfqr!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">	
	<s:hidden name="jsonData" />
	<s:hidden name="domain.xtcs20030" />
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">操作：</li>
		    <li><a href="#" id="queryBtn" class="licon04">检 索</a></li>
		    <li><a href="#" id="plScSendBtn" class="licon10">批量确认</a></li>
		    <li><a href="#" id="psfBtn" class="licon10">配送费账单</a></li>
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
		        	<td width="8%" align="right">派车单号：</td>
          			<td width="15%">
          				<s:textfield name="domain.pcdh" cssClass="pop_input noborder" />
		  			</td>
		  			<td width="8%" align="right">派车日期：</td>
          			<td width="21%">
          				<sys:dateCurrentDayTag myName="domain.pcrqq" myId="mainForm_domain_pcrqq" myClass="ymdate" />
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
