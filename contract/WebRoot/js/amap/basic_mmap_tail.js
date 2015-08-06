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
} 

//添加地图标
function gpsTrackMarkerAdd(jsondata){
	var realtime = jsondata.realtime //实时时间
		speed = jsondata.speed; //时速
		rt = realtime.substring(0,realtime.indexOf('.'));
		lng = jsondata.longitude; //经度
		lat = jsondata.latitude; //纬度
		if(lng.trim()=="0"||lat.trim()=="0") return;
		var dimX = parseFloat(lng);
		var dimY = parseFloat(lat) ;
		var gpsXY = sp_gpsfix_init.transform(dimX,dimY);// 经纬度纠偏
		var partyid = jsondata.partyid;
		var deviceid = jsondata.deviceid;
		if(partyid != null && partyid != ""){//手机号
			devOrpar = "手机号："+partyid+"<br/>";
		}else{//设备号
			devOrpar = "设备号："+deviceid+"<br/>";
		}
		lnglat = new MMap.LngLat(parseFloat(gpsXY.X),parseFloat(gpsXY.Y));
		mmap_basic_gpsTrackPoints.push(lnglat)
		mmap_basic_mapObj.setCenter(lnglat); //设置地图的中心点
		//逆地理编码	
		if(mmap_basic_geocoder == null){
			mmap_basic_geocoder = new MMap.Geocoder({  
				radius: 1000, // 以已知坐标为中心点，radius为半径，返回范围内兴趣点和道路信息
				extensions: "all"// 返回地址描述以及附近兴趣点和道路信息，默认“base”
			}); 
		}
		mmap_basic_geocoder.regeocode(lnglat,geocoder_CallBack);
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
    	   position:lnglat, //位置 
    	   icon:"../js/amap/imgs/car.png",//复杂图标
    	   draggable:false, //不可拖动
    	   cursor:"default",//鼠标悬停时显示的光标
    	   offset:new MMap.Pixel(-16,-16) //相对于基点的偏移量 
    }); 
	//判断解析出的地址长度，纵向调整信息窗体的位置
	var chineseCount=GetChineseCount(address);//得到字符串中的中文字符数
	var piexy;
    var piexparam=address.length;//字符串中的总长度
    var count;
    
    //如果字符串中全是中文字符就不做任何处理，如果有数字和英文字符，
    //就按照2个数字占用的字节数相当于一个中文字符的字节数来计算
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
	mmap_basic_mapObj.addOverlays(mmap_basic_marker);
    mmap_basic_mapObj.setCenter(mmap_basic_marker.position);
    
    var infoWindow = new MMap.InfoWindow({  
	     isCustom:true,  // 使用自定义窗体
	     content:mmap_basic_createInfoWindow('GPS实时轨迹跟踪&nbsp;&nbsp;',"车牌号："+carplatenumber+"<br/>所属机构：<br/>"+devOrpar+"速度："+speed+"km/h"+"<br/>GPS时间："+rt+"<br/>当前位置："+address+"<br/>驾驶员："+realname+"<br/>电话："+mobilenumber+"<br/>"),
	     size:new MMap.Size(300, 0),
	     offset:new MMap.Pixel(-128, -258-piexy*20)// -113,// -140
	});

    //实例化信息窗体  
    infoWindow.open(mmap_basic_mapObj,mmap_basic_marker.getPosition());
    //鼠标点击marker弹出自定义的信息窗体 
    mmap_basic_mapObj.bind(mmap_basic_marker,'click',function(e){ 
    	infoWindow.open(mmap_basic_mapObj,mmap_basic_marker.getPosition()); 
    });
  //画轨迹
    if(mmap_basic_gpsTrackPolyLine!=null){
    	mmap_basic_gpsTrackPolyLine.setPath(mmap_basic_gpsTrackPoints);
    }else{
    	mmap_basic_gpsTrackPolyLine=new MMap.Polyline({ 
			id:"line_0",
			path:mmap_basic_gpsTrackPoints,
			strokeColor:"#9905f7", //线颜色 
			strokeOpacity:0.6, //线透明度	  
			strokeWeight:7, //线宽 	 
			strokeStyle:"solid"//线样式	 
		}); 
		mmap_basic_mapObj.addOverlays(mmap_basic_gpsTrackPolyLine);//将轨迹拆线添加到地图上
    }
}

// 关闭信息窗体
function mmap_basic_closeInfoWindow(){  
    mmap_basic_mapObj.clearInfoWindow();  
}
var mmap_basic_gpsTrackPoints = [];  // GPS监控轨迹点集合
var mmap_basic_gpsTrackMarker = null; // GPS监控实物点标注对象
var mmap_basic_gpsTrackMarkerInfo = null;  // GPS监控实物点信息窗口
var mmap_basic_gpsTrackPolyLine = null; // GPS监控实物点轨迹对象

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
//定位，若标点存在 ，将将其设置为地图中心点
function setLocation(){
	if(mmap_basic_marker!=null){//已开始回放
		mmap_basic_mapObj.panTo(mmap_basic_marker.getPosition());
	}
	$("#float_location").addClass("none");
}