/**
 * 加载订单详情信息
 * @author haoyong
 * @date 2014-4-18
 */
function f_init_waybill(){
	var waybillid = $("#waybillid").val();
	//加载运单信息，收、发货方信息，服务信息和签收信息
	$.ajax({
		url:"../outwaybillcs/oreder_detail_json",
		type:'post',
		dataType:'json',
		data:{"waybillid":waybillid},
		success:function(data){
			$("#waybill_number").html(data.waybillnumber);
			$("#status").html(data.status);
			$("#client_number").html(data.clientnumber);
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
			//加载货物信息
			if(data.outGoodsList!=null||data.outGoodsList.length>0){
	 			var txt;
	 			var num = 0;
	 			var weight = 0;
	 			var volume = 0;
	 			var value=0;
	 			var j = data.outGoodsList.length+1;
	 			for(var i =0;i<data.outGoodsList.length;i++){ //展现返回的表格数据
	 				num=num+data.outGoodsList[i].num/1;
	 				weight=weight+data.outGoodsList[i].weight/1;
	 				volume=volume+data.outGoodsList[i].volume/1;
	 				value=value+data.outGoodsList[i].claimvalue/1;
	 				txt='<td class="tdleft" style="padding-left:24px;">'+data.outGoodsList[i].goodsname+'</td>'+
	 					'<td class="tdleft">'+data.outGoodsList[i].goodstype+'</td>'+
	 					'<td class="tdleft">'+data.outGoodsList[i].spec+'</td>'+
	 					'<td class="tdleft">'+data.outGoodsList[i].model+'</td>'+
	 					'<td class="tdleft">'+data.outGoodsList[i].num+'</td>'+
	 					
	 					'<td class="tdleft">'+data.outGoodsList[i].packagename+'</td>'+
	 					'<td class="tdleft">'+data.outGoodsList[i].weight+'</td>'+
	 					'<td class="tdleft">'+data.outGoodsList[i].volume+'</td>';
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
	//alert($("#waybillid").val());
}