/*头部开始*/
var html;
$(function() {
			html = '<div id=page><div class="chl-poster simple" id=header>'
								+ '<div id=site-nav><ul class=quick-menu><li id="lifirst"><div class="headerleft">'
								+ '<span>您好！欢迎光临传化物流!</span><span style="margin-left:10px;" id="loginName"><a href="../../logincs/login"></a></span></div>'
								+ '</li><li><a href="http://abc.tf56.com/Login.asp" target="_blank">切回旧版</a> </li>'
								+ '<li class="mych menu-item"><div class=menu>'
								+ '<a class=menu-hd href="#" target=_top rel=nofollow>其他网站<b></b></a>'
								+ '<div class=menu-bd><div class=menu-bd-panel><div style="text-align:left;">'
								+ '<div><a href="http://bus.tf56.com" target="_blank" rel=nofollow>货运班车</a></div>'
								+ '<div><a href="http://www.transfar56.com" target="_blank" rel=nofollow>企业门户网</a></div></div></div><s class="r"></s><s class=rt></s><s class=lt></s><s class=b></s><s class="b b2"></s><s class=rb></s><s class=lb></s>'
								+ '</div></div></li></ul></div></div></div>';

						$(".c-header").html(html);
						CH.Header.init();
	$.ajax( {
				url : "../logincs/getInit?random=" + Math.random(),
				dataType : "json",
				contentType : "application/json",
				type : 'get',
				success : function(data) {
					if (data.username_part == "" || data.username_part == null) {	
						$('#lifirst').after('<li><a href="../front/index.html" target="_blank" >首页<b></b></a></li><li><a href="../logincs/logout?random='+Math.random()+'">会员中心</a> </li>');
						$('#loginName').append('<a style="color:blue;font-weight:normal;" href="../logincs/login">登录</a>');
					} else {
						$('#lifirst').after('<li><a href="../front/index.html" target="_blank" >首页<b></b></a></li><li><a href="../logincs/index_main?random='+Math.random()+'">会员中心</a> </li>');
						$('#loginName').append(data.username_part+'&nbsp;&nbsp;<a style="color:blue;font-weight:normal;"href="../logincs/logout?random='+Math.random()+'">退出</a>');
//						$('#loginName').text(data.username_part);
//						$('#lifirst').after('<li><a href="../../logincs/logout?random='+Math.random()+'">退出</a> </li>'+'<li><a href="../../logincs/index_main?random='+Math.random()+'">会员中心</a> </li>');
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					//alert(XMLHttpRequest.status);
				// alert(XMLHttpRequest.readyState);
				//alert(textStatus); 
			}
			});//取session用户名

});

document.write('<link rel="stylesheet" type=text/css href="../css/webui/header.css"/>');
document.write('<script type=text/javascript src="../js/sys/webui/top.js"></script>');
/*头部结束*/