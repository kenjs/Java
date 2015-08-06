<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>货运-派车-物流损失登记</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>

<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
			
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		initHykhData(300, $("#mainForm_domain_pcJgbm4Query").val(), $("#mainForm_domain_djJgbm4Query").val());
		
		$("#mainForm_domain_pcJgbm4Query").change(function(){
			initHykhData(300, $(this).val(), $("#mainForm_domain_djJgbm4Query").val());
		});
		
		$("#mainForm_domain_djJgbm4Query").change(function(){
			$("[name='domain.fhrMc']").unbind();
			initHykhData(300, $("#mainForm_domain_pcJgbm4Query").val(), $(this).val());
		});
		
			//查询按钮事件
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
		
			var sjJgbm = $("#mainForm_domain_dwDm").val();
			bmInit(sjJgbm, '', "domain.pcbm4Query", "mainForm_domain_pcbm4Query", "Y", "Y");
			//初始化表格
			initDataGrid();
					

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
 
    
 
	

	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
	var	pcbm4Query = $("#mainForm_domain_pcbm4Query").val();
	var	pcrqQ = $("#mainForm_domain_pcrqQ").val();
	var	pcrqZ = $("#mainForm_domain_pcrqZ").val();
	var	clhm4Query = $("#mainForm_domain_clhm4Query").val();
	var fhrDjxh= $("#mainForm_domain_fhrDjxh").val(); 
	var fhrMc= $("#mainForm_domain_fhrMc").val(); 
	var	pcdh4Qyuery = $("#mainForm_domain_pcdh4Query").val();
	var dwDm = $("#mainForm_domain_dwDm").val();
	var dwbmBz4Query='';
	var pcJgbm4Query='';
	if(pcbm4Query!=null&&pcbm4Query!=''){
		dwbmBz4Query='B';
		pcJgbm4Query=pcbm4Query;
	}else{
		dwbmBz4Query='D';
		pcJgbm4Query=dwDm;
	}
		//请求表格数据
		var url = jcontextPath+"/hygl/wlssdj!query.action";   
	
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.pcrqQ":pcrqQ,"domain.pcrqZ":pcrqZ,
		 		"domain.clhm4Query":encodeURI(clhm4Query),"domain.fhrDjxh":fhrDjxh,"domain.fhrMc4Query":encodeURI(fhrMc),
		 		"domain.pcdh4Query":encodeURI(pcdh4Qyuery),"domain.dwbmBz4Query":dwbmBz4Query,"domain.pcJgbm4Query":pcJgbm4Query}								//请求的参数，json格式
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
		    colNames:['序号','操作','派车单号','车辆号码','挂车号码','司机',
		             '','订单编号','客户名称','货物名称','始发地','目的地','转入部门','结算数量','发货地址'
		              ,'收货地址','派车人','派车日期','派车部门','业务单位'],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[
	
		      {name:'pageXh', index:'pageXh', width:'35', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pageXh' + rowId + '\'';
				    }
			   },
			  {name:'hstoperationcol', index:'hstoperationcol', width:'50', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'hstoperationcol' + rowId + '\'';
				    }
			   },
			   {name:'pcdh', index:'pcdh', width:'70', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcdh' + rowId + '\'';
				    }
			   },
			   {name:'cyrClhm', index:'cyrClhm', width:'60', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'cyrClhm' + rowId + '\'';
				    }
			   },
			   {name:'cyrGchm', index:'cyrGchm', width:'60', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'cyrGchm' + rowId + '\'';
				    }
			   },
			   {name:'cyrSjxm', index:'cyrSjxm', width:'50', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'cyrSjxm' + rowId + '\'';
				    }
			   },
			  {name:'pcDjxh', index:'pcDjxh',hidden:true, width:'0', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcDjxh' + rowId + '\'';
				    }
			   },
			 
			  {name:'ddbh', index:'ddbh', sortable:false, width:'60', align:'center'},
			  {name:'khmc', index:'khmc', sortable:false, width:'120', align:'center'},
			  {name:'hwmc', index:'hwmc', sortable:false, width:'100', align:'center'},
			  {name:'fhrXzqhMc', index:'fhrXzqhMc', sortable:false, width:'50', align:'center'},
			  {name:'shrXzqhMc', index:'shrXzqhMc', sortable:false, width:'50', align:'center'},
			  {name:'zrbmMc', index:'zrbmMc', sortable:false, width:'100', align:'center'},
			  {name:'jsSl', index:'jsSl', sortable:false, width:'60', align:'right'},
			  {name:'fhrDz', index:'fhrDz', sortable:false, width:'120', align:'center'},
			  {name:'shrDz', index:'shrDz', sortable:false, width:'120', align:'center'},
			  {name:'pcrMc', index:'pcrMc', width:'70', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcrMc' + rowId + '\'';
				    }
			   },
			   {name:'pcrq', index:'pcrq', width:'80', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcrq' + rowId + '\'';
				    }
			   },
			   {name:'pcJgmc', index:'pcJgmc', width:'100', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcJgmc' + rowId + '\'';
				    }
			   },
			   {name:'ssJgmc', index:'ssJgmc',width:'100', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'ssJgmc' + rowId + '\'';
				    }
			   }
			 
		  
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum%>,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[20,50,100,300,500],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
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
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh"); 	  //获取当前单元格里面的登记序号 
               
                var link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">登记</font></a>";
        
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link });
           
            }
            var gridName = "dataList";
	   		var a = ['hstoperationcol','pageXh','pcdh','cyrClhm','cyrGchm','cyrSjxm','pcDjxh','pcrMc','pcrq','pcJgmc','ssJgmc'];
 		
       		Merger(gridName, 'pcDjxh', a);
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
		   <table width="99%" border="0" cellspacing="0" cellpadding="0">
		        <tr>
		           <td width="8%" align="right">业务单位：</td>
		          <td width="19%">	<sys:gsList myId="mainForm_domain_dwDm" myName="domain.dwDm" mcContainDmBz="Y" myClass="select" onChange="bmInit(this.value, '', 'domain.pcbm4Query', 'mainForm_domain_pcbm4Query', 'Y', 'Y')"/></td>
		          <td width="8%" align="right">派车部门：</td>
		          <td width="19%"><sys:gsBmList myName="domain.pcbm4Query" myId="mainForm_domain_pcbm4Query"  contaisQxz="false" myClass="select" /></td>
		        	 <td width="8%" align="right">派车日期：</td>
		          <td width="28%">
		             	<sys:dateFirstDLastMonthTag myName="domain.pcrqQ" myId="mainForm_domain_pcrqQ" myClass="ymdate" />
		          			～
		          		<sys:dateCurrentDayTag myName="domain.pcrqZ" myId="mainForm_domain_pcrqZ" myClass="ymdate" />
		          </td>
		        </tr>
		        <tr>
		        	<td align="right">客户名称：</td>
		            <td>
			           <s:hidden name="domain.fhrDjxh"></s:hidden>
	  					<div class="inputsel" style="width: 230px; ">
	  						<s:textfield name="domain.fhrMc" cssClass="pop_input noborder inputext bgstyle_optional" cssStyle="width:200px;"></s:textfield>
	  						<a href="#" class="icon_arrow" id="fhr" onFocus="this.blur()"></a>
	  					</div>
				  		<div class="inputsc">
			              <div id="inputSel_fhr" class="inputselcont inputselFixedSize ac_results" style="width: 230px; ">
			              </div>
			            </div>
	          		</td>
		        	<td align="right">派车单号：</td>
		         	<td><s:textfield name="domain.pcdh4Query" cssClass="pop_input noborder" /></td>
		        	<td align="right">车辆号码：</td>
		        	<td><s:textfield name="domain.clhm4Query" cssClass="pop_input noborder" /></td>	
		        </tr>
		        <tr>	        	  
		        
		        </tr>
		   </table>
		</fieldset>
	  </div>
	  </div>
		<!-- 分页表格 id必须为dataList -->
		<table id="dataList"><tr><td/></tr></table> 
		<!-- 分页导航 -->
		<div id="pager"></div>
		
		
		<%@include file="/common/message.jsp" %>
</s:form>
</body>
</html>
