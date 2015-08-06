<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@ page language="java" contentType="text/html;charset=GBK" pageEncoding="GBK"%>
<%@page import="com.cy.framework.constants.WebConstants"%>
<%@page import="com.cy.common.domain.UserDomain"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>财务-费用报销-申请</title>
<style type="text/css">
html,body {overflow:hidden;}
</style>
<script type="text/javascript" src="<sys:context/>/resource/wlglpt/js/wssp_common.js" ></script>
<script type="text/javascript">
	$(document).ready(function(){
			//查询按钮事件
			$("#queryBtn").click(function(){
				onRefresh();
			});
			
			//新增按钮事件
			$("#addBtn").click(function(){
				
				var url = jcontextPath+"/cwgl/cwfybxsq!initMx.action";
				window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
				onRefresh();
			});
			//初始化表格
			initDataGrid();
						
			//批量发送
			$("#plScSendBtn").click(function(){
				var wsDm="100002";//费用登记审批表
				var check=$(":checked[name='xhs']");
				var str="";
				var jzDjxh="";
				for(var i=0;i<check.length;i++){
					var before=check[i].value; 
					var ary=before.split("#"); 
					str=ary[1];
					jzDjxh=ary[2];
					for(var j=i;j<check.length;j++){
						var after=check[j].value;
						var ary1=after.split("#");
						//alert(ary[1]+"=="+ary1[1])
						if(ary1[1]!=ary[1]){
							showAlert('只有相同"审批项目分类"的记录才能一起批量发送审批！');
							return;
						}
						if(ary1[2]!=ary[2]){
							showAlert('只有相同"记账单位"的记录才能一起批量发送审批！');
							return;
						}
					}
				}
				//alert(str+"****"+jzDjxh)
				plScSend(wsDm,str,jzDjxh);
				
			});
			

	});

    function onUpdate(cwDjxh){
    	var url = jcontextPath+"/cwgl/cwfybxsq!initMx.action?domain.cwDjxh="+cwDjxh+"&num="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:800px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		onRefresh();
    }
    
    var keyValue = "";
    var fyjzDwDjxh="";
	function onDelete( cwDjxh,jzdw){
		keyValue = cwDjxh;
		fyjzDwDjxh=jzdw;
		showConfirm("确定要删除么？","yesCallBack");
	}	
	function yesCallBack(){
		 var jsonObj = {"domain.cwDjxh":keyValue,"domain.fyjzDwDjxh":fyjzDwDjxh};
		 var url = jcontextPath+"/cwfybxsq!delete";   
         ajaxCommon(url,jsonObj , "doSuccess");  
	}
	
	function doSuccess(){     
        showAlert("删除成功！");
        onRefresh();
	}	
    
	function onViewCk(value){
		var url=jcontextPath+"/cwfybxsq!onView.action?domain.cwDjxh="+value+"&num="+Math.random();
		window.showModalDialog(url,window,"dialogHeight:500px;dialogWidth:750px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
		onRefresh();
	}
	
	/**************************************分页开始****************************************/
	//刷新当前页面
	function onRefresh(){
		 var sqrq=$("#mainForm_domain_rqQ").val();
		 var sqrz=$("#mainForm_domain_rqZ").val();
		//请求表格数据
		var url = jcontextPath+"/cwfybxsq!query.action";   
		 $("#dataList").jqGrid("setGridParam",{
		 	url:url,
		 	datatype:'json',
		 	postData:{"domain.sqrq":sqrq,"domain.rqz":sqrz}								//请求的参数，json格式
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
		    colNames:['操作','<input title="选中/取消选中" type="checkbox" onclick="checkAllJGridChk(event,this,\'xhs\');" />', '财务登记序号(SEQ_CW_DJXH)','审核','报销单号','审批项目分类','费用报销总计'
		    		,'费用记账单位','费用支付单位','申请人','申请日期',"费用报销明细"
				    ,'记账单位编码','审批序号','审批状态代码','审批流程序号'],			 //name 列显示的名称
		     //name 对应结果集里面的属性名称  index 传到服务器端用来排序用的列名称 width 列宽度；align 对齐方式；sortable   是否可以排序
		    colModel :[ 
			  {name:'hstoperationcol', index:'', sortable:false, width:'65', align:'center'},
			  {name:'checkboxoperationcol', index:'checkboxoperationcol', width:'30', align:'center'
			},
		      {name:'cwDjxh', index:'cwDjxh', width:'0', align:'center',hidden:true}, 
		      {name:'spztMc', index:'spztMc', width:'70', align:'center'}, 
		      {name:'bxdh', index:'bxdh', width:'90', align:'center'}, 
		      {name:'spxmFl', index:'spxmFl', width:'90', align:'center'}, 
		      {name:'fybxje', index:'fybxje', width:'80', align:'center'}, 
		      {name:'jzdw', index:'jzdw', width:'100', align:'center'}, 
		      {name:'jfdw', index:'jfdw', width:'100', align:'center'}, 
		      {name:'sqr', index:'sqr', width:'80', align:'center'}, 
		      {name:'sqrq', index:'sqrq', width:'80', align:'center'}, 
		      {name:'mxBz', index:'mxBz', width:'450', align:'center'}, 
		     
		     

		      
		      {name:'fyjzDwDjxh', index:'fyjzDwDjxh', width:'0', align:'center',hidden:true},
		      {name:'wsSpxh', index:'wsSpxh', width:'0', align:'center',hidden:true},
		      {name:'wsspztDm', index:'wsspztDm', width:'0', align:'center',hidden:true},
		      {name:'xmflDjxh', index:'xmflDjxh', width:'0', align:'center',hidden:true},
		    ],
		    pager: '#pager',					//定义翻页用的导航栏，必须是有效的html元素。翻页工具栏可以放置在html页面任意位置
		    rowNum: <%=rowNum%>,							//在grid上显示记录条数，这个参数是要被传递到后台
		    rowList:[20,50,100,300,500],		//一个下拉选择框，用来改变显示记录数，当选择时会覆盖rowNum参数传递到后台
		    sortname: 'cwDjxh',				//默认排序 可以是列名称或者是一个数字，这个参数会被提交到后台
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
		       	   $("#mainForm").attr("action",jcontextPath+"/cwfybxsq!download");
				   $("#mainForm").submit();
		       } 
		 });
		 
	}
	
    //表格加载完毕后触发事件：修改操作列的内容，显示“修改”、“删除”，并增加链接
	function myGridComplete() {
            var graduateIds = $("#dataList").jqGrid('getDataIDs');
            for (var i = 0; i < graduateIds.length; i++) {
                var cl = graduateIds[i];
                var xtcsSfsp = $("#mainForm_domain_xtcsSfsp").val();
                var val = jQuery("#dataList").jqGrid('getCell', cl,"cwDjxh"); 	
                var xmflDjxh = jQuery("#dataList").jqGrid('getCell', cl,"xmflDjxh");
                var jzDw = jQuery("#dataList").jqGrid('getCell', cl,"fyjzDwDjxh"); 
                var input ="";
                if ("Y" == xtcsSfsp) {
                	var wsspztDm = jQuery("#dataList").jqGrid('getCell', cl,"wsspztDm");
                	if("0"==wsspztDm || "2"==wsspztDm){
                    	input="<input type=\"checkbox\" name=\"xhs\" value=\""+val+"#"+xmflDjxh+"#"+jzDw+"\" />";
                    }else{
                    	input="<input type=\"checkbox\" name=\"xhs\" disabled=\"disabled\" value=\""+val+"#"+xmflDjxh+"#"+jzDw+"\" />";
                    }
                	$("#dataList").jqGrid('setRowData', cl, { 'checkboxoperationcol': input });
                }
                
             //获取当前单元格里面的登记序号 
                var link = "<a href=\"javascript:onUpdate('"+val+"')\"><font color=\"blue\">修改</font></a>"
                + " <a href=\"javascript:onDelete('"+val+"','"+jzDw+"')\"><font color=\"blue\">删除</font></a>"
                $("#dataList").jqGrid('setRowData', cl, { 'hstoperationcol': link }); 
            }
     }
     /**************************************分页结束****************************************/

</script>
</head>
<body>
<s:form action="cwfylb!query" namespace="" method="post" id="mainForm" name="mainForm">
<s:hidden name="domain.xtcsSfsp" />
	<div class="right_btnbg">
		<ul class="lcont">
		    <li class="no">操作：</li>
		    <li><a href="#" id="queryBtn" class="licon04">检 索</a></li>
		    <li><a href="#" id="addBtn" class="licon01">报销申请</a></li>
		      <s:if test='domain.xtcsSfsp == "Y"'>
		    <li><a href="#" id="plScSendBtn" class="licon10">批量发送审批</a></li>
	    </s:if>
		    <li><a href="#" id="closeBtn" class="licon03">关闭</a></li>
	  	</ul>
	
	  <ul class="rcont">
		    <li class="ricon02" onclick="slideToggle('syquery')">显示/隐藏查询条件</li>
		   
	  	</ul>
	</div> 

<div class="right_cont">
		 <div id="divQuery">
	<fieldset>
		<legend>查询条件</legend>
		   <table width="95%" border="0" cellspacing="0" cellpadding="0">
	        <tr>
			<td width="12%" align="right">申请日期：</td>
			<td width="30%">
				
			
				
				 <sys:dateFirstDMonth myName="domain.rqQ" myId="mainForm_domain_rqQ" myClass="ymdate" />
	          			      ～
	          		      <sys:dateCurrentDayTag myName="domain.rqZ" myId="mainForm_domain_rqZ" myClass="ymdate" />
				
			</td>
			<td>&nbsp;</td>
			<td>&nbsp;</td>
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
