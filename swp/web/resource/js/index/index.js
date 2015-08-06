//ajax请求查询历史导入的未匹配成功的有效货源(未用到)
function historyImportInfo(mark){
	
	 $.ajax({
			url: jcontextPath + "/queryHistoryImportInfo.jspx",  
			type:'post',	
			dataType:'json',
			data:{'mark':mark},
			success:function(data){//回传函数
				if(data.result == 0) {//成功
					var cargoObj=data.content;
					var listObj=cargoObj.list;
					var divObj=$("#historyInfoId");
					var pageDivObj=$("#towPageId");
					var htmls='';
					var pageHtml='';
					for(var i=0;i<listObj.length;i++){
						var regCompanyVal="";
						if(listObj[0].regCompanyId==""){
							regCompanyVal = "未注册";
						}else{
							regCompanyVal="已注册";
						}
						//主页的列表内容
						htmls+='<h3>'+
							   '<table border="0" cellpadding="0" cellspacing="0">'+
							   '<tr onmousedown="onmousedownTr(\''+listObj[0].contactMobilephone+'\',1,\'hcargoninfoid'+i+'\');">'+
							   '<td width="50">'+(i+1)+'</td>'+
							   '<td width="220">'+listObj[0].companyName+'</td>'+
							   '<td width="90">'+listObj[0].contactName+'</td>'+
							   '<td width="110">'+listObj[0].contactMobilephone+'</td>'+
							   '<td width="80">'+listObj[0].importCount+'</td>'+
							   '<td width="120">'+regCompanyVal+'</td>'+
							   '<td width="90"><a href="'+jcontextPath+'/openCompanyFeedbackRecord.jspx?contactMobilephone='+listObj[0].contactMobilephone+'&mark=1">配车登记</a></td>'+
							   '</tr>'+
							   '</table></h3>'+
							   '<ul><li id="hcargoninfoid'+i+'">'+
							   '</li></ul>';
					}
					htmls+='</table>';
					divObj.html(htmls);
					
					//分页代码
					if(listObj.length>0){
						pageHtml+='<div class="numberBox" id="pageInfoHtmlIds"></div>';
					}
					pageHtml+='<input type="hidden" id="curPages" name="pageInfo.curPage" value="'+cargoObj.pageInfo.curPage+'"/>'+
						      '<input type="hidden" id="curPageNos" name="pageInfo.curPageNo" value="'+cargoObj.pageInfo.curPageNo+'"/>'+
						      '<input type="hidden" id="orderCargoInfoDomainObj.pageInfo.pageSize" name="pageInfo.pageSize" value="'+cargoObj.pageInfo.pageSize+'"/>';
					pageDivObj.html(pageHtml);
					
					var totalPages = cargoObj.pageInfo.totalPages;//总页数
					var curPageNos = cargoObj.pageInfo.curPageNo;//当前页数
					var pageSize = cargoObj.pageInfo.pageSize;//每页显示数据
					var totalRecords = cargoObj.pageInfo.totalRecords;//总记录数
					pageInfoTwo(totalPages,curPageNos,totalRecords);
					
				}else if(data.result == 1){//未登录
					location.href=jcontextPath+'/swp/login.jsp';
				}else {//出错（例：参数为空）
				  art.dialog({
					    time:3,
					    icon: 'error',
					    content: data.errorMessage 
					});
				}
			}
		});
	
}


//根据公司号码查询未匹配的货源
function onmousedownTr(contactMobilephone,mark,ulId){
    	 $.ajax({
				url: jcontextPath + "/queryTodayImportCargoByPhone.jspx",  
				type:'post',	
				dataType:'json', 
				data:{'contactMobilephone':contactMobilephone,'mark':mark},      	               
				success:function(data){//回传函数
					if(data.result == 0) {//成功
						var tradeListObj=data.content;
						var liObj=$("#"+ulId);
						liObj.html(tradeListObj);
					}else if(data.result == 1){//未登录
						location.href=jcontextPath+'/swp/login.jsp';
					}else {//出错（例：参数为空）
					  art.dialog({
						    time:3,
						    icon: 'error',
						    content: data.errorMessage 
						});
					}
				}
			});
     }

//统计某个营销专员今天导入的货源总条数
function countTodayImportCargos(){
	$.ajax({
		url: jcontextPath + "/countTodayImportCargo.jspx",  
		type:'post',	
		dataType:'json',   	               
		success:function(data){//回传函数
			if(data.result == 0) {//成功
				var liObj=$("#countImportId");
				var result=data.errorMessage;
				var resultArray=result.split(",");
				var context="今日导入成功货源数：<font color='red'>"+resultArray[0]+"</font>";
				if(resultArray[1]!=0){
					context+="(含已联系货源数：<font color='red'>"+resultArray[1]+"</font>)";
				}
				liObj.html(context);
			}
		}
	});
}

//联系登记
function lianxidjcargo(contactMobilephone,mark) {
	art.dialog.open(jcontextPath + '/swp/cargo/CompanyFeedbackRecord.jsp?contactMobilephone=' + contactMobilephone + '&mark='+mark,
                {id: 'N3690', title: '联系登记', width: 870, height: 650, lock: true, close: function () {
                    if (this.close) {
                        art.dialog.open.origin.$('#mainForm')[0].reset();
                    }
                }}
    );
} 


//关闭所有对话框
function closeUp() {
	var list = art.dialog.list;
	for (var i in list) {
    	list[i].close();
	};
}


	