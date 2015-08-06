var waybillstowageid=document.URL.split("?")[1].split("=")[1];
function init_waybll_grid(){
	var pagerow=99;
	var txt='<tr>' +
		'<th scope="col"   width="146px" style="padding-left:24px;">运单号</th>' +
		'<th scope="col"   width="90px" >运单状态</th>' +
		'<th scope="col"   width="90px" >托运日期</th>' +
		'<th scope="col"   width="120px" >收货联系人</th>' +
		'<th scope="col"   width="120px">收货地</th>' +
		'<th scope="col"   width="80px" >数量</th>' +
		'<th scope="col"   width="80px" >包装</th>' +
		'<th scope="col"   width="100px" class="tdleft">重量(公斤)</th>' +
		'<th scope="col"   width="100px" class="tdleft">体积(立方)</th>' +
		'<th scope="col"   width="220px" class="tdleft">备注</th>' +
	'</tr>';
for(var i =0;i<pagerow+1;i++){ //生成不见的空行
	txt=txt+'<tr id="tr'+i+'" class="ctr'+i+'" onmouseover="f_onmouseover('+i+')" onmouseout="f_onmouseout('+i+')">';
 	txt=txt+'</tr>';
 }
$("#hgrid").empty().append(txt);
}
function f_onmouseover(i){
$('.ctr'+i).css("background-color","#fffddd");
//$('#tr'+i).css("background-color","#FFCC80");
}
function f_onmouseout(i){
$('.ctr'+i).css("background-color","white");
}

function f_init_json(){
	$.ajax({
		url:"../dispatchtrackcs/waybillstowage_detail_json",
		type:'post',
		dataType:'json',
		data:{"waybillstowageid":waybillstowageid},
		success:function(data){
			//调度单信息
			$("#systemdispatchnumber_id").text(data.systemdispatchnumber);
			$("#status_id").text(data.status);
			$("#dispatchdate_id").text(format(data.dispatchdate));
			$("#carplatenumber_id").text(data.carplatenumber);
			$("#cartype_id").text(data.cartype);
			$("#drivername_id").text(data.drivername);
			$("#drivermobile_id").text(data.drivermobile);
			$("#paperdispatchnumber_id").text(data.paperdispatchnumber);
			$("#formpartyname_id").text(data.topartyname);
			//运单信息
			if(data.waybillList!=null||data.waybillList.length>0){
				var txt,num=0,weight=0,volume=0,j=0;
				for(var i =0;i<data.waybillList.length;i++){ //展现返回的表格数据	
					j=i+1;
	 				num=num+data.waybillList[i].factnum/1;
	 				weight=weight+data.waybillList[i].factweight/1;
	 				volume=volume+data.waybillList[i].factvolume/1;
	 				txt='<td class="tdleft" style="padding-left:24px;">'+data.waybillList[i].waybillnumber+'</td>'+
	 					'<td class="tdleft">'+data.waybillList[i].status+'</td>'+
	 					'<td class="tdleft">'+format(data.waybillList[i].consigndate)+'</td>'+
	 					'<td class="tdleft">'+data.waybillList[i].consignorlinkman+'</td>'+
	 					'<td class="tdleft">'+data.waybillList[i].consigneecity+data.waybillList[i].consigneeregion+'</td>'+
	 					
	 					'<td class="tdleft">'+(data.waybillList[i].factnum/1).toFixed(2)+'</td>'+
	 					'<td class="tdleft">'+data.waybillList[i].packagename+'</td>'+
	 					'<td class="tdleft">'+(data.waybillList[i].factweight/1).toFixed(2)+'</td>'+
	 					'<td class="tdleft">'+(data.waybillList[i].factvolume/1).toFixed(2)+'</td>'+
	 					'<td class="tdleft">'+data.waybillList[i].memo+'</td>';	 				
	 				$('#tr'+j).empty().append(txt);
	 			}
				var total ='<td class="tdleft"></td>'+
					'<td class="tdleft"></td>'+
					'<td class="tdleft"></td>'+
					'<td class="tdleft">'+'合计'+'</td>'+
					'<td class="tdleft"></td>'+
					'<td class="tdleft">'+num.toFixed(2)+'</td>'+
					'<td class="tdleft">'+'</td>'+
					'<td class="tdleft">'+weight.toFixed(2)+'</td>'+
					'<td class="tdleft">'+volume.toFixed(2)+'</td>'+
					'<td class="tdleft">'+'</td>';
				$('#tr'+0).empty().append(total);
			}
			
			//调度单操作日志
			if(data.waybillStowageLogList!=null||data.waybillStowageLogList.length>0){
				var txt='';
				for(var i=0;i<data.waybillStowageLogList.length;i++){
					txt=txt+'<tr id="log'+i+'" onmouseover="f_over('+i+')" onmouseout="f_out('+i+')">'+
								'<td class="log_c" style="padding-left:20px;">'+data.waybillStowageLogList[i].systemdispatchnumber+'</td>'+
								'<td class="log_c"">'+data.waybillStowageLogList[i].inputman+'</td>'+
								'<td class="log_c">'+data.waybillStowageLogList[i].inputdate+'</td>'+
								'<td class="log_c">'+data.waybillStowageLogList[i].status+'</td>'+
								'<td class="log_c">'+data.waybillStowageLogList[i].trace+'</td>'+
							'</tr>';				
				}
				$("#waybillstowagelog_grid").empty().append(txt);
			}
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
function format(date){
	var date = date.toString();
	var index = date.indexOf(" ");
	//alert(date+index);
	return date.substr(0,index);
}

function f_print() {//打印预览
	var url = '../dispatchtrackcs/waybillstowage_print?waybillstowageid='+waybillstowageid;
	window.open(url,"_blank");
}