<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>费用登记管理</title>
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
			
			$("#mainForm_domain_pcJgbm4Query").change(function(){
				$("[name='domain.fhrMc']").unbind();
				initHykhData(300, $(this).val(),$("#mainForm_domain_djJgbm4Query").val());
			});
			
			$("#mainForm_domain_djJgbm4Query").change(function(){
				$("[name='domain.fhrMc']").unbind();
				initHykhData(300, $("#mainForm_domain_pcJgbm4Query").val(), $(this).val());
			});
		
			//查询按钮事件
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//批量发送
			$("#plScSendBtn").click(function(){
				var wsDm="303001";//费用登记审批表
				plScSend(wsDm,"");
			});
			
			//初始化表格
			initDataGrid();
			var sjJgbm = $("#mainForm_domain_pcJgbm4Query").val();
			bmInit(sjJgbm, '', "domain.djJgbm4Query", "mainForm_domain_djJgbm4Query", "Y", "Y");
	});
	
	function onUpdate(fyDjxh,pcDjxh,ssJgbm,ddDjxh,xh,khDjxh,wfhDjxh,xybz){
		var xtcs20016 = $("#mainForm_domain_xtcs20016").val();
		if(xtcs20016=='Y'){
			if(xybz=='N'){
				showAlert("请先协议登记！");
				return;
			}
		}		
		var url = jcontextPath+"/hygl/fydjgl!initMx.action?domain.fyDjxh="+fyDjxh+"&domain.pcDjxh="+pcDjxh
					+"&domain.ssJgbm="+ssJgbm+"&domain.ddDjxh="+ddDjxh+"&domain.xh="+xh+"&domain.khDjxh="+khDjxh+"&domain.wfhDjxh="+wfhDjxh;
		window.showModalDialog(url,window,"dialogHeight:580px;dialogWidth:900px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
    	onRefresh();
	}
	var v1="";
	var v2="";
	function onDelete(fyDjxh,pcDjxh){
		v1=fyDjxh;
		v2=pcDjxh;
		showConfirm("确定要撤销吗 ？","doDelete");
	}
	
	function doDelete(){
		 var jsonObj = {"domain.fyDjxh":v1,"domain.pcDjxh":v2};
		 var url = jcontextPath+"/hygl/fydjgl!deleteFydj";   
         ajaxCommon(url,jsonObj , "deleteSuc");  
	}
	
	function deleteSuc(){  
		hideMessage();
        showAlert("撤销成功！");
        onRefresh();
	}	
	
	function onShow(fyDjxh,pcDjxh,selOrUpd){
		var url = jcontextPath+"/hygl/fydjgl!viewMx.action?domain.fyDjxh="+fyDjxh+"&domain.pcDjxh="+pcDjxh+"&domain.selOrUpd="+selOrUpd;
		window.showModalDialog(url,window,"dialogHeight:580px;dialogWidth:740px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
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
		var xtcsSfsp = $("#mainForm_domain_xtcsSfsp").val();
		if ("Y" == xtcsSfsp) {
			$("#dataList").jqGrid('showCol',["wsspztMc"]);
			$("#dataList").jqGrid('showCol',["checkboxoperationcol"]);
		}
		
		pcJgbm4Query = $("#mainForm_domain_pcJgbm4Query").val();
		djJgbm4Query = $("#mainForm_domain_djJgbm4Query").val();
		fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		fhrMc = $("#mainForm_domain_fhrMc").val();
		pcrqQ = $("#mainForm_domain_pcrqQ").val();
		pcrqZ = $("#mainForm_domain_pcrqZ").val();
		pcdh4Query = $("#mainForm_domain_pcdh4Query").val();
		clhm4Query = $("#mainForm_domain_clhm4Query").val();
		var zt = $(":input[name='domain.zt']:checked").val();
		//请求表格数据
		var url = jcontextPath+"/hygl/fydjgl!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.pcJgbm4Query":pcJgbm4Query,"domain.djJgbm4Query":djJgbm4Query,"domain.fhrDjxh":fhrDjxh,"domain.fhrMc":encodeURI(fhrMc),
		 			"domain.pcrqQ":pcrqQ,"domain.pcrqZ":pcrqZ,"domain.pcdh4Query":encodeURI(pcdh4Query),"domain.clhm4Query":encodeURI(clhm4Query),
		 			"domain.zt":zt}								//请求的参数，json格式
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
		    colNames:['序号','费用登记序号','派车登记序号',
		    			'文书审批序号','文书审批状态dm','wfhDjxh','派车单号',
		    			'操作','状态','订单编号', '回单编号','客户名称', 'ddDjxh','xh','khDJxh','xybz','货物名称','始发地', '目的地', '转入部门', 
		    			'结算数量', '发货地址', '收货地址','车辆号码', '挂车号码', '司机','派车人','派车日期', '派车部门','所属机构编码', '业务单位'],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[ 
			  {name:'pageXh', index:'pageXh', sortable:false, width:'30', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pageXh' + rowId + '\'';
			    }
			  },			  		 
			  {name:'fyDjxh', index:'fyDjxh', width:'80', align:'center', hidden:true},
			  {name:'pcDjxh', index:'pcDjxh', width:'80', align:'center', hidden:true},								  
			  {name:'wsSpxh', index:'wsSpxh', width:'80', align:'center', hidden:true},
			  {name:'wsspztDm', index:'wsspztDm', width:'80', align:'center', hidden:true},
			  {name:'wfhDjxh', index:'wfhDjxh', width:'80', align:'center', hidden:true},
			  {name:'pcdh', index:'pcdh', width:'70', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcdh' + rowId + '\'';
			    }
			  },			  
			  {name:'hstoperationcol', index:'hstoperationcol', sortable:false,width:'60', align:'center'},
			  {name:'djzt', index:'djzt', width:'40', hidden:true,align:'center'},		
			  {name:'ddbh', index:'ddbh', width:'70', align:'center'},	
			  {name:'hdbh', index:'hdbh', width:'70', align:'center'},		  	  				 
			  {name:'khmc', index:'khmc', width:'120', align:'center'},
			  {name:'ddDjxh', index:'ddDjxh', hidden:true,width:'120', align:'center'},
			  {name:'xh', index:'xh', hidden:true,width:'120', align:'center'},
			  {name:'khDjxh', index:'khDjxh', hidden:true,width:'120', align:'center'},
			  {name:'xybz', index:'xybz', hidden:true,width:'120', align:'center'},
			  {name:'hwmc', index:'hwmc', width:'80', align:'center'},
			  {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'50', align:'center'},
			  {name:'shrXzqhMc', index:'shrXzqhMc', width:'50', align:'center'},
			  {name:'zrbmMc', index:'zrbmMc', width:'100', align:'center'},
			  {name:'jsSl', index:'jsSl', width:'50', align:'center'},
			  {name:'fhrDz', index:'fhrDz', width:'100', align:'center'},
			  {name:'shrDz', index:'shrDz', width:'100', align:'center'},
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
		      {name:'pcrMc', index:'pcrMc', width:'50', align:'center',
		      	cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcrMc' + rowId + '\'';
			    }
		      }, 
		      {name:'pcrq', index:'pcrq', width:'70',align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
		      	cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcrq' + rowId + '\'';
			    }
		      },		      
		      {name:'pcJgmc', index:'pcJgmc', width:'80', align:'center',
		      	cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcJgmc' + rowId + '\'';
			    }
		      }, 
		      {name:'ssJgbm', index:'ssJgbm', width:'80',hidden:true, align:'center'},
		      {name:'ssJgmc', index:'ssJgmc', width:'80', align:'center',
		      	cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'ssJgmc' + rowId + '\'';
			    }
		      }
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum %>,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[10,20,50,100],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'FY_DJXH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/fydjgl!download");
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
                
                var val = jQuery("#dataList").jqGrid('getCell', cl,"fyDjxh"); 	  //获取当前单元格里面的登记序号 
                var pcDjxh = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh"); 
                var pcdh = jQuery("#dataList").jqGrid('getCell', cl,"pcdh"); 
                var wsSpxh = jQuery("#dataList").jqGrid('getCell', cl,"wsSpxh");
                var ssJgbm = jQuery("#dataList").jqGrid('getCell', cl,"ssJgbm");
                var ddbh = jQuery("#dataList").jqGrid('getCell', cl,"ddbh");
                var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh"); 
                var khDjxh = jQuery("#dataList").jqGrid('getCell', cl,"khDjxh");
                var xh = jQuery("#dataList").jqGrid('getCell', cl,"xh"); 
                var wfhDjxh = jQuery("#dataList").jqGrid('getCell', cl,"wfhDjxh");
                var xybz = jQuery("#dataList").jqGrid('getCell', cl,"xybz"); 
                var input ="";
                
                var strPc="<a href=\"javascript:onViewPc("+pcDjxh+")\"><font color=\"blue\">"+pcdh+"</font></a>";
                var str="<a href=\"javascript:onViewMx("+ddDjxh+")\"><font color=\"blue\">"+ddbh+"</font></a>";
                var djztStr = "<font color=\"red\">未登记</font>";
                var link = "<a href=\"javascript:onUpdate('"+val+"', '" + pcDjxh + "','"+ssJgbm+"','"+ddDjxh+"','"+xh+"','"+khDjxh+"','"+wfhDjxh+"','"+xybz+"')\"><font color=\"blue\">登记</font></a>" ;
                if (val != "") {
                	djztStr = "<font color=\"black\">已登记</font>";
                	link = "<a href=\"javascript:onUpdate('"+val+"', '" + pcDjxh + "','"+ssJgbm+"','"+ddDjxh+"','"+xh+"','"+khDjxh+"','"+wfhDjxh+"','"+xybz+"')\"><font color=\"blue\">登记</font></a>";
                }
                
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
                $("#dataList").jqGrid('setRowData', cl, { 'djzt': djztStr });
                $("#dataList").jqGrid('setRowData', cl, { 'ddbh': str });
                $("#dataList").jqGrid('setRowData', cl, { 'pcdh': strPc });
            }
            
		   var gridName = "dataList";
		   var a = ['pageXh','fyhj','pcdh','cyrClhm','cyrGchm','cyrSjxm','pcrMc','pcrq','pcJgmc','ssJgmc']; 		
		   Merger(gridName, 'pageXh', a);
   }
       
     /**************************************分页结束****************************************/
     
</script>
</head>
<body>
<s:form action="fydjgl!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
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
          			<td width="8%" align="right">业务单位：</td>
          			<td width="21%">
          				<sys:fgsList myId="mainForm_domain_pcJgbm4Query" myName="domain.pcJgbm4Query" mcContainDmBz="Y" myClass="select" onChange="bmInit(this.value, '', 'domain.djJgbm4Query', 'mainForm_domain_djJgbm4Query', 'Y', 'Y')" />
		  			</td>
		  			<td width="8%" align="right">派车部门：</td>
          			<td width="20%">
          				<select id="mainForm_domain_djJgbm4Query" name="domain.djJgbm4Query" class="select" >
          					<option value="${domain.djJgbm4Query }" selected="selected"></option>
          				</select>
		  			</td>
	        	  <td width="8%" align="right">派车日期：</td>
		          <td width="23%">
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
		          <td align="right">车辆号码：</td>
		          <td><s:textfield name="domain.clhm4Query" cssClass="pop_input noborder" /></td>
	        	  <td align="right">派车单号：</td>
		          <td><s:textfield name="domain.pcdh4Query" cssClass="pop_input noborder" /></td>
		        </tr>
		        <tr style="display: none;">
	        	  <td align="right">状态：</td>
		          <td>
		          	<s:radio name="domain.zt" list='#{"":"所有","1":"已登记","2":"未登记"}' listKey="key" listValue="value"></s:radio>
		          </td>
		          <td align="right"></td>
		          <td></td>
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