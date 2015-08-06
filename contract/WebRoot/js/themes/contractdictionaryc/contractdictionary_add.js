//获取指定格式的日期
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
 * 添加字典
 * @return
 */
function add(){
	if(check()){
		var date=myDateToString();
		var params ="&type="+$("#type").val()+"&text="+$("#text").val()
		+"&description="+$("#description").val()+"&inputdate="+ date
		+"&updatedate="+ date + "&random="+ Math.random();
		if(isExist(params)){
			alert("该字典已经存在！");
			return;
		}
		
		$.ajax( {
			type : "post",
			url : "../contractdictionarycs/insert",
			data : params,
			dataType : "json",
			error : function() {
				alert("加载错误");
			},
			success : function(data) {
				if (data.msg=="ok") {
					window.parent.ymPrompt.doHandler("保存,增加",true);
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
 * 验证字典字典是否已经存在
 * @return
 */
function isExist(params){
	var isexist;
	$.ajax( {
		type : "post",
		url : "../contractdictionarycs/isExist",
		data : params,
		async: false,
		dataType : "json",
		success : function(data) {
			if (data.msg=="ok") {
				isexist=true;
			}else{
				isexist=false;
			}
		}
	});
	return isexist;
}