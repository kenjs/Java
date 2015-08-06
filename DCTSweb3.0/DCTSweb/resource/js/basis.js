/**
 * 去除字符串前后的空格
 * @param str 原字符串
 * @returns 
 */
function trim(str){
	return str.replace(/(^\s*)|(\s*$)/g,"");
}

//省市区拆分并赋值给hidden
function setProvinceCityCounty(provCityCounty,proviceId,cityId,countyId){
	
	var provCityCountys=provCityCounty.split("-");
	if(provCityCountys.length>0){
	$("#"+proviceId).val(provCityCountys[0]);
	if(provCityCountys.length==2){
		$("#"+cityId).val(provCityCountys[1]);
	}else if(provCityCountys.length==3){
		$("#"+cityId).val(provCityCountys[1]);
		$("#"+countyId).val(provCityCountys[2]);
	}
 }
}

//查询的地址去掉‘-’
function provinceCityCountyFormat(serarchProCityCounty,proCityCountyId){
	if(serarchProCityCounty.length==1){
        $("#"+proCityCountyId).val(serarchProCityCounty[0]);
      }else if(serarchProCityCounty.length==2){
        $("#"+proCityCountyId).val(serarchProCityCounty[0]+serarchProCityCounty[1]);
      }else if(serarchProCityCounty.length==3){
         $("#"+proCityCountyId).val(serarchProCityCounty[0]+serarchProCityCounty[1]+serarchProCityCounty[2]);
      }
}

//父页面链接新页面，关闭art.dialog
function pageJump(url){
 art.dialog.open.origin.location.href=url;
 art.dialog.close();
}

//弹框
function artDialogOpenInfo(url,width,height){
    art.dialog.open(url,{id:'tranDetailId',width:width,height:height,lock:true,drag:true},false);
}
//链接到新页面
function locationHref(url){
	location.href=url;
}
//刷新原页面
function reload(){
	location.reload();
}
//1.后台数据库交互操作(回调函数1.成功后调方法，2.没有登录主页跳到登录页面，3.出错后弹错误框提示)
function execDatabaseInteractionHandle(url,dataParameter,okFunc){//方法作为参数
   $.ajax({
				url: url,   
				type:'post',	
				dataType:'json', 
				data:dataParameter,      	               
				success:function(data){//回传函数
					if(data.result == 0) {//成功
						okFunc();
						
					}else if(data.result == 1){//未登录
						location.href=jcontextPath+'/dcts/user/login.jsp';
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


//2.后台数据库交互操作(回调函数1.成功后art.dialog.open()弹出成功层，2.没有登录主页跳到登录页面，3.出错后弹错误框提示)
function execDatabInteraSuccesDialogOpen(url,dataParameter,successUrl){
	$.ajax({
        cache: true,
        type: "POST",
        url:url,
        data:dataParameter,
        async: false,
        dataType:'json', 
        error: function(request) {
        },
        success: function(data) {
			  if(data.result=='0'){//成功
			   art.dialog.open(successUrl,{width:437,height:228,lock:true,drag:true});//drag 是否允许用户拖动
			  }else if(data.result=='1'){//未登录
			     location.href=jcontextPath+"/index.jsp";
			  }else if(data.result=='2'){//出错
			  art.dialog({
				  time:3,
			      icon: 'error',
                  content: data.errorMessage
      		  });
			  }
			}
        
    });
	
}
//清空提示
function cleanContext(id){
  $("#"+id).html("");
}

/**
 * 弹框提示消息（2秒后自动关闭）
 * @param content 提示信息
 * @param icon 图标
 */
function artDialogInfo(time,content,icon){
art.dialog({
  time:time,
  icon: icon,
  content: content
});
}


//收藏司机
function collectionDriver(url,dataParameter,collectionId){
    $.ajax({
				url: url,   
				type:'post',	
				dataType:'json', 
				data:dataParameter,      	               
				success:function(data){//回传函数
					if(data.result == 0) {//成功
						//art.dialog.tips("成功收藏",2);
						
						artDialogInfo(2,'成功收藏','succeed');
					}else if(data.result == 1){//未登录
					    pageJump('<sys:context/>/dcts/user/login.jsp');
					}else {//出错（例：参数为空）
						artDialogInfo(3,data.errorMessage,'face-smile');
					}
				}
			});
}

 

/**
* 根据登录用户id查询评价
* @return
*/
function webUserAppraiseCount() {
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/queryDriverUserAssessCount",
	   	dataType:"json",
	   	//data:{driverId:driverId}, //参数     	
		success:function(data){//回传函数
	   		if(data.result == 1) {
	   		}else if(data.result == 0) {
	   			var dataObj = data.content;
	   			var assessThree = dataObj.satisfactory;
	   			var assessSix = dataObj.arial;
	   			var assessNine = dataObj.noSatisfactory;
	   			if(assessThree == '' || assessThree == null) {
	   				assessThree = '0';
	   			}
	   			if(assessSix == '' || assessSix == null) {
	   				assessSix = '0';
	   			}
	   			if(assessNine == '' || assessNine == null) {
	   				assessNine = '0';
	   			}
	   			appraiseCountHtml = '<span><i class="icon7">&nbsp;</i>好评('+assessThree+')</span>'+
	                				'<span><i class="icon8">&nbsp;</i>中评('+assessSix+')</span>'+
	                				'<span><i class="icon9">&nbsp;</i>差评('+assessNine+')</span>';
	   			$("#webUserNameId").html(dataObj.cargoName);
	   			$("#webUserAppraiseCountId").html(appraiseCountHtml);
	   		}
	   	}
	});
}

//弹框提示信息
function emagessPromptInfo(titlehtml,contenthtml,time){
	art.dialog({
		width:400,
		height:100,
		id: 'shake-demo—Id',
		title: titlehtml,
		time: time,
		content: contenthtml,
		lock: true,
		cancel:false
	});
}

//(1)车找货、(2)司机报价后点击后面的‘在线订车’、(3)订单详情页确认收货成功后提醒司机，百度云推送消息 20140715 PM 

function baiduPushMessagesToDriverTwo(channelId,userId,type){
	if(channelId==null||channelId==""||userId==null||userId==""){
		return;
	}
		   $.ajax({
						url: jcontextPath+'/pushMessageToDriver',   
						type:'post',	
						dataType:'json', 
						data:{'driverUserInfoDomain.baiduChannelId':channelId,'driverUserInfoDomain.baiduUserId':userId,'type':type},      	               
						success:function(data){//回传函数
						}
					});
		
	}

