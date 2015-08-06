<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp"%>
<head>
<title>快到网-个人中心-发布货源成功</title>
<link href="<sys:context/>/resource/css/public.css" rel="stylesheet" type="text/css" />
<link href="<sys:context/>/resource/css/bounced.css" rel="stylesheet" type="text/css" />
<script type="text/javascript"  src="<sys:context/>/resource/artDialog4.1.6/artDialog.js?skin=opera"></script>
<script type="text/javascript"   src="<sys:context/>/resource/artDialog4.1.6/plugins/iframeTools.js"></script>
<script type="text/javascript">
   //继续发布（刷新父页面，关闭art.dialog）
   function dialogClose(){
      art.dialog.close();
   }
   
</script>
</head>
<body>
<table cellpadding="0" cellspacing="0">
	<tr>
    	<td class="aui_nw"></td>
        <td class="aui_n">
        
        <div class="aui_titleber">
        	<div class="aui_title">搜索提示</div>
            <a class="aui_close" href="###"></a>
        </div>
        
        </td>
        <td class="aui_ne"></td>
    </tr>
	<tr>
    	<td class="aui_w"></td>
        <td class="aui_c">
        <!--只需要把这段代码放入弹窗内-->
<div class="bucutf">
     <div class="fl tks"><img src="../resource/image/tanku/jou.jpg" width="70" height="70" /></div>
     <div class="fr frkst">没有找到您搜索的车源，是否您填写的信息有误。<br />或者您可以在左侧搜索框内搜索您要找的车源。</div>
     <div class="nowf"><a href="javascript:dialogClose();">确定</a></div>
</div>
		<!--内容结束-->	
        </td>
        <td class="aui_e"></td>
    </tr>
	<tr>
    	<td class="aui_sw"></td>
        <td class="aui_s"></td>
        <td class="aui_se"></td>
    </tr>
</table>
</body>
</html>

