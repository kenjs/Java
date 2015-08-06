var mmap_basic_mapObj = null; // map 对象
var mmap_basic_maptype = null; // 地图类型
var mmap_basic_toolbar = null; // 工具条
var mmap_basic_overview = null; // 鹰眼
var mmap_basic_scale = null; // 比例尺
var mmap_basic_mousetool = null; // 鼠标工具
var mmap_basic_buildings = null; // 实例化3D楼块图层
var mmap_basic_lays = null;// 图层
var mmap_basic_marker = null;// 覆盖物
var mmap_basic_markerinfo = {};// 车辆点标记信息
var mmap_basic_lines = {};// 轨迹点信息 key(busid),value({'id':{lng,lat}})
var mmap_basic_geocoder = null;// 地理服务
var mmap_basic_newinfoWindow = null;// 信息窗口
/**
 * 地图初始化 levelval 13 id mapmonitor_show_map_div map放置div lng lat 经度纬度 position
 * true | false null false null 不自动定位当前位置 true 自动定位当前位置
 */
//设置监听事件判断窗口大小变化 ，以重新设置工具条的水平偏移量
$(window).resize(function() {
    var width = $(this).width();
    var height = $(this).height();
    if(mmap_basic_toolbar){
    	mmap_basic_toolbar.setOffset(new MMap.Pixel(width-70,10));
    }
    setLoadingimg();
});
function mmap_basic_init(levelval,id,lng,lat,position){
	var opt = {
		level:levelval,                          // 初始地图视野级别
		center:new MMap.LngLat(lng,lat),         // 设置地图中心点
		doubleClickZoom:true,                    // 双击放大地图
		scrollwheel:true                         // 鼠标滚轮缩放地图

	};
	mmap_basic_mapObj = new MMap.Map(id,opt); // 创建地图对象
	// 添加地图类型切换插件
    mmap_basic_mapObj.plugin(["MMap.ToolBar","MMap.OverView","MMap.Scale"],function(){  
    
        mmap_basic_mapObj.addControl(mmap_basic_maptype);  // 初始状态使用2D地图
		
		// 在地图中添加ToolBar插件
		mmap_basic_toolbar = new MMap.ToolBar({
			offset:new MMap.Pixel($(window).width()-70, 10)// 向右、向下为正
		});
		mmap_basic_mapObj.addControl(mmap_basic_toolbar);
		
		// 加载鹰眼
        mmap_basic_overview = new MMap.OverView({  
            visible:true // 初始化展示鹰眼
        });  
        mmap_basic_mapObj.addControl(mmap_basic_overview);  
		
		// 加载比例尺插件
		mmap_basic_scale = new MMap.Scale();  
        mmap_basic_mapObj.addControl(mmap_basic_scale); 
		
		// 加载地理服务
		mmap_basic_geocoder = new MMap.Geocoder({  
			radius: 1000, // 以已知坐标为中心点，radius为半径，返回范围内兴趣点和道路信息
			extensions: "all"// 返回地址描述以及附近兴趣点和道路信息，默认“base”
		}); 
    });
    mmap_basic_mapObj.bind(mmap_basic_mapObj,"dragend",function(){
    	if($("#float_location").hasClass("none")){
    		$("#float_location").removeClass("none");
    	}
    });
}

/**
 * 定位点上的基本信息 marker 标注点对象 infoWindow 弹出框对象
 */
function mmap_carinfo_creatJSON(marker,infoWindow){
	var p = {
		'marker':marker,
		'infoWindow':infoWindow
	}
	return p;
}

// 刷新地图
function mmap_basic_refreshmap(){
      mmap_basic_mapObj.clearMap();
}

// 设置地图中心点
function mmap_basic_setMapCenter(lng,lat){  
    mmap_basic_mapObj.setZoomAndCenter(13,new MMap.LngLat(lng,lat));  
}

// 地图自适应显示，如果有标记物，所有标记将在视野内显示
function mmap_basic_fit(){
	mmap_basic_mapObj.setFitView();
}
 
/**
 * 信息窗体中展示的内容 
 * infoJSON
 */
function mmap_basic_InfoWindow(infoJSON,speed,time,deviceid,address,partyid){
	var y = 100;
	var title="GPS车辆轨迹回放相关信息";
	var content = "<table width='100%' style='text-align:left;margin-top:-10px;'>";
	if(infoJSON){
		$.each(infoJSON,function(key,value){
			if(!(key=="starttime"||key=="endtime")){
				y += 24;
				content += "<tr><td width='30%'>" + key
				+ "</td><td width='70%'>" + value + "</td></tr>";
			}
		});
	}
	if(partyid!=""){
		y += 23;
		content += "<tr><td width='30%'>手机号: "
			+ "</td><td width='70%'>"+deviceid+"</td></tr>";
	}else{
		y += 23;
		content += "<tr><td width='30%'>设备号: "
			+ "</td><td width='70%'>"+deviceid+"</td></tr>";
	}
	if(speed!=""){
		y += 23;
		content += "<tr><td width='30%'>速度: "
			+ "</td><td width='70%'>"+speed+"km/h</td></tr>";
	}
	if(time!=""){
		y += 23;
		content += "<tr><td width='30%'>GPS时间: "
			+ "</td><td width='70%'>"+time+"</td></tr>";
	}
	if(address != ""){
		//y += 24;
		//判断解析出的地址长度，纵向调整信息窗体的位置
		var chineseCount=GetChineseCount(address);//得到字符串中的中文字符数
		var piexy;
	    var piexparam=address.length;//字符串中的总长度
	    var count;//折算成汉字后字的总长度
	    
	   //如果字符串中全是中文字符就不做任何处理，如果有数字和英文字符，就按照2个数字占用的字节数相当于一个中文字符的字节数来计算
	    if(chineseCount == piexparam){
			count = piexparam;
	    } else{
	    	var encount = piexparam-chineseCount;
	    	if(encount%2 == 0){
	    		count = chineseCount + Math.floor((piexparam-chineseCount)/2);
	    	} else {
	    		count = chineseCount +Math.floor((piexparam-chineseCount)/2)+1;
	    	}
	    }
	    //如果字符在15个以内，信息窗体中不增加行数,超出15的字数与15取整之后再加上2行
	    if(count <=14){
			   y += 23;
			} else{
				piexy=2+Math.floor((count-14)/14);
				y += piexy*23;
			}
		content += "<tr><td width='25%'>地址:"
			+ "</td><td width='75%'>"+address+"</td></tr>";
	}
	content += "</table>";
	if(pixel_y!=y){
		pixel_y = y;
		if(mmap_basic_gpsTrackMarkerInfo){
			mmap_basic_gpsTrackMarkerInfo.setOffset(new MMap.Pixel(-125,-pixel_y));
		}
	}
   return mmap_basic_createInfoWindow(title,content);
}
function InfoWindow(starttime,endtime,timediffs,address){
	var title="GPS车辆轨迹回放暂停信息";
	var content = "<table width='100%' style='text-align:left;margin-top:-10px;'>";
	var hourdiffs = parseInt(timediffs/(1000*60*60));
	var mindiffs = parseInt(((timediffs/1000)%(60*60))/60);
	var seconddiffs = ((timediffs/1000)%(60*60))%60;
	var diffstr = "";
	if(hourdiffs > 0){
		diffstr += hourdiffs + "小时";
	}
	if(mindiffs > 0){
		diffstr += mindiffs + "分";
	}else{
		if(hourdiffs > 0){
			diffstr += "0分";
		}
	}
	if(seconddiffs > 0){
		diffstr += seconddiffs + "秒";
	}

	content += "<tr><td width='25%'>开始时间: "
		+ "</td><td width='75%'>"+starttime+"</td></tr>";
	content += "<tr><td width='25%'>结束时间: "
		+ "</td><td width='75%'>"+endtime+"</td></tr>";
	content += "<tr><td width='25%'>停留时间: "
		+ "</td><td width='75%'>"+diffstr+"</td></tr>";
	content += "<tr><td width='25%'>停留位置: "
		+ "</td><td width='75%'>"+address+"</td></tr>";
	content += "</table>";
	return mmap_basic_createInfoWindow(title,content);
}
function amap_basic_geocoderadd(lng,lat){
	   var carinfoJSON = {};// 存放信息窗体中显示的内容
	  
		// 逆地理编码
		amap_basic_geocoder.getAddress(new AMap.LngLat(lng, lat)); 
		// 返回地理编码结果
		AMap.event.addListener(amap_basic_geocoder, "complete", function(data){
			amap_basic_geocoderadd_address = data.regeocode.formattedAddress;
		});
		return amap_basic_geocoderadd_address;
	}
 // 构建自定义信息窗体
function mmap_basic_createInfoWindow(title,content){
	
	var m = [];
	   m.push("<div class='info'>");// s info
	   m.push("<div class='info-top' onclick='mmap_basic_closeInfoWindow()'>");//s info-top
	   // 定义顶部标题
	   m.push("<div>"+title+"</div><img src='../js/amap/imgs/close.gif'/>"); // info-top content
	    m.push("</div>");// e info-top
	   // 定义中部内容
	   m.push("<div class='info-middle'>"+content+"</div>");
	   // 定义底部内容
	   m.push("<div class='info-bottom'><img src='../js/amap/imgs/sharp.png'/></div>");
	   m.push("</div>");// e info
	   return m.join("");
}  
// 关闭信息窗体
function mmap_basic_closeInfoWindow(){  
    mmap_basic_mapObj.clearInfoWindow();
	//mmap_basic_gpsTrackMarkerInfo.close();
}
//根据经纬度获取详细地址   mapabc
function mmap_basic_geocoderadd(lnglat){
	// 逆地理编码
	mmap_basic_geocoder.regeocode(lnglat,data_comback);
} 
function data_comback(data){
	var resultStr = "";
	if(data.status=="E0"){ 
        for(var i=0;i<data.list.length;i++){  
            resultStr += data.list[i].province.name+data.list[i].city.name+data.list[i].district.name; //
            if(data.list[i].poilist!=""){
            	resultStr += data.list[i].poilist[0].address==""?(data.list[i].roadlist==""?"":data.list[i].roadlist[0].name):(data.list[i].poilist[0].address+data.list[i].poilist[0].name);
            }
        } 
    }
	address = resultStr;
	if(mmap_basic_pos == -1){
		mmap_basic_gpsTrackMarkerInfo.setContent(mmap_basic_InfoWindow(windowInfo_content,speedArray[points_length-1],timeArray[points_length-1],deviceidArray[points_length-1],address,partyidArray[points_length-1]));
		mmap_basic_gpsTrackMarkerInfo.open(mmap_basic_mapObj,mmap_basic_gpsTrackPoints[points_length-1]);
	}else{
		mmap_basic_gpsTrackMarkerInfo.setContent(mmap_basic_InfoWindow(windowInfo_content,speedArray[mmap_basic_pos],timeArray[mmap_basic_pos],deviceidArray[mmap_basic_pos],address,partyidArray[mmap_basic_pos]));
		mmap_basic_gpsTrackMarkerInfo.open(mmap_basic_mapObj,mmap_basic_gpsTrackPoints[mmap_basic_pos]);
	}
}
var mmap_basic_gpsTrackPoints = [];  // GPS监控轨迹点集合
var mmap_basic_gpsTrackMarker = null; // GPS监控实物点标注对象
var mmap_basic_gpsTrackMarkerInfo = null;  // GPS监控实物点信息窗口
var mmap_basic_gpsTrackPolyLine = null; // GPS监控实物点轨迹对象
var mmap_basic_distance = 0;// 当前运行距离
var mmap_basic_total_distance = 0;// 轨迹总距离
var mmap_basic_distance_unit = "";// 当前运行距离+单位
var mmap_basic_total_distance_unit = "";// 轨迹总距离 + 单位
var windowInfo_content = null;
var address = "";// 实时地址
var flag = true;// 标记是否运行标志
var speedArray = new Array();
var timeArray = new Array();
var deviceidArray = new Array();
var partyidArray = new Array();
var pixel_y = 0;//信息窗口垂直方向偏移量 mapabc

var points_length = 0;//轨迹记录总数
// gps运行轨迹
function mmap_basic_addGpsTrack_polyline(gpsdata,windowInfo){
	//var_init();
	windowInfo_content = windowInfo;
	var pre_speed = "";
	var cur_speed = "";
	var f = true;
	var temp_partyid = "";
	if(gpsdata!=null&& gpsdata.length!=0){
		for(var i=0; i<gpsdata.length;i++){
			cur_speed = gpsdata[i].speed;
			temp_partyid = gpsdata[i].partyid;
			//去除速度为0/1的轨迹数据（但不包括第1条速度为0/1的数据）
			/*if((cur_speed=="0"||cur_speed=="1")&&(pre_speed=="0"||pre_speed=="1")){
				continue;
			}else{
				pre_speed = gpsdata[i].speed;
			}*/
			//去除开始的速度小于5的数据
			if(temp_partyid==""){//如果设备轨迹刚过滤（手机轨迹不做过滤）
				var floatspeed = parseFloat(cur_speed);
				if((cur_speed < 5)&&f==true){
					continue;
				}
			}
			f = false;
			
			
			var dimX = parseFloat(gpsdata[i].longitude);
			var dimY = parseFloat(gpsdata[i].latitude) ;
			var gpsXY = sp_gpsfix_init.transform(dimX,dimY);// 经纬度纠偏
			mmap_basic_gpsTrackPoints.push(new MMap.LngLat(parseFloat(gpsXY.X), parseFloat(gpsXY.Y)));
			speedArray.push(gpsdata[i].speed);
			timeArray.push(gpsdata[i].realtime.replace(".0",""));
			deviceidArray.push(gpsdata[i].deviceid);
			partyidArray.push(gpsdata[i].partyid);
		}// for
		//去除最后速度小于5的数据（如果是手机轨迹不去除）
		var floatspeed = 0;
		for(i = mmap_basic_gpsTrackPoints.length-1;i>=0;i--){
			floatspeed = parseFloat(speedArray[i]);
			temp_partyid = partyidArray[i];
			if(floatspeed<5&&temp_partyid==""){
				continue;
			}else{
				mmap_basic_gpsTrackPoints = mmap_basic_gpsTrackPoints.slice(0,i+1);
				break;
			}
		}
		points_length = mmap_basic_gpsTrackPoints.length;
		var arr = new Array();
		if(points_length>0){
			//添加两端标注点  --start  起始点标注
			var marker0 = new MMap.Marker({                    
		 	      id:"marker_0", //marker id                       
		 	      position:mmap_basic_gpsTrackPoints[0], //位置  
		 	      offset:new MMap.Pixel(-10,-36), //基点为图片左上角，设置相对基点的图片位置偏移量，向右向下为负 
		 	      icon:"../js/amap/imgs/qi.png",//图标，直接传递地址，还可以为MMap.Icon 对象 
		 	      draggable:false //不可拖动  
		 	});  
			arr.push(marker0);
			mmap_basic_mapObj.panTo(mmap_basic_gpsTrackPoints[0]);
		}
		if(points_length>1){
			//终点标注
			var marker1 = new MMap.Marker({                    
		 	      id:"marker_1", //marker id                       
		 	      position:mmap_basic_gpsTrackPoints[points_length-1], //位置  
		 	      offset:new MMap.Pixel(-10,-36), 
		 	      icon:"../js/amap/imgs/zhong.png",
		 	      draggable:false //不可拖动  
		 	});  
			arr.push(marker1);
			//添加两端标注点  --end
		}
		
		mmap_basic_gpsTrackPolyLine=new MMap.Polyline({ 
			id:"line_0",
			path:mmap_basic_gpsTrackPoints,
			strokeColor:"#9905f7", //线颜色 
			strokeOpacity:0.6, //线透明度	  
			strokeWeight:7, //线宽 	 
			strokeStyle:"solid"//线样式	 
		}); 
		arr.push(mmap_basic_gpsTrackPolyLine);
		mmap_basic_mapObj.addOverlays(arr);//将轨迹拆线添加到地图上
		mmap_basic_mapObj.setZoom(13);
		// 计算轨迹总距离
		mmap_basic_total_distance = mmap_basic_gpsTrackPolyLine.getLength();
		mmap_basic_total_distance_unit=show_format(mmap_basic_total_distance);
		$("#distance").html("0/"+mmap_basic_total_distance_unit);
	}// if gpsdata==null || gpsdata.length==0
}
//开始运行
function playBackStart(){
	if (mmap_basic_gpsTrackPoints.length > 0) {  
	 	// 添加地图标点 
	 	mmap_basic_gpsTrackMarker = new MMap.Marker({                    
	 	      id:"marker", //marker id                       
	 	      position:mmap_basic_gpsTrackPoints[0], //位置  
	 	      offset:new MMap.Pixel(-11,-35),
	 	      icon:"../js/amap/imgs/track.png",
	 	      draggable:false, //不可拖动  
	 	      zIndex:10000,
	 	      autoRotation:false   //true，表示图标自动旋转
	 	});  
	 	mmap_basic_mapObj.addOverlays(mmap_basic_gpsTrackMarker);//添加点覆盖物 
	 	// 注册单击事件
	 	mmap_basic_mapObj.bind(mmap_basic_gpsTrackMarker,"click",function(e){
	 		var pos = mmap_basic_pos - 1;
	 		if(pos<0) pos = mmap_basic_gpsTrackPoints.length-1;
	 		mmap_basic_gpsTrackMarkerInfo.open(mmap_basic_mapObj,mmap_basic_gpsTrackPoints[pos]);
		});
	 	// 循环调用 -到下一个轨迹点 
	 	mmap_basic_mapObj.bind(mmap_basic_gpsTrackMarker,"moveend",function(e){
			mmap_basic_pos = mmap_basic_pos+1;
			setTimeout("mmap_basic_addGpsTrack()",10);// 标注停留10毫秒
			//mmap_basic_addGpsTrack();
	 	});
	 	// 信息窗体定义
	 	mmap_basic_gpsTrackMarkerInfo = new MMap.InfoWindow({  
			isCustom:true,  // 使用自定义窗体
			content:mmap_basic_InfoWindow(windowInfo_content,speedArray[0],timeArray[0],deviceidArray[0],"",partyidArray[0]),
			autoMove:true,
			size:new MMap.Size(100 ,100),
			offset:new MMap.Pixel(-125,-pixel_y)
	    });
	 	//mapabc做判断
	 	if(windowInfo_content==null){
	 		mmap_basic_gpsTrackMarkerInfo.setOffset(new MMap.Pixel(-125,-150));
	 	}
		mmap_basic_addGpsTrack(); // 第二个开始
	} // if mmap_basic_gpsTrackPoints.length > 0
}
/**
 * 添加GPS轨迹. 就是运行 通过pos在mmap_parseGpsData_gpsTrackPoints数组中从哪一位开始
 */
var mmap_basic_outTime = 300; // 运行速度
var mmap_basic_pos = 1;// 第二个开始
var m = 0;
// 两点之间画轨迹
function mmap_basic_addGpsTrack(){
	mmap_basic_pos = (mmap_basic_pos == mmap_basic_gpsTrackPoints.length ? -1 : mmap_basic_pos); // 不能超过总长度
	
	
	var floatspeed = 0;
	var starttime="";
	var endtime = "";
	var g = true;
	var start_mmap_basic_pos = mmap_basic_pos;
	var end_mmap_basic_pos = "";
	var temp_partyid = "";
	for(var i=mmap_basic_pos;i<mmap_basic_gpsTrackPoints.length;i++){
		floatspeed = parseFloat(speedArray[i]);
		temp_partyid = partyidArray[i];
		if(floatspeed<5&&temp_partyid==""){
			g = false;
		}else{
			mmap_basic_pos = i;
			end_mmap_basic_pos = i-1;
			break;
		}
	}
	if(!g){
		var timediffs = getTimeDiff(timeArray[start_mmap_basic_pos],timeArray[end_mmap_basic_pos]); 
		if(timediffs*1.0/(1000*60)>=5){
			var starttime = timeArray[start_mmap_basic_pos];
			var endtime = timeArray[end_mmap_basic_pos];
			var marker = new MMap.Marker({   
				  id:"suspendmaker_"+m,
				  position:mmap_basic_gpsTrackPoints[start_mmap_basic_pos], //位置  
		 	      icon:"../js/amap/imgs/suspend.png",
		 	      offset:new MMap.Pixel(-8,-26),
		 	      draggable:false, //不可拖动  
		 	      autoRotation:false   //true，表示图标自动旋转
		 	}); 
			m++;
		 	mmap_basic_mapObj.addOverlays(marker);//添加点覆盖物 
		 	var markerInfo = new MMap.InfoWindow({
		 		isCustom:true,  // 使用自定义窗体
				content:InfoWindow(starttime,endtime,timediffs,""),
				autoMove:true,
				size:new MMap.Size(100 ,100),
				offset:new MMap.Pixel(-125,-200)
		    });
		 	mmap_basic_mapObj.bind(marker,"mouseover",function(){
		 		mmap_basic_geocoder.regeocode(mmap_basic_gpsTrackPoints[start_mmap_basic_pos],function(data){
		 			var resultStr = "";
		 			if(data.status=="E0"){ 
		 		        for(var i=0;i<data.list.length;i++){  
		 		            resultStr += data.list[i].province.name+data.list[i].city.name+data.list[i].district.name; //
		 		            if(data.list[i].poilist!=""){
		 		            	resultStr += data.list[i].poilist[0].address==""?(data.list[i].roadlist==""?"":data.list[i].roadlist[0].name):(data.list[i].poilist[0].address+data.list[i].poilist[0].name);
		 		            }
		 		        } 
		 		    }
		 			var address = resultStr;
		 			markerInfo.setContent(InfoWindow(starttime,endtime,timediffs,address));
		 			markerInfo.open(mmap_basic_mapObj,marker.getPosition());
		 		});
		 		//markerInfo.open(mmap_basic_mapObj,marker.getPosition());
		 	});
		}
	}
	
	if (mmap_basic_pos < 0) {  // 小于等于0 说明 轨迹运行完毕
		$("#distance").html(mmap_basic_total_distance_unit+"/"+mmap_basic_total_distance_unit);
		slider2.setValue(100);
		$("#suspend").addClass("none");
		if($("#finish").hasClass("none")){
			$("#finish").removeClass("none");
		}
		if(mmap_basic_gpsTrackMarker!=null){
			mmap_basic_geocoderadd(mmap_basic_gpsTrackMarker.getPosition());
		}
		return;  
	} // if mmap_basic_pos <= 0
	// 添加运行轨迹
	if(mmap_basic_pos>1){
		// 计算距离
		mmap_basic_distance += mmap_basic_distancebytwopoints(mmap_basic_gpsTrackPoints[mmap_basic_pos-1],mmap_basic_gpsTrackPoints[mmap_basic_pos]);
		mmap_basic_distance_unit = show_format(mmap_basic_distance);
		$("#distance").html(mmap_basic_distance_unit+"/"+mmap_basic_total_distance_unit);
	
		if(mmap_basic_total_distance!=0){
			var temp= Math.round(mmap_basic_distance*100/mmap_basic_total_distance,2);
			slider2.setValue(temp);
		}
	}
	if(!isInBounds(mmap_basic_gpsTrackPoints[mmap_basic_pos])){
		mmap_basic_mapObj.panTo(mmap_basic_gpsTrackPoints[mmap_basic_pos]);
	}
	// 添加GPS监控位置点
	mmap_basic_outTime = slider1.getValue();
	if(flag&&mmap_basic_gpsTrackMarker){
		if(mmap_basic_gpsTrackMarkerInfo.getIsOpen()){
			mmap_basic_gpsTrackMarkerInfo.close();
		}
		mmap_basic_gpsTrackMarker.moveTo(mmap_basic_gpsTrackPoints[mmap_basic_pos],mmap_basic_outTime);  // 将标注点移至下一点
	}
}
/**
 * 加载变量清空 
 */
function var_init(){
	mmap_basic_pos = 1;
	mmap_basic_distance = 0;// 当前运行距离
	map_basic_total_distance = 0;// 轨迹总距离
	mmap_basic_distance_unit = "";// 当前运行距离+单位
	mmap_basic_total_distance_unit = "";// 轨迹总距离 + 单位
	windowInfo_content = null;
	address = "";
	flag = true;
	speedArray = new Array();
	timeArray = new Array();
	deviceidArray = new Array();
	partyidArray = new Array();
	pixel_y = 0;
	m = 0;
	if(mmap_basic_gpsTrackPoints != null ||mmap_basic_gpsTrackPoints.length!=0){
		mmap_basic_gpsTrackPoints = [];
	}
	if(mmap_basic_gpsTrackMarker){
		mmap_basic_gpsTrackMarker.stopMove();
		mmap_basic_gpsTrackMarker = null;
	}
	if(mmap_basic_gpsTrackMarkerInfo){
		mmap_basic_mapObj.clearInfoWindow();
		mmap_basic_gpsTrackMarkerInfo = null;
	}
	if(mmap_basic_gpsTrackPolyLine){
		mmap_basic_gpsTrackPolyLine = null;
	}
	mmap_basic_mapObj.clearInfoWindow();
	mmap_basic_mapObj.removeOverlays(["marker","marker_0","marker_1","line_0"]);
}
/**
 * 暂停回放 zjr
 */
function map_basic_markStopMove(){
	flag = false;
	if(mmap_basic_gpsTrackMarker!=null){
		mmap_basic_gpsTrackMarker.stopMove();
		if(mmap_basic_gpsTrackMarkerInfo!=null){
			mmap_basic_geocoderadd(mmap_basic_gpsTrackMarker.getPosition());
		}
	} 
}
function cxbefore(){
	if(mmap_basic_gpsTrackMarker!=null){
		mmap_basic_gpsTrackMarker.stopMove();
	} 
	if(mmap_basic_gpsTrackMarkerInfo!=null){
		if(mmap_basic_gpsTrackMarkerInfo.getIsOpen()){
			mmap_basic_gpsTrackMarkerInfo.close();
		}
		mmap_basic_gpsTrackMarkerInfo = null;
	}
	if(mmap_basic_gpsTrackPolyLine!=null){
		mmap_basic_gpsTrackPolyLine = null;
	}
	var arr = new Array();
	for(var i=0;i<m;i++){
		arr.push("suspendmaker_"+i);
	}
	arr.push("marker");
	arr.push("marker_0");
	arr.push("marker_1");
	arr.push("line_0");
	//mmap_basic_mapObj.removeOverlays(["marker","marker_0","marker_1","line_0"]);
	mmap_basic_mapObj.removeOverlays(arr);
}
/**
 * 继续回放 zjr
 */
function map_basic_markMove_GoOn(){
	flag = true;
	if(mmap_basic_gpsTrackMarker&&mmap_basic_pos>=1){
		if(mmap_basic_gpsTrackMarkerInfo.getIsOpen()){
			mmap_basic_gpsTrackMarkerInfo.close();
		}
		mmap_basic_outTime = slider1.getValue();
		mmap_basic_gpsTrackMarker.moveTo(mmap_basic_gpsTrackPoints[mmap_basic_pos],mmap_basic_outTime);
	}
}
/**
 * 再从头开始回放 zjr
 */
function reStart_remove_PolyLine(){
	mmap_basic_mapObj.clearInfoWindow();
	
	mmap_basic_pos = 1;
	mmap_basic_distance = 0;// 当前运行距离
	address = "";
	flag = true;
	pixel_y = 0;
	if(mmap_basic_gpsTrackMarker!=null){
		mmap_basic_gpsTrackMarker.stopMove();
		mmap_basic_gpsTrackMarker = null;
	}
	if(mmap_basic_gpsTrackMarkerInfo!=null){
		mmap_basic_mapObj.clearInfoWindow();
		mmap_basic_gpsTrackMarkerInfo = null;
	}
	var arr = new Array();
	for(var i=0;i<m;i++){
		arr.push("suspendmaker_"+i);
	}
	mmap_basic_mapObj.removeOverlays(arr);
	m = 0;
	playBackStart();
}
/**
 * @function 地图两点距离  适用mapabc
 * @param LngLat1,LngLat2
 * @author zjr
 * @date 2013-12-13
 */
function mmap_basic_distancebytwopoints(lnglat1,lnglat2){
	var arr = new Array();
	arr.push(lnglat1);
	arr.push(lnglat2);
	var polyline=new MMap.Polyline({ 
		id:"line", 
		path:arr,
		strokeColor:"#00a", //线颜色 
		strokeOpacity:1, //线透明度	  
		strokeWeight:1, //线宽 	 
		strokeStyle:"solid", //线样式	 
		strokeDasharray:[10,6,3,6] //补充线样式 
	});
	mmap_basic_mapObj.addOverlays(polyline);//向地图添加覆盖物 
	var distance = polyline.getLength();
	mmap_basic_mapObj.removeOverlays("line");
	return distance;
}
/**
 * @author lj_na.li
 * @function 判断字符串的中文字符数
 * @date 2013-12-18
 * */
function GetChineseCount(str){ 
    var chinieseCount=0;
    var badChar ="ABCDEFGHIJKLMNOPQRSTUVWXYZ"; 
    badChar += "abcdefghijklmnopqrstuvwxyz"; 
    badChar += "0123456789"; 
    badChar += " "+"　";//半角与全角空格 
    badChar += "`~!@#$%^&()-_=+]\\|:;\"\\'<,>?/";//不包含*或.的英文符号 
    if(""==str){ 
        return false; 
    } 
    for(var i=0;i<=str.length;i++)
    { 
        var c = str.charAt(i);//字符串str中的字符 
        if(badChar.indexOf(c) > -1){ 
        }
        else{
            chinieseCount++;
        } 
    } 
    return chinieseCount; 
}
/**
 * @function 距离单位处理
 * @param number 数字
 * @author zjr
 */
function show_format(num){
	var disstr = String(num);
	var rettemp = '';
	if(disstr.length < 4) // 米
		rettemp = disstr + '米';
	else if(disstr.length >= 4)
		rettemp = sundry_formatFloat(num/1000,2) + '千米';
	return rettemp;
}
/**
 * 对数字进行保留几位小数处理
 * @param src  数字
 * @param pos  保留小数
 */
function sundry_formatFloat(src, pos){
    return Math.round(src*Math.pow(10, pos))/Math.pow(10, pos);
}
/**
 * @function 加载电子围栏
 * @param lbsfencelist(json)
 * @author zjr
 * @date 2014-01-09 
 */
function fenceaddtomap(fencelistdata){
	for(var i=0;i<fencelistdata.length;i++){
		//addOneMarker(fencelistdata[i].longitude,fencelistdata[i].latitude,i);//添加电子围栏中心点标注
		addOneCircle(fencelistdata[i].longitude,fencelistdata[i].latitude,fencelistdata[i].radius,i);//添加电子围栏区域圆
	}
}
//添加标注点
function addOneMarker(longitude,latitude,i){
	if(longitude==""||latitude=="") return;
	var markerOption = {
		id:"marker"+i,
	    icon:"../imgs/lbsmarkc/0.png",
	    position:new MMap.LngLat(longitude, latitude),
	    offset:new MMap.Pixel(-10,-36),
	    draggable:false  //让标注点不可以移动
	};  
	var marker = new MMap.Marker(markerOption);            
	mmap_basic_mapObj.addOverlays(marker);
}
//画圆
function addOneCircle(longitude,latitude,radius,i){
	if(longitude==""||latitude=="") return;
	if(isNaN(radius)) return;
	var circle = new MMap.Circle({  
        id:"circle"+i, //id 
        center:new MMap.LngLat(longitude,latitude),// 圆心 
        radius:radius, //半径 
        strokeColor: "#00a", //线颜色 
        strokeOpacity: 1, //线透明度 
        strokeWeight: 2, //线粗细度 
        fillColor: "#009999", //填充颜色 
        fillOpacity: 0.35 //填充透明度 
    });  
	mmap_basic_mapObj.addOverlays(circle); 
     //mmap_basic_mapObj.setFitView([circle]);
}
//定位图标定位
//若已开始回放，则将当前标注 点的位置为中心，若未开始回放，则将起始点设置为中心点
function setLocation(){
	if(mmap_basic_gpsTrackMarker!=null){//已开始回放
		mmap_basic_mapObj.panTo(mmap_basic_gpsTrackMarker.getPosition());
	}else{//未开始回放
		if(mmap_basic_gpsTrackPoints.length > 0){
			mmap_basic_mapObj.panTo(mmap_basic_gpsTrackPoints[0]);
		}
	}
	$("#float_location").addClass("none");
}
/**
 * @function 判断一个点是否在当前可视范围内
 * @return 返回一个boolean  是返回true,否返回false
 * @author zjr
 **/
function isInBounds(lnglat){
	var str = lnglat.toString();
	var longitude = parseFloat(str.split(",")[0])+0.00000001;
	var latitude = parseFloat(str.split(",")[1])+0.00000001;
	var newLnglat = new MMap.LngLat(longitude,latitude);
	var bounds=new MMap.Bounds(lnglat,newLnglat); 
	var mapbounds=mmap_basic_mapObj.getBounds(); 
	return mapbounds.intersects(bounds);
}