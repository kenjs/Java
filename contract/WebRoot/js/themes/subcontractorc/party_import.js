function f_hgrid_ini() {//初始化表格
	var pagerow=8;  //参数为每页行数
	f_hgrid_create(pagerow); 	
	//注意：此处的列宽和为820px
	var txt='<tr>' +
		'<th width="40px" class="tdcenter" style="text-align: left;height:40px;padding-left:10px;">选择</th>' +
		'<th width="100px" class="tdcenter" style="text-align: left;height:40px;padding-left:10px;">会员名</th>' +
		'<th width="100px" class="tdcenter" style="text-align: left;height:40px;padding-left:10px;">助记码</th>' +
		'<th width="200px" class="tdcenter" style="text-align: left;height:40px;padding-left:10px;">分包商名称</th>' +
		'<th width="150px" class="tdcenter" style="text-align: left;height:40px;padding-left:10px;">主要联系人</th>' +
		'</tr>';
	for(var i =0;i<pagerow;i++){ //生成不见的空行
		txt=txt+'<tr id="tr'+i+'" class="ctr"';
		if(i%2 != 0) {
			txt = txt+'style="background-color: #f8feff;"';
		}
	 	txt=txt+'></tr>';
	 }
	$("#hgrid").empty().append(txt);	
	 	$('.ctr').hover(function(){
	 		$(this).css("background-color","#fffddd");
	 	},function(){
	 		var indexvalue=$(this).index();
	 		if(indexvalue%2 != 0) {
	 		$(this).css("background-color","#fff");
	 		}
	 		else{
	 			$(this).css("background-color","#f8feff");
	 		}
	 	});
	 
}	
function f_hgrid_json(param) {//刷新hGrid数据
		//vparam不包括页码和每页行数
		param=f_hgrid_getparam(param); //得到全部参数
	 	$.ajax({
	 	url: "../subcontractorcs/subcontractorList_json",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:param, //参数     	               
	   	success:function(data){//回传函数
	 		$("#loading").hide();
	 		data=f_hgrid_setmsg(data); //设置总 记录数,页信息等
			var txt;
			for(var i =0;i<data.length;i++){ //展现返回的表格数据
				txt='<td class="tdleft"><input type="radio" class="radio" name="partyid" value="'+data[i].partyid+'"/></td>'+	
			 		'<td class="tdleft">'+ data[i].partyname+ 
					'<td class="tdleft">'+data[i].helpcode+'</td>'+  
					'<td class="tdleft" style=";padding-left: 15px">'+data[i].organization+'</td>'+  
					'<td class="tdleft">'+ data[i].contact+'</td>'
			 	$('#tr'+i).empty().append(txt);			      
		     }
			var pagerow=$("#pagerow").val(); //每页行数
			 for(var i =data.length;i<pagerow;i++){
				 $('#tr'+i).empty();
			 }
			 
			 $("tr[id^='tr']").click(function(){
				 $(this).find("input[name='partyid']").attr("checked",true);
			 });
/*			 $("input[name='partyid']").click(function(){
				 $("#warn").html("");
				 var partyid=$("#partyid").val();
				 var topartyid=$("input[name='partyid']:checked").val();
				 var pa1="partyid="+partyid+"&topartyid="+topartyid;
				 $("#btnsave").attr("disabled","disabled");
				 	$.ajax({
				 	 	url: "../shipperrelasubontractorcs/checkRealtionExsit",   
				 	 	type:'post',	
				 	 	dataType:'json', 
				 	 	data:pa1, //参数     	               
				 	   	success:function(data){
				 		if(data.msg!="ok"){
						 	$("#warn").css("textalign","center");
						 	$("#warn").html("重复选择分包商");
				 		}
				 		else{
				 			$("#btnsave").removeAttr("disabled");
				 		}
				 	}
				 	});
		
				 });*/
          }	
	  }); 
}
function trim(str){ //删除左右两端的空格
	   return str.replace(/(^\s*)|(\s*$)/g, "");
		}
function focusinput(obj) {
	if(obj.value=="请输入关键字"){
		obj.value = "";
	}
	obj.style.border="1px solid #a1a3a6";
}
function mouseoutinput(obj) {
	if(obj.value==""){
		obj.value = "请输入关键字";
	}
	obj.style.border="1px solid #d3d7d4";
}

$("#btnsave").click(function(){
	var partyid=$("input[name='partyid']:checked").val();
	$("#btnsave").attr("disabled","disabled");
	if(partyid==undefined){
		alert("请选择相关分包商");
		$("#btnsave").removeAttr("disabled");
	}else{
		window.parent.getpartyInfo(partyid);
		window.parent.ymPrompt.doHandler('close',true);
	}
	
});
$("#reback").click(function(){
	window.parent.ymPrompt.doHandler('close',true);
});
function radioClick(obj){
		 $("#warn").html("");
		 var partyid=$("#partyid1").val();
		 var topartyid=$(obj).val();
		 var pa1="partyid="+partyid+"&topartyid="+topartyid;
		 $("#btnsave").attr("disabled","disabled");
		 	$.ajax({
		 	 	url: "../shipperrelasubontractorcs/checkRealtionExsit",   
		 	 	type:'post',	
		 	 	dataType:'json', 
		 	 	data:pa1, //参数     	               
		 	   	success:function(data){
		 		if(data.msg!="ok"){
				 	$("#warn").css("textalign","center");
				 	$("#warn").html("重复选择分包商");
		 		}
		 		else{
		 			$("#btnsave").removeAttr("disabled");
		 		}
		 	}
		 	});
}