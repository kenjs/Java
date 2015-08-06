
var html;
$(function() {
	html='<div id="accordion">'
				+'<div class="menu1" title="业务管理">'
					+'<ul>'
						+'<li><a id="order1" href="../waybillcs/waybill_list?order=1">我的运单</a></li>'
					+'</ul>'
				+'</div>'
				+'<div class="menu1" title="会员档案">'
			       +'<ul>'
						+'<li><a id="order2" href="../partycs/index_detail_contract?order=2">查看档案</a></li>'
						+'<li><a id="order3" href="../operatorcs/edit_driverpwd_contract?order=3">密码修改</a></li>'
						+'<li><a id="order4" href="../operatorcs/index_list_contract?order=4">操作员管理</a></li>'
			        +'</ul>'
				+'</div>'; 
		$("#left").html(html);
	 $("#accordion").ligerAccordion(
			    {
			        //height: 300
			    });
	 $("#order"+getid()).css("color","#ec6110");
	 $("#order"+getid()).css("font-weight","bold");
	 
	 var dm=$("div.menu1");
	    var a=$(dm).children("ul").children("li").children("a");
	    var i=0;
	    $(a).each(function(index,item){
	        if($(item).css("font-weight")=='700' || $(item).css("font-weight")=='bold'){
	           i=index;
	        }
	    });
	    var p=$(a[i]).parent().parent().parent();
	    var t=$(".l-accordion-header");
	    $(p).siblings(".menu1 l-accordion-content").css("display","none");
	    $(t[0]).children("div").eq(0).removeClass("l-accordion-toggle l-accordion-toggle-open").addClass("l-accordion-toggle l-accordion-toggle-close");
	    $(dm[0]).css("display","none");
	    $(p).prev().children("div").eq(0).removeClass("l-accordion-toggle l-accordion-toggle-close").addClass("l-accordion-toggle l-accordion-toggle-open");
	    $(p).css("display","block");
});
  function getid(){
    var idUrl= document.URL;
	var num=idUrl.indexOf("?"); 
	idUrl=idUrl.substr(num+1);
	return (idUrl.split("=")[1]).split("&")[0];
  
 }

document.write('<link href="../js/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />')
document.write('<link href="../css/webui/left.css" rel="stylesheet" type="text/css" />')
document.write('<script src="../js/ligerUI/js/core/base.js" type="text/javascript"></script>')
document.write('<script src="../js/ligerUI/js/plugins/ligerAccordion.js" type="text/javascript"></script> ')