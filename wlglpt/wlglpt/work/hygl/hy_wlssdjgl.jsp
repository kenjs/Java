<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>货运-物流损失登记</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>

<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
			
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		initHykhData(300, $("#mainForm_domain_pcJgbm4Query").val());
		
		$("#mainForm_domain_dwDm").change(function(){
			$("[name='domain.fhrMc']").unbind();
			initHykhData(300, $(this).val(), $("#mainForm_domain_pcbm4Query").val());
		});
		
		$("#mainForm_domain_pcbm4Query").change(function(){
			$("[name='domain.fhrMc']").unbind();
			initHykhData(300, $("#mainForm_domain_dwDm").val(), $(this).val());
		});
		
		//查询按钮事件
		$("#queryBtn").click(function(){
			onRefresh();
		});
		
		//批量发送
		$("#plScSendBtn").click(function(){
			var wsDm="303002";//费用登记审批表
			plScSend(wsDm,"");
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
	
    function onUpdate(pcDjxh,wlDjxh,wfhDjxh,ddDjxh,hwmxxh,khDjxh,xybz){
    	//alert(pcdjxh+"--"+ddDjxh+"--"+hwmxxh+"--"+wfhDjxh)
    	var xtcs20016 = $("#mainForm_domain_xtcs20016").val();
		if(xtcs20016=='Y'){
			if(xybz=='N'){
				showAlert("请先协议登记！")
				return;
			}
		}
    	var url = jcontextPath+"/hygl/wlssdj!initSsMx.action?domain.pcDjxh="+pcDjxh+"&domain.wlssDjxh="+wlDjxh+"&domain.wfhDjxh="+wfhDjxh
    			+"&domain.ddDjxh="+ddDjxh+"&domain.xh="+hwmxxh+"&domain.wlssLybz=0"+"&number="+Math.random();
    	window.showModalDialog(url,window,"dialogHeight:760px;dialogWidth:900px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
    	onRefresh();
    }
    
    function onLook(pcDjxh,wlDjxh){
    	var url = jcontextPath+"/hygl/wlssdj!toLook.action?domain.pcDjxh="+pcDjxh+"&domain.wlssDjxh="+wlDjxh;
    	window.showModalDialog(url,window,"dialogHeight:405px;dialogWidth:675px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
    	onRefresh();
    }
    
    function onViewMx(ddDjxh) {
		var url = jcontextPath+"/jcgl/jctydgl!initMx.action?domain.ddDjxh="+ddDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    	//window.open(url);
	}
    
      
    function onViewPc(pcDjxh){
    	var url = jcontextPath+"/jcgl/jcpcxxgl!initMx.action?domain.pcDjxh="+pcDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    }
    
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		/*var xtcsSfsp = $("#mainForm_domain_xtcsSfsp").val();
		if ("Y" == xtcsSfsp) {
			$("#dataList").jqGrid('showCol',["wsspztMc"]);
			$("#dataList").jqGrid('showCol',["checkboxoperationcol"]);
		}*/
		
		var dwDm = $("#mainForm_domain_dwDm").val();
		var	pcbm4Query = $("#mainForm_domain_pcbm4Query").val();
		var	pcrqQ = $("#mainForm_domain_pcrqQ").val();
		var	pcrqZ = $("#mainForm_domain_pcrqZ").val();
		var fhrDjxh= $("#mainForm_domain_fhrDjxh").val(); 
		var fhrMc= $("#mainForm_domain_fhrMc").val(); 
		var	pcdh4Qyuery = $("#mainForm_domain_pcdh4Query").val();
		var	clhm4Query = $("#mainForm_domain_clhm4Query").val();
		var zt = $(":input[name='domain.zt']:checked").val();
		//请求表格数据
		var url = jcontextPath+"/hygl/wlssdjgl!query.action";   
	
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.dwDm":dwDm,"domain.pcbm4Query":pcbm4Query,"domain.pcrqQ":pcrqQ,"domain.pcrqZ":pcrqZ,
		 		"domain.fhrDjxh":fhrDjxh,"domain.fhrMc":encodeURI(fhrMc),"domain.clhm4Query":encodeURI(clhm4Query),
		 		"domain.pcdh4Query":encodeURI(pcdh4Qyuery),"domain.zt":zt}								//请求的参数，json格式
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
		    colNames:['序号','派车单号','物流损失序号','<input title="选中/取消选中" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />',
		    		'操作','派车登记序号','状态','文书审批序号','文书审批状态dm','审核',
		    		
		    		'订单编号','回单编号','xybz','客户名称','货物名称','始发地','目的地','转入部门','结算数量','发货地址','收货地址',//'金额','损失原因',
		    		'车辆号码','挂车号码','司机','运费','预付',
		    		
		           '派车人','派车日期','派车部门','业务单位','未发货登记序号','订单','货物明细','客户'],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[
				{name:'pageXh', index:'pageXh', width:'35', sortable:false,align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pageXh' + rowId + '\'';
				    }
				},
				{name:'pcdh', index:'pcdh', width:'70', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcdh' + rowId + '\'';
				    }
			   },
			  {name:'wlssDjxh', index:'wlssDjxh', sortable:false,hidden:true, width:'80', align:'center'},
			  {name:'checkboxoperationcol', index:'checkboxoperationcol',hidden:true, sortable:false, width:'20', align:'center'},
			  {name:'hstoperationcol', index:'hstoperationcol', width:'60', align:'center'},
		   
			  {name:'pcDjxh', index:'pcDjxh', width:'80', align:'center', hidden:true},					
			  {name:'djzt', index:'djzt', width:'40', align:'center', hidden:true},
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
			  {name:'wsspztMc', index:'wsspztMc', width:'50', hidden:true,align:'center'},
			 
			   {name:'ddbh', index:'ddbh', width:'70', align:'center'},
			   {name:'hdbh', index:'hdbh', width:'70', align:'center'},
			   {name:'xybz', index:'xybz', width:'70', align:'center',hidden:true},
			   {name:'khmc', index:'khmc', width:'120', align:'center'},
			   {name:'hwMc', index:'hwMc', sortable:false, width:'100', align:'center'},
				{name:'fhrXzqhMc', index:'fhrXzqhMc', sortable:false, width:'50', align:'center'},
				{name:'shrXzqhMc', index:'shrXzqhMc', sortable:false, width:'50', align:'center'},
				{name:'zrbmMc', index:'zrbmMc', sortable:false, width:'100', align:'center'},
			    {name:'jsSl', index:'jsSl', sortable:false, width:'60', align:'right'},
				{name:'fhrDz', index:'fhrDz', sortable:false, width:'150', align:'center'},
				{name:'shrDz', index:'shrDz', sortable:false, width:'150', align:'center'},
				//{name:'je', index:'je', width:'70', align:'center'},
				//{name:'wlssyyMc', index:'wlssyyMc', width:'70', align:'center'},
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
			   {name:'yfHj', index:'yfHj', width:'50', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'yfHj' + rowId + '\'';
			    }
			  },
			  {name:'yfYfYf', index:'yfYfYf', width:'40', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'yfYfYf' + rowId + '\'';
			    }
			  },
			 
			
			    {name:'pcrMc', index:'pcrMc', width:'70', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcrMc' + rowId + '\'';
				    }
			   },
			   {name:'pcrQ', index:'pcrQ', width:'80', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcrQ' + rowId + '\'';
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
			   },
				{name:'wfhDjxh', index:'wfhDjxh', sortable:false, width:'0',hidden:true, align:'center'},
				{name:'ddDjxh', index:'ddDjxh', sortable:false, width:'0',hidden:true, align:'center'},
				{name:'hwmxxh', index:'hwmxxh', sortable:false, width:'0',hidden:true, align:'center'},
				{name:'khDjxh', index:'khDjxh', sortable:false, width:'0',hidden:true, align:'center'}
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/wlssdjgl!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            var xtcsSfsp = $("#mainForm_domain_xtcsSfsp").val();
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh"); 	  //获取当前单元格里面的登记序号 
                var wlssDjxh = jQuery("#dataList").jqGrid('getCell', cl,"wlssDjxh"); 
                var wsSpxh = jQuery("#dataList").jqGrid('getCell', cl,"wsSpxh"); 
                var wfhDjxh = jQuery("#dataList").jqGrid('getCell', cl,"wfhDjxh"); 
                var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh"); 
                var hwmxxh = jQuery("#dataList").jqGrid('getCell', cl,"hwmxxh"); 
                var khDjxh = jQuery("#dataList").jqGrid('getCell', cl,"khDjxh"); 
                var ddbh = jQuery("#dataList").jqGrid('getCell', cl,"ddbh");
                var pcdh = jQuery("#dataList").jqGrid('getCell', cl,"pcdh");
                var xybz = jQuery("#dataList").jqGrid('getCell', cl,"xybz");
                var djztStr = "<font color=\"red\">未登记</font>";
                var strPc="<a href=\"javascript:onViewPc("+val+")\"><font color=\"blue\">"+pcdh+"</font></a>";
                var str="<a href=\"javascript:onViewMx("+ddDjxh+")\"><font color=\"blue\">"+ddbh+"</font></a>";
                var link = "<a href=\"javascript:onUpdate('"+val+"','" + wlssDjxh + "','"+wfhDjxh+"','"+ddDjxh+"','"+hwmxxh+"','"+khDjxh+"','"+xybz+"')\"><font color=\"blue\">登记</font></a>" ;
                if (wlssDjxh != "") {
                	djztStr = "已登记";
                	link = "<a href=\"javascript:onUpdate('"+val+"','" + wlssDjxh + "','"+wfhDjxh+"','"+ddDjxh+"','"+hwmxxh+"','"+khDjxh+"','"+xybz+"')\"><font color=\"blue\">修改</font></a>"
                		 	+ " <a href=\"javascript:onDelete('"+wlssDjxh+"')\"><font color=\"blue\">撤销</font></a>";
                }
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link });
                $("#dataList").jqGrid('setRowData', cl, { 'djzt': djztStr });
                $("#dataList").jqGrid('setRowData', cl, { 'ddbh': str });
                $("#dataList").jqGrid('setRowData', cl, { 'pcdh': strPc });
        		if ("Y" == xtcsSfsp) {
                	var wsspztDm = jQuery("#dataList").jqGrid('getCell', cl,"wsspztDm");
                	if("0"==wsspztDm || "2"==wsspztDm){
                    	input="<input type=\"checkbox\" name=\"xhs\" value=\""+wlssDjxh+"#"+wsSpxh+"\" />";
                    }else{
                    	input="<input type=\"checkbox\" name=\"xhs\" disabled=\"disabled\" value=\""+wlssDjxh+"#"+wsSpxh+"\" />";
                    }
                	$("#dataList").jqGrid('setRowData', cl, { 'checkboxoperationcol': input });
                }
            }
            var gridName = "dataList";
	   		var a = ['pageXh',
	   		         ,'yfHj','yfYfYf',
	   		         'pcdh','cyrClhm','cyrGchm','cyrSjxm','pcDjxh','pcrMc','pcrQ','pcJgmc','ssJgmc'];
 		
       		Merger(gridName, 'pcDjxh', a);
     }
     /**************************************分页结束****************************************/

</script>
</head>
<body>
<s:form action="wlssdjgl!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.xtcsSfsp" />
	<s:hidden name="domain.xtcs20016" />
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
		           <td width="8%" align="right">派车单位：</td>
		          <td width="21%">
		          	<sys:gsList myId="mainForm_domain_dwDm" myName="domain.dwDm" mcContainDmBz="Y" myClass="select" onChange="bmInit(this.value, '', 'domain.pcbm4Query', 'mainForm_domain_pcbm4Query', 'Y', 'Y')"/>
		          </td>
		          <td width="8%" align="right" >派车部门：</td>
		          <td width="21%"><sys:gsBmList myName="domain.pcbm4Query" myId="mainForm_domain_pcbm4Query"  contaisQxz="false" myClass="select" /></td>
		          <td width="8%" align="right">派车日期：</td>
		          <td width="25%">
		             	<sys:dateFirstDMonth myName="domain.pcrqQ" myId="mainForm_domain_pcrqQ" myClass="ymdate" />
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
	        	  <td align="right" style="display: none;">状态：</td>
		          <td style="display: none;">
		          	<s:radio name="domain.zt" list='#{"":"所有","1":"已登记","2":"未登记"}' listKey="key" listValue="value"></s:radio>
		          </td>
		          <td align="right"></td>
		          <td></td>
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
