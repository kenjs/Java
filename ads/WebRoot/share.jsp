<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<head>
	<meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0"/>
	<meta charset="utf-8" />
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<title>APP分享</title>
	<script type="text/javascript" src="resource/jquery.js"></script>	
	<script type="text/javascript">
		$(function() {
			var url = document.URL;
			var para = url.split("?")[1];		
			$.ajax({
				url : 'decodeBase64',
				type : 'post',
				data : {'telephone': para},
				dataType : 'json',
				async : false,
				success : function(data) {
					var tel = data.content;
					$("#hidden_put").val(tel);
					var show = tel.substring(0,3) + "****" + tel.substring(7, tel.length);
					$("#p_tele").html(show);
				}
			});
		});
		function downLoad() {
			var recommendedMobileNum = $("input[type='tel']").val(),
					recommendMobileNum = $("#hidden_put").val();
			if (!checkPhoneRegx()) {
				return false;
			}

			var downloadPath = "downloadNewVersion?os=android&regFrom=3";

			if (recommendMobileNum == '15267198797') {
				downloadPath = "downloadNewVersion?os=android&regFrom=4";
			}

			$.ajax({
				url : 'downloadByShare',
				type : 'post',
				data : {'recommendedMobileNum': recommendedMobileNum,'recommendMobileNum':recommendMobileNum},
				dataType : 'json',
				async : true,
				success : function(data) {
					var code = data.result;
					if (code == 1) {
						$("input[type='tel']").val("");
						if (isWeixin()) {
							window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.cy.lorry";
						} else {
							window.location.href = downloadPath;
						}
					}
				}
			});
		}
		function clearMsg() {
			$('#errMsg').css('padding','0');
			$('#errMsg').html('');
		}

		function checkPhoneRegx() {
			var obj = $("input[type='tel']").val();
			if (obj == null || obj == '') {
				$('#errMsg').html('请输入手机号');
				$('#errMsg').css('padding','5px 0');
				return false;
			}
			var rex = /^((13[0-9])|(15[^4,\D])|(18[0-9])|(17[0-9])|(14[0-9]))\d{8}$/;
			if (!rex.test(obj)) {
				$('#errMsg').html('请输入正确的手机号码！');
				$('#errMsg').css('padding','5px 0');
				return false;
			}
			return true;
		}

		function isWeixin(){
			var ua = navigator.userAgent.toLowerCase();
			if(ua.match(/MicroMessenger/i)=="micromessenger") {
				return true;
			} else {
				return false;
			}
		}
	</script>
	<style>
		*{ margin:0px; padding:0px;}
		ul,li{ list-style-type:none;}
		body{ font-size:14px; font-family:"微软雅黑"; background:#FFF;}
		header{ height:180px; margin-top:15px; background:url(image/logo.jpg) no-repeat center;  font-size:16px;}
		figure{ text-indent:24px; line-height:24px; padding:20px; text-align:justify; }
		aside{ width:auto; padding:10px 20px;}
		aside dt input[type="tel"]{ width:100%; box-sizing:border-box; border:solid 1px #ccc; padding:8px; border-radius:3px;}
		aside dd input[type="button"]{ width:100%; border:none; margin:10px 0 0 0; background:#4376ce;
			border-radius:3px; height:34px; box-shadow:inset 0 -2px 0px #152a47;font-size:14px; font-family:"微软雅黑";
			vertical-align:top; color:#fff;}
		#errMsg{
			color: red;
		}
	</style>
  </head>
  	
  <body>
	  <article>
		  <header></header>
		  <figure>
			  您的朋友<span id="p_tele">137****7654</span>邀请您使用快到网，安全、货多、更新快，提前预约回程货，您的随身配货助手！一起来场说走就走的配货之旅吧~
		  </figure>
		  <aside>
			  <dt>
				  <input type="tel" placeholder="请输入手机号" pattern="[0-9]{11}" required onfocus="clearMsg()" onblur="checkPhoneRegx()">
				  <div id="errMsg"></div>
			  </dt>
			  <dd><input type="button" value="下载" style="cursor: pointer;" onclick="downLoad()"></dd>
		  </aside>
	  </article>
  	<input type="hidden" id="hidden_put" />
  </body>
</html>
