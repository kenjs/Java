function fillData(){
	var data=getData();
	$("#type").val(data[1]);
	$("#text").val(data[2]);
	$("#description").val(data[3]);
	$("#btnid").val(data[0]);
}

/**
 * wei.huang
 * 2013-9-26
 * 获取指定格式的日期
 * @return
 */
function myDateToString(){
	var date=new Date();
	var year=date.getFullYear();
	var month=date.getMonth()+1;
	var day=date.getDate();
	var hour=date.getHours();
	var minute=date.getMinutes();
	var second=date.getSeconds();
	return year+"-"+month+"-"+day+" "+hour+":"+minute+":"+second;
}

/**
 * wei.huang
 * 检查字典类型和文本是否为空
 * @return
 */
function check(){
	var isvalid=true;
	var type=$("#type").val();
	var text=$("#text").val();
	
	if(type==""||type=="请选择"){
		$("#type_tips").html("请选择字典类型！");
		$("#type_tips").show();
		isvalid=false;
	}else if(type.length>30){
		$("#type_tips").html("字典类型不能超过30个字！");
		$("#type_tips").show();
		isvalid=false;
	}else{
		$("#type_tips").hide();
		isvalid=true;
	}
	if(isvalid){
		if(text==""||text==null){
			$("#text_tips").html("文本不能为空！");
			$("#text_tips").show();
			isvalid=false;
		}else if(text.length>30){
			$("#text_tips").html("文本不能超过30个字！");
			$("#text_tips").show();
			isvalid=false;
		}else{
			$("#text_tips").hide();
			isvalid=true;
		}
		if(isvalid){
			if($("#description").val().length>50){
				$("#description_tips").html("描述不能超过50个字！");
				$("#description_tips").show();
				isvalid=false;
			}else{
				$("#description_tips").hide();
				isvalid=true;
			}
		}
	}
	return isvalid;
}

/**
 * wei.huang
 * 2013-9-26
 * 修改字典
 * @return
 */
function edit(){
	if(check()){
		var date=myDateToString();
		var params ="&type="+$("#type").val()+"&text="+$("#text").val()
		+"&description="+$("#description").val()+"&contractdictionaryid="+ $("#btnid").val()
		+"&updatedate="+ date + "&random="+ Math.random();
		if(isExist(params)){
			alert("该字典已经存在！");
			return;
		}
		
		$.ajax( {
			type : "post",
			url : "../contractdictionarycs/update",
			data : params,
			dataType : "json",
			error : function() {
				alert("加载错误");
			},
			success : function(data) {
				if ("ok" == $.trim(data.msg)) {
					window.parent.ymPrompt.doHandler("保存,修改",true);
				} else {
					//..............如果失败给与提示
					alert("请刷新浏览器");
				}
			}
		})
	}
	}


function cancel(){
	window.parent.ymPrompt.doHandler('取消',true); 
}
/**
 * wei.huang
 * 2013-9-26
 * 验证字典是否已经存在
 * @return
 */
function isExist(params){
	var isexist=null;
	$.ajax( {
		type : "post",
		url : "../contractdictionarycs/isExist",
		data : params,
		async: false,
		dataType : "json",
		success : function(data) {
			if ("ok" == $.trim(data.msg)) {
				isexist=true;
			}else{
				isexist=false;
			}
		}
	});
	return isexist;
}
/**
 * wei.huang
 * 2013-9-26
 * 获取URL中的参数
 * @return
 */
function getData(){
	var url_Data= document.URL;
	var num=url_Data.indexOf("?");
	url_Data=url_Data.substr(num+1);
	url_Data= url_Data.split("&");
	var data=new Array();
	$.each(url_Data,function(key,val){
		data[key]=decodeURI(val.split("=")[1]);
	});
	return data;
 }