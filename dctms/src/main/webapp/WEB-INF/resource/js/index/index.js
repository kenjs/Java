/**
 * 当前车源搜索
 * @return
 */
function realSubmit() {
	document.getElementById('realMainForm').submit();
}

/**
 * 预约车源搜索
 * @return
 */
function returnSubmit() {
	document.getElementById('returnMainForm').submit();
}

//当前车源
function getRealCar(province,city) {
	if(province == null || province == '') {
		province = getCookie("province");
		if(province == null || province == '') {
			$.ajax({
				url: jcontextPath + "/ipUserActio",   
				type:'post',	
				dataType:'json',
				ansync:false,
				//data:{code:code}, //参数     	               
				success:function(data){//回传函数
					if(data.result == 1) {
						var dataObj=data.content;
						getdriver(dataObj.code,dataObj.name)
					}
				}
			});
			return;
		}else {
			if(city == null || city == '') {
				city = getCookie("city");
			}
		}
	}
	getdriver(province,city);
}
function getdriver(province,city) {
	$.ajax({
		url: jcontextPath + "/realDriverCar",   
		type:'post',	
		dataType:'json', 
		ansync:false,
		data:{province:province,city:city,curPage:0,pageSize:15}, //参数     	               
		success:function(data){//回传函数
			var dataObj=data.content;
			$("#startPcc").val(dataObj.startPcc);
			$("#lastLocation").val(dataObj.lastLocation);
			var openHtml = '<table border="0" cellpadding="0" cellspacing="0">';
				openHtml += '<thead ><tr>'+
				 				'<td style="width:150px;">车牌号</td>'+
				 				'<td >车型</td>'+
				 				'<td>运营路线</td>'+
				 				'<td>当前位置</td>'+
				 				'<td width="20">&nbsp;</td>'+
							'</tr></thead>';
				for(var i = dataObj.list.length; i >0; i-- ) {
					openHtml += '<tr style="cursor:pointer;" onclick="openDriverDetailed(\''+dataObj.list[i-1].id+'\');">'+
			              			'<td><span class="icon">&nbsp;</span>'+dataObj.list[i-1].carNumber+'</td>'+
			              			'<td title="'+dataObj.list[i-1].carTypes+'">'+dataObj.list[i-1].carTypes+'</td>'+
			              			'<td title="'+dataObj.list[i-1].driverLine+'">'+dataObj.list[i-1].driverLine+'</td>'+
			              			'<td title="'+dataObj.list[i-1].lastLocation+'"><span class="icon2">&nbsp;</span>'+dataObj.list[i-1].lastLocation+'</td>'+
			              			'<td><span class="icon3">&nbsp;</span></td>'+
		              			'</tr>';
				}
				openHtml += '</table><p class="more"><a href="javascript:realSubmit();">更多&gt;&gt;</a></p>';
		   		$("#con_cur_1").html(openHtml);
		}
	});
}

//预约车源
function getReturnCar() {
	var province = getCookie("province");
	var city = getCookie("city");
	$.ajax({
		url: jcontextPath + "/returnDriverCar",   
		type:'post',	
		dataType:'json', 
		data:{province:province,city:city,curPage:0,pageSize:15}, //参数     	               
		success:function(data){//回传函数
			var dataObj=data.content;
			var openHtml = '<table border="0" cellpadding="0" cellspacing="0">';
				openHtml += '<thead ><tr>'+
								'<td width="106">车牌号</td>'+
								'<td width="158">车型</td>'+
								'<td width="216">预约路线</td>'+
								'<td width="160">预约时间</td>'+
								'<td width="10">&nbsp;</td>'+
							'</tr></thead>';
				for(var i = 0; i < dataObj.list.length; i++ ) {
		   			//显示路线
					openHtml += '<tr style="cursor:pointer;" onclick="openDriverDetailed(\''+dataObj.list[i].driverId+'\');">'+
	          						'<td><span class="icon">&nbsp;</span>'+dataObj.list[i].carNumber+'</td>'+
	          						'<td title="'+dataObj.list[i].carTypes+'">'+dataObj.list[i].carTypes+'</td>'+
	          						'<td title="'+dataObj.list[i].startBusinessLine+'——'+dataObj.list[i].endBusinessLine+'">'+dataObj.list[i].startBusinessLine+'<span class="icon1">&nbsp;</span>'+dataObj.list[i].endBusinessLine+'</td>'+
	          						'<td title="'+dataObj.list[i].startTime+'-'+dataObj.list[i].endTime+'"><span class="icon5">&nbsp;</span>'+dataObj.list[i].startTime+'-'+dataObj.list[i].endTime+'</td>'+
	          						'<td><span class="icon3">&nbsp;</span></td>'+
          						'</tr>';
				}
				if(dataObj.list.length == 0) {
					openHtml += '<tr>'+
									'<td colspan="5" align="center">抱歉！暂无运营路线。</td>'+
								'</tr>';
				}
				openHtml += ' </table><p class="more"><a href="javascript:returnSubmit();">更多&gt;&gt;</a></p>';
		   		$("#carReturnDiv").html(openHtml);
		}
	});
}

//最新货源
function getNewCargo() {
	$.ajax({
		url: jcontextPath + "/newOrderCargoStart",   
		type:'post',	
		dataType:'json', 
		data:{curPage:0,pageSize:15}, //参数     	               
		success:function(data){//回传函数
			var dataObj=data.content;
			var openHtml = '';
   				openHtml += '<thead ><tr>'+
   								'<td width="74">货物名</td>'+
   								'<td width="100">发货单位</td>'+
   								'<td width="91">装货时间</td>'+
   								'<td width="136">装货地</td>'+
   							    '<td width="135">卸货地</td>'+
   							    '<td width="84">载重或体积</td>'+
   							    '<td width="10">&nbsp;</td>'+
   							'</tr></thead>';
   				for(var i = 0; i < dataObj.list.length; i++) {
   					var cargoWeightCubage = "";
   					if(dataObj.list[i].cargoWeight == "" || dataObj.list[i].cargoWeight == null) {
   						if(dataObj.list[i].cargoCubage == "" || dataObj.list[i].cargoCubage == null) {
   						}else {
   							cargoWeightCubage = dataObj.list[i].cargoCubage+"方";
   						}
   					}else{
   						if(dataObj.list[i].cargoCubage == "" || dataObj.list[i].cargoCubage == null) {
   							cargoWeightCubage = dataObj.list[i].cargoWeight+"吨";
   						}else {
   							cargoWeightCubage = dataObj.list[i].cargoWeight+"吨/"+dataObj.list[i].cargoCubage+"方";
   						}
   					}
   					openHtml += '<tr>'+
	              					'<td>'+dataObj.list[i].cargoName+'</td>'+
	              					'<td title="'+dataObj.list[i].companyName+'">'+dataObj.list[i].companyName+'</td>'+
	              					'<td title="'+dataObj.list[i].requestStartTime+'">'+dataObj.list[i].requestStartTime+'</td>'+
	              					'<td title="'+dataObj.list[i].startProvince+'-'+dataObj.list[i].startCity+'">'+dataObj.list[i].startProvince+'-'+dataObj.list[i].startCity+'</td>'+
	              					'<td title="'+dataObj.list[i].endProvince+'-'+dataObj.list[i].endCity+'">'+dataObj.list[i].endProvince+'-'+dataObj.list[i].endCity+'</td>'+
	              					'<td title="'+cargoWeightCubage+'">'+cargoWeightCubage+'</td>'+
	              					'<td><span class="icon3">&nbsp;</span></td>'+
              					'</tr>';
   				}
		   		$("#cargoStartDiv").html(openHtml);
		}
	});
}

//主页统计（登录后加载预约订单，待确认收货，待评价订单）
function tradePromptInfo(){
	$.ajax({
		url: jcontextPath + "/trandeInfoCount",   
		type:'post',	
		dataType:'json',  	               
		success:function(data){//回传函数
			var dataObj=data.content;
        	
			var countTradeHtml = '<li class="bno">待确认订单<br /><a href="'+jcontextPath+'/querySuccessCloseTransactionInfo?transactionInfoDomain.tradeStart=1&transactionInfoDomain.menuAId="><span class="dius">'+dataObj.waitingDriverTrade+'</span></a></li>'+
									'<li>待确认收货<br /><a href="'+jcontextPath+'/querySuccessCloseTransactionInfo?transactionInfoDomain.tradeStart=3&transactionInfoDomain.menuAId="><span class="dius">'+dataObj.waitingReceivingTrade+'</span></a></li>'+
								  '<li>待评价订单<br /><a href="'+jcontextPath+'/queryTransactionInfo?transactionInfoDomain.menuAId=a_id_5&succeNoAssestrades=5&transactionInfoDomain.cargoId="><span class="dius">'+dataObj.successNoAssessTrade+'</span></a></li>';
			$("#ulId").html(countTradeHtml);
		}
	});  
}

//主页数据统计
function getCountDate() {
	$.ajax({
		url: jcontextPath + "/countDate",   
		type:'post',	
		dataType:'json', 
		//data:{province:province,city:city}, //参数     	               
		success:function(data){//回传函数
			var dataObj=data.content;
			var countDateHtml = '<dl>'+
									'<dt><i>&nbsp;</i>今日新增</dt>'+
									'<dd>车辆：'+dataObj.driverDayCount+'</dd>'+
									'<dd>货源：'+dataObj.orderCargoDayCount+'</dd>'+
									'<dd>交易：'+dataObj.transactionDayCount+'</dd>'+
								'</dl>'+
								'<dl>'+
									'<dt><i class="f98">&nbsp;</i>累计数据</dt>'+
									'<dd>车辆：'+dataObj.driverAllCount+'</dd>'+
									'<dd>货源：'+dataObj.OrderCargoAllCount+'</dd>'+
									'<dd>交易：'+dataObj.transactionAllCount+'</dd>'+
								'</dl>';
			$("#countDateHtmlId").html(countDateHtml);
		}
	});
}

/**
 * 根据司机Id打开司机详细页面
 */
function openDriverDetailed(driverId){
	if(booleanUserSession() == false) {
		return;
	}
	var url = jcontextPath + "/openDriverDetailed.action?driverId="+driverId;
	window.open(url, "_blank"); //注意第二个参数
}

function todayDynamicInfo(){
	$.ajax({
		url: jcontextPath + "/queryTodayDynamic",   
		type:'post',	
		dataType:'json', 
		//data:{province:province,city:city}, //参数     	               
		success:function(data){//回传函数
			var dataObj=data.content;
			var countDHtml ="";
			//countDHtml+='<ul id="ulIds">';
			if(data.result=='tradeList'){
				for(var i=0;i<dataObj.length;i++){
					carNumbers=dataObj[i].carNumber.substr(0,4);
					countDHtml+='<li>'+dataObj[i].companyName+'与'+carNumbers+'****达成交易</li>';
				} 
			}else{
				for(var i=0;i<dataObj.length;i++){
					var startCode=dataObj[i].code.substr(0,3);
					var endCode=dataObj[i].code.substr(dataObj[i].code.length-5,4);
					countDHtml+='<li>'+startCode+'****'+endCode+'司机注册成为快到网会员</li>';
				}
			}
			//countDHtml+='</ul>';
			$("#ulIds").html(countDHtml);
		}
	});
}



