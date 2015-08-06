<html>
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>工作区</title>
<style type="text/css">
.tab {background:url(<sys:context/>/resource/images/tab1.gif) no-repeat; cursor:pointer; font-size:12px; }
.tab_sel {background:url(<sys:context/>/resource/images/tab2.gif) no-repeat; cursor:pointer; font-size:12px;}
.pane {display:table-row;}
.pane_hide {display:none;}
</style>
<script type="text/javascript">

//当前被选中
var SELECTED_INDEX = -1;
//最大Tab个数
var MAX_TAB_COUNT = 11;

var TAB_LIST = new Array();


function init(){
	var url=jcontextPath+'/dbsx/dbsxYbsx!initTab';
	navigate(url,"待办事项","true");
}

//window.onload=init;

/**
 * 点击左右滚动时执行
 * @param dir 滚动方向
 */
function scrollTab(dir){
    var tabContainerObject=document.getElementById("tabContainer");
	if(tabContainerObject.doScroll){
    	tabContainerObject.doScroll(dir);//firefox下不支持doscroll函数
	}
	flagDir();
}

/**
 * 指示出被隐藏的当前Tab页的方向
 */
function flagDir(){

    if(SELECTED_INDEX == -1){
		return;
	}
    var  tabListObject=document.getElementById("tabList");
    var tabContainerObject=document.getElementById("tabContainer");
    var toLeftObject=document.getElementById("toLeft");
    var toRightObject=document.getElementById("toRight");

    var oCel = tabListObject.cells(SELECTED_INDEX);

	toLeftObject.src = "<sys:context/>/resource/images/arrow1.gif";
	toRightObject.src = "<sys:context/>/resource/images/arrow2.gif";
	//
	if((oCel.offsetLeft > (tabContainerObject.scrollLeft+tabContainerObject.offsetWidth))){
		toRightObject.src = "<sys:context/>/resource/images/arrow2.gif";
	}else if((oCel.offsetLeft+oCel.offsetWidth) < tabContainerObject.scrollLeft){
		toLeftObject.src = "<sys:context/>/resource/images/arrow1.gif";
	}
}

/**
 * 滚动的方向标鼠标事件处理
 * @param obj 对象
 */
function scroll_doMouseOver(obj){
	var flag = "1";
	var toRightObject=document.getElementById("toRight");
	if(obj == toRightObject){
		flag = "2";
	}
	if(obj.src.lastIndexOf("arrow"+flag+"_2.gif") != -1){
		return;
	}
	obj.src = "<sys:context/>/resource/images/arrow"+flag+"_1.gif";
}

/**
 * 滚动的方向标鼠标事件处理
 * @param obj 对象
 */
function scroll_doMouseOut(obj){
	var flag = "1";
	var toRightObject=document.getElementById("toRight");
	if(obj == toRightObject){
		flag = "2";
	}
	if(obj.src.lastIndexOf("arrow"+flag+"_2.gif") != -1){
		return;
	}
	obj.src = "<sys:context/>/resource/images/arrow"+flag+".gif";
}

/**
 * 指定的索引Tab滚动到当前位置
 *
 */
function scrollToView(){
    var  tabListObject=document.getElementById("tabList");
    var tabContainerObject=document.getElementById("tabContainer");
    var toLeftObject=document.getElementById("toLeft");
    var toRightObject=document.getElementById("toRight");

    toLeftObject.src = "<sys:context/>/resource/images/arrow1.gif";
	toRightObject.src = "<sys:context/>/resource/images/arrow2.gif";
	var idx = getSelectedIndex();
	if(idx != -1){
		tabContainerObject.scrollLeft = tabListObject.cells(idx).offsetLeft;
	}
}


function getTabParam(flag,idx){
	var aList = TAB_LIST[idx];
	if(aList != null){
		return aList[flag];
	}
	return null;
}


function containsTab(title){
	var idx = locateTabParam("TITLE",title);
	return (idx == -1)?false:true;
}


function reOpenSelected(title){
	var idx = locateTabParam("TITLE",title);
	if(idx!=-1){
		setSelectedIndex(idx);
	}
}


function locateTabParam(flag,paramVal){
	for(var i=0;i<TAB_LIST.length;i++){
		if(TAB_LIST[i][flag] == paramVal){
			return i;
		}
	}
	return -1;
}

/**
 * 点击Tab页时选中它
 */
function tab_doClick(evt){
    //获取事件
    evt=evt?evt:(window.event?window.event:null);
    //IE和Firefox的事件属性不同
    var  obj = evt.srcElement ? evt.srcElement : evt.target;
	if(obj.nodeName != "TD"){
		obj = obj.parentNode;
	}
	setSelectedIndex(obj.cellIndex);
	//obj.onclick=closeWork;
}


/** 设置当前的Tab，
 * @param idx 索引
 */
//function setSelectedIndex(idx){
//    var  tabListObject=document.getElementById("tabList");
//    //使新的Tab被选中
//	oCel.className = "tab_sel";
//	labPanel.innerText = getTabParam("TITLE",idx);
//	setPanelsVisibility(true,idx);
//	SELECTED_INDEX = idx;
//}

/** 增加Tab
 * @param sTitle Tab标题
 * @param sIcon 图标路径
 * @param sUrl 链接路径。
 */
function addTab(sTitle,sIcon,sUrl){
    var tabListObject=document.getElementById("tabList");
    var tabContainerObject=document.getElementById("tabContainer");
    //添加到最后
    var oCel = tabListObject.insertCell(TAB_LIST.length);
	oCel.noWrap = true;
	oCel.title = sTitle;
	oCel.style.width="120px";
	oCel.style.textAlign="center";
	oCel.style.paddingLeft="3px";
	oCel.className = "tab";
    //兼容IE及Firefox
    oCel.onclick=tab_doClick;
    oCel.ondblclick=closeWork;
	//oCel.ondblclick=resizeWork(document.getElementById("maxImg"));

    //oCel.attachEvent("oncontextmenu",tab_doMenu);
	//sfj 修改此处的 ondblclick=\"resizeWork(img_switch)\" 方法
	var titleSegment = (sTitle.length > 6)? sTitle.substring(0,6)+".." : sTitle;

	//oCel.innerHTML = "<LABEL  oncontextmenu=\"return false;\" onselectstart=\"return false;\">"+titleSegment+"&nbsp;</LABEL>";
	//var imgI=getTabCount();
	//oCel.innerHTML = "<LABEL onmouseover=\"this.style.color='#4A87BF'\" onmouseout=\"this.style.color='black'\" >"+titleSegment+"&nbsp;</LABEL><IMG src='images/close_out.gif' onmouseover='src=\"images/close_over.gif\"' onmouseout='src=\"images/close_out.gif\"' onclick=\"closeWork(tab_doClick())\" />";
	oCel.innerHTML = "<LABEL oncontextmenu=\"return false;\" onselectstart=\"return false;\">"+titleSegment+"&nbsp;</LABEL><IMG src='<sys:context/>/resource/images/close_out.gif' onmouseover='src=\"<sys:context/>/resource/images/close_over.gif\"' onmouseout='src=\"<sys:context/>/resource/images/close_out.gif\"' onclick=\"closeWork(tab_doClick())\" />";


	
	var aParams = new Array(3);
	aParams["TITLE"] = sTitle;
	aParams["URL"] = sUrl;
	
	aParams["ID"] = createTabId();
	setTabParams(aParams,oCel.cellIndex);
	
	addPanels(sTitle,sIcon,sUrl);
	setSelectedIndex(oCel.cellIndex);
	tabContainerObject.scrollLeft = oCel.offsetLeft;
}


/** 设置当前的Tab，
 * @param idx 索引
 */
function setSelectedIndex(idx){
    var tabListObject=document.getElementById("tabList");
    var tabContainerObject=document.getElementById("tabContainer");
<%--    var toLeftObject=document.getElementById("toLeft");--%>
<%--    var toRightObject=document.getElementById("toRight");--%>
<%--    var labPanelObject=document.getElementById("labPanel");--%>

    //alert("selectedIndex:"+idx);
<%--	toLeftObject.src = "<sys:context/>/resource/images/arrow1.gif";--%>
<%--	toRightObject.src = "<sys:context/>/resource/images/arrow2.gif";--%>
	if(idx == -1){
		return ;
	}
	//自动滚动Tab页
	var oCel = tabListObject.cells[idx];

<%--	if((oCel.offsetLeft+oCel.offsetWidth) >= (tabContainerObject.scrollLeft+tabContainerObject.offsetWidth)){--%>
<%--		if(tabContainerObject.doScroll){--%>
<%--			tabContainerObject.doScroll("pageRight");--%>
<%--		}//firefox下不支持doScroll--%>
<%--	}else if(oCel.offsetLeft <= tabContainerObject.scrollLeft){--%>
<%--		if(tabContainerObject.doScroll){--%>
<%--			tabContainerObject.doScroll("pageLeft");//firefox下不支持doScroll--%>
<%--		}--%>
<%--	}--%>
<%--	if(SELECTED_INDEX == idx){--%>
<%--		return;--%>
<%--	}--%>

	if(SELECTED_INDEX != -1){
		tabListObject.cells[SELECTED_INDEX].className = "tab";
		setPanelsVisibility(false,SELECTED_INDEX);
	}
	//使新的Tab被选中
	oCel.className = "tab_sel";
	//labPanelObject.innerText = getTabParam("TITLE",idx);
	setPanelsVisibility(true,idx);
	SELECTED_INDEX = idx;
}

/** 需要调整Panels的可见性
 * @param bl true/false
 * @param idx 索引
 */
function setPanelsVisibility(bl,idx){
    var workPanelObject=document.getElementById("workPanel");
    var state = bl? "pane" : "pane_hide";
	//alert(idx+":"+state);
	//设置外挂Panel的状态
	var rowIdx = idx;
	workPanelObject.rows[rowIdx].className = state;
//	workPanelObject.rows[rowIdx+1].className = state;
//    var rowIdx = 2*idx;
//	workPanelObject.rows[rowIdx].className = state;
//	workPanelObject.rows[rowIdx+1].className = state;
}

/**
 * 列号
 */
function createTabId(){
	return Math.random().toString();
}

function setTabParams(aParams,idx){
	TAB_LIST[idx] = aParams;
}

/** 在增加相应地增加面板
 * @param sTitle Tab标题
 * @param sIcon 图标路径
 * @param sUrl 链接路径
 */
function addPanels(sTitle,sIcon,sUrl){


    var workPanelObject=document.getElementById("workPanel");
    //获取总的行数
    var countrows=workPanelObject.rows.length;
//    alert("cout rows="+countrows);
    //插入到最后
    var oRow = workPanelObject.insertRow(countrows);
	setPanelsVisibility(false,oRow.rowIndex);
//    var oRow1 = workPanelObject.insertRow(countrows+1);
//	setPanelsVisibility(false,oRow.rowIndex/2);
	var oCel = oRow.insertCell(0);
	oCel.innerHTML = "<iframe frameBorder='0' width='100%' height='100%' src='"+sUrl+"' >对不起，您的浏览器不支持IFRAME!</iframe>";

	//以下消息不需要
//	oRow1.valign = "bottom";
//	oRow1.style.height = 0;
//	oCel = oRow1.insertCell(0);
//	oCel.noWrap = true;
}


/** 为每个工作区增加监听器
 * @param obj 工作区iframe对象
 */
function stateListener(obj){
	if (obj.readyState == "complete"){
		//showLoading(false);
		//如果Title为null则加载页面的title
	}else if(obj.readyState == "loading"){
		//showLoading(true);
	}
}

/** 显示/隐藏加载信息
 * @param bl true/false
 */
function showLoading(bl){
	var state = bl ? "block" : "none";
	LOADING.style.display = state;
}


/** 导航页面
 * @param sUrl 链接地址
 * @param sTitle 链接的标题
 * @param bl 是否在新窗口打开工作区
 */
function navigate(sUrl,sTitle,bl){
	//判断以前是否已经新增过，没有则新增
	if(containsTab(sTitle)){
		if(!confirm("工作区["+sTitle+"]已经存在，\n您确定还要打开新的工作区吗？")){
			reOpenSelected(sTitle);
			return;
		}
	}
	if(getTabCount() < MAX_TAB_COUNT){
		addTab(sTitle,"<sys:context/>/resource/images/state1.gif",sUrl);
	} else{
		alert("系统提示：\n您最多可以打开["+MAX_TAB_COUNT+"]个工作区\n请关闭多余的工作区");
	}
	//调用内部方法
}

/** 返回当前的Tab数
 *
 */
function getTabCount(){
    var  tabListObject=document.getElementById("tabList");
    return tabListObject.cells.length;
}

////////////////
//var sizeWholeOrg = "65,*";//"53,*,20";
var sizeWholeOrg = "95,*";
var sizeWholeMax = "0,*";//"0,*,20";
var sizeMiddleOrg = "180,*";
var sizeMiddleMax = "0,*";
var sizeMiddleOld = "0,*";
var leftOpened = false;

/** 点击图片时改变工作区大小
 * @param obj 点击的图片对象
 */
function resizeWork(obj){
    var wholeObject=top.document.getElementById("FRM_WHOLE");
    var middleObject=top.document.getElementById("FRM_MIDDLE");
    if(wholeObject.rows == sizeWholeOrg){
		wholeObject.rows = sizeWholeMax;
		sizeMiddleOld = middleObject.cols;
		middleObject.cols = sizeMiddleMax;
		obj.src = "<sys:context/>/resource/images/min.gif";
		//obj.innerHTML = "2";
		obj.title = "还原";
	}else{
		wholeObject.rows = sizeWholeOrg;
		middleObject.cols = sizeMiddleOld;//
		obj.src = "<sys:context/>/resource/images/max.gif";
		obj.title = "工作区全屏";
	}
document.body.fireEvent("onresize");//zxm 加入，激发right窗口的resize事件，让页面捕获后调整相应的div高度。
}
//激发事件 可以让查询项目隐藏
function fireFolder(){
	/*for(i=0;i<document.getElementById("workPanel").getElementsByTagName("iframe").length;i++){
		document.getElementById("workPanel").getElementsByTagName("iframe")[i].contentWindow.document.body.fireEvent("onhelp");
	}*/
	obj=window.frames(getSelectedIndex()).document.getElementById("tab_in");
	if(obj==null) {
	window.frames(getSelectedIndex()).document.body.fireEvent("onhelp");
	}
	else{
	obj.contentWindow.document.body.fireEvent("onhelp");}
}

/** onmouseover时，改变图片
 * @param obj 图片对象
 * @param flag 标志onmouseover是哪个图片
 */
function resizeImage(obj,flag){
    var wholeObject=top.document.getElementById("FRM_WHOLE");
    if(wholeObject.rows == sizeWholeOrg){
		if(flag == 0){
			obj.src = "<sys:context/>/resource/images/min.gif";
		}else{
			obj.src = "<sys:context/>/resource/images/max.gif";
		}
	}else{
		if(flag == 0){
			obj.src = "<sys:context/>/resource/images/min.gif";
		}else{
			obj.src = "<sys:context/>/resource/images/max.gif";
		}
	}
}

/** 左边权限显示/隐藏的开关控制
 */
function switchLeft(){

    var middleObject=top.document.getElementById("FRM_MIDDLE");
    if(middleObject.cols == sizeMiddleOrg){
		middleObject.cols = sizeMiddleMax;
	}else{
		middleObject.cols = sizeMiddleOrg;
		/*
		if(!leftOpened){
			window.open("left.htm","FRM_LEFT");
			leftOpened = true;
		}
		*/
	}
	sizeMiddleOld = middleObject.cols;////记住原始大小
	setWorkFocus();
}

/**关闭当前工作区
 * @param bl true/false是否强制退出，null==false
 */
function closeWork(bl,oWin){
	var callerIdx = findCallerIndex(oWin);
	closeWorkByIdx(bl,callerIdx);
}

/////////////////////////////////////////////////////////////
/** 找到window对象所对应的工作区ID
 * @param oWin 发出调用的页面的window对象
 * @return 对应的工作区的ID
 */
function findCallerIndex(oWin){
	if(oWin == null){
		return getSelectedIndex();
	}
	var ifm = findCallerIframe(oWin);
	for(var i=0;i<window.frames.length;i++){
		if(ifm == window.frames(i)){
			//alert("callerIndex:"+i);
			return i;
		}
	}
	//alert("findIndex.非正常:"+getSelectedIndex());
	return getSelectedIndex();
}

/**
  * 得到当前的Tab的索引
 * @return 当前的索引
 */
function getSelectedIndex(){
	//tab_doClick();
	return SELECTED_INDEX;
}

/** 找到window对象所对应的工作区的iframe
 * @param oWin 页面的window对象
 * @return 对应的工作区的iframe对象
 */
function findCallerIframe(oWin){
	//alert("findIframe.recursion:"+win.document.title);
	if(oWin.parent == self){
		//alert("findIframe.return.document:"+win.document.title);
		return oWin;
	}
	return findCallerIframe(oWin.parent);//...
}

function closeWorkByIdx(bl,callerIdx){
	//logoutPage(callerIdx);
	removeTabAt(callerIdx);
	//showLoading(false);
	//setTimeout("CollectGarbage()",500);//延迟500ms进行垃圾收集
}

/** 删除指定的Tab，idx不一定为当前的Tab
 * @param idx 索引
 */
function removeTabAt(idx){
    var  tabListObject=document.getElementById("tabList");
    var  labPanelObject=document.getElementById("labPanel");

    if(tabListObject.cells.length < 1){
		return ;
	}
	if(!checkBound(idx)){
		return;
	}
	tabListObject.deleteCell(idx);
	//labPanelObject.innerText = "";
	removeTabParams(idx);
	removePanelsAt(idx);
	if(idx < SELECTED_INDEX){
		SELECTED_INDEX --;
	}else if(idx == SELECTED_INDEX){
		SELECTED_INDEX = -1;
		setSelectedIndex(tabListObject.cells.length-1);
	}
}

function removeTabParams(idx){
	TAB_LIST = TAB_LIST.slice(0,idx).concat(TAB_LIST.slice(idx+1));
}

/** 删除指定的Tab后，需要删除相应的Panel
 * @param idx 索引
 */
function removePanelsAt(idx){
    var workPanelObject=document.getElementById("workPanel");
    var rowIdx = idx;
	workPanelObject.deleteRow(rowIdx);
//	workPanelObject.deleteRow(rowIdx);
//    var rowIdx = 2*idx;
//	workPanelObject.deleteRow(rowIdx);
//	workPanelObject.deleteRow(rowIdx);
}

function checkBound(idx){
    var  tabListObject=document.getElementById("tabList");
    if(idx < 0 || idx > tabListObject.cells.length -1){
		showAlert("索引越界:"+idx+">"+(tabListObject.cells.length -1));
		return false;
	}
	return true;
}

/** 设置当前窗口焦点
*/
function setWorkFocus(){
	//有申报表头的。
	if (getSelectedIndex()==-1){
	    return;
	}
	try {
		var sb_objs=window.frames(getSelectedIndex()).document.body.getElementsByTagName("sb_header");
		for (var i=0;i<sb_objs.length;i++){
			if (sb_objs(i).getItemObject(0).disabled==false && sb_objs(i).getItemObject(0).type!="hidden"){
				sb_objs(i).getItemObject(0).focus();
				return;
			}
		}
		var objs=window.frames(getSelectedIndex()).document.body.getElementsByTagName("INPUT");
		//alert(objs.length);
		for (var i=0;i<objs.length;i++){
			if (objs(i).disabled==false && objs(i).type!="hidden"){
				objs(i).focus();
				return;
			}
		}
	} catch (E){
		//
	}
}
function showHistory(){
	showAlert("当前没有消息历史记录！");
	//window.setActive('false');
}
</script>
</head>

<body style="overflow:hidden; margin:0; padding:0px; padding-top:30px; height:96%;">
<!-- 页面右侧最上面显示tab页区域 -->
<table border="0" cellspacing="0" cellpadding="1" >
  <tr>
    <td><div id="tabContainer" style="position:absolute; top:4px; left:3px; width:100%; height:28px; font-size:12px;z-index:0; " oncontextmenu="return false;" onselectstart="return false;">
        <table style="height:30px; table-layout:fixed" border="0" cellSpacing="0" cellPadding="0">
          <tr id="tabList"></tr>
        </table>
      </div></td>
</table>
<!-- 页面右侧下面显示主内容区域 -->
<table border="0" width="100%" height="100%" align="center" cellpadding="0" cellspacing="0">
  <tbody id="workPanel" style="height:100%;">
  </tbody>
</table>
</body>
</html>