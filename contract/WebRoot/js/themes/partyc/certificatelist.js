function f_hgrid_ini() {//初始化表格
	
	var pagerow=10;  //参数为每页行数
	f_hgrid_create(pagerow); 	
	//注意：此处的列宽和为820px
	var txt='<tr>' +
		'<th scope="col"   width="100px" class="tdcenter">诚信卡号</th>' +
		'<th scope="col"   width="100px" class="tdcenter">车牌号</th>' +
		'<th scope="col"   width="80px" class="tdcenter">驾驶员</th>' +
		'<th scope="col"   width="100px" class="tdcenter">联系电话</th>' +
		'<th scope="col"   width="70px" class="tdcenter">吨位</th>' +
		'<th scope="col"   width="150px" class="tdcenter">去向</th>' +
		'<th scope="col"   width="150px" class="tdcenter">目前位置</th>' +
		'<th scope="col"   width="70px" class="tdcenter">操作</th>' +
		'</tr>';
	
	for(var i =0;i<pagerow;i++){ //生成不见的空行
				txt=txt+'<tr id="tr'+i+'" class="ctr">';
			 	txt=txt+'</tr>';
			 	

			 }
	$("#hgrid").empty().append(txt);
	$(".ctr").hover(function(){
		                 $(this).css("background-color","#fffddd");
		                 },
		                 function(){
		                          $(this).css("background-color","#fff");

		                 });

}

function f_hgrid_json(param) {//刷新hGrid数据
		//vparam不包括页码和每页行数
	    var tradetype;
		param=f_hgrid_getparam(param); //得到全部参数
	 	$.ajax({
	 	url: "../carcs/querycertificatelist",   
	 	type:'post',	
	 	dataType:'json', 
	 	data:param, //参数     	               
	   	success:function(data){//回传函数
	 		$("#loading").hide();
	 		data=f_hgrid_setmsg(data); //设置总 记录数,页信息等
			var txt;
			for(var i =0;i<data.length;i++){ //展现返回的表格数据
				txt = txt+data[i].partyName;
				
				txt='<td class="tdcenter1"><a href="#" style="color:#1560ea;font-family:"微软雅黑","宋体",Arial; OnClick=f_detail(\''+data[i].carid+'\',\''+data[i].partyid+'\',\''+data[i].trailerid+'\',\''+data[i].partyname+'\',\''+data[i].destination+'\')>'+data[i].partyname+"</a>"+'</td>'+
					'<td class="tdcenter1">'+data[i].carplatenumber+'</td>'+
					'<td class="tdcenter1">'+data[i].realname+'</td>'+
					'<td class="tdcenter1">'+data[i].mobilenumber+'</td>'+
					'<td class="tdcenter1">'+data[i].cardragmass/1000+'</td>'+
				 	'<td class="tdcenter1">'+data[i].destination+'</td>'+
					'<td class="tdcenter1">'+data[i].location+'</td>'+
					'<td class="tdcenter1"><a href="#" style="color:#1560ea;font-family:"微软雅黑","宋体",Arial; OnClick=f_detail(\''+data[i].carid+'\',\''+data[i].partyid+'\',\''+data[i].trailerid+'\',\''+data[i].partyname+'\',\''+data[i].destination+'\')>'+ "详情"+"</a>"+'</td>';		 
			 	$('#tr'+i).empty().append(txt);			      
		     }
			var pagerow=$("#pagerow").val(); //每页行数
			 for(var i =data.length;i<pagerow;i++){
				 $('#tr'+i).empty();
			 }
          }	
	  }); 
}
 
function f_detail(carid,partyid,trailerid,partyname,destination){//详情
//	alert("carid,partyid,trailerid,partyname="+carid+"      "+partyid+"         "+trailerid+"    "+partyname);
	var params="carid="+carid+'&partyid='+partyid+'&trailerid='+trailerid+'&partyname='+partyname+'&destination='+destination;
	window.open('../partycs/certificatedetail?random='+Math.random()+params);
	// window.open('../partycs/certificatedetail?carid='+carid+'&partyid='+partyid+'&trailerid='+trailerid+'&partyname='+encodeURIComponent(encodeURIComponent(partyname))+'&destination='+encodeURIComponent(encodeURIComponent(destination)));

}





