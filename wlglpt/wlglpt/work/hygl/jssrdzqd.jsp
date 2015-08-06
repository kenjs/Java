<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK"
	pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>结算-收入对帐-清单</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
			document.getElementById("maincont").onmousewheel=hideHelpWindow;
			
			initHykhData(300,$("#mainForm_domain_ssJgbm").val(), $("#mainForm_domain_djJgbm").val(),"jsonData","khMc","khDjxh");
			
			$("#mainForm_domain_djJgbm").change(function(){
				initHykhData(300, $("#mainForm_domain_ssJgbm").val(), $(this).val(),"jsonData","khMc","khDjxh");
			});
			//查询按钮事件
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//新增按钮事件
			$("#addBtn").click(function(){
				var djJgbm = trim($("#mainForm_domain_djJgbm").val()); 
				var dwDm = trim($("#mainForm_domain_ssJgbm").val()); 
				var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
				
		  		if(undefined==dwDm || null==dwDm || ""==dwDm){
					showAlert("请您选择业务单位！");
					return;
				}
				
				//if(undefined==djJgbm || null==djJgbm || ""==djJgbm){
					//showAlert("请您选择部门！");
					//return;
				//}
				
				if(undefined==khDjxh || null==khDjxh || ""==khDjxh){
					khDjxh = '';
				}
				//var dwMcStr = $("select[name='domain.ssJgbm'] option[selected]").text(); 
				var dwMcStr = $("select[name='domain.ssJgbm']").find("option:selected").text(); 
				var dwMc=dwMcStr.split(" ")[1];
				var khMc = trim($("#mainForm_domain_khMc").val()); 
				//popwindow(jcontextPath+"/hygl/jssrdzqd!initMx");
				onUpdate("",dwDm,khDjxh,dwMc,khMc);
			});
			//初始化表格
			initDataGrid();
			initList();				

	});
	
	function initList() {
		var ssJgbm = $("#mainForm_domain_ssJgbm").val(); 
		var djJgbm =$("#mainForm_domain_djJgbm").val();
		var jsonObj = {"domain.paramdm":ssJgbm,
			"domain.defaultValue":djJgbm,
			"domain.currentObjName":"domain.djJgbm",
			"domain.currentObjId":"mainForm_domain_djJgbm",
			"domain.containQbBz":"Y",
			"domain.mcContainDmBz":"Y"};
	
		var url=jcontextPath+"/common/wlglptCommon!bmInit";	
		ajaxCommon(url,jsonObj,"changeBmList");
	}
	function changeBmList(data){
		var list = data.domain.dataList;
		$("#"+data.domain.currentObjId).empty();
		$.each(list, function(i,domain){
		    var option = $("<option>").text(domain.mc).val(domain.dm);
		    //默认选中
		    if(data.domain.defaultValue==domain.dm){
		    	option = $("<option selected='selected'>").text(domain.mc).val(domain.dm);
		    }
		    
		    $("#"+data.domain.currentObjId).append(option);
		});
	}
	
	function onView(qdDjxh) {
		var url = jcontextPath+"/hygl/jssrdzqd!viewMx?domain.qdDjxh="+qdDjxh;
		window.open(url,"_blank")
	}
	
    function onUpdate(qdDjxh,ssJgbm,khDjxh,dwMc,khMc){
    	var url = jcontextPath+"/hygl/jssrdzqd!initMx.action?domain.qdDjxh="+qdDjxh+"&domain.ssJgbm="+ssJgbm+"&domain.khDjxh="+khDjxh;
    	url+="&domain.dwMc="+dwMc+"&domain.khMc="+khMc+"&num="+Math.random();
    	url = encodeURI(encodeURI(url));
    	
    	window.showModalDialog(url,window,"dialogHeight:600px;dialogWidth:760px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
    	//window.open(url);
    	onRefresh();
    	//popwindow(jcontextPath+"/hygl/jssrdzqd!initMx?domain.qdDjxh="+qdDjxh);
    }
    
    var keyValue = "";
	function onDelete( qdDjxh){
		keyValue = qdDjxh;
		showConfirm("确定要删除么？","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.qdDjxh":keyValue};
		 var url = jcontextPath+"/hygl/jssrdzqd!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("删除成功！");
        onRefresh();
	}	
    
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		var djJgbm = trim($("#mainForm_domain_djJgbm").val()); 
		var dwDm = trim($("#mainForm_domain_ssJgbm").val()); 
		var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
		var rqQ = trim($("#mainForm_domain_rqQ").val()); 
		var rqZ = trim($("#mainForm_domain_rqZ").val()); 
		
  		if(undefined==dwDm || null==dwDm || ""==dwDm){
			showAlert("请您选择业务单位！");
			return;
		}
		
		//if(undefined==khDjxh || null==khDjxh || ""==khDjxh){
			//showAlert("请您选择客户！");
			//return;
		//}
		if(undefined==rqQ || null==rqQ || ""==rqQ){
			showAlert("请您选择创建日期起！");
			return;
		}
		if(undefined==rqZ || null==rqZ || ""==rqZ){
			showAlert("请您选择创建日期止！");
			return;
		}	
		//请求表格数据
		var url = jcontextPath+"/hygl/jssrdzqd!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.khDjxh":encodeURI(khDjxh),"domain.rqQ":encodeURI(rqQ),"domain.rqZ":encodeURI(rqZ),
				      "domain.djJgbm":djJgbm,"domain.ssJgbm":dwDm}								//请求的参数，json格式
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
		    colNames:['操作', '清单登记序号','清单名称','清单金额','金额明细','汇总方式',
				     '登记人','登记日期','部门',
				     '单位'],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'80', align:'center'},
		      {name:'qdDjxh', index:'qdDjxh', width:'30', align:'center',hidden:true}, 		     
		      {name:'qdmc', index:'qdmc', width:'250', align:'center'}, 
		      {name:'heJe', index:'heJe', width:'80', align:'center'}, 
		      {name:'heJe', index:'heJe', width:'80', align:'center',hidden:true}, 
		      {name:'dzqdhzfsMc', index:'dzqdhzfsMc', width:'80', align:'center'}, 
		      
		      {name:'cjrMc', index:'cjrMc', width:'70', align:'center'}, 
		      {name:'djrq', index:'djrq', width:'70', align:'center'}, 

		      {name:'bmMc', index:'bmMc', width:'180', align:'center'}, 
		      {name:'dwMc', index:'dwMc', width:'120', align:'center'}
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum%>,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[20,50,100,300,500],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'QD_DJXH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
		    sortorder: 'DESC',				//默认排序方向
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
	
    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"qdDjxh"); 	  //获取当前单元格里面的登记序号 
                var link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">修改</font></a>"
                + " <a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">删除</font></a>"
                + " <a href=\"javascript:onView('"+val+"')\"><font color=\"blue\">查看</font></a>";
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
     /**************************************分页结束****************************************/

</script>
</head>
<body>
<s:form action="jssrdzqd!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="jsonData" />
	<div class="right_btnbg">
	<ul class="lcont">
		<li class="no">操作：</li>
		<li><a href="#" id="queryBtn" class="licon04">检 索</a></li>
		<li><a href="#" id="addBtn" class="licon01">增 加</a></li>
		<li><a href="#" id="closeBtn" class="licon03">关 闭</a></li>
	</ul>

	<ul class="rcont">
		<li class="ricon02" onclick="slideToggle('syquery')">显示/隐藏查询条件</li>
		<li class="ricon03">帮助</li>
	</ul>
	</div>
	
	<div class="right_cont" id="maincont">
	<div id="divQuery">
	<fieldset><legend>查询条件</legend>
	<table width="99%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="8%" align="right">业务单位：</td>
			<td width="25%">
				<sys:gsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y" myClass="select" onChange="initList()" /></td>
			<td width="8%" align="right">登记部门：</td>
			<td width="21%">
				<select name="domain.djJgbm" id="mainForm_domain_djJgbm" class="select">
					<option value="${domain.djJgbm }" selected="selected"></option>
				</select>
			</td>
			<td width="8%" align="right">客户名称：</td>
			<td width="21%">
			<s:hidden name="domain.khDjxh"></s:hidden>
			<div class="inputsel" style="width: 230px; ">
				<s:textfield name="domain.khMc" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width:200px;"></s:textfield> 
				<a href="#" class="icon_arrow" id="fhr" onfocus="this.blur()"></a></div>
			<div class="inputsc">
			<div id="inputSel_fhr"
				class="inputselcont inputselFixedSize ac_results"></div>
			</div>
			</td>
		</tr>
		<tr>
			<td align="right">创建日期：</td>
			<td>
				<s:textfield name="domain.rqQ" readonly="true" cssClass="ymdate"></s:textfield>
				 ～ 
				<s:textfield name="domain.rqZ" readonly="true" cssClass="ymdate"></s:textfield></td>
				<td colspan="4"></td>
		</tr>
	</table>
	</fieldset>
	</div>
		<!-- 分页表格 id必须为dataList -->
		<table id="dataList"><tr><td></td></tr>
		</table>
		<!-- 分页导航 -->
		<div id="pager"></div>
		<%@include file="/common/message.jsp"%>
	</div>

</s:form>
</body>
</html>
