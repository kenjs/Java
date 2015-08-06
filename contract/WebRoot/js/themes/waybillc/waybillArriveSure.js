function getId(){
	var url=document.URL;
	var num = url.indexOf("?");
	idUrl = decodeURI(url.substr(num+1));
	return idUrl;
}

$(function(){
	//
	$("#carenddate").val(myDates("detetime"));
});

function btnSave(){
	if(checkData()){
		//屏蔽保存按钮，防止多次点击提交
		var carenddate=$("#carenddate").val();
		$("#save").hide();
		var param = "carenddate="+carenddate+"&"+getId();
		$.ajax({
			url: "../waybillcs/arrive",
		 	type:'post',	
		 	dataType:'json', 
		 	data: param, //参数     	               
		   	success:function(data){//回传函数
		   		if(data.msg=="ok"){
		   			ymPrompt.alert({message:"到车成功！",handler:closeBack});
		 		}
		 	}
		});
	}
}
/**
 * hcm
 * 填充详情
 */
function fillDetail(){
	var params=getId();
	$.ajax({
		url: "../waybillamountcs/selectWaybillById",
	 	type:'post',	
	 	dataType:'json', 
	 	data:params, //参数                   
	   	success:function(data){
		$("#carbegindate").val(data.carbegindate);
	    }
	})
}
function closeBack(){
	window.parent.ymPrompt.doHandler('ok',true);
}

//
function checkData(){
	var obj = $("#carenddate");
	if($(obj).val()==""){
		$(obj).css("border","1px solid red");
		return false;
	}
	var carenddate=$("#carenddate").val();
	var carbegindate=$("#carbegindate").val();
	carbegindate = carbegindate.split(".")[0];
	//alert(carbegindate.replace(/-/g,"/"))
	var carendtime = new Date(carenddate.replace(/-/g,"/"));
	var carbegintime = new Date(carbegindate.replace(/-/g,"/"));
	if(carbegintime>=carendtime){
		ymPrompt.alert("到车时间必须大于发车时间！");
		return false;
	}
	return true;
}

/******js获取当前日期和时间，传参dete是日期，time是时间,detetime是日期时间*******/
function myDates(vales) {
	var str = vales;
	var detetime = '';
	var myDate = new Date();
	var yyyy = myDate.getFullYear();//获取完整的年份(4位,1970-????)
	var MM = myDate.getMonth();//获取当前月份(0-11,0代表1月)
	var dd = myDate.getDate();//获取当前日(1-31)
	var HH = myDate.getHours(); //获取当前小时数(0-23)
	var mm = myDate.getMinutes();//获取当前分钟数(0-59)
	var ss = myDate.getSeconds();    //获取当前秒数(0-59)
	if(str == 'dete') {//日期
		if(String(MM).length == 1) {
			MM = '0'+String(MM+1);
		}else {
			MM = (MM+1);
		}
		if(String(dd).length == 1) {
			dd = '0'+String(dd);
		}
		detetime = yyyy+'-'+MM+'-'+dd;
	}
	if(str == 'time') {//时间
		if(String(HH).length == 1) {
			HH = '0'+String(HH);
		}
		if(String(mm).length == 1) {
			mm = '0'+String(mm);
		}
		if(String(ss).length == 1) {
			ss = '0'+String(ss);
		}
		detetime = HH+':'+mm+':'+ss;
	}
	if(str == 'detetime') {
		if(String(MM).length == 1) {
			MM = '0'+String(MM+1);
		}else {
			MM = (MM+1);
		}
		if(String(dd).length == 1) {
			dd = '0'+String(dd);
		}
		if(String(HH).length == 1) {
			HH = '0'+String(HH);
		}
		if(String(mm).length == 1) {
			mm = '0'+String(mm);
		}
		if(String(ss).length == 1) {
			ss = '0'+String(ss);
		}
		detetime = yyyy+'-'+MM+'-'+dd+" "+HH+':'+mm+':'+ss;
	}
	return detetime;
}