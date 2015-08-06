function getSts(){
	var url=document.URL;
	var num = url.indexOf("?");
	idUrl = decodeURI(url.substr(num+1));
	return idUrl;
}

//装车后停留原始界面并刷新
function loadRefresh(){
	var url = getSts().split("=");
	//只有装车时才取此参数
	if(url.length > 2){
		var sts = url[2];
		var stss = sts.split(",");
		if(stss.length > 1){
			sts = stss[0];
			$("#sts").val(stss[1]);
		}
		//去掉默认选项卡
		var _rel = $(".current").attr("rel");
		$("#"+_rel).addClass("fn-hide");
		$(".current").removeClass("current");
		//显示选中历史选项卡
		$("#"+sts).removeClass("fn-hide");
		var _layerClass = $(".tab-change-title");
		_layerClass.find("li").each(function(){
			var _this = $(this);
			var _rel = _this.attr("rel");
			if(_rel==sts){
				_this.addClass("current");
			}
		});
	}
}

$(function(){
	loadRefresh();//装车后停留原始界面
	tabChange("tab-change-title");//选项卡效果
	setCount("tab-change-title");//选项卡总记录数设置
	f_hgrid_ini();// 加载列表
	loadQuery();  // 查询数据
	$(".page_nav").css("margin-top","20px");
	$(".page_nav").css("text-align","right");
	//移出翻页层
	$(".page_nav").next().remove();
	$(".page_nav").insertAfter("#hgrid_div");
	$("#hgrid_div .page_nav").remove();
	$(".page_nav").css("text-align","right");
	$(".page_nav").css("margin-top","10px");
});

function setCount(layerClass){
	//界面调用，动态传入外层标签class样式值
	var _layerClass = $("."+layerClass);
	//找到所有<li>元素，单击事件
	_layerClass.find("li").each(function(){
		var _this = $(this);
		var _rel = _this.attr("rel");
		var param = "status="+_rel+"&fromQuery="+"waybillmanager";
		if(_rel != '已签收'){
			$.ajax({
			 	url: "../waybillcs/selectCount", 
			 	contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
			 	type:'post',	
			 	dataType:"", 
			 	data:param, //参数
			   	success:function(data){//回传函数
					if(data!= null){
						var s = _this.text().split("(")[0];
						_this.text(s + "("+data+")");
					}
				}
			});
		}
	});
}

function loadQuery(){
	 setCount("tab-change-title");//选项卡总记录数设置
	 $("#pagecode").val("1");
	 var sts = $(".current").attr("rel");
	 var waybillnumber=trim($("#"+sts+" #waybillnumber").val());
	 var systemdispatchnumber=trim($("#"+sts+" #systemdispatchnumber").val());
	 var clientnumber = trim($("#"+sts+" #clientnumber").val());
	 var frompartyname=trim($("#"+sts+" #frompartyname").val());
	 var topartyname=trim($("#"+sts+" #topartyname").val());
	 var fromdate=trim($("#"+sts+" #fromdate").val());
	 var todate=trim($("#"+sts+" #todate").val());
	 //全部运单按照下拉框值查询
	 if(sts=="全部运单"){
	 	sts = $("#sts").val();
	 }
	 var params="waybillnumber="+waybillnumber+"&systemdispatchnumber="+systemdispatchnumber+"&clientnumber="+clientnumber+
	 	"&frompartyname="+frompartyname+"&topartyname="+topartyname+"&fromdate="+fromdate+"&todate="+todate+"&status="+sts+"&fromQuery="+"waybillmanager";
	 f_hgrid_json(params);
}
	
function f_hgrid_ini() {//初始化表格
	var pagerow=10;  //参数为每页行数
	f_hgrid_create(pagerow); 	
	//注意：此处的列宽和为1000px
	var txt='<tr>' +
			'<th width="120px" class="tdleft" style="height:30px;padding-left:10px;">运单编号</th>' +
			'<th width="40px" class="tdleft" style="height:30px;padding-left:10px;">状态</th>' +
			'<th width="90px" class="tdleft" style="height:30px;padding-left:10px;">客户单号</th>' +
			'<th width="50px" class="tdleft" style="height:30px;padding-left:10px;text-align:left;">紧急度</th>' +
			'<th width="70px" class="tdleft" style="height:30px;padding-left:10px;">托运日期</th>' +
			'<th width="50px" class="tdcenter" style="height:30px;padding-left:10px;">录单人</th>' +
			'<th width="150px" class="tdleft" style="height:30px;padding-left:10px;">发货方</th>' +
			'<th width="90px" class="tdleft" style="height:30px;padding-left:10px;">收货地址</th>' +
			'<th width="80px" class="tdleft" style="height:30px;padding-left:10px;">分包商</th>' +
			'<th width="70px" class="tdleft" style="height:30px;padding-left:10px;">车辆</th>' +
			'<th width="70px" class="tdleft" style="height:30px;padding-left:10px;">司机</th>' +
			'<th width="115px" class="tdleft" style="height:30px;padding-left:10px;">调度单</th>' +
			'</tr>';
	for(var i =0;i<pagerow+1;i++){ //生成不见的空行
		txt=txt+'<tr id="tr'+i+'" class="ctr"';
	 	txt=txt+'></tr>';
	}
	$("#hgrid").empty().append(txt);
	var txt='<tr>' +
	'<th scope="col" style="height:30px;padding-left:0px;width:70px" class="tdcenter">操作</th>' +
	'</tr>';
	for(var i =0;i<pagerow;i++){ //生成不见的空行
		txt=txt+'<tr id="czTr'+i+'" class="ctr'+i+'" >';
	 	txt=txt+'</tr>';
	}
	$("#operation").empty().append(txt);
 	$('.ctr').hover(function(){
 		$(this).css("background-color","#fffddd");
 	},function(){
 		var indexvalue=$(this).index();
 		$(this).css("background-color","#fff");
 	});
}	

function f_hgrid_json(param) {//刷新hGrid数据
		//vparam不包括页码和每页行数
		param=f_hgrid_getparam(param); //得到全部参数
	 	$.ajax({
	 	url: "../waybillcs/waybillcManager",
	 	contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	 	type:'post',	
	 	dataType:'json', 
	 	data:param, //参数  
	   	success:function(data){//回传函数
	 		$("#loading").hide();
	 		data=f_hgrid_setmsg(data); //设置总 记录数,页信息等
	 		var sts = $(".current").attr("rel");
			var txt,i;
			for(i=0;i<data.length;i++){ //展现返回的表格数据
				//alert(data[i].status);
				var option = '',checkStr='';
				if(data[i].status == '待配载'){
					option = option + '<span style="width:2px; margin-left: 3px;color:#1560ea;">|</span><a id="btnStowage" href="javascript:void(0);" onclick=btnStowage("'+data[i].waybillid+'","'+data[i].topartyname+'"); style="margin-left:3px;color:#1560ea;">配载</a>';
					if(sts == "待配载"){
						checkStr = '<input type="checkbox" value=\"'+data[i].waybillid+'\" name="checked" /><input type="hidden" value="'+data[i].topartyid+'" name="topartyid"/>';
					}
				}else if(data[i].status == '已配载'){
					option = option + '<span style="width:2px; margin-left: 3px;color:#1560ea;">|</span><a id="btnLoad" href="javascript:void(0);" onclick=btnLoad("'+data[i].waybillid+'"); style="margin-left:3px;color:#1560ea;">装车</a>';
				}else if(data[i].status == '已装车'){
					option = option + '<span style="width:2px; margin-left: 3px;color:#1560ea;">|</span><a id="btnStart" href="javascript:void(0);" onclick=btnStart("'+data[i].waybillid+'"); style="margin-left:3px;color:#1560ea;">发车</a>';
				}else if(data[i].status == '已发车'){
					option = option + '<span style="width:2px; margin-left: 3px;color:#1560ea;">|</span><a id="btnArrive" href="javascript:void(0);" onclick=btnArrive("'+data[i].waybillid+'"); style="margin-left:3px;color:#1560ea;">到车</a>';
				}else if(data[i].status == '已到车'){
					option = option + '<span style="width:2px; margin-left: 3px;color:#1560ea;">|</span><a id="btnSign" href=\"javascript:void(0);\" onclick=btnSign("'+data[i].waybillid+'"); style="margin-left:3px;color:#1560ea;">签收</a>';
				}
				txt='<td class="tdleft" style="color:#1560ea;cursor:pointer">'+checkStr+'<a href="#" onclick=btnDetail("'+data[i].waybillid+'") class="fn-cBlue">'+data[i].waybillnumber+'</a></td>'+	
					'<td class="tdleft">'+data[i].status+'</td>'+ 
				 	'<td class="tdleft">'+ data[i].clientnumber+ 
					'<td class="tdleft">'+data[i].urgencydegree+'</td>'+  
					'<td class="tdleft">'+ format(data[i].consigndate)+ 
				 	'<td class="tdleft">'+ data[i].inputman+ 
				 	'<td class="tdleft">'+ data[i].frompartyname+ 
				 	'<td class="tdleft">'+ data[i].consigneecity + data[i].consigneeregion+
				 	'<td class="tdleft topartyname">'+ data[i].topartyname+ 
				 	'<td class="tdleft carplatenumber">'+ data[i].carplatenumber+
				 	'<td class="tdleft drivername">'+ data[i].drivername+
				 	'<td class="tdleft systemdispatchnumber"><a href="javascript:void(0);" onclick=detailShow("'+data[i].waybillstowageid +'") class="fn-cBlue">'+ data[i].systemdispatchnumber+'</a></td>';
				 	var txt1='<td style="text-align:center;color:#444444;"><a id="btndetail" href="javascript:void(0);" onclick=btnDetail("'+data[i].waybillid+'") style="margin-left:10px;color:#1560ea;">详情</a>'+
				 	option + '</td>';
			 	$('#tr'+i).empty().append(txt);
			 	$('#czTr'+i).empty().append(txt1);	      
		     }
			 var len = data.length;
			 if(len==0){
				 $("#recordcount").val("0");
			 }
			 var pagerow=$("#pagerow").val(); //每页行数
			 if(len > 0 && sts == "待配载"){
				txt = '<td colspan="12"><input type="checkbox" onclick=check(this); name="checked"/>&nbsp;全选&nbsp;<input class="btn_sel" onclick="stowages();" type="button" value="批量配载"/><td>';
				$('#tr'+ i++).empty().append(txt);
				$('#czTr'+len).empty();
				len++;
			 }
			 for(var y = len;y<pagerow+1;y++){
			 	$('#tr'+y).empty();
			 	$('#czTr'+y).empty();
			 }
          },
          error : function(XMLHttpRequest, textStatus, errorThrown) {
				//session已失效 返回登陆页面
        	  window.location.href="../waybillcs/waybillManager?order=3";
			    //alert(XMLHttpRequest.status);
			   //alert(XMLHttpRequest.readyState);
			   //alert(textStatus); 
		}
	  }); 
}

/**
 * 调度单详情
 * @param waybillstowageid
 * @return
 */
function detailShow(waybillstowageid){
	var url = '../dispatchtrackcs/waybillstowage_detail?waybillstowageid='+waybillstowageid;
	window.open(url, "_blank");
}

function check(obj){
	if($(obj).attr("checked")){
		$("#hgrid").find("input[name=checked]").attr("checked","checked");
	}
	if($(obj).is(":checked")==false){
		$('#hgrid tr input[name=checked]').removeAttr("checked");
	}
}
//批量配载
function stowages(){
	var check=$("#hgrid input[name=checked]");
	var topartyid=$("#hgrid input[name=topartyid]");
	var ary = "",toparty="";
	for(var i=0;i<check.length-1;i++){
		if($(check[i]).is(":checked")){
			if(toparty == ""){
				toparty = $(topartyid[i]).val();
			}else if($(topartyid[i]).val() != toparty){
				ymPrompt.alert("不同分包商不允许配载同一辆车！");
				return;
			}
			if(ary==""){
				ary = $(check[i]).val();
			}else{
				ary = ary +"#"+$(check[i]).val();
			}
		}
	}
	if(ary.length==0){
		ymPrompt.alert("请选择运单");
		return;
	}else{
		 btnStowage(ary);
	}
}

//配载
function btnStowage(id,topartyname){
	var height=510;
	ymPrompt.win({message:'../waybillcs/waybillStowageAdd?waybillid='+id+'&topartyname='+topartyname,width:760,height:height,fixPosition:true,dragOut:false,handler:callBack,title:'运单配载',iframe:true});
}

//弹出框关闭回调函数
function callBack(data){
	if(data=="ok"){
		loadQuery();
	}
}

//装车确认
function btnLoad(id){
	var sts = $(".current").attr("rel");
	//全部运单按照下拉框值查询
	if(sts=="全部运单"){
		sts = sts +","+ $("#sts").val();
	}
	window.location.href="../waybillcs/waybillLoadSure?waybillid="+id+"&status="+sts;
}

//发车确认
function btnStart(id){
	var height=260;
	ymPrompt.win({message:'../waybillcs/waybillStartSure?waybillid='+id,width:320,height:height,fixPosition:true,dragOut:false,handler:callBack,title:'发车确认',iframe:true});
}

//到车确认
function btnArrive(id){
	var height=260;
	ymPrompt.win({message:'../waybillcs/waybillArriveSure?waybillid='+id,width:320,height:height,fixPosition:true,dragOut:false,handler:callBack,title:'到车确认',iframe:true});
}
	
//签收
function btnSign(id){
	var height=300;
	ymPrompt.win({message:'../waybillcs/waybillSignAdd?waybillid='+id,width:550,height:height,fixPosition:true,dragOut:false,handler:callBack,title:'运单签收',iframe:true});
}
//详情
function btnDetail(id){
	var url = "../waybillcs/waybill_detail?order=3&waybillid="+id;
	window.open(url,"_blank");
}

function trim(str){ //删除左右两端的空格
	   return str.replace(/(^\s*)|(\s*$)/g, "");
}

function format(date){
	var date = date.toString();
	var index = date.indexOf(" ");
	return date.substr(0,index);
}

function f_hgrid_setmsg(data){//data为json数据 
	//var recordcount=data['list'][0].map[0]['entry'][0]['string'][1]; //总记录数
    $(".myjl").remove();
    //	var recordcount=data[0].recordcount;//总记录数
	if(data==null||data=='undefined'){	
		$("#hgridDiv").css("overflow-x","hidden");
		$('<div class="myjl" style="font-size:18px;text-align:center;padding-top:20px;font-family:微软雅黑;">没有记录</div>').insertBefore(".page_nav");
		$(".page_nav").hide();
		return '';
		}
	else{
	var recordcount=data.Count;//总记录数
	if(recordcount==0||recordcount==''){
		$("#hgridDiv").css("overflow-x","hidden");
		$('<div class="myjl" style="font-size:18px;text-align:center;padding-top:20px;font-family:微软雅黑;">没有记录</div>').insertBefore(".page_nav");
		$(".page_nav").hide();
		return '';
	}
	else{
	$("#hgridDiv").css("overflow-x","scroll");
	$(".page_nav").show();	
 	$("#recordcount").val(recordcount);//设置总记录数
	var pagecode=$("#pagecode").val(); //当前页
	var pagerow=$("#pagerow").val(); //每页行数
	var pagecount= parseInt((recordcount-1)/pagerow+1); //总页数
	$("#pageinfo").text("共"+recordcount+"条 第"+pagecode+"/"+pagecount.toString()+"页");
	var pagemin=pagecode-4; //第n页最小
	if (pagemin<1) pagemin=1;
	var pagemax=pagemin+8;//最大
	if (pagemax>pagecount) pagemax=pagecount;
	if (pagemax<(pagemin+8) && pagemin>1) pagemin=pagemin-1;
	if (pagemax<(pagemin+8) && pagemin>1) pagemin=pagemin-1;
	if (pagemax<(pagemin+8) && pagemin>1) pagemin=pagemin-1;
	if (pagemax<(pagemin+8) && pagemin>1) pagemin=pagemin-1;
	//$("strong").remove();
	for (var i=1;i<=9;i++){ //设置4+1+4个对象
		if (pagemin<=pagemax) {
			$("#page"+i+" a").text(pagemin);
			$("#page"+i).show();
			if (pagemin==pagecode) $("#page"+i+" a").wrapInner("<strong></strong>");			
			}
		else 
			$("#page"+i).hide();
		pagemin=pagemin+1;
	}
	}
	}
	return data.Data; 
}
