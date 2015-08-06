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
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
			document.getElementById("maincont").onmousewheel=hideHelpWindow;
			
			initHykhData(300);
			$("#mainForm_domain_pcJgbm").change(function(){
				initRy();
			});
		
			//查询按钮事件
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//新增按钮事件
			$("#addBtn").click(function(){
				
			});
			//初始化表格
			initDataGrid();
			initRy();	
			var sjJgbm = $("#mainForm_domain_ssJgbm").val();
			bmInit(sjJgbm, '', "domain.pcJgbm", "mainForm_domain_pcJgbm", "Y", "Y");
	});
	
	function initRy() {
		var sj = $("#mainForm_domain_pcJgbm").val();
		commonInit("BMYH", sj, '', "domain.pcrCzyDjxh", "mainForm_domain_pcrCzyDjxh", "Y", false);
	}

    function onUpdate(ddDjxh){
    	var url = jcontextPath+"/hyzpaj!initMx.action?domain.pcDjxh="+ddDjxh;
    	window.showModalDialog(url,window,"dialogHeight:780px;dialogWidth:820px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
    	onRefresh();
    	//popwindow(jcontextPath+"/hygl/hytydgl!initMx?domain.ddDjxh="+ddDjxh);
    }
    
    function onViewPc(pcDjxh) {
		var url = jcontextPath+"/jcgl/jcpcxxgl!initMx.action?domain.pcDjxh="+pcDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    	//window.open(url);
	}
    
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		sj = $("#mainForm_domain_ssJgbm").val();
		bm = $("#mainForm_domain_pcJgbm").val();
		var bz='';
		if(bm!=null&&bm!=''){
			sj=bm;
			bz='B';
		}
		else{
			bz='D';
		}
		djrCzyDjxh4Query = $("#mainForm_domain_pcrCzyDjxh").val();
		//fhrDjxh4Query
		rqq = $("#mainForm_domain_pcrqq").val();
		//shrDjxh4Query
		rqz = $("#mainForm_domain_pcrqz").val(); 
		if(rqq>rqz){
			showError("派车开始日期不能大于派车截止日期！");
			return;
		}
		fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		clhm = $("#mainForm_domain_clhm4Query").val();
		sjxm = $("#mainForm_domain_sjXm").val();
		pcdh = $("#mainForm_domain_pcDh").val();
		fhrMc = $("#mainForm_domain_fhrMc").val();
	
		
		//请求表格数据
		var url = jcontextPath+"/hygl/hyzpaj!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.pcJgbm":sj,"domain.bz":bz, "domain.pcrCzyDjxh":encodeURI(djrCzyDjxh4Query),"domain.pcrqq":rqq,"domain.fhrDjxh":fhrDjxh,
		 			"domain.pcrqz":rqz, "domain.clHm":encodeURI(clhm),"domain.sjXm":encodeURI(sjxm),"domain.pcDh":encodeURI(pcdh)
		 			,"domain.fhrMc":encodeURI(fhrMc)
		 			}								//请求的参数，json格式
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
		    colNames:['操作', '状态','派车单号','派车日期', '车主姓名', '车辆号码', '挂车号码', 
		              '司机姓名', '手机号码', '其他联系电话', '派车人','所属单位','所属部门','ID' 
		              ],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'50', align:'center'},
			  {name:'ajCs', index:'ajCs', width:'60', align:'center'}, 
			  {name:'pcDh', index:'pcDh', width:'90', align:'center'}, 
		      {name:'pcrq', index:'pcrq', width:'80',align:'center'}, 
		      {name:'czxm', index:'czxm', width:'80', align:'center'}, 
			  {name:'clHm', index:'clHm', width:'80', align:'center'},
			  
			  {name:'gcHm', index:'gcHm', width:'90', align:'center'}, 
			  {name:'sjXm', index:'sjXm', width:'80', align:'center'}, 
		      {name:'sjHm', index:'sjHm', width:'100', align:'center'}, 
		      {name:'dianhua', index:'dianhua', width:'100', align:'center'},
		      {name:'pcrXm', index:'pcrXm', width:'80', align:'center'},
		      {name:'sjMc', index:'sjMc', width:'90', align:'center'}, 
		      {name:'bmMc', index:'bmMc', width:'90', align:'center'}, 
		      {name:'pcDjxh', index:'pcDjxh', width:'0', hidden:true, align:'center'}
		      
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum%>,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[20,50,100,300,500],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'DD_DJXH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/hyzpaj!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
               var val = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh"); 
                var ajCs = jQuery("#dataList").jqGrid('getCell', cl,"ajCs"); 
                var pcDjxh = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh"); 
                var pcdh = jQuery("#dataList").jqGrid('getCell', cl, "pcDh");
                var pcStr="<a href=\"javascript:onViewPc("+pcDjxh+")\"><font color=\"blue\">"+pcdh+"</font></a>";
				var link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">登记</font></a>";
         
               	if(ajCs==0){
               		var zt="<font color=\"red\">未安检</font>";
               	}
               	else{
               		var zt="<font color=\"black\">已安检</font>";
               	}
                $("#dataList").jqGrid('setRowData', cl, { 'pcDh': pcStr }); 
               	 $("#dataList").jqGrid('setRowData', cl, { 'ajCs': zt }); 
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
	
    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接

       
   	
     /**************************************分页结束****************************************/

</script>
</head>
<body>
<s:form action="hytydgl!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="dropDownData"></s:hidden>
	<s:hidden name="jsonData" />
	
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
	<div style="display: none;" id="maincont"></div>
	<div class="right_cont">
	  <div id="divQuery">
		<fieldset>
			<legend>查询条件</legend>
		   <table width="99%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		        	<td width="8%" align="right">业务单位：</td>
          			<td width="21%">
          				<sys:gsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y" myClass="select" onChange="bmInit(this.value, '', 'domain.pcJgbm', 'mainForm_domain_pcJgbm', 'Y', 'Y')" />
		  			</td>
		  			<td width="8%" align="right">派车部门：</td>
          			<td width="21%">
          				<select id="mainForm_domain_pcJgbm" name="domain.pcJgbm" class="select" >
          					<option value="${domain.pcJgbm }" selected="selected"></option>
          				</select>
		  			</td>
	        	  <td width="8%" align="right">派车人：</td>
		          <td width="25%">
		          		<select name="domain.pcrCzyDjxh" id="mainForm_domain_pcrCzyDjxh" class="select" />
	          	  </td>
	          	</tr>
		        <tr>
		        	<td align="right">客户名称：</td>
		          <td>
		          	<s:hidden name="domain.fhrDjxh"></s:hidden>
  					<div class="inputsel" style="width: 230px; ">
  						<s:textfield name="domain.fhrMc" cssClass="pop_input noborder inputext" cssStyle="width:200px;"></s:textfield>
  						<a href="#" class="icon_arrow" id="fhr" onFocus="this.blur()"></a>
  					</div>
			  		<div class="inputsc">
		              <div id="inputSel_fhr" class="inputselcont inputselFixedSize ac_results">
		              </div>
		            </div>
		          </td>
		          <td align="right">派车单号：</td>
		          <td><s:textfield name="domain.pcDh" cssClass="pop_input noborder" /></td>
		          <td width="8%" align="right">派车日期：</td>
		          <td width="21%">
		          <sys:dateFirstDLastMonthTag myName="domain.pcrqq" myId="mainForm_domain_pcrqq" myClass="ymdate" />
	          			～
	          		<sys:dateCurrentDayTag myName="domain.pcrqz" myId="mainForm_domain_pcrqz" myClass="ymdate" />
		          </td>
		          <td colspan="4"></td>
			    </tr>
		        <tr>
		        	 <td align="right">司机姓名：</td>
		          <td><s:textfield name="domain.sjXm" cssClass="pop_input noborder" /></td>
	        	  <td align="right">车辆号码：</td>
		          	 <td><s:textfield name="domain.clhm4Query" cssClass="pop_input noborder" /></td>
		          
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
