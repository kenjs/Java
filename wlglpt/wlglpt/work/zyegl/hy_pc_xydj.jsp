<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>货运-派车-协议登记</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
		document.getElementById("maincont").onmousewheel=hideHelpWindow;
		initHykhData(300, $("#mainForm_domain_ssJgbm").val(), $("#mainForm_domain_pcJgbm").val());
		
		$("#mainForm_domain_ssJgbm").change(function(){
			$("[name='domain.fhrMc']").unbind();
			initHykhData(300, $(this).val(), $("#mainForm_domain_pcJgbm").val());
		});
		
		$("#mainForm_domain_pcJgbm").change(function(){
			initRy();
			$("[name='domain.fhrMc']").unbind();
			initHykhData(300, $("#mainForm_domain_ssJgbm").val(), $(this).val());
		});
		
		//查询按钮事件
		$("#queryBtn").click(function(){
			onRefresh();
		});
		
		//批量发送
		$("#plScSendBtn").click(function(){
			var wsDm="303003";//费用登记审批表
			plScSend(wsDm,"");
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
	
	function onXydj(pcDjxh) {
		var xtcs20016 = $("#mainForm_domain_xtcs20016").val();	
		if(xtcs20016=="N"){
			showAlert("本企业不需要协议登记！");
			return;
		}
		var url = jcontextPath+"/zyegl/pcxydj!initXydj.action?domain.pcDjxh="+pcDjxh+"&num="+Math.random();
    	window.showModalDialog(url,window,"dialogHeight:430px;dialogWidth:600px;center:yes;resizable:no;status:no;scroll:no;help:no;minimize:yes;")
    	//window.open(url);
    	onRefresh();
	}
	
	function onXydjHwmx(pcDjxh,wfhDjxh,xysl) {
		if (xysl/1 > 0) {
			editFlag = "Y";
		}else {
			editFlag = "N";
		}
		var url = jcontextPath+"/zyegl/pcxydj!initHwxxXydj.action?domain.hwmxDomain.pcDjxh="+pcDjxh
				+"&domain.hwmxDomain.wfhDjxh="+wfhDjxh+"&domain.hwmxDomain.editFlag="+editFlag+"&num="+Math.random();
    	window.showModalDialog(url,window,"dialogHeight:450px;dialogWidth:635px;center:yes;resizable:no;status:no;scroll:no;help:no;minimize:yes;")
    	//window.open(url);
    	onRefresh();
	}
	
	var pcDjxhGolbal;
	function onDelXydj(pcDjxh) {
		pcDjxhGolbal = pcDjxh;
		showConfirm("确定要撤销吗？","doDelXydj");
	}
	
	function doDelXydj() {
		 var jsonObj = {"domain.pcDjxh":pcDjxhGolbal};
		 var url = jcontextPath+"/zyegl/pcxydj!delete";   
         ajaxCommon(url,jsonObj , "doDelSuc");  
	}
	function onShowXybd(pcDjxh) {
		//alert(wfhDjxh);
		var url = jcontextPath+"/zyegl/pcxydj!initXybd.action?domain.pcDjxh="+pcDjxh;
    	window.showModalDialog(url,window,"dialogHeight:600px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:no;help:no;minimize:yes;")
    	onRefresh();
	}
    function doDelSuc(){     
        showAlert("撤销成功！");
        onRefresh();
	}	
    
    function onViewMx(ddDjxh) {
    	//alert(ddDjxh);
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
		var xtcs20206 = $("#mainForm_domain_xtcs20206").val();
		if ("Y" == xtcs20206) {
			$("#dataList").jqGrid('showCol',["wsspztMc"]);
			$("#dataList").jqGrid('showCol',["fsspCheck"]);
		}
		
		var zt = $("input[name='domain.zt']:checked").val();
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		pcJgbm = $("#mainForm_domain_pcJgbm").val();
		pcrCzyDjxh = $("#mainForm_domain_pcrCzyDjxh").val();
		pcrqq = $("#mainForm_domain_pcrqq").val();
		pcrqz = $("#mainForm_domain_pcrqz").val(); 
		if(pcrqq>pcrqz){
			showError("派车开始日期不能大于派车截止日期！");
				return;
		}
		if (pcrqq == "" || pcrqz == "") {
			showAlert("派车日期不能为空！");
			return;
		}
		fhrDjxh = $("#mainForm_domain_fhrDjxh").val();
		fhrMc = $("#mainForm_domain_fhrMc").val();
		pcdh = $("#mainForm_domain_pcdh").val();
		//请求表格数据
		var url = jcontextPath+"/zyegl/pcxydj!query";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.pcJgbm":pcJgbm, "domain.pcrCzyDjxh":encodeURI(pcrCzyDjxh),"domain.pcrqq":pcrqq,
	 			"domain.pcrqz":pcrqz, "domain.fhrMc":encodeURI(fhrMc),"domain.pcdh":encodeURI(pcdh),
	 			"domain.fhrDjxh":fhrDjxh,"domain.zt":zt}								//请求的参数，json格式
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
		    colNames:['序号','<input title="选中/取消选中" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />',
		              '操作','派车登记序号','文书审批序号','变更标志','审批','审批状态代码','派车单号', '原/现运费','现运费', '原/现预付','现预付','订单编号','未发货登记序号',
		              '客户名称', 'ddDjxh','货物名称', '始发地', '目的地', '转入部门',
		              '原/现数量','现结算数量', '数量', 
		              '重量', '体积','包装', '发货地址', '要求发货日期', '收货人','收货地址',
		               '要求到达日期','类别', '装车', '车辆号码', '挂车号码', 
			              '司机','派车人', '派车日期', '派车部门', '所属机构','状态', '协议号'],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[
			  {name:'pageXh', index:'pageXh', sortable:false, width:'30', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pageXh' + rowId + '\'';
			    }
			  },
			  {name:'fsspCheck', index:'fsspCheck', hidden:true, width:'20', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'fsspCheck' + rowId + '\'';
			    }
			  },
			  {name:'hstoperationcol', index:'', sortable:false, width:'50', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'hstoperationcol' + rowId + '\'';
			    }
			  },
			  {name:'pcDjxh', index:'pcDjxh', hidden:true,width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcDjxh' + rowId + '\'';
			    }
			  },
			  {name:'wsSpxh', index:'wsSpxh',hidden:true, width:'80', align:'center'},
			  {name:'bgbz', index:'bgbz',hidden:true, width:'80', align:'center'},
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
			  {name:'yfHj', index:'yfHj', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'yfHj' + rowId + '\'';
			    }
			  },
			  {name:'xyYfHj', index:'xyYfHj', hidden:true,width:'50', align:'center'},
			  {name:'yfYfyf', index:'yfYfyf', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'yfYfyf' + rowId + '\'';
			    }
			  },
			  {name:'xyYfYfyf', index:'xyYfYfyf', hidden:true,width:'40', align:'center'},
			  {name:'dingdan', index:'dingdan', width:'80', align:'center'},
			  {name:'wfhDjxh', index:'wfhDjxh',hidden:true, width:'', align:'center'},
			  {name:'fhrMc', index:'fhrMc', width:'120', align:'center'},
			  {name:'ddDjxh', index:'ddDjxh', hidden:true,width:'120', align:'center'},
			  {name:'hwMc', index:'hwMc', width:'100', align:'center'},
		      {name:'fhrXzqhMc', index:'fhrXzqhMc', width:'40', align:'center'},
		      {name:'shrXzqhMc', index:'shrXzqhMc', width:'40', align:'center'},
		      {name:'zrbmMc', index:'zrbmMc', width:'100', align:'center'},
		      {name:'jssl', index:'jssl', width:'80', align:'right'},
		      {name:'xyJsSl', index:'xyJsSl', hidden:true,width:'60', align:'right'},
		      {name:'sl', index:'sl', width:'50', hidden:true,align:'right'},  
		      {name:'zl', index:'zl', width:'50', align:'right'},
		      {name:'tj', index:'tj', width:'50', align:'right'},
		      {name:'hwbz', index:'hwbz', width:'50', align:'center'},
		      {name:'sfdMc', index:'sfdMc', width:'200', align:'center'},
		      {name:'yqFhrq', index:'yqFhrq', width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      {name:'shrMc', index:'shrMc', width:'100', align:'center'},
		      {name:'mddMc', index:'mddMc', width:'200', align:'center'},
		      {name:'yqDdrq', index:'yqDdrq', width:'80', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},
		      
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
		      {name:'pcrMc', index:'pcrMc', width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcrMc' + rowId + '\'';
			    }
			  },
			  {name:'pcrq', index:'pcrq', width:'70', align:'center', formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'},
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pcrq' + rowId + '\'';
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
			  },
			  {name:'dzsl', index:'dzsl', sortable:false, width:'45', align:'center', 
					cellattr: function(rowId, tv, rawObject, cm, rdata) {
					   return 'id=\'dzsl' + rowId + '\'';
				    }
				  },
				  {name:'xyh', index:'xyh', width:'65', align:'center', 
						cellattr: function(rowId, tv, rawObject, cm, rdata) {
						   return 'id=\'xyh' + rowId + '\'';
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
		  
		 //add custom button to export the data to excel
		  /* $("#dataList").jqGrid('navButtonAdd','#pager',{
		       caption:"", 
		       onClickButton : function () { 
		       	   $("#mainForm").attr("action",jcontextPath+"/zyegl/pcxydj!download");
				   $("#mainForm").submit();
		       } 
		 }); */
		 
	}
	
	function myGridComplete() {
        var graduateIds = $("#dataList").jqGrid('getDataIDs');
        var xtcs20206 = $("#mainForm_domain_xtcs20206").val();
        for (var i = 0; i < graduateIds.length; i++) {
            var cl = graduateIds[i];
            var pcDjxh = jQuery("#dataList").jqGrid('getCell', cl,"pcDjxh"); 
            var pcdh = jQuery("#dataList").jqGrid('getCell', cl,"pcdh"); 
            var wsSpxh = jQuery("#dataList").jqGrid('getCell', cl,"wsSpxh");
            var xysl = jQuery("#dataList").jqGrid('getCell', cl,"dzsl");
            var dingdan = jQuery("#dataList").jqGrid('getCell', cl,"dingdan");
           // var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh"); 
            var ddbh = jQuery("#dataList").jqGrid('getCell', cl,"dingdan");
 	        var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ddDjxh"); 
 	        var ddbhStr="<a href=\"javascript:onViewMx("+ddDjxh+")\"><font color=\"blue\">"+ddbh+"</font></a>";
 	        $("#dataList").jqGrid('setRowData', cl, { 'dingdan': ddbhStr });
 	        
            var link = "<a href=\"javascript:onXydj('"+pcDjxh+"')\"><font color=\"blue\">登记</font></a>";
            var str = "<font color=\"red\">未登记</font>";
            if (xysl/1 > 0) {
            	str = "已登记";
            	link = "<a href=\"javascript:onXydj('"+pcDjxh+"')\"><font color=\"blue\">修改</font></a>"
            			+"&nbsp;<a href=\"javascript:onDelXydj('"+pcDjxh+"')\"><font color=\"blue\">撤销</font></a>";
            			//+"&nbsp;<a href=\"javascript:onShowXybd('"+pcDjxh+"')\"><font color=\"blue\">查看</font></a>";
            }
           // var strDan="<a href=\"javascript:onViewMx("+1+")\"><font color=\"blue\">"+dingdan+"</font></a>";
            var strPc="<a href=\"javascript:onViewPc("+pcDjxh+")\"><font color=\"blue\">"+pcdh+"</font></a>";
            var hwMc = jQuery("#dataList").jqGrid('getCell', cl,"hwMc");
           	var wfhDjxh = jQuery("#dataList").jqGrid('getCell', cl,"wfhDjxh");
            var link4Hw = "<a href=\"javascript:onXydjHwmx('"+pcDjxh+"','"+wfhDjxh+"',"+xysl+")\"><font color=\"blue\">"+hwMc+"</font></a>";
            
            $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            $("#dataList").jqGrid('setRowData', cl, { 'dzsl': str });
            $("#dataList").jqGrid('setRowData', cl, { 'hwMc': link4Hw });
            //$("#dataList").jqGrid('setRowData', cl, { 'dingdan': strDan });
            $("#dataList").jqGrid('setRowData', cl, { 'pcdh': strPc });
            var bgbz = jQuery("#dataList").jqGrid('getCell', cl,"bgbz");
            if ("Y" == xtcs20206 ) {
				var wsspztDm = jQuery("#dataList").jqGrid('getCell', cl,"wsspztDm"); 
				var spLink = '<input type="checkbox" name="xhs" value="'+pcDjxh+'#'+wsSpxh+'" />';
				if ("1" == wsspztDm || "3" == wsspztDm || "Y" != bgbz) {
					spLink = '<input type="checkbox" name="xhs" value="'+pcDjxh+'#'+wsSpxh+'" disabled="disabled" />';
				}
				
				$("#dataList").jqGrid('setRowData', cl, { 'fsspCheck': spLink }); 
    		}
            
            var yfhj = jQuery("#dataList").jqGrid('getCell', cl,"yfHj");
	        var xyYfhj = jQuery("#dataList").jqGrid('getCell', cl,"xyYfHj");
	        var yfYfyf = jQuery("#dataList").jqGrid('getCell', cl,"yfYfyf");
	        var xyYfYfyf = jQuery("#dataList").jqGrid('getCell', cl,"xyYfYfyf");
	        var jssl = jQuery("#dataList").jqGrid('getCell', cl,"jssl");
	        var xyJssl = jQuery("#dataList").jqGrid('getCell', cl,"xyJsSl");
	        
	        var yfStr = yfhj + "/" + xyYfhj;
	        if (yfhj != xyYfhj) {
	        	yfStr = "<font color=\"red\">" + yfStr + "</font>";
	        }
	        var yfyfStr = yfYfyf + "/" + xyYfYfyf;
	        if (yfYfyf != xyYfYfyf) {
	        	yfyfStr = "<font color=\"red\">" + yfyfStr + "</font>";
	        }
	        var jsslStr = jssl + "/" + xyJssl;
	        if (jssl != xyJssl) {
	        	jsslStr = "<font color=\"red\">" + jsslStr + "</font>";
	        }
	        $("#dataList").jqGrid('setRowData', cl, { 'yfHj': yfStr });
	        $("#dataList").jqGrid('setRowData', cl, { 'yfYfyf': yfyfStr });
	        $("#dataList").jqGrid('setRowData', cl, { 'jssl': jsslStr });
        }
        
        var gridName = "dataList";
 	    var a = ['pageXh','hstoperationcol','dzsl','pcDjxh','pcdh','xyh','fsspCheck','wsspztMc','pcfsMc','zcfxMc','cyrClhm','cyrGchm','cyrSjxm',
 	            'yfHj','xyYfHj','yfYfyf','xyYfYfyf','pcrMc','pcrq','pcJgbmMc','ssJgbmMc'
 	            ];
  		
        Merger(gridName, 'pcDjxh', a);
 	}
     /**************************************分页结束****************************************/

</script>
</head>
<body>
<s:form action="pcxydj!query" namespace="/zyegl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.xtcs20206" />
	<s:hidden name="domain.xtcs20016" />
	<s:hidden name="jsonData"></s:hidden>
	
	
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">操作：</li>
		    <li><a href="#" id="queryBtn" class="licon04">检 索</a></li>
	    <s:if test='domain.xtcs20206 == "Y"'>
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
		          <td><s:textfield name="domain.pcdh" cssClass="pop_input noborder" /></td>
		          <td align="right">派车日期：</td>
		          <td>
		          <sys:dateFirstDMonth myName="domain.pcrqq" myId="mainForm_domain_pcrqq" myClass="ymdate" />
	          			～
	          		<sys:dateCurrentDayTag myName="domain.pcrqz" myId="mainForm_domain_pcrqz" myClass="ymdate" />
		         
		          </td>
		        </tr>
		        <tr>
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
		</div>
		
		<%@include file="/common/message.jsp" %>
</s:form>
</body>
</html>
