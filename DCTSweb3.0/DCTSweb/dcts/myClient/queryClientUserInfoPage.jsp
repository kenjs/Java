<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<head>
<title>快到网-个人中心-发货方管理</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/personal.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/threeLinkage/css/cityLayout.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<sys:context/>/resource/js/basis.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/pageInfoThree.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/mycenter/myCenterLeftMenu.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/myClient/queryClient.js"></script>

<style type="text/css">
.city_input { border:1px solid #d6d6d6; width:180px; height:30px;
 background:url(<sys:context/>/resource/threeLinkage/images/ts-indexcity.png) no-repeat; 
 line-height:30px; margin-top:5px;  text-indent:5px;}
</style>

<script type="text/javascript">
   $(function(){
   		var totalPages = ${webUserInfoDamain.pageInfo.totalPages};//总页数
		var curPageNos = ${webUserInfoDamain.pageInfo.curPageNo};//当前页数
		var totalRecords = ${webUserInfoDamain.pageInfo.totalRecords};//总记录数
		clientPageInfo(totalPages,curPageNos,totalRecords);
   });
    
  function getDelete(id) {
  
  }
</script>
</head>
<body>
  
<jsp:include page="/head.jsp" />
<!--个人中心-->

<div class="mian">
	<div class="mian_bor">
	<jsp:include page="/dcts/myCenter/myCenterLeftMenu.jsp" />
	<s:form id="mainForm" action="/querySonWebUserInfo" namespace="/" method="post">
	<!-- 当前左边菜单链接的Id -->
	<input type="hidden" id="menuAIdHi" value="${webUserInfoDamain.menuAId }" name="menuAId"/>
	<input type="hidden" id="userType" value="${webUserInfoDamain.userType }" name="userType"/>
	<input type='hidden' id='curPage' name='curPage' value='${webUserInfoDamain.pageInfo.curPage}'/><!-- 需要显示页面 -->
    <div class="fr sonafr">
    	<div class="funtop">
			<h3><i>&nbsp;</i>${webUserInfoDamain.userTypeVal }管理
		
			</h3>
			<ul class="ulnt">
				<li><label>客户名称：</label>
					<input name="companyName" id="companyName" style="width:202px;" value="${webUserInfoDamain.companyName}" type="text" />
				</li>
				<li><label>公司地址：</label>
					 <input type="text" id="companyPcc" name="companyPcc" readonly="readonly" style="width:155px;" value="${webUserInfoDamain.companyPcc}" class="city_input  inputFocus proCityQueryAll proCitySelAll" ov="请选择/输入城市名称"/>
				</li>
				<li><label>创建日期：</label>
					<input id="createTimeStart" name="createTimeStart" value="${webUserInfoDamain.createTimeStart}" type="text" class="ut1" onClick="WdatePicker()" />
					&nbsp;&nbsp;至&nbsp;&nbsp;
					<input id="createTimeEnd" name="createTimeEnd" value="${webUserInfoDamain.createTimeEnd}" type="text" class="ut1" onClick="WdatePicker()"/>
				</li>
				<li><label>是否有效：</label>
					<select name="deletedFlag" id="deletedFlag" class="valid"><option <s:if test="webUserInfoDamain.deletedFlag==0">selected="selected"</s:if>  value="0">有效</option><option <s:if test="webUserInfoDamain.deletedFlag==1">selected="selected"</s:if> value="1">无效</option></select>
				</li>
				<li class="liut" style="right: 80px"><a href="javascript:getSubmit();" class="sout">搜索</a></li>
				
				<li class="liut" ><a href="<sys:context/>/addSonWebUserInfo?userType=${webUserInfoDamain.userType }&menuAId=${webUserInfoDamain.menuAId }" class="sout">新增</a></li>
			</ul>
		</div>
        <div class="data dataf" >
        	<div class="data dataf" >
				  <table border="0" cellpadding="0" cellspacing="0">
					<thead >
					  <tr>
						<td width="100">客户名称</td>
						<td width="120">公司地址</td>
						<td width="130">详细地址</td>
						<td width="80">联系人</td>
						<td width="100">联系电话</td>
						<td width="60">编码</td>
						<td width="70">创建日期</td>
						<td width="120">操作</td>
					  </tr>
					</thead>
					<!--  列表数据循环开始 -->
            		<c:if test="${fn:length(webUserInfoDamain.list) > 0}">
   						<c:forEach items="${webUserInfoDamain.list}" var="pic">
   							<tr>
   								<td>${pic.companyName}</td>
					  			<td title="${pic.companyProvince}${pic.companyCity}${pic.companyCounty}">${pic.companyProvince}${pic.companyCity}${pic.companyCounty}</td>
					  			<td title="${pic.companyAddress}">${pic.companyAddress}</td>
					  			<td>${pic.contactName}</td>
					  			<td>${pic.contactTelephone}</td>
					  			<td>${pic.encoded}</td>
					  			<td>${pic.createTime}</td>
					  			<td>
					  				<a href="<sys:context/>/querySonWebUserInfoById?userType=${webUserInfoDamain.userType }&menuAId=${webUserInfoDamain.menuAId }&type=select&id=${pic.id}">详情</a>
					  				&nbsp;
					  				<a href="<sys:context/>/querySonWebUserInfoById?userType=${webUserInfoDamain.userType }&menuAId=${webUserInfoDamain.menuAId }&id=${pic.id}">修改</a>
					  				&nbsp;
					  				<!-- <a href="javascript:getDelete('${pic.id}');">删除</a> -->
					  			</td>
            				</tr>	
  						</c:forEach>
  					</c:if>
		  			<c:if test="${fn:length(webUserInfoDamain.list) == 0}">
		   				<tr>
		              		<td colspan="8" align="center">暂无${webUserInfoDamain.userTypeVal }信息</td>
		            	</tr>	
		  			</c:if>
				</table>
				<!-- 分页开始 -->
				<div id="pageInfoHtmlId" class="feye">
				</div>
				<!-- 分页结束 -->
			</div>  
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
