var html;
$(function() {
	html='<div id="accordion">' 
				+'<div class="menu1" title="易配货">'
					+'<ul>'
						+'<li><a id="order1" href="../goodssourcecs/index_fastpub?order=1" target="_self">发布货源</a></li>'
						+'<li><a id="order2" href="../goodssourcecs/index_goodssource_onscreen?order=2" target="_self">在屏货源</a></li>'
						+'<li><a id="order3" href="../tradecs/vtrade?order=3" target="_self">成交管理</a></li>'
						+'<li><a id="order4" href="../goodssourcecs/index_3pl_parkcarin?order=4" target="_self">网上车场</a></li>'
						+'<li><a id="order5" href="../goodssourcecs/goodssource_3pl_list?order=5" target="_blank">货源大厅</a></li>'
						+'<li><a id="order6" href="../partycs/affirmprotocol?order=6" target="_self">三证查询</a></li>'
						
			        +'</ul>'
				+'</div>'
//				+'<div class="menu1" title="零担货">'
//	               +'<ul>'
//						+'<li><a id="order7" href="../tradecs/index_consignee_list?order=7" target="_self">我要收货</a></li>'
//						+'<li><a id="order8" href="../tradecs/index_consignor_list?order=8" target="_self">我要发货</a></li>'
//						+'<li><a id="order9" href="../consignorconsigneecs/index_list?order=9" target="_self">收发人管理</a></li>'
//						+'<li><a id="order10" href="../tradecs/index_statistica_trade_list?order=10" target="_self">收货统计表</a></li>'
//	                +'</ul>'
//				+'</div>' 
				+'<div class="menu1" title="会员档案">'
	               +'<ul>'
						+'<li><a id="order11" href="../partycs/index_detail?order=11" target="_self">查看档案</a></li>'
						+'<li><a id="order12" href="../operatorcs/index_edit?order=12" target="_self">密码修改</a></li>'
						+'<li><a id="order13" href="../accountcs/account_list?order=13" target="_self">账户管理</a></li>'
						+'<li><a id="order14" href="../operatorcs/index_list?order=14" target="_self">操作员管理</a></li>'
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