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
								// key(busid),value({'marker':marker,'info':infowindow})
var mmap_basic_lines = {};// 轨迹点信息 key(busid),value({'id':{lng,lat}})
var mmap_basic_geocoder = null;// 地理服务
var mmap_basic_newinfoWindow = null;// 信息窗口


//当浏览器宽度发生改变的时候，狼牙棒工具条位置自动发生变化
$(window).resize(function() {
    var width = $(this).width();
    var height = $(this).height();
    if(mmap_basic_toolbar){
    	mmap_basic_toolbar.setOffset(new MMap.Pixel(width-70,10));
    }
});

/**
 * 地图初始化 levelval 13 id mapmonitor_show_map_div map放置div lng lat 经度纬度 position
 * true | false null false null 不自动定位当前位置 true 自动定位当前位置
 */
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
    if($("#float_location").length>=1){
    	 mmap_basic_mapObj.bind(mmap_basic_mapObj,"dragend",function(){
    	    if($("#float_location").hasClass("none")){
    	    	$("#float_location").removeClass("none");
    	    }
    	});
    }
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

/**
 * 添加点标记 lng 经度 lat 纬度 busid carinfoJSON 展示信息 carstatus 0 停止 1 运行 2 关闭 3 报警
 * icomold 图片类型 例如 car/delineation/10_ car/delineation/11_
 */
function mmap_basic_addMarker(lng,lat,busid,carinfoJSON,carstatus,icomold,content,angle){ 

	// 如果有 把原有的marker 删除
	mmap_basic_deletemarker(busid);
	// 如果有把原有的信息窗体删除
	var ciJ = mmap_basic_markerinfo[busid];
	if(ciJ != null){
		var cii = mmap_basic_markerinfo[busid]['infoWindow'];
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
	markerImg.src=mmap_public_image_judge(carstatus,icomold);	
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

	 
    mmap_basic_marker=new MMap.Marker({ 
		id:busid,                   
		// icon:mmap_public_image_judge(carstatus,icomold),
		position:new MMap.LngLat(lng,lat),
		cursor:"../MapControl/openhand.cur",//鼠标悬停时显示的光标 
		content:markerContent   // 自定义点标记覆盖物内容
		// offset: new MMap.Pixel(-14,-30) //偏移位置
    });  
   // mmap_basic_marker.setMap(mmap_basic_mapObj);  // 在地图上添加点
    mmap_basic_mapObj.addOverlays(mmap_basic_marker);
	mmap_basic_markerinfo[busid] = mmap_carinfo_creatJSON(mmap_basic_marker,null);// 将车辆对应的标记添加到json中
	
	mmap_basic_window(busid,carinfoJSON);// 鼠标移动到标记点上，展开信息窗体
   // mmap_basic_marker.setCursor("pointer");// 当鼠标放置标记物上时，鼠标以手指形状显示
}

/**
 * 根据车辆状态 展示不同的图标 carstatus 0 停止 1 运行 2 关闭 3 报警 icomold 图片类型 例如
 * car/delineation/10_ car/delineation/11_ return 图片路径
 */
function mmap_public_image_judge(carstatus,icomold){
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
function mmap_basic_fit(){
	mmap_basic_mapObj.setFitView();
}

// 实例化信息窗体
// 参数可以增加或者减少
function mmap_basic_window(busid,carinfoJSON){

	
    var infoWindow = new MMap.InfoWindow({  
        isCustom:true,  // 使用自定义窗体
        content:mmap_basic_NewInfoWindow(carinfoJSON),  
        size:new MMap.Size(300, 0),  
        offset:new MMap.Pixel(18, -58)// -113, -140
    });
	
	// mmap_basic_newinfoWindow = infoWindow;
	
    // 鼠标移动到地图点上时显示详细信息
	if(mmap_basic_markerinfo != null && mmap_basic_markerinfo != {}){
		var abm = mmap_basic_markerinfo[busid]['marker'];
		if(abm != null){
			mmap_basic_markerinfo[busid] = mmap_carinfo_creatJSON(abm,infoWindow); // {"busid":{"marker":null,"infowindow":null}}
			mmap_basic_mapObj.bind(abm,"mouseover",function(e){
				infoWindow.open(mmap_basic_mapObj,abm.getPosition());  
			});
			
		}
	}
}

// 删除点标记
function mmap_basic_deletemarker(id){
	if(mmap_basic_markerinfo != null && mmap_basic_markerinfo != {}){
		var abm = mmap_basic_markerinfo[id];
		if(abm != null && abm != 'undefined'){
			//mmap_basic_markerinfo[id]['marker'].setMap(null);
			mmap_basic_mapObj.removeOverlays(mmap_basic_markerinfo[id]['marker']);
			mmap_basic_markerinfo[id] == null;
			// 如果有把原有的信息窗体删除
			/*
			 * var cii = mmap_basic_markerinfo[id]['infoWindow']; if(cii != null &&
			 * cii.getIsOpen() == true){ cii.close(); }
			 */
		}
	}
	
}

 
/**
 * 信息窗体中展示的内容 infoJSON
 * 
 */
function mmap_basic_InfoWindow(infoJSON,speed,time,address){
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
   return mmap_basic_createInfoWindow(title,content);
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
function mmap_basic_NewInfoWindow(carinfoJSON){
	var title='GPS实时轨迹跟踪车辆信息';
	var content = "";
	$.each(carinfoJSON,function(key,value){
		content += key+" : "+value+"<br/>";
	});
   return mmap_basic_createInfoWindow(title,content);
}
 // 构建自定义信息窗体
function mmap_basic_createInfoWindow(title,content){  
	var m = [];
	   m.push("<div class='info'>");// s info
	   m.push("<div class='info-top'>");//s info-top
	   // 定义顶部标题
	   m.push("<div>"+title+"</div><img src='../js/amap/imgs/close.gif' onclick='mmap_basic_mapObj.clearInfoWindow();'/>"); // info-top content
	   m.push("</div>");// e info-top
	   // 定义中部内容
	   m.push("<div class='info-middle'>"+content+"</div>");
	   // 定义底部内容
	   m.push("<div class='info-bottom'><img src='../js/amap/imgs/sharp.png'/></div>");
	   m.push("</div>");// e info
	return m.join("");  
	
	/* var info = document.createElement("div");  
        info.className = "info";  
    // 定义顶部标题
    var top = document.createElement("div");  
        top.className = "info-top";  
    var titleD = document.createElement("div");  
        titleD.innerHTML = title;  
    var closeX = document.createElement("img");  
        closeX.src = "../js/amap/imgs/close.gif";  
        closeX.onclick = mmap_basic_closeInfoWindow;  
        
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
    return info;*/  
}  
// 关闭信息窗体
function mmap_basic_closeInfoWindow(){  
    mmap_basic_mapObj.clearInfoWindow();  
}

/*
 * 设置地图状态 True:允许；False：不允许 地图基本状态设置也可以合并多个参数为一个对象，同时传入
 * 如：mmap_basic_mapObj.setStatus({dragEnable:true,jogEnable:false});
 */
// 是否允许拖拽地图
function isDragEnable(tag){  
	mmap_basic_mapObj.setStatus({dragEnable:tag});  
}
// 是否允许键盘平移地图
function isKeyboardEnable(tag){  
	mmap_basic_mapObj.setStatus({keyboardEnable:tag});  
}
// 是否允许双击放大图片
function isDoubleClickZoom(tag){  
	mmap_basic_mapObj.setStatus({doubleClickZoom:tag});  
}
// 点击放大地图，地图放大一级显示
function mmap_basic_ClickZoomIn(){  
    mmap_basic_mapObj.zoomIn();  
}
// 点击缩小地图，地图缩小一级显示
function mmap_basic_ClickZoomOut(){  
    mmap_basic_mapObj.zoomOut();  
}
  // 拉宽放大地图
function mmap_basic_rectZoomIn(){
	if(mmap_basic_mousetool !=null){
		mmap_basic_mousetool.close();
	}
   var rectOptions={     
			strokeStyle:"dashed",  
			strokeColor:"#FF33FF",  
			fillColor:"#FF99FF",  
			fillOpacity:0.5,  
			strokeOpacity:1,  
			strokeWeight:2    
    };  
    mmap_basic_mapObj.plugin(["MMap.MouseTool"],function(){  
	     
         mmap_basic_mousetool = new MMap.MouseTool(mmap_basic_mapObj);   
         // 通过rectOptions更改拉框放大时鼠标绘制的矩形框样式
		 mmap_basic_mousetool.rectZoomIn(rectOptions);     
	});  
}
// 拉宽缩小地图
function mmap_basic_rectZoomOut(){
	if(mmap_basic_mousetool !=null){
		mmap_basic_mousetool.close();
	}
   var rectOptions={     
		strokeStyle:"dashed",  
		strokeColor:"#FF33FF",  
		fillColor:"#FF99FF",  
		fillOpacity:0.5,  
		strokeOpacity:1,  
		strokeWeight:2    
    };  
    mmap_basic_mapObj.plugin(["MMap.MouseTool"],function(){   
         mmap_basic_mousetool = new MMap.MouseTool(mmap_basic_mapObj);   
         mmap_basic_mousetool.rectZoomOut(rectOptions);     // 通过rectOptions更改拉框缩小时鼠标绘制的矩形框样式
        });  
}

// 测量距离
function mmap_basic_mouseToolRule(){
	if(mmap_basic_mousetool !=null){
		mmap_basic_mousetool.close();
	}
	var lineOptions={     
		strokeStyle:"solid",  
		strokeColor:"#FF33FF",    
		strokeOpacity:1,  
		strokeWeight:2    
		};  
    mmap_basic_mapObj.plugin(["MMap.MouseTool"],function(){   
         mmap_basic_mousetool = new MMap.MouseTool(mmap_basic_mapObj);   
         mmap_basic_mousetool.rule(lineOptions);     // 通过lineOptions更改量测线的样式
		 MMap.event.addListener(mmap_basic_mousetool,"draw",function(e){
         var drawObj = e.obj;
		document.getElementById('line_l').value = Round1(e.obj.getLength()/1000,2);
      });
		   
        });  
}

// 测量多边形面积
function mmap_basic_measurePolygonArea(){
	if(mmap_basic_mousetool !=null){
		mmap_basic_mousetool.close();
	}
    // 设置折线的属性
    var polygonOption = {  
		strokeColor:"#FF33FF",    
		strokeOpacity:1,  
		strokeWeight:2    
    };  
	mmap_basic_mapObj.plugin(["MMap.MouseTool"],function(){          
			mmap_basic_mousetool = new MMap.MouseTool(mmap_basic_mapObj);   
			mmap_basic_mousetool.measureArea(polygonOption);  // 调用鼠标工具的面积量测功能
			
			MMap.event.addListener(mmap_basic_mousetool,"draw",function(e){
        var drawObj = e.obj;
			document.getElementById('polygon_a').value = Round1(e.obj.getArea()/1000000,2);
   });
		  
    });  
	
}

// 测量圆形面积

function mmap_basic_measureCircleArea(){  
	if(mmap_basic_mousetool !=null){
		mmap_basic_mousetool.close();
	}
    // 设置圆的属性
    var circleOption = {  
		strokeColor:"#FF33FF",  
		fillColor:"#FF99FF",  
		fillOpacity:0.5,  
		strokeOpacity:1,  
		strokeWeight:2    
    };
	mmap_basic_mapObj.plugin(["MMap.MouseTool"],function(){          
        mmap_basic_mousetool = new MMap.MouseTool(mmap_basic_mapObj); 
		mmap_basic_mousetool.circle(circleOption); 
		
		MMap.event.addListener(mmap_basic_mousetool,"draw",function(e){
			var drawObj = e.obj;  // obj属性就是绘制完成的覆盖物对象。
			var r = e.obj.getRadius();
				document.getElementById('circle_r').value = Round1(r/1000,2);
				document.getElementById('circle_a').value = Round1(3.14*r*r/1000000,2);
			var infoWindow_circle = new MMap.InfoWindow({  
				isCustom:true,  // 使用自定义窗体
                content:createInfoWindow_circle("半径："+Round1(r/1000,2)+"千米<br/>圆面积："+Round1(3.14*r*r/1000000,2)+"平方公里"),  
                size:new MMap.Size(80, 0),  
                offset:new MMap.Pixel(150, -20)// -113, -140
    }); 
	   infoWindow_circle.open(mmap_basic_mapObj,e.obj.getCenter()); 
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
function mmap_basic_accessLngLat(){
	if(mmap_basic_mousetool !=null){
		mmap_basic_mousetool.close();
	}
	var clickEventListener=MMap.event.addListener(mmap_basic_mapObj,'click',function(e){  
        document.getElementById("lngX").value=e.lnglat.getLng();  
        document.getElementById("latY").value=e.lnglat.getLat();    
    });  

}

 mmap_basic_lines = {"busid":"1","id":[{"lng":"120.18904","lat":"30.213423"},
{"lng":"120.382122","lat":"30.201176"}]
};

// 绘制轨迹
function mmap_basic_polyline(){
   mmap_basic_marker = new MMap.Marker({
			map:mmap_basic_mapObj,
			position:new MMap.LngLat(mmap_basic_lines.id[0].lng,mmap_basic_lines.id[0].lat),// 基点位置
			icon:"../js/amap/imgs/car.png", // marker图标，直接传递地址url
			offset:new MMap.Pixel(-16,-16), // 相对于基点的位置
			autoRotation:true
		});
		lineArr = new Array(); 
	    var i=0;
		$.each(mmap_basic_lines,function(){
			  lineArr.push(new MMap.LngLat(mmap_basic_lines.id[i].lng,mmap_basic_lines.id[i].lat));
			  i = i+1
	    });
		// 绘制轨迹
		var polyline=new MMap.Polyline({
			map:mmap_basic_mapObj,
			path:lineArr,
			strokeColor:"#00A",// 线颜色
			strokeOpacity:1,// 线透明度
			strokeWeight:3,// 线宽
			strokeStyle:"solid",// 线样式
		});
}
// 开始轨迹回放
function mmap_basic_startAnimation(){
    mmap_basic_marker.moveAlong(lineArr,150,null,false);
	// mmap_basic_marker.moveTo(new
	// MMap.LngLat(mmap_basic_lines.id[1].lng,mmap_basic_lines.id[1].lat),80,null);
}

function mmap_basic_stopAnimation(){
    mmap_basic_marker.stopMove();
}

var mmap_basic_gpsTrackPoints = [];  // GPS监控轨迹点集合
var mmap_basic_gpsTrackMarker = null; // GPS监控实物点标注对象
var mmap_basic_gpsTrackMarkerInfo = null;  // GPS监控实物点信息窗口
var mmap_basic_gpsTrackPolyLine = null; // GPS监控实物点轨迹对象
var mmap_basic_infoWindow_content = ""; // 信息窗体内容
var mmap_basic_distance = 0;// 当前运行距离
var mmap_basic_total_distance = 0;// 轨迹总距离
var mmap_basic_distance_unit = "";// 当前运行距离+单位
var mmap_basic_total_distance_unit = "";// 轨迹总距离 + 单位
var flag = true;// 标注运行标志
var windowInfo_content = null;
var mmap_polyline_suspend_distance = 500;// 轨迹中断的最短距离
var speedArray = new Array();
var timeArray = new Array();
var mmap_basic_point_index = 0;
var address = "";
// gps运行轨迹
function mmap_basic_addGpsTrack_polyline(gpsdata,infodata){
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
			mmap_basic_gpsTrackPoints.push(new MMap.LngLat(parseFloat(gpsXY.X), parseFloat(gpsXY.Y)));  
			speedArray.push(gpsdata[i].speed);
			timeArray.push(gpsdata[i].realtime.replace(".0",""));
		}// for
	}// if gpsdata==null || gpsdata.length==0

	 if (mmap_basic_gpsTrackPoints.length > 0) {  
	 	// 计算轨迹总距离
		mmap_basic_total_distance_unit=map_basic_measureDistance(mmap_basic_gpsTrackPoints);
		$("#distance").html("0/"+mmap_basic_total_distance_unit);
		
		// 添加地图标注点
	 	mmap_basic_gpsTrackMarker = new MMap.Marker({
			map:mmap_basic_mapObj,
			position:mmap_basic_gpsTrackPoints[0],// 基点位置
			icon:"../js/amap/imgs/car.png", // marker图标，直接传递地址url
			offset:new MMap.Pixel(-16,-16), // 相对于基点的位置
			autoRotation:true
		}); 
	 	// 注册单击事件
	 	MMap.event.addListener(mmap_basic_gpsTrackMarker,"click",function(e){
	 		var pos = mmap_basic_pos - 1;
	 		if(pos<0) pos = mmap_basic_gpsTrackPoints.length-1;
	 		mmap_basic_gpsTrackMarkerInfo.open(mmap_basic_mapObj,mmap_basic_gpsTrackPoints[pos]);
	 	});
	 	// 循环调用画线
		MMap.event.addListener(mmap_basic_gpsTrackMarker,"moveend",function(e){
			var cp = mmap_basic_gpsTrackPoints[mmap_basic_pos];  
			if (mmap_basic_mapObj.getBounds().contains(cp)) {  // 当前地图是否存在cp点
				mmap_basic_mapObj.panTo(cp);// 监控该点 将中心点变换到指定位置
			} 
			// 修改信息窗口中gps时间和速度
			mmap_basic_gpsTrackMarkerInfo.setContent(mmap_basic_InfoWindow(infodata,speedArray[mmap_basic_pos],timeArray[mmap_basic_pos],""));
			mmap_basic_gpsTrackMarkerInfo.open(mmap_basic_mapObj,mmap_basic_gpsTrackPoints[mmap_basic_pos]);  
			mmap_basic_pos = mmap_basic_pos+1;
			setTimeout("mmap_basic_addGpsTrack()",100);// 标注停留300毫秒
		});
		mmap_basic_gpsTrackMarkerInfo = new MMap.InfoWindow({  
			isCustom:true,  // 使用自定义窗体
			content:mmap_basic_InfoWindow(infodata,speedArray[0],timeArray[0],""),
			size:new MMap.Size(100, 320),  
			offset:new MMap.Pixel(20, -30)// -113, -140
    	});
		// 打开信息窗体
		mmap_basic_gpsTrackMarkerInfo.open(mmap_basic_mapObj,mmap_basic_gpsTrackPoints[0]);  
		mmap_basic_mapObj.panTo(mmap_basic_gpsTrackPoints[0]);
		mmap_basic_addGpsTrack(); // 第二个开始
	} // if mmap_basic_gpsTrackPoints.length > 0
}

/**
 * 添加GPS轨迹. 就是运行 @ 通过pos 在basic_se_parseGpsData_gpsTrackPoints数组中从哪一位开始
 */
var mmap_basic_outTime = 80; // 运行速度
var mmap_basic_pos = 1;// 第二个开始
// 两点之间画轨迹
function mmap_basic_addGpsTrack(){
	mmap_basic_pos = (mmap_basic_pos >= mmap_basic_gpsTrackPoints.length ? 0 : mmap_basic_pos); // 不能超过总长度
	if (mmap_basic_pos <= 0) {  // 小于等于0 说明 轨迹运行完毕
		$("#distance").html(mmap_basic_total_distance_unit+"/"+mmap_basic_total_distance_unit);
		slider2.setValue(100);
		mmap_basic_geocoderadd_byLnglat(mmap_basic_gpsTrackMarkerInfo.getPosition());
		return;  
	} // if mmap_basic_pos <= 0
	
	// 添加运行轨迹
	if(mmap_basic_gpsTrackPolyLine!=null){
		// 计算距离
		// mmap_basic_distance = mmap_basic_gpsTrackPolyLine.getLength();
		mmap_basic_distance += mmap_basic_gpsTrackPoints[mmap_basic_pos-2].distance(mmap_basic_gpsTrackPoints[mmap_basic_pos-1]);
		mmap_basic_distance_unit = show_format(mmap_basic_distance);
		$("#distance").html(mmap_basic_distance_unit+"/"+mmap_basic_total_distance_unit);
		
		
		if(mmap_basic_total_distance!=0){
			var temp= Math.round(mmap_basic_distance*100/mmap_basic_total_distance,2);
			slider2.setValue(temp);
		}
	}
	var distance = mmap_basic_gpsTrackPoints[mmap_basic_pos-1].distance(mmap_basic_gpsTrackPoints[mmap_basic_pos]);
	if(distance>=mmap_polyline_suspend_distance){
		mmap_basic_point_index = mmap_basic_pos;
		mmap_basic_gpsTrackPolyLine = null;
		mmap_basic_gpsTrackMarker.setPosition(mmap_basic_gpsTrackPoints[mmap_basic_pos]);  // 将标注点移至下一点
		mmap_basic_gpsTrackMarkerInfo.setContent(mmap_basic_InfoWindow(windowInfo_content,speedArray[mmap_basic_pos],timeArray[mmap_basic_pos],""));
		mmap_basic_gpsTrackMarkerInfo.open(mmap_basic_mapObj,mmap_basic_gpsTrackPoints[mmap_basic_pos]);  
		var cp = mmap_basic_gpsTrackPoints[mmap_basic_pos];  
		if (mmap_basic_mapObj.getBounds().contains(cp)) {  // 当前地图是否存在cp点
			mmap_basic_mapObj.panTo(cp);// 监控该点 将中心点变换到指定位置
		} 
		mmap_basic_pos = mmap_basic_pos+1;
		mmap_basic_addGpsTrack();
	}else{
		if(mmap_basic_gpsTrackPolyLine){
			mmap_basic_gpsTrackPolyLine.setMap(null);
		}
		// 绘制轨迹
		lineArr = mmap_basic_gpsTrackPoints.slice(mmap_basic_point_index,mmap_basic_pos+1);
		mmap_basic_gpsTrackPolyLine=new MMap.Polyline({
			map:mmap_basic_mapObj,
			path:lineArr,
			strokeColor:"#00A",// 线颜色
			strokeOpacity:1,// 线透明度
			strokeWeight:3,// 线宽
			strokeStyle:"solid",// 线样式
		});
		// 添加GPS监控位置点

		var cp = mmap_basic_gpsTrackPoints[mmap_basic_pos];  
		if (mmap_basic_mapObj.getBounds().contains(cp)) {  // 当前地图是否存在cp点
			mmap_basic_mapObj.panTo(cp);// 监控该点 将中心点变换到指定位置
		} 

		mmap_basic_outTime = slider1.getValue();
		if(flag&&mmap_basic_gpsTrackMarker){
			mmap_basic_gpsTrackMarker.moveTo(mmap_basic_gpsTrackPoints[mmap_basic_pos],mmap_basic_outTime);  // 将标注点移至下一点
			mmap_basic_gpsTrackMarkerInfo.setPosition(mmap_basic_gpsTrackPoints[mmap_basic_pos-1]); 
		}
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
	mmap_basic_point_index = 0;
	flag = true;
	speedList = new Array();
	timeList = new Array();
	
	if(mmap_basic_gpsTrackPoints != null ||mmap_basic_gpsTrackPoints.length!=0){
		mmap_basic_gpsTrackPoints = [];
	}
	if(mmap_basic_gpsTrackMarker){
		mmap_basic_gpsTrackMarker.stopMove();
		mmap_basic_gpsTrackMarker.setMap(null);
		mmap_basic_gpsTrackMarker = null;
	}
	if(mmap_basic_gpsTrackMarkerInfo){
		mmap_basic_mapObj.clearInfoWindow();
		mmap_basic_gpsTrackMarkerInfo = null;
	}
	if(mmap_basic_gpsTrackPolyLine){
		mmap_basic_gpsTrackPolyLine.setMap(null);
		mmap_basic_gpsTrackPolyLine = null;
	}
}
/**
 * 暂停回放 zjr
 */
function map_basic_markStopMove(){
	flag = false;
	if(mmap_basic_gpsTrackMarker){
		mmap_basic_gpsTrackMarker.stopMove();
	} 
	if(mmap_basic_gpsTrackMarkerInfo){
		mmap_basic_geocoderadd_byLnglat(mmap_basic_gpsTrackMarkerInfo.getPosition());
	}
}
/**
 * 继续回放 zjr
 */
function map_basic_markMove_GoOn(){
	flag = true;
	if(mmap_basic_gpsTrackMarker){
		mmap_basic_outTime = slider1.getValue();
		mmap_basic_gpsTrackMarker.moveTo(mmap_basic_gpsTrackPoints[mmap_basic_pos],mmap_basic_outTime);
	}
}
/**
 * 再从头开始回放 zjr
 */
function reStart_remove_PolyLine(){
	if(mmap_basic_gpsTrackMarker){
		mmap_basic_gpsTrackMarker.stopMove();
		mmap_basic_gpsTrackMarker.setMap(null);
		mmap_basic_gpsTrackMarker = null;
	}
	mmap_basic_mapObj.clearInfoWindow();
	if(mmap_basic_gpsTrackPolyLine){
		mmap_basic_point_index = mmap_basic_pos;
		mmap_basic_gpsTrackPolyLine.setMap(null);
		mmap_basic_gpsTrackPolyLine = null;
	}
	mmap_basic_mapObj.clearMap();
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
		if(temp<mmap_polyline_suspend_distance)	distance = distance+temp;
	}
	mmap_basic_total_distance = distance;
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
function mmap_basic_addLbsMarker(lng,lat,busid,carinfoJSON,content){ 
	var dimX = parseFloat(lng) + "";
	var dimY = parseFloat(lat) + "";
	var gpsXY = sp_gpsfix_init.transform(dimX,dimY);
    var newlng = parseFloat(gpsXY.X);
    var newlat = parseFloat(gpsXY.Y);
	// 如果有 把原有的marker 删除
	mmap_basic_deletemarker(busid);
	// 如果有把原有的信息窗体删除
	/*
	 * var ciJ = mmap_basic_markerinfo[busid]; if(ciJ != null){ var cii =
	 * mmap_basic_markerinfo[busid]['infoWindow']; if(cii != null &&
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

	 
    mmap_basic_marker=new MMap.Marker({ 
		id:busid,                   
		// icon:mmap_public_image_judge(carstatus,icomold),
		cursor:"../MapControl/openhand.cur",//鼠标悬停时显示的光标 
		position:new MMap.LngLat(newlng,newlat),
		content:markerContent,   // 自定义点标记覆盖物内容
		// offset: new MMap.Pixel(-14,-30) //偏移位置
    });  
   // mmap_basic_marker.setMap(mmap_basic_mapObj);  // 在地图上添加点
    mmap_basic_mapObj.addOverlays(mmap_basic_marker);
	mmap_basic_markerinfo[busid] = mmap_carinfo_creatJSON(mmap_basic_marker,null);// 将车辆对应的标记添加到json中
	
	mmap_basic_window(busid,carinfoJSON);// 鼠标移动到标记点上，展开信息窗体
   // mmap_basic_marker.setCursor("pointer");// 当鼠标放置标记物上时，鼠标以手指形状显示
}

// 根据经纬度获取详细地址


function mmap_basic_geocoderadd(lng,lat){
   var carinfoJSON = {};// 存放信息窗体中显示的内容
  
	// 逆地理编码
	mmap_basic_geocoder.getAddress(new MMap.LngLat(lng, lat)); 
	// 返回地理编码结果
	MMap.event.addListener(mmap_basic_geocoder, "complete", function(data){
		mmap_basic_geocoderadd_address = data.regeocode.formattedAddress;
	});
	return mmap_basic_geocoderadd_address;
}

/**
 * 添加点标记 lng 经度 lat 纬度 busid carinfoJSON 展示信息 icomold 图片类型 例如
 * car/delineation/10_ car/delineation/11_
 */
function mmap_basic_addLbsMarker_new(lng,lat,busid,carinfoJSON,content){ 
	/*var dimX = parseFloat(lng);
	var dimY = parseFloat(lat);
	var gpsXY = sp_gpsfix_init.transform(dimX,dimY);
    var newlng = parseFloat(gpsXY.X);
    var newlat = parseFloat(gpsXY.Y);*/
	// 如果有 把原有的marker 删除
	mmap_basic_deletemarker(busid);
	// alert(1);
	// 如果有把原有的信息窗体删除
	var ciJ = mmap_basic_markerinfo[busid];
	if(ciJ != null && ciJ !=""){
		var cii = mmap_basic_markerinfo[busid]['infoWindow'];
		if(cii != null && cii.getIsOpen()==true){
			cii.close();
			// alert("内部");
			// cii.setPosition(new MMap.LngLat(newlng,newlat));
		}
	}
 	// 自定义点标记内容
	var markerContent = document.createElement("div");
	markerContent.className = "markerContentStyle";
	// 点标记中的图标
	var markerImg= document.createElement("img");
	markerImg.className="markerlnglat";
	markerImg.id = busid;
	markerImg.src="../imgs/lbsmapshow/0.png";// "http://webapi.amap.com/images/marker_sprite.png";//
	markerContent.appendChild(markerImg);
		
	 // 点标记中的文本
	// var markerSpan = document.createElement("div");
	var markerSpan = document.createElement("span");
	markerImg.className="markercontent";
	markerSpan.innerHTML = content;
	markerContent.appendChild(markerSpan);

	 
   /* mmap_basic_marker=new MMap.Marker({ 
		id:busid,                   
	    position:new MMap.LngLat(newlng,newlat),
	    draggable:false, //不可拖动 
		content:markerContent   // 自定义点标记覆盖物内容
		
		// offset: new MMap.Pixel(-14,-30) //偏移位置
    }); */ 
    
    mmap_basic_marker=new MMap.Marker({ 
		id:busid,                   				 
	    position:new MMap.LngLat(lng,lat), //位置 
		offset:new MMap.Pixel(-11.5,-32), //基点为图片左上角，设置相对基点的图片位置偏移量，向左向下为负
		draggable:false, //不可拖动 
		cursor:"../imgs/lbsmapshow/aero_link.cur",//鼠标悬停时显示的光标 
		content:markerContent   // 自定义点标记覆盖物内容
    });  
  if(mmap_basic_mapObj ==null || mmap_basic_mapObj ==""){
	  mmap_basic_init(8,"container",120.29731701,30.21907165);
  }
    mmap_basic_mapObj.addOverlays(mmap_basic_marker); // 在地图上添加点
	mmap_basic_markerinfo[busid] = mmap_carinfo_creatJSON(mmap_basic_marker,null);// 将车辆对应的标记添加到json中
	//如果传递的参数中，当前位置需要根据经纬度解析，如果存在就不用解析
	if(carinfoJSON['当前位置'] == ""){
	mmap_basic_window_new(busid,carinfoJSON,lng,lat);// 鼠标移动到标记点上，展开信息窗体
	}else{
		 mmap_basic_window_newaddr(busid,carinfoJSON,lng,lat);// 鼠标移动到标记点上，展开信息窗体,地址信息是已经存在的，不用在页面中解析
	}
	return mmap_basic_marker
}
// 实例化信息窗体
// 参数可以增加或者减少
function mmap_basic_window_new(busid,carinfoJSON,lng,lat){
	
 // 鼠标移动到地图点上时显示详细信息
	if(mmap_basic_markerinfo != null && mmap_basic_markerinfo != {}){
		var abm = mmap_basic_markerinfo[busid]['marker'];
		if(abm != null){
				  mmap_basic_mapObj.bind(abm,"mouseover",function(e){
					  var lnglat=new MMap.LngLat(lng,lat);
					 /* mmap_basic_geocoder.getAddress(new MMap.LngLat(lng,lat)); */
			 			// 返回地理编码结果
			 			/*MMap.event.addListener(mmap_basic_geocoder, "complete", function(data){
			 				// 返回地址描述
							  carinfoJSON['当前位置'] = data.regeocode.formattedAddress;
							  var infoWindow = "";
							  infoWindow = new MMap.InfoWindow({  
								     isCustom:true,  // 使用自定义窗体
								     content:mmap_basic_NewInfoWindow(carinfoJSON),  
								     size:new MMap.Size(300, 0),  
								     offset:new MMap.Pixel(18, -58)// -113,
																	// -140
								 });
						 mmap_basic_markerinfo[busid] = mmap_carinfo_creatJSON(abm,infoWindow); // {"busid":{"marker":null,"infowindow":null}}
						 infoWindow.open(mmap_basic_mapObj,abm.getPosition());
					});*/
					 if(mmap_basic_geocoder == "" || mmap_basic_geocoder == null){
						// 加载地理服务
							mmap_basic_geocoder = new MMap.Geocoder({  
								radius: 1000, // 以已知坐标为中心点，radius为半径，返回范围内兴趣点和道路信息
								extensions: "all"// 返回地址描述以及附近兴趣点和道路信息，默认“base”
							}); 
					 } 
			 	   mmap_basic_geocoder.regeocode(lnglat,data_comback);
			 	   function data_comback(data){
			 		// 返回地址描述
			 		  var resultStr = "";
			 			if(data.status=="E0"){ 
			 		        for(var i=0;i<data.list.length;i++){  
			 		        	//alert(data);
			 		            resultStr += data.list[i].province.name+data.list[i].city.name+data.list[i].district.name; 
			 		           // alert("resultStr="+resultStr);
			 		            resultStr += (data.list[i].poilist == "" ? "" : data.list[i].poilist[0].address) == "" ? (data.list[i].roadlist == "" ? "" :data.list[i].roadlist[0].name):data.list[i].poilist[0].address;
			 		            resultStr += data.list[i].poilist == "" ? "" : data.list[i].poilist[0].name;
			 		        } 
			 		    }
			 			   //判断解析出的地址长度，纵向调整信息窗体的位置
			 			//判断解析出的地址长度，纵向调整信息窗体的位置
			 			var chineseCount=GetChineseCount(resultStr);//得到字符串中的中文字符数
		 				var piexy;
		 			    var piexparam=resultStr.length;//字符串中的总长度
		 			    var count;
		 			    
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
		 			 //如果字符在16个以内，信息窗体中不增加行数，超过16并且在37个以内增加一行，超过37，超出的字数与21取整之后再加上2行
		 			   if(count <=16){
		 					piexy = 0;
		 				} else if(count >16 && count<=37){
		 					piexy = 1;
		 				} else{
		 					piexy=2+Math.floor((count-37)/21);
		 				}
						  carinfoJSON['当前位置'] =resultStr;
						  var infoWindow = "";
						  infoWindow = new MMap.InfoWindow({  
							     isCustom:true,  // 使用自定义窗体
							     content:mmap_basic_NewInfoWindow(carinfoJSON),  
							     size:new MMap.Size(300, 0),  
							     offset:new MMap.Pixel(-128, -275-piexy*20)// -113,// -140
							 });
					 mmap_basic_markerinfo[busid] = mmap_carinfo_creatJSON(abm,infoWindow); // {"busid":{"marker":null,"infowindow":null}}
					 infoWindow.open(mmap_basic_mapObj,abm.getPosition());

			 	   }
			 			
 			});   
			
		}
	}
}
/**
 * @author lj_na.li
 * @function 判断字符串的中文字符数
 * @date 2013-12-18
 * @return count，例如3
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
function mmap_basic_geocoderadd_byLnglat(lnglat){
	// 逆地理编码
	mmap_basic_geocoder.getAddress(lnglat); 
	// 返回地理编码结果
	MMap.event.addListener(mmap_basic_geocoder, "complete", function(data){
		address = data.regeocode.formattedAddress.trim();
		mmap_basic_gpsTrackMarkerInfo.setContent(mmap_basic_InfoWindow(windowInfo_content,speedArray[mmap_basic_pos],timeArray[mmap_basic_pos],address));
	});
}

function mmap_basic_window_newaddr(busid,carinfoJSON,lng,lat){
	
	 // 鼠标移动到地图点上时显示详细信息
		if(mmap_basic_markerinfo != null && mmap_basic_markerinfo != {}){
			var abm = mmap_basic_markerinfo[busid]['marker'];
			if(abm != null){
					  mmap_basic_mapObj.bind(abm,"mouseover",function(e){
							  var infoWindow = "";
							  infoWindow = new MMap.InfoWindow({  
								     isCustom:true,  // 使用自定义窗体
								     content:mmap_basic_NewInfoWindow(carinfoJSON),  
								     size:new MMap.Size(300, 0),  
								     offset:new MMap.Pixel(-128, -213)// -113,// -140
								 });
						 mmap_basic_markerinfo[busid] = mmap_carinfo_creatJSON(abm,infoWindow); // {"busid":{"marker":null,"infowindow":null}}
						 infoWindow.open(mmap_basic_mapObj,abm.getPosition())
	 			});   
				
			}
		}
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
	if($("#float_location").hasClass("none")==false){
		$("#float_location").addClass("none");
	}
}