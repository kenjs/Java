   function getDeliveryFlag(){
   		var deliveryFlag = $("#deliveryFlag").val();
   		if(deliveryFlag == "0" || deliveryFlag == "") {
   			$("#deliveryFlag").val("1");
   			$("#deliveryFlag").attr("checked");
   		}
   		if(deliveryFlag == "1") {
   			$("#deliveryFlag").val("0");
   			$("#deliveryFlag").attr("checked",true);
   		}
   }
   
   function getArrivalSure(){
   		var  arrivalSure = $("#arrivalSure").val();
   		if(arrivalSure == "0" || arrivalSure == "") {
   			$("#arrivalSure").val("1");
   			$("#deliveryFlag").attr("checked");
   		}
   		if(arrivalSure == "1") {
   			$("#arrivalSure").val("0");
   			$("#deliveryFlag").attr("checked",true);
   		}
   }
   
   function getReceiveSure(){
   		var receiveSure = $("#receiveSure").val();
   		if(receiveSure == "0" || receiveSure == "") {
   			$("#receiveSure").val("1");
   			$("#deliveryFlag").attr("checked");

   		}
   		if(receiveSure == "1") {
   			$("#receiveSure").val("0");
   			$("#deliveryFlag").attr("checked",true);

   		}
   }
   
   function returnQuery(){
	  	var userType = $("#userType").val();
	  	var menuAId = $("#menuAIdHi").val();
	  	location.href=jcontextPath+"/querySonWebUserInfo?userType="+userType+"&menuAId="+menuAId;
	  }
   
   //提交添加表单
   function getAddSubmits() {
	     if(!checkAddForms()){
	       return false;
	     }
	    
		 document.getElementById('mainForm').submit();
	  }
   
 //提交添加表单
   function getUpdateSubmits() {
	  
	     if(!checkUpdateForms()){
	       return false;
	     }
	    
		 document.getElementById('mainUpdateForm').submit();
	  }
   
   //修改客户信息表单验证
   function checkUpdateForms(){
		 //公司地址
		  var companyPcc=$.trim($("#companyPcc").val());
		  if(companyPcc == null || companyPcc == ""){
		     $("#companyPccMpt").show();
			 $("#companyPccNotEmpt").show();//显示
			 return false;
		  }
		 //联系人
		  var contactName=$.trim($("#contactName").val());
		 if(contactName == null || contactName == ""){
		     $("#contactNameMpt").show();
			 $("#contactNameNotEmpt").show();//显示
			 return false;
		  }
		  
		 //联系电话      
		 var contactTelephone=$.trim($("#contactTelephone").val());
		  if(contactTelephone == null || contactTelephone == ""){
		     $("#contactTelephoneMpt").show();
			 $("#contactTelephoneNotEmpt").show();//显示
			 return false;
		  }else{
		    if(bilenumber(contactTelephone)||testit(contactTelephone)||checkphone(contactTelephone)){
				  
			  }else{
			    $("#contactTelephoneMpt").show();
				$("#contactTelephoneError").show();
			     return false;
			  }
		  }
		  return true;
	   }
  //添加表单验证 
   function checkAddForms(){
	      //用户名
			var code = $.trim($("#code").val());
	   		if(code == null || code == "" || code == "请输入用户名"){//不能为空
	   			$("#divcodezhu").show();
	   			$("#divCodeNull").show();//显示
				return false;
	   		}
	   		if(code.length<3 || code.length>20) {//判断位数3到20位
	   			$("#divcodezhu").show();
	   			$("#divCodeLength").show();//显示
	   			return false; 
	   		}
	   		if (!code.match(/^[a-zA-Z0-9_]{1,}$/)){//用户名只能由字母数字下划线组成
	   			$("#divcodezhu").show();
	   			$("#divCodeLetter").show();//显示
	    		return false;  
	  		}  
	   		if(code != null && code != ""){
	   			$.ajax({
					url: jcontextPath + "/queryWebUserInfoCode",   
					type:'post',	
					dataType:'json', 
					data:{code:code}, //参数     	               
					success:function(data){//回传函数
						if(data.result == 1) {
							$("#divcodezhu").show();
							$("#divCodeCyi").show();//显示
							return false;
						}
					}
				});
	   		}
	   		
		//密码
			var password = $.trim($("#password").val());
			if(password == null || password == ""){//不能为空
				$("#divPasswordzhu").show();
	   			$("#divPasswordNull").show();//显示
				return false;
	   		}
	   		if(password.length<6 || password.length>20) {//判断位数6到20位
	   			$("#divPasswordzhu").show();
	   			$("#divPasswordLength").show();//显示
	   			return false; 
	   		}
	   		
	   		
		//两次密码
			var password = $.trim($("#password").val());
			var passwordes = $.trim($("#passwordes").val());
			if(password == null || password == ""){//不能为空
				$("#divPasswordzhu").show();
	   			$("#divPasswordNull").show();//显示
				return false;
	   		}
	   		if(passwordes != password) {
	   			$("#divPasswordeszhu").show();
	   			$("#divPasswordes").show();//显示
	   			return false;
	   		}
	   		
		//公司名称
			var companyName = $.trim($("#companyName").val());
			if(companyName == null || companyName == "" || companyName == "请输入企业名称"){//不能为空
				$("#divCompanyNamezhu").show();
	   			$("#divCompanyName").show();//显示
				return false;
	   		}
	   		var userType = $.trim($("#userType").val());
	   		$.ajax({
					url: jcontextPath + "/queryCompanyByName",   
					type:'post',	
					dataType:'json', 
					data:{companyName:companyName,userType:userType}, //参数     	               
					success:function(data){//回传函数
						if(data.result == 1) {
							$("#divCompanyNamezhu").show();
							$("#divCompanyNameCyi").show();//显示
							return false;
						}
					}
			});
		   if(!checkUpdateForms()){
			   return false;
		   }
		  return true;
	   }
   
	   //修改时:提示信息的隐藏
      function on_focus_update(str){
    	  if(str=="companyPcc"){
    			 $("#companyPccMpt").hide();
    			 $("#companyPccNotEmpt").hide();
    			}
    			if(str=="contactName"){
    			$("#contactNameMpt").hide();
    			$("#contactNameNotEmpt").hide();
    			}
    			if(str=="contactTelephone"){
    			  $("#contactTelephoneMpt").hide();
    			  $("#contactTelephoneError").hide();
    			  $("#contactTelephoneNotEmpt").hide();
    			}
      }
      //添加:提示信息的隐藏
	  function on_focus(str){
		if(str == "code") {
			$("#divcodezhu").hide();
			$("#divCodeCyi").hide();//隐藏
			$("#divCodeNull").hide();//隐藏
			$("#divCodeLength").hide();//隐藏
			$("#divCodeLetter").hide();//隐藏
		}
		if(str == "password") {
			$("#divPasswordzhu").hide();
			$("#divPasswordNull").hide();//隐藏
			$("#divPasswordLength").hide();//隐藏
		}
		if(str == "passwordes") {
			$("#divPasswordeszhu").hide();
			$("#divPasswordes").hide();//隐藏
		}
		if(str == "companyName") {
			$("#divCompanyNamezhu").hide();
			$("#divCompanyName").hide();//隐藏
			$("#divCompanyNameCyi").hide();//隐藏
		}
		on_focus_update(str);
		//$("#divCodeNumberzhu").hide();
		//$("#divContentFormat").hide();//隐藏		
	}
	 
	//重置密码
	  function restPw(id){//方法作为参数
		  
		  var dialog = art.dialog({
			    content:'确认为该子账号重置密码？',
			    lock:true,
			    fixed: true,
			    id: 'Fm2',
			    icon: 'question',
			    ok: function () {
			    	restPwHandle(id);
			    },
			    cancel: true
			  });
		}
	//重置密码后台操作
	  function restPwHandle(id){
		   $.ajax({
				url: jcontextPath+'/updatePwdAction',   
				type:'post',	
				dataType:'json', 
				data:{'userId':id,pwd:'000000'},      	               
				success:function(data){//回传函数
					if(data.result == 0) {//成功
						  art.dialog({
							    time:3,
							    icon: 'succeed',
							    content: '密码成功重置为：000000,请及时通知该子账户' 
							});
						
					}else if(data.result == 1){//未登录
						location.href=jcontextPath+'/dcts/user/login.jsp';
					}else {//出错（例：参数为空）
					  art.dialog({
						    time:3,
						    icon: 'error',
						    content: '密码重置失败' 
						});
					}
				}
			});
	  }
   