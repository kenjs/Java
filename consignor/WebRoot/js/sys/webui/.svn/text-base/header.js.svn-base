/*头部开始*/
var html;
function logout(obj){
	var url = $(obj).attr('url') + "logincs/login";
	$.ajax( {
		url : "../logincs/logout?random=" + Math.random(),
		dataType : "json",
		contentType : "application/json",
		type : 'get',
		success : function(data) {
			window.self.location = url;
		},
		error : function(XMLHttpRequest, textStatus, errorThrown) {
			
		}
	});
}
$(function() {
			html = '<div id=page><div class="chl-poster simple" id=header>'
								+ '<div id=site-nav><ul class=quick-menu><li id="lifirst"><div class="headerleft">'
								+ '<span>您好！欢迎光临传化物流</span><span id="loginName"></span></div>'
								+ '</li><li id="lisecond"><a url="" href="javascript:void(0)" onClick="logout(this)">退出</a></li><li><a href="http://abc.tf56.com/Login.asp" target="_blank">切回旧版</a> </li>'
								+ '<li class="mych menu-item"><div class=menu>'
								+ '<a class=menu-hd href="#" target=_top rel=nofollow>其他网站<b></b></a>'
								+ '<div class=menu-bd><div class=menu-bd-panel><div style="text-align:left;">'
								+ '<div><a href="http://bus.tf56.com" target="_blank" rel=nofollow>路港快线</a></div>'
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
					} else {
						$('#loginName').text(data.username_part);
						$('#lisecond').after('<li><a href="http://www.tf56.com" target="_blank">返回首页</a></li>');
					}
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					
				}
			});
	
		$.ajax({
			url:"../logincs/getMainIp?random="+Math.random(),
			dataType:"json",
			contentType: "application/json",
	      	type:'get',
	      	success:function(data){
				if(data.islogin=='yes'){
					$('#lisecond a').attr('url',data.siteip);
				}else{
					var url = data.siteip + "logincs/login";
					window.self.location = url;
				}
	      	},
	      	error:function(){
	      		//alert("读取配置文件失败");
	      	}
		});

});

document.write('<link rel="stylesheet" type=text/css href="../css/webui/header.css"/>');
document.write('<script type=text/javascript src="../js/sys/webui/top.js"></script>');
/*头部结束*/