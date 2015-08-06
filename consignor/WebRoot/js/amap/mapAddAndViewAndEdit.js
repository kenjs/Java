//以下部分为高德地图处理
var mapObj = null; 
var windowsArr = new Array();    
var marker = new Array(); 
//初始化地图
function mapInit(){
	   mapObj = new AMap.Map("mapContainer",{  
		    center:new AMap.LngLat(120.359,30.8532), //地图中心点  杭州
		    level:8  //地图显示的缩放级别  
		    });  

//加载工具条  
mapObj.plugin(["AMap.ToolBar","AMap.OverView","AMap.Scale"],function(){  
    var tool=new AMap.ToolBar();  
    mapObj.addControl(tool);  
    //加载鹰眼  
    var view=new AMap.OverView();  
    mapObj.addControl(view);  
    //加载比例尺  
    var scale=new AMap.Scale();  
    mapObj.addControl(scale); 
 });
//添加地图类型切换插件  
mapObj.plugin(["AMap.MapType"],function(){  
    //地图类型切换  
    type= new AMap.MapType({defaultType:0});//初始状态使用2D地图  
    mapObj.addControl(type);  
});  
}

//搜索地址显示
function geocoder() {  
	var address = $("#keyword").val();//获得input输入框填写的地址
    var MGeocoder;  
    //加载地理编码插件  
    mapObj.plugin(["AMap.Geocoder"], function() {          
        MGeocoder = new AMap.Geocoder({   
            city:"010", //城市，默认：“全国”  
            radius:1000 //范围，默认：500  
        });  
        //返回地理编码结果   
        AMap.event.addListener(MGeocoder, "complete", geocoder_CallBack);   
        //地理编码  
        MGeocoder.getLocation(address);   
    });  
}    
//地理编码返回结果展示     
function geocoder_CallBack(data){  
    var resultStr="";  
    //地理编码结果数组  
    var geocode = new Array();  
    geocode = data.geocodes;    
    for (var i = 0; i < geocode.length; i++) {  
        //拼接输出html  
        resultStr += "<span>"+"<b>地址</b>："+geocode[i].formattedAddress+"</span>";    
        var windowsArr = new Array();    
        var markerOption = {  
            map:mapObj,                   
            icon:"http://api.amap.com/webapi/static/Images/"+(i+1)+".png",    
            position:new AMap.LngLat(geocode[i].location.getLng(),geocode[i].location.getLat()),
            draggable:true
        };              
        var mar = new AMap.Marker(markerOption);    
          
        var infoWindow = new AMap.InfoWindow({    
            content:geocode[i].formattedAddress,    
            size:new AMap.Size(150,0),    
            offset:{x:0,y:-30}  
        });    
        windowsArr.push(infoWindow);    
          
        var aa = function(e){infoWindow.open(mapObj,mar.getPosition());};    
        AMap.event.addListener(mar,"click",aa);    
    }    
    mapObj.setFitView();     
    document.getElementById("addressResult").innerHTML = resultStr;    
}  


//输入提示  
function autoSearch() {   
var keywords = document.getElementById("keyword").value;  
var auto;    
var autoOptions = {  
    city: "" //城市，默认全国  
};  
auto = new AMap.Autocomplete(autoOptions);  
//查询成功时返回查询结果  
AMap.event.addListener(auto, "complete", autocomplete_CallBack);  
auto.search(keywords);  
}  

//输出输入提示结果的回调函数  
function autocomplete_CallBack(data) {  
var resultStr = "";  
var tipArr = [];  
tipArr = data.tips;  
if (tipArr.length>0) {                    
    for (var i = 0; i < tipArr.length; i++) {  
        resultStr += "<div id='divid" + (i + 1) + "' onmouseover='openMarkerTipById(" + (i + 1)  
                    + ",this)' onclick='selectResult(" + i + ")' onmouseout='onmouseout_MarkerStyle(" + (i + 1)  
                    + ",this)' style=\"font-size: 13px;cursor:pointer;padding:5px 5px 5px 5px;\">" + tipArr[i].name + "<span style='color:#C1C1C1;'>"+ tipArr[i].district + "</span></div>";  
    }  
}  
else  {  
    resultStr = " π__π 亲,人家找不到结果!<br />要不试试：<br />1.请确保所有字词拼写正确<br />2.尝试不同的关键字<br />3.尝试更宽泛的关键字";  
}  
 
document.getElementById("result1").innerHTML = resultStr;  
document.getElementById("result1").style.display = "block";  
}  

//输入提示框鼠标滑过时的样式  
function openMarkerTipById(pointid, thiss) {  //根据id打开搜索结果点tip    
thiss.style.background = '#CAE1FF';  
}  

//输入提示框鼠标移出时的样式  
function onmouseout_MarkerStyle(pointid, thiss) {  //鼠标移开后点样式恢复    
thiss.style.background = "";  
}  

//从输入提示框中选择关键字并查询  
function selectResult(index) {  
if (navigator.userAgent.indexOf("MSIE") > 0) {  
    document.getElementById("keyword").onpropertychange = null;  
    document.getElementById("keyword").onfocus = focus_callback;  
}  
//截取输入提示的关键字部分  
var text = document.getElementById("divid" + (index + 1)).innerHTML.replace(/<[^>].*?>.*<\/[^>].*?>/g,"");;  
document.getElementById("keyword").value = text;  
document.getElementById("result1").style.display = "none";  
//根据选择的输入提示关键字查询  
mapObj.plugin(["AMap.PlaceSearch"], function() {          
    var msearch = new AMap.PlaceSearch();  //构造地点查询类  
    AMap.event.addListener(msearch, "complete", placeSearch_CallBack); //查询成功时的回调函数  
    msearch.search(text);  //关键字查询查询  
});  
}  

//定位选择输入提示关键字  
function focus_callback() {  
if (navigator.userAgent.indexOf("MSIE") > 0) {  
    document.getElementById("keyword").onpropertychange = autoSearch;  
}  
}  

//输出关键字查询结果的回调函数  
function placeSearch_CallBack(data) {  
//清空地图上的InfoWindow和Marker  
windowsArr = [];  
marker     = [];  
mapObj.clearMap();  
var resultStr1 = "";  
var poiArr = data.poiList.pois;  
var resultCount = poiArr.length;  
for (var i = 0; i < resultCount; i++) {  
    resultStr1 += "<div id='divid" + (i + 1) + "' onclick='getOneAddress()' onmouseover='openMarkerTipById1(" + i + ",this)' onmouseout='onmouseout_MarkerStyle(" + (i + 1) + ",this)' style=\"font-size: 12px;cursor:pointer;padding:0px 0 4px 2px; border-bottom:1px solid #C1FFC1;\"><table><tr><td><img src=\"http://webapi.amap.com/images/" + (i + 1) + ".png\"></td>" + "<td><h3><font color=\"#00a6ac\">名称:" + poiArr[i].name + "</font></h3>";  
        resultStr1 += TipContents(poiArr[i].type, poiArr[i].address, poiArr[i].tel) + "</td></tr></table></div>";  
        addmarker(i, poiArr[i]);  
}  
mapObj.setFitView(); 
document.getElementById("result").innerHTML = ""; 
document.getElementById("result").innerHTML = resultStr1;  
document.getElementById("result").style.display = "block";  
}  

//鼠标滑过查询结果改变背景样式，根据id打开信息窗体  
function openMarkerTipById1(pointid, thiss) {  
thiss.style.background = '#CAE1FF';  
windowsArr[pointid].open(mapObj, marker[pointid]); 
}  

//添加查询结果的marker&infowindow ,标注点上信息框的显示     
function addmarker(i, d) {  
var lngX = d.location.getLng();  
var latY = d.location.getLat();  
var markerOption = {  
    map:mapObj,  
    icon:"http://webapi.amap.com/images/" + (i + 1) + ".png",  
    position:new AMap.LngLat(lngX, latY)  
};  
var mar = new AMap.Marker(markerOption);            
marker.push(new AMap.LngLat(lngX, latY));  

var infoWindow = new AMap.InfoWindow({  
    content:"<table><form><tr><td><h3><font color=\"#00a6ac\">&nbsp;&nbsp;" + (i + 1) + ". " + d.name + "</font></h3></td></tr>"+
    '<tr><td><font color=\"#00a6ac\">电话:&nbsp;&nbsp;'+ d.tel +'</font></td></tr>'+  
    "<tr><td><font color=\"#00a6ac\">地图地址:&nbsp;"+d.address+"</font></td></tr>",
    size:new AMap.Size(230, 0),   
    autoMove:true,    
    offset:new AMap.Pixel(0,-30)  
});  
windowsArr.push(infoWindow);   
var aa = function (e) {infoWindow.open(mapObj, mar.getPosition());};  
AMap.event.addListener(mar, "click", aa); 
}  



//infowindow搜索查询后的显示内容  
function TipContents(type, address, tel) {  //窗体内容  
if (type == "" || type == "undefined" || type == null || type == " undefined" || typeof type == "undefined") {  
    type = "暂无";  
}  
if (address == "" || address == "undefined" || address == null || address == " undefined" || typeof address == "undefined") {  
    address = "暂无";  
}  
if (tel == "" || tel == "undefined" || tel == null || tel == " undefined" || typeof address == "tel") {  
    tel = "暂无";  
}  
var str = "&nbsp;&nbsp;地址：" + address + "<br />&nbsp;&nbsp;电话：" + tel + " <br />&nbsp;&nbsp;类型：" + type;  
return str;  
}  

//当地址输入框鼠标锁定时，隐藏关键字查询后显示数据的div
function removeDiv(){
	$("#result").hide();
}

//点击关键字查询后显示数据的div上名字
function getOneAddress(){
	$("#result").hide();
}

