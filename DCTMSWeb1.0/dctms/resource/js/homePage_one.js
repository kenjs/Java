//首页查询所有车源
	function queryDriverCar() {
		$.ajax({
			type: "POST",
		   	async:true,
		   	url: jcontextPath + "/queryMainDriverCarInfo",
		   	dataType:"json",	
		   	contentType: "application/x-www-form-urlencoded; charset=GBK", 
		   	success:function(data){
		   		var dataObj=data.content;
		   		var carStateType = "";
		   		var openHtml = '<table>';
		   			openHtml += '<tr class="menu">'+
		   							'<td width="100">车牌</td><td width="120">车型</td><td width="100">运营状态</td><td width="120">路线</td>'+
		   							'<td width="120">当前位置</td><td width="100">时间</td><td width="100">联系方式</td>'+
        					    '</tr>';
		   		for(var i = 0;i<dataObj.length;i++) {
		   			if(i%2 == 0) {
		   				openHtml += '<tr>';
		   			}else {
		   				openHtml += '<tr class="selected">';
		   			}
		   			openHtml += '<td title="'+dataObj[i].carNumber+'">'+dataObj[i].carNumber+'</td>'+
        						'<td title="'+dataObj[i].carTypes+'">'+dataObj[i].carTypes+'</td>'+
        						'<td title="'+dataObj[i].carStateTypeValue+'">'+dataObj[i].carStateTypeValue+'</td>'+
        						'<td title="'+dataObj[i].businessLines+'">'+dataObj[i].businessLines+'</td>'+
        						'<td title="'+dataObj[i].location+'">'+dataObj[i].location+'</td>'+
        						'<td title="'+dataObj[i].collectTime+'">'+dataObj[i].collectTime+'</td>'+
        						'<td title="'+dataObj[i].code+'">'+dataObj[i].code+'</td>'+
        						'</tr>';
        			
		   		}
		   		openHtml += ' </table><div class="morebox1"><a href="javascript:" onclick="getQuery(\'/queryDriverCarInfo\');">更多</a></div>';
		   		$("#DriverCarHtnlId").html(openHtml);
		   	},
		    error:function(data){
			}
		});
	}
	
	//首页查询所有货源
	function queryOrderCargo() {
		$.ajax({
			type: "POST",
		   	async:true,
		   	url: jcontextPath + "/queryStartDeployMainOrderCargoInfo",
		   	dataType:"json",	
		   	contentType: "application/x-www-form-urlencoded; charset=GBK", 
		   	success:function(data){
		   		var dataObj=data.content;
		   		var openHtml = '<table>';
		   			openHtml += '<tr class="menu">'+
		   							'<td width="100">装货地</td><td width="100">卸货地</td><td width="100">发货单位</td><td width="100">货物名称</td>'+
		   							'<td width="60">体积（方）</td><td width="60">重量（吨）</td><td width="120">车型要求</td><td width="100">发货时间</td>'+
		   							'<td width="100">联系方式</td>'+
        					    '</tr>';
		   		for(var i = 0;i<dataObj.length;i++) {
		   			if(i%2 == 0) {
		   				openHtml += '<tr>';
		   			}else {
		   				openHtml += '<tr class="selected">';
		   			}
        			openHtml += 	'<td title="'+dataObj[i].startLocation+'">'+dataObj[i].startLocation+'</td>'+
        							'<td title="'+dataObj[i].endLocation+'">'+dataObj[i].endLocation+'</td>'+
        							'<td title="'+dataObj[i].companyName+'">'+dataObj[i].companyName+'</td>'+
        							'<td title="'+dataObj[i].cargoName+'">'+dataObj[i].cargoName+'</td>'+
        							'<td title="'+dataObj[i].cargoCubage+'">'+dataObj[i].cargoCubage+'</td>'+
        							'<td title="'+dataObj[i].cargoWeight+'">'+dataObj[i].cargoWeight+'</td>'+
        							'<td title="'+dataObj[i].requestCarTypes+'" class="longer">'+dataObj[i].requestCarTypes+'</td>'+
        							'<td title="'+dataObj[i].requestStartTime+'">'+dataObj[i].requestStartTime+'</td>'+
        							'<td title="'+dataObj[i].contactTelephone+'">'+dataObj[i].contactTelephone+'</td>'+
        							'</tr>';
		   		}
		   		openHtml += ' </table><div class="morebox2"><a href="javascript:" onclick="getQuery(\'/queryStartDeployOrderCargoInfo\');">更多</a></div>';
		   		$("#OrderCargoHtnlId").html(openHtml);
		   	},
		    error:function(data){
			}
		});
	}
	
	
	//首页查询所有成交记录
	function queryEndDeployMainOrderCargo() {
		$.ajax({
			type: "POST",
		   	async:true,
		   	url: jcontextPath + "/queryEndDeployMainOrderCargoInfo",
		   	dataType:"json",	
		   	contentType: "application/x-www-form-urlencoded; charset=GBK", 
		   	success:function(data){
		   		var dataObj=data.content;
		   		var openHtml = '<table>';
		   			openHtml += '<tr class="menu">'+
		   							'<td width="100">发货单位</td><td width="100">发货地</td><td width="100">卸货地</td><td width="100">货物名称</td>'+
		   							'<td width="100">车牌</td><td width="120">车型</td><td width="100">交易状态</td>'+
        					    '</tr>';
		   		for(var i = 0;i<dataObj.length;i++) {
		   			if(i%2 == 0) {
		   				openHtml += '<tr>';
		   			}else {
		   				openHtml += '<tr class="selected">';
		   			}
        			openHtml += 	'<td title="'+dataObj[i].companyName+'">'+dataObj[i].companyName+'</td>'+
        							'<td title="'+dataObj[i].startLocation+'">'+dataObj[i].startLocation+'</td>'+
        							'<td title="'+dataObj[i].endLocation+'">'+dataObj[i].endLocation+'</td>'+
        							'<td title="'+dataObj[i].cargoName+'">'+dataObj[i].cargoName+'</td>'+
        							'<td title="'+dataObj[i].carNumber+'">'+dataObj[i].carNumber+'</td>'+
        							'<td title="'+dataObj[i].carTypes+'">'+dataObj[i].carTypes+'</td>'+
        							'<td>交易成功</td>'+
        						'</tr>';
		   		}
		   		openHtml += '</table><div class="morebox3"><a href="javascript:" onclick="getQuery(\'/queryEndDeployOrderCargoInfo\');">更多</a></div>';
		   		$("#queryEndDeployMainOrderCargoHtnlId").html(openHtml);
		   	},
		    error:function(data){
			}
		});
	}
	
	
	function getQuery(url) {
		parent.location.href = jcontextPath+url;
	}