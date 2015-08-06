<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<%@ include file="/common/meta.jsp"%>
<head>
<title>托运单查询 </title>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">

//显示隐藏查询条件
function slideToggle(sydiv){
	if (sydiv=="jbxx")
		{$("#jbxxcont").slideToggle(100);} //显示隐藏基本信息效果的切换,点一下收,点一下开
	if (sydiv=="pchwxx")
		{$("#pchwxxcont").slideToggle(100);} //显示隐藏派车货物信息效果的切换,点一下收,点一下开
	if (sydiv=="clgzxx")
		{$("#clgzxxcont").slideToggle(100);} //显示隐藏车辆跟踪信息效果的切换,点一下收,点一下开
	if (sydiv=="yfzfxx")
		{$("#yfzfxxcont").slideToggle(100);} //显示隐藏运费支付信息效果的切换,点一下收,点一下开
	if (sydiv=="wlssxx")
		{$("#wlssxxcont").slideToggle(100);} //显示隐藏运费支付信息效果的切换,点一下收,点一下开

}

function getAutoGridHeight(length) {
	var heightT = 260;
    if (length <= 2) {
    	heightT = 2 * 25 + 15;
    }else if (length <= 10) {
    	heightT = length * 25 + 15;
    }
    
    return heightT;
}

</script>
<base target="_self" />
</head>

<body style="overflow: auto;">
<%try{ %>
<s:form action="jctydgl!initMx" namespace="/jcgl" method="post" id="mainForm" name="mainForm">
	<s:hidden name="domain.ddDjxh" />
	
	<div class="pop_tbmain">
      <div class="pop_tbtitle"><p><a href="#" class="btnarrow" onfocus="this.blur();" onclick="slideToggle('jbxx')"></a></p><h2>基本信息</h2></div>
      <div class="pop_tbcont" id="jbxxcont">
      	<jsp:include page="/work/hygl/hy_tydgl_mx_readonly.jsp"/>
      </div>
    </div>
    
    <div class="pop_tbmain">
      <div class="pop_tbtitle"><p><a href="#" class="btnarrow" onfocus="this.blur();" onclick="slideToggle('pchwxx')"></a></p><h2>派车货物信息</h2></div>
      <div class="pop_tbcont" id="pchwxxcont">
      	<jsp:include page="/work/jcgl/jc_tyd_pcxx.jsp" />
      </div>
    </div>
    
    <div class="pop_tbmain">
      <div class="pop_tbtitle"><p><a href="#" class="btnarrow" onfocus="this.blur();" onclick="slideToggle('clgzxx')"></a></p><h2>车辆跟踪信息</h2></div>
      <div class="pop_tbcont" id="clgzxxcont">
      	<jsp:include page="/work/jcgl/jc_clgzxx.jsp" />
      </div>
    </div>
    
    <div class="pop_tbmain">
      <div class="pop_tbtitle"><p><a href="#" class="btnarrow" onfocus="this.blur();" onclick="slideToggle('yfzfxx')"></a></p><h2>运费支付情况</h2></div>
      <div class="pop_tbcont" id="yfzfxxcont">
      	<jsp:include page="/work/jcgl/jc_yfzfxx.jsp" />
      </div>
    </div>
	
	<div class="pop_tbmain">
      <div class="pop_tbtitle"><p><a href="#" class="btnarrow" onfocus="this.blur();" onclick="slideToggle('wlssxx')"></a></p><h2>物流损失信息</h2></div>
      <div class="pop_tbcont" id="wlssxxcont">
      	<jsp:include page="/work/jcgl/jc_wlssxx.jsp" />
      </div>
    </div>
    
	<%@include file="/common/message.jsp" %>
</s:form>
<%}catch(Exception e){e.printStackTrace();throw e;} %>
</body>
</html>
