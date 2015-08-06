<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>快到网-个人中心-常用车源</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/threeLinkage/css/cityLayout.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<sys:context/>/resource/js/basis.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/pageInfoThree.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/mycenter/myCenterLeftMenu.js"></script>

<style type="text/css">
.city_input { border:1px solid #d6d6d6; width:180px; height:30px;
 background:url(<sys:context/>/resource/threeLinkage/images/ts-indexcity.png) no-repeat; 
 line-height:30px; margin-top:5px;  text-indent:5px;}
 
</style>

<script type="text/javascript">
   $(function(){
   var totalPages = ${driverUserInfoDomain.pageInfo.totalPages};//总页数
		var curPageNos = ${driverUserInfoDomain.pageInfo.curPageNo};//当前页数
		var pageSize = ${driverUserInfoDomain.pageInfo.pageSize};//每页显示数据
		var totalRecords = ${driverUserInfoDomain.pageInfo.totalRecords};//总记录数
		pageInfo(totalPages,curPageNos,totalRecords);
   });
   
   function getSubmit(flag){
	 // 区分点击的是搜索按钮（初始化从第一条开始查）还是分页链接
	 $("#flagId").val(flag);
   
       //当前位置
        var searchLastLocation=$("#searchLastLocation").val();
        //当前位置拆分
       var searchLastLocationArray=searchLastLocation.split("-");
   //运营路线只具体到市,输入框只读,具体到市,具体到区县时默认也只取省市
       //起始地路线
        var startProCityCounty=$("#startProCityCounty").val();
        //目的地路线
        var endProCityCounty=$("#endProCityCounty").val();
        
      //运营路线拆分及赋值
       if(startProCityCounty!=""){
        var startProCityCountyArray=startProCityCounty.split("-");
         if(startProCityCountyArray.length>0){
            if(startProCityCountyArray.length==1){
              $("#startProvince").val(startProCityCountyArray[0]);
               
            }else if(startProCityCountyArray.length>1){//只具体到市
              $("#startProvince").val(startProCityCountyArray[0]);
              $("#startCity").val(startProCityCountyArray[1]);
            }
         }
       }
       if(endProCityCounty!=""){
        var endProCityCountyArray=endProCityCounty.split("-");
        if(endProCityCountyArray.length>0){
            if(endProCityCountyArray.length==1){
              $("#endProvince").val(endProCityCountyArray[0]);
               
            }else if(endProCityCountyArray.length>1){//只具体到市
              $("#endProvince").val(endProCityCountyArray[0]);
              $("#endCity").val(endProCityCountyArray[1]);
            }
         }
       }
       //查询当前位置 具体到了区县
       provinceCityCountyFormat(searchLastLocationArray,"lastLocation");
       //提交表单
      document.getElementById('mainForm').submit();
   }
  
</script>
</head>
  
<body>
  
<jsp:include page="/head.jsp" />
<!--个人中心-->

<div class="mian">
	<div class="mian_bor">
	<jsp:include page="/dcts/myCenter/myCenterLeftMenu.jsp" />
	<s:form id="mainForm" action="/queryLocalDriverUserCarInfo" namespace="/" method="post">
	<!-- 当前左边菜单链接的Id -->
	<input type="hidden" id="menuAIdHi" value="${driverUserInfoDomain.menuAId }" name="driverUserInfoDomain.menuAId"/>
	<!-- 区分点击的是搜索按钮（初始化从第一条开始查）还是分页链接 -->
	<input type="hidden" id="flagId" value="" name="flag"/>
	
     <input type="hidden" id="startProvince" name="driverUserInfoDomain.startProvince" value=""/>
     <input type="hidden" id="startCity" name="driverUserInfoDomain.startCity" value=""/>
     <input type="hidden" id="endProvince" name="driverUserInfoDomain.endProvince" value=""/>
     <input type="hidden" id="endCity" name="driverUserInfoDomain.endCity" value=""/>
	 <input type="hidden" id="lastLocation" value="" name="driverUserInfoDomain.lastLocation"/>
    <div class="fr sonafr">
    	<div class="funtop">
    	<h3><i>&nbsp;</i>我的常用车辆</h3>
        <ul>
        	<li class="w442">
        	<label>线路：</label>
        	<input name="driverUserInfoDomain.startProCityCounty"  style="width: 110px;"　readonly="readonly"  value="${driverUserInfoDomain.startProCityCounty }" id="startProCityCounty"  type="text" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/><i class="icon1">&nbsp;</i>
        	<input name="driverUserInfoDomain.endProCityCounty" style="width: 110px;" readonly="readonly" value="${driverUserInfoDomain.endProCityCounty }" id="endProCityCounty"  type="text" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/>
        	</li>
            <li><label>当前位置：</label>
            <input name="driverUserInfoDomain.searchLastLocation" readonly="readonly" value="${driverUserInfoDomain.searchLastLocation }" id="searchLastLocation"  type="text" class="city_input  inputFocus proCityQueryAll proCitySelAll" />
            </li>
            <li class="w442"><label>司机姓名：</label>
            <input name="driverUserInfoDomain.name" value="${driverUserInfoDomain.name }" type="text" class="intu" />
            </li>
            <li><a href="javascript:getSubmit('0');" class="sout">搜索</a><label>手机号码：</label>
            <input name="driverUserInfoDomain.code" value="${driverUserInfoDomain.code }" type="text" class="intu" />
            </li>
        </ul>
        </div>
        <div class="data dataf" >
          <table border="0" cellpadding="0" cellspacing="0">
            <thead >
              <tr>
              	<td width="30">&nbsp;</td>
                <td width="70">司机姓名</td>
                <td width="70">车牌号</td>
                <td width="180">车型</td>
                <td width="180" >运营路线</td>
                <td width="160">当前位置</td>
                <td width="30">&nbsp;</td>
              </tr>
            </thead>
            <s:if test="driverUserInfoDomain.list.size>0">
            <s:iterator value="driverUserInfoDomain.list">
              <tr>
              <td><span class="icon">&nbsp;</span></td>
              <td>${name }</td>
              <td>${carNumber }</td>
              <td>${carTypes }</td>
              <td title="${driverLine }">${driverLine }</td>
              <td><span class="icon2">&nbsp;</span>${lastLocation }</td>
              <td><a target="_blank" href="<sys:context/>/openDriverDetailed.action?driverId=${id}">查看</a></td>
            </tr>  
            </s:iterator>
            </s:if>
           </table>
          <!-- <p class="more"><a href="">更多&gt;&gt;</a></p> -->
          <s:if test="driverUserInfoDomain.list.size()>0">
			<div class="numberBox" id="pageInfoHtmlId">  
			</div>
			</s:if>
			<input type='hidden' id='curPage' name='driverUserInfoDomain.pageInfo.curPage' value='${driverUserInfoDomain.pageInfo.curPage}'/><!-- 需要显示页面 -->
			<input type='hidden' id='curPageNo' name='driverUserInfoDomain.pageInfo.curPageNo' value='${driverUserInfoDomain.pageInfo.curPageNo}'/><!-- 当前页面 -->
			<input type='hidden' id='driverUserInfoDomain.pageInfo.pageSize' name='driverUserInfoDomain.pageInfo.pageSize' value='${driverUserInfoDomain.pageInfo.pageSize}'/><!-- 当前页面 -->
			          
        </div>
    </div>
    </s:form>
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
</body>
</html>
