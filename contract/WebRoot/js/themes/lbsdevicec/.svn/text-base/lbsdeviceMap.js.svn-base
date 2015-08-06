$(document).ready(function(){

	function getData(){
		var urlData = document.URL;
		var begin = urlData.indexOf("=");
		urlData = urlData.substr(begin+1);
		return urlData;
	}
	
	getServiceInfo();
	//获取业务数据
	function getServiceInfo(){
		var urlData = getData();
		var str = decodeURI(urlData);

		var jsonData=JSON.parse(str); 
		//alert(JSON.stringify(jsonData));
		//var serviceInfo = jsonData.serviceInfo[0];
		//alert(serviceInfo);
		dataHanddle(jsonData);
	}
	
	var deviceid,partyid;
	var serviceInfoData;
	//处理数据
	function dataHanddle(jsonData){
		var deviceInfoData = jsonData.deviceInfo;
		serviceInfoData = jsonData.serviceInfo[0];
		var length = deviceInfoData.length;
		if(length == 0) return ;
		for ( var i = 0; i < length; i++) {
			var type = deviceInfoData[i].type;
			if(type == "device"){//固定设备
				deviceid = deviceInfoData[i].deviceid;
			}else if(type == "party"){//移动设备
				partyid = deviceInfoData[i].partyid;
			}
		}
		
		//业务数据只传固定设备
		if(deviceid != null && partyid == null){
			params = "deviceid="+deviceid;
			loaddata();
		//业务数据只传移动设备
		}else if(deviceid == null && partyid != null){
			params = "partyid="+partyid;
			loaddata();
		//业务数据传固定设备和移动设备
		}else if(deviceid != null && partyid != null){
			params = "deviceid="+deviceid;
			loaddata();
		}
	}

var speed,rt,lng,lat,lnglat;
function loaddata(){
	$.ajax({
	 	url: "../lbsdevicecs/selectLbsTrackByDeviceIdAndParyId",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:params, //参数     	  
	   	success:function(data){
	 		if(data != "" && data != null){
	 			var realtime = data.realtime //实时时间
	 			speed = data.speed; //时速
	 			rt = realtime.substring(0,realtime.indexOf('.'));
	 			lng = data.longitude; //经度
	 			lat = data.latitude; //纬度
	 			lnglat = new MMap.LngLat(lng,lat);
	 			mmap_basic_mapObj.clearMap(); 
	 			mmap_basic_mapObj.setCenter(lnglat); //设置地图的中心点
	 			//逆地理编码	
	 			var geocoderOption = {
 					radius: 1000, // 以已知坐标为中心点，radius为半径，返回范围内兴趣点和道路信息
					extensions: "all"// 返回地址描述以及附近兴趣点和道路信息，默认"base"
	 			};
	 			var geocoder = new MMap.Geocoder(geocoderOption);
	 			geocoder.regeocode(lnglat,geocoder_CallBack);
	 		}else{
	 			alert("暂无跟踪数据!");
	 		}
	 	}
	});
}

//回调函数 
function geocoder_CallBack(data){
	var address; //地址
	if(data.status == "E0"){ //表示成功
	    	var list = data.list;
	    	for ( var i = 0; i < list.length; i++) {
			var province = list[i].province.name; //省份
			var city = list[i].city.name; //城市
			var district = list[i].district.name; //地区
			address = province+city+district; //地址
			if(list[i].poilist.length > 0){ //获取兴趣点
				//道路信息
				if(list[i].poilist[i].address == "" || list[i].poilist[i].address == null){
					if(list[i].roadlist.length > 0){//获取道路信息
						var roadlist = list[i].roadlist[i].name; //道路
						address += roadlist;
					}
				}else{//兴趣点道路信息
					address += list[i].poilist[i].address; //兴趣点名称
				}
				address += list[i].poilist[i].name;
			}
		}
	}
	
	mmap_basic_marker = new MMap.Marker({ 
    	   id:"marker", //marker id 
    	   position:new MMap.LngLat(lng,lat), //位置 
    	   icon:"../js/amap/imgs/car.png",//复杂图标 
    	   draggable:false, //不可拖动
    	   cursor:"default",//鼠标悬停时显示的光标
    	   offset:new MMap.Pixel(-16,-16) //相对于基点的偏移量 
    }); 
 
	mmap_basic_mapObj.addOverlays(mmap_basic_marker);
    mmap_basic_mapObj.setCenter(mmap_basic_marker.position);
    var serviceInfo = "";
	//获取业务数据
	$.each(serviceInfoData, function(key, value) {
		serviceInfo += key+value +"<br/>";
	});
    var infoWindow = new MMap.InfoWindow({  
	     isCustom:true,  // 使用自定义窗体
	     content:mmap_basic_createInfoWindow('业务实时轨迹跟踪&nbsp;&nbsp;',serviceInfo+"GPS时间："+rt+"<br/>当前位置："+address+"<br/>"),  
	     size:new MMap.Size(300, 0),  
	     offset:new MMap.Pixel(-128, -310)// -113,-140
	 });
    //实例化信息窗体  
    infoWindow.open(mmap_basic_mapObj,mmap_basic_marker.getPosition());
    //鼠标点击marker弹出自定义的信息窗体 
    mmap_basic_mapObj.bind(mmap_basic_marker,'click',function(e){  
    	infoWindow.open(mmap_basic_mapObj,mmap_basic_marker.getPosition());    
    });
}

var lbsdeviceMap_interval = window.setInterval(function(){loaddata();},60000); //1分钟调用一次

//页面关闭、刷新、浏览器关闭触发
window.onbeforeunload = function(){
	window.clearInterval(lbsdeviceMap_interval); //清除定时任务
}
});