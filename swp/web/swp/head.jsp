<!DOCTYPE html>
<html>
<%@ page language="java" contentType="text/html;charset=UTF-8"%>
<%@ include file="/common/include-tag.jsp" %>
<%@ include file="/common/include-jqueryJs.jsp" %>
<head>
  <title>快到网-营销平台-登录</title>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

  <style type="text/css">
    *{ margin:0px; padding:0px;}
    ul,li{ list-style-type:none;}
    a{ text-decoration:none;}
    .hea_top{ width:1000px; margin:0 auto; height:36px; line-height:36px; overflow: hidden;}
    .hea_top ul li {color:#121212; font-size:12px; letter-spacing:1px; float:right; padding:0 10px;}
    .hea_top ul li a{color:#121212; padding-right:15px;}
    .hea_top ul li span{ color:red; margin-right:3px;}
  </style>


  <style type="text/css">
    .fileu input[type="password"]{ height:24px; line-height:24px; width:190px;}
    .fileu p{ font-size:12px; color:red; padding-left:80px; }
  </style>

  <link href="<sys:context/>/resource/css/slide.css" rel="stylesheet" type="text/css" />
  <script type="text/javascript" src="<sys:context/>/resource/js/jquery.js"></script>
  <script src="<sys:context/>/resource/threeLinkage/js/jquery-1.6.2.min.js"></script>


  <script type="text/javascript">
    function dropoutUser() {
      top.location.href = jcontextPath + "/userLogout.jspx";
    }

    function updatePassword() {
      var userId = $("#userId").val();
      if(userId == null || userId == "") {
        $("#phtmlId").html("参数错误！");
        return;
      }
      var password1 = $("#password1").val();
      if(password1 == null || password1 == "") {
        $("#phtmlId").html("原始密码不能为空！");
        return;
      }
      var password = $("#password").val();
      if(password == null || password == "") {
        $("#phtmlId").html("新密码不能为空！");
        return;
      }
      $.ajax({
        url: jcontextPath + "/updatePassWord.jspx",
        type:'post',
        dataType:'json',
        data:{'userId':userId,'password':password,'password1':password1},
        success:function(data){//回传函数
          if(data.result == 0) {//成功
            usercloses();
            getsAlert(data.errorMessage);
          }else if(data.result == 1){//未登录
            $("#phtmlId").html(data.errorMessage);
            return;
          }
        }
      });
    }

    function updatePasswordPage(userId) {
      var str = "<div class=\"fileu\"><ul>";
      str += "<p id=\"phtmlId\"></p>";
      str += "<li><label>原始密码：</label><input type=\"password\" id=\"password1\" name=\"password1\" value=\"\" /><input type=\"hidden\" id=\"userId\" name=\"userId\" value=\""+userId+"\" /></li>";
      str += "<li><label>新密码：</label><input type=\"password\" id=\"password\" name=\"password\" value=\"\" /></li>";
      str += "<li><label></label><input name=\"\" type=\"button\" value=\"修改\" onclick=\"updatePassword();\" /><input name=\"\" type=\"button\" value=\"关闭\" onclick=\"usercloses();\"/></li>";
      str += "</ul></div>";
      art.dialog({
        padding: 0,
        title: "修改密码",
        content: str,
        lock: true
      });
    }

    //提示弹窗
    function getsAlert(contentVal) {
      art.dialog({
        time:3,
        icon: 'error',
        content:contentVal
      });
    }

    /**
     *关闭弹窗
     */
    function usercloses() {
      var list = art.dialog.list;
      for (var i in list) {
        list[i].close();
      };
    }
  </script>



</head>
<body>
<div class="hea_top">
  <ul>
    <li><a href="javascript:updatePasswordPage('${sessionScope.user.id}');">修改密码</a><a href="javascript:dropoutUser();">切换用户</a></li>
    <li>
      <span>
        ${sessionScope.user.name}
      </span>
        您已成功登陆营销管理平台
    </li>
  </ul>
</div>
</body>
</html>