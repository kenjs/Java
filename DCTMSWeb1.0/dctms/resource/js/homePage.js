//��ҳ��ѯ���г�Դ
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
		   		var openHtml = '<table border="0">';
		   			openHtml += '<tr class="menu">'+
		   							'<td width="60">����</td><td width="200">����</td><td width="50">��Ӫ״̬</td><td width="180">·��</td>'+
		   							'<td width="140">��ǰλ��</td><td width="100">ʱ��</td><td width="70">��ϵ��ʽ</td>'+
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
		   		openHtml += ' </table>';
		   		$("#DriverCarHtnlId").html(openHtml);
		   	},
		    error:function(data){
			}
		});
	}
	
	//��ҳ��ѯ���л�Դ
	function queryOrderCargo() {
		$.ajax({
			type: "POST",
		   	async:true,
		   	url: jcontextPath + "/queryStartDeployMainOrderCargoInfo",
		   	dataType:"json",	
		   	contentType: "application/x-www-form-urlencoded; charset=GBK", 
		   	success:function(data){
		   		var dataObj=data.content;
		   		var openHtml = '<table border="0">';
		   			openHtml += '<tr class="menu">'+
		   							'<td width="120">װ����</td><td width="120">ж����</td><td width="100">������λ</td><td width="100">��������</td>'+
		   							'<td width="60">���������</td><td width="60">�������֣�</td><td width="200">����Ҫ��</td><td width="60">����ʱ��</td>'+
		   							'<td width="70">��ϵ��ʽ</td>'+
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
		   		openHtml += ' </table>';
		   		$("#OrderCargoHtnlId").html(openHtml);
		   	},
		    error:function(data){
			}
		});
	}
	
	
	//��ҳ��ѯ���гɽ���¼
	function queryEndDeployMainOrderCargo() {
		$.ajax({
			type: "POST",
		   	async:true,
		   	url: jcontextPath + "/queryEndDeployMainOrderCargoInfo",
		   	dataType:"json",	
		   	contentType: "application/x-www-form-urlencoded; charset=GBK", 
		   	success:function(data){
		   		var dataObj=data.content;
		   		var openHtml = '<table border="0">';
		   			openHtml += '<tr class="menu">'+
		   							'<td width="100">������λ</td><td width="120">������</td><td width="120">ж����</td><td width="100">��������</td>'+
		   							'<td width="60">����</td><td width="200">����</td><td width="60">����״̬</td>'+
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
        							'<td>���׳ɹ�</td>'+
        						'</tr>';
		   		}
		   		openHtml += '</table>';
		   		$("#queryEndDeployMainOrderCargoHtnlId").html(openHtml);
		   	},
		    error:function(data){
			}
		});
	}
	
	
	function getQuery(url) {
		parent.location.href = jcontextPath+url;
	}