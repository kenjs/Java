/**
 * 搜索更多货源
 * @return
 */
function realMoreSbmint(){
	document.getElementById('mainForm').submit();
	return true;
}
function getRealPageInfo(str) {
	$("#curPage").val(str);
	realMoreSbmint();
}
function cargoPageInfo(totalPages,curPageNos,totalRecords) {
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
   pageHTML += "<dt><a href='javascript:getRealPageInfo(1);'>首页</a></dt>";
   if (curpage == 1) {//上一页
	   pageHTML += "<dt><a href='javascript:getRealPageInfo("+curpage+");'>上一页</a></dt>";
   }
   if(curpage>1) {
	   pageHTML += "<dt><a href='javascript:getRealPageInfo("+(curpage-1)+");'>上一页</a></dt>";
   }
   pageHTML += "</dl><ul>";
   for (var i = listbegin; i <= listend; i++) {
	   if (i != curpage) {//如果i不等于当前页
			pageHTML += "<li><a href='javascript:getRealPageInfo("+i+");'>"+i+"</a></li>";
       } else {
    	   pageHTML += "<li><a class='ult'>"+i+"</a></li>";
	   }
	}
    pageHTML += "</ul><dl>";
	//下一页
	if(curpage == totalPage) {
		pageHTML += "<dt><a href='javascript:getRealPageInfo("+curpage+");'>下一页</a></dt>";
	}else {
		pageHTML += "<dt><a href='javascript:getRealPageInfo("+(curpage + 1)+");'>下一页</a></dt>";
	} 
	pageHTML += "<dd><a href='javascript:getRealPageInfo("+totalPages+");'>尾页</a></dd>";
	pageHTML += "<dd><a>共"+totalPages+"页\/"+totalRecord+"条信息</a></dd>";
	pageHTML += "</dl>";
	if(totalPage == 0){
		$("#pageInfoHtmlId").html("");
	}else {
		$("#pageInfoHtmlId").html(pageHTML);
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
		data:{province:province,city:city,pageSize:6}, //参数     	               
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

