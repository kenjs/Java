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
		'</tr>'+
		'<tr id="total_tr"></tr>';
	for(var i =0;i<pagerow;i++){ //生成不见的空行
		txt=txt+'<tr id="tr'+i+'" class="ctr'+i+'" onmouseover="f_onmouseover('+i+')" onmouseout="f_onmouseout('+i+')">';
	 	txt=txt+'</tr>';
	 }
	$("#hgrid").empty().append(txt);
	
	var txt='<tr>' +
	'<th scope="col"   width="100px" class="tdleft">操作</th>' +
	'</tr><tr><td style="border:none;"></td></tr>';
	for(var i =0;i<pagerow;i++){ //生成不见的空行
		txt=txt+'<tr id="czTr'+i+'" class="ctr'+i+'" onmouseover="f_onmouseover('+i+')" onmouseout="f_onmouseout('+i+')">';
	 	txt=txt+'</tr>';
	 }
	$("#operation").empty().append(txt);
}
 function f_searchclick(){
	    $("#pagecode").val("1");
		var waybillnumber = trim($("#waybillnumber").val());
		var frompartyname = trim($("#frompartyname").val());
		var mdd = trim($("#mdd").val());
		var topartyname = trim($("#topartyname").val());
		var khh = trim($("#khh").val());
		var status = $('.status div span').text();
		var billstatus = $('.billstatus div span').text();
		if(status=="全部"){
				status="";
		}
		if(billstatus=="全部"){
			billstatus="all";
		}
		//alert(status+billstatus);
		var fromdate= $("#fromdate").val();
		var todate= $("#todate").val();
		var params="&waybillnumber="+waybillnumber+"&frompartyname="+frompartyname+"&topartyname="+topartyname+"&status="+
		           status+"&billstatus="+billstatus+"&fromdate="+fromdate+"&fromQuery="+"waybillamount"+"&todate="+todate+
		           "&menuindex="+2+"&clientnumber="+khh+"&consigneetown="+mdd;
		//alert(params);
		f_hgrid_ini();
		f_hgrid_json(params);
}
 //关闭费用录入 后的刷新
 function f_searchclick1(){
	 	//alert($("#pagecode").val());
		f_hgrid_json("");
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
		var flag = false;
		var params=f_hgrid_getparam(param); //得到全部参数
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
			for(var i =0;i<data.length;i++){ //展现返回的表格数据
				var billstatus = data[i].billstatus;
				flag = true;
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
				
				if(data[i].billstatus=="已确认"){
					txt1 = '<td class="tdleft" style="text-align:left;color:#444444;"><a id="btndetail" href="javascript:void(0);" onclick=btnDetail("'+data[i].waybillid+'") style="margin-left:5px;">详情</a></td>';
				}else if(data[i].billstatus=="已计费"){
					txt1 = '<td class="tdleft" style="text-align:left;color:#444444;"><a id="btndetail" href="javascript:void(0);" onclick=btnDetail("'+data[i].waybillid+'") style="margin-left:5px;">详情</a>'
					+'&nbsp;<a href="javascript:void(0);" style="margin-left:5px;" onClick=addBution("'+data[i].waybillid+'")>修改</a></td>';
				}else{
					txt1 = '<td class="tdleft" style="text-align:left;color:#444444;"><a id="btndetail" href="javascript:void(0);" onclick=btnDetail("'+data[i].waybillid+'") style="margin-left:5px;">详情</a>'
					+'&nbsp;<a href="javascript:void(0);" style="margin-left:5px;" onClick=addBution("'+data[i].waybillid+'")>录入</a></td>';
				}	
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
          },
          error:function(XMLHttpRequest, textStatus, errorThrown){
        	  alert(textStatus);
          }
	  }); 
}
function addBution(waybillid){
	//window.location.href="../waybillamountcs/waybillamountAdd?order=5&waybillid="+waybillid;
	var url = "../waybillamountcs/waybillamountAdd?order=5&waybillid="+waybillid;
	window.open(url, "_blank");
}
function btnDetail(waybillid){
	var url = "../waybillamountcs/waybillamountDetail?order=5&waybillid="+waybillid;
	window.open(url, "_blank");
}
function waybillDetail(waybillid){
	var url = "../waybillcs/waybill_detail?order=5&waybillid="+waybillid;
	window.open(url, "_blank");
}
function backBtn(){
	opener.f_searchclick1();
	window.close();
	//改弹出了
//	var order = getid();
//	if(order == 6){
//		window.location.href="../waybillamountcs/waybillamountconfirmlist?order=6";
//	} else{
//		window.location.href="../waybillamountcs/waybillamountList?order=5";		
//	}
}
function amountBtn(ysbz){
	var receivablefreight= $("#receivablefreight").val();
	var payablefreight= $("#payablefreight").val();
	if(ysbz==1){
		if($("#ysyf").val()==null||$("#ysyf").val()==""){
			if(receivablefreight!=null&&receivablefreight!=""){
				$("#ysyf").val(receivablefreight);
				countYs();
			}
		}
	}else{
		if($("#yfyf").val()==null||$("#yfyf").val()==""){
			if(payablefreight!=null&&payablefreight!=""){
				$("#yfyf").val(payablefreight);
				countYf();
			}
		}
	}
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
	$('#hgrid').after('<div id="loading" style="text-align:center;padding:20px 0 ;"><img src="../imgs/sys/loading.gif" border="0" /></div>'); 

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
/*******************************************详情(修改)******************************************************/
var yshj = 0;
var yfhj = 0;
var xqbz = "";
/**
 * hcm
 * 获取当前waybillid
 */
function getWaybillid(){
	var url_Data= document.URL;
	var num=url_Data.indexOf("=");
	url_Data=url_Data.substr(num+1);
	$("#waybillid").val(url_Data.split("=")[1]);
}
function f_hgrid_ini_goods() {//初始化表格
	var pagerow=99;  //参数为每页行数
	//f_hgrid_create(pagerow); 	
	//注意：此处的列宽和为820px
	var order = getid();
	if(order == 5){
		$("#a_order_id").text("费用录入");
		$("#a_order_id").attr('href','../waybillamountcs/waybillamountList?order=5');
	} else
	if(order == 6){
		$("#a_order_id").text("费用确认");
		$("#a_order_id").attr('href','../waybillamountcs/waybillamountconfirmlist?order=6');
	}
	
	var txt='<tr><td height="36px;"style="text-align: left;" colspan="9"><b class="ft-14">货物信息</b></td></tr>'+	
		'<tr>' +
		'<th scope="col"   width="146px">货物名称</th>' +
		'<th scope="col"   width="90px" >货物类型</th>' +
		'<th scope="col"   width="80px" >规格</th>' +
		'<th scope="col"   width="80px" >型号</th>' +
		'<th scope="col"   width="60px">数量</th>' +
		
		'<th scope="col"   width="100px" >包装</th>' +
		'<th scope="col"   width="100px" class="tdleft">重量(kg)</th>' +
		'<th scope="col"   width="100px" class="tdleft">体积(m³)</th>' +
		'<th scope="col"   width="100px" class="tdleft">声明价值</th>' +
		'<th scope="col"   width="140px" class="tdleft">计费方式</th>' +
		'</tr>';
	for(var i =0;i<pagerow+1;i++){ //生成不见的空行
		txt=txt+'<tr id="tr'+i+'" class="ctr'+i+'" onmouseover="f_onmouseover('+i+')" onmouseout="f_onmouseout('+i+')">';
	 	txt=txt+'</tr>';
	 }
	$("#hgrid").empty().append(txt);
}
/**
 * hcm
 * 填充详情
 */
function fillDetail(bz){
	var params="waybillid="+$("#waybillid").val();
	xqbz=bz
	$.ajax({
		url: "../waybillamountcs/selectWaybillById",
	 	type:'post',	
	 	dataType:'json', 
	 	data:params, //参数                   
	   	success:function(data){
		if(bz=="xg"){
			if(data.waybillamountlist.length<1){
				document.title="新增";
				$("#xg").html("新增");
			}
		}
		for(var i=0;i<data.waybillamountlist.length;i++){
			 fillWaybillamount(data.waybillamountlist[i]);
		}
		if(yshj==0){
			yshj="";
		}else{
			yshj=yshj.toFixed(2);
		}
		if(yfhj==0){
			yfhj="";
		}else{
			yfhj=yfhj.toFixed(2);
		}
		$("#yshj").html(yshj);
		$("#yfhj").html(yfhj);
		
		$("#ydh").html('<a href="#" onclick=waybillDetail("'+data.waybillid+'") class="fn-cBlue">'+data.waybillnumber+'</a>');
		$("#khdh").html("客户单号："+data.clientnumber);
		$("#tyrq").html("托运日期："+format(data.consigndate));
		$("#fbs").html("分派分包商："+data.topartyname);
		
		$("#fhf").html(data.frompartyname);
		$("#fhlxr").html(data.consignorlinkman);
		$("#fhlxdh").html(data.consignortelephonenumber);
		$("#fhsj").html(data.consignormobilenumber);
		$("#fhd").html(data.consignorprovince+" "+data.consignorcity+" "+data.consignorregion);
		$("#fhxxdz").html(data.consignortown);
		var str="";
		if(data.distance!=null&&data.distance!=""){
			str = data.distance+' 公里'
		}
		$("#jl").html(str);
		
		$("#shf").html(data.consignee);
		$("#shlxr").html(data.consigneelinkman);
		$("#shlxdh").html(data.consigneetelephonenumber);
		$("#shsj").html(data.consigneemobilenumber);
		$("#shd").html(data.consigneeprovince+" "+data.consigneecity+" "+data.consigneeregion);
		$("#shxxdz").html(data.consigneetown);
		/*$("#hwmc").html(data.goodslist[0].goodsname);
		$("#hwlx").html(data.goodslist[0].goodstype);
		$("#gg").html(data.goodslist[0].spec);
		$("#xh").html(data.goodslist[0].model);
		$("#sl").html(data.goodslist[0].factnum);
		$("#bz").html(data.goodslist[0].packagename);
		$("#zl").html(data.goodslist[0].factweight);
		$("#tj").html(data.goodslist[0].factvolume);
		$("#smjz").html(data.goodslist[0].claimvalue);
		$("#jffs").html(data.goodslist[0].measuretype);*/
		$("#jsfs").html(data.settletype);
		$("#shfs").html(data.receivetype);
		$("#dhfs").html(data.arrivetype);
		$("#hdfs").html(data.backbilltype);
		$("#hdzs").html(data.backbillnum);
		$("#partyid").val(data.partyid);
		$("#frompartyid").val(data.frompartyid);
		$("#topartyid").val(data.topartyid);
		$("#receivablefreight").val((data.receivablefreight/1).toFixed(2));
		$("#payablefreight").val((data.payablefreight/1).toFixed(2));
		//alert(data.payablefreight+" "+data.receivablefreight);
 		if(data.goodslist!=null||data.goodslist.length>0){
 			var txt;
 			var num = 0;
 			var weight = 0;
 			var volume = 0;
 			var value=0;
 			var j = data.goodslist.length+1;
 			for(var i =0;i<data.goodslist.length;i++){ //展现返回的表格数据
 				num=num+data.goodslist[i].factnum/1;
 				weight=weight+data.goodslist[i].factweight/1;
 				volume=volume+data.goodslist[i].factvolume/1;
 				value=value+data.goodslist[i].claimvalue/1;
 				txt='<td class="tdleft">'+data.goodslist[i].goodsname+'</td>'+
 					'<td class="tdleft">'+data.goodslist[i].goodstype+'</td>'+
 					'<td class="tdleft">'+data.goodslist[i].spec+'</td>'+
 					'<td class="tdleft">'+data.goodslist[i].model+'</td>'+
 					'<td class="tdleft">'+data.goodslist[i].factnum+'</td>'+
 					
 					'<td class="tdleft">'+data.goodslist[i].packagename+'</td>'+
 					'<td class="tdleft">'+data.goodslist[i].factweight+'</td>'+
 					'<td class="tdleft">'+data.goodslist[i].factvolume+'</td>'+
 					'<td class="tdleft">'+data.goodslist[i].claimvalue+'</td>'+
 					'<td class="tdleft">'+data.goodslist[i].measuretype+'</td>';
 				$('#tr'+i).empty().append(txt);
 			}
 			num=dropZero(num);
 			weight=dropZero(weight.toFixed(2));
 			volume=dropZero(volume.toFixed(2));
 			value=dropZero(value.toFixed(2));
 			var total = '<td class="tdleft">'+'合计'+'</td>'+
						'<td class="tdleft">'+'</td>'+
						'<td class="tdleft">'+'</td>'+
						'<td class="tdleft">'+'</td>'+
						'<td class="tdleft">'+num+'</td>'+
						'<td class="tdleft">'+'</td>'+
						'<td class="tdleft">'+weight+'</td>'+
						'<td class="tdleft">'+volume+'</td>'+
						'<td class="tdleft">'+value+'</td>'+
						'<td class="tdleft">'+'</td>';
 			$('#tr'+j).empty().append(total);
 			//$("#hgrid tr:last").append(total);
 			var pagerow=$("#pagerow").val(); //每页行数
 			for(var i =data.length;i<pagerow;i++){
 				 $('#tr'+i).empty();
 			}
 		}
	}
	})
}
function dropZero(number){
	var reg = /^[0]+(\.[0]{2})?$/;
	if(reg.test(number)){
		number='';
	}
	return number;
}
/**
 * 填充对应费用
 * @param waybillamount
 * @return
 */
function fillWaybillamount(waybillamount) {
	var ysyf;
	if (waybillamount.inorout == "1") {
		ysyf = "ys";
		yshj=yshj+waybillamount.amount/1;
	} else {
		ysyf = "yf";
		yfhj=yfhj+waybillamount.amount/1;
	}
	var num = waybillamount.amount;
	var id = waybillamount.waybillamountid;
	if(xqbz=="xq"){
		switch (waybillamount.amountitem) {
		case "运费":
			$("#" + ysyf + "yf").html(num);
			break;
		case "保险费":
			$("#" + ysyf + "bxf").html(num);
			break;
		case "提货费":
			$("#" + ysyf + "thf").html(num);
			break;
		case "送货费":
			$("#" + ysyf + "shf").html(num);
			break;
		case "包装费":
			$("#" + ysyf + "bzf").html(num);
			break;
		case "燃油附加费":
			$("#" + ysyf + "ryfjf").html(num);
			break;
		case "短信通知费":
			$("#" + ysyf + "dxtzf").html(num);
			break;
		case "其他费用":
			$("#" + ysyf + "qt").html(num);
			break;
		}
	}else{
		switch (waybillamount.amountitem) {
		case "运费":
			$("#" + ysyf + "yf").val(num);
			$("#" + ysyf + "yfid").val(id);
			break;
		case "保险费":
			$("#" + ysyf + "bxf").val(num);
			$("#" + ysyf + "bxfid").val(id);
			break;
		case "提货费":
			$("#" + ysyf + "thf").val(num);
			$("#" + ysyf + "thfid").val(id);
			break;
		case "送货费":
			$("#" + ysyf + "shf").val(num);
			$("#" + ysyf + "shfid").val(id);
			break;
		case "包装费":
			$("#" + ysyf + "bzf").val(num);
			$("#" + ysyf + "bzfid").val(id);
			break;
		case "燃油附加费":
			$("#" + ysyf + "ryfjf").val(num);
			$("#" + ysyf + "ryfjfid").val(id);
			break;
		case "短信通知费":
			$("#" + ysyf + "dxtzf").val(num);
			$("#" + ysyf + "dxtzfid").val(id);
			break;
		case "其他费用":
			$("#" + ysyf + "qt").val(num);
			$("#" + ysyf + "qtid").val(id);
			break;
		}
	}
	
}
/**
 * hcm
 * 保存
 * @return
 */
function saveWaybillamount(){
	var xyNum="";
	$(".fyNum").each(function(i,obj){
		if(isNaN(obj.value)&&obj.value!=null&&obj.value!=""){
			xyNum="N";
		};
	});
	if(xyNum=="N"){
		ymPrompt.alert('输入有误,请输入数字！');
		return;
	}
	var url="../waybillamountcs/saveWaybillamount";
	var params="waybillid="+$("#waybillid").val()+"&partyid="+$("#partyid").val()+"&frompartyid="+$("#frompartyid").val()+"&topartyid="+$("#topartyid").val()+
			   "&ysyf="+$.trim($("#ysyf").val())+"&ysbxf="+$.trim($("#ysbxf").val())+
			   "&ysthf="+$.trim($("#ysthf").val())+"&ysshf="+$.trim($("#ysshf").val())+
			   "&ysbzf="+$.trim($("#ysbzf").val())+"&ysryfjf="+$.trim($("#ysryfjf").val())+
			   "&ysdxtzf="+$.trim($("#ysdxtzf").val())+"&ysqt="+$.trim($("#ysqt").val())+
			   
			   "&yfyf="+$.trim($("#yfyf").val())+"&yfbxf="+$.trim($("#yfbxf").val())+
			   "&yfthf="+$.trim($("#yfthf").val())+"&yfshf="+$.trim($("#yfshf").val())+
			   "&yfbzf="+$.trim($("#yfbzf").val())+"&yfryfjf="+$.trim($("#yfryfjf").val())+
			   "&yfdxtzf="+$.trim($("#yfdxtzf").val())+"&yfqt="+$.trim($("#yfqt").val())+
			   "&random="+Math.random();
	$.ajax({
		url : url,
		dataType : "json",
		type :'POST',
		data:params,
		success : function(data) {
			if(data.msg=="ok"){
				ymPrompt.alert({message:'保存成功！',handler:successFun});
			}else{
				ymPrompt.alert('保存失败！');
			}
		}
  });
}
/**
 * hcm
 * 保存并确认
 * @return
 */
function saveAndConfirmListWaybillamount(){
	var xyNum="";
	$(".fyNum").each(function(i,obj){
		if(isNaN(obj.value)&&obj.value!=null&&obj.value!=""){
			xyNum="N";
		};
	});
	if(xyNum=="N"){
		ymPrompt.alert('输入有误,请输入数字！');
		return;
	}
	var url="../waybillamountcs/saveWaybillamount";
	var params="waybillid="+$("#waybillid").val()+"&partyid="+$("#partyid").val()+"&frompartyid="+$("#frompartyid").val()+"&topartyid="+$("#topartyid").val()+
			   "&ysyf="+$.trim($("#ysyf").val())+"&ysbxf="+$.trim($("#ysbxf").val())+
			   "&ysthf="+$.trim($("#ysthf").val())+"&ysshf="+$.trim($("#ysshf").val())+
			   "&ysbzf="+$.trim($("#ysbzf").val())+"&ysryfjf="+$.trim($("#ysryfjf").val())+
			   "&ysdxtzf="+$.trim($("#ysdxtzf").val())+"&ysqt="+$.trim($("#ysqt").val())+
			   
			   "&yfyf="+$.trim($("#yfyf").val())+"&yfbxf="+$.trim($("#yfbxf").val())+
			   "&yfthf="+$.trim($("#yfthf").val())+"&yfshf="+$.trim($("#yfshf").val())+
			   "&yfbzf="+$.trim($("#yfbzf").val())+"&yfryfjf="+$.trim($("#yfryfjf").val())+
			   "&yfdxtzf="+$.trim($("#yfdxtzf").val())+"&yfqt="+$.trim($("#yfqt").val())+
			   "&random="+Math.random();
	$.ajax({
		url : url,
		dataType : "json",
		type :'POST',
		data:params,
		success : function(data) {
			if(data.msg=="ok"){
				billConfirm($("#waybillid").val());
			}else{
				ymPrompt.alert('保存失败！');
			}
		}
  });
}
function billConfirm(waybillid){
	var yshj = $("#yshj").html();
	var yfhj = $("#yfhj").html();
	if(isNaN(yshj)||isNaN(yfhj)){
		ymPrompt.alert('费用有误，无法确认！');
		return;
	}
	if(yshj<=0&&yfhj<=0){
		ymPrompt.alert('费用为零，无法确认！');
		return;
	}
	var billstatus = '已确认';
	var params = "&waybillid=" +waybillid+ "&billstatus=" + billstatus + "&random="+Math.random();
	$.ajax({
		url: "../waybillamountcs/updateBillStatusByWaybillId",   
		 type:'post',	
		 dataType:'json', 
		 data:params, //参数     	               
		 success:function(data){//回传函数
			var str = '保存并确认';
			if($("#xg").html()=="费用确认"){
				str = '费用确认';
			}
			if(data.msg == 'ok'){
				ymPrompt.alert({message:str+"成功！",handler:successFun});
			}else{
				ymPrompt.alert(str+'失败！');
			}
		 }
	});
}
function successFun(){
	opener.f_searchclick1();
	window.close(); 
//改弹窗了
//	var order = getid();
//	if(order == 5){
//		window.location.href="../waybillamountcs/waybillamountList?order=5";
//	} else
//	if(order == 6){
//		window.location.href="../waybillamountcs/waybillamountconfirmlist?order=6";
//	}
}