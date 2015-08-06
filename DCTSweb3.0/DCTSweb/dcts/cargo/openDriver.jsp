<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<% 
String orderCargoId=request.getParameter("orderCargoId");
String types = request.getParameter("types");

%>
<head>
<title>快到网-确认订车</title>
<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/bounced.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"  src="<sys:context/>/resource/js/basis.js"></script>
<link href="<sys:context/>/resource/threeLinkage/css/cityLayout.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"  src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
<script type="text/javascript"   src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>
<script type="text/javascript"  src="<sys:context/>/resource/js/dataDict.js"></script>
<script type="text/javascript">
var orderCargoId = '<%=orderCargoId %>';
var types = '<%=types %>';
$(function(){
	getCarLengthDict(document.getElementById("carLength" ));
	getCarPlateTypeDict(document.getElementById("carPlateType" ));
	getCarBarTypeDict(document.getElementById("carBarType"));
	getDriver(orderCargoId);
	cargoTitleHtml(orderCargoId);
});


//继续发布（刷新父页面，关闭art.dialog）
function continuePublish(){
   //art.dialog.open.origin 表示父窗体
   if(types == '1') {
   		art.dialog.open.origin.$('#mainForm')[0].reset();
   }else{
     art.dialog.open.origin.reload();
   }
   art.dialog.close();
}

function getDriver(orderCargoId){
	var orderCargoId = orderCargoId;
	var startProCityCounty = $("#startProCityCounty").val();
	var endProCityCounty = $("#endProCityCounty").val();
	var code = $("#code").val();
	var carNumber = $("#carNumber").val();
	var lastLocation = $("#lastLocation").val();
	var carLength = $("#carLength").val();
	var carPlateType = $("#carPlateType").val();
	var carBarType = $("#carBarType").val();
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/queryDriverTheCarInfo",
	   	dataType:"json",
	    data:{orderCargoId:orderCargoId,
	    	  startProCityCounty:startProCityCounty,
	    	  endProCityCounty:endProCityCounty,
	    	  code:code,carNumber:carNumber,
	    	  lastLocation:lastLocation,
	    	  carLength:carLength,carBarType:carBarType,
	    	  carPlateType:carPlateType}, //参数     	
			  success:function(data){//回传函数
			var dataObj = data.content;
			$("#startProCityCounty").val(dataObj.startProCityCounty);
			$("#endProCityCounty").val(dataObj.endProCityCounty);
			$("#code").val(dataObj.code);
			$("#carNumber").val(dataObj.carNumber);
			$("#lastLocation").val(dataObj.lastLocation);
			//$('select#carLength').attr('value',dataObj.carLength); 
			$("#carPlateType").val(dataObj.carPlateType);
			$("#carLength").val(dataObj.carLength);
			$("#carBarType").val(dataObj.carBarType);
			var carDriverHtml = "";
				carDriverHtml = '<thead >'+
              						'<tr>'+
              							   '<td width="25"></td>'+
						                   '<td width="100">司机姓名</td>'+
								           '<td width="100">车牌号</td>'+
								           '<td width="100">手机号码</td>'+
								           '<td width="160">车型</td>'+
								           '<td width="175">当前位置</td>'+
								           '<td width="200">运营路线</td>'+
              						'</tr>'+
            					'</thead>'; 
			for(var i = 0;i<dataObj.list.length;i++) {
				var driverList = '';
				if(dataObj.list[i].lineType == 2) {
					driverList = '<i class="rela">';
				}
				carDriverHtml += '<tr id="ghbs" style="cursor:pointer;" onclick="getDriverById(\''+dataObj.list[i].id+'\');">'+
					              '<td>'+driverList+'</td>'+
					              '<td>'+dataObj.list[i].name+'</td>'+
					              '<td>'+dataObj.list[i].carNumber+'</td>'+
					              '<td>'+dataObj.list[i].code+'</td>'+
					              '<td title="'+dataObj.list[i].carTypes+'">'+dataObj.list[i].carTypes+'</td>'+
					              '<td title="'+dataObj.list[i].lastLocation+'">'+dataObj.list[i].lastLocation+'</td>'+
					              '<td title="'+dataObj.list[i].driverLine+'">'+dataObj.list[i].driverLine+'</td>'+
					             '</tr>';
			}
			$("#carDriverHtmlId").html(carDriverHtml);
	   	}
	});	
}

function getDriverById(driverId){
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/queryDriverTheCarInfo",
	   	dataType:"json",
	    data:{driverId:driverId}, //参数     	
		success:function(data){//回传函数
			var dataObj=data.content;
			var driverhtmById = '';
			if(dataObj.list.length == 1) {
				$("#driverCarId").val(dataObj.list[0].id);
				var driverLine = new Array();
				var driverBusinessLine = new Array();
				if(dataObj.list[0].driverLine != "" && dataObj.list[0].driverLine != null) {
					driverLine =  dataObj.list[0].driverLine.split(",");
				}
				if(dataObj.list[0].driverBusinessLine != "" && dataObj.list[0].driverBusinessLine != null) {
					driverBusinessLine =  dataObj.list[0].driverBusinessLine.split(",");
				}
				driverhtmById = '<li><label>司机姓名&nbsp;:</label><input name="" type="text" class="fint" value="'+dataObj.list[0].name+'"/>'+
				                '<input type="hidden" id="baiduChannelId" name="baiduChannelId" value="'+dataObj.list[0].baiduChannelId+'"/>'+
				                '<input type="hidden" id="baiduUserId" name="baiduUserId" value="'+dataObj.list[0].baiduUserId+'"/></li>'+
	                        	'<li><label>车牌号&nbsp;:</label><input name="" type="text" class="fint" value="'+dataObj.list[0].carNumber+'"/></li>'+
	                        	'<li><label>手机号&nbsp;:</label><input name="" type="text" class="fint" value="'+dataObj.list[0].code+'"/></li>'+
		                        '<li><label>车型&nbsp;:</label><input name="" type="text" class="fint" value="'+dataObj.list[0].carTypes+'"/></li>'+
		                        '<li><label>当前位置&nbsp;:</label><input name="" type="text" class="fint" value="'+dataObj.list[0].lastLocation+'"/></li>';
		        for(var i = 0;i<driverBusinessLine.length;i++) {
		        	if(i=3) {
		        		break;
		        	}
		        	if(i == 0) {
		        		driverhtmById += '<li><label>预约线路&nbsp;:</label><input name="" type="text" class="fint" value="'+driverBusinessLine[i]+'"/></li>';
		        	}else {
		        		driverhtmById += '<li><label>&nbsp;</label><input name="" type="text" class="fint" value="'+driverBusinessLine[i]+'"/></li>';
		        	}
		        }                
		        for(var i = 0;i<driverLine.length;i++) {
		        	if(i=3) {
		        		break;
		        	}
		        	if(i == 0) {
		        		driverhtmById += '<li><label>运营线路&nbsp;:</label><input name="" type="text" class="fint" value="'+driverLine[i]+'"/></li>';
		        	}else {
		        		driverhtmById += '<li><label>&nbsp;</label><input name="" type="text" class="fint" value="'+driverLine[i]+'"/></li>';
		        	}
		        }
			}
			$("#drriverHtmlId").html(driverhtmById);
	   	}
	});	
}

function setTheCarOrderCargo() {
	var driverId = $("#driverCarId").val();
	//云推送的两个Id
	var baiduChannelId = $("#baiduChannelId").val();
	var baiduUserId = $("#baiduUserId").val();
	
	if(driverId == null || driverId == "") {
		$("#orderIdNull").show();
		return;
	}
	art.dialog({
		width:400,
		height:100,
		id: 'shake-demo',
		title: '订车提示框',
		content: '<div class="frkst">尊敬的用户您好！确认用车请点击确定(请您耐心等待司机的确认信息)，否则点击取消。</div>',
		lock: true,
		fixed: true,
		cancel:false,
		ok: function () {
			setTheCar(driverId,orderCargoId,baiduChannelId,baiduUserId);
		},
		okValue: '确认',
		cancelValue:'取消',
		cancel: function () {
			art.dialog.list['shake-demo'].colse;
		}
	});
}

//订车提交方法
function setTheCar(driverId,orderCargoId,baiduChannelId,baiduUserId) {
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/addTransactionCargoQuote",
	   	dataType:"json",
	    data:{driverId:driverId,orderCargoId:orderCargoId}, //参数     	
		success:function(data){//回传函数
			if(data.result == 0) {//订车成功
			   // getTrue();
			   //给司机推送消息后只提示订车成功
				baiduPushMessagesToDriverThree(baiduChannelId,baiduUserId);
			}
			if(data.result == 1) {//未登录
				var url = jcontextPath + "/dcts/user/login.jsp";
				window.open(url); 
			}
	   	}
	});
}


// 货找车成功后提醒司机，百度云推送消息 20140715 PM(推送消息成功与否都不带提示的 哼哼)

function baiduPushMessagesToDriverThree(channelId,userId){
	if(channelId==null||channelId==""||userId==null||userId==""){
	  //单单提示订车成功
	  getTrue();
		return;
	}
	//execDatabaseInteractionHandle(jcontextPath+'/pushMessageToDriver',{'driverUserInfoDomain.baiduChannelId':channelId,'driverUserInfoDomain.baiduUserId':userId,'type':'2'},okFunc);
		   $.ajax({
						url: jcontextPath+'/pushMessageToDriver',   
						type:'post',	
						dataType:'json', 
						data:{'driverUserInfoDomain.baiduChannelId':channelId,'driverUserInfoDomain.baiduUserId':userId,'type':'2'},      	               
						success:function(data){//回传函数
						//单单提示订车成功
						    getTrue();
						}
					});
		
	}

	
//订车成功后的弹框跳转（原有的方法）
function getTrue(){
	var openHtml = '';
	if(types == '1') {
		openHtml = '<div class="buced">'+
                		'<div class="fl ticks" style="width:100px;"><img src="<sys:context/>/resource/image/index/jou.jpg" width="70" height="70" /></div>'+
                		'<div class="fr frcks" style="width:294px;">恭喜！您已成功订车！请等待司机确认。</div>'+
                		'<div class="botcks" style="width: 450px;">'+
                		'<a href="javascript:continuePublish();">继续发布</a>'+
                		'<a href="javascript:pageJump(\'<sys:context/>/queryTransactionInfo?transactionInfoDomain.menuAId=<s:property value="@com.cy.dcts.common.constants.Constants@MY_ORDER"/>\');">我的订单</a>'+
                		'<a href="javascript:pageJump(\'<sys:context/>/index.jsp\');">返回首页<i>&nbsp;</i></a></div>'+
        			'</div>';
	}else {
		openHtml = '<div class="buced">'+
                		'<div class="fl ticks" style="width:100px;"><img src="<sys:context/>/resource/image/index/jou.jpg" width="70" height="70" /></div>'+
                		'<div class="fr frcks" style="width:294px;">恭喜！您已成功订车！请等待司机确认。</div>'+
                		'<div class="botcks" style="width: 450px;">'+
                		'<a href="javascript:continuePublish();">继续订车</a>'+
                		'<a href="javascript:pageJump(\'<sys:context/>/queryTransactionInfo?transactionInfoDomain.menuAId=<s:property value="@com.cy.dcts.common.constants.Constants@MY_ORDER"/>\');">我的订单</a>'+
                		'<a href="javascript:pageJump(\'<sys:context/>/index.jsp\');">返回首页<i>&nbsp;</i></a></div>'+
        			'</div>';
	}
	art.dialog({
		width:400,
		height:100,
		id: 'N9IDK',
		title: '消息',
		content:openHtml,
		lock: true,
		fixed: true,
		cancel:false
	});		
}




//货物详细信息 
function cargoTitleHtml(orderCargoId) {
	$.ajax({
		type: "POST",
	   	async:false,
	   	url: jcontextPath + "/queryOrderCargoQuoteById",
	   	dataType:"json",
	    data:{orderCargoId:orderCargoId}, //参数     	
		success:function(data){//回传函数
			var dataObj=data.content;
			var cargoTitle = '';
			var driverType = '';
			var cargoType = "";
			if(dataObj.cargoType == 0) {
	   			cargoType = '重货';
	   		}else if(dataObj.cargoType == 1) {
	   			cargoType = '泡货';
	   		}
	   		if(dataObj.requestCarLength != null && dataObj.requestCarLength != '') {
	   			driverType += dataObj.requestCarLength+'&nbsp;';
	   		}
	   		if(dataObj.requestCarPlateType != null && dataObj.requestCarPlateType != '') {
	   			driverType += dataObj.requestCarPlateType+'&nbsp;';
	   		}
	   		if(dataObj.requestCarBarType != null && dataObj.requestCarBarType != '') {
	   			driverType += dataObj.requestCarBarType+'&nbsp;';
	   		}
	   		if(dataObj.cargoWeight != null && dataObj.cargoWeight != '') {
	   			driverType += dataObj.cargoWeight+'吨&nbsp;';
	   		}
	   		if(dataObj.cargoCubage != null && dataObj.cargoCubage != '') {
	   			driverType += dataObj.cargoCubage+'方&nbsp;';
	   		}
			cargoTitle += 	'<tr>'+
			                	'<td width="180">货源类型<span>:</span>'+cargoType+' </td>'+
			                    '<td width="200">货源名称<span>:</span>'+dataObj.cargoName+'</td>'+
			                    '<td width="200">装货时间<span>:</span>'+dataObj.requestStartTime+'</td>'+
			                	'<td width="200">卸货时间<span>:</span>'+dataObj.requestEndTime+'</td>'+
			                	'<td width="250">车型要求<span>:</span>'+driverType+'</td>'+
			                '</tr>'+
			                '<tr>'+
			                	'<td width="180">装货地<span>:</span>'+dataObj.startProCityCounty+' </td>'+
			                    '<td width="200">详细地址<span>:</span>'+dataObj.startTown+'</td>'+
			                    '<td width="200">卸货地<span>:</span>'+dataObj.endProCityCounty+'</td>'+
			                	'<td width="200">详细地址<span>:</span>'+dataObj.endTown+'</td>'+
			                	'<td width="250"></td>'+
			                '</tr>';
			$("#cargohtmlId").html(cargoTitle);
	   	}
	});
}		 
</script>
<style type="text/css">
.city_input { border:1px solid #d6d6d6; width:180px; height:30px;
 background:url(<sys:context/>/resource/threeLinkage/images/ts-indexcity.png) no-repeat; 
 line-height:30px; margin-top:5px;  text-indent:5px;}
</style>
</head>
<body style="overflow:hidden;">
        <div class="mt2">
        	<div class="stop">
    	    <h3><i>&nbsp;</i>货源信息</h3>
            <table cellpadding="0" cellspacing="0" id="cargohtmlId">
          	</table>
	   </div>
   			<div class="fl pick_fl">
   				<p class="ckpt"><i class="newi">&nbsp;</i>已显示的车辆信息为系统智能推荐车辆，您可通过搜索条件查找更多车辆。</p>
    			<div class="mian_sach sect" style="width: 800px;">
             		<ul>
	               		<li class="w47">装货地</li>
	                	<li><input name="startProCityCounty" value="" id="startProCityCounty"  type="text" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/></li>
	                	<li>卸货地</li>
	                	<li><input name="endProCityCounty" value="" id="endProCityCounty"  type="text" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="/输入城市名称"/></li>
	                	<li>当前位置</li>
	                	<li><input name="lastLocation" value="" id="lastLocation"  type="text" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/></li>
              		</ul>
              		<ul>
	               		<li class="w47">车牌号</li>
	                	<li><input class="inth" name="carNumber" id="carNumber" value="" type="text" /></li>
	                	<li>手机号</li>
	                	<li><input class="inth" name="code" id="code" value="" type="text" /></li>
	                	<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;车长</li>
	                	<li><select id="carLength" name="carLength"></select></li>
              		</ul>
              		<ul>
              			<li class="w47">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;板</li>
	                	<li><select id="carPlateType" name="carPlateType"></select></li>
	               		<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;栏</li>
	                	<li><select id="carBarType" name="carBarType"></select></li>
	                	<li><a href="javascript:getDriver('');" class="sout">搜索</a></li>
              		</ul>
				</div>
    			<div class="cler"></div>
    			<p class="ckpt"><i class="newi">&nbsp;</i>请您选择一辆车，匹配您的货源。<span id="orderIdNull" class="retun" style="display:none;">请您选择一条车源信息</span></p>
        		<div class="divtab divth" style="width:880px; height: 288px;">
         			<table border="0" cellpadding="0"  cellspacing="0" id="carDriverHtmlId">
            			<thead >
              				<tr>
              				   <td width="25"></td>
					           <td width="100">司机姓名</td>
					           <td width="100">车牌号</td>
					           <td width="100">手机号码</td>
					           <td width="160">车型</td>
					           <td width="175">当前位置</td>
					           <td width="200">运营路线</td>
              				</tr>
            			</thead>
           			</table>
          		</div>
        	</div>
         	<div class="fr pick_fr" style="height: 485px;">
                <p class="ckpt" style="height: 36px;"><i class="newi">&nbsp;</i>在线订车后将货物信息发送给指定的司机，请等待司机的确认。</p>
	            <ul class="uht" id="drriverHtmlId">
	               <li><label>司机姓名&nbsp;:</label><input name="" type="text" class="fint" /></li>
	               <li><label>车牌号&nbsp;:</label><input name="" type="text" class="fint" /></li>
	               <li><label>手机号&nbsp;:</label><input name="" type="text" class="fint" /></li>
	               <li><label>车型&nbsp;:</label><input name="" type="text" class="fint" /></li>
	               <li><label>当前位置&nbsp;:</label><input name="" type="text" class="fint" /></li>
	               <li><label>预约线路&nbsp;:</label><input name="" type="text" class="fint" /></li>
	               <li><label>运营线路&nbsp;:</label><input name="" type="text" class="fint" /></li>
	             </ul>
	            <input type="hidden" id="driverCarId" name="driverCarId" value=""/>
                <div class="bot_n" style="width:200px;"><a href="javascript:setTheCarOrderCargo();">在线订车</a>&nbsp;<a href="javascript:continuePublish();">取消</a></div>
            </div>
        </div>
</body>
<!-- 弹出省市区的层 -->
<jsp:include page="/resource/threeLinkage/provinceCityAreaDiv.jsp" />
<script src="<sys:context/>/resource/threeLinkage/js/jquery-1.6.2.min.js"></script>
<script src="<sys:context/>/resource/threeLinkage/js/public.js"></script>

</html>
