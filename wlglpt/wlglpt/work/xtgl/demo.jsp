<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.cy.common.domain.UserDomain"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK" />
<title>demo</title>
<%@ include file="/common/meta.jsp"%>

<link rel="stylesheet" href="<sys:context />/resource/pageframe/com/jquery.ztree/zTreeStyle.css" type="text/css" />
<link rel="stylesheet" href="<sys:context />/resource/pageframe/com/jquery.ztree/demo.css" type="text/css" />
<script type="text/javascript" src="<sys:context />/resource/pageframe/com/jquery.ztree/jquery.ztree.core-3.5.js"></script>

<style type="text/css">
html,body {overflow:hidden;}
</style>

<!-- 实现IE6下png图片透明 -->
<!--[if IE 6]>
<script src="<sys:context />/resource/pageframe/js/DD_belatedPNG_0.0.8a-min.js" type="text/javascript"></script>
<script type="text/javascript">
	DD_belatedPNG.fix('h2, a');
</script>
<![endif]-->

<script type="text/javascript">
	$(document).ready(function(){
		var sjJgbm = $("#gs").val();
		bmInit(sjJgbm, '', "bm", "bm", "Y", "N");
		
		var bm = $("#bm").val();
		gwInit(bm, '', "gw", "gw", "Y", "N");
		
		$("#addBtn").click(function(){
			var url = jcontextPath + "/xtgl/demo!initMx";
		    window.showModalDialog(url,window,"dialogHeight:420px;dialogWidth:760px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
		    onRefresh();
		});
	});
	
	function onUpdate(){
		
		var url = jcontextPath + "/xtgl/demo!initMx";
		    window.showModalDialog(url,window,"dialogHeight:420px;dialogWidth:760px;center:yes;resizable:no;status:no;scroll:yes;help:no;minimize:yes;")
		    onRefresh();
    }
	
	function checkdata(){
		var controlNameArray = ["city", "gs"];
		var labelNameArray = ["城市", "公司"];
		var compareValueArray = [10, 20];
		var nodeTypeArray = [NodeType.STRING,NodeType.STRING];
		var notNullArray = [true,true];				
		var dataValidity = new DataValidity(mainForm, controlNameArray, labelNameArray, compareValueArray, nodeTypeArray, notNullArray);		
		return dataValidity.isValidate();
	}

	var setting = {
			view: {
				dblClickExpand: false
			},
			data: {
				simpleData: {
					enable: true
				}
			},
			callback: {
				beforeClick: beforeClick,
				onClick: onClick
			}
		};

		var zNodes = <%= request.getAttribute("domain.ztreeNodes") %>;

		function beforeClick(treeId, treeNode) {
			var check = (treeNode && !treeNode.isParent);
			if (!check) alert("只能选择部门...");
			return check;
		}
		
		function onClick(e, treeId, treeNode) {
			var zTree = $.fn.zTree.getZTreeObj("treeDemo"),
			nodes = zTree.getSelectedNodes(),
			v = "";
			ids = "";
			nodes.sort(function compare(a,b){return a.id-b.id;});
			for (var i=0, l=nodes.length; i<l; i++) {
				v += nodes[i].name + ",";
				ids += nodes[i].id + ",";
			}
			if (v.length > 0 ) {
				v = v.substring(0, v.length-1);
			}
			if (ids.length > 0) {
				ids = ids.substring(0, ids.length-1);
			}
			$("#citySel").val(ids + " " + v);
		}

		function showMenu() {
			var cityObj = $("#citySel");
			var cityOffset = $("#citySel").offset();
			$("#menuContent").css({left:cityOffset.left + "px", top:cityOffset.top + cityObj.outerHeight() + "px"}).slideDown("fast");

			$("body").bind("mousedown", onBodyDown);
		}
		function hideMenu() {
			$("#menuContent").fadeOut("fast");
			$("body").unbind("mousedown", onBodyDown);
		}
		function onBodyDown(event) {
			if (!(event.target.id == "menuBtn" || event.target.id == "menuContent" || $(event.target).parents("#menuContent").length>0)) {
				hideMenu();
			}
		}

		$(document).ready(function(){
			$.fn.zTree.init($("#treeDemo"), setting, zNodes);
		});
		
		function onTc() {
			//showAlert("弹出框~~~");
			//showMaxError("弹出框~~~");
			//checkdata();
			var url = jcontextPath + "/work/xtgl/collapsible.jsp";
			window.open(url);
		}
		
</script>
</head>

<body>
<s:form action="demo!init" namespace="/xtgl" method="post" id="mainForm" name="mainForm">

<!-- 操作区 -->
<div class="right_btnbg">
  <ul class="lcont">
    <li class="no">操作：</li>
    <li><a href="#" id="queryBtn" class="ricon02">检 索</a></li>
    <li><a href="#" id="addBtn" class="licon01">增加</a></li>
    <li><a href="#" class="licon02">删除</a></li>
    <li><a href="#" id="closeBtn" class="licon03">关 闭</a></li>
  </ul>
  <ul class="rcont">
    <li class="ricon01">显示图形</li>
    <li class="ricon02" onClick="slideToggle('syquery')">显示/隐藏查询提示</li>
    <li class="ricon03">帮助</li>
  </ul>
</div>

<div class="right_cont">  
	<!--
  	表格相关说明：
	  1、统一table上加样式class="tab_css",标题行用<thead></thead>包着，字段用标签<th></th>；数据行用<tbody></tbody>包着，内容单元格用<td></td>,至于table上宽度用百分比或固定值来做根据字段来定。如果当前一屏下能放下用百分比，字段比较多就用固定值,本表格实现奇偶行颜色区分，当鼠标经过某行，或点击都会有颜色区分。如果不需要有奇偶行颜色区分等功能的table上引class="poptab_css"，详见pop_window.html页面。
	  2、实现100%：
		下拉列表实现100%用class="select"
		文本输入框实现100%用class="input",如果去边框再加noborder，class="input noborder"(注意多个class放一起中间用空格)
		文本区域实现100%用class="textarea"
  	  3、实现必录，只读，可录,class分别为bgstyle_required(必录)，bgstyle_readonly(只读),bgstyle_optional(可录)
   --> 
  
  <!-- 查询条件divQuery功能显示隐藏查询条件ID值及根据内容取高度不能修改 -->
    <div id="divQuery">
    <fieldset>
      <legend>查询条件</legend>
      	<table width="95%" border="0" cellspacing="0" cellpadding="0">
      		<tr>
	          <td width="15%" align="right">开票日期：</td>
	          <td width="40%"><input class="ymdate" type="text" />
	          	～
	          <input class="ymdate" type="text" /></td>
	          <td width="15%" align="right">年月选择：</td>
	          <td width="30%"><input class="ymonth" type="text" /></td>
	        </tr>
	        <tr>
	          <td align="right">年份选择：</td>
	          <td><input class="year" type="text" /></td>
	          <td align="right">月份选择：</td>
	          <td><input class="month" type="text" /></td>
	        </tr>
	        <tr>
	          <td align="right">带时间日期：</td>
	          <td><input class="ymdatetime" type="text" /></td>
	          <td align="right">时间选择：</td>
	          <td><input class="time" type="text" /></td>
	        </tr>
	        <tr>
	          <td width="15%" align="right">公司：</td>
	          <td width="35%">
	          	<sys:gsList myId="gs" myName="gs" onChange="bmInit(this.value, '', 'bm', 'bm', 'Y', 'N')" mcContainDmBz="N" myClass="select"/>
			  </td>
			  <td width="15%" align="right">部门：</td>
	          <td width="35%">
	           	<select id="bm" name="bm" class="select" onchange="gwInit(this.value, '', 'gw', 'gw', 'Y', 'N')"></select>
			  </td>
	        </tr>
	        <tr>
	          <td width="15%" align="right">岗位：</td>
	          <td width="35%">
	          	<select id="gw" name="gw" class="select"></select>
			  </td>
			  <td width="15%" align="right"></td>
	          <td width="35%">
	           	<button type="button" class="pop_btnbg" onclick="onTc();">弹出框</button>
			  </td>
	        </tr>
	        <tr>
	          <td align="right"> 公司部门：</td>
	          <td>
	          	  <sys:gsBmList myId="gsbm" myName="gsbm" myClass="select" mcContainDmBz="N" />
			  </td>
			  <td align="right">
			  	
			  </td>
	          <td>
	          	
			  </td>
	        </tr>
	        <tr>
	        	<td colspan="4">
	        		<ul class="list">
						<li class="title">&nbsp;&nbsp;城市：<input id="citySel" name="city" type="text" readonly value="" style="width:220px; height: 20px;"/>
					&nbsp;<a id="menuBtn" href="#" onclick="showMenu(); return false;">选择</a></li>
					</ul>
	        	</td>
	        </tr>
	      </table>
	  </fieldset>
  </div>
  
  
  
  <%@include file="/common/message.jsp" %>
</div>
</s:form>
</body>
</html>
