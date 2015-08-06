<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
	<title>快到网-中国最大的车源货源发布平台  物流供需|车源货源|  快到网56top.cn</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<script src="<sys:context/>/resource/js/jquery.js"></script>
	<script src="<sys:context/>/resource/js/gsjs.js"></script>
	<script src="<sys:context/>/resource/js/jquery.js"></script>
	<script type="text/javascript"  src="<sys:context/>/resource/js/basis.js"></script>
	<script src="<sys:context/>/resource/js/web/queryDriverDetailed.js"></script>
	<script src="<sys:context/>/resource/js/jquery.SuperSlide.2.1.1.js"></script>
	
	<script src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
	<script src="<sys:context/>/resource/artDialog4.1.6/jquery.artDialog.js"></script>
    <script type="text/javascript"   src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>
	<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
	<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
$(function(){
	driverBusinessLineById();
	driverLineById();
	driverAppraiseCountById();
	getRecommendRealDiverCar();
});
</script>	
</head>

<body>
<!-- 头部开始 -->
<jsp:include page="/head.jsp" />
<!-- 头部结束 -->
<div class="mian">
    <div class="both mh36"></div>
	<div class="border">
    	<div class="fl flount">
            <div class="maotf_two">车辆信息</div>
              <div class="fl mat_rhgt mat_row">
                	<h3><i>&nbsp;</i>司机档案</h3>
                	<input type="hidden" id="driverId" name="driverId" value="${driverUserInfoDomain.id }"/>
                	<!-- 百度云推送消息提醒司机确认运输货物 -->
                	<input type="hidden" id="baiduUserId" name="baiduUserId" value="${driverUserInfoDomain.baiduUserId }"/>
                	<input type="hidden" id="baiduChannelId" name="baiduChannelId" value="${driverUserInfoDomain.baiduChannelId }"/>
                    <ul> 
                    	<li><label>司机姓名：</label>${driverUserInfoDomain.name }<span class="icon m12">&nbsp;</span></li>
                        <li><label>车牌：</label>${driverUserInfoDomain.carNumber }</li>
                        <li><label>手机号码：</label>${driverUserInfoDomain.code }</li>
                    </ul>
                </div>
                <div class="mat_nt mofnt">
                	<h3><i>&nbsp;</i>预约信息</h3>
                    <ul class="ult ultf" id="driverBusinessLineById">
                        <!-- 预约信息 -->
                    </ul>  
				</div>
		<div class="bot_row"><a href="javascript:getDriverCar();">选择车辆</a></div>
     <div class="news news_row"><i style="float:left;">&nbsp;</i>您还可以把司机收藏到您的常用车源，方便您以后联系。<a id="driverCollectIds" href="javascript:collectionDriver('<sys:context/>/addUserDriver',{'driverUserInfoDomain.id':${driverUserInfoDomain.id}},'driverCollectIds');">收藏司机</a></div>
        </div>
        <div class="fl frount">
     		<h3 class="unth3">当前位置：<a id="cltn" title="${driverUserInfoDomain.lastLocation }" href="javascript:artDialogMap('${driverUserInfoDomain.longitude }','${driverUserInfoDomain.latitude }','${driverUserInfoDomain.lastLocation }');">${driverUserInfoDomain.lastLocation }</a><p>${driverUserInfoDomain.locationModifyTime }</p></h3>
          <a id="cltn" href="javascript:artDialogMap('${driverUserInfoDomain.longitude }','${driverUserInfoDomain.latitude }','${driverUserInfoDomain.lastLocation }');"><div class="backu" id="allmap" style="height: 130px;width:220px;"></div></a>
            <div class="mat_both">
                	<h3><i>&nbsp;</i>小提示</h3>
					<div>点击选择车辆，在快到网上完成在线订车即可通过点击货物跟踪查看您的货物运输情况。</div>
             </div>
        </div>
    </div>
    <div class="mh"></div>
    <div class="con_title">
      <ul>
        <li><a>搜索车源</a>></li>
        <li><a>选择车辆</a>></li>
        <li><a>在线订车</a>></li>
        <li><a>确认送达</a>></li>
        <li><a>评价司机</a></li>
      </ul>
    </div>
    <div class="fl mac_righ">
      <div class="boder" style="height:471px;">
          <div class="mian_title">
            <ul>
              <li id="cur1" onclick ="setContentTab('cur',1,3)" class="current"><a>车辆信息</a></li>
              <li id="cur2" onclick ="setContentTab('cur',2,3)"><a href="javascript:driverAppraiseInfoById('1');">评价详情</a></li>
              <li id="cur3" onclick ="setContentTab('cur',3,3)"><a href="javascript:driverTransactionInfoById('1');">交易记录</a></li>
            </ul>
          </div>
			 <div id="con_cur_1" class="data" >
                 <div class="fl houre">
                	<h3><i>&nbsp;</i>诚信档案</h3>
                    <ul id="driverAppraiseCountId">
                        <li><label>好评：</label><span><i class="icon7">&nbsp;</i>(0)</span></li>
                        <li><label>中评：</label><span><i class="icon8">&nbsp;</i>(0)</span></li>
                        <li><label>差评：</label><span><i class="icon9">&nbsp;</i>(0)</span></li>
                    </ul>
                    <ul class="tday">
                        <li><label>手机认证：</label><i class="aut">&nbsp;</i>已认证<!-- <i class="aut3">&nbsp;</i>未认证 --></li>
                        <li><label>实名认证：</label>
                        	<c:if test="${driverUserInfoDomain.auditFlag == 1}">
                        		<i class="aut1">&nbsp;</i>已认证
                        	</c:if>
                        	<c:if test="${driverUserInfoDomain.auditFlag == 0}">
                        		<i class="aut4">&nbsp;</i>未认证
                        	</c:if>
                        </li>
                        <!-- <li><label>保证金信息：</label><i class="aut2">&nbsp;</i>已缴纳<i class="aut5">&nbsp;</i>未缴纳</li> -->
                    </ul>
                </div>
                <div class="mat_nt mat_nt_row">
                	<h3><i>&nbsp;</i>车辆信息</h3>
                    <ul class="ult1">
                    	<li><label>车长：</label>${driverUserInfoDomain.carLength }</li>
                    	<li><label>板：</label>${driverUserInfoDomain.carPlateType }</li>
                    	<li><label>栏：</label>${driverUserInfoDomain.carBarType }</li>
                        <li><label>最大载重：</label>${driverUserInfoDomain.carWeight }</li>
                        <li><label>载重体积：</label>${driverUserInfoDomain.carCubage }</li>
                    </ul>
				</div>
                <div style=" clear:both;"></div>
				<div class="mat_nt">
                	<h3><i>&nbsp;</i>常运线路</h3>
                    <ul class="ult" id="driverLineById">
                        <!-- 常运线路 -->
                    </ul>  
				</div>
				</div>
               <div id="con_cur_2" class="hiden" >
               		<div class="evalu evalu_row" id="driverAppraiseInfoId">
                		<!-- 评价内容 -->
              		 </div>
              		 <div class="feye"  id="driverAppraiseInfoPageId">
              		 	<!-- 分页 -->
				     </div>
               </div>
               <div id="con_cur_3" class="hiden" >
          			<div class="data datarow" id="driverTransactionId">
           				<!-- 司机交易记录 -->
					</div>
					<div class="feye"  id="driverTransactionInfoPageId">
              		 	<!-- 分页 -->
				    </div>
       		  </div>
     	 </div>  
    </div>
    <div class="boder recomm comrow" id="driverCarId">
     </div>
    <div class="both mh"></div>
      <jsp:include page="/cooperativePartner.jsp" />
      <br />
      <br />
</div>
<!-- 地图要用的经纬度 -->
<input type="hidden" id="longitude" name="longitude" value="${driverUserInfoDomain.longitude }"/>
<input type="hidden" id="latitude" name="latitude" value="${driverUserInfoDomain.latitude }"/>
<!-- 底部开始 -->
<jsp:include page="/bottom.jsp" />
<!-- 底部结束 -->
<link href="<sys:context/>/resource/css/bounced.css" rel="stylesheet" type="text/css" />

</body>
</html>
<script type="text/javascript" src="http://api.map.baidu.com/api?v=2.0&ak=GGFRAysCPHhn05mzdPayTZsc"></script>
<script type="text/javascript">
//jQuery(".conxt").slide({titCell:".hd ul",mainCell:".bd ul",autoPage:true,effect:"topLoop",autoPlay:true,vis:5});
window.onload=function(){
	var not=document.getElementById('not');
	var codes=document.getElementById('codes');
		not.onmouseover=function(){
			codes.style.display='block'
			}
		not.onmouseout=function(){
			codes.style.display='none'
			}
	}
	
//现在用车
function getDriverCar(){
	var driverHtml = cargoListHtml();
	art.dialog({
		width:1220,
		height:520,
		id: 'shakeId',
		title: '订车',
		content: driverHtml,
		lock: true
	});
}

function setTheCarOrderCargo() {
	var driverId = $("#driverId").val();
	var orderCargoId = $("#orderCargoId").val();
	//消息百度云推送的两个Id
	var baiduChannelId=$("#baiduChannelId").val();
	var baiduUserId=$("#baiduUserId").val();
	
	if(orderCargoId == null || orderCargoId == "") {
		$("#orderIdNull").show();
		return;
	}
	confirm(driverId,orderCargoId,baiduChannelId,baiduUserId);
}
//订车提交方法
function setTheCar(driverId,orderCargoId,baiduChannelId,baiduUserId) {
	closeUp();
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/addTransactionCargoQuote",
	   	dataType:"json",
	    data:{driverId:driverId,orderCargoId:orderCargoId}, //参数     	
		success:function(data){//回传函数
			if(data.result == 0) {//订车成功
				trueSuccess("1");
				//车找货成功后提醒司机，百度云推送消息 20140715 PM（成功失败与否不带任何提示）
                baiduPushMessagesToDriverTwo(baiduChannelId,baiduUserId,'2');
			}
			if(data.result == 1) {//未登录
				var url = jcontextPath + "/dcts/user/login.jsp";
				window.open(url); 
			}
			if(data.result == 2) {//参数错误
				trueSuccess("2");
			}
	   	}
	});
}

function confirm(driverId,orderCargoId,baiduChannelId,baiduUserId){
	art.dialog({
		width:400,
		height:100,
		id: 'shake-demo',
		title: '订车提示框',
		content: '<div class="frkst">尊敬的用户您好！确认订车请点击确定(请您耐心等待司机的确认信息)，否则点击取消。</div>',
		lock: true,
		fixed: true,
		cancel:false,
		ok: function () {
		//确认调用事件
		setTheCar(driverId,orderCargoId,baiduChannelId,baiduUserId);
		},
		okValue: '提交',
		cancelValue:'取消',
		cancel: function () {
		//取消调用事件
		}
	});
}
//成功提示
function trueSuccess(str) {
	var titlehtml = "";
	var contenthtml = "";
	if(str == "1") {
		titlehtml = "成功提示";
		contenthtml = '<div class="frkst">尊敬的用户您好！恭喜您订车成功！请等待司机确认。</div>';
	}
	if(str == "2") {
		titlehtml = "失败提示";
		contenthtml = '<div class="frkst">对不起！参数错误，订车失败！</div>';
	}
	art.dialog({
		width:400,
		height:100,
		id: 'shake-demo—Id',
		title: titlehtml,
		time: 5,
		content: contenthtml,
		lock: true,
		cancel:false
	});
}


//关闭所有对话框
function closeUp() {
	var list = art.dialog.list;
	for (var i in list) {
    	list[i].close();
	};
}


//------------------------当前位置地图------------------
    //--------(1)当前页面的小地图
          //当前位置的经度和纬度 
		var longitudeDefault=$("#longitude").val();
		var latitudeDefault=$("#latitude").val();
		//进入页面就加载地图
		getCollectMap("allmap");
		
		//获取小地图（没有添加默认缩放平移控件，添加缩略地图控件，添加比例尺控件）
		function getCollectMap(mapId){
		if((longitudeDefault != '' && longitudeDefault != undefined)&&(latitudeDefault !='' && latitudeDefault != undefined)){
			// 百度地图API功能 
		var map = new BMap.Map(mapId);            // 创建Map实例
		var point = new BMap.Point(longitudeDefault, latitudeDefault);    // 创建点坐标
		map.centerAndZoom(point,15);                     // 初始化地图,设置中心点坐标和地图级别。
		map.enableScrollWheelZoom();                            //启用滚轮放大缩小
		addMarker(longitudeDefault, latitudeDefault,map,16);
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
	
	//----------------------地图结束---------------------------------
	
	//弹出当前位置地图层
	function artDialogMap(longitudeDefault,latitudeDefault,lastLocation){ 
	art.dialog.open("<sys:context/>/dcts/web/lastLocationMap.jsp?longitudeDefault="+longitudeDefault+"&latitudeDefault="+latitudeDefault,{title:'当前位置：'+lastLocation,width:800,height:620,lock:true,drag:true});
	}
</script>
