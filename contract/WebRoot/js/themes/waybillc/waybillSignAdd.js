function getId(){
	var url=document.URL;
	var num = url.indexOf("?");
	idUrl = decodeURI(url.substr(num+1));
	return idUrl;
}

$(function(){
	var param = getId();
	selectById(param);//填充运单编号
	selectNumSumByWaybillId(param);//填充回单方式&货物总数
	//下拉框填充值
	f_contract('回单方式','backbilltype','');
});

//获取运单编号
function selectById(param){
	$.ajax({
		url : "../waybillcs/selectById",
		type : 'post',
		dataType : 'json',
		data : param, // 参数
		success : function(data) {// 回传函数
			$("#waybillnumber").html(data.waybillnumber);
		}
	});
}

////获取货物总数
function selectNumSumByWaybillId(param){
	$.ajax({
		url : "../goodscs/selectNumSumByWaybillId",
		type : 'post',
		dataType : 'json',
		data : param, // 参数
		success : function(data) {// 回传函数
			if(data!=null && data.length>0){
				$("#backbilltype").val(data[0].backBillType);
				$("#numSum").html(data[0].numSum);
			}
		}
	});
}

function btnSave(){
	if(checkData()){
		//屏蔽保存按钮，防止多次点击提交
		$("#save").hide();
		var signman=$("#signman").val();
		var signstatus=$("#signstatus").val();
		var backbilltype=$("#backbilltype").val();
		var signnum=$("#signnum").val();
		var memo=$("#memo").val();
		var param = "signman="+signman+"&signstatus="+signstatus+"&backbilltype="+backbilltype+
			"&signnum="+signnum+"&memo="+memo+"&"+getId();
		$.ajax({
			url: "../waybillsigncs/insert",
		 	type:'post',	
		 	dataType:'json', 
		 	data: param, //参数  	               
		   	success:function(data){//回传函数
		   		if(data.msg=="ok"){
		   			ymPrompt.alert({message:"签收成功！",handler:closeBack});
		 		}
		 	}
		});
	}
}

function closeBack(){
	window.parent.ymPrompt.doHandler('ok',true);
}

function checkData(){
	var signman = $("#signman");
	var signnum = $("#signnum");
	if($(signman).val()==""){
		$(signman).css("border","1px solid red");
		return false;
	}else{
		$(signman).css("border","1px solid #CCCCCC");
	}
	if($(signnum).val()==""){
		$(signnum).css("border","1px solid red");
		return false;
	}else{
		$(signnum).css("border","1px solid #CCCCCC");
	}
	return true;
}

/**数据字典-填充下拉框
 * obj: 数据类型 not null
 * names:字段名称 not null
 * ids：下拉框index标识 null
*/
function f_contract(obj,names,ids) {
	var types = obj;
	var name = names+ids;
	$.ajax({
		url: "../waybillcs/selectListByType",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:{"type":types}, //参数     	               
	   	success:function(data){//回传函数
	 		var dataObj=eval(data);
	 		var opent = '<option selected="selected" value=""></option>'; 
	 		for(var i=0;i<dataObj.length;i++){
	 			opent += '<option value="'+dataObj[i].text+'">'+dataObj[i].text+'</option>';
	 		}
	 		$("#"+name).html(opent);
	 	}
	});
}


