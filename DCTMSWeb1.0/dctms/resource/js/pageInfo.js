// 列表页分页码
// 2014-02-19 nxj

function pageInfo(totalPages,curPageNos,totalRecords) {
	var liststep = 10;//最多显示分页页数
	var totalPage = totalPages;//总页数
	var page = curPageNos;//当前页数
	var totalRecord = totalRecords;	//总记录数
	
   if (totalPage < page) {
        page = totalPage;//如果分页变量大总页数，则将分页变量设计为总页数
   }
   if (page < 1) {
		page = 1;//如果分页变量小于１,则将分页变量设为１
   }
//   var listbegin = page - ((liststep % 2 == 0) ? liststep / 2 : parseInt(liststep/2) + 1);
//   if (listbegin < 1) {
//        listbegin = 1;
//   }
//   var listend = page + liststep;//分页信息显示到第几页
//   if (listend > totalPage) {
//		listend = totalPage + 1;
//   } 
   var listbegin =0;
   var listend = 0;
   //判断总页数是否小于10
   if(totalPage<=liststep){
	   listbegin = 1;
	   listend = totalPage;
   }else {
	   if(page<=5){
		   listbegin = 1;
		   listend = liststep;
	   }
	   else if(totalPage - page>=5){
		   listbegin = page - 4;
		   listend = page +5;
	   }else{
		   listbegin = totalPage - liststep +1;
		   listend = totalPage;
	   }
   }
   var pageHTML = "<a href='javascript:getPageInfos(1);'>首页</a>";
   if (page == 1) {//上一页
	   pageHTML += "<a href='javascript:getPageInfos("+page+");'>上一页</a>";
   }
   if(page>1) {
	   pageHTML += "<a href='javascript:getPageInfos("+(page-1)+");'>上一页</a>";
   }
   pageHTML += "<span class='pageNum'>";
   for (var i = listbegin; i < listend+1; i++) {
	   if (i != page) {//如果i不等于当前页
			pageHTML += "<a href='javascript:getPageInfos("+i+");' >"+i+"</a>";
       } else {
    	   pageHTML += "<a class='currentA'>"+i+"</a>";
	   }
	}
   pageHTML += "</span>";
	//下一页
	if(page == totalPage) {
		pageHTML += "<a href='javascript:getPageInfos("+page+");'>下一页</a>";
	}else {
		pageHTML += "<a href='javascript:getPageInfos("+(page + 1)+");'>下一页</a>";
	} 
	pageHTML += "<a href='javascript:getPageInfos("+totalPages+");'>尾页</a>";
	pageHTML += ""+
				"<p>第<input class='jump' type='text' id='pageGo' name='pageGo' value='' />页"+
				"<input class='go-botton' onclick='getGo();' type='button' value='GO' /></p>"+
				""+
				"<span class='text'>共"+totalPages+"页\/"+totalRecord+"条信息</span>";
	if(totalPage == 0){
		var pageInfoHtmlId = document.getElementById("pageInfoHtmlId");
		pageInfoHtmlId.innerHTML="";
		$("#pageInfoHtmlId").append(pageHTML);
		//$("#pageInfoHtmlId").hide();
	}else {
		var pageInfoHtmlId = document.getElementById("pageInfoHtmlId");
		pageInfoHtmlId.innerHTML="";

		$("#pageInfoHtmlId").append(pageHTML);
	}
}


//司机历史轨迹分页
//function locationPageInfo(totalPages,curPageNos,totalRecords) {
//	var liststep = 5;//最多显示分页页数
//	var totalPage = totalPages;//总页数
//	var page = curPageNos;//当前页数
//	var totalRecord = totalRecords;	//总记录数
//	
//   if (totalPage < page) {
//        page = totalPage;//如果分页变量大总页数，则将分页变量设计为总页数
//   }
//   if (page < 1) {
//		page = 1;//如果分页变量小于１,则将分页变量设为１
//   }
//   var listbegin = page - ((liststep % 2 == 0) ? liststep / 2 : parseInt(liststep/2) + 1);
//   if (listbegin < 1) {
//        listbegin = 1;
//   }
//   var listend = page + liststep;//分页信息显示到第几页
//   if (listend > totalPage) {
//		listend = totalPage + 1;
//   } 
//   var pageHTML = "<ul class='number'><li><a href='javascript:getPageInfos(1);'>首页</a></li>";
//   if (page == 1) {//上一页
//	   pageHTML += "<li><a href='javascript:getPageInfos("+page+");'>上一页</a></li>";
//   }
//   if(page>1) {
//	   pageHTML += "<li><a href='javascript:getPageInfos("+(page-1)+");'>上一页</a></li>";
//   }
//   for (var i = listbegin; i < listend; i++) {
//	   if (i != page) {//如果i不等于当前页
//			pageHTML += "<li><a href='javascript:getPageInfos("+i+");'>"+i+"</a></li>";
//       } else {
//    	   pageHTML += "<li><a class='currentA'>"+i+"</a></li>";
//	   }
//	}
//	//下一页
//	if(page == totalPage) {
//		pageHTML += "<li><a href='javascript:getPageInfos("+page+");'>下一页</a></li>";
//	}else {
//		pageHTML += "<li><a href='javascript:getPageInfos("+(page + 1)+");'>下一页</a></li>";
//	} 
//	pageHTML += "<li><a href='javascript:getPageInfos("+totalPages+");'>尾页</a></li>";
//	pageHTML += "<li class='text'>共"+totalPages+"页\/"+totalRecord+"条信息</li></ul>";
//	if(totalPage == 0){
//		$("#pageInfoHtmlId").append("");
//	}else {
//		$("#pageInfoHtmlId").append(pageHTML);
//	}
//	
//}

// 查询搜索
function getSubmit() {
	$("#curPage").val(1);
	$("#curPageNo").val(1);
	queryInfo();
	//document.getElementById('mainForm').submit();
	//return true;
}

//翻页
function getPageInfos(curPages) {
	var curPage = curPages;//要显示的页数
	$("#curPage").val(curPage);
	$("#curPageNo").val(curPage);
	queryInfo();
	//document.getElementById('mainForm').submit();
	//return true;
}

//直接到达页面
function getGo() {
	var pageGo = $("#pageGo").val();//要显示的页数
	if(trim(pageGo) == '') {
		return false;
	}else {
		var reg = new RegExp("^[0-9]*$");
		if(!reg.test(trim(pageGo))){
		    return false;
		}
	}
	$("#curPage").val(pageGo);  
	$("#curPageNo").val(pageGo);
	queryInfo();
	//document.getElementById('mainForm').submit();
	//return true;
}

//鼠标放上
function getOnmouseover(myId) {
	$("#tr"+myId).addClass("selected");
}

//鼠标拿下
function getOnmouseout(myId) {
	$("#tr"+myId).removeClass("selected");
}
/**
* ajaxCommon ajax提交js方法
* @param method post或者get提交
* @param url 请求的url地址 
* @param data 请求的data数据
* @param async 是否异步true为异步
* @param obj 回调函数
*/
function AjaxSubmit(obj){
	var url = obj.url + "?random="+Math.random();
	if(obj.method==="get"){
		url+=(url.indexOf("?")==-1?"?":"&")+params(obj.data);
	}
	var xhr = createXhr();
	if(obj.async){
		xhr.onreadystatechange=function(){
			if(xhr.readyState==4){
				JsAjaxCallBack(xhr,obj);
			}
		};
	}
	xhr.open(obj.method,url,obj.async);
	if(obj.method==="post"){
		xhr.setRequestHeader('Content-Type','application/x-www-form-urlencoded');
		xhr.send(params(obj.data));
	}else{
		xhr.send(null);
	}
	if(!obj.async){
		JsAjaxCallBack(xhr,obj);
	}
}


//jsajax中名值对转换为字符串
function params(data){
	var arr =[];
	for(var i in data ){
		arr.push(encodeURI(i)+"="+encodeURI(encodeURI(data[i])));
	}
	return arr.join("&");
}
//js ajax中回值
function JsAjaxCallBack(xhr,obj){
	if(xhr.status==200){
		var result = xhr.responseText;
		result=result.replace(/[\r\n]/g,"");//去掉回车换行
		if(result=="login"){
			window.parent.parent.location.href=jcontextPath+ "/managerLogout";//返回到登录页面
			return;
		}
		if(result=="loginMx"){
			window.close();
			return;
		}
		if(result=="异常，此次操作无效"){
			alert("异常，此次操作无效");
			return;
		}
		if(result.indexOf("{")==-1){
			obj.success(result);
			return;
		}
		obj.success(JSON.parse(result));
	}else{
		alert("访问出错，代码"+xhr.status);
	}
}
//创建Xhr兼容浏览器
function createXhr(){
	if(typeof XMLHttpRequest!="undefined"){
		return new XMLHttpRequest();
	}else if(typeof ActiveXObject!="undefined"){
		var version = [
		               "MSXML2.XMLHttp.6.0",
		               "MSXML2.XMLHttp.3.0",
		               "MSXML2.XMLHttp"
		               ];
		for(var i in version){
			try{
				return new ActiveXObject(version[i]);
			}catch(e){
				//跳过
			}
		}
	}
	throw new Error("您的浏览器不支持ajax");
	return xhr;
}

//初始化方法
function queryInfoInit(){
	document.getElementById("curPage").value=1;
	document.getElementById("curPageNo").value=1;
	queryInfo();
}
