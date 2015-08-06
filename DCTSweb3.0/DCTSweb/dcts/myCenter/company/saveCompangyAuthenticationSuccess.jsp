<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>快到网-个人中心-批量导入</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/personal.css" rel="stylesheet" type="text/css" />

</head>
  
<body>
  
<jsp:include page="/head.jsp" />
<div class="mian">
<input type="hidden" id="menuAIdHi" value="${menuAId }" name="menuAId"/>
	<div class="mian_bor">
	<jsp:include page="/dcts/myCenter/myCenterLeftMenu.jsp" />
	<!-- 提醒内容 -->
	 <div class="fr sonafr">
    	<div class="ntica">
            <h3><i>&nbsp;</i>认证提醒</h3>
            <div class="media">
         		认证资料已接受，三个工作日后，可以看到认证结果哦。
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
