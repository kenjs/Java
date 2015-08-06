var businessLineHTML = "";
var lineHTML = "";
/**
 * 根据司机id查询预约线路
 * @return
 */
function driverBusinessLineById() {
	var driverId = $("#driverId").val();
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/driverBusinessLineById",
	   	dataType:"json",
	   	data:{driverId:driverId}, //参数     	
		success:function(data){//回传函数
			var dataObj=data.content;
			var driverCarLineHtml = "";
			businessLineHTML = '<li class="w80">预约线路<span>:</span></li>';
	   		for (var i=0;i<dataObj.length;i++){
	   			//路线的拼接
	   			var startBusinessLine=dataObj[i].startProvince;//若省为空则市也为空
	   			var endBusinessLine=dataObj[i].endProvince;
	   			if(dataObj[i].startCity!=""&&dataObj[i].startCity!=null){
	   				startBusinessLine += '——'+dataObj[i].startCity;
	   			}
	   			if(dataObj[i].startProvince==""||dataObj[i].startProvince==null){
	   				startBusinessLine="全部";
	   			}
	   			if(dataObj[i].endCity!=""&&dataObj[i].endCity!=null){
	   					endBusinessLine += '——'+dataObj[i].endCity;
	   			}
	   			if(dataObj[i].endProvince==""||dataObj[i].endProvince==null){
	   				endBusinessLine="全部";
	   		   }
	   			//显示内容
	   			driverCarLineHtml += '<li>'+startBusinessLine+'<i class="nxot1">&nbsp;'+
	   								 '</i>'+endBusinessLine+'</li>'+
	   								 '<li>'+dataObj[i].startTime+'—'+dataObj[i].endTime+'</li>';
	   			if(i == 0) {
	   				businessLineHTML += dataObj[i].startProvince+dataObj[i].startCity+'—'+dataObj[i].endProvince+dataObj[i].endCity+'/'+dataObj[i].startTime+'—'+dataObj[i].endTime;
	   			}else {
	   				businessLineHTML += ','+dataObj[i].startProvince+dataObj[i].startCity+'—'+dataObj[i].endProvince+dataObj[i].endCity+'/'+dataObj[i].startTime+'—'+dataObj[i].endTime;
	   			}
	   		}
	   		if(dataObj.length == 0) {
	   			driverCarLineHtml +='<li>暂无预约线路信息</li>';
	   			businessLineHTML = "";
	   		}
	   		$("#driverBusinessLineById").html(driverCarLineHtml);
	   	}
	});
}

/**
 * 根据司机id查询运营线路
 * @return
 */
function driverLineById() {
	var driverId = $("#driverId").val();
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/queryDriverLineById",
	   	dataType:"json",
	   	data:{driverId:driverId}, //参数     	
		success:function(data){//回传函数
			var dataObj=data.content;
			var driverLineHtml = "";
			lineHTML = '<li class="w80">运营路线<span>:</span></li>';
	   		for (var i=0;i<dataObj.length;i++){
	   			//路线的拼接
	   			var startBusinessLine=dataObj[i].startProvince;//若省为空则市也为空
	   			var endBusinessLine=dataObj[i].endProvince;
	   			if(dataObj[i].startCity!=""&&dataObj[i].startCity!=null){
	   				startBusinessLine +='——'+dataObj[i].startCity;
	   			}
	   			if(dataObj[i].startProvince==""||dataObj[i].startProvince==null){
	   				startBusinessLine="全部";
	   			}
	   			if(dataObj[i].endCity!=""&&dataObj[i].endCity!=null){
	   					endBusinessLine +='——'+dataObj[i].endCity;
	   			}
	   			if(dataObj[i].endProvince==""||dataObj[i].endProvince==null){
	   				endBusinessLine="全部";
	   		   }
	   			//显示内容
	   			driverLineHtml += '<li>'+startBusinessLine+'<i class="nxot2">&nbsp;'+
	   							  '</i>'+endBusinessLine+'</li>';
	   			if(i == 0) {
	   				lineHTML += dataObj[i].startProvince+dataObj[i].startCity+'—'+dataObj[i].endProvince+dataObj[i].endCity;
	   			}else {
	   				lineHTML += ","+dataObj[i].startProvince+dataObj[i].startCity+'—'+dataObj[i].endProvince+dataObj[i].endCity;
	   			}
	   		}
	   		if(dataObj.length == 0) {
	   			driverLineHtml +='<li>暂无运营线路信息</li>';
	   			lineHTML = "";
	   		}
	   		$("#driverLineById").html(driverLineHtml);
	   	}
	});
}


/**
 * 推荐当前车源
 * @return
 */
function getRecommendRealDiverCar(){
	var province = getCookie("province");
	var city = getCookie("city");
	$.ajax({
		url: jcontextPath + "/recommendRealDiverCar",   
		type:'post',	
		dataType:'json', 
		data:{province:province,city:city,pageSize:3}, //参数     	               
		success:function(data){//回传函数
			var dataObj=data.content;
			var driverCarHtml = "";
			driverCarHtml += '<h3><i>&nbsp;</i>推荐车源</h3>';
	   		for (var i=0;i<dataObj.length;i++){
	   			var driverLine = dataObj[i].driverLine;
	   			var driverLineSplt = "";
	   			var driverLineHTML = "";
	   			if(driverLine != "") {
	   				driverLineSplt = driverLine.split(',');
	   				for(var j = 0;j<driverLineSplt.length;j++) {
	   					driverLineHTML += driverLineSplt[j]+'<br />'
	   				}
	   			}
	   			if(driverLineHTML == "") {
	   				driverLineHTML = '暂无运营线路<br />'
	   			}
	   			driverCarHtml += '<ul>'+
	   								'<li><span>姓名：</span>'+dataObj[i].name+'<i class="icon ml20">&nbsp;</i></li>'+
	   								'<li><span>车牌：</span>'+dataObj[i].carNumber+'</li>'+
	   								'<li><span>运营路线：</span><div class="city">'+driverLineHTML+'</div></li>'+
	   							 '</ul>';
	   		}
	   		$("#driverCarId").html(driverCarHtml);
	   	}
	});
}


/**
 * 根据司机id查询交易记录
 * @return
 */
function driverTransactionInfoById(curPage) {
	var driverId = $("#driverId").val();
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/queryDriverCarTransactionInfo",
	   	dataType:"json",
	   	data:{driverId:driverId,curPage:curPage,pageSize:10}, //参数     	
		success:function(data){//回传函数
	   		if(data.result == 1) {
	   			var url = jcontextPath + "/dcts/user/login.jsp";
				window.open(url);
	   		}else if(data.result == 2) {
	   			
	   		}else if(data.result == 0) {
	   			var dataObj=data.content;
	   			var driverTransactionInfoHtml = '<table class="tab1" border="0" cellpadding="0" cellspacing="0" >';
	   			driverTransactionInfoHtml +=  '<thead >'+
					              			 '<tr>'+
				   								'<td width="170">订单编号</td>'+
				   								'<td width="105">交易人</td>'+
				   								'<td width="112">交易日期</td>'+
				   								'<td>运输路线</td>'+
				   							  '</tr>'+
				   							  '</thead>';
	   			for(var i = 0;i<dataObj.list.length;i++) {
	   				driverTransactionInfoHtml +=  '<tr>'+
				  	              					'<td>'+dataObj.list[i].orderNumber+'</td>'+
				  	              					'<td><span class="icon">&nbsp;</span>'+dataObj.list[i].name+'</td>'+
				  	              					'<td>'+dataObj.list[i].createTime+'</td>'+
				  	              					'<td>'+dataObj.list[i].startProCityCounty+'<span class="icon1">&nbsp;</span>'+dataObj.list[i].endProCityCounty+'</td>'+
				  	              				   '</tr>';
	   			}
	   			
	   			if(dataObj.list.length == 0) {
	   				driverTransactionInfoHtml +=  '<tr><td  colspan="4" align="center">暂无交易记录</td></tr>';
	   				driverTransactionInfoHtml +=  '</table>';
	   			}else {
	   				driverTransactionInfoHtml +=  '</table>';
	   				driverTransactionInfoPage(dataObj.pageInfo.totalPages,dataObj.pageInfo.curPageNo,dataObj.pageInfo.totalRecords);
	   			}
		   		$("#driverTransactionId").html(driverTransactionInfoHtml);
	   		}
	   	}
	});
}


function driverTransactionInfoPage(totalPages,curPageNos,totalRecords) {
	var liststep = 5;//最多显示分页页数
	var totalPage = totalPages;//总页数
	var curpage = curPageNos;//当前页数
	var totalRecord = totalRecords;	//总记录数
	
   if (totalPage < curpage) {
	   curpage = totalPage;//如果分页变量大总页数，则将分页变量设计为总页数
   }
   if (curpage < 1) {
	   curpage = 1;//如果分页变量小于１,则将分页变量设为１
   }
   //计算要展示哪几页
   var listbegin;
   var listend;
  
   listbegin = curpage - ((liststep % 2 == 0) ? liststep / 2 : parseInt(liststep / 2));
   listend = curpage - 1 + ((liststep % 2 == 0) ? liststep / 2 : parseInt(liststep / 2) + 1);// 分页信息显示到第几页
   
   if((totalPage - curpage) < parseInt(liststep /2)){
	   listbegin = totalPage - liststep + 1;
	   listend = totalPage;
   }
   if(curpage <= parseInt(liststep /2)){
	   listbegin = 1;
	   listend = liststep;
   }
   if (listbegin < 1) {
	   listbegin = 1;
   }
   if (listend > totalPage) {
	   listend = totalPage;
   } 
   
   var pageHTML = "<dl>";		
   if (curpage == 1) {//上一页
	   pageHTML += "<dt><a href='javascript:driverTransactionInfoById("+curpage+");'>上一页</a></dt>";
   }
   if(curpage>1) {
	   pageHTML += "<dt><a href='javascript:driverTransactionInfoById("+(curpage-1)+");'>上一页</a></dt>";
   }
   pageHTML += "</dl><ul>";
   for (var i = listbegin; i <= listend; i++) {
	   if (i != curpage) {//如果i不等于当前页
			pageHTML += "<li><a href='javascript:driverTransactionInfoById("+i+");'>"+i+"</a></li>";
       } else {
    	   pageHTML += "<li><a class='ult'>"+i+"</a></li>";
	   }
	}
    pageHTML += "</ul><dl>";
	//下一页
	if(curpage == totalPage) {
		pageHTML += "<dt><a href='javascript:driverTransactionInfoById("+curpage+");'>下一页</a></dt>";
	}else {
		pageHTML += "<dt><a href='javascript:driverTransactionInfoById("+(curpage + 1)+");'>下一页</a></dt>";
	} 
	pageHTML += "</dl>";
	if(totalPage == 0){
		$("#driverTransactionInfoPageId").html("");
	}else {
		$("#driverTransactionInfoPageId").html(pageHTML);
	}
}


/**
 * 根据司机id查询评价记录
 * @return
 */
function driverAppraiseInfoById(curPage) {
	var driverId = $("#driverId").val();
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/queryUserDriverAssessInfodmList",
	   	dataType:"json",
	   	data:{driverId:driverId,curPage:curPage,pageSize:4}, //参数     	
		success:function(data){//回传函数
	   		if(data.result == 1) {
	   			var url = jcontextPath + "/dcts/user/login.jsp";
				window.open(url);
	   		}else if(data.result == 0) {
	   			var dataObj=data.content;
	   			var driverAppraiseInfoHtml = "";
	   			for(var i = 0;i<dataObj.list.length;i++) {
	   				var pjiaHtml = "";
	   				if(dataObj.list[i].tradeEvaluateScore == 3) {
	   					pjiaHtml = '<span><i class="icon7">&nbsp;</i>好评</span>';
	   				}
	   				if(dataObj.list[i].tradeEvaluateScore == 6) {
	   					pjiaHtml = '<span><i class="icon8">&nbsp;</i>中评</span>';
	   				}
	   				if(dataObj.list[i].tradeEvaluateScore == 9) {
	   					pjiaHtml = '<span><i class="icon9">&nbsp;</i>差评</span>';
	   				}
	   				driverAppraiseInfoHtml +=  '<dl>'+
								               		'<dt>'+
									                    '<h5>'+dataObj.list[i].createTime+'</h5>'+
									                    '<span>'+dataObj.list[i].companyName+'</span>'+pjiaHtml+
								                    '</dt>'+
								                    '<dd>'+
	   													'<p>'+dataObj.list[i].assess+'</p>'+
	   												'</dd>'+
	   											'</dl>';
	   			}
	   			driverAppraiseInfoPage(dataObj.pageInfo.totalPages,dataObj.pageInfo.curPageNo,dataObj.pageInfo.totalRecords);
	   			$("#driverAppraiseInfoId").html(driverAppraiseInfoHtml);
	   		}
	   	}
	});
}


function driverAppraiseInfoPage(totalPages,curPageNos,totalRecords) {
	var liststep = 5;//最多显示分页页数
	var totalPage = totalPages;//总页数
	var curpage = curPageNos;//当前页数
	var totalRecord = totalRecords;	//总记录数
	
   if (totalPage < curpage) {
	   curpage = totalPage;//如果分页变量大总页数，则将分页变量设计为总页数
   }
   if (curpage < 1) {
	   curpage = 1;//如果分页变量小于１,则将分页变量设为１
   }
   //计算要展示哪几页
   var listbegin;
   var listend;
   
   listbegin = curpage - ((liststep % 2 == 0) ? liststep / 2 : parseInt(liststep / 2));
   listend = curpage - 1 + ((liststep % 2 == 0) ? liststep / 2 : parseInt(liststep / 2) + 1);// 分页信息显示到第几页
   
   if((totalPage - curpage) < parseInt(liststep /2)){
	   listbegin = totalPage - liststep + 1;
	   listend = totalPage;
   }
   if(curpage <= parseInt(liststep /2)){
	   listbegin = 1;
	   listend = liststep;
   }
   if (listbegin < 1) {
	   listbegin = 1;
   }
   if (listend > totalPage) {
	   listend = totalPage;
   } 
   var pageHTML = "<dl>";		
   if (curpage == 1) {//上一页
	   pageHTML += "<dt><a href='javascript:driverAppraiseInfoById("+curpage+");'>上一页</a></dt>";
   }
   if(curpage>1) {
	   pageHTML += "<dt><a href='javascript:driverAppraiseInfoById("+(curpage-1)+");'>上一页</a></dt>";
   }
   pageHTML += "</dl><ul>";
   for (var i = listbegin; i <= listend; i++) {
	   if (i != curpage) {//如果i不等于当前页
			pageHTML += "<li><a href='javascript:driverAppraiseInfoById("+i+");'>"+i+"</a></li>";
       } else {
    	   pageHTML += "<li><a class='ult'>"+i+"</a></li>";
	   }
	}
  
    pageHTML += "</ul><dl>";
	//下一页
	if(curpage == totalPage) {
		pageHTML += "<dt><a href='javascript:driverAppraiseInfoById("+curpage+");'>下一页</a></dt>";
	}else {
		pageHTML += "<dt><a href='javascript:driverAppraiseInfoById("+(curpage + 1)+");'>下一页</a></dt>";
	} 
	pageHTML += "</dl>";
	if(totalPage == 0){
		$("#driverAppraiseInfoPageId").html("");
	}else {
		$("#driverAppraiseInfoPageId").html(pageHTML);
	}
}


/**
 * 根据司机id查询诚信档案
 * @return
 */
function driverAppraiseCountById() {
	var driverId = $("#driverId").val();
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/queryUserDrivrAssessCount",
	   	dataType:"json",
	   	data:{driverId:driverId}, //参数     	
		success:function(data){//回传函数
	   		if(data.result == 1) {
	   			var url = jcontextPath + "/dcts/user/login.jsp";
				window.open(url);
	   		}else if(data.result == 0) {
	   			var dataObj = data.content;
	   			appraiseCountHtml = '<li><label>好评：</label><span><i class="icon7">&nbsp;</i>('+dataObj.assessThree+')</span></li>'+
                    				'<li><label>中评：</label><span><i class="icon8">&nbsp;</i>('+dataObj.assessSix+')</span></li>'+
                    				'<li><label>差评：</label><span><i class="icon9">&nbsp;</i>('+dataObj.assessNine+')</span></li>';
	   			$("#driverAppraiseCountId").html(appraiseCountHtml);
	   		}
	   	}
	});
}

//订车弹窗口展现司机信息
function driverTitleHtml() {
	var driverId = $("#driverId").val();
	var driverTitle = "";
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/queryDriverTheCarInfo",
	   	dataType:"json",
	    data:{driverId:driverId}, //参数     	
		success:function(data){//回传函数
			var dataObj=data.content;
			if(dataObj.list.length == 1) {
				driverTitle = 	'<td>司机姓名<span>:</span>'+dataObj.list[0].name+'</td>'+
	        					'<td>车牌号<span>:</span>'+dataObj.list[0].carNumber+'</td>'+
	        					'<td>手机号<span>:</span>'+dataObj.list[0].code+'</td>'+
	        					'<td>车长<span>:</span>'+dataObj.list[0].carLength+'</td>'+
	        					'<td>板<span>:</span>'+dataObj.list[0].carPlateType+'</td>'+
	        					'<td>栏<span>:</span>'+dataObj.list[0].carBarType+'</td>'+
	        					'<td>当前位置<span>:</span>'+dataObj.list[0].lastLocation+'</td>';
			}
	   	}
	});	
	return driverTitle;
}

//订车展示当前用户的所有有效的货源
function cargoListHtml() {
	var driverTrHtml = "";
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/queryOrderCargoQuote",
	   	dataType:"json",
	   	//data:{driverId:driverId}, //参数     	
		success:function(data){//回传函数
			var dataObj=data.content;
	   		for (var i=0;i<dataObj.length;i++){
	   			var counts = dataObj[i].quoteCount;
	   			var cargoType = dataObj[i].cargoType;
	   			var driverType = dataObj[i].requestCarLength+' '+dataObj[i].requestCarPlateType+' '+dataObj[i].requestCarBarType;
	   			if(counts == 0) {
	   				counts = "暂无司机报价";
	   			}else{
	   				counts = counts+"司机报价";
	   			}
	   			if(cargoType == 0) {
	   				cargoType ="重货";
	   			}else if(cargoType == 1) {
	   				cargoType ="泡货";
	   			}else {
	   				cargoType = "";
	   			}
	   			if(dataObj[i].cargoWeight != null && dataObj[i].cargoWeight == "") {
	   				driverType += ' '+dataObj[i].cargoWeight+'吨';
	   			}
	   			if(dataObj[i].cargoCubage != null && dataObj[i].cargoCubage == "") {
	   				driverType += ' '+dataObj[i].cargoCubage+'方';
	   			}
	   			driverTrHtml += '<tr style="cursor:pointer;" onclick="setDriverCar(\''+dataObj[i].id+'\');">'+
					              '<td>'+cargoType+'-'+dataObj[i].cargoName+'</td>'+
					              '<td>'+dataObj[i].requestStartTime+'</td>'+
					              '<td>'+driverType+'</td>'+
					              '<td title="'+dataObj[i].startProCityCounty+'">'+dataObj[i].startProCityCounty+'</td>'+
					              '<td title="'+dataObj[i].endProCityCounty+'">'+dataObj[i].endProCityCounty+'</td>'+
					              '<td>待交易</td>'+
					              '<td title="'+counts+'">'+counts+'</td>'+
					            '</tr>';
	   		}
	   	}
	});
	var titleHTML = driverTitleHtml();
	var driverHtml = '<div class="mt2">'+
					 '<div class="stop">'+
					 	'<h3><i>&nbsp;</i>司机信息</h3>'+
					 	'<table cellpadding="0" cellspacing="0">'+
					    	'<tr>'+titleHTML+'</tr>'+
					    '</table>'+
					  '<div class="switch">'+
					  	'<ul><li>'+businessLineHTML+'</li></ul>'+
					   	'<ul><li>'+lineHTML+'</li></ul>'+
					  '</div>'+
					'</div>'+
   					 '<div class="fl pick_fl">'+
        				'<p class="ckpt"><i class="newi">&nbsp;</i>请您选择一条货源，匹配您选择的车辆。<span id="orderIdNull" class="retun" style="display:none;">请您选择一条货源信息</span></p>'+
        				'<div class="divtab" style="width:836px;">'+
	         				'<table border="0" cellpadding="0" cellspacing="0">'+
					            '<thead >'+
					              '<tr>'+
					                '<td width="115">货物名称</td>'+
					                '<td width="80">装货时间</td>'+
					                '<td width="150">车型要求</td>'+
					                '<td width="155">装货地</td>'+
					                '<td width="155">卸货地</td>'+
					                '<td width="58">状态</td>'+
					                '<td width="100">报价</td>'+
					              '</tr>'+
					            '</thead>'+driverTrHtml+
	           				'</table>'+
          				'</div>'+
          				'<div class="divtab divtab1" style="width:836px;">'+
         					'<table border="0" cellpadding="0" cellspacing="0" id="quoteHtmlId">'+
					            '<thead >'+
					              '<tr>'+
					              	'<td width="80">订车操作</td>'+
					              	'<td width="80">当前报价</td>'+
                					'<td width="80">报价类型</td>'+
                					'<td width="114">手机号码</td>'+
                					'<td width="200">当前位置</td>'+
                					'<td width="80">司机姓名</td>'+
                					'<td width="99">车牌号</td>'+
                					'<td width="200">车型</td>'+
					              '</tr>'+
					            '</thead>'+
           					'</table>'+
          				'</div>'+
        			'</div>'+
        			'<div class="fr pick_fr">'+
                		'<p class="ckpt" style="height: 35px;"><i class="newi">&nbsp;</i>在线订车后将货物信息发送给指定的司机,请等待司机的确认</p>'+
                    	'<ul>'+
                    		'<input id="orderCargoId" name="orderCargoId" type="hidden" />'+
                    		'<li><label>装货地&nbsp;:</label><input id="startProCityCounty" name="startProCityCounty" type="text" class="fint" />'+
            					'<div><label></label><input id="startTown" name="startTown" type="text" class="fint" /></div>'+
          					'</li>'+
                        	'<li><label>卸货地&nbsp;:</label><input id="endProCityCounty" name="endProCityCounty" type="text" class="fint" />'+
            					'<div><label></label><input id="endTown" name="endTown" type="text" class="fint" /></div>'+
          					'</li>'+
                    		'<li><label>装货时间&nbsp;:</label><input id="requestStartTime" name="requestStartTime" type="text" class="fint" /></li>'+
                    		'<li><label>货物信息&nbsp;:</label><input id="cargoType" name="cargoType" type="text" class="fint in65" />&nbsp;&nbsp;'+
                    			'<input id="cargoName" name="cargoName" type="text" class="fint fint100" />'+
                    		'</li>'+
                    		'<li><label class="fl">备注&nbsp;:</label><textarea id="remark" name="remark" cols="" rows=""></textarea></li>'+
                    	'</ul>'+
                    	'<div class="bot_n"><a href="javascript:setTheCarOrderCargo();">在线订车</a>&nbsp;<a href="javascript:closeUp();">取消</a></div>'+
                	'</div>'+
            	'</div>';
	return driverHtml;
}

//查看货源详细信息
function setDriverCar(orderCargoId) {
	$("#orderIdNull").hide();
	//货物详细信息 
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/queryOrderCargoQuoteById",
	   	dataType:"json",
	    data:{orderCargoId:orderCargoId}, //参数     	
		success:function(data){//回传函数
			var dataObj=data.content;
			$("#orderCargoId").val(dataObj.id);
	   		$("#startProCityCounty").val(dataObj.startProCityCounty);
	   		$("#startTown").val(dataObj.startTown);
	   		$("#endProCityCounty").val(dataObj.endProCityCounty);
	   		$("#endTown").val(dataObj.endTown);
	   		$("#requestStartTime").val(dataObj.requestStartTime);
	   		if(dataObj.cargoType == 0) {
	   			$("#cargoType").val("重货");
	   		}else if(dataObj.cargoType == 1) {
	   			$("#cargoType").val("泡货");
	   		}
	   		$("#cargoName").val(dataObj.cargoName);
	   		$("#remark").val(dataObj.remark);
	   	}
	});
	//货物报价信息
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/queryQuoteList",
	   	dataType:"json",
	    data:{orderCargoId:orderCargoId}, //参数     	
		success:function(data){//回传函数
			var dataObj=data.content;
			var quoteHtml = "";
				quoteHtml = '<thead >'+
					            '<tr>'+
					            '<td width="80">订车操作</td>'+
				              	'<td width="80">当前报价</td>'+
            					'<td width="80">报价类型</td>'+
            					'<td width="114">手机号码</td>'+
            					'<td width="200">当前位置</td>'+
            					'<td width="80">司机姓名</td>'+
            					'<td width="99">车牌号</td>'+
            					'<td width="200">车型</td>'+
					             '</tr>'+
					        '</thead>';
	   		for (var i=0;i<dataObj.length;i++){
	   			quoteHtml += '<tr>'+
	   							 '<td><a href="javascript:setTheCar(\''+dataObj[i].driverId+'\',\''+dataObj[i].cargoId+'\');">在线订车</a></td>'+
	   							 '<td>'+dataObj[i].quoteFair+'元</td>'+
	              				 '<td>'+dataObj[i].quoteTypeVal+'</td>'+
	              				 '<td>'+dataObj[i].code+'</td>'+
	              				 '<td><span class="icon2">&nbsp;</span>'+dataObj[i].lastLocation+'</td>'+
	              				 '<td>'+dataObj[i].name+'</td>'+
	              				 '<td>'+dataObj[i].carNumber+'</td>'+
	              				 '<td>'+dataObj[i].carTypes+'</td>'+
            				 '</tr>';
	   		}
	   		$("#quoteHtmlId").html(quoteHtml);
	   	}
	});
}


// 车找货成功后提醒司机，百度云推送消息 20140715 PM 

function baiduPushMessagesToDriverTwo(channelId,userId){
	if(channelId==null||channelId==""||userId==null||userId==""){
		return;
	}
		   $.ajax({
						url: jcontextPath+'/pushMessageToDriver',   
						type:'post',	
						dataType:'json', 
						data:{'driverUserInfoDomain.baiduChannelId':channelId,'driverUserInfoDomain.baiduUserId':userId,'type':'2'},      	               
						success:function(data){//回传函数
						}
					});
		
	}


