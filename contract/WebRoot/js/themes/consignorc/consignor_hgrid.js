 function scollPostion(){//滚动条位置
        var t, l;
        if (document.documentElement && document.documentElement.scrollTop) {
            t = document.documentElement.scrollTop;
            l = document.documentElement.scrollLeft;
        } else if (document.body) {
            t = document.body.scrollTop;
            l = document.body.scrollLeft;
        }
        return { top: t, left: l };
    }

 /**
  * wei.huang
  * 2013-10-8
  * 列表数据填充
  */
 function f_hgrid_json(index){
	 myScrollTo();
	 if(index==1){
		 f_hgrid1_json("");
		 $("#shfQuery").hide();
	 }else if(index==2){
		 f_hgrid2_json("");
		 $("#shfQuery").show();
	 }else if(index==3){
		 f_hgrid3_json("");
		 $("#shfQuery").hide();
	 }else if(index==4){
		 f_hgrid4_json("");
		 $("#shfQuery").hide();
	 }
 }

 /**
  * wei.huang
  * 2013-10-9
  * 让滚动条保持原来的位置
  */
function myScrollTo(){
	var position=scollPostion();
	window.scrollTo(position.left,position.top);
}
 
function f_hgrid_create(pagerow,function_index){//初始化
	//参数为每页行数 
	var txt='<div class="page_nav" style="padding:3px 3px 3px 3px;">'+
		'<span id="pageinfo"></span>'+
		'<span id="pagetop"><a href="#">首页</a></span>'+
		'<span id="pagepre"><a href="#">上一页</a></span>'+
		'<span id="page1"><a href="#"></a></span>'+
		'<span id="page2"><a href="#"></a></span>'+
		'<span id="page3"><a href="#"></a></span>'+		
		'<span id="page4"><a href="#"></a></span>'+		
		'<span id="page5"><a href="#"></a></span>'+		
		'<span id="page6"><a href="#"></a></span>'+		
		'<span id="page7"><a href="#"></a></span>'+		
		'<span id="page8"><a href="#"></a></span>'+		
		'<span id="page9"><a href="#"></a></span>'+			
		'<span id="pagenext"><a href="#">下一页</a></span>'+
		'<span id="pagebottom"><a href="#">末页</a></span>'+
		'<input type="hidden" id="pagecode" value="1"/>'+ 
		'<input type="hidden" id="pageparam" value=""/>'+
		'<input type="hidden" id="pagerow" value="'+pagerow+'"/>'+
		'<input type="hidden" id="recordcount" value="0"/>'+
		'</div>'
	
	$('#hgrid').after(txt); //after
	$('#hgrid').after('<div id="loading" style="text-align:center;"><img src="../imgs/sys/loading.gif" border="0" /></div>'); 

	$("#pagetop a").click(function(){ //首页
		$("#pagecode").val("1");	 
		f_hgrid_json(function_index); 
		return false; 
	});
	
	$("#pagenext a").click(function(){//下一页
		var pagecode=parseInt($("#pagecode").val())+1;
		var recordcount=parseInt($("#recordcount").val());
		var pagerow=parseInt($("#pagerow").val());		
		if (pagecode>parseInt((recordcount-1)/pagerow)+1){pagecode=parseInt((recordcount-1)/pagerow)+1;}
		$("#pagecode").val(pagecode);
		f_hgrid_json(function_index); 
		return false;
	});
	
	$("#pagepre a").click(function(){ //上一页
		var pagecode=parseInt($("#pagecode").val());
		pagecode=pagecode-1;
		if (pagecode<1) pagecode=1;
		$("#pagecode").val(pagecode);
		f_hgrid_json(function_index); 
		return false; 
	});
	
	$("#pagebottom a").click(function(){ //末页
		var pagecode=parseInt($("#pagecode").val());
		var recordcount=parseInt($("#recordcount").val());
		var pagerow=parseInt($("#pagerow").val());
		if (pagecode!=parseInt((recordcount-1)/pagerow)+1){
			pagecode=parseInt((recordcount-1)/pagerow)+1;
			$("#pagecode").val(pagecode);
			f_hgrid_json(function_index); 
			}
		return false; 
	});

	for (var i=1;i<=9;i++){
		$("#page"+i+" a").click(function(){ 
			$("#pagecode").val($(this).text());
			f_hgrid_json(function_index); 
			return false; 
		});
	}

}

function f_hgrid_setmsg(data){//data为json数据 
    $(".myjl").remove();
	if(data==null||data=='undefined'){	
		$('<div class="myjl" style="font-size:18px;text-align:center;padding-top:20px;font-family:微软雅黑;">没有记录</div>').insertBefore(".page_nav");
		$(".page_nav").hide();
		return '';
		}
	else{
	var recordcount=data.Count;//总记录数
	if(recordcount==0||recordcount==''){
		$('<div class="myjl" style="font-size:18px;text-align:center;padding-top:20px;font-family:微软雅黑;">没有记录</div>').insertBefore(".page_nav");
		$(".page_nav").hide();
		return '';
	}
	else{
	$(".page_nav").show();	
 	$("#recordcount").val(recordcount);//设置总记录数
	var pagecode=$("#pagecode").val(); //当前页
	var pagerow=$("#pagerow").val(); //每页行数
	var pagecount= parseInt((recordcount-1)/pagerow+1); //总页数
	$("#pageinfo").text("共"+recordcount+"条 第"+pagecode+"/"+pagecount.toString()+"页");
	var pagemin=pagecode-4; //第n页最小
	if (pagemin<1) pagemin=1;
	var pagemax=pagemin+8;//最大
	if (pagemax>pagecount) pagemax=pagecount;
	if (pagemax<(pagemin+8) && pagemin>1) pagemin=pagemin-1;
	if (pagemax<(pagemin+8) && pagemin>1) pagemin=pagemin-1;
	if (pagemax<(pagemin+8) && pagemin>1) pagemin=pagemin-1;
	if (pagemax<(pagemin+8) && pagemin>1) pagemin=pagemin-1;
	//$("strong").remove();
	for (var i=1;i<=9;i++){ //设置4+1+4个对象
		if (pagemin<=pagemax) {
			$("#page"+i+" a").text(pagemin);
			$("#page"+i).show();
			if (pagemin==pagecode) $("#page"+i+" a").wrapInner("<strong></strong>");			
			}
		else 
			$("#page"+i).hide();
		pagemin=pagemin+1;
	}
	}
	}
	return data.Data; 
}			

function f_hgrid_getparam(param){ //得到全部参数
	if (param.length<1) //无参
		{param=$("#pageparam").val();} 
	else  //有参
		{$("#pageparam").val(param);} //读取上次的参数或存储
	var pagecode=$("#pagecode").val(); //当前页
	var pagerow=$("#pagerow").val(); //每页行数
	if (param.substr(0, 1)!="&") {param="&"+param}
	param="skipCount="+(pagecode-1)*pagerow+"&pageSize="+pagerow+"&random="+Math.random()+param;
 	return param;
}
