$(function(){
	f_hgrid_ini();// 加载列表
	loadQuery();  // 查询数据
});

function loadQuery(){
	 $("#pagecode").val("1");

	 var params="";
	 f_hgrid_json(params);
}

function f_hgrid_ini() {//初始化表格
	var pagerow=10;  //参数为每页行数
	f_hgrid_create(pagerow); 	
	//注意：此处的列宽和为1000px
	var txt='<tr>' +
			'<th width="120px" class="tdleft" style="height:30px;padding-left:0px;">运单编号</th>' +
			'<th width="40px" class="tdleft" style="height:30px;padding-left:0px;">状态</th>' +
			'<th width="90px" class="tdleft" style="height:30px;padding-left:0px;">客户单号</th>' +
			'<th width="50px" class="tdleft" style="height:30px;padding-left:0px;text-align:left;">紧急度</th>' +
			'<th width="70px" class="tdleft" style="height:30px;padding-left:0px;">托运日期</th>' +
			'<th width="50px" class="tdcenter" style="height:30px;padding-left:0px;">录单人</th>' +
			'<th width="150px" class="tdleft" style="height:30px;padding-left:0px;">发货方</th>' +
			'<th width="90px" class="tdleft" style="height:30px;padding-left:0px;">收货地址</th>' +
			'<th width="80px" class="tdleft" style="height:30px;padding-left:0px;">分包商</th>' +
			'<th width="70px" class="tdleft" style="height:30px;padding-left:0px;">车辆</th>' +
			'<th width="70px" class="tdleft" style="height:30px;padding-left:0px;">司机</th>' +
			'<th width="115px" class="tdleft" style="height:30px;padding-left:0px;">调度单</th>' +
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
	 	url: "../waybillstowagecs/waybillStowageList", 
	 	contentType: 'application/x-www-form-urlencoded; charset=UTF-8',
	 	type:'post',	
	 	dataType:'json', 
	 	data:param, //参数  
	   	success:function(data){//回传函数
	 		$("#loading").hide();
	 		data=f_hgrid_setmsg(data); //设置总 记录数,页信息等
	 	}
	  }); 
}