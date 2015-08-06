<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<sys:context/>/resource/js/pageInfo.js"></script>
<script type="text/javascript"  src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
<script type="text/javascript"   src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>

<title>快到网-个人中心-竞价详情</title>


<script type="text/javascript">
   $(function(){
   var totalPages = ${driverCargoAssessInfoDomain.pageInfo.totalPages};//总页数
		var curPageNos = ${driverCargoAssessInfoDomain.pageInfo.curPageNo};//当前页数
		var pageSize = ${driverCargoAssessInfoDomain.pageInfo.pageSize};//每页显示数据
		var totalRecords = ${driverCargoAssessInfoDomain.pageInfo.totalRecords};//总记录数
		pageInfo(totalPages,curPageNos,totalRecords);
   });
</script>
</head>
<body>
<jsp:include page="/head.jsp" />
<!--个人中心-->
<div class="mian">
	<div class="mian_bor">
	<jsp:include page="/dcts/myCenter/myCenterLeftMenu.jsp" />
    <s:form id="mainForm" action="/queryDriverCargoAssessByCargoId" namespace="/" method="post">
    <div class="fr sonafr">
        <div class="data dataf" >
        	<h3><i>&nbsp;</i>司机点评</h3>
          <table border="0" cellpadding="0" cellspacing="0">
            <thead >
              <tr>
              	<td width="30">&nbsp;</td>
                <td width="80">司机姓名</td>
                <td>车牌号</td>
                <td>手机号码</td>
                <td>点评类型</td>
                <td>评语</td>
                <td>点评时间</td>
              </tr>
            </thead>
            <s:if test="driverCargoAssessInfoDomain.list.size>0">
              <s:iterator value="driverCargoAssessInfoDomain.list">
                 <tr>
              <td><span class="icon">&nbsp;</span></td>
              <td>${name }</td>
              <td>${carNumber }</td>
              <td>${code }</td>
              <td>${typeVal }</td>
              <td>${assessInfo }</td>
              <td title="${createTime }">${createTime }</td>
            </tr>
              </s:iterator>
            </s:if>
          </table>
         <!-- 分页 -->
         <s:if test="driverCargoAssessInfoDomain.list.size()>0">
			<div class="numberBox" id="pageInfoHtmlId">  
			</div>
			</s:if>
			<input type='hidden' id='curPage' name='driverCargoAssessInfoDomain.pageInfo.curPage' value='${driverCargoAssessInfoDomain.pageInfo.curPage}'/><!-- 需要显示页面 -->
			<input type='hidden' id='curPageNo' name='driverCargoAssessInfoDomain.pageInfo.curPageNo' value='${driverCargoAssessInfoDomain.pageInfo.curPageNo}'/><!-- 当前页面 -->
			<input type='hidden' id='driverCargoAssessInfoDomain.pageInfo.pageSize' name='driverCargoAssessInfoDomain.pageInfo.pageSize' value='${driverCargoAssessInfoDomain.pageInfo.pageSize}'/><!-- 当前页面 -->
			   
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
</body>
</html>

