  function f_hgrid_ini() {//初始化表格
	var pagerow=10;  //参数为每页行数
	f_hgrid_create(pagerow); 	
	//注意：此处的列宽和为820px
	var txt='<tr>' +
		'<th scope="col"   width="110px" >运单编号</th>' +
		'<th scope="col"   width="60px" >运单状态</th>' +
		'<th scope="col"   width="60px" >计费状态</th>' +
		'<th scope="col"   width="80px" >托运日期</th>' +
		'<th scope="col"   width="160px">发货方</th>' +
		'<th scope="col"   width="170px">客户单号</th>' +
		
		'<th scope="col"   width="100px" >目的地</th>' +
		'<th scope="col"   width="160px">分包商</th>' +
		'<th scope="col"   width="110px" class="tdleft">数量</th>' +
		'<th scope="col"   width="120px" class="tdleft">重量</th>' +
		'<th scope="col"   width="110px" class="tdleft">体积</th>' +
		
		'<th scope="col"   width="130px" class="tdleft">应收</th>' +
		'<th scope="col"   width="130px" class="tdleft">应付</th>' +
		'</tr><tr id="total_tr"></tr>';
	for(var i =0;i<pagerow;i++){ //生成不见的空行
		txt=txt+'<tr id="tr'+i+'" class="ctr'+i+'" onmouseover="f_onmouseover('+i+')" onmouseout="f_onmouseout('+i+')">';
	 	txt=txt+'</tr>';
	 }
	$("#hgrid").empty().append(txt);
	
	var txt='<tr>' +
	'<th scope="col"   width="150px" class="tdleft">操作</th>' +
	'</tr><tr><td style="border:none;"></td></tr>';
	for(var i =0;i<pagerow;i++){ //生成不见的空行
		txt=txt+'<tr id="czTr'+i+'" class="ctr'+i+'" onmouseover="f_onmouseover('+i+')" onmouseout="f_onmouseout('+i+')">';
	 	txt=txt+'</tr>';
	 }
	$("#operation").empty().append(txt);
}
function addBution(waybillid){
	//window.location.href="../waybillamountcs/waybillamountAdd?order=6&waybillid="+waybillid;
	var url = "../waybillamountcs/waybillamountAdd?order=6&waybillid="+waybillid;
	window.open(url, "_blank");
}
function billConfirm(waybillid,obj){
	//window.location.href="../waybillamountcs/waybillamountConfirmDetail?order=6&waybillid="+waybillid;
	var url = "../waybillamountcs/waybillamountConfirmDetail?order=6&waybillid="+waybillid;
	window.open(url, "_blank");
}
function f_onmouseover(i){
	$('.ctr'+i).css("background-color","#fffddd");
//	$('#tr'+i).css("background-color","#FFCC80");
}
function f_onmouseout(i){
	$('.ctr'+i).css("background-color","white");
}
function f_hgrid_json(param) {//刷新hGrid数据
		//vparam不包括页码和每页行数
		var params=f_hgrid_getparam(param); //得到全部参数
		var flag = false;
	 	$.ajax({
	 	url: "../waybilldistributioncs/waybillList",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:params, //参数     	               
	   	success:function(data){//回传函数
	 		$('#loading').hide();	 		
	 		data=f_hgrid_setmsg(data); //设置总记录数,页信息等
			var txt;
			var txt1;
			var issure;
			for(var i =0;i<data.length;i++){ //展现返回的表格数据
				flag = true;
				var billstatus = data[i].billstatus;
				if(data[i].billstatus == '已计费'){
					issure = '&nbsp;<a href="javascript:void(0);" style="margin-left:5px;" onClick=billConfirm("'+data[i].waybillid+'",this)>费用确认</a>'
					+'&nbsp;<a href="javascript:void(0);" style="margin-left:5px;" onClick=addBution("'+data[i].waybillid+'")>修改</a></td>';
				}else if(data[i].billstatus == '已确认'){
					issure = '&nbsp;<a href="javascript:void(0);" style="margin-left:5px;" onClick=billCancel("'+data[i].waybillid+'","'+data[i].insettlebillid+'","'+data[i].outsettlebillid+'",this)>取消确认</a></td>'
				}else{
					issure = '';
				}
				if(data[i].insettlebillid!=""&&data[i].insettlebillid!=null){
					billstatus = "已结算";
				}
				if(data[i].outsettlebillid!=""&&data[i].outsettlebillid!=null){
					billstatus = "已结算";
				}
				txt='<td class="tdleft"><a href="#" onclick=waybillDetail("'+data[i].waybillid+'") class="fn-cBlue">'+data[i].waybillnumber+'</a></td>'+
					'<td class="tdleft">'+data[i].status+'</td>'+
					'<td class="tdleft">'+billstatus+'</td>'+
					'<td class="tdleft">'+format(data[i].consigndate)+'</td>'+
					'<td class="tdleft">'+data[i].frompartyname+'</td>'+
					'<td class="tdleft">'+data[i].clientnumber+'</td>'+
					
					'<td class="tdleft">'+data[i].consigneecity+" "+data[i].consigneeregion+'</td>'+
					'<td class="tdleft">'+data[i].topartyname+'</td>'+
					'<td class="tdleft">'+data[i].factnum+'</td>'+
					'<td class="tdleft">'+data[i].factweight+'</td>'+
					'<td class="tdleft">'+data[i].factvolume+'</td>'+
					
					'<td class="tdleft">'+data[i].receivablesum+'</td>'+
					'<td class="tdleft">'+data[i].payablesum+'</td>';
				txt1 = '<td class="tdleft" style="text-align:left;"><a id="btndetail" href="javascript:void(0);" onclick=btnDetail("'+data[i].waybillid+'") style="margin-left:5px;">详情</a>'
					+issure;
				$('#tr'+i).empty().append(txt);
				$('#czTr'+i).empty().append(txt1);
			}
			var totHtml = '';
			if(flag){
				$.ajax({
					url: "../waybilldistributioncs/total_json",   
				 	type:'post',	
				 	dataType:'json', 
				 	data:params, //参数     	               
				   	success:function(data){						
						totHtml = '<td><span style="font-weight:bold;color:red"> 合计</span></td>'+'<td></td>'+'<td></td>'+'<td></td>'+'<td></td>'+
							'<td></td>'+'<td></td>'+'<td></td>'+
							'<td><span style="font-weight:bold;color:red">'+data.num+'</span></td>'+
							'<td><span style="font-weight:bold;color:red">'+data.weight+'</span></td>'+
							'<td><span style="font-weight:bold;color:red">'+data.volume+'</span></td>'+
							'<td><span style="font-weight:bold;color:red">'+data.receivablesum+'</span></td>'+
							'<td><span style="font-weight:bold;color:red">'+data.payablesum+'</span></td>';
						$("#total_tr").empty().append(totHtml);
					}
				});
			}
			var pagerow=$("#pagerow").val(); //每页行数
			for(var i =data.length;i<pagerow;i++){
				 $('#tr'+i).empty();
				 $('#czTr'+i).empty();
			}
          }
	  }); 
}
function billCancel(waybillid,inId,outId,obj){
	//alert(inId+":"+outId);
	if(inId!=""&&inId!=null){
		ymPrompt.alert("已加入结算单，不可取消！");
		return;
	}
	if(outId!=""&&outId!=null){
		ymPrompt.alert("已加入结算单，不可取消！");
		return;
	}
	var billstatus = '已计费';
	var params = "&waybillid=" +waybillid+ "&billstatus=" + billstatus + "&random="+Math.random();
	ymPrompt.confirmInfo({message:'是否取消确认?',autoClose: true,height:200,width:300,fixPosition:true,dragOut:false,handler: 
		function(status){
			if(status=='ok'){
				$.ajax({
					url: "../waybillamountcs/updateBillStatusByWaybillId",   
					 type:'post',	
					 dataType:'json', 
					 data:params, //参数     	               
					 success:function(data){//回传函数
						if(data.msg == 'ok'){
							ymPrompt.alert("取消确认成功！");
							f_searchclick1();
						}
						
					}
				});
			}else{
				return false;
			}
		}
	});
}
function f_searchclick(){
	$("#pagecode").val("1");
	var waybillnumber = trim($("#waybillnumber").val());
	var mdd = trim($("#mdd").val());
	var frompartyname = trim($("#frompartyname").val());
	var topartyname = trim($("#topartyname").val());
	var status = $('.status div span').text();
	var billstatus = $('.billstatus div span').text();
	var khh = trim($("#khh").val());
	if(billstatus == '全部'){
		billstatus = 'billConfirm';
	}else{
		billstatus = $('.billstatus div span').text();
	}
	
	if(status == '全部'){
		status = '';
	}else{
		status =  $('.status div span').text();
	}
	
	var fromdate= $("#fromdate").val();
	var todate= $("#todate").val();
	var params="&waybillnumber="+waybillnumber+"&frompartyname="+frompartyname+"&topartyname="+topartyname+
				"&status="+status+"&billstatus="+billstatus+"&fromdate="+fromdate+"&todate="+todate+"&fromQuery="+"waybillamount"+
				"&clientnumber="+khh+"&consigneetown="+mdd;
	//alert(params);
    f_hgrid_ini();
	f_hgrid_json(params);
}
//关闭费用录入 后的刷新
function f_searchclick1(){
	f_hgrid_json("");
}
function btnDetail(waybillid){
	var url ="../waybillamountcs/waybillamountDetail?order=6&waybillid="+waybillid;
	window.open(url, "_blank");
}
function waybillDetail(waybillid){
	var url = "../waybillcs/waybill_detail?order=6&waybillid="+waybillid;
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
	return data.Data; 
}