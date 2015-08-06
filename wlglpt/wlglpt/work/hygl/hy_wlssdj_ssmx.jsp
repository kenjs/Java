<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>物流损失</title>
<style type="text/css">
.hiddenCss {display: none;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		})
		
		$("#addBtn").click(function(){
			var pcDjxh = $("#mainForm_domain_pcDjxh").val();
			var wfhDjxh =$("#mainForm_domain_wfhDjxh").val();
			var ddDjxh = $("#mainForm_domain_ddDjxh").val();
			var hwmxxh = $("#mainForm_domain_hwmxxh").val();
            var khDjxh = $("#mainForm_domain_khDjxh").val();
			onUpdate(pcDjxh,"",wfhDjxh,ddDjxh,hwmxxh,khDjxh);
		})
		//初始化表格
		initDataGrid();
		
		onRefresh();
		
	 })
	 function onUpdate(pcDjxh,wlDjxh,wfhDjxh,ddDjxh,hwmxxh,khDjxh){
    	//alert(pcdjxh+"--"+ddDjxh+"--"+hwmxxh+"--"+wfhDjxh)
    	var url = jcontextPath+"/hygl/wlssdj!initMx.action?domain.pcDjxh="+pcDjxh+"&domain.wlssDjxh="+wlDjxh+"&domain.wfhDjxh="+wfhDjxh
    			+"&domain.ddDjxh="+ddDjxh+"&domain.xh="+hwmxxh+"&domain.wlssLybz=0"+"&number="+Math.random();
    	window.showModalDialog(url,window,"dialogHeight:600px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
    	onRefresh();
    }  
	function onUpload(pcDjxh,wlDjxh,wfhDjxh,ddDjxh,hwmxxh,khDjxh){
	    	//alert(pcdjxh+"--"+ddDjxh+"--"+hwmxxh+"--"+wfhDjxh)
	    	var url = jcontextPath+"/hygl/wlssdjzp!initMx.action?domain.pcDjxh="+pcDjxh+"&domain.wlssDjxh="+wlDjxh+"&domain.wfhDjxh="+wfhDjxh
	    			+"&domain.ddDjxh="+ddDjxh+"&domain.xh="+hwmxxh+"&domain.wlssLybz=0"+"&number="+Math.random();
	    	window.showModalDialog(url,window,"dialogHeight:600px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
	    	onRefresh();
	}  
	function onSend(wlssDjxh,oldWsspxh){
		var wsDm="303002";//物流损失登记审批表
		//alert(wlssDjxh+"-"+oldWsspxh);
		scSend(wsDm,"",wlssDjxh,oldWsspxh);
	}
	//重写 发送公共方法的回调  去掉了close 换成onRefresh
	function doScSendSuc(data) {
		hideMessage();
		showAlert("发送成功！");
		onRefresh();
	}
	
	var dd='';
    function onDelete(djxh){
    	dd=djxh;
    	showConfirm("确定要删除么？","toDelete");
    }
    function toDelete()
    {
    	var url=jcontextPath+"/hygl/wlssdj!delete";
    	json={"domain.wlssDjxh":dd};
    	ajaxCommon(url,json,"deletePhoto");
    }
    function okDelete(){
    	showAlert("删除成功！");
    	onRefresh();
    }
    function deletePhoto(){
    	var url=jcontextPath+"/hygl/wlssdjzp!deletePhotoes";
    	json={"domain.wlssDjxh":dd};
    	ajaxCommon(url,json,"okDelete");
    }
  /**************************************分页开始****************************************/
  //刷新表格
	function onRefresh(){
		var xtcsSfsp = $("#mainForm_domain_xtcsSfsp").val();
		if ("Y" == xtcsSfsp) {
			$("#dataList").jqGrid('showCol',["wsspztMc"]);
		}
		var pcDjxh = $("#mainForm_domain_pcDjxh").val();
		var wfhDjxh = $("#mainForm_domain_wfhDjxh").val();
  		//请求表格数据
		var url = jcontextPath+"/wlssdj!querySsMx.action";   
		 $("#dataList").jqGrid("setGridParam",{ 
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.pcDjxh":pcDjxh,"domain.wfhDjxh":wfhDjxh}							//请求的参数，json格式
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
			width:pageWidth()-20,  
			height:pageHeight()-90,	
		    gridComplete: myGridComplete,		//表格加载完毕事件
		    shrinkToFit:false, 
		    colNames:['序号','操作','物流损失登记序号','文书审批序号','文书审批状态代码','审核','损失金额','金额',
		    		  '损失原因','处理方式','责任人','损失来源','损失数量','损失重量',
		    		  '损失体积','备注','登记单位','修改人','修改时间',
		    		  '创建人','创建时间'],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[ 
			  {name:'pageXh', index:'pageXh', width:'35', sortable:false,align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pageXh' + rowId + '\'';
				    }
				},
			   {name:'hstoperationcol', index:'',width:'120', sortable:false,align:'center',
			   		cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'hstoperationcol' + rowId + '\'';
				    }
			   },
			   {name:'wlssDjxh', index:'wlssDjxh', width:'80', align:'center', hidden:true,
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'wlssDjxh' + rowId + '\'';
			    }
			  },
			   {name:'wsSpxh', index:'wsSpxh', width:'80', align:'center', hidden:true,
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'wsSpxh' + rowId + '\'';
			    }
			  },
			  {name:'wsspztDm', index:'wsspztDm', width:'80', align:'center', hidden:true,
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'wsspztDm' + rowId + '\'';
			    }
			  },			  
		      {name:'wsspztMc', index:'wsspztMc', width:'60',align:'center',
		      	cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'wsspztMc' + rowId + '\'';
			    }
		      },
			  {name:'zje', index:'zje', width:'60', sortable:false,align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'zje' + rowId + '\'';
				    }
			 },
			  
		      {name:'je', index:'je', width:'40', align:'center'},
		      {name:'wlssyy', index:'wlssyy', width:'80', align:'center'},
		      {name:'wlssclfsMc', index:'wlssclfsMc', width:'60', align:'center'},
		      {name:'zrr', index:'zrr', width:'100', align:'center'},
		      {name:'wlssLymc', index:'wlssLymc', width:'80', sortable:false,align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'wlssLymc' + rowId + '\'';
				    }
			  },
			  {name:'ssSl', index:'ssSl', width:'60', sortable:false,align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'ssSl' + rowId + '\'';
				    }
			  },
			  {name:'ssZl', index:'ssZl', width:'60', sortable:false,align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'ssZl' + rowId + '\'';
				    }
			  },
			  {name:'ssTj', index:'ssTj', width:'60', sortable:false,align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'ssTj' + rowId + '\'';
				    }
			  },
			  {name:'bz', index:'bz', width:'200', sortable:false,align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'bz' + rowId + '\'';
				    }
			  },
			  {name:'ssJgmc', index:'ssJgmc', width:'80', sortable:false,align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'ssJgmc' + rowId + '\'';
				    }
			  },
		      {name:'xgrMc', index:'xgrMc', width:'70', sortable:false,align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'xgrMc' + rowId + '\'';
				    }
			  },
			  {name:'xgrq', index:'xgrq', width:'70', sortable:false,align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'xgrq' + rowId + '\'';
				    }
			  },
			  {name:'cjrMc', index:'cjrMc', width:'70', sortable:false,align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'cjrMc' + rowId + '\'';
				    }
			  },
			  {name:'cjrq', index:'cjrq', width:'70', sortable:false,align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'cjrq' + rowId + '\'';
				    }
			  }
		     ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum%>,						
		    rowList:[20,50,100,300,500],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'PAGEXH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
		    sortorder: 'asc',				//默认排序方向
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
		}
		    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”并增加链接
			function myGridComplete() {
		            var graduateIds = $("#dataList").jqGrid('getDataIDs');

		            var pcDjxh = $("#mainForm_domain_pcDjxh").val();
					var wfhDjxh = $("#mainForm_domain_wfhDjxh").val();
					var ddDjxh = $("#mainForm_domain_ddDjxh").val();
					var hwmxxh = $("#mainForm_domain_hwmxxh").val();
		            var khDjxh = $("#mainForm_domain_khDjxh").val();
		            
		            for (var i = 0; i < graduateIds.length; i++) {
		                var cl = graduateIds[i];
		                var wlssDjxh = jQuery("#dataList").jqGrid('getCell', cl,"wlssDjxh"); 	  //获取当前单元格里面的参数序号 
	                	var wsspztDm = jQuery("#dataList").jqGrid('getCell', cl,"wsspztDm");
	                	var wsSpxh = jQuery("#dataList").jqGrid('getCell', cl,"wsSpxh");
	                	//alert(wlssDjxh);
	                	link = "<a href=\"javascript:onUpdate('"+pcDjxh+"','" + wlssDjxh + "','"+wfhDjxh+"','"+ddDjxh+"','"+hwmxxh+"','"+khDjxh+"')\"><font color=\"blue\">修改</font></a>"
	                		 	+ " <a href=\"javascript:onDelete('"+wlssDjxh+"')\"><font color=\"blue\">删除</font></a>"
	                		 	+ " <a href=\"javascript:onUpload('"+pcDjxh+"','" + wlssDjxh + "','"+wfhDjxh+"','"+ddDjxh+"','"+hwmxxh+"','"+khDjxh+"')\"><font color=\"blue\">照片</font></a>";
	                	var xtcsSfsp = $("#mainForm_domain_xtcsSfsp").val();
						if ("Y" == xtcsSfsp) {
							if(wsspztDm=="0"||wsspztDm=="2"){
	                		link = "<a href=\"javascript:onUpdate('"+pcDjxh+"','" + wlssDjxh + "','"+wfhDjxh+"','"+ddDjxh+"','"+hwmxxh+"','"+khDjxh+"')\"><font color=\"blue\">修改</font></a>"
	                		 	+ " <a href=\"javascript:onDelete('"+wlssDjxh+"')\"><font color=\"blue\">删除</font></a>"
	                		 	+" <a href=\"javascript:onSend('"+wlssDjxh+"','"+wsSpxh+"')\"><font color=\"blue\">发送</font></a>"
	                		 	+ " <a href=\"javascript:onUpload('"+pcDjxh+"','" + wlssDjxh + "','"+wfhDjxh+"','"+ddDjxh+"','"+hwmxxh+"','"+khDjxh+"')\"><font color=\"blue\">照片</font></a>";
	                		}
						}
		                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
		            }
		            
		            
		            
		            var gridName = "dataList";
			   		var a = ['pageXh','hstoperationcol','wlssDjxh','wsSpxh','wsspztDm','wsspztMc',
			   				 'zje','wlssLymc','ssSl','ssZl','ssTj','bz',
			   				 'ssJgmc','xgrMc','xgrq','cjrMc','cjrq'];
		 		
		       		Merger(gridName, 'pageXh', a);
		     }
		     
		     
</script>
</head>

<body>
<%try{ %>
   <s:form action="wlssdj!initSsMx" namespace="/hygl" method="post" id="mainForm" name="mainForm">
   <s:hidden name="domain.pcDjxh"></s:hidden>
   <s:hidden name="domain.wfhDjxh"></s:hidden>
   <s:hidden name="domain.ddDjxh"></s:hidden>
   <s:hidden name="domain.hwmxxh"></s:hidden>
   <s:hidden name="domain.khDjxh"></s:hidden>
   <s:hidden name="domain.xtcsSfsp"></s:hidden>
   <s:hidden name="domain.xydjBz"></s:hidden>
   	<div class="pop_contc">
   		<div  id="divQuery">
			<fieldset>
		    <legend>基本信息</legend>
			<table width="96%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
				<tr>
					<td width="14%" align="right">派车单号：</td>
      				<td width="18%" align="left"><s:textfield name="domain.pcdh" cssClass="pop_input bgstyle_readonly"></s:textfield></td>
      				<td  align="right">客户：</td>
      				<td ><s:textfield name="domain.Khmc" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
      				<td  align="right">货物：</td>
      				<td ><s:textfield name="domain.hwmc" cssClass="pop_input bgstyle_readonly" ></s:textfield></td>
				</tr>
				<tr>
					<td  align="right">司机：</td>
      				<td  align="left"><s:textfield name="domain.cyrSjxm" cssClass="pop_input bgstyle_readonly"></s:textfield></td>
					<td width="14%" align="right">车辆号码：</td>
      				<td width="18%" align="left"><s:textfield name="domain.clhm4Query" cssClass="pop_input bgstyle_readonly"></s:textfield></td>
					<td width="14%" align="right">挂车号码：</td>
      				<td width="18%" align="left"><s:textfield name="domain.cyrGchm" cssClass="pop_input bgstyle_readonly"></s:textfield></td>
				</tr>
				<tr>
      				<td align="right">业务单位：</td>
      				<td ><s:textfield name="domain.ssJgmc" cssClass="pop_input bgstyle_readonly"></s:textfield></td>
      				<td  align="right">派车人：</td>
      				<td ><s:textfield name="domain.pcrMc" cssClass="pop_input bgstyle_readonly"></s:textfield></td>
      				<td align="right">派车日期：</td>
      				<td><s:textfield name="domain.pcrq" cssClass="pop_input bgstyle_readonly"></s:textfield></td>
				</tr>
			</table>
			</fieldset>
			
			<div class="pop_btn">
			    <button type="button" class="pop_btnbg" id="addBtn">新 增</button>
			    &nbsp;
			    <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
		    </div>
		   </div>
	 <!-- 分页表格 id必须为dataList -->
		<table id="dataList"><tr><td /></tr></table>
		<!-- 分页导航 -->
		<div id="pager"></div>
    </div>
   <%@include file="/common/message.jsp" %>
   </s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>