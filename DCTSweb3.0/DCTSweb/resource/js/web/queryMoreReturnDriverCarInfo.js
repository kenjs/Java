/**
 * 搜索更多当前车源
 * @return
 */
function returnMoreSbmint(){
	document.getElementById('mainForm').submit();
	return true;
}
//车辆-板
function setCarPlateTypeDict(strValue) {
	if(strValue == "全部") {
		strValue = "";
	}
	$("#returnCarPlateType").val(strValue);
	getCarPlateTypeDict(strValue);
}
function getCarPlateTypeDict(str){
	if(str == "") {
		str = "请选择"
	}
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/getCarPlateTypeDict",
	   	dataType:"json",	
	   	contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
	   	success:function(data){
			var dataObj=data.content;
			var CarPlateTypeDictHtml = "";
	   		if(data == null){
	   			showAlert("加载失败！");
	   		}
	   		for (var i=0;i<dataObj.length;i++){
	   			var quanbu = "";
	   			if(dataObj[i].code == 0) {
	   				quanbu = "全部";
	   			}else{
	   				quanbu = dataObj[i].name;
	   			}
	   			if(dataObj[i].name == str) {
	   				CarPlateTypeDictHtml += '<a href="javascript:setCarPlateTypeDict(\'\');" class="out">'+quanbu+'</a>';
	   			}else {
	   				CarPlateTypeDictHtml += '<a href="javascript:setCarPlateTypeDict(\''+quanbu+'\');">'+quanbu+'</a>';
	   			}
	   		}
	   		$("#CarPlateTypeDictId").html(CarPlateTypeDictHtml);
	   	},
	    error:function(data){
	    	showAlert(data.responseText);
		}
	});
}

//车辆-栏
function setCarBarTypeDict(strValue) {
	if(strValue == "全部") {
		strValue = "";
	}
	$("#returnCarBarType").val(strValue);
	getCarBarTypeDict(strValue);
}
function getCarBarTypeDict(str){
	if(str == "") {
		str = "请选择"
	}
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/getCarBarTypeDict",
	   	dataType:"json",	
	   	contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
	   	success:function(data){
			var dataObj=data.content;
			var CarBarTypeDict = "";
	   		if(data == null){
	   			showAlert("加载失败！");
	   		}
	   		for (var i=0;i<dataObj.length;i++){
	   			var quanbu = "";
	   			if(dataObj[i].code == 0) {
	   				quanbu = "全部";
	   			}else{
	   				quanbu = dataObj[i].name;
	   			}
	   			if(dataObj[i].name == str) {
	   				CarBarTypeDict += '<a href="javascript:setCarBarTypeDict(\'\');" class="out">'+quanbu+'</a>';
	   			}else {
	   				CarBarTypeDict += '<a href="javascript:setCarBarTypeDict(\''+quanbu+'\');">'+quanbu+'</a>';
	   			}
	   		}
	   		$("#CarBarTypeDictId").html(CarBarTypeDict);
	   	},
	    error:function(data){
	    	showAlert(data.responseText);
		}
	});
}

//车辆-长
function setCarLengthDict(strValue) {
	if(strValue == "全部") {
		strValue = "";
	}
	$("#returnCarLength").val(strValue);
	getCarLengthDict(strValue);
}
function getCarLengthDict(str){
	if(str == "") {
		str = "请选择"
	}
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/getCarLengthDict",
	   	dataType:"json",	
	   	contentType: "application/x-www-form-urlencoded; charset=UTF-8", 
	   	success:function(data){
			var dataObj=data.content;
			var CarLengthDict = "";
	   		if(data == null){
	   			showAlert("加载失败！");
	   		}
	   		for (var i=0;i<dataObj.length;i++){
	   			var quanbu = "";
	   			if(dataObj[i].code == 0) {
	   				quanbu = "全部";
	   			}else{
	   				quanbu = dataObj[i].name;
	   			}
	   			if(dataObj[i].name == str) {
	   				CarLengthDict += '<a href="javascript:setCarLengthDict(\'\');" class="out">'+quanbu+'</a>';
	   			}else {
	   				CarLengthDict += '<a href="javascript:setCarLengthDict(\''+quanbu+'\');">'+quanbu+'</a>';
	   			}
	   		}
	   		$("#CarLengthDictId").html(CarLengthDict);
	   	},
	    error:function(data){
	    	showAlert(data.responseText);
		}
	});
}

function getReturnPageInfo(str) {
	$("#curPage").val(str);
	returnMoreSbmint();
}
function returnPageInfo(totalPages,curPageNos,totalRecords) {
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
   pageHTML += "<dt><a href='javascript:getReturnPageInfo(1);'>首页</a></dt>";
   if (curpage == 1) {//上一页
	   pageHTML += "<dt><a href='javascript:getReturnPageInfo("+curpage+");'>上一页</a></dt>";
   }
   if(curpage>1) {
	   pageHTML += "<dt><a href='javascript:getReturnPageInfo("+(curpage-1)+");'>上一页</a></dt>";
   }
   pageHTML += "</dl><ul>";
   for (var i = listbegin; i <= listend; i++) {
	   if (i != curpage) {//如果i不等于当前页
			pageHTML += "<li><a href='javascript:getReturnPageInfo("+i+");'>"+i+"</a></li>";
       } else {
    	   pageHTML += "<li><a class='ult'>"+i+"</a></li>";
	   }
	}
   pageHTML += "</ul><dl>";
	//下一页
	if(curpage == totalPage) {
		pageHTML += "<dt><a href='javascript:getReturnPageInfo("+curpage+");'>下一页</a></dt>";
	}else {
		pageHTML += "<dt><a href='javascript:getReturnPageInfo("+(curpage + 1)+");'>下一页</a></dt>";
	} 
	pageHTML += "<dd><a href='javascript:getReturnPageInfo("+totalPages+");'>尾页</a></dd>";
	pageHTML += "<dd><a>共"+totalPages+"页\/"+totalRecord+"条信息</a></dd>";
	pageHTML += "</dl>";
	if(totalPage == 0){
		$("#pageInfoHtmlId").append("");
	}else {
		$("#pageInfoHtmlId").append(pageHTML);
	}
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
		data:{province:province,city:city,pageSize:5}, //参数     	               
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
	   								'<li class="seef"><a target="_blank" href="javascript:openDriverDetailed('+dataObj[i].id+');">查看详情</a></li>'+
	   							 '</ul>';
	   		}
	   		$("#driverCarId").html(driverCarHtml);
	   	}
	});
}


/**
 * 根据司机Id打开司机详细页面
 */
function openDriverDetailed(driverId){
	if(booleanUserSession() == false){
		return;
	}
	var encoded = $("#userEncoded").val();
	if(encoded != 0) {
		art.dialog({
			width:400,
			height:100,
			id: 'shake-demo',
			title: '提示',
			content: '对不起，您没有操作权限！',
			lock: true,
			fixed: true,
			ok: function () {
			},
			okValue: '确定'
		});
		return;
	}
	var url = jcontextPath + "/openDriverDetailed.action?driverId="+driverId;
	window.open(url, "_blank"); //注意第二个参数
}
