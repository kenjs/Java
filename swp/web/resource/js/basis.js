/**
 * 去除字符串前后的空格
 * @param str 原字符串
 * @returns 
 */
function trim(str){
	return str.replace(/(^\s*)|(\s*$)/g,"");
}

//营销平台20141017
function getSubmit(flag){
	 document.getElementById('mainForm').submit();
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

//关闭当前弹框
function closeArtDialog(){
	art.dialog.close();
}

//刷新主页面，关闭子页面
function closeDialogAndReloadOrigin(){
	//关闭当前art,刷新主页面
	 art.dialog.open.origin.reload();
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

//继续发布（刷新父页面，关闭art.dialog）
function continuePublish(id){
   //art.dialog.open.origin 表示父窗体
   art.dialog.open.origin.$('#'+id)[0].reset();
   art.dialog.close();
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
					    pageJump('<sys:context/>/swp/login.jsp');
					}else {//出错（例：参数为空）
						artDialogInfo(3,data.errorMessage,'face-smile');
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


function artDialogPrompts(contenthtml){
	  	art.dialog({
	   		//icon:'succeed',
	   		width:400,
	   		height:100,
	   		id: 'message_id',
	   		content: contenthtml,
	   		lock: true,
	   		cancel:false
	   	});
}

//百度推送消息

function baiduPushMessagesToDriverTwo(channelId,userId,type){
	if(channelId==null||channelId==""||userId==null||userId==""){
		return;
	}
	$.ajax({
		url: jcontextPath+'/pushMessageToDriver.jspx',   
		type:'post',	
		dataType:'json', 
		data:{'baiduChannelId':channelId,'baiduUserId':userId,'type':type},      	               
		success:function(data){//回传函数
		}
	});
		
}

//删除记录（货源），取消会员（即将VIP修改为普通会员）
function deleteInfo(content,url,pramater,okFunc){
 var dialog = art.dialog({
  content:content,
  lock:true,
  fixed: true,
  id: 'Fm2',
  icon: 'question',
  ok: function () {
  	execDatabaseInteractionHandle(url,pramater,okFunc)
  },
  cancel: true
});
}

