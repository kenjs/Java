
/**  
* 地图上添加编辑控件
*/
function se_delay_kmlMapEditor(){
	var kmlString = '<kml>';
	kmlString += '<Document>';
	kmlString += '<Style id="webPolygonStyle_2">';
	kmlString += '	<LineStyle>';
	kmlString += '		<color>73ff0000</color>';
	kmlString += '		<width>5</width>';
	kmlString += '		<lineType>solid</lineType>';
	kmlString += '	</LineStyle>';
	kmlString += '	<PolyStyle>';
	kmlString += '		<color>73ccff99</color>';
	kmlString += '	</PolyStyle>';
	kmlString += '</Style>';
	kmlString += '<Style id="webLineStyle_1">';
	kmlString += '	<LineStyle>';
	kmlString += '		<color>73ff0000</color>';
	kmlString += '		<width>5</width>';
	kmlString += '		<lineType>solid</lineType>';
	kmlString += '	</LineStyle>';
	kmlString += '</Style>';
	kmlString += '<Style id="webMarkerStyle_0">';
	kmlString += '	<IconStyle>';
	kmlString += '		<Icon>';
	kmlString += '			<href>images/spot/editor/1-1.png</href>';
	kmlString += '			<w>32</w>';
	kmlString += '			<h>32</h>';
	kmlString += '			<x>9</x>';
	kmlString += '			<y>30</y>';
	kmlString += '		</Icon>';
	kmlString += '	</IconStyle>';
	kmlString += '</Style>';
	
	kmlString += '<Placemark>';
	kmlString += '	<name>图标</name>';
	kmlString += '	<styleUrl>#webMarkerStyle_0</styleUrl>';
	kmlString += '	<Point>';
	kmlString += '		<coordinates format="bsei">120.35132,30.98097</coordinates>';
	kmlString += '	</Point>';
	kmlString += '</Placemark>';
	kmlString += '<Placemark>';
	kmlString += '	<name>线条</name>';
	kmlString += '	<LineString>';
	kmlString += '		<coordinateMode>ordinal</coordinateMode>';
	kmlString += '		<coordinates format="bsei">120.35749,30.01935,0 120.41929,30.01934,0 120.41105,31.97777,0 120.47216,31.97725,0 120.46598,31.99303,0</coordinates>';
	kmlString += '	</LineString>';
	kmlString += '	<styleUrl>#webLineStyle_1</styleUrl>';
	kmlString += '</Placemark>';
	kmlString += '<Placemark>';
	kmlString += '	<name>图形</name>';
	kmlString += '	<Polygon>';
	kmlString += '		<outerBoundaryIs>';
	kmlString += '			<LinearRing>';
	kmlString += '				<coordinateMode>ordinal</coordinateMode>';
	kmlString += '				<coordinates format="bsei">120.28847,30.00091,0 120.32006,30.00196,0\n120.33585,31.97724,0\n120.28229,31.97092,0</coordinates>';
	kmlString += '			</LinearRing>';
	kmlString += '		</outerBoundaryIs>';
	kmlString += '	</Polygon>';
	kmlString += '	<styleUrl>#webPolygonStyle_2</styleUrl>';
	kmlString += '</Placemark>';
	kmlString += '</Document>';
	kmlString += '</kml>';
	
	basic_se_mapEditor = new SE.MapEditor(); // 定义编辑工具
	basic_se_mapObj.addControl(basic_se_mapEditor); // 放到操作中心
	basic_se_mapEditor.addEditor(basic_se_markerEditor = new SE.MarkerEditor()); // 标注点
	basic_se_mapEditor.addEditor(basic_se_polyLineEditor = new SE.PolyLineEditor()); // 标注线
	basic_se_mapEditor.addEditor(basic_se_polygonEditor = new SE.PolygonEditor()); // 标注面
	
	basic_se_mapEditor.loadKml(kmlString); // 加载字符串
}

/**  
* 地图上最佳显示视图
* @param points LngLat数组
*/
function se_delay_getBestMap(points){
	// 将地图定位到最佳视图
    basic_se_mapObj.getBestMap(points);  
}

/**  
* 地图上当前区域打印
*/
function se_delay_printMap(){
	var html = "<!DOCTYPE html>\n";  
	html += "<html>\n";  
	html += "<head>\n";
	html += "<meta http-equiv=\"Content-Type\" content=\"text\/html; charset=utf-8\">\n";
	html += "<title>Print<\/title>\n";  
	html += "<style type=\"text\/css\">\n";  
	html += "body,html{ width:100%;height:100%;overflow:visible;margin:0;padding:0; }\n";  
	html += "<\/style>\n";  
	html += "<\/head>\n";  
	html += "<body>\n";
	html += "<center>\n";  
	html += basic_se_mapObj.getMapContent()+"\n";
	html += "<\/center>\n";
	html += "<\/body>\n";
	html += "<script type=\"text\/javascript\" language=\"javascript\">";
	html += "setTimeout(function(){";
	html += "    window.print();";
	html += "},3000);";
	html += "<\/script>";
	html += "<\/html>";
	
	var win = document.open("about:blank","win","menubar=1,width="+basic_se_mapObj.container.offsetWidth+",height="+(basic_se_mapObj.container.offsetHeight-20));  
	win.document.writeln(html);  
	win.document.close();  
}

/**  
* 地图上当前区域四个角的坐标
* @ se_delay_fourMapLngLats_wsen_array[] 经纬度数组 string
* 0 西北 1 西南 2 东南 3 东北
*/
var se_delay_fourMapLngLats_wsen_array = [];
function se_delay_fourMapLngLats(){
	var bounds = basic_se_mapObj.getLngLatBounds();  
	var south_west = bounds.getSouthWest();//西南经纬度坐标  
	var north_east = bounds.getNorthEast();//东北经纬度坐标  
	var north_west = new SE.LngLat(south_west.getLng(),north_east.getLat());//西北经纬度坐标  
	var south_east = new SE.LngLat(north_east.getLng(),south_west.getLat());//东南经纬度坐标
	
	se_delay_fourMapLngLats_wsen_array.push((north_west.getLng()/100000.0) + ',' + (north_west.getLat()/100000.0));  
	se_delay_fourMapLngLats_wsen_array.push((south_west.getLng()/100000.0) + ',' + (south_west.getLat()/100000.0)); 
	se_delay_fourMapLngLats_wsen_array.push((south_east.getLng()/100000.0) + ',' + (south_east.getLat()/100000.0));    
	se_delay_fourMapLngLats_wsen_array.push((north_east.getLng()/100000.0) + ',' + (north_east.getLat()/100000.0)); 
}

/**  
* 地图上鼠标移动返回地图中心点经纬度与缩放级别
* @ basic_se_mouseMoveLngLat_clicked_lnglat[] 经纬度与缩放级别数组 string
* 0 经度 1 纬度 2 缩放级别
*/
var basic_se_mouseMove_addListener_move = null; // 监听鼠标移动
var basic_se_mouseMoveLngLat_clicked_lnglat = [];// 最新的每次点击将当前地图中心点经纬度与缩放级别 存放在数组中
function se_delay_mouseMoveLngLat(){
	basic_se_mouseMoveLngLat_clicked_lnglat = [];
	if(basic_se_mouseMove_addListener_move == null){
		basic_se_mouseMove_addListener_move = SE.Event.addListener(basic_se_mapObj, "move", 
		function(){
			basic_se_mouseMoveLngLat_clicked_lnglat = []; // 清空
			var point = basic_se_mapObj.getCenterPoint();
			basic_se_mouseMoveLngLat_clicked_lnglat.push(point.getLng()/100000.0);
			basic_se_mouseMoveLngLat_clicked_lnglat.push(point.getLat()/100000.0);
			basic_se_mouseMoveLngLat_clicked_lnglat.push(basic_se_mapObj.getCurrentZoom());
		});
	}else{
		SE.Event.removeListener(basic_se_mouseMove_addListener_move);
		basic_se_mouseMove_addListener_move = null;
	}
}

/**  
* 地图上鼠标点击返回经纬度与缩放级别
* @ basic_se_mouseClickLngLat_clicked_lnglat[] 经纬度与缩放级别数组 string
* 0 经度 1 纬度 2 缩放级别
*/
var basic_se_mouseClickLngLat_addListener_click = null; // 监听鼠标点击
var basic_se_mouseClickLngLat_clicked_lnglat = []; // 最新的每次点击将当前的经纬度与缩放级别 存放在数组中
function se_delay_mouseClickLngLat(){
	basic_se_mouseClickLngLat_clicked_lnglat = [];
	if(basic_se_mouseClickLngLat_addListener_click == null){
		basic_se_mouseClickLngLat_addListener_click = SE.Event.addListener(basic_se_mapObj, "click", 
		function(point,b){
			if(b == 1){// 左击
				basic_se_mouseClickLngLat_clicked_lnglat = []; // 清空
				var ll = utils_general_pointToLngLat(point);
				basic_se_mouseClickLngLat_clicked_lnglat.push(ll.getLng()/100000.0);
				basic_se_mouseClickLngLat_clicked_lnglat.push(ll.getLat()/100000.0);
				basic_se_mouseClickLngLat_clicked_lnglat.push(basic_se_mapObj.getCurrentZoom());
			}
		});
	}else{
		SE.Event.removeListener(basic_se_mouseClickLngLat_addListener_click);
		basic_se_mouseClickLngLat_addListener_click = null;
	}
}


/**
* 地图定位城市 通过中文名称、拼音、电话区号、行政区号编码
* @param word 输入文字
* @param zoom 地图缩放级别
*/
function se_delay_setCenter(word,zoom){
	basic_se_mapObj.setCenter(word,zoom);
}

/**  
* 地图画多边形 同时进行编辑
*/
var se_delay_onDrawPolygon_bind_draw = null;// 绑定绘画事件
var se_delay_onDrawPolygon_Polygon = null; // 绘画多边形对象
var se_delay_onDrawPolygon_addListener_click = null; // 绑定鼠标移入事件
function se_delay_onDrawPolygon(){
	
	if( se_delay_onDrawPolygon_bind_draw == null){
		// 标注 多边形 可编辑
		se_delay_onDrawPolygon_bind_draw = SE.Event.bind(basic_se_PolygonTool,"draw",basic_se_mapObj,
		function(bounds){
			se_delay_onDrawPolygon_Polygon = new SE.Polygon(bounds); // 绘画多边形对象
			se_delay_onDrawPolygon_Polygon.setLineColor('#0000ff'); // 设置多边形颜色 string
			se_delay_onDrawPolygon_Polygon.setLineStroke(3); // 设置多边形宽度 number px
			basic_se_mapObj.addOverLay(se_delay_onDrawPolygon_Polygon); // 加入图层中
			basic_se_PolygonTool.close(); // 绘画多边形工具关闭
		
			// 编辑结束
			SE.Event.addListener(se_delay_onDrawPolygon_Polygon,"editend",
			function(){
				
				// 判断是否在区域内
				
			});
			// 移动上去
			se_delay_onDrawPolygon_addListener_click = SE.Event.addListener(se_delay_onDrawPolygon_Polygon,'click',
			function(){
				if(!se_delay_onDrawPolygon_Polygon.isEditable())
					se_delay_onDrawPolygon_Polygon.enableEdit(); // 激活可编辑状态
				else
					se_delay_onDrawPolygon_Polygon.disableEdit(); // 不可编辑状态
			});
		}); 

		basic_se_PolygonTool.open(); // 绘画多边形工具打开
	}else{
		if(se_delay_onDrawPolygon_Polygon != null && se_delay_onDrawPolygon_Polygon.isEditable())
			se_delay_onDrawPolygon_Polygon.disableEdit(); // 不可编辑状态
			
		// 删除监听
		SE.Event.removeListener(se_delay_onDrawPolygon_bind_draw);
		SE.Event.removeListener(se_delay_onDrawPolygon_addListener_click);
		se_delay_onDrawPolygon_bind_draw = null;
		se_delay_onDrawPolygon_addListener_click = null;
		
		basic_se_PolygonTool.close();// 绘画多边形工具关闭
		
		// 删除多边形
		if(se_delay_onDrawPolygon_Polygon != null){
			basic_se_mapObj.removeOverLay(se_delay_onDrawPolygon_Polygon);
		}
	}
}

/**  
* 地图画圆形 同时进行编辑
*/
var se_delay_onDrawCircle_bind_draw = null;// 绑定绘画事件
var se_delay_onDrawCircle_Circle = null; // 绘画圆形对象
var se_delay_onDrawCircle_addListener_click = null; // 绑定鼠标移入事件
var se_delay_onDrawCircle_addListener_drawend = null; // 绑定绘画结束事件
var se_delay_onDrawCircle_addListener_editend = null; // 绑定绘画编辑结束事件
function se_delay_onDrawCircle(){
	
	if( se_delay_onDrawCircle_bind_draw == null){
		// 标注 圆形 可编辑
		se_delay_onDrawCircle_bind_draw = SE.Event.bind(basic_se_CircleTool,"draw",basic_se_mapObj,
		function(center,radius){
		}); 
		
		se_delay_onDrawCircle_addListener_drawend = SE.Event.addListener(basic_se_CircleTool,'drawend',
		function(c){    
            var center = c.getCenter();  
            var radius = c.getRadius();  
            se_delay_onDrawCircle_Circle = new SE.Circle(center,radius); // 绘画圆形对象
			se_delay_onDrawCircle_Circle.setLineColor('#0000ff'); // 设置圆形颜色 string
			se_delay_onDrawCircle_Circle.setLineStroke(3); // 设置圆形宽度 number px
			basic_se_mapObj.addOverLay(se_delay_onDrawCircle_Circle); // 加入图层中
			basic_se_CircleTool.close(); // 绘画圆形工具关闭
			
			// 编辑结束
			se_delay_onDrawCircle_addListener_editend = SE.Event.addListener(se_delay_onDrawCircle_Circle,"editend",
			function(){
				this.endEdit();// 结束编辑
				
				// 判断是否在区域内
				
			});
			// 移动上去
			se_delay_onDrawCircle_addListener_click = SE.Event.addListener(se_delay_onDrawCircle_Circle,'click',
			function(){
				if(!se_delay_onDrawCircle_Circle.isEditable())
					se_delay_onDrawCircle_Circle.enableEdit(); // 激活可编辑状态
				else
					se_delay_onDrawCircle_Circle.disableEdit(); // 不可编辑状态
			});
        });  

		basic_se_CircleTool.open(); // 绘画圆形工具打开
	}else{
		if(se_delay_onDrawCircle_Circle != null && se_delay_onDrawCircle_Circle.isEditable())
			se_delay_onDrawCircle_Circle.disableEdit(); // 不可编辑状态
	
		// 删除监听
		SE.Event.removeListener(se_delay_onDrawCircle_bind_draw);
		SE.Event.removeListener(se_delay_onDrawCircle_addListener_click);
		SE.Event.removeListener(se_delay_onDrawCircle_addListener_drawend);
		SE.Event.removeListener(se_delay_onDrawCircle_addListener_editend);
		
		se_delay_onDrawCircle_bind_draw = null;
		se_delay_onDrawCircle_addListener_click = null;
		se_delay_onDrawCircle_addListener_drawend = null;
		se_delay_onDrawCircle_addListener_editend = null;
		
		basic_se_CircleTool.close();// 绘画圆形工具关闭
		
		// 删除圆形
		if(se_delay_onDrawCircle_Circle != null){
			basic_se_mapObj.removeOverLay(se_delay_onDrawCircle_Circle);
		}
	}
}

/**  
* 地图画矩形 同时进行编辑
*/
var se_delay_onDrawRect_bind_draw = null;// 绑定绘画事件
var se_delay_onDrawRect_Rect = null; // 绘画矩形对象
var se_delay_onDrawRect_addListener_click = null; // 绑定鼠标移入事件
function se_delay_onDrawRect(){
	
	if( se_delay_onDrawRect_bind_draw == null){
		// 标注 线 可编辑
		se_delay_onDrawRect_bind_draw = SE.Event.bind(basic_se_RectTool,"draw",basic_se_mapObj,
		function(bounds){
			se_delay_onDrawRect_Rect = new SE.Rect(bounds); // 绘画矩形对象
			se_delay_onDrawRect_Rect.setLineColor('#0000ff'); // 设置矩形颜色 string
			se_delay_onDrawRect_Rect.setLineStroke(3); // 设置矩形宽度 number px
			basic_se_mapObj.addOverLay(se_delay_onDrawRect_Rect); // 加入图层中
			basic_se_RectTool.close(); // 绘画矩形工具关闭
		
			// 编辑结束
			SE.Event.addListener(se_delay_onDrawRect_Rect,"editend",
			function(){
				
				// 判断是否在区域内
				
			});
			// 移动上去
			se_delay_onDrawRect_addListener_click = SE.Event.addListener(se_delay_onDrawRect_Rect,'click',
			function(){
				if(!se_delay_onDrawRect_Rect.isEditable())
					se_delay_onDrawRect_Rect.enableEdit(); // 激活可编辑状态
				else
					se_delay_onDrawRect_Rect.disableEdit(); // 不可编辑状态
			});
		}); 

		basic_se_RectTool.open(); // 绘画矩形工具打开
	}else{
		if(se_delay_onDrawRect_Rect != null && se_delay_onDrawRect_Rect.isEditable())
			se_delay_onDrawRect_Rect.disableEdit(); // 不可编辑状态
			
		// 删除监听
		SE.Event.removeListener(se_delay_onDrawRect_bind_draw);
		SE.Event.removeListener(se_delay_onDrawRect_addListener_click);
		se_delay_onDrawRect_bind_draw = null;
		se_delay_onDrawRect_addListener_click = null;
		
		basic_se_RectTool.close();// 绘画矩形工具关闭
		
		// 删除矩形
		if(se_delay_onDrawRect_Rect != null){
			basic_se_mapObj.removeOverLay(se_delay_onDrawRect_Rect);
		}
	}
} 

/**  
* 地图上中心点直接变换到指定的地理坐标
* @param lng,lat 经纬度 坐标 number
*/
function se_delay_setCenterAtLngLat(lng,lat){
	// 将地图中心点变换至指定地理坐标
    basic_se_mapObj.setCenterAtLngLat(new SE.LngLat(Number(lng),Number(lat)));  
}
