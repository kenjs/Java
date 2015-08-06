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
	<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
	<link href="<sys:context/>/resource/threeLinkage/css/cityLayout.css" rel="stylesheet" type="text/css" />
	<script src="<sys:context/>/resource/js/jquery.js"></script>
	<script src="<sys:context/>/resource/js/gsjs.js"></script>
	<script src="<sys:context/>/resource/js/index/login.js"></script>
	<script type="text/javascript" src="<sys:context/>/resource/js/checkoutData.js"></script>
	<script src="<sys:context/>/resource/js/web/queryMoreRealDriverCarInfo.js"></script>
	<script src="<sys:context/>/resource/js/jquery.SuperSlide.2.1.1.js"></script>
	<script src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
	<script src="<sys:context/>/resource/artDialog4.1.6/jquery.artDialog.js"></script>
	
<style type="text/css">
.city_input { border:1px solid #d6d6d6; width:180px; height:30px;
 background:url(<sys:context/>/resource/threeLinkage/images/ts-indexcity.png) no-repeat; 
 line-height:30px; margin-top:5px;  text-indent:5px;}
</style>
<script type="text/javascript">
var sessionUser = "<%=session.getAttribute("user")%>";
$(function(){
	var totalPages = ${driverUserInfoDomain.pageInfo.totalPages};//总页数
	var curPageNos = ${driverUserInfoDomain.pageInfo.curPageNo};//当前页数
	var totalRecords = ${driverUserInfoDomain.pageInfo.totalRecords};//总记录数
	var carPlateType = $("#realCarPlateType").val();
	var carBarType = $("#realCarBarType").val();
	var carLength = $("#realCarLength").val();
	getCarPlateTypeDict(carPlateType);
	getCarBarTypeDict(carBarType);
	getCarLengthDict(carLength);
	realPageInfo(totalPages,curPageNos,totalRecords);//分页方法
	getRecommendRealDiverCar();//推荐当前车源
});

/**
 * 判断是否登录，如果没有登录就弹出登录页面
 */
function booleanUserSession(){
	if(sessionUser == null || sessionUser == "" || sessionUser == "null") {
		test();
		return false;
	}
}

//给当前导航栏加样式
$(document).ready(function(){
       //给导航栏标题加底色
	 	$(".nav ul li:eq(1)").css('background','url(resource/image/index/rl.jpg) repeat-x');
	});
</script>	
</head>

<body>
<!-- 头部开始 -->
<jsp:include page="/head.jsp" />
<!-- 头部结束 -->
<div class="mian">
  <div class="mian_left fl">
    <div class="mian_top">
      <div class="fl">
      	<s:form id="mainForm" action="queryMoreRealDriverInfo" namespace="/" method="post">
        <div id="con_two_1" class="mian_sach sect">
        	<ul>
	            <li>运营路线</li>
	            <li>
	              <input type="text" id="startPcc" name="startPcc" readonly="readonly" value="${driverUserInfoDomain.startPcc}" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/>
	              <span class="icon4">&nbsp;</span>
	              <input type="text" id="endPcc" name="endPcc" readonly="readonly" value="${driverUserInfoDomain.endPcc}" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/>
	            </li>
          	</ul>
          	<ul>
            	<li>当前位置</li>
            	<li>
            		<input type="text" id="lastLocation" name="lastLocation" value="${driverUserInfoDomain.lastLocation}"  readonly="readonly" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/>
            	</li>
            	<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
            	<li>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</li>
          	</ul>
	        <ul>
	           <li>手机号码</li>
	           <li>
	           <input class="inth" id="code" name="code" value="${driverUserInfoDomain.code}" type="text" />
	           </li>
	           <li>车牌号码</li>
	           <li>
	           <input class="inth" id="carNumber" name="carNumber"  value="${driverUserInfoDomain.carNumber}" type="text" />
	           </li>
	        </ul>
          <dl>
            <dt>车辆类型</dt>
             <input type="hidden" id="realCarPlateType" name="carPlateType" value="${driverUserInfoDomain.carPlateType}"/>
             <input type="hidden" id="realCarBarType" name="carBarType" value="${driverUserInfoDomain.carBarType}"/>
             <input type="hidden" id="realCarLength" name="carLength" value="${driverUserInfoDomain.carLength}"/>
            <dd>
              <div>
              	<span id="CarPlateTypeDictId"></span>
              	<span id="CarBarTypeDictId"></span>
              </div>
              <div id="CarLengthDictId"></div>
            </dd>
          </dl>
        </div>
         <div class="firs"><a href="javascript:realMoreSbmint();" class="sout">精确搜索</a></div>
        <input type='hidden' id='curPage' name='curPage' value='${driverUserInfoDomain.pageInfo.curPage}'/><!-- 需要显示页面 -->
        </s:form>
      </div>
    </div>
    <div class="shadow"></div>
    <div class="boder">
      <div>
        <div id="con_cur_1" class="data">
          <table border="0" cellpadding="0" cellspacing="0">
            <thead >
              <tr>
                <td width="118">车牌号</td>
                <td width="156">车型</td>
                <td width="255">运营路线</td>
                <td width="101">当前位置</td>
                <td width="20">&nbsp;</td>
              </tr>
            </thead>
            <!--  列表数据循环开始 -->
            <c:if test="${fn:length(driverUserInfoDomain.list) > 0}">
   				<c:forEach items="${driverUserInfoDomain.list}" var="pic">
   					<tr style="cursor:pointer;" onclick="openDriverDetailed('${pic.id}');">
              			<td><span class="icon">&nbsp;</span>${pic.carNumber}</td>
              			<td title="${pic.carTypes}">${pic.carTypes}</td>
              			<td title="${pic.driverLine}">${pic.driverLine}</td>
              			<td title="${pic.lastLocation}"><span class="icon2">&nbsp;</span>${pic.lastLocation}</td>
              			<td><span class="icon3">&nbsp;</span></td>
            		</tr>	
  				</c:forEach>
  			</c:if>
  			<c:if test="${fn:length(driverUserInfoDomain.list) == 0}">
   				<tr>
              		<td colspan="5" align="center">暂无符合条件的数据</td>
            	</tr>	
  			</c:if>
  			<!--  列表数据循环结束 -->  
          </table>
          <!-- 分页开始 -->
          <div class="feye" id="pageInfoHtmlId">
			<!-- 分页展示 -->
		  </div>
		  <!-- 分页结束 -->
        </div>
      </div>
    </div>
  </div>
  <div class="mian_right fr" >
		<div class="boder dures" style="height: 218px;">
	      	<a class="at">搜索车源</a>
	      	<a class="at1">选择车辆</a>
	      	<a class="at2">确定车辆</a>
	      	<a class="at3">&nbsp;&nbsp;联系车主</a>  
        </div>
        <!-- 推荐车源 -->
        <div class="boder recomm" style="height: 742px;" id="driverCarId">         
        </div>
  </div>
  <div class="both"></div>
  <div class="mh"></div>
  <!-- 合作伙伴 -->
  <jsp:include page="/cooperativePartner.jsp" />
  <br />
  <br />
</div>
<!-- 底部开始 -->
<jsp:include page="/bottom.jsp" />
<!-- 弹出省市区的层 -->
<jsp:include page="/resource/threeLinkage/provinceCityAreaDiv.jsp" />
<script src="<sys:context/>/resource/threeLinkage/js/jquery-1.6.2.min.js"></script>
<script src="<sys:context/>/resource/threeLinkage/js/public.js"></script>
<!-- 底部结束 -->
<!-- 登录div -->
<link href="<sys:context/>/resource/css/bounced.css" rel="stylesheet" type="text/css" />
</body>
</html>
<script type="text/javascript">
jQuery(".conxt").slide({titCell:".hd ul",mainCell:".bd ul",autoPage:true,effect:"topLoop",autoPlay:true,vis:5});
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
function test(){
art.dialog({
width:400,
height:100,
id: 'shake-demo',
title: '提示框',
content: '<div class="frkst">对不起！您没有访问车辆详细页面的权限，请先登录在查看车辆详细信息。</div>',
lock: true,
fixed: true,
ok: function () {
//确认调用事件
ext();
inputTipText();  //初始化Input的灰色提示信息  
},
okValue: '提交',
cancelValue:'取消',
cancel: function () {
}
});
}

function ext(){
var loginHtml = '<div class="laing_f">'+
					'<ul>'+
						'<li><label>用户名</label>'+
							'<input id="code" name="code" type="text" class="int_t" tipMsg="用户名" onkeydown="if(event.keyCode == 13)login();"  onblur="on_blur(\'code\');"  onfocus="on_focus(\'code\');"/>'+
							'<div class="mpt " id="divCodeMpt" style="display:none">'+
				            	'<div class="wn_a"></div>'+
				                '<div class="wn_s" id="divCodeNull" style="display:none">用户名不能为空！</div>'+
				                '<div class="wn_s" id="divCodeLength" style="display:none">用户名长度为3到20位！</div>'+
				          		'<div class="wn_s" id="divCodeLetter" style="display:none">用户名数字字母或下划线组成！</div>'+
				                '<div class="wn_s" id="divCodeNot" style="display:none">用户名不存在！</div>'+
				                '<div class="wn_s" id="divMobilephoneNot" style="display:none">手机号码不存在！</div>'+
				                '<div class="wn_s" id="divCoent" style="display:none">请填写完整信息！</div>'+
				                '<div class="wn_c"></div>'+
            				'</div>'+
						'</li>'+
						'<li><label>密码</label>'+
							'<input id="password" name="password" type="password" class="int_t" onkeydown="if(event.keyCode == 13)login();" onblur="on_blur(\'password\');"  onfocus="on_focus(\'password\');"/>'+
							'<div class="mpt " id="divPwdMpt" style="display:none">'+
				            	'<div class="wn_a"></div>'+
				                '<div class="wn_s" id="divPwdNull" style="display:none">密码不能为空！</div>'+
				                '<div class="wn_s" id="divPwdError" style="display:none">密码错误！</div>'+
				                '<div class="wn_c"></div>'+
            				'</div>'+
	          			'</li>'+
	          			'<li class="mort">'+
		          			'<span><a href="javascript:getRetrieveUserPws();">忘记密码？</a></span>'+
		            		'<label></label>'+
		            		'<input name="" type="checkbox" value="" />&nbsp;记住密码'+
		            	'</li>'+
	          			'<li class="lanot_t">'+
	            			'<label></label>'+
	            			'<a href="javascript:login();" class="hoa mr10">登录</a><a href="javascript:getRegister();">注册</a>'+
	            		'</li>'+
	        		'</ul>'+
 			  	'</div>';
art.dialog({
width:400,
height:300,
id: 'shakeId',
title: '登录',
content: loginHtml,
lock: true,
fixed: true
});
}
//关闭所有对话框
function closeUp() {
var list = art.dialog.list;
for (var i in list) {
    list[i].close();
};
}
</script>