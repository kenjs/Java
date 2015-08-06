/**
 * 加载运详情信息
 * @author haoyong
 * @date 2013-11-27
 */
function f_init_waybill(){
	var order = getid();
	if(order == 1){
		//alert($("#a_order_id").text());
	} else 
	if(order == 2){
		$("#a_order_id").text("运单分派");
		$("#a_order_id").attr('href','../waybilldistributioncs/waybilldistributionlist?order=2');
	} else
	if(order == 3){
		$("#a_order_id").text("运单管理");
		$("#a_order_id").attr('href','../waybillcs/waybillManager?order=3');
	} else
	if(order == 5){
		$("#a_order_id").text("费用录入");
		$("#a_order_id").attr('href','../waybillamountcs/waybillamountList?order=5');
	} else
	if(order == 6){
		$("#a_order_id").text("费用确认");
		$("#a_order_id").attr('href','../waybillamountcs/waybillamountconfirmlist?order=6');
	}

	var waybillid = $("#waybillid").val();
	//加载运单信息，收、发货方信息，服务信息和签收信息
	$.ajax({
		url:"../waybillamountcs/selectWaybillById",
		type:'post',
		dataType:'json',
		data:{"waybillid":waybillid},
		success:function(data){
			$("#waybill_number").html(data.waybillnumber);
			$("#consign_date").html(format(data.consigndate));
			$("#urgency_degree").html(data.urgencydegree);
			$("#client_number").html(data.clientnumber);
			$("#toparty_name").html(data.topartyname);
			//发货方和收收货方信息
			$("#consignor_linkman").html(data.consignorlinkman);
			$("#consignee_linkman").html(data.consigneelinkman);
			$("#consignor_telephonenumber").html(data.consignortelephonenumber);
			$("#consignee_telephonenumber").html(data.consigneetelephonenumber);
			$("#consignor_mobilenumber").html(data.consignormobilenumber);
			$("#consignee_mobilenumber").html(data.consigneemobilenumber);
			$("#consignor_address").html(data.consignorprovince+data.consignorcity+data.consignorregion);
			$("#consignee_address").html(data.consigneeprovince+data.consigneecity+data.consigneeregion);
			$("#consignor_town").html(data.consignortown);
			$("#consignee_town").html(data.consigneetown);
			$("#distance").html(data.distance);
			$("#consignee").html(data.consignee);
			$("#from_partyname").html(data.frompartyname);
			//服务信息
			$("#settle_type").html(data.settletype);
			$("#receive_type").html(data.receivetype);
			$("#arrive_type").html(data.arrivetype);
			$("#backbill_type1").html(data.backbilltype);			
			$("#backbill_num").html(data.backbillnum);
			$("#waybill_memo").html(data.memo);			
			//运单状态的时间
			$("div#time_1").html(formatdate(data.inputdate));
			$("div#time_2").html(formatdate(data.suredate));
			$("div#time_3").html(formatdate(data.carbegindate));
			$("div#time_4").html(formatdate(data.carenddate));
			//签收信息
			$("#sign_status").html(data.waybillsign.signstatus);
			$("#sign_man").html(data.waybillsign.signman);
			$("#sign_num").html(data.waybillsign.signnum);
			$("#backbill_type2").html(data.waybillsign.backbilltype);
			$("#backbill_memo").html(data.waybillsign.memo);
			$("div#time_5").html(formatdate(data.waybillsign.inputdate));
			waybillstatus_imagechange(data.status);
			//加载货物信息
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
	 				txt='<td class="tdleft" style="padding-left:24px;">'+data.goodslist[i].goodsname+'</td>'+
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
	 			var total = '<td class="tdleft" style="padding-left:24px;">'+'合计'+'</td>'+
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
	});
	//运单操作日志信息
	$.ajax({
		url:"../waybilllogcs/selectWaybillLogList",
		type:'post',
		dataType:'json',
		data:{"waybillid":waybillid},
		success:function(data){
			var txt='';
			for(var i=0;i<data.length;i++){
				txt=txt+'<tr id="log'+i+'" onmouseover="f_over('+i+')" onmouseout="f_out('+i+')">'+
							'<td class="log_c" style="padding-left:20px;">'+data[i].waybillnumber+'</td>'+
							'<td class="log_c"">'+data[i].inputman+'</td>'+
							'<td class="log_c">'+data[i].inputdate+'</td>'+
							'<td class="log_c">'+data[i].status+'</td>'+
							'<td class="log_c">'+data[i].trace+'</td>'+
						'</tr>';				
			}
			$("#waybillog_grid").empty().append(txt);
		}
	});
}
function f_over(i){
	$('#log'+i).css("background-color","#fffddd");
//	$('#tr'+i).css("background-color","#FFCC80");
}
function f_out(i){
	$('#log'+i).css("background-color","white");
}
//运单进度条样式设置
function waybillstatus_imagechange(status_param){	//fn-bg-blue 待分单->待配载->已配载->已装车->已发车->已到车->已签收
	switch(status_param){
		case '已签收':
			$("span#waybill_status_5").removeClass("unfinished").addClass('complete');
			$("span#waybill_status_4").removeClass("unfinished").addClass('complete');
			$("span#waybill_status_3").removeClass("unfinished").addClass('complete');
			$("span#waybill_status_2").removeClass("unfinished").addClass('complete');
			$("span#waybill_status_1").removeClass("unfinished").addClass('complete');
			$("div#line_4").addClass("fn-bg-blue");$("div#line_3").addClass("fn-bg-blue");
			$("div#line_2").addClass("fn-bg-blue");$("div#line_1").addClass("fn-bg-blue");
			$("font#accept").addClass("detail_color");
			$("font#loaded").addClass("detail_color");
			$("font#started").addClass("detail_color");
			$("font#arrived").addClass("detail_color");
			$("font#signed").addClass("detail_color");
			break;
		case '已到车':
		case '已到达':
			$("span#waybill_status_5").removeClass("complete").addClass('unfinished');
			$("span#waybill_status_4").removeClass("unfinished").addClass('complete');
			$("span#waybill_status_3").removeClass("unfinished").addClass('complete');
			$("span#waybill_status_2").removeClass("unfinished").addClass('complete');
			$("span#waybill_status_1").removeClass("unfinished").addClass('complete');
			$("div#line_3").addClass("fn-bg-blue");
			$("div#line_2").addClass("fn-bg-blue");$("div#line_1").addClass("fn-bg-blue");
			$("font#accept").addClass("detail_color");
			$("font#loaded").addClass("detail_color");
			$("font#started").addClass("detail_color");
			$("font#arrived").addClass("detail_color");
			$("div#time_5").html("");
			break;
		case '已发车':
			$("span#waybill_status_5").removeClass("complete").addClass('unfinished');
			$("span#waybill_status_4").removeClass("complete").addClass('unfinished');
			$("span#waybill_status_3").removeClass("unfinished").addClass('complete');
			$("span#waybill_status_2").removeClass("unfinished").addClass('complete');
			$("span#waybill_status_1").removeClass("unfinished").addClass('complete');
			$("div#line_2").addClass("fn-bg-blue");$("div#line_1").addClass("fn-bg-blue");
			$("font#accept").addClass("detail_color");
			$("font#loaded").addClass("detail_color");
			$("font#started").addClass("detail_color");
			$("div#time_4").html("");
			$("div#time_5").html("");
			break;		
		case '已装车':
			$("span#waybill_status_5").removeClass("complete").addClass('unfinished');
			$("span#waybill_status_4").removeClass("complete").addClass('unfinished');
			$("span#waybill_status_3").removeClass("complete").addClass('unfinished');
			$("span#waybill_status_2").removeClass("unfinished").addClass('complete');
			$("span#waybill_status_1").removeClass("unfinished").addClass('complete');
			$("div#line_1").addClass("fn-bg-blue");
			$("font#accept").addClass("detail_color");
			$("font#loaded").addClass("detail_color");
			$("div#time_3").html("");
			$("div#time_4").html("");
			$("div#time_5").html("");
			//$("ul li:eq(2) div span").removeClass("unfinished").addClass('complete');
			break;
		case '待分单':
		case '待配载':
		case '已配载':
		case '已受理':
			$("span#waybill_status_5").removeClass("complete").addClass('unfinished');
			$("span#waybill_status_4").removeClass("complete").addClass('unfinished');
			$("span#waybill_status_3").removeClass("complete").addClass('unfinished');
			$("span#waybill_status_2").removeClass("complete").addClass('unfinished');
			$("span#waybill_status_1").removeClass("unfinished").addClass('complete');
			$("font#accept").addClass("detail_color");
			$("div#time_2").html("");
			$("div#time_3").html("");
			$("div#time_4").html("");
			$("div#time_5").html("");
			break;
	}
}
//初始化货物信息表格
function f_hgrid_ini_goods() {//初始化表格
	var pagerow=99;  //参数为每页行数
	//f_hgrid_create(pagerow); 	
	//注意：此处的列宽和为820px
	var txt='<tr><td height="36px;"style="text-align: left;padding-left:24px;" colspan="9"><b class="ft-14">货物信息</b></td></tr>'+	
		'<tr>' +
		'<th scope="col"   width="146px" style="padding-left:24px;">货物名称</th>' +
		'<th scope="col"   width="80px" >货物类型</th>' +
		'<th scope="col"   width="80px" >规格</th>' +
		'<th scope="col"   width="80px" >型号</th>' +
		'<th scope="col"   width="60px">数量</th>' +
		
		'<th scope="col"   width="100px" >包装</th>' +
		'<th scope="col"   width="100px" class="tdleft">重量(kg)</th>' +
		'<th scope="col"   width="100px" class="tdleft">体积(m³)</th>' +
		'<th scope="col"   width="100px" class="tdleft">声明价值</th>' +
		'<th scope="col"   width="150px" class="tdleft">计费方式</th>' +
		'</tr>';
	for(var i =0;i<pagerow+1;i++){ //生成不见的空行
		txt=txt+'<tr id="tr'+i+'" class="ctr'+i+'" onmouseover="f_onmouseover('+i+')" onmouseout="f_onmouseout('+i+')">';
	 	txt=txt+'</tr>';
	 }
	$("#hgrid").empty().append(txt);
}
function f_onmouseover(i){
	$('.ctr'+i).css("background-color","#fffddd");
//	$('#tr'+i).css("background-color","#FFCC80");
}
function f_onmouseout(i){
	$('.ctr'+i).css("background-color","white");
}
//如果数字为0或0.00,转为空格
function dropZero(number){
	var reg = /^[0]+(\.[0]{2})?$/;
	if(reg.test(number)){
		number='';
	}
	return number;
}
//格式时间（yyyy-MM-dd）
function format(date){
	var date = date.toString();
	var index = date.indexOf(" ");
	//alert(date+index);
	return date.substr(0,index);
}
//日期字符串转为yyyy-MM-dd HH:mm:ss
function formatdate(dateStr){
	var targetDateStr = dateStr;
	var index = dateStr.indexOf(".");
	if(index != -1 ){
		targetDateStr = dateStr.slice(0,index);
	}
	var date = targetDateStr.substr(0,10);
	var time = targetDateStr.substr(11,targetDateStr.length);
	return date+'</br>'+time;
}
//获取运单id
function getWaybillid(){
	var url_Data= document.URL;
	var num=url_Data.indexOf("=");
	url_Data=url_Data.substr(num+1);
	$("#waybillid").val(url_Data.split("=")[1]);
}