var waybillstowageid=document.URL.split("?")[1].split("=")[1];
$(function(){
	f_init_json();
	f_goods_json();
	f_username_json();
})


function f_init_json(){
	$.ajax({
		url:"../dispatchtrackcs/waybillstowage_detail_json",
		type:'post',
		dataType:'json',
		data:{"waybillstowageid":waybillstowageid},
		success:function(data){
			//调度单信息
			$("#systemdispatchnumber_id").text(data.systemdispatchnumber);//调度单号
			$("#dispatchdate_id").text((data.dispatchdate).substr(0,16));//调度日期
			$("#topartyname_id").text(data.topartyname);//供车方
			$("#drivername_id").text(data.drivername);	//司机姓名
			$("#drivermobile_id").text(data.drivermobile);//联系电话
			$("#carplatenumber_id").text(data.carplatenumber);//车牌号
			$("#cartype_id").text(data.cartype);//车辆类型
			//$("#_id").text('');//备注
			$("#paperdispatchnumber_id").text(data.paperdispatchnumber);//纸质出车单号
		}
	});
}


function f_goods_json(){
	$.ajax({
		url:"../dispatchtrackcs/waybillstowage_print_json",
		type:'post',
		dataType:'json',
		data:{"waybillstowageid":waybillstowageid},
		success:function(data){
			var table3Html = '';
			table3Html = '<tr>'+
    						'<td style="width:280px;"><font size="3">托运客户</font></td>'+
    						'<td style="width:280px;"><font size="3">提货地址</font></td>'+
    						'<td style="width:200px;"><font size="3">到货地址</font></td>'+
    						'<td style="width:215px;"><font size="3">货物名称</font></td>'+
    						'<td style="width:125px;"><font size="3">规格型号</font></td>'+
    						'<td style="width:150px;"><font size="3">数量</font></td>'+
    						'<td style="width:100px;"><font size="3">包装</font></td>'+
    						'<td style="width:150px;"><font size="3">客户单号</font></td>'+
    					'</tr>';
			if(data != null && data.length>0) {
				for(var i = 0;i<data.length;i++) {
					table3Html += '<tr>'+
	        							'<td style="width:280px;">'+data[i].frompartyname+'</td>'+
	        							'<td style="width:280px;">'+data[i].consignortown+'</td>'+
	        							'<td style="width:200px;">'+data[i].consigneecity+'-'+data[i].consigneeregion+'</td>'+
	        							'<td style="width:215px;">'+data[i].goodsname+'</td>'+
	        							'<td style="width:125px;">'+data[i].spec+'*'+data[i].model+'</td>'+
	        							'<td style="width:150px;">'+data[i].num+'</td>'+
	        							'<td style="width:100px;">'+data[i].packagename+'</td>'+
	        							'<td style="width:150px;">'+data[i].clientnumber+'</td>'+
	        					  '</tr>';
				}
			}
			$("#table3Hmtl_id").html(table3Html);
		}
	});
}

function f_username_json(){
	$.ajax({
		url:"../dispatchtrackcs/waybillstowage_userName_json",
		type:'post',
		dataType:'json',
		data:{"waybillstowageid":waybillstowageid},
		success:function(data){
			$("#username_id").text(data.username);//联系电话
		}
	});
}