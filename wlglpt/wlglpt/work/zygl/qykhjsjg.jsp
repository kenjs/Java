<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>企业-客户-结算价格</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>

<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>

<script type="text/javascript">
	$(document).ready(function(){
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		initHykhData(300, $("#mainForm_domain_ssJgbm").val(), $("#mainForm_domain_bmDjxh").val());
		
		$("#mainForm_domain_bmDjxh").change(function(){
			initHykhData(300, $("#mainForm_domain_ssJgbm").val(), $(this).val());
		});
			
			//查询按钮事件
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//新增按钮事件
			$("#addBtn").click(function(){
				var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
				var bmDjxh = trim($("#mainForm_domain_bmDjxh").val());
				if(bmDjxh==''||bmDjxh==undefined){
					bmDjxh=ssJgbm
				}
				var fhrDjxh = trim($("#mainForm_domain_fhrDjxh").val()); 
				var fhrMc = trim($("#mainForm_domain_fhrMc").val()); 
				if(fhrDjxh==''||fhrDjxh==null){
					showError("客户不能为空！");
					return ;
				}
				var url = jcontextPath+"/zygl/qykhjsjg!initMx.action?domain.ssJgbm="+bmDjxh+"&domain.khDjxh="+fhrDjxh+"&domain.khMc="+fhrMc;
		    	window.showModalDialog(url,window,"dialogHeight:570px;dialogWidth:700px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		    	onRefresh();
			});
			//初始化表格
			initDataGrid();
			
			var gs = $("#mainForm_domain_ssJgbm").val();
			bmInit(gs, '', "domain.bmDjxh", "mainForm_domain_bmDjxh", "false", "Y");
	});

    function onUpdate(khDjxh){
    	var url =jcontextPath+"/zygl/qykhjsjg!initMx.action?domain.jsjgDjxh="+khDjxh;
		window.showModalDialog(url,window,"dialogHeight:570px;dialogWidth:700px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		onRefresh();
    }
    
    var keyValue = "";
	function onDelete( khDjxh){
		keyValue = khDjxh;
		showConfirm("确定要删除么？","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.jsjgDjxh":keyValue};
		 var url = jcontextPath+"/zygl/qykhjsjg!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("删除成功！");
        onRefresh();
	}	
    
	function onDropDownSelected4Fhr(li, itemIndex) {
		var sValue = li.selectValue;
		var jsonStr=$("#mainForm_fhrData").val();
	   	if("[]"!=jsonStr){
	    	var data=eval(jsonStr);
	        $(data).each(function(i,item){
	        	if(sValue==item.fhrMc){
	        		$("#mainForm_domain_fhrDjxh").val(item.fhrDjxh);
	        		commonInit('kh-hw', item.fhrDjxh, '', 'domain.hwDjxh', 'mainForm_domain_hwDjxh', 'Y', 'Y');
	        		
		        	return;
	        	}
	    	});
		}
	}
	
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		
		var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
		var bmDjxh = trim($("#mainForm_domain_bmDjxh").val());
		var fhrMc = trim($("#mainForm_domain_fhrDjxh").val()); 
		var hwDjxh='';
		if(fhrMc!=''&&fhrMc!=null){
			 hwDjxh = trim($("#mainForm_domain_hwDjxh").val());
		}
		if(bmDjxh!=null && bmDjxh!=""){
		  ssJgbm=bmDjxh;
	    }
		//请求表格数据
		var url = jcontextPath+"/zygl/qykhjsjg!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.khDjxh":fhrMc,"domain.hwDjxh":hwDjxh}								//请求的参数，json格式
		 }
		 ).trigger("reloadGrid");	
		
			//显示到第一页，以免总体数据发生变化的时候，分页的信息混乱
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
		    colNames:['操作', '所属机构','客户名称','是否适用全部货物','始发地','目的地',
				     '货物名称','价格计算公式','价格计算系统公式','有效期起','有效期止',
				     '创建人','创建日期','修改人','修改日期',''],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
		      {name:'sjMc', index:'sjMc', width:'110', align:'center'}, 
		      {name:'khMc', index:'khMc', width:'160', align:'center'}, 
		      {name:'syfw', index:'syfw', width:'110', align:'center'}, 
		      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'70', align:'center'}, 
		      {name:'shrXzqhMc', index:'shrXzqhMc', width:'70', align:'center'}, 
			  {name:'hwMc', index:'hwMc', width:'130', align:'center'},
				
		      {name:'jgjsgs', index:'jgjsgs', width:'150', align:'center'},
		      {name:'xtgs', index:'xtgs', width:'150', align:'center'},
		      {name:'yxqQ', index:'yxqQ', width:'80', align:'center'}, 
		      {name:'yxqZ', index:'yxqZ', width:'80', align:'center'}, 
		      
			  {name:'cjrMc', index:'cjrMc', width:'80', align:'center'},
			  {name:'cjrq', index:'cjrq', width:'80', align:'center'}, 
		      {name:'xgrMc', index:'xgrMc', width:'80', align:'center'}, 
		      {name:'xgrq', index:'xgrq', width:'80', align:'center'},
		      {name:'jsjgDjxh', index:'jsjgDjxh', width:'80', align:'center',hidden:true}
			 ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum%>,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[20,50,100,300,500],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'KH_DJXH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
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
		       	   $("#mainForm").attr("action",jcontextPath+"/zygl/qykhjsjg!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"khDjxh"); 
                var syfw = jQuery("#dataList").jqGrid('getCell', cl,"syfw"); 
                //获取当前单元格里面的登记序号 
                var id = jQuery("#dataList").jqGrid('getCell', cl,"jsjgDjxh"); 
                var link = "<a href=\"javascript:onUpdate('"+id+"')\"><font color=\"blue\">修改</font></a>"
                + " <a href=\"javascript:onDelete('"+id+"')\"><font color=\"blue\">删除</font></a>";
                var str='';
                if(syfw=='Y'){
                	str="是";
                }
                else{
                	str="否";
                }
                $("#dataList").jqGrid('setRowData', cl, { 'syfw': str }); 
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
     /**************************************分页结束****************************************/

</script>
</head>
<body>
<s:form action="qykhdjxx!query" namespace="/zygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="fhrData" />
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
	<fieldset>
		<legend>查询条件</legend>
	   <table width="99%" border="0" cellspacing="0" cellpadding="0">
	        <tr>
	          <td width="8%" align="right">单位：</td>
	          <td width="21%"><sys:gsList myName="domain.ssJgbm" myId="mainForm_domain_ssJgbm" mcContainDmBz="Y" myClass="select" onChange="bmInit(this.value, '', 'domain.bmDjxh', 'mainForm_domain_bmDjxh', 'Y', 'Y')"/></td>
	          <td width="8%" align="right">部门：</td>
	          <td width="21%"><select id="mainForm_domain_bmDjxh" name="domain.bmDjxh" class="select"/></td>
	          <td width="8%" align="right">客户：</td>
	          <td width="21%">
  					<div class="inputsel" style="width: 230px; ">
  						<s:hidden name="domain.fhrDjxh"/>
  						<s:textfield name="domain.fhrMc" cssClass="pop_input noborder inputext bgstyle_optional" style="width:200px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="fhr" onFocus="this.blur()"></a>
  					</div>
			  		<div class="inputsc">
		              <div id="inputSel_fhr" class="inputselcont 
		              
		              inputselFixedSize ac_results" style="width:270px;">
		              </div>
		            </div>
		          </td>
		      </tr>
		      <tr>
	          <td width="10%" align="right">货物：</td>
	          <td width="25%"><select name="domain.hwDjxh" id="mainForm_domain_hwDjxh" class="select"/></td>
	        </tr>
	   </table>
	</fieldset>
  
		<!-- 分页表格 id必须为dataList -->
		<table id="dataList"><tr><td/></tr></table> 
		<!-- 分页导航 -->
		<div id="pager"></div>
		</div>
		<%@include file="/common/message.jsp" %>
	</div>
</s:form>
</body>
</html>
