function f_hgrid_ini(){//初始化表格
	var pagerow=10;  //参数为每页行数
	f_hgrid_create(pagerow); 	
	//注意：此处的列宽和为820px
	var txt='<tr>' +	
	'<th scope="col" width="100px" class="tdleft" style="text-align: left;height:30px;padding-left:10px;">调度单号</th>' +
	'<th scope="col" width="90px" class="tdleft" style="text-align: left;height:30px;padding-left:3px;">调度单状态</th>' +
	'<th scope="col" width="80px" class="tdleft" style="text-align: left;height:30px;padding-left:3px;">调度日期</th>' +
	'<th scope="col" width="120px" class="tdleft" style="text-align: left;height:30px;padding-left:3px;">纸质调度单号</th>' +
	'<th scope="col" width="140px" class="tdleft" style="text-align: left;height:30px;padding-left:3px;">发车时间</th>' +
	'<th scope="col" width="140px" class="tdleft" style="text-align: left;height:30px;padding-left:3px;">到车时间</th>' +
	'<th scope="col" width="100px" class="tdleft" style="text-align: left;height:30px;padding-left: 0px;">车牌号</th>'+
	'<th scope="col" width="100px" class="tdleft" style="text-align: left;height:30px;padding-left: 0px;">司机手机</th>'+
	'<th scope="col" width="80px" class="tdleft" style="text-align: left;height:30px;padding-left: 0px;">运单票数</th>'+
	'<th scope="col" width="60px" class="tdleft" style="text-align: left;height:30px;padding-left: 0px;">数量</th>'+
	'<th scope="col" width="100px" class="tdleft" style="text-align: left;height:30px;padding-left: 0px;">重量（公斤）</th>'+
	'<th scope="col" width="100px" class="tdleft" style="text-align: left;height:30px;padding-left: 0px;">体积（立方）</th>'
	'</tr>';
	for(var i =0;i<pagerow;i++){ //生成不见的空行
		txt=txt+'<tr id="tr'+i+'" class="ctr'+i+'" onmouseover="f_onmouseover('+i+')" onmouseout="f_onmouseout('+i+')">';
	 	txt=txt+'</tr>';
	 }
	$("#hgrid").empty().append(txt);
	
	var txt='<tr>' +
			'<th scope="col" width="200px" class="tdleft" style="text-align: left;height:30px;padding-left: 10px;">操作</th>' +
			'</tr>';
	for(var i =0;i<pagerow;i++){ //生成不见的空行
		txt=txt+'<tr id="czTr'+i+'" class="ctr'+i+'" onmouseover="f_onmouseover('+i+')" onmouseout="f_onmouseout('+i+')">';
	 	txt=txt+'</tr>';
	 }
	$("#operation").empty().append(txt);
}

function f_onmouseover(i){
	$('.ctr'+i).css("background-color","#fffddd");
}
function f_onmouseout(i){
	$('.ctr'+i).css("background-color","white");
}
function f_hgrid_json(param) {//刷新hGrid数据
	//vparam不包括页码和每页行数
	param=f_hgrid_getparam(param); //得到全部参数	
 	$.ajax({
 	url: "../dispatchtrackcs/selectWaybillstowageList",   
 	type:'post',	
 	dataType:'json', 
 	data:param, //参数     	         
   	success:function(data){//回传函数
 		$('#loading').hide();
 		data=f_hgrid_setmsg(data); //设置总记录数,页信息等
 		if(data==null || data.length==0){
			$("#hgrid").parent().css("overflow-x","hidden");
		}else{
			$("#hgrid").parent().css("overflow-x","scroll");
		}
		var txt,oper='<td style="text-align:left;color:#444444;"></td>';
		for(var i =0;i<data.length;i++){ //展现返回的表格数据
			if(data[i].status=='待确认'){
				oper='<td style="text-align:left;color:#444444;"><a id="btnload" href="javascript:void(0);" onclick=loadCarConfirm("'+data[i].waybillstowageid+'","'+data[i].systemdispatchnumber+'") style="margin-left:5px;">装车确认</a>'+
				'&nbsp;<a href="javascript:void(0);" onclick=doDelete("'+data[i].waybillstowageid+'","'+data[i].systemdispatchnumber+'") style="margin-left:5px;">删除</a>'+
				'&nbsp;<a href="javascript:void(0);" onclick=f_print("'+data[i].waybillstowageid+'") style="margin-left:5px;">打印</a></td>';
			} else 
			if(data[i].status=='已确认'){
				oper='<td style="text-align:left;color:#444444;"><a id="btnbegin" href="javascript:void(0);" onclick=carBegin("'+data[i].waybillstowageid+'","'+data[i].systemdispatchnumber+'") style="margin-left:5px;">发车</a>'+
				'&nbsp;<a href="javascript:void(0);" onclick=f_print("'+data[i].waybillstowageid+'") style="margin-left:5px;">打印</a></td>';
			} else 
			if(data[i].status=='已发车'){
				oper='<td style="text-align:left;color:#444444;"><a id="btnarrive" href="javascript:void(0);" onclick=carArrive("'+data[i].waybillstowageid+'","'+data[i].systemdispatchnumber+'") style="margin-left:5px;">到车</a>'+
				'&nbsp;<a href="javascript:void(0);" onclick="track(&quot;'+format(data[i].carbegindate)+'&quot;,&quot;'+data[i].systemdispatchnumber+'&quot;,&quot;'+data[i].carplatenumber+'&quot;,&quot;'+data[i].drivermobile+'&quot;,&quot;'+data[i].drivername+'&quot;)" style="margin-left:5px;">跟踪</a>'+
				'&nbsp;<a href="javascript:void(0);" onclick="playback(&quot;'+data[i].status+'&quot;,&quot;'+data[i].carplatenumber+'&quot;,&quot;'+format(data[i].carbegindate)+'&quot;,&quot;'+format(data[i].carenddate)+'&quot;,&quot;'+data[i].systemdispatchnumber+'&quot;,&quot;'+data[i].drivermobile+'&quot;,&quot;'+data[i].drivername +'&quot;)" style="margin-left:5px;">回放</a>'+
				'&nbsp;<a href="javascript:void(0);" onclick=f_print("'+data[i].waybillstowageid+'") style="margin-left:5px;">打印</a></td>';
			} else 
			if(data[i].status=='已到车'){
				oper='<td style="text-align:left;color:#444444;"><a href="javascript:void(0);" onclick="playback(&quot;'+data[i].status+'&quot;,&quot;'+data[i].carplatenumber+'&quot;,&quot;'+format(data[i].carbegindate)+'&quot;,&quot;'+format(data[i].carenddate)+'&quot;,&quot;'+data[i].systemdispatchnumber+'&quot;,&quot;'+data[i].drivermobile+'&quot;,&quot;'+data[i].drivername +'&quot;)" style="margin-left:5px;">回放</a>'+
				'&nbsp;<a href="javascript:void(0);" onclick=f_print("'+data[i].waybillstowageid+'") style="margin-left:5px;">打印</a></td>';
			}
			txt=			
				'<td class="tdleft"><a href="javascript:void(0);" onclick=detailShow("'+data[i].waybillstowageid +'") class="fn-cBlue">'+data[i].systemdispatchnumber+'</a></td>'+
				'<td class="tdleft">'+data[i].status+'</td>'+
				'<td class="tdleft">'+format2date(data[i].dispatchdate)+'</td>'+
				'<td class="tdleft">'+data[i].paperdispatchnumber+'</td>'+
				'<td class="tdleft">'+format(data[i].carbegindate)+'</td>'+
				'<td class="tdleft">'+format(data[i].carenddate)+'</td>'+
				'<td class="tdleft">'+data[i].carplatenumber+'</td>'+
				'<td class="tdleft">'+data[i].drivermobile+'</td>'+
				'<td class="tdleft">'+data[i].waybillnum+'</td>'+
				'<td class="tdleft">'+(data[i].factnum/1).toFixed(2)+'</td>'+
				'<td class="tdleft">'+(data[i].factweight/1).toFixed(2)+'</td>'+
				'<td class="tdleft">'+(data[i].factvolume/1).toFixed(2)+'</td>';
			$('#tr'+i).empty().append(txt);
			$('#czTr'+i).empty().append(oper);
		}		
		var pagerow=$("#pagerow").val(); //每页行数
		 for(var i =data.length;i<pagerow;i++){
			 $('#tr'+i).empty();
			 $('#czTr'+i).empty();
		 }
      }
  }); 
}
function f_search(){
	$("#pagecode").val("1"); 
	var systemdispatchnumber = trim($("#systemdispatchnumber").val()),
		fromdate=trim($("#fromdate").val()),
		todate=trim($("#todate").val()),
		carplatenumber=trim($("#carplatenumber").val()),
		drivermobile=trim($("#drivermobile").val()),
		status=trim($('.status div span').text()),
		paperdispatchnumber=trim($("#paperdispatchnumber").val());
	if(status=='全部'){
		status='';
	}
	var params="systemdispatchnumber="+systemdispatchnumber+"&fromdate="+fromdate+"&todate="+todate+
		"&carplatenumber="+carplatenumber+"&drivermobile="+drivermobile+"&status="+status+"&paperdispatchnumber="+
		paperdispatchnumber+"&random="+Math.random();
	f_hgrid_json(params);
}
function format(dateStr){
	var targetDateStr = dateStr;
	var index = dateStr.indexOf(".");
	if(index != -1 ){
		targetDateStr = dateStr.slice(0,index);
	}
	var date = targetDateStr.substr(0,10);
	var time = targetDateStr.substr(11,targetDateStr.length);
	return date+' '+time;
}
function format2date(date){
	var date = date.toString();
	var index = date.indexOf(" ");
	//alert(date+index);
	return date.substr(0,index);
}
function f_hgrid_create(pagerow){//初始化
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
		'</div><div style="height: 100px;"></div>'
	
	$(".page_nav").remove();
	$("#loading").remove();
	$('#hgrid_div').after(txt); //after
	$('#hgrid').after('<div id="loading" style="text-align:center;"><img src="../imgs/sys/loading.gif" border="0" /></div>'); 

	$("#pagetop a").click(function(){ //首页
		window.scrollTo(0,0);
		$("#pagecode").val("1");	 
		f_hgrid_json(""); 
		return false; 
	});
	
	$("#pagenext a").click(function(){//下一页
		window.scrollTo(0,0);
		var pagecode=parseInt($("#pagecode").val())+1;
		var recordcount=parseInt($("#recordcount").val());
		var pagerow=parseInt($("#pagerow").val());		
		if (pagecode>parseInt((recordcount-1)/pagerow)+1){pagecode=parseInt((recordcount-1)/pagerow)+1;}
		$("#pagecode").val(pagecode);
		f_hgrid_json("");
		return false;
	});
	
	$("#pagepre a").click(function(){ //上一页
		window.scrollTo(0,0);
		var pagecode=parseInt($("#pagecode").val());
		pagecode=pagecode-1;
		if (pagecode<1) pagecode=1;
		$("#pagecode").val(pagecode);
		f_hgrid_json("");
		return false; 
	});
	
	$("#pagebottom a").click(function(){ //末页
		window.scrollTo(0,0);
		var pagecode=parseInt($("#pagecode").val());
		var recordcount=parseInt($("#recordcount").val());
		var pagerow=parseInt($("#pagerow").val());		
		if (pagecode!=parseInt((recordcount-1)/pagerow)+1){
			pagecode=parseInt((recordcount-1)/pagerow)+1;
			$("#pagecode").val(pagecode);
			f_hgrid_json("");
			}
		return false; 
	});

	for (var i=1;i<=9;i++){
		$("#page"+i+" a").click(function(){ 
			window.scrollTo(0,0);
			$("#pagecode").val($(this).text());
			f_hgrid_json("");
			return false; 
		});
	}

}

/**
 * 调度单详情
 * @param waybillstowageid
 * @return
 */
function detailShow(waybillstowageid){
	var url = '../dispatchtrackcs/waybillstowage_detail?waybillstowageid='+waybillstowageid;
	window.open(url, "_blank");
}

/**
 * 装车确认
 * @param waybillstowageid
 * @return
 */
function loadCarConfirm(waybillstowageid,systemdispatchnumber){
	var height=240;
	ymPrompt.win({message:'../dispatchtrackcs/carloadconfirm?waybillstowageid='+waybillstowageid+'&systemdispatchnumber='+systemdispatchnumber,width:350,height:height,fixPosition:true,dragOut:false,handler:callBack,title:'装车确认',iframe:true});
}

/**
 * 调度单删除
 * @param waybillstowageid
 * @return
 */
function doDelete(waybillstowageid,systemdispatchnumber){
	ymPrompt.confirmInfo({message:'是否删除该票调度单?',autoClose: true,height:200,width:300,fixPosition:true,dragOut:false,handler: 
		function(status){
		if(status=='ok'){
			$.ajax({
				url: "../dispatchtrackcs/delete_waybillstowage", 
				type:"post",
				dataType:"json",
				data:{"waybillstowageid":waybillstowageid,"systemdispatchnumber":systemdispatchnumber},
				success:function(data){
					if(data.msg=="ok"){
						f_hgrid_json("");
						ymPrompt.alert("删除成功！");
					}else{
						ymPrompt.alert("删除失败！");
					}
				},
				error:function(){
					ymPrompt.alert("删除失败！");
				}
		});
		}else{
			return false;
		}
		}
		})
}

/**
 * 发车确认
 * @param waybillstowageid
 * @return
 */
function carBegin(waybillstowageid,systemdispatchnumber){
	var height=240;
	ymPrompt.win({message:'../dispatchtrackcs/carbeginconfirm?waybillstowageid='+waybillstowageid+'&systemdispatchnumber='+systemdispatchnumber,width:350,height:height,fixPosition:true,dragOut:false,handler:callBack,title:'发车确认',iframe:true});
}

/**
 * 到车确认
 * @param waybillstowageid
 * @return
 */
function carArrive(waybillstowageid,systemdispatchnumber){
	var height=240;
	ymPrompt.win({message:'../dispatchtrackcs/cararriveconfirm?waybillstowageid='+waybillstowageid+'&systemdispatchnumber='+systemdispatchnumber,width:350,height:height,fixPosition:true,dragOut:false,handler:callBack,title:'到车确认',iframe:true});
}

function callBack(data){
	switch(data){
	case "load_ok":
		ymPrompt.alert({message:"装车确认成功！",handler:f_search});
		break;
	case "load_fail":
		ymPrompt.alert({message:"装车确认失败！",handler:f_search});
		break;
	case "begin_ok":
		ymPrompt.alert({message:"发车确认成功！",handler:f_search});
		break;
	case "begin_fail":
		ymPrompt.alert({message:"发车确认失败！",handler:f_search});
		break;
	case "arrive_ok":
		ymPrompt.alert({message:"到车确认成功！",handler:f_search});
		break;
	case "arrive_fail":
		ymPrompt.alert({message:"到车确认失败！",handler:f_search});
		break;
	}
}

/**
 * @author wei.huang
 * @date 2014-1-21
 * @function 调度单跟踪
 * @return
 */
function track(factCarStartDate,systemDispatchNumber,carPlateNumber,driverMobile,driverName){
	$.ajax({
		url: "../dispatchtrackcs/track",  
	 	type:'post',	
	 	dataType:'json', 
	 	data:{"starttime":factCarStartDate,"systemdispatchnumber":systemDispatchNumber,"carplatenumber":carPlateNumber,"drivermobile":driverMobile,"drivername":driverName}, //参数     	               
	   	success:function(data){//回传函数
	 		if(data.msg=="sorry"){
	 			ymPrompt.alert("此手机号对应的司机会员不存在！");
	 			return;
	 		}
	 		window.open("../lbsdevicecs/lbsdeviceMap.html?data="+encodeURI(JSON.stringify(data)));
		}
	  }); 
}

/**
 * 打印调度单
 * @param parId
 * @return
 */
function f_print(waybillstowageid) {//打印预览
	var url = '../dispatchtrackcs/waybillstowage_print?waybillstowageid='+waybillstowageid;
	window.open(url,"_blank");
}

/**
 * @author wei.huang
 * @date 2014-1-9
 * @function 调度单回放
 * @return
 */
function playback(status,carPlateNumber,startTime,endTime,systemDispatchNumber,driverMobile,driverName){
	$.ajax({
		url: "../dispatchtrackcs/playback",
	 	type:'post',	
	 	dataType:'json', 
	 	data:{"status":status,"carplatenumber":carPlateNumber,"starttime":startTime,"endtime":endTime,"systemdispatchnumber":systemDispatchNumber,"drivermobile":driverMobile,"drivername":driverName}, //参数     	               
	   	success:function(data){//回传函数
	 		if(data.msg=="sorry"){
	 			ymPrompt.alert("此手机号对应的司机会员不存在！");
	 			return;
	 		}
	 		window.open("../lbsdevicecs/carNumberMap.html?data="+encodeURI(JSON.stringify(data)));
		}
	  }); 
}

function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}	