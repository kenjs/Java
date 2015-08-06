<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<title>快到网-用户-登录</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="resource/css/public.css" rel="stylesheet" type="text/css" />
<link href="resource/css/index.css" rel="stylesheet" type="text/css" />
<script type="text/javascript">

</script>
<style type="text/css">
	#saveBtn{
		background: url("resource/image/index/radius.jpg") no-repeat scroll -198px -220px transparent;
	}
</style>
</head>
<body>
<!-- 头部开始 -->
<div class="header">
  <div class="hea_top">
  </div>
  <div class="hea_cent">
    <div class="fl hea_logo">
    	<a href=""><img src="resource/image/index/logo.jpg" /></a>
          <div class="slet_dta">
            <a>会员登录</a>
          </div>
    </div>
  </div>
</div>
<!-- 头部结束 -->
<form id="mainForm" action="dologin" method="post">
<div class="mian">
	<div class="fl flant">
    <div class="laing">
        <ul>
          <li>
            <label>用户名</label>
            <input id="code" name="code" tipMsg="用户名" type="text" class="int" onkeydown="if(event.keyCode == 13)login();" value="" />
          	 <div class="mpt" id="divCodeMpt" style="display:none">
            	<div class="wn_a"></div>
                <div class="wn_s" id="divCodeNull" style="display:none">用户名不能为空！</div>
                <div class="wn_s" id="divCodeNot" style="display:none">用户名不存在！</div>
                <div class="wn_c"></div>
            </div> 
          </li>
          <li>
            <label>密码</label>
            <input id="password" name="password" type="password" value="" class="int" onkeydown="if(event.keyCode == 13)login();" />
          	 <div class="mpt" id="divPwdMpt" style="display:none">
            	<div class="wn_a"></div>
                <div class="wn_s" id="divPwdNull" style="display:none">密码不能为空！</div>
                <div class="wn_s" id="divPwdError" style="display:none">登录密码输入错误！</div>
                <div class="wn_c"></div>
            </div>
          </li>
          <li>
            <label></label>
            <input id="bearPassword" name="bearPassword" type="checkbox" value="" />
            &nbsp;&nbsp;&nbsp;记住密码<span class="mpt1"></span></li>
          <li class="lanot">
            <label></label>
            <!-- <a href="" id="saveBtn" class="hoa mr10">登录</a> -->
            <input type="submit" class="hoa mr10" id="saveBtn" style="width: 50px;height: 30px;" value="登陆"/>
          </li>
        </ul>
      </div>
    </div>
    <div class="fr frland"></div>
    <div class="both"></div>
<br />
<br />
</div>
</form>
<!-- 底部开始 -->

<!-- 底部结束 -->
</body>
</html>