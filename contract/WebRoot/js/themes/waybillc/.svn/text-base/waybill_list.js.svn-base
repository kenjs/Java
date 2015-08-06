/*****列表页表头******/
function f_hgrid_ini() {//初始化表格
	var pagerow=10;  //参数为每页行数
	f_hgrid_create(pagerow); 	
	//注意：此处的列宽和为820px
	var txt='<tr>' +
		'<th scope="col" style="text-align: left;height:30px;padding-left:3px;"  width="100px" class="tdleft">&nbsp;运单编号</th>' +
		'<th scope="col" style="text-align: left;height:30px;padding-left:10px;"  width="80px" class="tdleft">状态</th>' +
		'<th scope="col" style="text-align: left;height:30px;padding-left:10px;" width="80px" class="tdleft">托运日期</th>' +
		'<th scope="col" style="text-align: left;height:30px;padding-left:10px;" width="260px" class="tdleft">发货方</th>' +
		'<th scope="col" style="text-align: left;height:30px;padding-left:10px;" width="180px" class="tdleft">收货地</th>' +
		'<th scope="col" style="text-align: left;height:30px;padding-left:10px;" width="200px" class="tdleft">分包商</th>' +
		'<th scope="col" style="text-align: left;height:30px;padding-left:10px;" width="260px" class="tdleft">操作</th>' +
		'</tr>';
	for(var i =0;i<pagerow;i++){ //生成不见的空行
		txt=txt+'<tr id="tr'+i+'" class="ctr'+i+'" onmouseover="f_onmouseover('+i+')" onmouseout="f_onmouseout('+i+')">';
	 	txt=txt+'</tr>';
	 }
	$("#hgrid").empty().append(txt);
}
function f_onmouseover(i){
	$('.ctr'+i).css("background-color","#fffddd");
}
function f_onmouseout(i){
	$('.ctr'+i).css("background-color","white");
}
/****新增****/
function f_insertclick(){
	window.location.href="../waybillcs/waybillAdd?order=1";
}

function f_hgrid_json(param){//刷新表格数据
	param = f_hgrid_getparam(param);//得到全部参数
	$.ajax({
		url:"../waybilldistributioncs/waybillList",
		type:"post",
		dataType:"json",
		data:param,
		success:function(data){
		$('#loading').hide(); 		
 		data=f_hgrid_setmsg(data); //设置总记录数,页信息等
 		var txt;
 		var opera;
		for(var i =0;i<data.length;i++){ //展现返回的表格数据
			txt='<td class="tdleft"><a href="#" onclick=btnDetail("'+data[i].waybillid+'") class="fn-cBlue">'+data[i].waybillnumber+'</a></td>'+
				'<td class="tdleft">'+data[i].status+'</td>'+
				'<td class="tdleft">'+format(data[i].consigndate)+'</td>'+
				'<td class="tdleft">'+data[i].frompartyname+'</td>'+
				'<td class="tdleft">'+data[i].consigneecity+data[i].consigneeregion+'</td>'+
				'<td class="tdleft">'+data[i].topartyname+'</td>';
			if(data[i].status == '待分派'){
				opera = '<td class="tdleft"><a href="javascript:void(0);" onclick=btnDetail("'+data[i].waybillid+'") style="margin-left:5px;">详情</a>'+
				' <a href="javascript:void(0);" onclick=btnUpdate("'+data[i].waybillid+'")>修改</a>'+
				' <a href="javascript:void(0);" onclick=btnDelete("'+data[i].waybillid+'")>作废</a>'+
				' <a href="javascript:void(0);" onclick=btnPrint("'+data[i].waybillid+'")>打印运单</a>'+
				'</td>';
			} else {
				opera = '<td class="tdleft"><a href="javascript:void(0);" onclick=btnDetail("'+data[i].waybillid+'") style="margin-left:5px;">详情</a>'+				
				' <a href="javascript:void(0);" onclick=btnPrint("'+data[i].waybillid+'")>打印运单</a>'+
				'</td>';
			}
			txt = txt+opera;
			$('#tr'+i).empty().append(txt);
		}
		var pagerow=$("#pagerow").val(); //每页行数
		 for(var i =data.length;i<pagerow;i++){
			 $('#tr'+i).empty();
		 }
	}
	});
	
}
function queryWaybilList(){
	$("#pagecode").val("1");
	var waybillnumber=$("#waybillnumber").val();
	var frompartyname=$("#frompartyname").val();
	var topartyname=$("#topartyname").val();
	var fromdate=$("#fromdate").val();
	var todate=$("#todate").val();
	var status = $('.status div span').text();
	if(status=="全部"){
			status="";
		}
	var params = "waybillnumber="+waybillnumber+"&frompartyname="+frompartyname+"&topartyname="+topartyname+
				"&fromdate="+fromdate+"&todate="+todate+"&status="+status+"&fromQuery="+"waybillList"+"&random="+Math.random();
	f_hgrid_json(params);
}
function btnDetail(id){
	var url = "../waybillcs/waybill_detail?order=1&waybillid="+id;
	window.open(url, "_blank");
}
function btnUpdate(id){
	window.location.href="../waybillcs/waybillEdit?waybillid="+id;
}
function format(date){
	var date = date.toString();
	var index = date.indexOf(" ");
	return date.substr(0,index);
}
function btnDelete(id){
	ymPrompt.confirmInfo({message:'确定作废吗?',autoClose: true,height:200,width:300,fixPosition:true,dragOut:false,handler: 
		function(status){
		if(status=='ok'){
			$.ajax({
				url: "../waybillcs/delete", 
				type:"post",
				dataType:"json",
				data:{"waybillid":id},
				success:function(data){
					if(data.msg=="ok"){
						f_hgrid_json("");
						ymPrompt.alert("作废成功！");
					}else{
						ymPrompt.alert("作废失败！");
					}
				},
				error:function(){
					ymPrompt.alert("作废失败！");
				}
		});
		}else{
			return false;
		}
		}
		})
}