function getSubmit(){
      document.getElementById('mainForm').submit();
}

function getRealPageInfo(str) {
	$("#curPage").val(str);
	getSubmit();
}

function clientPageInfo(totalPages,curPageNos,totalRecords) {
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