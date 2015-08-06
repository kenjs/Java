function f_hgrid_init(){
	var pagerow=5;		//每页显示条数
	f_hgrid_create(pagerow);
	var txt = '<tr>'+
			'<th scope="col" width="80px" class="tdcenter" style="text-align: left;height:30px;padding-left:20px;line-height:30px;">选择</th>'+
			'<th scope="col" width="200px" class="tdcenter" style="text-align: left;height:30px;padding-left:30px;">分包商名称</th>'+
			'<th scope="col" width="250px" class="tdcenter" style="text-align: left;height:30px;padding-left:30px;">发货地</th>'+
			'<th scope="col" width="250px" class="tdcenter" style="text-align: left;height:30px;padding-left:30px;">收货地</th>'+
			'<th scope="col" width="300px" class="tdcenter" style="text-align: left;height:30px;padding-left:30px;">备注</th>'+
			  '</tr>';	
	for(var i =0;i<pagerow;i++){ //生成不见的空行
		txt=txt+'<tr id="tr'+i+'" class="ctr'+i+'" onmouseover="f_onmouseover('+i+')" onmouseout="f_onmouseout('+i+')">';
	 	txt=txt+'</tr>';
	 }
	$("#hgrid").empty().after(txt);
}
function f_onmouseover(i){
	$('.ctr'+i).css("background-color","#fffddd");
}
function f_onmouseout(i){
	$('.ctr'+i).css("background-color","white");
}
function f_hgrid_create(pagerow){//初始化
	//参数为每页行数 
	var txt='<div style=" margin:auto;">'+
		'<div style="width:250px;float:left;" id="opera_div">'+
			'<span style="display:block;margin:5px;float:left;"><input type="button" class="sureBtn" id="reback" name="reback" onclick="doConfirm();" value="确认"/></span>'+
			'<span style="float:left;margin:5px;"><input type="button" id="reback" class="closeBtn" name="reback" onclick="window.parent.ymPrompt.close();" value="关闭"/></span>'+
		'</div>'+
		'<div style="padding:15px 3px 3px 3px;width:500px;float:left;text-align:right;" id="page_div">'+
		'<span id="pageinfo"></span>'+
		'<span id="pagepre"><a href="#">上一页</a></span>'+
		'<span id="pagenext"><a href="#">下一页</a></span>'+
		'<input type="hidden" id="pagecode" value="1"/>'+ 
		'<input type="hidden" id="pageparam" value=""/>'+
		'<input type="hidden" id="pagerow" value="'+pagerow+'"/>'+
		'<input type="hidden" id="recordcount" value="0"/>'+
		'</div><div style="height: 100px;"></div>'+
		'</div>';
	
	$('#hgrid').after(txt); //after
	$('#hgrid').after('<div id="loading" style="text-align:center;width:620px;"><img src="../imgs/sys/loading.gif" border="0" /></div>'); 

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
}
function f_hgrid_json(param) {//刷新hGrid数据
	//vparam不包括页码和每页行数
	var organization = $("#organization_id").val();
	var frompartyid = getParam(1);
	var fromaddress = $("#fromaddress_id").val();
	var toaddress = $("#toaddress_id").val();
	var description = $("#description_id").val();
	var params = "word="+organization+"&fromaddress="+fromaddress+"&frompartyid="+frompartyid
		"&toaddress="+toaddress+"&description="+description+"&random="+Math.random();
	param=f_hgrid_getparam(param); //得到全部参数	
 	$.ajax({
 	url: "../waybilldistributioncs/chooseSubcontractorListJson",   
 	type:'post',	
 	dataType:'json', 
 	data:param, //参数     	               
   	success:function(data){//回传函数
 		$('#loading').hide(); 		
 		data=f_hgrid_setmsg(data); //设置总记录数,页信息等
		var txt;
		if(data.length==0){
			$("#opera_div").hide();
			$("#page_div").hide();
			$('<div class="myjl" style="font-size:18px;text-align:center;padding-top:20px;font-family:微软雅黑;">没有记录</div>').insertBefore("#page_div");
		}
		for(var i =0;i<data.length;i++){ //展现返回的表格数据
			txt='<td class="tdleft"><input type="radio" name="subcontractors" value="'+data[i].topartyid+'" /></td>'+
				'<td class="tdleft">'+data[i].organization+'</td>'+
				'<td class="tdleft">'+data[i].fromaddress+'</td>'+
				'<td class="tdleft">'+data[i].toaddress+'</td>'+
				'<td class="tdleft">'+data[i].distance+'</td>';
			$('#tr'+i).empty().append(txt);
		}
		var pagerow=$("#pagerow").val(); //每页行数
		 for(var i =data.length;i<pagerow;i++){
			 $('#tr'+i).empty();
		 }
      }	
  }); 
}
function selectSubconr(){
	$("#pagecode").val("1");
	var frompartyid = getParam(1);
	if(frompartyid == null || frompartyid == undefined || frompartyid == 'undefined'){
		frompartyid = "";
	}
	var organization = $("#organization_id").val();
	var fromaddress = $("#fromaddress_id").val();
	var toaddress = $("#toaddress_id").val();
	var description = $("#description_id").val();
	var params = "word="+organization+"&fromaddress="+fromaddress+
				"&toaddress="+toaddress+"&description="+description+"&frompartyid="+frompartyid+
				"&random="+Math.random();
	f_hgrid_json(params);
}
function doConfirm(){	
	var frompartyid = getParam(1);
	var partyid=$('input:radio[name="subcontractors"]:checked').val(); 
	if(partyid == undefined || partyid == "" ||partyid == null){
		ymPrompt.alert("请选择分包商！");
		return;
	}
	var param = "topartyid="+partyid+"&waybillid="+getParam(0)+"&random="+Math.random();
	$.ajax({
		url: "../waybilldistributioncs/waybillDistributionSave", 
		type:"post",
		dataType:"json",
		data:param,
		success:function(data){
			if(data.msg=="ok"){
				ymPrompt.alert({message:"分派成功！",handler:refreshParentWindow});
			}else{
				ymPrompt.alert({message:"分派失败！",handler:refreshParentWindow});
			}
		},
		error:function(){
			ymPrompt.alert({message:"分派失败！",handler:refreshParentWindow});
		}
	});
}
function trim(str){ //删除左右两端的空格
	if(str == null || str == undefined || str == ""){
		return "";
	}
	str.replace(/(^\s*)|(\s*$)/g,"");
}

function getParam(i){//waybillid=ids&frompartyid=frompartyid
	var iUrl = document.URL;	
	var num=iUrl.indexOf("?") 
	iUrl=iUrl.substr(num+1);
	var data = iUrl.split("&");
	data = data[i].split("=");
	return data[1]
}
function refreshParentWindow(){
	$(".sureBtn").hide();
	$(".closeBtn").click();
	parent.selectWaybillList();
}