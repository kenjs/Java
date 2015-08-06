<html>
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>������</title>
<style type="text/css">
.tab {background:url(<sys:context/>/resource/images/tab1.gif) no-repeat; cursor:pointer; font-size:12px; }
.tab_sel {background:url(<sys:context/>/resource/images/tab2.gif) no-repeat; cursor:pointer; font-size:12px;}
.pane {display:table-row;}
.pane_hide {display:none;}
</style>
<script type="text/javascript">

//��ǰ��ѡ��
var SELECTED_INDEX = -1;
//���Tab����
var MAX_TAB_COUNT = 11;

var TAB_LIST = new Array();


function init(){
	var url=jcontextPath+'/dbsx/dbsxYbsx!initTab';
	navigate(url,"��������","true");
}

//window.onload=init;

/**
 * ������ҹ���ʱִ��
 * @param dir ��������
 */
function scrollTab(dir){
    var tabContainerObject=document.getElementById("tabContainer");
	if(tabContainerObject.doScroll){
    	tabContainerObject.doScroll(dir);//firefox�²�֧��doscroll����
	}
	flagDir();
}

/**
 * ָʾ�������صĵ�ǰTabҳ�ķ���
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
 * �����ķ��������¼�����
 * @param obj ����
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
 * �����ķ��������¼�����
 * @param obj ����
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
 * ָ��������Tab��������ǰλ��
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
 * ���Tabҳʱѡ����
 */
function tab_doClick(evt){
    //��ȡ�¼�
    evt=evt?evt:(window.event?window.event:null);
    //IE��Firefox���¼����Բ�ͬ
    var  obj = evt.srcElement ? evt.srcElement : evt.target;
	if(obj.nodeName != "TD"){
		obj = obj.parentNode;
	}
	setSelectedIndex(obj.cellIndex);
	//obj.onclick=closeWork;
}


/** ���õ�ǰ��Tab��
 * @param idx ����
 */
//function setSelectedIndex(idx){
//    var  tabListObject=document.getElementById("tabList");
//    //ʹ�µ�Tab��ѡ��
//	oCel.className = "tab_sel";
//	labPanel.innerText = getTabParam("TITLE",idx);
//	setPanelsVisibility(true,idx);
//	SELECTED_INDEX = idx;
//}

/** ����Tab
 * @param sTitle Tab����
 * @param sIcon ͼ��·��
 * @param sUrl ����·����
 */
function addTab(sTitle,sIcon,sUrl){
    var tabListObject=document.getElementById("tabList");
    var tabContainerObject=document.getElementById("tabContainer");
    //��ӵ����
    var oCel = tabListObject.insertCell(TAB_LIST.length);
	oCel.noWrap = true;
	oCel.title = sTitle;
	oCel.style.width="120px";
	oCel.style.textAlign="center";
	oCel.style.paddingLeft="3px";
	oCel.className = "tab";
    //����IE��Firefox
    oCel.onclick=tab_doClick;
    oCel.ondblclick=closeWork;
	//oCel.ondblclick=resizeWork(document.getElementById("maxImg"));

    //oCel.attachEvent("oncontextmenu",tab_doMenu);
	//sfj �޸Ĵ˴��� ondblclick=\"resizeWork(img_switch)\" ����
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


/** ���õ�ǰ��Tab��
 * @param idx ����
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
	//�Զ�����Tabҳ
	var oCel = tabListObject.cells[idx];

<%--	if((oCel.offsetLeft+oCel.offsetWidth) >= (tabContainerObject.scrollLeft+tabContainerObject.offsetWidth)){--%>
<%--		if(tabContainerObject.doScroll){--%>
<%--			tabContainerObject.doScroll("pageRight");--%>
<%--		}//firefox�²�֧��doScroll--%>
<%--	}else if(oCel.offsetLeft <= tabContainerObject.scrollLeft){--%>
<%--		if(tabContainerObject.doScroll){--%>
<%--			tabContainerObject.doScroll("pageLeft");//firefox�²�֧��doScroll--%>
<%--		}--%>
<%--	}--%>
<%--	if(SELECTED_INDEX == idx){--%>
<%--		return;--%>
<%--	}--%>

	if(SELECTED_INDEX != -1){
		tabListObject.cells[SELECTED_INDEX].className = "tab";
		setPanelsVisibility(false,SELECTED_INDEX);
	}
	//ʹ�µ�Tab��ѡ��
	oCel.className = "tab_sel";
	//labPanelObject.innerText = getTabParam("TITLE",idx);
	setPanelsVisibility(true,idx);
	SELECTED_INDEX = idx;
}

/** ��Ҫ����Panels�Ŀɼ���
 * @param bl true/false
 * @param idx ����
 */
function setPanelsVisibility(bl,idx){
    var workPanelObject=document.getElementById("workPanel");
    var state = bl? "pane" : "pane_hide";
	//alert(idx+":"+state);
	//�������Panel��״̬
	var rowIdx = idx;
	workPanelObject.rows[rowIdx].className = state;
//	workPanelObject.rows[rowIdx+1].className = state;
//    var rowIdx = 2*idx;
//	workPanelObject.rows[rowIdx].className = state;
//	workPanelObject.rows[rowIdx+1].className = state;
}

/**
 * �к�
 */
function createTabId(){
	return Math.random().toString();
}

function setTabParams(aParams,idx){
	TAB_LIST[idx] = aParams;
}

/** ��������Ӧ���������
 * @param sTitle Tab����
 * @param sIcon ͼ��·��
 * @param sUrl ����·��
 */
function addPanels(sTitle,sIcon,sUrl){


    var workPanelObject=document.getElementById("workPanel");
    //��ȡ�ܵ�����
    var countrows=workPanelObject.rows.length;
//    alert("cout rows="+countrows);
    //���뵽���
    var oRow = workPanelObject.insertRow(countrows);
	setPanelsVisibility(false,oRow.rowIndex);
//    var oRow1 = workPanelObject.insertRow(countrows+1);
//	setPanelsVisibility(false,oRow.rowIndex/2);
	var oCel = oRow.insertCell(0);
	oCel.innerHTML = "<iframe frameBorder='0' width='100%' height='100%' src='"+sUrl+"' >�Բ��������������֧��IFRAME!</iframe>";

	//������Ϣ����Ҫ
//	oRow1.valign = "bottom";
//	oRow1.style.height = 0;
//	oCel = oRow1.insertCell(0);
//	oCel.noWrap = true;
}


/** Ϊÿ�����������Ӽ�����
 * @param obj ������iframe����
 */
function stateListener(obj){
	if (obj.readyState == "complete"){
		//showLoading(false);
		//���TitleΪnull�����ҳ���title
	}else if(obj.readyState == "loading"){
		//showLoading(true);
	}
}

/** ��ʾ/���ؼ�����Ϣ
 * @param bl true/false
 */
function showLoading(bl){
	var state = bl ? "block" : "none";
	LOADING.style.display = state;
}


/** ����ҳ��
 * @param sUrl ���ӵ�ַ
 * @param sTitle ���ӵı���
 * @param bl �Ƿ����´��ڴ򿪹�����
 */
function navigate(sUrl,sTitle,bl){
	//�ж���ǰ�Ƿ��Ѿ���������û��������
	if(containsTab(sTitle)){
		if(!confirm("������["+sTitle+"]�Ѿ����ڣ�\n��ȷ����Ҫ���µĹ�������")){
			reOpenSelected(sTitle);
			return;
		}
	}
	if(getTabCount() < MAX_TAB_COUNT){
		addTab(sTitle,"<sys:context/>/resource/images/state1.gif",sUrl);
	} else{
		alert("ϵͳ��ʾ��\n�������Դ�["+MAX_TAB_COUNT+"]��������\n��رն���Ĺ�����");
	}
	//�����ڲ�����
}

/** ���ص�ǰ��Tab��
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

/** ���ͼƬʱ�ı乤������С
 * @param obj �����ͼƬ����
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
		obj.title = "��ԭ";
	}else{
		wholeObject.rows = sizeWholeOrg;
		middleObject.cols = sizeMiddleOld;//
		obj.src = "<sys:context/>/resource/images/max.gif";
		obj.title = "������ȫ��";
	}
document.body.fireEvent("onresize");//zxm ���룬����right���ڵ�resize�¼�����ҳ�沶��������Ӧ��div�߶ȡ�
}
//�����¼� �����ò�ѯ��Ŀ����
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

/** onmouseoverʱ���ı�ͼƬ
 * @param obj ͼƬ����
 * @param flag ��־onmouseover���ĸ�ͼƬ
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

/** ���Ȩ����ʾ/���صĿ��ؿ���
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
	sizeMiddleOld = middleObject.cols;////��סԭʼ��С
	setWorkFocus();
}

/**�رյ�ǰ������
 * @param bl true/false�Ƿ�ǿ���˳���null==false
 */
function closeWork(bl,oWin){
	var callerIdx = findCallerIndex(oWin);
	closeWorkByIdx(bl,callerIdx);
}

/////////////////////////////////////////////////////////////
/** �ҵ�window��������Ӧ�Ĺ�����ID
 * @param oWin �������õ�ҳ���window����
 * @return ��Ӧ�Ĺ�������ID
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
	//alert("findIndex.������:"+getSelectedIndex());
	return getSelectedIndex();
}

/**
  * �õ���ǰ��Tab������
 * @return ��ǰ������
 */
function getSelectedIndex(){
	//tab_doClick();
	return SELECTED_INDEX;
}

/** �ҵ�window��������Ӧ�Ĺ�������iframe
 * @param oWin ҳ���window����
 * @return ��Ӧ�Ĺ�������iframe����
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
	//setTimeout("CollectGarbage()",500);//�ӳ�500ms���������ռ�
}

/** ɾ��ָ����Tab��idx��һ��Ϊ��ǰ��Tab
 * @param idx ����
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

/** ɾ��ָ����Tab����Ҫɾ����Ӧ��Panel
 * @param idx ����
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
		showAlert("����Խ��:"+idx+">"+(tabListObject.cells.length -1));
		return false;
	}
	return true;
}

/** ���õ�ǰ���ڽ���
*/
function setWorkFocus(){
	//���걨��ͷ�ġ�
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
	showAlert("��ǰû����Ϣ��ʷ��¼��");
	//window.setActive('false');
}
</script>
</head>

<body style="overflow:hidden; margin:0; padding:0px; padding-top:30px; height:96%;">
<!-- ҳ���Ҳ���������ʾtabҳ���� -->
<table border="0" cellspacing="0" cellpadding="1" >
  <tr>
    <td><div id="tabContainer" style="position:absolute; top:4px; left:3px; width:100%; height:28px; font-size:12px;z-index:0; " oncontextmenu="return false;" onselectstart="return false;">
        <table style="height:30px; table-layout:fixed" border="0" cellSpacing="0" cellPadding="0">
          <tr id="tabList"></tr>
        </table>
      </div></td>
</table>
<!-- ҳ���Ҳ�������ʾ���������� -->
<table border="0" width="100%" height="100%" align="center" cellpadding="0" cellspacing="0">
  <tbody id="workPanel" style="height:100%;">
  </tbody>
</table>
</body>
</html>