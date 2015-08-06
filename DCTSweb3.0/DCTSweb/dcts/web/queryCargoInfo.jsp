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
	<script src="<sys:context/>/resource/js/web/queryCargoInfo.js"></script>
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
	var totalPages = ${orderCargoInfoDomain.pageInfo.totalPages};//总页数
	var curPageNos = ${orderCargoInfoDomain.pageInfo.curPageNo};//当前页数
	var totalRecords = ${orderCargoInfoDomain.pageInfo.totalRecords};//总记录数
	cargoPageInfo(totalPages,curPageNos,totalRecords);//分页方法
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
</script>	
</head>

<body>
<!-- 头部开始 -->
<jsp:include page="/head.jsp" />
<!-- 头部结束 -->
<div class="mian">
  <div class="mian_left fl">
    <div class="shadow"></div>
    <div class="boder">
      <div>
      <s:form id="mainForm" action="queryOrderCargoList" namespace="/" method="post">
      <input type='hidden' id='curPage' name='curPage' value='${orderCargoInfoDomain.pageInfo.curPage}'/><!-- 需要显示页面 -->
        <div id="con_cur_1" class="data">
          <table border="0" cellpadding="0" cellspacing="0">
            <thead >
              <tr>
                <td width="74">货物名</td>
                <td width="100">发货单位</td>
                <td width="91">装货时间</td>
                <td width="135">装货地</td>
                <td width="135">卸货地</td>
                <td width="84">载重或体积</td>
                <td width="10">&nbsp;</td>
              </tr>
            </thead>
            <!--  列表数据循环开始 -->
            <c:if test="${fn:length(orderCargoInfoDomain.list) > 0}">
   				<c:forEach items="${orderCargoInfoDomain.list}" var="pic">
   					<tr>
              			<td title="${pic.cargoName}">${pic.cargoName}</td>
              			<td title="${pic.companyName}">${pic.companyName}</td>
              			<td title="${pic.requestStartTime}">${pic.requestStartTime}</td>
              			<td title="${pic.startProvince}-${pic.startCity}">${pic.startProvince}-${pic.startCity}</td>
              			<td title="${pic.endProvince}-${pic.endCity}">${pic.endProvince}-${pic.endCity}</td>
              			<c:if test="${pic.cargoWeight != '0.0' && pic.cargoCubage != '0.0'}" >
              				<td>${pic.cargoWeight}吨/${pic.cargoCubage}方</td>
              			</c:if>
              			<c:if test="${pic.cargoWeight != '0.0' && pic.cargoCubage == '0.0'}" >
              				<td>${pic.cargoWeight}吨</td>
              			</c:if>
              			<c:if test="${pic.cargoWeight == '0.0' && pic.cargoCubage != '0.0'}" >
              				<td>${pic.cargoCubage}方</td>
              			</c:if><c:if test="${pic.cargoWeight == '0.0' && pic.cargoCubage == '0.0'}" >
              				<td></td>
              			</c:if>
              			<td><span class="icon3">&nbsp;</span></td>
            		</tr>	
  				</c:forEach>
  			</c:if>
  			<c:if test="${fn:length(orderCargoInfoDomain.list) == 0}">
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
        </s:form>
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
//给当前导航栏加样式
$(document).ready(function(){
       //给导航栏标题加底色
	 	$(".nav ul li:eq(5)").css('background','url(resource/image/index/rl.jpg) repeat-x');
	
	});

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
</script>