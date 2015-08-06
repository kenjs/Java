<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>快到网-个人中心-跟踪车辆</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/threeLinkage/css/cityLayout.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<sys:context/>/resource/js/basis.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/pageInfoThree.js"></script>
<style type="text/css">
.city_input { border:1px solid #d6d6d6; width:120px; height:30px;
 background:url(<sys:context/>/resource/threeLinkage/images/ts-indexcity.png) no-repeat; 
 line-height:30px; margin-top:5px;  text-indent:5px;}
 
</style>

<script type="text/javascript">
 $(function(){
    //判断是否登录（关闭该弹出框，主要面跳转）
	// if($("#userObj").val()==""){
	  //  pageJump('<sys:context/>/dcts/user/login.jsp');
	 //}
 
    var totalPages = ${locationCollectInfoDomain.pageInfo.totalPages};//总页数
	var curPageNos = ${locationCollectInfoDomain.pageInfo.curPageNo};//当前页数
	var pageSize = ${locationCollectInfoDomain.pageInfo.pageSize};//每页显示数据
	var totalRecords = ${locationCollectInfoDomain.pageInfo.totalRecords};//总记录数
	pageInfo(totalPages,curPageNos,totalRecords);
   });
   
   function getSubmit(flag){
   // 区分点击的是搜索按钮（初始化从第一条开始查）还是分页链接 
      $("#flagId").val(flag);
       //提交表单
      document.getElementById('mainForm').submit();
   }
 </script>
</head>

<body>
<!-- 保存登录用户对象-js未登录是跳到登录页面 art.dialog.open打开该页面时 -->
   <!--  <input type="hidden" id="userObj" value="<s:property value="#session.user"/>"/> -->
    
<jsp:include page="/head.jsp" />

<!--个人中心-->
<div class="mian">
	<div class="mian_bor">
	<jsp:include page="/dcts/myCenter/myCenterLeftMenu.jsp" />
    <div class="fr sonafr">
    <!-- 放地图 -->
    <div class="data dataf" >
        	<h3 class="mlf"><i>&nbsp;</i>司机当前位置</h3>
            <div class="maput" id="allmap"></div>
           <!-- <div class="utcnt"><input name="" type="button" value="返回" class="subt"  /></div> --> 
        </div>
    <!-- 放列表 -->
    <div class="w820">
    <s:form id="mainForm" action="/queryLocationInfo" namespace="/" method="post">
     <!-- 区分点击的是搜索按钮（初始化从第一条开始查）还是分页链接 -->
	 <input type="hidden" id="flagId" value="" name="flag"/>
     <input type="hidden" name="locationCollectInfoDomain.driverId" value="${locationCollectInfoDomain.driverId }"/>
     <div class="data dataf" >
    	<h3><i>&nbsp;</i>运输动态</h3>
        <div class="nerh">
        <ul>
            <li>定位时间：</li>
            <li>
              <input name="locationCollectInfoDomain.startTime" value="${locationCollectInfoDomain.startTime }" readonly="readonly" onClick="WdatePicker()" type="text" />
            </li>
            <li>至</li>
            <li>
              <input name="locationCollectInfoDomain.endTime" value="${locationCollectInfoDomain.endTime }" readonly="readonly" onClick="WdatePicker()" type="text" />
            </li>
            <li><a href="javascript:getSubmit('0')" class="sout">搜索</a></li>
            <li>定位地址：</li>
            <li>
              <input name="locationCollectInfoDomain.searchLocation" style="width: 157px;" value="${locationCollectInfoDomain.searchLocation }" id="searchLocation" type="text"  class="city_input  inputFocus proCityQueryAll proCitySelAll int300" />
            </li>
          </ul>
        </div>
		  <table border="0" cellpadding="0" cellspacing="0">
            <thead >
              <tr>
                <td width="171">时间</td>
                <td>跟踪信息</td>
              </tr>
            </thead>
            <s:if test="locationCollectInfoDomain.list.size>0">
               <s:iterator value="locationCollectInfoDomain.list">
                   <tr>
		              <td>${collectTime }</td>
		              <td><span class="icon2">&nbsp;</span>${location }</td>
		            </tr> 
               </s:iterator>
            </s:if>
            
          </table>
               <!-- <p class="more"><a href="">更多&gt;&gt;</a></p> -->
          <s:if test="locationCollectInfoDomain.list.size()>0">
			<div class="numberBox" id="pageInfoHtmlId">  
			</div>
			</s:if>
			<input type='hidden' id='curPage' name='locationCollectInfoDomain.pageInfo.curPage' value='${locationCollectInfoDomain.pageInfo.curPage}'/><!-- 需要显示页面 -->
			<input type='hidden' id='curPageNo' name='locationCollectInfoDomain.pageInfo.curPageNo' value='${locationCollectInfoDomain.pageInfo.curPageNo}'/><!-- 当前页面 -->
			<input type='hidden' id='locationCollectInfoDomain.pageInfo.pageSize' name='locationCollectInfoDomain.pageInfo.pageSize' value='${locationCollectInfoDomain.pageInfo.pageSize}'/><!-- 当前页面 -->
			  
        </div>
        </s:form>
       </div>
        
    </div>
    </div>
      <!-- 合作伙伴 -->
       <div class="both mh"></div>
    <jsp:include page="/cooperativePartner.jsp" />
</div>
<br />
<br />
<br />

<!--个人中心结束-->
<jsp:include page="/bottom.jsp" />
<!-- 弹出省市区的层 -->
<jsp:include page="/resource/threeLinkage/provinceCityAreaDiv.jsp" />
 <script src="<sys:context/>/resource/threeLinkage/js/jquery-1.6.2.min.js"></script>
 <script src="<sys:context/>/resource/threeLinkage/js/public.js"></script>
 
 <input type="hidden" value="${locationCollectLastInfoDomain.code}" id="driverCode"/>
 <input type="hidden" value="${locationCollectLastInfoDomain.carNumber}" id="driverCarNumber"/>
 <!-- 当前位置的经纬度 -->
<input type="hidden" id="longitude" value="${locationCollectLastInfoDomain.longitude}"/>
<input type="hidden" id="latitude" value="${locationCollectLastInfoDomain.latitude}"/>
</body>
</html>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=GGFRAysCPHhn05mzdPayTZsc"></script>
<script type="text/javascript"> 
		var marker;
		//当前位置的经度和纬度
		var longitudeDefault=$("#longitude").val();
		var latitudeDefault=$("#latitude").val();
		//设置DEFAULT VALUE  （默认华星现代产业园）
		//if(longitudeDefault == '' || longitudeDefault == undefined){
		//longitudeDefault = 120.12850189208984;
			//latitudeDefault = 30.281110763549805;
		//}
		if((longitudeDefault != '' && longitudeDefault != undefined)&&(latitudeDefault !='' && latitudeDefault != undefined)){
			// 百度地图API功能 
		var map = new BMap.Map("allmap");            // 创建Map实例
		var point = new BMap.Point(longitudeDefault, latitudeDefault);    // 创建点坐标
		map.centerAndZoom(point,15);                     // 初始化地图,设置中心点坐标和地图级别。
		map.enableScrollWheelZoom();                            //启用滚轮放大缩小
		map.addControl(new BMap.NavigationControl());  //添加默认缩放平移控件
		map.addControl(new BMap.OverviewMapControl({isOpen:true, anchor: BMAP_ANCHOR_TOP_RIGHT}));   //添加缩略地图控件(右上角，打开) 
		map.addControl(new BMap.ScaleControl({anchor: BMAP_ANCHOR_BOTTOM_LEFT}));                    //  添加比例尺控件(左下)
		addMarker(longitudeDefault, latitudeDefault);
		
		}
		
		// 编写自定义函数,创建标注并展示
		function addMarker(longitude,latitude){
		var points = new BMap.Point(longitude, latitude);    // 创建点坐标
		 marker= new BMap.Marker(points);
		 // marker.setMap("");
		  map.addOverlay(marker);
		  map.centerAndZoom(points,15); 
		  
		  // 百度地图API功能()
		  var driverCode=$("#driverCode").val();
		  var driverCarNumber=$("#driverCarNumber").val();
		var sContent =
		//"<div><h4 style='margin:0 0 5px 0;padding:0.2em 0'>司机车辆信息:</h4>" + 
		"<div><h4 style='margin:0 0 5px 0;padding:0.2em 0'>&nbsp;</h4>" + 
		"<div><p style='font-size:13px;'>司机手机号："+driverCode+",车牌号："+driverCarNumber+"</p>" + 
		"</div>";
		  var infoWindow = new BMap.InfoWindow(sContent);  // 创建信息窗口对象
		  marker.addEventListener("mouseover", function(){          
			   this.openInfoWindow(infoWindow);
			});
		}
		
	</script>
