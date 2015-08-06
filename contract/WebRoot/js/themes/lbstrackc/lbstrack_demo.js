var current_request = null;
$(function (){ 
    $("#accordion").ligerAccordion(
    {
        //height: 300
    });
    // 初始化 高德地图 （中心点 杭州）
    mmap_basic_init(8,'right_map_div',120.29731701,30.21907165,false);
    setLoadingimg();
    $("#cx").click(function(){
    	if($("#start").hasClass("none")){
    		$("#start").removeClass("none");
        	$("#restart").addClass("none");
    	}
    	$("#finish").addClass("none");
		$("#suspend").removeClass("none");
		$("#go_on").removeClass("block").addClass("none");
		cxbefore();
		$("#distance").html("0");//回放里程置0
		slider2.setValue(0);//回放进度置0
    	var_init();
    	getLbsTrackData();
    });
	$("#start").click(function(){
		$("#distance").html("0");//回放里程置0
		slider2.setValue(0);//回放进度置
		$("#suspend").removeClass("none");
		$("#go_on").removeClass("block").addClass("none");
		$(this).removeClass("block").addClass("none");
		$("#restart").removeClass("none");
		playBackStart();//轨迹回放开始
	});
	$("#restart").click(function(){
		$("#finish").addClass("none");
		$("#suspend").removeClass("none");
		$("#go_on").removeClass("block").addClass("none");
		$("#distance").html("0");//回放里程置0
		slider2.setValue(0);//回放进度置0
		reStart_remove_PolyLine();//删除轨迹重新添加运行轨迹
	});
	$("#suspend").click(function(){
		map_basic_markStopMove();
		$(this).removeClass("block").addClass("none");
		$("#go_on").removeClass("none");
	});
	$("#go_on").click(function(){//继续轨迹运行
		map_basic_markMove_GoOn();
		$(this).removeClass("block").addClass("none");
		$("#suspend").removeClass("none");
	});
    $("#switch_div").click(function(){
    	left_show_control();
    });
    var idUrl = document.URL;
	var num = idUrl.indexOf("?");
	var str = idUrl.substr(num+1);
	if(str.indexOf("flag")!=-1){//设备轨迹回放业务处理
		var partyid = str.split("&")[0].split("=")[1];
		var deviceid = str.split("&")[1].split("=")[1];
		$("#partyIds").val(partyid==""?"-1":partyid);
		$("#deviceIds").val(deviceid==""?"-1":deviceid);
		$("#starttime").val(getBeforeThreeDays());
		$("#endtime").val(nowTimeToString());
		$("#validdates").val("-1");
		$("#invaliddates").val("-1");
		$("#starttime").get(0).onchange=function(){
			setTimeLimit_device($("#starttime").get(0));
		};
		$("#endtime").get(0).onchange=function(){
			setTimeLimit_device($("#endtime").get(0));
		};
	}else{//业务轨迹回放数据处理
		getDeviceInfo();//获取设备信息
	}
	var_init();
	getLbsTrackData();
});   

//设置请求图片位置
function setLoadingimg(){
	var width = $(this).width();
    var height = $(this).height();
	$("#imgloading").css("margin-top",(height/2-50)+"px");
	$("#imgloading").css("left",(width/2-20)+"px");
}
/*
author:lj_jinrong.zhang
function:获取轨迹数据
date:2013-11-20
*/ 
function getLbsTrackData() {
	if(current_request){current_request.abort();}
	var paras = "";
	var starttime = $("#starttime").val().trim();//开始时间
	var real_starttime = $("#real_starttime").val().trim();
	var endtime = $("#endtime").val().trim();//结束时间
	var real_endtime = $("#real_endtime").val().trim();
	var partyids = $("#partyIds").val().trim();//会员id
	var deviceids = $("#deviceIds").val().trim();//设备号串
	var validdates = $("#validdates").val().trim();//生效日期
	var invaliddates = $("#invaliddates").val().trim();//失效日期
	if(validdates == "-1"&&invaliddates == "-1"){//设备轨迹回放
		validdates = starttime;
		invaliddates = endtime;
	}else{//业务数据
		if(starttime == maxtimeCompare(starttime,real_starttime)){
			var valDate = validdates.split(",");
			var starttime_temp = "";
			var valDate_temp = "";
			for(var i=0;i<valDate.length;i++){
				if(valDate[i]!="-1"){
					starttime_temp = maxtimeCompare(starttime,valDate[i]);
				}else{
					starttime_temp = starttime;
				}
				valDate_temp += starttime_temp+",";
			}
			if(valDate_temp.length>1){
				validdates = valDate_temp.substring(0, valDate_temp.length-1);
			}
		}
		if(endtime == mintimeCompare(endtime,real_endtime)){
			var invalDate = invaliddates.split(",");
			var endtime_temp = "";
			var invalDate_temp = "";
			for(var k = 0;k<invalDate.length;k++){
				if(invalDate[k]!="-1"){
					endtime_temp = mintimeCompare(endtime,invalDate[k]);
				}else{
					endtime_temp = endtime;
				}
				invalDate_temp += endtime_temp+",";
			}
			if(invalDate_temp.length>1){
				invaliddates = invalDate_temp.substring(0, invalDate_temp.length-1);
			}
		}
	}
	paras = "partyids=" + partyids + "&deviceids=" + deviceids +
		"&validdates=" + validdates + "&invaliddates=" + invaliddates;
	current_request = $.ajax( {
		type : "post",
		url : "../lbsdevicecs/selectLbsTrackListByIds?random="
				+ Math.random(),
		dataType : "json",
		data : paras,
		beforeSend: function(XMLHttpRequest) {
			if($('#imgloading').hasClass("none")){
				$('#imgloading').removeClass("none");
			}
		},
		error : function() {
			//alert("加载错误");
			if($('#imgloading').hasClass("none")==false){
				$('#imgloading').addClass("none");
			}
		},
		success : function(gpsdata) {
			if($('#imgloading').hasClass("none")==false){
				$('#imgloading').addClass("none");
			}
			$("#start").removeAttr("disabled");
			$("#restart").removeAttr("disabled");
			if (gpsdata!= null&&gpsdata.length!=0) {
				mmap_basic_addGpsTrack_polyline(gpsdata,serviceInfo);
			} else {
				alert("暂无轨迹数据");
			}
		}
	});
}

/*
 author:zjr
 function: 条件查询框收缩与伸展控制 
 date:2013-11-22
*/
function left_show_control(){
	$("#col1").toggleClass("none");
	if($("#col1").hasClass("none")){
		$("#playback_info").width("10");
		$("#zk").removeClass("none");
		$("#sj").addClass("none");
	}else{
		$("#playback_info").width("265");
		$("#sj").removeClass("none");
		$("#zk").addClass("none");
	}
}
//获取车辆设备信息
//function getDeviceInfo(carplatenumber){
//	var paras = "carplatenumber="+carplatenumber;
//	$.ajax( {
//		type : "post",
//		url : "../lbstrackcs/selectDeviceListByCarPlateNo?random=" + Math.random(),
//		dataType : "json",
//		data : paras,
//		error : function() {
//			alert("加载错误");
//		},
//		success : function(data) {
//			//处理返回数据
//			if(data != null){
//				serviceInfo = data.serviceInfo[0];
//				dataHanddle(data);
//			}else{
//				alert("暂无数据");
//			}
//		}
//	});
//}
/* 获取车辆设备信息*/
function getDeviceInfo(){
	var data = getData();
//	alert("data:::"+decodeURI(data));
//	data = decodeURI(data);
//	alert(".......data:"+decodeURI(data));
	var str = decodeURI(data);
//	alert("str"+str);
	var da=JSON.parse(str); 
//	alert(JSON.stringify(da));
	serviceInfo = da.serviceInfo[0];
	servicetimeinfo = da;
	dataHanddle(da);
}
var serviceInfo = null;//标注窗口信息
var servicetimeinfo = null;
//设备数据处理
function dataHanddle(jsonData){
	var deviceInfoData = jsonData.deviceInfo;
	var serviceInfoData = jsonData.serviceInfo[0];
	var length = deviceInfoData.length;
	if(length == 0) return ;
	$("#starttime").val(serviceInfoData.starttime);
	$("#starttime").get(0).onchange=function(){
		setTimeLimit($("#starttime").get(0));
	};
	$("#real_starttime").val($("#starttime").val().trim());
	$("#endtime").val(serviceInfoData.endtime);
	$("#endtime").get(0).onchange=function(){
		setTimeLimit($("#endtime").get(0));
	};
	$("#real_endtime").val($("#endtime").val().trim());
	var partyids = "";
	var deviceids = "";
	var validdates = "";
	var invaliddates = "";
	for(var i=0;i<length;i++){
		if(deviceInfoData[i].type=="device"){
			partyids += "-1,";
			deviceids += deviceInfoData[i].deviceid+",";
		}else if(deviceInfoData[i].type=="party"){
			partyids += deviceInfoData[i].partyid+",";
			deviceids += "-1,";
		}
		if(deviceInfoData[i].validate!=""){
			validdates += deviceInfoData[i].validate+",";
		}else{
			validdates += "-1,";
		}
		if(deviceInfoData[i].invaliddate!=""){
			invaliddates += deviceInfoData[i].invaliddate+",";
		}else{
			invaliddates += "-1,";
		}
	}
	if(partyids.length!=0){
		partyids = partyids.substring(0,partyids.length-1);
		deviceids = deviceids.substring(0, deviceids.length-1);
		validdates = validdates.substring(0, validdates.length-1);
		invaliddates = invaliddates.substring(0, invaliddates.length-1);
	}
	$("#partyIds").val(partyids);
	$("#deviceIds").val(deviceids);
	$("#validdates").val(validdates);
	$("#invaliddates").val(invaliddates);
	selectFenceListByIds();
}
//获取电子围栏信息
function selectFenceListByIds(){
	var deviceids = $("#deviceIds").val();
	var partyids = $("#deviceIds").val();
	var paras = "deviceids="+deviceids+"&partyids="+partyids;
	$.ajax( {
		type : "post",
		url : "../lbsdevicecs/selectLbsFenceListByIds?random=" + Math.random(),
		dataType : "json",
		data : paras,
		error : function() {
			alert("加载错误");
		},
		success : function(data) {
			//处理返回数据
			if(data != null&&data.length!=0){
				fenceaddtomap(data);
			}else{
				alert("暂无电子围栏数据");
			}
		}
	});
}
//获取当前日期时间的字符串
function nowTimeToString(){
	var myDate = new Date();
	var year = myDate.getFullYear();    //获取完整的年份(4位,1970-????)
	var month = myDate.getMonth()+1;       //获取当前月份(0-11,0代表1月)
	var day = myDate.getDate();        //获取当前日(1-31)
	var hours = myDate.getHours();       //获取当前小时数(0-23)
	var minutes = myDate.getMinutes();     //获取当前分钟数(0-59)
	var seconds = myDate.getSeconds(); 
	if(month<10) month = "0"+month;//获取当前秒数(0-59)
	if(day<10) day = "0"+day;
	if(hours<10) hours = "0"+hours;
	if(minutes<10) minutes = "0"+minutes;
	if(seconds<10) seconds = "0"+seconds;
	var nowdate = year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds //当前日期时间
	return nowdate;
}
//两时间比较取小值 
function mintimeCompare(basetime,comparetime){
	var regEx = new RegExp("\\-","gi");
	var basetime_val=basetime.replace(regEx,"/");
	var comparetime_val=comparetime.replace(regEx,"/");
	var basetext = Date.parse(basetime_val);
	var comptext = Date.parse(comparetime_val);
	if(comptext>basetext) return basetime;
	return comparetime;
    
}
//两时间 比较取大值
function maxtimeCompare(basetime,comparetime){
    var regEx = new RegExp("\\-","gi");
	var basetime_val=basetime.replace(regEx,"/");
	var comparetime_val=comparetime.replace(regEx,"/");
	var basetext = Date.parse(basetime_val);
	var comptext = Date.parse(comparetime_val);
	if(comptext<basetext) return basetime;
	return comparetime;
}
//获取当天的两天前的日期
var beforeThreeDate = "";
function getBeforeThreeDays(){ 
	var date = new Date();
    var beforeday_milliseconds=date.getTime()-2*1000*60*60*24;        
    var beforeday = new Date();        
    beforeday.setTime(beforeday_milliseconds);        
         
    var strYear = beforeday.getFullYear();     
    var strDay = beforeday.getDate();     
    var strMonth = beforeday.getMonth()+1;   
    if(strMonth<10)     
    {     
        strMonth="0"+strMonth;     
    }     
    datastr = strYear+"-"+strMonth+"-"+strDay + " 00:00:00";   
    beforeThreeDate = datastr;
    return datastr;   
  }   
function getData(){
	var dataUrl= document.URL;
	var num=dataUrl.indexOf("=") 
	dataUrl=dataUrl.substr(num+1);
	//alert("getdata:::::"+dataUrl);
	return dataUrl;
 }
//业务轨迹回放对可填时间做限制   限制时间在业务时间范围内
function setTimeLimit(obj){
	var id = obj.id;
	var value = obj.value;
	var regEx = new RegExp("\\-","gi");
	var objValue=value.replace(regEx,"/");
	var objDate = Date.parse(objValue);
	var startValue = servicetimeinfo.serviceInfo[0].starttime.replace(regEx,"/");
	var startDate = Date.parse(startValue);
	var endValue = servicetimeinfo.serviceInfo[0].endtime.replace(regEx,"/");;
	var endDate = Date.parse(endValue);
	if(objDate<startDate||objDate>endDate){
		alert("请选择业务时间范围内的时间");
		if(id=="starttime"){//恢复默认开始时间
			$(obj).val(servicetimeinfo.serviceInfo[0].starttime);
		}else{//恢复默认结束时间
			$(obj).val(servicetimeinfo.serviceInfo[0].endtime);
		}
	}
}
//设备轨迹回放对可填时间做限制   限制时间段最长为三天
function setTimeLimit_device(obj){
	var id = obj.id;
	var objValue = obj.value;
	var regEx = new RegExp("\\-","gi");
	var startValue = $("#starttime").val().trim().replace(regEx,"/");
	var endValue = $("#endtime").val().trim().replace(regEx,"/");
	var startDate = 0;
	if(startValue!=""){
		startDate = Date.parse(startValue);
	}
	var endDate = 0;
	if(endValue!=""){
		endDate = Date.parse(endValue);
	}
	if(id == "starttime"){
		var date = new Date();
		date.setTime(startDate+5*24*60*60*1000);
		var year = date.getFullYear();    //获取完整的年份(4位,1970-????)
		var month = date.getMonth()+1;       //获取当前月份(0-11,0代表1月)
		var day = date.getDate();        //获取当前日(1-31)
		var hours = date.getHours();       //获取当前小时数(0-23)
		var minutes = date.getMinutes();     //获取当前分钟数(0-59)
		var seconds = date.getSeconds(); 
		if(month<10) month = "0"+month;//获取当前秒数(0-59)
		if(day<10) day = "0"+day;
		if(hours<10) hours = "0"+hours;
		if(minutes<10) minutes = "0"+minutes;
		if(seconds<10) seconds = "0"+seconds;
		var datestr = year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
		$("#endtime").val(datestr);//给结束时间赋值，值为开始时间三天后的时间字符串
	}else{
		if(startValue!=""){
			var d_value = (endDate-startDate)*1.0/(1000*60*60*24);
			if(d_value>5||d_value<0){
				if(d_value>5){
					alert("请选择开始时间五天以内的时间!");
				}else{
					alert("结束时间不能早于开始时间!");
				}
				var date = new Date();
				date.setTime(startDate+5*24*60*60*1000);
				var year = date.getFullYear();    //获取完整的年份(4位,1970-????)
				var month = date.getMonth()+1;       //获取当前月份(0-11,0代表1月)
				var day = date.getDate();        //获取当前日(1-31)
				var hours = date.getHours();       //获取当前小时数(0-23)
				var minutes = date.getMinutes();     //获取当前分钟数(0-59)
				var seconds = date.getSeconds(); 
				if(month<10) month = "0"+month;//获取当前秒数(0-59)
				if(day<10) day = "0"+day;
				if(hours<10) hours = "0"+hours;
				if(minutes<10) minutes = "0"+minutes;
				if(seconds<10) seconds = "0"+seconds;
				var datestr = year+"-"+month+"-"+day+" "+hours+":"+minutes+":"+seconds;
				$("#endtime").val(datestr);//给结束时间赋值，值为开始时间三天后的时间字符串
			}
		}
	}
}
//根据两时间字符串获取时间差
function getTimeDiff(starttime,endtime){
	var regEx = new RegExp("\\-","gi");
	var starttime_val=starttime.replace(regEx,"/");
	var endtime_val=endtime.replace(regEx,"/");
	var starttimeValue = Date.parse(starttime_val);
	var endtimeValue = Date.parse(endtime_val);
	var timeDiffs = endtimeValue - starttimeValue;
	return timeDiffs;
}