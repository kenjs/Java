<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<%@ include file="/common/include-jqueryJs.jsp"%>
<head>
<title>快到网-身份证查询-结果</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/index.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/personal.css" rel="stylesheet" type="text/css" />

<script type="text/javascript" src="<sys:context/>/resource/js/cargo/importCargoFormFile.js"></script>
<script type="text/javascript" src="<sys:context/>/resource/js/mycenter/myCenterLeftMenu.js"></script>
<script type="text/javascript"  src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
<script type="text/javascript"   src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>
<script type="text/javascript">
	function getReturn() {
		window.location.href = jcontextPath + "/openIdetityVerifyPage";
	}
</script>
</head>
  
<body>
  
<jsp:include page="/head.jsp" />
<div class="mian">
<input type="hidden" id="menuAIdHi" value="${menuAId }" name="menuAId"/>
	<div class="mian_bor">
	<jsp:include page="/dcts/myCenter/myCenterLeftMenu.jsp" />
	
	<div class="fr sonafr">
    	<div class="ntica">
            <h3><i>&nbsp;</i>身份证查询结果</h3>
            <div class="laing two_laing">
                <ul>
                	<s:if test="domain.errorCode != null">
                		<li><span style="color: red;font-size: 20px;font-style: oblique;">不好, 出错了！</span></li>
                		<li>
                			<label>错误代码&nbsp;:</label><span style="color: red;">${domain.errorCode }</span>
                		</li>
                		<li>
                			<label>错误信息&nbsp;:</label><span style="color: red;">${domain.errorMesage }</span>
                		</li>
                	</s:if>
                	<s:elseif test="domain.errorMesage != null">
                		<li>
		                    <label>身份证号&nbsp;:</label><span style="color: red;">${domain.IDNumber }</span>
		                </li>
		                <li>
                    		<label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;:</label><span style="color: red;">${domain.name }</span>
                  		</li>
                  		<li>
                  			<hr />
                  		</li>
                  		<li>
                  			<label>错误信息&nbsp;:</label><span style="color: red;">${domain.errorMesage }</span>
                  		</li> 
                  		<li>
                  			<label>错&nbsp;误&nbsp;列&nbsp;&nbsp;:</label><span style="color: red;">${domain.errorMesageCol }</span>
                  		</li>
                	</s:elseif>
                	<s:elseif test="domain.accountMsg != null">
                		<li>
                			<label>错误信息&nbsp;:</label><span style="color: red;">${domain.accountMsg }</span>
                		</li>
                	</s:elseif>
                	<s:else>
	                  <li>
	                    <label>身份证号&nbsp;:</label><span style="color: red;">${domain.IDNumber }</span>
	                  </li>
	                  <li>
	                  	<label>查询结果&nbsp;:</label><span style="color: red;">${domain.IDNumberVerifyResult }</span>
	                  </li>
	                  <li>
	                  	<hr />
	                  </li> 
	                  <li>
	                    <label>姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;:</label><span style="color: red;">${domain.name }</span>
	                  </li>                                  
	                  <li>
	                  	<label>查询结果&nbsp;:</label><span style="color: red;">${domain.nameVerifyResult }</span>
	                  </li>	                 
                  </s:else>
                  <li class="ternow mt20">
	                    <label></label>
	                    <input id="form_submit" type="button" value="返回" class="sub" onclick="getReturn()" style="margin-left: 25%;"/></li>
                </ul>
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