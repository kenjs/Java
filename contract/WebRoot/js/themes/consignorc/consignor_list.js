  function f_hgrid_ini() {//初始化表格
	var pagerow=10;  //参数为每页行数
	f_hgrid_create(pagerow); 	
	//注意：此处的列宽和为820px
	var txt='<tr>' +
		'<th scope="col" style="padding-left:10px;"  width="200px" class="tdleft">&nbsp;发货方名称</th>' +
		'<th scope="col"  width="80px" class="tdleft">助记码</th>' +
		'<th scope="col"  width="90px" class="tdleft">会员名</th>' +
		'<th scope="col"  width="80px" class="khh">客户号</th>' +
		'<th scope="col"  width="80px" class="tdleft">联系人</th>' +
		'<th scope="col"  width="90px" class="tdleft">手机</th>' +
		'<th scope="col"  width="120px" class="tdleft">所在地</th>' +
		'<th scope="col"  width="80px" class="tdleft">操作</th>' +
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
		var params=f_hgrid_getparam(param); //得到全部参数
	 	$.ajax({
	 	url: "../subcontractorcs/contractAndSubContractorList",
	 	type:'post',	
	 	dataType:'json', 
	 	data:params, //参数     	
	 	cache:false,
	   	success:function(data){//回传函数
	 		$('#loading').hide();
	 		data=f_hgrid_setmsg(data); //设置总记录数,页信息等
			var txt;
			for(var i =0;i<data.length;i++){ //展现返回的表格数据
				txt='<td class="tdleft" style="padding-left:10px;">&nbsp;'+
					'<a href="../consignorcs/consignor_detail?order=15&partyid='+data[i].partyid+'" target="_blank">'+
					data[i].organization+'</a>'+
					'</td>'+
					'<td class="tdleft">'+data[i].helpcode+'</td>'+			
					'<td class="tdleft">'+data[i].partyname+'</td>'+
					'<td class="tdleft">'+data[i].khh+'</td>'+
				 	'<td class="tdleft">'+data[i].contact+'</td>'+
				 	'<td class="tdleft">'+data[i].mobilenumber+'</td>'+
				 	'<td class="tdleft">'+data[i].city+data[i].region+'</td>'+
				 	'<td class="tdleft">'+
				 	'<a href="../consignorcs/consignor_detail?order=15&partyid='+data[i].partyid+'" target="_blank">详情</a>'+
				 	'&nbsp'+
				 	'<a href="javascript:void(0);" OnClick="f_editclick(&quot;'+data[i].partyid+'&quot;)">修改</a></td>';
			 	$('#tr'+i).empty().append(txt);			      
		     }
			var pagerow=$("#pagerow").val(); //每页行数
			 for(var i =data.length;i<pagerow;i++){
				 $('#tr'+i).empty();
			 }
          }
	  }); 
}
/**
 * wei.huang
 * 2013-10-23
 * 修改发货方
 * @param id
 * @return
 */
function f_editclick(id){
	window.location="../consignorcs/consignor_edit?order=15&partyid="+id;
}
function f_deleteclick(id){	
	ymPrompt.confirmInfo({message:'确定删除该发货方吗?',autoClose: true,height:200,width:300,fixPosition:true,dragOut:false,handler: 
		function(status){
		if(status=='ok'){
		$.ajax({
		 	url: "../shipperrelasubontractorcs/deleteConsigner",   
		 	type:'post',	
		 	dataType:'json', 
		 	data:{frompartyid:id,tablename:"party",tableid:id}, //参数     	               
		   	success:function(data){
		 		if(data.msg=="ok"){		 			
		 			window.location.reload();
		 		}
		 		else{
		 			ymPrompt.alert("删除失败！");
		 		}
		 	},
			error:function(){
				//alert(data.msg);
		 		ymPrompt.alert("删除失败！");
			}
		});
		}else{
			return false;
		}
		}
		});
	}

function f_searchclick(){
	$("#pagecode").val("1");
	var organization=$("#organization").val();
	var partyname=$("#partyname").val();
	var params="&organization="+organization+"&partyname="+partyname+"&partytype=发货方";
	f_hgrid_json(params);	
}

function f_insertclick(){
	window.location.href="../consignorcs/consignor_add?order=15";
}