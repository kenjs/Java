<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/personal.css" rel="stylesheet" type="text/css" />
<title>快到网-个人中心-评价管理</title>
<script type="text/javascript" src="<sys:context/>/resource/js/mycenter/myCenterLeftMenu.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/pageInfoTwo.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/pageInfoThree.js"></script>
<script src="<sys:context/>/resource/js/jquery.js"></script>
<!-- <script src="<sys:context/>/resource/js/gsjs.js"></script> -->
<script type="text/javascript">
 $(function(){
   //层的显示 及菜单的样式
 for (i = 1; i <= 2; i++) {
        var menu = document.getElementById("cur" + i);
        var cont = document.getElementById("con_cur" + "_" + i);
        menu.className = i == ${blockDiv } ? "current" : "";
        if (i == ${blockDiv }) {
            cont.style.display = "block";
        } else {
            cont.style.display = "none";
        }
    }
   
   if(${blockDiv }==1){//来自司机的评价（司机对该货主的评价）
     var totalPage= ${driverUserAssessInfoDomain.pageInfo.totalPages};//总页数
	 var curPageNo= ${driverUserAssessInfoDomain.pageInfo.curPageNo};//当前页数
	 var pageSize= ${driverUserAssessInfoDomain.pageInfo.pageSize};//每页显示数据
	 var totalRecord = ${driverUserAssessInfoDomain.pageInfo.totalRecords};//总记录数
	 pageInfo(totalPage,curPageNo,totalRecord);
   }else{//对他人的评价（该货主对司机的评价）
	var totalPages = ${userDriverAssessInfoDomain.pageInfo.totalPages};//总页数
	var curPageNos = ${userDriverAssessInfoDomain.pageInfo.curPageNo};//当前页数
	var pageSize = ${userDriverAssessInfoDomain.pageInfo.pageSize};//每页显示数据
	var totalRecords = ${userDriverAssessInfoDomain.pageInfo.totalRecords};//总记录数
	pageInfoTwo(totalPages,curPageNos,totalRecords);
   }
   });

  function getSubmit(userFlag){
    // 区分点击的是搜索按钮（初始化从第一条开始查）还是分页链接
       $("#userFlagId").val(userFlag);  
	   document.getElementById("mainForm").submit();
  }
  function mainSubmit(flag){
    // 区分点击的是搜索按钮（初始化从第一条开始查）还是分页链接
       $("#driverFlagId").val(flag);  
	   document.getElementById("towForm").submit();
  }
</script>
</head>
<body>
<jsp:include page="/head.jsp" />
<input type="hidden" id="menuAIdHi" value="${menuAId }" name="menuAId"/>
<!--个人中心-->
<div class="mian">
	<div class="mian_bor">
	<jsp:include page="/dcts/myCenter/myCenterLeftMenu.jsp" />
    <div class="fr sonafr">
            <div class="dynam">
                <h3><i>&nbsp;</i>我的评价动态</h3>
                <div class="numder">
                <span><i class="icon7">&nbsp;</i>好评(${driverUserAssessInfoDomain.satisfactory })</span>
                <span><i class="icon8">&nbsp;</i>中评(${driverUserAssessInfoDomain.arial })</span>
                <span><i class="icon9">&nbsp;</i>差评(${driverUserAssessInfoDomain.noSatisfactory })</span>
            </div>
            </div>
     <div class="boder tab_data">
                  <div class="mian_title">
                    <ul>
                      <li id="cur1" onclick ="getSubmit('0')" class="current"><a href="###">来自司机的评价</a></li>
                      <li id="cur2" onclick ="mainSubmit('0')"><a href="###">给他人的评价</a></li>
                    </ul>
                  </div>
                  
         <!-- (1)司机给货主的评价（来自司机的评价） -->
        <div id="con_cur_1" class="data" >
          <s:form id="mainForm" action="/loadDriverUserEvaluation?menuAId=a_id_9" namespace="/" method="post">
          <!-- 区分点击的是搜索按钮（初始化从第一条开始查）还是分页链接 -->
		<input type="hidden" id="userFlagId" value="" name="userFlag"/>
		
          <table border="0" cellpadding="0" cellspacing="0">
            <thead >
              <tr>
                <td width="130"><s:select id="assessEvaluateScoreId" list="dataDictInfoDomain.tradeEvaluateScoreData" onchange="getSubmit('0')" cssClass="slect" listKey="code" listValue="name" name="driverUserAssessInfoDomain.assessEvaluateScore"/></td>
                <td width="310">评论</td>
                <td width="120">评价来源</td>
                <td width="240">货物信息</td>
              </tr>
            </thead>
            <tbody>
            <s:if test="driverUserAssessInfoDomain.list.size>0">
               <s:iterator value="driverUserAssessInfoDomain.list">
                    <tr valign="top">
		              <td>
		              <s:if test="assessEvaluateScore==@com.cy.dcts.common.constants.Constants@EVALUATE_NOSATISFACTORY_KEY">
		                  <i class="icon9">&nbsp;</i>
		                </s:if><s:elseif test="assessEvaluateScore==@com.cy.dcts.common.constants.Constants@EVALUATE_ARIAL_KEY">
		                  <i class="icon8">&nbsp;</i>
		                </s:elseif><s:else>
		                  <i class="icon7">&nbsp;</i>
		                </s:else>
		              ${assessEvaluateValue }</td>
		              <td><div class="tdiv">${assess }</div></td>
		              <td><i class="icon">&nbsp;</i><a style="color: #4a6ba5;" target="_blank" href="<sys:context/>/openDriverDetailed.action?driverId=${driverId}">${name }</a></td>
		              <td><div class="tdiv1"><p>${cargoTypeVal }-${cargoName }</p> ${startProvince }-${startCity }——${endProvince }-${endCity }</div></td>
		            </tr>
               </s:iterator>
            </s:if>
             
            </tbody>
            </table>
             <!-- 分页 -->
         <s:if test="driverUserAssessInfoDomain.list.size>0">
			<div class="numberBox" id="pageInfoHtmlId">  
			</div>
			</s:if>
			<input type='hidden' id='curPage' name='driverUserAssessInfoDomain.pageInfo.curPage' value='${driverUserAssessInfoDomain.pageInfo.curPage}'/><!-- 需要显示页面 -->
			<input type='hidden' id='curPageNo' name='driverUserAssessInfoDomain.pageInfo.curPageNo' value='${driverUserAssessInfoDomain.pageInfo.curPageNo}'/><!-- 当前页面 -->
			<input type='hidden' id='driverUserAssessInfoDomain.pageInfo.pageSize' name='driverUserAssessInfoDomain.pageInfo.pageSize' value='${driverUserAssessInfoDomain.pageInfo.pageSize}'/><!-- 当前页面 -->
			  
            </s:form>
         </div>
         
         <!-- (2)货主给司机的评价（对他人的评价） -->
	<div id="con_cur_2" class="hiden data">
	  <s:form id="towForm" action="/loadUserDriverEvaluation?menuAId=a_id_9" namespace="/" method="post">
	    <!-- 区分点击的是搜索按钮（初始化从第一条开始查）还是分页链接 -->
		<input type="hidden" id="driverFlagId" value="" name="flag"/>
	  
          <table border="0" cellpadding="0" cellspacing="0">
            <thead >
              <tr>
                <td width="130"><s:select id="tradeEvaluateScoreId" list="dataDictInfoDomain.tradeEvaluateScoreData" onchange="mainSubmit('0')" cssClass="slect" listKey="code" listValue="name" name="userDriverAssessInfoDomain.tradeEvaluateScore"/></td>
                <td width="310">评论</td>
                <td width="120">被评价人</td>
                <td width="240">货物信息</td>
              </tr>
            </thead>
            <tbody>
              <s:if test="userDriverAssessInfoDomain.list.size>0">
               <s:iterator value="userDriverAssessInfoDomain.list">
                    <tr valign="top">
		              <td>
		                <s:if test="tradeEvaluateScore==@com.cy.dcts.common.constants.Constants@EVALUATE_NOSATISFACTORY_KEY">
		                  <i class="icon9">&nbsp;</i>
		                </s:if><s:elseif test="tradeEvaluateScore==@com.cy.dcts.common.constants.Constants@EVALUATE_ARIAL_KEY">
		                  <i class="icon8">&nbsp;</i>
		                </s:elseif><s:else>
		                  <i class="icon7">&nbsp;</i>
		                </s:else>
		               ${tradeEvaluateValue }</td>
		              <td><div class="tdiv">${assess }</div></td>
		              <td><i class="icon">&nbsp;</i><a style="color: #4a6ba5;" target="_blank" href="<sys:context/>/openDriverDetailed.action?driverId=${driverId}">${name }</a></td>
		              <td><div class="tdiv1"><p>${cargoTypeVal }-${cargoName }</p> ${startProvince }-${startCity }——${endProvince }-${endCity }</div></td>
		            </tr>
               </s:iterator>
            </s:if>
            </tbody>
            </table>
             <!-- 分页 -->
         <s:if test="userDriverAssessInfoDomain.list.size>0">
			<div class="numberBox" id="pageInfoHtmlIds">  
			</div>
			</s:if>
			<input type='hidden' id='curPages' name='userDriverAssessInfoDomain.pageInfo.curPage' value='${userDriverAssessInfoDomain.pageInfo.curPage}'/><!-- 需要显示页面 -->
			<input type='hidden' id='curPageNos' name='userDriverAssessInfoDomain.pageInfo.curPageNo' value='${userDriverAssessInfoDomain.pageInfo.curPageNo}'/><!-- 当前页面 -->
			<input type='hidden' id='userDriverAssessInfoDomain.pageInfo.pageSize' name='userDriverAssessInfoDomain.pageInfo.pageSize' value='${userDriverAssessInfoDomain.pageInfo.pageSize}'/><!-- 当前页面 -->
			  
          </s:form>   
        </div>
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
</body>
</html>
