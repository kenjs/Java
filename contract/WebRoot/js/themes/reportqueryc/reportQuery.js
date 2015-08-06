var isCondition=false;//标记是否使用查询条件
function f_hgrid_ini() {//初始化表格
	var pagerow=10;  //参数为每页行数
	f_hgrid_create(pagerow); 	
	var txt='<tr>' +
			'<th scope="col" width="110px" class="tdleft" style="padding-right:10px;">&nbsp;运单编号</th>' +
			'<th scope="col" width="80px" class="tdleft" style="padding-right:10px;">运单状态</th>' +
			'<th scope="col" width="80px" class="tdleft" style="padding-right:10px;">紧急程度</th>' +
			'<th scope="col" width="80px" class="tdleft" style="padding-right:10px;">托运日期</th>' +
			'<th scope="col" width="140px" class="tdleft" style="padding-right:10px;">发货方</th>' +
			'<th scope="col" width="150px" class="tdleft" style="padding-right:10px;">发货地</th>' +
			'<th scope="col" width="150px" class="tdleft" style="padding-right:10px;">收货地</th>' +
			'<th scope="col" width="140px" class="tdleft" style="padding-right:10px;">分包商</th>' +
			'<th scope="col" width="100px" class="tdleft" style="padding-right:10px;">数量</th>' +
			'<th scope="col" width="100px" class="tdleft" style="padding-right:10px;">重量</th>' +
			'<th scope="col" width="100px" class="tdleft" style="padding-right:10px;">体积</th>' +
			'<th scope="col" width="100px" class="tdleft" style="padding-right:10px;">应收费用</th>' +
			'<th scope="col" width="100px" class="tdleft" style="padding-right:10px;">应付费用</th>' +
			'<th scope="col" width="100px" class="tdleft" style="padding-right:10px;">毛利</th>' +
			'<th scope="col" width="100px" class="tdleft" style="padding-right:10px;">毛利率</th>' +
		'</tr>'+
		'<tr id="statisticRow" style="background-color:#f4fcfc""></tr>';
	for(var i =1;i<=pagerow;i++){ //生成不见的空行
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
function f_hgrid_json(param){
	if(isCondition){
		param+=getConditions();
	}
	param=f_hgrid_getparam(param); //得到全部参数
	var pagerow=$("#pagerow").val(); //每页行数
	$.ajax({
		url:"../reportquerycs/selectWaybillListForReport",
		type:"post",
		dataType:"json",
		data:param,
		success:function(data){
			$('#loading').hide();
	 		data=f_hgrid_setmsg(data); //设置总记录数,页信息等
	 		//将统计信息插入到table的第一行
	 		if(data.length>0){
				 var txt_statistic=	'<td colspan="8" class="tdleft"><span style="font-weight:bold;color:red">&nbsp;合计</span></td>'+
				 					'<td class="tdleft"><span style="color:red;padding-right:10px;">'+data[0].totalnum+'</sapn></td>'+
				 					'<td class="tdleft"><span style="color:red;padding-right:10px;">'+data[0].totalweight+'</sapn></td>'+
				 					'<td class="tdleft"><span style="color:red;padding-right:10px;">'+data[0].totalvolume+'</sapn></td>'+
				 					'<td class="tdleft"><span style="color:red;padding-right:10px;">'+data[0].totalinamount+'</sapn></td>'+
				 					'<td class="tdleft"><span style="color:red;padding-right:10px;">'+data[0].totaloutamount+'</sapn></td>'+
				 					'<td class="tdleft"><span style="color:red;padding-right:10px;">'+data[0].totalgrossprofit+'</sapn></td>'+
				 					'<td class="tdleft"><span style="color:red;padding-right:10px;">'+(parseFloat(data[0].totalgrossmargin)*100).toFixed(2)+'%</sapn></td>';
				 $("#statisticRow").empty().append(txt_statistic);
			}else{
				$("#statisticRow").empty();
				//重新定位“没有记录”提示框的位置
				$(".myjl").remove();
				var top=$("#hgrid_div").position().top+$("#hgrid_div").outerHeight()/2;
				var left=$("#hgrid_div").position().left+$("#hgrid_div").outerWidth()/2-20;
				var tips='<div class="myjl" style="font-size:18px;text-align:center;position:absolute;top:'+top+'px;left:'+left+'px;font-family:微软雅黑;">没有记录</div>';
				$("#hgrid_div").append(tips);
			}
			var txt;
			for(var i=1;i<data.length;i++){ //最后一项为统计信息
				var grossmargin=parseFloat(data[i].grossmargin);
				var mymargin="";
				if(grossmargin<0.03){
					mymargin='<span style="color:red;padding-right:10px;">'+(grossmargin*100).toFixed(2)+'%</span>';
				}else{
					mymargin=(grossmargin*100).toFixed(2)+'%';
				}
				txt='<td class="tdleft" style="padding-right:10px;">&nbsp;'+
						'<a href="../waybillcs/waybill_detail?order=2&waybillid='+data[i].waybillid+'" style="color:#1560ea" target="_blank">'+data[i].waybillnumber+'</a>'+
					'</td>'+
					'<td class="tdleft" style="padding-right:10px;">'+data[i].status+'</td>'+					
					'<td class="tdleft" style="padding-right:10px;">'+data[i].urgencydegree+'</td>'+
				 	'<td class="tdleft" style="padding-right:10px;">'+data[i].consigndate+'</td>'+
				 	'<td class="tdleft" style="padding-right:10px;">'+data[i].consignor+'</td>'+			
					'<td class="tdleft" style="padding-right:10px;">'+data[i].consignorprovince+data[i].consignorcity+data[i].consignorregion+'</td>'+
					'<td class="tdleft" style="padding-right:10px;">'+data[i].consigneeprovince+data[i].consigneecity+data[i].consigneeregion+'</td>'+
					'<td class="tdleft" style="padding-right:10px;">'+data[i].consignee+'</td>'+
					'<td class="tdleft" style="padding-right:10px;">'+data[i].factnum+'</td>'+
					'<td class="tdleft" style="padding-right:10px;">'+data[i].factweight+'</td>'+
					'<td class="tdleft" style="padding-right:10px;">'+data[i].factvolume+'</td>'+
					'<td class="tdleft" style="padding-right:10px;">'+data[i].needinamount+'</td>'+
					'<td class="tdleft" style="padding-right:10px;">'+data[i].needoutamount+'</td>'+
					'<td class="tdleft" style="padding-right:10px;">'+data[i].grossprofit+'</td>'+
					'<td class="tdleft" style="padding-right:10px;">'+mymargin+'</td>';
			 	$('#tr'+i).empty().append(txt);   
		     }
			 var pagerow=$("#pagerow").val(); //每页行数
			 for(var i =data.length;i<=pagerow;i++){
				 $('#tr'+i).empty();
			 }
	},
	error:function(){
		ymPrompt.alert("加载错误!");
	}
	})
}
/**
 * @author wei.huang
 * @date 2013-11-29
 * @function 获取查询条件
 * @return
 */
function getConditions(){
	var status="";
	if($("#status").val()!="全部"){
		status=$("#status").val();
	}
	var consignorprovince="";
	var consignorcity="";
	var consignorregion="";
	if($("#consignoraddress").val()!="请选择/输入城市名称"&&$.trim($("#consignoraddress").val())!=""){
		var address=$.trim($("#consignoraddress").val()).split("-");
		if(address.length>0){
			consignorprovince=address[0];
		}
		if(address.length>1){
			consignorcity=address[1];
		}
		if(address.length>2){
			consignorregion=address[2];
		}
	}
	var consigneeprovince="";
	var consigneecity="";
	var consigneeregion="";
	if($.trim($("#consigneeaddress").val())!=""){
		var address=$.trim($("#consigneeaddress").val()).split("-");
		if(address.length>0){
			consigneeprovince=address[0];
		}
		if(address.length>1){
			consigneecity=address[1];
		}
		if(address.length>2){
			consigneeregion=address[2];
		}
	}
	var startdate=$.trim($("#startdate").val());
	var enddate=$.trim($("#enddate").val());
	//日期校验
	var objRe =/^\d{4}-(0?[1-9]|1[012])-(0?[1-9]|[12]\d|3[01])$/;
	if(objRe.test(startdate)){
		startdate+=" 00:00:00";
	}
	if(objRe.test(enddate)){
		enddate+=" 23:59:59";
	}
	var params="waybillnumber="+$.trim($("#waybillnumber").val())+"&status="+status+"&consignor="+$.trim($("#consignor").val())+
		"&consignee="+$.trim($("#consignee").val())+"&grossmargin="+$("#grossmargin").val()+"&consignorprovince="+consignorprovince+
		"&consignorcity="+consignorcity+"&consignorregion="+consignorregion+"&consigneeprovince="+consigneeprovince+
		"&consigneecity="+consigneecity+"&consigneeregion="+consigneeregion+"&startdate="+startdate+"&enddate="+enddate;
	return params;
}
function search(){
	isCondition=true;
	$("#pagecode").val("1");
	f_hgrid_json("");
}

function exportExcel(obj){
	var href="../reportquerycs/exportExcel?"+getConditions();
	$(obj).attr("href",href);
}