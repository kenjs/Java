var selectDefault='全部';
function input_onfocus(obj){
	$(obj).css("border","1px solid  #a0cfe0");
}
function f_settleAdd(){
	window.location="../settlecs/settle_add?order=4&reportType=标准报价&";
}
function f_settleAdd_jt(){
	window.location="../settlecs/settle_add_jt?order=4&reportType=阶梯报价&";
}
function f_hgrid_ini() {//初始化表格
	var pagerow=15;  //参数为每页行数
	f_hgrid_create(pagerow); 	
	//注意：此处的列宽和为840px
	var txt='<tr>' +
		'<th scope="col"   width="100px" class="tdleft">&nbsp;结算类型</th>' +
		'<th scope="col"   width="100px" class="tdleft">报价方式</th>' +
		'<th scope="col"   width="220px" class="tdleft">发货方名称</th>' +
		'<th scope="col"   width="220px" class="tdleft">分包商名称</th>' +
		'<th scope="col"   width="150px" class="tdleft">操作</th>' +
		'</tr>';
	for(var i =0;i<pagerow;i++){ //生成不见的空行
		txt=txt+'<tr id="tr'+i+'" class="ctr'+i+'" onmouseover="f_onmouseover('+i+')" onmouseout="f_onmouseout('+i+')">';
	 	txt=txt+'</tr>';
	 }
	$("#hgrid").empty().append(txt);
}

function f_hgrid_json(param) {//刷新hGrid数据
	//vparam不包括页码和每页行数
	param=f_hgrid_getparam(param); //得到全部参数
 	$.ajax({
 	url: "../settlecs/selectList",
 	type:'post',
 	dataType:'json', 
 	data:param, //参数     	               
   	success:function(data){//回传函数
 		$('#loading').hide();
 		data=f_hgrid_setmsg(data); //设置总记录数,页信息等
		var txt;
		for(var i =0;i<data.length;i++){ //展现返回的表格数据
			var partyHtml;
			if('应收结算'==data[i].type){
				partyHtml='<td class="tdleft">'+data[i].organization+'</td>'+					
				'<td class="tdleft"></td>';
			}
			if('应付结算'==data[i].type){
				partyHtml='<td class="tdleft"></td>'+					
				'<td class="tdleft">'+data[i].organization+'</td>';
			}
			txt='<td class="tdleft">&nbsp;'+data[i].type+'</td>'+'<td class="tdleft">&nbsp;'+data[i].reporttype+'</td>'+partyHtml+
			 	'<td class="tdleft"><a href="javascript:void(0)" onclick="detail(&quot;'+data[i].type+'&quot;,&quot;'+data[i].topartyid+'&quot;,&quot;'+data[i].organization+'&quot;,&quot;'+data[i].reporttype+'&quot;)">详情</a>&nbsp'+
			 	'<a href="javascript:void(0)" onclick="edit(&quot;'+data[i].settlesetid+'&quot;,&quot;'+data[i].type+'&quot;,&quot;'+data[i].reporttype+'&quot;,&quot;'+data[i].topartyid+'&quot;,&quot;'+data[i].organization+'&quot;)">修改</a>&nbsp'+
			 	'<a href="javascript:void(0)" onclick="deleteSettleSet(&quot;'+data[i].settlesetid+'&quot;,&quot;'+data[i].type+'&quot;,&quot;'+data[i].reporttype+'&quot;,&quot;'+data[i].topartyid+'&quot;)">删除</a></td>'; 
		 	$('#tr'+i).empty().append(txt);
	     }
		 var pagerow=$("#pagerow").val(); //每页行数
		 for(var i =data.length;i<pagerow;i++){
			 $('#tr'+i).empty();
		 }
      }
  }); 
}
function detail(type,topartyid,organization,reporttype){
	var data="type="+type+"&topartyid="+topartyid+"&organization="+organization+"&reporttype="+reporttype;
	if(type=='应付结算'){
		data=data+"&tradeType=分包商";
	}else{
		data=data+"&tradeType=发货方";
	}
	data=data+"&random="+Math.random();
	if(reporttype=='标准报价'){
		ymPrompt.win({message:"../settlecs/settle_detail?"+encodeURI(data),width:800,height:450,fixPosition:true,dragOut:false,title:'结算设置详情',iframe:true});
	}else if(reporttype=='阶梯报价'){
		ymPrompt.win({message:"../settlecs/settle_detail_jt?"+encodeURI(data),width:800,height:450,fixPosition:true,dragOut:false,title:'结算设置详情',iframe:true});
	}
}

function edit(settlesetid,type,reporttype,topartyid,organization){
	var params="type="+type+"&topartyid="+topartyid;
	if(type=='应付结算'){
		params=params+"&tradeType=分包商";
	}else{
		params=params+"&tradeType=发货方";
	}
	params=params+"&organization="+organization+"&settlesetid="+settlesetid+"&reporttype="+reporttype;
	params=encodeURI(params);
	if('标准报价'==reporttype){
		window.location="../settlecs/settle_add?order=4&"+params;
	}else{
		window.location="../settlecs/settle_add_jt?order=4&"+params;
	}
}
function f_onmouseover(i){
	$('.ctr'+i).css("background-color","#fffddd");
}
function f_onmouseout(i){
	$('.ctr'+i).css("background-color","white");
}
function f_searchclick(){
	if($("#topartyid").val()==''){
		$("#topartyid").attr("myid",'');
	}
	if($("#topartyidfbsid").val()==''){
		$("#topartyidfbsid").attr("myid",'');
	}
	var type = $('.type div span').text();
	if(type==selectDefault){
		type="";
	}
	var reporttype = $('.reporttype div span').text();
	if(reporttype==selectDefault){
		reporttype="";
	}
	var topartyid=$.trim($("#topartyid").attr("myid"));
	var topartyidfbsid=$.trim($("#topartyidfbsid").attr("myid"));
	var params1="type="+type+"&topartyid="+topartyid+"&topartyidfbsid="+topartyidfbsid+"&reporttype="+reporttype;
	$("#pagecode").val("1");
	f_hgrid_json(params1);
}
/**
 * @author yaoyan.lin
 * @date 2013-11-21
 * @function 根据发货方名称和会员名查询城区距离列表
 * @return json数据
 */
function deleteSettleSet(settlesetid,type,reporttype,topartyid){
	ymPrompt.confirmInfo({message:'确定删除吗?',autoClose: true,height:200,width:300,fixPosition:true,dragOut:false,handler: 
		function(status){
		if(status=='ok'){
			$.ajax({
				url: "../settlecs/deleteBySettleSetId", 
				type:"post",
				dataType:"json",
				data:{"settlesetid":settlesetid,"type":type,"reporttype":reporttype,"topartyid":topartyid},
				success:function(data){
					if(data.msg=="ok"){
						f_hgrid_json("");
						ymPrompt.alert("删除成功！");
					}else{
						ymPrompt.alert("删除失败！");
					}
				},
				error:function(){
					ymPrompt.alert("删除失败！");
				}
		});
		}else{
			return false;
		}
		}
		})
}