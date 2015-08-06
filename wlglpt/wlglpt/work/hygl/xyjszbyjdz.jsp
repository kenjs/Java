<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>下游结算-转包-月结对账</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wlglpt_autocomplete.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/dropdownAndInputsel.js" ></script>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
			//查询按钮事件
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//批量对账
			$("#plDzBtn").click(function(){
				//alert(0);
				var checkedVal = new Array();
				var pldz = $(":checked[name='pldz']");
				if (pldz.length <= 0) {
					showAlert("请选择需要批量对账的记录！");
					return;
				}
				var dzCheckBoxs = $(":checkbox[name='pldz']:checked:visible:enabled");
				$.each(dzCheckBoxs,function(i){
					checkedVal[i] = $(dzCheckBoxs[i]).val();
				});
				var url = jcontextPath+"/hygl/xyjszbyjdz!plDz";	
				var jsonObj = {"domain.pldzStr":checkedVal};
				ajaxCommon(url,jsonObj,"plDzSuccess");
			});
			
			//批量发送审批
			$("#plScSendBtn").click(function(){
				var wsDm="306001"; //分包商月结对账表
				plScSend(wsDm,"");
			});
			
			$("#dyylBtn").click(function(){
				var ssJgbm = $("#mainForm_domain_ssJgbm").val();
				var zrbmDm = $("#mainForm_domain_zrbmDm").val();
				var zrbmDjxh = $("#mainForm_domain_zrbmDjxh").val();
				var pcrqQ = $("#mainForm_domain_pcrqQ").val();
				var pcrqZ = $("#mainForm_domain_pcrqZ").val();
				var dzbz = trim($(":checked[name='domain.dzbz']").val());
				
				var url = jcontextPath+"/hygl/xyjszbyjdz!dyyl?domain.ssJgbm="+ssJgbm+"&domain.zrbmDm="+zrbmDm+
					"&domain.zrbmDjxh="+zrbmDjxh+"&domain.pcrqQ="+pcrqQ+"&domain.pcrqZ="+pcrqZ+"&domain.dzbz="+dzbz;
				window.open(url,"_blank")
			});
			
			//初始化表格
			initDataGrid();
	});
	
	function plDzSuccess(){
		showAlert("批量对账成功！");
        onRefresh();
	}

    function onUpdate(dzDjxh){
    	var url = jcontextPath+"/hygl/xyjszbyjdz!initMx?domain.dzDjxh="+dzDjxh;
    	window.showModalDialog(url, window,"dialogHeight:180px;dialogWidth:470px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;maximize:yes;");
    	//popwindow(jcontextPath+"/hygl/xyjszbyjdz!initMx?domain.dzDjxh="+dzDjxh);
    	onRefresh();
    }
    
    var keyValue = "";
	function onDelete( dzDjxh){
		keyValue = dzDjxh;
		showConfirm("确定要删除么？","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.dzDjxh":keyValue};
		 var url = jcontextPath+"/hygl/xyjszbyjdz!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("删除成功！");
        onRefresh();
	}	
    
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		var ssJgbm = $("#mainForm_domain_ssJgbm").val();
		var zrbmDm = $("#mainForm_domain_zrbmDm").val();
		var zrbmDjxh = $("#mainForm_domain_zrbmDjxh").val();
		var pcrqQ = $("#mainForm_domain_pcrqQ").val();
		var pcrqZ = $("#mainForm_domain_pcrqZ").val();
		var dzbz = trim($(":checked[name='domain.dzbz']").val());
		//请求表格数据
		var url = jcontextPath+"/hygl/xyjszbyjdz!query";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.ssJgbm":ssJgbm,"domain.zrbmDm":zrbmDm,
		 		"domain.zrbmDjxh":zrbmDjxh,"domain.pcrqQ":pcrqQ,"domain.pcrqZ":pcrqZ,"domain.dzbz":dzbz} //请求的参数，json格式
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
		    colNames:['对账<input title="全/不选" type="checkbox" onclick="checkAllJGridChk(event,this,\'pldz\');" />',
		    		'审批<input title="全/不选" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />',
		    		'','','','操作','状态','所属机构','派车单号','派车日期','货物名称','分包商类别','分包商',
				    '月结','对账金额','对帐人','对帐日期','对帐部门','是否差异','差异金额'/*,'是否需要审批','审批状态','审批序号'*/],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[ 
			  /*{name:'pageXh', index:'pageXh', sortable:false, width:'35', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pageXh' + rowId + '\'';
			    }
			  },*/
			  {name:'pldz', index:'pldz', sortable:false, width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'pldz' + rowId + '\'';
			    }
			  },
			  {name:'plsp', index:'plsp', sortable:false, width:'60', align:'center', 
				cellattr: function(rowId, tv, rawObject, cm, rdata) {
				   return 'id=\'plsp' + rowId + '\'';
			    }
			  },
			  {name:'dzDjxh', index:'dzDjxh',hidden:true, align:'center'},
			  {name:'dzbz', index:'dzbz',hidden:true, align:'center'},
			  {name:'wsspztDm', index:'wsspztDm',hidden:true, align:'center'},
		      {name:'dz', index:'', width:'60', align:'center'},
		      {name:'status', index:'', width:'60', align:'center'},
		      {name:'jgmc', index:'jgmc', width:'130', align:'center'},
		      {name:'pcdh', index:'pcdh', width:'100', align:'center'}, 
		      
		      {name:'pcrq', index:'pcrq', width:'100', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}, 
		      {name:'hwmc', index:'hwmc', width:'100', align:'center'}, 
		      {name:'zrbmmc', index:'zrbmmc', width:'100', align:'center'},
		      {name:'fbsmc', index:'fbsmc', width:'100', align:'center'}, 
		      {name:'jsYj', index:'jsYj', width:'100', align:'center'}, 

		      {name:'dzje', index:'dzje', width:'100', align:'center'},
		      {name:'dzrmc', index:'dzrmc', width:'100', align:'center'},
		      {name:'dzrq', index:'dzrq', width:'100', align:'center',formatter:'date',formatoptions:{srcformat:'Y-m-d',newformat:'Y-m-d'}}, 
		      {name:'dzjgmc', index:'dzjgmc', width:'100', align:'center'},

		      {name:'dzCybz', index:'dzCybz', width:'100', align:'center'}, 
		      {name:'dzcyje', index:'dzcyje', width:'100', align:'center'}, 
		      /*{name:'spbz', index:'spbz', width:'100', align:'center'}, 
		      {name:'wsspztDm', index:'wsspztDm', width:'100', align:'center'}, 
		      {name:'wsSpxh', index:'wsSpxh', width:'100', align:'center'}*/
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum%>,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[20,50,100,300,500],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'DZ_DJXH',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
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
		       	   $("#mainForm").attr("action",jcontextPath+"/hygl/xyjszbyjdz!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var val = jQuery("#dataList").jqGrid('getCell', cl,"dzDjxh"); 	  //获取当前单元格里面的登记序号 
                //var link = "<a href=\"javascript:onDelete('"+val+"')\"><font color=\"blue\">删除</font></a>";
                //$("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link });
                var dzlink="无",pldzlink="",plsplink="",stslink="";
                var dzbz = jQuery("#dataList").jqGrid('getCell', cl,"dzbz");
                var wsspztDm = jQuery("#dataList").jqGrid('getCell', cl,"wsspztDm");
                if(dzbz=="N"){//未对帐
                	dzlink = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">对账</font></a>";
                	stslink = "<font color=\"red\">未对帐</font>";
                	pldzlink = "<input type=\"checkbox\" name=\"pldz\" value=\""+val+"\" />";
                	plsplink = "<input type=\"checkbox\" name=\"xhs\" disabled=\"disabled\" value=\""+val+"\" />";
                }else if(dzbz=="Y"){//已对账
                	stslink = "<font color=\"red\">已对帐</font>";
                	plsplink = "<input type=\"checkbox\" name=\"xhs\" value=\""+val+"\" />";
                	if(${domain.sfsp=="Y"}){//需要审批
                		pldzlink = "<input type=\"checkbox\" name=\"pldz\" value=\""+val+"\" />";
                		dzlink = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">已对账</font></a>";
                		if(wsspztDm==1){//已发送审批
                			plsplink = "<input type=\"checkbox\" name=\"xhs\" disabled=\"disabled\" value=\""+val+"\" />";
                			dzlink = "已对账";
                			pldzlink = "<input type=\"checkbox\" name=\"pldz\" disabled=\"disabled\" value=\""+val+"\" />";
                			stslink = "<font color=\"red\">审批中</font>";
                		}else if(wsspztDm==2){//退回
                			plsplink = "<input type=\"checkbox\" name=\"xhs\" value=\""+val+"\" />";
                			stslink = "<font color=\"red\">已退回</font>";
                		}else if(wsspztDm==3){//终审
                			plsplink = "<input type=\"checkbox\" name=\"xhs\" disabled=\"disabled\" value=\""+val+"\" />";
                			dzlink = "已对账";
                			pldzlink = "<input type=\"checkbox\" name=\"pldz\" disabled=\"disabled\" value=\""+val+"\" />";
                			stslink = "<font color=\"red\">已终审</font>";
                		}else if(wsspztDm==4){//不需要审批时已对账，已经生成财务信息不允许修改
                			dzlink = "已对账";
                			plsplink = "<input type=\"checkbox\" name=\"xhs\" disabled=\"disabled\" value=\""+val+"\" />";
                			pldzlink = "<input type=\"checkbox\" name=\"pldz\" disabled=\"disabled\" value=\""+val+"\" />";
                			stslink = "<font color=\"red\">已对账</font>";
                		}
                	}else if(${domain.sfsp=="N"}){//不需要审批
                		dzlink = "已对账";
                		stslink = "<font color=\"red\">已对账</font>";
	                	pldzlink = "<input type=\"checkbox\" name=\"pldz\" disabled=\"disabled\" value=\""+val+"\" />";
                	}
                }
                $("#dataList").jqGrid('setRowData', cl, { 'dz': dzlink });
                var dzCybz = jQuery("#dataList").jqGrid('getCell', cl,"dzCybz");
                var cybz = "";
                if(dzCybz=="Y"){
                	cybz = "是";
                }else if(dzCybz=="N"){
                	cybz = "否"
                }
                $("#dataList").jqGrid('setRowData', cl, { 'dzCybz': cybz });
                //批量对账
                $("#dataList").jqGrid('setRowData', cl, { 'pldz': pldzlink });
                //批量审批
                $("#dataList").jqGrid('setRowData', cl, { 'plsp': plsplink });
                //批量审批
                $("#dataList").jqGrid('setRowData', cl, { 'status': stslink });
            }
     }
     /**************************************分页结束****************************************/

</script>
</head>
<body>
<s:form action="hygl/xyjszbyjdz!query" namespace="" method="post" id="mainForm" name="mainForm">
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">操作：</li>
		    <li><a href="#" id="queryBtn" class="licon04">检 索</a></li>
		    <li><a href="#" id="plScSendBtn" class="licon10">批量发送审批</a></li>
		    <li><a href="#" id="plDzBtn" class="licon10">批量对账</a></li>
		    <li><a href="#" id="dyylBtn" class="licon10">月结账单</a></li>
		    <li><a href="#" id="closeBtn" class="licon03">关闭</a></li>
	  	</ul>
	    <ul class="rcont">
		    <li class="ricon02" onclick="slideToggle('syquery')">显示/隐藏查询条件</li>
		    <li class="ricon03">帮助</li>
	  	</ul>
	</div> 

	<div class="right_cont">
		<div id="divQuery">
	<fieldset><legend>查询条件</legend>
	<table width="99%" border="0" cellspacing="0" cellpadding="0">
		<tr>
			<td width="8%" align="right">业务单位：</td>
			<td width="25%">
				<sys:gsList myId="mainForm_domain_ssJgbm" myName="domain.ssJgbm" mcContainDmBz="Y" myClass="select" onChange="initList()" /></td>
			<td width="8%" align="right">分包商类型：</td>
			<td width="21%">
				<sys:Zrbm myId="mainForm_domain_zrbmDm" myName="domain.zrbmDm" contaisQxz="true" myClass="select"></sys:Zrbm>
			</td>
			<td width="8%" align="right">下游：</td>
			<td width="21%">
				<sys:xyzbList myId="mainForm_domain_zrbmDjxh" myName="domain.zrbmDjxh" contaisQxz="true" contaisDq="N" myClass="select"></sys:xyzbList>
			</td>
		</tr>
		<tr>
			<td align="right">派车日期：</td>
			<td>
				<sys:dateFirstDMonth myName="domain.pcrqQ" myId="mainForm_domain_pcrqQ" myClass="ymdate"></sys:dateFirstDMonth>
				 ～ 
				<sys:dateCurrentDayTag myName="domain.pcrqZ" myId="mainForm_domain_pcrqZ" myClass="ymdate"></sys:dateCurrentDayTag>
			</td>
			<td align="right">对账状态：</td>
			<td colspan="3">
				<s:radio id="mainForm_domain_dzbz" name="domain.dzbz" list="#{'':'所有','Y':'已对账','N':'未对账'}"></s:radio>
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
