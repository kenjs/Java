<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>开票申请</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
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
			
			//批量发送
			$("#plScSendBtn").click(function(){
				var wsDm="305003";//开票申请审核表
				plScSend(wsDm,"");
			});
			
			$("#srkpBtn").click(function(){
				var dwDm = trim($("#mainForm_domain_ssJgbm").val()); 
				var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
				var kpsqDjxh="";
				var kpsqfsDm='3';
				if(undefined==dwDm || null==dwDm || ""==dwDm){
					showAlert("请您选择业务单位！");
					return;
				}
				
				if(undefined==khDjxh){
					khDjxh ="";
				}
				var dwMcStr=$("select[name='domain.ssJgbm']").find("option:selected").text();
				var dwMc=dwMcStr.split(" ")[1];
				var khMc = trim($("#mainForm_domain_khMc").val()); 
				var h=650;
		    	var w=860;
		    	//popwindow(jcontextPath+"/hygl/jskpsq!initMx?domain.kpsqDjxh="+kpsqDjxh);
		    	var url = jcontextPath+"/hygl/jskpsq!initMx.action?domain.kpsqDjxh="+kpsqDjxh+"&domain.kpsqfsDm="+kpsqfsDm;
		    	url+="&domain.ssJgbm="+dwDm+"&domain.dwMc="+dwMc+"&domain.khDjxh="+khDjxh+"&domain.khMc="+khMc+"&num="+Math.random();
		    	url = encodeURI(encodeURI(url));
		    	var parm="dialogHeight:"+h+"px;dialogWidth:"+w+"px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;"
		    	var val=window.showModalDialog(url,window,parm);
		    	if(val!=''&&val!=undefined){
		    		var url=jcontextPath+"/hygl/jskpsq!deleteSqKpTemp"
			    	var jsonObj={"domain.flag":val};
		    		ajaxCommon(url,jsonObj,"afterDelete");
		    	}
		    	onRefresh();
		    	//window.open(url);
		    	
			})
			
			//对账开票按钮事件
			$("#dzkpBtn").click(function(){
				//var url=jcontextPath+"/hygl/jskpsq!initMx?domain.kpsqfsDm=1";
				//popwindow(url);
				var dwDm = trim($("#mainForm_domain_ssJgbm").val()); 
				var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
				
		  		if(undefined==dwDm || null==dwDm || ""==dwDm){
					showAlert("请您选择业务单位！");
					return;
				}
				
				//if(undefined==khDjxh || null==khDjxh || ""==khDjxh){
				//	showAlert("请您选择客户名称！");
				//	return;
				//}
				//var dwMcStr = $("select[name='domain.ssJgbm'] option[selected]").text(); 
				var dwMcStr = $("select[name='domain.ssJgbm']").find("option:selected").text(); 
				var dwMc=dwMcStr.split(" ")[1];
				var khMc = trim($("#mainForm_domain_khMc").val()); 
				onUpdate("","1",600,760,dwDm,dwMc,khDjxh,khMc);
			});
			//预开票
			$("#ykpBtn").click(function(){
				var dwDm = trim($("#mainForm_domain_ssJgbm").val()); 
				var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
				
		  		if(undefined==dwDm || null==dwDm || ""==dwDm){
					showAlert("请您选择业务单位！");
					return;
				}
				
				//if(undefined==khDjxh || null==khDjxh || ""==khDjxh){
				//	showAlert("请您选择客户名称！");
				//	return;
				//}
				//var dwMcStr = $("select[name='domain.ssJgbm'] option[selected]").text(); 
				var dwMcStr = $("select[name='domain.ssJgbm']").find("option:selected").text(); 
				var dwMc=dwMcStr.split(" ")[1];
				var khMc = trim($("#mainForm_domain_khMc").val()); 
				onUpdate("","2",500,620,dwDm,dwMc,'',khMc);
			});
			//初始化表格
			initDataGrid();
			initList();					

	});
	
	function afterDelete(){
		onRefresh();
	}
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
    function onUpdate(kpsqDjxh,kpsqfsDm,height,width,ssJgbm,dwMc,khDjxh,khMc){
    	var h=600;
    	var w=760;
    	if(undefined==ssJgbm)
    		ssJgbm="";
    	if(undefined==dwMc)
    		dwMc="";
    	if(undefined==khDjxh)
    		khDjxh="";
    	if(undefined==khMc)
    		khMc="";
    	if(undefined!=height)
    		h=height;
    	if(undefined!=width)
    		w=width;
    	//popwindow(jcontextPath+"/hygl/jskpsq!initMx?domain.kpsqDjxh="+kpsqDjxh);
    	var url = jcontextPath+"/hygl/jskpsq!initMx.action?domain.kpsqDjxh="+kpsqDjxh+"&domain.kpsqfsDm="+kpsqfsDm;
    	url+="&domain.ssJgbm="+ssJgbm+"&domain.dwMc="+dwMc+"&domain.khDjxh="+khDjxh+"&domain.khMc="+khMc;
    	url = encodeURI(encodeURI(url));
    	var parm="dialogHeight:"+h+"px;dialogWidth:"+w+"px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;"
    	window.showModalDialog(url,window,parm)
    	//window.open(url);
    	onRefresh();
    }
    function onUpdateToSrKp(kpsqDjxh,kpsqfsDm,height,width,ssJgbm,dwMc,khDjxh,khMc){
    	var h=600;
    	var w=760;
    	if(undefined==ssJgbm)
    		ssJgbm="";
    	if(undefined==dwMc)
    		dwMc="";
    	if(undefined==khDjxh)
    		khDjxh="";
    	if(undefined==khMc)
    		khMc="";
    	if(undefined!=height)
    		h=height;
    	if(undefined!=width)
    		w=width;
    	//popwindow(jcontextPath+"/hygl/jskpsq!initMx?domain.kpsqDjxh="+kpsqDjxh);
    	var url = jcontextPath+"/hygl/jskpsq!initMx.action?domain.kpsqDjxh="+kpsqDjxh+"&domain.kpsqfsDm="+kpsqfsDm;
    	url+="&domain.ssJgbm="+ssJgbm+"&domain.dwMc="+dwMc+"&domain.khDjxh="+khDjxh+"&domain.khMc="+khMc+"&num"+Math.random();
    	url = encodeURI(encodeURI(url));
    	var parm="dialogHeight:"+h+"px;dialogWidth:"+w+"px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;"
    	var val=window.showModalDialog(url,window,parm);
    	if(val!=''&&val!=undefined){
    		var url=jcontextPath+"/hygl/jskpsq!deleteSqKpTemp"
	    	var jsonObj={"domain.flag":val};
    		ajaxCommon(url,jsonObj,"afterDelete");
    	}
    	onRefresh();
    }
    
     function onView(kpsqDjxh,kpsqfsDm,height,width,ssJgbm,dwMc,khDjxh,khMc){
    	var h=600;
    	var w=760;
    	if(undefined==ssJgbm)
    		ssJgbm="";
    	if(undefined==dwMc)
    		dwMc="";
    	if(undefined==khDjxh)
    		khDjxh="";
    	if(undefined==khMc)
    		khMc="";
    	if(undefined!=height)
    		h=height;
    	if(undefined!=width)
    		w=width;
    	//popwindow(jcontextPath+"/hygl/jskpsq!initMx?domain.kpsqDjxh="+kpsqDjxh);
    	var url = jcontextPath+"/hygl/jskpsq!initMxCk.action?domain.kpsqDjxh="+kpsqDjxh;
    	url = encodeURI(encodeURI(url));
    	var parm="dialogHeight:"+h+"px;dialogWidth:"+w+"px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;"
    	window.showModalDialog(url,window,parm)
    	//window.open(url);
    	onRefresh();
    }
    var keyValue = "";
	function onDelete( kpsqDjxh){
		keyValue = kpsqDjxh;
		showConfirm("确定要删除么？","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.kpsqDjxh":keyValue};
		 var url = jcontextPath+"/hygl/jskpsq!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("删除成功！");
        onRefresh();
	}	
    
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		var xtcsSfsp = $("#mainForm_domain_xtcsSfsp").val();
		if ("Y" == xtcsSfsp) {
			$("#dataList").jqGrid('showCol',["wsspztMc"]);
			$("#dataList").jqGrid('showCol',["fsspCheck"]);
		}
		
		var khDjxh = trim($("#mainForm_domain_khDjxh").val()); 
		var rqQ = trim($("#mainForm_domain_rqQ").val()); 
		var rqZ = trim($("#mainForm_domain_rqZ").val()); 
		var djJgbm = trim($("#mainForm_domain_djJgbm").val()); 
		var ssJgbm = trim($("#mainForm_domain_ssJgbm").val()); 
		
		if(undefined==ssJgbm || null==ssJgbm || ""==ssJgbm){
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
		var url = jcontextPath+"/hygl/jskpsq!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.khDjxh":encodeURI(khDjxh),"domain.rqQ":rqQ,"domain.rqZ":rqZ,"domain.djJgbm":djJgbm,
				      "domain.ssJgbm":ssJgbm}								//请求的参数，json格式
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
		    colNames:['<input title="选中/取消选中" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />',
		    		'操作', '文书审批序号','审批','审批状态代码','开票申请登记序号','申请方式dm','申请方式','客户名称','申请开票金额','申请开票日期',
				     '登记人','登记日期','登记部门','所属单位',
				     '开票单位'],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[ 
			  {name:'fsspCheck', index:'fsspCheck', hidden:true, width:'20', align:'center'},
			  {name:'hstoperationcol', index:'', sortable:false, width:'60', align:'center'},
			  {name:'wsSpxh', index:'wsSpxh',hidden:true, width:'80', align:'center'},
			  {name:'wsspztMc', index:'wsspztMc', hidden:true,width:'45', align:'center'},
			  {name:'wsspztDm', index:'wsspztDm', hidden:true,width:'80', align:'center'},
		      {name:'kpsqDjxh', index:'kpsqDjxh', width:'80', align:'center',hidden:true}, 
		      {name:'kpsqfsDm', index:'kpsqfsDm', width:'40', align:'center',hidden:true},
		      {name:'kpsqfsMc', index:'kpsqfsMc', width:'70', align:'center'},  
		      {name:'khMc', index:'khMc', width:'100', align:'center',hidden:true}, 
		      {name:'sqKpjeHj', index:'sqKpjeHj', width:'80', align:'center'}, 

		      {name:'sqKprq', index:'sqKprq', width:'80', align:'center'}, 
		      {name:'cjrMc', index:'cjrMc', width:'70', align:'center'}, 
		      {name:'djrq', index:'djrq', width:'60', align:'center'}, 
		      {name:'bmMc', index:'bmMc', width:'100', align:'center'}, 

		      {name:'dwMc', index:'dwMc', width:'80', align:'center'}, 
		      {name:'kpDwJgMc', index:'kpDwJgMc', width:'80', align:'center'}
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum%>,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[20,50,100,300,500],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'KPSQ_DJXH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/jskpsq!download");
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
                var val = jQuery("#dataList").jqGrid('getCell', cl,"kpsqDjxh"); 	  //获取当前单元格里面的登记序号 
                var kpsqfsDm = jQuery("#dataList").jqGrid('getCell', cl,"kpsqfsDm");
                var link="";
                if("1"==kpsqfsDm){
                	link += "<a href=\"javascript:onUpdate('"+val+"','"+kpsqfsDm+"')\"><font color=\"blue\">修改</font></a>"; 
                }
                //预开票
                if("2"==kpsqfsDm){
                	link += "<a href=\"javascript:onUpdate('"+val+"','"+kpsqfsDm+"',"+480+","+600+")\"><font color=\"blue\">修改</font></a>";
                }
               
                if("3"==kpsqfsDm){
                	link += "<a href=\"javascript:onUpdateToSrKp('"+val+"','"+kpsqfsDm+"',"+650+","+860+")\"><font color=\"blue\">修改</font></a>";
                }
                
               link+= " <a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">删除</font></a>";
               /*if("1"==kpsqfsDm){
                	link += " <a href=\"javascript:onView('"+val+"','"+kpsqfsDm+"')\"><font color=\"blue\">查看</font></a>"; 
                }
                //预开票
                if("2"==kpsqfsDm){
                	link += " <a href=\"javascript:onView('"+val+"','"+kpsqfsDm+"',"+480+","+600+")\"><font color=\"blue\">查看</font></a>";
                }*/
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
                
                var wsSpxh = jQuery("#dataList").jqGrid('getCell', cl,"wsSpxh");
                if ("Y" == xtcsSfsp) {
					var wsspztDm = jQuery("#dataList").jqGrid('getCell', cl,"wsspztDm"); 
					var spLink = '<input type="checkbox" name="xhs" value="'+val+'#'+wsSpxh+'" />';
					if ("1" == wsspztDm || "3" == wsspztDm) {
						spLink = '<input type="checkbox" name="xhs" value="'+val+'#'+wsSpxh+'" disabled="disabled" />';
					}
					
					$("#dataList").jqGrid('setRowData', cl, { 'fsspCheck': spLink }); 
	    		}
            }
     }
     /**************************************分页结束****************************************/

</script>
</head>
<body>
<s:form action="jskpsq!query" namespace="/hygl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.xtcsSfsp" />
	<s:hidden name="jsonData" />
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">操作：</li>
		    <li><a href="#" id="queryBtn" class="licon04">检 索</a></li>
		    <li><a href="#" id="dzkpBtn" class="licon11">对账开票</a></li>
		    <li><a href="#" id="srkpBtn" class="licon11">收入开票</a></li>
		    <li><a href="#" id="ykpBtn" class="licon12">预开票</a></li>
		    <s:if test='domain.xtcsSfsp == "Y"'>
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
				<td align="right">申请日期：</td>
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
		<table id="dataList"><tr><td/></tr></table> 
		<!-- 分页导航 -->
		<div id="pager"></div>
		<%@include file="/common/message.jsp" %>
	</div>
</s:form>
</body>
</html>
