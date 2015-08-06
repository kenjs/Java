<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>快到网-个人中心-我的货源</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/threeLinkage/css/cityLayout.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<sys:context/>/resource/js/dataFormat.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/basis.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/pageInfoThree.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/mycenter/myCenterLeftMenu.js"></script>

<script type="text/javascript"  src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
<script type="text/javascript"   src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>
<style type="text/css">
.city_input { border:1px solid #d6d6d6; width:180px; height:30px;
 background:url(<sys:context/>/resource/threeLinkage/images/ts-indexcity.png) no-repeat; 
 line-height:30px; margin-top:5px;  text-indent:5px;}
 
</style>

<script type="text/javascript">
  $(function(){
   var totalPages = ${orderCargoInfoDomain.pageInfo.totalPages};//总页数
		var curPageNos = ${orderCargoInfoDomain.pageInfo.curPageNo};//当前页数
		var pageSize = ${orderCargoInfoDomain.pageInfo.pageSize};//每页显示数据
		var totalRecords = ${orderCargoInfoDomain.pageInfo.totalRecords};//总记录数
		pageInfo(totalPages,curPageNos,totalRecords);
   });
   
   function getSubmit(flag){
     if($("#cargoNameId").val()=="请输入货物名称"){
        $("#cargoNameId").val("");
      }
      $("#flagId").val(flag);
     document.getElementById('mainForm').submit();
   }
   //根据货源状态查询
   function changeFalg(){
     location.href="<sys:context/>/queryLocalOrderCargoInfo?orderCargoInfoDomain.cargoFlag="+$("#cargoFlag").val();
   }
    //删除货源
  function deleteInfo(content,url,pramater,okFunc){
   var dialog = art.dialog({
    content:content,
    lock:true,
    fixed: true,
    id: 'Fm2',
    icon: 'question',
    ok: function () {
    	execDatabaseInteractionHandle(url,pramater,okFunc)
    },
    cancel: true
  });
  }
  
  //重新发布货源
  function afreshPublish(url,id,okFunc){
   var contents='确认重新发布该货源吗？<br/>'+
   				'<p id="requestStartTimeHtmlId" style="color:red"></p><br/>'+
   				'<b style="color:red">*</b>'+
                '装货时间：<input  name="orderCargoInfoDomain.requestStartTime"  value="${orderCargoInfoDomain.requestStartTime }" id="requestStartTime" onfocus=cleanContext("requestStartTimeHtmlId") readonly="readonly" onClick=WdatePicker({dateFmt:"yyyy-MM-dd"}) class="int m3" /><br/><br/>'+
                '<p id="requestEndTimeHtmlId" style="color:red"></p><br/>'+
                '&nbsp;卸货时间：<input  name="orderCargoInfoDomain.requestEndTime" value="${orderCargoInfoDomain.requestEndTime }" onfocus=cleanContext("requestEndTimeHtmlId") id="requestEndTime" readonly="readonly" onClick="WdatePicker()" class="int m3"/>';
     var dialog = art.dialog({
     content:contents,
     lock:true,
     fixed: true,
     id: 'Fm1',
     icon: 'question',
     ok: function () {
		//卸货时间
	 var requestEndTime = $("#requestEndTime").val();
		//发货时间 
     var requestStartTime = $("#requestStartTime").val();
     //参数验证
     if(trim(requestStartTime) == "") {
			$("#requestStartTimeHtmlId").html("装货时间不能为空！");
			return false;
		}
		//当前时间
		var todatas=new Date(getDateFormat().replace(/\-/g, "\/"));
		var startTimes = new Date(requestStartTime.replace(/\-/g, "\/"));
		if(startTimes<todatas){
			$("#requestStartTimeHtmlId").html("装货时间不能小于当前时间！");
			return false;
		}
		
		if(trim(requestEndTime) != ""){
		var endTimes = new Date(requestEndTime.replace(/\-/g, "\/")); 
		if(endTimes<startTimes){
			$("#requestEndTimeHtmlId").html("卸货时间不能小于装货时间！");
			return false;
		}
		}
     
    	//参数验证通过-进行数据库交互
    	var dataParameter={'orderCargoInfoDomain.id':id,'orderCargoInfoDomain.requestStartTime':requestStartTime,'orderCargoInfoDomain.requestEndTime':requestEndTime};
    	execDatabaseInteractionHandle(url,dataParameter,okFunc);
    },
    cancel: true
  });
  }
  
  
</script>
</head>
  
  <body>
  <jsp:include page="/head.jsp" />
<!--个人中心-->
<div class="mian">
	<div class="mian_bor">
	<jsp:include page="/dcts/myCenter/myCenterLeftMenu.jsp" />
	<s:form id="mainForm" action="/queryLocalOrderCargoInfo" namespace="/" method="post">
	<!-- 当前左边菜单链接的Id -->
	<input type="hidden" id="menuAIdHi" value="${orderCargoInfoDomain.menuAId }" name="orderCargoInfoDomain.menuAId"/>
	
	<!-- 区分点击的是搜索按钮（初始化从第一条开始查）还是分页链接 -->
	<input type="hidden" id="flagId" value="" name="flag"/>
    <div class="fr sonafr">
    	<div class="funtop">
    	<h3><i>&nbsp;</i>货源管理</h3>
        <ul>
        	<li class="w396"><label>货物名称：</label>
        	<s:select list="dataDictInfoDomain.cargoTypeData"  listKey="code" listValue="name" cssClass="sel65" name="orderCargoInfoDomain.cargoType" style="width:68px;"/>
        	<input name="orderCargoInfoDomain.cargoName" id="cargoNameId" value="${orderCargoInfoDomain.cargoName }" type="text" tipMsg="请输入货物名称" class="intu" /></li>
            <li class="w410"><label>装货时间：</label>
            <input type="text" name="orderCargoInfoDomain.startTime" value="${orderCargoInfoDomain.startTime }" id="startTime" readonly="readonly" onClick="WdatePicker()" class="inut"/>
            <em class="mtl">到</em>
            <input type="text" name="orderCargoInfoDomain.endTime" value="${orderCargoInfoDomain.endTime }" id="endTime" readonly="readonly"  onClick="WdatePicker()" class="inut"/>
            </li>
            <li class="w396"><label>装货地：</label>
            <input name="orderCargoInfoDomain.startProCityCounty" readonly="readonly"  value="${orderCargoInfoDomain.startProCityCounty }" id="startProCityCounty"  type="text" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/>
            </li>
            <li class="w410"><a href="javascript:getSubmit('0');" class="sout">搜索</a>
            <label>卸货地：</label>
            <input type="text" readonly="readonly" id="endProCityCounty" name="orderCargoInfoDomain.endProCityCounty" value="${orderCargoInfoDomain.endProCityCounty }" class="city_input  inputFocus proCityQueryAll proCitySelAll" /></li>
        </ul>
        </div>
    <div class="data dataf" >
       <table border="0" cellpadding="0" cellspacing="0">
       <thead>
              <tr>
              	<td width="80">货物名称</td>
                <td width="70">装货时间</td>
                <td width="140">装货地</td>
                <td width="140">卸货地</td>
                <td width="130">载重或体积</td>
                <td width="100"><s:select list="dataDictInfoDomain.cargoFlagData" id="cargoFlag" listKey="code" listValue="name" name="orderCargoInfoDomain.cargoFlag" onchange="getSubmit('0');" cssClass="slect"></s:select></td>
                <td>操作</td>
              </tr>
        </thead>
        <s:if test="orderCargoInfoDomain.list.size>0">
          <s:iterator value="orderCargoInfoDomain.list" >
           <tr>
              	<td title="${cargoName }">${cargoName }</td>
              	<td>${requestStartTime }</td>
              	<td title="${startProCityCounty } ">${startProCityCounty }</td>
              	<td title="${endProCityCounty }">${endProCityCounty }</td>
              	<td>
              	<s:if test="cargoWeight!=0.0">
              	${cargoWeight }吨
              	</s:if>
              	<s:if test="cargoWeight!=''&&cargoCubage!=''">/</s:if>
              	<s:if test="cargoCubage!=0.0">
              	 ${cargoCubage }方
              	</s:if>
              	 
              	</td>
              	<td>${cargoFlagVal }</td>
                <td>
                
                <s:if test="cargoFlag==0"><!-- 待交易中 -->
                 <!-- 要求的装货时间小于当前时间（失效）——重新发布—— -->
                   <s:if test="isExpire==1">
                    <!-- 重新发布就新增一条记录 -->
                    	<a href="javascript:afreshPublish('<sys:context/>/addLocalOrderCargoInfo',${id},reload);">重新发布</a>/
                    	<a href="javascript:deleteInfo('确认删除该货源吗？','<sys:context/>/deleteLocalOrderCargoInfo',{'orderCargoInfoDomain.id':${id}},reload);">删除</a>
                    </s:if>
                    <s:else><!-- 待交易且未过期的 -->
                         <a href="javascript:setDriverCar('${id}')">找车</a>/
                          <!-- 司机对货源的点评(查看有多少司机看过我的货) -->
		                 <s:if test="cargoAssessCount!=0">
		                  <a target="_blank" href="<sys:context/>/queryDriverCargoAssessByCargoId?driverCargoAssessInfoDomain.cargoId=${id}">${cargoAssessCount }司机点评</a></br>
		                 </s:if>
	                     <s:if test="quoteCount!=0">
	                       <a target="_blank" href="<sys:context/>/queryDriverQuoteInfo?driverUserInfoDomain.orderId=${id}">${quoteCount }名司机报价</a>
		                 </s:if>
		                
		                  <s:if test="quoteCount==0">
		                     <a href="<sys:context/>/openModifyLocalOrderCargoInfo?orderCargoInfoDomain.id=${id}">修改</a>/
		                   <a href="javascript:deleteInfo('确认删除该货源吗？','<sys:context/>/deleteLocalOrderCargoInfo',{'orderCargoInfoDomain.id':${id}},reload);">删除</a>
		                  </s:if>
                    </s:else>
              </s:if><s:else><!-- 交易中或交易完成的 -->
                   <a href="<sys:context/>/queryTransactionInfo?transactionInfoDomain.cargoId=${id}&transactionInfoDomain.menuAId=<s:property value='@com.cy.dcts.common.constants.Constants@MY_ORDER'/>">查看订单</a>
                  <s:if test="cargoFlag==2">
                    <a href="javascript:deleteInfo('确认删除该货源吗？','<sys:context/>/deleteLocalOrderCargoInfo',{'orderCargoInfoDomain.id':${id}},reload);">删除</a>
                  </s:if>
              </s:else>
                </td>
              </tr>     
              </s:iterator>
        </s:if>
         
        </table>
<!-- <p class="more"><a href="">更多&gt;&gt;</a></p> -->
   <s:if test="orderCargoInfoDomain.list.size>0">
			<div class="numberBox" id="pageInfoHtmlId">  
			</div>
			</s:if>
			<input type='hidden' id='curPage' name='orderCargoInfoDomain.pageInfo.curPage' value='${orderCargoInfoDomain.pageInfo.curPage}'/><!-- 需要显示页面 -->
			<input type='hidden' id='curPageNo' name='orderCargoInfoDomain.pageInfo.curPageNo' value='${orderCargoInfoDomain.pageInfo.curPageNo}'/><!-- 当前页面 -->
			<input type='hidden' id='orderCargoInfoDomain.pageInfo.pageSize' name='orderCargoInfoDomain.pageInfo.pageSize' value='${orderCargoInfoDomain.pageInfo.pageSize}'/><!-- 当前页面 -->
			     
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
<link href="<sys:context/>/resource/css/bounced.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">	
function setDriverCar(orderCargoId) {
	art.dialog.open(
		'<sys:context/>/dcts/cargo/openDriver.jsp?orderCargoId='+orderCargoId+'&types=2',
		{id:'N3690',title:'订车',width:1220,height:600,lock:true}
	);//drag 是否允许用户拖动

}		
</script>
<!-- 弹出省市区的层 -->
<jsp:include page="/resource/threeLinkage/provinceCityAreaDiv.jsp" />
 <script src="<sys:context/>/resource/threeLinkage/js/jquery-1.6.2.min.js"></script>
 <script src="<sys:context/>/resource/threeLinkage/js/public.js"></script>
</body>
</html>