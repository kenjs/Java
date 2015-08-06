<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>财务-支付登记</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>

<script type="text/javascript">
	$(document).ready(function(){
			initHykhData(300, $("#mainForm_domain_ssJgbm").val());
			
			//查询按钮事件
			$("#queryBtn").click(function(){
				onRefresh();
			});
			$("#addBtn").click(function(){
				var xhs = $(":checked[name='pldj']");
				var hjJe=0;
				var num =0;
				ysyfDjxhs = new Array();
				$.each(xhs,function(i,obj){
					var strs = obj.value.split("#");
					ysyfDjxh = strs[0];
					ysyfDjxhs[i]=ysyfDjxh;
					wsfJe = strs[1]/1;
					hjJe = hjJe+wsfJe;
					num++;
				})
				var zcflDm = $("#mainForm_domain_zcflDm").val();
				var yh = $("#yhTd select").find("option:selected").text();
				var rq = $("#mainForm_domain_rq").val(); 
				var str;
				if(zcflDm=="11"){
					str = "现金账户";
				}else if(zcflDm=="12"){
					str = "银行账户 "+yh;
				}else{
					str = "油卡账户"
				}
				if(xhs.length<=0){
					showAlert("请选择要登记的应收付记录！")
					return;
				}else{
					showConfirm("一共"+num+"条应收付记录，合计金额"+hjJe+"元！批量登记到"+str+"，登记日期为"+rq+"！确认批量登记么？","doPlDj")
				}
			});	
			//初始化表格
			initDataGrid();
			initJsfMc();
			initYh();
	});
	//自定义jquery
	function getJqueryParamZdy(obj, paraName) {
		var data = "";
		$.each(obj, function(i){
			data += paraName + "[" + i + "]=" + encodeURI(obj[i]) + "&";
		});
		return data;
	}
	function initYh(){
		var zcflDm = $("#mainForm_domain_zcflDm").val();
		if(zcflDm=="12"){
			$("#yhTd").html($("#yhDiv").html());
		}else{
			$("#yhTd").html($("#noneDiv").html());
		}
		
	}
	function doPlDj(){
		var rq = $("#mainForm_domain_rq").val(); 
		var yhCshDjxh = $("#yhTd select").val();
		var zcflDm = $("#mainForm_domain_zcflDm").val();
		if(zcflDm != "12"){
		   yhhdh = "";
		   var val = $("#mainForm_domain_zcflDm").find("option:selected").text();
		   var strs=val.split(" "); //字符分割      
		   yhCshDjxh = strs[2];
		   var showStr;
		   if(zcflDm=="11"){
		   		showStr="请先在资产初始化中维护现金！";
		   }else{
		   		showStr="请先在资产初始化中维护油卡！";
		   }
		   if(yhCshDjxh==null||yhCshDjxh==""){
				showAlert(showStr);
				return;
			}
		}else{
			if(yhCshDjxh==null||yhCshDjxh==""){
				showAlert("请先在资产初始化中维护银行账号！");
				return;
			}
		}
		//alert(yhCshDjxh+rq);
		var jsonStr = getJqueryParamZdy(ysyfDjxhs, "domain.ysyfDjxhs");
		var jsonObj = {"domain.yhCshDjxh":yhCshDjxh,"domain.rq":rq,"domain.zcflDm":zcflDm};
		jsonStr += jQuery.param(jsonObj);
		var url = jcontextPath+"/cwgl/cwzfdj!plDj";
        //alert(jsonStr);
        ajaxCommon(url,encodeURI(jsonStr),"saveOk", false);
	}
	function saveOk(){
		showSuccess("保存成功！","onRefresh");
	}
    function onRegister(ysyfDjxh){
    	var url = jcontextPath+"/cwgl/cwzfdj!initMx.action?domain.ysyfDjxh="+ysyfDjxh + "&num="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:680px;dialogWidth:850px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		onRefresh();
    }
     function initJsfMc(){
   		 var yfjsfDm = $("#mainForm_domain_yfjsfDm").val();
   		 //alert(yfjsfDm);
   		 if(yfjsfDm=="11"||yfjsfDm=="12"||yfjsfDm=="21"||yfjsfDm=="22"||yfjsfDm=="23"||yfjsfDm=="41"){
   		 	$(".mcTd").show();
   		 	$(".noneTd").hide();
   		 }else{
   		 	$(".mcTd").hide();
   		 	$(".noneTd").show();
   		 	$("#mainForm_domain_yfjsfDjmc").val("");
   		 }
   }
    function onDelete(ysyfDjxh){
    	keyValue = ysyfDjxh;
		showConfirm("确定要删除么？","yesCallBack");
    }
    
    function yesCallBack(){
    	var url = jcontextPath+"/cwgl/cwzfdj!cancle";
    	var jsonObj = {"domain.ysyfDjxh":keyValue};
    	ajaxCommon(url,jsonObj,"doSuccess");
    }
	
	function doSuccess(){     
        showAlert("撤销成功！");
        onRefresh();
	}	
	function onViewMx(ddDjxh) {
		var url = jcontextPath+"/jcgl/jctydgl!initMx.action?domain.ddDjxh="+ddDjxh;
    	window.showModalDialog(url,window,"dialogHeight:650px;dialogWidth:880px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;")
    	//window.open(url);
	}
    /*function initMc(){
      var sj = $("#mainForm_domain_ssJgbm").val();
      var yfjsfDm = $("#mainForm_domain_yfjsfDm").val();
      var url = jcontextPath+"/cwgl/cwzfdj!queryXl";  
	  var jsonObj = {"domain.ssJgbm":sj,"domain.yfjsfDm":yfjsfDm};
	  ajaxCommon(url,jsonObj,"YesInit");
   }
   function YesInit(obj){
      document.getElementById("mainForm_domain_yfjsfDjxh").options.length=0; 
      var list = obj.domain.yfjsfMcList;
     
      for(var i=0;i<list.length;i++){
        document.getElementById("mainForm_domain_yfjsfDjxh").options.add(new Option(list[i].mcStr,list[i].yfjsfDjxh));
      }
      $("#mainForm_domain_yfjsfDjxh").val();
   }*/
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var ysyfDjxh = $("#mainForm_domain_ysyfDjxh").val();
		var yfjsfDm = $("#mainForm_domain_yfjsfDm").val();
		var yfjsfDjxh = "";
        var yfjsfDjmc = $("#mainForm_domain_yfjsfDjmc").val();
		var csrqQ = $("#mainForm_domain_csrqQ").val();
		var csrqZ = $("#mainForm_domain_csrqZ").val();
		var zt = $("input[name='domain.zt']:checked").val();
		var ddbh = $("#mainForm_domain_ddbh").val();
		var ysyflyDm = $("#mainForm_domain_ysyflyDm").val();
		var kmxlDm = $("#mainForm_domain_kmxlDm").val();
		//请求表格数据
		var url = jcontextPath+"/cwgl/cwzfdj!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm, "domain.ysyfDjxh":ysyfDjxh, "domain.yfjsfDjxh":yfjsfDjxh,"domain.yfjsfDjmc":encodeURI(yfjsfDjmc),
		 			  "domain.yfjsfDm":yfjsfDm, "domain.csrqQ":csrqQ, "domain.csrqZ":csrqZ, "domain.zt":zt,"domain.ddbh":encodeURI(ddbh),
		 			  "domain.ysyflyDm":ysyflyDm,"domain.kmxlDm":kmxlDm}								//请求的参数，json格式
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
		    colNames:['<input title="选中/取消选中" type="checkbox" onclick="checkAllJGridChk(event,this,\'pldj\');" />','操作','应收应付状态代码','状态','应收应付登记序号','业务登记序号','订单编号','结算方','名称','金额','已付',
		              '未付','类别','项目','来源代码','来源','产生日期',
		              '说明','登记部门','所属单位'],		 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[
			  {name:'pldjCheck',index:'pldjCheck',width:'30',align:'center'},
			  {name:'hstoperationcol', index:'', sortable:false,width:'60', align:'center'},
			  {name:'ysyfztDm', index:'ysyfztDm',hidden:true},
			  {name:'ysyfztMc', index:'ysyfztMc', width:'35', align:'center'},
			  {name:'ysyfDjxh', index:'ysyfDjxh',hidden:true, align:'center'}, 
			  {name:'ywDjxh', index:'ywDjxh', width:'100',hidden:true, align:'center'},
			  {name:'ddbh', index:'ddbh', width:'70',align:'center'},
		      {name:'yfjsfMc', index:'yfjsfMc', width:'50', align:'center'},
		      {name:'yfjsfDjmc', index:'yfjsfDjmc', width:'150', align:'center'}, 
		      {name:'ysfJe', index:'ysfJe', width:'50', align:'center'},
			  {name:'yisfJe', index:'yisfJe', width:'50', align:'center'},
			  {name:'wsfJe', index:'wsfJe', width:'50', align:'center'},			  		     
		      {name:'kmdlMc', index:'kmdlMc', width:'70', align:'center'}, 	      
		      {name:'kmxlMc', index:'kmxlMc', width:'70', align:'center'},			  
			  {name:'ysyflyDm', index:'ysyflyDm', width:'70', align:'center',hidden:true},
			  {name:'ysyflyMc', index:'ysyflyMc', width:'70', align:'center'}, 			  
			  {name:'csrq', index:'csrq', width:'70', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}},	
			  {name:'sm', index:'sm', width:'400', align:'left'},            
			  {name:'djJgmc', index:'djJgmc', width:'60', align:'center'},
			  {name:'ssJgmc', index:'ssJgmc', width:'80', align:'center'}
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum%>,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[20,50,100,300,500],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'ysyfDjxh',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
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
		       	   $("#mainForm").attr("action",jcontextPath+"/cwzfdj!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //表格加载完毕后触发事件：修改操作列的内容，显示“登记”，并增加链接
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"ysyfDjxh"); 	 
                var ysyfztDm = jQuery("#dataList").jqGrid('getCell', cl,"ysyfztDm");                
                var wsfJe = jQuery("#dataList").jqGrid('getCell', cl,"wsfJe");
                var ysyflyDm = jQuery("#dataList").jqGrid('getCell', cl,"ysyflyDm");
                if(ysyfztDm == '11'){
	                var link = "<a href=\"javascript:onRegister('"+val+"')\"><font color=\"blue\">登记</font></a>";
	                var linkPlDj = '<input type="checkbox" name="pldj" value="'+val+"#"+wsfJe+'" />';
                }
                else if(ysyfztDm == '12'){
	                var link = "<a href=\"javascript:onRegister('"+val+"')\"><font color=\"blue\">修改</font></a> " + 
	                " <a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">撤销</font></a>";
	                var linkPlDj = '<input type="checkbox" name="pldj"  disabled="disabled" value="'+val+"#"+wsfJe+'" />';
                }
                var ddbh = jQuery("#dataList").jqGrid('getCell', cl,"ddbh");
                var ddDjxh = jQuery("#dataList").jqGrid('getCell', cl,"ywDjxh");
                var str;
                if(ysyflyDm=="1"){
                	 str="<a href=\"javascript:onViewMx("+ddDjxh+")\"><font color=\"blue\">"+ddbh+"</font></a>";
                }else{
                	 str=ddbh;
                }
               
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link });
                $("#dataList").jqGrid('setRowData', cl, { 'ddbh': str });
                $("#dataList").jqGrid('setRowData', cl, { 'pldjCheck': linkPlDj});
            }
     }
     /**************************************分页结束****************************************/

</script>
</head>
<body>
<s:form action="cwzfdj!query" namespace="/cwgl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="fhrData" />
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">操作：</li>
		    <li><a href="#" id="queryBtn" class="licon04">检 索</a></li>
		    <li><a href="#" id="addBtn" class="licon01">批量登记</a></li>
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
				  			<td width="10%" align="right">业务部门：</td>
		          			<td width="25%">
		          				 <sys:fgsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y"  myClass="select" contaisQxz="true"></sys:fgsList>
				  			</td>		
							<td width="8%" align="right">结算方：</td>
							<td width="21%">
						   		<s:select list="domain.yfjsfDmList" name="domain.yfjsfDm" listKey="yfjsfDm" listValue="lbStr"
						             cssClass="select" onchange="initJsfMc()"></s:select>
							</td>
				  			<td width="8%" align="right" class="mcTd">名称：</td>
							<td width="21%" class="mcTd">
								<s:textfield name="domain.yfjsfDjmc" cssClass="inputext pop_input noborder bgstyle_optional"></s:textfield>
							</td>
							<td width="29%"colspan="2" class="noneTd"></td>
							<td style="display: none;">
							   <select name="domain.yfjsfDjxh" id="mainForm_domain_yfjsfDjxh" class="select" >
							      <option value="">-- 请选择 --</option>
							   </select>
							</td>
		        		</tr>
		        		<tr>
					        <td align="right">日期起止：</td>
		        			<td>
		        				<sys:dateFirstDMonth myName="domain.csrqQ" myId="mainForm_domain_csrqQ" myClass="ymdate" />
	          					～
	          					<sys:dateCurrentDayTag myName="domain.csrqZ" myId="mainForm_domain_csrqZ" myClass="ymdate" />
		        			</td>
	     	 				<td align="right">订单编号：</td>
							<td >
								<s:textfield name="domain.ddbh" cssClass="inputext pop_input noborder bgstyle_optional"></s:textfield>
							</td>
				        	<td align="right">状态：</td>
					          <td>
					          	<s:radio name="domain.zt" list='#{"":"所有","1":"已付","2":"未付（完）"}' listKey="key" listValue="value"></s:radio>
					          </td>
		        		</tr>
		        		<tr> 
			  			    <td align="right">来源：</td>
			                <td><sys:DmYsyfly myId="mainForm_domain_ysyflyDm" myName="domain.ysyflyDm" myClass="select" contaisQxz="true"></sys:DmYsyfly></td>
					        <td align="right">项目：</td>
							<td ><sys:DmKmxl myId="mainForm_domain_kmxlDm" myName="domain.kmxlDm" myClass="select" srBz="N" contaisQxz="true"></sys:DmKmxl></td>
					        <td colspan="2"></td>
						</tr>
		      		</table>
	    	</fieldset>
	    	<fieldset>
				<legend>批量登记</legend>
				<table width="99%" border="0" cellspacing="0" cellpadding="0">
					<tr>
						<td width="8%" align="right">资产分类：</td>
						<td width="21%" >
						  <sys:ZcflCsh myName="domain.zcflDm" myId="mainForm_domain_zcflDm" myClass="select" onChange="initYh()" ssJgbm="domain.ssJgbm"></sys:ZcflCsh>
						</td>
						<td width="8%" align="right">银行：</td>
						<td width="21%" id="yhTd"></td>
						<td width="8%" align="right">登记日期：</td> 
						<td width="21%"> 
							<sys:dateCurrentDayTag myName="domain.rq" myId="mainForm_domain_rq" myClass="ymdate" />
		  			    </td>
					</tr>	   
				</table>
			</fieldset>
		</div>
		<div style="display: none;" id="yhDiv"><sys:yhzh myName="domain.yhCshDjxh" myId="mainForm_domain_yhCshDjxh" myClass="select" ssJgbm="domain.ssJgbm"></sys:yhzh></div>
		<div style="display: none;" id="noneDiv"><select id="noneSelect" class="select"><option value="" selected="selected"></option></select></div>
		<!-- 分页表格 id必须为dataList -->
		<table id="dataList"><tr><td/></tr></table> 
		<!-- 分页导航 -->
		<div id="pager"></div>
		<%@include file="/common/message.jsp" %>
	</div>
</s:form>
</body>
</html>
