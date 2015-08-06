var html;
$(function() {
	html='<div id="accordion">' 
				+'<div class="menu1" title="基础管理">'
					+'<ul>'
						+'<li><a id="order1" href="../subcontractorcs/subcontractorList">分包商管理</a></li>'
						+'<li><a id="order2" href="../consigneeconsignoraddresscs/consignor_list?oreder=2">发货方管理</a></li>'
						+'<li><a id="order3" href="../contractdictionarycs/contractdictionary_list?order=3">字典类管理</a></li>'
						+'<li><a id="order4" href="../pactcs/pactsList">合同管理</a></li>'
			        +'</ul>'
				+'</div>'
				+'<div class="menu1" title="会员管理">'
	               +'<ul>'
						+'<li><a id="order5" href="javascript:void(0)">会员信息</a></li>'
						+'<li><a id="order6" href="javascript:void(0)">修改密码</a></li>'
						+'<li><a id="order7" href="../operatorcs/edit_driverpwd?order=7">操作员管理</a></li>'
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
	var num=idUrl.indexOf("?"); 
	idUrl=idUrl.substr(num+1);
	return idUrl.split("=")[1];
  
 }
document.write('<link href="../js/ligerUI/skins/Aqua/css/ligerui-all.css" rel="stylesheet" type="text/css" />')
document.write('<link href="../css/webui/left.css" rel="stylesheet" type="text/css" />')
document.write('<script src="../js/ligerUI/js/core/base.js" type="text/javascript"></script>')
document.write('<script src="../js/ligerUI/js/plugins/ligerAccordion.js" type="text/javascript"></script> ')