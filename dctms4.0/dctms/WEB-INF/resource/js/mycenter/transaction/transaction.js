/**
 * 百度云推送消息
 */
function baiduPushMessagesToDriver(channelId,userId,okFunc){
	if(channelId==null||channelId==""||userId==null||userId==""){
		artDialogInfo(2,"参数不能为空","error");
		return;
	}
	execDatabaseInteractionHandle(jcontextPath+'/pushMessageToDriver',{'driverUserInfoDomain.baiduChannelId':channelId,'driverUserInfoDomain.baiduUserId':userId,'type':'1'},okFunc);
	}

//百度推送成功web端提示
function successPromptDriverInfo(){
	art.dialog({
		  time:2,
		  icon: 'succeed',
		  content: '成功提醒司机!'
		});
}

//取消订单(我的订单列表和订单详情页,个人中心的首页)您确定要取消订单吗？取消后不能恢复!
 function cancleTranceDialog(id,cargoFlag,tradeStart,okFunc,userDriverAssessCount){
	 var content='<div class="newf" style="width:400px;"><i style="float:left;">&nbsp;</i>'+
		 '取消订单后，该订单就无法继续交易，但是您可以在我的货源中再次定 &nbsp;&nbsp; &nbsp;&nbsp;车或者重新发布该货源，是否确定取消？</div>'+
	 '<form id="cancleAssessForm" action="" namespace="/" method="post">'+
	 '<input type="hidden" id="tranId" value="'+id+'" name="transactionInfoDomain.id"/>'+
     '<input type="hidden" id="cargoFlag" value="'+cargoFlag+'" name="transactionInfoDomain.cargoFlag"/>'+
     '<input type="hidden" id="tradeStart" value="'+tradeStart+'" name="transactionInfoDomain.tradeStart"/>'+
     '<input type="hidden" id="userDriverAssessCount" value="'+userDriverAssessCount+'" name="transactionInfoDomain.userDriverAssessCount"/>'+
     '<h4 class="tilef">请您选择取消订单的原因：</h4><b id="promptId" style="color:red"></b>'+
     '<dt><textarea style="width: 245px;height: 50px;" name="" id="textareaId" onfocus=cleanContext("promptId") ></textarea></dt>';
     
	 if(userDriverAssessCount==0){
		 content+= '<div style="border-top: dotted 1px #666;margin-top: 15px;">'+
	     '<ul>'+
	 	  '<li>'+
	    ' <table border="0" cellpadding="0" cellspacing="0">'+
	     '	<thead>'+
	     '	<tr >'+
	         	'<td colspan="3"><h4 class="tilef" >请给司机做出评价:</h4></td>'+
	         '</tr>'+
	         '</thead>'+
	         '<tr>'+
	         	'<td><input name="userDriverAssessInfo.tradeEvaluateScore" checked type="radio" value="3" />好评<i class="icon7">&nbsp;</i></td>'+
	            ' <td><input name="userDriverAssessInfo.tradeEvaluateScore" type="radio" value="6" />中评<i class="icon8">&nbsp;</i></td>'+
	            ' <td><input name="userDriverAssessInfo.tradeEvaluateScore" type="radio" value="9" />差评<i class="icon9">&nbsp;</i></td>'+
	         '</tr>  '+
	     '</table>'+
	     '</li>'+
	     '<li><b id="transactionAreaID" style="color: red;"></b></li>'+
	    ' <li><textarea style="width: 245px;height: 50px;" name="userDriverAssessInfo.assess" onfocus="cleanContext(transactionAreaID)" id="assessId" cols="" rows=""></textarea></li>'+         
	 '</ul>'+
	 '</div>';
	 }
	content+='</form>';
   var dialog = art.dialog({
   content:content,
   fixed: true,
   lock:true,
   id: 'Fm1',
   //icon: 'question',
   ok: function () {
  	var remark = document.getElementById('textareaId').value;//取消原因
  	if(remark==""){
  	  	 $("#promptId").html("请填写取消订单的原因！");
  	  	 return false;
  	 }
  	//货主对司机还没有评价过，可评价
  	if(userDriverAssessCount==0){
  		var zz=$("input[name='userDriverAssessInfo.tradeEvaluateScore']")[2];
  		 var transactionArea=$("#assessId")[0].value;//评语
  	  	
  	  	//差评必填评语
  	  	if(zz.checked&&transactionArea==""){
  	 	   $("#transactionAreaID").html("'差评'必填评价原因");
  	 	   return false;
  	 	}
  	}
  	var dataParameter=$("#cancleAssessForm").serialize();
  	//var dataParameter={'transactionInfoDomain.id':id,'transactionInfoDomain.cargoFlag':cargoFlag,'transactionInfoDomain.tradeStart':tradeStart,'transactionInfoDomain.remark':remark,'userDriverAssessInfo.tradeEvaluateScore':assessScoreValue,'userDriverAssessInfo.assess':transactionArea};
  	var url=jcontextPath + "/cancleTransaction";
  	execDatabaseInteractionHandle(url,dataParameter,okFunc);
  	
  },
  cancel: true
});
}
 

//评价
  function userDriverAssessInfo(tranId,driverId,cargoId){
    var contents = '<form id="assessForm" action="" namespace="/" method="post">'+
      '<input type="hidden" id="tranId" value="'+tranId+'" name="userDriverAssessInfo.transactionId"/>'+
      '<input type="hidden" id="driverId" value="'+driverId+'" name="userDriverAssessInfo.driverId"/>'+
      '<input type="hidden" id="cargoId" value="'+cargoId+'" name="userDriverAssessInfo.cargoId"/>'+
        '<div class="evalua" id="first" name="first">'+
        	'<h3><i>&nbsp;</i>货物已经送到了吗？快快给司机的服务进行评价吧！<br/>(温馨提示：请您及时给司机做出评价，系统将在30天后默认好评)</h3>'+
            '<div class="fl flua">'+
            '<ul>'+
            	'<li>'+
                '<table border="0" cellpadding="0" cellspacing="0">'+
                	'<thead>'+
                	'<tr>'+
                    	'<td>好评</td>'+
                        '<td>中评</td>'+
                        '<td>差评</td>'+
                    '</tr>'+
                    '</thead>'+
                    '<tr>'+
                    	'<td><input name="userDriverAssessInfo.tradeEvaluateScore" checked type="radio" value=3 /><i class="icon7">&nbsp;</i></td>'+
                        '<td><input name="userDriverAssessInfo.tradeEvaluateScore" type="radio" value=6 /><i class="icon8">&nbsp;</i></td>'+
                        '<td><input name="userDriverAssessInfo.tradeEvaluateScore" type="radio" value=9 /><i class="icon9">&nbsp;</i></td>'+
                    '</tr> '+
                '</table>'+
                '</li>'+
                '<li><b id="transactionAreaID" style="color: red;"></b></li>'+
                '<li><textarea name="userDriverAssessInfo.assess" onfocus="cleanContext(transactionAreaID)" id="assessId" cols="" rows=""></textarea></li>'+
                
            '</ul>'+
            '</div>'+
        '</div></form>';
	
	
	var dialog = art.dialog({
   content:contents,
   fixed: true,
   lock:true,
   id: 'Fm1',
   //icon: 'question',
   ok: function () {
  	//评价处理 
  	 var zz=$("input[name='userDriverAssessInfo.tradeEvaluateScore']")[2];
	 var transactionArea=$("#assessId")[0].value;//评语
  	
  	//差评必填评语
  	if(zz.checked&&transactionArea==""){
 	   $("#transactionAreaID").html("'差评'必填评价原因");
 	   return false;
 	}else{
  	var dataParameter=$("#assessForm").serialize();
  	var url=jcontextPath + "/saveUserDriverAssessInfo";
  	okFuncHandle(url,dataParameter);
  	}
  },
  cancel: true
});
  }
  
  //1.1.我的订单和个人中心的首页评价(回调函数1.成功和出错后都弹错误框提示，2.没有登录主页跳到登录页面)
function okFuncHandle(url,dataParameter){
   $.ajax({
				url: url,   
				type:'post',	
				dataType:'json', 
				data:dataParameter,      	               
				success:function(data){//回传函数
					if(data.result == 0) {//成功
					  reload();
					 //art.dialog({
					    //icon: 'succeed',
					  // content: '恭喜您！评价成功' 
					//});
						
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

//个人中心和主页统计一样显示的样式不同（预约订单，待确认收货，待评价订单）
function myCenterTradePromptInfo(){
	$.ajax({
		url: jcontextPath + "/trandeInfoCount",   
		type:'post',	
		dataType:'json',  	               
		success:function(data){//回传函数
			var dataObj=data.content;
        	
			var countTradeHtml = '<li>交易提醒：</li>'+
				                  '<li><a  href="'+jcontextPath+'/querySuccessCloseTransactionInfo?transactionInfoDomain.tradeStart=1&transactionInfoDomain.menuAId=">待确认订单<span class="dius">'+dataObj.waitingDriverTrade+'</span></a></li>'+
									'<li><a href="'+jcontextPath+'/querySuccessCloseTransactionInfo?transactionInfoDomain.tradeStart=3&transactionInfoDomain.menuAId=">待确认收货<span class="dius">'+dataObj.waitingReceivingTrade+'</span></a></li>'+
								  '<li><a href="'+jcontextPath+'/queryTransactionInfo?transactionInfoDomain.menuAId=a_id_5&succeNoAssestrades=5&transactionInfoDomain.cargoId=">待评价订单<span class="dius">'+dataObj.successNoAssessTrade+'</span></a></li>';
			$("#trandePromptId").html(countTradeHtml);
		}
	});  
}

