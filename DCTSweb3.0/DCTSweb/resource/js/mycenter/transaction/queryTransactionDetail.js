 
  //确认装货和确认到货
  function transactionAlertDialogPrompt(currentStart,contents,url,channelId,userId){
	  var receiveTimeHtml='<br/>'+
	  '<b id="receiveTimePrompt" style="color:red"></b><br/>'+
      '收货时间<font color="red">*</font> <input onfocus=cleanContext("receiveTimePrompt")  name="transactionInfoDomain.receiveTime" value="" id="receiveTimeId" readonly="readonly" onClick="WdatePicker()" class="intu"/>';
	  if(currentStart=='3'){
			 contents='担任此次运输的司机还未卸货,您确认货物已送达？';
			          
		 }
	  
   var dialog = art.dialog({
    content:contents+receiveTimeHtml,
    lock:true,
    fixed: true,
    id: 'Fm2',
    icon: 'question',
    ok: function () {
    	//pageJump(url);
     //判断收货时间是否填写
    	var receiveTime=$("#receiveTimeId").val();
    	if(receiveTime==""){
    		 $("#receiveTimePrompt").html("收货时间*必填");
    		 return false;
    	}
    	var urls=url+"&transactionInfoDomain.receiveTime="+receiveTime;
    	pageJumpAndPushMessage(urls,channelId,userId);//20140723 PM 
    },
    cancel: true
  });
  }

  //货主点击确认收货后发推送消息20140723 PM 
  function pageJumpAndPushMessage(url,channelId,userId){
	  pageJump(url);
	  baiduPushMessagesToDriverTwo(channelId,userId,'4');
  }

  //订单详情中货主对司机进行评价保存（一条订单只能评价一次）
  function saveEvaluate(){
	   var zz=$("input[name='userDriverAssessInfo.tradeEvaluateScore']")[2];
	   var transactionArea=$("#assessId")[0].value;
	   if(zz.checked&&transactionArea==""){
	   $("#transactionAreaID").html("'差评'必填原因");
	   return false;
	   }
	   
	   $.ajax({
           cache: true,
           type: "POST",
           url:jcontextPath+"/saveUserDriverAssessInfo",
           data:$('#mainForm').serialize(),
           async: false,
           dataType:'json', 
           error: function(request) {
           },
           success: function(data) {
				  if(data.result=='0'){
					  $("#saveEvaluateId").attr({"disabled":"disabled"});//防止二次提交
					  artDialogInfo(2,"恭喜您！评价成功",'succeed');
					  reload();
				  }else if(data.result=='1'){
				     location.href=jcontextPath+"/index.jsp";
				  }else if(data.result=='3'){
					  artDialogInfo(3,data.errorMessage,'face-smile');
				  }else{
				  artDialogInfo(3,data.errorMessage,'error');
				  }
				}
           
       });
	   //document.getElementById('mainForm').submit();
	   return true;
	 }
  
  
  
  