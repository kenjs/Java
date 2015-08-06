 
  //确认装货和确认到货
  function transactionAlertDialogPrompt(currentStart,contents,url){
	 if(currentStart=='3'){
		 contents="担任此次运输的司机还未卸货,您确认货物已送达？";
	 }
   var dialog = art.dialog({
    content:contents,
    lock:true,
    fixed: true,
    id: 'Fm2',
    icon: 'question',
    ok: function () {
    	pageJump(url);
    },
    cancel: true
  });
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
  
  
  
  