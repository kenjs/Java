/**
 * @author wei.haung
 * @date 2013-11-22
 * @function 获取URL中的参数
 * @return data(数组：第一个值：settlebillid，第二个是settlebillnumber，第三个是status，第四个是结算期，第五个是收/发货方，第六个是inorout：1=发货方，0=分包商，第七个conorganization发/收货方)
 */
function getUrlData(){
	var url=document.URL;
	var data=decodeURI(url.substring(url.indexOf("=")+1)).split(",");
	return data;
}
var myData=getUrlData();
function f_onmouseover(i){
	$('.ctr'+i).css("background-color","#fffddd");
}
function f_onmouseout(i){
	$('.ctr'+i).css("background-color","white");
}
/**
 * @author wei.haung
 * @date 2013-11-22
 * @function 填写结算单部分的信息
 */
function settleBillDetail_Ini(){
	$("#settlebillnumber").html(myData[1]);
	$("#status").html(myData[2]);
	$("#settledate").html(myData[3]);
	$("#organization").html(myData[4]);//分包商
	if(myData.length==7){
		$("#conorganization").html(myData[6]);//发货方
	}
	
}
/**
 * @author wei.huang
 * @date 2013-11-14
 * @function 生成核销发票明细表的表头
 */
function verificationBillDetail_Ini(){
	//注意：此处的列宽和为1000px
	var txt='<tr>' +
		'<th scope="col" width="150px" class="tdleft">&nbsp;开票日期</th>' +
		'<th scope="col" width="150px" class="tdleft">开票人</th>' +
		'<th scope="col" width="200px" class="tdleft">发票代码</th>' +
		'<th scope="col" width="200px" class="tdleft">发票号</th>' +
		'<th scope="col" width="300px" class="tdleft">发票金额</th>' +
		'</tr>';
	$("#verificationbilldetail").empty().append(txt);
	
}
/**
 * @author wei.huang
 * @date 2013-11-14
 * @function 刷新核销发票明细表的数据
 */
function verificationBillDetail_Json(){
	$.ajax({
	 	url: "../settlebillverificationcs/selectList",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:{"settlebillid":myData[0]}, //参数     	               
	   	success:function(data){//回传函数
			var txt;
			for(var i =0;i<data.length;i++){ //展现返回的表格数据
				txt='<tr>'+
						'<td class="tdleft">&nbsp;'+data[i].billdate+'</td>'+
						'<td class="tdleft">'+data[i].billman+'</td>'+
						'<td class="tdleft">'+data[i].billcode+'</td>'+	
						'<td class="tdleft">'+data[i].billnumber+'</td>'+
					 	'<td class="tdleft">'+data[i].billamount+'</td>'+
				 	'</tr>';
			 	$("#verificationbilldetail").append(txt);			      
		     }
	      }	
	  }); 
}
/**
 * @author wei.huang
 * @date 2013-11-14
 * @function 生成拖运单明细表的表头
 */
function wayBillDetail_Ini(){
	var amount="";
	if(myData[5]=="0"){
		amount="应付费用(元)";
	}else if(myData[5]=="1"){
		amount="应收费用(元)";
	}
	//注意：此处的列宽和为1000px
	var txt='<tr>' +
				'<th scope="col" width="90px" class="tdleft">&nbsp;运单编号</th>' +
				'<th scope="col" width="70px" class="tdleft">托运日期</th>' +
				'<th scope="col" width="100px" class="tdleft">发货地</th>' +
				'<th scope="col" width="100px" class="tdleft">目的地</th>' +
				'<th scope="col" width="100px" class="tdleft">收货方</th>' +
				'<th scope="col" width="70px" class="tdleft">货物名称</th>' +
				'<th scope="col" width="50px" class="tdleft">数量</th>' +
				'<th scope="col" width="50px" class="tdleft">包装</th>' +
				'<th scope="col" width="50px" class="tdleft">重量</th>' +
				'<th scope="col" width="50px" class="tdleft">体积</th>' +
				'<th scope="col" width="64px" class="tdleft">结算方式</th>' +
				'<th scope="col" width="64px" class="tdleft">到货方式</th>' +
				'<th scope="col" width="64px" class="tdleft">回单方式</th>' +
				'<th scope="col" width="90px" class="tdleft">'+amount+'</th>' +
			'</tr>'+
			'<tr>'+
				'<td colspan="14"><span id="totalAmount" style="float:right;margin-right:20px;font-weight:bolder;color:red;display:none"></sapn></td>'
			'</tr>';
	$("#waybilldetail").empty().append(txt);
}
/**
 * @author wei.huang
 * @date 2013-11-14
 * @function 刷新运单明细表的数据
 */
function wayBillDetail_Json(){
	var param="";
	if(myData[5]=="0"){
		param="outsettlebillid="+myData[0];
	}else if(myData[5]=="1"){
		param="insettlebillid="+myData[0];
	}
	param+="&inorout="+myData[5];
	$.ajax({
	 	url: "../settlebillcs/selectMixedWaybillBySettleBillId",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:param, //参数     	               
	   	success:function(data){//回传函数
			var txt="";
			var totalamount=0.00;
			var count=data.length;
			for(var i =0;i<count;i++){ //展现返回的表格数据
				totalamount+=parseFloat(data[i].needamount);
				txt='<tr>'+
						'<td class="tdleft">&nbsp;<a href="../waybillcs/waybill_detail?order=2&waybillid='+data[i].waybillid+'" target="_blank">'+data[i].waybillnumber+'</a></td>'+
						'<td class="tdleft">'+data[i].consigndate+'</td>'+					
						'<td class="tdleft">'+data[i].consignorprovince+data[i].consignorcity+data[i].consignorregion+'</td>'+
					 	'<td class="tdleft">'+data[i].consigneeprovince+data[i].consigneecity+data[i].consigneeregion+'</td>'+
					 	'<td class="tdleft">'+data[i].consignee+'</td>'+					
						'<td class="tdleft">'+data[i].goodsname+'</td>'+
					 	'<td class="tdleft">'+data[i].factnum+'</td>'+
					 	'<td class="tdleft">'+data[i].packagename+'</td>'+					
						'<td class="tdleft">'+data[i].factweight+'</td>'+
					 	'<td class="tdleft">'+data[i].factvolume+'</td>'+
					 	'<td class="tdleft">'+data[i].settletype+'</td>'+
					 	'<td class="tdleft">'+data[i].arrivetype+'</td>'+					
						'<td class="tdleft">'+data[i].backbilltype+'</td>'+
					 	'<td class="tdleft">'+data[i].needamount+'</td>'+
				 	'</tr>';
			 	$("#waybilldetail").append(txt);		      
		     }
			if(count>0){
				$("#totalAmount").html("合计费用："+totalamount.toFixed(2));
				$("#totalAmount").show();
			}else{
				$("#totalAmount").parent("td").hide();
			}
	      }	
	  }); 
}
/**
 * @author wei.huang
 * @date 2013-11-15
 * @function 返回上一级
 */
function back(){
	window.close();
}

//应收结算导出 add by yao.xia 2013-12-18
function exportExcel(){
	var href="../settlebillcs/exportExcel?settlebillid="+myData[0]+"&inorout="+myData[5]+
		"&settledate="+myData[3]+"&organization="+myData[4];
	window.open(href);
}