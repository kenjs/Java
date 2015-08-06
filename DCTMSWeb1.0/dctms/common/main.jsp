<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<%@ page contentType="text/html;charset=GBK"%>

<head>
<title>快到网管理系统</title>
<script type='text/javascript' src='<sys:context/>/resource/My97DatePicker/WdatePicker.js'></script>
<script type='text/javascript' src='<sys:context/>/resource/pageframe/com/jquery-1.6.1.js'></script>
<script type='text/javascript' src='<sys:context/>/resource/pageframe/js/helper.js'></script>
<script type='text/javascript' src='<sys:context/>/resource/pageframe/js/common.js'></script>
<script type='text/javascript'  src='<sys:context/>/resource/pageframe/js/jquery.cookie.js'></script>
<script type='text/javascript' src='<sys:context/>/resource/pageframe/com/json2.js'></script>
<script type='text/javascript' src='<sys:context/>/resource/pageframe/js/base.js'></script>
<style type="text/css">
body{background:#ccc}
</style>
<script>
function mainFun(str){
	var form = $("#mainForm");
	form.attr("action",str);
	form.submit();
}
</script>
</head>
<frameset rows="80,*" cols="*" id="FRM_WHOLE" frameborder="no" framespacing="0">
	 <frame src="common/top.jsp" name="FRM_TOP" id="FRM_TOP" scrolling="no" frameborder="0" height="100%">
	 <frameset rows="*" cols="200,*" id="FRM_MAIN" frameborder="no" framespacing="0">
		   <frame src="common/left.jsp" name="FRM_LEFT" id="FRM_LEFT" scrolling="auto" height="100%" width="98%">
		   <frame src="common/blank.jsp" name="FRM_BLANK" id="FRM_BLANK" scrolling="auto" height="100%" width="98%">
  	</frameset>  
</frameset>

<body>

</body>
</html>