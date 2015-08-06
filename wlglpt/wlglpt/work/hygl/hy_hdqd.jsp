<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<html>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>回单清单打包</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript">
      $(document).ready(function(){
			//查询按钮事件
			$("#queryBtn").click(function(){
				onRefresh();
			});
			$("#dbfsBtn").click(function(){
				//alert($("#mainForm_domain_jsGsbm").val());
				if(!checkdata()){
					return;
				}
				var xhs = $(":checked[name='xhs']");
				if(xhs.length<=0){
					showAlert("请选择要打包的回单！")
					return;
				}else{
					showConfirm("一共"+xhs.length+"张回单！确认打包并发送么？","doDbfs")
				}
			});
			//初始化表格
			initDataGrid();

	});	
	function onViewPc(pcDjxh){
    	var url = jcontextPath+"/jcgl/jcpcxxgl!initMx.action?domain.pcDjxh="+pcDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    }
    function doDbfs(){
    	var jsGsbm = $("#mainForm_domain_jsGsbm").val();
    	var fsGsbm = $("#mainForm_domain_ssJgbm").val();
    	
    	var qdmc = $("#mainForm_domain_qdmc").val();
    	var bz = $("#mainForm_domain_bz").val();
    	var xhs = $(":checked[name='xhs']");
    	
    	var jsonStr = getJqueryParam(xhs,"domain.hdDjxhs");
		var jsonObj = {"domain.jsGsbm":jsGsbm,"domain.fsGsbm":fsGsbm,"domain.qdmc":encodeURI(qdmc),"domain.bz":encodeURI(bz)};
		jsonStr += jQuery.param(jsonObj);
		var url = jcontextPath+"/hygl/hypchwxxhdqd!dbfs";  
        //alert(jsonStr);
        ajaxCommon(url,encodeURI(jsonStr),"fsOk", false);
    }
    function fsOk(){
    	onRefresh();
    }
    function checkdata(){
		var controlNameArray = ["domain.qdmc","domain.jsGsbm","domain.bz"];
		var labelNameArray = ["清单名称","接收单位","备注"];
		var compareValueArray = [100,16,500];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true,false];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}
  /**************************************分页开始****************************************/
  //刷新表格
	function onRefresh(){
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var rqQ = $("#mainForm_domain_rqQ").val();
		var rqZ = $("#mainForm_domain_rqZ").val(); 
  		//请求表格数据
		var url = jcontextPath+"/hypchwxxhdqd!query.action";   
		 $("#dataList").jqGrid("setGridParam",{ 
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.rqQ":rqQ,"domain.rqZ":rqZ}								//请求的参数，json格式
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
		    colNames:['<input title="选中/取消选中" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />', '回单登记序号','派车登记序号','未发货登记序号','订单登记序号','货物明细序号',
				     '回单编号','实装数量','实装重量','实装体积','回单接收日期','要求到达日期','收货方式代码',
				     '实装_结算数量',
				     '派车单号','派车日期','派车方式', '装车方式','车辆号码','挂车号码',
				     '司机','运费合计','预付运费', '备注','派车人','派车部门','业务公司'
				     
				    ],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'40', align:'center'},
		      {name:'hdDjxh', index:'hdDjxh', width:'100', align:'center',hidden:true}, 
		      {name:'pcDjxh', index:'pcDjxh', width:'100', align:'center',hidden:true}, 
		      {name:'wfhDjxh', index:'wfhDjxh', width:'100', align:'center',hidden:true}, 
		      {name:'ddDjxh', index:'ddDjxh', width:'100', align:'center',hidden:true}, 
		      {name:'xh', index:'xh', width:'100', align:'center',hidden:true},
		      {name:'hdbh', index:'hdbh', width:'60', align:'center'}, 
		      
		      {name:'szHwSl', index:'szHwSl', width:'60', align:'center'}, 
		      {name:'szHwZl', index:'szHwZl', width:'60', align:'center'}, 
		      {name:'szHwTj', index:'szHwTj', width:'60', align:'center'}, 
		      {name:'hdjsrq', index:'hdjsrq', width:'80', align:'center'},
		      {name:'yqDdrq', index:'yqDdrq', width:'80', align:'center'},
		      {name:'shfsDm', index:'shfsDm', width:'100', align:'center',hidden:true}, 
		      {name:'szJsSl', index:'szJsSl', width:'100', align:'center',hidden:true},
		      {name:'pcdh', index:'pcdh', width:'70', align:'center'},
		      
		      
		      {name:'pcrq', index:'pcrq', width:'80', align:'center'},
		      {name:'pcfsMc', index:'pcfsMc', width:'60', align:'center'},
		      {name:'zcfsMc', index:'zcfsMc', width:'60', align:'center'},
		      {name:'cyrClhm', index:'cyrClhm', width:'70', align:'center'},
		      {name:'cyrGchm', index:'cyrGchm', width:'70', align:'center'},
		      
		      {name:'cyrSjxm', index:'cyrSjxm', width:'100', align:'center'},
		      {name:'yfHj', index:'yfHj', width:'100', align:'center'},
		      {name:'yfYfyf', index:'yfYfyf', width:'100', align:'center'},
		      {name:'bz', index:'bz', width:'150', align:'center'},
		      {name:'pcrMc', index:'pcrMc', width:'100', align:'center'},
		      {name:'pcJgmc', index:'pcJgmc', width:'100', align:'center'},
		      {name:'ssJgmc', index:'ssJgmc', width:'100', align:'center'}	      		      
		      
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum%>,						
		    rowList:[20,50,100,300,500],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'HD_DJXH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
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
		 
		    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”并增加链接
			function myGridComplete() {
		            var graduateIds = $("#dataList").jqGrid('getDataIDs');
		            for (var i = 0; i < graduateIds.length; i++) {
		                var cl = graduateIds[i];
		                var val = jQuery("#dataList").jqGrid('getCell', cl,"hdDjxh"); 	  //获取当前单元格里面的参数序号 
		                var link = '<input type="checkbox" name="xhs" value="'+val+'" />';
		                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link });
		                var pcDjxh = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh");
		                var pcdh = jQuery("#dataList").jqGrid('getCell', cl,"pcdh");
                		var strPc="<a href=\"javascript:onViewPc("+pcDjxh+")\"><font color=\"blue\">"+pcdh+"</font></a>";
                		$("#dataList").jqGrid('setRowData', cl, { 'pcdh': strPc });
		            }
		     }
	}
</script>
</head>
<body>
<s:form action="hypchwxxhdqd!queryQd" theme="simple" namespace="cwgl" method="post" id="mainForm" name="mainForm">
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
				<legend>回单打包</legend>
				<table width="99%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="10%" align="right">清单名称：</td>
						<td width="22%" >
						 	<s:textfield name="domain.qdmc" cssClass="inputext pop_input noborder bgstyle_required"></s:textfield>
						</td>
						<td width="10%" align="right">接收单位：</td>
						<td width="28%">
							 <sys:fgsList myId="mainForm_domain_jsGsbm" myName="domain.jsGsbm" myClass="select" contaisDq="N"></sys:fgsList>
						</td>
						<td width="29%" align="center" colspan="2">
							<button type="button" class="pop_btnbg" id="dbfsBtn">打包发送</button>
						</td> 
					</tr>
					<tr>
      				<td align="right">备注：</td>
      				<td colspan="5">
      					<s:textarea name="domain.bz" rows="3" cssClass="pop_textarea_colspan2" ></s:textarea>
      				</td>
      			</tr>	   
				</table>
			</fieldset>
			<fieldset>
				<legend>查询条件</legend>
				<table width="99%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="10%" align="right">单位：</td> 
						<td width="22%">  
						   <sys:gsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y"  myClass="select"/>
		  			    </td>
		  			    <td  align="right" width="10%">日期：</td>
		                <td width="32%">
		                  	<sys:dateFirstDMonth myName="domain.rqQ" myId="mainForm_domain_rqQ" myClass="ymdate" />
			          		～
			          		<sys:dateCurrentDayTag myName="domain.rqZ" myId="mainForm_domain_rqZ" myClass="ymdate" />
		                </td>  
						<td width="26%" colspan="2"></td>     
		            </tr>
				</table>
			</fieldset>
		</div>
	
		<!-- 分页表格 id必须为dataList -->
		<table id="dataList"><tr><td /></tr></table>
		<!-- 分页导航 -->
		<div id="pager"></div>
	</div>
</s:form>
</body>
