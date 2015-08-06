/**
 * 去除字符串前后的空格
 * @param str 原字符串
 * @returns 
 */
function trim(str){
	return str.replace(/(^\s*)|(\s*$)/g,"");
}

$(function(){
	//document.oncontextmenu=new Function("event.returnValue=false;");
	//document.onselectstart=new Function("event.returnValue=false;");
	//document.oncopy=new Function("event.returnValue=false;");
	//document.onpaste=new Function("event.returnValue=false;");
	
});

//列表页分页码
//2014-02-19 nxj
function pageInfoTwo(totalPages,curPageNos,totalRecords) {
	var liststep = 10;//最多显示分页页数
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
   var pageHTML = "<a href='javascript:getPageInfosTwo(1);'>首页</a>";
   if (curpage == 1) {//上一页
	   pageHTML += "<a href='javascript:getPageInfosTwo("+curpage+");'>上一页</a>";
   }
   if(curpage>1) {
	   pageHTML += "<a href='javascript:getPageInfosTwo("+(curpage-1)+");'>上一页</a>";
   }
   
   for (var i = listbegin; i <= listend; i++) {
	   if (i != curpage) {//如果i不等于当前页
			pageHTML += "<a href='javascript:getPageInfosTwo("+i+");'>"+i+"</a>";
       } else {
    	   pageHTML += "<a class='currentA'>"+i+"</a>";
	   }
	}
	//下一页
	if(curpage == totalPage) {
		pageHTML += "<a href='javascript:getPageInfosTwo("+curpage+");'>下一页</a>";
	}else {
		pageHTML += "<a href='javascript:getPageInfosTwo("+(curpage + 1)+");'>下一页</a>";
	} 
	pageHTML += "<a href='javascript:getPageInfosTwo("+totalPages+");'>尾页</a>";
	pageHTML += ""+
				"<p>第<input class='jump' type='text' id='pageGo' name='pageGo' value='' />页"+
				"<input class='go-botton' onclick='getGoTwo();' type='button' value='GO' /><span class='text'>共"+totalPages+"页\/"+totalRecord+"条信息</span></p>"+
				""+
				"";
	if(totalPage == 0){
		$("#pageInfoHtmlIds").append("");
	}else {
		$("#pageInfoHtmlIds").append(pageHTML);
	}
}


// 查询搜索
function getSubmitTwo() {
	$("#curPages").val(1);
	$("#curPageNos").val(1);
	document.getElementById('towForm').submit();
	return true;
}

//翻页
function getPageInfosTwo(curPages) {
	var curPage = curPages;//要显示的页数
	$("#curPages").val(curPage);
	$("#curPageNos").val(curPage);
	var actionValue=document.getElementById('towForm').action.split("/");
	if(actionValue.length>0&&actionValue[actionValue.length-1]=='queryVdriverCarInfo'&&curPages>10&&$("#userId").val()!=undefined&&$("#userId").val()!=""){
		alert("请您使用查询条件精确查看车源");
	}else{
		document.getElementById('towForm').submit();
	}
	return;
}

//直接到达页面
function getGoTwo() {
	var pageGo = $("#pageGo").val();//要显示的页数
	if(trim(pageGo) == '') {
		return false;
	}else {
		var reg = new RegExp("^[0-9]*$");
		if(!reg.test(trim(pageGo))){
		    return false;
		}
	}
	$("#curPages").val(pageGo);  
	$("#curPageNos").val(pageGo);
	var actionValue=document.getElementById('towForm').action.split("/");
	if(actionValue.length>0&&actionValue[actionValue.length-1]=='queryVdriverCarInfo'&&pageGo>10&&$("#userId").val()!=undefined&&$("#userId").val()!=""){
		alert("请您使用查询条件精确查看车源");
	}else{
		document.getElementById('towForm').submit();
	}
	return;
}

//鼠标放上
function getOnmouseover(myId) {
	$("#tr"+myId).addClass("selected");
}

//鼠标拿下
function getOnmouseout(myId) {
	$("#tr"+myId).removeClass("selected");
}