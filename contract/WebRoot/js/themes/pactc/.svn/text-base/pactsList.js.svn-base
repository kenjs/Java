function f_hgrid_ini() {//初始化表格
	var pagerow=10;  //参数为每页行数
	f_hgrid_create(pagerow); 	
	//注意：此处的列宽和为820px
	var txt='<tr>' +
		'<th width="100px" class="tdcenter" style="text-align: left;height:40px;padding-left:10px;">合同编号</th>' +
		'<th width="90px" class="tdcenter" style="text-align: left;height:40px;">合同类型</th>' +
		'<th width="100px" class="tdcenter" style="text-align: left;height:40px;">合同甲方</th>' +
		'<th width="95px" class="tdcenter" style="text-align: left;height:40px;">甲方签订人</th>' +
		'<th width="100px" class="tdcenter" style="text-align: left;height:40px;">合同乙方</th>' +
		'<th width="95px" class="tdcenter" style="text-align: left;height:40px;">乙方签订人</th>' +
		'<th width="80px" class="tdcenter" style="text-align: left;height:40px;">签订日期</th>' +
		'<th width="80px" class="tdcenter" style="text-align: left;height:40px;">状态</th>' +
		'<th width="150px" class="tdcenter" style="text-align: center;height:40px;">操作</th>' +
		'</tr>';
	for(var i =0;i<pagerow;i++){ //生成不见的空行
		txt=txt+'<tr id="tr'+i+'" class="ctr">';
	 }
	$("#hgrid").empty().append(txt);	
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
	 	url: "../pactcs/pactsList_data",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:param, //参数     	               
	   	success:function(data){//回传函数
	 		$("#loading").hide();
	 		data=f_hgrid_setmsg(data); //设置总 记录数,页信息等
			var txt;
			if(data.length!=0){
			for(var i =0;i<data.length;i++){ //展现返回的表格数据
				txt='<td class="tdleft"padding-left:10px;"><a href="javascript:void(0);" onclick=btnDetail("'+data[i].pactid+'")>'+data[i].pactnumber+'</a></td>'+		
					'<td class="tdleft">'+data[i].type+'</td>'+  
				 	'<td class="tdleft" style="text-overflow:ellipsis;white-space:nowrap;overflow:hidden;">'+ data[i].frompartyrealname+ 
					'<td class="tdleft" style="">'+data[i].frompartysignman+'</td>'+  
					'<td class="tdleft" style="text-overflow:ellipsis;white-space:nowrap;overflow:hidden;">'+ data[i].topartyrealname+ 
				 	'<td class="tdleft">'+ data[i].topartysignman+ 
				 	'<td class="tdleft">'+ data[i].signdate+ 
				 	'<td class="tdleft" style="text-overflow:ellipsis;white-space:nowrap;overflow:hidden;">'+data[i].state+'</td>'+  
				 	'<td style="text-align:center;color:#444444;"><a id="btnDel" href="javascript:void(0);" style=";margin-left:10px;" onclick=btnDetail("'+data[i].pactid+'")>详情</a><a  href="javascript:void(0);" style="margin-left:10px;" onclick=btnEdit("'+data[i].pactid+'")>修改</a><a id="btnDel" href="javascript:void(0);" style=";margin-left:10px;" onclick=btnDel("'+data[i].pactid+'")>删除</a></td>'; 
			 	$('#tr'+i).empty().append(txt);			      
		     }
			}
			var pagerow=$("#pagerow").val(); //每页行数
			 for(var i =data.length;i<pagerow;i++){
				 $('#tr'+i).empty();
			 }
          }	
	  }); 
}
function btnDetail(id){
	ymPrompt.win({message:"../subcontractorcs/subcontractor_detail?partyid="+id,width:600,height:480,fixPosition:true,dragOut:false,title:'确认成交信息',iframe:true});
}
function btnEdit(id){
	window.location="../pactcs/pactsEdit?order=17&pactid="+id;
}
function btnDel(id){
	ymPrompt.confirmInfo({message:'确定删除?',autoClose: true,height:200,width:300,fixPosition:true,dragOut:false,handler: 
		function(status){
		if(status=='ok'){
			$.ajax({
			 	url: "../pactcs/pactDel",   
			 	type:'post',	
			 	dataType:'json', 
			 	data:{pactid:id}, //参数     	               
			   	success:function(data){//回传函数
			 		if(data.msg=='ok'){
			 			//table.deleteRow(i);
			 			$(this).parent("td").parent("tr").remove();
			 			window.location.href="../pactcs/pactsList?order=17";
			 		}
				}
			});
		}
	}
	});
}
function btnAdd(obj){
	window.location="../pactcs/pactsAdd?order=17";
}
function trim(str){ //删除左右两端的空格
	   return str.replace(/(^\s*)|(\s*$)/g, "");
		}
function focusinput(obj) {
	if(obj.value=="请输入关键字"){
		obj.value = "";
	}
}
function mouseoutinput(obj) {
	if(obj.value==""){
		obj.value = "请输入关键字";
	}
	obj.style.border="1px solid #d3d7d4";
}
function btnDetail(pactId){
	window.location="../pactcs/pactsDetail?order=17&pactid="+pactId;
}