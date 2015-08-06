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

<!-- ʵ��IE6��pngͼƬ͸�� -->
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
		var labelNameArray = ["����", "��˾"];
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
			if (!check) alert("ֻ��ѡ����...");
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
			//showAlert("������~~~");
			//showMaxError("������~~~");
			//checkdata();
			var url = jcontextPath + "/work/xtgl/collapsible.jsp";
			window.open(url);
		}
		
</script>
</head>

<body>
<s:form action="demo!init" namespace="/xtgl" method="post" id="mainForm" name="mainForm">

<!-- ������ -->
<div class="right_btnbg">
  <ul class="lcont">
    <li class="no">������</li>
    <li><a href="#" id="queryBtn" class="ricon02">�� ��</a></li>
    <li><a href="#" id="addBtn" class="licon01">����</a></li>
    <li><a href="#" class="licon02">ɾ��</a></li>
    <li><a href="#" id="closeBtn" class="licon03">�� ��</a></li>
  </ul>
  <ul class="rcont">
    <li class="ricon01">��ʾͼ��</li>
    <li class="ricon02" onClick="slideToggle('syquery')">��ʾ/���ز�ѯ��ʾ</li>
    <li class="ricon03">����</li>
  </ul>
</div>

<div class="right_cont">  
	<!--
  	������˵����
	  1��ͳһtable�ϼ���ʽclass="tab_css",��������<thead></thead>���ţ��ֶ��ñ�ǩ<th></th>����������<tbody></tbody>���ţ����ݵ�Ԫ����<td></td>,����table�Ͽ���ðٷֱȻ�̶�ֵ���������ֶ������������ǰһ�����ܷ����ðٷֱȣ��ֶαȽ϶���ù̶�ֵ,�����ʵ����ż����ɫ���֣�����꾭��ĳ�У�������������ɫ���֡��������Ҫ����ż����ɫ���ֵȹ��ܵ�table����class="poptab_css"�����pop_window.htmlҳ�档
	  2��ʵ��100%��
		�����б�ʵ��100%��class="select"
		�ı������ʵ��100%��class="input",���ȥ�߿��ټ�noborder��class="input noborder"(ע����class��һ���м��ÿո�)
		�ı�����ʵ��100%��class="textarea"
  	  3��ʵ�ֱ�¼��ֻ������¼,class�ֱ�Ϊbgstyle_required(��¼)��bgstyle_readonly(ֻ��),bgstyle_optional(��¼)
   --> 
  
  <!-- ��ѯ����divQuery������ʾ���ز�ѯ����IDֵ����������ȡ�߶Ȳ����޸� -->
    <div id="divQuery">
    <fieldset>
      <legend>��ѯ����</legend>
      	<table width="95%" border="0" cellspacing="0" cellpadding="0">
      		<tr>
	          <td width="15%" align="right">��Ʊ���ڣ�</td>
	          <td width="40%"><input class="ymdate" type="text" />
	          	��
	          <input class="ymdate" type="text" /></td>
	          <td width="15%" align="right">����ѡ��</td>
	          <td width="30%"><input class="ymonth" type="text" /></td>
	        </tr>
	        <tr>
	          <td align="right">���ѡ��</td>
	          <td><input class="year" type="text" /></td>
	          <td align="right">�·�ѡ��</td>
	          <td><input class="month" type="text" /></td>
	        </tr>
	        <tr>
	          <td align="right">��ʱ�����ڣ�</td>
	          <td><input class="ymdatetime" type="text" /></td>
	          <td align="right">ʱ��ѡ��</td>
	          <td><input class="time" type="text" /></td>
	        </tr>
	        <tr>
	          <td width="15%" align="right">��˾��</td>
	          <td width="35%">
	          	<sys:gsList myId="gs" myName="gs" onChange="bmInit(this.value, '', 'bm', 'bm', 'Y', 'N')" mcContainDmBz="N" myClass="select"/>
			  </td>
			  <td width="15%" align="right">���ţ�</td>
	          <td width="35%">
	           	<select id="bm" name="bm" class="select" onchange="gwInit(this.value, '', 'gw', 'gw', 'Y', 'N')"></select>
			  </td>
	        </tr>
	        <tr>
	          <td width="15%" align="right">��λ��</td>
	          <td width="35%">
	          	<select id="gw" name="gw" class="select"></select>
			  </td>
			  <td width="15%" align="right"></td>
	          <td width="35%">
	           	<button type="button" class="pop_btnbg" onclick="onTc();">������</button>
			  </td>
	        </tr>
	        <tr>
	          <td align="right"> ��˾���ţ�</td>
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
						<li class="title">&nbsp;&nbsp;���У�<input id="citySel" name="city" type="text" readonly value="" style="width:220px; height: 20px;"/>
					&nbsp;<a id="menuBtn" href="#" onclick="showMenu(); return false;">ѡ��</a></li>
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
