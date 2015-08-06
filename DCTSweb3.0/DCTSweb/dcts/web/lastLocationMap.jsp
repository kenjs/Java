<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<%
  String longitudeDefault=request.getParameter("longitudeDefault");
  String latitudeDefaul=request.getParameter("latitudeDefault");
 %>
<head>
<title>当前位置的地图</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=GGFRAysCPHhn05mzdPayTZsc"></script>
</head>
<body>
 <!-- 地图要用的经纬度 -->
<input type="hidden" id="longitudeId" value="<%=longitudeDefault %>"/>
<input type="hidden" id="latitudeId" value="<%=latitudeDefaul %>"/>
 <div id="popMap" style="width:780px;height:600px;"></div>
</body>
<script type="text/javascript">
   //当前位置的经度和纬度 
		var longitudeDefault=$("#longitudeId").val();
		var latitudeDefault=$("#latitudeId").val();
     //加载弹出地图
	 popMapDiv("popMap");
	    
  function popMapDiv(mapId){
		if((longitudeDefault != '' && longitudeDefault != undefined)&&(latitudeDefault !='' && latitudeDefault != undefined)){
			// 百度地图API功能 
		var maps = new BMap.Map(mapId);            // 创建Map实例
		var point = new BMap.Point(longitudeDefault, latitudeDefault);    // 创建点坐标
		maps.centerAndZoom(point,15);                     // 初始化地图,设置中心点坐标和地图级别。
		maps.enableScrollWheelZoom();                            //启用滚轮放大缩小
		maps.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
		maps.addControl(new BMap.OverviewMapControl({isOpen:true, anchor: BMAP_ANCHOR_TOP_RIGHT}));   //添加缩略地图控件(右上角，打开) 
		maps.addControl(new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT}));                    //  添加比例尺控件(左下)
		addMarker(longitudeDefault, latitudeDefault,maps,14);
		}
  }
		// 编写自定义函数,创建标注并展示
	function addMarker(longitude,latitude,map,rank){
		var points = new BMap.Point(longitude, latitude);    // 创建点坐标
		var marker= new BMap.Marker(points);
		 // marker.setMap("");
		  map.addOverlay(marker);
		  map.centerAndZoom(points,16); 
  }
</script>
</html>