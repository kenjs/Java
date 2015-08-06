//鼠标放到div区域得到光标触发事件
function mcancelclosetime(id) {
	// alert("1");
	$("#Layer" + id).css("border", "1px solid #a0cde0");
}
function mclosetime(id) {
	$("#Layer" + id).css("border", "1px solid #fafafa");
}

function focusinput(obj) {
	if (obj.value == "请输入运单号") {
		obj.value = "";
	}
	obj.style.border = "1px solid #a0cde0";
	markText("#waybillnumber","");
}

function mouseoutinput(obj) {
	if (obj.value == "") {
		obj.value = "请输入运单号";
	}
	obj.style.border = "1px solid #a0cde0";
}

function opens(str) {
	window.location.href=str;
}
$.ajax({
	url:"../logincs/getInit?random="+Math.random(),
	dataType:"json",
	contentType: "application/json",
  	type:'get',
  	success:function(data){
		$('#realname').html(data.realname);
		$('#realnameSuffix').show();
		$('#email').html(data.email);
		$('#mobilenumber').html(data.mobilenumber);
  	},
  	error:function(){
  		//window.location = '../logincs/login';
  	}
});

//昨日订单发货统计
$.ajax({
	url:"../waybillcs/selectYestodayCount",
	dataType:"json",
  	type:'post',
  	data:"",
  	success:function(data){
		$('#yestodayCount').html(data.yestodayCount);
  	}
});
//本月订单发货统计
$.ajax({
	url:"../waybillcs/selectMouthCount",
	dataType:"json",
  	type:'post',
  	data:"",
  	success:function(data){
		$('#mouthCount').html(data.mouthCount);
  	}
});

function f_select(){
	var waybillnumber = trim($("#waybillnumber").val());
	if(waybillnumber == '请输入运单号' || waybillnumber == '') {
		markText("#waybillnumber","请输入运单号");
	}else {
		$.ajax({
			url: "../waybillcs/selectWaybillNumber",
		 	type:'post',	
		 	dataType:'json', 
		 	data:{waybillnumber:waybillnumber}, //参数     	               
		   	success:function(data){
		 		if(data.id == 'NO') {
		 			markText("#waybillnumber","运单不存在");
		 		}else{
		 			var url = "../waybillcs/waybill_detail?order=1&waybillid="+data.id;
		 			//window.open(url,"_blank");
		 			window.location.href = url;
		 		}
		 	}
		});
	}
}

function markText(targetId,message){
	if(message==""){
		$(targetId+"_message").hide();
		return false;
	}else{
		var _targetId=targetId.substr(1);//去掉#
		if($(targetId+"_message").length==0){//当信息提示div不存在时
			var top=$(targetId).position().top + parseInt($(targetId).css("height"))+10;
			var left=$(targetId).position().left;
			var txt='<div id="'+_targetId+'_message" style="border:0px;color:white;font-size:12px;background-color:red;margin:0px;position:absolute;top:'+top+'px;left:'+left+'px">'+
			message+'</div>';
			$("body").append(txt);
		}else{//当信息提示div存在时
			$(targetId+"_message").html(message);
			$(targetId+"_message").show();
		}
		$(targetId).css("border","2px solid red");
		return targetId+"_message";
	}
}

function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}	
