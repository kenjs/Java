<html>
<%@ include file="/common/include.jsp"%>
<%@ page contentType="text/html;charset=GBK"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=gbk">
<title>伸缩按钮功能</title>
<%@ include file="/common/meta.jsp"%>
<script type="text/javascript">
//点击伸缩右边内容
function changeWin(){
	var sizeMiddleOrg = "170,5,*";
	var sizeMiddleMax = "0,5,*";;
	var middleObject=top.document.getElementById("FRM_MIDDLE");
    if(middleObject.cols == sizeMiddleOrg){
		middleObject.cols = sizeMiddleMax;
		document.all.menuSwitch.innerHTML = "<img src='"+jcontextPath+"/resource/wlglpt/images/l_stretch_btn.gif'>";
	}else{
		middleObject.cols = sizeMiddleOrg;
		document.all.menuSwitch.innerHTML = "<img src='"+jcontextPath+"/resource/wlglpt/images/l_expand_btn.gif'>";
	}
}
</script>

<style type="text/css">
html,body {background:#2088ff;}
</style>
</head>

<body>
<table width="5" height="100%" border="0" cellspacing="0" cellpadding="0">
  <tr>
    <td onClick="changeWin()" style="cursor:pointer;" id="menuSwitch"><img src="<sys:context/>/resource/wlglpt/images/l_expand_btn.gif" /></td>
  </tr>
</table>
</body>
</html>
