<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<%@page import="com.cy.common.domain.UserDomain"%>
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>
<%@ include file="/common/include.jsp"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk" />
<title>弹出窗口样式</title>
<%@ include file="/common/meta.jsp"%>

<style type="text/css">
html,body {background:none;}
</style>

<script type="text/javascript">
	function onTc() {
			//showAlert("弹出框~~~");
			showMaxError("弹出框~~~");
			//checkdata();
			
		}
	
	$(function(){
		$("#closeBtn").click(function(){
			window.close();
		})
	}) 
			
$(document).ready(function(){
	var sjJgbm = $("#gs").val();
	bmInit(sjJgbm, '', "bm", "bm", "Y", "N");
	
	var bm = $("#bm").val();
	gwInit(bm, '', "gw", "gw", "Y", "N");
});
</script>
</head>

<body>
<s:form action="demo!initMx" namespace="/xtgl" method="post" id="mainForm" name="mainForm">
<!-- 弹出窗口标题带关闭图标 id="toolbar"为拖动iframe窗口用到，不可修改 -->

<!-- 弹出窗口内容区域 style里属性为窗口内容高度，超过规定的高度自动出滚动条，高度值根据实际改变 -->

 <div class="pop_contc" style="height:320px; overflow:auto;">
  	 <fieldset>
		  <legend>基本信息</legend>
    <table width="100%" border="0" cellspacing="0" cellpadding="0" class="poptabinfo_css">
      
      <tr>
        <td width="12%" align="right">公司名称:</td>
        <td width="88%" colspan="3"><button type="button" class="pop_btnbg" onclick="onTc();">弹出框</button></td>
      </tr>
      <tr>
        <td align="right">公司名称:</td>
        <td colspan="3">物流系统</td>
      </tr>
      <tr>
        <td align="right">公司名称:</td>
        <td colspan="3">物流系统</td>
      </tr>
     
      <tr>
        <td align="right">公司名称:</td>
        <td colspan="3">物流系统</td>
      </tr>
      <tr>
        <td align="right">公司名称:</td>
        <td colspan="3">物流系统</td>
      </tr>
      <tr>
        <td align="right">公司名称:</td>
        <td colspan="3">物流系统</td>
      </tr>
      <tr>
         <td width="15%" align="right">公司：</td>
         <td width="35%">
         	<sys:gsList myId="gs" myName="gs" onChange="bmInit(this.value, '', 'bm', 'bm', 'Y', 'N')" mcContainDmBz="N" myClass="select"/>
		  </td>
		  <td width="15%" align="right">部门：</td>
	         <td width="35%">
	          	<select id="bm" name="bm" class="select" onchange="gwInit(this.value, '', 'gw', 'gw', 'Y', 'N')"></select>
		  </td>
       </tr>
       <tr>
         <td width="15%" align="right">岗位：</td>
         <td width="35%">
         	<select id="gw" name="gw" class="select"></select>
		  </td>
		  <td width="15%" align="right"></td>
	         <td width="35%">
	          	<button type="button" class="pop_btnbg" onclick="onTc();">弹出框</button>
		  </td>
       </tr>
    </table>
    </fieldset>
    <div class="pop_btn">
      <button type="button" class="pop_btnbg">检 索</button>
      &nbsp;
       <button type="button" class="pop_btnbg" id="closeBtn">关 闭</button>
    </div>
  </div>

<%@include file="/common/message.jsp" %>
</s:form>
</body>
</html>