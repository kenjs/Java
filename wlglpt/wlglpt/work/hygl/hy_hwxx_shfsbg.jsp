<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>货运-货物信息-送货方式变更</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
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
	
	function onViewMx(ddDjxh) {
		var url = jcontextPath+"/jcgl/jctydgl!initMx.action?domain.ddDjxh="+ddDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    	//window.open(url);
	}
    
	function onQingdan(pcDjxh){
		var url = jcontextPath+"/jcgl/hypcxxgl!onQingdan.action?domain.pcDjxh="+pcDjxh;
		window.open(url,"_blank")
		//navigateMenu(url,'派车清单','true');
	}
	
	 function onViewPc(pcDjxh){
	    var url = jcontextPath+"/jcgl/jcpcxxgl!initMx.action?domain.pcDjxh="+pcDjxh;
	    window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
	}
	function onUpdateShfs(zs,pcDjxh,ddDjxh,xh,wfhDjxh){
		var shBz;
		if(zs == '自提'){
			shBz = 1;
		}else{
			shBz = 2;
		}
		var url = jcontextPath+"/hygl/hyhwxxshfsbg!initMx.action?domain.pcDjxh="+pcDjxh+"&domain.ddDjxh="+ddDjxh+"&domain.xh="+xh+"&domain.wfhDjxh="+wfhDjxh+"&domain.shBz="+shBz;
	    window.showModalDialog(url,window,"dialogHeight:560px;dialogWidth:920px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
	}
	function onViewShfs(zs,pcDjxh,ddDjxh,xh,wfhDjxh){
		var shBz;
		if(zs == '自提'){
			shBz = 1;
		}else{
			shBz = 2;
		}
		var url = jcontextPath+"/hygl/hyhwxxshfsbg!viewMx.action?domain.pcDjxh="+pcDjxh+"&domain.ddDjxh="+ddDjxh+"&domain.xh="+xh+"&domain.wfhDjxh="+wfhDjxh+"&domain.shBz="+shBz;
	    window.showModalDialog(url,window,"dialogHeight:560px;dialogWidth:920px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
	}
	function onDeleteShfs(zs,pcDjxh,ddDjxh,xh,wfhDjxh){
		var shBz;
		if(zs == '自提'){
			shBz = 1;
		}else{
			shBz = 2;
		}
	   var url = jcontextPath+"/hygl/hyhwxxshfsbg!delete";  
	   var jsonObj = {"domain.ddDjxh":ddDjxh,"domain.pcDjxh":pcDjxh,"domain.xh":xh,"domain.wfhDjxh":wfhDjxh,"domain.shBz":shBz};  			
	   ajaxCommon(url,jsonObj,"deleteSuc");
	}
	function deleteSuc(){
		onRefresh();
	}
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		pcJgbm = $("#mainForm_domain_pcJgbm").val();
		pcrCzyDjxh = $("#mainForm_domain_pcrCzyDjxh").val();
		fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		pcrqq = $("#mainForm_domain_pcrqq").val();
		pcrqz = $("#mainForm_domain_pcrqz").val(); 
		if (pcrqq == "" || pcrqz == "") {
			showAlert("派车日期不能为空！");
			return;
		}
		cyrClhm = '';//$("#mainForm_domain_cyrClhm").val();
		cyrSjxm = '';//$("#mainForm_domain_cyrSjxm").val();
		fhrMc = $("#mainForm_domain_fhrMc").val();
		pcdh = $("#mainForm_domain_pcdh").val();
		dingdan = '';//$("#mainForm_domain_dingdan").val();
	
		
		//请求表格数据
		var url = jcontextPath+"/hygl/hyhwxxshfsbg!query.action";
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.pcxxDomain.ssJgbm":ssJgbm,"domain.pcxxDomain.pcJgbm":pcJgbm, "domain.pcxxDomain.pcrCzyDjxh":encodeURI(pcrCzyDjxh),"domain.pcxxDomain.pcrqq":pcrqq,
		 			"domain.pcxxDomain.pcrqz":pcrqz, "domain.pcxxDomain.cyrClhm":encodeURI(cyrClhm),"domain.pcxxDomain.cyrSjxm":encodeURI(cyrSjxm),"domain.pcxxDomain.fhrMc":encodeURI(fhrMc),"domain.pcxxDomain.pcdh":encodeURI(pcdh),
		 			"domain.pcxxDomain.dingdan":encodeURI(dingdan),"domain.pcxxDomain.fhrDjxh":fhrDjxh
		 			}								//请求的参数，json格式
		 }
		 ).trigger("reloadGrid");		//显示到第一页，以免总体数据发生变化的时候，分页的信息混乱
	}
	
	<% 
	    //获取每个用户的每页显示参数值
		UserDomain userDomain=(UserDomain) request.getSession().getAttribute(WebConstants.SES_USER_INFO);
		String rowNum = "50";
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
		    colNames:['序号','派车登记序号','文书审批序号','审批','审批状态代码',
		              '派车单号', '派车日期','类别', '装车', '车辆号码', '挂车号码', 
		              '司机', '运费/预付', '收入/到付','重量','体积',
		              '操作', '订单编号','客户名称','ddDjxh','xh','wfhDjxh','shbgBz','货物名称','送货', '始发地', '目的地', '结算数量', '数量', 
		              '重量', '体积','包装','处理方式','下游单位','合计','现付','月结','到付','回扣','发货地址', '要求发货日期', '收货人','收货地址',
		               '要求到达日期', '派车人', '派车部门', '所属机构'],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[
			  {name:'pageXh', index:'pageXh', sortable:false, width:'30', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pageXh' + rowId + '\'';
			    }
			  },
			  {name:'pcDjxh', index:'pcDjxh',hidden:true, width:'80', align:'center'},
			  {name:'wsSpxh', index:'wsSpxh',hidden:true, width:'80', align:'center'},
			  {name:'wsspztMc', index:'wsspztMc', hidden:true,width:'45', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'wsspztMc' + rowId + '\'';
			    }
			  },
			  {name:'wsspztDm', index:'wsspztDm', hidden:true,width:'80', align:'center'},
			  {name:'pcdh', index:'pcdh', width:'65', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcdh' + rowId + '\'';
			    }
			  },
			  {name:'pcrq', index:'pcrq', width:'65', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'pcrq' + rowId + '\'';
				    }
				  },
			  {name:'pcfsMc', index:'pcfsMc', width:'35', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcfsMc' + rowId + '\'';
			    }
			  },
			  {name:'zcfxMc', index:'zcfxMc', width:'40', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'zcfxMc' + rowId + '\'';
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
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
			  {name:'dingdan', index:'dingdan', width:'80', align:'center'},
			  {name:'fhrMc', index:'fhrMc', width:'120', align:'center'},
			  {name:'ddDjxh', index:'ddDjxh',hidden:true, width:'120', align:'center'},
			  {name:'xh', index:'xh',hidden:true, width:'120', align:'center'},
			  {name:'wfhDjxh', index:'wfhDjxh',hidden:true, width:'120', align:'center'},
			  {name:'shbgBz', index:'shbgBz',hidden:true, width:'120', align:'center'},
			  {name:'hwMc', index:'hwMc', width:'100', align:'center'},
			  {name:'zs', index:'zs', width:'40', align:'center'},
		      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'40', align:'center'},
		      {name:'shrXzqhMc', index:'shrXzqhMc', width:'40', align:'center'},
		      {name:'jssl', index:'jssl',hidden:true, width:'60', align:'right'},
		      {name:'sl', index:'sl', width:'50', align:'right'},  
		      {name:'zl', index:'zl', width:'50', align:'right'},
		      {name:'tj', index:'tj', width:'50', align:'right'},
		      {name:'hwbz', index:'hwbz', width:'50', align:'center'},
		      {name:'pchwClfsDm', index:'pchwClfsDm', width:'60', align:'center',hidden:true},
		      {name:'zrbmMc', index:'zrbmMc', width:'100', align:'center'},
			  {name:'zcHj', index:'zcHj', width:'40', align:'center'},
			  {name:'zcXf', index:'zcXf', width:'40', align:'center'},
			  {name:'zcYj', index:'zcYj', width:'40', align:'center'},
			  {name:'zcDf', index:'zcDf', width:'40', align:'center'},
			  {name:'zcHk', index:'zcHk', width:'40', align:'center'},
		      {name:'sfdMc', index:'sfdMc', width:'200', align:'center'},
		      {name:'yqFhrq', index:'yqFhrq', width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      {name:'shrMc', index:'shrMc', width:'100', align:'center'},
		      {name:'mddMc', index:'mddMc', width:'200', align:'center'},
		      {name:'yqDdrq', index:'yqDdrq', width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      
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
		    rowNum: 10,							//在grid上显示记录条数，这个参数是要被传递到后台
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
		  
		 
	}
	
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            var xtcs20201 = $("#mainForm_domain_xtcs20201").val();
    		
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var pcDjxh = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh"); 
                var pcdh = jQuery("#dataList").jqGrid('getCell', cl,"pcdh");
                var wsSpxh = jQuery("#dataList").jqGrid('getCell', cl,"wsSpxh");
               
                var ddbh = jQuery("#dataList").jqGrid('getCell', cl,"dingdan");
                var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh"); 
                var xh = jQuery("#dataList").jqGrid('getCell', cl,"xh"); 
                var wfhDjxh = jQuery("#dataList").jqGrid('getCell', cl,"wfhDjxh"); 
                var shbgBz = jQuery("#dataList").jqGrid('getCell', cl,"shbgBz"); 
                var zs = jQuery("#dataList").jqGrid('getCell', cl,"zs"); 
				var link;
				//alert(shbgBz);
				if(shbgBz/1>0){
					link= "<a href=\"javascript:onViewShfs('"+zs+"',"+pcDjxh+","+ddDjxh+","+xh+","+wfhDjxh+")\"><font color=\"blue\">查看</font></a>"
					     + " <a href=\"javascript:onDeleteShfs('"+zs+"',"+pcDjxh+","+ddDjxh+","+xh+","+wfhDjxh+")\"><font color=\"blue\">撤销</font></a>";
				}else{
					if(zs == '自提'){
						link= "<a href=\"javascript:onUpdateShfs('"+zs+"',"+pcDjxh+","+ddDjxh+","+xh+","+wfhDjxh+")\"><font color=\"blue\">改送货</font></a>";
					}else{
					    link= "<a href=\"javascript:onUpdateShfs('"+zs+"',"+pcDjxh+","+ddDjxh+","+xh+","+wfhDjxh+")\"><font color=\"blue\">改自提</font></a>";
					}
				}

				//alert(link);
				var str="<a href=\"javascript:onViewMx("+ddDjxh+")\"><font color=\"blue\">"+ddbh+"</font></a>";
				var strPc="<a href=\"javascript:onViewPc("+pcDjxh+")\"><font color=\"blue\">"+pcdh+"</font></a>";
				
				if ("Y" == xtcs20201) {
					var wsspztDm = jQuery("#dataList").jqGrid('getCell', cl,"wsspztDm");
					var spLink = '<input type="checkbox" name="xhs" value="'+pcDjxh+'#'+wsSpxh+'" />';
					if ("1" == wsspztDm || "3" == wsspztDm) {
						spLink = '<input type="checkbox" name="xhs" value="'+pcDjxh+'#'+wsSpxh+'" disabled="disabled" />';
					}
					
					$("#dataList").jqGrid('setRowData', cl, { 'fsspCheck': spLink });
	    		}
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link });
                $("#dataList").jqGrid('setRowData', cl, { 'dingdan': str });
                $("#dataList").jqGrid('setRowData', cl, { 'pcdh': strPc });
            }
            
            var gridName = "dataList";
     	    var a = ['pageXh','pcdh','fsspCheck','wsspztMc','pcfsMc','zcfxMc','cyrClhm','cyrGchm','cyrSjxm',
     	            'yfhjyf','srhjdf','pcrMc','pcrq','pcJgbmMc','ssJgbmMc','zcZl','zcTj'
     	            ];
      		
            Merger(gridName, 'pcDjxh', a);
     }
	
    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接

       
   	
     /**************************************分页结束****************************************/
</script>
</head>
<body>
<s:form action="hypcxxgl!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.xtcs20201" />
	<s:hidden name="jsonData" />
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">操作：</li>
		    <li><a href="#" id="queryBtn" class="licon04">检 索</a></li>
		    <s:if test='domain.xtcs20201 == "Y"'>
		    <li><a href="#" id="plScSendBtn" class="licon10">批量发送审批</a></li>
		    </s:if>
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
		        	<td width="8%" align="right">业务单位：</td>
          			<td width="21%">
          				<sys:gsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y" myClass="select" onChange="bmInit(this.value, '', 'domain.pcJgbm', 'mainForm_domain_pcJgbm', 'Y', 'Y')" />
		  			</td>
		  			<td width="8%" align="right">派车部门：</td>
          			<td width="21%">
          				<select id="mainForm_domain_pcJgbm" name="domain.pcJgbm" class="select" >
          					<option value="${domain.pcxxDomain.pcJgbm }" selected="selected"></option>
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
		          <td><s:textfield name="domain.pcdh" cssClass="pop_input noborder" /></td>
		          <td align="right">派车日期：</td>
		          <td >
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
