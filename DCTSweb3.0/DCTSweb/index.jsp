<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
	<title>快到网-中国最大的车源货源发布平台  物流供需|车源货源|  快到网56top.cn</title>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="Keywords" content="快到网、物流行业网站、物流信息、货源信息、车主信息" />
	<meta name="Description" content="快到网是中国最大的车源货源发布平台，可以免费发布车源和货源信息，让车主和物流公司更方便的找到信息。无需支付任何费用，便可快捷获取物流信息。" />
	<script type="text/javascript" src="<sys:context/>/resource/js/jquery.js"></script>
	<script type="text/javascript" src="<sys:context/>/resource/js/gsjs.js"></script>
	<script type="text/javascript" src="<sys:context/>/resource/js/checkoutData.js"></script>
	<script type="text/javascript" src="<sys:context/>/resource/js/user/login.js"></script>
	<script type="text/javascript" src="<sys:context/>/resource/js/index/cookie.js"></script>
	<script type="text/javascript" src="<sys:context/>/resource/js/index/index.js"></script>
    <script type="text/javascript" src="<sys:context/>/resource/js/basis.js"></script>
	<script src="<sys:context/>/resource/js/jquery.SuperSlide.2.1.1.js"></script>
	<script src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
	<script src="<sys:context/>/resource/artDialog4.1.6/jquery.artDialog.js"></script>
	<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
	<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
	<link href="<sys:context/>/resource/threeLinkage/css/cityLayout.css" rel="stylesheet" type="text/css" />
	<style type="text/css">
.city_input { border:1px solid #d6d6d6; width:180px; height:30px;
 background:url(<sys:context/>/resource/threeLinkage/images/ts-indexcity.png) no-repeat; 
 line-height:30px; margin-top:5px;  text-indent:5px;}
 
</style>
<script type="text/javascript">
var sessionUser = "<%=session.getAttribute("user")%>";
$(function(){
	inputTipText();  //初始化Input的灰色提示信息  
	getLoginMessage();
	getRealCar(getCookie("province"),getCookie("city"));//当前车源数据
	//判断是否登录
 	if($("#userObj").val()!=""){
 		webUserAppraiseCount();
    	tradePromptInfo();
 	}
	todayDynamicInfo();//今日动态
});

//货物跟踪
 function cargoTrackingIndexImage(){
	 if(sessionUser == null || sessionUser == "" || sessionUser == "null"){
	    locationHrefLogin();
	 }else{
	   location.href=jcontextPath+"/querySuccessCloseTransactionInfo?signType=cargoFllow&transactionInfoDomain.menuAId=&transactionInfoDomain.tradeStart=";
	 }
 }

/**
 * 判断是否登录，如果没有登录就弹出登录页面
 */
function booleanUserSession(){
	if(sessionUser == null || sessionUser == "" || sessionUser == "null") {
		test();
		return false;
	}
}
</script>	
</head>
<body>
<!-- 头部开始 -->
<jsp:include page="/head.jsp" />
<!-- 保存登录用户对象-登录后加载预约订单，待确认收货，待评价订单 -->
<input type="hidden" id="userObj" value="<s:property value="#session.user"/>"/>
<!-- 头部结束 -->
<div class="mian">
  <div class="mian_left fl">
    <div class="mian_top">
      <div class="fl fltit">
        <ul>
          <li id="two1" onclick ="setContentTab('two',1,2)" class="current"><a style="cursor:pointer;">当前车源</a></li>
          <li id="two2" onclick ="setContentTab('two',2,2)"><a style="cursor:pointer;">预约车源</a></li>
        </ul>
      </div>
      <div class="fl">
      	<!--当前车源开始 -->
      	<s:form id="realMainForm" action="queryMoreRealDriverInfo" namespace="/" target="_blank"  method="post">
        <div id="con_two_1" class="mian_sach">
        	<ul>
            <li>运营路线</li>
            <li>
              <input type="text" id="startPcc" name="startPcc"  readonly="readonly" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/>
              <span class="icon4">&nbsp;</span>
              <input type="text" id="endPcc" name="endPcc"   readonly="readonly" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/>
            </li>
          </ul>
		  <ul>
            <li>当前位置</li>
            <li>
              <input type="text" id="lastLocation" name="lastLocation"   readonly="readonly" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/>
            </li>
          </ul>
          <div class="firon"><a href="javascript:realSubmit();" class="sout">搜索</a></div>
        </div>
        </s:form>
         <!--当前车源结束 -->
        <!--预约车源开始 -->
        <s:form id="returnMainForm" action="queryMoreReturnDriverInfo" namespace="/" target="_blank"  method="post">
        <div id="con_two_2" class="hiden">
        <div class="mian_sach">
          <ul>
            <li>预约线路</li>
            <li>
              <input name="startPcc" id="startPcc"   readonly="readonly" type="text" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/>
              <span class="icon1">&nbsp;</span>
              <input name="endPcc" id="endPcc"   readonly="readonly" type="text" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/>
            </li>
 			</ul>
            <ul>
            <li>装货时间</li>
            <li>
              <input name="startTime" id="startTime"   readonly="readonly" type="text" onClick="WdatePicker()" class="inth" />
            </li>
          </ul>
          <div class="firon"><a href="javascript:returnSubmit();" class="sout">搜索</a></div>
          </div>
        </div>
        </s:form>
         <!--预约车源结束 -->
      </div>
    </div>
    <div class="shadow"></div>
    <div class="con_title">
      <ul>
        <li><a>搜索车源</a>></li>
        <li><a>选择车辆</a>></li>
        <li><a>在线订车</a>></li>
        <li><a>确认送达</a>></li>
        <li><a>评价司机</a></li>
      </ul>
    </div>
    <div class="boder">
      <div class="mian_title">
        <ul>
          <li id="cur1" onclick ="setContentTab('cur',1,3)" class="current"><a href="javascript:getRealCar('','');" style="cursor:pointer;">当前车源</a></li>
          <li id="cur2" onclick ="setContentTab('cur',2,3)"><a href="javascript:getReturnCar();" style="cursor:pointer;">预约车源</a></li>
          <li id="cur3" onclick ="setContentTab('cur',3,3)"><a href="javascript:getNewCargo();" style="cursor:pointer;">最新货源</a></li>
        </ul>
      </div>
      <div>
      	<!-- 当前车源开始 -->
        <div id="con_cur_1" class="data" >
         	<!-- 当前车源内容 -->
        </div>
        <!-- 当前车源结束 -->
        <!-- 预约车源开始 -->
        <div id="con_cur_2" class="hiden">
        	<div class="data" id="carReturnDiv">
          		<!-- 预约车源内容 --> 
        	</div>        
       	</div>
       	<!-- 预约车源结束 -->
       	<!-- 最新货源 开始-->
        <div id="con_cur_3" class="hiden">
        	<div class="data" >
        		<table border="0" id="cargoStartDiv" cellpadding="0" cellspacing="0">
        		</table><p class="more"><a href="<sys:context/>/queryOrderCargoList">更多&gt;&gt;</a></p>
        	</div>
        </div>
        <!-- 最新货源结束 -->
      </div>
    </div>
  </div>
  <div class="mian_right fr">
    <div class="landing">
      <div class="backtop"></div>
     <!-- 未登录开始 -->
     <s:if test="#session.user==null">
     <s:form id="mainForm" action="/loginWebUserInfo" namespace="/" method="post">
     <input type='hidden' id='errorMessage' name='errorMessage' value='${webUserInfoDamains.errorMessage}'/>
     	<h3><a href="<sys:context/>/dcts/user/retrieveUserPws.jsp">忘记密码</a>快速登录</h3>
      	<div class="laing">
        <ul>
          <li>
            <label>账号</label>
            <input id="code" name="webUserInfoDamains.code" type="text" tipMsg="用户名" class="int" value="" onkeydown="if(event.keyCode == 13)login();" onfocus="on_focus('code');"/>
            <div class="mpt htmpt" id="divCodeMpt" style="display:none">
            	<div class="wn_a"></div>
                <div class="wn_s" id="divCodeNull" style="display:none">用户名不能为空！</div>
                <div class="wn_s" id="divCodeLength" style="display:none">用户名长度为2到20位！</div>
          		<div class="wn_s" id="divCodeLetter" style="display:none">用户名数字字母或下划线组成！</div>
                <div class="wn_s" id="divCodeNot" style="display:none">用户名不存在！</div>
                <div class="wn_s" id="divMobilephoneNot" style="display:none">手机号码不存在！</div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li class="mto">
            <label>密码</label>
            <input id="password" name="webUserInfoDamains.password" type="password" class="int" value="" onkeydown="if(event.keyCode == 13)login();" onfocus="on_focus('pwd');"/>
            <div class="mpt htmpt" id="divPwdMpt" style="display:none">
            	<div class="wn_a"></div>
                <div class="wn_s" id="divPwdNull" style="display:none">密码不能为空！</div>
                <div class="wn_s" id="divPwdError" style="display:none">登录密码输入错误！</div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
            <label></label>
            <input id="bearPassword" name="bearPassword" type="checkbox" value="" />
            &nbsp;&nbsp;&nbsp;记住密码</li>
          <li class="lanot">
            <label></label>
            <a href="javascript:login();" id="saveBtn"  class="hoa mr10">登录</a><a href="<sys:context/>/dcts/user/Register.jsp" target="_blank" >注册</a></li>
        </ul>
      	</div>
     </s:form>
     </s:if>
     <!-- 未登录 结束-->
     <!-- 已登录开始 -->
     <s:else>
       <h3><a href="javascript:logout();">退出</a>会员资料</h3>
       <div class="lanch nchf">
       		<dl>
            	<dt class="fl"><img src="<sys:context/>/resource/image/index/tx.png" width="100" height="100" /></dt>
                <dd class="fr">
            		<h2 id="webUserNameId"></h2>
            		<p><a href="<sys:context/>/openUpdatePwdView">密码修改</a></p>
           			<div class="clmt">
		            	<!--通过审合同
		            	<a href="###"><i class="aut">&nbsp;</i></a>
		            	<a href="###"><i class="aut1">&nbsp;</i></a>
		            	<a href="###"><i class="aut6">&nbsp;</i></a>
		            	<a href="###"><i class="aut2">&nbsp;</i></a>-->
		            	<a><i class="aut" title="手机认证">&nbsp;</i></a>
		            	<s:if test="#session.user.enterpriseFlag == 0">
		            		<a><i class="aut7" title="企业认证">&nbsp;</i></a><!-- 企业认证 -->
		            	</s:if>
		            	<s:else>
		            		<a><i class="aut6" title="企业认证">&nbsp;</i></a>
		            	</s:else>
		            	<!-- <a href="###"><i class="aut5">&nbsp;</i></a> --><!-- 付费认证 -->
            		</div>
                </dd>
            </dl>
            <div class="numder numr" id="webUserAppraiseCountId">
                <span><i class="icon7">&nbsp;</i>好评</span>
                <span><i class="icon8">&nbsp;</i>中评</span>
                <span><i class="icon9">&nbsp;</i>差评</span>
            </div>
            <!-- 交易提醒统计 -->
            <ul id="ulId"> </ul>
       </div>
       </s:else>
       <!-- 已登录结束 -->
      <div class="backbot"></div>
    </div>
    <!-- 数据统计开始 -->
    <div class="centft boder" id="countDateHtmlId">
      <!-- 数据统计内容 -->
    </div>
    <!-- 数据统计结束 -->
    <div class="boder conxt"> <a id="cargoTrackingIndexId" href="javascript:cargoTrackingIndexImage('cargoTrackingIndexId');" class="aot">货物跟踪</a> <a href="<sys:context/>/openIdetityVerifyPage?menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@IDENTITY_CARD_QUERY'/>" target="_blank" class="aot1">身份证查询</a> <a target="_blank" href="<sys:context/>/helpCenter.jsp?parameters=411" class="aot2">司机APP下载</a> <a href="###" class="aot3">企业APP下载</a> </div>
    <div class="boder conxt"  >
      <h3><i class="f98">&nbsp;</i>今日动态</h3>
       <div class="bd" id="todayDynamicDiv" >
         <ul id="ulIds" >
          <li>畅宇物流与鄂c2****</li>
          <li>畅宇物流与苏AG****</li>
          <li>畅宇物流与浙LA****</li>
          <li>畅宇物流与皖M9****</li>
          <li>畅宇物流与鄂c2****</li>
         </ul>
      </div>
    </div>
  </div>
<!-- 合作伙伴 -->
  <div class="both"></div>
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
</body>
</html>
<!-- 引用js插件滚动（文字滚动） -->
<script type="text/javascript" src="<sys:context/>/resource/js/jquery.SuperSlide.2.1.1.js"></script>

<script type="text/javascript">
$(document).ready(function(){
       //给导航栏标题加底色
	 	$(".nav ul li:eq(0)").css('background','url(<sys:context/>/resource/image/index/rl.jpg) repeat-x');
	 	getCountDate();//统计数据
	   
	});

//引用js插件滚动（今日动态）
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
	$("#code").focus();
	},
	okValue: '提交',
	cancelValue:'取消',
	cancel: function () {
	}
	});
}

</script>
