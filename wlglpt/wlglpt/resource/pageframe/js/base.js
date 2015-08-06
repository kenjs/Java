// JavaScript Document

/*�����Ҽ�*/
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


/*���´򿪱�ǩҳ*/
function navigateMenu(url,lable,bool){
		parent.parent.document.getElementById("FRM_RIGHT").contentWindow.navigate(url,lable,bool);
}
//�����ż����ɫ���ƶ�ѡ��Ч��
$(function() {
	//�����ż����ɫ���ƶ�ѡ��Ч��
	$('.tab_css tbody tr:odd').addClass('odd');
	$('.tab_css tbody tr').hover(
    	function() { $(this).addClass('highlight'); },
    	function() { $(this).removeClass('highlight'); }
	).click( function() {
		$('.selected').removeClass('selected');
		$(this).addClass('selected');
	});
	$(".tab_css tr:even").addClass("even"); //even odd Ϊjquery�ķ�����evenΪż����oddΪ�������ر�ע�⣺������0��ʼ�����Ե�һ����ż���� 
	$(".tab_css tr:odd").addClass("odd"); 
});

/*����ֻ��*/
$(function() {
	//����رհ�ť ҳ���ϵĹر�
	$("#closeBtn,#closePic").click(sysClose);
	
	$("#closeWindow").click(
			function(){
				window.close()
			}
	);
	
	//����رհ�ť ��Ϣ��ҳ���ϵĹر�
	$("#messageClosePic,#messageCloseBtn").click(sysMessageClose);
});

/*����ֻ��*/
$(function() {
	$(".bgstyle_readonly").attr('readonly',true);
	//$(".bgstyle_readonly").attr("disabled",true);
});


//��ʾ���ز�ѯ����
function slideToggle(sydiv){
	if (sydiv=="sytips")
		//�ص�������������onresize�������ñ�����¼����� ��kpdwh.jsp setGridAuto
		{$("#divTips").slideToggle(100,function (){$(window).resize();});} //��ʾ������ʾ ����Ч�����л�,��һ����,��һ�¿�
	if (sydiv=="syquery")
		////�ص�������������onresize�������ñ�����¼����� ��kpdwh.jsp setGridAuto
		{$("#divQuery").slideToggle(100,function (){$(window).resize();});} //��ʾ���ز�ѯ���� ����Ч�����л�,��һ����,��һ�¿�
	if (sydiv=="spj")
		{$("#spjcont").slideToggle(100);} //��ʾ�������� ����Ч�����л�,��һ����,��һ�¿�
	if (sydiv=="splc")
		{$("#splccont").slideToggle(100);} //��ʾ�������� ����Ч�����л�,��һ����,��һ�¿�
	if (sydiv=="spyj")
		{$("#spyjcont").slideToggle(100);} //��ʾ�������� ����Ч�����л�,��һ����,��һ�¿�
}

// ���㵱ǰ���ڵĿ�� //
function pageWidth(){
		 var width = width = window.innerWidth != null ? window.innerWidth : document.documentElement && document.documentElement.clientWidth ? document.documentElement.clientWidth : document.body != null ? document.body.clientWidth : null;
         return width;
}

// ���㵱ǰ���ڵĸ߶� //
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

//���㵱ǰ�����г�����ѯ��������ʾ��ĸ߶ȣ�����ñ��ʵ�ʵĸ߶�
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

// ���㵱ǰ���ڵ��ϱ߹�����//
function topPosition(){
         return typeof window.pageYOffset != 'undefined' ? window.pageYOffset : document.documentElement && document.documentElement.scrollTop ? document.documentElement.scrollTop : document.body.scrollTop ? document.body.scrollTop : 0;
}

// ���㵱ǰ���ڵ���߹�����//
function leftPosition(){
         return typeof window.pageXOffset != 'undefined' ? window.pageXOffset : document.documentElement && document.documentElement.scrollLeft ? document.documentElement.scrollLeft : document.body.scrollLeft ? document.body.scrollLeft : 0;
}

$(window).bind("resize",function(){
	setGridAuto();
});  

function setGridAuto(){  
       var gridTabWidth=pageWidth()-10;  	//ȥ����ȵļ��㣬ֻ�����߶�
       var gridTabHeigt=pageTableHeight()-90;
       if(document.getElementById("dataList")){//�ж�id���ڲŽ��в���
    	   $("#dataList").setGridWidth(gridTabWidth);  
    	   $("#dataList").setGridHeight(gridTabHeigt);  
       }
}  


//��ʾ�������ԣ������ã�
 function showMeTheObj(obj){
 	if(obj==undefined){
 		alert("����δ����(obj==undefined)");
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
         message += messageArray4Sort[i] + " �� "; 
     }

 	alert(message);

 	return;
 }
 


function popwindow(url,width,height,myContentStr,yesCallBack,noCallBack){
	
		//Ĭ�Ͽ�500����350
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
		
		//������ݲ�Ϊ�գ���׷�ӵ�url����
		if(myContentStr != undefined){
			if(url.indexOf("?")>-1){
				url = url + "&myContentStr="+myContentStr;
			}else{
				url = url + "?myContentStr="+myContentStr;
			}
		}
		//������ǡ�������Ϊ��
		if(yesCallBack != undefined){
			if(url.indexOf("?")>-1){
				url = url + "&yesCallBack="+yesCallBack;
			}else{
				url = url + "&yesCallBack="+yesCallBack;
			}
		}
		//������񡱷�����Ϊ��
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
		// ��ȡ�������ඥ����ƫ��
		mytop = $(document).scrollTop();
		// ���㵯�����left
		getPosLeft = screenwidth/2 - width;
		// ���㵯�����top
		getPosTop = screenheight/2 - height;
		// css��λ������
		$(iframe).css({"left":getPosLeft,"top":getPosTop});
		
		// ����������ڴ�С�ı�ʱ...
		$(window).resize(function(){
			screenwidth = $(window).width();
			screenheight = $(window).height();
			mytop = $(document).scrollTop();
			getPosLeft = screenwidth/2 - width;
			getPosTop = screenheight/2 - height;
			$(iframe).css({"left":getPosLeft,"top":getPosTop+mytop});
		 });
		 
		 // ������������ʱ...
		 $(window).scroll(function(){
		 	screenwidth = $(window).width();
		 	screenheight = $(window).height();
		 	mytop = $(document).scrollTop();
		 	getPosLeft = screenwidth/2 - width;
			getPosTop = screenheight/2 - height;
		 	$(iframe).css({"left":getPosLeft,"top":getPosTop+mytop});
		 });
		 
		 $(iframe).fadeIn("fast");
		 // ��ȡҳ���ĵ��ĸ߶�
		 var docheight = $(document).height();
		 // ׷��һ���㣬ʹ�������
		 $("body").append("<div id='greybackground'></div>");
		 //$("html").css({"overflow":"hidden"});
		 $("#greybackground").css({"opacity":"0.1","height":docheight});
		 
		 // select������ �޷���ֵ
		 $("select").css({"display":"none"});
		 //$("html").css({"overflow":"hidden"});
		 return false;
	}
	
	
	//�������tabҳ�д򿪣������top.FRM_RIGHT.closeWork(true);�رգ�
	//������ڵ�����div�д򿪣���رյ�ǰ��div���ڣ����ø����ڵ�changeGreybackground����������iframe���ﵽ�رյ�Ч��
	function sysClose() {
		var tabObj = parent.parent.document.getElementById("FRM_RIGHT");
		if(tabObj){		
			tabObj.contentWindow.closeWork(true);
		}else{
			parent.changeGreybackground();
			return false;
		}
	}
	
	//��������Ϣ���ϵĹرհ�ť������showAlert,showConfirm,showSucces,showError
	function sysMessageClose() {
		parent.changeGreybackground();
		parent.focus();
		return false;
	}
	
	//�޸�iframeΪ���أ������������ͬʱ����select�б�״̬Ϊ��ѡ
	function changeGreybackground(){
		$("#box").hide();
		$("#box").empty(); 
		$("#box").attr("src","")
		$("#greybackground").remove();
		//$("html").css({"overflow":"hidden"});
		$("select").css({"display":"inline"}); 
	}
	
/**
* ajaxCommon ajaxͨ�ô�����
* @param url �����url��ַ 
* @param data �����data����
* @param doSuccessFucName �Զ���ĳɹ�������
* 
* �ύ��ʽΪPOST����������Ϊjson���Լ��ṩ��Ĭ�ϵĴ�������
* �����ύǰ����doBeforeSend���ύ�괦��doComplete���ɹ�������ݴ���doSuccess�����������ݴ���doError
* �����Ҫ�Զ��壬�������Լ���ҳ������д��Щ���� 
*/
function	ajaxCommon(url,data,doSuccessFucName,encodeBz,asyncFlag){
	asyncFlag = asyncFlag == undefined ? true : asyncFlag;
	var gnmkDm = $("#domain_gnmkDm").val();
	data["domain.gnmkDm"] = gnmkDm;
	if(encodeBz !=  false){
		var newJsonStr = "";
		$.each(data,function(key,val){
			 // each����~����1��Ҫ�����ı���~����2��������~function(��,ֵ)
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
			   		//����Զ��庯������������Զ��庯��������Ĭ�ϵ���doSuccess����
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

	//�ύǰ����
	function doBeforeSend() {
		disableAllButtons();
	}
	
	//�ύ�괦��
	function doComplete(){
		enableAllButtons();
	}	
	
	//�ɹ�������ݴ��� ��ʾ����ɹ����رյ�ǰҳ�棬�����ø����ڵ�ˢ�º���
	function doSuccess(data){
		hideMessage();
		showSuccess("����ɹ���","doYesCallBack");
	}
	
	//ѡ���ǵķ��ش���
	function doYesCallBack(){
		sysClose();
		parent.onRefresh();
	}
		 
	
	//���������ݴ���
	function doError(data){
		hideMessage();
		showMaxError(data.responseText);
	}
	
	//�������еİ�ť����ѡ
	function disableAllButtons(){
		$("button").each(function(i){
			$(this).attr("disabled","true");
		});
	}
	
	//�������еİ�ť��ѡ
	function enableAllButtons(){
		$("button").each(function(i){
			$(this).removeAttr("disabled");
		});
	}

	/*��������б���*/
	function getJqueryParam(obj, paraName) {
		var data = "";
		$.each(obj, function(i){
			data += paraName + "[" + i + "]=" + encodeURI(obj[i].value) + "&";
		});
		
		return data;
	}
	
	//�������÷��� jqgrid �ϲ��д���ԭ����������Ѹ���difCellName����
	//gridName���ַ������ͣ���Ҫ�����jqgrid���id��difCellName:�ַ������ͣ�����ĳ���е�ֵ���ϲ�����ֵ��ͬ���кϲ�
	//cellIds: �������ͣ���Ҫ�ϲ�����id
	//cellNames: �������ͣ���Ҫ�ϲ�������
    function Merger(gridName, difCellName, cellIds,cellNames) {
    	// �� cellNames δ���壬��Ĭ��ͬid��ͬ
    	if (cellNames == null || cellNames.length <= 0) {
    		cellNames = cellIds;
    	}
        //�õ���ʾ�������id����
        var mya = $("#" + gridName + "").getDataIDs();
        //��ǰ��ʾ������
        var length = mya.length;
        for (var i = 0; i < length; i++) {
            //���ϵ��»�ȡһ����Ϣ
            var before = $("#" + gridName + "").jqGrid('getRowData', mya[i]);
            //����ϲ�����
            var rowSpanTaxCount = 1;
            for (j = i + 1; j <= length; j++) {
                //���ϱߵ���Ϣ�Ա� ���ֵһ���ͺϲ�����+1 Ȼ������rowspan �õ�ǰ��Ԫ������
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
	

/*������ѡ��ʹ�䵥�����Դ�����ѡ���*/
$(function(){
	$(".ymdatetime").click(function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyy��MM��dd�� HHʱmm��ss��'})})/*����ѡ�� ������ʱ��*/
	$(".ymdate").click(function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM-dd'})})/*����ѡ�� ������*/
	$(".ymonth").click(function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyy-MM'})})/*����ѡ�� ����*/
	$(".year").click(function(){WdatePicker({skin:'whyGreen',dateFmt:'yyyy��'})})/*����ѡ�� ��*/
	$(".month").click(function(){WdatePicker({skin:'whyGreen',dateFmt:'MM��'})})/*����ѡ�� ��*/
	$(".time").click(function(){WdatePicker({skin:'whyGreen',dateFmt:'H:mm:ss'})})/*����ѡ�� ʱ��*/
});

//ltmenucont������ʾ���ݲ���
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

//�Ҳ����������ݸ��ݷֱ��������߶�,�����Զ����ֹ�����
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

//tabҳ�л�
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

//������
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