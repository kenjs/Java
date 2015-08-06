$(function ()
{ 
    $("#accordion").ligerAccordion(
    {
        //height: 300
    });
    // 初始化 高德地图 （中心点 杭州）
	amap_basic_init(8,'right_map_div',120.29731701,30.21907165,false);
	
	$("#start").click(function(){
		$("#distance").html("0");//回放里程置0
		slider2.setValue(0);//回放进度置
		getLbsTrackData();//查询轨迹数据
		$("#suspend").removeClass("none");
		$("#go_on").removeClass("block").addClass("none");
		$(this).removeClass("block").addClass("none");
		$("#restart").removeClass("none");
		$("#restart").click(function(){
			reStart_remove_PolyLine();//删除轨迹重新添加运行轨迹
			$("#distance").html("0");//回放里程置0
			slider2.setValue(0);//回放进度置0
			getLbsTrackData();//根据条件重新请求数据
			//$(this).removeClass("block").addClass("none");
			//$("#start").removeClass("none");
			
			$("#suspend").removeClass("none");
			$("#go_on").removeClass("block").addClass("none");
		});
	});
	$("#suspend").click(function(){
		map_basic_markStopMove();
		$(this).removeClass("block").addClass("none");
		$("#go_on").removeClass("none");
		$("#go_on").click(function(){//继续轨迹运行
			map_basic_markMove_GoOn();
			$(this).removeClass("block").addClass("none");
			$("#suspend").removeClass("none");
		});
	});
    
    $("#switch_div").click(function(){
    	left_show_control();
    });
    $("#type").change(function(){
    	convert_content();
    });
    //获取partyid,deviceid参数值
    var idUrl = document.URL;
	var num = idUrl.indexOf("?");
	var str = idUrl.substr(num+1);
	var partyid = str.split("&")[0].split("=")[1];
	var deviceid = str.split("&")[1].split("=")[1];
	$("#partyId").val(partyid);
	$("#deviceId").val(deviceid);
});    
/*
author:lj_jinrong.zhang
function:获取轨迹数据
date:2013-11-20
*/ 
function getLbsTrackData() {
	var paras1 = "";
	var paras2 = "";
	var partyid = $("#partyId").val().trim();//会员Id
	var type = $("#type").val().trim();//类型
	var deviceid = $("#deviceId").val().trim();//设备号
	var orderno = $("#orderNo").val().trim();//业务单号
	var starttime = $("#starttime").val().trim();//开始时间
	var endtime = $("#endtime").val().trim();//结束时间
	paras1 = "partyid=" + partyid + "&deviceid=" + deviceid + "&orderno="
			+ orderno + "&starttime=" + starttime + "&endtime=" + endtime;
	paras2 = "type=" + type + "&deviceid=" + deviceid + "&orderno="
			+ orderno;
	$.ajax( {
		type : "post",
		url : "../lbstrackcs/selectDeviceInfoByDeviceOrOrderNo?random=" + Math.random(),
		dataType : "json",
		data : paras2,
		error : function() {
			alert("加载错误");
		},
		success : function(infodata) {
			//if (infodata != null) {
			$.ajax( {
				type : "post",
				url : "../lbstrackcs/selectLbsTrackList?random="
						+ Math.random(),
				dataType : "json",
				data : paras1,
				error : function() {
					alert("加载错误")
				},
				success : function(gpsdata) {
					if (gpsdata != null && gpsdata.length !=0) {
						amap_basic_addGpsTrack_polyline(gpsdata, infodata);
					} else {
						alert("暂无轨迹数据");
					}
				}
			});
			//}
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
		$("#zk").removeClass("none");
		$("#sj").addClass("none");
	}else{
		$("#sj").removeClass("none");
		$("#zk").addClass("none");
	}
}
//根据类型更换输入input框内容 zjr
function convert_content(){
	var value = $("#type").val();
	if(value=="1" || value=="2"){
		if($("#orderNo_tr").hasClass("none")){
			$("#orderNo_tr").removeClass("none");
			$("#deviceId_tr").addClass("none");
			$("#deviceId").val('');
		}else{
			return;
		}
	}else{
		if($("#deviceId_tr").hasClass("none")){
			$("#deviceId_tr").removeClass("none");
			$("#orderNo_tr").addClass("none");
			$("#orderNo").val('');
		}else{
			return;
		}
	}
}