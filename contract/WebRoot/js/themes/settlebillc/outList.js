var organization="";
function f_hgrid_ini(type) {//初始化表格
	var pagerow=10;  //参数为每页行数
	if(type=="1"){
		organization="发货方";
	}else{
		organization="分包商"
	}
	f_hgrid_create(pagerow); 	
	//注意：此处的列宽和为840px
	var txt='<tr>' +
		'<th scope="col"   width="105px" class="tdleft" style="">&nbsp;结算单号</th>' +
		'<th scope="col"   width="60px" class="tdleft">状态</th>' +
		'<th scope="col"   width="80px" class="tdleft">核销发票号</th>' +
		'<th scope="col"   width="140px" class="tdleft">结算期</th>' +
		'<th scope="col"   width="140px" class="tdleft">'+organization+'</th>' +
		'<th scope="col"   width="170px" class="tdleft">发货方</th>' +
		'<th scope="col"   width="70px" class="tdleft">应付合计</th>' +
		'<th scope="col"   width="140px" class="tdleft">最后编辑时间</th>' +
		'</tr>';
	for(var i =0;i<pagerow;i++){ //生成不见的空行
		txt=txt+'<tr id="tr'+i+'" class="ctr'+i+'" onmouseover="f_onmouseover('+i+')" onmouseout="f_onmouseout('+i+')">';
	 	txt=txt+'</tr>';
	 }
	$("#hgrid").empty().append(txt);
	var txt='<tr>' +
	'<th scope="col" style="height:30px;padding-left:0px;width:70px;background-color: #EEEEEE;" class="tdcenter">操作</th>' +
	'</tr>';
	for(var i =0;i<pagerow;i++){ //生成不见的空行
		txt=txt+'<tr id="czTr'+i+'" class="ctr'+i+'" >';
	 	txt=txt+'</tr>';
	}
	$("#operation").empty().append(txt);
}

function f_onmouseover(i){
	$('.ctr'+i).css("background-color","#fffddd");
}
function f_onmouseout(i){
	$('.ctr'+i).css("background-color","white");
}
function f_hgrid_json(param) {//刷新hGrid数据
		//vparam不包括页码和每页行数
	var inorout="";
	if(organization=="发货方"){
		inorout="1";
	}else{
		inorout="0";
	}
		param=f_hgrid_getparam(param)+"&inorout="+inorout+"&conorgname="; //得到全部参数
	 	$.ajax({
	 	 	url: "../settlebillcs/settleBillList",   
	 	 	type:'post',	
	 	 	dataType:'json', 
	 	 	data:param, //参数     	               
	 	   	success:function(data){//回传函数
	 	 		$('#loading').hide();
	 	 		data=f_hgrid_setmsg(data); //设置总记录数,页信息等
	 			var txt;
	 			var txt1;
	 			for(var i =0;i<data.length;i++){ //展现返回的表格数据
	 				if(data[i].status=="未审核"){
	 					operate='<a href="javascript:void(0)" onclick="update(this,'+'&quot;'+data[i].settlebillid+'&quot;,&quot;'+data[i].organization+'&quot;,&quot;'+data[i].conorganization+'&quot;,&quot;'+data[i].inoutpartyid+'&quot;,&quot;'+i+'&quot;)">修改</a>'+
	 					'&nbsp;<a href="javascript:void(0)" onclick="deleteAction(this'+',&quot;'+data[i].settlebillid+'&quot;,&quot;'+i+'&quot;)">删除</a>';
	 				}else{
	 					operate='';
	 				}
	 				txt='<td class="tdleft">&nbsp;<a href="javascript:void(0)" onclick="detail(&quot;'+data[i].settlebillid+'&quot;,&quot;'+data[i].settlebillnumber+'&quot;,&quot;'+data[i].startdate+'-'+data[i].enddate+'&quot;,&quot;'+data[i].organization+'&quot;,&quot;'+data[i].conorganization+'&quot;,&quot;'+i+'&quot;,&quot;'+inorout+'&quot;)" style="margin-left:0px;">'+data[i].settlebillnumber+'</a></td>'+
	 					'<td class="tdleft"><span style="margin-left:0px;" id="tr'+i+'_td2">'+data[i].status+'</span></td>'+					
	 					'<td class="tdleft"><span style="margin-left:0px;" id="tr'+i+'_td3">'+data[i].billnumber+'</span></td>'+
	 				 	'<td class="tdcenter">'+data[i].startdate+'-'+data[i].enddate+'</td>'+
	 				 	'<td class="tdleft">'+data[i].organization+'</td>'+					
	 					'<td class="tdleft">'+data[i].conorganization+'</td>'+
	 					'<td class="tdleft">'+data[i].needinoutallamount+'</td>'+
	 					'<td class="tdleft"><span style="margin-left:0px;" id="tr'+i+'_td7">'+data[i].inputdate+'</span></td>';
	 				 	txt1='<td class="tdleft">&nbsp'+
	 				 	'<a href="javascript:void(0)" onclick="detail(&quot;'+data[i].settlebillid+'&quot;,&quot;'+data[i].settlebillnumber+'&quot;,&quot;'+data[i].startdate+'-'+data[i].enddate+'&quot;,&quot;'+data[i].organization+'&quot;,&quot;'+data[i].conorganization+'&quot;,&quot;'+i+'&quot;,&quot;'+inorout+'&quot;)">详情&nbsp;</a>'+
	 				 	operate+'</td>';
	 			 	$('#tr'+i).empty().append(txt);	
	 			 	$('#czTr'+i).empty().append(txt1);	
	 		     }
	 			 var pagerow=$("#pagerow").val(); //每页行数
	 			 for(var i =data.length;i<pagerow;i++){
	 				 $('#tr'+i).empty();
	 				$('#czTr'+i).empty();
	 			 }
	 	      }	
	 	  }); 
}

//查询条件 add by yao.xia 2013-12-18
function getConditions(){
	var enddateValue=$.trim($("#enddate").val());
	if(enddateValue!=''){
		enddateValue=enddateValue+" 23:59:59";
	}
	var startdateValue=$.trim($("#startdate").val());
	if(startdateValue!=''){
		startdateValue=startdateValue+" 0:00:00";
	}
	var settlebillnumber=$.trim($("#settlebillnumber").val()), startdate=startdateValue,
		enddate=enddateValue,inoutpartyrealname=$.trim($("#inoutpartyrealname").val()),
		clientnumber=$.trim($("#clientnumber").val()),conorgname=$.trim($("#conorgname").val());
	var params="clientnumber="+clientnumber+"&settlebillnumber="+settlebillnumber+"&startdate="+startdate+"&enddate="+enddate
				+"&inoutpartyrealname="+inoutpartyrealname+"&conorgname="+conorgname+"&random="+Math.random();
	return params;
}

function search(){
	var params = getConditions();
	$("#pagecode").val("1");
	f_hgrid_json(params);
	
}
function update(obj,settlebillid,organization,conorganization,inoutpartyid,i){
	var params="&settlebillid="+settlebillid+"&organization="+encodeURI(organization)+"&conorganization="+encodeURI(conorganization)+"&inoutpartyid="+inoutpartyid+"&random="+Math.random();
	window.location="../settlebillcs/outUpdate?order=8"+params;
}
function deleteAction(obj,settlebillid,i){
	ymPrompt.confirmInfo({message:'确定删除吗?',autoClose: true,height:200,width:300,fixPosition:true,dragOut:false,handler: 
		function(status){
		if(status=='ok'){
			$.ajax({
				url: "../settlebillcs/delete",   
			 	type:'post',	
			 	dataType:'json', 
			 	data:{"settlebillid":settlebillid,"inorout":"0"}, //参数     	               
			   	success:function(data){//回传函数
			 		if(data.msg="ok"){
			 			$(obj).parent().parent().remove()
			 		}else{
			 			search();
			 		}
			}
			});
		}
		}
	})
}
function detail(settlebillid,settlebillnumber,start_end_date,organization,conorganization,trnum,inorout){
	var status=$("#tr"+trnum+"_td2").html();
	var data=encodeURI(settlebillid+","+settlebillnumber+","+status+","+start_end_date+","+organization+","+inorout+","+conorganization);
	window.open("../settlebillcs/payableSettlebillDetail?data="+data);
}
