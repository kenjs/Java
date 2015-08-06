
/**
* 标注 所用图标路径
* return Icon字符串路径
*/
function utils_general_iconurl(color){
	switch(color) {
		// 图钉
		case 'blue':
			return 'images/spot/default/spot_blue.png';
			break;
		case 'green':
			return 'images/spot/default/spot_green.png';
			break;
		case 'red':
			return 'images/spot/default/spot_red.png';
			break;
		case 'yerrow':
			return 'images/spot/default/spot_yerrow.png';
			break;
			
		// 旗帜
		case 'messagemark':
			return 'images/spot/flag/messagemark.png';
			break;
		case 'position':
			return 'images/spot/flag/position.png';
			break;
		case 'qizi':
			return 'images/spot/flag/qizi.png';
			break;
		case 'serch':
			return 'images/spot/flag/serch.png';
			break;
		
		// 图形三角
		case '13_green':
			return 'images/spot/delineation/13_green.png';
			break;
		case '13_off':
			return 'images/spot/delineation/13_off.png';
			break;
		case '13_stop':
			return 'images/spot/delineation/13_stop.png';
			break;
		case '13_warn':
			return 'images/spot/delineation/13_warn.png';
			break;
		case '14_green':
			return 'images/spot/delineation/14_green.png';
			break;
		case '14_off':
			return 'images/spot/delineation/14_off.png';
			break;
		case '14_stop':
			return 'images/spot/delineation/14_stop.png';
			break;
		case '14_warn':
			return 'images/spot/delineation/14_warn.png';
			break;
		
		default:
			return 'images/spot/default/spot_blue.png';
	}
}

/**
* 地图上像素坐标转 经纬度
* @param  point 地图上像素坐标
* return 经纬度 SE.LngLat
*/
function utils_general_pointToLngLat(point){
	var lnglat = basic_se_mapObj.fromContainerPixelToLngLat(point);  
	return lnglat
}

/**
* 地图上经纬度转 像素坐标 
* @param lnglat 地图上经纬度
* return 像素坐标 SE.Point
*/
function utils_general_lngLatToPoint(lnglat){
	var point = basic_se_mapObj.fromLngLatToContainerPixel(lnglat);  
	return point
}

/**
* 地图上指定缩放级别
* @param zoom 缩放级别数字
*/
function utils_general_setZoom(zoom){
	basic_se_mapObj.setZoom(zoom);
}