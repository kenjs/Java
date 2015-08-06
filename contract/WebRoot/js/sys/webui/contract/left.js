
var html;
$(function() {
	html='<div id="accordion">'
				+'<div class="menu1" title="运营管理">'
					+'<ul>'
						+'<li><a id="order25" href="javascript:checkJurisdiction(25)">订单管理</a></li>'
						+'<li><a id="order1" href="javascript:checkJurisdiction(1)">接单管理</a></li>'
						+'<li><a id="order2" href="javascript:checkJurisdiction(2)">运单分派</a></li>'
						+'<li><a id="order3" href="javascript:checkJurisdiction(3)">运单管理</a></li>'
						+'<li><a id="order22" href="javascript:checkJurisdiction(22)">调度跟踪</a></li>'
						+'<li><a id="order23" href="javascript:checkJurisdiction(23)">运单修改</a></li>'
					+'</ul>'
				+'</div>'
				+'<div class="menu1" title="结算管理">'
					+'<ul>'
						+'<li><a id="order4" href="javascript:checkJurisdiction(4)">结算设置</a></li>'
						+'<li><a id="order5" href="javascript:checkJurisdiction(5)">费用录入</a></li>'
						+'<li><a id="order6" href="javascript:checkJurisdiction(6)">费用确认</a></li>'
						+'<li><a id="order7" href="javascript:checkJurisdiction(7)">应收结算单管理</a></li>'
						+'<li><a id="order8" href="javascript:checkJurisdiction(8)">应付结算单管理</a></li>'
						+'<li><a id="order9" href="javascript:checkJurisdiction(9)">应收发票核销</a></li>'
						+'<li><a id="order10" href="javascript:checkJurisdiction(10)">应付发票核销</a></li>'
					+'</ul>'
				+'</div>'
				+'<div class="menu1" title="报表查询">'
					+'<ul>'
						+'<li><a id="order11" href="javascript:checkJurisdiction(11)">托单明细表</a></li>'
						/*+'<li><a id="order12" href="javascript:checkJurisdiction(12)">应收明细表</a></li>'
						+'<li><a id="order13" href="javascript:checkJurisdiction(13)">应付明细表</a></li>'*/
					+'</ul>'
				+'</div>'		
	+'<div class="menu1" title="基础管理">'
		+'<ul>'
			+'<li><a id="order14" href="javascript:checkJurisdiction(14)">分包商管理</a></li>'
			+'<li><a id="order15" href="javascript:checkJurisdiction(15)">发货方管理</a></li>'
			+'<li><a id="order16" href="javascript:checkJurisdiction(16)">字典类管理</a></li>'
			+'<li><a id="order17" href="javascript:checkJurisdiction(17)">合同管理</a></li>'
			+'<li><a id="order18" href="javascript:checkJurisdiction(18)">城区距离管理</a></li>'
			+'<li><a id="order24" href="javascript:checkJurisdiction(24)">银行保理管理</a></li>'
        +'</ul>'
	+'</div>'
	+'<div class="menu1" title="会员档案">'
       +'<ul>'
			+'<li><a id="order19" href="javascript:checkJurisdiction(19)">查看档案</a></li>'
			+'<li><a id="order20" href="javascript:checkJurisdiction(20)">密码修改</a></li>'
			+'<li><a id="order21" href="javascript:checkJurisdiction(21)">操作员管理</a></li>'
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
  function checkJurisdiction(order){
	  //权限标志 分别代表对应菜单 bz[0]代表order1的菜单 值为0即无权限
	  var bz = [0,0,0,0,0,0,0,0,0,0,
	            0,0,0,0,0,0,0,0,0,0,
	            0,0,0,0,0];    
	  $.ajax( {
			url : "../logincs/getJsInit?random=" + Math.random(),
			dataType : "json",
			contentType : "application/json",
			type : 'get',
			async:false,
			success : function(data) {
				//alert(data.operator);
				if ("admin" ==data.operator) {	
					for(var j=0;j<25;j++){
						bz[j]++;
					}
				} else {
					var js = data.jsStr;
					var jsStr= js.split(",");
					 //alert(jsStr);
					  for(var i=0;i<jsStr.length;i++){
						  if(jsStr[i]=="项目经理"){
							  for(k=0;k<20;k++){
								  bz[k]++;
							  }
							  bz[21]++;
							  bz[22]++;
							  bz[23]++;
						  }else if(jsStr[i]=="基地领导"){
							  for(k=0;k<20;k++){
								  bz[k]++;
							  }
							  bz[21]++;
						  }else if(jsStr[i]=="驻场代表"){
							  var qx = [1,15,19,20];
							  for(k=0;k<qx.length;k++){
								  //数组比实际order 小1
								  var n = qx[k]-1;
								  bz[n]++;
							  }
						  }else if(jsStr[i]=="客服专员"){
							  var qx = [1,2,3,22,5,6,7,8,11,14,15,19,20];
							  for(k=0;k<qx.length;k++){
								  var n = qx[k]-1;
								  bz[n]++;
							  }
						  }else if(jsStr[i]=="客服主管"){
							  for(k=0;k<20;k++){
								  bz[k]++;
							  }
							  bz[21]++;
						  }else if(jsStr[i]=="销售经理"){
							  var qx = [9,10,11,14,15,17,18,19,20];
							  for(k=0;k<qx.length;k++){
								  var n = qx[k]-1;
								  bz[n]++;
							  }
						  }else if(jsStr[i]=="财务"){
							  var qx = [7,8,9,10,11,17,19,20];
							  for(k=0;k<qx.length;k++){
								  var n = qx[k];
								  bz[n]++;
							  }
						  }
					  }
				}
			},
			error : function(XMLHttpRequest, textStatus, errorThrown) {
				//session已失效 返回登陆页面
				bz = [1,1,1,1,1,1,1,1,1,1,
				      1,1,1,1,1,1,1,1,1,1,
				      1,1,1,1,1];
			    //alert(XMLHttpRequest.status);
			   //alert(XMLHttpRequest.readyState);
			   //alert(textStatus); 
		}
		});//取用户角色
	  //alert(bz);
	  switch(order){
		  case 1:
			  if(bz[0]<1){
			  		ymPrompt.alert('对不起！您没有权限！');
			  		return;
			  	}
				window.location.href="../waybillcs/waybillList?order=1";
			    break;
		  case 2:
			  	if(bz[1]<1){
			  		ymPrompt.alert('对不起！您没有权限！');
			  		return;
			  	}
			  	window.location.href="../waybilldistributioncs/waybilldistributionlist?order=2";
			    break;
		  case 3:
			  	if(bz[2]<1){
			  		ymPrompt.alert('对不起！您没有权限！');
			  		return;
			  	}
				window.location.href="../waybillcs/waybillManager?order=3";
			    break;
		  case 4:
			  	if(bz[3]<1){
			  		ymPrompt.alert('对不起！您没有权限！');
			  		return;
			  	}
				window.location.href="../settlecs/settle_set?order=4";
			    break;
		  case 5:
			  	if(bz[4]<1){
			  		ymPrompt.alert('对不起！您没有权限！');
			  		return;
			  	}
				window.location.href="../waybillamountcs/waybillamountList?order=5";
			    break;
		  case 6:
			  if(bz[5]<1){
			  		ymPrompt.alert('对不起！您没有权限！');
			  		return;
			  	}
				window.location.href="../waybillamountcs/waybillamountconfirmlist?order=6";
			    break;
		  case 7:
			  	if(bz[6]<1){
			  		ymPrompt.alert('对不起！您没有权限！');
			  		return;
			  	}
				window.location.href="../settlebillcs/inList?order=7";
			    break;
		  case 8:
			  	if(bz[7]<1){
			  		ymPrompt.alert('对不起！您没有权限！');
			  		return;
			  	}
				window.location.href="../settlebillcs/outList?order=8";
			    break;
		  case 9:
			  	if(bz[8]<1){
			  		ymPrompt.alert('对不起！您没有权限！');
			  		return;
			  	}
				window.location.href="../settlebillcs/verificationReceivable?order=9";
			    break;
		  case 10:
			  	if(bz[9]<1){
			  		ymPrompt.alert('对不起！您没有权限！');
			  		return;
			  	}
				window.location.href="../settlebillcs/verificationPayable?order=10";
			    break;
		  case 11:
			  	if(bz[10]<1){
			  		ymPrompt.alert('对不起！您没有权限！');
			  		return;
			  	}
				window.location.href="../reportquerycs/waybillDetail?order=11";
			    break;
		  case 12:
			  	if(bz[11]<1){
			  		ymPrompt.alert('对不起！您没有权限！');
			  		return;
			  	}
				window.location.href="../reportquerycs/receivableDetail?order=12";
			    break;
		  case 13:
			  	if(bz[12]<1){
			  		ymPrompt.alert('对不起！您没有权限！');
			  		return;
			  	}
				window.location.href="../reportquerycs/payableDetail?order=13";
			    break;
		  case 14:
			  	if(bz[13]<1){
			  		ymPrompt.alert('对不起！您没有权限！');
			  		return;
			  	}
				window.location.href="../subcontractorcs/subcontractorList?order=14";
			    break;
		  case 15:
			  	if(bz[14]<1){
			  		ymPrompt.alert('对不起！您没有权限！');
			  		return;
			  	}
				window.location.href="../consignorcs/consignor_list?order=15";
			    break;
		  case 16:
			  	if(bz[15]<1){
			  		ymPrompt.alert('对不起！您没有权限！');
			  		return;
			  	}
				window.location.href="../contractdictionarycs/contractdictionary_list?order=16";
			    break;
		  case 17:
			  	if(bz[16]<1){
			  		ymPrompt.alert('对不起！您没有权限！');
			  		return;
			  	}
				window.location.href="../pactcs/pactsList?order=17";
			    break;
		  case 18:
			  	if(bz[17]<1){
			  		ymPrompt.alert('对不起！您没有权限！');
			  		return;
			  	}
				window.location.href="../citydistancecs/list?order=18";
			    break;
		  case 19:
			  	if(bz[18]<1){
			  		ymPrompt.alert('对不起！您没有权限！');
			  		return;
			  	}
				window.location.href="../partycs/index_detail_contract?order=19";
			    break;
		  case 20:
			  	if(bz[19]<1){
			  		ymPrompt.alert('对不起！您没有权限！');
			  		return;
			  	}
				window.location.href="../operatorcs/edit_driverpwd_contract?order=20";
			    break;
		  case 21:
			  	if(bz[20]<1){
			  		ymPrompt.alert('对不起！您没有权限！');
			  		return;
			  	}
				window.location.href="../operatorcs/index_list_contract?order=21";
			    break;
		  case 22:
			  	if(bz[21]<1){
			  		ymPrompt.alert('对不起！您没有权限！');
			  		return;
			  	}
				window.location.href="../dispatchtrackcs/dispatchtrack_list?order=22";
			    break;
		  case 23:
			  	if(bz[22]<1){
			  		ymPrompt.alert('对不起！您没有权限！');
			  		return;
			  	}
				window.location.href="../waybillcs/waybillupdate_list?order=23";
			    break;
		  case 24:
			  	if(bz[23]<1){
			  		ymPrompt.alert('对不起！您没有权限！');
			  		return;
			  	}
				window.location.href="../subcontractorcs/factoringList?order=24";
			    break;
		case 25:
			  	if(bz[24]<1){
			  		ymPrompt.alert('对不起！您没有权限！');
			  		return;
			  	}
				window.location.href="../outwaybillcs/orderManage?order=25";
			    break;
	  }
	  
  }
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