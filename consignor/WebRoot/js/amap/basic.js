var amap_basic_mapObj = null; // map 对象
var amap_basic_maptype = null; // 地图类型
var amap_basic_toolbar = null; // 工具条
var amap_basic_overview = null; // 鹰眼
var amap_basic_scale = null; // 比例尺
var amap_basic_mousetool = null; // 鼠标工具
var amap_basic_buildings = null; // 实例化3D楼块图层
var amap_basic_lays = null;// 图层
var amap_basic_marker = null;// 覆盖物
var amap_basic_markerinfo = {};// 车辆点标记信息
								// key(busid),value({'marker':marker,'info':infowindow})
var amap_basic_lines = {};// 轨迹点信息 key(busid),value({'id':{lng,lat}})
var amap_basic_geocoder = null;// 地理服务
var amap_basic_newinfoWindow = null;// 信息窗口
/**
 * 地图初始化 levelval 13 id mapmonitor_show_map_div map放置div lng lat 经度纬度 position
 * true | false null false null 不自动定位当前位置 true 自动定位当前位置
 */
function amap_basic_init(levelval,id,lng,lat,position){
	var opt = {
		level:levelval,                          // 初始地图视野级别
		center:new AMap.LngLat(lng,lat),         // 设置地图中心点
		doubleClickZoom:true,                    // 双击放大地图
		scrollwheel:true                         // 鼠标滚轮缩放地图

	};
	amap_basic_mapObj = new AMap.Map(id,opt); // 创建地图对象
	
	// 添加地图类型切换插件
    amap_basic_mapObj.plugin(["AMap.MapType","AMap.ToolBar","AMap.OverView","AMap.Scale","AMap.Geocoder"],function(){  
        // 地图类型切换
		amap_basic_maptype = new AMap.MapType({
			defaultType:0,
			showRoad:true
		});
        amap_basic_mapObj.addControl(amap_basic_maptype);  // 初始状态使用2D地图
		
		// 在地图中添加ToolBar插件
		amap_basic_toolbar = new AMap.ToolBar({
			//offset:new AMap.Pixel(880, 105)// 向右、向下为正
			offset:new AMap.Pixel(1180, 105)// 向右、向下为正
		});
		amap_basic_mapObj.addControl(amap_basic_toolbar);
		
		// 加载鹰眼
        amap_basic_overview = new AMap.OverView({  
            visible:true // 初始化展示鹰眼
        });  
        amap_basic_mapObj.addControl(amap_basic_overview);  
		
		// 加载比例尺插件
		amap_basic_scale = new AMap.Scale();  
        amap_basic_mapObj.addControl(amap_basic_scale); 
		
		// 加载地理服务
		amap_basic_geocoder = new AMap.Geocoder({  
			radius: 1000, // 以已知坐标为中心点，radius为半径，返回范围内兴趣点和道路信息
			extensions: "all"// 返回地址描述以及附近兴趣点和道路信息，默认“base”
		}); 
    });  
	
	amap_basic_buildings = new AMap.Buildings(); // 实例化3D楼块图层
    amap_basic_buildings.setMap(amap_basic_mapObj);// 在map中添加3D楼块图层
	
	setTimeout(function(){
		$('.amap-copyright').css('display','none');// 版本
		$('.amap-logo').css('display','none');// 高德 图片
	},500);

}

/**
 * 定位点上的基本信息 marker 标注点对象 infoWindow 弹出框对象
 */
function amap_carinfo_creatJSON(marker,infoWindow){
	var p = {
		'marker':marker,
		'infoWindow':infoWindow
	}
	return p;
}

// 刷新地图
function amap_basic_refreshmap(){
      amap_basic_mapObj.clearMap();
}

// 设置地图中心点
function amap_basic_setMapCenter(lng,lat){  
    amap_basic_mapObj.setZoomAndCenter(13,new AMap.LngLat(lng,lat));  
}

/**
 * 添加点标记 lng 经度 lat 纬度 busid carinfoJSON 展示信息 carstatus 0 停止 1 运行 2 关闭 3 报警
 * icomold 图片类型 例如 car/delineation/10_ car/delineation/11_
 */
function amap_basic_addMarker(lng,lat,busid,carinfoJSON,carstatus,icomold,content,angle){ 

	// 如果有 把原有的marker 删除
	amap_basic_deletemarker(busid);
	// 如果有把原有的信息窗体删除
	var ciJ = amap_basic_markerinfo[busid];
	if(ciJ != null){
		var cii = amap_basic_markerinfo[busid]['infoWindow'];
		if(cii != null && cii.getIsOpen() == true){
			cii.close();
		}
	}
 	// 自定义点标记内容
	var markerContent = document.createElement("div");
	markerContent.className = "markerContentStyle";
	// 点标记中的图标
	var markerImg= document.createElement("img");
	markerImg.className="markerlnglat";
	markerImg.id = busid;
	markerImg.src=amap_public_image_judge(carstatus,icomold);	
	markerImg.onload=function(){
		var angle_temp = Number(angle);
		$('#'+busid).rotate(angle_temp);
		$('#'+busid).show();
	};
	markerContent.appendChild(markerImg);
		
	 // 点标记中的文本
	// var markerSpan = document.createElement("div");
	var markerSpan = document.createElement("span");
	markerImg.className="markercontent";
	markerSpan.innerHTML = content;
	markerContent.appendChild(markerSpan);

	 
    amap_basic_marker=new AMap.Marker({ 
		id:busid,                   
		// icon:amap_public_image_judge(carstatus,icomold),
		position:new AMap.LngLat(lng,lat),
		content:markerContent   // 自定义点标记覆盖物内容
		// offset: new AMap.Pixel(-14,-30) //偏移位置
    });  
    amap_basic_marker.setMap(amap_basic_mapObj);  // 在地图上添加点
	amap_basic_markerinfo[busid] = amap_carinfo_creatJSON(amap_basic_marker,null);// 将车辆对应的标记添加到json中
	
	amap_basic_window(busid,carinfoJSON);// 鼠标移动到标记点上，展开信息窗体
    amap_basic_marker.setCursor("pointer");// 当鼠标放置标记物上时，鼠标以手指形状显示
}

/**
 * 根据车辆状态 展示不同的图标 carstatus 0 停止 1 运行 2 关闭 3 报警 icomold 图片类型 例如
 * car/delineation/10_ car/delineation/11_ return 图片路径
 */
function amap_public_image_judge(carstatus,icomold){
	var basicPath = "../page/images/default/map/spot/";
	basicPath = basicPath+icomold;
	if(carstatus == 0)
		basicPath = basicPath+'stop.png';
	else if(carstatus == 1)
		basicPath = basicPath+'travel.png';
	else if(carstatus == 2)
		basicPath = basicPath+'off.png';
	else if(carstatus == 3)
		basicPath = basicPath+'warn.png';
	return basicPath;
}

// 地图自适应显示，如果有标记物，所有标记将在视野内显示
function amap_basic_fit(){
	amap_basic_mapObj.setFitView();
}

// 实例化信息窗体
// 参数可以增加或者减少
function amap_basic_window(busid,carinfoJSON){

	
    var infoWindow = new AMap.InfoWindow({  
        isCustom:true,  // 使用自定义窗体
        content:amap_basic_NewInfoWindow(carinfoJSON),  
        size:new AMap.Size(300, 0),  
        offset:new AMap.Pixel(18, -58)// -113, -140
    });
	
	// amap_basic_newinfoWindow = infoWindow;
	
    // 鼠标移动到地图点上时显示详细信息
	if(amap_basic_markerinfo != null && amap_basic_markerinfo != {}){
		var abm = amap_basic_markerinfo[busid]['marker'];
		if(abm != null){
			amap_basic_markerinfo[busid] = amap_carinfo_creatJSON(abm,infoWindow); // {"busid":{"marker":null,"infowindow":null}}
			amap_basic_mapObj.bind(abm,"mouseover",function(e){
				infoWindow.open(amap_basic_mapObj,abm.getPosition());  
			});
			
		}
	}
}

// 删除点标记
function amap_basic_deletemarker(id){
	if(amap_basic_markerinfo != null && amap_basic_markerinfo != {}){
		var abm = amap_basic_markerinfo[id];
		if(abm != null && abm != 'undefined'){
			amap_basic_markerinfo[id]['marker'].setMap(null);
			amap_basic_markerinfo[id] == null;
			// 如果有把原有的信息窗体删除
			/*
			 * var cii = amap_basic_markerinfo[id]['infoWindow']; if(cii != null &&
			 * cii.getIsOpen() == true){ cii.close(); }
			 */
		}
	}
	
}

 
/**
 * 信息窗体中展示的内容 infoJSON
 * 
 */
function amap_basic_InfoWindow(infoJSON,speed,time,address){
	var title="GPS车辆轨迹回放相关信息";
	if(infoJSON ==null)return;
	var content = "<table width='100%' style='text-align:left;margin-top:-10px;'>";
	content += content_tr(infoJSON.carplatenumber,"车牌号");
	content += content_tr(infoJSON.carowner,"驾驶员");
	content += content_tr(infoJSON.mobilenumber,"电话号码");
	if(speed != ""){
		content += content_tr(speed,"速度");
	}
	if(time != ""){
		content += content_tr(time,"GPS时间");
	}
	if(address　!= ""){
		content += content_tr(address,"当前地址");
	}
	content += "</table>";
   return amap_basic_createInfoWindow(title,content);
}
// 生成信息窗口行信息 zjr
function content_tr(value,replaceString){
	var str = "";
	str = "<tr><td style='text-align:left' width='25%'>" + replaceString + " : "
	+ "</td><td style='text-align:left' width='75%'>" + value + "</td></tr>";
	return str;
}

/**
 * 信息窗体中展示的内容-ln carinfoJSON： json {"车牌号":buslicplate}
 * 
 */
function amap_basic_NewInfoWindow(carinfoJSON){
	var title='GPS实时轨迹跟踪车辆信息';
	var content = "";
	$.each(carinfoJSON,function(key,value){
		content += key+" : "+value+"<br/>";
	});
   
   return amap_basic_createInfoWindow(title,content);
}
 // 构建自定义信息窗体
function amap_basic_createInfoWindow(title,content){  
    var info = document.createElement("div");  
        info.className = "info";  
    // 定义顶部标题
    var top = document.createElement("div");  
        top.className = "info-top";  
    var titleD = document.createElement("div");  
        titleD.innerHTML = title;  
    var closeX = document.createElement("img");  
        closeX.src = "../js/amap/imgs/close.gif";  
        closeX.onclick = amap_basic_closeInfoWindow;  
        
		top.appendChild(titleD);  
		top.appendChild(closeX);
		info.appendChild(top);  
    // 定义中部内容
    var middle = document.createElement("div");  
		middle.className = "info-middle";  
		middle.innerHTML = content;  
		info.appendChild(middle);  
      
    // 定义底部内容
    var bottom = document.createElement("div"), sharp;  
		bottom.className = "info-bottom";  
		sharp = document.createElement("img");  
		sharp.src = "../js/amap/imgs/sharp.png";  
		bottom.appendChild(sharp);    
		info.appendChild(bottom);  
    return info;  
}  
// 关闭信息窗体
function amap_basic_closeInfoWindow(){  
    amap_basic_mapObj.clearInfoWindow();  
}

/*
 * 设置地图状态 True:允许；False：不允许 地图基本状态设置也可以合并多个参数为一个对象，同时传入
 * 如：amap_basic_mapObj.setStatus({dragEnable:true,jogEnable:false});
 */
// 是否允许拖拽地图
function isDragEnable(tag){  
	amap_basic_mapObj.setStatus({dragEnable:tag});  
}
// 是否允许键盘平移地图
function isKeyboardEnable(tag){  
	amap_basic_mapObj.setStatus({keyboardEnable:tag});  
}
// 是否允许双击放大图片
function isDoubleClickZoom(tag){  
	amap_basic_mapObj.setStatus({doubleClickZoom:tag});  
}
// 点击放大地图，地图放大一级显示
function amap_basic_ClickZoomIn(){  
    amap_basic_mapObj.zoomIn();  
}
// 点击缩小地图，地图缩小一级显示
function amap_basic_ClickZoomOut(){  
    amap_basic_mapObj.zoomOut();  
}
  // 拉宽放大地图
function amap_basic_rectZoomIn(){
	if(amap_basic_mousetool !=null){
		amap_basic_mousetool.close();
	}
   var rectOptions={     
			strokeStyle:"dashed",  
			strokeColor:"#FF33FF",  
			fillColor:"#FF99FF",  
			fillOpacity:0.5,  
			strokeOpacity:1,  
			strokeWeight:2    
    };  
    amap_basic_mapObj.plugin(["AMap.MouseTool"],function(){  
	     
         amap_basic_mousetool = new AMap.MouseTool(amap_basic_mapObj);   
         // 通过rectOptions更改拉框放大时鼠标绘制的矩形框样式
		 amap_basic_mousetool.rectZoomIn(rectOptions);     
	});  
}
// 拉宽缩小地图
function amap_basic_rectZoomOut(){
	if(amap_basic_mousetool !=null){
		amap_basic_mousetool.close();
	}
   var rectOptions={     
		strokeStyle:"dashed",  
		strokeColor:"#FF33FF",  
		fillColor:"#FF99FF",  
		fillOpacity:0.5,  
		strokeOpacity:1,  
		strokeWeight:2    
    };  
    amap_basic_mapObj.plugin(["AMap.MouseTool"],function(){   
         amap_basic_mousetool = new AMap.MouseTool(amap_basic_mapObj);   
         amap_basic_mousetool.rectZoomOut(rectOptions);     // 通过rectOptions更改拉框缩小时鼠标绘制的矩形框样式
        });  
}

// 测量距离
function amap_basic_mouseToolRule(){
	if(amap_basic_mousetool !=null){
		amap_basic_mousetool.close();
	}
	var lineOptions={     
		strokeStyle:"solid",  
		strokeColor:"#FF33FF",    
		strokeOpacity:1,  
		strokeWeight:2    
		};  
    amap_basic_mapObj.plugin(["AMap.MouseTool"],function(){   
         amap_basic_mousetool = new AMap.MouseTool(amap_basic_mapObj);   
         amap_basic_mousetool.rule(lineOptions);     // 通过lineOptions更改量测线的样式
		 AMap.event.addListener(amap_basic_mousetool,"draw",function(e){
         var drawObj = e.obj;
		document.getElementById('line_l').value = Round1(e.obj.getLength()/1000,2);
      });
		   
        });  
}

// 测量多边形面积
function amap_basic_measurePolygonArea(){
	if(amap_basic_mousetool !=null){
		amap_basic_mousetool.close();
	}
    // 设置折线的属性
    var polygonOption = {  
		strokeColor:"#FF33FF",    
		strokeOpacity:1,  
		strokeWeight:2    
    };  
	amap_basic_mapObj.plugin(["AMap.MouseTool"],function(){          
			amap_basic_mousetool = new AMap.MouseTool(amap_basic_mapObj);   
			amap_basic_mousetool.measureArea(polygonOption);  // 调用鼠标工具的面积量测功能
			
			AMap.event.addListener(amap_basic_mousetool,"draw",function(e){
        var drawObj = e.obj;
			document.getElementById('polygon_a').value = Round1(e.obj.getArea()/1000000,2);
   });
		  
    });  
	
}

// 测量圆形面积

function amap_basic_measureCircleArea(){  
	if(amap_basic_mousetool !=null){
		amap_basic_mousetool.close();
	}
    // 设置圆的属性
    var circleOption = {  
		strokeColor:"#FF33FF",  
		fillColor:"#FF99FF",  
		fillOpacity:0.5,  
		strokeOpacity:1,  
		strokeWeight:2    
    };
	amap_basic_mapObj.plugin(["AMap.MouseTool"],function(){          
        amap_basic_mousetool = new AMap.MouseTool(amap_basic_mapObj); 
		amap_basic_mousetool.circle(circleOption); 
		
		AMap.event.addListener(amap_basic_mousetool,"draw",function(e){
			var drawObj = e.obj;  // obj属性就是绘制完成的覆盖物对象。
			var r = e.obj.getRadius();
				document.getElementById('circle_r').value = Round1(r/1000,2);
				document.getElementById('circle_a').value = Round1(3.14*r*r/1000000,2);
			var infoWindow_circle = new AMap.InfoWindow({  
				isCustom:true,  // 使用自定义窗体
                content:createInfoWindow_circle("半径："+Round1(r/1000,2)+"千米<br/>圆面积："+Round1(3.14*r*r/1000000,2)+"平方公里"),  
                size:new AMap.Size(80, 0),  
                offset:new AMap.Pixel(150, -20)// -113, -140
    }); 
	   infoWindow_circle.open(amap_basic_mapObj,e.obj.getCenter()); 
		}); 
    });  
}
// 自定义圆形信息窗体
function createInfoWindow_circle(content){  
    var info = document.createElement("div");  
        info.className = "info";  
    // 定义中部内容
    var middle = document.createElement("div");  
        middle.className = "info-middle";  
        middle.innerHTML = content;  
        info.appendChild(middle);  
    return info;  
}  
// 获取地图上的经纬度
function amap_basic_accessLngLat(){
	if(amap_basic_mousetool !=null){
		amap_basic_mousetool.close();
	}
	var clickEventListener=AMap.event.addListener(amap_basic_mapObj,'click',function(e){  
        document.getElementById("lngX").value=e.lnglat.getLng();  
        document.getElementById("latY").value=e.lnglat.getLat();    
    });  

}

 amap_basic_lines = {"busid":"1","id":[{"lng":"120.18904","lat":"30.213423"},
{"lng":"120.382122","lat":"30.201176"}]
};

// 绘制轨迹
function amap_basic_polyline(){
   amap_basic_marker = new AMap.Marker({
			map:amap_basic_mapObj,
			position:new AMap.LngLat(amap_basic_lines.id[0].lng,amap_basic_lines.id[0].lat),// 基点位置
			icon:"../js/amap/imgs/car.png", // marker图标，直接传递地址url
			offset:new AMap.Pixel(-8,-8), // 相对于基点的位置
			autoRotation:true
		});
		lineArr = new Array(); 
	    var i=0;
		$.each(amap_basic_lines,function(){
			  lineArr.push(new AMap.LngLat(amap_basic_lines.id[i].lng,amap_basic_lines.id[i].lat));
			  i = i+1
	    });
		// 绘制轨迹
		var polyline=new AMap.Polyline({
			map:amap_basic_mapObj,
			path:lineArr,
			strokeColor:"#00A",// 线颜色
			strokeOpacity:1,// 线透明度
			strokeWeight:3,// 线宽
			strokeStyle:"solid",// 线样式
		});
}
// 开始轨迹回放
function amap_basic_startAnimation(){
    amap_basic_marker.moveAlong(lineArr,150,null,false);
	// amap_basic_marker.moveTo(new
	// AMap.LngLat(amap_basic_lines.id[1].lng,amap_basic_lines.id[1].lat),80,null);
}

function amap_basic_stopAnimation(){
    amap_basic_marker.stopMove();
}

var amap_basic_gpsTrackPoints = [];  // GPS监控轨迹点集合
var amap_basic_gpsTrackMarker = null; // GPS监控实物点标注对象
var amap_basic_gpsTrackMarkerInfo = null;  // GPS监控实物点信息窗口
var amap_basic_gpsTrackPolyLine = null; // GPS监控实物点轨迹对象
var amap_basic_infoWindow_content = ""; // 信息窗体内容
var amap_basic_distance = 0;// 当前运行距离
var amap_basic_total_distance = 0;// 轨迹总距离
var amap_basic_distance_unit = "";// 当前运行距离+单位
var amap_basic_total_distance_unit = "";// 轨迹总距离 + 单位
var flag = true;// 标注运行标志
var windowInfo_content = null;
var amap_polyline_suspend_distance = 500;// 轨迹中断的最短距离
var speedArray = new Array();
var timeArray = new Array();
var amap_basic_point_index = 0;
var address = "";
// gps运行轨迹
function amap_basic_addGpsTrack_polyline(gpsdata,infodata){
	var_init();
	windowInfo_content = infodata;
	var pre_speed = "";
	var cur_speed = "";
	if(gpsdata!=null&& gpsdata.length!=0){
		for(var i=0; i<gpsdata.length;i++){
			cur_speed = gpsdata[i].speed;
			if(cur_speed == pre_speed && cur_speed == "0") {
				continue;
			}else {
				pre_speed = gpsdata[i].speed;
			}
			var dimX = parseFloat(gpsdata[i].longitude);
			var dimY = parseFloat(gpsdata[i].latitude);
			var gpsXY = sp_gpsfix_init.transform(dimX,dimY);
			amap_basic_gpsTrackPoints.push(new AMap.LngLat(parseFloat(gpsXY.X), parseFloat(gpsXY.Y)));  
			speedArray.push(gpsdata[i].speed);
			timeArray.push(gpsdata[i].realtime.replace(".0",""));
		}// for
	}// if gpsdata==null || gpsdata.length==0

	 if (amap_basic_gpsTrackPoints.length > 0) {  
	 	// 计算轨迹总距离
		amap_basic_total_distance_unit=map_basic_measureDistance(amap_basic_gpsTrackPoints);
		$("#distance").html("0/"+amap_basic_total_distance_unit);
		
		// 添加地图标注点
	 	amap_basic_gpsTrackMarker = new AMap.Marker({
			map:amap_basic_mapObj,
			position:amap_basic_gpsTrackPoints[0],// 基点位置
			icon:"../js/amap/imgs/car.png", // marker图标，直接传递地址url
			offset:new AMap.Pixel(-8,-8), // 相对于基点的位置
			autoRotation:true
		}); 
	 	// 注册单击事件
	 	AMap.event.addListener(amap_basic_gpsTrackMarker,"click",function(e){
	 		var pos = amap_basic_pos - 1;
	 		if(pos<0) pos = amap_basic_gpsTrackPoints.length-1;
	 		amap_basic_gpsTrackMarkerInfo.open(amap_basic_mapObj,amap_basic_gpsTrackPoints[pos]);
	 	});
	 	// 循环调用画线
		AMap.event.addListener(amap_basic_gpsTrackMarker,"moveend",function(e){
			var cp = amap_basic_gpsTrackPoints[amap_basic_pos];  
			if (amap_basic_mapObj.getBounds().contains(cp)) {  // 当前地图是否存在cp点
				amap_basic_mapObj.panTo(cp);// 监控该点 将中心点变换到指定位置
			} 
			// 修改信息窗口中gps时间和速度
			amap_basic_gpsTrackMarkerInfo.setContent(amap_basic_InfoWindow(infodata,speedArray[amap_basic_pos],timeArray[amap_basic_pos],""));
			amap_basic_gpsTrackMarkerInfo.open(amap_basic_mapObj,amap_basic_gpsTrackPoints[amap_basic_pos]);  
			amap_basic_pos = amap_basic_pos+1;
			setTimeout("amap_basic_addGpsTrack()",100);// 标注停留300毫秒
		});
		amap_basic_gpsTrackMarkerInfo = new AMap.InfoWindow({  
			isCustom:true,  // 使用自定义窗体
			content:amap_basic_InfoWindow(infodata,speedArray[0],timeArray[0],""),
			size:new AMap.Size(100, 320),  
			offset:new AMap.Pixel(20, -30)// -113, -140
    	});
		// 打开信息窗体
		amap_basic_gpsTrackMarkerInfo.open(amap_basic_mapObj,amap_basic_gpsTrackPoints[0]);  
		amap_basic_mapObj.panTo(amap_basic_gpsTrackPoints[0]);
		amap_basic_addGpsTrack(); // 第二个开始
	} // if amap_basic_gpsTrackPoints.length > 0
}

/**
 * 添加GPS轨迹. 就是运行 @ 通过pos 在basic_se_parseGpsData_gpsTrackPoints数组中从哪一位开始
 */
var amap_basic_outTime = 80; // 运行速度
var amap_basic_pos = 1;// 第二个开始
// 两点之间画轨迹
function amap_basic_addGpsTrack(){
	amap_basic_pos = (amap_basic_pos >= amap_basic_gpsTrackPoints.length ? 0 : amap_basic_pos); // 不能超过总长度
	if (amap_basic_pos <= 0) {  // 小于等于0 说明 轨迹运行完毕
		$("#distance").html(amap_basic_total_distance_unit+"/"+amap_basic_total_distance_unit);
		slider2.setValue(100);
		amap_basic_geocoderadd_byLnglat(amap_basic_gpsTrackMarkerInfo.getPosition());
		return;  
	} // if amap_basic_pos <= 0
	
	// 添加运行轨迹
	if(amap_basic_gpsTrackPolyLine!=null){
		// 计算距离
		// amap_basic_distance = amap_basic_gpsTrackPolyLine.getLength();
		amap_basic_distance += amap_basic_gpsTrackPoints[amap_basic_pos-2].distance(amap_basic_gpsTrackPoints[amap_basic_pos-1]);
		amap_basic_distance_unit = show_format(amap_basic_distance);
		$("#distance").html(amap_basic_distance_unit+"/"+amap_basic_total_distance_unit);
		
		
		if(amap_basic_total_distance!=0){
			var temp= Math.round(amap_basic_distance*100/amap_basic_total_distance,2);
			slider2.setValue(temp);
		}
	}
	var distance = amap_basic_gpsTrackPoints[amap_basic_pos-1].distance(amap_basic_gpsTrackPoints[amap_basic_pos]);
	if(distance>=amap_polyline_suspend_distance){
		amap_basic_point_index = amap_basic_pos;
		amap_basic_gpsTrackPolyLine = null;
		amap_basic_gpsTrackMarker.setPosition(amap_basic_gpsTrackPoints[amap_basic_pos]);  // 将标注点移至下一点
		amap_basic_gpsTrackMarkerInfo.setContent(amap_basic_InfoWindow(windowInfo_content,speedArray[amap_basic_pos],timeArray[amap_basic_pos],""));
		amap_basic_gpsTrackMarkerInfo.open(amap_basic_mapObj,amap_basic_gpsTrackPoints[amap_basic_pos]);  
		var cp = amap_basic_gpsTrackPoints[amap_basic_pos];  
		if (amap_basic_mapObj.getBounds().contains(cp)) {  // 当前地图是否存在cp点
			amap_basic_mapObj.panTo(cp);// 监控该点 将中心点变换到指定位置
		} 
		amap_basic_pos = amap_basic_pos+1;
		amap_basic_addGpsTrack();
	}else{
		if(amap_basic_gpsTrackPolyLine){
			amap_basic_gpsTrackPolyLine.setMap(null);
		}
		// 绘制轨迹
		lineArr = amap_basic_gpsTrackPoints.slice(amap_basic_point_index,amap_basic_pos+1);
		amap_basic_gpsTrackPolyLine=new AMap.Polyline({
			map:amap_basic_mapObj,
			path:lineArr,
			strokeColor:"#00A",// 线颜色
			strokeOpacity:1,// 线透明度
			strokeWeight:3,// 线宽
			strokeStyle:"solid",// 线样式
		});
		// 添加GPS监控位置点

		var cp = amap_basic_gpsTrackPoints[amap_basic_pos];  
		if (amap_basic_mapObj.getBounds().contains(cp)) {  // 当前地图是否存在cp点
			amap_basic_mapObj.panTo(cp);// 监控该点 将中心点变换到指定位置
		} 

		amap_basic_outTime = slider1.getValue();
		if(flag&&amap_basic_gpsTrackMarker){
			amap_basic_gpsTrackMarker.moveTo(amap_basic_gpsTrackPoints[amap_basic_pos],amap_basic_outTime);  // 将标注点移至下一点
			amap_basic_gpsTrackMarkerInfo.setPosition(amap_basic_gpsTrackPoints[amap_basic_pos-1]); 
		}
	}
}
/**
 * 加载变量清空
 */
function var_init(){
	amap_basic_pos = 1;
	amap_basic_distance = 0;// 当前运行距离
	map_basic_total_distance = 0;// 轨迹总距离
	amap_basic_distance_unit = "";// 当前运行距离+单位
	amap_basic_total_distance_unit = "";// 轨迹总距离 + 单位
	windowInfo_content = null;
	amap_basic_point_index = 0;
	flag = true;
	speedList = new Array();
	timeList = new Array();
	
	if(amap_basic_gpsTrackPoints != null ||amap_basic_gpsTrackPoints.length!=0){
		amap_basic_gpsTrackPoints = [];
	}
	if(amap_basic_gpsTrackMarker){
		amap_basic_gpsTrackMarker.stopMove();
		amap_basic_gpsTrackMarker.setMap(null);
		amap_basic_gpsTrackMarker = null;
	}
	if(amap_basic_gpsTrackMarkerInfo){
		amap_basic_mapObj.clearInfoWindow();
		amap_basic_gpsTrackMarkerInfo = null;
	}
	if(amap_basic_gpsTrackPolyLine){
		amap_basic_gpsTrackPolyLine.setMap(null);
		amap_basic_gpsTrackPolyLine = null;
	}
}
/**
 * 暂停回放 zjr
 */
function map_basic_markStopMove(){
	flag = false;
	if(amap_basic_gpsTrackMarker){
		amap_basic_gpsTrackMarker.stopMove();
	} 
	if(amap_basic_gpsTrackMarkerInfo){
		amap_basic_geocoderadd_byLnglat(amap_basic_gpsTrackMarkerInfo.getPosition());
	}
}
/**
 * 继续回放 zjr
 */
function map_basic_markMove_GoOn(){
	flag = true;
	if(amap_basic_gpsTrackMarker){
		amap_basic_outTime = slider1.getValue();
		amap_basic_gpsTrackMarker.moveTo(amap_basic_gpsTrackPoints[amap_basic_pos],amap_basic_outTime);
	}
}
/**
 * 再从头开始回放 zjr
 */
function reStart_remove_PolyLine(){
	if(amap_basic_gpsTrackMarker){
		amap_basic_gpsTrackMarker.stopMove();
		amap_basic_gpsTrackMarker.setMap(null);
		amap_basic_gpsTrackMarker = null;
	}
	amap_basic_mapObj.clearInfoWindow();
	if(amap_basic_gpsTrackPolyLine){
		amap_basic_point_index = amap_basic_pos;
		amap_basic_gpsTrackPolyLine.setMap(null);
		amap_basic_gpsTrackPolyLine = null;
	}
	amap_basic_mapObj.clearMap();
}
/**
 * 地图多点之间总的距离 zjr
 * 
 * @param LngLats
 *            经纬度数组
 */
function map_basic_measureDistance(LngLats){
	var distance = 0;
	var temp_dis = 0;
	if(LngLats == null||LngLats.length<=1) return "0";
	for(var i=0;i<LngLats.length-1;i++){
		temp = LngLats[i].distance(LngLats[i+1]);
		if(temp<amap_polyline_suspend_distance)	distance = distance+temp;
	}
	amap_basic_total_distance = distance;
	return show_format(distance);
}
/**
 * 距离单位处理 zjr
 * 
 * @param number
 *            数字
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
 * 
 * @param src
 *            数字
 * @param pos
 *            保留小数
 */
function sundry_formatFloat(src, pos){
    return Math.round(src*Math.pow(10, pos))/Math.pow(10, pos);
}




/**
 * 添加点标记 lng 经度 lat 纬度 busid carinfoJSON 展示信息 icomold 图片类型 例如
 * car/delineation/10_ car/delineation/11_
 */
function amap_basic_addLbsMarker(lng,lat,busid,carinfoJSON,content){ 
	var dimX = parseFloat(lng) + "";
	var dimY = parseFloat(lat) + "";
	var gpsXY = sp_gpsfix_init.transform(dimX,dimY);
    var newlng = parseFloat(gpsXY.X);
    var newlat = parseFloat(gpsXY.Y);
	// 如果有 把原有的marker 删除
	amap_basic_deletemarker(busid);
	// 如果有把原有的信息窗体删除
	/*
	 * var ciJ = amap_basic_markerinfo[busid]; if(ciJ != null){ var cii =
	 * amap_basic_markerinfo[busid]['infoWindow']; if(cii != null &&
	 * cii.getIsOpen() == true){ cii.close(); } }
	 */
 	// 自定义点标记内容
	var markerContent = document.createElement("div");
	markerContent.className = "markerContentStyle";
	// 点标记中的图标
	var markerImg= document.createElement("img");
	markerImg.className="markerlnglat";
	markerImg.id = busid;
	markerImg.src="http://webapi.amap.com/images/0.png";// "http://webapi.amap.com/images/marker_sprite.png";//
	markerContent.appendChild(markerImg);
		
	 // 点标记中的文本
	// var markerSpan = document.createElement("div");
	var markerSpan = document.createElement("span");
	markerImg.className="markercontent";
	markerSpan.innerHTML = content;
	markerContent.appendChild(markerSpan);

	 
    amap_basic_marker=new AMap.Marker({ 
		id:busid,                   
		// icon:amap_public_image_judge(carstatus,icomold),
		position:new AMap.LngLat(newlng,newlat),
		content:markerContent,   // 自定义点标记覆盖物内容
		// offset: new AMap.Pixel(-14,-30) //偏移位置
    });  
    amap_basic_marker.setMap(amap_basic_mapObj);  // 在地图上添加点
	amap_basic_markerinfo[busid] = amap_carinfo_creatJSON(amap_basic_marker,null);// 将车辆对应的标记添加到json中
	
	amap_basic_window(busid,carinfoJSON);// 鼠标移动到标记点上，展开信息窗体
    amap_basic_marker.setCursor("pointer");// 当鼠标放置标记物上时，鼠标以手指形状显示
}

// 根据经纬度获取详细地址


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

/**
 * 添加点标记 lng 经度 lat 纬度 busid carinfoJSON 展示信息 icomold 图片类型 例如
 * car/delineation/10_ car/delineation/11_
 */
function amap_basic_addLbsMarker_new(lng,lat,busid,carinfoJSON,content){ 
	var dimX = parseFloat(lng);
	var dimY = parseFloat(lat);
	var gpsXY = sp_gpsfix_init.transform(dimX,dimY);
    var newlng = parseFloat(gpsXY.X);
    var newlat = parseFloat(gpsXY.Y);
	// 如果有 把原有的marker 删除
	amap_basic_deletemarker(busid);
	// alert(1);
	// 如果有把原有的信息窗体删除
	var ciJ = amap_basic_markerinfo[busid];
	if(ciJ != null && ciJ !=""){
		var cii = amap_basic_markerinfo[busid]['infoWindow'];
		if(cii != null && cii.getIsOpen()==true){
			cii.close();
			// alert("内部");
			// cii.setPosition(new AMap.LngLat(newlng,newlat));
		}
	}
 	// 自定义点标记内容
	var markerContent = document.createElement("div");
	markerContent.className = "markerContentStyle";
	// 点标记中的图标
	var markerImg= document.createElement("img");
	markerImg.className="markerlnglat";
	markerImg.id = busid;
	markerImg.src="http://webapi.amap.com/images/0.png";// "http://webapi.amap.com/images/marker_sprite.png";//
	markerContent.appendChild(markerImg);
		
	 // 点标记中的文本
	// var markerSpan = document.createElement("div");
	var markerSpan = document.createElement("span");
	markerImg.className="markercontent";
	markerSpan.innerHTML = content;
	markerContent.appendChild(markerSpan);

	 
    amap_basic_marker=new AMap.Marker({ 
		id:busid,                   
	    position:new AMap.LngLat(newlng,newlat),
		content:markerContent   // 自定义点标记覆盖物内容
		// offset: new AMap.Pixel(-14,-30) //偏移位置
    });  
    amap_basic_marker.setMap(amap_basic_mapObj);  // 在地图上添加点
	amap_basic_markerinfo[busid] = amap_carinfo_creatJSON(amap_basic_marker,null);// 将车辆对应的标记添加到json中
	
	amap_basic_window_new(busid,carinfoJSON,lng,lat);// 鼠标移动到标记点上，展开信息窗体
    amap_basic_marker.setCursor("pointer");// 当鼠标放置标记物上时，鼠标以手指形状显示
}
// 实例化信息窗体
// 参数可以增加或者减少
function amap_basic_window_new(busid,carinfoJSON,lng,lat){
	
 // 鼠标移动到地图点上时显示详细信息
	if(amap_basic_markerinfo != null && amap_basic_markerinfo != {}){
		var abm = amap_basic_markerinfo[busid]['marker'];
		if(abm != null){
				  amap_basic_mapObj.bind(abm,"mouseover",function(e){
					  amap_basic_geocoder.getAddress(new AMap.LngLat(lng,lat)); 
			 			// 返回地理编码结果
			 			AMap.event.addListener(amap_basic_geocoder, "complete", function(data){
			 				// 返回地址描述
							  carinfoJSON['当前位置'] = data.regeocode.formattedAddress;
							  var infoWindow = "";
							  infoWindow = new AMap.InfoWindow({  
								     isCustom:true,  // 使用自定义窗体
								     content:amap_basic_NewInfoWindow(carinfoJSON),  
								     size:new AMap.Size(300, 0),  
								     offset:new AMap.Pixel(18, -58)// -113,
																	// -140
								 });
						 amap_basic_markerinfo[busid] = amap_carinfo_creatJSON(abm,infoWindow); // {"busid":{"marker":null,"infowindow":null}}
						 infoWindow.open(amap_basic_mapObj,abm.getPosition());
					});
 			});   
			
		}
	}
}

function sleep(numberMillis) {   
	var now = new Date();   
	var exitTime = now.getTime() + numberMillis;  
	while (true) {
	   now = new Date();      
	   if (now.getTime() > exitTime)
	   return;   
	}
} 
/**
 * 逆地理编码 zjr
 * 参数 LngLat
 */
function amap_basic_geocoderadd_byLnglat(lnglat){
	// 逆地理编码
	amap_basic_geocoder.getAddress(lnglat); 
	// 返回地理编码结果
	AMap.event.addListener(amap_basic_geocoder, "complete", function(data){
		address = data.regeocode.formattedAddress.trim();
		amap_basic_gpsTrackMarkerInfo.setContent(amap_basic_InfoWindow(windowInfo_content,speedArray[amap_basic_pos],timeArray[amap_basic_pos],address));
	});
}