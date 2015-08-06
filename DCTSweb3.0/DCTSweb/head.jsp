<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>头部</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<script type="text/javascript" src="<sys:context/>/resource/js/index/areaDataDict.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/checkoutData.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/index/cookie.js"></script>
<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">
var sessionUser = "<%=session.getAttribute("user")%>";
	$(function(){
		var not=document.getElementById('not');
		var bln=document.getElementById('bln');
		not.onclick=function() {
			if( bln.style.display=='block'){
				bln.style.display='none';
			}else{
				bln.style.display='block';
			}
		}
	});
	$(function(){
		inputTipText();  //初始化Input的灰色提示信息
		if(getCookie("province") == "") {
			getProvinceCity();
		}else {
			getIpAreaDict(getCookie("province"),getCookie("city"));//根据IP获取地址
			setCookieProvinceCity();
		}
	});
	//确定按钮
	function changeCityTrue() {
		var bln=document.getElementById('bln');
		var province = $("#ipProvince").val();
		var city = $("#ipCity").val();
		addCookie("province",province,0);	
		addCookie("city",city,0);
		setCookieProvinceCity();
		bln.style.display='none';
		getRealCar(province,city);
	}
	//取消按钮
	function callOff() {
		getIpAreaDict(getCookie("province"),getCookie("city"));
		var bln=document.getElementById('bln');
		bln.style.display='none';
	}
	function setCookieProvinceCity() {
		if(getCookie("city") == "") {
			$("#not").html('<a title="'+getCookie("province")+'">'+getCookie("province")+'</a><span>切换城市</span>');
		}else {
			$("#not").html('<a title="'+getCookie("city")+'">'+getCookie("city")+'</a><span>切换城市</span>');
		}
	}
	
	//根据访问者获取访问者的地区（省、市）
	function getProvinceCity() {
		$.ajax({
			ansync:true,
			url: jcontextPath + "/ipUserActio",   
			type:'post',	
			dataType:'json', 
			//data:{code:code}, //参数     	               
			success:function(data){//回传函数
				if(data.result == 1) {
					var dataObj=data.content;
					getIpAreaDict(dataObj.code,dataObj.name);//根据IP获取地址
					//$("#lastLocation").val(dataObj.code+"-"+dataObj.name);
					if(dataObj.name == null || dataObj.name == "") {
						addCookie("province",dataObj.code,0);
						$("#not").html('<a title="'+dataObj.code+'">'+dataObj.code+'</a><span>切换城市</span>');
					}else {
						addCookie("province",dataObj.code,0);
						addCookie("city",dataObj.name,0);
						$("#not").html('<a title="'+dataObj.name+'">'+dataObj.name+'</a><span>切换城市</span>');
					}
				}
			}
		});
	}
	
	//退出登录
	function logout() {
		$.ajax({
			url: jcontextPath + "/userLogout",   
			type:'post',	
			dataType:'json', 
			success:function(data){//回传函数
				parent.location.href = jcontextPath;
			}
		});
	}
	
	//首页快速搜索
	function getCarNumberPhone() {
		var carNumberPhoneId = $("#carNumberPhoneId").val();
		if(carNumberPhoneId == '' || carNumberPhoneId == null || carNumberPhoneId == "快速搜索 ：手机号码/车牌号码") {
			return;
		}
		document.getElementById('carNumberPhoneMainForm').submit();
	}
	
	//清空快速搜索内容
	function setCarNumberPhone() {
		 $("#carNumberPhoneId").val("");
		 $("#carNumberPhoneId").focus();
		 $("#carNumberPhoneId").blur();
	}
	
	function locationHrefLogin(){
		location.href=jcontextPath+'/dcts/user/login.jsp';
	}
	
</script>	
</head>
<body>
<!-- 未登录 -->
<s:if test="#session.user==null">
<div class="header">
  <div class="hea_top">
    <ul>
      <!-- 
      <li class="pno"><a href="">企业APP下载</a></li>
      <li><a id="cargoTrackingId" href="javascript:cargoTracking();">货物跟踪</a></li>
       -->
      <li><a href="<sys:context/>/dcts/user/login.jsp" target="_blank" class="p12">登录</a><a href="<sys:context/>/dcts/user/Register.jsp"  target="_blank" >免费注册</a></li>
      <li>你好欢迎来到快到网</li>
    </ul>
  </div>
  <div class="hea_cent">
    <div class="fl hea_logo">
    	<a href="<sys:context/>/"><img src="<sys:context/>/resource/image/index/logo.jpg" /></a>
    	<div id="not" class="slet_dta"><a title="杭州市">杭州市...</a><span>切换城市</span></div>
    </div>
    <div class="fl matop"><img src="<sys:context/>/resource/image/index/benner.png" width="345" height="37" /></div>
    <div class="fr hea_sou">
      <label>
        <s:form id="carNumberPhoneMainForm" action="queryMoreRealDriverInfo.action" namespace="/" target="_blank"  method="post">
	        <input type="text" id="carNumberPhoneId" name="carNumberPhoneId" tipMsg="快速搜索 ：手机号码/车牌号码" value="" class="search" />
	        <input name="" onClick="getCarNumberPhone();" type="button" class="btm"/>
        </s:form>
      </label>
      <div class="poor" onClick="setCarNumberPhone();"></div>
    </div>
  </div>
</div>
<div class="relat" >
    <div id="bln">
    	<select id="ipProvince" name="ipProvince"></select>
    	<select id="ipCity" name="ipCity"></select>
    	<div>
    		<input id="confirmId" name="confirmId" type="button" onclick="changeCityTrue();" value="确定" class="subt" />
    		<input name="" type="button" onclick="callOff();" value="取消" class="subt" />
    	</div>
    </div>
</div>
<div class="nav100">
  <div class="nav">
    <ul>
      <li><a href="<sys:context/>/">首页</a></li>
      <li><a href="<sys:context/>/queryMoreRealDriverInfo">当前车源</a></li>
      <li><a href="<sys:context/>/queryMoreReturnDriverInfo">预约车源</a></li>
      <li><a href="javascript:locationHrefLogin();">我的快到</a></li>
      <li><a href="javascript:locationHrefLogin();">货物跟踪</a></li>
      <li><a href="<sys:context/>/queryOrderCargoList">货源信息</a></li>
    </ul>
    <div class="fr navfr"><a href="<sys:context/>/openAddLocalOrderCargoInfo?orderCargoInfoDomain.menuAId=" class="a1">发布货源</a><span><i>&nbsp;</i>4009904656</span></div>
  </div>
</div>
</s:if>
<!-- 已登录 -->
<s:else>
<div class="header">
  <div class="hea_top">
    <ul>
      <!-- 
      <li class="pno"><a href="">企业APP下载</a></li>
      <li><a href="<sys:context/>/querySuccessCloseTransactionInfo?transactionInfoDomain.menuAId=&transactionInfoDomain.tradeStart=">货物跟踪</a></li>
      <li><a href="<sys:context/>/querySuccessCloseTransactionInfo?transactionInfoDomain.menuAId=&transactionInfoDomain.tradeStart=">我的快到</a></li>
       -->
      <li><a href="javascript:logout();" class="p12">安全退出</a></li>
      <li>
      <input type="hidden" value="${session.user.name}" id="userName"/>
      <input type="hidden" value="${session.user.parentId}" id="userEncoded"/>
      <tt id="userInfoId" style="color: red;"></tt>
      <script type="text/javascript">
        var userName=$("#userName").val();
        if(userName==""){
          $("#userInfoId").html("${session.user.code}");
        }else{
          $("#userInfoId").html("${session.user.name}");
        }
      </script>
      	欢迎来到快到网
      </li>
    </ul>
  </div>
  <div class="hea_cent">
    <div class="fl hea_logo">
    	<a href="<sys:context/>/"><img src="<sys:context/>/resource/image/index/logo.jpg" /></a>
    	<div id="not" class="slet_dta"><a title="杭州市">杭州市...</a><span>切换城市</span></div>
    </div>
    <div class="fl matop"><img src="<sys:context/>/resource/image/index/benner.png" width="345" height="37" /></div>
    <div class="fr hea_sou">
      <label>
      	<s:form id="carNumberPhoneMainForm" action="queryMoreRealDriverInfo.action" namespace="/" target="_blank"  method="post">
	        <input type="text" id="carNumberPhoneId" name="carNumberPhoneId" tipMsg="快速搜索 ：手机号码/车牌号码" value="" class="search" />
	        <input name="" onClick="getCarNumberPhone();" type="button" class="btm"/>
        </s:form>
      </label>
      <div class="poor" onClick="setCarNumberPhone();"></div>
    </div>
  </div>
</div>
<div class="relat">
    <div id="bln" >
		<select id="ipProvince" name="ipProvince"></select>
    	<select id="ipCity" name="ipCity"></select>
    	<div>
    		<input id="confirmId" name="confirmId" type="button" onclick="changeCityTrue();" value="确定" class="subt" />
    		<input name="" type="button" onclick="callOff();" value="取消" class="subt" />
    	</div>
	</div>
</div>
<div class="nav100">
  <div class="nav">
    <ul>
      <li><a href="<sys:context/>/">首页</a></li>
      <li><a href="<sys:context/>/queryMoreRealDriverInfo">当前车源</a></li>
      <li><a href="<sys:context/>/queryMoreReturnDriverInfo">预约车源</a></li>
      <li><a href="<sys:context/>/querySuccessCloseTransactionInfo?signType=myCenter&transactionInfoDomain.menuAId=&transactionInfoDomain.tradeStart=">我的快到</a></li>
      <s:if test="#session.user.parentId == 0">
      <li><a href="<sys:context/>/querySuccessCloseTransactionInfo?signType=cargoFllow&transactionInfoDomain.menuAId=&transactionInfoDomain.tradeStart=">货物跟踪</a></li>
      <li><a href="<sys:context/>/queryOrderCargoList">货源信息</a></li>
      <li><a href="<sys:context/>/dcts/myCenter/transaction/importTransactionOrderCargo.jsp" ><i class="net">&nbsp;</i>导入订单</a></li>
      </s:if>
    </ul>
   
    <div class="fr navfr">
     <s:if test="#session.user.parentId == 0"><a href="<sys:context/>/openAddLocalOrderCargoInfo?orderCargoInfoDomain.menuAId=" class="a1">发布货源</a>
     </s:if>
     <span><i>&nbsp;</i>4009904656</span>
    </div>
  </div>
</div>
</s:else>
</body>
</html>
