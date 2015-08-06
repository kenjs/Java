function f_hgrid_ini(){//初始化表格
	var pagerow=10;  //参数为每页行数
	f_hgrid_create(pagerow); 	
	//注意：此处的列宽和为820px
	var txt='<tr>' +
	'<th scope="col" width="30px" class="tdleft" style="text-align: left;height:30px;padding-left:3px;"></th>'+
	'<th scope="col" width="100px" class="tdleft" style="text-align: left;height:30px;padding-left:3px;">运单编号</th>' +
	'<th scope="col" width="90px" class="tdleft" style="text-align: left;height:30px;padding-left:3px;">紧急程度</th>' +
	'<th scope="col" width="90px" class="tdleft" style="text-align: left;height:30px;padding-left:3px;">客户单号</th>' +
	'<th scope="col" width="80px" class="tdleft" style="text-align: left;height:30px;padding-left:3px;">托运日期</th>' +
	'<th scope="col" width="100px" class="tdleft" style="text-align: left;height:30px;padding-left:3px;">发货方</th>' +
	'<th scope="col" width="100px" class="tdleft" style="text-align: left;height:30px;padding-left:3px;">发货地</th>' +
	'<th scope="col" width="100px" class="tdleft" style="text-align: left;height:30px;padding-left:3px;">收货地</th>' +
	'<th scope="col" width="100px" class="tdleft" style="text-align: left;height:30px;padding-left: 0px;">货物类型</th>' +
	'<th scope="col" width="110px" class="tdleft" style="text-align: left;height:30px;padding-left: 5px;">操作</th>' +
	'</tr>';
	for(var i =0;i<pagerow;i++){ //生成不见的空行
		txt=txt+'<tr id="tr'+i+'" class="ctr'+i+'" onmouseover="f_onmouseover('+i+')" onmouseout="f_onmouseout('+i+')">';
	 	txt=txt+'</tr>';
	 }
	$("#hgrid").empty().append(txt);
}

function f_onmouseover(i){
	$('.ctr'+i).css("background-color","#fffddd");
}
function f_onmouseout(i){
	$('.ctr'+i).css("background-color","white");
}
function f_hgrid_json(param) {//刷新hGrid数据
	//vparam不包括页码和每页行数
	param=f_hgrid_getparam(param); //得到全部参数	
 	$.ajax({
 	url: "../waybilldistributioncs/waybillList",   
 	type:'post',	
 	dataType:'json', 
 	data:param, //参数     	         
   	success:function(data){//回传函数
 		$('#loading').hide();
 		data=f_hgrid_setmsg(data); //设置总记录数,页信息等
		var txt;
		if(!data==""){
			$("#organization2_org").val(data[0].frompartyname);
			$("#frompartyid").val(data[0].frompartyid);
		}
		if(data.length==0){
			$("#left_div").hide();
			$("#right_div").hide();
			$('<div class="myjl" style="font-size:18px;text-align:center;padding-top:20px;font-family:微软雅黑;">没有记录</div>').insertBefore("#right_div");
		}
		for(var i =0;i<data.length;i++){ //展现返回的表格数据
			txt='<td class="tdleft"><input type="checkbox" id="wayBills" name="wayBills" value="'+data[i].waybillid+'" /></td>'+
				'<td class="tdleft"><a href="#" onclick=btnDetail("'+data[i].waybillid+'") class="fn-cBlue">'+data[i].waybillnumber+'</a></td>'+
				'<td class="tdleft">'+data[i].urgencydegree+'</td>'+
				'<td class="tdleft">'+data[i].clientnumber+'</td>'+
				'<td class="tdleft">'+format(data[i].consigndate)+'</td>'+
				'<td class="tdleft">'+data[i].frompartyname+'</td>'+
				'<td class="tdleft">'+data[i].consignorcity+data[i].consignorregion+'</td>'+
				'<td class="tdleft">'+data[i].consigneecity+data[i].consigneeregion+'</td>'+
				'<td class="tdleft">'+data[i].goodstype+'</td>'+
				'<td style="text-align:left;color:#444444;"><a id="btndetail" href="javascript:void(0);" onclick=btnDetail("'+data[i].waybillid+'") style="margin-left:5px;">详情</a>'+
				'&nbsp;<a href="javascript:void(0);" style="margin-left:5px;" onClick=btnDistribution("'+data[i].waybillid+'")>分派</a></td>';
			$('#tr'+i).empty().append(txt);
		}		
		var pagerow=$("#pagerow").val(); //每页行数
		 for(var i =data.length;i<pagerow;i++){
			 $('#tr'+i).empty();
		 }
      }
  }); 
}

function selectWaybillList(){
	$("#left_div").hide();
	$("#right_div").hide();
	f_hgrid_ini();
	$("#pagecode").val("1");
	var clientnumber = trim($("#clientnumber").val());
	var frompartyid = $("#frompartyid").val();
	var consigneeAddress = trim($("#consigneeaddress").val());
	var fromdate= trim($("#fromdate").val());
	var todate= trim($("#todate").val());
	var urgencyDegree = $('.status div span').text();
	if(urgencyDegree=="全部"){
		urgencyDegree="";
	}
	var params="frompartyid="+frompartyid+"&consigneetown="+consigneeAddress+"&distristatus="+"待分派"+"&clientnumber="+clientnumber+
		"&fromdate="+fromdate+"&todate="+todate+"&clientnumber="+clientnumber+"&fromQuery="+"waybillassign"+"&urgencydegree="+urgencyDegree+"&random="+Math.random();
	f_hgrid_json(params);
}
function btnDetail(id){
	var url = "../waybillcs/waybill_detail?order=2&waybillid="+id;
	window.open(url,"_blank");
}
/**
 * 单个运单分派
 * @param waybillid
 * @return
 */
function btnDistribution(id){
	var frompartyid = $("#frompartyid").val();
	//window.location="../waybilldistributioncs/chooseSubcontractorList?waybillid="+id+"&frompartyid="+frompartyid;	
	ymPrompt.win({message:"../waybilldistributioncs/subcontractorlist?waybillid="+id+"&frompartyid="+frompartyid,width:820,height:550,fixPosition:true,dragOut:false,title:'选择分包商',iframe:true,autoClose:true});
}

function format(date){
	var date = date.toString();
	var index = date.indexOf(" ");
	return date.substr(0,index);
}
function f_hgrid_create(pagerow){//初始化
	//参数为每页行数 
	var txt='<div style=" margin:auto;">'+
		'<div style="width:300px;float:left;padding-left:8px;" id="left_div">'+
		'<span><input type="checkbox" id="choosAll" name="waybillSel" onclick="selAll();"/></span>'+
		'<span style="font-size:12px;color:black;padding-left:5px;padding-right:5px;">全选</span>'+
		'<span>将选中的</span>'+
		'<span><input type="button" id="batchDistri" value="批量分派" class="btn_sel" onclick="batchDistribution();" style="border: 1px solid #CCCCCC;"/></span>'+
		'</div>'+
		'<div style="padding:15px 3px 3px 3px;width:500px;float:left;text-align:right;" id="right_div">'+
		'<span id="pageinfo"></span>'+
		'<span id="pagetop"><a href="#">首页</a></span>'+
		'<span id="pagepre"><a href="#">上一页</a></span>'+
		'<span id="page1"><a href="#"></a></span>'+
		'<span id="page2"><a href="#"></a></span>'+
		'<span id="page3"><a href="#"></a></span>'+		
		'<span id="page4"><a href="#"></a></span>'+		
		'<span id="page5"><a href="#"></a></span>'+		
		'<span id="page6"><a href="#"></a></span>'+		
		'<span id="page7"><a href="#"></a></span>'+		
		'<span id="page8"><a href="#"></a></span>'+		
		'<span id="page9"><a href="#"></a></span>'+			
		'<span id="pagenext"><a href="#">下一页</a></span>'+
		'<span id="pagebottom"><a href="#">末页</a></span>'+
		'<input type="hidden" id="pagecode" value="1"/>'+ 
		'<input type="hidden" id="pageparam" value=""/>'+
		'<input type="hidden" id="pagerow" value="'+pagerow+'"/>'+
		'<input type="hidden" id="recordcount" value="0"/>'+
		'</div><div style="height: 100px;"></div>'+
		'</div>';
	
	$('#hgrid').after(txt); //after
	$('#hgrid').after('<div id="loading" style="text-align:center;padding-top:20px;"><img src="../imgs/sys/loading.gif" border="0" /></div>'); 

	$("#pagetop a").click(function(){ //首页
		window.scrollTo(0,0);
		$("#pagecode").val("1");	 
		f_hgrid_json(""); 
		return false; 
	});
	
	$("#pagenext a").click(function(){//下一页
		window.scrollTo(0,0);
		var pagecode=parseInt($("#pagecode").val())+1;
		var recordcount=parseInt($("#recordcount").val());
		var pagerow=parseInt($("#pagerow").val());		
		if (pagecode>parseInt((recordcount-1)/pagerow)+1){pagecode=parseInt((recordcount-1)/pagerow)+1;}
		$("#pagecode").val(pagecode);
		f_hgrid_json("");
		return false;
	});
	
	$("#pagepre a").click(function(){ //上一页
		window.scrollTo(0,0);
		var pagecode=parseInt($("#pagecode").val());
		pagecode=pagecode-1;
		if (pagecode<1) pagecode=1;
		$("#pagecode").val(pagecode);
		f_hgrid_json("");
		return false; 
	});
	
	$("#pagebottom a").click(function(){ //末页
		window.scrollTo(0,0);
		var pagecode=parseInt($("#pagecode").val());
		var recordcount=parseInt($("#recordcount").val());
		var pagerow=parseInt($("#pagerow").val());		
		if (pagecode!=parseInt((recordcount-1)/pagerow)+1){
			pagecode=parseInt((recordcount-1)/pagerow)+1;
			$("#pagecode").val(pagecode);
			f_hgrid_json("");
			}
		return false; 
	});

	for (var i=1;i<=9;i++){
		$("#page"+i+" a").click(function(){ 
			window.scrollTo(0,0);
			$("#pagecode").val($(this).text());
			f_hgrid_json("");
			return false; 
		});
	}
}
/**
 * 运单全选
 * @return
 */
function selAll(){
	var boxList = document.getElementsByName("wayBills");
	var box = document.getElementById("choosAll");
	if(box.checked){
		for(var i=0;i<boxList.length;i++){
			boxList[i].checked=true;
		}
	}
	else{
		for(var i=0;i<boxList.length;i++){
			boxList[i].checked=false;
		}
	}
}
/**
 * 批量分派运单
 * @return
 */
function batchDistribution(){
	var ids = new Array();
	var frompartyid = $("#frompartyid").val();
	var waybilllIds = document.getElementsByName("wayBills");
	$.each(waybilllIds,function(i,o){
		if(o.checked){			
			ids[i]=o.value;
		}
	});
	if(ids.length==0){
		ymPrompt.alert("请先选择要分派的运单！");
		return;
	};
	ymPrompt.win({message:"../waybilldistributioncs/subcontractorlist?waybillid="+ids+"&frompartyid="+frompartyid,width:820,height:550,fixPosition:true,dragOut:false,title:'选择分包商',iframe:true});
}
function trim(str){ //删除左右两端的空格
    return str.replace(/(^\s*)|(\s*$)/g, "");
}	