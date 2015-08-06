/*头部开始*/
var html;
$(function() {
			html = '<div id="shortcut"><div class="w">'
								+'<ul class="fr lh">'
								+'<li class="fore1 ld" style="text-align:left;width:300px;margin-right:600px;border:0px solid red;" id="loginbar"> 您好！欢迎来到传化物流！<span id="username" style="width:200px;border:0px solid red;"></span><span id="logout"></span></li>'
								+'<li class="fore2 " data-widget="dropdown">'
								+'<dl>'
								+'<dt class="ld"><a href="../front/index.html" target="_self">返回首页<b></b></a></dt>'
								+'</dl></li></ul>'
								+'<span class="clr"></span>'
								+ '</div></div>';

						$(".header").html(html);
});
/*头部结束*/