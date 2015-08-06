var sortArray = new Array();
function f_hgrid_ini() {//初始化表格
	var pagerow=10;  //参数为每页行数
	f_hgrid_create(pagerow); 	
	//注意：此处的列宽和为1860
	var txt='<tr>' +
		'<th scope="col"   width="110px" >运单编号</th>' +
		'<th scope="col"   width="110px" >客户单号'+
		'<a href="javascript:void(0);" onclick="sortSerach(this,1);"><img src="../imgs/sys/up-icon.png" title="升序" alt="升序" style="margin-bottom:-3px" id="up1"></a>'+
		'</th>' +
		'<th scope="col"   width="80px" >托运日期'+
		'<a href="javascript:void(0);" onclick="sortSerach(this,2);"><img src="../imgs/sys/up-icon.png" title="升序" alt="升序" style="margin-bottom:-3px" id="up2"></a>'+
		'</th>' +
		'<th scope="col"   width="60px" >运单状态</th>' +		
		'<th scope="col"   width="120px">发货联系人</th>' +
		'<th scope="col"   width="120px">发货地</th>' +
		'<th scope="col"   width="220px">发货详细地址</th>' +
		
		'<th scope="col"   width="120px">收货方</th>' +
		'<th scope="col"   width="120px">收货联系人</th>' +
		'<th scope="col"   width="120px">收货人手机</th>' +
		'<th scope="col"   width="120px">收货地</th>' +
		'<th scope="col"   width="300px">收货详细地址</th>' +
		
		'<th scope="col"   width="60px" class="tdleft">数量</th>' +
		'<th scope="col"   width="100px" class="tdleft">重量（公斤）</th>' +
		'<th scope="col"   width="100px" class="tdleft">体积（立方）</th>' +
		'</tr>'+
		'<tr id="total_goods">' +
		'</tr>';
	
	for(var i =0;i<pagerow;i++){ //生成不见的空行
		txt=txt+'<tr id="tr'+i+'" class="ctr'+i+'" onmouseover="f_onmouseover('+i+')" onmouseout="f_onmouseout('+i+')">';
	 	txt=txt+'</tr>';
	 }
	$("#hgrid").empty().append(txt);
	
	var txt='<tr>' +
	'<th scope="col"   width="150px" class="tdleft">操作</th>' +
	'</tr><tr><td></td></tr>';
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
		var params=f_hgrid_getparam(param); //得到全部参数
	 	$.ajax({
	 	url: "../waybillcs/selectWaybillList",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:params, //参数     	               
	   	success:function(data){//回传函数
	 		$('#loading').hide();
	 		jsonData=f_hgrid_setmsg(data);//设置总记录数,页信息等
			var txt;
			var txt1;
			var issure;
			data = jsonData.Data;
			for(var i =0;i<data.length;i++){ //展现返回的表格数据
				txt='<td class="tdleft"><a href="#" onclick=waybillDetail("'+data[i].waybillid+'") class="fn-cBlue">'+data[i].waybillnumber+'</a></td>'+
					'<td class="tdleft">'+data[i].clientnumber+'</td>'+
					'<td class="tdleft">'+format(data[i].consigndate)+'</td>'+
					'<td class="tdleft">'+data[i].status+'</td>'+
					'<td class="tdleft">'+data[i].consignorlinkman+'</td>'+
					'<td class="tdleft">'+data[i].consignorcity+" "+data[i].consignorregion+'</td>'+
					'<td class="tdleft" title="'+data[i].consignortown+'">'+data[i].consignortown+'</td>'+
					'<td class="tdleft">'+data[i].consignee+'</td>'+
					'<td class="tdleft">'+data[i].consigneelinkman+'</td>'+
					'<td class="tdleft">'+data[i].consigneemobilenumber+'</td>'+
					'<td class="tdleft">'+data[i].consigneecity+" "+data[i].consigneeregion+'</td>'+
					'<td class="tdleft" title="'+data[i].consigneetown+'">'+data[i].consigneetown+'</td>'+
					'<td class="tdleft">'+data[i].factnum+'</td>'+
					'<td class="tdleft">'+data[i].factweight+'</td>'+
					'<td class="tdleft">'+data[i].factvolume+'</td>';
				if(data[i].status=="已发车"){
					issure='&nbsp;<a href="javascript:void(0)" onclick="track(&quot;'+format(data[i].carbegindate)+'&quot;,&quot;'+data[i].systemdispatchnumber+'&quot;,&quot;'+data[i].carplatenumber+'&quot;,&quot;'+data[i].drivermobile+'&quot;,&quot;'+data[i].drivername+'&quot;)">跟踪</a>'+
					'&nbsp;<a href="javascript:void(0)" onclick="playback(&quot;'+data[i].status+'&quot;,&quot;'+data[i].carplatenumber+'&quot;,&quot;'+format(data[i].carbegindate)+'&quot;,&quot;'+format(data[i].carenddate)+'&quot;,&quot;'+data[i].systemdispatchnumber+'&quot;,&quot;'+data[i].drivermobile+'&quot;,&quot;'+data[i].drivername +'&quot;)">回放</a>';
				}else if(data[i].status=="已到车"||data[i].status=="已签收"){
					issure='&nbsp;<a href="javascript:void(0)" onclick="playback(&quot;'+data[i].status+'&quot;,&quot;'+data[i].carplatenumber+'&quot;,&quot;'+format(data[i].carbegindate)+'&quot;,&quot;'+format(data[i].carenddate)+'&quot;,&quot;'+data[i].systemdispatchnumber+'&quot;,&quot;'+data[i].drivermobile+'&quot;,&quot;'+data[i].drivername +'&quot;)">回放</a>';
				}else{
					issure="";
				}
				txt1 = '<td class="tdleft" style="text-align:left;"><a id="btndetail" href="javascript:void(0);" onclick=waybillDetail("'+data[i].waybillid+'") style="margin-left:5px;">详情</a>'
					+issure;
				$('#tr'+i).empty().append(txt);
				$('#czTr'+i).empty().append(txt1);
			}
			var totHtml = '<td>合计</td>'+'<td></td>'+'<td></td>'+'<td></td>'+'<td></td>'+
			'<td></td>'+'<td></td>'+'<td></td>'+'<td></td>'+'<td></td>'+
			'<td></td>'+'<td></td>'+'<td>'+jsonData.num.toFixed(2)+'</td>'+'<td>'+jsonData.weight.toFixed(3)+'</td>'+'<td>'+jsonData.volume.toFixed(3)+'</td>';
			$("#total_goods").empty().append(totHtml);
			var pagerow=$("#pagerow").val(); //每页行数
			for(var i =data.length;i<pagerow;i++){
				 $('#tr'+i).empty();
				 $('#czTr'+i).empty();
			}
          }
	  }); 
}

//运单信息导出excel
function export_waybill(param) {
	var url = "../waybillcs/exportExcel?"+param;
	window.open(url);
}

function searchBtn(){
	$("#pagecode").val("1");
	var waybillnumber = trim($("#waybillnumber").val());
	var consigneelinkman = trim($("#consigneelinkman").val());
	var consigneemobilenumber = trim($("#consigneemobilenumber").val());
	var khh = trim($("#khh").val());
	var status = $('.status div span').text();
	if(status == '全部'){
		status = '';
	}
	var fromdate= $("#fromdate").val();
    var todate= $("#todate").val();
	var params="&waybillnumber="+waybillnumber+"&consigneelinkman="+consigneelinkman+"&consigneemobilenumber="+consigneemobilenumber+
				"&status="+status+"&fromdate="+fromdate+"&todate="+todate+"&fromQuery="+"waybillListFhf"+"&clientnumber="+khh+
				"&dateSort=ASC";
	//alert(params);
    f_hgrid_ini();
	f_hgrid_json(params);
}
function sortSerach(obj,index){
	//clientnumber_sort_str = '';
	//consigndate_sort_str = '';
	sortArray.length = 0;
	$("#pagecode").val("1");
	var waybillnumber = trim($("#waybillnumber").val());
	var consigneelinkman = trim($("#consigneelinkman").val());
	var consigneemobilenumber = trim($("#consigneemobilenumber").val());
	var khh = trim($("#khh").val());
	var status = $('.status div span').text();
	if(status == '全部'){
		status = '';
	}
	var fromdate= $("#fromdate").val();
    var todate= $("#todate").val();
	var params="&waybillnumber="+waybillnumber+"&consigneelinkman="+consigneelinkman+"&consigneemobilenumber="+consigneemobilenumber+
				"&status="+status+"&fromdate="+fromdate+"&todate="+todate+"&fromQuery="+"waybillListFhf"+"&clientnumber="+khh;
	var img_id1 = $('#up1').attr('id'),img_id2 = $('#up2').attr('id');
	var id = $(obj).find('img').attr("id");
	if(index == 1){
		if(id == 'up1'){
			$(obj).html('<img src="../imgs/sys/down-icon.png" title="降序" alt="降序" style="margin-bottom:-3px" id="down1">');
			params = params + "&cnSort=DESC";
			//clientnumber_sort_str = "&cnSort=DESC";
			sortArray.push("&cnSort=DESC");
		} else{
			$(obj).html('<img src="../imgs/sys/up-icon.png" title="升序" alt="升序" style="margin-bottom:-3px" id="up1">');
			params = params + "&cnSort=ASC";	
			//clientnumber_sort_str = "&cnSort=ASC";
			sortArray.push("&cnSort=ASC");
		}
		/**if(img_id2=='up2'){
			params = params + '&dateSort=ASC';
		}else{
			params = params + '&dateSort=DESC';
		}**/
	} else {
		if(id == 'up2'){
			$(obj).html('<img src="../imgs/sys/down-icon.png" title="降序" alt="降序" style="margin-bottom:-3px" id="down2">');
			params = params + "&dateSort=DESC";	
			//consigndate_sort_str = "&dateSort=DESC";
			sortArray.push("&dateSort=DESC");
		} else{
			$(obj).html('<img src="../imgs/sys/up-icon.png" title="升序" alt="升序" style="margin-bottom:-3px" id="up2">');
			params = params + "&dateSort=ASC";
			//consigndate_sort_str = "&dateSort=ASC";
			sortArray.push("&dateSort=ASC");
		}
		/**if(img_id1=='up1'){
			params = params + '&cnSort=ASC';
		}else{
			params = params + '&cnSort=DESC';
		}**/
	}
	//f_hgrid_ini();
	f_hgrid_json(params);
}

function waybillDetail(waybillid){
	var url = "../waybillcs/waybill_detail?order=1&waybillid="+waybillid;
	window.open(url,"_blank");
}
function trim(str){ //删除左右两端的空格
	   return str.replace(/(^\s*)|(\s*$)/g, "");
}
function format(date){
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
		$('#hgrid').after('<div id="loading" style="text-align:center;padding:20px 0"><img src="../imgs/sys/loading.gif" border="0" /></div>'); 

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
function f_hgrid_setmsg(data){//data为json数据 
	//var recordcount=data['list'][0].map[0]['entry'][0]['string'][1]; //总记录数
    $(".myjl").remove();
    //	var recordcount=data[0].recordcount;//总记录数
	if(data==null||data=='undefined'){	
		$("#hgridDiv").css("overflow-x","hidden");
		$('<div class="myjl" style="font-size:18px;text-align:center;padding-top:20px;font-family:微软雅黑;">没有记录</div>').insertBefore(".page_nav");
		$(".page_nav").hide();
		return '';
		}
	else{
	var recordcount=data.Count;//总记录数
	if(recordcount==0||recordcount==''){
		$("#hgridDiv").css("overflow-x","hidden");
		$('<div class="myjl" style="font-size:18px;text-align:center;padding-top:20px;font-family:微软雅黑;">没有记录</div>').insertBefore(".page_nav");
		$(".page_nav").hide();
		return '';
	}
	else{
	$("#hgridDiv").css("overflow-x","scroll");
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
	return data; 
}
/**
 * @author wei.huang
 * @date 2014-1-23
 * @function 调度单跟踪
 * @return
 */
function track(factCarStartDate,systemDispatchNumber,carPlateNumber,driverMobile,driverName){
	$.ajax({
		url: "../waybillcs/track",  
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
 * @author wei.huang
 * @date 2014-1-23
 * @function 调度单回放
 * @return
 */
function playback(status,carPlateNumber,startTime,endTime,systemDispatchNumber,driverMobile,driverName){
	$.ajax({
		url: "../waybillcs/playback",
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

function search(){
	alert(0);
}

function exportExcel(obj){
	var waybillnumber = trim($("#waybillnumber").val());
	var consigneelinkman = trim($("#consigneelinkman").val());
	consigneelinkman=encodeURI(encodeURI(consigneelinkman));
	var consigneemobilenumber = trim($("#consigneemobilenumber").val());
	var khh = trim($("#khh").val());
	var status = $('.status div span').text();
	if(status == '全部'){
		status = '';
	}
	status=encodeURI(encodeURI(status));
	var fromdate= $("#fromdate").val();
    var todate= $("#todate").val();
	var params="waybillnumber="+waybillnumber+"&consigneelinkman="+consigneelinkman+"&consigneemobilenumber="+consigneemobilenumber+
				"&status="+status+"&fromdate="+fromdate+"&todate="+todate+"&fromQuery="+"waybillListFhf"+"&clientnumber="+khh;
	if(sortArray.length == 0){
		params = params + "&dateSort=ASC";
	}else{
		params = params + sortArray[0];
	}
	export_waybill(params);
}