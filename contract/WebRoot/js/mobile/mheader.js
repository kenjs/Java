/*头部开始*/
var html;
$(function() {
		html = '<div id="header"><ul>'
								+ '<li id="lione"><a href="mobile_goodsourceslist.html" id="findgoodssource">找货源</a></li>'
								+ '<li id="litwo"><a href="mobile_driver_detail.html" id="personaldata">个人资料</a></li>'
								+ '<li id="lithree"></li>'
								+ '<li id="lifour"><a href="#"><span id="loginORlogout"></span></a></li>'
								+ '</ul></div>';
		$(".header").html(html);
		
		$.ajax( {
			url : "../mfrontcs/getInit?random=" + Math.random(),
			dataType : "json",
			contentType : "application/json",
			type : 'get',
			success : function(data) {
				if (data.username_part == "" || data.username_part == null) {	
					$('#loginORlogout').text("登录").click(function(){
						window.location='../mfrontcs/mlogout?random='+Math.random();
					}).css("padding-left","35%");
					$('#lifour').css(
							{
								"background":"url(../imgs/mobile/login1-icon.png) no-repeat 15% center"
							}
							);
				} else {
					$('#loginORlogout').text("退出").click(function(){
						window.location='../mfrontcs/mlogout?random='+Math.random();
					}).css("padding-left","35%");
					$('#lifour').css(
							{
								"background":"url(../imgs/mobile/login2-icon.png) no-repeat 15% center"
							}
							);
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				//alert(XMLHttpRequest.status);
			// alert(XMLHttpRequest.readyState);
			//alert(textStatus); 
		}
		});//取session用户名		
						
});
/*头部结束*/