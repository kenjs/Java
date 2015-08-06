// JavaScript Document

/*屏蔽右键*/
/*$(function() {
	$(document).bind("contextmenu", function() { return true; });
});
*/
$(function() {
	var gmenu = document.getElementById("maincont");
	if (gmenu != undefined) {
		gmenu.style.overflow="auto";
		gmenu.style.width=document.documentElement.clientWidth -8 + "px";
		gmenu.style.height=document.documentElement.clientHeight + "px";
	}
});


/*重新打开标签页*/
function navigateMenu(url,lable,bool){
		parent.parent.document.getElementById("FRM_RIGHT").contentWindow.navigate(url,lable,bool);
}
//表格奇偶行颜色和移动选中效果
$(function() {
	//表格奇偶行颜色和移动选中效果
	$('.tab_css tbody tr:odd').addClass('odd');
	$('.tab_css tbody tr').hover(
    	function() { $(this).addClass('highlight'); },
    	function() { $(this).removeClass('highlight'); }
	).click( function() {
		$('.selected').removeClass('selected');
		$(this).addClass('selected');
	});
	$(".tab_css tr:even").addClass("even"); //even odd 为jquery的方法，even为偶数，odd为奇数。特别注意：索引从0开始，所以第一行是偶数！ 
	$(".tab_css tr:odd").addClass("odd"); 
});

/*控制只读*/
$(function() {
	//点击关闭按钮 页面上的关闭
	$("#closeBtn,#closePic").click(sysClose);
	
	$("#closeWindow").click(
			function(){
				window.close()
			}
	);
	
	//点击关闭按钮 消息框页面上的关闭
	$("#messageClosePic,#messageCloseBtn").click(sysMessageClose);
});

/*控制只读*/
$(function() {
	$(".bgstyle_readonly").attr('readonly',true);
	//$(".bgstyle_readonly").attr("disabled",true);
});


//显示隐藏查询条件
function slideToggle(sydiv){
	if (sydiv=="sytips")
		//回调函数：触发下onresize函数，让表格重新计算宽高 见kpdwh.jsp setGridAuto
		{$("#divTips").slideToggle(100,function (){$(window).resize();});} //显示隐藏提示 窗帘效果的切换,点一下收,点一下开
	if (sydiv=="syquery")
		////回调函数：触发下onresize函数，让表格重新计算宽高 见kpdwh.jsp setGridAuto
		{$("#divQuery").slideToggle(100,function (){$(window).resize();});} //显示隐藏查询条件 窗帘效果的切换,点一下收,点一下开
	if (sydiv=="spj")
		{$("#spjcont").slideToggle(100);} //显示隐藏内容 窗帘效果的切换,点一下收,点一下开
	if (sydiv=="splc")
		{$("#splccont").slideToggle(100);} //显示隐藏内容 窗帘效果的切换,点一下收,点一下开
	if (sydiv=="spyj")
		{$("#spyjcont").slideToggle(100);} //显示隐藏内容 窗帘效果的切换,点一下收,点一下开
}

// 计算当前窗口的宽度 //
function pageWidth(){
		 var width = width = window.innerWidth != null ? window.innerWidth : document.documentElement && document.documentElement.clientWidth ? document.documentElement.clientWidth : document.body != null ? document.body.clientWidth : null;
         return width;
}

// 计算当前窗口的高度 //
function pageHeight(){
	var height = window.innerHeight != null? window.innerHeight : document.documentElement && document.documentElement.clientHeight ? document.documentElement.clientHeight : document.body != null? document.body.clientHeight : null;
    if(navigator.userAgent.indexOf("MSIE")>0) {   
		if(navigator.userAgent.indexOf("MSIE 6.0")>0 || navigator.userAgent.indexOf("MSIE 7.0")>0 || navigator.userAgent.indexOf("MSIE 8.0")>0){   
			height=height; 
		}   
		if(navigator.userAgent.indexOf("MSIE 9.0")>0) {  
			height=height - 25; 
		}   
	 } else {
		height=height - 30;
	 }
    return height;
}

//计算当前窗口中除掉查询条件和提示框的高度，即获得表格实际的高度
function pageTableHeight(){
		var pageTableHeight = pageHeight()-5;
		var tip = document.getElementById("divTips");
		var query = document.getElementById("divQuery");
		var my = document.getElementById("divMy");
		if(null != tip){
			pageTableHeight = pageTableHeight-tip.scrollHeight;
		}
		if(null != query){
			//alert("query:"+query.clientHeight);
			pageTableHeight = pageTableHeight-query.scrollHeight;
		}		
		if(null != my){
			pageTableHeight = pageTableHeight-my.scrollHeight;
		}
        return pageTableHeight;
}

// 计算当前窗口的上边滚动条//
function topPosition(){
         return typeof window.pageYOffset != 'undefined' ? window.pageYOffset : document.documentElement && document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop ? document.body.scrollTop : 0;
}

// 计算当前窗口的左边滚动条//
function leftPosition(){
         return typeof window.pageXOffset != 'undefined' ? window.pageXOffset : document.documentElement && document.documentElement.scrollLeft ? document.documentElement.scrollLeft : document.body.scrollLeft ? document.body.scrollLeft : 0;
}

$(window).bind("resize",function(){
	setGridAuto();
});  

function setGridAuto(){  
       var gridTabWidth=pageWidth()-10;  	//去掉宽度的计算，只保留高度
       var gridTabHeigt=pageTableHeight()-90;
       if(document.getElementById("dataList")){//判断id存在才进行操作
    	   $("#dataList").setGridWidth(gridTabWidth);  
    	   $("#dataList").setGridHeight(gridTabHeigt);  
       }
}  


//显示对象属性（调试用）
 function showMeTheObj(obj){
 	if(obj==undefined){
 		alert("对象未定义(obj==undefined)");
 		return;
 	}

 	var message = "";
 	
 	for(var property in obj){
 	   		message += property + ",";
 	}
 	
 	var messageArray4Sort = message.split(",");
     message = "";
     messageArray4Sort.sort();
     for(var i=0; i<messageArray4Sort.length; i++){
         message += messageArray4Sort[i] + " ｜ "; 
     }

 	alert(message);

 	return;
 }
 


function popwindow(url,width,height,myContentStr,yesCallBack,noCallBack){
	
		//默认宽500，高350
		if(width == undefined){
			width = 760;
		}
		if(height == undefined){
			height = 408;
		}

		var iframe = $("#box");
		if (iframe.length <= 0) {
			var iframe = $("<iframe scrolling=\"no\" frameborder=\"0\" allowtransparency=\"true\" id=\"box\" src=\"\"></iframe>")
		}
		$(iframe).css({"width":width,"height":height});
		
		$(iframe).appendTo($(document.body));
		
		width=$(iframe).width()/2;
		height=$(iframe).height()/2;
		
		//如果内容不为空，则追加到url后面
		if(myContentStr != undefined){
			if(url.indexOf("?")>-1){
				url = url + "&myContentStr="+myContentStr;
			}else{
				url = url + "?myContentStr="+myContentStr;
			}
		}
		//如果“是”方法不为空
		if(yesCallBack != undefined){
			if(url.indexOf("?")>-1){
				url = url + "&yesCallBack="+yesCallBack;
			}else{
				url = url + "&yesCallBack="+yesCallBack;
			}
		}
		//如果“否”方法不为空
		if(noCallBack != undefined){
			if(url.indexOf("?")>-1){
				url = url + "&noCallBack="+noCallBack;
			}else{
				url = url + "&noCallBack="+noCallBack;
			}
		}
		$(iframe).attr("src",url);
		var screenwidth,screenheight,mytop,getPosLeft,getPosTop1
		screenwidth = $(window).width();
		screenheight = $(window).height();
		// 获取滚动条距顶部的偏移
		mytop = $(document).scrollTop();
		// 计算弹出层的left
		getPosLeft = screenwidth/2 - width;
		// 计算弹出层的top
		getPosTop = screenheight/2 - height;
		// css定位弹出层
		$(iframe).css({"left":getPosLeft,"top":getPosTop});
		
		// 当浏览器窗口大小改变时...
		$(window).resize(function(){
			screenwidth = $(window).width();
			screenheight = $(window).height();
			mytop = $(document).scrollTop();
			getPosLeft = screenwidth/2 - width;
			getPosTop = screenheight/2 - height;
			$(iframe).css({"left":getPosLeft,"top":getPosTop+mytop});
		 });
		 
		 // 当拉动滚动条时...
		 $(window).scroll(function(){
		 	screenwidth = $(window).width();
		 	screenheight = $(window).height();
		 	mytop = $(document).scrollTop();
		 	getPosLeft = screenwidth/2 - width;
			getPosTop = screenheight/2 - height;
		 	$(iframe).css({"left":getPosLeft,"top":getPosTop+mytop});
		 });
		 
		 $(iframe).fadeIn("fast");
		 // 获取页面文档的高度
		 var docheight = $(document).height();
		 // 追加一个层，使背景变灰
		 $("body").append("<div id='greybackground'></div>");
		 //$("html").css({"overflow":"hidden"});
		 $("#greybackground").css({"opacity":"0.1","height":docheight});
		 
		 // select不可用 无法传值
		 $("select").css({"display":"none"});
		 //$("html").css({"overflow":"hidden"});
		 return false;
	}
	
	
	//如果是在tab页中打开，则调用top.FRM_RIGHT.closeWork(true);关闭；
	//如果是在弹出的div中打开，则关闭当前的div窗口，调用父窗口的changeGreybackground函数，隐藏iframe，达到关闭的效果
	function sysClose() {
		var tabObj = parent.parent.document.getElementById("FRM_RIGHT");
		if(tabObj){		
			tabObj.contentWindow.closeWork(true);
		}else{
			parent.changeGreybackground();
			return false;
		}
	}
	
	//弹出的消息框上的关闭按钮，包括showAlert,showConfirm,showSucces,showError
	function sysMessageClose() {
		parent.changeGreybackground();
		parent.focus();
		return false;
	}
	
	//修改iframe为隐藏，并溢出背景，同时设置select列表状态为可选
	function changeGreybackground(){
		$("#box").hide();
		$("#box").empty(); 
		$("#box").attr("src","")
		$("#greybackground").remove();
		//$("html").css({"overflow":"hidden"});
		$("select").css({"display":"inline"}); 
	}
	
/**
* ajaxCommon ajax通用处理函数
* @param url 请求的url地址 
* @param data 请求的data数据
* @param doSuccessFucName 自定义的成功处理函数
* 
* 提交方式为POST，数据类型为json，以及提供了默认的处理函数，
* 比如提交前处理：doBeforeSend、提交完处理：doComplete、成功后的数据处理：doSuccess、出错后的数据处理：doError
* 如果需要自定义，可以在自己的页面上重写这些函数 
*/
function	ajaxCommon(url,data,doSuccessFucName,encodeBz,asyncFlag){
	asyncFlag = asyncFlag == undefined ? true : asyncFlag;
	var gnmkDm = $("#domain_gnmkDm").val();
	data["domain.gnmkDm"] = gnmkDm;
	if(encodeBz !=  false){
		var newJsonStr = "";
		$.each(data,function(key,val){
			 // each方法~参数1，要遍历的变量~参数2遍历方法~function(键,值)
			 newJsonStr = newJsonStr + "\"" + key + "\"" + " : " + "\"" + encodeURI(val) + "\"" + ",";
		});
		newJsonStr = newJsonStr.substring(0,newJsonStr.length-1);
		newJsonStr = "{" + newJsonStr + "}";
		var newJsonObj = JSON.parse(newJsonStr);
		data = newJsonObj;
	}
	url = url + ".action";
	$.ajax({
			   type: "POST",
			   async:asyncFlag,
			   url: url,
			   dataType:"json",
			   data: data,
			   contentType: "application/x-www-form-urlencoded; charset=GBK", 
			   beforeSend:function(){
			       try{
				        if(typeof(eval("doBeforeSend")) =="function"){
				        	eval("doBeforeSend();");
				        }
			        }catch(e){
			        	//alert("not function"); 
			       	}  
			   },
			   complete:function(){
			   		try{
				        if(typeof(eval("doComplete")) =="function"){
				        	eval("doComplete();");
				        }
			        }catch(e){
			        	//alert("not function"); 
			       	}
			   },
			   success: function(data){
			   		var successFun = "";
			   		//如果自定义函数存在则调用自定义函数，否则默认调用doSuccess函数
			   		if(doSuccessFucName != undefined){
			   			successFun = doSuccessFucName;
			   		}else{
			   			successFun = "doSuccess";
			   		}
			   		try{
				        if(typeof(eval(successFun)) =="function"){
				        	eval(successFun+"(data);");
				        }
			        }catch(e){
			        	//alert("not function"); 
			       	}
			   },
			   error:function(data){
			   		try{
				        if(typeof(eval("doError")) =="function"){
				        	eval("doError(data);");
				        }
			        }catch(e){
			        	//alert("not function"); 
			       	}
			   }
	 });  
}

	//提交前处理
	function doBeforeSend() {
		disableAllButtons();
	}
	
	//提交完处理
	function doComplete(){
		enableAllButtons();
	}	
	
	//成功后的数据处理 提示保存成功，关闭当前页面，并调用父窗口的刷新函数
	function doSuccess(data){
		hideMessage();
		showSuccess("保存成功！","doYesCallBack");
	}
	
	//选择是的返回处理
	function doYesCallBack(){
		sysClose();
		parent.onRefresh();
	}
		 
	
	//出错后的数据处理
	function doError(data){
		hideMessage();
		showMaxError(data.responseText);
	}
	
	//设置所有的按钮不可选
	function disableAllButtons(){
		$("button").each(function(i){
			$(this).attr("disabled","true");
		});
	}
	
	//设置所有的按钮可选
	function enableAllButtons(){
		$("button").each(function(i){
			$(this).removeAttr("disabled");
		});
	}

	/*对数组进行编码*/
	function getJqueryParam(obj, paraName) {
		var data = "";
		$.each(obj, function(i){
			data += paraName + "[" + i + "]=" + encodeURI(obj[i].value) + "&";
		});
		
		return data;
	}
	
	//公共调用方法 jqgrid 合并行处理，原表格数据需已根据difCellName排序
	//gridName：字符串类型，需要处理的jqgrid表格id；difCellName:字符串类型，根据某个列的值来合并，该值相同的行合并
	//cellIds: 数组类型，需要合并的列id
	//cellNames: 数组类型，需要合并的列名
    function Merger(gridName, difCellName, cellIds,cellNames) {
    	// 若 cellNames 未定义，则默认同id相同
    	if (cellNames == null || cellNames.length <= 0) {
    		cellNames = cellIds;
    	}
        //得到显示到界面的id集合
        var mya = $("#" + gridName + "").getDataIDs();
        //当前显示多少条
        var length = mya.length;
        for (var i = 0; i < length; i++) {
            //从上到下获取一条信息
            var before = $("#" + gridName + "").jqGrid('getRowData', mya[i]);
            //定义合并行数
            var rowSpanTaxCount = 1;
            for (j = i + 1; j <= length; j++) {
                //和上边的信息对比 如果值一样就合并行数+1 然后设置rowspan 让当前单元格隐藏
                var end = $("#" + gridName + "").jqGrid('getRowData', mya[j]);
                if (before[difCellName] == end[difCellName]) {
                    rowSpanTaxCount++;
                    //$("#" + gridName + "").setCell(mya[j], difCellName, '', { display: 'none' });
                    //$("#" + difCellName + "" + mya[i] + "").attr("rowspan", rowSpanTaxCount);
                    if (cellIds != null && cellIds.length != undefined) {
                    	for (var t=0; t<cellNames.length; t++) {
                    		$("#" + gridName + "").setCell(mya[j], cellNames[t], '', { display: 'none' });
                    		$("#" + cellIds[t] + "" + mya[i] + "").attr("rowspan", rowSpanTaxCount);
                    	}
                    }
                    
                } else {
                    rowSpanTaxCount = 1;
                    break;
                }
                
            }
            i = j-1;
        }
    }
	

/*绑定日期选择，使其单击可以打开日期选择框*/
$(function(){
	$(".ymdatetime").click(function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyy年MM月dd日 HH时mm分ss秒'})})/*触发选框 年月日时间*/
	$(".ymdate").click(function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})})/*触发选框 年月日*/
	$(".ymonth").click(function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})})/*触发选框 年月*/
	$(".year").click(function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyy年'})})/*触发选框 年*/
	$(".month").click(function(){WdatePicker({skin:'whyGreen',dateFmt:'MM月'})})/*触发选框 月*/
	$(".time").click(function(){WdatePicker({skin:'whyGreen',dateFmt:'H:mm:ss'})})/*触发选框 时间*/
});

//ltmenucont下拉显示内容部分
$(function(){ 
	$(".mtitle").mouseenter(function(){
		$(this).children("h2").addClass("current").siblings(".ltmenucont").slideDown(100);
		})
	$(".mtitle").mouseleave(function(){
		$(this).children(".ltmenucont").slideUp(100).siblings("h2").removeClass("current");
		})
	})
	
$(function() {

    $("#explore-nav li a").click(function() {
        
        // Figure out current list via CSS class
        var curList = $("#explore-nav li a.current").attr("rel");
        
        // List moving to
        var $newList = $(this);
        
        // Remove highlighting - Add to just-clicked tab
        $("#explore-nav li a").removeClass("current");
        $newList.addClass("current");
        
        // Figure out ID of new list
        var listID = $newList.attr("rel");
        
        if (listID != curList) {
            
            // Fade out current list
            $("#"+curList).fadeOut(300, function() {
                
                // Fade in new list on callback
                $("#"+listID).fadeIn();
            
            });
            
        }        
        
        // Don't behave like a regular link
        return false;
    });

});

//右侧表格区块内容根据分辨率来定高度,超出自动出现滚动条
function contHeight(){
	var ct=document.getElementById("divQuery").offsetHeight;
	var cw=document.getElementById("divQuery").offsetWidth;
	if(navigator.userAgent.indexOf("MSIE")>0)  
		{   
			if(navigator.userAgent.indexOf("MSIE 6.0")>0 || navigator.userAgent.indexOf("MSIE 7.0")>0 || navigator.userAgent.indexOf("MSIE 8.0")>0)  
			{   
				var cmh=document.documentElement.clientHeight - ct - 35; 
			}   
			if(navigator.userAgent.indexOf("MSIE 9.0")>0)  
			{  
				var cmh=document.documentElement.clientHeight - ct - 65; 
			}   
		}
	else
		{
			var cmh=document.documentElement.clientHeight - ct - 65;
		}
	var cmw=document.documentElement.clientWidth - 10;
	var tabh =document.getElementById("divTable");
	tabh.style.height=cmh+"px";
	tabh.style.width=cmw+"px";
	tabh.style.overflow="auto";
}

//tab页切换
function setTab(name,cursel,n){
	for(i=1;i<=n;i++){
		var menu=document.getElementById(name+i);
		var con=document.getElementById("con_"+name+"_"+i);
		menu.className=i==cursel?"current":"";
		
		if (con) {
			con.style.display=i==cursel?"block":"none";
		}
	}
}

//下拉框
$(function(){
	$(".icon_arrow").click(function(){
		var id = this.id;
		var pos = findXzqhPos(this);
		
		if ("xzqh" == id) {
			inputSel = $(this).attr("xzqh");
			$("#inputSel_"+id).css({
				top: (pos.y + this.offsetHeight) + "px" ,
				left: pos.x + "px"
			});
		}
		
		$("#inputSel_"+id).toggle();//.slideToggle("fast");
		if ($("#inputSel_"+id).parent(".inputsc").css("display") == "none") {
			$("#inputSel_"+id).parent(".inputsc").show();
		}else {
			$("#inputSel_"+id).parent(".inputsc").hide();
		}
		//$(".inputsc").toggle();
		return false;
	});
	$(document).bind("click",function(e){
	    var target = $(e.target);
	    if(target.closest(".inputselcont").length == 0){
			$(".inputselcont").hide();//slideUp("fast");
			$(".inputsc").hide();
		}
	});
});

function findXzqhPos(obj) {
	var curleft = (obj.offsetLeft-15) || 0;
	var curtop = obj.offsetTop || 0;
	while (obj = obj.offsetParent) {
		curleft += (obj.offsetLeft-15);
		curtop += obj.offsetTop;
	}

	return {x:curleft,y:curtop};
}

function onProvinceClick(provinceCode) {
	var obj = window.event.srcElement;
	$(".city").css({"display":"none"});
	$(".city[name='"+provinceCode+"']").css({"display":"block"});
	setTab('area',2,3)
	$(".province a").removeClass("current");
	$(obj).addClass("current");
}

function onCityClick(cityCode) {
	var obj = window.event.srcElement;
	changeCurrentClass(obj);
	$(".county").css({"display":"none"});
	
	var county = $(".county[name='"+cityCode+"']");
	if (county.length > 0) {
		county.css({"display":"block"});
		setTab('area',3,3)
	}else {
		setMultiXzqhVal(cityCode,$(obj).text())
		$(".inputselcont").slideUp("fast");
	}
}

function onCountyClick(countyCode) {
	var obj = window.event.srcElement;
	changeCurrentClass(obj);
	setMultiXzqhVal(countyCode,$(obj).text())
	$(".inputselcont").slideUp("fast");
}

function changeCurrentClass(obj) {
	$(obj).siblings().removeClass("current");
	$(obj).addClass("current");
}

var inputSel;

function setMultiXzqhVal(val, text) {
	$("#mainForm_domain_"+inputSel+"Mc").val(text);
	$("#mainForm_domain_"+inputSel+"Dm").val(val);
}