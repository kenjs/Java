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
	obj.style.border = "1px solid #a1a3a6";
}

function mouseoutinput(obj) {
	if (obj.value == "") {
		obj.value = "请输入运单号";
	}
	obj.style.border = "1px solid #d3d7d4";
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


function f_select(){
	var waybillnumber = $("#waybillnumber").val();
	if(waybillnumber == '请输入运单号' || waybillnumber == '') {
		ymPrompt.alert("请输入运单号!");
	}else {
		$.ajax({
			url: "../waybillcs/selectWaybillNumber",
		 	type:'post',	
		 	dataType:'json', 
		 	data:{waybillnumber:waybillnumber}, //参数     	               
		   	success:function(data){
		 		if(data.id == 'NO') {
		 			ymPrompt.alert("找不到结果，此运单号不存在!");
		 		}else{
		 			var url = "../waybillcs/waybill_detail?order=1&waybillid="+data.id;
		 			//window.open(url, "_blank");
		 			window.location.href=url;
		 		}
		 	}
		});
	}
}
