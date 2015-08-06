//以下部分为高德地图处理
var mapObj = null; 
var windowsArr = new Array();    
var marker = new Array(); 
var mmap_basic_geocoder = null;
//初始化地图
function mapInit(){
	mapObj = new MMap.Map("mapContainer",{  
		center:new MMap.LngLat(120.359,30.8532), //地图中心点  杭州
		level:8  //地图显示的缩放级别  
	});  

	//加载工具条  
	mapObj.plugin(["MMap.ToolBar","MMap.OverView","MMap.Scale"],function(){  
		var tool=new MMap.ToolBar();  
		mapObj.addControl(tool);  
		//加载鹰眼  
		var view=new MMap.OverView();  
		mapObj.addControl(view);  
		//加载比例尺  
		var scale=new MMap.Scale();  
		mapObj.addControl(scale); 
	});
}
//数组去除重复项  zjr
Array.prototype.unique = function() { 
	var a = {}, c = [], l = this.length; 
	for (var i = 0; i < l; i++) { 
		var b = this[i]; 
		var d = (typeof b) + b; 
		if (a[d] === undefined) { 
			c.push(b); 
			a[d] = 1; 
		} 
	} 
	return c; 
} 

