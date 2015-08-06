  //表单验证
	function checkAddInfo() {
	
		//发货时间 
		var requestStartTimes = $("#requestStartTime").val();
		if(trim(requestStartTimes) == "") {
			$("#requestStartTimeHtmlId").html("装货时间不能为空！");
			$("#requestStartTimeMpt").show();
			return false;
		}
		//当前时间
		var todatas=new Date(getDateFormat().replace(/\-/g, "\/"));
		var startTimes = new Date(requestStartTimes.replace(/\-/g, "\/"));
		if(startTimes<todatas){
			$("#requestStartTimeHtmlId").html("装货时间不能小于当前时间！");
			$("#requestStartTimeMpt").show();
			return false;
		}
		
		//卸货时间
		var requestEndTimes = $("#requestEndTime").val();
		if(trim(requestEndTimes) != ""){
		var endTimes = new Date(requestEndTimes.replace(/\-/g, "\/")); 
		if(endTimes<startTimes){
			$("#requestEndTimeHtmlId").html("卸货时间不能小于装货时间！");
			$("#requestEndTimeMpt").show();
			return false;
		}
		}
	  
	  //装货地
	  var startProCityCounty=trim($("#startProCityCounty").val());
		if( startProCityCounty== ""){
			$("#startProCityCountyHtmlId").html("装货地不能为空！");
			$("#startProCityCountyMpt").show();
			return false;
		}else{
		  var spcc= startProCityCounty.split("-");
		  if(spcc.length<2){
		    $("#startProCityCountyHtmlId").html("装货地省市必填！");
			$("#startProCityCountyMpt").show();
			return false;
		  }else{
		     setProvinceCityCounty(startProCityCounty,"startProvinceId","startCityId","startCountyId");
		  }
		}
		
		//卸货地
		var endProCityCounty=trim($("#endProCityCounty").val());
		if( endProCityCounty== ""){
			$("#endProCityCountyHtmlId").html("卸货地不能为空！");
			$("#endProCityCountyMpt").show();
			return false;
		}else{
		  var epcc= endProCityCounty.split("-");
		  if(epcc.length<2){
		    $("#endProCityCountyHtmlId").html("卸货地省市必填！");
			$("#endProCityCountyMpt").show();
			return false;
		  }else{
		  setProvinceCityCounty(endProCityCounty,"endProvinceId","endCityId","endCountyId");
		  }
		}
		
		//货物名称(不是必输的限定输入的长度为10个字符)
		var cargoName = $("#cargoName").val();//货物名称
	    if(cargoName.length>10){
	    	$("#cargoNameHtmlId").html("货物名称长度需在10个字符以内");
			$("#cargoNameMpt").show();
			return ;
	    }
		
		var cargoWeight = $("#cargoWeight").val();//货物重量
		var cargoCubage = $("#cargoCubage").val();//货物体积
		if(trim(cargoWeight) == "") {
			if(trim(cargoCubage) == "") {
				$("#cargoWeightCubageHtmlId").html("货物重量或货物体积必填一个！");
				$("#cargoWeightCubageMpt").show();
				return false;
			}
		}
		
		
		//联系人 
		var contactNames = $("#contactName").val();
		if(trim(contactNames) == "") {
			$("#contactNameHtmlId").html("联系人不能为空！");
			$("#contactNameMpt").show();
			return false;
		}
		
		//手机号和固定电话
		var contactTelephones = $("#contactTelephone").val();//固定电话
		var contactMobilephone = $("#contactMobilephone").val();//手机号
		if(trim(contactTelephones) == ""&&trim(contactMobilephone) == "") {
			$("#contactTelephoneHtmlId").html("手机号和固定电话必填一个！");
			$("#contactTelephoneMpt").show();
			return false;
		}else{
		  if(trim(contactMobilephone) !=""&&!bilenumber(contactMobilephone)){
			  $("#contactMobilephoneHtmlId").html("请填写正确的手机号！");
			  $("#contactMobilephoneMpt").show();
		     return false;
		  }
		  //alert(contactTelephones);
		  if(trim(contactTelephones) !=""){
			  if(testit(contactTelephones)||checkphone(contactTelephones)){
		   
			  }else{
				  $("#contactTelephoneHtmlId").html("请填写正确的固定电话（需加区号）！");
				  $("#contactTelephoneMpt").show();
				  return false;
			  }
		  }
		}
		
		return true;
	}
	
	//必填项得到焦点时隐藏错误提示层并清空内容
	function getOnfucts(str) {
	    var newStr=str.substring(0, str.length-3)+"HtmlId";
	    $("#"+str).hide();
		$("#"+newStr).html("");
	}
	//手机号码文本框得到焦点时
	function getMobilephone() {
		//手机号错误提示层
	    $("#contactMobilephoneMpt").hide();
		$("#contactMobilephoneHtmlId").html("");
		//电话错误提示层
		 $("#contactTelephoneMpt").hide();
		 $("#contactTelephoneHtmlId").html("");
	}