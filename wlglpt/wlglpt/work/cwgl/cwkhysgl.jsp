<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>财务-客户预收收入</title>
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
				onRefresh();
			});
			
			//新增按钮事件
			$("#addBtn").click(function(){
				var kh=$("#mainForm_domain_fhrDjxh").val();
				if(kh==""){
					showError("请选择一个客户！");
					return;
				}
				var url = jcontextPath+"/cwgl/khyslr!initMx.action?domain.khDjxh="+kh;
				window.showModalDialog(url,window,"dialogHeight:480px;dialogWidth:700px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
				onRefresh();
			});
			//初始化表格
			initDataGrid();
						

	});

    function onUpdate(srDjxh){
    	popwindow(jcontextPath+"/cwkhysgl!initMx?domain.srDjxh="+srDjxh);
    }
    
    function onLook(srDjxh){
    	var url = jcontextPath+"/cwgl/khyslr!onLook.action?domain.srDjxh="+srDjxh+"&num="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:400px;dialogWidth:700px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		onRefresh();
    }
    
    var keyValue = "";
	function onDelete( srDjxh){
		keyValue = srDjxh;
		showConfirm("确定要删除么？","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.srDjxh":keyValue};
		 var url = jcontextPath+"/khyslr!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("删除成功！");
        onRefresh();
	}	
    
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		  var jgbm=$("#mainForm_domain_ssJgbm4Query").val();
		  var fhrDjxh=$("#mainForm_domain_fhrDjxh").val();
		  var fhrMc = $("#mainForm_domain_fhrMc").val();
		  var rqq=$("#mainForm_domain_rqq").val();
		  var rqz=$("#mainForm_domain_rqz").val();
		  if(rqq>rqz){
			  showError("登记日期起不能大于登记日期止！");
			  return;
		  }
		//请求表格数据
		var url = jcontextPath+"/khyslr!query";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.djJgbm":jgbm,"domain.khDjxh":fhrDjxh,"domain.rqq":rqq,"domain.rqz":rqz,"domain.fhrMc":encodeURI(fhrMc)}								//请求的参数，json格式
		 }
		 ).trigger("reloadGrid", [{page:1}]);		//显示到第一页，以免总体数据发生变化的时候，分页的信息混乱
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
		    colNames:['操作', '收入登记序号(SEQ_SR_DJXH)','客户名称','金额','登记人','登记日期',
				     '登记部门','所属机构','修改人','修改日期'],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
		      {name:'srDjxh', index:'srDjxh', width:'100',hidden:true, align:'center'}, 
		      {name:'khMc', index:'khMc', width:'150', align:'center'}, 
		      {name:'je', index:'je', width:'100', align:'center'}, 
		      {name:'djrMc', index:'djrMc', width:'100', align:'center'}, 
		      {name:'djrq', index:'djrq', width:'100', align:'center'}, 
		      {name:'djBm', index:'djBm', width:'100', align:'center'}, 
		      {name:'ssJgmc', index:'ssJgmc', width:'100', align:'center'}, 
		      {name:'xgrMc', index:'xgrMc', width:'80', align:'center'}, 
		      {name:'xgrq', index:'xgrq', width:'80', align:'center'}
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum%>,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[20,50,100,300,500],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'SR_DJXH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
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
		  
	  	  // add custom button to export the data to excel
		  $("#dataList").jqGrid('navButtonAdd','#pager',{
		       caption:"", 
		       onClickButton : function () { 
		       	   $("#mainForm").attr("action",jcontextPath+"/khyslr!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"srDjxh"); 	  //获取当前单元格里面的登记序号 
                var link = "<a href=\"javascript:onLook('"+val+"')\"><font color=\"blue\">查看</font></a>";
              
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
     /**************************************分页结束****************************************/

</script>
</head>
<body>
<s:form action="cwkhysgl!query" namespace="" method="post" id="mainForm" name="mainForm">
	<div class="right_btnbg">
	<s:hidden name="fhrData" />
		<ul class="lcont">
		    <li class="no">操作：</li>
		    <li><a href="#" id="queryBtn" class="licon04">检 索</a></li>
		    <li><a href="#" id="addBtn" class="licon01">增加</a></li>
		    <li><a href="#" id="closeBtn" class="licon03">关闭</a></li>
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
			<td width="8%" align="right">业务部门：</td>
			<td width="30%">
				<sys:gsList myId="mainForm_domain_ssJgbm4Query" myName="domain.ssJgbm4Query" mcContainDmBz="Y" myClass="select" onChange="bmInit(this.value, '', 'domain.djJgbm4Query', 'mainForm_domain_djJgbm4Query', 'Y', 'Y')" />
				
				
			</td>
			<td width="8%" align="right">客户名称：</td>
			<td width="20%">
					<s:hidden name="domain.fhrDjxh"></s:hidden>
  					<div class="inputsel" style="width: 215px; ">
  						<s:textfield name="domain.fhrMc" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width:185px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="fhr" onFocus="this.blur()"></a>
  					</div>
			  		<div class="inputsc">
		              <div id="inputSel_fhr" class="inputselcont inputselFixedSize ac_results">
		              </div>
		            </div></td>
		    <td width="8%" align="right">登记日期：</td>
		     <td width="35%"><sys:dateFirstDLastMonthTag myName="domain.rqq" myId="mainForm_domain_rqq" myClass="ymdate" />
	          			～
	          		<sys:dateCurrentDayTag myName="domain.rqz" myId="mainForm_domain_rqz" myClass="ymdate" /></td></td>
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
