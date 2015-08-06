
var basic_se_mapObj = null; //basic_se_mapObj 对象

var basic_se_MarkTool = null; //mark工具类对象
var basic_se_CircleTool = null; //circle工具类对象
var basic_se_PolygonTool = null; //polygon工具类对象
var basic_se_PolyLineTool = null; //polyline工具类对象
var basic_se_RectTool = null; //rect工具类对象
var basic_se_EllipseTool = null; //ellipse工具类对象
var basic_se_ZoomInTool = null; //zoomin 放大工具类对象
var basic_se_ZoomOutTool = null; //zoomin 缩小工具类对象

var basic_se_mapEditor = null; //mapEditor 编辑工具
var basic_se_markerEditor = null; //markerEditor 标注点编辑
var basic_se_polyLineEditor = null; //polyLineEditor 标注线编辑
var basic_se_polygonEditor = null; //polygonEditor 标注面编辑
	
/** 
* 地图初始化
* levelval  13
* id mapmonitor_show_map_div  map放置div
* lng lat 经度纬度位置
*/
function basic_se_init(levelval,id,lng,lat){
	basic_se_mapObj = new SE.Map(id); // 对于div id 进行加载
	basic_se_mapObj.centerAndZoom(new SE.LngLat(lng,lat),levelval); // 放置中心点与缩放级别
	
	// 添加标准控件，骨头棒(左上角)
	var mapControl = new SE.MapControl();
	basic_se_mapObj.addControl(mapControl);
	
	// 添加 卫图 矢量 和 融合控件(右上角)
//	var switchControl = new SE.MapTypeControl();
//	basic_se_mapObj.addControl(switchControl);
//	basic_se_mapObj.removeMapType(SE.Traffic_MAP);
//	switchControl.setRight(5);
	
	// 添加 比例尺(左下角)
	var scale = new SE.ScaleControl();
	scale.setLeft(20);
	scale.setBottom(30);
	basic_se_mapObj.addControl(scale);
	
	// 添加 鹰眼(右下角)
	// 4 右下方 2 右上方 -2左下方 -4 左上方
	var overmap = new SE.OverviewMapControl(4,[100,100],null,8,1,2);  
    basic_se_mapObj.addControl(overmap);
	
	// 添加 地图中心十字
	/*var crossCon = new SE.CenterCrossControl();
	crossCon.setColor('#ff0000');
	crossCon.setStroke(3);
	basic_se_mapObj.addControl(crossCon);*/
	
	// ----------  鼠标效果 ----------
	// 鼠标滚动
	basic_se_mapObj.handleMouseScroll(true);
	// 鼠标双击缩放地图
	basic_se_mapObj.enableDoubleClickZoom();
	// 地图拖动
	basic_se_mapObj.enableDrag();
	// 自定义鼠标样式
	basic_se_mapObj.setMapCursor('../js/semap/images/basic/hand/openhand_default.cur',
			'../js/semap/images/basic/hand/closedhand_default.cur');
	
	// ----------  初始化工具类 ----------
	basic_se_MarkTool = new SE.MarkTool(basic_se_mapObj); //mark工具类对象
	basic_se_CircleTool = new SE.CircleTool(basic_se_mapObj); //circle工具类对象
	basic_se_PolygonTool = new SE.PolygonTool(basic_se_mapObj); //polygon工具类对象
	basic_se_PolyLineTool = new SE.PolyLineTool(basic_se_mapObj); //polyline工具类对象
	basic_se_RectTool = new SE.RectTool(basic_se_mapObj); //rect工具类对象
	basic_se_EllipseTool = new SE.EllipseTool(basic_se_mapObj); //ellipse工具类对象
	basic_se_ZoomInTool = new SE.ZoomInTool(basic_se_mapObj); //zoomin 放大工具类对象
	basic_se_ZoomOutTool = new SE.ZoomInTool(basic_se_mapObj,{zoomAdd:-1}); //zoomin 缩小工具类对象
	
	// 键盘事件
	basic_se_mapObj.handleKeyboard();
}

/** 
* 地图全局操作 方式
*/
function basic_se_overall(){
}

/**  
* 整体地图类型切换
*   
* @param type 地图类型id number
* 1 矢量地图 2 影像地图  3 混合地图
*/  
function basic_se_mapType(type) {  
	if(type == 1)
		basic_se_mapObj.setMapType(SE.NORMAL_MAP);// 矢量地图
	else if(type == 2)
		basic_se_mapObj.setMapType(SE.SATELLITE_MAP);// 影像地图
	else if(type == 3)
		basic_se_mapObj.setMapType(SE.HYBRID_MAP);// 混合地图
}	

/**  
* 整个地图拖动与否
*   
* @param isdrag 鼠标是否实现拖动 boolean
* true 拖动 false 不拖动
*/  
var basic_se_isdrag_boolean_isdrag = true; // 能够拖动
function basic_se_isdrag() {
	if(basic_se_isdrag_boolean_isdrag){
		basic_se_mapObj.enableDrag();// 地图拖动
		basic_se_isdrag_boolean_isdrag = false;
	}else{
		basic_se_mapObj.disableDrag();// 地图不能拖动
		basic_se_isdrag_boolean_isdrag = true;
	}
}

/**  
* 整个地图 地图调用框放大缩小功能. 
* @param type 拉框或缩小标识
* 1 放大 2 缩小
*/ 
function basic_se_ZoomChange(type){
	
	// 清空所有该控件在地图绘制的矩形图形
	basic_se_ZoomInTool.clear();
	basic_se_ZoomOutTool.clear();
	
	// 关闭画矩形工具
	if(basic_se_ZoomInTool != null)
			basic_se_ZoomInTool.close();
	if(basic_se_ZoomOutTool != null)
			basic_se_ZoomOutTool.close();
	
	if( basic_se_ZoomChange_bind_enddraw == null){
		basic_se_zoom(type);
	}else{
		// 删除监听
		SE.Event.removeListener(basic_se_ZoomChange_bind_enddraw);
		basic_se_ZoomChange_bind_enddraw = null;
	}
}  

/**  
* 整个地图 激活拉框放大缩小功能. 
* @param type 拉框或缩小标识
* 1 放大 2 缩小
*/  
var basic_se_ZoomChange_bind_enddraw = null;
function basic_se_zoom(type){
	switch(type){  
	case 1: // 拉框放大
		// 绑定事件
		basic_se_ZoomChange_bind_enddraw = SE.Event.bind(basic_se_ZoomInTool,"enddraw",basic_se_ZoomInTool,
		function(ctrl){
			ctrl.close(); // 1、绘画完毕后操作关闭
			basic_se_zoom(type);// 2、绘画完毕后操作继续操作
		});
		basic_se_ZoomInTool.open(); 
		break;  
	case 2: // 拉框缩小
		// 绑定事件
		basic_se_ZoomChange_bind_enddraw = SE.Event.bind(basic_se_ZoomOutTool,"enddraw",basic_se_ZoomOutTool,
		function(ctrl){
			ctrl.close();	// 1、绘画完毕后操作关闭
			basic_se_zoom(type);// 2、绘画完毕后操作继续操作						 
		});
		basic_se_ZoomOutTool.open(); 
		break;  
	} 	
}

/**  
* 地图画线条 同时进行编辑
*/
var basic_se_onDrawLine_bind_draw = null;// 绑定绘画事件
var basic_se_onDrawLine_addListener_click = null;// 绑定鼠标移入事件
var basic_se_onDrawLine_polyLine = null; // 绘画线路对象
function basic_se_onDrawLine(){
	
	if( basic_se_onDrawLine_bind_draw == null){
		// 标注 线 可编辑
		basic_se_onDrawLine_bind_draw = SE.Event.bind(basic_se_PolyLineTool,"draw",basic_se_mapObj,
		function(bounds,line){
			basic_se_onDrawLine_polyLine = new SE.PolyLine(bounds); // 绘画线路对象
			basic_se_onDrawLine_polyLine.setLineColor('#0000ff'); // 设置线路颜色 string
			basic_se_onDrawLine_polyLine.setLineStroke(3); // 设置线路宽度 number px
			basic_se_mapObj.addOverLay(basic_se_onDrawLine_polyLine); // 加入图层中
			basic_se_PolyLineTool.close(); // 绘画线路测距工具关闭
			
			// 编辑改变 展示计算距离数字
			basic_se_onDrawLine_polyLine.onChange(function(){
				var LngLats = basic_se_onDrawLine_polyLine.getLngLats(); // 折线上所有点的坐标
				basic_se_measureDistanceShow(LngLats); // 展示距离框框
			});  
		
			// 编辑结束
			basic_se_onDrawLine_polyLine.onEditEnd(function(){
			});
			
			// 移动点击就编辑
			basic_se_onDrawLine_addListener_click = SE.Event.addListener(basic_se_onDrawLine_polyLine,'click',
			function(){
				if(!basic_se_onDrawLine_polyLine.isEditable())
					basic_se_onDrawLine_polyLine.enableEdit(); // 激活可编辑状态
				else
					basic_se_onDrawLine_polyLine.disableEdit(); // 不可编辑状态					
			});
			
			var LngLats = basic_se_onDrawLine_polyLine.getLngLats(); // 折线上所有点的坐标
			basic_se_measureDistanceShow(LngLats); // 展示距离框框
			
		});
		
		basic_se_PolyLineTool.open(); // 绘画线路测距工具打开 
	}else{
		if(basic_se_onDrawLine_polyLine != null && basic_se_onDrawLine_polyLine.isEditable())
			basic_se_onDrawLine_polyLine.disableEdit(); // 不可编辑状态
			
		// 删除监听
		SE.Event.removeListener(basic_se_onDrawLine_bind_draw);
		SE.Event.removeListener(basic_se_onDrawLine_addListener_click);
		basic_se_onDrawLine_bind_draw = null;
		basic_se_onDrawLine_addListener_click = null;
		
		basic_se_PolyLineTool.close();// 绘画线路测距工具关闭
		
		// 删除线条
		if(basic_se_onDrawLine_polyLine != null){
			basic_se_mapObj.removeOverLay(basic_se_onDrawLine_polyLine);
		}
		// 删除提示框
		if(basic_se_measureDistanceShow_pointOverlay != null){
			basic_se_mapObj.removeOverLay(basic_se_measureDistanceShow_pointOverlay);
		}
	}
} 

/**
* 展示出地图多点之间总的距离 界面
* @param LngLats 经纬度数组
*/
var basic_se_measureDistanceShow_pointOverlay = null; // 展示距离
function basic_se_measureDistanceShow(LngLats){
	var lnglat = LngLats[LngLats.length-1]; // 将距离展示在最后一个坐标上 
	if(basic_se_measureDistanceShow_pointOverlay == null)
		basic_se_measureDistanceShow_pointOverlay = new SE.PointOverlay(lnglat); 
	else{
		basic_se_mapObj.removeOverLay(basic_se_measureDistanceShow_pointOverlay);  
		basic_se_measureDistanceShow_pointOverlay.setLngLat(lnglat);  
	}  
	basic_se_measureDistanceShow_pointOverlay.setLabel("总距离：" + basic_se_measureDistance(LngLats));       
	basic_se_mapObj.addOverLay(basic_se_measureDistanceShow_pointOverlay); 
	basic_se_measureDistanceShow_pointOverlay.setOffset(new SE.Point(0,0)); // 偏离
}

/**
* 地图多点之间总的距离
* @param LngLats 经纬度数组
*/
function basic_se_measureDistance(LngLats){
	var distance = Math.round(SE.PolyLineTool.getPointsDistance(LngLats));
	var disstr = String(distance);
	var rettemp = '';
	if(disstr.length < 4) // 米
		rettemp = disstr + '米';
	else if(disstr.length >= 4)
		rettemp = sundry_formatFloat(distance/1000,2) + '千米';
	return rettemp;
}

/**  
* 地图画矩形 同时进行编辑
*/
var basic_se_onDrawRect_bind_draw = null;// 绑定绘画事件
var basic_se_onDrawRect_Rect = null; // 绘画矩形对象
var basic_se_onDrawRect_addListener_click = null; // 绑定鼠标移入事件
function basic_se_onDrawRect(){
	
	if( basic_se_onDrawRect_bind_draw == null){
		// 标注 线 可编辑
		basic_se_onDrawRect_bind_draw = SE.Event.bind(basic_se_RectTool,"draw",basic_se_mapObj,
		function(bounds){
			basic_se_onDrawRect_Rect = new SE.Rect(bounds); // 绘画矩形对象
			basic_se_onDrawRect_Rect.setLineColor('#0000ff'); // 设置矩形颜色 string
			basic_se_onDrawRect_Rect.setLineStroke(3); // 设置矩形宽度 number px
			basic_se_mapObj.addOverLay(basic_se_onDrawRect_Rect); // 加入图层中
			basic_se_RectTool.close(); // 绘画矩形工具关闭
		
			// 编辑结束
			SE.Event.addListener(basic_se_onDrawRect_Rect,"editend",
			function(){
				
				// 判断是否在区域内
				
			});
			// 移动上去
			basic_se_onDrawRect_addListener_click = SE.Event.addListener(basic_se_onDrawRect_Rect,'click',
			function(){
				if(!basic_se_onDrawRect_Rect.isEditable())
					basic_se_onDrawRect_Rect.enableEdit(); // 激活可编辑状态
				else
					basic_se_onDrawRect_Rect.disableEdit(); // 不可编辑状态
			});
		}); 

		basic_se_RectTool.open(); // 绘画矩形工具打开
	}else{
		if(basic_se_onDrawRect_Rect != null && basic_se_onDrawRect_Rect.isEditable())
			basic_se_onDrawRect_Rect.disableEdit(); // 不可编辑状态
			
		// 删除监听
		SE.Event.removeListener(basic_se_onDrawRect_bind_draw);
		SE.Event.removeListener(basic_se_onDrawRect_addListener_click);
		basic_se_onDrawRect_bind_draw = null;
		basic_se_onDrawRect_addListener_click = null;
		
		basic_se_RectTool.close();// 绘画矩形工具关闭
		
		// 删除矩形
		if(basic_se_onDrawRect_Rect != null){
			basic_se_mapObj.removeOverLay(basic_se_onDrawRect_Rect);
		}
	}
} 

/**
* 地图上所框选区域内，判断坐标点是否在区域内
* @param rectObj 矩形区域 对象
* @param lnglats LngLat数组
*/
var basic_se_judgeAreaInOut_inArray = []; // 在区域内
var basic_se_judgeAreaInOut_outArray = []; // 在区域外
function basic_se_judgeAreaInOut(rectObj,lnglats){
	for(var i = 0;i<lnglats.length; i++){
		// 判断是否在区域内
		if(rectObj.containsPoint(lnglats[i])){  
			basic_se_judgeAreaInOut_inArray.push(lnglats[i]);
		}else{
			basic_se_judgeAreaInOut_outArray.push(lnglats[i]);
		}
	}
}

/**
* 地图上当前区域内 聚合展示
* @param clusterdata 需要聚合的点二维数组 经纬度 [[lng,lat],[lng,lat]]
* @param num 多少个坐标聚合 默认 100
*/
function basic_se_clusterMarker(clusterdata,num){
	if(num == 'undefined' || num == null){ // 没有
		num = 100;
	}
	var config = {}; // 函数
	config.clusterStyle=[]; //定义数组变量
	var c_u = 'images/cluster/default/m';
	for(var i=0;i<5;i++){
	  config.clusterStyle.push({url:c_u+i+".png",number:num*(i+1)});// 对变量赋值
	}	
	var _clusterdata=[]; // 经纬度数组
	for(var i=0;i<4000;i++){
		var lng = parseFloat(clusterdata[i][0]);
		var lat = parseFloat(clusterdata[i][1]);
		if(lng&&lat){
			_clusterdata.push(new SE.LngLat(lng,lat));
		}			
	}
	var markerClusterer = new SE.MarkerClusterer(basic_se_mapObj,config);
	markerClusterer.setColor('#000000');//设置字体颜色 为绿色 00ffcc  默认黑色 000000
	markerClusterer.cluster(_clusterdata);	
}

/**
* 地图上显示兴趣点坐标
* @param lnglats 经度纬度二维数组 string[]
*/
var basic_se_marker_instrustLngLat = []; // 兴趣点SE.LngLat 对象数组
function basic_se_intrustMarker(lnglats){
	if(lnglats.length != basic_se_marker_instrustLngLat.length){
		basic_se_marker_instrustLngLat = []; // 清空
	}else{
		return ;	
	}
	// 遍历坐标点
	for (var i=0; i<lnglats.length; i++) {
		var ll = new SE.LngLat(parseFloat(lnglats[i][0]),parseFloat(lnglats[i][1]))
		basic_se_marker_instrustLngLat.push(ll); // 放入全局
		
		var marker = new SE.Marker(ll); // 构造方法
		marker.setIconImage(utils_general_iconurl('qizi'),new SE.Size(24,33));// 图标，图标长宽
		basic_se_mapObj.addOverLay(marker); // 标注点 放到图层上
	}
	if(basic_se_marker_instrustLngLat.length > 0){
		se_delay_getBestMap(basic_se_marker_instrustLngLat);// 调整到最佳位置
	}
}

/**
* 刷新地图上当前区域
*/
function basic_se_flushMap(){
	// 在地图上线条删除
	if(basic_se_onDrawLine_bind_draw != null){
		if(basic_se_onDrawLine_polyLine != null && basic_se_onDrawLine_polyLine.isEditable())
			basic_se_onDrawLine_polyLine.disableEdit(); // 不可编辑状态
			
		// 删除监听
		SE.Event.removeListener(basic_se_onDrawLine_bind_draw);
		SE.Event.removeListener(basic_se_onDrawLine_addListener_click);
		basic_se_onDrawLine_bind_draw = null;
		basic_se_onDrawLine_addListener_click = null;
		
		basic_se_PolyLineTool.close();// 绘画线路测距工具关闭
		
		// 删除线条
		if(basic_se_onDrawLine_polyLine != null){
			basic_se_mapObj.removeOverLay(basic_se_onDrawLine_polyLine);
		}
		// 删除提示框
		if(basic_se_measureDistanceShow_pointOverlay != null){
			basic_se_mapObj.removeOverLay(basic_se_measureDistanceShow_pointOverlay);
		}
	}
	
	//在地图上矩形框 删除
	if(basic_se_onDrawRect_bind_draw != null){
		if(basic_se_onDrawRect_Rect != null && basic_se_onDrawRect_Rect.isEditable())
			basic_se_onDrawRect_Rect.disableEdit(); // 不可编辑状态
			
		// 删除监听
		SE.Event.removeListener(basic_se_onDrawRect_bind_draw);
		SE.Event.removeListener(basic_se_onDrawRect_addListener_click);
		basic_se_onDrawRect_bind_draw = null;
		basic_se_onDrawRect_addListener_click = null;
		
		basic_se_RectTool.close();// 绘画矩形工具关闭
		
		// 删除矩形
		if(basic_se_onDrawRect_Rect != null){
			basic_se_mapObj.removeOverLay(basic_se_onDrawRect_Rect);
		}
	}
}

/*----------------------------------------实时监控与轨迹回放-------------------------------------------------*/

/**
* 地图上标注 点 实时监控
* @param lng lat 经度纬度位置 number
* @param titlename 标题名称 json {'标题':"地图API"} 也可以是 字符串
* @param content 内容 json {'名称':"欢迎使用地图API"} 也可以是 字符串
* @param iconame 图标名称 默认 blue
*/
var basic_se_monitorMarker_newMarker = null; // 最新那个标注点
var basic_se_monitorMarker_addListener_mouseover = null; // 监听鼠标移入事件
function basic_se_monitorMarker(lng,lat,titlename,content,iconame){
	// 只保留最新标注
	if(basic_se_monitorMarker_newMarker != null){
		basic_se_mapObj.removeOverLay(basic_se_monitorMarker_newMarker);// 删除原有的点
		basic_se_monitorMarker_newMarker = null;
	}
	if(basic_se_monitorMarker_addListener_mouseover != null){
		SE.Event.removeListener(basic_se_monitorMarker_addListener_mouseover);// 删除监听鼠标移入事件
		basic_se_monitorMarker_addListener_mouseover = null;
	}
	
	// 创建位置Marker标记
	basic_se_monitorMarker_newMarker = new SE.Marker(new SE.LngLat(lng,lat)); // 构造方法
	iconame = (iconame == null || iconame == 'undefined' || iconame == '' ? 'blue' : iconame);// 判断传入图标名称
	basic_se_monitorMarker_newMarker.setIconImage(utils_general_iconurl(iconame),new SE.Size(23,32));// 图标，图标长宽
	basic_se_monitorMarker_newMarker.getIcon().setShadow();// 展示图标阴影效果
	//basic_se_monitorMarker_newMarker.setAnimation(SE.ANIMATION_DROP);// 从天而降效果 ANIMATION_DROP  上下跳动 ANIMATION_BOUNCE
	basic_se_mapObj.addOverLay(basic_se_monitorMarker_newMarker); // 标注点 放到图层上
	
	//当移入 标注点时展示
	basic_se_monitorMarker_addListener_mouseover = SE.Event.addListener(basic_se_monitorMarker_newMarker,'mouseover',
	function(p){  
		// 1、多个tab选项卡
		//var tabs = [];     
		//tabs.push(new SE.InfoWindowTab("主要","您的内容"));     
		//tabs.push(new SE.InfoWindowTab("周边","<input type='text' value='餐饮'><input type='button' disabled value='查询'>"));     
		//tabs.push(new SE.InfoWindowTab("驾车","<input type='text' value='西湖'><input type='button' disabled value='查询'>"));     
		//tabs.push(new SE.InfoWindowTab("导航","<input type='text' value='花港观鱼'><input type='button' disabled value='查询'>"));
		//infoWin=basic_se_monitorMarker_newMarker.openInfoWinHtml();  // 定义窗口
		//infoWin.setLabel(tabs); //将选项卡放入
		
		// 2、单个信息展示
		var ohtml = basic_se_infoWinHtmlString('content',content); // 内容 字符串组串
		infoWin=basic_se_monitorMarker_newMarker.openInfoWinHtml(ohtml);  // 定义窗口与放置内容
		
		
		infoWin.setTitle(basic_se_infoWinHtmlString('title',titlename)); // 标题 字符串组串
		if(!SE.Tool.browserInfo().isIE6){  
			infoWin.title_ctx3.style.marginTop='-3px';  
		}
		infoWin.setWidth(300); // 宽度
		infoWin.autoMoveToShow(); // 自动移动地图以确保信息框在地图显示 范围内
		infoWin.closeInfoWindowWithMouse(); // 当鼠标移出 自动关闭
	});
}

/**
* 地图上标注 鼠标移入展示页面和标题 json方式
* @param type 字符串类型 页面内容 content； 标题 title
* @param json json类型
* title:{'title':'地图API'} 一个
* content:{'busid':'111','address':'杭州'}*
* return Icon对象
*/
function basic_se_infoWinHtml(type,json){
	var content = '';
	if(type == 'content'){
		content += '<table>';
		$.each(json, function(key, value) {
			if(value == null){
				value = '';	
			}
			content += '<tr>';
			content += '<td>'+key+'</td>';
			content += '<td>'+value+'</td>';
			content += '</tr>';
		});
		content += '<table>';
	}else if(type == 'title'){
		$.each(json, function(key, value) {
			if(value == null){
				value = '';	
			}
			content = value;		  
		});
	}
	return content;
}

/**
* 地图上标注 鼠标移入展示页面和标题 String方式
* @param type 字符串类型 页面内容 content； 标题 title
* @param str string类型
* return Icon对象
*/
function basic_se_infoWinHtmlString(type,str){
	var content = '';
	if(type == 'content'){
		content += '<div>';
		content += str;
		content += '<div>';
	}else if(type == 'title'){
		content = str;
	}
	return content;
}

/**  
* 解析GPS数据.
* @param gpsdata GPS轨迹点数据 json格式
*/    
var basic_se_parseGpsData_gpsTrackPoints = [];  // GPS监控轨迹点集合
var basic_se_parseGpsData_gpsTrackMarker = null; // GPS监控实物点标注对象 
var basic_se_parseGpsData_gpsTrackMarkerInfo = null;  // GPS监控实物点标注信息对象 
var basic_se_parseGpsData_gpsTrackPolyLine = null; // GPS监控实物点轨迹对象  
//轨迹查询数据   说明：为首次查询数据，即重新回放时不再请求数据
var basic_se_gpsdata = null;
function basic_se_parseGpsData(gpsdata) {  
	if(basic_se_gpsdata == null){
		basic_se_gpsdata = gpsdata;
	}
	// 创建GPS监控轨迹点列表  
	var icon_data = {
		url:"../js/semap/images/basic/car.png",
		size:[16,16],
		buslisplate:"浙F22386"
	}
	var _icon = null;  
	
	for(var i=0; i<gpsdata.Data.length;i++){
		var dimX = parseFloat(gpsdata.Data[i].longitude) + "";
		var dimY = parseFloat(gpsdata.Data[i].latitude) + "";
		var gpsXY = sp_gpsfix_init.transform(dimX,dimY);
		basic_se_parseGpsData_gpsTrackPoints.push(new SE.LngLat(parseFloat(gpsXY.X), parseFloat(gpsXY.Y)));  
	}
	// 创建GPS监控实物点图标
	var _icon_url = icon_data.url;  
	var _icon_size = new SE.Size(icon_data.size[0], icon_data.size[1]);  //尺寸
	var _icon_anchor = new SE.Point(icon_data.size[0]/2, icon_data.size[1]/2);  //
	var _icon_buslisplate = icon_data.buslisplate;// 车牌号
	var _icon = new SE.Icon(_icon_url, _icon_size, _icon_anchor);  // 定义icon对象
	_icon.removeShadow();  // 去掉阴影
	// 创建GPS监控实物点对象  第一个
	if (basic_se_parseGpsData_gpsTrackPoints.length > 0) {  
		basic_se_parseGpsData_gpsTrackMarker = new SE.Marker(basic_se_parseGpsData_gpsTrackPoints[0], _icon);  // 点
		basic_se_mapObj.addOverLay(basic_se_parseGpsData_gpsTrackMarker);  
		
		basic_se_parseGpsData_gpsTrackMarkerInfo = new SE.PointOverlay(basic_se_parseGpsData_gpsTrackPoints[0]);  // 提示框
		basic_se_parseGpsData_gpsTrackMarkerInfo.setLabel("车牌："+_icon_buslisplate);  // 车牌号展示
		basic_se_mapObj.addOverLay(basic_se_parseGpsData_gpsTrackMarkerInfo);  
		
		basic_se_parseGpsData_gpsTrackMarkerInfo.setOffset(new SE.Point(5,25));  // 偏移位置
		
		// 扩大缩放级别
		//utils_general_setZoom(13);
		// 添加GPS监控动画  
		basic_se_addGpsTrack(); // 第二个开始
	} 
}  

/**  
* 添加GPS轨迹.  就是运行
* @ 通过pos 在basic_se_parseGpsData_gpsTrackPoints数组中从哪一位开始
*/
var basic_se_addGpsTrack_addgpstrackTimeoutID = null; // 间隔时间执行
var basic_se_addGpsTrack_outTime = 1120; // 运行速度 默认 两个点之间间隔1120毫秒  越小越快
var basic_se_addGpsTrack_pos = 1;// 第二个开始
var basic_se_addGpsTrack_spotSuspendTime = 10; // 标注点停留时间
function basic_se_addGpsTrack() {
	// 获得位置，到达终点就停止
	basic_se_addGpsTrack_pos = basic_se_addGpsTrack_pos ? 
			( basic_se_addGpsTrack_pos >= basic_se_parseGpsData_gpsTrackPoints.length ? 0 : 
				basic_se_addGpsTrack_pos) : 0;  // 不能超过总长度
	if (basic_se_addGpsTrack_pos <= 0) {  // 小于等于0
		clearTimeout(basic_se_addGpsTrack_addgpstrackTimeoutID);  
		basic_se_parseGpsData_gpsTrackMarker.closeInfoWindow(); //将提示删除
		se_delay_getBestMap(basic_se_parseGpsData_gpsTrackPoints);  // 将视图放置在最佳位置
		return;  
	} 
	
	setTimeout(function(){
		// 添加GPS监控位置点  
		var cp = basic_se_parseGpsData_gpsTrackPoints[basic_se_addGpsTrack_pos];  
		if (!basic_se_mapObj.getBoundsLatLng().containsPoint(cp)) {  // 当前地图是否存在cp点
			basic_se_mapObj.panTo(cp);// 监控该点  将中心点变换到指定位置
		}  
		basic_se_parseGpsData_gpsTrackMarker.setLngLat(cp);  // 将标注点移至此处
		basic_se_parseGpsData_gpsTrackMarkerInfo.setLngLat(cp);  // 将提示框移至此处
		
		// 逆定理编码查询坐标位置
		new SE.ServiceGC().rgcEncoding({location:cp},
		function(data){  
			if(data.status!='error'){                        
				with(data.result){
					var add = "";
					try{
						add = ( address == 'undefined' || address == null ? "" : address);// 详细地址
					}catch (e){
						
					}
					//var html = '<span>'+district_text+'</br>'+add+'</span>';
					var html = '<span>'+add+'</span>';
					basic_se_mapObj.layerInfoWin = basic_se_parseGpsData_gpsTrackMarker.openInfoWinHtml(html);//提示框内容                        
					basic_se_mapObj.layerInfoWin.setTitle("GPS监控位置点"); // 提示框名称
					basic_se_mapObj.layerInfoWin.setHeight(70); // 设置高度
					
					//basic_se_mapObj.layerInfoWin.moveToShow(); // 移动图标 确保信息在地图范围内
					//basic_se_mapObj.layerInfoWin.content.style.padding='0';  
					//basic_se_mapObj.layerInfoWin.setWidth(185);  
				}  
			}  
		});  
		
		// 移除并添加GPS监控轨迹  
		if (basic_se_parseGpsData_gpsTrackPolyLine) {  
			basic_se_mapObj.removeOverLay(basic_se_parseGpsData_gpsTrackPolyLine);//移除先有轨迹
		}  
		basic_se_parseGpsData_gpsTrackPolyLine = new SE.PolyLine(basic_se_parseGpsData_gpsTrackPoints.slice(0, basic_se_addGpsTrack_pos + 1)); //添加
		basic_se_mapObj.addOverLay(basic_se_parseGpsData_gpsTrackPolyLine);
		
		// 添加GPS监控动画 
		basic_se_addGpsTrack_pos = basic_se_addGpsTrack_pos+1;//顺序执行
		//测距  开始
		var LngLats = basic_se_parseGpsData_gpsTrackPolyLine.getLngLats(); // 折线上所有点的坐标
		var distance = basic_se_measureDistance(LngLats); 
		$("#distance").html(distance);
		//测距  结束
		if(basic_se_addGpsTrack_outTime != 100000000000){
			basic_se_addGpsTrack_outTime = slider1.getValue();
		}
		basic_se_addGpsTrack_addgpstrackTimeoutID = setTimeout("basic_se_addGpsTrack()",basic_se_addGpsTrack_outTime ); 
	},basic_se_addGpsTrack_spotSuspendTime); 
}

/**
* 轨迹回放 速度调整
* @param outTime 停顿时间
* 默认 1120     20 120 220 320 420 520 620 720 820 920 1020 1120 1220 1320 1420 1520 1620 1720 1820 1920
*/
var basic_se_polyLineSpeed_outTime = basic_se_addGpsTrack_outTime; // 默认当前速度
function basic_se_polyLineSpeed(outTime){
	basic_se_addGpsTrack_outTime = outTime;
	basic_se_polyLineSpeed_outTime = outTime;
}

/**
* 轨迹回放 暂停
* @param outTime 停顿时间
* 默认 10000000000
*/
function basic_se_polyLineSuspend(){
	// 暂停
	if(basic_se_addGpsTrack_outTime != 100000000000){
		basic_se_addGpsTrack_outTime = 100000000000;
	// 结束暂停
	}else{
		basic_se_addGpsTrack_outTime = basic_se_polyLineSpeed_outTime;
		clearTimeout(basic_se_addGpsTrack_addgpstrackTimeoutID); // 删除暂停
		basic_se_addGpsTrack_addgpstrackTimeoutID = setTimeout("basic_se_addGpsTrack()", basic_se_polyLineSpeed_outTime); // 继续
	}
}

/**
* 轨迹回放 开始与停止
* @param gpsdata 轨迹数据 json
* @type 1 开始 2 停止
*/
function basic_se_polyLineStartEnd(gpsdata,type){
	if(type == 1){
		// 停止
		if(basic_se_parseGpsData_gpsTrackPoints){
			basic_se_parseGpsData_gpsTrackPoints = [];
		}
		if(basic_se_parseGpsData_gpsTrackMarker){
			basic_se_parseGpsData_gpsTrackMarker = null;
		}
		if(basic_se_parseGpsData_gpsTrackMarkerInfo){
			basic_se_parseGpsData_gpsTrackMarkerInfo = null;
		}
		if(basic_se_parseGpsData_gpsTrackPolyLine){
			basic_se_parseGpsData_gpsTrackPolyLine = null;
		}  
	}else if(type ==2){
		// 开始
		basic_se_parseGpsData(gpsdata);
	}
}

/**
* 轨迹回放 样式设置
*/
function basic_se_polyLineIntercalate(){
	
}

/**
 * 删除轨迹
 */
function delete_gpsTrackPolyLine(){
	clearTimeout(basic_se_addGpsTrack_addgpstrackTimeoutID); 
	basic_se_mapObj.removeOverLay(basic_se_parseGpsData_gpsTrackPolyLine);
	basic_se_mapObj.removeOverLay(basic_se_parseGpsData_gpsTrackMarker);
	basic_se_mapObj.removeOverLay(basic_se_parseGpsData_gpsTrackMarkerInfo);
	basic_se_parseGpsData_gpsTrackPoints = [];
	//basic_se_parseGpsData_gpsTrackMarker = null;
	//basic_se_parseGpsData_gpsTrackMarkerInfo = null;
	basic_se_parseGpsData_gpsTrackPolyLine = null;
	basic_se_addGpsTrack_pos = 1;
	
	//重新回放
	//basic_se_parseGpsData(basic_se_gpsdata);
}