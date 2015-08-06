<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ include file="/common/include.jsp"%>
<%@ page contentType="text/html;charset=GBK"%>

<head>
<title>功能菜单</title>
<%@ include file="/common/meta.jsp"%>
<script type="text/javascript" src="<sys:context />/resource/pageframe/js/ddaccordion.js"></script>

<style type="text/css">
html,body {height:100%; background:#e6f3f9;}
</style>
	
<script type="text/javascript">
$(document).ready(function(){

	initTrees('');
	$('#tree').lightTreeview({
		collapse: true,
		line: true,
		nodeEvent: true,
		unique: false,
		style: 'black',
		animate: 400
	});
		
	});
	
	var ss='';
	function checkXt(tree){
		var domain=tree.domain;
		var str=tree.domain.dataList;
		$.each(str, function(i, hw){
			var obj=hw.xzxmList;
			$.each(obj,function(ii,list){
				if(hw.sjxlbDm=='23'){
					//alert(hw.xzxmDm+"--"+hw.csmrz)		
					//alert(list.xzxmValueDm+"--"+list.xzxmDm);
					if(hw.xzxmDm==list.xzxmDm&&hw.csmrz==list.xzxmValueDm){
						if(list.xzxmValueMc=='按树形菜单显示'){
							$("#tree")[0].style.display='block';
							ss='1';
						} 
						if(list.xzxmValueMc=='按折叠菜单显示'){
								ss='2';
								var curList = $("#explore-nav li a.current").attr("rel");
						   		var $newList = $("#zhed");
						        $("#explore-nav li a").removeClass("current");
						        $newList.addClass("current");
						        var listID = $newList.attr("rel");
						        if (listID != curList) {
						        	$("#"+curList).css({"display":"none"});
						        	$("#"+listID).css({"display":"block"});
						        }        
						        document.getElementById("pmcon").style.display="block";
								document.getElementById("tmcon").style.display="none";
						}
						if(ss=='1'){
							if(list.xzxmValueMc=='全部展开'){
								selectqzk1();
							}
							 if(list.xzxmValueMc=='全部折叠'){
								checkqzd1();
							}
						}
						else if(ss=='2'){
							if(list.xzxmValueMc=='全部展开'){
								selectqzk2();
							}
							 if(list.xzxmValueMc=='全部折叠'){
								 checkqzd2();
							}
						}
					}
				}
			}) 
			
		})
	}

	 function selectqzk1(){
		 $.lightTreeview.open('#tree ol,#tree ul');
	  } 
	 function checkqzd1(){
		$.lightTreeview.close('#tree ol,#tree ul');
	 }
	 function selectqzk2(){
		 $.lightTreeview.open('#pulldown ul');
	 }
	 function checkqzd2(){
		$.lightTreeview.close('#pulldown ul');
	 }
	
	function initTrees(xtmlXh) {
		
		var url = jcontextPath + "/xtgl/login!treeView";
		var jsonObj = {"loginUserDomain.xtmlXh":xtmlXh};
		ajaxCommon(url,jsonObj,"doUserMenuSuccess");
	}
	
	function doUserMenuSuccess(data) {
		var latestOprMenuList = data.loginUserDomain.latestOprMenuList;
		updateSubMenu(data);
		updateLatestOprMenu(latestOprMenuList);
	}
	
	function updateLatestOprMenu(latestOprMenuList) {
		$("#latestOpr").empty();
	
		$.each(latestOprMenuList, function(i, obj){
			var liObj = $("<li></li>")
			$(liObj).appendTo($("#latestOpr"));
			
			var url = obj.url;
			if (url.indexOf("?") > 0) {
				url = url + "&";
			}else {
				url = url + "?";
			}
			url = url + "domain.gnmkDm="+obj.gnmkDm+"&domain.xtmlXh="+obj.sjMenuDm;
			
			var clickMethod = "navigateMenu('"+url+"','"+obj.gnmkMc+"','true')"; 
			var aObj = $("<a href=\"#\" onclick=\""+clickMethod+"\"></a>");
			$(aObj).text(obj.gnmkMc);
			$(aObj).appendTo($(liObj));
			
		});
	}
	
	function updateSubMenu(data) {
		var subMenu = data.loginUserDomain.subMenuList;
		$("#tree").empty();
		$("#pulldown").empty();
		
		$("<div><img src=\""+jcontextPath+"/resource/pageframe/images/micon_home.gif\" /> <strong>我的权限</strong></div>").appendTo($("#tree"));
		
		var olObj;
		var ulPullDown;
		$.each(subMenu, function(i, obj){
			var node= obj.node;
			var liObj = $("<li></li>");
			var divObj;// = $("<div></div>");
			
			var divPullDown;
			var liPullDown;
			if ("Y" == node) {
				//树形菜单父节点------begin---------
				divObj = $("<div class=\"treeview-folder\"></div>");
				$(divObj).text(obj.gnmkMc);
				$(divObj).appendTo(liObj);
				olObj = $("<ol></ol>");
				//默认第一节打开
				
				if(i == 0) {
					
					$(olObj).css("display","block");
				}else {
					$(olObj).css("display","none");
				}
				$(olObj).appendTo(liObj);
				$(liObj).appendTo($("#tree"));
				//树形菜单父节点------end---------
				
				//accordion菜单父节点------begin---------
				divPullDown = $("<div class=\"pulldownmenut\" onclick=\"pullDownClick(this)\"></div>");
				$(divPullDown).text(obj.gnmkMc);
				
				$(divPullDown).appendTo($("#pulldown"));
				ulPullDown = $("<ul class=\"pulldownmenuc\"></ul>");
				$(ulPullDown).appendTo($("#pulldown"));
				//accordion菜单父节点------end---------
				
			}else {
			
				//树形菜单子节点------begin---------
				var url = obj.url;
				if (url.indexOf("?") > 0) {
					url = url + "&";
				}else {
					url = url + "?";
				}
				url = url + "domain.gnmkDm="+obj.gnmkDm+"&domain.xtmlXh="+obj.sjMenuDm;
				var clickMethod = "navigateMenu('"+url+"','"+obj.gnmkMc+"','true')";
				divObj = $("<div class=\"treeview-file\" onclick=\""+clickMethod+"\"></div>")
				$(divObj).text(obj.gnmkMc);
				$(divObj).appendTo(olObj);
				//树形菜单子节点------begin---------
				
				//accordion菜单子节点------begin---------
				var liPullDownMethod = "navigateMenu('"+url+"','"+obj.gnmkMc+"','true')";
				liPullDown = $("<li onclick=\""+liPullDownMethod+"\"></li>");
				$(liPullDown).text(obj.gnmkMc);
				$(liPullDown).appendTo($(ulPullDown));
				//accordion菜单子节点------begin---------
			}
			
		});
		
		$('#tree').lightTreeview({
			collapse: true,
			line: true,
			nodeEvent: true,
			unique: false,
			style: 'black',
			animate: 400
		});
		
		//展开第一节
		$("#pulldown div").eq(0).next("ul.pulldownmenuc").show(200);
		var url = jcontextPath+"/xtgl/xtsz!initMxx"; 
		var jsonObj={"":""};
		ajaxCommon(url,jsonObj,"checkXt");
	}
	

	
	function pullDownClick(obj) {
		
		$(obj).next("ul.pulldownmenuc").toggle(200);
		
		$(obj).siblings().next("ul.pulldownmenuc").hide(200);
	}
	
	ddaccordion.init({
		headerclass: "pulldownmenut", //Shared CSS class name of headers group that are expandable
		contentclass: "pulldownmenuc", //Shared CSS class name of contents group
		revealtype: "click", //Reveal content when user clicks or onmouseover the header? Valid value: "click", "clickgo", or "mouseover"
		mouseoverdelay: 170, //if revealtype="mouseover", set delay in milliseconds before header expands onMouseover
		collapseprev: true, //Collapse previous content (so only one open at any time)? true/false 
		defaultexpanded: [0], //index of content(s) open by default [index1, index2, etc]. [] denotes no content
		onemustopen: false, //Specify whether at least one header should be open always (so never all headers closed)
		animatedefault: false, //Should contents open by default be animated into view?
		persiststate: true, //persist state of opened contents within browser session?
		toggleclass: ["", "pulldownopen"], //Two CSS classes to be applied to the header when it's collapsed and expanded, respectively ["class1", "class2"]
		togglehtml: ["prefix", "", ""], //Additional HTML added to the header when it's collapsed and expanded, respectively  ["position", "html1", "html2"] (see docs)
		animatespeed: "fast", //speed of animation: integer in milliseconds (ie: 200), or keywords "fast", "normal", or "slow"
		oninit:function(headers, expandedindices){ //custom code to run when headers have initalized
			//do nothing
		},
		onopenclose:function(header, index, state, isuseractivated){ //custom code to run whenever a header is opened or closed
			//do nothing
		}
	})
	
	<!-- 点击左侧菜单在右侧显示 -->
	function navigateMenu(a,b,c){
		parent.parent.document.getElementById("FRM_RIGHT").contentWindow.navigate(a,b,c);
	}

	<!-- 树型与折叠菜单屏开与合并 -->
	function tm() {
		document.getElementById("tmcon").style.display="block";
		document.getElementById("pmcon").style.display="none";
	}
	function pm() {
		document.getElementById("pmcon").style.display="block";
		document.getElementById("tmcon").style.display="none";
	}

	<!-- 根据分辨率取高度 -->
	function setHeight(){
		var h=document.documentElement.clientHeight - 61;
		var hight=document.getElementById("tree");
		var highp=document.getElementById("pulldown");
		hight.style.height=h+"px";
		highp.style.height=h+"px";
	   }
	   window.onload=setHeight;
	   window.onresize=setHeight;
	 
	function getCdDh(){
		var url=jcontextPath+"/xtgl/login!initCdDh";
		window.showModalDialog(url,window,"dialogHeight:680px;dialogWidth:820px;center:yes;resizable:no;status:no;scroll:yes;help:no;")
	}   
</script>
</head>

<body>
<!-- 功能菜单 -->
<div class="mainleft">
  <div class="menu_pic">
    <div class="ltmenu">
      <div class="mtitle"><h2><img src="<sys:context/>/resource/pageframe/images/icon_ls.gif" />最近操作</h2>
        <div class="ltmenucont" id="ltmenucont">
          <ul id="latestOpr">
          </ul>
        </div>
      </div>
    </div>
  </div>
  <ul id="tree" class="lightTreeview treeview-black treemenu" style="display: none">
    
  </ul>
  
  <div id="pulldown" class="pulldownmenu">
    
  </div>
</div>
</div>

<div class="leftcbtm">
  <div id="explore-nav" class="leftcbtm01">
    <li id="treemenu"><a href="#" id="shux" rel="tree" title="树形菜单"  class="current"  onClick="tm()" onFocus="this.blur()"><img src="<sys:context/>/resource/wlglpt/images/lmenu_icon01.png" /></a></li>
    <li id="pulldownmenu"><a href="#" id="zhed" rel="pulldown" title="折叠菜单" onClick="pm()"  onFocus="this.blur()"><img src="<sys:context/>/resource/wlglpt/images/lmenu_icon02.png" /></a></li>
  </div>
  <ul id="tmcon" class="leftcbtm02">
    <li><a href="#" title="功能菜单导航" onclick="getCdDh()" onFocus="this.blur()"><img src="<sys:context/>/resource/wlglpt/images/lmenu_icon03.png" /></a></li>
    <li><a href="#"  title="全部展开" onclick="$.lightTreeview.open('#tree ol,#tree ul')" onFocus="this.blur()"><img src="<sys:context/>/resource/wlglpt/images/lmenu_icon04.png" /></a></li>
    <li><a href="#" title="全部折叠" onclick="$.lightTreeview.close('#tree ol,#tree ul')" onFocus="this.blur()"><img src="<sys:context/>/resource/wlglpt/images/lmenu_icon05.png" /></a></li>
  </ul>
  <ul id="pmcon" class="leftcbtm02" style="display:none;">
    <li><a href="#" title="功能菜单导航" onclick="getCdDh()"><img src="<sys:context/>/resource/wlglpt/images/lmenu_icon03.png" /></a></li>
    <li><a href="#"  title="全部展开" onclick="$.lightTreeview.open('#pulldown ul')" onFocus="this.blur()"><img src="<sys:context/>/resource/wlglpt/images/lmenu_icon04.png" /></a></li>
    <li><a href="#" id="qzd" title="全部折叠" onclick="$.lightTreeview.close('#pulldown ul')" onFocus="this.blur()"><img src="<sys:context/>/resource/wlglpt/images/lmenu_icon05.png" /></a></li>
  </ul>
</div>
 <%@include file="/common/message.jsp" %>
</body>
</html>
