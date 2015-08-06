var html;
$(function() {
	html='<div id="accordion">' 
				+'<div class="menu1" title="易配货">'
					+'<ul>'
						+'<li><a href="../goodssourcecs/driver_goodssource_search" target="_blank">找货源</a></li>'
						+'<li><a id="order2" href="../tradecs/driver_unconfirmedtrade_list?order=2">待确认成交</a></li>'
						+'<li><a id="order3" href="../tradecs/driver_trade_list?order=3">成交管理</a></li>'
			        +'</ul>'
				+'</div>'
				+'<div class="menu1" title="会员管理">'
	               +'<ul>'
						+'<li><a id="order4" href="../partycs/driverdetail?order=4">我的档案</a></li>'
						+'<li><a id="order5" href="../accountcs/account_driverlist?order=5">消费记录</a></li>'
						+'<li><a id="order6" href="../operatorcs/edit_driverpwd?order=6">修改密码</a></li>'
	                +'</ul>'
				+'</div>' 
			+'</div>' 
		$("#left").html(html);
	 $("#accordion").ligerAccordion(
			    {
			        //height: 300
			    });

	 $("#order"+getid()).css("color","#ec6110");
	 $("#order"+getid()).css("font-weight","bold");
});
  function getid(){
    var idUrl= document.URL;
	var num=idUrl.indexOf("?") 
	idUrl=idUrl.substr(num+1);
	return idUrl.split("=")[1];
  
 }
document.write('<link href="../js/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />')
document.write('<link href="../css/webui/left.css" rel="stylesheet" type="text/css" />')
document.write('<script src="../js/ligerUI/js/core/base.js" type="text/javascript"></script>')
document.write('<script src="../js/ligerUI/js/plugins/ligerAccordion.js" type="text/javascript"></script> ')