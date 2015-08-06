/**
 * hcm
 * 获取当前waybillid
 */
function getWaybillidWhenUpdate(){
	var url_Data= document.URL;
	var num=url_Data.indexOf("=");
	url_Data=url_Data.substr(num+1);
	var str = url_Data.split("&");
	$("#waybillid").val((str[1]).split("=")[1]);
	$("#settlebillid").val((str[2]).split("=")[1]);
}
function getReturnUrl(){
	var url_Data= document.URL;
	var num=url_Data.indexOf("returnUrl");
	url_Data=url_Data.substr(num+9);
	return url_Data;
}
function f_hgrid_ini_goods() {//初始化表格
	var pagerow=99;  //参数为每页行数
	//f_hgrid_create(pagerow); 	
	//注意：此处的列宽和为820px
	var returnUrl = getReturnUrl();
	var order = getid();
	if(order == 7){
		$("#a_order_id").text("应收结算管理");
		$("#a_order_id").attr('href','../settlebillcs/inList?order=7');
		$("#a_order_id2").attr('href','../settlebillcs/inUpdate?'+returnUrl);
		$("#yfTr").hide();
	} else
	if(order == 8){
		$("#a_order_id").text("应付结算管理");
		$("#a_order_id").attr('href','../settlebillcs/outList?order=8');
		$("#a_order_id2").attr('href','../settlebillcs/outUpdate?'+returnUrl);
		$("#ysTr").hide();
	}
	
	var txt='<tr><td height="36px;"style="text-align: left;" colspan="9"><b class="ft-14">货物信息</b></td></tr>'+	
		'<tr>' +
		'<th scope="col"   width="146px">货物名称</th>' +
		'<th scope="col"   width="100px" >货物类型</th>' +
		'<th scope="col"   width="80px" >规格</th>' +
		'<th scope="col"   width="80px" >型号</th>' +
		'<th scope="col"   width="60px">数量</th>' +
		
		'<th scope="col"   width="100px" >包装</th>' +
		'<th scope="col"   width="100px" class="tdleft">重量(kg)</th>' +
		'<th scope="col"   width="100px" class="tdleft">体积(m³)</th>' +
		'<th scope="col"   width="100px" class="tdleft">声明价值</th>' +
		'<th scope="col"   width="130px" class="tdleft">计费方式</th>' +
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
function fillDetailWhenUpdate(){
	var params="waybillid="+$("#waybillid").val();
	$.ajax({
		url: "../waybillamountcs/selectWaybillById",
	 	type:'post',	
	 	dataType:'json', 
	 	data:params, //参数                   
	   	success:function(data){
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
 				txt='<td class="tdleft"><input type="hidden" class="goodsid" value="'+data.goodslist[i].goodsid+'"/>'+data.goodslist[i].goodsname+'</td>'+
 					'<td class="tdleft">'+data.goodslist[i].goodstype+'</td>'+
 					'<td class="tdleft">'+data.goodslist[i].spec+'</td>'+
 					'<td class="tdleft">'+data.goodslist[i].model+'</td>'+
 					'<td class="tdleft"><input type="text" onchange="hj()" onkeyup="jw3(this)" class="com_input factnum" style="width: 60px;" value="'+data.goodslist[i].factnum+'"/></td>'+
 					
 					'<td class="tdleft">'+data.goodslist[i].packagename+'</td>'+
 					'<td class="tdleft"><input type="text" onchange="hj()" onkeyup="jw3(this)" class="com_input factweight" style="width: 60px;" value="'+data.goodslist[i].factweight+'"/></td>'+
 					'<td class="tdleft"><input type="text" onchange="hj()" onkeyup="jw3(this)" class="com_input factvolume" style="width: 60px;" value="'+data.goodslist[i].factvolume+'"/></td>'+
 					'<td class="tdleft">'+data.goodslist[i].claimvalue+'</td>'+
 					'<td class="tdleft">'+data.goodslist[i].measuretype+'</td>';
 				$('#tr'+i).empty().append(txt);
 			}
 			num=dropZero(num);
 			weight=dropZero(weight.toFixed(3));
 			volume=dropZero(volume.toFixed(3));
 			value=dropZero(value.toFixed(2));
 			var total = '<td class="tdleft">'+'合计'+'</td>'+
						'<td class="tdleft">'+'</td>'+
						'<td class="tdleft">'+'</td>'+
						'<td class="tdleft">'+'</td>'+
						'<td class="tdleft" id="hjNum">'+num+'</td>'+
						'<td class="tdleft">'+'</td>'+
						'<td class="tdleft" id="hjWeight">'+weight+'</td>'+
						'<td class="tdleft" id="hjVolume">'+volume+'</td>'+
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
/**
 * hcm
 * 保存
 * @return
 */
function saveWaybillamountWhenSettleBill(){
	var xyNum="";
	$("input[type='text']").each(function(i,obj){
		if(isNaN(obj.value)&&obj.value!=null&&obj.value!=""){
			xyNum="N";
		};
	});
	if(xyNum=="N"){
		ymPrompt.alert('输入有误,请输入数字！');
		return;
	}
	var inorout =1;
	var order = getid();
	if(order == 7){
		inorout =1;
	} else if(order == 8){
		inorout =0;
	}
	var url="../waybillamountcs/saveWaybillamountWhenSettleBill";
	var params="settlebillid="+$("#settlebillid").val()+"&waybillid="+$("#waybillid").val()+"&partyid="+$("#partyid").val()+"&frompartyid="+$("#frompartyid").val()+"&topartyid="+$("#topartyid").val()+
			   "&ysyf="+$.trim($("#ysyf").val())+"&ysbxf="+$.trim($("#ysbxf").val())+
			   "&ysthf="+$.trim($("#ysthf").val())+"&ysshf="+$.trim($("#ysshf").val())+
			   "&ysbzf="+$.trim($("#ysbzf").val())+"&ysryfjf="+$.trim($("#ysryfjf").val())+
			   "&ysdxtzf="+$.trim($("#ysdxtzf").val())+"&ysqt="+$.trim($("#ysqt").val())+
			   
			   "&yfyf="+$.trim($("#yfyf").val())+"&yfbxf="+$.trim($("#yfbxf").val())+
			   "&yfthf="+$.trim($("#yfthf").val())+"&yfshf="+$.trim($("#yfshf").val())+
			   "&yfbzf="+$.trim($("#yfbzf").val())+"&yfryfjf="+$.trim($("#yfryfjf").val())+
			   "&yfdxtzf="+$.trim($("#yfdxtzf").val())+"&yfqt="+$.trim($("#yfqt").val())+
			   "&inorout="+inorout+"&random="+Math.random();
	var goodsid = "";
	var factnum = "";
	var factweight = "";
	var factvolume = "";
	$(".goodsid").each(function(i,obj){
		var numTemp = $(".factnum")[i].value==''?0:$(".factnum")[i].value;
		var weghtTemp = $(".factweight")[i].value==''?0:$(".factweight")[i].value;
		var volumeTemp = $(".factvolume")[i].value==''?0:$(".factvolume")[i].value;
		goodsid = goodsid+obj.value+"#";
		factnum = factnum+numTemp+"#";
		factweight = factweight+weghtTemp+"#";
		factvolume = factvolume+volumeTemp+"#";
	})
	params = params+ "&goodsid="+goodsid+"&factnum="+factnum+"&factweight="+factweight+"&factvolume="+factvolume;
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
function successFun(){
	var returnUrl = getReturnUrl();
	var order = getid();
	if(order == 7){
		window.location.href='../settlebillcs/inUpdate?'+returnUrl;
	} else
	if(order == 8){
		window.location.href='../settlebillcs/outUpdate?'+returnUrl;
	}
}
function backBtn(){
	var returnUrl = getReturnUrl();
	var order = getid();
	if(order == 7){
		window.location.href='../settlebillcs/inUpdate?'+returnUrl;
	} else
	if(order == 8){
		window.location.href='../settlebillcs/outUpdate?'+returnUrl;
	}
}
function hj(){
	var factnum = 0;
	var factweight = 0;
	var factvolume = 0;
	var bz = "";
	$(".factnum").each(function(i,obj){
		var num1 = obj.value;
		if(isNaN(num1)){
			bz = "false";
		}else{
			factnum = factnum+num1/1;
		}
		var num2 = $(".factweight")[i].value;
		if(isNaN(num2)){
			bz = "false";
		}else{
			factweight = factweight+num2/1;
		}
		var num3 = $(".factvolume")[i].value;
		if(isNaN(num3)){
			bz = "false";
		}else{
			factvolume = factvolume+num3/1;
		}
	});
	if(bz == "false"){
		ymPrompt.alert('输入有误,请输入数字！');
		return;
	}
	$("#hjNum").html(factnum);
	$("#hjWeight").html(factweight);
	$("#hjVolume").html(factvolume);	
}
function jw3(obj){
	var num = obj.value;
	var str = num.split(".");
	if(str.length>1){
		if(str[1]!=null&&str[1].length>3){
			var newNum = str[0]+'.'+str[1][0]+str[1][1]+str[1][2];
			$(obj).val(newNum);
			hj();
		}
	}
}