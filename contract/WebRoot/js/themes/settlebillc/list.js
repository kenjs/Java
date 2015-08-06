var organizationtype="";
function f_hgrid_ini(organization) {//初始化表格
	organizationtype=organization;
	var pagerow=10;  //参数为每页行数
	f_hgrid_create(pagerow);
	var txt="";
	if(organization=="发货方"){
		//注意：此处的列宽和为840px
		txt='<tr>' +
			'<th scope="col" width="110px" class="tdleft">&nbsp;结算单号</th>' +
			'<th scope="col" width="65px" class="tdleft">状态</th>' +
			'<th scope="col" width="85px" class="tdleft">核销发票号</th>' +
			'<th scope="col" width="140px" class="tdcenter">结算期</th>' +
			'<th scope="col" width="150px" class="tdleft">'+organization+'</th>' +
			'<th scope="col" width="100px" class="tdleft">应收合计</th>' +
			'<th scope="col" width="100px" class="tdleft">已开票合计</th>' +
			'<th scope="col" width="100px" class="tdleft">未开票合计</th>' +
			'</tr>';
	}else{
		txt='<tr>' +
			'<th scope="col" width="110px" class="tdleft">&nbsp;结算单号</th>' +
			'<th scope="col" width="65px" class="tdleft">状态</th>' +
			'<th scope="col" width="85px" class="tdleft">核销发票号</th>' +
			'<th scope="col" width="140px" class="tdcenter">结算期</th>' +
			'<th scope="col" width="150px" class="tdleft">'+organization+'</th>' +
			'<th scope="col" width="150px" class="tdleft">发货方</th>' +
			'<th scope="col" width="100px" class="tdleft">应付合计</th>' +
			'<th scope="col" width="100px" class="tdleft">已开票合计</th>' +
			'<th scope="col" width="100px" class="tdleft">未开票合计</th>' +
			'</tr>';
	}
	
	for(var i =0;i<pagerow;i++){ //生成不见的空行
		txt=txt+'<tr id="tr'+i+'" class="ctr'+i+'" onmouseover="f_onmouseover('+i+')" onmouseout="f_onmouseout('+i+')">';
	 	txt=txt+'</tr>';
	 }
	$("#hgrid").empty().append(txt);
	
	var txt='<tr>' +
	'<th scope="col"   width="130px" class="tdleft">操作</th>' +
	'</tr>';
	for(var i =0;i<pagerow;i++){ //生成不见的空行
		txt=txt+'<tr id="czTr'+i+'" class="ctr'+i+'" onmouseover="f_onmouseover('+i+')" onmouseout="f_onmouseout('+i+')">';
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
	var inorout="";
	if(organizationtype=="发货方"){
		inorout="1";
	}else if(organizationtype=="分包商"){
		inorout="0";
		param+="&conorgname="
	}
	param+="&inorout="+inorout;
	param=f_hgrid_getparam(param); //得到全部参数
 	$.ajax({
 	url: "../settlebillcs/settleBillList",   
 	type:'post',	
 	dataType:'json', 
 	data:param, //参数     	               
   	success:function(data){//回传函数
 		$('#loading').hide();
 		data=f_hgrid_setmsg(data); //设置总记录数,页信息等
		var txt,txt1;
		if(organizationtype=="发货方"){
			inorout="1";
			for(var i =0;i<data.length;i++){ //展现返回的表格数据
				var operate='';//操作方法
				if(data[i].status=="未审核"){
					operate='<a href="javascript:void(0)" onclick="audit(&quot;'+i+'&quot;,&quot;'+data[i].settlebillid+'&quot;,&quot;'+data[i].settlebillnumber+'&quot;,&quot;'+data[i].organization+'&quot;,&quot;'+data[i].status+"&quot;,&quot;"+data[i].needinoutallamount+"&quot;,&quot;"+data[i].factamount+"&quot;,&quot;"+data[i].needinoutremainamount+"&quot;,&quot;"+inorout+"&quot;,&quot;"+data[i].startdate+'-'+data[i].enddate+'&quot;)">审核</a>';
				}else if(data[i].status!="全部核销"){
					var _data="&settlebillid="+data[i].settlebillid+"&settlebillnumber="+data[i].settlebillnumber+"&organization="+data[i].organization+
						"&status="+data[i].status+"&needinoutallamount="+data[i].needinoutallamount+"&factamount="+data[i].factamount+"&needinoutremainamount="+data[i].needinoutremainamount+"&inorout="+inorout+"&start_end_date="+data[i].startdate+'-'+data[i].enddate;
					operate='<a href="../settlebillverificationcs/inVerification?'+encodeURI("order=9"+_data)+'" target="_self">核销</a>';
					if(inorout==0){
						operate='<a href="../settlebillverificationcs/outVerification?'+encodeURI("order=10"+_data)+'" target="_self">核销</a>';
					}
				} 
				if(data[i].status == "已审核"){
					operate='<a href="../settlebillverificationcs/inVerification?'+encodeURI("order=9"+_data)+'" target="_self">核销</a>'+
							'&nbsp;<a href="javascript:void(0)" onclick="cancel('+data[i].settlebillid+')">取消审核</a>';
					if(inorout==0){
						operate='<a href="../settlebillverificationcs/outVerification?'+encodeURI("order=10"+_data)+'" target="_self">核销</a>'+
							'&nbsp;<a href="javascript:void(0)" onclick="cancel('+data[i].settlebillid+')">取消审核</a>';
					}
				}
				txt='<td class="tdleft">&nbsp;'+
						'<a href="javascript:void(0)" onclick="detail(&quot;'+data[i].settlebillid+'&quot;,&quot;'+data[i].settlebillnumber+'&quot;,&quot;'+data[i].startdate+'-'+data[i].enddate+'&quot;,&quot;'+data[i].organization+'&quot;,&quot;'+i+'&quot;,&quot;'+inorout+'&quot;,&quot;'+data[i].conorganization+'&quot;)">'+data[i].settlebillnumber+'</a>'+
					'</td>'+
					'<td class="tdleft"><span id="tr'+i+'_td2">'+data[i].status+'</span></td>'+					
					'<td class="tdleft">'+data[i].billnumber+'</td>'+
				 	'<td class="tdcenter">'+data[i].startdate+'-'+data[i].enddate+'</td>'+
				 	'<td class="tdleft">'+data[i].organization+'</td>'+	
					'<td class="tdleft">'+data[i].needinoutallamount+'</td>'+
					'<td class="tdleft">'+data[i].factamount+'</td>'+
					'<td class="tdleft">'+data[i].needinoutremainamount+'</td>';			 	
			   txt1 = '<td class="tdleft">'+
				 		'<a href="javascript:void(0)" onclick="detail(&quot;'+data[i].settlebillid+'&quot;,&quot;'+data[i].settlebillnumber+'&quot;,&quot;'+data[i].startdate+'-'+data[i].enddate+'&quot;,&quot;'+data[i].organization+'&quot;,&quot;'+i+'&quot;,&quot;'+inorout+'&quot;,&quot;'+data[i].conorganization+'&quot;)">详情</a>'+
				 		'&nbsp<span id="tr'+i+'_td9">'+operate+'</span>'+
				 	'</td>';
			 	$('#tr'+i).empty().append(txt);
			 	$('#czTr'+i).empty().append(txt1);
		     }
			 var pagerow=$("#pagerow").val(); //每页行数
			 for(var i =data.length;i<pagerow;i++){
				 $('#tr'+i).empty();
				 $('#czTr'+i).empty();
			 }
		}else if(organizationtype=="分包商"){
			inorout="0";
			for(var i =0;i<data.length;i++){ //展现返回的表格数据
				var operate='';//操作方法
				if(data[i].status=="未审核"){
					operate='<a href="javascript:void(0)" onclick="audit(&quot;'+i+'&quot;,&quot;'+data[i].settlebillid+'&quot;,&quot;'+data[i].settlebillnumber+'&quot;,&quot;'+data[i].organization+'&quot;,&quot;'+data[i].status+"&quot;,&quot;"+data[i].needinoutallamount+"&quot;,&quot;"+data[i].factamount+"&quot;,&quot;"+data[i].needinoutremainamount+"&quot;,&quot;"+inorout+"&quot;,&quot;"+data[i].startdate+'-'+data[i].enddate+'&quot;)">审核</a>';
				}else if(data[i].status!="全部核销"){
					var _data="&settlebillid="+data[i].settlebillid+"&settlebillnumber="+data[i].settlebillnumber+"&organization="+data[i].organization+
						"&status="+data[i].status+"&needinoutallamount="+data[i].needinoutallamount+"&factamount="+data[i].factamount+"&needinoutremainamount="+data[i].needinoutremainamount+"&inorout="+inorout+"&start_end_date="+data[i].startdate+'-'+data[i].enddate;
					operate='<a href="../settlebillverificationcs/inVerification?'+encodeURI("order=9"+_data)+'" target="_self">核销</a>';
					if(inorout==0){
						operate='<a href="../settlebillverificationcs/outVerification?'+encodeURI("order=10"+_data)+'" target="_self">核销</a>';
					}
				} 
				if(data[i].status == "已审核"){
					operate='<a href="../settlebillverificationcs/inVerification?'+encodeURI("order=9"+_data)+'" target="_self">核销</a>'+
							'&nbsp;<a href="javascript:void(0)" onclick="cancel('+data[i].settlebillid+')">取消审核</a>';
					if(inorout==0){
						operate='<a href="../settlebillverificationcs/outVerification?'+encodeURI("order=10"+_data)+'" target="_self">核销</a>'+
							'&nbsp;<a href="javascript:void(0)" onclick="cancel('+data[i].settlebillid+')">取消审核</a>';
					}
				}
				txt='<td class="tdleft">&nbsp;'+
						'<a href="javascript:void(0)" onclick="detail(&quot;'+data[i].settlebillid+'&quot;,&quot;'+data[i].settlebillnumber+'&quot;,&quot;'+data[i].startdate+'-'+data[i].enddate+'&quot;,&quot;'+data[i].organization+'&quot;,&quot;'+i+'&quot;,&quot;'+inorout+'&quot;,&quot;'+data[i].conorganization+'&quot;)">'+data[i].settlebillnumber+'</a>'+
					'</td>'+
					'<td class="tdleft"><span id="tr'+i+'_td2">'+data[i].status+'</span></td>'+					
					'<td class="tdleft">'+data[i].billnumber+'</td>'+
				 	'<td class="tdcenter">'+data[i].startdate+'-'+data[i].enddate+'</td>'+
				 	'<td class="tdleft">'+data[i].organization+'</td>'+	
					'<td class="tdleft">'+data[i].conorganization+'</td>'+
					'<td class="tdleft">'+data[i].needinoutallamount+'</td>'+
					'<td class="tdleft">'+data[i].factamount+'</td>'+
					'<td class="tdleft">'+data[i].needinoutremainamount+'</td>';			 	
			   txt1 = '<td class="tdleft">'+
				 		'<a href="javascript:void(0)" onclick="detail(&quot;'+data[i].settlebillid+'&quot;,&quot;'+data[i].settlebillnumber+'&quot;,&quot;'+data[i].startdate+'-'+data[i].enddate+'&quot;,&quot;'+data[i].organization+'&quot;,&quot;'+i+'&quot;,&quot;'+inorout+'&quot;,&quot;'+data[i].conorganization+'&quot;)">详情</a>'+
				 		'&nbsp<span id="tr'+i+'_td9">'+operate+'</span>'+
				 	'</td>';
			 	$('#tr'+i).empty().append(txt);
			 	$('#czTr'+i).empty().append(txt1);
		     }
			 var pagerow=$("#pagerow").val(); //每页行数
			 for(var i =data.length;i<pagerow;i++){
				 $('#tr'+i).empty();
				 $('#czTr'+i).empty();
			 }
		}
      }	
  }); 
}

/**
 * 取消审核
 * @param settlebillid
 * @return
 */
function cancel(settlebillid){
	ymPrompt.confirmInfo({message:'是否取消审核?',autoClose: true,height:200,width:300,fixPosition:true,dragOut:false,handler: 
		function(status){
		if(status=='ok'){
			$.ajax({
				url: "../settlebillcs/cancel", 
				type:"post",
				dataType:"json",
				data:{"settlebillid":settlebillid},
				success:function(data){
					search();
					if(data.msg=="ok"){
						ymPrompt.alert("取消审核成功！");
					}else{
						ymPrompt.alert("取消审核失败！");
					}
				},
				error:function(){
					ymPrompt.alert("取消审核失败！");
				}
		});
		}else{
			return false;
		}
		}
		})
}

/**
 * @author wei.huang
 * @date 2013-11-14
 * @function 运单详情
 * @return
 */
function detail(settlebillid,settlebillnumber,start_end_date,organization,trnum,inorout,conorganization){
	var status=$("#tr"+trnum+"_td2").html();
	var data=encodeURI(settlebillid+","+settlebillnumber+","+status+","+start_end_date+","+organization+","+inorout+","+conorganization);
	if(organizationtype=="发货方"){
		window.open("../settlebillcs/receivableSettlebillDetail?data="+data);
	}else if(organizationtype=="分包商"){
		window.open("../settlebillcs/payableSettlebillDetail?data="+data);
	}
}
/**
 * @author wei.huang
 * @date 2013-11-20
 * @function 审核结算单
 * @return
 */
function audit(trnum,settlebillid,settlebillnumber,organization,status,needinoutallamount,factamount,needinoutremainamount,inorout,start_end_date){
	ymPrompt.confirmInfo({message:'确定审核吗?',autoClose: true,height:200,width:300,fixPosition:true,dragOut:false,handler: 
		function(status){
		if(status=='ok'){
		$.ajax({
		 	url: "../settlebillcs/updateStatusBySettleBillId",   
		 	type:'post',	
		 	dataType:'json', 
		 	data:{"settlebillid":settlebillid}, //参数     	               
		   	success:function(data){
		 		search();
		 		if(data.msg=="ok"){		 			
		 			var _data="settlebillid="+settlebillid+"&settlebillnumber="+settlebillnumber+"&organization="+organization+
					"&status="+status+"&needinoutallamount="+needinoutallamount+"&factamount="+factamount+"&needinoutremainamount="+needinoutremainamount+"&inorout="+inorout+"&start_end_date="+start_end_date;
		 			var operate='<a href="../settlebillverificationcs/inVerification?'+encodeURI("order=9"+_data)+'" target="_self">核销</a>';
		 			if(inorout=="0"){
		 				operate='<a href="../settlebillverificationcs/outVerification?'+encodeURI("order=10"+_data)+'" target="_self">核销</a>';
		 			}
		 			$("#tr"+trnum+"_td9").html(operate);
		 			$("#tr"+trnum+"_td2").html("已审核");
		 			ymPrompt.alert("审核成功!");
		 		}
		},
			error:function(){
			ymPrompt.alert("审核失败！");
		}
		});
		}else{
			return false;
		}
		}
		})
}
/**
 * @author wei.huang
 * @date 2013-11-20
 * @function 查询指定结算单
 * @return
 */
function search(){
	var params="";
	if($.trim($("#settlebillnumber").val())!=""){
		params+="settlebillnumber="+$.trim($("#settlebillnumber").val());
	}
	if($.trim($("#clientnumber").val())!=""){
		params+="clientnumber="+$.trim($("#clientnumber").val());
	}
	if($("#status").val()!="全部"){
		params+="&status="+$("#status").val();
	}
	if($.trim($("#startdate").val())!=""){
		params+="&startdate="+$.trim($("#startdate").val())+" 0:00:00";
	}
	if($.trim($("#enddate").val())!=""){
		params+="&enddate="+$.trim($("#enddate").val())+" 23:59:59";
	}
	if($.trim($("#organization").val())!=""){
		var inorout="";
		if(organizationtype=="发货方"){
			inorout="1";
		}else if(organizationtype=="分包商"){
			inorout="0";
		}
		$.ajax({
			async:false,
			url:"../settlebillcs/selectPartyIdByOrganization",
			type:'post',	
		 	dataType:'json',
			data:{"organization":$.trim($("#organization").val()),"inorout":inorout},
			success:function(data){
				var dataSize=data.length;
				if(dataSize>0){
					var inoutpartyid="";
					for(var i=0;i<dataSize;i++){
						inoutpartyid+=data[i].partyid;
						if(i+1<dataSize){
							inoutpartyid+=",";
						}
					}
					params+="&inoutpartyid="+inoutpartyid;
				}else{
					params+="&inoutpartyid=-1";
				}
			},
			error:function(){
				ymPrompt.alert("加载错误！");
			}
		})
	}
		params+="&conorgname="+$.trim($("#conorgname").val());
		$("#pagecode").val("1");
		f_hgrid_json(params);
}
function f_hgrid_create(pagerow){//初始化
	//参数为每页行数 
	var txt='<div class="page_nav" style="padding:3px 3px 3px 3px;">'+
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
		'</div><div style="height: 100px;"></div>'
	
		$(".page_nav").remove();
		$("#loading").remove();
		$('#hgrid_div').after(txt); //after
		$('#hgrid').after('<div id="loading" style="text-align:center;"><img src="../imgs/sys/loading.gif" border="0" /></div>'); 

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