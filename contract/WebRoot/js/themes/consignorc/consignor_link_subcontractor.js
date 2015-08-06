function f_hgrid_ini() {//初始化表格
	var pagerow=8;  //参数为每页行数
	f_hgrid_create(pagerow); 	
	//注意：此处的列宽和为820px
	var txt='<tr>' +
		'<th width="30px" class="tdcenter" style="text-align: left;height:30px;padding-left:10px;">选择</th>' +
		'<th width="85px" class="tdcenter" style="text-align: left;height:30px;padding-left:10px;">会员名</th>' +
		'<th width="85px" class="tdcenter" style="text-align: left;height:30px;padding-left:10px;">助记码</th>' +
		'<th width="100px" class="tdcenter" style="text-align: left;height:30px;padding-left:10px;">分包商名称</th>' +
		'<th width="100px" class="tdcenter" style="text-align: left;height:30px;padding-left:10px;">主要联系人</th>' +
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
	 	url: "../subcontractorcs/contractAndSubContractorList",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:param, //参数     	               
	   	success:function(data){//回传函数
	 		$("#loading").hide();
	 		data=f_hgrid_setmsg(data); //设置总 记录数,页信息等
			var txt;
			for(var i =0;i<data.length;i++){ //展现返回的表格数据
				txt='<td class="tdleft" style="color:#1560ea;"><input type="radio" class="radio" name="partyid"  value="'+data[i].partyid+'"/></td>'+		
				 	'<td class="tdleft">'+ data[i].partyname+ 
					'<td class="tdleft" style=";padding-left: 15px">'+data[i].helpcode+'</td>'+  
					'<td class="tdleft">'+ data[i].organization+ 
				 	'<td class="tdleft">'+ data[i].contact+ 
				 	'</td>'; 
			 	$('#tr'+i).empty().append(txt);			      
		     }
			var pagerow=$("#pagerow").val(); //每页行数
			 for(var i =data.length;i<pagerow;i++){
				 $('#tr'+i).empty();
			 }
			 $("tr[id^='tr']").click(function(){
				 $(this).find("input[name='partyid']").attr("checked",true);
			 });
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
function save(){
	var partyid=$('input[name="partyid"]:checked').val();
	var partyname=$('input[name="partyid"]:checked').parent("td").next().html();
	var data=getid()+"&topartyid="+partyid+"&topartyname="+partyname;
	$("#btnsave").attr("disabled","disabled");
	if(partyid==undefined){
		alert("请选择相关发货方");
		$("#btnsave").removeAttr("disabled");
		return false;
	}else{/**
	 		*关联分包商
	 		**/
	
	 	$.ajax({
		 	url: "../shipperrelasubontractorcs/contractLinkSubContractor",   
		 	type:'post',	
		 	dataType:'json', 
		 	data:data, //参数     	               
		   	success:function(data){
	 			if(data.msg=='ok'){
	 				alert("关联成功");
	 				//window.parent.ymPrompt.doHandler('close',true);
	 				window.parent.ymPrompt.doHandler("4,保存,增加",true);
	 			}else{
	 				$("#btnsave").removeAttr("disabled");
	 			}
	 		}
	 	});
		
	}
}

function reback(){
	window.parent.ymPrompt.close();
}
function getid() {
	var idUrl = document.URL;
	var num = idUrl.indexOf("?");
	idUrl = idUrl.substr(num + 1);
	return idUrl;
}